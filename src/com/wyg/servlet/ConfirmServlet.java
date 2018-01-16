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

public class ConfirmServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//现在可以保证传过来一定有产品。
			//pos里面存储了选中产品的pos，此时不需要用到orderlines
			//此时，需要new一个order对象
			ShopCart shopCart=(ShopCart) req.getSession().getAttribute("shopCart");
			String checkBox[]=req.getParameterValues("newslist");
			List<String>pos=new ArrayList<>();
			Iterator<OrderLine> orderlines = shopCart.getOrderlines();
			List<OrderLine> list=new ArrayList<>();
			while(orderlines.hasNext())
			{
				OrderLine orderLine = orderlines.next();
				list.add(orderLine);
			}
			
			for(int i=0;i<checkBox.length;i++)
			{
				System.out.println(checkBox[i]);
				String s=checkBox[i].split("#")[1];
				String amount=req.getParameter("text_box"+s);
				list.get(i).setAmount(Long.parseLong(amount));
				pos.add(s);
			}
			
			
			//list表示订单项
			HttpSession session=req.getSession();
			session.setAttribute("orderlines",list);
			session.setAttribute("pos",pos);
			req.getRequestDispatcher("confirm.jsp?code="+Math.random()).forward(req, resp);
	}
}
