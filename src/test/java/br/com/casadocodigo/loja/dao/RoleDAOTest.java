package br.com.casadocodigo.loja.dao;

import br.com.casadocodigo.loja.builders.RoleBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.models.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, RoleDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class RoleDAOTest {

    @Autowired
    private RoleDAO roleDAO;

    @Transactional
    @Test
    public void gravar() {

        Role role = RoleBuilder.newRole().buildOne();

        roleDAO.gravar(role);

        Role roleConsultada = roleDAO.getRoleBy(role.getNome());

        Assert.assertEquals(role, roleConsultada);

    }

    @Transactional
    @Test
    public void getAllRoles() {

        List<Role> roles = RoleBuilder.newRole().more(3).buildAll();

        roles.forEach(role -> roleDAO.gravar(role));

        List<Role> rolesConsultada = roleDAO.getAllRoles();

        Assert.assertEquals(roles.size(), rolesConsultada.size());

    }
}