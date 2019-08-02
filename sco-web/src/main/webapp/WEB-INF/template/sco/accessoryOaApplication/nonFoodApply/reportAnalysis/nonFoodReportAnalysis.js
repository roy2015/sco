
var noneOaReportAnalyFn = {
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
		noneOaReportAnalyFn.seaFir = true;
		var title = '关联报表';
		var href = 'analysisReportNonFood_showLinkAnalysisReportNonFoodForm_1.html';
		
		var dlg = utils.showDlg({
			title:title,href:href,width:540,height:430,
			buttons:[{text:'确认关联',id:'sbt',handler:function(){noneOaReportAnalyFn.submitLinkForm(dlg);},iconCls:'save'},
			        {text:'关闭页面',handler:function(){dlg.dialog('close');},iconCls:'close'}
			],onLoad:function() {
				$("#typeCode").val(val);//报表类型编号
				$("#typeName").html(text);//报表类型名称
				noneOaReportAnalyFn.addRptGrid = utils.grid($('#comOalinkReportGrid'));
				noneOaReportAnalyFn.addRptGrid.registerExtFilters("typeCode", "reportsName", "minSaveDate", "maxSaveDate");
				noneOaReportAnalyFn.addRptGrid.initFilters({
					onBeforeLoad:function(obj) {
						if (noneOaReportAnalyFn.seaFir) {
							noneOaReportAnalyFn.seaFir = false;
							return false;
						}
						obj.reportsTypeCode = val;
						obj.reportsName = $("#reportsName").val();
						obj.reportsName = $("#reportsName").val();
						obj.created = $("#minSaveDate").datebox("getValue");
						obj.createdEnd = $("#maxSaveDate").datebox("getValue");
						
						$('input[id=selectedRt]').val(noneOaReportAnalyFn.added);//保存已选中的商品
					},
					onLoadSuccess:function(obj) {
						//选中已选择的商品
						$(obj.rows).each(function(i, row){
							var mCode = row.merchandiseCode;
							if($('input[id=selectedRt]').val().indexOf(mCode) > 0){
								$('#comOalinkReportGrid').datagrid('selectRow', i);
							}
						});
					},
					onClickRow:noneOaReportAnalyFn.selectedMd,
					onCheck:noneOaReportAnalyFn.selectedMd,
					onUncheck:noneOaReportAnalyFn.selectedMd,
					onCheckAll:noneOaReportAnalyFn.selectedMd,
					onUncheckAll:noneOaReportAnalyFn.selectedMd
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
		noneOaReportAnalyFn.seaFir = false;
		var param = noneOaReportAnalyFn.addRptGrid.getFilterValue();
		$('#comOalinkReportGrid').datagrid('load', param);
	},
	
	//打开成本分析文件
	formatFileName:function(value, rowData) {
		return '<a href=javascript:void(0) onclick=noneOaReportAnalyFn.downloadOriginalFile("' + encodeURIComponent(rowData.reportsUrl) + '","'+ encodeURIComponent(rowData.reportsName) + '")>' + value + '</a><br>';
	},
	
	// 下载文件
	downloadOriginalFile : function(filePath, reportsName) {
		window.location = "committeeApply_downloadOriginalFile_6.html?path=" + filePath + "&reportsName=" + reportsName;
	},
	
	//清空查询
	clearSearch:function() {
		$('#comSearRpt_form').form('reset');
		$('#comOalinkReportGrid').datagrid('clearSelections');
		$('#comOalinkReportGrid').datagrid('loadData',{total:0,rows:[]});
	},
	
	//关联dlg
	submitLinkForm:function(dlg) {
		var rows = $('#comOalinkReportGrid').datagrid("getSelected");
		if (!rows) {
			$.messager.alert("提示", "请选择一条报表");
			return;
		}
		utils.post("analysisReportNonFood_insertLinkAnalysisReportNonFood_2.html",
			{
			applicationCode : '${applicationCodeNow}', 
			quotedCodes : '${quotedCodes}',
		    reportsTypeCode : $("#typeCode").val(),
		    reportsCode : rows.reportsCode
			},function(){
				dlg.dialog('close');
				noneOaReportAnalyFn.reloadSystemOaReportAnalyGrid();
		});
		
	},

	//取消关联确认
	confirmRemoveLinkSystem:function() {
		var rows = $("#nonFoodOaReportAnalyGrid").datagrid("getSelections");
		if (rows.length < 1) {
			window.parent.$.messager.alert("提示", "请勾选至少一条报表记录");
			return ;
		}
		$("#removeSystemDlg").window('open');//打开窗口
	},

	//取消系统报表关联
	removeLink:function() {
		var rows = $('#nonFoodOaReportAnalyGrid').datagrid("getSelections");
		var reportsCode = '';
		for (var i in rows) {
			reportsCode += "'" + rows[i].reportsCode + "',";
		}
		reportsCode = reportsCode.substring(0, reportsCode.length - 1);
		noneOaReportAnalyFn.closeDlg();
		utils.post('analysisReportNonFood_deleteAnalysisReportSystem_2.html', 
				{	
				 applicationCode : '${applicationCodeNow}', 
				 quotedCodes : '${quotedCodes}',
				 reportsCodes : reportsCode
				},function(){
					noneOaReportAnalyFn.reloadSystemOaReportAnalyGrid();
		});
	},

	//取消上传关联确认
	confirmRemoveLinkUpload:function() {
		var rows = $("#uploadOaReportAnalyGrid").datagrid("getSelections");
		if (rows.length < 1) {
			window.parent.$.messager.alert("提示", "请勾选至少一条报表记录");
			return ;
		}
		var reportsCode = '';
		for (var i in rows) {
			reportsCode += "'" + rows[i].reportsCode + "',";
		}
		reportsCode = reportsCode.substring(0, reportsCode.length - 1);
		parent.window.utils.confirm('提示','是否确认删除此分析报表',function(){
			utils.post("analysisReportNonFood_deleteAnalysisReportUpload_2.html",
					{
					applicationCode : '${applicationCodeNow}', 
					quotedCodes : '${quotedCodes}',
				 	reportsCodes : reportsCode
				 	},function(){
						noneOaReportAnalyFn.reloadUploadOaReportAnalyGrid();
			});
		});
	},

	//取消申购单关联
	confirmRemoveLinkPurOrder:function() {
		var rows = $("#purOrderOaReportAnalyGrid").datagrid("getSelections");
		if (rows.length < 1) {
			window.parent.$.messager.alert("提示", "请勾选至少一条报表记录");
			return ;
		}
		var reportsCode = '';
		for (var i in rows) {
			reportsCode += "'" + rows[i].reportsCode + "',";
		}
		reportsCode = reportsCode.substring(0, reportsCode.length - 1);
		parent.window.utils.confirm('提示','是否确认删除此分析报表',function(){
			utils.post("analysisReportNonFood_deleteAnalysisReportPurOrder_2.html",
				{
				applicationCode : '${applicationCodeNow}', 
				quotedCodes : '${quotedCodes}',
				reportsCodes : reportsCode
				},function(){
					noneOaReportAnalyFn.reloadPurOrderOaReportAnalyGrid();
				}
			);
		});
	},
	
	//上传报表
	rptUpload:function() {
		var val = $('#reportType').combobox('getValue');
		if(!val) {
			parent.window.$.messager.alert('提示', '请选择需要关联的报表类型');
			return;
		}
		if(!$('#txt').val()) {
			parent.window.$.messager.alert('提示', '请选择上传文件');
			return;
		}
		
		showLoading();
		var file = $("#upload");
		$("#rptUpload_form").form("submit", {
			url : "analysisReportNonFood_uploadAnalysisReport_2.html",
			success : function(data) {
				var json = $.parseJSON(data);
				var msg = json.msg;
				if (json.success) {
					setTimeout(function(){
						$('.msg_bg').remove();
						$.messager.show({
							title : '提示',
							msg : msg
						});
						$("#txt").val("");
						file.after(file.clone().val("")); 
						file.remove();
						noneOaReportAnalyFn.reloadUploadOaReportAnalyGrid();
					}, 3000);
				}else {
					$('.msg_bg').remove();
					window.parent.$.messager.alert("提示", msg);
				}
			}
		});
		
	},
	
	//显示添加或修改窗体
	showPurOrderUploadForm:function(isEdit){
		var title='上传申购单';
		var href='analysisReportNonFood_showPurOrderUploadForm_1.html';
		var dlg=utils.showDlg({
			title:title,href:href,width:400,height:259,
			buttons:[{text:'确定',
						handler:function(){noneOaReportAnalyFn.purOrderUpload(dlg);},iconCls:'save'},
			        {text:'关闭',
						handler:function(){dlg.dialog('close');},iconCls:'close'}
			],onLoad:function() {
				$("#nonFood_form").form('validate');
			}
		});
		$(".window-shadow").remove();
	},
	
	//上传申购单
	purOrderUpload:function(dlg) {
		if(!$('#purTxt').val()) {
			parent.window.$.messager.alert('提示', '请选择一个本地文件');
			return;
		}
		if(!$("#nonFood_form").form('validate')) {
			return;
		}
		
		$("#nonFood_form").form("submit", {
			url : "analysisReportNonFood_uploadAnalysisReportPurOrder_2.html",
			onSubmit: function(param){   
		        param.applicationCode = '${applicationCodeNow}'; 
		        param.quotedCodes = '${quotedCodes}';
			},
			success : function(data) {
				var json = $.parseJSON(data);
				var msg = json.msg;
				if (json.success) {
					window.parent.$.messager.show({
						title : '提示',
						msg : msg
					});
					dlg.dialog('close');
					noneOaReportAnalyFn.reloadPurOrderOaReportAnalyGrid();
				}else {
					window.parent.$.messager.alert("提示", msg);
				}
			}
		});
	},
	
	//重新加载已关联系统报表列表
	reloadSystemOaReportAnalyGrid:function() {
		$('#nonFoodOaReportAnalyGrid').datagrid("clearSelections");
		$('#nonFoodOaReportAnalyGrid').datagrid('load');
	},

	//重新加载已关联上传报表列表
	reloadUploadOaReportAnalyGrid:function() {
		$('#uploadOaReportAnalyGrid').datagrid("clearSelections");
		$('#uploadOaReportAnalyGrid').datagrid('load');
	},

	//重新加载已关联申购单列表
	reloadPurOrderOaReportAnalyGrid:function() {
		$('#purOrderOaReportAnalyGrid').datagrid("clearSelections");
		$('#purOrderOaReportAnalyGrid').datagrid('load');
	},
	
	//关闭确认dlg
	closeDlg:function() {
		$("#removeSystemDlg").window('close');//关闭窗口
	},
	
	//启用、禁用按钮
	setBtnStatus:function() {
		var rows = $('#nonFoodOaReportAnalyGrid').datagrid("getSelections");
		if (rows.length == 1) {
			$("a[id='remove']").linkbutton("enable");
		} else if (rows.length > 1){
			$("a[id='remove']").linkbutton("enable");
		} else {
			$("a[id='remove']").linkbutton("disable");
		}	
	}
	
};