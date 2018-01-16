package com.wyg.pojo;
/*
 * 书籍种类
 */

import java.io.Serializable;
import java.util.Set;

public class Category  implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private Set<CategoryDetail> cate_detail;
	
	public Category(long id) {
		this.id = id;
	}
	public Category() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<CategoryDetail> getCate_detail() {
		return cate_detail;
	}
	public void setCate_detail(Set<CategoryDetail> cate_detail) {
		this.cate_detail = cate_detail;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", cate_detail=" + cate_detail + "]";
	}
	
}
