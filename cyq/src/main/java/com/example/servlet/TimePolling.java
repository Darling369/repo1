package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet("/TimePolling")
public class TimePolling extends HttpServlet
{

	/**
	 * Constructor of the object.
	 */
	public TimePolling()
	{
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 *servlet的doGet方法<br>
	 *当window的标记值方法等于get时，将调用此方法。
	 *@param “request客户端向服务器发送的请求
	 *@param ”response服务器向客户端发送的响应
	 *@在发生错误时抛出ServletException

	 *@在发生错误时引发IOException
	 */
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
	/**
	 *servlet的doPost方法<br>
	 *当窗体的标记值方法等于post时，将调用此方法。
	 *@param “request客户端向服务器发送的请求
	 *@param ”response服务器向客户端发送的响应
	 *@在发生错误时抛出ServletException
	 *@在发生错误时引发IOException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//AsyncContext asyncContext
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		 // 这里用Thread.sleep来模拟comet，相当于每隔5秒服务器向客户端推送一条消息  
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {  
            e.printStackTrace();
        }
        Date date=new Date();
        //设置日期格式
        SimpleDateFormat format=new SimpleDateFormat("HH:mm  yyyy/MM/dd");
//把date转为String类型
        String dataStr=format.format(date);
        System.out.println(date);
        out.println(dataStr); 
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException
	{
		// Put your code here
	}
}
