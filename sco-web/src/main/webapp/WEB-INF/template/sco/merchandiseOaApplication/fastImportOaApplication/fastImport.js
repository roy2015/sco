var fastImportUtil = null;
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
$(document).ready(
	function() {
		fastImportUtil = utils.grid($('#fastImportGrid'));
		// 注册查询框
		fastImportUtil.registerExtFilters("applicationCode","intentionSupplierCode", "intentionSupplierName",
				"minCreateDate","maxCreateDate", "intentionCode","intentionName", "dxRoleCode", "dlRoleCode",
				"centreTypeCodeElse", "smallTypeCode","detailTypeCode", "fineTypeCode", "purchaseDepartments",
				"rationed", "purchaseType", "saleType", "applicationStatus", "minApplicationDate",
				"maxApplicationDate", "visitApplicationStatus", "minVisitDate", "maxVisitDate", 
				"packageApplicationStatus", "minPackageDate", "maxPackageDate");
		fastImportUtil.initFilters({
			onBeforeLoad : function(obj) {
				// 清空查询
				if (clearSearch) {
					return true;
				}
				var length = $.param(obj).split("&").length;
				// 判断参数个数
				if (length < 3) {
					if (!firstIn)
						$.messager.alert("提示", "请选择或录入至少一项查询条件");
					firstIn = false;
					return false;
				}
				if (length == 4 && $.param(obj).indexOf("order") > -1) {
					var param = fastImportUtil.getFilterValue();
					if ($.param(param).length > 0) {
						return true;
					}
					$.messager.alert("提示", "请选择或录入至少一项查询条件");
					return false;
				}
			},
			onLoadSuccess : function() {
				firstIn = false;
				$('#fastImportGrid').datagrid("clearSelections");
			}
		});
	}
);

