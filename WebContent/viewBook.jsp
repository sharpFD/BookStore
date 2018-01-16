<%@page import="com.wyg.pojo.User"%>
<%@page import="com.wyg.pojo.Evaluate"%>
<%@page import="com.wyg.pojo.CategoryDetail"%>
<%@page import="com.wyg.util.PageJumpUtils"%>
<%@page import="com.wyg.pojo.Product"%>
<%@page import="com.wyg.util.Loaddata"%>
<%@page import="com.wyg.pojo.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>briup电子商务-首页</title>
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/icons.css" />
<link rel="stylesheet" href="css/table.css" />
<script type="text/javascript" src='js/myjs.js'></script>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>

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
    
	<div class="container5">
    	<div class="cn5_top">
            <div class="cn5_top_x center01">
            	<%
            	long getid=Long.parseLong(request.getParameter("id"));
            	Product product=Loaddata.getProductById(getid);
            	CategoryDetail categoryDetail=product.getCate_detail();
            	Long id=categoryDetail.getId();
            	String cateid=Loaddata.getCategoryIdbyDetailId(id)+"";
            	String type=Loaddata.getTypeName(cateid);
            	String concretype=Loaddata.getConcretypeName(product.getCate_detail().getId()+"");
            	%>
            
            	<a class="font20" href="ListServlet?type=<% out.print(Loaddata.getCategoryIdbyDetailId(product.getCate_detail().getId())+"");%>"><%out.print(type); %></a> >
                <a href="ListServlet?type=<% out.print(Loaddata.getCategoryIdbyDetailId(product.getCate_detail().getId())+"");%>&concretype=<%out.print(product.getCate_detail().getId()+""); %>"><%out.print(concretype); %></a>
                
            </div>
            <div class="cn5_top_y center01">
            	<div class="cn5topy_1">
                	<div class="cn5topy_imgview">
                    	<img id="bigPic" src="<%out.print(product.getImages());%>"/>
                        <ul class="cn5topy_imglist">
                        	<li id='pic1'><a href="javascript:void(0);" onclick="changesmallPic(<%out.print(product.getId()+"");%>,'pic1','1')"><img src="<%out.print(product.getImages());%>"></a></li>
                            <li id='pic2'><a href="javascript:void(0);" onclick="changesmallPic(<%out.print(product.getId()+"");%>,'pic2','2')"><img src="<%out.print(product.getImages2());%>"></a></li>
                            <li id='pic3'><a href="javascript:void(0);" onclick="changesmallPic(<%out.print(product.getId()+"");%>,'pic3','3')"><img src="<%out.print(product.getImages3());%>"></a></li>
                            <li id='pic4'><a href="javascript:void(0);" onclick="changesmallPic(<%out.print(product.getId()+"");%>,'pic4','4')"><img src="<%out.print(product.getImages4());%>"></a></li>
                            <li id='pic5'><a href="javascript:void(0);" onclick="changesmallPic(<%out.print(product.getId()+"");%>,'pic5','5')"><img src="<%out.print(product.getImages5());%>"></a></li>
                            
                        </ul>
                    </div>
                </div>
                <div class="cn5topy_2">
                	<h1 class="pro_title font15"><%out.print(product.getName());%></h1>
                    <div class="pro_price">
                            <div id=myprice class='pro_price_x'>
                                <p> briup价：
                                <b><%out.print(product.getPrice()); %></b> 
                                <a href='#'>(降价通知)</a></p>
                            </div>
                            
                    </div>
                    <div class="pro_ship">
                        <div>
                        	<div class="pro_key fl">服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</div>
                            <ul class="pro_service f1">
                                <li class="service_fq">分期付款</li>
                                <li class="service_myf">免运费</li>
                                <li class="service_zt">自提</li>
                                <li class="service_th">7天无理由退换货</li>
                            </ul>
                        </div>
                    </div>
                   <div class="pro_selects">
                    	<div class="pro_select">
                        	<div class="pro_key pro_key_vertical fl">选择版本：</div>
                            <div class="pro_select_vals">
                            <div id='ver1'><span><a href='javascript:void(0)' onclick="checkVersion(<%out.print(product.getId()+"");%>,'1')">精装版</a></span></div>
                            <div id='ver2'><a href='javascript:void(0)' onclick="checkVersion(<%out.print(product.getId()+"");%>,'2')">简装版</a></div>
                            <div id='ver3'><a href='javascript:void(0)' onclick="checkVersion(<%out.print(product.getId()+"");%>,'3')">收藏版</a></div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="pro_buy">
                    	<div class="pro_buy_nums">
                        	<input id="num" type="text" value="1" onchange="getnum(this.value)"/>
                            <dl>
                            	<dd id="add" onclick="changenumadd()">+</dd>
                                <dd id="del" onclick="changenumdel()">-</dd>
                            </dl>
                        </div>
                        <%if(isLogin==true){ %>
                        <div class="pro_addshop"> <a href="javascript:void(0)" onclick="addShopCart(<%out.print(product.getId());%>)">加入购物车 </a></div>
                        <%} %>
                        
                        <%if(isLogin==false){ %>
                        <div class="pro_addshop"> <a href="login.jsp">加入购物车 </a></div>
                        <%} %>
                    </div>
                    
                </div>
            </div>
        </div>
    	<div class="c5_b2">
        	
            <div class="c5_b2_right">
            	<!--选项卡-->
            	<ul class="c5_b2_tabs">
                	<li id="1" class="current">商品介绍</li>
                    <li id="2" >规格参数</li>
                    <li id="3">包装清单</li>
                    <li id="4" >商品评价</li>
                </ul>
                <!--内容1-->
                <div id="content1" class="current">
              	<div class="c5_b2_right_1 box">
                        <div class="introduce_item">
                          <ul>
                        	<li>商品名称：<%out.print(product.getName());%></li>
                            <li>商品编号：<%out.print(product.getISBN());%></li>
                            <li>作者：<%out.print(product.getWriter()+"<br><br><br>");%></li>
                            <li class="fr"><a class="color_link1" href="#">更多参数>></a></li>
                          </ul>
                        </div>
               		</div>
                    <div class="intro_pics">
                    	<img class="intro_pic" src="<%out.print(product.getFeature_images());%>">
                    </div>
                    </div>
                    
                    <!--内容2-->
                    <div id="content2" class="hide">
                    
                    <div class="c5_b2_right_1 box">
                        <div class="introduce_item2">
                          <ul>
                        	<li>版次：<%out.print(product.getVersion());%></li>
                            <li>页数：<%out.print(product.getPages()+"<br><br><br>");%></li>
                            <li>出版社：<%out.print(product.getPublish().getName()+"<br><br><br>");%></li>
                            <li>编辑推荐：<%out.print(product.getExplain()+"<br><br><br>");%></li>
                            </ul>
                            <span></span>
                            <ul>
                        	<li>印刷时间：<%out.print(product.getPublish_date());%></li>
                            <li>页数：<%out.print(product.getPages());%></li>
                            <li>销量：<%out.print(product.getSale_num()+"<br><br><br>");%></li>
                            </ul>
                            <span></span>
                            <ul>
                            <li>库存：<%out.print(product.getStock());%></li>
                            <li>上架时间：<%out.print(product.getStart_date());%></li>
                            <li>ISBN：<%out.print(product.getISBN()+"<br><br><br>");%></li>
                             </ul>
                        </div>
               		</div>
               		
                    <div class="intro_pics">
                    	<img class="intro_pic" src="<%out.print(product.getFeature_images());%>">
                    </div> 
                    </div>
                    
                     <!--内容3-->
                    <div id="content3" class="hide">
                    <div class="c5_b2_right_1 box">
                        <div class="introduce_item3">
                          <ul>
                        	<li>目录：<%out.print("<br>"+product.getBill()+"<br><br><br>");%></li>
                            <li>商品描述：<%out.print(product.getDescription());%></li>
                          </ul>
                        </div>
               		</div>
                    <div class="intro_pics">
                    	<img class="intro_pic" src="<%out.print(product.getFeature_images());%>">
                    	
                    </div>
                    </div>
                    
                     <!--内容4-->
                     <%
                     HttpSession getsession=request.getSession();
                     List<Evaluate> currentevaluate=null;
                     User user=null;
                     if(isLogin==true){
                      user=(User)getsession.getAttribute("user");
                      currentevaluate=Loaddata.getEvaluate(user.getId(), product.getId()); 
                     }
                     List<Evaluate>evaluates=Loaddata.findEvaByProductId(product.getId());
                     int star=1;
                     %>
                    <div id="content4" class="hide">
                    <form action="EvaluateServlet" method="post" name="eva">
                    	<div class="introduce_item3">
	                  			<div class="star">
	                 		与商品描述相符
				<a href="javascript:click(1)" style="display:inline-block; margin-left: 10px;"><img src="img/star.png" id="star1" onMouseOver="over(1)" onMouseOut="out(1)" /></a>
				<a href="javascript:click(2)" style="display:inline-block;"><img src="img/star.png" id="star2" onMouseOver="over(2)" onMouseOut="out(2)" /></a>
				<a href="javascript:click(3)" style="display:inline-block;"><img src="img/star.png" id="star3" onMouseOver="over(3)" onMouseOut="out(3)" /></a>
				<a href="javascript:click(4)" style="display:inline-block;" ><img src="img/star.png" id="star4" onMouseOver="over(4)" onMouseOut="out(4)"/></a>
				<a href="javascript:click(5)" style="display:inline-block;"><img src="img/star.png" id="star5" onMouseOver="over(5)" onMouseOut="out(5)"/></a>
				<%if(user!=null){ %>
				<input type="hidden" name="userid" value="<%=user.getId()%>">
				<%} %>
				<input type="hidden" name="productid" value="<%=product.getId()%>">
				<input id="star" type="hidden" name="star" value="1">
				<input id="bookid" type="hidden" name="bookid" value="<%=product.getId()%>">
				<span id="message"></span>
								</div>
                        </div>
							<textarea name="comments" rows="15" cols="100" style="margin-left: 160px;"></textarea>
							<%if(isLogin==false){ %>
							<img alt="无" src="images/21_12.png" style="margin-left: 670px; margin-top: 10px;">
							<%} else{ %>
							<% 
								if(currentevaluate.size()==0){	//没有购买过
							%>
							<img alt="无" src="images/21_11.png" style="margin-left: 670px; margin-top: 10px;">
							<%} else{				//购买过
									if(Loaddata.isEva(currentevaluate)==0){	//未评论过							
							%>
								<img alt="无" src="images/21_07.png" style="margin-left: 670px; margin-top: 10px;" onclick="comments2()">
							<%} else{		//评论过%>
									<img alt="无" src="images/21_09.png" style="margin-left: 670px; margin-top: 10px;">
							<%} }}%>
							<%for(int i=0;i<evaluates.size();i++){
								Evaluate evaluate=evaluates.get(i);
								%>
							<div class="comments">
								<div class="info">
								<img src="images/qrcode_jprj.jpg">
										<a href="#" style="margin-top: 20px;"><%=evaluate.getUser().getName() %></a>
									<div class="content">
										<span>
											<%=evaluate.getContent() %>
										</span>
											<%if(evaluate.getImages()!=null) {%>
											<img alt="无图片评论" src="<%=evaluate.getImages()%>">
											<%} %>
											
									</div>
								</div>
								<div class="msg">
									<span>评论时间：<%=evaluate.getEva_date() %></span>
									<span>图书版本：【精装版】</span>
									<span>评论星级：<img src="images/star<%=evaluate.getStar() %>.png" style="float: right; height: 25px;"></span>
								</div>
							</div>
							<%} %>
							
						</form>	
                    </div>
                    <!-- 结束 -->
                </div>
