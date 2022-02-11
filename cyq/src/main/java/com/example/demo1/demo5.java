package com.example.demo1;

import com.example.domain.username;
import com.example.service.UserService;
import com.example.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet( "/demo5")
public class demo5 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //客户端输入的用户数据
        UserService service = new UserServiceimpl();
        List<username> users = service.findAll();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        //找回密码输入的用户名
        String yhm = request.getParameter("yhm");
//        HashMap<String, String> map = new HashMap();
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        List lista = new ArrayList();
        lista.add(username);
    }


    
}



//        if (map.containsKey(username)) {
//            //当用户名存在，判断密码是否输入正确
//            if (password.equals(map.get(username))) {
//                System.out.println("密码正确");
//                request.getRequestDispatcher("/yellow.html").forward(request, response);
//            } else {
//                System.out.println("密码错误");
//                request.getRequestDispatcher("/false.html").forward(request, response);
//            }
//        } else if (username != null & password != null) {
//            //注册的新用户资料保存到map中
//            System.out.println("成功注册");
//            map.put(username, password);
//            request.getRequestDispatcher("/new.html").forward(request, response);
//            request.setAttribute("username", "password");
//            //用户找回密码把输入的传给yhm
//        } else if (map.containsKey(yhm)) {
//            String a = map.get(yhm);
//            System.out.println("找回密码");
//            System.out.println("账号：" + yhm + "  ； 成功找回密码：" + a);
//            response.setCharacterEncoding("GBK");
//            //直接打印密码传给网页
//            request.setAttribute("password",a);
//            request.getRequestDispatcher("/password.jsp").forward(request, response);
//        } else {
//            request.getRequestDispatcher("/rememberfalse.html").forward(request, response);
//            System.out.println("用户：" + yhm + "想找回密码但用户名不存在");
//        }
//        System.out.println("用户名：" + username);
//        System.out.println("密码：" + password);
//        for (String key : map.keySet()) {
//            System.out.println("账号=" + key + ", 密码=" + map.get(key));
//            System.out.println("---------------------------");
//        }
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyq", "lxq", "123456");
//            String sql = "select * from pass  where username = 2298820678 ";
//            Statement stmt = con.createStatement();
//            int counter = stmt.executeUpdate(sql);
//            System.out.println(counter);
//            stmt.close();
//            con.close();
//        }catch (SQLException E){
//            System.out.println(E);
//        }catch (ClassNotFoundException e){
//            System.out.println(e);
//        }
//    }
//    }
//}


//    public static void main(String username,String password) throws Exception {
//
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyq", "lxq", "123456");
//        String sql = "select * from pass  where username = ";
//        Statement stmt = con.createStatement();
//        int counter = stmt.executeUpdate(sql);
//        System.out.println(counter);
//        stmt.close();
//        con.close();
//    }
//}
