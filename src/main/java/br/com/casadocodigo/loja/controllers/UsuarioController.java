package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Pedido;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.ProdutoValidation;
import br.com.casadocodigo.loja.validation.UsuarioValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UsuarioValidation());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listarUsuario() {

        List<Usuario> usuarios = usuarioDAO.listarUsuario();

        ModelAndView modelAndView = new ModelAndView("usuarios");

        modelAndView.addObject("usuarios", usuarios);

        return modelAndView;
    }

    @RequestMapping("/form")
    public ModelAndView form(Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView("usuarioForm");
        return modelAndView;
    }

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView gravarUsuario(@Valid Usuario usuario, BindingResult result){

        System.out.println(result);
        if(result.hasErrors()) {
            return form(usuario);
        }

        usuarioDAO.gravar(usuario);

        //redirectAttributes.addFlashAttribute("message", "Usuario cadastrado com sucesso!");

        return new ModelAndView("redirect:/usuarios");
    }

}