package com.wyg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyg.pojo.Product;
import com.wyg.util.Loaddata;

public class ChangePicServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String pos=req.getParameter("pos");
			String bookid=req.getParameter("bookid");
			Product product=Loaddata.getProductById(Long.parseLong(bookid));
			PrintWriter writer=resp.getWriter();
			switch (pos) {
			case "1":writer.write(product.getImages().trim());
				break;
			case "2":writer.write(product.getImages2().trim());
				break;
			case "3":writer.write(product.getImages3().trim());
				break;
			case "4":writer.write(product.getImages4().trim());
				break;
			case "5":writer.write(product.getImages5().trim());
				break;
			}
			if(writer!=null)writer.close();
	}
	
}
