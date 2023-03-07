package com.omarinhos.servlet.services;

import com.omarinhos.servlet.configs.ProductoServicePrincipal;
import com.omarinhos.servlet.configs.Service;
import com.omarinhos.servlet.interceptors.TransactionalJpa;
import com.omarinhos.servlet.models.entities.Categoria;
import com.omarinhos.servlet.models.entities.Producto;
import com.omarinhos.servlet.repositories.CrudRepository;
import com.omarinhos.servlet.repositories.RepositoryJpa;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
@ProductoServicePrincipal
@TransactionalJpa
public class ProductoServiceJpaImpl implements ProductoService{

    @Inject
    @RepositoryJpa
    private CrudRepository<Producto> repository;

    @Inject
    @RepositoryJpa
    private CrudRepository<Categoria> repositoryCategoria;


    @Override
    public List<Producto> listar() {
        try {
            return repository.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repository.guardar(producto);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void elminar(Long id) {
        try {
            repository.eliminar(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try {
            return repositoryCategoria.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoria.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
