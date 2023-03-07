package com.omarinhos.servlet.controllers;

import com.omarinhos.servlet.models.entities.Usuario;
import com.omarinhos.servlet.services.UsuarioService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/form")
public class UsuarioFormServlet extends HttpServlet {

    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Usuario usuario = new Usuario();
        if (id > 0) {
            Optional<Usuario> usuarioOptional = service.porId(id);
            if (usuarioOptional.isPresent()) {
                usuario = usuarioOptional.get();
            }
        }
        req.setAttribute("usuario", usuario);
        req.setAttribute("title", "Formulario de usuario");
        getServletContext().getRequestDispatcher("/userForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        Map<String, String> errores = new HashMap<>();

        if (username == null || username.isBlank()) {
            errores.put("username", "El username es requerido.");
        }else if (username.length() > 12) {
            errores.put("username", "El username debe tener como máximo 12 caracteres");
        }
        if (password == null || password.isBlank()) {
            errores.put("password", "El password es requerido.");
        }
        if (email == null || email.isBlank()) {
            errores.put("email", "El email es requerido.");
        }

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = null;
        }

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);

        if (errores.isEmpty()) {
            service.guardar(usuario);
            resp.sendRedirect(req.getContextPath() + "/usuarios");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("usuario", usuario);
            req.setAttribute("title", "Formulario de usuario");
            getServletContext().getRequestDispatcher("/userForm.jsp").forward(req, resp);
        }
    }
}
