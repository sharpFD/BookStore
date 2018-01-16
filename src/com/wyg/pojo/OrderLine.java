package com.wyg.pojo;


import java.io.Serializable;

//订单项
public class OrderLine implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	//购买产品的数量
	private long amount;
	//一类产品的总价
	private double single_price;
	//产品
	private Product product;
	//属于的订单
	private Order order;
	//产品的版本
	private String product_version;
	
	
	public OrderLine() {
	}
	public OrderLine(long id, long amount, double single_price, Product product, Order order, String product_version) {
		super();
		this.id = id;
		this.amount = amount;
		this.single_price = single_price;
		this.product = product;
		this.order = order;
		this.product_version = product_version;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public double getSingle_price() {
		return single_price;
	}
	public void setSingle_price(double single_price) {
		this.single_price = single_price;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getProduct_version() {
		return product_version;
	}
	public void setProduct_version(String product_version) {
		this.product_version = product_version;
	}
	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", amount=" + amount + ", single_price=" + single_price + ", product=" + product
				+ ", order=" + order + ", product_version=" + product_version + "]";
	}
	
}
