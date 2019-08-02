var sellYearOnYearUtil = null;
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
$(document).ready(function() {
			sellYearOnYearUtil = utils.grid($('#sellYearOnYear_Grid'));
			// 注册查询框
			sellYearOnYearUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName",
					 "dxRoleCode", "dlRoleCode","centreTypeCode", "smallTypeCode", 
					 "detailTypeCode", "fineTypeCode","purchaseDepartments");
			sellYearOnYearUtil.initFilters({
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
						var param = sellYearOnYearUtil.getFilterValue();
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
var sellYearOnYearFn = {
	// 查询
	search : function() {
		var param = sellYearOnYearUtil.getFilterValue();
		var rationed = $("input[name='rationed']:checked").val();
		param.rationed=rationed;
		
		$('#sellYearOnYear_Grid').datagrid('load', param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#sellYearOnYear_search').form('reset');
		clearComboboxOptions();//在IE8下，选中中分类，点击查询。清空查询条件时，小分类下拉框有数据
		$('#sellYearOnYear_Grid').datagrid('clearSelections');
		$('#sellYearOnYear_Grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		clearSearch = false;
	},
	refresh : function() {
		clearSearch = true;
		$('#sellYearOnYear_Grid').datagrid('reload');
		clearSearch = false;
	},
	//查看同比分析
	sellDetailReport : function() {
		var sellYearOnYearGrid = $('#sellYearOnYear_Grid').datagrid('getChecked');
		/*if (sellYearOnYearGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条商品记录！</center>');
		} else {*/
			var sellRegion=$("#sellRegion").combobox('getValue');
			var startDate = $("#minDate").datebox('getValue');
			var endDate = $("#maxDate").datebox('getValue');
			var directJoin =  $("input[name='directJoin']:checked").val();//直营或加盟
			var rationed =  $("input[name='rationed']:checked").val();//公斤装或定量装
			var seeYear = $("#seeYear").val();//查看年数
			//区域是否必填校验
			if (sellRegion == "") {
				$.messager.alert('提示', '<center>请输入必选条件地区！</center>');
				return;
			}
			// 日起范围的合法性校验
			if (startDate == "" || endDate == "") {
				$.messager.alert('提示', '<center>请输入必选条件时间范围！</center>');
				return;
			}
			if (startDate > endDate) {
				$.messager.alert('提示', '<center>结束时间不能早于开始时间！</center>');
				return;
			}
			if (startDate.substring(0, 4) != endDate.substring(0, 4)) {
				$.messager.alert('提示', '<center>开始日期与结束日期的年份必须相同!</center>');
				return;
			}
			if (seeYear != null && seeYear != "" && seeYear > 3) {
				$.messager.alert('提示', '<center>最多只可查看3年同比数据!</center>');
				return;
			}
			
			var  merchandiseCodes=[];
			var  merchandiseAndSupplierCodes=[];
			$.each(sellYearOnYearGrid, function(index, item){
				merchandiseCodes.push("'"+item.merchandiseCode+"'");
				//根据商品编号和供应商编号查询同比信息
				merchandiseAndSupplierCodes.push(item.merchandiseCode+":"+item.supplierCode);
			});
			var url = "";
			//查看同比类型
			var analysisType = $("#analysisType").combobox("getValue");
			var tabName = "";
			if("product"==analysisType){
				if (sellYearOnYearGrid.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条商品记录！</center>');
					return ;
				} 
				//单品
				tabName="单品同比结果";
				url = "sellYearOnYear_showProductSellYearOnYearGrid_1.html?"+
				"sellRegion="+sellRegion+
				"&startDate=" + startDate + 
				"&endDate=" + endDate + 
				"&directJoin=" + directJoin+
				"&merchandiseCodes=" + merchandiseCodes+
				"&merchandiseAndSupplierCodes=" + merchandiseAndSupplierCodes+
				"&rationed="+rationed+
				"&seeYear="+seeYear;
			}else if("detail"==analysisType){
				if (sellYearOnYearGrid.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条商品记录！</center>');
					return ;
				} 
				//明细类
				tabName="明细类同比结果";
				url = "detailSellYearOnYear_showDetailSellYearOnYearGrid_1.html?"+
				"sellRegion="+sellRegion+
				"&startDate=" + startDate + 
				"&endDate=" + endDate + 
				"&directJoin=" + directJoin+
				"&merchandiseCodes=" + merchandiseCodes+
				"&merchandiseAndSupplierCodes=" + merchandiseAndSupplierCodes+
				"&rationed="+rationed+
				"&seeYear="+seeYear;
			}else if("small"==analysisType){
				if (sellYearOnYearGrid.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条商品记录！</center>');
					return;
				} 
				//小分类
				tabName="小分类同比结果";
				url = "smallSellYearOnYear_showSmallSellYearOnYearGrid_1.html?"+
				"sellRegion="+sellRegion+
				"&startDate=" + startDate + 
				"&endDate=" + endDate + 
				"&directJoin=" + directJoin+
				"&merchandiseCodes=" + merchandiseCodes+
				"&merchandiseAndSupplierCodes=" + merchandiseAndSupplierCodes+
				"&rationed="+rationed+
				"&seeYear="+seeYear;
			}else if("big"==analysisType){
				//整体
				tabName="整体同比结果";
				url = "marketSellYearOnYear_showMarketSellYearOnYearGrid_1.html?"+
				"sellRegion="+sellRegion+
				"&startDate=" + startDate + 
				"&endDate=" + endDate + 
				"&directJoin=" + directJoin+
				"&merchandiseCodes=" + merchandiseCodes+
				"&merchandiseAndSupplierCodes=" + merchandiseAndSupplierCodes+
				"&rationed="+rationed+
				"&seeYear="+seeYear;
			}
			
			if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
				parent.activeTab(tabName);
				parent.refreshTab(tabName);
				return;
			}
			parent.addTabByUrl(tabName, 'agent', url);
			
		//}
	},
};