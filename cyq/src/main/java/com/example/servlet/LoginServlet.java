package com.example.servlet;

import com.example.domain.User;
import com.example.domain.nicheng;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *用户登录后台
 * @author Darling
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    ArrayList list =new ArrayList();
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
        //判断后台自动生成的验证码是不是相等于输入的验证码
        if (!checkcode_server.equalsIgnoreCase(verifycode)) {
            request.setAttribute("login_msg", "验证码错误");
            request.getRequestDispatcher("/demo.jsp").forward(request, response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceimpl();
        User loginUser = service.login(user);
        if (loginUser != null) {
            //登录成功
            request.setCharacterEncoding("utf-8");
            session.setAttribute("user", loginUser);
            //获取用户登录时输入的用户名
            String username1 = request.getParameter("username");

            //获取监听器的map
            Map map1 = (Map) session.getAttribute("map");
//            System.out.println("登录页面输出获取map"+map1);

            //查询用户名的昵称
            List<nicheng> users = service.findName(username1);

//            List list = (List) session.getAttribute("list");
            Object iiiii = session.getAttribute("iiiii");
            if (loginUser.getStatus().equals("Y")){
                if(iiiii==null){
                    if (!map1.containsKey(username1)){
                        //将用户名存入session
                        session.setAttribute("username",username1);
                        //存入session
                        session.setAttribute("name",users);
//                        list.add(session);
                        session.setAttribute("iiiii","1");
                        //跳转
                        response.sendRedirect(request.getContextPath() + "/logServelt");
                    }else {
                    request.setAttribute("login_msg", "该用户已登录");
                    request.getRequestDispatcher("/demo.jsp").forward(request, response);
                    }
                }else {
                    request.setAttribute("login_msg", "浏览器已有账号登录");
                    request.getRequestDispatcher("/demo.jsp").forward(request, response);
                }

            }else {
                //登录失败
                request.setAttribute("login_msg", "邮箱未激活");
                request.getRequestDispatcher("/demo.jsp").forward(request, response);
            }
        } else {
            //登录失败
            request.setAttribute("login_msg", "账号密码错误");
            request.getRequestDispatcher("/demo.jsp").forward(request, response);
        }
    }
}
