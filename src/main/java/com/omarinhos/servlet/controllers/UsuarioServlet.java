package com.omarinhos.servlet.controllers;

import com.omarinhos.servlet.models.Usuario;
import com.omarinhos.servlet.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService usuarioService = new UsuarioServiceImpl(conn);
        List<Usuario> usuarios = usuarioService.listar();

        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = loginService.getUsername(req);

        req.setAttribute("usuarios", usuarios);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", "Listado de usuarios");
        getServletContext().getRequestDispatcher("/userListar.jsp").forward(req, resp);
    }
}
