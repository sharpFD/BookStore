var OptionList=function(className,callFun){
    this.currValue="",//当前值
        this.currIndex="",//当前选中的索引
        this.currListHtml="";//当前行生成的html
        this.className=className,
        this.callFun=callFun;
}

OptionList.prototype={
    createListHtml:function(datanodes){
        var self=this;
        for(var i=0;i< datanodes.length;i++){
            var curr_option_dc=new option_dc( datanodes[i].text, datanodes[i].value, datanodes[i].callFun, datanodes[i].selected);
            this.currListHtml+=curr_option_dc.createLineHtml(curr_option_dc);
        }
        this.currListHtml="<ul>"+this.currListHtml+"</ul>";

        jQuery("."+this.className).html(this.currListHtml);
        jQuery("."+this.className +" a").click(function(){
            currText= jQuery(this).attr("data_text");
            currValue= jQuery(this).attr("data_value");
            currIndex="index_"+currValue;
            jQuery("."+self.className+" li").removeClass("tb-selected");
            jQuery("."+self.className+" ."+currIndex).addClass("tb-selected");
           var result={value:currValue,index:currIndex,text:currText}
            eval(self.callFun+"(result)");
        });
    },createListHtmlForAjax:function(url){
        var self=this;
        jQuery.ajax({
            type: "get",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
            url: url,//要访问的后台地址
            success: function(result){//msg为返回的数据，在这里做数据绑定
                var datanodes=eval(result);
                self.createListHtml(datanodes);
            }
        });
    }

}

var option_dc=function(text,value,callFun,selected){
           this.text=text,
           this.value=value,
           this.callFun=callFun,
           this.selected=selected;

}
option_dc.prototype={
       createLineHtml:function(option_dc){
           var currLineHtml="\<span\>"+option_dc.text+"\</span\>";
           currLineHtml="\<a href='#' data_value='"+option_dc.value+"' data_text='"+option_dc.text+"' >"+ currLineHtml+" \</a\>";
           if(option_dc.selected){
               currLineHtml="\<li class='"+"index_"+option_dc.value+" tb-selected'\>"+ currLineHtml+" \<i\>已选中\</i\> \</li\>";
           } else{
               currLineHtml="\<li class='index_"+option_dc.value+"' \>"+ currLineHtml+" \<i\>已选中\</i\> \</li\>";
           }
           return currLineHtml;
       }

}
