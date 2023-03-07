package com.omarinhos.servlet.services;

import com.omarinhos.servlet.models.entities.Categoria;
import com.omarinhos.servlet.models.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> listar();
    Optional<Producto> porId(Long id);
    void guardar(Producto producto);
    void elminar(Long id);
    List<Categoria> listarCategorias();
    Optional<Categoria> porIdCategoria(Long id);
}
