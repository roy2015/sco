var firstIn = true;// 第一次进入页面
var clearSearch = false;// 清空查询
var show="";
var supplierEvaluateTableGridUtil = null;
$(document).ready(function() {
	$("a[id='df']").linkbutton("disable");
	$("a[id='fb']").linkbutton("disable");
	supplierEvaluateTableGridUtil = utils.grid($('#supplierEvaluateTable_grid'));
	supplierEvaluateTableGridUtil.registerExtFilters('templateName','status', 'supplierName', 'supplierCode', 'createby','createbySE','minPublishDate', 'maxPublishDate', 'minCreated', 'maxCreated');
	supplierEvaluateTableGridUtil.initFilters({
		onClickRow : supplierEvaluateTableFn.setButtonState, // 设置按钮状态
		onCheck : supplierEvaluateTableFn.setButtonState, // 设置按钮状态
		onUncheck : supplierEvaluateTableFn.setButtonState, // 设置按钮状态
		onSelectAll : supplierEvaluateTableFn.setButtonState, // 设置按钮状态
		onUnselectAll : supplierEvaluateTableFn.setButtonState, // 设置按钮状态
		onLoadSuccess:function(){
			supplierEvaluateTableFn.setButtonState();
		}
	});
});
var flag = true;
var supplierEvaluateTableFn = {
	// 搜索考评表
	searchTable : function(type) {
		// $('#supplierEvaluateTable_grid').datagrid('getColumnOption','evaluateEndDate').hidden=true;
		var param = supplierEvaluateTableGridUtil.getFilterValue();
		if (JSON.stringify(param) == "{}") {
			$.messager.alert('提示', '请输入至少一项查询考评表条件');
			return;
		}
		supplierEvaluateTableFn.showType(type);
		param.searchButton = "1";
		// $("span:contains('考核结束时间')").parent().remove();
		var url = "supplierEvaluateTable_listSupplierEvaluateTable_2.html";
		$('#supplierEvaluateTable_grid').datagrid({
			'url' : url,
			queryParams : param
		});
	},
	// 搜索模板
	searchTemplate : function(type) {
		var createby=$("#createby").val();
		$("#createby").val("");
		var param = supplierEvaluateTableGridUtil.getFilterValue();
		if (JSON.stringify(param) == "{}") {
			$.messager.alert('提示', '请输入至少一项查询考评模板条件');
			$("#createby").val(createby);
			return;
		}
		supplierEvaluateTableFn.showType(type);
		// $("span:contains('考核结束时间')").parent().remove();
		var url = "supplierEvaluateTable_listSupplierEvaluateTable_2.html";
		$('#supplierEvaluateTable_grid').datagrid({
			'url' : url,
			queryParams : param
		});
		
	},
	showType : function(type) {
		if (type == "hide") {
			show = 'hideColumn';
		} else {
			show = 'showColumn';
		}
		$('#supplierEvaluateTable_grid').datagrid(show, 'evaluateTableCode');
		$('#supplierEvaluateTable_grid').datagrid(show, 'createby');
		$('#supplierEvaluateTable_grid').datagrid(show, 'supplierCode');
		$('#supplierEvaluateTable_grid').datagrid(show, 'supplierName');
		$('#supplierEvaluateTable_grid').datagrid(show, 'scoreCG');
		$('#supplierEvaluateTable_grid').datagrid(show, 'scorePK');
		$('#supplierEvaluateTable_grid').datagrid(show, 'scoreKC');
		$('#supplierEvaluateTable_grid').datagrid(show, 'scoreTotal');
		$('#supplierEvaluateTable_grid').datagrid(show, 'scoreRank');
		$('#supplierEvaluateTable_grid').datagrid(show, 'scorePL');
	},
	// 清空查询
	clearFilter : function() {
		clearSearch = true;
		$("#signedQty_search").form("reset");
		$("#supplierEvaluateTable_grid").datagrid('loadData', {
			total : 0,
			rows : []
		});

		clearSearch = false;
	},
	// 改变考评模板编号样式
	change : function(value, row, index) {
		if (value != null && value != "") {
			return '<a href=javascript:void(0) onClick=supplierEvaluateTableFn.openSearchTemplate("' + encodeURIComponent(row.templateCode) + '")>' + value + '</a><br>';
		}

	},
	// 改变考评表编号样式
	changeStyle : function(value, row, index) {
		if (value != null && value != "") {
			return '<a href=javascript:void(0) onClick=supplierEvaluateTableFn.openSearch("' + encodeURIComponent(row.evaluateTableCode) + '")>' + value + '</a><br>';
		}

	},
	// 打开一个新的页面
	openSearchTemplate : function(templateCode) {
		var url = "supplierEvaluateTemplate_showSupplierEvaluateTemplateByTemplateCode_1.html?templateCode=" + templateCode;
		parent.addTabByUrl('供应商考评模板', 'copy', url);// 新打开一个选项卡，也就是新打开一个页面
	},
	// 查看已发布的考评表详情
	openSearch : function(evaluateTableCode) {
		var url = "supplierEvaluateTable_showSupplierEvaluateTableByEvaluateTableCode_1.html?evaluateTableCode=" + evaluateTableCode;
		parent.addTabByUrl('供应商考评表详情', 'copy', url);// 新打开一个选项卡，也就是新打开一个页面
	},
	// 发布考评表
	publishTable : function() {
		var list = "";
		var row = $('#supplierEvaluateTable_grid').datagrid("getSelections");
		if (row.length != 1) {
			$.messager.alert("提示", "请只选择一个模版");
			return;
		} else {
			if(row[0].status=="已关闭"){
				$.messager.alert("提示", "请选择一个未关闭的模版");
				return;	
			}
			var dlg = utils.showDlg({
				title : '发布考评表',
				href : 'supplierEvaluateTable_showSupplierEvaluateTableForm_1.html' + '?templateCode=' + row[0].templateCode,
				width : 500,
				height : 400,
				buttons : [ {
					id:'save',
					text : '确定发布',
					handler : function() {
						var arr = [];
						var supplierCode = "";
						var templateCode = "";
						var rows = $('#publishSupplierEvaluateTable_grid').datagrid("getSelections");
						var row = $('#supplierEvaluateTable_grid').datagrid("getSelections");
						if (rows.length == 0) {
							$.messager.alert("提示", "请选择至少一个供应商");
							return;
						}
						for (var i = 0; i < rows.length; i++) {
							supplierCode += rows[i].supplierCode + ",";
							templateCode += row[0].templateCode + ",";
						}
						$("a[id='save']").linkbutton("disable");
						$.post("supplierEvaluateTable_insertTemplateTable_2.html", {
							"supplierCodes" : supplierCode,
							"templateCodes" : templateCode
						}, function(json) {
							if (json.success) {
								dlg.dialog('close');
								parent.messagerShow({
									title : '操作成功!',
									msg : json.msg
								});
								
							} else {
								$("a[id='save']").linkbutton("enable");
								$.messager.alert("操作失败", json.msg);
							}
						}, "json");
					}
				}, {
					text : '关闭',
					handler : function() {
						dlg.dialog('close');
					},
					iconCls : 'close'
				} ],
			});
		}
	},
	mark : function() {
		var row = $("#supplierEvaluateTable_grid").datagrid("getSelections");
		if (row.length != 1) {
			$.messager.alert("提示", "请选择一条记录，只可对考评模版为已发布状态的考评表打分");
			return;
		} else if (row.length == 1 && row[0].status != '已发布') {
			$.messager.alert("提示", "请选择一条记录，只可对考评模版为已发布状态的考评表打分");
			return;
		} else {
			if (row[0].evaluateTableCode) {
				var evaluateTableCode = row[0].evaluateTableCode;
				var url = "supplierEvaluateTable_showSupplierEvaluateTableMarkByEvaluateTableCode_1.html?evaluateTableCode=" + evaluateTableCode;
				parent.addTabByUrl('供应商考评表打分', 'copy', url);
			} else {
				$.messager.alert("提示", "请先发布考评表，之后再打分");
			}
		}
	},
	//格式化日期
	formatterDate : function(value, row) {
		return row.evaluateStartDate + '~' + row.evaluateEndDate;
	},
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#supplierEvaluateTable_grid').datagrid("getSelections");
		if(rows.length<1){
			$("a[id='df']").linkbutton("disable");
			$("a[id='fb']").linkbutton("disable");
		}else{
			if(show=="showColumn"){
				$("a[id='df']").linkbutton("enable");
			}else if(show=="hideColumn"){
				$("a[id='fb']").linkbutton("enable");
			}
		}	
	}
};