var marketPromotionAnalysisOneGridUtil = null;
var marketPromotionAnalysisTwoGridUtil = null;
var marketPromotionAnalysisThreeGridUtil = null;
$(function() {
	marketPromotionAnalysisOneGridUtil = utils.grid($('#marketPromotionAnalysis_grid_one'));
	marketPromotionAnalysisTwoGridUtil = utils.grid($('#marketPromotionAnalysis_grid_two'));
	marketPromotionAnalysisThreeGridUtil = utils.grid($('#marketPromotionAnalysis_grid_three'));
});
var marketPromotionAnalysisFn = {
	searchMarket : function() {
		marketPromotionAnalysisFn.clearTable();
		var sellRegion = $("#sellRegion").combobox("getValue");
		var sellRegionName = $("#sellRegion").combobox("getText");
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox("getValue");
		var minDates = $("#minDates").datebox("getValue");
		var maxDates = $("#maxDates").datebox("getValue");
		
		var DL = $('input[name="DL"]:checked');
		var show = $('input[name="show"]:checked');
		if (sellRegion != "" && minDate != "") {
			if(minDate==minDates&&maxDate==maxDates){
				$.messager.alert("提示", "两个档期日期一样");
				return;
			}
			var url = "marketPromotionAnalysis_listMarketPromotionAnalysis_2.html";
			$('#marketPromotionAnalysis_grid_one').datagrid({
				url : url,
				queryParams : {
					"sellRegion" : sellRegion,
					"sellRegionName" : sellRegionName,
					"minDate" : minDate,
					"maxDate" : maxDate,
					"minDates" : minDates,
					"maxDates" : maxDates,
					"searchType" : DL[0].value,
					"searchTable" : show[0].value
				}
			});
			$("#tableOne").show();
		} else {
			$.messager.alert("提示", "请选择必须搜索项");
		}

	},// 填写保存文件名称
	saveFile : function() {
		var row = $('#marketPromotionAnalysis_grid_one').datagrid("getRows");
		var rows = $('#marketPromotionAnalysis_grid_three').datagrid("getRows");
		var url = "";
		if (rows == "" && row == "") {
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
		var row = $('#marketPromotionAnalysis_grid_one').datagrid("getRows");
		var rows = $('#marketPromotionAnalysis_grid_three').datagrid("getRows");
		var url = "";
		if (row != "") {
			url = "marketPromotionAnalysis_saveSearchDataForm_2.html?saveType=1";
		} else if (rows != "") {
			url = "marketPromotionAnalysis_saveSearchDataForm_2.html?saveType=2";
		}
		var sellRegion = $("#sellRegion").combobox("getValue");
		var sellRegionName=$("#sellRegion").combobox("getText");
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox("getValue");
		var minDates = $("#minDates").datebox("getValue");
		var maxDates = $("#maxDates").datebox("getValue");
		if(minDate==minDates&&maxDate==maxDates){
			$.messager.alert("提示", "两个档期日期一样");
			return;
		}
		var DL = $('input[name="DL"]:checked');
		var show = $('input[name="show"]:checked');
		var prarm = "&sellRegion=" + sellRegion +"&minDate=" + minDate + "&maxDate=" + maxDate + "&minDates=" + minDates + "&maxDates=" + maxDates
				+ "&searchType=" + DL[0].value + "&searchTable="+show[0].value+"&sellRegionName="+sellRegionName;
		url += prarm;
		$("a[id='save']").linkbutton("disable");
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
	execlMarket : function() {
		var row = $('#marketPromotionAnalysis_grid_one').datagrid("getRows");
		var rows = $('#marketPromotionAnalysis_grid_three').datagrid("getRows");
		var url = "";
		if (rows == "" && row == "") {
			$.messager.alert("提示", "数据为空不能导出");
			return;
		} else if (row != "") {
			url = "marketPromotionAnalysis_exportMarketPromotionAnalysisToExcel_6.html?export=1";
		} else if (rows != "") {
			url = "marketPromotionAnalysis_exportMarketPromotionAnalysisToExcel_6.html?export=2";
		}
		var sellRegion = $("#sellRegion").combobox("getValue");
		var sellRegionName=$("#sellRegion").combobox("getText");
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox("getValue");
		var minDates = $("#minDates").datebox("getValue");
		var maxDates = $("#maxDates").datebox("getValue");
		if(minDate==minDates&&maxDate==maxDates){
			$.messager.alert("提示", "两个档期日期一样");
			return;
		}
		var DL = $('input[name="DL"]:checked');
		var show = $('input[name="show"]:checked');
		var prarm = "&sellRegion=" + sellRegion +"&minDate=" + minDate + "&maxDate=" + maxDate + "&minDates=" + minDates + "&maxDates=" + maxDates
				+ "&searchType=" + DL[0].value + "&searchTable="+show[0].value+"&sellRegionName="+sellRegionName;
		url += prarm;
		window.location = url;
		$.messager.show({
			title : '提示',
			msg : '数据导出中,请稍后...',
			timeout : 4000,
			showType : 'slide'
		});
	},
	searchDetail : function() {
		marketPromotionAnalysisFn.clearTable();
		var sellRegion = $("#sellRegion").combobox("getValue");
		var sellRegionName = $("#sellRegion").combobox("getText");
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox("getValue");
		var minDates = $("#minDates").datebox("getValue");
		var maxDates = $("#maxDates").datebox("getValue");
		var DL = $('input[name="DL"]:checked');
		var show = $('input[name="show"]:checked');
		if (sellRegion != "" && minDate != "") {
			if(minDate==minDates&&maxDate==maxDates){
				$.messager.alert("提示", "两个档期日期一样");
				return;
			}
			var url = "marketPromotionAnalysis_listMarketPromotionAnalysisDetail_2.html";
			$('#marketPromotionAnalysis_grid_two').datagrid({
				url : url,
				queryParams : {
					"sellRegion" : sellRegion,
					"sellRegionName" : sellRegionName,
					"minDate" : minDate,
					"maxDate" : maxDate,
					"minDates" : minDates,
					"maxDates" : maxDates,
					"searchType" : DL[0].value,
					"searchTable" : show[0].value
				}
			});
			var urls = "marketPromotionAnalysis_listMarketPromotionAnalysisDetails_2.html";
			$('#marketPromotionAnalysis_grid_three').datagrid({
				url : urls,
				queryParams : {
					"sellRegion" : sellRegion,
					"sellRegionName" : sellRegionName,
					"minDate" : minDate,
					"maxDate" : maxDate,
					"minDates" : minDates,
					"maxDates" : maxDates,
					"searchType" : DL[0].value,
					"searchTable" : show[0].value
				}
			});
			$("#tableTwo").show();
			$("#tableThree").show();
		} else {
			$.messager.alert("提示", "请选择必须搜索项");
		}
	},
	// 格式百分
	formatterPer : function(value, row) {
		if (value != null) {
			return moneyFormatter(value) + '%';
		} else {
			return value;
		}
	},//格式活跃度
	formatterActive : function(value, row) {
		if(value>100){
		 	return 100.00+'%';
		 }else if(value!=null){
		 	return moneyFormatter(value)+'%';
		 }else{
		 	return value;
		 }
	},
	// 格式销售单位
	formatterUnit : function(value, row) {
		if (value != null) {
			return moneyFormatter(value);
		} else {
			return value;
		}
	},
	// 格式销售单位
	formatterUnit1 : function(value, row) {
		if (value != null) {
			return formatterCount(value);
		} else {
			return value;
		}
	},
	bfb : function(value, row) {
		if (row.bfb != '' && row.bfb != null) {
			if (value != null) {
				return moneyFormatter(value) + '%';
			} 
		}
		if (value != null) {
			return moneyFormatter(value);
		} else {
			return value;
		}
	},
	bfb1 : function(value, row) {
		if (row.bfb != '' && row.bfb != null) {
			if (value != null) {
				return moneyFormatter(value) + '%';
			} 
		}
		if (value != null) {
			return formatterCount(value);
		} else {
			return value;
		}
	
	},
	clear : function() {
		marketPromotionAnalysisFn.clearInfo();
		marketPromotionAnalysisFn.clearTable();
	},
	clearTable : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		$(".marketPromotionAnalysis").hide();
		$('#marketPromotionAnalysis_grid_one').datagrid('loadData', {
			total : 0,
			rows : []
		});
		$('#marketPromotionAnalysis_grid_two').datagrid('loadData', {
			total : 0,
			rows : []
		});
		$('#marketPromotionAnalysis_grid_three').datagrid('loadData', {
			total : 0,
			rows : []
		});
	},
	clearInfo : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		$(".list-input").datebox("setValue", "");
		$(".filterSelect").combobox("setValue", "");
		$("#DL").attr("checked",true);
		$("#show").attr("checked",true);
	},
	// 格式化时间
	formattDate : function(value, row) {
		var date = new Date(value);
		var month = date.getMonth() + 1;
		if (10 > month) {
			month = '0' + month;
		}
		var day = date.getDate();
		if (10 > day) {
			day = '0' + day;
		}
		return date.getFullYear() + '-' + month + '-' + day;
	}
};