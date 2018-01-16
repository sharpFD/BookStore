<%@page import="java.math.BigDecimal"%>
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
	<title>结算页面</title>
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
<link rel="stylesheet" href="css/orderSure.css" />
<script language="javascript" src='js/myjs.js'></script>
<script language="javascript" src='js/jquery-1.8.3.min.js'></script>
</head>
<body>
	<%  HttpSession getsession=request.getSession();
	  	List<OrderLine>orderLines=(List<OrderLine>)getsession.getAttribute("orderlines");
	  	List<String>pos=(List<String>)getsession.getAttribute("pos");
	%>
	<!-- class表示需要new的bean类，id表示接受的变量，scope表示对象存储的容器 -->
	
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
        	<img src="images/play_02.png" alt="">
        </div>
       
    </div>
	<p class="orderButtom">填写并核对订单信息</p>
    <!--中间复杂部分-->
    <div class="content">
    	<div class="contentCenter">
    		<div class="centerTop">
    			<b style="font-size:20px;">收货人信息</b>
    			<form action="SubmitServlet" method="post" name="form2">
    			<p style="font-size:15px;">
    				
					收件人姓名：<input type="text" name="name" onblur="checknamenull(this.value)"><br/><span id="name"></span>
					
					收件人地址：<input type="text" name="address" onblur="checkAddress()"><br/><span id="address"></span>
					
					收件人电话：<input type="text" name="telephone"  onblur="checkTelephone()"><br/><span id="telephone"></span>
					
				</p>
				</form>
    		</div>
    		<p class="singleP"><b>送货清单【未支付】</b><span><a href="shopCart.jsp">返回修改购物车</a></span></p>
    		<div class="bigDiv">
    			<div class="twoDiv">
    				<b>商家：briup自营</b>
					<%
						float total=0;
						for(int i=0;i<pos.size();i++){
						Product product=orderLines.get(Integer.parseInt(pos.get(i))).getProduct();
						OrderLine getOrderLine=orderLines.get(Integer.parseInt(pos.get(i)));
						String version=getOrderLine.getProduct_version();
						total+=getOrderLine.getSingle_price();
					%>
					<ul class="oneUL">
    					<li>
    						<img src="<%out.print(product.getImages()); %>" class="pic">
    						<p><%out.print(Loaddata.getTypeNameById(product.getId())); %>&nbsp;&nbsp;<%out.print(Loaddata.getConcretypeNameById(product.getId())); %>&nbsp;&nbsp;<%out.print("《"+product.getName()+"》"+Loaddata.showBookVersion(version));%>&nbsp;&nbsp;</p>
    						<p><font><%out.print(Loaddata.showPrice(version, product)); %></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×<%out.print(getOrderLine.getAmount()); %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有货</p>
    						<p><img src="images/sureLogo_14.png" alt=""><span>七天无理由退换货</span></p>

    					</li>
    					<!-- <li><pre>【赠品】  高级定制干花书签  随机 ×1</pre></li> -->
					</ul>
					<%} %>
    			</div>
    		</div>
				<%session.setAttribute("total", total); 
				BigDecimal b = new BigDecimal(total); 
				float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue(); 
				%>
    		<div class="money">
    			<span><font><%out.print(pos.size()); %></font>件商品，总商品金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥<%out.print(f1); %></span>
    			<span><img src="images/sureLogo_18.png" alt="">运费：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥0.00</span>
    			
    			<span>应付总额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥<%out.print(f1); %></span>
    		</div>
    		<div class="submit">
    			<span>应付金额：<font>¥<%out.print(total); %></font><img src="images/21_03.png" alt="" onclick="checkForm2()"></span>
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