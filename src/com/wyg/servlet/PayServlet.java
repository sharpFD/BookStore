package com.wyg.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wyg.pojo.Evaluate;
import com.wyg.pojo.Order;
import com.wyg.pojo.Payway;
import com.wyg.util.Loaddata;

public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//更改订单状态为已支付
		//更改订单支付方式为选择的支付方式
		Order order=null;
			 order=Loaddata.findOrderByOrderId(Long.parseLong(request.getParameter("orderid")));
		String payway=request.getParameter("pay");
		order.setStatus("已支付");
		order.setPayway(new Payway(Integer.parseInt(payway)));
		Loaddata.updateOrderById(order);
		HttpSession session=request.getSession();
		@SuppressWarnings("unchecked")
		List<Evaluate>evaluates=(List<Evaluate>) session.getAttribute("evaluates");
		for(int i=0;i<evaluates.size();i++)
		{
			System.out.println(evaluates.get(i));
			evaluates.get(i).setContent("");
			evaluates.get(i).setImages("");
			Loaddata.insertEvaluate(evaluates.get(i));
		
		}
		request.getRequestDispatcher("finish.jsp?orderid="+order.getId()+"&code="+Math.random()).forward(request, response);
	}

}
