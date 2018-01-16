//检查用户名是否存在
var gversion=1;	//购买书的版本，全局变量
var gnum=1;
function checkName(name)
{
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
	  if(xmlhttp.responseText=='yes')
	  document.getElementById("name").innerHTML="<img src='images/ok.png' width='25px' heigth='25px'></img>";
	  else
	  document.getElementById("name").innerHTML="<img src='images/no.png' width='25px' heigth='25px'></img>";  
    }
 
  }
xmlhttp.open("GET","RegisterServlet?name="+name,true);
xmlhttp.send();
}
//检查用户名是否存在

/*切换图片效果*/
function changesmallPic(bookid,liid,pos)
{
	
	clear();
	document.getElementById(liid).className='current';
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  document.getElementById("bigPic").src=xmlhttp.responseText;
	    }
	 
	  }
	xmlhttp.open("GET","ChangePicServlet?bookid="+bookid+"&pos="+pos+"&code="+Math.random(),true);
	xmlhttp.send();
}
function clear()
{
	document.getElementById('pic1').className='';
	document.getElementById('pic2').className='';
	document.getElementById('pic3').className='';
	document.getElementById('pic4').className='';
	document.getElementById('pic5').className='';
}

/*修改商品数量*/
function changenumadd()
{
	var current=document.getElementById('num').value;
	if(current==""){
		current=0;
	}
	current=parseInt(current)+1;
	document.getElementById('num').value=current;
	gnum=current;
}
function changenumdel()//只对加减有效
{
	var current=document.getElementById('num').value;
	if(current=="")
		current=2;
	if(current>1){
	current=parseInt(current)-1;
	document.getElementById('num').value=current;
	gnum=current;
}
}
function getnum(getnum)
{
	gnum=getnum;
}

/*版本选择Ajax*/
function checkVersion(bookid,version)
{
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
		  switch (version) {
		case '1':
			gversion='1';
			document.getElementById('ver1').innerHTML="<div id='ver1'><span><a href='javascript:;' onclick=\"checkVersion('"+bookid+ "','1')\">精装版</a></span></div>";
			document.getElementById('ver2').innerHTML="<div id='ver2'><a href='javascript:;' onclick=\"checkVersion('"+bookid+ "','2')\">简装版</a></div>";
			document.getElementById('ver3').innerHTML="<div id='ver3'><a href='javascript:;' onclick=\"checkVersion('"+bookid+ "','3')\">收藏版</a></div>";
		break;
		case '2':
			gversion='2';
			 document.getElementById('ver1').innerHTML="<div id='ver1'><a href='javascript:;' onclick=\"checkVersion('"+bookid+ "','1')\">精装版</a></div>";
				document.getElementById('ver2').innerHTML="<div id='ver2'><span><a href='javascript:;' onclick=\"checkVersion('"+bookid+ "','2')\">简装版</a></span></div>";
				document.getElementById('ver3').innerHTML="<div id='ver3'><a href='javascript:;' onclick=\"checkVersion('"+bookid+ "','3')\">收藏版</a></div>";
			break;
		case '3':
			gversion='3';
			 document.getElementById('ver1').innerHTML="<div id='ver1'><a href='javascript:;' onclick=\"checkVersion('"+bookid+ "','1')\">精装版</a></div>";
				document.getElementById('ver2').innerHTML="<div id='ver2'><a href='javascript:;' onclick=\"checkVersion('"+bookid+ "','2')\">简装版</a></div>";
				document.getElementById('ver3').innerHTML="<div id='ver3'><span><a href='javascript:;' onclick=\"checkVersion('"+bookid+ "','3')\">收藏版</a></span></div>";
			break;
		}
		if(xmlhttp.responseText!=null)
			{
			document.getElementById('myprice').innerHTML="<div id=myprice class='pro_price_x'><p> briup价：<b>"+xmlhttp.responseText+"</b> <a href='#'>(降价通知)</a></p></div>";
			}
  }
  }
xmlhttp.open("GET","ProductEnoughServlet?&bookid="+bookid+"&version="+version+"&num"+gnum+"&record"+Math.random(),true);
xmlhttp.send();
}


