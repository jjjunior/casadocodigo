package br.com.casadocodigo.loja.builders;

import br.com.casadocodigo.loja.models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UsuarioBuilder {

    private List<Usuario> usuarios = new ArrayList<>();

    private UsuarioBuilder(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static UsuarioBuilder newUsuario(String email) {
        Usuario usuario = create(email);
        return new UsuarioBuilder(usuario);
    }

    public static UsuarioBuilder newUsuario(String email, String nome, String senha) {
        Usuario usuario = create(email, nome, senha);
        return new UsuarioBuilder(usuario);
    }

    private static Usuario create(String email, String nome, String senha) {
        Usuario usuario = new Usuario(email, nome, senha);
        return usuario;
    }

    private static Usuario create(String email) {
        Usuario usuario = new Usuario(email);
        return usuario;
    }

    public UsuarioBuilder more(int number) {
        Random random = new Random();
        Usuario base = usuarios.get(usuarios.size() - 1);
        for (int i = 0; i < number; i++) {
            usuarios.add(create(base.getEmail() + random.nextInt(1234545677), base.getNome(), base.getSenha()));
        }
        return this;
    }

    public Usuario buildOne() {
        return usuarios.get(0);
    }

    public List<Usuario> buildAll() {
        return usuarios;
    }
}