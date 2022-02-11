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
/**
 * @author Darling
 */
@WebServlet("/charServlet")
public class Chat extends HttpServlet
{

	/**
	 * Constructor of the object.
	 */
	public Chat()
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

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
////      查询在线人数
//		PrintWriter out = response.getWriter();
//		HttpSession session = request.getSession();
//		Object size = session.getAttribute("size");
//		System.out.println(size);
//		out.println(size);
//		request.getRequestDispatcher("/chata.jsp").forward(request, response);

//		使客户端浏览器，区分不同种类的数据，并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据。
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
//		跳转回chat.jsp
		HttpSession session = request.getSession();
		request.getRequestDispatcher("/chata.jsp").forward(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//解码
		response.setCharacterEncoding("UTF-8");
		//
		PrintWriter out = response.getWriter();
		//获取session
		HttpSession session=request.getSession();
		//获取用户名
		String username = (String) session.getAttribute("username");
		//添加监听
		Constants.addThread(session.getId(), Thread.currentThread());
		
		try{
			synchronized (Thread.currentThread())
			{
				Thread.currentThread().wait();
			}
		}catch(InterruptedException ex){
			System.out.println(Thread.currentThread()+"-->Stop!");
		}
		//推送数据
		
		System.out.println("=======>"+Constants.getMessage());
		out.println(Constants.getMessage());
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
