<%@page import="com.wyg.pojo.PriceRank"%>
<%@page import="com.wyg.util.PageJumpUtils"%>
<%@page import="com.wyg.pojo.Publish"%>
<%@page import="com.wyg.pojo.Product"%>
<%@page import="com.wyg.util.Loaddata"%>
<%@page import="com.wyg.pojo.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>briup电子商务-首页</title>
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/zzsc.css"/>
<script language="javascript" src='js/jquery.min.js'></script>
<script language="javascript" src='js/myjs.js'></script>
<script type="text/javascript">
	function addShopCart(){
	alert('加入购物车成功!');
	}
</script>
<script type="text/javascript">
	var pagnation=$("#pagnation");
	$(document).on("keydown",function(event){
		switch(event.keyCode){
			 case 37 : window.location.href = pagnation.find(".page-prev").attr("href");break;
			 case 39 : window.location.href = pagnation.find(".page-next").attr("href");break;
		}
	});
</script>
</head>
<body onload="init()">
	<!--顶部-->
	<%
	boolean isLogin=PageJumpUtils.isLogin(request);
	HttpSession getsession=request.getSession();
    String type=(String)getsession.getAttribute("type");
	String concretytpe=(String)getsession.getAttribute("concretytpe");
	if("null".equals(concretytpe))concretytpe=null;
	String typeName=(String)getsession.getAttribute("typeName"); 
	String concretypeName=(String)getsession.getAttribute("concretypeName");
	//System.out.println("jsp out :: type="+type+"   concretytpe=="+concretytpe+"  typeName="+typeName+"   concretypeName"+concretypeName);
	%>
	<!--顶部-->
	<div class="top">
    	<div class="top_center">
    		<ul class="top_lr">
            	<li><a href="<%if(isLogin!=false)out.print("userinfo.jsp");else out.print("login.jsp"); %>" style="color: <% 
            			if(isLogin!=false)out.print("black");else out.print("red"); 	
            	%>;"><%
            			if(isLogin!=false)	//登陆了
            			{
            				out.print("你好,"+PageJumpUtils.getUserByreq(request).getName());
            			}
            			else			//没登陆
            			{
            				out.print("亲,请登录");
            			}
            	
            	 %></a></li>
            	 <%if(isLogin==false){ %>
            	 <li><a href="register.jsp">免费注册</a></li>
            	 <%} %>
            	 
            </ul>
            <ul class="top_bars">
            	<%if(isLogin!=false){ %>
            		<li><a href="LogoutServlet">退出</a>|</li>
                <li><a href="OrderServlet?code=<%=Math.random()%>">我的订单<span class="jt_down"></span></a>|</li>
            			<%} %>
                <li><a href="#">关注杰普<span class="jt_down"></span></a>|</li>
                <li><a href="#">网站导航<span class="jt_down"></span></a></li>
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	<a href="index.jsp"><img src="images/logo.png"></a>
    	<div class="h3_center">
        	<div class="search_box">
            	<input type="text"/>
                <span>搜索</span>
            </div>
            <p>
            	<%List<Category> list=Loaddata.getAlltype();
            for(int i=0;i<list.size();i++)
            {
            %>
          	    <a href="ListServlet?type=<% out.print(list.get(i).getId());%> "> <% out.print(list.get(i).getName()); %> </a>|
          	    <%} %>
            </p>
        </div>
        <div class="h3_right">
        	<%if(isLogin!=false){%> 
        	<div class="myyy">
        	<a href="userinfo.jsp?id=<%out.print(PageJumpUtils.getUserByreq(request).getId()); %>">个人信息</a>
                <span class="sj_down"></span>
             </div>
            <div class="tsc">
           	  <a href="SummaryServlet?code=<%=Math.random()%>">去购物车结算</a>
              <span class="sj_right"> </span>
            </div>
            	<% }%> 
        </div>
    </div>
    <!--头部导航-->
    <div class="nav_top">
    	<div class="nav_top_center">
            <div>
                全部图书分类
            </div>
            <ul>
                <%
            for(int i=0;i<list.size();i++)
            {
            %>
                <li><a href="ListServlet?type=<% out.print(list.get(i).getId());%>"><% out.print(list.get(i).getName()); %></a></li>
                <%} %>
            </ul>
        </div>
    </div>
    
    <!--内容-->
    <div class="container4">
    	<!--热卖-->
    	<div class="c4_b1">
        	<ul class="c4_b1_boxes">
        	<%List<Product>gethotsaleProducts=Loaddata.getHotSalesProducts();
        		for(int i=0;i<gethotsaleProducts.size();i++){
        		Product hotSaleProduct=gethotsaleProducts.get(i);
        		String getimages=hotSaleProduct.getImages();
        		String myimages=getimages.split("[#]")[0];
        		String getname=hotSaleProduct.getName();
       			double getprice=hotSaleProduct.getPrice();
        	%>
            	<li>
                	<img src="<% out.print(myimages);%>">
                    <div class="c4_b1_box_txt">
                    	<p><%out.print(getname); %> </p>
                        <h1>活动价：<b>￥<%out.print(getprice); %></b></h1>
                        <h2><a href="viewBook.jsp?id=<%=hotSaleProduct.getId()%>">立即抢购</a></h2>
                    </div>
                </li>
                <%} %>
                
            </ul>
        </div>
        <div class="c4_b2">
            <h1 class="c4_b2_x">
            	<a href="#">${typeName}&nbsp;&nbsp;></a>
                <span><a href="#"><% if(concretytpe==null){out.print(typeName);} else{out.print(concretypeName);} %></a></span>
            </h1> 
                
            <ul id="box" class="c4_b2_y">
            	 <!-- <li>
                	<span class="search_key">价格：</span>
                    <span class="search_val">0-99</span>
                    <span class="search_del"></span>
                </li>
                <li>
                	<span class="search_key">出版社：</span>
                    <span class="search_val">清华出版社</span>
                    <span class="search_del"></span>
                </li>  -->
            </ul>
        </div>
        <div class="c4_b3">
        	<h1></h1>
        	<div>
            	<ul class="c4_b3_search">
                	<li class="c4_b3_name">价格：</li>
                    <li class="c4_b3_vals">
                    <%
                    	List<PriceRank>ranks=Loaddata.findAllRank();
                    	for(int i=0;i<ranks.size();i++){
                    		PriceRank rank=ranks.get(i);
                    		if(concretytpe==null){
                    %>
                        <a id="rank#<%=i %>" href="ListServlet?type=<%=type %>" onclick="rank(this.id)"><%=rank.getMin_price()+"-"+rank.getMax_price() %></a>
                        <%}
                    	else{
                        %>
                        <a id="rank#<%=i %>" href="ListServlet?type=<%=type %>&concretype=<%=concretytpe %>" onclick="rank(this.id)"><%=rank.getMin_price()+"-"+rank.getMax_price() %></a>
                        <%} } %>
					</li>
                </ul><ul class="c4_b3_search">
                	<li class="c4_b3_name">出版社：</li>
                    <li class="c4_b3_vals">
                    <%List<Publish>getAllPublishs =Loaddata.getAllPublish();
                    	for(int i=0;i<getAllPublishs.size();i++)
                    	{
                    		if(concretytpe==null){
                    %>
                        <a id="publish#<%=i %>" href="ListServlet?type=<%=type %>" onclick="publish(this.id)"><%out.print(getAllPublishs.get(i).getName());%></a>
                        <%} 
                    	else{
                        %>
                         <a id="publish#<%=i %>" href="ListServlet?type=<%=type %>&concretype=<%=concretytpe %>" onclick="publish(this.id)"><%out.print(getAllPublishs.get(i).getName());%></a>
                        <%} }%>
					</li>
                </ul>
            </div>
        </div>
    	<div class="c4_b4">
        	<div class="c4_b4_x">
            	<ul class="c4_b4_nav">
            	<%if(concretytpe==null){ %>
                	<li id="fil0" class="current"><a href="ListServlet?type=<%=type %>" onclick="fil(0)">默认</a></li>
                    <li id="fil1"><a href="ListServlet?type=<%=type %>" onclick="fil(1)">销量</a></li>
                    <li id="fil2"><a href="ListServlet?type=<%=type %>" onclick="fil(2)">价格</a></li>
                    <li id="fil3"><a href="ListServlet?type=<%=type %>" onclick="fil(3)">新品</a></li>
                    <%}else{ %>
                    <li id="fil0" class="current"><a href="ListServlet?type=<%=type %>&concretype=<%=concretytpe %>" onclick="fil(0)">默认</a></li>
                    <li id="fil1"><a href="ListServlet?type=<%=type %>&concretype=<%=concretytpe %>" onclick="fil(1)">销量</a></li>
                    <li id="fil2"><a href="ListServlet?type=<%=type %>&concretype=<%=concretytpe %>" onclick="fil(2)">价格</a></li>
                    <li id="fil3"><a href="ListServlet?type=<%=type %>&concretype=<%=concretytpe %>" onclick="fil(3)">新品</a></li>
                    <%} %>
                </ul>
                <div class="c4_b4_search">
                	<input type="text"/><span>确认</span>
                </div>
                <div class="c4_b4_pages">
                	
                </div>
				<div class="clear"></div>
            </div>
             <div class="c4_b4_y">
            	<ul>
                    <li><input type="checkbox"> 仅显示有货</li>
                </ul>               
            </div>
       	</div>
    	<!--商品列表-->
        <div class="c4_b5">	
        	<div class="c4_b5_content">
            	<ul class="c4_b5_c_boxes">
            	<% 
            	List<Product>products=(List<Product>)getsession.getAttribute("products");
            	System.out.println("getproducts的大小为"+products.size());
            	int pagesize=5;
            	int currentpage;
            	if(request.getParameter("page")==null)
            		currentpage=1;
            	else
       			 currentpage=Integer.parseInt(request.getParameter("page"));
       			int totalsize=products.size();
       			int pagenum=(totalsize%pagesize)==0?(totalsize/pagesize):(totalsize/pagesize)+1;
       			if(currentpage>pagenum)currentpage=pagenum;
       			if(currentpage<=0)currentpage=1;
       			int start=pagesize*(currentpage-1)+1;
       			int end;
       			if(currentpage<pagenum)
       		    end=pagesize*(currentpage);
       			else
       				end=totalsize;
       			//System.out.println("currentpage="+currentpage+"totalsize="+totalsize+"pagenum="+pagenum+"pagesize"+pagesize+"start="+start+"end="+end);
       			if(totalsize!=0){
            	for(int i=start-1;i<end;i++){
						Product getproduct=products.get(i); 	
            		%>
            	
                	<li class="c4_b5_c_box">
                    	<!--图片-->
                    	<div class="c4_b5_c_box_pic">
                        	<div class="c4b5_pic_view">
                            	<a href="viewBook.jsp?id=<% out.print(getproduct.getId());%>"> <img src="<%out.print(getproduct.getImages());%>"></a> 
                            </div>
                        </div>
                        
                        <!--价钱-->
                        <div class="c4_b5_c_box_txt">
                        	<h1>￥ <%out.print(getproduct.getPrice()); %></h1>
                            <h2><a href="viewBook.jsp?<% out.print(getproduct.getId());%>"><%out.print(getproduct.getName()); %></a></h2>
                        </div>
                        
                        <!--购买等操作-->
                        <div class="c4b5_el">
                        	<div class="c4b5_el_x">
                            	<span class="wjx01"></span>
                            </div>
                        </div>
                        <%if(isLogin==true){ %>
                        <ul class="c4b5_option">
                        		<%if(concretytpe!=null){ %>
                                <li class="shopcar_box"><span class="shopcar01"></span><a name='shop' href="AddShopServlet?type=<%out.print(type);%>&concretype=<%out.print(concretytpe);%>&bookid=<%out.print(getproduct.getId());%>" onclick="addShopCart()">加入购物车 </a></li>
                                <%} %>
                                <%if(concretytpe==null){ %>
                                <li class="shopcar_box"><span class="shopcar01"></span><a name='shop' href="AddShopServlet?type=<%out.print(type);%>&bookid=<%out.print(getproduct.getId());%>" onclick='addShopCart()'>加入购物车 </a></li>
                                <% }%>
                        </ul>
                                <%} %>
                    </li>
                     <%}} %>
                </ul>
            </div>
        </div>
    </div>
    
	<input id="currpage" type="hidden" value="<%=currentpage %>">
	<input id="ranksize" type="hidden" value="<%=ranks.size()%>">
    <input id="publishsize" type="hidden" value="<%=getAllPublishs.size()%>">
    <input id="type" type="hidden" value="<%=type%>">
	<input id="pagenum" type="hidden" value="<%=pagenum%>">
    <input id="concretype" type="hidden" value="<%=concretytpe%>">
