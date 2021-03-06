package br.com.casadocodigo.loja.dao;

import br.com.casadocodigo.loja.models.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;


@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService, Serializable {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Usuario loadUserByUsername(String email) {
        List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
                .setParameter("email", email)
                .getResultList();

        if (usuarios.isEmpty()) {
            throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
        }

        return usuarios.get(0);
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
        return usuarios;
    }

    public void gravar(Usuario usuario) {
        manager.persist(usuario);
    }

    public void alterar(Usuario usuario) {
        manager.merge(usuario);
    }
}