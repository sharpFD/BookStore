<%@page import="com.wyg.servlet.AddShopServlet"%>
<%@page import="com.wyg.pojo.Report"%>
<%@page import="com.wyg.util.PageJumpUtils"%>
<%@page import="com.wyg.pojo.Product"%>
<%@page import="com.wyg.pojo.Category"%>
<%@page import="com.wyg.pojo.CategoryDetail"%>
<%@page import="java.util.List"%>
<%@page import="com.wyg.util.Loaddata"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电子商务-首页</title>
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
<link rel="stylesheet" href="css/reset.css">
<link href="css/jquery.slideBox.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery.slideBox.min.js" type="text/javascript"></script>
<script>
jQuery(function($){
	$('#demo1').slideBox();
	$('#demo2').slideBox({
		direction : 'top',//left,top#方向
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'linear',//swing,linear//滚动特效
		delay : 5,//滚动延迟时间，单位：秒
		startIndex : 1//初始焦点顺序
	});
	$('#demo3').slideBox({
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'linear',//swing,linear//滚动特效
		delay : 5,//滚动延迟时间，单位：秒
		hideClickBar : false,//不自动隐藏点选按键
		clickBarRadius : 10
	});
	$('#demo4').slideBox({
		hideBottomBar : true//隐藏底栏
	});
});
</script>
</head>
<body>
	<!--顶部-->
	<%
	boolean isLogin=PageJumpUtils.isLogin(request);
	%>
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
          	    <a href="ListServlet?type=<% out.print(list.get(i).getId());%>"> <% out.print(list.get(i).getName()); %> </a>|
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
    
	<div class="container3">
    	<div class="c3_b1">
        	<div class="c3_b1_left">
            	<dl>
               		
               		<%for(int i=0;i<list.size();i++)
               		{
               			%>
               			<dd>
                    	<h1><%out.print(list.get(i).getName());%></h1>
                    	<p>
                    	<% List<CategoryDetail>concretypes=Loaddata.getConcretypesByid((int)list.get(i).getId());
                    	for(int j=0;j<concretypes.size();j++)
                    	{
                    	%>
                        	<a href="ListServlet?type=<%out.print(list.get(i).getId());%>&concretype=<% out.print(concretypes.get(j).getId());%>"><% out.print(concretypes.get(j).getName());%></a>
                        	<%} %>
                      	</p>
                      	 </dd>
                      	<%}%>
                   
                </dl>
            </div>
            <div class="c3_b1_center">
            	<!--  <div>
                	<a href="viewBook.jsp"><img src="images/ad1.png"></a>
                </div>  -->
                
                <div id="demo1" class="slideBox">
  <ul class="items">
    <li><a href="ListServlet?type=0" title="推理小说大王——东野奎吾"><img src="img/1.jpg"></a></li>
    <li><a href="ListServlet?type=1" title="6.18狂欢进行中——童书统统减"><img src="img/2.jpg"></a></li>
    <li><a href="ListServlet?type=2" title="6.18狂欢进行时——满减大促销"><img src="img/3.jpg"></a></li>
    <li><a href="ListServlet?type=3" title="6月新书速递——宝宝单词攻略"><img src="img/4.jpg"></a></li>
    <li><a href="ListServlet?type=4" title="6月人气好书大盘点"><img src="img/5.jpg"></a></li>
    <li><a href="ListServlet?type=5" title="红6月——用学习来减压"><img src="img/6.jpg"></a></li>
  </ul>
</div>
                
                
                
                <div class="c3_b1_c_bottom">
                    <ul>
                    <%List<Product>hotProducts=Loaddata.getHotProducts();
                    	for(int i=0;i<hotProducts.size();i++)
                    	{
                    		Product product=hotProducts.get(i);
                    %>
                    
                        <li>
                           <a href="viewBook.jsp?id=<%out.print(product.getId());%>"  ><img src="<%out.print(product.getImages());%>" width="150px" height="150px"/></a>
                           <a href="viewBook.jsp?id=<%out.print(product.getId());%>"  ><%out.print(product.getName()); %></a>  
                        </li>
                           <%} %>
                      
                    </ul>
            	</div>
            </div>
            <div class="c3_b1_right">
            	<h1>杰普快报<a href="#">更多</a></h1>
            	<ul>
                	<%
                	List<Report> reports=Loaddata.getAllReport();
                	for(int i=0;i<reports.size();i++) {
                	%>
                	<li><a href="#"><%out.print(reports.get(i).getTitle());%>  </a></li>
                	<%} %>
                </ul>
            <div style="clear:both"></div>
        </div>
       
        <div class="c3_b2">
        	<ul>
        	 <%
        List<Product>gethotsaleProducts=Loaddata.getHotSalesProducts();
        for(int i=0;i<gethotsaleProducts.size();i++){ 
        	Product getproduct=gethotsaleProducts.get(i);
        %>
        	<%if(i!=gethotsaleProducts.size()-1){ %>
            	<li>
            	<%}else {%>
            	<li class="no_mr">
            	<%} %>
            			<div class="c3_b2_txt">
                    	<h1><%=getproduct.getName() %></h1>
                        <p><%=getproduct.getCate_detail().getName() %>经典</p>
                        <h2>覆盖<%=getproduct.getCate_detail().getName() %>基础及全新内容</h2>
                        <p><a href="viewBook.jsp?id=<%=getproduct.getId()%>">更多精彩，点击进入</a></p>
                    </div>
                </li>
                
         <%} %>
         	
                 
            </ul>
        </div>
        
    <div class="c20"></div>
    </div></div>
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
