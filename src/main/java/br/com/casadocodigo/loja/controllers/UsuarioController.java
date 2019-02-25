package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private RoleDAO roleDAO;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UsuarioValidation());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        ModelAndView modelAndView = new ModelAndView("usuarios/usuario");
        modelAndView.addObject("usuarios", usuarios);
        return modelAndView;
    }

    @RequestMapping("/form")
    public ModelAndView form(Usuario usuario, String mensagem) {
        ModelAndView modelAndView = new ModelAndView("usuarios/form");
        modelAndView.addObject("message", mensagem);
        return modelAndView;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public ModelAndView listarRoles(String email) {
        List<Role> roles = roleDAO.getAllRoles();
        Usuario usuario = usuarioDAO.loadUserByUsername(email);
        ModelAndView modelAndView = new ModelAndView("usuarios/role");
        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @RequestMapping(value = "/alterarRoles", method = RequestMethod.POST)
    public ModelAndView alterarRoles(Usuario usuario, String email, RedirectAttributes redirectAttributes) {
        Usuario usuarioAlterado = usuarioDAO.loadUserByUsername(email);
        usuarioAlterado.setRoles(usuario.getRoles());
        usuarioDAO.alterar(usuarioAlterado);
        redirectAttributes.addFlashAttribute("message", "Permissões alteradas com sucesso!");
        return new ModelAndView("redirect:/usuarios");
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView gravarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return form(usuario, "");
        }

        try {
            Usuario usuario1 = usuarioDAO.loadUserByUsername(usuario.getEmail());
            return form(usuario, "Usuario: " + usuario1.getEmail() + " já existe em nosso cadastro");
        } catch (UsernameNotFoundException e) {
            usuarioDAO.gravar(usuario);
            redirectAttributes.addFlashAttribute("message", "Usuario cadastrado com sucesso!");
            return new ModelAndView("redirect:/usuarios");
        }
    }
}