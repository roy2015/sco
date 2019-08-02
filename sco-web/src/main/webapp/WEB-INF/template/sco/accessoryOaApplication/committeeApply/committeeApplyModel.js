var committeeApplyGridUtil = null;
var firstIn = true;// 是否第一次进入页面
var clearSearch = false;// 清空查询
$(document).ready(
		function() {
			committeeApplyGridUtil = utils.grid($('#committeeApplyGrid'));
			committeeApplyGridUtil.registerExtFilters("intentionSupplierCode", "intentionSupplierName", "intentionCode", "intentionName", "intentionCreated", "intentionCreatedEnd", "centreTypeCode",
					"smallTypeCode", "detailTypeCode", "fineTypeCodes", "enquiryCode", "enquiryCreated", "enquiryCreatedEnd", "quotedCode", "quotedCreated", "quotedCreatedEnd", "applicationCode",
					"applicationStatus", "search");
			committeeApplyGridUtil.initFilters({
				onBeforeLoad:function(obj) {
					// 清空查询
					if(clearSearch) {
						return true;
					}
					var length = $.param(obj).split("&").length;
					// 判断参数个数
					if (length < 4) {
						if(!firstIn) $.messager.alert("提示", "请输入至少一项查询条件");
						firstIn = false;
						return false;
					}
					if(length == 5 && $.param(obj).indexOf("order") > -1) {
						var param = committeeApplyGridUtil.getFilterValue();
						if($.param(param).length > 0){
							return true;
						}
						$.messager.alert("提示", "请输入至少一项查询条件");
						return false;
					}
				},
				onLoadSuccess : function() {

					$('#committeeApplyGrid').datagrid("clearSelections");
				}
			});
		});
