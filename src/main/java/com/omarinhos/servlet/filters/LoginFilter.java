package com.omarinhos.servlet.filters;

import com.omarinhos.servlet.services.LoginService;
import com.omarinhos.servlet.services.LoginServiceSessionImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/carro/*", "/productos/form/*", "/productos/eliminar/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) req);
        if (username.isPresent()) {
            filterChain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).sendRedirect(((HttpServletRequest) req).getContextPath() + "/login.jsp");
            //((HttpServletResponse) resp).sendError(HttpServletResponse.SC_UNAUTHORIZED
                    //, "No estás autorizado para ingresar a esta página!");
        }
    }

}
