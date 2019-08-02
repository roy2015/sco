var supplierQualityLevelGridUtil=null;
var firstIn = true;//是否第一次进入页面
var clearSearch = false;//清空查询

$(document).ready(function(){
	supplierQualityLevelGridUtil=utils.grid($('#supplierQualityLevel_grid'));
	supplierQualityLevelGridUtil.registerExtFilters("supplierCode", "supplierName", 
	        "minsupplierQualityLevelDate","maxsupplierQualityLevelDate");
	supplierQualityLevelGridUtil.initFilters({
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
				var param = supplierQualityLevelGridUtil.getFilterValue();
				if($.param(param).length > 0){
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
			var param = supplierQualityLevelGridUtil.getFilterValue();
			var temp = $.param(param).split("&");
			if(temp==""){
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
		},
		onLoadSuccess:function(){
			$('#supplierQualityLevel_grid').datagrid("clearSelections");
		}
	});
});
var supplierQualityLevelFn = {
		//搜索按钮功能
		search:function() {
			var param = supplierQualityLevelGridUtil.getFilterValue();
			var temp = $.param(param).split("&");
			if(temp==""){
				$.messager.alert("提示", "请输入至少一项查询条件");
				return;
			}
			$('#supplierQualityLevel_grid').datagrid('load', param);
		},
		//显示上传质量星级界面 
		showUpload:function() {
			var dlg = utils.showDlg({
				title : '上传质量星级表',
				href : 'supplierQualityLevel_showSupplierQualityLevelForm_1.html',
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
			$('#supplierQualityLevel_grid').datagrid('loadData',{total:0,rows:[]});
			clearSearch = false;
		},
	refresh:function(){
    	$('#supplierQualityLevel_grid').treegrid('reload');
    }
		
};