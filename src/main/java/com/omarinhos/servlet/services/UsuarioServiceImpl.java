package com.omarinhos.servlet.services;

import com.omarinhos.servlet.models.Usuario;
import com.omarinhos.servlet.repositories.UsuarioRepository;
import com.omarinhos.servlet.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(Connection conn) {
        repository = new UsuarioRepositoryImpl(conn);
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(repository.porUsername(username))
                    .filter(usuario -> usuario.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