var fastImportFn = {
	// 添加申请报告(快速调价)
	showInsert : function() {
		var fastImportGrid = $('#fastImportGrid').datagrid('getChecked');
		if (fastImportGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
		} else {
			var intentionAndSupplierCodes = [];
			var applicationFlag = true;
			$.each(fastImportGrid, function(index, item) {
				if (item.applicationStatus == "W"
						|| item.applicationStatus == "SPTG") {
					// 没有做OA申请，或者做了OA申请，但审批已通过
					applicationFlag = true;
				} else {
					// 勾选已经做了OA申请记录,并且不是审批通过状态
					applicationFlag = false;
				}
				intentionAndSupplierCodes.push(item.intentionCode + ":" + item.intentionSupplierCode);
			});
			if (!applicationFlag) {
				$.messager.alert('提示', '<center>请勾选快速引进申请单状态是"无"的记录！</center>');
				return;
			}
			var url = "fastImportApplication_showInsertApplicationFastImportForm_1.html?intentionAndSupplierCodes="
					+ intentionAndSupplierCodes;
			var tabName = '新增快速引进申请单';
			if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
				parent.activeTab(tabName);
				parent.refreshTab(tabName);
				return;
			}
			parent.addTabByUrl(tabName, 'agent', url);
		}
	},
	
	// 修改申请报告(快速调价)
	showEdit : function(applicationCode, intentionAndSupplierCodes, index) {
		var url = "fastImportApplication_showUpdateApplicationFastadjustpriceForm_1.html?applicationCode="
				+ applicationCode
				+ "&intentionAndSupplierCodes="
				+ intentionAndSupplierCodes;
		var tabName = '申请单' + applicationCode;
		if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
			parent.activeTab(tabName);
			parent.refreshTab(tabName);
			return;
		}
		parent.addTabByUrl(tabName, 'agent', url);
	},
	
	// 查询方法
	search : function() {
		var param = fastImportUtil.getFilterValue();
		$('#fastImportGrid').datagrid('load', param);
	},
	
	// 删除申请报告(快速调价)
	remove : function() {
		var fastImportGrid = $('#fastImportGrid').datagrid('getChecked');
		if (fastImportGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一个申请报告！</center>');
			return;
		} 
		
		var applicationFlag = true;//申请单状态、包装设计申请、巡厂申请是否正确
		var applicationCodes = [];//审批单号
		var intSup = [];//意向品编号+意向供应商编号
		var msg = "";
		$.each(fastImportGrid, function(index, item) {
			if (applicationFlag) {
				if (item.applicationStatus != "CG") {
					msg = "此记录不是草稿状态或已有巡厂申请或包装设计申请，不可删除!";
					applicationFlag = false;
				} else {
					if (
						(item.visitApplicationStatus=='W' && item.packageApplicationStatus=='W')
						||(item.visitApplicationStatus=='BH' && item.packageApplicationStatus=='BH')
					) {
						applicationCodes.push("'" + item.applicationCode + "'");
//						intSup.push("'" + item.intentionCode + "-" + item.intentionSupplierCode + "'");
					} else {
						applicationFlag = false;
						msg = "此记录不是草稿状态或已有巡厂申请或包装设计申请，不可删除!";
					}
				}
			}
		});
			
		if (!applicationFlag) {
			$.messager.alert('提示', msg);
			return;
		}
		utils.confirm("操作确认", "确认删除OA申请?", function() {
			$.post("fastImportApplication_completeDelFastImport_2.html", 
					{
						applicationCodes : applicationCodes
					}, function(data) {
						if(data.success){
							parent.messagerShow({title:'提示', msg:'删除成功!'});
							fastImportUtil.refresh();
						} else {
							$.messager.alert("提示", "此记录不是草稿状态或已有巡厂申请或包装设计申请，不可删除!");
//							$.messager.alert("提示", "部分申请单新品引进状态或巡厂申状态请或包装设计申请状态不符合删除条件：<br>"
//									+ data.rows);
							fastImportUtil.refresh();
						}
			}, "json");
		});
	},
	
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#fastImport_search').form('reset');
		clearComboboxOptions();// 在IE8下，选中中分类，点击查询。清空查询条件时，小分类下拉框有数据
		$('#fastImportGrid').datagrid('clearSelections');
		$('#fastImportGrid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		clearSearch = false;
	},
	
	refresh : function() {
		clearSearch = true;
		$('#fastImportGrid').datagrid('reload');
		clearSearch = false;
	},
	
	// 导出列表页申请记录
	exportAllApplication : function() {
		var param = $.param(fastImportUtil.getFilterValue());
		if (param.length < 1) {
			$.messager.alert('提示', '请输入至少一项查询条件!');
			return;
		}

		window.location = "fastImportApplication_exportFastImportToExcel_3.html?"
				+ $.param(fastImportUtil.getFilterValue());
		showRightDownMsg('文件导出提示', '文件正在导出中，请稍后！', 4000, 'slide');
	},

	// 新增创建OA快速引进申请
	createTbpmNew : function() {
		var reportNewGrid = $('#fastImportGrid').datagrid('getChecked');
		if (reportNewGrid.length != 1) {
			$.messager.alert('提示', '<center>请勾选一条快速引进申请单状态是"草稿"的记录!</center>');
		} else {
			var applicationCode="";
			var applicationStatus="";
			var url = "fastImportApplication_syncApplicationFastadjustprice_2.html";
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus != "CG") {
					$.messager.alert('提示', '<center>请确认勾选的记录均为"草稿"状态!</center>');
					return;
				}
				 applicationCode=item.applicationCode;
				 applicationStatus=item.applicationStatus;
				 //校验是否缺少必填文件和建议文件
				 $.ajax({
						type : "post",
						url : url,
						async : false,//同步
						data : {applicationCode : applicationCode},
						dataType: "json",
						success : function(data){
							if(data.success) {
								//fastImportUtil.refresh();
								//跳转到来伊份TBPM系统
								window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/newproductImport/快速引进申请表.caf?sco_no="+applicationCode);
								showRightDownMsg('提示', '创建OA快速引进申请成功！', 3000, 'slide');
							} else {
								//说明缺少文件
								if(data.msg == "缺少必填文件"){
									$.messager.alert('提示', data.rows);
								}else{
									/*var url="reportNew_showLackSuggestFileNew_1.html?applicationCode="+applicationCode;
									var dlg=utils.showDlg({
										title:"错误提示",href:url,width:1060,height:400,maximizable:true,
										buttons:[{text:'保存',handler:function(){fastImportFn.submitLackForm(dlg,applicationCode);},iconCls:'save'},
										        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
										]
									});*/
								}
							}
						}
					});
				 
				/*$.post(url+"?applicationCode="+applicationCode, 
						null, function(json){
					if(json.success){
						//fastImportUtil.refresh();
						//跳转到来伊份TBPM系统
						window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/newproductImport/快速引进申请表.caf?sco_no="+applicationCode);
						showRightDownMsg('提示', '创建OA快速引进申请成功！', 3000, 'slide');
					}else{
						//说明缺少文件
						if(json.msg == "缺少必填文件"){
							$.messager.alert('提示', json.rows);
						}else{
//							var url="reportNew_showLackSuggestFileNew_1.html?applicationCode="+applicationCode;
//							var dlg=utils.showDlg({
//								title:"错误提示",href:url,width:1060,height:400,maximizable:true,
//								buttons:[{text:'保存',handler:function(){fastImportFn.submitLackForm(dlg,applicationCode);},iconCls:'save'},
//								        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
//								]
//							});
						}
					}
				},"json");*/
			});
		}
	},
	
	// 同步OA申请
	syncApplication : function() {
		var fastImportGrid = $('#fastImportGrid').datagrid('getChecked');
		if (fastImportGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
		} else if (fastImportGrid.length > 1) {
			$.messager.alert('提示', '<center>只能选择一条记录！</center>');
		} else {
			var applicationCode = "";
			var applicationStatus = "";
			$.each(fastImportGrid,function(index, item) {
				if (item.applicationStatus != "CG") {
					$.messager.alert('提示', '<center请确认勾选的记录均为"草稿"状态！</center>');
					return;
				}
				applicationCode = item.applicationCode;
				applicationStatus = item.applicationStatus;
	
				utils.confirm("操作确认", "确认同步OA申请吗?",function() {
					$.post("fastAdjustprice_syncApplicationFastadjustprice_2.html?applicationCode="+ applicationCode,null,
						function(json) {
							if (json.success) {
								fastImportUtil.refresh();
							} else {
								// 说明缺少文件
								$.messager.alert('提示', '<center>缺少必填申请文件，不能OA同步!</center>');
								// utils.showDlg({title:'缺少申请报告商品错误提示',height:500,width:250,maximizable:true,resizable:true,content:json.rows},true);
							}
						},
					"json");
				});
			});
		}
	},

	submitLackForm :function(dlg,applicationCode){
		/*var url="reportNew_insertApplicationLackFileNew_2.html";
		utils.form("lackFileNew_form").submit(url, null, function() {
			dlg.dialog('close');
			//跳转到来伊份TBPM系统
			window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/newproductImport/快速引进申请表.caf?sco_no="+applicationCode);
			//reportNewGridUtil.refresh();//这是以前用于提交后，刷新更新状态之后的数据
			//$('#reportNewGrid').datagrid('reload');
		 });*/
	},
	
	// 撤销OA申请
	undoApplication : function() {
		var fastImportGrid = $('#fastImportGrid').datagrid('getChecked');
		if (fastImportGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
		} else {
			var applicationFlag = true;
			var applicationCodes = [];
			$.each(fastImportGrid, function(index, item) {
				if (item.applicationStatus != "YX") {
					// 判断是否勾选已经做了OA申请记录的意向品
					applicationFlag = false;
				} else {
					applicationCodes.push(item.applicationCode);
				}
			});
			if (!applicationFlag) {
				$.messager.alert('提示', '<center>只能撤销OA申请单状态是"允许OA同步"的记录！</center>');
				return;
			}
			utils.confirm("操作确认", "确认撤销允许OA同步?", function() {
				utils.post(
					"fastAdjustprice_undoApplicationFastadjustprice_2.html?applicationCodes="
							+ applicationCodes, null, function() {
						fastImportUtil.refresh();
				});
			});
		}
	},
	
	// 打开TBPM待办
	openTbpmDb : function() {
		var reportNewGrid = $('#fastImportGrid').datagrid('getChecked');
		if (reportNewGrid.length != 1) {
			$.messager.alert('提示', '<center>请勾选一条快速引进申请单状态是"驳回"的记录!</center>');
		} else {
			var applicationCode = "";
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus != "BH") {
					$.messager.alert('提示', '<center>请确认勾选的记录均为"驳回"状态!</center>');
					return;
				}
				applicationCode = item.applicationCode;
				 //打开TBPM系统的待办页面
				window.open("http://eip.laiyifen.com/tbpm/cordys/laiyifen/common/html/inbox/inbox.htm");
				}
			);
		}
	},
	
	// 新增创建OA巡厂申请
	createTbpmVisit : function() {
		var reportNewGrid = $('#fastImportGrid').datagrid('getChecked');
		if (reportNewGrid.length == 0) {
			$.messager.alert('提示', '<center>请勾选一条快速引进申请记录!</center>');
		}else {
			var applicationCode="";
			var merchandiseCode="";
			var supplierCode="";
			var codes="";
			var applicationFlag=true;
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus == 'W' || (item.visitApplicationStatus != 'W'
						&& item.visitApplicationStatus != 'BH')) {
					applicationFlag = false;
				}else{
					applicationCode = item.applicationCode;
					merchandiseCode = item.intentionCode;
					supplierCode = item.intentionSupplierCode;
					if(codes.length > 1) codes += ",";
					codes+=item.applicationCode + ":" + item.intentionCode
							+ ":" + item.intentionSupplierCode;
				}
			});
			
			if(!applicationFlag){
				$.messager.alert('提示', '请勾选快速引进申请单状态不是"无"且巡厂申请审批状态是"无"或"驳回"的记录!');
				return;
			}
			var url = "reportNew_createTbpmVisit_2.html";
			$.ajax({
				type : "post",
				url : url,
				async : false,//同步
				data : {codes : codes},
				dataType: "json",
				success : function(data){
					if(data.success) {
						var applicationVfCode = data.msg;//获取后台返回的巡厂申请单号
						fastImportUtil.refresh();//刷新列表页
						//新增创建TBPM巡厂申请
						window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/AccessSite/新品访厂申请表.caf?visit_no="+applicationVfCode);
						showRightDownMsg('提示', '创建OA巡厂申请成功！', 3000, 'slide');
					} 
				}
			});
			
			/*$.post("?codes="+codes, null, function(json){
				if(json.success){
					var applicationVfCode=json.msg;//获取后台返回的巡厂申请单号
					fastImportUtil.refresh();//刷新列表页
					//新增创建TBPM巡厂申请
					window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/AccessSite/新品访厂申请表.caf?visit_no="+applicationVfCode);
					showRightDownMsg('提示', '创建OA巡厂申请成功！', 3000, 'slide');
				}
			},"json");*/
		}
	},
	
	// 新增创建OA包装进度申请
	createTbpmPackage : function() {
		var reportNewGrid = $('#fastImportGrid').datagrid('getChecked');
//		console.info(reportNewGrid);
		if (reportNewGrid.length != 1) {
			$.messager.alert('提示', '<center>请勾选一条新品引进申请记录!</center>');
		} else {
			var url = "reportNew_createTbpmPackage_2.html";
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus == 'W' || (item.packageApplicationStatus!='W' && item.packageApplicationStatus!='BH')) {
					$.messager.alert('提示', '请勾选一条快速引进申请单状态不是"无"且包装设计申请审批状态是"无"或"驳回"的记录!');
					return;
				}
				 var applicationCode = item.applicationCode;
				 var merchandiseCode = item.intentionCode;
				 var merchandiseName = item.intentionName;
				 var supplierCode = item.intentionSupplierCode;
				 var supplierName = item.intentionSupplierName;
				 
				 $.ajax({
					type : "post",
					url : url,
					async : false,//同步
					data : {applicationCode : applicationCode, merchandiseCode : merchandiseCode, supplierCode : supplierCode},
					dataType: "json",
					success : function(jason){
						if(jason.success) {
							var mq = jason.rows;
							 fastImportUtil.refresh();//刷新列表页
							 //新增创建TBPM包装进度申请
							 window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/design/包装设计申请.caf?"
									 +"applicationPdCode="+jason.msg+"&merchandiseCode="+merchandiseCode+"&merchandiseName="+encodeURIComponent(merchandiseName)
									 +"&supplierCode="+supplierCode+"&supplierName="+supplierName+"&companySite="+mq.companySite
									 +"&contacts="+mq.contactsName+"&contactsPhone="+mq.contactsPhone);
							 showRightDownMsg('提示', '创建OA包装进申请成功！', 3000, 'slide');
						}
					}
				});
				 
				 /*$.post(url+"?applicationCode="+applicationCode+"&merchandiseCode="+merchandiseCode+"&supplierCode="+supplierCode, null, function(json){
					 var mq = json.rows;
					 fastImportUtil.refresh();//刷新列表页
					 //新增创建TBPM包装进度申请
					 window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/design/包装设计申请.caf?"
							 +"applicationPdCode="+json.msg+"&merchandiseCode="+merchandiseCode+"&merchandiseName="+merchandiseName
							 +"&supplierCode="+supplierCode+"&supplierName="+supplierName+"&companySite="+mq.companySite
							 +"&contacts="+mq.contactsName+"&contactsPhone="+mq.contactsPhone);
					 showRightDownMsg('提示', '创建OA包装进申请成功！', 3000, 'slide');
				},"json");*/
				
			});
		}
	},
	
	// 查看OA审批意见
	lookApproveOpinion : function() {
		var fastImportGrid = $('#fastImportGrid').datagrid('getChecked');
		if (fastImportGrid.length != 1) {
			$.messager.alert('提示', '<center>请只选择一条申请记录！</center>');
		} else {
			var applicationCode = "";// 申请单号
			var applicationStatus = "";// 申请状态
			var oaApplicationCode = "";// OA系统是申请单号
			var oaContacts = "";// OA系统的联系人
			$.each(fastImportGrid, function(index, item) {
				applicationCode = item.applicationCode;
				oaApplicationCode = item.oaApplicationCode;
				oaContacts = item.oaContacts;
				applicationStatus = item.applicationStatus;
			});

			// 需要先判断是否做了OA申请，然后判断OA系统的信息是否为空
			if (applicationCode == null || applicationCode == "" || applicationStatus == "CG") {
				$.messager.alert('提示', '请选择已经做了OA申请且申请状态不为草稿的记录！');
			} else {
//				if (oaApplicationCode == null || oaApplicationCode == "" || oaContacts == null || oaContacts == "") {
//					$.messager.alert('提示', '<center>没有OA系统同步数据，不能查看OA审批意见!</center>');
//				} else {
					var url = "http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/"
						+ "newproduct/common/approvalHistoryPageToSco.caf?formId="+oaApplicationCode;
					window.open(url);
//				}
			}
		}
	}

};