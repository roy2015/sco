/*修正toFixed四舍六入五成双*/
Number.prototype.toFixed=function (d) { 
    var s=this+""; 
    if(!d)d=0; 
    if(s.indexOf(".")==-1)s+="."; 
    s+=new Array(d+1).join("0"); 
    if(new RegExp("^(-|\\+)?(\\d+(\\.\\d{0,"+(d+1)+"})?)\\d*$").test(s)){
        var s="0"+RegExp.$2,pm=RegExp.$1,a=RegExp.$3.length,b=true;
        if(a==d+2){
            a=s.match(/\d/g); 
            if(parseInt(a[a.length-1])>4){
                for(var i=a.length-2;i>=0;i--){
                    a[i]=parseInt(a[i])+1;
                    if(a[i]==10){
                        a[i]=0;
                        b=i!=1;
                    }else break;
                }
            }
            s=a.join("").replace(new RegExp("(\\d+)(\\d{"+d+"})\\d$"),"$1.$2");

        }if(b)s=s.substr(1); 
        return (pm+s).replace(/\.$/,"");
    }return this+"";
};
/*必须和某个字段相等*/
$.extend($.fn.validatebox.defaults.rules, {  
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }  
});
/**
 * 限制下拉列表只能选择，不能输入其它值
 */
$.extend($.fn.combobox.defaults.rules, {  
    forceSelection: {
        validator:function(value,param){
        	var combo=$(param[0]);
        	return combo.combobox("checkInput",value);
        },
        message:'必须从列表中选择一条记录！'
    }
});

//限制下拉表格只能使用表格内的数据(combogrid)
$.extend($.fn.combogrid.defaults.rules, {  
    forceSelection: {
        validator:function(value,param){
        	var combo=$(param[0]);
        	return combo.combogrid("checkInput",value);
        },
        message:'必须从表格中选择一条记录！'
    }
});
//限制下拉表格只能使用表格内的数据(combobox)
$.extend($.fn.combobox.defaults.rules, {  
    forceComboboxSelection: {
        validator:function(value,param){
        	var combo=$(param[0]);
        	return combo.combobox("checkComboboxInput",value);
        },
        message:'请从下拉框中选择数据！'
    }
});
$.extend($.fn.combobox.methods,{
	checkComboboxInput:function(jq,value){
		var combo=$(jq);
    	var data=combo.combobox("getData");
		var textField=combo.combobox("options").textField;
		var vaild=false;
		if(!value)value=combo.combobox("getText");
		$.each(data,function(i,r){
			if(r[textField]==value){
				vaild=true;
				return false;
			}
		});
        return vaild;
	}
});


$.extend($.fn.combobox.methods,{
	checkInput:function(jq,value){
		var combo=$(jq);
    	var data=combo.combobox("getData");
		var textField=combo.combobox("options").textField;
		var vaild=false;
		if(!value)value=combo.combobox("getText");
		$.each(data,function(i,r){
			if(r[textField]==value){
				vaild=true;
				return false;
			}
		});
        return vaild;
	}
});

$.extend($.fn.combogrid.methods,{
	checkInput:function(jq,value){
		var combo=$(jq);
    	var data=combo.combogrid("grid").datagrid("getData").rows;
		var textField=combo.combo("options").textField;
		var vaild=false;
		if(!value)value=combo.combo("getText");
		$.each(data,function(i,r){
			if(r[textField]==value){
				vaild=true;
				return false;
			}
		});
        return vaild;
	}
});
/**
 * 限制日期框输入是否合法
 */
$.extend($.fn.validatebox.defaults.rules, {  
    checkInput: {  
        validator: function(value, param){  
            return value!==""&&dateParser(value)!=null; 
        },  
        message: '日期输入不合法'  
    }  
});

/*文件选择框*/
$.extend($.fn.datagrid.defaults.editors, {  
    fileField: {
        init: function(container, options){
        	var id = "file"+new Date().getTime();
            var inputFile=$('<input id="'+id+'" type="file" class="datagrid-editable-input">').appendTo(container);
            return inputFile;  
        },  
        getValue: function(target){  
            return $(target).val();  
        },  
        setValue: function(target, value){  
            //$(target).val(value);  
        },
        resize: function(target, width){  
            var input = $(target);  
            if ($.boxModel == true){  
                input.width(width - (input.outerWidth() - input.width()));  
            } else {  
                input.width(width);  
            }  
        }  
    }  
});
/*只读标签*/
$.extend($.fn.datagrid.defaults.editors, {  
    labelField: {
        init: function(container, options){
            var inputFile=$('<input type="text" readonly class="datagrid-editable-input">').appendTo(container);
            return inputFile;  
        },  
        getValue: function(target){  
            return $(target).val();  
        },  
        setValue: function(target, value){  
            $(target).val(value);  
        },
        resize: function(target, width){  
            var input = $(target);  
            if ($.boxModel == true){  
                input.width(width - (input.outerWidth() - input.width()));  
            } else {  
                input.width(width);  
            }  
        }  
    }  
});

