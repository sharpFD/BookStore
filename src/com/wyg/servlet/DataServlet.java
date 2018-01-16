package com.wyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyg.pojo.Data;
@WebServlet("/DataServlet")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String priceid=request.getParameter("priceid");
			String publishid=request.getParameter("publishid");
			String orderid=request.getParameter("orderid");
			String type=request.getParameter("type");
			String concretype=request.getParameter("concretype");
			if("null".equals(priceid))priceid=null;
			if("null".equals(publishid))publishid=null;
			if("null".equals(orderid))orderid=null;
			if("null".equals(type))type=null;
			if("null".equals(concretype))concretype=null;
			Data.getInstance().setPriceid(priceid);
			Data.getInstance().setPublishid(publishid);
			Data.getInstance().setOrderid(orderid);
			Data.getInstance().setType(type);
			Data.getInstance().setConcretype(concretype);
			//request.getRequestDispatcher("ListServlet").forward(request, response);
			response.sendRedirect("ListServlet?type="+type+"&concretype="+concretype);
	}
}
