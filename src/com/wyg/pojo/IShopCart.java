package com.wyg.pojo;

import java.math.BigDecimal;
import java.util.Iterator;

public interface IShopCart {
	
	void addProduct(Product product,String version) throws Exception;
	
	void removeProduct(long productid,String version) throws Exception;
	
	void removeAllProducts() throws Exception;
	
	void updateProduct(long productid,Integer number,String version) throws Exception;
	
	BigDecimal getTotalPrice() throws Exception;
	
	Iterator<OrderLine> getOrderlines() throws Exception;
	
}