var committeeApplyFn = {
	// 添加申请报告(新品引进)
	showInsert : function(applicationCode) {
		var quotedCodes = [];
		if (applicationCode == null || applicationCode == 'null') {
			var committeeApplyGrid = $('#committeeApplyGrid').datagrid('getChecked');
			if (committeeApplyGrid.length == 0) {
				$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
				return;
			} else {
				var applicationFlag = true;
				$.each(committeeApplyGrid, function(index, item) {
					if (item.applicationStatus != null && item.applicationStatus != "") {
						// 判断是否勾选已经做了OA申请记录的意向品
						applicationFlag = false;
					}

					quotedCodes.push(item.quotedCode);

				});
				if (!applicationFlag) {
					$.messager.alert('提示', '<center>请勾选申请单状态是"无"的记录！</center>');
					return;
				}
			}
			var tabName = '新增采购委员会竞价单OA申请';
		}else{
			var tabName ='申请单-'+applicationCode;
		}
		var url = "committeeApply_addCommitteeApply_1.html?quotedCodes=" + quotedCodes + "&applicationCode=" + applicationCode;
		if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
			parent.activeTab(tabName);
			parent.refreshTab(tabName);
			return;
		}
		parent.addTabByUrl(tabName, 'agent', url);

	},
	// 查询方法
	search : function() {
		var param = committeeApplyGridUtil.getFilterValue();

		var intentionCreated = $("#intentionCreated").datebox("getValue");
		var intentionCreatedEnd = $("#intentionCreatedEnd").datebox("getValue");
		// 创建时间的合法性校验
		if (intentionCreated == "" && intentionCreatedEnd != "") {
			$.messager.alert('提示', '<center>请输入意向品开始创建时间！</center>');
			return;
		}
		if (intentionCreatedEnd == "" && intentionCreated != "") {
			$.messager.alert('提示', '<center>请输入意向品结束创建时间！</center>');
			return;
		}
		if (intentionCreated > intentionCreatedEnd) {
			$.messager.alert('提示', '<center>结束意向品创建时间不能早于开始创建时间！</center>');
			return;
		}

		var enquiryCreated = $("#enquiryCreated").datebox("getValue");
		var enquiryCreatedEnd = $("#enquiryCreatedEnd").datebox("getValue");
		// 询价单提交日期的合法性校验
		if (enquiryCreated == "" && enquiryCreatedEnd != "") {
			$.messager.alert('提示', '<center>请输入询价单创建开始时间！</center>');
			return;
		}
		if (enquiryCreatedEnd == "" && enquiryCreated != "") {
			$.messager.alert('提示', '<center>请输入询价单创建结束时间！</center>');
			return;
		}
		if (enquiryCreated > enquiryCreatedEnd) {
			$.messager.alert('提示', '<center>询价单创建结束时间不能早于询价单创建开始时间！</center>');
			return;
		}
		var quotedCreated = $("#quotedCreated").datebox("getValue");
		var quotedCreatedEnd = $("#quotedCreatedEnd").datebox("getValue");
		// 报价单提交日期的合法性校验
		if (quotedCreated == "" && quotedCreatedEnd != "") {
			$.messager.alert('提示', '<center>请输入报价单上传开始时间！</center>');
			return;
		}
		if (quotedCreatedEnd == "" && quotedCreated != "") {
			$.messager.alert('提示', '<center>请输入报价单上传结束时间！</center>');
			return;
		}
		if (quotedCreated > quotedCreatedEnd) {
			$.messager.alert('提示', '<center>报价单上传结束时间不能早于报价单上传开始时间！</center>');
			return;
		}
		param.search='search';
		$('#committeeApplyGrid').datagrid('load', param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#committeeApply_search').form('reset');
		clearComboboxOptions();
		// accessoryIntentionGridUtil.clearFilter();
		 $('#committeeApplyGrid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
	},
	
	
	remove : function() {
		var applicationCode = [];
		var applicationFlag = true;
		var applyGrid = $('#committeeApplyGrid').datagrid('getChecked');
		if (applyGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
			return;
		} else {
			$.each(applyGrid, function(index, item) {
				if (item.applicationStatus == "CG" && item.applicationCode != null && item.applicationCode != "") {
					applicationCode.push(item.applicationCode);
				} else {
					applicationFlag = false;
					return;
				}

			});
			if (applicationFlag) {
				if (applicationCode.length > 0) {
					utils.confirm("操作确认", "确认删除OA申请吗?", function() {
						utils.post("committeeApply_deleteApplication_2.html?applicationCode=" + applicationCode, null, function() {
							// intentionUtil.refresh();
							$('#committeeApplyGrid').datagrid('reload');
						});
					});
				} else {
					$.messager.alert('提示', '<center>请勾选至少一条有效的记录</center>');
					return;
				}
			} else {
				$.messager.alert('提示', '<center>所选记录申请单号必须存在，并且是草稿状态</center>');
			}
		}
	},
	close : function() {
		var applicationCode = [];
		var applicationFlag = true;
		var applyGrid = $('#committeeApplyGrid').datagrid('getChecked');
		if (applyGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
			return;
		} else {
			$.each(applyGrid, function(index, item) {
				if (item.applicationStatus == "CG" && item.applicationCode != null && item.applicationCode != "") {
					applicationCode.push(item.applicationCode);
				} else {
					applicationFlag = false;
					return;
				}

			});
			if (applicationFlag) {
				if (applicationCode.length > 0) {
					utils.confirm("操作确认", "确认关闭OA申请吗?", function() {
						utils.post("committeeApply_closeApplication_2.html?applicationCode=" + applicationCode, null, function() {
							// intentionUtil.refresh();
							$('#committeeApplyGrid').datagrid('reload');
						});
					});
				} else {
					$.messager.alert('提示', '<center>请勾选至少一条有效的记录</center>');
					return;
				}
			} else {
				$.messager.alert('提示', '<center>所选记录申请单号必须存在，并且是草稿状态</center>');
			}
		}
	},
	allowOaSynchronous : function() {
		var applicationCode = [];
		var applicationFlag = true;
		var applyGrid = $('#committeeApplyGrid').datagrid('getChecked');
		if (applyGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
			return;
		} else if(applyGrid.length > 1){
			$.messager.alert('提示', '<center>请最多选择一条记录！</center>');
			return;
		}else{
			$.each(applyGrid, function(index, item) {
				if (item.applicationStatus == "CG" && item.applicationCode != null && item.applicationCode != "") {
					applicationCode.push(item.applicationCode);
				} else {
					applicationFlag = false;
					return;
				}

			});
			if (applicationFlag) {
				if (applicationCode.length > 0) {
					utils.confirm("操作确认", "确认允许OA同步吗?", function() {
						utils.post("committeeApply_allowOaSynchronous_2.html?applicationCode=" + applicationCode, null, function() {
							// intentionUtil.refresh();
							$('#committeeApplyGrid').datagrid('reload');
						});
					});
				} else {
					$.messager.alert('提示', '<center>请勾选至少一条有效的记录</center>');
					return;
				}
			} else {
				$.messager.alert('提示', '<center>所选记录申请单号必须存在，并且是草稿状态</center>');
			}
		}
	},
	undoOaSynchronous : function() {
		var applicationCode = [];
		var applicationFlag = true;
		var applyGrid = $('#committeeApplyGrid').datagrid('getChecked');
		if (applyGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
			return;
		} else {
			$.each(applyGrid, function(index, item) {
				if (item.applicationStatus == "YX" && item.applicationCode != null && item.applicationCode != "") {
					applicationCode.push(item.applicationCode);
				} else {
					applicationFlag = false;
					return;
				}

			});
			if (applicationFlag) {
				if (applicationCode.length > 0) {
					utils.confirm("操作确认", "确认撤销OA同步吗?", function() {
						utils.post("committeeApply_undoOaSynchronous_2.html?applicationCode=" + applicationCode, null, function() {
							// intentionUtil.refresh();
							$('#committeeApplyGrid').datagrid('reload');
						});
					});
				} else {
					$.messager.alert('提示', '<center>请勾选至少一条有效的记录</center>');
					return;
				}
			} else {
				$.messager.alert('提示', '<center>所选记录申请单号必须存在，并且是允许OA同步状态</center>');
			}
		}
	},
	//查看OA审批意见
	lookApproveOpinion:function(){
		var applyGrid = $('#committeeApplyGrid').datagrid('getChecked');
		if (applyGrid.length == 0) {
			$.messager.alert('提示', '<center>请选择一条记录！</center>');
		} else if(applyGrid.length >1){
			$.messager.alert('提示', '<center>请只选择一条申请记录！</center>');
		}else{
			var applicationCode="";//申请单号
			var oaApplicationCode="";//OA系统的申请单号
			$.each(applyGrid, function(index, item) {
				applicationCode=item.applicationCode;
				oaApplicationCode=item.oaApplicationCode;
			});
			
			//需要先判断是否做了OA申请，然后判断OA系统的信息是否为空
			if(applicationCode==null||applicationCode==""){
				$.messager.alert('提示', '<center>请选择已经做了OA申请记录！</center>');
			}else{
				if(oaApplicationCode==null||oaApplicationCode==""){
					$.messager.alert('提示', '<center>没有OA系统同步数据，不能查看OA审批意见!</center>');
				}else{
					window.open("http://eip.laiyifen.com/oa/lyf/cgwyhjjd.nsf/0/"+oaApplicationCode+"?opendocument");
					             
				}
			}
			
		}
	}
	
};