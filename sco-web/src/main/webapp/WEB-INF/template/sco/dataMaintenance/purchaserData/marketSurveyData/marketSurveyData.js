var marketDataGrid=null;
var firstIn = true;//是否第一次进入页面
var clearSearch = false;//清空查询
$(document).ready(function(){
	marketDataGrid = utils.grid($('#marketSurveyData_grid'));
	marketDataGrid.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", 
			"jpmcName","mcRegion","minMarketSurveyDate","maxMarketSurveyDate",
			"centreTypeCode", "smallTypeCode", "detailTypeCode", "fineTypeCode","dxRoleCode","dlRoleCode");
	marketDataGrid.initFilters({
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
				var param = marketDataGrid.getFilterValue();
				if($.param(param).length > 0){
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
		},
		onLoadSuccess:function(){
			$('#marketSurveyData_grid').datagrid("clearSelections");
			marketDataFn.setButtonState();
		},
		onClickRow : marketDataFn.setButtonState, //设置按钮状态
		onCheck : marketDataFn.setButtonState,
		onUncheck : marketDataFn.setButtonState,
		onCheckAll : marketDataFn.setButtonState,
		onUncheckAll : marketDataFn.setButtonState
	});
});

var marketDataFn = {
	
	//搜索按钮功能
	search:function() {
		var param = marketDataGrid.getFilterValue();
		$('#marketSurveyData_grid').datagrid('load', param);
	},
	
	//折后公斤价
	discountPrice:function(value, row, index) {
		return moneyFormatter(value);
		/*var sellPrice = row.sellPrice;
		if(row.storageForm == '公斤'){
			return '<span id = discountPrice'+ index +'>' + sellPrice + '</span>';
		} else {
			return '<span id = discountPrice'+ index +'>' 
				+ E2E_division_sec((Number(sellPrice)/Number(row.netWeight)), 2)
				+ '</span>';
		}*/
	},
	
	//竞品折后公斤价
	mdiscountPrice:function(value, row, index) {
		return moneyFormatter(value);
		/*var marketSurveyPrice = row.marketSurveyPrice;
		if(row.jpPriceUnits == '公斤'){
			return '<span id = mdiscountPrice'+ index +'>' + marketSurveyPrice + '</span>';
		} else {
			return '<span id = mdiscountPrice'+ index +'>' 
				+ E2E_division_sec((Number(marketSurveyPrice)/Number(row.jpContent)), 2)
				+ '</span>';
		}*/
	},

	//显示上传竞品价格界面 
	showUpload:function() {
		var dlg = utils.showDlg({
			title : '上传市调表',
			href : 'marketSurveyData_showImportMarketSurveyDataForm_1.html',
			width : 400,
			height : 250,
			buttons : [ {text:'关闭',
				handler:function(){dlg.dialog('close');},iconCls:'close'} ]
		});
	},
	
	//提交新增或修改表单
	submitForm:function(dlg){
		var merchandise_code = $("input#merchandise_code").val();
		var url = 'marketSurveyData_insertMarketSurveyData_2.html';
		if(merchandise_code){
			url = 'marketSurveyData_updateMarketSurveyData_2.html';
		}
		utils.form("marketSurveyData_form").submit(url,null,function(){
			dlg.dialog('close');
			marketDataGrid.refresh();
		});
	},
	
	//填写保存文件名称
	saveFile:function() {
		var obj = $('#marketSurveyData_grid').datagrid('getRows');
		if(obj.length < 1) {
			$.messager.alert("提示", "当前未查询到任何数据,无法保存报表");
			return;
		}
		$("#saveFileDlg").window('open');//打开窗口
	},

	//关闭界面
	closeSaveFileDlg:function() {
		$("#fileName").val("");//清空填写的值
        $("#saveFileDlg").window('close');//关闭窗口
	},
	
	//提交填写文件名称的对话框
	submitSaveFileDlg:function() {
		var fileName = $.trim($("#fileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			return;
		}

		/*var list = $('#marketSurveyData_grid').datagrid("getRows");
		for (var index in list) {
			list[index].discountPrice = $('span[id = discountPrice' + index + ']').text();
			list[index].mdiscountPrice = $('span[id = mdiscountPrice' + index + ']').text();
		}*/
		
		$("#saveFile").linkbutton("disable");	
		var param = $.param(marketDataGrid.getFilterValue());
		$.post("marketSurveyData_saveSearchDataForm_2.html?"+param, {
			fileName : fileName,
			rows : param
		}, function(data) {
			var json = $.parseJSON(data);
			var msg = json.msg;
			if (json.success) {// 成功
				$("#fileName").val("");// 清空填写的值
				$("#saveFileDlg").window('close');// 关闭窗口
				$.messager.show({
					title : '提示',
					msg : msg
				});
			} else {// 失败
				$.messager.alert("提示", msg);
			}
			$("#saveFile").linkbutton("enable");
		});
	},
	
	// 删除竞品价格
	remove:function() {
		var rows = $('#marketSurveyData_grid').datagrid('getSelections');
		if (rows.length < 1 ) {
			$.messager.alert('提示', '请勾选至少一张签量单');
			return; 
		} 
		var ids = '';
		for (var i in rows) {
			ids += "'" + rows[i].id + "',";
		}
		ids = ids.substring(0, ids.length - 1);
		utils.confirm("操作确认","确认删除竞品价格 ?",function(){
			utils.post("marketSurveyData_deleteMarketSurveyData_2.html",
				{ids:ids},function(){
					marketDataGrid.refresh();
			});
		});
	},
	
	//清除查询
	clearFilter:function(){
		clearSearch = true;
		$('#signedQty_search').form('reset');
		clearComboboxOptions();
		$('.datagrid-sort-desc,.datagrid-sort-asc')
			.removeClass('datagrid-sort-desc datagrid-sort-asc');
		$('#marketSurveyData_grid').datagrid('loadData',{total:0,rows:[]});
		$('#marketSurveyData_grid').datagrid('clearSelections');
		clearSearch = false;
	},
	
	refresh:function(){
    	$('#marketSurveyData_grid').treegrid('reload');
    },
    
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#marketSurveyData_grid').datagrid("getSelections");
		if (rows.length == 1) {
			$("a[id='remove']").linkbutton("enable");
		} else if(rows.length > 1) {
			$("a[id='remove']").linkbutton("enable");
		} else {
			$("a[id='remove']").linkbutton("disable");
		}
	},
	
	//导出到Excel
	export2Excel:function(){
		var param = $.param(marketDataGrid.getFilterValue());
		if(param.length < 1){
			$.messager.alert("提示", "请输入至少一项查询条件");
			return;
		}
		window.location = "marketSurveyData_exportDataToExcel_6.html?" + param;
		$.messager.show({
			title:'提示',
			msg:'数据导出中,请稍后...',
			timeout:4000,
			showType:'slide'
		});
	}
	
};