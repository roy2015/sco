var merchandisePromotionPurchaseDataGridUtil = null;
var firstIn = true;
$(document).ready(function() {
	merchandisePromotionPurchaseDataGridUtil = utils.grid($('#merchandisePromotionPurchaseData_grid'));
	merchandisePromotionPurchaseDataGridUtil.registerExtFilters("promotionSchedule", "promotionName", "merchandiseCode","supplierCode", "merchandiseCode", "minDate", "maxDate");
	merchandisePromotionPurchaseDataGridUtil.initFilters({
		onBeforeLoad : function(obj) {
			// 清空查询
			if (firstIn) {
				return false;
			}
		}
	});
});
var merchandisePromotionPurchaseDataFn = {
	// 查询
	search : function() {
		firstIn = false;
		var param = merchandisePromotionPurchaseDataGridUtil.getFilterValue();
		if (JSON.stringify(param)=="{}") {
			$.messager.alert('操作失败', "请输入至少一项查询条件");
			return;
		}
		$('#merchandisePromotionPurchaseData_grid').datagrid('load', param);
	},
	// 删除商品促销进货价格维护
	remove : function() {
		var record =$('#merchandisePromotionPurchaseData_grid').datagrid('getSelections');
		if (record == null||record==''){
			$.messager.alert('操作失败', "请至少选择一条记录");
			return;
		}
		utils.confirm("操作确认", "确认删除商品促销进货价格维护?", function() {
			utils.post("merchandisePromotionPurchaseData_deleteMerchandisePromotionPurchaseData_2.html", {
				param : JSON.stringify(record)
			}, function() {
				merchandisePromotionPurchaseDataGridUtil.refresh();
			});
		});
	},
	// 清除查询
	clearFilter : function() {
		firstIn=true;
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		$("#merchandisePromotionPurchaseData_search").form("reset");
		$("#merchandisePromotionPurchaseData_grid").datagrid('loadData', {
			total : 0,
			rows : []
		});
	},
	//格式千分位
	formatterUnit:function(value,row){
		if(value!=null){
			return moneyFormatter(value);
		}else{
			return value;
		}
	},
	//显示上传商品促销进货价格维护界面 
	showUpload:function() {
		var dlg = utils.showDlg({
			title : '上传商品促销进货价格维护记录',
			href : 'merchandisePromotionPurchaseData_showMerchandisePromotionPurchaseDataForm_1.html',
			width : 400,
			height : 200,
			buttons : [ {text:'关闭',
				handler:function(){dlg.dialog('close');},iconCls:'close'} ]
		});
	}
};