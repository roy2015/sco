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
		if ($('#lastUpdated').is(':checked')) {
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
		clearComboboxOptions();
		clearSearch = false;
	},

	/**
	 * 刷新
	 */
	refresh : function() {
		clearSearch = true;
		$('#accounting_grid').datagrid('reload');
		clearSearch = false;
	},

	/**
	 * 添加商品核算表
	 */
	showInsert : function(inlandImport) {
		var record = accountingGridUtil.getSelectedRecord();
		if (record == null) {
			return;
		}
		var url = "accounting_showInsertAccountingForm_1.html";
		var url_param = '?inlandImport=' + inlandImport;
		
		// 判断意向品编号意向供应商编号是否为空
		if (record.intentionCode != null && record.intentionSupplierCode != null) {
			url_param += "&intentionCode=" + record.intentionCode + "&intentionSupplierCode=" + record.intentionSupplierCode;
		}
		// 判断商品编号是否为空
		if (record.merchandiseCode != null && record.supplierCode != null) {
			url_param += "&merchandiseCode=" + record.merchandiseCode + "&supplierCode=" + record.supplierCode;
		}
		parent.addTabByUrl("核算/投料表", "icon-save", url + url_param);
	},

	/**
	 * 复制商品核算表
	 */
	showCopy : function() {
		var record = accountingGridUtil.getSelectedRecord();
		if (record == null) {
			return;
		}
		var url = "accounting_showCopyAccountingGrid_1.html";
		var url_param = '';
		// 判断意向品编号意向供应商编号是否为空
		if (record.intentionCode != null && (record.intentionSupplierCode != null || record.supplierCode != null)) {
			url_param = "?intentionCode=" + record.intentionCode + "&intentionSupplierCode=" + record.intentionSupplierCode;
		}
		// 判断商品编号是否为空
		if (record.merchandiseCode != null && record.supplierCode != null) {
			// 判断意向品编号是否为空
			if (url_param != '') {
				url_param = url_param + "&merchandiseCode=" + record.merchandiseCode + "&supplierCode=" + record.supplierCode;
			} else {
				url_param = url_param + "?merchandiseCode=" + record.merchandiseCode + "&supplierCode=" + record.supplierCode;
			}
		}
		parent.addTabByUrl("复制核算/投料表", "copy", url + url_param);
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
		// 判断核算表编号是否为空
		if (record.accountingCode != null) {
			url += '?accountingCode=' + record.accountingCode;
		} else {
			return;
		}
		parent.addTabByUrl("核算/投料表", "agent", url);
	},

	/**
	 * 删除核算表
	 */
	remove : function() {
		var record = accountingGridUtil.getSelectedRecord();
		if (record == null || record.accountingCode == null || record.accountingCode == '') {
			return;
		}
		if (record.applicationCode != null && record.applicationCode != '') {
			$.messager.alert("提示", "该核算表已关联OA申请单，不可删除");
			return;
		}
		showLoading();
		utils.confirm("操作确认", "是否确认删除所选的核算/投料表? 删除后系统中无法找回该核算/投料表及对应的扫描版核算表/投料表", function() {
			utils.post("accounting_deleteAccounting_2.html", {
				accountingCode : record.accountingCode
			}, function() {
				accountingGridUtil.refresh();
			});
		});
		$('.msg_bg').remove();
	},

	/**
	 * 下载模板
	 */
	downloadIngredientItemTemplate : function() {
		window.location = "ingredient_downloadIngredientItemTemplate_3.html";
	},

	/**
	 * 显示上传核算数据表界面
	 */
	showUpload : function() {
		var dlg = utils.showDlg({
			title : '上传核算数据表',
			href : 'accounting_showAccountDataForm_1.html',
			width : 400,
			height : 200,
			buttons : [ {
				text : '关闭',
				handler : function() {
					dlg.dialog('close');
				},
				iconCls : 'close'
			} ]
		});
	},

	/**
	 * 显示上传投料表界面
	 */
	showUploadIngredientItemForm : function() {
		var record = accountingGridUtil.getSelectedRecord();
		if (record == null) {
			return;
		}
		
		var param = null;
		if (record.accountingCode != null && record.accountingCode != '') {
			param = '?ingredientCode=' + record.accountingCode;
		}
		if (record.intentionCode != null && record.intentionCode != '') {
			param = param == null ? '?intentionCode=' + record.intentionCode : param + '&intentionCode=' + record.intentionCode;
		}
		if (record.intentionSupplierCode != null && record.intentionSupplierCode != '') {
			param = param == null ? '?intentionSupplierCode=' + record.intentionSupplierCode : param + '&intentionSupplierCode=' + record.intentionSupplierCode;
		}
		if (record.merchandiseCode != null && record.merchandiseCode != '') {
			param = param == null ? '?merchandiseCode=' + record.merchandiseCode : param + '&merchandiseCode=' + record.merchandiseCode;
		}
		if (record.supplierCode != null && record.supplierCode != '') {
			param = param == null ? '?supplierCode=' + record.supplierCode : param + '&supplierCode=' + record.supplierCode;
		}

		var dlg = utils.showDlg({
			title : '上传投料表',
			href : 'ingredient_showUploadIngredientItemForm_1.html' + (param == null ? '' : param),
			width : 400,
			height : 200,
			buttons : [ {
				text : '关闭',
				handler : function() {
					dlg.dialog('close');
				},
				iconCls : 'close'
			} ]
		});
	},
	
	/**
	 * 下载核算/投料表扫描版
	 * @param scanPath
	 */
	downloadScan : function(accountingCode,intentionCode,intentionSupplierCode,merchandiseCode,supplierCode,scanType) {
		if (accountingCode != null && accountingCode != '') {
			var param = '?accountingCode=' + accountingCode + '&scanType=' + scanType;
			if (intentionCode != null && intentionCode != '') {
				param = param + '&intentionCode=' + intentionCode;
			}
			if (intentionSupplierCode != null && intentionSupplierCode != '') {
				param = param + '&intentionSupplierCode=' + intentionSupplierCode;
			}
			if (merchandiseCode != null && merchandiseCode != '') {
				param = param + '&merchandiseCode=' + merchandiseCode;
			}
			if (supplierCode != null && supplierCode != '') {
				param = param + '&supplierCode=' + supplierCode;
			}
			window.location ="accounting_downloadAccountingIngredientScan_6.html" + param;
		}
	},
};