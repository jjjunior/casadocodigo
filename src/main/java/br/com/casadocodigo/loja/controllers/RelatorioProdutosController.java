package br.com.casadocodigo.loja.controllers;


import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.helper.DataHelper;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.ProdutoRelatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {

    @Autowired
    private ProdutoDAO produtoDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ProdutoRelatorio> relatorioProduto(@RequestParam(value = "data", required = false) String data) {

        List<Produto> produtos;
        if (data == null) {
            produtos = produtoDAO.listar();
        } else {
            DataHelper dataHelper = new DataHelper(data);
            if (!dataHelper.isDataValida()) {
                return new ResponseEntity("Data " + data + " é invalida ", HttpStatus.BAD_REQUEST);
            }
            produtos = produtoDAO.listar(dataHelper.getCalendar());
        }

        ProdutoRelatorio produtoRelatorio = new ProdutoRelatorio(Calendar.getInstance(), produtos.size(), produtos);

        return new ResponseEntity<ProdutoRelatorio>(produtoRelatorio, HttpStatus.OK);

    }

}
