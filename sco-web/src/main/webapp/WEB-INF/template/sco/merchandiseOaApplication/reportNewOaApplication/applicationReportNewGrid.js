var reportNewGridUtil = null;
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
$(document).ready(
	function() {
		reportNewGridUtil = utils.grid($('#reportNewGrid'));
		// 注册查询框
		reportNewGridUtil.registerExtFilters("applicationCode", "intentionSupplierCode", "intentionSupplierName",
				"intentionCode", "intentionName", "createDateStart", "createDateEnd", "centreTypeCodeElse",
				"smallTypeCode", "detailTypeCode", "fineTypeCode", "purchaseDepartments", "rationed",
				"purchaseType", "saleType", "dxRoleCode", "dlRoleCode", "applicationStatus",
				"applicationDateStart", "applicationDateEnd","visitApplicationStatus","visitDateStart","visitDateEnd",
				"packageApplicationStatus","packageDateStart","packageDateEnd");
		reportNewGridUtil.initFilters({
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
					var param = reportNewGridUtil.getFilterValue();
					if ($.param(param).length > 0) {
						return true;
					}
					$.messager.alert("提示", "请输入至少一项查询条件");
					return false;
				}
			},
			onLoadSuccess : function() {
				firstIn = false;
				// $('#reportNewGrid').datagrid("clearSelections");
			}
		});
	}
);

