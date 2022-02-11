package com.example.servlet;

import com.example.domain.Admin;
import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceimpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/adminloginServlet")
public class adminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //设置编码格式
        String verifycode = request.getParameter("verifycode");
        //获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.equalsIgnoreCase(verifycode)) {
            request.setAttribute("login_msg", "验证码错误");
            request.getRequestDispatcher("/adminlogin.jsp").forward(request, response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceimpl();
        System.out.println(admin.getPassword()+admin.getUsername());
        Admin loginUser = service.admin(admin);
        if (loginUser != null) {
            //登录成功
            session.setAttribute("user", loginUser);
            String username1 = request.getParameter("username");
            session.setAttribute("username",username1);
//            List<nicheng> users = service.findName(username1);
//            session.setAttribute("name",users);
            response.sendRedirect(request.getContextPath() + "/select.jsp");
        } else {
            //登录失败
            request.setAttribute("login_msg", "用户名或密码错误");
            request.getRequestDispatcher("/adminlogin.jsp").forward(request, response);
        }

    }

}