function addShopCart(bookid){
	var xmlhttp;
	//var current=document.getElementById('num').value;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  alert('加入购物车成功')
	    }
	 
	  }
	xmlhttp.open("GET","AddShopServlet?bookid="+bookid+"&version="+gversion+"&num="+gnum+"&code="+Math.random(),true);
	xmlhttp.send();
}

function checkPassword(){
	var password=$("input[type='password'][name='password']").val();
	if(password.trim().length==0){
		$("span[id='password']").html("<img src='images/no.png' width='25px' height='25px'></img>");
		return false;
	}else{
		$("span[id='password']").html("<img src='images/ok.png' width='25px' height='25px'></img>");
		return true;
	}
}
function checkZip(){
	 var postcode=$("input[type='text'][name='zip']").val();  
     if(is_postcode(postcode)){  
    	$("span[id='zip']").html("<img src='images/ok.png' width='25px' height='25px' ></img>");
    	return true;
     }else{  
    	 $("span[id='zip']").html("<img src='images/no.png' width='25px' height='25px'></img>"); 
         return false;  
     }  
}
/*邮政编码验证*/
function is_postcode(postcode) {  
    if ( postcode.trim().length ==0) { 
		$("span[id='zip']").html("<img src='images/no.png' width='25px' height='25px'></img>");
        return false;  
    } else {  
        if (! /^[0-9][0-9]{5}$/.test(postcode)) { 
    		$("span[id='zip']").html("<img src='images/no.png' width='25px' height='25px'></img>");
            return false;  
        }  
    }  
    return true;  
}  
function checkAddress(){
	var address=$("input[type='text'][name='address']").val();
	if(address.trim().length==0){
		$("span[id='address']").html("<img src='images/no.png' width='25px' height='25px'></img>");
		return false;
	}else{
		$("span[id='address']").html("<img src='images/ok.png' width='25px' height='25px'></img>");
		return true;
	}
}

function checknamenull(){
	var address=$("input[type='text'][name='name']").val();
	if(address.trim().length==0){
		$("span[id='name']").html("<img src='images/no.png' width='25px' height='25px'></img>");
		return false;
	}else{
		$("span[id='name']").html("<img src='images/ok.png' width='25px' height='25px'></img>");
		return true;
	}
}

function checkTelephone(){
	var telephone=$("input[type='text'][name='telephone']").val();
	if(is_mobile(telephone)){
		$("span[id='telephone']").html("<img src='images/ok.png' width='25px' height='25px'></img>");
		return true;
	}else{
		return false;
	}
}
/*验证手机号*/  
function is_mobile(mobile) {  
     if( mobile.trim().length==0) {  
 	 $("span[id='telephone']").html("<img src='images/no.png' width='25px' height='25px'></img>");
      return false;  
     } else {  
       if( ! /^0{0,1}(13[0-9]|15[0-9]|18[0-9]|14[0-9])[0-9]{8}$/.test(mobile) ) {  
      $("span[id='telephone']").html("<img src='images/no.png' width='25px' height='25px'></img>");
        return false;  
      }  
      return true;  
    }  
} 
function checkEmail(){
	var email=$("input[type='text'][name='email']").val();
	if(is_email(email)){
		 	 $("span[id='email']").html("<img src='images/ok.png' width='25px' height='25px'></img>");
		 	 return true;
	}else{
		 	 $("span[id='email']").html("<img src='images/no.png' width='25px' height='25px'></img>");
		 	 return false;
	}
}
//邮箱验证
function is_email(email) {  
    if ( email.trim().length==0) {  
         $("span[id='email']").html("<img src='images/no.png' width='25px' height='25px'></img>");
        return false;  
    } else {  
        if (! /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(email)) {  
        	$("span[id='email']").html("<img src='images/no.png' width='25px' height='25px'></img>");
            return false;  
        }  
    }  
    return true;  
}  

/*登录表单验证*/
function checkForm(){
	if(checkName($("input[type='text'][name='name']").val())==false)return false;
	if(checkPassword()==false)return false;
	if(checkZip()==false)return false;
	if(checkAddress()==false)return false;
	if(checkTelephone()==false)return false;
	if(checkEmail()==false)return false;
	var yes=$("input[type='checkbox'][name='yes']").get(0).checked;
	if(!yes){
		$.messager.alert("消息提示","阅读协议并勾选","error");
		return false;
	}
}