<%
String jump;
if(concretytpe!=null)
jump="JumpServlet?type="+type+"&concretype="+concretytpe+"&currentpage="+currentpage+"&code="+Math.random()+"&page=";
else
jump="JumpServlet?type="+type+"&currentpage="+currentpage+"&code="+Math.random()+"&page=";
%>
<div class="pagnation" id="pagnation">
	<a id="-1" href="<%=jump+"pre"%>" target="_self" class="page-prev" onclick="pagebef()">上一页</a>
	 <%if(pagenum>5){ %> 
	<a id="1" href="<%=jump+1%>" target="_self" onclick="page(this.id)">1</a>
	<a id="2" href="<%=jump+2%>" target="_self" onclick="page(this.id)">2</a>
	<a id="3" href="<%=jump+3%>" target="_self" onclick="page(this.id)">3</a>
	<a id="4" href="<%=jump+4%>" target="_self" onclick="page(this.id)">4</a>
	<a id="5" href="<%=jump+5%>" target="_self" onclick="page(this.id)">5</a>...
	<a id="<%=pagenum %>" href="<%=jump+(pagenum)%>" target="_self" onclick="page(this.id)"><%=pagenum %></a>
	 <%}else{ 
		for(int i=1;i<=pagenum;i++){
	%>
			<a id="<%=i %>" href="<%=jump+i%>" target="_self" onclick="page(this.id)"><%=i %></a>
	<%}} %> 
	<a id="-2" href="<%=jump+"next"%>" target="_self" class="page-next" onclick="pageaft(<%=pagenum%>)">下一页</a>
</div>
    <!--脚部-->
    <div class="footer3">
    	<div class="f3_top">
        	<div class="f3_center">
                <div class="ts1">品目繁多 愉悦购物</div>
                <div class="ts2">正品保障 天天低价</div>
                <div class="ts3">极速物流 特色定制</div>
                <div class="ts4">优质服务 以客为尊</div>
            </div>
        </div>
        <div class="f3_middle">
        	<ul class="f3_center">
            	<li class="f3_mi_li01">
                	<h1>购物指南</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>配送方式</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>支付方式</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>售后服务</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>服务保障</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li06">
                	<h1>客服中心</h1>
                    <img src="images/qrcode_jprj.jpg" width="80px" height="80px">
                    <p>抢红包、疑问咨询、优惠活动</p>
                </li>
            </ul>
        </div>
         <div class="f3_bottom">
        	<p class="f3_links">
                <a href="#">关于我们</a>|
                <a href="#">联系我们</a>|
                <a href="#">友情链接</a>|
                <a href="#">供货商入驻</a> 
           	</p>
            <p>沪ICP备14033591号-8 杰普briup.com版权所有 杰普软件科技有限公司 </p>
          	<img src="images/police.png">
        </div>
    </div>

</body>
</html>