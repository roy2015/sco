var firstIn = true;//第一次进入页面
var clearSearch = false;//清空查询
var publishSupplierEvaluateTableGridUtil=null;
$(document).ready(function(){
	publishSupplierEvaluateTableGridUtil=utils.grid($('#publishSupplierEvaluateTable_grid'));
	publishSupplierEvaluateTableGridUtil.registerExtFilters('sName','sCode','centreTypeCode','smallTypeCode','detailTypeCode','fineTypeCode','fTypeCode');
	publishSupplierEvaluateTableGridUtil.initFilters({
		onBeforeLoad:function(obj){
			//清空查询
			if(clearSearch){
				return true;
			}
			//判断参数个数
			var length=$.param(obj).split("&").length;
			if (length < 3) {
				if(!firstIn) $.messager.alert("提示", "请输入至少一项查询条件");
				firstIn = false;
				return false;
			}
		},
		onLoadSuccess:function(){
			$('#publishSupplierEvaluateTable_grid').datagrid("clearSelections");
		}
	});
	$(".spfenlei").hide();
	$(".flfenlei").hide();
});
var publishSupplierEvaluateTableFn = {
	//搜索供应商
	searchSelect:function(){
		var param=publishSupplierEvaluateTableGridUtil.getFilterValue();
		if($.param(param).split('&')==""){
			$.messager.alert('提示','请输入至少一项查询条件');
			return;
		}
		$('#publishSupplierEvaluateTable_grid').datagrid('load',param);
	},
	//弹出窗口单选改变时显示不同的下拉列表框
	changeSelect : function(a) {
		var fenleiValue = $(a).val();
		if (fenleiValue == 'SP') {
			$(".spfenlei").show();
			$(".flfenlei").hide();
			$(".fl").combobox('setValues','');
		}
		if (fenleiValue == 'FL') {
			$(".spfenlei").hide();
			$(".flfenlei").show();
			$(".sp").combobox('setValues','');
		}
	},
	//清空弹出窗口的查询结果
	clearFilterOnDialog:function(){
		clearSearch=true;
		$("#publishSupplierEvaluateTable_form").form("reset");
		$("#publishSupplierEvaluateTable_grid").datagrid('loadData',{total:0,rows:[]});
		clearSearch=false;
	},
};