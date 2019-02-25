package br.com.casadocodigo.loja.dao;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, ProdutoDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProdutoDAOTest {


    @Autowired
    private ProdutoDAO produtoDao;

    private CountDownLatch lock = new CountDownLatch(1);


    @Transactional
    @Test
    public void gravar() {

        Produto produto = ProdutoBuilder.newProduto().buildOne();

        produtoDao.gravar(produto);

        Produto produtoConsultado = produtoDao.listar().get(0);

        Assert.assertEquals(produto, produtoConsultado);

    }

    @Test
    @Transactional
    public void deveSomarTodosOsPrecosPorTipoLivro() {

        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
        List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();

        livrosImpressos.stream().forEach(produtoDao::gravar);
        livrosEbook.stream().forEach(produtoDao::gravar);

        BigDecimal valor = produtoDao.somaPrecosPorTipo(TipoPreco.EBOOK);
        Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
    }

    @Test
    @Transactional
    public void deveRetornarProdutosPorData() throws Exception {

        Calendar dataLivro1 = new GregorianCalendar();
        dataLivro1.set(2017, 10, 01);

        Calendar dataLivro2 = new GregorianCalendar();
        dataLivro1.set(2017, 11, 01);

        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN, dataLivro1).more(2).buildAll();
        List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN, dataLivro2).more(2).buildAll();

        livrosImpressos.stream().forEach(produtoDao::gravar);
        livrosEbook.stream().forEach(produtoDao::gravar);


        lock.await(3000, TimeUnit.MILLISECONDS);

        List<Produto> livrosImpressosConsulta = produtoDao.listar(dataLivro2);

        Assert.assertEquals(livrosImpressosConsulta.size(), 3);
    }

    @Test
    @Transactional
    public void deveRetornarTodosOsProdutos() throws Exception {

        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
        List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();

        livrosImpressos.stream().forEach(produtoDao::gravar);
        livrosEbook.stream().forEach(produtoDao::gravar);

        lock.await(1000, TimeUnit.MILLISECONDS);

        List<Produto> livrosImpressosConsulta = produtoDao.listar();

        Assert.assertEquals(livrosImpressosConsulta.size(), 8);
    }

    @Transactional
    @Test
    public void deveRetornarProdutoPorId() {

        Produto produto = ProdutoBuilder.newProduto().buildOne();

        produtoDao.gravar(produto);

        Produto produtoConsultado = produtoDao.listar().get(0);
        Produto produtoConsultadoPorFind = produtoDao.find(produtoConsultado.getId());

        Assert.assertEquals(produtoConsultado.getId(), produtoConsultadoPorFind.getId());

    }

}
