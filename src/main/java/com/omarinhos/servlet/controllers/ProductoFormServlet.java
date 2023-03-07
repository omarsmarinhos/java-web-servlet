package com.omarinhos.servlet.controllers;

import com.omarinhos.servlet.configs.ProductoServicePrincipal;
import com.omarinhos.servlet.models.entities.Categoria;
import com.omarinhos.servlet.models.entities.Producto;
import com.omarinhos.servlet.services.ProductoService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Producto producto = new Producto();
        Categoria categoria = new Categoria();
        producto.setCategoria(categoria);
        if (id > 0) {
            Optional<Producto> productoOptional = service.porId(id);
            if (productoOptional.isPresent()) {
                producto = productoOptional.get();
            }
        }
        req.setAttribute("categorias", service.listarCategorias());
        req.setAttribute("producto", producto);
        req.setAttribute("title", "Formulario de producto");
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");

        Integer precio;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }

        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_registro");
        Long categoriaId;
        try {
            categoriaId = Long.valueOf(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoriaId = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es requerido.");
        }
        if (sku == null || sku.isBlank()) {
            errores.put("sku", "El sku es requerido.");
        } else if (sku.length() > 10) {
            errores.put("sku", "El sku debe tener como máximo 10 caracteres");
        }
        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha_registro", "la fecha es requerida.");
        }
        if (precio.equals(0)) {
            errores.put("precio", "El precio es requerido.");
        }
        if (categoriaId.equals(0L)) {
            errores.put("categoria", "La categoría es requerido.");
        }
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            fecha = null;
        }

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = null;
        }
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setSku(sku);
        producto.setPrecio(precio);
        producto.setFechaRegistro(fecha);
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);

        if (errores.isEmpty()) {
            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategorias());
            req.setAttribute("producto", producto);
            req.setAttribute("title", "Formulario de producto");
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
