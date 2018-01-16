package com.wyg.pojo;

import java.io.Serializable;
import java.util.Set;
/*
 * 产品
 */
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	//产品名称
	private String name;
	//产品价格
	private String price;
	//付款方式
	private String service_fg;
	//是否免运费
	private String service_myf;
	//取货方式eg:自提
	private String service_zt;
	//退货情况提示
	private String service_th;
	//产品的版本： 1精装版，2简装版，4收藏版等...
	private int version;
	//产品的库存
	private long stock;
	//产品的描述信息
	private String description;
	/*
	 * 产品的参数
	 */
	//产品的作者
	private String writer;
	//书的页数
	private long pages;
	//书的唯一标示码
	private String ISBN;
	//出版时间
	private String publish_date;
	//产品的特点
	private String feature_images;
	//产品的图片
	private String images;
	//产品清单
	private String bill;
	//产品上架时间
	private String start_date;
	//产品销售的数量
	private long sale_num;
	//特推产品 1 普通产品 0，热卖2（最多3件）
	//0表示普通产品，-1表示首页展示特推产品，1表示热销产品
	private int flag;
	//说明,如覆盖什么。。。知识点
	private String explain;
	//出版社
	private Publish publish;
	//产品所属的类型
	private CategoryDetail cate_detail;
	//产品的评价
	private Set<Evaluate> evaluates;
	//订单项
	private Set<OrderLine> orderlines;
	//简报
	private Set<Report> reports;
	
	
	public Product(long id) {
		this.id = id;
	}
	
	
	public Product(long id, long stock) {
		this.id = id;
		this.stock = stock;
	}
	
	

	public Product(long id, long sale_num,String a) {
		this.id = id;
		this.sale_num = sale_num;
	}


	public Product() {
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
	public double getPrice() {
		return Double.parseDouble(price.split("[#]")[0]);
	}
	public double getPrice2() {
		return Double.parseDouble(price.split("[#]")[1]);
	}
	public double getPrice3() {
		return Double.parseDouble(price.split("[#]")[2]);
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getService_fg() {
		return service_fg;
	}
	public void setService_fg(String service_fg) {
		this.service_fg = service_fg;
	}
	public String getService_myf() {
		return service_myf;
	}
	public void setService_myf(String service_myf) {
		this.service_myf = service_myf;
	}
	public String getService_zt() {
		return service_zt;
	}
	public void setService_zt(String service_zt) {
		this.service_zt = service_zt;
	}
	public String getService_th() {
		return service_th;
	}
	public void setService_th(String service_th) {
		this.service_th = service_th;
	}

	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Publish getPublish() {
		return publish;
	}
	public void setPublish(Publish publish) {
		this.publish = publish;
	}
	public long getPages() {
		return pages;
	}
	public void setPages(long pages) {
		this.pages = pages;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getFeature_images() {
		return feature_images;
	}
	public void setFeature_images(String feature_images) {
		this.feature_images = feature_images;
	}
	
	public String getBill() {
		return bill;
	}
	public void setBill(String bill) {
		this.bill = bill;
	}
	public CategoryDetail getCate_detail() {
		return cate_detail;
	}
	public void setCate_detail(CategoryDetail cate_detail) {
		this.cate_detail = cate_detail;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public Set<Evaluate> getEvaluates() {
		return evaluates;
	}
	public void setEvaluates(Set<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}
	public Set<OrderLine> getOrderlines() {
		return orderlines;
	}
	public void setOrderlines(Set<OrderLine> orderlines) {
		this.orderlines = orderlines;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public long getSale_num() {
		return sale_num;
	}
	public void setSale_num(long sale_num) {
		this.sale_num = sale_num;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getImages() {
		return images.split("[#]")[0];
	}
	public String getImages2() {
		return images.split("[#]")[1];
	}
	public String getImages3() {
		return images.split("[#]")[2];
	}
	public String getImages4() {
		return images.split("[#]")[3];
	}
	public String getImages5() {
		return images.split("[#]")[4];
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public Set<Report> getReports() {
		return reports;
	}
	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", service_fg=" + service_fg
				+ ", service_myf=" + service_myf + ", service_zt=" + service_zt + ", service_th=" + service_th
				+ ", version=" + version + ", stock=" + stock + ", description=" + description + ", writer=" + writer
				+ ", pages=" + pages + ", ISBN=" + ISBN + ", publish_date=" + publish_date + ", feature_images="
				+ feature_images + ", images=" + images + ", bill=" + bill + ", start_date=" + start_date
				+ ", sale_num=" + sale_num + ", flag=" + flag + ", explain=" + explain + ", publish=" + publish
				+  ", cate_detail=" + cate_detail + ", evaluates=" + evaluates
				+ ", orderlines=" + orderlines + ", reports=" + reports + "]";
	}
	@Override
	public int hashCode() {
		return (int) (Integer.parseInt(ISBN)+id);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==this)return true;
		if(obj.getClass()==Product.class)
		{
			return ((Product)obj).ISBN.equals(this.ISBN)&&((Product)obj).id==this.id;
		}
		return false;
	}
}
