<%@page import="com.wyg.pojo.Evaluate"%>
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
	<title>订单详情</title>
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
<link rel="stylesheet" href="css/orderSure.css" />
<script language="javascript" src='js/myjs.js'></script>
<script language="javascript" src='js/jquery-1.8.3.min.js'></script>
</head>
<body>
	<%  
	String orderid=request.getParameter("orderid");
	Order order=Loaddata.findOrderByOrderId(Integer.parseInt(orderid));
	Set<OrderLine>set=order.getOrderlines();
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
	<p class="orderButtom">订单详情</p>
    <!--中间复杂部分-->
    <div class="content">
    	<div class="contentCenter">
    		<p class="singleP" style="margin-bottom: 25px;">
    		<b>送货清单</b><b style="margin-left: 40px;">订单编号：【<%=order.getId()%>】</b> 
    		<b style="margin-left: 40px;">订单状态：【<%=order.getStatus()%>】</b> 
    		<b style="margin-left: 40px;">下单日期：【<%=order.getStart_date()%>】</b> 
    		<b style="margin-left: 40px;">支付方式：【<%
    		if(order.getStatus().equals("未支付")){
    			out.print("未支付");
    		}
    		else
    			out.print(Loaddata.getPayWayName(order.getPayway().getId()));
    		
    		%>】
    		<%if(!order.getStatus().equals("未支付")){ %>
    		
    		</b> <br/><br/>
    		<b>收件人姓名：【<%=order.getReceiver().getName() %>】</b> 
    		<b style="margin-left: 40px;">收件人地址：【<%=order.getReceiver().getAddress() %>】</b> 
    		<b style="margin-left: 40px;">收件人电话：【<%=order.getReceiver().getPhone() %>】</b> 
    		<%} %>
    		<div class="bigDiv">
    			<div class="twoDiv">
    				<b>商家：briup自营</b>
					<%
					HttpSession getsession=request.getSession();
					User user=(User)getsession.getAttribute("user");
					long num=0;
					boolean flag=false;
					Iterator<OrderLine> iterator=set.iterator();
					while(iterator.hasNext())
					{
						OrderLine orderLine=iterator.next();
						Product product=orderLine.getProduct();
						Evaluate evaluate=Loaddata.getUniqueEva(user.getId(), product.getId(), order.getId());
						String version=orderLine.getProduct_version();
						num++;
					%>
					<ul class="oneUL">
    					<li>
    						<img src="<%out.print(product.getImages()); %>" class="pic">
    						<p><%out.print(Loaddata.getTypeNameById(product.getId())); %>&nbsp;&nbsp;<%out.print(Loaddata.getConcretypeNameById(product.getId())); %>&nbsp;&nbsp;<%out.print("《"+product.getName()+"》"+Loaddata.showBookVersion(version));%>&nbsp;&nbsp;</p>
    						<p><font><%out.print(Loaddata.showPrice(version, product)); %></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×<%out.print(orderLine.getAmount()); %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有货</p>
    						<p><img src="images/sureLogo_14.png" alt=""><span>七天无理由退换货</span></p>
    						<%
    						if(evaluate!=null){
    						if(evaluate.getState()==0){ flag=true;	//如果没有评价
    						getsession.setAttribute("order", order.getId());
    						%>
							<p><a href="viewBook.jsp?id=<%=product.getId()%>"><img src="images/21_14.png" alt=""></a></p>
							<%}else{ %>
							<p><img src="images/21_09.png" alt=""></p>
							<%} }%>
    					</li>
					</ul>
					<%} %>
					
    			</div>
    			
    		</div>
    		<p class="singleP"><span><a href="order.jsp">返回订单信息页</a></span></p>
    		<%BigDecimal b = new BigDecimal(order.getSum_price()); 
			float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();  %>
    		<div class="money">
    			<span><font><%out.print(num); %></font>件商品，总商品金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥<%out.print(f1); %></span>
    			<span><img src="images/sureLogo_18.png" alt="">运费：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥0.00</span>
    			
    			<span>应付总额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥<%out.print(f1); %></span>
    		</div>
    		<div class="submit">
    			<span><%
    			if(order.getStatus().equals("未支付"))out.print("待付");else out.print("已付");%>金额：<font>¥<%out.print(f1); %></font>
    			<%if(order.getStatus().equals("未支付")) 
    			{
    			%>
    			<a href="pay.jsp?orderid=<%=order.getId()%>"><img src="images/21_06.png" alt="" ></a>
    			<%}else{
    				if(flag==false){
    				%>
    			<img src="images/21_05.png" alt="">
    			<%}
    				else{	
    			 %>
    			 <img src="images/21_13.png" alt="">
    			 <%}} %>
    			</span>
    		</div>
    	</div>

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
            <p>沪ICP备14033591号-8 杰普软件briup.com版权所有 杰普软件科技有限公司 </p>
          	<img src="images/police.png">
        </div>
    </div>
</body>
</html>