package com.example.servlet;

import com.example.service.UserService;
import com.example.service.impl.UserServiceimpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String code = request.getParameter("code");
        if(code!=null){
            //调用service完成激活 
            UserService service = new UserServiceimpl();
            boolean flag = service.active(code);

            //判断标记
            String msg = null;
            if(flag){
                //激活成功
                msg = "激活成功，请<a href='demo.jsp'>登录</a>";
                System.out.println("激活成功");
            }else {
                //激活失败
                msg = "激活失败,请重新<a href='asd.jsp'>注册</a>";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);

        }
    }
}
