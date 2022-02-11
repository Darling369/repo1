package com.example.servlet;

import com.example.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/logServelt")
public class LogServelt extends HttpServlet
{
	/**
	 * 对象的构造函数。
	 */
	public LogServelt()
	{
		super();
	}

	/**
	 *
	 * 销毁servlet <br>
	 */
	@Override
	public void destroy()
	{
		super.destroy(); //只需将“销毁”字符串放入日志
		// 写入你的代码
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//登陆跳转

//		使客户端浏览器，区分不同种类的数据，并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据。
		request.setCharacterEncoding("utf-8");
		//获取session
		HttpSession session=request.getSession();
		session.setMaxInactiveInterval(4);
//      获取用户昵称
		Object userNamne = session.getAttribute("name");
		//保存用户名到Constans工具类中的user集合
		Constants.user.add(String.valueOf(userNamne));

//		拼接消息（昵称加字符串）
		String message=userNamne+":进入了聊天";

		//获取时间
		Long time=new Date().getTime();

		//调用工具类的addMessage方法
		Constants.addMessage(message, time.toString());
		//建立username域
		//跳转
//		request.getRequestDispatcher("/chata.jsp").forward(request,response);
		response.sendRedirect(request.getContextPath()+"/charServlet");
		//用户登录
		Constants.notifyAllThread();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		http长连接，刷新用户
//		int a=0;
//		if (a==1){
			//      查询在线人数
//		HttpSession session = request.getSession();
//		Object size = session.getAttribute("size");
//		System.out.println("size logser"+size);
//		System.out.println("=================");
//		response.getWriter().println(size);
//		}
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException
	{
		// Put your code here
	}

}
