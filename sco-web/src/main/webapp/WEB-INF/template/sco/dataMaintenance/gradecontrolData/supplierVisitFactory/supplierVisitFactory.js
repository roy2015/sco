var supplierVisitFactoryGridUtil=null;
var firstIn = true;//是否第一次进入页面
var clearSearch = false;//清空查询

$(document).ready(function(){
	supplierVisitFactoryGridUtil=utils.grid($('#supplierVisitFactory_grid'));
	supplierVisitFactoryGridUtil.registerExtFilters("supplierCode", "supplierName", 
	        "minsupplierVisitFactoryDate","maxsupplierVisitFactoryDate");
	supplierVisitFactoryGridUtil.initFilters({
		onBeforeLoad:function(obj) {
			//清空查询
			if(clearSearch) {
				return true;
			}
			var length = $.param(obj).split("&").length;
			//判断参数个数
			if (length < 3) {
				if(!firstIn) $.messager.alert("提示", "请输入至少一项查询条件");
				firstIn = false;
				return false;
			}
			if(length == 4 && $.param(obj).indexOf("order") > -1) {
				var param = supplierVisitFactoryGridUtil.getFilterValue();
				if($.param(param).length > 0){
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
			var param = supplierVisitFactoryGridUtil.getFilterValue();
			var temp = $.param(param).split("&");
			if(temp==""){
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
		},
		onLoadSuccess:function(){
			$('#supplierVisitFactory_grid').datagrid("clearSelections");
		}
	});
});
var supplierVisitFactoryFn = {
		//搜索按钮功能
		search:function() {
			var param = supplierVisitFactoryGridUtil.getFilterValue();
			var temp = $.param(param).split("&");
			if(temp==""){
				$.messager.alert("提示", "请输入至少一项查询条件");
				return;
			}
			$('#supplierVisitFactory_grid').datagrid('load', param);
		},
		//显示上传巡检得分界面 
		showUpload:function() {
			var dlg = utils.showDlg({
				title : '上传巡检得分表',
				href : 'supplierVisitFactory_showSupplierVisitFactoryForm_1.html',
				width : 400,
				height : 200,
				buttons : [ {text:'关闭',
					handler:function(){dlg.dialog('close');},iconCls:'close'} ]
			});
		},
		//清除查询
		clearFilter:function(){
			$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
			clearSearch = true;
			$('#signedQty_search').form('reset');
			$('#supplierVisitFactory_grid').datagrid('loadData',{total:0,rows:[]});
			clearSearch = false;
		},
	refresh:function(){
    	$('#supplierVisitFactory_grid').treegrid('reload');
    }
};