function checkForm2(){
	var flag=true;
	if(checknamenull($("input[type='text'][name='name']").val())==false)flag=false;
	if(checkAddress()==false)flag=false;
	if(checkTelephone()==false)flag=false;
	if(flag==false)
		{alert('请检查收货人信息')
		return false;
		}
		else
			document.form2.submit();
}

function pay()
{
	alert('付款成功')
	document.form3.submit();
}

function pay2(orderid)
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    }
	 
	  }
	xmlhttp.open("GET","PayServlet?orderid="+orderid,true);
	xmlhttp.send();
}

function clearpage()
{
	var total=document.getElementById("pagenum").value;
	for(var i=1;i<=total;i++)
	document.getElementById(i).className="";
}

function page(pagenum)	//修改属性
{
	document.getElementById("currpage").value=pagenum;
}

$(function(){
	clearpage();
	var id=document.getElementById("currpage").value;
	document.getElementById(id).className="current";
	}
)

function pagebrf()	//只修改属性
{
	clearpage();
	var id=document.getElementById("currpage").value;
	if(id==1)
		{
		document.getElementById(id).className="current";
		}
	else
		{
		document.getElementById(id-1).className="current";
		}
}

function pageatf(pagenum)
{
	clearpage();
	var id=document.getElementById("currpage").value;
	if(id==pagenum)
		{
		document.getElementById(id).className="current";
		}
	else
		{
		document.getElementById(id+1).className="current";
		}
}
function clearrank()
{
	var size=document.getElementById("ranksize").value;
	for(var i=0;i<size;i++)
		{
			document.getElementById("rank#"+i).className="";
		}
}

function clearfil()
{
	for(var i=0;i<=3;i++)
		document.getElementById("fil"+i).className="";
}

function clearpublish()
{
	var size=document.getElementById("publishsize").value;
	//	alter('ww');
	for(var i=0;i<size;i++)
		{
			document.getElementById("publish#"+i).className="";
		}
}
function rank(rankid)
{
	localStorage.rankid=rankid;
	clearrank();
	document.getElementById(rankid).className="current";
	if(document.getElementById("exrank"))
		{
			var parent=document.getElementById("box");
			var old=document.getElementById("exrank");
			parent.replaceChild(createrank(rankid), old);
		}
	else
	createrank(rankid);
	reflesh();
}

function publish(publishid)
{
	localStorage.publishid=publishid;
	clearpublish();
	document.getElementById(publishid).className="current";
	if(document.getElementById("expublish"))
	{	
		var parent=document.getElementById("box");
		var old=document.getElementById("expublish");
		parent.replaceChild(createpublish(publishid), old);
	}
else
	createpublish(publishid);
	reflesh();
}

function fil(id)
{
	clearfil();
	document.getElementById("fil"+id).className="current";
	localStorage.orderid=id;
	reflesh();
}
function createrank(id)
{
	var parent=document.getElementById("box");
	//创建li标签
	var li=document.createElement("li");
	li.setAttribute("id", "exrank");
	//将li标签添加
	parent.appendChild(li);
	//创建第一个span
	var span1=document.createElement("span");
	span1.setAttribute("class", "search_key");
	span1.innerHTML="价格：";
	//将第一个span添加
	li.appendChild(span1);
	
	//创建第二个span
	var span2=document.createElement("span");
	span2.setAttribute("class", "search_val");
	span2.innerHTML=resolverank(id);
	//span2.innerHTML="0-99";
	//将第二个span添加
	li.appendChild(span2);
	
	//创建第三个span
	var span3=document.createElement("span");
	span3.setAttribute("class", "search_del");
	span3.setAttribute("onclick", "delrank()");
	//将第二个span添加
	li.appendChild(span3);
	return li;
}

