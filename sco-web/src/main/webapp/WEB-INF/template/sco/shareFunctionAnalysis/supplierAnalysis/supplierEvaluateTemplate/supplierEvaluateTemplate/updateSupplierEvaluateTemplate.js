//count用于动态的为table的id赋值
var count=0;
//用于复制table
var flag=false;
$(document).ready(function(){
	$("#signedQty_search table:eq(1) a:eq(1)").hide();
	count=$("#signedQty_search>table:gt(0)").length-1;
	$("input[id^='weight']").on('keyup', function (event) {
	    var $amountInput = $(this);
	    //响应鼠标事件，允许左右方向键移动 
	    event = window.event || event;
	    if (event.keyCode == 37 | event.keyCode == 39) {
	        return;
	    }
	    //先把非数字的都替换掉，除了数字和. 
	    $amountInput.val($amountInput.val().replace(/[^\d.]/g, "").
	        //只允许一个小数点              
	        replace(/^\./g, "").replace(/\.{2,}/g, ".").
	        //只能输入小数点后两位
	        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
	});
	$("input[id^='weight']").on('blur', function () {
	    var $amountInput = $(this);
	    //最后一位是小数点的话，移除
	    $amountInput.val(($amountInput.val().replace(/\.$/g, "")));
	});
});
var flag=true;
var updateSupplierEvaluateTemplateFn = {
	//新增一个表格
	addMarkOptions:function(temp){
	count++;
	var table1='<table id="table'+count+'">'+
	'<tr>'+
	'<td>'+
		'<table style="width:800px;border:1px solid grey">'+
			'<tr>'+
				'<td style="text-align:left">'+
					'<table id="tab'+count+'" style="margin-top:10px;margin-bottom:10px">'+
						'<tr>'+
							'<td style="text-align:right;width:70px">'+
								'<label style="color: red;" >*</label>'+
								'评分项名称:'+
							'</td>'+
							'<td style="width:120px;">'+
								'<input id="evaluateItemName'+count+'" style="width:120px;" class="easyui-validatebox" data-options="required:true,'+"validType:'length[1,10]'"+'"  />'+
							'</td>'+
							'<td style="text-align:right;width:60px">'+
								'<label style="color: red;" >*</label>'+
								'权重(%):'+
							'</td>'+
							'<td style="width:120px;">'+
								'<input id="weight'+count+'" style="width:120px;" class="easyui-validatebox" data-options="required:true" />'+
							'</td>'+
							'<td style="text-align:right;width:70px">'+
								'<label style="color: red;" >*</label>'+
								'打分部门:'+
							'</td>'+
							'<td>'+
								'<input id="departments'+count+'" class="easyui-combobox filterSelect" style="width: 124px;" '+
									'data-options="'+
										'valueField:\'text\','+
										'textField:\'text\','+
										'editable:false,'+
									    'url:\'businessComBox_demartments_5.html\''+
							   ' " />'+
						    '</td>'+
						'</tr>'+
					'</table>'+
				'</td>'+
			'<tr>'+
			'<tr>'+
				'<td>'+
					'<table style="margin-top:10px;margin-bottom:10px">'+
						'<tr>'+
							'<td style="text-align:right;width:80px">'+
								'评分依据:'+
							'</td>'+
							'<td>'+
								'<textarea id="according'+count+'" style="width:210px;height:100px;max-width:210px;max-height:100px;disabled:disabled" class="easyui-validatebox" validtype="length[0,330]"></textarea>'+
							'</td>'+
							'<td></td>'+
							'<td style="text-align:right;width:80px">'+
								'评分标准:'+
							'</td>'+
							'<td>'+
								'<textarea id="standard'+count+'" style="width:210px;height:100px;max-width:210px;max-height:100px" class="easyui-validatebox" validtype="length[0,330]"></textarea>'+
							'</td>'+
							'<td></td>'+
						'</tr>'+
					'</table>'+
				'</td>'+
			'</tr>'+
		'</table>'+
	'</td>'+
	'<td>'+
		'<div style="margin-top:-80px;text-align:left">'+
		'<a onclick="updateSupplierEvaluateTemplateFn.addMarkOptions('+'\''+temp+'\''+');" plain="true" class="easyui-linkbutton" data-options='+"iconCls:'add'"+'></a>'+
		'<a onclick="updateSupplierEvaluateTemplateFn.removeMarkOptions(this);" plain="true" class="easyui-linkbutton" data-options='+"iconCls:'close'"+'></a>'+
		'</div>'+
	'</td>'+
'</tr>'+
'</table>';
	
	var table2='<table id="table'+count+'">'+
	'<tr>'+
	'<td>'+
		'<table style="width:800px;border:1px solid grey">'+
			'<tr>'+
				'<td style="text-align:left">'+
					'<table id="tab'+count+'" style="margin-top:10px;margin-bottom:10px">'+
						'<tr>'+
							'<td style="text-align:right;width:70px">'+
								'<label style="color: red;" >*</label>'+
								'评分项名称:'+
							'</td>'+
							'<td style="width:120px;">'+
								'<input id="evaluateItemName'+count+'" style="width:120px;" class="easyui-validatebox" data-options="required:true,'+"validType:'length[1,10]'"+'"  />'+
							'</td>'+
							'<td style="text-align:right;width:60px">'+
								'<label style="color: red;" >*</label>'+
								'权重(%):'+
							'</td>'+
							'<td style="width:120px;">'+
								'<input id="weight'+count+'" style="width:120px;" class="easyui-validatebox" data-options="required:true" onkeyup="this.value=this.value.replace(/\D/g,"")" onafterpaste="this.value=this.value.replace(/\D/g,"")" />'+
							'</td>'+
						'</tr>'+
					'</table>'+
				'</td>'+
			'<tr>'+
			'<tr>'+
				'<td>'+
					'<table style="margin-top:10px;margin-bottom:10px">'+
						'<tr>'+
							'<td style="text-align:right;width:80px">'+
								'评分依据:'+
							'</td>'+
							'<td>'+
								'<textarea id="according'+count+'" style="width:210px;height:100px;max-width:210px;max-height:100px;disabled:disabled" class="easyui-validatebox" validtype="length[0,330]"></textarea>'+
							'</td>'+
							'<td></td>'+
							'<td style="text-align:right;width:80px">'+
								'评分标准:'+
							'</td>'+
							'<td>'+
								'<textarea id="standard'+count+'" style="width:210px;height:100px;max-width:210px;max-height:100px" class="easyui-validatebox" validtype="length[0,330]"></textarea>'+
							'</td>'+
							'<td></td>'+
						'</tr>'+
					'</table>'+
				'</td>'+
			'</tr>'+
		'</table>'+
	'</td>'+
	'<td>'+
		'<div style="margin-top:-80px;text-align:left">'+
			'<a onclick="updateSupplierEvaluateTemplateFn.addMarkOptions('+'\''+temp+'\''+');" plain="true" class="easyui-linkbutton" data-options='+"iconCls:'add'"+'></a>'+
			'<a onclick="updateSupplierEvaluateTemplateFn.removeMarkOptions(this);" plain="true" class="easyui-linkbutton" data-options='+"iconCls:'close'"+'></a>'+
		'</div>'+
	'</td>'+
'</tr>'+
'</table>';
	if(temp != 'FL'){
		$("form").append(table1);
	}else{
		$("form").append(table2);
	}
	$.parser.parse($("html"));
},
//移除一个表格
removeMarkOptions:function(a){
	$(a).parents("table").remove();
},
//保存修改
save:function(code,temp){
	//创建一个数值
	var list= [];
	//获取表格的个数
	var weight=$("input[id^='weight']");
	var evaluateItemName=$("input[id^='evaluateItemName']");
	if(temp != 'FL'){
		var departments=$("input[id^='departments']");	
	}
	var according=$("textarea[id^='according']");
	var standard=$("textarea[id^='standard']");
	var ret="";
	var score=0;
	$.each(weight, function(i,val){
		score+=parseFloat(val.value);
		if(val.value==""){
			ret="true";
			return;
		}
		if(evaluateItemName[i].value==""){
			ret="true";
			return;
		} 
		if(temp != 'FL'){
			if($(departments[i]).combobox('getValue')==""){
				ret="true";
				return;
			}
		}
		var obj=code+',';
		obj+=$("#templateName").val()+',';
		obj+= $("#minCreated").datebox("getValue")+',';
		obj+= $("#maxCreated").datebox("getValue")+',';
		obj+= $("#fullScore").val()+',';
		obj+= evaluateItemName[i].value+',';
		obj+= val.value+',';
		if(temp != 'FL'){
			obj+= $(departments[i]).combobox('getValue')+',';
		}
		if(according[i].value==''||according[i].value==null){
			obj+= 'null'+',';
		}else{
			obj+=according[i].value+',';
		}
		if(standard[i].value==''||standard[i].value==null){
			obj+= 'null';
		}else{
			obj+= standard[i].value;
		}
		list.push(obj);//把字符串存入list数组
	});
	if(ret=="true"){
		$.messager.alert("提示","指示的必填项不能为空");
		return;
	}	
	if(score!=100){
		$.messager.alert("提示","权重数值相加不等于100%");
		return;
	}
		
	$("a[id='save']").linkbutton("disable");
	$("#signedQty_search").form(
			"submit",
			{
				url : "supplierEvaluateTemplate_updateSupplierEvaluateTemplate_2.html" ,
				onSubmit : function(param) {
					if (!$('#signedQty_search').form('validate')){
						$('.msg_bg').remove();
						$.messager.alert('提示', '请填入所有必填项!');
						$("a[id='save']").linkbutton("enable");
						return false;	// 返回false将停止form提交
					}
					param.array = list;
					showLoading();
				},
				success : function(data) {
					var json = $.parseJSON(data);
					if (json.success) {
						parent.messagerShow({
							title : '操作成功!',
							msg : json.msg
						});
						parent.pubTab.closeCurrTab();
					} else {
						
						parent.messagerShow({
							title : '操作失败!',
							msg : json.msg
						});
					}
					$("a[id='save']").linkbutton("enable");
				}
			});
}
};