/**
 * 日期格式化函数
 * @param date
 * @returns '年-月-日'
 */
function dateFormatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
} 

/**
 * 日期解析函数
 * @param date
 * @returns '年-月-日'
 */
function dateParser(s){
    if (!s)//如果s为空
    	return new Date();  //则返回当前时间
    if(s.length!=10) return null;
	var ss = (s.split('-')); 
    var y = parseInt(ss[0],10);  
    var m = parseInt(ss[1],10);  
    var d = parseInt(ss[2],10);  
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){  
        return new Date(y,m-1,d);  
    } else {  
        return null;  
    } 
}

/**
 * 日期格式化函数
 * @param date
 * @returns '年-月'
 */
function formatDateToMonth(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m);  
} 

/**
 * 货币格式化函数
 * @param s 金额
 * @param n 小数位数 (不足补零)
 * @returns {String}
 */
function moneyFormatter(s,n) 
{   
   if (s == null||isNaN(parseFloat(s))) {
		return s;
   }
   n = (n >= 0 && n <= 20) ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(var i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   var result=t.split("").reverse().join("");
   if (n != 0) result += "." + r;
   return result.replace("-,","-");
};

/**
 * 在数字格中添加分隔符
 * 
 * @param count 数值
 * @param dig 保留位数 (不补零)
 * @returns
 */
function formatterCount(count, dig){
	if (count == null) return "";
	if (dig == null) dig = 2;
	var tmp = count + "";
	var index = tmp.indexOf(".");// 是否包含小数
	var subLen = 0;

	if (index != -1) {
		var len = tmp.substring(index).length;
		if (len > (dig + 1)) {
			tmp = parseFloat(count).toFixed(dig) + "";
		}
	} else {
		tmp += ".00";// 不包含小数
		subLen = 2;
	}
	var re = /(\d{1,3})(?=(\d{3})+(?:\.))/g;
	tmp = tmp.replace(re, "$1,");
	if (subLen > 0) return tmp.substring(0, tmp.length - subLen - 1);
	return tmp;
}

// 让JS的字符串有JAVA字符串的replaceAll方法
String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
};
/**
 * 扩展树的方法，以便可以选中实心节点
 */
$.extend($.fn.tree.methods, {
	getCheckedExt : function(jq) {
		var checked = $(jq).tree("getChecked");
		var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
		$.each(checkbox2, function() {
			var node = $.extend({}, $.data(this, "tree-node"), {
				target : this
			});
			checked.push(node);
		});
		return checked;
	},
	getSolidExt : function(jq) {
		var checked = [];
		var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
		$.each(checkbox2, function() {
			var node = $.extend({}, $.data(this, "tree-node"), {
				target : this
			});
			checked.push(node);
		});
		return checked;
	}
});

//字符串是否以str结尾
String.prototype.endWith=function(str){ 
	var reg=new RegExp(str+"$"); 
	return reg.test(this); 
};

//校验附件文件上传格式[自定义符合的文件类型,合法返回true,否则返回false]
var E2E_checkFileExt_custom=function(val, strArr){
	var fileExt = $.trim(val.toUpperCase());
	var flag = false;
	if(fileExt == "") return flag;
	$(strArr).each(function(index,value){
		if(fileExt.endWith(value.toUpperCase())){
			flag = true;
			return flag;
		}
	});
	return flag;
};

//保留两位小数
var E2E_division_sec = function(num, precision){
	precision = precision ? parseInt(precision) : 0;
    if (precision <= 0) return Math.round(num);
    return Math.round(num * Math.pow(10, precision)) / Math.pow(10, precision);
};

//校验文件名称
var E2E_validate_fileName = function(fileName) {
	return fileName.length > 0 && fileName.length < 95 
		&& (new RegExp('^[^\\\\\\/:*?\\"<>|#]+$').test(fileName));
};
/**
 * 日期格式化函数,只取年月
 * @param date
 * @returns {String}
 */
function yearFormatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var currentDate=new Date();
    var currentY=currentDate.getFullYear();  
    var currentM= currentDate.getMonth()+1; 
    if(y>currentY){
    	y=currentY;
    }
	if (y >= currentY && m >= currentM) {
		if (currentM != 1) {
			m = currentM - 1;
		}else{
			y = currentY -1;
			m = currentM + 11;
		}
	}
    return y+'-'+(m<10?('0'+m):m);  
}

/**
 * 计算日期天数(格式年月日)
 * 
 * @param date1 日期1
 * @param date2 日期2
 */
function dateDiff(date1, date2) {
	var dateArr1 = date1.split('-');//开始日期
	var dateArr2 = date2.split('-');//结束日期
	return (new Date(dateArr1[0], dateArr1[1], dateArr1[2]).getTime() 
			- new Date(dateArr2[0], dateArr2[1], dateArr2[2]).getTime()) / 1000 / 60 / 60 / 24; 
}

/**
 * 清除combobox的选项
 */
function clearComboboxOptions() {
	//清空固定的combox下的选择项
	var widget  = ["smallTypeCode", "detailTypeCode", "fineTypeCode", "materialSmallTypeCode"];
	for (var i in widget) {
		$("#" + widget[i]).combobox("loadData", []);  
	}
	
	//清空给定的combobox下的选择项
	if(arguments.length > 0) {
		for(var j in arguments) {
			$("#" + arguments[j]).combobox("loadData", []);  
		}
	}
}

//格式化为2位小数数(超出时四舍五入)
function subStrLength(val,s) {
	/*var data = val + "";
	var loc = data.indexOf(".");
	if (loc >= 0) {
		var tmp = data.substring(loc);
		if(tmp.length > 3){
			val = val.toFixed(2);
		}
	}*/
	if (isNaN(val) || val == null) val = 0;
	if (isNaN(s) || s == null) s = 2;
	return val.toFixed(s);
}

/**
 * 格式化字符串百分比(带千分符,默认两位)
 */
function formatterStrPercent(val,scal) {
	if (val == '' || val == undefined) return '';
	if (val.indexOf('%') > -1){
		val = val.substring(0, val.length - 1);
	} 
	val = moneyFormatter(val, scal);
	return val + '%'; 
}

//显示遮罩
function loading(id){
	return $("#"+id).each(function(){ 
		$(this).datagrid("getPager").pagination("loading"); 
		var opts = $(this).datagrid("options"); 
		var wrap = $.data(this,"datagrid").panel; 
		if(opts.loadMsg) { 
			$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:wrap.width(),height:wrap.height()}).appendTo(wrap); 
			$("<div class=\"datagrid-mask-msg\"></div>").html(opts.loadMsg).appendTo(wrap).css({display:"block",left:(wrap.width()-$("div.datagrid-mask-msg",wrap).outerWidth())/2,top:(wrap.height()-$("div.datagrid-mask-msg",wrap).outerHeight())/2}); 
		} 
	}); 
}

//隐藏遮罩 
function loaded(jq){ 
	return jq.each(function(){ 
		$(this).datagrid("getPager").pagination("loaded"); 
		var wrap = $.data(this,"datagrid").panel; 
		wrap.find("div.datagrid-mask-msg").remove(); 
		wrap.find("div.datagrid-mask").remove(); 
	}); 
}

//显示右下角消息
function showRightDownMsg(title, msg, timeout, showType) {
	$.messager.show({
	 	title : title,
		msg : msg,
		timeout : timeout,
		showType : showType
	});
}

//显示加载动画
function showLoading()
{
	var str = '<div class="msg_bg" style="background:#000;opacity:0.5;filter:alpha(opacity=50);z-index:99998;width:100%;position:absolute;left:0;top:0"></div>';
	str += '<div class="msg_bg" style="z-index:99999;width:100%;position:absolute;left:0;top:0;text-align:center;"><img src="images/loading.gif" alt="" class="loading"></div>'
	$('body').append(str);
	var scroll_height = $(document).scrollTop(); 
	$('.msg_bg').height($(document).height());
	$('.loading').css('margin-top',scroll_height + 240);
}

function getFormJson(form) {
	var o = {};
	var a = $(form).serializeArray();
	$.each(a, function () {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
}