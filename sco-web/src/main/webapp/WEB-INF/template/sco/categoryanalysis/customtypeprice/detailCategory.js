var cusDetPrice1 = null;
var cusDetPrice2 = null;

$(document).ready(function() {
	cusDetPrice1 = utils.grid($('#cusDetPrice1'));
	cusDetPrice1.initFilters({
		onLoadSuccess : function(data) {
			$(".datagrid-mask").remove();
			$(".datagrid-mask-msg").remove();
		}
	});
	$('#cusDetPrice1').datagrid('loadData', {total:'${paraMap.d1Size}', rows:${paraMap.data1Json}});
	
	cusDetPrice2 = utils.grid($('#cusDetPrice2'));
	cusDetPrice2.initFilters({
		onLoadSuccess : function(data) {
			if (data.rows.length < 1 && !'${paraMap.flag2}') $("#allGrid2").hide();
		}
	});
	$('#cusDetPrice2').datagrid('loadData', {total:'${paraMap.d2Size}', rows:${paraMap.data2Json}});
	
});

var cusDetFn = {

	//关闭当前页面
	closePage : function() {
		parent.pubTab.closeCurrTab();
	},
	
	// 百分比格式
	formatPercent : function(val) {
		if (!val || val == 0) {
			return '0.00%';
		} else {
			val = (val * 100).toFixed(2);
			return val + '%';
		}
	},

	// 大于1显示100%
	formatBigPercent : function(val) {
		if (val >= 1) return '100.00%';
		return cusDetFn.formatPercent(val);
	},
	
	//处理json对象
	replaceJsonStr:function() {
		var param = $("#allList").val().replace(new RegExp(/(%)/g), "\"");
		return JSON.stringify(JSON.parse(param)).replace(/"([^"]*)"/g, "'$1'");
	},

	//显示消息
	showMsg : function(msg) {
		$.messager.show({
			title:'提示',
			msg:msg,
			timeout:5000,
			showType:'slide'
		});
	},
	
	// 导出到Excel
	exportToExcel : function() {
		var data1 = $('#cusDetPrice1').datagrid('getRows');
		var data2 = $('#cusDetPrice2').datagrid('getRows');
		// 是否查出数据
		if (data1.length < 1 && data2.length < 1) {
			window.parent.$.messager.alert('提示', '当前未查询到任何数据');
			return;
		}
		
		cusDetFn.showMsg('数据导出中,请稍后...');
		
		$("#cusDet_form").form('submit', {
			url : "customTypePrice_exportDetCategoryToExcel_6.html",
			onSubmit : function(param) {
				param.rows = cusDetFn.replaceJsonStr();
			},
			success : function(data) {
				var json = $.parseJSON(data);
				if (json.success) {
					
				}else{
					window.parent.$.messager.alert("提示", "导出文件出错");
				}
			}
		});
	},

	// 填写保存文件名称
	saveFile : function() {
		var data1 = $('#cusDetPrice1').datagrid('getRows');
		var data2 = $('#cusDetPrice2').datagrid('getRows');
		// 是否查出数据
		if (data1.length < 1 && data2.length < 1) {
			window.parent.$.messager.alert('提示', '当前未查询到任何数据');
			return;
		}

		$("#cusDetFileDlg").window('open');// 打开窗口
	},

	// 提交填写文件名称的对话框
	submitSaveFileDlg : function() {
		var fileName = $.trim($("#cusDetFileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			return;
		}
		
		$("#saveFile").linkbutton("disable");
		$("#cusDet_form").form('submit', {
			url : "customTypePrice_saveDetCategory_2.html",
			onSubmit : function(param) {
				param.fileName = fileName;
				param.rows = cusDetFn.replaceJsonStr();
			},
			success : function(data) {
				var json = $.parseJSON(data);
				if (json.success) {
					$("#cusDetFileName").val("");// 清空填写的值
					$("#cusDetFileDlg").window("close");// 关闭窗口
					cusDetFn.showMsg(json.msg);
				}else{
					window.parent.$.messager.alert("提示", json.msg);
				}
				$("#saveFile").linkbutton("enable");
			}
		});
	},

	// 关闭界面
	closeSaveFileDlg : function() {
		$("#cusDetFileName").val("");// 清空填写的值
		$("#cusDetFileDlg").window('close');// 关闭窗口
	}

};