package br.com.casadocodigo.loja.builders;

import br.com.casadocodigo.loja.models.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoleBuilder {

    private List<Role> roles = new ArrayList<>();

    private RoleBuilder(Role role) {
        roles.add(role);
    }

    public static RoleBuilder newRole(String nome) {
        Role role = create(nome);
        return new RoleBuilder(role);
    }

    public static RoleBuilder newRole() {
        Role role = create("ROLE_VAREJO");
        return new RoleBuilder(role);
    }

    private static Role create(String nome) {
        Role role = new Role();
        role.setNome(nome);
        return role;
    }

    public RoleBuilder more(int number) {
        Random random = new Random();
        Role base = roles.get(roles.size() - 1);
        for (int i = 0; i < number; i++) {
            roles.add(create(base.getNome() + random.nextInt(1234545677)));
        }
        return this;
    }

    public Role buildOne() {
        return roles.get(0);
    }

    public List<Role> buildAll() {
        return roles;
    }
}