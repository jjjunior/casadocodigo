package br.com.casadocodigo.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.casadocodigo.loja.models.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RoleDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Role role) {
		manager.persist(role);
	}

	public List<Role> getAllRoles() {
		TypedQuery<Role> query = manager.createQuery("select r from Role r", Role.class);
		List<Role> roles = query.getResultList();
		return roles;


	}
}
