var accountingGridUtil = null;
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
$(document).ready(
		function() {
			accountingGridUtil = utils.grid($('#accounting_grid'));
			accountingGridUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", "purchaseDepartments", "rationed", "purchaseType", "saleType", "minCreateDate",
					"maxCreateDate", "minForetasteDate", "maxForetasteDate", "accountingCode", "updateby", "minUpdated", "maxUpdated", "applicationCode", "applicationStatus", "minApproveDate",
					"maxApproveDate", "centreTypeCodeElse", "smallTypeCode", "detailTypeCode", "fineTypeCode", "merchandiseDxRoleCode", "merchandiseDlRoleCode");
			accountingGridUtil.initFilters({
				onBeforeLoad : function(obj) {
					// 清空查询
					if (clearSearch) {
						return true;
					}
					var length = $.param(obj).split("&").length;
					// 判断参数个数
					if (length < 3) {
						if (!firstIn) {
							$.messager.alert("提示", "请输入至少一项查询条件");
						}
						firstIn = false;
						return false;
					}
					if (length == 4 && $.param(obj).indexOf('order') > -1) {
						var param = accountingGridUtil.getFilterValue();
						if ($.param(param).length > 0) {
							return true;
						}
						$.messager.alert("提示", "请输入至少一项查询条件");
						return false;
					}
				},
				onLoadSuccess : function() {
					firstIn = false;
					$('#accounting_grid').datagrid("clearSelections");
				},
			});
		});
var accountingFn = {
	/**
	 * 搜索商品及对应的核算/投料表记录
	 */
	search : function() {
		var param = accountingGridUtil.getFilterValue();
		if ( $('#lastUpdated').is(':checked')) {
			param.lastUpdated = $('#lastUpdated').is(':checked');
		}
		$('#accounting_grid').datagrid('load', param);
	},

	/**
	 * 清除查询
	 */
	clearFilter : function() {
		clearSearch = true;
		$('#accounting_form').form('reset');
		$('#accounting_grid').datagrid('clearSelections');
		$('#accounting_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		clearSearch = false;
	},

	/**
	 * 刷新DataGrid
	 */
	refresh : function() {
		clearSearch = true;
		$('#accounting_grid').datagrid('reload');
		clearSearch = false;
	},
	
	/**
	 * 复制并添加商品核算表
	 */
	showCopyInsert : function() {
		var record = accountingGridUtil.getSelectedRecord();
		if (record == null) {
			return;
		}
		var url = "accounting_showCopyInsertAccountingForm_1.html";
		var url_param = '';
		// 判断核算表编号是否为空
		if (record.accountingCode != null) {
			url_param = "?accountingCode=" + record.accountingCode;
		} else {
			$.messager.alert("提示", "请选择一条有核算/投料表的记录");
			return;
		}
		
		var accountingCode = $('#accounting_accountingCode').val();
		var intentionCode = $('#accounting_intentionCode').val();
		var intentionSupplierCode = $('#accounting_intentionSupplierCode').val();
		var merchandiseCode = $('#accounting_merchandiseCode').val();
		var supplierCode = $('#accounting_supplierCode').val();
		if (intentionCode != null && intentionCode != '') {
			url_param = url_param + '&intentionCode=' + intentionCode;
			if (intentionSupplierCode != null && intentionSupplierCode != '') {
				url_param = url_param + '&intentionSupplierCode=' + intentionSupplierCode;
			}
		}
		if (merchandiseCode != null && merchandiseCode != '') {
			url_param = url_param + '&merchandiseCode=' + merchandiseCode;
		}
		if (supplierCode != null && supplierCode != '') {
			url_param = url_param + '&supplierCode=' + supplierCode;
		}
		parent.addTabByUrl("核算/投料表", "agent", url + url_param);
	},
	
	/**
	 * 查看核算表详情
	 */
	showLoad : function() {
		var record = accountingGridUtil.getSelectedRecord();
		if (record == null) {
			return;
		}
		var url = 'accounting_showLoadAccountingForm_1.html';
		var url_param = '';
		// 判断核算表编号是否为空
		if (record.accountingCode != null) {
			url_param = '?accountingCode=' + record.accountingCode;
		} else {
			return;
		}
		// 判断意向品编号意向供应商编号是否为空
		if (record.intentionCode != null && (record.intentionSupplierCode != null || record.supplierCode != null)) {
			url_param = url_param + "&intentionCode=" + record.intentionCode;
			if (record.intentionSupplierCode != null) {
				url_param = url_param + "&intentionSupplierCode=" + record.intentionSupplierCode;
			}
			if (record.supplierCode != null) {
				url_param = url_param + "&supplierCode=" + record.supplierCode;
			}
		}
		// 判断商品编号是否为空
		if (record.merchandiseCode != null && record.supplierCode != null) {
			// 判断意向品编号是否为空
			url_param = url_param + "&merchandiseCode=" + record.merchandiseCode + "&supplierCode=" + record.supplierCode;
		}
		url = url + url_param;
		parent.addTabByUrl("核算/投料表", "agent", url);
	}
};