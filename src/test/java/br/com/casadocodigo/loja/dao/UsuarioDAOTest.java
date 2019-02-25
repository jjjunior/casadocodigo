package br.com.casadocodigo.loja.dao;

import br.com.casadocodigo.loja.builders.UsuarioBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.models.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, DataSourceConfigurationTest.class, UsuarioDAO.class})
@ActiveProfiles("test")
public class UsuarioDAOTest {


    @Autowired
    public UsuarioDAO usuarioDAO;

    @Test
    @Transactional
    public void deveRetornarUsuarioPorEmail() {
        Usuario usuario = UsuarioBuilder.newUsuario("jjjunior@gmail.com", "junior", "abc123").buildOne();

        usuarioDAO.gravar(usuario);
        Usuario usuarioConsultado = usuarioDAO.loadUserByUsername("jjjunior@gmail.com");
        usuarioConsultado.setSenha("123456");
        usuarioDAO.alterar(usuarioConsultado);

        Assert.assertEquals(usuario.getEmail(), usuarioConsultado.getEmail());
        Assert.assertEquals(usuarioConsultado.getSenha(), "$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");

    }

}