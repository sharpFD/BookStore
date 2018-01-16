package com.wyg.servlet;

import java.util.Iterator;

import com.wyg.pojo.OrderLine;
import com.wyg.pojo.Product;
import com.wyg.pojo.ShopCart;

public class test {
	private static ShopCart shopCart;
	public static void main(String[] args) {
		Iterator<OrderLine> orderlines = shopCart.getOrderlines();
		while(orderlines.hasNext())
		{
			OrderLine orderLine = orderlines.next();
			Product product=orderLine.getProduct();
			System.out.println(product.getId()+"       "+product.getName());
		}
	}
	public static void getShopCart(ShopCart shopCart)
	{
		test.shopCart=shopCart;
	}
}
