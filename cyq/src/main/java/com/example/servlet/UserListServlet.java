package com.example.servlet;

import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service= new UserServiceimpl();
        List<User> users = service.findAllUser();
        request.setAttribute("users",users);
        request.getRequestDispatcher("/admin.jsp").forward(request,response);
//        //客户端输入的用户数据
//        List lista = new ArrayList();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
////        //找回密码输入的用户名
//        String yhm = request.getParameter("yhm");
//        lista.add(username);
////        request.setAttribute("username",username);
////        request.setAttribute("password",password);





    }
}
