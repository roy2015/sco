
var adjustProceOaReportAnalyFn = {
	//设置datargrid高度
	setDatagridHeight:function() {
		$(".datagrid-view").css("height", "330px");
	},
	
	added:'',//待添加报表
	addRptGrid:null,//报表datagrid
	seaFir:true,//第一次进入
	//关联报表
	linkReport:function() {
		var val = $("#reportType").combobox('getValue');
		var text = $('#reportType').combobox('getText');
		if (!val) {
			window.parent.$.messager.alert("提示", "请选择需要关联的报表类型");
			return;
		}
		adjustProceOaReportAnalyFn.seaFir = true;
		var title = '关联报表';
		var href = 'analysisReportAdjustPrice_showLinkAnalysisReportAdjustPriceForm_1.html';
		
		var dlg = utils.showDlg({
			title:title,href:href,width:540,height:430,
			buttons:[{text:'确认关联',id:'sbt',handler:function(){adjustProceOaReportAnalyFn.submitLinkForm(dlg);},iconCls:'save'},
			        {text:'关闭页面',handler:function(){dlg.dialog('close');},iconCls:'close'}
			],onLoad:function() {
				$("#typeCode").val(val);//报表类型编号
				$("#typeName").html(text);//报表类型名称
				adjustProceOaReportAnalyFn.addRptGrid = utils.grid($('#adjustPriceOalinkReportGrid'));
				adjustProceOaReportAnalyFn.addRptGrid.registerExtFilters("typeCode", "reportsName", "minSaveDate", "maxSaveDate");
				adjustProceOaReportAnalyFn.addRptGrid.initFilters({
					onBeforeLoad:function(obj) {
						if (adjustProceOaReportAnalyFn.seaFir) {
							adjustProceOaReportAnalyFn.seaFir = false;
							return false;
						}
						obj.reportsTypeCode = val;
						obj.reportsName = $("#reportsName").val();
						obj.reportsName = $("#reportsName").val();
						obj.created = $("#minSaveDate").datebox("getValue");
						obj.createdEnd = $("#maxSaveDate").datebox("getValue");
						
						$('input[id=selectedRt]').val(adjustProceOaReportAnalyFn.added);//保存已选中的商品
					},
					onLoadSuccess:function(obj) {
						//选中已选择的商品
						$(obj.rows).each(function(i, row){
							var mCode = row.merchandiseCode;
							if($('input[id=selectedRt]').val().indexOf(mCode) > 0){
								$('#adjustPriceOalinkReportGrid').datagrid('selectRow', i);
							}
						});
					},
					onClickRow:adjustProceOaReportAnalyFn.selectedMd,
					onCheck:adjustProceOaReportAnalyFn.selectedMd,
					onUncheck:adjustProceOaReportAnalyFn.selectedMd,
					onCheckAll:adjustProceOaReportAnalyFn.selectedMd,
					onUncheckAll:adjustProceOaReportAnalyFn.selectedMd
				});
			}
		});
		$(".window-shadow").remove();
	},
	
	//选择数据
	selectedMd:function() {
		
	},
	
	//查询列表
	searchRpt:function() {
		adjustProceOaReportAnalyFn.seaFir = false;
		var param = adjustProceOaReportAnalyFn.addRptGrid.getFilterValue();
		$('#adjustPriceOalinkReportGrid').datagrid('load', param);
	},

	// 点击文件名称下载文件
	formatFileName : function(value, row, index) {
		return '<a href=javascript:void(0) onclick=adjustProceOaReportAnalyFn.downloadOriginalFile("' + encodeURIComponent(row.reportsUrl) + '")>' + value + '</a><br>';
	},
	
	// 下载文件
	downloadOriginalFile : function(filePath) {
		window.location = "analysisReportNew_downloadOriginalFile_6.html?path=" + filePath;
	},
	
	//清空查询
	clearSearch:function() {
		$('#adjustPriceSearchRpt_form').form('reset');
		$('#adjustPriceOalinkReportGrid').datagrid('clearSelections');
		$('#adjustPriceOalinkReportGrid').datagrid('loadData',{total:0,rows:[]});
	},
	
	//关联dlg
	submitLinkForm:function(dlg) {
		var rows = $('#adjustPriceOalinkReportGrid').datagrid("getSelected");
		if (!rows) {
			$.messager.alert("提示", "请选择一条报表");
			return;
		}
		utils.post("analysisReportAdjustPrice_insertLinkAnalysisReportAdjustPrice_2.html",
				{
				applicationCode : '${applicationCode}', 
			    intentionAndSupplierCodes : '${intentionAndSupplierCodes}',
			    reportsTypeCode : $("#typeCode").val(),
			    reportsCode : rows.reportsCode
				},function(){
					dlg.dialog('close');
					adjustProceOaReportAnalyFn.reloadAdjustPriceOaReportAnalyGrid();
		});
		
	},

	//取消关联确认
	confirmRemoveLink:function() {
		var rows = $("#adjustPriceOaReportAnalyGrid").datagrid("getSelections");
		if (rows.length < 1) {
			window.parent.$.messager.alert("提示", "请勾选至少一条报表记录");
			return ;
		}
		$("#removeDlg").window('open');//打开窗口
	},
	
	//取消关联
	removeLink:function() {
		var rows = $('#adjustPriceOaReportAnalyGrid').datagrid("getSelections");
		var reportsCode = '';
		for (var i in rows) {
			reportsCode += "'" + rows[i].reportsCode + "',";
		}
		reportsCode = reportsCode.substring(0, reportsCode.length - 1);
		adjustProceOaReportAnalyFn.closeDlg();
		utils.post('analysisReportAdjustPrice_deleteAnalysisReportAdjustPrice_2.html', 
				{	
				 applicationCode : '${applicationCode}', 
				 intentionAndSupplierCodes : '${intentionAndSupplierCodes}',
				 reportsCode : reportsCode
				},function(){
					adjustProceOaReportAnalyFn.reloadAdjustPriceOaReportAnalyGrid();
		});
	},
	
	//重新加载已关联报表列表
	reloadAdjustPriceOaReportAnalyGrid:function() {
		$('#adjustPriceOaReportAnalyGrid').datagrid("clearSelections");
		$('#adjustPriceOaReportAnalyGrid').datagrid('load');
	},
	
	//关闭确认dlg
	closeDlg:function() {
		$("#removeDlg").window('close');//关闭窗口
	},
	
	//启用、禁用按钮
	setBtnStatus:function() {
		var rows = $('#adjustPriceOaReportAnalyGrid').datagrid("getSelections");
		if (rows.length == 1) {
			$("a[id='remove']").linkbutton("enable");
		} else if (rows.length > 1){
			$("a[id='remove']").linkbutton("enable");
		} else {
			$("a[id='remove']").linkbutton("disable");
		}	
	}
	
};