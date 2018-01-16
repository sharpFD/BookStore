package com.wyg.pojo;


import java.util.Set;

/*
 * 杰普简报
 */
public class Report {
	private long id;
	//标题
	private String title;
	//简报推荐的产品
	private Set<Product> products;
	//简报生成时间
	private String start_date;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
}
