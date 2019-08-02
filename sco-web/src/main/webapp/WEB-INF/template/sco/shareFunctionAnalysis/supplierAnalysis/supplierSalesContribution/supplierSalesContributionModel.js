var salesContributionUtil = null;
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
$(document).ready(function() {
			salesContributionUtil = utils.grid($('#salesContribution_Grid'));
			// 注册查询框
			salesContributionUtil.registerExtFilters("supplierCode", "supplierName", "minDate", "maxDate");
			salesContributionUtil.initFilters({
				onBeforeLoad : function(obj) {
					// 清空查询
					if (clearSearch) {
						return true;
					}
					var length = $.param(obj).split("&").length;
					// 判断参数个数
					if (length < 3) {
						if (!firstIn)
							$.messager.alert("提示", "请输入至少一项查询条件");
						firstIn = false;
						return false;
					}
					if (length == 4 && $.param(obj).indexOf("order") > -1) {
						var param = salesContributionUtil.getFilterValue();
						if ($.param(param).length > 0) {
							return true;
						}
						$.messager.alert("提示", "请输入至少一项查询条件");
						return false;
					}
				},
				onLoadSuccess : function() {
					firstIn = false;
				}
			});
		});
var salesContributionFn = {
	// 查询
	search : function() {
		var param = salesContributionUtil.getFilterValue();
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox('getValue');
		
		// 日起范围的合法性校验
		if (minDate == "" || maxDate == "") {
			$.messager.alert('提示', '<center>请输入必选条件时间范围！</center>');
			return;
		}
		if (minDate.substring(0, 4) != maxDate.substring(0, 4)) {
			$.messager.alert('提示', '<center>开始日期与结束日期的年份必须相同!</center>');
			return;
		}
		$('#salesContribution_Grid').datagrid('load', param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#salesContribution_search').form('reset');
		$('#salesContribution_Grid').datagrid('clearSelections');
		$('#salesContribution_Grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		clearSearch = false;
	},
	refresh : function() {
		clearSearch = true;
		$('#salesContribution_Grid').datagrid('reload');
		clearSearch = false;
	},
	//导出到Excel
	exportSalesContributionToExcel:function(){
		var param=$.param(salesContributionUtil.getFilterValue());
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox('getValue');
		
		// 日起范围的合法性校验
		if (minDate == "" || maxDate == "") {
			$.messager.alert('提示', '<center>请输入必选条件时间范围！</center>');
			return;
		}
		if (minDate.substring(0, 4) != maxDate.substring(0, 4)) {
			$.messager.alert('提示', '<center>开始日期与结束日期的年份必须相同!</center>');
			return;
		}
		
		window.location="salesContribution_exportSalesContributionToExcel_3.html?"+$.param(salesContributionUtil.getFilterValue());
		$.messager.show({
			title:'文件导出提示',
			msg:'文件正在导出中，请稍后！',
			timeout:4000,
			showType:'slide'
		});
	},
	// 填写保存文件名称
	saveFile : function() {
		var rows = $('#salesContribution_Grid').datagrid("getRows");
		if (rows.length==0) {
			$.messager.alert("提示", "数据为空不能保存!");
			return;
		}
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
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox('getValue');
		var supplierCode = $("#supplierCode").val();//供应商编号
		var supplierName = $("#supplierName").val();//供应商名称
		url = "salesContribution_saveSalesContributionToHtml_2.html?"+$.param(salesContributionUtil.getFilterValue());;
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
};