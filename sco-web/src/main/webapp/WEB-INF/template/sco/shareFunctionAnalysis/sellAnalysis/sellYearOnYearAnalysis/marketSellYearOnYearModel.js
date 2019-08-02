var marketSellFn = {
	// 关闭tan页面
	close : function() {
		parent.pubTab.closeTab("整体同比结果");
	},
	// 填写保存文件名称
	saveFile : function() {
		$("#saveFileDlg").window('open');// 打开窗口
	},
	//关闭对话框
	closeSaveFileDlg : function() {
		$("#fileName").val("");// 清空填写的值
		$("#saveFileDlg").window('close');// 关闭窗口
	},
	//提交填写文件名称的对话框
	submitSaveFileDlg : function() {
		$("#save").linkbutton("disable");
		var fileName = $.trim($("#fileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法!");
				$("#save").linkbutton("enable");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空!");
			$("#save").linkbutton("enable");
			return;
		}
		url = "marketSellYearOnYear_saveMarketSellToHtml_2.html?merchandiseCodes=" + $("input#merchandiseCodes").val() + 
				"&startDate=" + $("input#startDate").val()+ 
				"&endDate=" + $("input#endDate").val() + 
				"&directJoin=" + $("input#directJoin").val()+
				"&rationed=" + $("input#rationed").val()+
				"&seeYear=" + $("input#seeYear").val()+
				"&sellRegion=" + $("input#sellRegion").val();
		
		$.post(url, {fileName : fileName}, function(data) {
			var json = $.parseJSON(data);
			var msg = json.msg;
			if (json.success) {// 成功
				$("#fileName").val("");// 清空填写的输入框
				$("#saveFileDlg").window('close');// 关闭窗口
				$.messager.show({
					title : '提示',
					msg : msg
				});
			} else {// 失败
				$.messager.alert("提示", msg);
			}
			$("#save").linkbutton("enable");
		});
	},
	//导出销售同比
	exportMarketSellExcel : function() {
		var url = "marketSellYearOnYear_exportMarketSellToExcel_6.html?merchandiseCodes=" + $("input#merchandiseCodes").val() + 
					"&startDate=" + $("input#startDate").val()+ 
					"&endDate=" + $("input#endDate").val() + 
					"&directJoin=" + $("input#directJoin").val()+
					"&rationed=" + $("input#rationed").val()+
					"&seeYear=" + $("input#seeYear").val()+
					"&sellRegion=" + $("input#sellRegion").val()+
					"&timeRange=" + $("font#timeRange").text();
		window.location = url;
		
		$.messager.show({
			title : '提示',
			msg : '数据导出中,请稍后...',
			timeout : 4000,
			showType : 'slide'
		});
	}
	
}