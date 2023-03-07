package com.omarinhos.servlet.services;

import com.omarinhos.servlet.configs.Service;
import com.omarinhos.servlet.interceptors.TransactionalJpa;
import com.omarinhos.servlet.models.entities.Usuario;
import com.omarinhos.servlet.repositories.RepositoryJpa;
import com.omarinhos.servlet.repositories.UsuarioRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class UsuarioServiceImpl implements UsuarioService{

    @Inject
    @RepositoryJpa
    private UsuarioRepository repository;

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(repository.porUsername(username))
                    .filter(usuario -> usuario.getPassword().equals(password));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try {
            repository.guardar(usuario);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repository.eliminar(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Usuario> listar() {
        try {
            return repository.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