<script>

window.onload=function(){
	var l1=document.getElementById("1");
	var l2=document.getElementById("2");
	var l3=document.getElementById("3");
	var l4=document.getElementById("4");
	var con1=document.getElementById("content1");
	var con2=document.getElementById("content2");
	var con3=document.getElementById("content3");
	var con4=document.getElementById("content4");
	var selectarray=[l1,l2,l3,l4];
	for(var i=0;i<selectarray.length;i++)
		{
		selectarray[i].index=i;
		selectarray[i].onmouseover=function()
		{
			show(this.index);				
		}
		}
	
	function show(pos)
	{
		if(pos=="0")
			{
				clear();
				l1.className="current";				
				con1.className="current";
			}
		
		else if(pos=="1")
		{
			clear();
			l2.className="current";				
			con2.className="current";
		}
		
		else if(pos=="2")
		{
			clear();
			l3.className="current";				
			con3.className="current";
		}
		
		else
		{
			clear();
			l4.className="current";				
			con4.className="current";
		}
	}
	function clear()
	{
		l1.className="";
		l2.className="";
		l3.className="";
		l4.className="";
		con1.className="hide";
		con2.className="hide";
		con3.className="hide";
		con4.className="hide";
	}
	
}
</script>      
            <div class="c5_b2_right">
              	<div class="c5_b2_right_2">
                    	<h1>正品行货</h1>
                        <p>briup网上商城向您保证所售商品均为正品，briup自营商品开具机打发票或电子发票。</p>
                        
                        <h1>全国联保</h1>
                        <p>凭质保证书及briup网上商城发票，可享受全国联保服务，与您亲临商场选购的商品享受相同的质量保证。briup网上商城还为您提供具有竞争力的商品价格和运费政策，请您放心购买！</p>
                        
                        <h1>正品行货</h1>
                        <p>briup网上商城向您保证所售商品均为正品，briup自营商品开具机打发票或电子发票。</p>
                        
                        <h1>全国联保</h1>
                        <p>凭质保证书及briup网上商城发票，可享受全国联保服务，与您亲临商场选购的商品享受相同的质量保证。briup网上商城还为您提供具有竞争力的商品价格和运费政策，请您放心购买！</p>
                        
            		</div> 
            </div>
            
            <!--左侧栏-->
            <div class="c5_b2_left_container">
                <div class="c5_b2_left box">
                    <h1>服务时间 早9：00~凌晨1：00</h1>
                    <p>
                    <a href="#">
                        <img class="callmebyqq" src="images/icon_qq_03.png"/>
                    </a>
                    </p>
                </div>
                
                <div class="c5_b2_left box">
                    <h1>其他种类</h1>
                    <dl>
                        <dd>文学类</dd>
                        <dd>漫画类</dd>
                        <dd>通书类</dd>
                    </dl>
                    <dl>
                        <dd>文学类</dd>
                        <dd>漫画类</dd>
                        <dd>通书类</dd>
                    </dl>
                </div>
            </div> 
        </div>
    </div>
    	
    <div class="c20"></div>
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
