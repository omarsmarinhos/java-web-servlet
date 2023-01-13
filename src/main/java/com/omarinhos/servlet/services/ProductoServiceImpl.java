package com.omarinhos.servlet.services;

import com.omarinhos.servlet.models.Categoria;
import com.omarinhos.servlet.models.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService{

    @Override
    public List<Producto> listar() {
        return null;
    }

    public Optional<Producto> buscarProducto(String nombre) {
        return listar().stream().filter(p -> p.getNombre().contains(nombre)).findFirst();
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream()
                .filter(p -> p.getId().equals(id))
                .findAny();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void elminar(Long id) {

    }

    @Override
    public List<Categoria> listarCategorias() {
        return null;
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.empty();
    }
}
