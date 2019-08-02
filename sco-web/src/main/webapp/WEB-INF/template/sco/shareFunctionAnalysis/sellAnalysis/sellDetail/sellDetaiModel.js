var sellDetailGridUtil = null;
var firstIn=true;
$(document).ready(function() {
		sellDetailGridUtil = utils.grid($('#sellDetail_grid'));
		sellDetailGridUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", "dxRoleCode", "dlRoleCode", "centreTypeCode", "smallTypeCode",
					"detailTypeCode", "fineTypeCode", "purchaseDepartments","sellRegion","minDate","maxDate");
		sellDetailGridUtil.initFilters({
			onBeforeLoad : function(obj) {
				// 清空查询
				if (firstIn) {
					return false;
				}
			}
		});
});
var sellDetailFn = {
	// 查询
	search : function() {
		firstIn=false;
		var DL = $('input[name="DL"]:checked');
		var sn=$.trim($("#supplierName").val());
		var sc=$.trim($("#supplierCode").val());
		var mn=$.trim($("#merchandiseName").val());
		var mc=$.trim($("#merchandiseCode").val());
		if(sn==""){
			$("#supplierName").val("");
		}
		if(sc==""){
			$("#supplierCode").val("");
		}
		if(mn==""){
			$("#merchandiseName").val("");
		}
		if(mc==""){
			$("#merchandiseCode").val("");
		}
		var param = sellDetailGridUtil.getFilterValue();
		param.show = DL[0].value;
		if (param.sellRegion == null || param.minDate==null || param.maxDate== null) {
			$.messager.alert('操作失败', "必选项未选择");
			return;
		}
		$('#sellDetail_grid').datagrid('load', param);
	},
	// 查询明细
	searchInfo : function() {
		var rows = $('#sellDetail_grid').datagrid("getSelections");
		if (rows.length < 1) {
			$.messager.alert('操作失败', "请勾选至少一条商品查询记录");
			return;
		}
		var sellRegion=$("#sellRegion").combobox('getValue');
		var supplierCode = sellDetailGridUtil.getSelectedIdArr('supplierCode');
		var merchandiseCode = sellDetailGridUtil.getSelectedIdArr('merchandiseCode');
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox('getValue');
		var show =  $('input[name="show"]:checked');
		var DL = $('input[name="DL"]:checked');
		var url = "sellDetail_showSellDetailInfo_1.html?"+
					"supplierCode=" + supplierCode + 
					"&merchandiseCode=" + merchandiseCode + 
					"&minDate=" + minDate + 
					"&maxDate=" + maxDate + 
					"&dl=" + DL[0].value+
					"&show=" + show[0].value+
					"&sellRegion="+sellRegion;
		var tabName = '销售明细查询结果';
		if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
			parent.activeTab(tabName);
			parent.refreshTab(tabName);
			return;
		}
		parent.addTabByUrl(tabName, 'agent', url);
	},
	// 清除查询
	clearFilter : function() {
		firstIn=true;
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		$("#sellDetail_search").form("reset");
		clearComboboxOptions();
		$("#sellDetail_grid").datagrid('loadData', {
			total : 0,
			rows : []
		});
	},
	refresh : function() {
		$('#sellDetail_grid').treegrid('reload');
	}
};