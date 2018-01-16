<%@page import="com.wyg.pojo.User"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="com.wyg.pojo.Order"%>
<%@page import="com.wyg.util.Loaddata"%>
<%@page import="com.wyg.pojo.Product"%>
<%@page import="com.wyg.pojo.OrderLine"%>
<%@page import="java.util.List"%>
<%@page import="com.wyg.pojo.ShopCart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>我的订单</title>
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
<link rel="stylesheet" href="css/orderSure.css" />
<script language="javascript" src='js/myjs.js'></script>
<script language="javascript" src='js/jquery-1.8.3.min.js'></script>
</head>
<body>
	<%  
	
	HttpSession getsession=request.getSession();
	User user=(User)session.getAttribute("user");
	List<Order>orders=Loaddata.findOrderByUserId(user.getId());
	%>
	<!--顶部-->
	<div class="top">
    	<div class="top_center">
            <ul class="top_bars">
            	<li><a href="LogoutServlet">退出</a>|</li>
               <li><a href="OrderServlet?code=<%=Math.random()%>">我的订单<span class="jt_down"></span></a>|</li>
                <li><a href="#">关注杰普<span class="jt_down"></span></a>|</li>
                <li><a href="#">网站导航<span class="jt_down"></span></a></li>
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	<a href="index.jsp"><img src="images/logo.png"  class="oneImg"></a>
        <div class="h3_right">
        </div>
       
    </div>
	<p class="orderButtom">所有订单信息</p>
    <!--中间复杂部分-->
    <%for(int i=0;i<orders.size();i++) 
    {
    	Order order=orders.get(i);  //得到当前订单
    	Set<OrderLine>set=order.getOrderlines(); //得到当前订单的所有订单项
    	List<OrderLine>list=Loaddata.setToList(set); 
		System.out.println(orders.get(i).getId()+"---"+orders.get(i).getStatus());
    %>
    <div class="content" style="margin-bottom: -200px;">
    	<div class="contentCenter">
    		<p class="singleP"><b>送货清单</b><b style="margin-left: 40px;">订单编号：【<%=order.getId()%>】</b> 
    		<b style="margin-left: 40px;">订单状态：【<%=order.getStatus()%>】</b> 
    		<b style="margin-left: 40px;">下单日期：【<%=order.getStart_date()%>】</b> 
    		<b style="margin-left: 40px;">收件人：【<%=order.getReceiver().getName()%>】</b> 
    		<b style="margin-left: 40px;">收件地址：【<%=order.getReceiver().getAddress()%>】</b> 
    		<div class="bigDiv">
    			<div class="twoDiv">
    				<b>商家：briup自营</b>
					<%
					long num=0;
					int time=list.size();
					if(time>2)time=2;
					for(int j=0;j<time;j++)
					{
						OrderLine orderLine=list.get(j);
						Product product=orderLine.getProduct();
						String version=orderLine.getProduct_version();
						num++;
					%>
					<ul class="oneUL">
    					<li>
    						<img src="<%out.print(product.getImages()); %>" class="pic">
    						<p><%out.print(Loaddata.getTypeNameById(product.getId())); %>&nbsp;&nbsp;<%out.print(Loaddata.getConcretypeNameById(product.getId())); %>&nbsp;&nbsp;<%out.print("《"+product.getName()+"》"+Loaddata.showBookVersion(version));%>&nbsp;&nbsp;</p>
    						<p><font><%out.print(Loaddata.showPrice(version, product)); %></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×<%out.print(orderLine.getAmount()); %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有货</p>
    						<p><img src="images/sureLogo_14.png" alt=""><span>七天无理由退换货</span></p>
    					</li>
					</ul>
					<%} %>
    			</div>
    		</div>
    		<%BigDecimal b = new BigDecimal(order.getSum_price()); 
			float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();  %>
    			<p class="singleP"><span><a href="orderdetail.jsp?orderid=<%=order.getId()%>">点击查看详情</a></span></p>
    	</div>
    </div>
    <%} %>
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
            <p>沪ICP备14033591号-8 杰普软件briup.com版权所有 杰普软件科技有限公司 </p>
          	<img src="images/police.png">
        </div>
    </div>
</body>
</html>