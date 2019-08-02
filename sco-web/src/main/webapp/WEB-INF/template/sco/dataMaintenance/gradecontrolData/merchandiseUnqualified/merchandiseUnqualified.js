var firstIn = true;//是否第一次进入页面
var clearSearch = false;//清空查询
var merchandiseUnqualifiedGridUtil=null;
$(document).ready(function(){
	merchandiseUnqualifiedGridUtil=utils.grid($('#merchandiseUnqualified_grid'));
	merchandiseUnqualifiedGridUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", 
	        "minSpotCheckDate","maxSpotCheckDate",
			"centreTypeCode", "smallTypeCode", "detailTypeCode", "fineTypeCode","dxRoleCode","dlRoleCode");
	merchandiseUnqualifiedGridUtil.initFilters({
		
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
				var param = merchandiseUnqualifiedGridUtil.getFilterValue();
				if($.param(param).length > 0){
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
			var param = merchandiseUnqualifiedGridUtil.getFilterValue();
			var temp = $.param(param).split("&");
			if(temp==""){
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
		},
		
		onLoadSuccess:function(){
			$('#merchandiseUnqualified_grid').datagrid("clearSelections");
		}
	});
});
var merchandiseUnqualifiedFn = {
	//搜索按钮功能
	search:function() {
		var param = merchandiseUnqualifiedGridUtil.getFilterValue();
		var temp = $.param(param).split("&");
		if(temp==""){
			$.messager.alert("提示", "请输入至少一项查询条件");
			return;
		}
		$('#merchandiseUnqualified_grid').datagrid('load', param);
	},
	//清除查询
	clearFilter:function(){
		clearSearch = true;
		$('#signedQty_search').form('reset');
		clearComboboxOptions();
		$('#merchandiseUnqualified_grid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
	},
	//显示上传商品不合格界面 
	showUpload:function() {
		var dlg = utils.showDlg({
			title : '上传商品外部抽检不合格记录',
			href : 'merchandiseUnqualified_showMerchandiseUnqualifiedForm_1.html',
			width : 400,
			height : 200,
			buttons : [ {text:'关闭',
				handler:function(){dlg.dialog('close');},iconCls:'close'} ]
		});
	}
};