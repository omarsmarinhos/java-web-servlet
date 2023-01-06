package com.omarinhos.servlet.services;

import com.omarinhos.servlet.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> login(String username, String password);
    Optional<Usuario> porId(Long id);
    void guardar(Usuario usuario);
    void eliminar(Long id);
    List<Usuario> listar();
}
