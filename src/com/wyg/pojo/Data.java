package com.wyg.pojo;


public class Data {
	private static Data data=new Data();
		private String priceid;
		private String publishid;
		private String orderid;
		private String type;
		private String concretype;
		public String getPriceid() {
			return priceid;
		}
		public void setPriceid(String priceid) {
			this.priceid = priceid;
		}
		public String getPublishid() {
			return publishid;
		}
		public void setPublishid(String publishid) {
			this.publishid = publishid;
		}
		public String getOrderid() {
			return orderid;
		}
		public void setOrderid(String orderid) {
			this.orderid = orderid;
		}
		public static Data getInstance()
		{
			return data;
		}
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getConcretype() {
			return concretype;
		}
		public void setConcretype(String concretype) {
			this.concretype = concretype;
		}

		static{
			data.priceid="0";
			data.publishid="0";
			data.orderid="0";
			data.type="0";
			data.concretype="0";
		}
}
