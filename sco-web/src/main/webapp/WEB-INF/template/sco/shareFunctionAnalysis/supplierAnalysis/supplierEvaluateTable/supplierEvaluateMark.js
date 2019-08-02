$(document).ready(function(){
	//把所有输入控件禁用
	$("input[id^='templateName']").attr("disabled", "disabled");
	$("input[id^='supplierCode']").attr("disabled", "disabled");
	$("input[id^='supplierName']").attr("disabled", "disabled");
	$("input[id^='evaluateItemName']").attr("disabled", "disabled");
	$("input[id^='evaluateFullScore']").attr("disabled", "disabled");
	$("input[id^='evaluateScore']").attr("disabled", "disabled");
	$("input[id^='weight']").attr("disabled", "disabled");
	$("input[id^=quanzhongfen]").attr("readonly","readonly").css("backgroundColor","#F3F3EA");
	$("form > table:gt(0)").css("backgroundColor","#efefef");
	var length=$("input[id^='score']").length;
	for(var i=0;i<length;i++){
		$("#score"+i).val(moneyFormatter($("#score"+i).val()));
		$("#quanzhongfen"+i).val(moneyFormatter($("#quanzhongfen"+i).val()));
		$("#weight"+i).val(moneyFormatter($("#weight"+i).val()));
	}
});
var supplierEvaluateMarkFn = {
	save:function(){
		var length = $("form > table:gt(0)").length;
		if(length!=0){
			var arr = [];
			var str=null;
			for(var i=0;i<length;i++){
				if($("#score"+i).val() == null || $("#score"+i).val() == ''){
					$.messager.alert("提示","请填入所有评分项的分值");
					return;
				}
				var score=parseFloat($("#score"+i).val()).toFixed(2);
				var obj={score:score,evaluateItemCode:$("#evaluateItemCode"+i).val(),evaluateTableCode:$("#evaluateTableCode").val()};
				if(obj.score>100){
					$.messager.alert("提示","单个评分项的'分值'不可超过100");
					return;
				}
				arr.push(obj);
			}
			$("a[id='save']").linkbutton("disable");
			str=JSON.stringify(arr);
			$.post("supplierEvaluateTable_updateSupplierEvaluateTableByEvaluateItemCode_2.html",
					{"array":str},
					function(data){
						if(data.success){
							parent.messagerShow({title:"操作成功",msg:data.msg});
							parent.pubTab.closeCurrTab();
						}else{
							$("a[id='save']").linkbutton("enable");
							parent.messagerShow({title:"操作失败",msg:data.msg});
						}
					},
			"json");
		}else{
			alert("评分项为空");
		}
	},
	changeNumber:function(){
		//权重分=分值÷100*考评表满分*权重
		var length=$(".pfx").length;
		for(var i=0;i<length;i++){
			$("#quanzhongfen"+i).val(($("#score"+i).val()/100*$("#evaluateFullScore").val()*$("#weight"+i).val()/100).toFixed(2));
		}
	}
}
