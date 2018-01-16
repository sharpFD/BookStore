package com.wyg.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wyg.pojo.Data;
import com.wyg.pojo.Product;
import com.wyg.util.Loaddata;

@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//传入5个参数，type，concretype，priceid，publicid，orderid
		List<Product> productsBytype=null;
		List<Product>productsByPrice=null;
		List<Product>productsByPublish=null;
		List<Product>productsByPriceAndPublish=null;
		Data data=Data.getInstance();
		String type=request.getParameter("type");
		String concretytpe=request.getParameter("concretype");
		if(type==null)type=data.getType().trim();
		//if(concretytpe==null)concretytpe=data.getConcretype();
		String typeName=Loaddata.getTypeName(type);
		String concretypeName=null;
		/*System.out.println("type="+type+"   concretype="+concretytpe+"  order="+request.getParameter("orderid")
		+"priceid="+request.getParameter("priceid")+" publishid="+request.getParameter("publishid"));*/
		HttpSession session=request.getSession();
		session.setAttribute("type", type);
		session.setAttribute("concretytpe",concretytpe );
		session.setAttribute("typeName", typeName);
		if(concretytpe!=null&&!"null".equals(concretytpe)){
		 concretypeName=Loaddata.getConcretypeName(concretytpe);
		 }
		session.setAttribute("concretypeName", concretypeName);
		
    	if(concretytpe!=null&!"null".equals(concretytpe))	//先筛选类型
    	{
    		productsBytype=Loaddata.showProductsByDetailId(Long.parseLong(concretytpe));	
    		 System.out.println("执行了concretype类型不为空");
    		 System.out.println("不为空大小为"+productsBytype.size());
    	}
    	else
    	{
    		System.out.println("执行了concretype类型为空***********大类型type="+type);
    		productsBytype=Loaddata.showProductsByCategoryId(Long.parseLong(type));
    	}
    	
    	
    	if(data.getPriceid()!=null&&data.getPublishid()==null)	//如果用户只点击了价格区间
    	{
    		 System.out.println("执行了点击价格区间");
    		 String order=data.getOrderid();
 			String priceid=data.getPriceid();
    		switch (order) {
			case "0":productsByPrice=Loaddata.getProductsByPriceODID(Long.parseLong(priceid));
				break;
			case "1":productsByPrice=Loaddata.getProductsByPriceODSA(Long.parseLong(priceid));
				break;
			case "2":productsByPrice=Loaddata.getProductsByPriceODPR(Long.parseLong(priceid));
				break;
			case "3":productsByPrice=Loaddata.getProductsByPriceODST(Long.parseLong(priceid));
				break;
			}
    		System.out.println("经过价格筛选的list   ="+productsByPrice.size());
      		 for(int i=0;i<productsByPrice.size();i++)
      			 System.out.println(productsByPrice.get(i).getId());
    		
      		productsByPrice.retainAll(productsBytype);
    		System.out.println("经过交集的list  ="+productsByPrice.size());
   		 for(int i=0;i<productsByPrice.size();i++)
   			 System.out.println(productsByPrice.get(i).getId());
    		session.setAttribute("products", productsByPrice);
    	}
    	
    	else if(data.getPublishid()!=null&&data.getPriceid()==null)//如果用户只点击了出版社
    	{
    		 System.out.println("执行了点击出版社区间");
    		 String order=data.getOrderid();
 			String publishid=data.getPublishid();
    		switch (order) {
			case "0":productsByPublish=Loaddata.getProductsByPublishODID(Long.parseLong(publishid));
				break;
			case "1":productsByPublish=Loaddata.getProductsByPublishODSA(Long.parseLong(publishid));
				break;
			case "2":productsByPublish=Loaddata.getProductsByPublishODPR(Long.parseLong(publishid));
				break;
			case "3":productsByPublish=Loaddata.getProductsByPublishODST(Long.parseLong(publishid));
				break;
			}
    		productsByPublish.retainAll(productsByPublish);
    		session.setAttribute("products",productsByPublish);
    	}
    	
    	else if(data.getPublishid()!=null&&data.getPriceid()!=null){
    		 System.out.println("执行了价格和出版社区间");
    		 String order=data.getOrderid();
			String priceid=data.getPriceid();
			String publishid=data.getPublishid();
    		switch (order) {
			case "0":productsByPriceAndPublish=Loaddata.getProductByPriceAndPublishODID(Long.parseLong(priceid), Long.parseLong(publishid));
				break;
			case "1":productsByPriceAndPublish=Loaddata.getProductByPriceAndPublishODSA(Long.parseLong(priceid), Long.parseLong(publishid));
				break;
			case "2":productsByPriceAndPublish=Loaddata.getProductByPriceAndPublishODPR(Long.parseLong(priceid), Long.parseLong(publishid));
				break;
			case "3":productsByPriceAndPublish=Loaddata.getProductByPriceAndPublishODST(Long.parseLong(priceid), Long.parseLong(publishid));
				break;
			}
    		System.out.println("价格和出版社筛选前list大小为       "+productsByPriceAndPublish.size());
    		for(int i=0;i<productsByPriceAndPublish.size();i++)
    		{
    			System.out.println("id="+productsByPriceAndPublish.get(i).getId()+"  isbn="+productsByPriceAndPublish.get(i).getISBN());
    			
    		}
    		productsByPriceAndPublish.retainAll(productsBytype);
    		
    		session.setAttribute("products", productsByPriceAndPublish);
		}
    	else {
			System.out.println("执行了两个都为空区间");
			session.setAttribute("products",productsBytype);
		}
    	if (concretytpe==null) {
    		response.sendRedirect("list.jsp?type="+type);
		}
    	else
    	response.sendRedirect("list.jsp?type="+type+"&concretype="+concretytpe);
	}
}
