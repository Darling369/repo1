package com.example.servlet;

import com.example.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
@WebServlet("/SendMsg")

public class SendMsg extends HttpServlet
{

	public SendMsg()
	{
		super();
	}

	@Override
	public void destroy()
	{
		super.destroy(); //只需将“销毁”字符串放入日志
		// 写入你的代码
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		使客户端浏览器，区分不同种类的数据，并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据。
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//		使客户端浏览器，区分不同种类的数据，并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据。
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();





//获取用户输入的信息
		String message=request.getParameter("message");
		//时间
		Long time=new Date().getTime();
		//获取域对象
		HttpSession session=request.getSession();
		String userName=session.getAttribute("name").toString();
		//调用工具类，加入信息
		Constants.addMessage(userName+":"+message,time.toString());
		Constants.notifyAllThread();
	}

	@Override
	public void init() throws ServletException
	{
		// Put your code here
	}

}
