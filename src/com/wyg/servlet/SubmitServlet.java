package com.wyg.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.wyg.pojo.Evaluate;
import com.wyg.pojo.Order;
import com.wyg.pojo.OrderLine;
import com.wyg.pojo.Payway;
import com.wyg.pojo.Product;
import com.wyg.pojo.Receiver;
import com.wyg.pojo.ShopCart;
import com.wyg.pojo.User;
import com.wyg.util.Loaddata;
public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String date=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("GBK");
		//1.存储收货人信息 完成
		//新增，新增评论
		//2.存储订单信息	  完成
		//3.减少库存信息	  完成
		//4.减少购物车		  完成
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String tel=request.getParameter("telephone");
	  	//1.创建订单
	  	long id=createOrder(request,name, address, tel);  	
	  	//2.创建订单项
	  	createOrderLines(request, id);
	  	//3.减少相应库存
	  	updateStock(request);
	  	//4.减少购物车
	  	removeShopCart(request);
	  	request.getRequestDispatcher("pay.jsp?orderid="+id).forward(request,response);
	}
	public long createOrder(HttpServletRequest request,String name,String address,String tel)
	{
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		float total=(float) session.getAttribute("total");
		Receiver receiver= new Receiver(1, name, address, tel, user);
		Loaddata.insertReceiver(receiver);
		date=new Date().toString();
		Order order=new Order(1, 1, date, total, new Payway(1), "未支付",user,receiver);
		session.setAttribute("order", order);
		return Loaddata.insertOrder(order);
	}
	
	@SuppressWarnings("unchecked")
	public void createOrderLines(HttpServletRequest request,long id)
	{
		HttpSession session=request.getSession();
		List<OrderLine>orderLines=(List<OrderLine>)session.getAttribute("orderlines");
	  	List<String>pos=(List<String>)session.getAttribute("pos");
	  	List<Product>duProducts=new ArrayList<>();
	  	User user=(User) session.getAttribute("user");
		for(int i=0;i<pos.size();i++){
			Product product=orderLines.get(Integer.parseInt(pos.get(i))).getProduct();
			duProducts.add(product);
			//Evaluate evaluate=new Evaluate(1, "", "", user, product, 0, "", 0);
			OrderLine getOrderLine=orderLines.get(Integer.parseInt(pos.get(i)));
			String version=getOrderLine.getProduct_version();
			OrderLine orderLine=new OrderLine(1,getOrderLine.getAmount(), getOrderLine.getSingle_price(), product, new Order(id), version);
			Loaddata.insertOrderlines(orderLine);
			//Loaddata.insertEvaluate(evaluate);
			}
		List<Product> newlist=removeDuplicate(duProducts);
		List<Evaluate> evaluates=new ArrayList<>();
		for(int i=0;i<newlist.size();i++)
		{
			Product product=newlist.get(i);
			Evaluate evaluate=new Evaluate(1, "", date, user, product, 0, "", 0);
			evaluates.add(evaluate);
		}
		session.setAttribute("evaluates", evaluates);
	}
	public   static   List<Product>  removeDuplicate(List<Product> list)   { 
		   for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )   { 
		    for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )   { 
		      if  (list.get(j).equals(list.get(i)))   { 
		        list.remove(j); 
		      } 
		    } 
		  } 
		   return list;
		} 
	@SuppressWarnings("unchecked")
	public void updateStock(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		List<OrderLine>orderLines=(List<OrderLine>)session.getAttribute("orderlines");
	  	List<String>pos=(List<String>)session.getAttribute("pos");			
	  	
		for(int i=0;i<pos.size();i++){
			Product product=orderLines.get(Integer.parseInt(pos.get(i))).getProduct();
			OrderLine getOrderLine=orderLines.get(Integer.parseInt(pos.get(i)));
			long count=getOrderLine.getAmount();
			long beforecount=product.getStock();
			long beforesale=product.getSale_num();
			Loaddata.updateStock(new Product(product.getId(), beforecount-count));
			Loaddata.updatesalenum(new Product(product.getId(), beforesale+count, ""));
			}
	}
	@SuppressWarnings("unchecked")
	public void removeShopCart(HttpServletRequest request)
	{
		ShopCart shopCart=(ShopCart) request.getSession().getAttribute("shopCart");
		List<OrderLine>orderLines=(List<OrderLine>)request.getSession().getAttribute("orderlines");
	  	List<String>pos=(List<String>)request.getSession().getAttribute("pos");			
	  	
		for(int i=0;i<pos.size();i++){
			Product product=orderLines.get(Integer.parseInt(pos.get(i))).getProduct();
			OrderLine getOrderLine=orderLines.get(Integer.parseInt(pos.get(i)));
			shopCart.removeProduct(product.getId(), getOrderLine.getProduct_version());
			}
	}
	
}
