var count=0;

$(document).ready(function(){
	$("#signedQty_search table:eq(1) a:eq(1)").hide();
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

var supplierEvaluateTemplateFn = {
	addMarkOptions:function(){
		count++;
		var table='<table id="table'+count+'">'+
		'<tr>'+
		'<td>'+
			'<table style="width:800px;border:1px solid grey">'+
				'<tr>'+
					'<td style="text-align:left">'+
						'<table style="margin-top:10px;margin-bottom:10px">'+
							'<tr>'+
						'<td style="text-align:right;width:70px">'+
						'<label style="color: red;" >*</label>'+
						'评分项名称:'+
						'</td>'+
						'<td style="width:120px;">'+
							'<input id="evaluateItemName'+count+'" style="width:120px;" class="easyui-validatebox" data-options="required:true,'+"validType:'length[1,10]'"+'" />'+
						'</td>'+
						'<td style="text-align:right;width:60px">'+
						'<label style="color: red;" >*</label>'+
							'权重(%):'+
						'</td>'+
						'<td style="width:120px;">'+
							'<input id="weight" style="width:120px;" class="easyui-validatebox" required="true" />'+
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
									'<textarea id="according'+count+'" style="width:210px;height:100px;max-width:210px;max-height:100px;disabled:disabled" class="easyui-validatebox" validtype="length[0,333]"></textarea>'+
								'</td>'+
								'<td></td>'+
								'<td style="text-align:right;width:80px">'+
									'评分标准:'+
								'</td>'+
								'<td>'+
									'<textarea id="standard'+count+'" style="width:210px;height:100px;max-width:210px;max-height:100px" class="easyui-validatebox" validtype="length[0,333]"></textarea>'+
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
				'<a onclick="supplierEvaluateTemplateFn.addMarkOptions();" plain="true" class="easyui-linkbutton"  data-options='+"iconCls:'add'"+'></a>'+
				'<a onclick="supplierEvaluateTemplateFn.removeMarkOptions(this);" plain="true" class="easyui-linkbutton"  data-options='+"iconCls:'close'"+'></a>'+
			'</div>'+
		'</td>'+
	'</tr>'+
'</table>';
		$("#signedQty_search>table:last").after(table);
		$.parser.parse($("#table"+count));
		if($("#signedQty_search table").size()>=2){
			$("#signedQty_search table:gt(1) a:eq(1)").show();
		}
	},
	removeMarkOptions:function(a){
		$(a).parents("table").remove();
	},
	closetab:function(title){
		parent.pubTab.closeTab(title);
	},
	save:function(){
		var w=0;
		var list=new Array();
		var arrlength=$("#signedQty_search>table:gt(0)").length;
		var obj='';
		var weight=$("input[id^='weight']");
		var evaluateItemName=$("input[id^='evaluateItemName']");
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
			var obj='';
			obj+=$("#templateName").val()+',';
			obj+= $("#minCreated").datebox("getValue")+',';
			obj+= $("#maxCreated").datebox("getValue")+',';
			obj+= $("#fullScore").val()+',';
			obj+=evaluateItemName[i].value+',';
			obj+= val.value+',';
			
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
					url : "supplierEvaluateTemplate_insertAccessorySupplierEvaluateTemplate_2.html" ,
					onSubmit : function(param) {
						if (!$('#signedQty_search').form('validate')){
							$('.msg_bg').remove();
							$.messager.alert('提示', '请填入所有必填项!');
							$("a[id='save']").linkbutton("enable");
							return false;	// 返回false将停止form提交
						}
						param.list = list;
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
function close(){
	parent.pubTab.closeTab('供应商考评模板-辅料');
}