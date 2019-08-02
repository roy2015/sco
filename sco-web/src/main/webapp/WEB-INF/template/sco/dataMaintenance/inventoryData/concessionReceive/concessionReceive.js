var concessionReceiveGridUtil=null;
var firstIn = true;//是否第一次进入页面
var clearSearch = false;//清空查询
$(document).ready(function(){
	concessionReceiveGridUtil=utils.grid($('#concessionReceive_grid'));
	concessionReceiveGridUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", 
	        "minConcessionReceiveDate","maxConcessionReceiveDate",
			"centreTypeCode", "smallTypeCode", "detailTypeCode", "fineTypeCode","dxRoleCode","dlRoleCode");
	concessionReceiveGridUtil.initFilters({
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
				var param = concessionReceiveGridUtil.getFilterValue();
				if($.param(param).length > 0){
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
			var param = concessionReceiveGridUtil.getFilterValue();
			var temp = $.param(param).split("&");
			if(temp==""){
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
		},
		onLoadSuccess:function(){
			$('#concessionReceive_grid').datagrid("clearSelections");
		}
	});
});
var concessionReceiveFn = {
		//搜索按钮功能
		search:function() {
			var param = concessionReceiveGridUtil.getFilterValue();
			$('#concessionReceive_grid').datagrid('load', param);
		},
		//显示上传让步接收界面 
		showUpload:function() {
			var dlg = utils.showDlg({
				title : '上传让步接收表',
				href : 'concessionReceive_showConcessionReceiveDataForm_1.html',
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
			clearComboboxOptions();
			$('#concessionReceive_grid').datagrid('loadData',{total:0,rows:[]});
			clearSearch = false;
		},
	refresh:function(){
    	$('#concessionReceive_grid').treegrid('reload');
    },
	//从EXCEL导入
	importFromExcel:function(){
		utils.submitFormWidthFile({
			url:'concessionReceive_importConcessionReceive_2.html',
			fileElementId:'concessionReceiveFile',
			success:function(json){
				if(json.success){
					$.messager.show({title:'操作成功', msg:json.msg, showType:'show'});
				}else{
					$.messager.alert('操作失败', json.msg);
				}
			}
		});
	},
	//下载模板
	downloadTpl:function(){
		window.location="concessionReceive_downloadConcessionReceiveDataTemplate_2.html";
	}
};