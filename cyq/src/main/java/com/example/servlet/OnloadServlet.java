package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/onloadServlet")
public class OnloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        request.getRequestDispatcher("/demo.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        //获取session
        HttpSession session = request.getSession();
        //把获取到的用户session存入到session1
        Map map = (Map) session.getAttribute("map");
        //获取用户昵称
        int size = map.size()-1;
        //把所有
        response.setCharacterEncoding("UTF-8");
        Object name = session.getAttribute("name");
        ArrayList list = new ArrayList();
//        System.out.println("s:"+name);
        for (Object o : map.keySet()) {
            Object o1 = map.get(o);
            list.add(o1);
        }
        list.remove(0);
//        System.out.println("list:"+list);
        response.getWriter().println(size+","+list);
        //        String s = JSONArray.fromObject(AllSession).toString();
        //防止重复
//        if (!AllSession.contains(session1)){
//            AllSession.add(session1);
//        }
//        session.setAttribute("listnames",AllSession);
//        //获取集合大小从而判断在线人数
//        int size = AllSession.size();
//        System.out.println("size"+size);
//        System.out.println("=================");
//        response.getWriter().println(size);
//    }
    }

}
