package com.omarinhos.servlet.controllers;

import com.omarinhos.servlet.configs.ProductoServicePrincipal;
import com.omarinhos.servlet.models.entities.Producto;
import com.omarinhos.servlet.services.ProductoService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {
            Optional<Producto> o = service.porId(id);
            if (o.isPresent()) {
                service.elminar(id);
                resp.sendRedirect(req.getContextPath() + "/productos");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el producto en la base de datos.");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el id es null, se debe enviar como parámetro en la url.");
        }
    }
}
