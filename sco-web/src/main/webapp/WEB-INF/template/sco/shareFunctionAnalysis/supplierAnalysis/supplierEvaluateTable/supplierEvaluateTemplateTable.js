$(document).ready(function() {
	// 把所有输入控件禁用
	$("input[id^='templateName']").attr("disabled", "disabled");
	$("input[id^='supplierCode']").attr("disabled", "disabled");
	$("input[id^='supplierName']").attr("disabled", "disabled");
	$("input[id^='evaluateItemName']").attr("disabled", "disabled");
	$("input[id^='evaluateItemScore']").attr("disabled", "disabled");
	$("input[id^='evaluateFullScore']").attr("disabled", "disabled");
	$("input[id^='weight").attr("disabled", "disabled");
	$("input[id^='score']").attr("disabled", "disabled");
	$("input[id^='departments']").attr("disabled", "disabled");
	$("form > table:gt(0)").css("backgroundColor", "#efefef");
	var length=$("input[id^='score']").length;
	var score=0;
	for(var i=0;i<length;i++){
		score+=parseFloat($("#score"+i).val());
		$("#score"+i).val(moneyFormatter($("#score"+i).val()));
		$("#evaluateItemScore"+i).val(moneyFormatter($("#evaluateItemScore"+i).val()));
		$("#weight"+i).val(moneyFormatter($("#weight"+i).val()));
	}
	$("#evaluateFullScore").val(score.toFixed(2));
});
var supplierEvaluateTemplate = {
	// 导出明细查询结果
	export2Excel : function() {
		url = "supplierEvaluateTable_exportDataToExcel_6.html?evaluateTableCode=" + $("input#evaluateTableCode").val();
		window.location = url;
		$.messager.show({
			title : '提示',
			msg : '数据导出中,请稍后...',
			timeout : 4000,
			showType : 'slide'
		});
	},//格式千分位
	formatterUnit:function(value){
		if(value!=null){
			return moneyFormatter(value);
		}else{
			return value;
		}
	}
}