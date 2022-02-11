package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/rememberfalse.jsp")
public class Filterdemo implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        String uri = request1.getRequestURI();
        if (uri.contains("/rememberfalse.jsp")){
            Object user = request1.getSession().getAttribute("user");
            if (user!=null){
                chain.doFilter(request, response);
            }else {
                request.setAttribute("login_msg","您尚未登录，请登录");
                request.getRequestDispatcher("/demo.jsp").forward(request,response);
            }
        }
    }
}