var reportNewFn = {
	
	// 添加申请报告(新品引进)
	showInsert : function() {
		var reportNewGrid = $('#reportNewGrid').datagrid('getChecked');
		if (reportNewGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
		} else {
			var intentionAndSupplierCodes = [];
			var applicationFlag = true;
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus != null && item.applicationStatus != "" && item.applicationStatus != "W") {
					// 判断是否勾选已经做了OA申请记录的意向品
					applicationFlag = false;
				}
				intentionAndSupplierCodes.push(item.intentionCode + ":" + item.intentionSupplierCode);
			});
			if (!applicationFlag) {
				$.messager.alert('提示', '<center>请勾选新品引进申请单状态是"无"的记录！</center>');
				return;
			}
			var url = "reportNew_showInsertApplicationReportNewForm_1.html?intentionAndSupplierCodes="
					+ intentionAndSupplierCodes;
			var tabName = '新增商品新品引进申请单';
			if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
				parent.activeTab(tabName);
				parent.refreshTab(tabName);
				return;
			}
			parent.addTabByUrl(tabName, 'agent', url);
		}
	},
	
	// 修改申请报告(新品引进)
	showEdit : function(applicationCode,index) {
		var url = "reportNew_showUpdateApplicationReportNewForm_1.html?applicationCode=" + applicationCode;
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
		var param = reportNewGridUtil.getFilterValue();

		var createDateStart = $("#createDateStart").datebox("getValue");
		var createDateEnd = $("#createDateEnd").datebox("getValue");
		// 创建时间的合法性校验
		if (createDateStart == "" && createDateEnd != "") {
			$.messager.alert('提示', '<center>请输入意向品开始创建时间！</center>');
			return;
		}
		if (createDateEnd == "" && createDateStart != "") {
			$.messager.alert('提示', '<center>请输入意向品结束创建时间！</center>');
			return;
		}
		if (createDateStart > createDateEnd) {
			$.messager.alert('提示', '<center>结束创建时间不能早于开始创建时间！</center>');
			return;
		}

		var applicationDateStart = $("#applicationDateStart").datebox("getValue");
		var applicationDateEnd = $("#applicationDateEnd").datebox("getValue");
		// 申请单提交日期的合法性校验
		if (applicationDateStart == "" && applicationDateEnd != "") {
			$.messager.alert('提示', '<center>请输入新品引进申请单开始时间！</center>');
			return;
		}
		if (applicationDateEnd == "" && applicationDateStart != "") {
			$.messager.alert('提示', '<center>请输入新品引进申请单结束时间！</center>');
			return;
		}
		if (applicationDateStart > applicationDateEnd) {
			$.messager.alert('提示', '<center>新品引进申请单结束时间不能早于新品引进申请单开始时间！</center>');
			return;
		}
		
		var visitDateStart = $("#visitDateStart").datebox("getValue");
		var visitDateEnd = $("#visitDateEnd").datebox("getValue");
		// 巡厂申请单提交日期的合法性校验
		if (visitDateStart == "" && visitDateEnd != "") {
			$.messager.alert('提示', '<center>请输入巡厂申请单开始时间！</center>');
			return;
		}
		if (visitDateEnd == "" && visitDateStart != "") {
			$.messager.alert('提示', '<center>请输入巡厂申请单结束时间！</center>');
			return;
		}
		if (visitDateStart > visitDateEnd) {
			$.messager.alert('提示', '<center>巡厂申请单结束时间不能早于巡厂申请单开始时间！</center>');
			return;
		}
		
		var packageDateStart = $("#packageDateStart").datebox("getValue");
		var packageDateEnd = $("#packageDateEnd").datebox("getValue");
		// 包装设计申请提交日期的合法性校验
		if (packageDateStart == "" && packageDateEnd != "") {
			$.messager.alert('提示', '<center>请输入包装设计申请单开始时间！</center>');
			return;
		}
		if (packageDateEnd == "" && packageDateStart != "") {
			$.messager.alert('提示', '<center>请输入包装设计申请单结束时间！</center>');
			return;
		}
		if (packageDateStart > packageDateEnd) {
			$.messager.alert('提示', '<center>包装设计申请单结束时间不能早于包装设计申请单开始时间！</center>');
			return;
		}

		$('#reportNewGrid').datagrid('load', param);
	},
	
	// 删除申请报告(新品引进)
	remove : function() {
		var reportNewGrid = $('#reportNewGrid').datagrid('getChecked');
		if (reportNewGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条OA申请状态是"草稿"的记录！</center>');
		} else {
			var applicationFlag = true;
			var applicationCodes = [];
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus == "CG" && ((item.visitApplicationStatus=='W' && item.packageApplicationStatus=='W')||(item.visitApplicationStatus=='BH' && item.packageApplicationStatus=='BH'))) {
					applicationCodes.push("'" + item.applicationCode + "'");
				} else {
					// 判断是否勾选已经做了OA申请记录的意向品
					applicationFlag = false;
				}
			});
			if (!applicationFlag) {
				$.messager.alert('提示', '此记录不是草稿状态或已有巡厂申请或包装设计申请，不可删除!');
				return;
			}
			utils.confirm("操作确认", "确认删除OA申请?", function() {
				utils.post("reportNew_deleteApplicationReportNew_2.html?applicationCodes=" + applicationCodes, null,
					function() {
						reportNewGridUtil.refresh();
				});
			});
		}
	},
	
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#applicationReportNew_search').form('reset');
		clearComboboxOptions();//在IE8下，选中中分类，点击查询。清空查询条件时，小分类下拉框有数据
		$('#reportNewGrid').datagrid('clearSelections');
		$('#reportNewGrid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		clearSearch = false;
	},
	
	refresh : function() {
		clearSearch = true;
		$('#reportNewGrid').datagrid('reload');
		clearSearch = false;
	},
	
	// 导出列表页申请记录
	exportAllApplication : function() {
		var param = $.param(reportNewGridUtil.getFilterValue());
		if (param.length < 1) {
			$.messager.alert('提示', '请输入至少一项查询条件!');
			return;
		}
		param += '&exportNew=exportNew';//新品引进导出数据时:该OA申请单创建人
		
		window.location = "reportNew_exportReportNewApplicationToExcel_3.html?"
				+ param;
		showRightDownMsg('文件导出提示', '文件正在导出中，请稍后！', 4000, 'slide');
	},
	
	// 新增创建TBPM新品引进申请
	createTbpmNew : function() {
		var reportNewGrid = $('#reportNewGrid').datagrid('getChecked');
		if (reportNewGrid.length == 0) {
			$.messager.alert('提示', '<center>请勾选一条新品引进申请单状态是"草稿"的记录!</center>');
		}else if(reportNewGrid.length >1){
			$.messager.alert('提示', '<center>请只勾选一条申请记录!</center>');
		} else {
			var applicationCode="";
			var applicationStatus="";
			var url = "reportNew_syncApplicationNew_2.html";
			
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
					success : function(json){
						if(json.success){
							//reportNewGridUtil.refresh();
							//跳转到来伊份TBPM系统
							window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/newproductImport/新品引进申请表.caf?sco_no="+applicationCode);
							showRightDownMsg('提示', '创建OA新品引进申请成功！', 3000, 'slide');
						}else{
							//说明缺少文件
							if(json.msg=="缺少必填文件"){
								$.messager.alert('提示', '<center>申请单'+applicationCode+'缺少必填文件申请报告，不能新增创建OA新品引进申请!</center>');
							}else{
								var url="reportNew_showLackSuggestFileNew_1.html?applicationCode="+applicationCode;
								var dlg=utils.showDlg({
									title:"错误提示",href:url,width:1060,height:400,maximizable:true,
									buttons:[{text:'保存',handler:function(){reportNewFn.submitLackForm(dlg,applicationCode);},iconCls:'save'},
									        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
									]
								});
							}
						}
					}
				});
				
				
				/*$.post(url+"?applicationCode="+applicationCode, null, function(json){
					if(json.success){
						//reportNewGridUtil.refresh();
						//跳转到来伊份TBPM系统
						window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/newproductImport/新品引进申请表.caf?sco_no="+applicationCode);
						showRightDownMsg('提示', '创建OA新品引进申请成功！', 3000, 'slide');
					}else{
						//说明缺少文件
						if(json.msg=="缺少必填文件"){
							$.messager.alert('提示', '<center>申请单'+applicationCode+'缺少必填文件申请报告，不能新增创建OA新品引进申请!</center>');
						}else{
							var url="reportNew_showLackSuggestFileNew_1.html?applicationCode="+applicationCode;
							var dlg=utils.showDlg({
								title:"错误提示",href:url,width:1060,height:400,maximizable:true,
								buttons:[{text:'保存',handler:function(){reportNewFn.submitLackForm(dlg,applicationCode);},iconCls:'save'},
								        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
								]
							});
						}
					}
				},"json");*/
				
			});
		}
	},
	
	// 打开TBPM待办
	openTbpmDb : function() {
		var reportNewGrid = $('#reportNewGrid').datagrid('getChecked');
		if (reportNewGrid.length == 0) {
			$.messager.alert('提示', '<center>请勾选一条新品引进申请单状态是"驳回"的记录!</center>');
		}else if(reportNewGrid.length >1){
			$.messager.alert('提示', '<center>请只勾选一条申请记录!</center>');
		} else {
			var applicationCode="";
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus != "BH") {
					$.messager.alert('提示', '<center>请确认勾选的记录均为"驳回"状态!</center>');
					return;
				}
				 applicationCode=item.applicationCode;
				 //打开TBPM系统的待办页面
				 window.open("http://eip.laiyifen.com/tbpm/cordys/laiyifen/common/html/inbox/inbox.htm");
			});
		}
	},
	
	// 新增创建TBPM巡厂申请
	createTbpmVisit : function() {
		var reportNewGrid = $('#reportNewGrid').datagrid('getChecked');
		if (reportNewGrid.length == 0) {
			$.messager.alert('提示', '<center>请勾选一条新品引进申请记录!</center>');
		}else {
			var applicationCode = "";
			var merchandiseCode = "";
			var supplierCode = "";
			var codes = "";
			var applicationFlag = true;
			var url = "reportNew_createTbpmVisit_2.html";
			
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus == 'W' || (item.visitApplicationStatus!='W' && item.visitApplicationStatus!='BH')) {
					applicationFlag = false;
				}else{
					applicationCode = item.applicationCode;
					merchandiseCode = item.intentionCode;
					supplierCode = item.intentionSupplierCode;
					if(codes.length > 1) codes += ",";
					codes+=item.applicationCode+":"+item.intentionCode+":"+item.intentionSupplierCode;
				}
			});
			
			if(!applicationFlag){
				$.messager.alert('提示', '请勾选新品引进申请单状态不是"无"且巡厂申请审批状态是"无"或"驳回"的记录!');
				return;
			}
			
			$.ajax({
				type : "post",
				url : url,
				async : false,//同步
				data : {codes : codes},
				dataType: "json",
				success : function(json){
					if(json.success){
						var applicationVfCode = json.msg;//获取后台返回的巡厂申请单号
						reportNewGridUtil.refresh();//刷新列表页
						//新增创建TBPM巡厂申请
						window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/AccessSite/新品访厂申请表.caf?visit_no="+applicationVfCode);
						showRightDownMsg('提示', '创建OA巡厂申请成功！', 3000, 'slide');
					}
				}
			});
			
			/*$.post(url+"?codes="+codes, null, function(json){
				if(json.success){
					var applicationVfCode = json.msg;//获取后台返回的巡厂申请单号
					reportNewGridUtil.refresh();//刷新列表页
					//新增创建TBPM巡厂申请
					window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/AccessSite/新品访厂申请表.caf?visit_no="+applicationVfCode);
					showRightDownMsg('提示', '创建OA巡厂申请成功！', 3000, 'slide');
				}
			},"json");*/
		}
	},
	
	// 新增创建TBPM包装进度申请
	createTbpmPackage : function() {
		var reportNewGrid = $('#reportNewGrid').datagrid('getChecked');
		if (reportNewGrid.length == 0) {
			$.messager.alert('提示', '<center>请勾选一条新品引进申请记录!</center>');
		}else if(reportNewGrid.length >1){
			$.messager.alert('提示', '<center>请只勾选一条新品引进申请记录!</center>');
		} else {
			var url = "reportNew_createTbpmPackage_2.html";
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus == 'W' || (item.packageApplicationStatus!='W' && item.packageApplicationStatus!='BH')) {
					$.messager.alert('提示', '请勾选一条新品引进申请单状态不是"无"且包装设计申请审批状态是"无"或"驳回"的记录!');
					return;
				}
				 var applicationCode=item.applicationCode;
				 var merchandiseCode=item.intentionCode;
				 var merchandiseName = item.intentionName;
				 var supplierCode = item.intentionSupplierCode;
				 var supplierName = item.intentionSupplierName;
				 
				 $.ajax({
						type : "post",
						url : url,
						async : false,//同步
						data : {applicationCode : applicationCode, merchandiseCode : merchandiseCode, supplierCode : supplierCode},
						dataType: "json",
						success : function(json){
							if(json.success) {
								var mq = json.rows;
								 reportNewGridUtil.refresh();//刷新列表页
								 //新增创建TBPM包装进度申请
								 window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/design/包装设计申请.caf?"
										 +"applicationPdCode="+json.msg+"&merchandiseCode="+merchandiseCode+"&merchandiseName="+encodeURIComponent(merchandiseName)
										 +"&supplierCode="+supplierCode+"&supplierName="+supplierName+"&companySite="+mq.companySite
										 +"&contacts="+mq.contactsName+"&contactsPhone="+mq.contactsPhone);
								 showRightDownMsg('提示', '创建OA包装进度申请成功！', 3000, 'slide');
							}
						}
					});
				 
				 /*$.post(rul+"?applicationCode="+applicationCode+"&merchandiseCode="+merchandiseCode+"&supplierCode="+supplierCode, null, function(json){
					 var mq = json.rows;
					 reportNewGridUtil.refresh();//刷新列表页
					 //新增创建TBPM包装进度申请
					 window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/design/包装设计申请.caf?"
							 +"applicationPdCode="+json.msg+"&merchandiseCode="+merchandiseCode+"&merchandiseName="+merchandiseName
							 +"&supplierCode="+supplierCode+"&supplierName="+supplierName+"&companySite="+mq.companySite
							 +"&contacts="+mq.contactsName+"&contactsPhone="+mq.contactsPhone);
					 showRightDownMsg('提示', '创建OA包装进度申请成功！', 3000, 'slide');
				 },"json");*/
				 
			});
		}
	},
	
	// 同步OA申请
	syncApplication : function() {
		var reportNewGrid = $('#reportNewGrid').datagrid('getChecked');
		if (reportNewGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
		} else if(reportNewGrid.length >1){
			$.messager.alert('提示', '<center>只能选择一条记录！</center>');
		}else{
			var applicationCode="";
			var applicationStatus="";
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus != "CG") {
					$.messager.alert('提示', '<center>请确认勾选的记录均为"草稿"状态！</center>');
					return;
				}
				 applicationCode=item.applicationCode;
				 applicationStatus=item.applicationStatus;
				 
				 utils.confirm("操作确认","确认同步OA申请吗?",function(){
						/*utils.post("reportNew_syncApplicationNew_1.html?applicationCode="+applicationCode,null,function(){
							//intentionUtil.refresh();
							$('#reportNewGrid').datagrid('reload');
						});*/
						//console.dir(applicationCode);
						$.post("reportNew_syncApplicationNew_2.html?applicationCode="+applicationCode, null, function(json){
							if(json.success){
								//$('#reportNewGrid').datagrid('reload');
								reportNewGridUtil.refresh();
							}else{
								//说明缺少文件
								if(json.msg=="缺少必填文件"){
									$.messager.alert('提示', '<center>缺少必填文件申请报告，不能OA同步!</center>');
									//utils.showDlg({title:'缺少申请报告商品错误提示',height:500,width:250,maximizable:true,resizable:true,content:json.rows},true);
								}else{
									var url="reportNew_showLackSuggestFileNew_1.html?applicationCode="+applicationCode;
									//parent.addTabByUrl("333", 'agent', url);
									var dlg=utils.showDlg({
										title:"错误提示",href:url,width:1060,height:550,
										buttons:[{text:'保存',handler:function(){reportNewFn.submitLackForm(dlg);},iconCls:'save'},
										        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
										]
									});
								}
							}
						},"json");
					});
			});
		}
	},
	
	submitLackForm :function(dlg, applicationCode){
		if (!$("#lackFileNew_form").form('validate')) {
			return;
		}
		var url="reportNew_insertApplicationLackFileNew_2.html";
		var param = getFormJson("#lackFileNew_form");
		$.ajax({
			type : "post",
			url : url,
			async : false,//同步
			data : param,
			dataType: "json",
			success : function(data){
				if(data.success) {
					window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/newproductImport/新品引进申请表.caf?sco_no="+applicationCode);
					//reportNewGridUtil.refresh();//这是以前用于提交后，刷新更新状态之后的数据
					//$('#reportNewGrid').datagrid('reload');
				}
			}
		});
		
		/*utils.form("lackFileNew_form").submit(url, null, function() {
			dlg.dialog('close');
			//跳转到来伊份TBPM系统
			window.open("http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/newproduct/newproductImport/新品引进申请表.caf?sco_no="+applicationCode);
			//reportNewGridUtil.refresh();//这是以前用于提交后，刷新更新状态之后的数据
			//$('#reportNewGrid').datagrid('reload');
		 });*/
	},
	
	// 撤销OA申请
	undoApplication : function() {
		var reportNewGrid = $('#reportNewGrid').datagrid('getChecked');
		if (reportNewGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
		}else{
			var applicationFlag = true;
			var applicationCodes = [];
			$.each(reportNewGrid, function(index, item) {
				if (item.applicationStatus != "YX") {
					// 判断是否勾选已经做了OA申请记录的意向品
					applicationFlag = false;
				} else {
					applicationCodes.push(item.applicationCode);
				}
			});
			if (!applicationFlag) {
				$.messager.alert('提示', '<center>只能撤销OA引进申请单状态是"允许OA同步"的记录！</center>');
				return;
			}
			utils.confirm("操作确认", "确认撤销允许OA同步?", function() {
				utils.post("reportNew_undoApplicationNew_2.html?applicationCodes=" + applicationCodes, null,
					function() {
						reportNewGridUtil.refresh();
				});
			});
		}
	},
	
	//查看OA审批意见
	lookApproveOpinion:function(){
		var reportNewGrid = $('#reportNewGrid').datagrid('getChecked');
		if (reportNewGrid.length == 0) {
			$.messager.alert('提示', '<center>请选择一条记录！</center>');
		} else if(reportNewGrid.length >1){
			$.messager.alert('提示', '<center>请只选择一条申请记录！</center>');
		}else{
			var applicationCode = "";// 申请单号
			var applicationStatus = "";// 申请状态
			var oaApplicationCode = "";// OA系统是申请单号
			var oaContacts = "";// OA系统的联系人
			$.each(reportNewGrid, function(index, item) {
				applicationCode = item.applicationCode;
				oaApplicationCode = item.oaApplicationCode;
				oaContacts = item.oaContacts;
				applicationStatus = item.applicationStatus;
			});
			//需要先判断是否做了OA申请，然后判断OA系统的信息是否为空
			if (applicationCode == null || applicationCode == "" || applicationStatus == "CG") {
				$.messager.alert('提示','请选择已经做了OA申请且申请状态不为草稿的记录！');
			} else {
//				if(oaApplicationCode==null||oaApplicationCode==""||oaContacts==null||oaContacts==""){
//					$.messager.alert('提示', '<center>没有OA系统同步数据，不能查看OA审批意见!</center>');
//				}else{
					var url = "http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/"
						+ "newproduct/common/approvalHistoryPageToSco.caf?formId="+oaApplicationCode;
					window.open(url);
//				}
			}
		}
	}
	
};