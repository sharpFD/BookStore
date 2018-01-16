package com.wyg.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wyg.pojo.OrderLine;
import com.wyg.pojo.ShopCart;

public class SummaryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		ShopCart shopCart=(ShopCart) session.getAttribute("shopCart");
		if(shopCart.getOrderlines().hasNext()!=false){
		Iterator<OrderLine> orderlines = shopCart.getOrderlines();
		List<OrderLine> list=new ArrayList<>();
		while(orderlines.hasNext())
		{
			OrderLine orderLine = orderlines.next();
			list.add(orderLine);
		}
		session.setAttribute("orderlines", list);
		req.getRequestDispatcher("/shopCart.jsp").forward(req, resp);
		}
		else
			req.getRequestDispatcher("/emptyCar.html").forward(req,resp);
	}
	
}
