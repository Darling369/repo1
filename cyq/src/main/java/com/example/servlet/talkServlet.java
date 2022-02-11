package com.example.servlet;

import com.example.domain.username;
import com.example.service.UserService;
import com.example.service.impl.UserServiceimpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/talkServlet")
public class talkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String text = request.getParameter("text");
//        username usera  = new username();
//        request.setAttribute("text",usera.list.get(1));
//        System.out.println(text);
//        request.setAttribute("text", text);
//        request.getRequestDispatcher("/rememberfalse.jsp").forward(request, response);

        //向数据库添加要聊天记录
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        username a = new username();
        try {
            BeanUtils.populate(a, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceimpl();
        service.talkabout(a);
        System.out.println("talk"+a);

//        List<username> users = service.findAll();
//        request.setAttribute("users",users);
//list集合转为json
//        response.setCharacterEncoding("UTF-8");
//        System.out.println(JSONArray.fromObject(users).toString());
//        String s = JSONArray.fromObject(users).toString();
//        response.getWriter().write(s);
//        String talk = request.getParameter("talk");
//        response.getWriter().write(talk);

//        Map<String, Object> map1 =new HashMap<String,Object>();
//        map1.put("talk","草泥马");
//        ObjectMapper mapper = new ObjectMapper();
//response.getWriter().write(users.get(1));
        request.getRequestDispatcher("/rememberfalse.jsp").forward(request, response);
    }
}
