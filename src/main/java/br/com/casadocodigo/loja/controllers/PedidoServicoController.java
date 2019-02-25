package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.models.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoServicoController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView consultaPedido() {

        final String uri = "https://book-payment.herokuapp.com/orders";

        List<Pedido> pedidos = restTemplate.getForObject(uri, List.class);

        ModelAndView modelAndView = new ModelAndView("pedidos/pedido");

        modelAndView.addObject("pedidos", pedidos);

        return modelAndView;
    }
}