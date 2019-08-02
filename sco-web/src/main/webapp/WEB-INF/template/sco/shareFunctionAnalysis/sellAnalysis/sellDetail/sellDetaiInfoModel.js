var sellDetailInfoFn = {
	// 关闭tan页面
	close : function() {
		parent.pubTab.closeTab("销售明细查询结果");
	},
	// 导出明细查询结果
	export2Excel : function() {
		var rows = $('#sellDetailInfo_grid').datagrid("getRows");
		if (rows == "") {
			$.messager.alert("提示", "数据为空不能导出");
			return;
		}
		url = "sellDetail_exportDataToExcel_6.html?supplierCode=" + $("input#supplierCode").val() + "&merchandiseCode=" + $("input#merchandiseCode").val() + "&minDate=" + $("input#minDate").val()
				+ "&maxDate=" + $("input#maxDate").val() + "&show=" + $("input#show").val()+"&sellRegion="+$("input#sellRegion").val()+ "&dl=" + $("input#dl").val();
		window.location = url;
		$.messager.show({
			title : '提示',
			msg : '数据导出中,请稍后...',
			timeout : 4000,
			showType : 'slide'
		});
	},// 填写保存文件名称
	saveFile : function() {
		var rows = $('#sellDetailInfo_grid').datagrid("getRows");
		if (rows == "") {
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
		url = "sellDetail_saveSearchDataForm_2.html?supplierCode=" + $("input#supplierCode").val() + "&merchandiseCode=" + $("input#merchandiseCode").val() + "&minDate=" + $("input#minDate").val()
				+ "&maxDate=" + $("input#maxDate").val() + "&show=" + $("input#show").val()+"&sellRegion="+$("input#sellRegion").val()+ "&dl=" + $("input#dl").val();
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
	//格式百分
	formatterPer : function(value, row) {
		if (value != null) {
			return moneyFormatter(value) + '%';
		} else {
			return value;
		}
	},
	//格式活跃度
	formatterActive : function(value, row) {
		if(value>100){
		 	return 100.00+'%';
		 }else{
		 	return moneyFormatter(value)+'%';
		 }
	},
	//格式千分位
	formatterUnit:function(value,row){
		if(value!=null){
			return moneyFormatter(value);
		}else{
			return value;
		}
	},
	//格式千分位
	formatterUnit1:function(value,row){
		if(value!=null){
			return formatterCount(value);
		}else{
			return value;
		}
	}
}