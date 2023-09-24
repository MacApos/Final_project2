package pl.coderslab.final_project.web;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
@Order(1)
public class RequestResponseLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        HttpSession session = request.getSession();
//        if (session.getAttribute("admin") == null) {
//            session.setAttribute("admin", 0);
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}