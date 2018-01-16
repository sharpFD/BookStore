package com.wyg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyg.pojo.Product;
import com.wyg.util.Loaddata;

public class ProductEnoughServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String version=req.getParameter("version");
			String bookid=req.getParameter("bookid");
			System.out.println("bookid="+bookid+"version="+version);
			Product product=Loaddata.getProductById(Long.parseLong(bookid));
			double getprice=0;
			switch (version) {
			case "1":
				getprice=product.getPrice();
				break;

			case "2":
				getprice=product.getPrice2();
				break;
			case "3":
				getprice=product.getPrice3();
				break;
			}
			PrintWriter writer=resp.getWriter();
			writer.write(getprice+"");
			if(writer!=null)writer.close();
	}
}
