package br.com.casadocodigo.loja.dao;

import br.com.casadocodigo.loja.models.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class RoleDAO {

    @PersistenceContext
    private EntityManager manager;

    public void gravar(Role role) {
        manager.persist(role);
    }

    public Role getRoleBy(String nome) {
        TypedQuery<Role> query = manager.createQuery("select r from Role r where r.nome = :nome", Role.class);
        query.setParameter("nome", nome);

        Role role = query.getSingleResult();
        return role;
    }


    public List<Role> getAllRoles() {
        TypedQuery<Role> query = manager.createQuery("select r from Role r", Role.class);
        List<Role> roles = query.getResultList();
        return roles;


    }
}
