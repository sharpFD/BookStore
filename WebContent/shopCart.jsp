<%@page import="com.wyg.util.Loaddata"%>
<%@page import="com.wyg.pojo.OrderLine"%>
<%@page import="com.wyg.pojo.ShopCart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.wyg.pojo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>购物车页面</title>
<link rel="stylesheet" href="css/fullCar.css" />
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/icons.css" />
<link href="css/lanrenzhijia.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.1.4.2-min.js"></script>
<script src="js/Calculation.js"></script>
</head>
<body>
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
        	<img src="images/play_01.png" alt="">
        </div>
    </div>
    <!-- 中间部分 !-->
<div class="gwc" style=" margin:auto;">
  <table cellpadding="0" cellspacing="0" class="gwc_tb1">
    <tr>
      <td class="tb1_td1"><input id="Checkbox1" type="checkbox"  class="allselect"/></td>
      <td class="tb1_td1">全选</td>
      <td class="tb1_td3">商品</td>
      <td class="tb1_td4">版本及单价</td>
      <td class="tb1_td5">数量</td>
      <td class="tb1_td6">小结</td>
      <td class="tb1_td7">操作</td>
    </tr>
  </table>
  
  <form name="form1" action="ConfirmServlet" method="post">
  <%
  HttpSession getsession=request.getSession();
  ShopCart shopCart=(ShopCart)getsession.getAttribute("shopCart");	//得到总价和增删该查
  	List<OrderLine>orderLines=(List)getsession.getAttribute("orderlines");//得到具体产品相关
  	for(int i=0;i<orderLines.size();i++){
  		Product product=orderLines.get(i).getProduct();
  		OrderLine getOrderLine=orderLines.get(i);
  		getOrderLine.setSingle_price(Loaddata.showPrice(getOrderLine.getProduct_version(), product)*getOrderLine.getAmount());
  %>
  <table cellpadding="0" cellspacing="0" class="gwc_tb2">
    <tr>
      <td class="tb2_td1"><input type="checkbox" value="<%out.print( (Loaddata.showPrice(getOrderLine.getProduct_version(), product)*getOrderLine.getAmount())+"#"+i); %>" name="newslist" id="newslist-<%out.print(i);%>" /></td>
      <td class="tb2_td2"><a href="#"><img src="<%out.print(product.getImages());%>"/></a></td>
      <td class="tb2_td3"><a href="#"><%out.print(product.getName());%></a></td>
      <td class="tb1_td4"><%out.print(Loaddata.showBookVersion(getOrderLine.getProduct_version())+" ￥:"+Loaddata.showPrice(getOrderLine.getProduct_version(), product));%></td>
      <td class="tb1_td5"><input id="<%out.print(i);%>" class="del" name=""  style=" width:20px; height:18px;border:1px solid #ccc;" type="button" value="-" />
        <input id="text_box<%out.print(i);%>" name="text_box<%out.print(i);%>" type="text" value="<%out.print(getOrderLine.getAmount()); %>" style=" width:30px; text-align:center; border:1px solid #ccc;" onblur="GetCount()" />
        <input id="<%out.print(i);%>" class="add"  name="" style=" width:20px; height:18px;border:1px solid #ccc;" type="button" value="+" />
      </td>
      <td class="tb1_td6"><label id="total<%out.print(i);%>" class="tot" style="color:#ff5500;font-size:14px; font-weight:bold;" ></label></td>
      <td class="tb1_td7"><a href="DeleteProductServlet?bookid=<%=product.getId()%>&version=<%=getOrderLine.getProduct_version()%>">删除</a></td>
    </tr>
  </table>
 <input id="price<%out.print(i);%>" type="hidden" value="<%out.print(Loaddata.showPrice(getOrderLine.getProduct_version(), product));%>">
 <input type="hidden" id="pos<%out.print(i);%>" value="<%out.print(i);%>">
  <%} %>
 </form>
  
  <table cellpadding="0" cellspacing="0" class="gwc_tb3">
    <tr>
      <td class="tb3_td1"><input id="invert" type="checkbox" />
        反选
        <input id="cancel" type="checkbox" />
        取消 </td>
      <td class="tb3_td2">已选商品
        <label id="shuliang" style="color:#ff5500;font-size:14px; font-weight:bold;">0</label>
        件</td>
      <td class="tb3_td3">合计(不含运费):<span>￥</span><span style=" color:#ff5500;">
        <label id="zong1" style="color:#ff5500;font-size:14px; font-weight:bold;"></label>
        </span></td>
      <td class="tb3_td4"><span id="jz1">结算</span><a href="javascript:;" onclick="summary()"  style=" display:none;"  class="jz2" id="jz2">结算</a></td>
    </tr>
  </table>
</div>
<script type="text/javascript">
  	function summary()
  	{
  		var flag=false;
  		var num=<%out.print(orderLines.size());%>
  		 for(var i=0;i<num;i++)
  			{
  				if(document.getElementById("newslist-"+i).checked==true)
  				{
  					flag=true;
  				}
  			}
  		if(flag==true)
  			{document.form1.submit();}
  		else
  			{
  			alert('请至少选择一个产品');
  			}
  		//javascript:
  		//document.getElementById(elementId)
  	}
  </script>
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