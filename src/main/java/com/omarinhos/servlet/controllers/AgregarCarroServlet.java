package com.omarinhos.servlet.controllers;

import com.omarinhos.servlet.configs.ProductoServicePrincipal;
import com.omarinhos.servlet.models.Carro;
import com.omarinhos.servlet.models.ItemCarro;
import com.omarinhos.servlet.models.Producto;
import com.omarinhos.servlet.services.ProductoService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Inject
    private Carro carro;
    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Producto> producto = service.porId(id);

        if (producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());

            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }

}
