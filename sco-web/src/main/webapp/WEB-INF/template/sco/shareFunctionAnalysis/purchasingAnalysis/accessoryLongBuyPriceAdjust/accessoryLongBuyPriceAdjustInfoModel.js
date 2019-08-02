var accessoryLongBuyPriceAdjustInfoFn = {
	// 关闭tan页面
	close : function() {
		parent.pubTab.closeTab("长期进价调整明细报表-辅料");
	},
	// 填写保存文件名称
	saveFile : function() {
		var row = $('#accessoryLongBuyPriceAdjustInfo_grid').datagrid("getRows");
		if (row == ""||row==null) {
			$.messager.alert("提示", "数据为空不能保存");
			return;
		}
		$("#saveFileDlg").window('open');// 打开窗口
	},
	// 关闭界面
	closeSaveFileDlg : function() {
		$("#fileName").val("");// 清空填写的值
		$("#saveFileDlg").window('close');// 关闭窗口
	},// 提交填写文件名称的对话框
	submitSaveFileDlg : function() {
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
		$("a[id='save']").linkbutton("disable");
		var url = "accessoryLongBuyPriceAdjust_saveSearchDataForm_2.html?";
		var prarm = "minDate=" + $("#minDate").val() + 
					"&maxDate=" + $("#maxDate").val() + 
					"&show=" + $("#show").val() + 
					"&regionCode=" + $("#sellRegion").val() + 
					"&warehouseCode="+$("#warehouseCode").val()+
					"&merchandiseAndSupplierCodes=" + $("#merchandiseAndSupplierCodes").val() + 
					"&type=2";
		url += prarm;
		$.post(url, {
			fileName : fileName
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
			$("a[id='save']").linkbutton("enable");
		});
	},
	execlMerchandise : function() {
		var row = $('#accessoryLongBuyPriceAdjustInfo_grid').datagrid("getRows");
		if (row == ""||row==null) {
			$.messager.alert("提示", "数据为空不能导出");
			return;
		}
		var url = "accessoryLongBuyPriceAdjust_exportAccessoryLongBuyPriceAdjustInfoToExcel_6.html?";
		var prarm = "minDate=" + $("#minDate").val() + 
					"&maxDate=" + $("#maxDate").val() + 
					"&show=" + $("#show").val() + 
					"&regionCode=" + $("#sellRegion").val() + 
					"&merchandiseAndSupplierCodes=" + $("#merchandiseAndSupplierCodes").val()+
					"&type=2"+
					"&warehouseCode="+$("#warehouseCode").val();
		url += prarm;
		window.location = url;
		$.messager.show({
			title : '提示',
			msg : '数据导出中,请稍后...',
			timeout : 4000,
			showType : 'slide'
		});
	},
	//格式千分位
	formatterUnit:function(value,row){
		if(value!=null){
			return moneyFormatter(value);
		}else{
			return value;
		}
	}
};