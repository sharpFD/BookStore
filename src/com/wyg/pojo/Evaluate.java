package com.wyg.pojo;


import java.io.Serializable;

/*
 * 产品的评价
 */
public class Evaluate  implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	//评价给出的图片 
	private String images;
	//评价的时间
	private String eva_date;
	//评价的用户
	private User user;
	//评价的产品
	private Product product;
	//产品的星级评价
	private int state;
	//评价
	private String content;
	
	private int star;
	
	
	public Evaluate() {
	}
	
	public Evaluate(String images, String eva_date, User user, Product product, int state, String content, int star) {
		super();
		this.images = images;
		this.eva_date = eva_date;
		this.user = user;
		this.product = product;
		this.state = state;
		this.content = content;
		this.star = star;
	}

	public Evaluate(long id, String images, String eva_date, User user, Product product, int state, String content,
			int star) {
		super();
		this.id = id;
		this.images = images;
		this.eva_date = eva_date;
		this.user = user;
		this.product = product;
		this.state = state;
		this.content = content;
		this.star = star;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getEva_date() {
		return eva_date;
	}
	public void setEva_date(String eva_date) {
		this.eva_date = eva_date;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Evaluate [id=" + id + ", images=" + images + ", eva_date=" + eva_date + ", user=" + user + ", product="
				+ product + ", state=" + state + ", content=" + content + ", star=" + star + "]";
	}
	
	
}
