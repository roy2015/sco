var merchandisePromotionAnalysisGridUtil = null;
var firstIn=true;
$(function() {
	merchandisePromotionAnalysisGridUtil = utils.grid($('#merchandisePromotionAnalysis_grid'));
	merchandisePromotionAnalysisGridUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", "dxRoleCode", "dlRoleCode", "centreTypeCode", "smallTypeCode",
			"detailTypeCode", "fineTypeCode","purchaseDepartments","sellRegion","minDate","maxDate");
	merchandisePromotionAnalysisGridUtil.initFilters({
		onBeforeLoad : function(obj) {
			// 清空查询
			if (firstIn) {
				return false;
			}
		}
	});
});
var merchandisePromotionAnalysisFn = {
	search : function() {
		firstIn=false;
		var sn = $.trim($("#supplierName").val());
		var sc = $.trim($("#supplierCode").val());
		var mn = $.trim($("#merchandiseName").val());
		var mc = $.trim($("#merchandiseCode").val());
		if (sn == "") {
			$("#supplierName").val("");
		}
		if (sc == "") {
			$("#supplierCode").val("");
		}
		if (mn == "") {
			$("#merchandiseName").val("");
		}
		if (mc == "") {
			$("#merchandiseCode").val("");
		}
		var param = merchandisePromotionAnalysisGridUtil.getFilterValue();
		if (param.sellRegion == null || param.minDate==null || param.maxDate== null) {
			$.messager.alert('操作失败', "必选项未选择");
			return;
		}
		$('#merchandisePromotionAnalysis_grid').datagrid('load', param);
	},
	searchMerchandise : function() {
		var row = $('#merchandisePromotionAnalysis_grid').datagrid("getSelections");
		if (row.length != 1) {
			$.messager.alert("提示", "请只选择一个商品");
			return;
		}
		var merchandiseCode = row[0].merchandiseCode;
		var supplierCode = row[0].supplierCode;
		var sellRegion = $("#sellRegion").combobox("getValue");
		var regionName = $("#sellRegion").combobox("getText");
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox("getValue");
		var show = $('input[name="show"]:checked');
		if (sellRegion != "" && minDate != "") {
			var url = "merchandisePromotionAnalysis_showMerchandisePromotionAnalysisDetailGrid_1.html?" + "merchandiseCode=" + merchandiseCode + "&supplierCode=" + supplierCode + "&sellRegion="
					+ sellRegion + "&minDate=" + minDate + "&maxDate=" + maxDate + "&searchTable=" + show[0].value+"&regionName="+regionName;
			var tabName = '商品促销对比分析';
			if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
				parent.activeTab(tabName);
				parent.refreshTab(tabName);
				return;
			}
			parent.addTabByUrl(tabName, 'agent', url);
		} else {
			$.messager.alert("提示", "请选择必须搜索项");
		}

	},
	clear : function() {
		firstIn=true;
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		$("#merchandisePromotionAnalysis_search").form('reset');
		clearComboboxOptions();
		$("#merchandisePromotionAnalysis_grid").datagrid('loadData', {
			total : 0,
			rows : []
		});
	}
};