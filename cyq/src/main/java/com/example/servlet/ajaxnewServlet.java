package com.example.servlet;

import com.example.domain.username;
import com.example.service.UserService;
import com.example.service.impl.UserServiceimpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ajaxnewServlet")
public class ajaxnewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setCharacterEncoding("UTF-8");
            UserService service = new UserServiceimpl();
            List<username> users = service.findmaxid();
            request.setAttribute("users",users);
            response.setCharacterEncoding("UTF-8");
            String s = JSONArray.fromObject(users).toString();
    }
}
