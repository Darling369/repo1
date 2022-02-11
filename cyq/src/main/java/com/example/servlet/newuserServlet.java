package com.example.servlet;

import com.example.domain.ABC;
import com.example.domain.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceimpl;
import com.example.util.MailUtils;
import com.example.util.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/newuserServlet")
public class newuserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //把表单数据转成map
        Map<String, String[]> map = request.getParameterMap();
        map.forEach((k,v) ->{
            System.out.println(k + ":" + v);
                });
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceimpl();
//        //判断用户是否已存在，如果存在着报错反之注册
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
        List<ABC> news = service.findcopy();
        int q = news.size();
        int num = 0;
        int c = 0;
        for (ABC a:news) {
            boolean b = a.getUsername().equals(username);
            if (b == false) {
                num++;
            }
            if (b) {
                c++;
            }
        }
        //如果用户不存在就注册执行下面代码
            if (num==q) {
                //设置邮箱激活码，唯一字符串
                user.setCode(UuidUtil.getUuid());
                //设置激活状态
                user.setStatus("N");
                //数据库添加新用户
                service.addUser(user);
                //激活邮件发送
//                ?code="+user.getCode()+"
//                String content="<a href=‘http://localhost:8080/cyq/activeUserServlet?code=\"+user.getCode()+\"'>点击激活邮箱</a>";

                String content=("<html lang='zh-CN'><head ><meta charset='utf-8'>"
                        + "</head><body>这是一封激活邮件"
                        + "<a href=\'http://localhost:8080/cyq/activeUserServlet?code="+user.getCode()+"'>点击激活邮箱</a>" +
                        "</body></html>");
//                2298820678@qq.com
                MailUtils.sendMail(user.getEmail(),content,"激活邮件");
                request.setAttribute("login_msg", "注册成功,请激活");
                System.out.println("用户：注册成功"+user);
                request.getRequestDispatcher("/demo.jsp").forward(request, response);
            } else if(c==1) {
                request.setAttribute("login_msg", "该用户已存在");
                request.getRequestDispatcher("/asd.jsp").forward(request, response);
//                    response.sendRedirect(request.getContextPath() + "/rememberfalse.html");
            }
        }
    }