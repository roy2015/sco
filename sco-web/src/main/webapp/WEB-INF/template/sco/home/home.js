var scheduleGridUtil = null;
var elseRemindGridUtil = null;
$(document).ready(function(){
	//初始化待办事项datagrid
	scheduleGridUtil=utils.grid($('#gtasksRemind_grid'));
	scheduleGridUtil.initFilters({
		noParamCanSort:true,
		onLoadSuccess:function(data){
			var total = $.parseJSON(JSON.stringify(data))["total"];
			$("#gtasksRemindTool").find("font").text(total);
		}
	});
	//初始化其他提醒datagrid
	elseRemindGridUtil=utils.grid($('#elseRemind_grid'));
	elseRemindGridUtil.initFilters({
		noParamCanSort:true,
		onLoadSuccess:function(){
			
		}
	});
	
	$("#elseRemindType").combobox({
		onSelect:function(record){
			$('#elseRemind_grid').datagrid('load', {'remindType':record.id});
		}
	})
});

var homeFn = {
	// 查看申请信息详情
	showEdit : function(applicationCode,applicationType) {
		var url = '';
		switch (applicationType) {
		case 'MERCHANDISE_NEW':
			url = "reportNew_showUpdateApplicationReportNewForm_1.html?applicationCode=" + applicationCode;
			break;
		case 'MERCHANDISE_OLDUP':
			url = "reportOldup_showUpdateApplicationReportOldupForm_1.html?applicationCode=" + applicationCode;
			break;
		case 'MERCHANDISE_ADJUSTPRICE':
			url = "reportAdjustprice_showUpdateApplicationReportAdjustpriceForm_1.html?applicationCode=" + applicationCode;
			break;
		case 'MERCHANDISE_FASTADJUSTPRICE':
			url = "fastAdjustprice_showUpdateApplicationFastadjustpriceForm_1.html?applicationCode=" + applicationCode;
			break;
		case 'ACCESSORY_CGWYHJJD':
			url = 'committeeApply_addCommitteeApply_1.html?quotedCodes=&applicationCode=' + applicationCode;
			break;
		case 'ACCESSORY_FSPJJD':
			url = 'nonFoodApply_addNonFoodApply_1.html?quotedCodes=&applicationCode=' + applicationCode;
			break;
		case 'ACCESSORY_XJBJ':
			url = 'compareApply_addCompareApply_1.html?quotedCodes=&applicationCode=' + applicationCode;
			break;
		case 'MERCHANDISE_FASTIMPORT':
			url = 'fastImportApplication_showUpdateApplicationFastadjustpriceForm_1.html?quotedCodes=&applicationCode=' + applicationCode;
			break;
		default:
			break;
		}
		var tabName = '申请单' + applicationCode;
		if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
			parent.activeTab(tabName);
			parent.refreshTab(tabName);
			return;
		}
		parent.addTabByUrl(tabName, 'agent', url);
	},
	notRemind : function(){
		var remindCodes = [];
		function elseRemindFlag(remindCode,remindType,qlCode,configCode){
			var obj = new Object();
			obj.remindCode = remindCode;
			obj.remindType = remindType;
			obj.qlCode = qlCode;
			obj.configCode = configCode;
			return obj;
		}
		var checkedRows = $('#elseRemind_grid').datagrid('getChecked');
		if (checkedRows.length == 0) {
			$.messager.alert("提示", "请勾选至少一条记录");
			return;
		}
		$.each(checkedRows, function(index, item){
			remindCodes.push(new elseRemindFlag(item.remindCode,item.remindType,item.qlCode,item.configCode));
		})
		//转换数组
		var data =JSON.stringify(remindCodes).replace(/"([^"]*)"/g, "'$1'");
		$.post("home_addElseRemindFlag_2.html",{reminds:data}, function(data){
			$('#elseRemind_grid').datagrid('load');
			$('#elseRemind_grid').datagrid('unselectAll');  
		});
	},
	myReport:function(){
		pubTab.showTab("我的报表","excel","reports_showReportGrid_1.html");
	},
	merchandiseIntention:function(){
		pubTab.showTab("商品意向品管理","privilege","merchandiseIntention_showMerchandiseIntentionGrid_1.html");
	},
	merchandiseCostAnalysis:function(){
		pubTab.showTab("商品总成本类比分析","copy","totalCostAnalogyAnalysis_showTotalCostAnalogyAnalysisGrid_1.html");
	},
	sellDetail:function(){
		pubTab.showTab("销售明细","copy","sellDetail_showSellDetailGrid_1.html");
	},
	webSiteMaterial:function(){
		pubTab.showTab("公示网站原料历史行情","timer","websiteHistory_showWebsiteHistoryMain_1.html");
	}
};