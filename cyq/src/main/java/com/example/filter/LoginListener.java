package com.example.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.HashMap;

@WebListener
public class LoginListener implements HttpSessionListener, ServletContextListener, HttpSessionAttributeListener {
    HashMap<Object,Object> map = new HashMap<>();
    ArrayList list = new ArrayList();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象已经创建");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象已经销毁");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session对象已经创建");
        se.getSession().setAttribute("iiiii",null);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        System.out.println("已调用销毁程序"+session);
        Object username = session.getAttribute("username");
        if(username!=null){
            map.remove(username);
            System.out.println("用户"+username+"已销毁");
        }
//        if(session!=null){
//            list.remove(session);
//        }
        session.setAttribute("iiiii",null);

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        Object username = session.getAttribute("username");
        Object name = session.getAttribute("name");
        map.put(username,name);
        session.setAttribute("map",map);
//        if(!list.contains(session)){
//            list.add(session);
//        }
//        session.setAttribute("list",list);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeRemoved(event);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeReplaced(event);
    }
}
