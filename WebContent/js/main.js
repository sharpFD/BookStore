/*基于价格进行筛选*/
function price_select(value,rank_minprice,rank_maxprice){
	$("#price_sel").text(rank_minprice+"-"+rank_maxprice);
	$("#price_sel").attr("value",value);
	var publish_id=$("#pub_sel").text();
	$.ajax({
		url:"SelectProduct",
		type:"post",
		cache:false,
		dataType:"json",
		data:{price_rank:value,minprice:rank_minprice,maxprice:rank_maxprice,publish_id:publish_id},
		success:function(data){
			$(".c4_b5_c_boxes").empty();
			$.each(data,function(i,e){
				//$.each(e,function(i1,e1){
					var newLi=$("<li class='c4_b5_c_box'>"
                    	+"<div class='c4_b5_c_box_pic'>"
                        	+"<div class='c4b5_pic_view'>"
                            	+"<a href='#'><img src='"+e.images+"'></a>"
                            +"</div>"
                        +"</div>"
                        +"<div class='c4_b5_c_box_txt'>"
                        	+"<h1>￥ "+e.price+".0</h1>"
                            +"<h2><a href='#'>"+e.name+"</a></h2>"
                        +"</div>"
                        +"<div class='c4b5_el'>"
                        	+"<div class='c4b5_el_x'>"
                            	+"<span class='wjx01'></span>"
                            +"</div>"
                        +"</div>"
                        +"<ul class='c4b5_option'>"
                                +"<li class='shopcar_box'><span class='shopcar01' va='${product.id}'></span>加入购物车</li>"
                        +"</ul>"
                    +"</li>");
					$(".c4_b5_c_boxes").append(newLi);
				//});
			});
		}
	});
}
/*基于销量价格新品进行排序*/
function order_product(value){
	$(".c4_b4_nav li[id="+value+"]").attr("class","current");
	var v=parseInt(value);
	for(var i=0;i<=3;i++){
		if(v!=i){
			$(".c4_b4_nav li[id="+i+"]").removeAttr("class");
		}
	}
	var price=$("#price_sel").text();
	var publish=$("#pub_sel").text();
	$.ajax({
		url:"SaleOrder",
		type:"post",
		dataType:"json",
		data:{price:price,publish:publish,method:value},
		success:function(data){
			$(".c4_b5_c_boxes").empty();
			$.each(data,function(i,e){
				//$.each(e,function(i1,e1){
					var newLi=$("<li class='c4_b5_c_box'>"
                    	+"<div class='c4_b5_c_box_pic'>"
                        	+"<div class='c4b5_pic_view'>"
                            	+"<a href='#'><img src='"+e.images+"'></a>"
                            +"</div>"
                        +"</div>"
                        +"<div class='c4_b5_c_box_txt'>"
                        	+"<h1>￥ "+e.price+".0</h1>"
                            +"<h2><a href='#'>"+e.name+"</a></h2>"
                        +"</div>"
                        +"<div class='c4b5_el'>"
                        	+"<div class='c4b5_el_x'>"
                            	+"<span class='wjx01'></span>"
                            +"</div>"
                        +"</div>"
                        +"<ul class='c4b5_option'>"
                                +"<li class='shopcar_box'><span class='shopcar01' va='${product.id}'></span>加入购物车</li>"
                        +"</ul>"
                    +"</li>");
					$(".c4_b5_c_boxes").append(newLi);
				//});
			});
		}
	});
}
/*登录表单验证*/
function checkName(name){
	if(name==null||name==""){
	name=$("input[type='text'][name='name']").val();
	}
	if(name.trim().length==0){
		$.messager.alert("消息提示","用户名不能为空","error");
		$("span[id='name']").html("<img src='css/easyUI/icons/no.png'></img>");
		return false;
	}else{
		$.ajax({
			url:"CheckName",
			type:"post",
			data:{name:name},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,e){
					if(i=="no"){
						$("span[id='name']").html("<img src='css/easyUI/icons/ok.png'></img>");
						return true;
					}else{
						$.messager.alert("消息提示","用户名已经存在","error");
						$("span[id='name']").html("<img src='css/easyUI/icons/no.png'></img>");
						return false;
					}
				});
			}
		});
	}
}
function checkPassword(){
	var password=$("input[type='password'][name='password']").val();
	if(password.trim().length==0){
		$.messager.alert("消息提示","密码不能为空","error");
		$("span[id='password']").html("<img src='css/easyUI/icons/no.png'></img>");
		return false;
	}else{
		$("span[id='password']").html("<img src='css/easyUI/icons/ok.png'></img>");
		return true;
	}
}
function checkZip(){
	 var postcode=$("input[type='text'][name='zip']").val();  
     if(is_postcode(postcode)){  
    	$("span[id='zip']").html("<img src='css/easyUI/icons/ok.png'></img>");
    	return true;
     }else{  
    	 $("span[id='zip']").html("<img src='css/easyUI/icons/no.png'></img>"); 
         return false;  
     }  
}
/*邮政编码验证*/
function is_postcode(postcode) {  
    if ( postcode.trim().length ==0) { 
    	$.messager.alert("消息提示","邮政编码不能为空","error");
		$("span[id='zip']").html("<img src='css/easyUI/icons/no.png'></img>");
        return false;  
    } else {  
        if (! /^[0-9][0-9]{5}$/.test(postcode)) { 
        	$.messager.alert("消息提示","邮政编码必须是6位数字","error");
    		$("span[id='zip']").html("<img src='css/easyUI/icons/no.png'></img>");
            return false;  
        }  
    }  
    return true;  
}  
function checkAddress(){
	var address=$("input[type='text'][name='address']").val();
	if(address.trim().length==0){
		$.messager.alert("消息提示","地址值不能为空","error");
		$("span[id='address']").html("<img src='css/easyUI/icons/no.png'></img>");
		return false;
	}else{
		$("span[id='address']").html("<img src='css/easyUI/icons/ok.png'></img>");
		return true;
	}
}
function checkTelephone(){
	var telephone=$("input[type='text'][name='telephone']").val();
	if(is_mobile(telephone)){
		$("span[id='telephone']").html("<img src='css/easyUI/icons/ok.png'></img>");
		return true;
	}else{
		return false;
	}
}
/*验证手机号*/  
function is_mobile(mobile) {  
     if( mobile.trim().length==0) {  
     $.messager.alert("消息提示","电话号码不能为空","error");
 	 $("span[id='telephone']").html("<img src='css/easyUI/icons/no.png'></img>");
      return false;  
     } else {  
       if( ! /^0{0,1}(13[0-9]|15[0-9]|18[0-9]|14[0-9])[0-9]{8}$/.test(mobile) ) {  
      $.messager.alert("消息提示","电话号码必须是13位数字且符合规范","error");
      $("span[id='telephone']").html("<img src='css/easyUI/icons/no.png'></img>");
        return false;  
      }  
      return true;  
    }  
} 
function checkEmail(){
	var email=$("input[type='text'][name='email']").val();
	if(is_email(email)){
		 	 $("span[id='email']").html("<img src='css/easyUI/icons/ok.png'></img>");
		 	 return true;
	}else{
		 	 $("span[id='email']").html("<img src='css/easyUI/icons/no.png'></img>");
		 	 return false;
	}
}
//邮箱验证
function is_email(email) {  
    if ( email.trim().length==0) {  
    	 $.messager.alert("消息提示","邮箱不能为空","error");
         $("span[id='email']").html("<img src='css/easyUI/icons/no.png'></img>");
        return false;  
    } else {  
        if (! /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(email)) {  
        	$.messager.alert("消息提示","邮箱不符合规范","error");
        	$("span[id='email']").html("<img src='css/easyUI/icons/no.png'></img>");
            return false;  
        }  
    }  
    return true;  
}  
//加载页面初始化的代码
$(function(){
	/*默认对话框是关闭的状态*/
	$('#dlg').dialog('close');
	var sg=$("input[type='hidden'][name='msg']").val();
	if(sg.trim().length!=0){
		$.messager.alert("消息提示",sg,"error");
	}
});
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

