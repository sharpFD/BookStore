package com.wyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/JumpServlet")
public class JumpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
		String type=request.getParameter("type");
		String concretype=request.getParameter("concretype");
		String currentpage=request.getParameter("currentpage");
		if(concretype==null){
		if(page.equals("pre"))
		{
			//request.getRequestDispatcher("list.jsp?type="+type+"&page="+(Integer.parseInt(currentpage)+1));
			response.sendRedirect("list.jsp?type="+type+"&page="+(Integer.parseInt(currentpage)-1));
		}
		else if(page.equals("next"))
		{
			//request.getRequestDispatcher("list.jsp?type="+type+"&page="+(Integer.parseInt(currentpage)-1));
			response.sendRedirect("list.jsp?type="+type+"&page="+(Integer.parseInt(currentpage)+1));
		}
		else
		{
			//request.getRequestDispatcher("list.jsp?type="+type+"&page="+page);
			response.sendRedirect("list.jsp?type="+type+"&page="+page);
			System.out.println("list.jsp?type="+type+"&page="+page);
		}
		}
		else
		{
			if(page.equals("pre"))
			{
				//request.getRequestDispatcher("list.jsp?type="+type+"&concretype="+concretype+"&page="+(Integer.parseInt(currentpage)+1));
				response.sendRedirect("list.jsp?type="+type+"&concretype="+concretype+"&page="+(Integer.parseInt(currentpage)-1));
			}
			else if(page.equals("next"))
			{
				//request.getRequestDispatcher("list.jsp?type="+type+"&concretype="+concretype+"&page="+(Integer.parseInt(currentpage)-1));
				response.sendRedirect("list.jsp?type="+type+"&concretype="+concretype+"&page="+(Integer.parseInt(currentpage)+1));
			}
			else
			{
				//request.getRequestDispatcher("list.jsp?type="+type+"&concretype="+concretype+"&page="+page);
				response.sendRedirect("list.jsp?type="+type+"&concretype="+concretype+"&page="+page);
				System.out.println("concretype!=null,page="+page);
			}
		}
	}
}
