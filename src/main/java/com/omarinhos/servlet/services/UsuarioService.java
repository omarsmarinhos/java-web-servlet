package com.omarinhos.servlet.services;

import com.omarinhos.servlet.models.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> login(String username, String password);
}
