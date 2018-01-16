package com.wyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wyg.pojo.Product;
import com.wyg.pojo.ShopCart;
import com.wyg.util.Loaddata;

public class AddShopServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type=req.getParameter("type");
		String concretype=req.getParameter("concretype");
		HttpSession session=req.getSession();
		ShopCart shopCart=(ShopCart)session.getAttribute("shopCart");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("GBK");
		if(type!=null){
		long bookid =Integer.parseInt(req.getParameter("bookid"));
		Product product=Loaddata.getProductById(bookid);
		shopCart.addProduct(product,"1");
		//LogoutServlet.getShopCart(shopCart);
		if(concretype!=null)
		req.getRequestDispatcher("list.jsp?type="+type+"&concretype="+concretype).forward(req, resp);
		else
			{req.getRequestDispatcher("list.jsp?type="+type).forward(req, resp);
			}
		}
		else
		{
			String bookid=req.getParameter("bookid");
			String version=req.getParameter("version");
			int num=Integer.parseInt(req.getParameter("num"));
			System.out.println("进来了bookid"+bookid+"version="+version+"num="+num);
			Product product=Loaddata.getProductById(Long.parseLong(bookid));
			for(int i=0;i<num;i++)
			{
				shopCart.addProduct(product,version);
			}
			req.getRequestDispatcher("viewBook.jsp?id="+product.getId()).forward(req, resp);
		}
}
}
