package com.omarinhos.servlet.controllers;

import com.omarinhos.servlet.services.LoginService;
import com.omarinhos.servlet.services.LoginServiceCookieImpl;
import com.omarinhos.servlet.services.LoginServiceSessionImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername(req);
        if (username.isPresent()) {
            HttpSession session = req.getSession();
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
