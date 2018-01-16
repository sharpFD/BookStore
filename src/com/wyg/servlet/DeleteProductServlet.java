package com.wyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wyg.pojo.ShopCart;

public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookid=request.getParameter("bookid");
		String version=request.getParameter("version");
		HttpSession session=request.getSession();
		ShopCart shopCart=(ShopCart) session.getAttribute("shopCart");
		shopCart.removeProduct(Long.parseLong(bookid), version);
		request.getRequestDispatcher("shopCart.jsp?code="+Math.random());
	/*	response.sendRedirect("shopCart.jsp?code="+new Date());*/
	}
}
