var merchandisePromotionBuyPriceAdjustGridUtil = null;
var firstIn = true;// 是否第一次进入页面
$(function() {
	merchandisePromotionBuyPriceAdjustGridUtil = utils.grid($('#merchandisePromotionBuyPriceAdjust_grid'));
	merchandisePromotionBuyPriceAdjustGridUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", "dxRoleCode", "dlRoleCode", "centreTypeCode", "smallTypeCode",
			"detailTypeCode", "fineTypeCode","warehouseCode","minDate","maxDate");
	merchandisePromotionBuyPriceAdjustGridUtil.initFilters({
		onBeforeLoad : function(obj) {
			// 清空查询
			if (firstIn) {
				return false;
			}
		}
	});
});
var merchandisePromotionBuyPriceAdjustFn = {
	search : function() {
		firstIn=false;
		var DL = $('input[name="show"]:checked');
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
		var param = merchandisePromotionBuyPriceAdjustGridUtil.getFilterValue();
		param.show = DL[0].value;
		if (param.warehouseCode == null || param.minDate==null || param.maxDate== null) {
			$.messager.alert('操作失败', "必选项未选择");
			return;
		}
		$('#merchandisePromotionBuyPriceAdjust_grid').datagrid('load', param);
	},
	searchMerchandise : function() {
		var row = $('#merchandisePromotionBuyPriceAdjust_grid').datagrid("getSelections");
		if (row.length < 1) {
			$.messager.alert("提示", "请至少选择一个商品");
			return;
		}
		var  merchandiseAndSupplierCodes=[];
		$.each(row, function(index, item){
			//根据商品编号和供应商编号查询同比信息
			merchandiseAndSupplierCodes.push(item.merchandiseCode+":"+item.supplierCode);
		});
		var warehouseCode = $("#warehouseCode").combobox("getValues");
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox("getValue");
		var show = $('input[name="show"]:checked');
		if (warehouseCode != "" && minDate != "") {
			var url = "merchandisePromotionBuyPriceAdjust_showMerchandisePromotionBuyPriceAdjustInfoGrid_1.html?merchandiseAndSupplierCodes=" + merchandiseAndSupplierCodes + "&warehouseCode="
					+warehouseCode + "&minDate=" + minDate + "&maxDate=" + maxDate + "&searchTable=" + show[0].value;
			var tabName = '促销进价调整明细报表';
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
		$("#merchandisePromotionBuyPriceAdjust_search").form('reset');
		clearComboboxOptions();
		$("#merchandisePromotionBuyPriceAdjust_grid").datagrid('loadData', {
			total : 0,
			rows : []
		});
	}
};