function createpublish(id)
{
	var parent=document.getElementById("box");
	//创建li标签
	var li=document.createElement("li");
	li.setAttribute("id", "expublish");
	//将li标签添加
	parent.appendChild(li);
	//创建第一个span
	var span1=document.createElement("span");
	span1.setAttribute("class", "search_key");
	span1.innerHTML="出版社：";
	//将第一个span添加
	li.appendChild(span1);
	
	//创建第二个span
	var span2=document.createElement("span");
	span2.setAttribute("class", "search_val");
	span2.innerHTML=resolvepublish(id);
	//span2.innerHTML="北京大学出版社";
	//将第二个span添加
	li.appendChild(span2);
	
	//创建第三个span
	var span3=document.createElement("span");
	span3.setAttribute("class", "search_del");
	span3.setAttribute("onclick", "delpublish()");
	//将第二个span添加
	li.appendChild(span3);
	return li;
}

function resolverank(id)
{
	var newid=id.split("#")[1];
	newid=parseInt(newid);
	switch (newid) {
	case 0:return "0-19";
		break;
	case 1:return "20-39";
		break;
	case 2:return "40-59";
		break;
	case 3:return "60-79";
		break;
	case 4:return "80-99";
		break;
	case 5:return "100-119";
		break;
	case 6:return "120-139";
		break;
	case 7:return "140-159";
		break;
	case 8:return "160-179";
	break;
	}
}

function resolvepublish(id)
{
	var newid=id.split("#")[1];
	newid=parseInt(newid);
	switch (newid) {
	case 0:return "人民出版社";
		break;
	case 1:return "清华大学出版社";
		break;
	case 2:return "电子工业出版社";
		break;
	case 3:return "中华书局";
		break;
	case 4:return "中信出版社";
		break;
	case 5:return "上海译文出版社";
		break;
	case 6:return "北京大学出版社";
		break;
	case 7:return "机械工业出版社";
		break;
	}
}
function delrank()
{	
	var type=document.getElementById("type").value;
	var concretype=document.getElementById("concretype").value;
	var parent=document.getElementById("box");
	var child=document.getElementById("exrank");
	parent.removeChild(child);
	localStorage.removeItem("rankid");
	clearrank();
	if("null"==concretype)
	{
		//window.location.href="list.jsp?type="+type;
		window.location.reload();
	}
	else{
		window.location.href="list.jsp?type="+type+"&concretype="+concretype;}
	//reflesh();
}
function delpublish()
{
	var parent=document.getElementById("box");
	var child=document.getElementById("expublish");
	parent.removeChild(child);
	localStorage.removeItem("publishid");
	clearpublish();
	//reflesh();
}
function init() {
	if(localStorage.rankid!=null)
		rank(localStorage.rankid);
	
	if(localStorage.publishid!=null)
	publish(localStorage.publishid);
	
	fil(localStorage.orderid);
}

function reflesh(){
	var currentpage=document.getElementById("currpage").value;
	var type=document.getElementById("type").value;
	var concretype=document.getElementById("concretype").value;
	var priceid=localStorage.rankid;
	var publishid=localStorage.publishid;
	var order=localStorage.orderid;
	var flag=false;
	if(priceid==undefined)
		{
		priceid=null;
		}
	else
		priceid=priceid.split("#")[1];
	if(publishid==undefined)
	{
		publishid=null;
	}
	else
		{
		publishid=publishid.split("#")[1];
		}
	if(order==undefined)
	{
		order=0;
	}
	//alert("currentpage="+currentpage+" type="+type+" concretype="+concretype+" priceid="+priceid+"  publishid="+publishid+"  order="+order);
	
	var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    }
  }
xmlhttp.open("GET","DataServlet?type="+type+"&concretype="+concretype+"&priceid="+priceid+"&publishid="+publishid+"&orderid="+order,true);
xmlhttp.send();
}


var check = 0;//该变量是记录当前选择的评分
var time = 0;//该变量是统计用户评价的次数，这个是我的业务要求统计的（改变评分不超过三次），可以忽略

