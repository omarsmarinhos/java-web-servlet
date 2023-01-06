package com.omarinhos.servlet.controllers;

import com.omarinhos.servlet.models.Carro;
import com.omarinhos.servlet.models.ItemCarro;
import com.omarinhos.servlet.models.Producto;
import com.omarinhos.servlet.services.ProductoService;
import com.omarinhos.servlet.services.ProductoServiceImpl;
import com.omarinhos.servlet.services.ProductoServiceJdbcImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Long id = Long.parseLong(req.getParameter("id"));
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        Optional<Producto> producto = service.porId(id);

        if (producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());

            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");

            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }

}