/*用户登录验证*/
function checkLoginName(){
	var name=$("input[type='text'][name='name']").val();
	if(name.trim().length==0){
		$.messager.alert("消息提示","用户名不能为空","error");
		return false;
	}else{
		$.ajax({
			url:"CheckName",
			type:"post",
			data:{name:name},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,e){
					if(i=="no"){
						$.messager.alert("消息提示","用户不存在","error");
						return false;
					}else{
						return true;
					}
				});
			}
		});
	}
}
function checkLoginPassword(){
	var password=$("input[type='password'][name='password']").val();
	if(password.trim().length==0){
		$.messager.alert("消息提示","密码不能为空","error");
		return false;
	}
}
function checkLogin(){
	if(!checkLoginName)return false;
	if(!checkLoginPassword)return false;
}
/*个人信息链接*/
function Userinfo(){
	window.location.href="user/userinfo.jsp";
}

function changeTab(elementId1,elementId2,elementId3,elementId4,no)
{
	if(no==1)
	{
		document.getElementById(elementId1).style.display="block";
		document.getElementById(elementId2).style.display="none";
		document.getElementById(elementId3).style.display="none";
		document.getElementById(elementId4).style.display="none";
	}
	else if(no==2)
	{
		document.getElementById(elementId1).style.display="none";
		document.getElementById(elementId2).style.display="block";
		document.getElementById(elementId3).style.display="none";
		document.getElementById(elementId4).style.display="none";

	}
	else if(no==3)
	{
		document.getElementById(elementId1).style.display="none";
		document.getElementById(elementId2).style.display="none";
		document.getElementById(elementId3).style.display="block";
		document.getElementById(elementId4).style.display="none";

	}
	else
	{
		document.getElementById(elementId1).style.display="none";
		document.getElementById(elementId2).style.display="none";
		document.getElementById(elementId3).style.display="none";
		document.getElementById(elementId4).style.display="block";

	}
}