/*over()是鼠标移过事件的处理方法*/
function over(param){
	if(param == 1){
		$("#star1").attr("src","img/star_red.png");//第一颗星星亮起来，下面以此类推
		$("#message").html("很差");//设置提示语，下面以此类推
	}else if(param == 2){
		$("#star1").attr("src","img/star_red.png");
		$("#star2").attr("src","img/star_red.png");
		$("#message").html("比较差");
	}else if(param == 3){
		$("#star1").attr("src","img/star_red.png");
		$("#star2").attr("src","img/star_red.png");
		$("#star3").attr("src","img/star_red.png");
		$("#message").html("一般");
	}else if(param == 4){
		$("#star1").attr("src","img/star_red.png");
		$("#star2").attr("src","img/star_red.png");
		$("#star3").attr("src","img/star_red.png");
		$("#star4").attr("src","img/star_red.png");
		$("#message").html("比较好");
	}else if(param == 5){
		$("#star1").attr("src","img/star_red.png");
		$("#star2").attr("src","img/star_red.png");
		$("#star3").attr("src","img/star_red.png");
		$("#star4").attr("src","img/star_red.png");
		$("#star5").attr("src","img/star_red.png");
		$("#message").html("很好");
	}
}
/*out 方法是鼠标移除事件的处理方法，当鼠标移出时，恢复到我的打分情况*/
function out(){
	if(check == 1){//打分是1，设置第一颗星星亮，其他星星暗，其他情况以此类推
		$("#star1").attr("src","img/star_red.png");
		$("#star2").attr("src","img/star.png");
		$("#star3").attr("src","img/star.png");
		$("#star4").attr("src","img/star.png");
		$("#star5").attr("src","img/star.png");
		$("#message").html("");
	}else if(check == 2){
		$("#star1").attr("src","img/star_red.png");
		$("#star2").attr("src","img/star_red.png");
		$("#star3").attr("src","img/star.png");
		$("#star4").attr("src","img/star.png");
		$("#star5").attr("src","img/star.png");
		$("#message").html("");
	}else if(check == 3){
		$("#star1").attr("src","img/star_red.png");
		$("#star2").attr("src","img/star_red.png");
		$("#star3").attr("src","img/star_red.png");
		$("#star4").attr("src","img/star.png");
		$("#star5").attr("src","img/star.png");
		$("#message").html("");
	}else if(check == 4){
		$("#star1").attr("src","img/star_red.png");
		$("#star2").attr("src","img/star_red.png");
		$("#star3").attr("src","img/star_red.png");
		$("#star4").attr("src","img/star_red.png");
		$("#star5").attr("src","img/star.png");
		$("#message").html("");
	}else if(check == 5){
		$("#star1").attr("src","img/star_red.png");
		$("#star2").attr("src","img/star_red.png");
		$("#star3").attr("src","img/star_red.png");
		$("#star4").attr("src","img/star_red.png");
		$("#star5").attr("src","img/star_red.png");
		$("#message").html("");
	}else if(check == 0){
		$("#star1").attr("src","img/star.png");
		$("#star2").attr("src","img/star.png");
		$("#star3").attr("src","img/star.png");
		$("#star4").attr("src","img/star.png");
		$("#star5").attr("src","img/star.png");
		$("#message").html("");
	}
}
/*click()点击事件处理，记录打分*/
function click(param){
	time++;//记录打分次数
	check = param;//记录当前打分
	out();//设置星星数
	document.getElementById("star").value=param;
}
function comments2()
{
	alert('评论成功!');
	document.eva.submit();
}

$(function(){
	//元素将会在3000毫秒内向着指定的css属性值进行变化
	// opacity 透明度  left top height width
	var y = $("div[class='tsc'] a").offset().top;
	var a = $("#bigPic").css("margin-top");
	my = y-parseInt(a.substring(0,a.length-2));
	var x = $("div[class='tsc'] a").offset().left;
	var hei = $("div[class='tsc'] a").height();
	var wid = $("div[class='tsc'] a").width();
	console.log("Y "+y);
	console.log("X "+x);
	console.log("height" +hei);
	console.log("width" +wid);
	console.log("my"+my);
	console.log("li[class='c4_b5_c_box']");
		$(a).on("click",function(){
			$("bigPic").eq(i).animate({
				left:x,
				top:my,
				height:hei,
				width:wid,
				opacity:0
			},2000,function(){
				alert("加入购物车完成");
			});
		});
	
	
});