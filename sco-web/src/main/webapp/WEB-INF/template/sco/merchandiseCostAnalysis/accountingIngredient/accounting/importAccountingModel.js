// 地区数量
var regionCount = 0;
// 内包装数量
var npackagTypeCount = 0;
// 外包装数量
var wpackagTypeCount = 0;
// 损耗类型数量
var wastageTypeCount = 0;
// 本次录入核算表的商品/意向品Grid
var addAccountingGridUtil = null;
// 备注地区
var accountingRemarksRegion = [ 'accounting_manage_remarks_region_', 
                                'accounting_freight_remarks_region_', 
                                'accounting_tax_remarks_region_', 
                                'accounting_profit_remarks_region_', 
                                'accounting_elsesubtotal_region_', 
                                'accounting_aggregate_region_'];
// 备注地区动态tr
var accountingRegionTableTr = [ 'region_table_tr_', 
                                'accounting_manage_remarks_region_table_tr_', 
                                'accounting_freight_remarks_region_table_tr_', 
                                'accounting_tax_remarks_region_table_tr_', 
                                'accounting_profit_remarks_region_table_tr_' ];
// 地区动态td
var accountingRegionTableTd = [ 'accounting_region_table_td_', 
                                'accounting_freight_region_table_td_', 
                                'accounting_tax_region_table_td_', 
                                'accounting_profit_region_table_td_', 
                                'accounting_elsesubtotal_region_table_td_', 
                                'accounting_aggregate_region_table_td_' ];
// 固定显示1 报价单位
var unitsTd_1 = ['accounting_ingredient_zlsubtotal_units_td', 
                 'accounting_ingredient_flsubtotal_units_td', 
                 'accounting_ingredient_totalcost_units_td'];
// 报价计量单位
var unitsTd = [ 'accounting_factoryprice_units_td', 
                'accounting_rmbsettlementprice_units_td', 
                'accounting_oceanfreight_units_td', 
                'accounting_updateorderfee_units_td', 
                'accounting_premium_units_td', 
                'accounting_customscharges_units_td', 
                'accounting_importfeetotal_units_td', 
                'accounting_customsduties_units_td', 
                'accounting_addedvaluetax_units_td', 
                'accounting_cdavttotal_units_td', 
                'accounting_customsclearancetotal_units_td', 
                'accounting_deductptcost_units_td', 
                'accounting_nwpackagsubtotal_units_td', 
                'accounting_wec_units_td', 
                'accounting_sbzjwh_units_td', 
                'accounting_manpower_units_td', 
                'accounting_manage_units_td', 
                'accounting_taxdiffer_units_td', 
                'accounting_interest_units_td', 
                'accounting_freight_units_td', 
                'accounting_tax_units_td', 
                'accounting_profit_units_td', 
                'accounting_elsesubtotal_units_td', 
                'accounting_aggregate_units_td' ];
//内外包装/损耗类型 报价计量单位
var npackagtype_wpackagtype_wastagetype_units = ['accounting_npackagtype_units_table_td_', 
                                                 'accounting_wpackagtype_units_table_td_', 
                                                 'accounting_wastagetype_units_td_'];
// 是否保存过报表，当保存核算表后，才可以上传扫描版文件
var accountingSave = null;
$(document).ready(function() {
	// 页面加载时判断是否保存过核算表
	accountingSave = '<#if operate??>success</#if>';
	// 页面加载时地区数量
	regionCount = '<#if accountingRegionList??&&(accountingRegionList?size>0)>${accountingRegionList?size-1}<#else>0</#if>';
	// 页面加载时内包装数量
	npackagTypeCount = '<#if accountingNPackagList??&&(accountingNPackagList?size>0)>${accountingNPackagList?size-1}<#else>0</#if>';
	// 页面加载时外包装数量
	wpackagTypeCount = '<#if accountingWPackagList??&&(accountingWPackagList?size>0)>${accountingWPackagList?size-1}<#else>0</#if>';
	// 页面加载时损耗类型数量
	wastageTypeCount = '<#if accountingWastageList??&&(accountingWastageList?size>0)>${accountingWastageList?size-1}<#else>0</#if>';
	// 本次录入核算表的商品/意向品
	addAccountingGridUtil = utils.grid($('#addAccounting_grid'));
	addAccountingGridUtil.initFilters({
		onLoadSuccess : function() {
			$('#addAccounting_grid').datagrid("clearSelections");
			addAccountingFn.setButtonState();
		}
	});
	// 投料表
	ingredientItemGridUtil = utils.grid($('#ingredientItem_grid'));
	ingredientItemGridUtil.initFilters({
		url:'<#if ingredient.ingredientCode??>ingredient_listIngredientItem_2.html</#if>',
		queryParams : {
			ingredientCode : '<#if ingredient.ingredientCode??>${ingredient.ingredientCode}</#if>'
		},
		onLoadSuccess : function() {
			$('#ingredientItem_grid').datagrid("clearSelections");
		}
	});
	// 报价计量单位
	$('#accounting_units').combobox("setValue",'${accounting.units}');
	// 页面加载时事件处理
	addAccountingFn.unitsOnChange();
	addAccountingFn.ingredientYieldValue();
	addAccountingFn.deductPackagTotalCostValue();
	addAccountingFn.nwpPackagTypeSubtotal();
	addAccountingFn.depreciationTotalCompute();
	addAccountingFn.unitsWageCompute();
	// 可编辑datagrid中textarea的宽高
	$.extend($.fn.datagrid.defaults.editors, {
		textarea : {
			init : function(container, options) {
				var input = $('<textarea class="datagrid-editable-input" rows=' + options.rows + '></textarea>').appendTo(container);
				return input;
			},
			getValue : function(target) {
				return $(target).val();
			},
			setValue : function(target, value) {
				$(target).val(value);
			},
			resize : function(target, width) {

				var input = $(target);
				if ($.boxModel == true) {
					input.width(width - (input.outerWidth() - input.width()));
				} else {
					input.width(width);
				}
			}
		}
	});
});
var addAccountingFn = {
	/**
	 * 保存核算表
	 * 
	 * @returns {Boolean}
	 */
	saveAccounting : function() {
		var applicationStatus = $('#accounting_applicationStatus').val();
		if (applicationStatus != null && applicationStatus != '' && applicationStatus != 'CG') {
			$.messager.alert('提示', '核算表当前OA申请状态不可修改');
			return false;
		}
		if (ingredientItemFn.endEditing()) {
			$('#ingredientItem_grid').datagrid('acceptChanges');
		} else {
			return false;
		}
		addAccountingFn.eachIngredient('投料表',1);
		$("#accounting_form").form('submit', {
			url : 'accounting_saveAccounting_2.html',
			onSubmit : function(param) {
				if (!$('#accounting_form').form('validate')){
					$('.msg_bg').remove();
					$.messager.alert('提示', '请填入所有必填项!');
					return false;	// 返回false将停止form提交
				}
				//投料表json
				param.ingredientItem = JSON.stringify($('#ingredientItem_grid').datagrid('getRows'));
				showLoading();
			},
			success : function(data) {
				var json = $.parseJSON(data);
				$('.msg_bg').remove();
				if (json.success) {
					$('#ingredientItemInputCountSum').val(json.rows.ingredientItemInputCountSum);
					$('#ingredientItemInputCostSum').val(json.rows.ingredientItemInputCostSum);
					$('#ingredientItemAvgCostSum').val(json.rows.ingredientItemAvgCostSum);
					// 重新加载投料表，当核算表是复制过来时，需要更改核算编号
					$('#ingredientItem_grid').datagrid({
						url:'ingredient_listIngredientItem_2.html',
						queryParams : {
							ingredientCode : $('#accounting_accountingCode').val()
						}
					});
					$('#ingredientItem_grid').datagrid('load');
					parent.messagerShow({
						title : '操作成功!',
						msg : json.msg
					});
					// 重新加载本次申请商品
					$('#addAccounting_grid').datagrid('reload', {accountingCode : $('#accounting_accountingCode').val()});
					// 当保存核算表后，才可以上传扫描版文件
					accountingSave = 'success';
				} else {
					parent.messagerShow({
						title : '操作失败!',
						msg : json.msg
					});
				}
			}
		});
	},
	
	/**
	 * 关闭当前窗口
	 */
	closeWindow: function () {
		parent.pubTab.closeCurrTab();
	},
	
	/**
	 * 打开上传核算/投料表扫描版页面
	 */
	showUploadScan : function(scanType) {
		if (accountingSave == null || accountingSave == '') {
			$.messager.alert('提示', '请先保存核算表!');
			return;
		}
		var applicationStatus = $('#accounting_applicationStatus').val();
		if (applicationStatus != null && applicationStatus != '' && applicationStatus != 'CG') {
			$.messager.alert('提示', '核算表当前OA申请状态不可修改');
			return;
		}
		var title;
		if (scanType != null && scanType == 'accounting') {
			title = '上传扫描版核算表';
		} else if (scanType != null && scanType == 'ingredient') {
			title = '上传扫描版投料表';
		} else {
			return;
		}
		var accountingCode = $('#accounting_accountingCode').val();
		var intentionCode = $('#accounting_intentionCode').val();
		var intentionSupplierCode = $('#accounting_intentionSupplierCode').val();
		var merchandiseCode = $('#accounting_merchandiseCode').val();
		var supplierCode = $('#accounting_supplierCode').val();
		var param = null;
		if (accountingCode != null && accountingCode != '') {
			param = '?accountingCode=' + accountingCode;
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
			if (param != null) {
				param = param + '&scanType=' + scanType;
			} else {
				return;
			}
		}

		var dlg = utils.showDlg({
			title : title,
			href : 'accounting_showUploadAccountingIngredientScanForm_1.html' + param,
			width : 400,
			height : 200,
			buttons : [ {
				text : '关闭',
				handler : function() {
					dlg.dialog('close');
				},
				iconCls : 'close'
			}]
		});
	},
	
	/**
	 * 下载核算/投料表扫描版
	 * @param scanPath
	 */
	downloadScan : function(scanType) {
		var accountingCode = $('#accounting_accountingCode').val();
		var intentionCode = $('#accounting_intentionCode').val();
		var intentionSupplierCode = $('#accounting_intentionSupplierCode').val();
		var merchandiseCode = $('#accounting_merchandiseCode').val();
		var supplierCode = $('#accounting_supplierCode').val();
		var param = null;
		if (accountingCode != null && accountingCode != '') {
			param = '?accountingCode=' + accountingCode;
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
			if (param != null) {
				param = param + '&scanType=' + scanType;
			} else {
				return;
			}
		}
		window.location ="accounting_downloadAccountingIngredientScan_6.html" + param;
	},
	
	/**
	 * 删除核算/投料表扫描版
	 * @param type
	 */
	removeScan : function(type) {
		var applicationStatus = $('#accounting_applicationStatus').val();
		if (applicationStatus != null && applicationStatus != '' && applicationStatus != 'CG') {
			$.messager.alert('提示', '核算表当前OA申请状态不可修改');
			return;
		}
		var title;
		if (type != null && type == 'accounting') {
			title = '确认删除扫描版核算表';
		} else if (type != null && type == 'ingredient') {
			title = '确认删除扫描版投料表';
		} else {
			return;
		}
		utils.confirm("操作确认", title, function() {
			utils.post("accounting_deleteAccountingIngredientScan_2.html", {
				accountingCode : $('#accounting_accountingCode').val(),
				scanType : type
			}, function(json) {
				if(json.success){
					parent.messagerShow({title:'操作成功!', msg:json.msg});
					$('#addAccounting_grid').datagrid('load')
				}else{
					parent.messagerShow({title:'操作失败!', msg:json.msg});
				}
			});
		});
	},
	
	/**
	 * 设置按钮状态
	 * @param index
	 * @param record
	 */ 
	setButtonState : function() {
		var row = $('#addAccounting_grid').datagrid("getRows");
		if (row.length == 1) {
			//判断核算表扫描版是否为空
			if (row[0].accountingScanPath != null && row[0].accountingScanPath != '') {
				$('#removeScanSccounting').linkbutton("enable");
			}else {
				$('#removeScanSccounting').linkbutton("disable");
			}
			//判断投料表扫描版是否为空
			if (row[0].ingredientScanPath != null && row[0].ingredientScanPath != '') {
				$('#removeScanIngredient').linkbutton("enable");
			}else {
				$('#removeScanIngredient').linkbutton("disable");
			}
		}
	},
	
	/**
	 * 遍历显示投料明细
	 */
	eachIngredient : function(title,index) {
		if (index == 1) {
			if (ingredientItemFn.endEditing()) {
				$('#ingredientItem_grid').datagrid('acceptChanges');
			} else {
				return false;
			}
			var ingredientItem = $('#ingredientItem_grid').datagrid('getRows');
			var inputCostZl = 0;
			var inputCostFl = 0;
			var avgCost = 0;
			$('#accounting_ingredient_table').html('');
			$.each(ingredientItem, function(n, value) {
				$('#accounting_ingredient_table').append(
					'<tr id="accounting_ingredient_table_tr_'+ n +'">' +
						'<td style="width: 210px;">投料表:'+ (value.text==null?value.materialTypeName:value.text) +'-'+ value.materialName +'</td>' +
						'<td style="width: 100px;">1'+ $('#accounting_units').combobox("getValue") +'</td>' +
						'<td>' +
							'<input value="'+ value.avgCost +'" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4" readonly="readonly"/>' +
						'</td>' +
						'<td style="width: 442px;">' +
							'<textarea class="easyui-validatebox" style="width: 420px; height: 50px; background-color: rgb(235, 235, 228);" validtype="length[0,330]" readonly="readonly">'+ (value.remarks==null?'':value.remarks) +'</textarea>' +
						'</td>' +
						'<td style="width: 31px"></td>' +
					'</tr>'
				);
				if (value.materialType == 'ZL') {
					inputCostZl = (inputCostZl-0) + (value.avgCost-0);
				}
				if (value.materialType == 'FL') {
					inputCostFl = (inputCostFl-0) + (value.avgCost-0);
				}
				avgCost = (avgCost-0) + (value.avgCost-0);
				
			});
			$('#accounting_ingredient_zlsubtotal_region_input').numberbox('setValue', inputCostZl);
			$('#accounting_ingredient_flsubtotal_region_input').numberbox('setValue', inputCostFl);
			$('#accounting_ingredient_totalcost_region_input').numberbox('setValue', avgCost);
			$.parser.parse($('#accounting_ingredient_table'));
		}
	},
	
	/**
	 * 投料表得率
	 */
	ingredientYieldValue : function() {
		$('#accounting_ingredient_yield_region_input').numberbox('setValue', $('#ingredient_yield').numberbox('getValue'));
	},
	
	/**
	 * 出厂价类型onChange
	 */
	factoryPriceTypeOnChange : function() {
		var factoryPriceType = $('#accounting_factorypricetype').combobox("getValue");
		if (factoryPriceType != null && factoryPriceType != '' && factoryPriceType == 'ELSE') {
			$('#accounting_factorypricetype_elsetype').show();
			$('#accounting_factorypricetype_elsetype').removeAttr('disabled');
		} else {
			$('#accounting_factorypricetype_elsetype').val('');
			$('#accounting_factorypricetype_elsetype').hide();
			$('#accounting_factorypricetype_elsetype').attr("disabled","inline");
		}
	},
	
	/**
	 * 计算-商品人民币结算价格
	 */
	deductRmbSettlementPrice : function() {
		var factoryPrice = $('#accounting_factoryprice_region_input').numberbox('getValue');
		var exchangerate = $('#accounting_exchangerate_region_input').numberbox('getValue');
		//都不为空时，计算
		if (factoryPrice != '' && exchangerate != '') {
			$('#accounting_rmbsettlementprice_region_input').numberbox('setValue', factoryPrice * exchangerate);
		} else {
			$('#accounting_rmbsettlementprice_region_input').numberbox('setValue', '');
		}
		addAccountingFn.deductCustomsClearanceTotal();
	},
	
	/**
	 * 货柜类型onChange
	 */
	containerTypeOnChange : function() {
		var containerType = $('#accounting_oceanfreight_containertype').combobox("getValue");
		if (containerType != null && containerType != '') {
			if (containerType == 'ELSE') {
				$('#accounting_oceanfreight_containertype_elsetype').show();
				$('#accounting_oceanfreight_containertype_elsetype').removeAttr('disabled');
			} else {
				$('#accounting_oceanfreight_containertype_elsetype').val('');
				$('#accounting_oceanfreight_containertype_elsetype').hide();
				$('#accounting_oceanfreight_containertype_elsetype').attr("disabled","inline");
			}
		}
	},
	
	/**
	 * 计算-进口费用小计
	 */
	deductImportFeeTotal : function() {
		var oceanfreight = $('#accounting_oceanfreight_region_input').numberbox('getValue');
		var updateOrderFee = $('#accounting_updateorderfee_region_input').numberbox('getValue');
		var premium = $('#accounting_premium_region_input').numberbox('getValue');
		var customscharges = $('#accounting_customscharges_region_input').numberbox('getValue');
		if (oceanfreight == '' && updateOrderFee == '' && premium == '' && customscharges == '') {
			$('#accounting_importfeetotal_region_input').numberbox('setValue', '');
		} else {
			$('#accounting_importfeetotal_region_input').numberbox('setValue', Number(oceanfreight) + Number(updateOrderFee) + Number(premium) + Number(customscharges));
		}
		addAccountingFn.deductCustomsClearanceTotal();
	},
	
	/**
	 * 计算-关税/增值税小计
	 */
	deductCdAvtTotal : function() {
		var customsduties = $('#accounting_customsduties_region_input').numberbox('getValue');
		var addedvaluetax = $('#accounting_addedvaluetax_region_input').numberbox('getValue');
		if (customsduties == '' && addedvaluetax == '') {
			$('#accounting_cdavttotal_region_input').numberbox('setValue', '');
		} else {
			$('#accounting_cdavttotal_region_input').numberbox('setValue', Number(customsduties) + Number(addedvaluetax));
		}
		addAccountingFn.deductCustomsClearanceTotal();
	},
	
	/**
	 * 计算-清关后商品总成本
	 */
	deductCustomsClearanceTotal : function() {
		var rmbSettlementPrice = $('#accounting_rmbsettlementprice_region_input').numberbox('getValue');
		var importFeeTotal = $('#accounting_importfeetotal_region_input').numberbox('getValue');
		var cdAvtTotal = $('#accounting_cdavttotal_region_input').numberbox('getValue');
		if (rmbSettlementPrice == '' && importFeeTotal == '' && cdAvtTotal == '') {
			$('#accounting_customsclearancetotal_region_input').numberbox('setValue', '');
		} else {
			$('#accounting_customsclearancetotal_region_input').numberbox('setValue', Number(rmbSettlementPrice) + Number(importFeeTotal) + Number(cdAvtTotal));
		}
		addAccountingFn.deductPackagTotalCostValue();
	},

	/**
	 * 计算-扣除包装后原材料总成本
	 */
	deductPackagTotalCostValue : function() {
		var customsClearanceTotal = $('#accounting_customsclearancetotal_region_input').numberbox('getValue');
		var packagProportion = $('#accounting_packagproportion_region_input').numberbox('getValue');
		//都不为空时，计算
		if (customsClearanceTotal != '' && packagProportion != '') {
			$('#accounting_deductptcost_region_input').numberbox('setValue', customsClearanceTotal * (100 - packagProportion) / 100);
		} else {
			$('#accounting_deductptcost_region_input').numberbox('setValue', '');
		}
		addAccountingFn.total();
	},

	/**
	 * 计算-内外包装材料小计
	 */
	nwpPackagTypeSubtotal : function() {
		var nPackagTypeCount = '';
		var wPackagTypeCount = '';
		//计算所有内包装和
		$('[id^=accounting_npackagtype_region_input_]').each(function() {
			nPackagTypeCount =  Number(nPackagTypeCount) + Number($(this).numberbox('getValue'));
		});
		//计算所有外包装和
		$('[id^=accounting_wpackagtype_region_input_]').each(function() {
			wPackagTypeCount = Number(wPackagTypeCount) + Number($(this).numberbox('getValue'));
		});
		if (nPackagTypeCount == '' && wPackagTypeCount == '') {
			$('#accounting_nwpackagsubtotal_region_input').numberbox('setValue', '');
		} else {
			$('#accounting_nwpackagsubtotal_region_input').numberbox('setValue', nPackagTypeCount + wPackagTypeCount);
		}
		addAccountingFn.total();
	},
	
	/**
	 * 计算-损耗小计(废弃)
	 */
	wastageSubtotal : function(){
		var wastageTypeCount = '';
		//计算所有损耗和
		$('[id^=accounting_wastagetype_region_input_]').each(function() {
			wastageTypeCount = Number(wastageTypeCount) + Number($(this).numberbox('getValue'));
		});
		if (wastageTypeCount == '') {
			$('#accounting_wastagesubtotal_region_input').numberbox('setValue', '');
		} else {
			$('#accounting_wastagesubtotal_region_input').numberbox('setValue', wastageTypeCount);
		}
		addAccountingFn.total();
	},
	
	/**
	 * 计算-小计
	 */
	total : function() {
		var deductPackagTotalCost = $('#accounting_deductptcost_region_input').numberbox('getValue');
		var nwPackagTypeSubtotal = $('#accounting_nwpackagsubtotal_region_input').numberbox('getValue');
		var wastageType = '';
		//计算所有损耗和
		$('[id^=accounting_wastagetype_region_input_]').each(function() {
			wastageType = Number(wastageType) + Number($(this).numberbox('getValue'));
		});
		var wec = $('#accounting_wec_region_input').numberbox('getValue');
		var sbzjwh = $('#accounting_sbzjwh_region_input').numberbox('getValue');
		var manpower = $('#accounting_manpower_region_input').numberbox('getValue');
		var manage = $('#accounting_manage_region_input').numberbox('getValue');
		var taxdiffer = $('#accounting_taxdiffer_region_input').numberbox('getValue');
		var interest = $('#accounting_interest_region_input').numberbox('getValue');
		
		// 其他小计
		$('[id^=accounting_elsesubtotal_region_input_]').each(function() {
			var _id = $(this).attr('id');
			var number = _id.substr(_id.lastIndexOf('_') + Number(1), _id.length);
			var freight = $('#accounting_freight_region_input_' + number).numberbox('getValue');
			var tax = $('#accounting_tax_region_input_' + number).numberbox('getValue');
			var profit = $('#accounting_profit_region_input_' + number).numberbox('getValue');
			//地区其他小计
			if (wastageType == '' && wec == '' && sbzjwh == '' && manpower == '' && manage == '' && taxdiffer == '' && interest == '' && freight == '' && tax == '' && profit == '') {
				$(this).numberbox('setValue', '');
			} else {
				$(this).numberbox('setValue', Number(wastageType) + Number(wec) + Number(sbzjwh) + Number(manpower) + Number(manage) + Number(taxdiffer) + Number(interest) + Number(freight) + Number(tax) + Number(profit));
			}
		});
		// 总价
		$('[id^=accounting_aggregate_region_input_]').each(function() {
			var _id = $(this).attr('id');
			var number = _id.substr(_id.lastIndexOf('_') + Number(1), _id.length);
			var freight = $('#accounting_freight_region_input_' + number).numberbox('getValue');
			var tax = $('#accounting_tax_region_input_' + number).numberbox('getValue');
			var profit = $('#accounting_profit_region_input_' + number).numberbox('getValue');
			//总价
			var aggregate = Number(deductPackagTotalCost) + Number(nwPackagTypeSubtotal) + Number(wastageType) + Number(wec) + Number(sbzjwh) + Number(manpower) + Number(manage) + Number(taxdiffer)+ Number(interest) + Number(freight) + Number(tax) + Number(profit);;
			if (deductPackagTotalCost == '' && nwPackagTypeSubtotal == '' && wastageType == '' && wec == '' && sbzjwh == '' && manpower == '' && manage == '' && taxdiffer == '' && interest == '' && freight == '' && tax == '' && profit == '') {
				$(this).numberbox('setValue', '');
			} else {
				$(this).numberbox('setValue', aggregate);
			}
			//管理地区占比
			$('#accounting_manage_remarks_proportion_input_' + number).numberbox('setValue', manage / aggregate * 100);
			//税收地区占比
			$('#accounting_tax_remarks_proportion_input_' + number).numberbox('setValue', tax / aggregate * 100);
			//利润地区占比
			if (profit != '' && profit < 0) {
				$('#accounting_profit_remarks_proportion_input_' + number).numberbox('setValue', '');
			} else {
				$('#accounting_profit_remarks_proportion_input_' + number).numberbox('setValue', profit / aggregate * 100);
			}
		});
	},
	
	/**
	 * 获取报价计量单位值
	 * 
	 * @returns
	 */
	getQuantityUnits : function() {
		var quantity = $('#accounting_quantity').val();
		var units = $('#accounting_units').combobox("getValue");
		//当都不为空时，返回计量单位
		if (quantity != null && quantity != '' && units != null && units != '') {
			return quantity + units;
		} else {
			return '';
		}
	},
	
	/**
	 * 报价计量单位onChange事件
	 */
	unitsOnChange : function() {
		addAccountingFn.eachIngredient('',1);
		var quantityunits = addAccountingFn.getQuantityUnits();
		// 1给固定的报价计量单位赋值
		$.each(unitsTd_1, function(n, value) {
			$('#' + value).text(1 + $('#accounting_units').combobox("getValue"));
		});
		if (quantityunits != null && quantityunits != '') {
			// 给固定的报价计量单位赋值
			$.each(unitsTd, function(n, value) {
				$('#' + value).text(quantityunits);
			});
			// 给动态增加的报价计量单位赋值
			$.each(npackagtype_wpackagtype_wastagetype_units, function(n, value) {
				$('[id^=' + value + ']').each(function() {
					$(this).text(quantityunits);
				});
			});
		}
	},
	
	/**
	 * 地区下拉框onSelect事件
	 * 
	 * @param obj
	 */
	regionOnSelect : function(obj) {
		var region_text = $(obj).combobox("getText");
		var _id = $(obj).attr('id');
		var number;
		// 判断当前对象id是否为空
		if (_id != null && _id != '') {
			number = _id.substr(_id.lastIndexOf('_') + Number(1), _id.length);
			// 判断当前选中内容是否为空
			if (number != null && number != '') {
				// 设置核算表表头地区的内容为当前选择的地区
				$('#accounting_region_table_td_' + number).text(region_text);
				// 设置动态增加地区输入框中的内容为当前选择的地区
				$.each(accountingRemarksRegion, function(n, value) {
					$('#' + value + number).val(region_text);
				});
			}
		}
	},
	
	/**
	 * 计算-水电煤 合计
	 */
	wecTotalCompute : function() {
		var water = $('#accounting_wec_remarks_water_input').numberbox('getValue');
		var oil = $('#accounting_wec_remarks_oil_input').numberbox('getValue');
		var electricity = $('#accounting_wec_remarks_electricity_input').numberbox('getValue');
		var gas = $('#accounting_wec_remarks_gas_input').numberbox('getValue');
		var coal = $('#accounting_wec_remarks_coal_input').numberbox('getValue');
		var total = '';
		// 当都不为空时，计算
		if (water != '') {
			total = Number(total) + Number(water);
		}
		if(oil != '') {
			total = Number(total) + Number(oil);
		}
		if(electricity != '') {
			total = Number(total) + Number(electricity);
		}
		if(gas != '') {
			total = Number(total) + Number(gas);
		}
		if(coal != '') {
			total = Number(total) + Number(coal);
		}
		if (total != '') {
			$('#accounting_wec_remarks_total_input').numberbox('setValue', total / 1000);
		} else {
			$('#accounting_wec_remarks_total_input').numberbox('setValue', '');
		}
	},

	/**
	 * 计算-人工 每kg成品元/kg
	 */
	unitsWageCompute : function() {
		var manpowerCount = $('#accounting_manpower_remarks_manpowercount_input').numberbox('getValue');
		var avgWage = $('#accounting_manpower_remarks_avgwage_input').numberbox('getValue');
		var monthCapacity = $('#accounting_manpower_remarks_monthcapacity_input').numberbox('getValue');
		// 当都不为空时，计算
		if (manpowerCount != '' && avgWage != '' && monthCapacity != '') {
			$('#accounting_manpower_remarks_unitswage_input').numberbox('setValue', avgWage * manpowerCount / monthCapacity / 1000);
		} else {
			$('#accounting_manpower_remarks_unitswage_input').numberbox('setValue', '');
		}
	},

	/**
	 * 计算-内包装(复合袋-制袋) 公斤价格
	 */
	kgPriceCompute : function(obj) {
		var _id = $(obj).attr('id');
		var number;
		if (_id != null && _id != '') {// 判断当前对象id是否为空
			number = _id.substr(_id.lastIndexOf('_') + Number(1), _id.length);
		}
		if (number != null && number != '') {
			var weight = $('#accounting_npackagtype_remarks_weight_input_' + number).numberbox('getValue');
			var unitsPrice = $('#accounting_npackagtype_remarks_unitsprice_input_' + number).numberbox('getValue');
			// 当都不为空时，计算
			if (weight != '' && unitsPrice != '') {
				$('#accounting_npackagtype_remarks_kgprice_input_' + number).numberbox('setValue', unitsPrice / weight * 1000);
			} else {
				$('#accounting_npackagtype_remarks_kgprice_input_' + number).numberbox('setValue', '');
			}
		}

	},

	/**
	 * 计算-外包装(纸箱) 纸箱用料面积
	 */
	areaCompute : function(obj) {
		var _id = $(obj).attr('id');
		var number;
		if (_id != null && _id != '') {// 判断当前对象id是否为空
			number = _id.substr(_id.lastIndexOf('_') + Number(1), _id.length);
		}
		if (number != null && number != '') {
			var length = $('#accounting_wpackagtype_remarks_length_input_' + number).numberbox('getValue');
			var width = $('#accounting_wpackagtype_remarks_width_input_' + number).numberbox('getValue');
			var height = $('#accounting_wpackagtype_remarks_height_input_' + number).numberbox('getValue');
			var unitsPrice = $('#accounting_wpackagtype_remarks_unitsprice_input_' + number).numberbox('getValue');
			// 当长宽高不为空时，计算
			if (length != '' && width != '' && height != '') {
				var area = (Number(length) + Number(width) + Number(5)) * (Number(width) + Number(height) + Number(3)) * 2 / 10000;
				$('#accounting_wpackagtype_remarks_area_input_' + number).numberbox('setValue', area);
				// 当单价不为空时，计算
				if (unitsPrice != '') {
					$('#accounting_wpackagtype_remarks_ylunitsprice_input_' + number).numberbox('setValue', unitsPrice / area);
				} else {
					$('#accounting_wpackagtype_remarks_ylunitsprice_input_' + number).numberbox('setValue', '');
				}
			} else {
				$('#accounting_wpackagtype_remarks_area_input_' + number).numberbox('setValue', '');
			}
		}

	},
	
	/**
	 * 合计设备折旧值计算
	 */
	depreciationTotalCompute : function() {
		var depreciation = $('#accounting_sbzjwh_remarks_depreciation_input').numberbox('getValue');
		var capacity = $('#accounting_sbzjwh_remarks_capacity_input').numberbox('getValue');
		// 当都不为空时，计算
		if (depreciation != '' && capacity != '') {
			$('#accounting_sbzjwh_remarks_depreciationtotal_input').numberbox('setValue', depreciation / capacity * 10);
		} else {
			$('#accounting_sbzjwh_remarks_depreciationtotal_input').numberbox('setValue', '');
		}
	},
	
	
	
	/**
	 * 增加地区
	 */
	addRegion : function() {
		var region = $('select[id^=region_]').length;
		if (region >= 10) {
			return;
		}
		regionCount++;
		addAccountingFn.addRow();
		addAccountingFn.addCol();
	},
	
	/**
	 * 删除地区
	 * 
	 * @param _id
	 */
	delRegion : function(_id) {
		addAccountingFn.delRow(_id);
		addAccountingFn.delCol(_id);
	},
	
	/**
	 * 新增核算表地区-备注列中的地区输入框
	 */
	addRow : function() {
		// 核算总表地区
		var region_table_tr_template = 
				'<tr id="region_table_tr_'+regionCount+'">' +
					'<td>进货地区:</td>' +
					'<td>'+
						'<select id="region_'+ regionCount +'" name="accountingRegionList['+ regionCount +'].region" class="easyui-combobox" style="width: 100px" data-options="editable:false,multiple:true,valueField:\'id\',textField:\'text\',required:true,url:\'masterDataType_listWarehouseOption_5.html\',onSelect:function(){addAccountingFn.regionOnSelect(this);},onUnselect:function(){addAccountingFn.regionOnSelect(this);}"></select>' +
					'</td>' +
					'<td>' +
						'<a class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true" onclick="addAccountingFn.delRegion('+ regionCount + ')">' +
					'</td>' +
				'</tr>';
		$("#region_table").append(region_table_tr_template);
		$.parser.parse($('#region_table_tr_'+ regionCount));
		// 管理-备注地区
		var accounting_manage_remarks_region_table_tr_template = 
				'<tr id="accounting_manage_remarks_region_table_tr_'+regionCount+'">' +
					'<th style="width: 100px;">进货地区:</th>' +
					'<td style="width: 100px;">' +
						'<input id="accounting_manage_remarks_region_'+ regionCount +'" name="accountingManageRegionList['+ regionCount +'].region" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />' +
					'</td>' +
					'<th style="width: 100px;">占比(%):</th>' +
					'<td style="width: 100px;">' +
						'<input id="accounting_manage_remarks_proportion_input_'+ regionCount +'" name="accountingManageRegionList['+ regionCount +'].proportion" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4,min:0,max:9999999999" readonly="readonly" />' +
					'</td>' +
				'</tr>';
		$("#accounting_manage_remarks_region_table").append(accounting_manage_remarks_region_table_tr_template);
		$.parser.parse($('#accounting_manage_remarks_region_table_tr_'+ regionCount));
		// 运输地区-备注地区
		var accounting_freight_remarks_region_table_tr_template = 
				'<tr id="accounting_freight_remarks_region_table_tr_'+regionCount+'">' +
					'<th style="width: 100px;">进货地区:</th>' +
					'<td>' +
						'<input id="accounting_freight_remarks_region_'+regionCount+'" name="accountingFreightRegionList['+regionCount+'].region" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />' +
					'</td>' +
					'<th style="width: 100px;">总公里数(km):</th>' +
					'<td>' +
						'<input id="accounting_freight_remarks_sumkm_input_'+regionCount+'" name="accountingFreightRegionList['+regionCount+'].sumKm" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
					'</td>' +
				'</tr>';
		$("#accounting_freight_remarks_region_table").append(accounting_freight_remarks_region_table_tr_template);
		$.parser.parse($('#accounting_freight_remarks_region_table_tr_'+ regionCount));
		// 税收地区-备注地区
		var accounting_tax_remarks_region_table_tr_template = 
				'<tr id="accounting_tax_remarks_region_table_tr_'+regionCount+'">' +
					'<th style="width: 100px;">进货地区:</th>' +
					'<td>' +
						'<input id="accounting_tax_remarks_region_'+ regionCount +'" name="accountingTaxRegionList['+ regionCount +'].region" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />' +
					'</td>' +
					'<th style="width: 100px;">占比(%):</th>' +
					'<td>' +
						'<input id="accounting_tax_remarks_proportion_input_'+ regionCount +'" name="accountingTaxRegionList['+ regionCount +'].proportion" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4,min:0,max:9999999999" readonly="readonly" />' +
					'</td>' +
				'</tr>';
		$("#accounting_tax_remarks_region_table").append(accounting_tax_remarks_region_table_tr_template);
		$.parser.parse($('#accounting_tax_remarks_region_table_tr_'+ regionCount));
		// 利润地区-备注地区
		var accounting_profit_remarks_region_table_tr_template = 
				'<tr id="accounting_profit_remarks_region_table_tr_'+regionCount+'">' +
					'<th style="width: 100px;">进货地区:</th>' +
					'<td>' +
						'<input id="accounting_profit_remarks_region_'+ regionCount +'" name="accountingProfitRegionList['+ regionCount +'].region" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />' +
					'</td>' +
					'<th style="width: 100px;">占比(%):</th>' +
					'<td>' +
						'<input id="accounting_profit_remarks_proportion_input_'+ regionCount +'" name="accountingProfitRegionList['+ regionCount +'].proportion" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4,min:0,max:9999999999" readonly="readonly" />' +
					'</td>' +
				'</tr>';
		$("#accounting_profit_remarks_region_table").append(accounting_profit_remarks_region_table_tr_template);
		$.parser.parse($('#accounting_profit_remarks_region_table_tr_'+ regionCount));
	},

	/**
	 * 删除核算表地区-备注列中的地区输入框
	 * 
	 * @param _id
	 */
	delRow : function(_id) {
		$.each(accountingRegionTableTr, function(n, value) {
			$('#' + value + _id).remove();
		});
	},

	/**
	 * 新增核算表地区-地区列中的输入框
	 */
	addCol : function() {
		// 地区表头
		$("#accounting_region_table tr").append('<td id="accounting_region_table_td_'+ regionCount +'" style="width: 100px;"></td>');
		// 运输
		$("#accounting_freight_region_table tr").append(
				'<td id="accounting_freight_region_table_td_'+ regionCount +'">'+
					'<input id="accounting_freight_region_input_'+ regionCount +'" name="accountingFreightRegionList['+ regionCount +'].price" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.total" />'+
				'</td>');
		$.parser.parse($('#accounting_freight_region_table_td_'+ regionCount));
		// 税收
		$("#accounting_tax_region_table tr").append(
				'<td id="accounting_tax_region_table_td_'+ regionCount +'">'+
					'<input id="accounting_tax_region_input_'+ regionCount +'" name="accountingTaxRegionList['+ regionCount +'].price" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.total" />'+
				'</td>');
		$.parser.parse($('#accounting_tax_region_table_td_'+ regionCount));
		// 利润
		$("#accounting_profit_region_table tr").append(
				'<td id="accounting_profit_region_table_td_'+ regionCount +'">'+
					'<input id="accounting_profit_region_input_'+ regionCount +'" name="accountingProfitRegionList['+ regionCount +'].price" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:-9999999999,max:9999999999,onChange:addAccountingFn.total" />'+
				'</td>');
		$.parser.parse($('#accounting_profit_region_table_td_'+ regionCount));
		// 其他成本小计
		$("#accounting_elsesubtotal_region_table tr").append(
				'<td id="accounting_elsesubtotal_region_table_td_'+ regionCount +'">'+
					'<input id="accounting_elsesubtotal_region_'+ regionCount +'" name="accountingElsesubtotalRegionList['+ regionCount +'].region" class="easyui-validatebox" style="width: 100px; display: none;" />'+
					'<input id="accounting_elsesubtotal_region_input_'+ regionCount +'" name="accountingElsesubtotalRegionList['+ regionCount +'].subtotal" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4,min:0,max:9999999999" readonly="readonly"" />'+
				'</td>');
		$.parser.parse($('#accounting_elsesubtotal_region_table_td_'+ regionCount));
		// 总价
		$("#accounting_aggregate_region_table tr").append(
				'<td id="accounting_aggregate_region_table_td_'+ regionCount +'">'+
					'<input id="accounting_aggregate_region_'+ regionCount +'" name="accountingAggregateRegionList['+ regionCount +'].region" class="easyui-validatebox" style="width: 100px; display: none;" />'+
					'<input id="accounting_aggregate_region_input_'+ regionCount +'" name="accountingAggregateRegionList['+ regionCount +'].sumPrice" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />'+
				'</td>');
		$.parser.parse($('#accounting_aggregate_region_table_td_'+ regionCount));
	},

	/**
	 * 删除核算表地区-地区列中的输入框
	 * 
	 * @param _id
	 */
	delCol : function(_id) {
		$.each(accountingRegionTableTd, function(n, value) {
			$('#' + value + _id).remove();
		});
	},
	
	/**
	 * 内包装类型加载成功事件
	 * 
	 * @param obj
	 */
	nPackagTypeOnLoadSuccess: function(packag) {
		if (packag.npackagType == 'W') {
			addAccountingFn.append_accounting_packagType('accounting_npackagtype_region_table_td_', packag.index, null);
			addAccountingFn.append_accounting_packagType('accounting_npackagtype_remarks_table_td_', packag.index, null);
		} else if (packag.npackagType == 'FHD_JM') { // 复合袋-卷膜
			addAccountingFn.append_accounting_npackagType_fhdjm_region_table_td(packag);
		} else if (packag.npackagType == 'FHD_LSM') { // 复合袋-拉伸膜
			addAccountingFn.append_accounting_npackagType_fhdjm_region_table_td(packag);
		} else if (packag.npackagType == 'FHD_ZD') { // 复合袋-制袋
			addAccountingFn.append_accounting_npackagType_fhdzd_region_table_td(packag);
			// 页面加载时自动计算-内包装(复合袋-制袋) 公斤价格
			addAccountingFn.kgPriceCompute($('#accounting_npackagtype_remarks_weight_input_' + packag.index));
		} else if (packag.npackagType == 'ST') { // 塑托
			addAccountingFn.append_accounting_npackagType_fhdst_region_table_td(packag);
		} else if (packag.npackagType == 'TYJ') { // 脱氧剂
			addAccountingFn.append_accounting_npackagType_tyj_region_table_td(packag);
		} else if (packag.npackagType == 'NDD') { // 内胆袋
			addAccountingFn.append_accounting_npackagType_ndd_region_table_td(packag);
		} else if (packag.npackagType == 'BQ') { // 标签
			addAccountingFn.append_accounting_npackagType_bq_region_table_td(packag);
		} else if (packag.npackagType == 'ZZL') { // 纸张类
			addAccountingFn.append_accounting_npackagType_bq_region_table_td(packag);
		} else if (packag.npackagType == 'ELSE') { // 其它
			addAccountingFn.append_accounting_npackagType_else_region_table_td(packag);
			$('#accounting_npackagtype_elsename_input_' + packag.index).show();
			$('#accounting_npackagtype_elsename_input_' + packag.index).removeAttr('disabled');
		}
	},
	
	/**
	 * 外包装类型加载成功事件
	 * 
	 * @param obj
	 */
	wPackagTypeOnLoadSuccess: function(packag) {
		if (packag.wpackagType == 'W') {
			addAccountingFn.append_accounting_packagType('accounting_wpackagtype_region_table_td_', packag.index, null);
			addAccountingFn.append_accounting_packagType('accounting_wpackagtype_remarks_table_td_', packag.index, null);
		} else if (packag.wpackagType == 'FXD') { // 封箱带
			addAccountingFn.append_accounting_wpackagType_fxd_region_table_td(packag);
		} else if (packag.wpackagType == 'ZX') { // 纸箱
			addAccountingFn.append_accounting_wpackagType_zx_region_table_td(packag);
			// 页面加载时自动计算-外包装(纸箱) 纸箱用料面积
			addAccountingFn.areaCompute($('#accounting_wpackagtype_remarks_texture_input_' + packag.index));
		} else if (packag.wpackagType == 'ELSE') { // 其它
			addAccountingFn.append_accounting_wpackagType_else_region_table_td(packag);
			$('#accounting_wpackagtype_elsename_input_' + packag.index).show();
			$('#accounting_wpackagtype_elsename_input_' + packag.index).removeAttr('disabled');
		}
	},
	
	/**
	 * 内外包装类型改变事件
	 * 
	 * @param obj
	 */
	packagTypeOnSelect : function(obj) {
		var packagtype_value = $(obj).combobox("getValue");
		var _id = $(obj).attr('id');
		var number;
		var packagType_id;
		if (_id != null && _id != '') {// 判断当前对象id是否为空
			packagType_id = _id.substr(0, _id.lastIndexOf('_'));
			number = _id.substr(_id.lastIndexOf('_') + Number(1), _id.length);
		}
		if (packagtype_value != null && packagtype_value != '') {// 判断当前选中内容是否为空
			if (packagType_id != null && packagType_id == 'accounting_npackagtype_combox') { // 判断是否为内包装袋下拉框
				var packag = new Object();
		    	packag.index = number;
		    	packag.npackagType = '';
		    	packag.elseName = '';
		    	packag.price = '';
		    	packag.texture = '';
		    	packag.kgPrice = '';
		    	packag.weightProportion = '';
		    	packag.materialSize = '';
		    	packag.weight = '';
		    	packag.unitsPrice = '';
		    	packag.quantity = '';
		    	packag.technologyRequirements = '';
		    	packag.remarks = '';
		    	$('#accounting_npackagtype_elsename_input_' + number).val('');
				$('#accounting_npackagtype_elsename_input_' + number).hide();
				$('#accounting_npackagtype_elsename_input_' + number).attr("disabled","inline");
				if (packagtype_value == 'W') {
					addAccountingFn.append_accounting_packagType('accounting_npackagtype_region_table_td_', number, null);
					addAccountingFn.append_accounting_packagType('accounting_npackagtype_remarks_table_td_', number, null);
				} else if (packagtype_value == 'FHD_JM') { // 复合袋-卷膜
					addAccountingFn.append_accounting_npackagType_fhdjm_region_table_td(packag);
				} else if (packagtype_value == 'FHD_LSM') { // 复合袋-拉伸膜
					addAccountingFn.append_accounting_npackagType_fhdjm_region_table_td(packag);
				} else if (packagtype_value == 'FHD_ZD') { // 复合袋-制袋
					addAccountingFn.append_accounting_npackagType_fhdzd_region_table_td(packag);
				} else if (packagtype_value == 'ST') { // 塑托
					addAccountingFn.append_accounting_npackagType_fhdst_region_table_td(packag);
				} else if (packagtype_value == 'TYJ') { // 脱氧剂
					addAccountingFn.append_accounting_npackagType_tyj_region_table_td(packag);
				} else if (packagtype_value == 'NDD') { // 内胆袋
					addAccountingFn.append_accounting_npackagType_ndd_region_table_td(packag);
				} else if (packagtype_value == 'BQ') { // 标签
					addAccountingFn.append_accounting_npackagType_bq_region_table_td(packag);
				} else if (packagtype_value == 'ZZL') { // 纸张类
					addAccountingFn.append_accounting_npackagType_bq_region_table_td(packag);
				} else if (packagtype_value == 'ELSE') { // 其它
					addAccountingFn.append_accounting_npackagType_else_region_table_td(packag);
					$('#accounting_npackagtype_elsename_input_' + number).show();
					$('#accounting_npackagtype_elsename_input_' + number).removeAttr('disabled');
				}
			} else if (packagType_id != null && packagType_id == 'accounting_wpackagtype_combox') { // 判断是否为内包装袋下拉框
				var packag = new Object();
		    	packag.index = number;
		    	packag.npackagType = '';
		    	packag.elseName = '';
		    	packag.price = '';
		    	packag.unitsPrice = '';
		    	packag.useQuantity = '';
		    	packag.texture = '';
		    	packag.length = '';
		    	packag.width = '';
		    	packag.height = '';
		    	packag.quantity = '';
		    	packag.ylUnitsPrice = '';
		    	packag.specification = '';
		    	packag.remarks = '';
		    	$('#accounting_wpackagtype_elsename_input_' + number).val('');
				$('#accounting_wpackagtype_elsename_input_' + number).hide();
				$('#accounting_wpackagtype_elsename_input_' + number).attr("disabled","inline");
				if (packagtype_value == 'W') {
					addAccountingFn.append_accounting_packagType('accounting_wpackagtype_region_table_td_', number, null);
					addAccountingFn.append_accounting_packagType('accounting_wpackagtype_remarks_table_td_', number, null);
				} else if (packagtype_value == 'FXD') { // 封箱带
					addAccountingFn.append_accounting_wpackagType_fxd_region_table_td(packag);
				} else if (packagtype_value == 'ZX') { // 纸箱
					addAccountingFn.append_accounting_wpackagType_zx_region_table_td(packag);
				} else if (packagtype_value == 'ELSE') { // 其它
					addAccountingFn.append_accounting_wpackagType_else_region_table_td(packag);
					$('#accounting_wpackagtype_elsename_input_' + number).show();
					$('#accounting_wpackagtype_elsename_input_' + number).removeAttr('disabled');
				}
			}
		}
	},
	
	/**
	 * 增加内包装类型
	 */
	addNPackagType : function() {
		var accounting_npackagtype_combox = $('select[id^=accounting_npackagtype_combox_]').length;
		if (accounting_npackagtype_combox >= 10) {
			return;
		}
		npackagTypeCount++;
		var npackagTypeTemplate = 
				'<tr id="accounting_npackagtype_table_tr_'+ npackagTypeCount +'">' +
					'<td style="width: 210px;">' +
						'内包装名称: ' +
						'<select npackagType="0" class="easyui-combobox" id="accounting_npackagtype_combox_'+ npackagTypeCount +'" name="accountingNPackagList['+ npackagTypeCount +'].npackagType" style="width: 104px;" data-options="valueField:\'id\',textField:\'text\',value:\'W\',panelHeight:\'auto\',editable:false,url:\'businessComBox_MerchandiseNPackag_5.html\',onSelect:function(){addAccountingFn.packagTypeOnSelect(this);}" />' +
						'<br><input id="accounting_npackagtype_elsename_input_'+ npackagTypeCount +'" name="accountingNPackagList['+ npackagTypeCount +'].elseName" class="easyui-validatebox" style="width: 100px; display:none;" disabled data-options="required:true" validtype="length[0,10]" />' +
					'</td>' +
					'<td id="accounting_npackagtype_units_table_td_'+ npackagTypeCount +'">'+ addAccountingFn.getQuantityUnits() +'</td>' +
					'<td id="accounting_npackagtype_region_table_td_'+ npackagTypeCount +'"></td>' +
					'<td id="accounting_npackagtype_remarks_table_td_'+ npackagTypeCount +'" style="width: 442px;"></td>' +
					'<td><a class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true" onclick="addAccountingFn.delNPackagType('+ npackagTypeCount +')" /></td>' +
				'</tr>';
		$("#accounting_npackagtype_table").append(npackagTypeTemplate);
		$.parser.parse($('#accounting_npackagtype_table_tr_' + npackagTypeCount));
	},
	/**
	 * 删除内包装类型
	 * 
	 * @param _id
	 */
	delNPackagType : function(_id) {
		$("#accounting_npackagtype_table_tr_" + _id).remove();
	},
	
	/**
	 * 内包装类型-append内容(复合袋-卷膜/拉伸膜)
	 * 
	 * @param packag
	 */
	append_accounting_npackagType_fhdjm_region_table_td : function(packag) {
		var accounting_npackagtype_fhdjm_region_input_template = '<input id="accounting_npackagtype_region_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].price" value="'+ packag.price +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.nwpPackagTypeSubtotal" />';
		var accounting_npackagtype_fhdjm_remarks_table_template = 
				'<table>' +
					'<tr>' +
						'<th style="width: 100px"><div>具体材质&厚度:</div></th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_texture_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].texture" value="'+ packag.texture +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>公斤价格(元):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_kgprice_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].kgPrice" value="'+ packag.kgPrice +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>重量占比(%):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_weightproportion_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].weightProportion" value="'+ packag.weightProportion +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>备注:</th>' +
						'<td>' +
							'<textarea id="accounting_npackagtype_remarks_remarks_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]">'+ packag.remarks +'</textarea>' +
						'</td>' +
					'</tr>' +
				'</table>';
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_region_table_td_', packag.index, accounting_npackagtype_fhdjm_region_input_template);
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_remarks_table_td_', packag.index, accounting_npackagtype_fhdjm_remarks_table_template);
	},
	
	/**
	 * 内包装类型-append内容(复合袋-制袋)
	 * 
	 * @param packag
	 */
	append_accounting_npackagType_fhdzd_region_table_td : function(packag) {
		var accounting_npackagtype_fhdzd_region_input_template = '<input id="accounting_npackagtype_region_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].price" value="'+ packag.price +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.nwpPackagTypeSubtotal" />';
		var accounting_npackagtype_fhdzd_remarks_table_template = 
				'<table>' +
					'<tr>' +
						'<th style="width: 100px"><div>具体材质&厚度:</div></th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_texture_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].texture" value="'+ packag.texture +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>尺寸(cm):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_materialsize_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].materialSize" value="'+ packag.materialSize +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>单个克重(g):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_weight_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].weight" value="'+ packag.weight +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:function(){addAccountingFn.kgPriceCompute(this)}" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>单价(元):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_unitsprice_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].unitsPrice" value="'+ packag.unitsPrice +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:function(){addAccountingFn.kgPriceCompute(this)}" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>公斤价格(元):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_kgprice_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].kgPrice" value="'+ packag.kgPrice +'" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4,min:0,max:9999999999" readonly="readonly" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>数量:</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_quantity_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].quantity" value="'+ packag.quantity +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>备注:</th>' +
						'<td>' +
							'<textarea id="accounting_npackagtype_remarks_remarks_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]">'+ packag.remarks +'</textarea>' +
						'</td>' +
					'</tr>' +
				'</table>';
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_region_table_td_', packag.index, accounting_npackagtype_fhdzd_region_input_template);
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_remarks_table_td_', packag.index, accounting_npackagtype_fhdzd_remarks_table_template);
	},
	
	/**
	 * 内包装类型-append内容(复合袋-塑托)
	 * 
	 * @param packag
	 */
	append_accounting_npackagType_fhdst_region_table_td : function(packag) {
		var accounting_npackagtype_fhdst_region_input_template = '<input id="accounting_npackagtype_region_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].price" value="'+ packag.price +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.nwpPackagTypeSubtotal" />';
		var accounting_npackagtype_fhdst_remarks_table_template = 
				'<table>' +
					'<tr>' +
						'<th style="width: 100px"><div>具体材质&厚度:</div></th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_texture_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].texture" value="'+ packag.texture +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>单个克重(g):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_weight_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].weight" value="'+ packag.weight +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>公斤价格(元):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_kgprice_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].kgPrice" value="'+ packag.kgPrice +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>重量占比(%):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_weightproportion_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].weightProportion" value="'+ packag.weightProportion +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>备注:</th>' +
						'<td>' +
							'<textarea id="accounting_npackagtype_remarks_remarks_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]">'+ packag.remarks +'</textarea>' +
						'</td>' +
					'</tr>' +
				'</table>';
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_region_table_td_', packag.index, accounting_npackagtype_fhdst_region_input_template);
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_remarks_table_td_', packag.index, accounting_npackagtype_fhdst_remarks_table_template);
	},
	
	/**
	 * 内包装类型-append内容(复合袋-脱氧剂)
	 * 
	 * @param packag
	 */
	append_accounting_npackagType_tyj_region_table_td : function(packag) {
		var accounting_npackagtype_tyj_region_input_template = '<input id="accounting_npackagtype_region_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].price" value="'+ packag.price +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.nwpPackagTypeSubtotal" />';
		var accounting_npackagtype_tyj_remarks_table_template = 
				'<table>' +
					'<tr>' +
						'<th style="width: 100px">单个克重(g):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_weight_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].weight" value="'+ packag.weight +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>尺寸(cm):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_materialsize_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].materialSize" value="'+ packag.materialSize +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>单价(元):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_unitsprice_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].unitsPrice" value="'+ packag.unitsPrice +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>数量:</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_quantity_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].quantity" value="'+ packag.quantity +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>备注:</th>' +
						'<td>' +
							'<textarea id="accounting_npackagtype_remarks_remarks_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]">'+ packag.remarks +'</textarea>' +
						'</td>' +
					'</tr>' +
				'</table>';
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_region_table_td_', packag.index, accounting_npackagtype_tyj_region_input_template);
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_remarks_table_td_', packag.index, accounting_npackagtype_tyj_remarks_table_template);
	},
	
	/**
	 * 内包装类型-append内容(复合袋-内胆袋)
	 * 
	 * @param packag
	 */
	append_accounting_npackagType_ndd_region_table_td : function(packag) {
		var accounting_npackagtype_ndd_region_input_template = '<input id="accounting_npackagtype_region_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].price" value="'+ packag.price +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.nwpPackagTypeSubtotal" />';
		var accounting_npackagtype_ndd_remarks_table_template = 
				'<table>' +
					'<tr>' +
						'<th style="width: 100px"><div>具体材质&厚度:</div></th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_texture_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].texture" value="'+ packag.texture +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>尺寸(cm):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_materialsize_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].materialSize" value="'+ packag.materialSize +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>单价(元):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_unitsprice_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].unitsPrice" value="'+ packag.unitsPrice +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>数量:</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_quantity_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].quantity" value="'+ packag.quantity +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>备注:</th>' +
						'<td>' +
							'<textarea id="accounting_npackagtype_remarks_remarks_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]">'+ packag.remarks +'</textarea>' +
						'</td>' +
					'</tr>' +
				'</table>';
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_region_table_td_', packag.index, accounting_npackagtype_ndd_region_input_template);
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_remarks_table_td_', packag.index, accounting_npackagtype_ndd_remarks_table_template);
	},
	
	/**
	 * 内包装类型-append内容(复合袋-标签/纸张类)
	 * 
	 * @param packag
	 */
	append_accounting_npackagType_bq_region_table_td : function(packag) {
		var accounting_npackagtype_bq_region_input_template = '<input id="accounting_npackagtype_region_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].price" value="'+ packag.price +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.nwpPackagTypeSubtotal" />';
		var accounting_npackagtype_bq_remarks_table_template = 
				'<table>' +
					'<tr>' +
						'<th style="width: 100px"><div>具体材质&克重:</div></th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_texture_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].texture" value="'+ packag.texture +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>尺寸(cm):</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_materialsize_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].materialSize" value="'+ packag.materialSize +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>数量:</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_quantity_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].quantity" value="'+ packag.quantity +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>工艺要求:</th>' +
						'<td>' +
							'<input id="accounting_npackagtype_remarks_technologyrequirements_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].technologyRequirements" value="'+ packag.technologyRequirements +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>备注:</th>' +
						'<td>' +
							'<textarea id="accounting_npackagtype_remarks_remarks_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]">'+ packag.remarks +'</textarea>' +
						'</td>' +
					'</tr>' +
				'</table>';
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_region_table_td_', packag.index, accounting_npackagtype_bq_region_input_template);
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_remarks_table_td_', packag.index, accounting_npackagtype_bq_remarks_table_template);
	},
	
	/**
	 * 内包装类型-append内容(其他)
	 * 
	 * @param packag
	 */
	append_accounting_npackagType_else_region_table_td : function(packag) {
		var accounting_npackagtype_else_region_input_template = '<input id="accounting_npackagtype_region_input_"'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].price" value="'+ packag.price +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.nwpPackagTypeSubtotal" />';
		var accounting_npackagtype_else_remarks_table_template = 
				'<table>' +
					'<tr>' +
						'<th style="width: 100px">备注:</th>' +
						'<td>' +
							'<textarea id="accounting_npackagtype_remarks_remarks_input_'+ packag.index +'" name="accountingNPackagList['+ packag.index +'].remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]">'+ packag.remarks +'</textarea>' +
						'</td>' +
					'</tr>' +
				'</table>';
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_region_table_td_', packag.index, accounting_npackagtype_else_region_input_template);
		addAccountingFn.append_accounting_packagType('accounting_npackagtype_remarks_table_td_', packag.index, accounting_npackagtype_else_remarks_table_template);
	},
	
	/**
	 * 增加外包装类型
	 */
	addWPackagType : function() {
		var accounting_wpackagtype_combox = $('select[id^=accounting_wpackagtype_combox_]').length;
		if (accounting_wpackagtype_combox >= 10) {
			return;
		}
		wpackagTypeCount++;
		var wpackagTypeTemplate = 
				'<tr id="accounting_wpackagtype_table_tr_'+ wpackagTypeCount +'">' +
					'<td>' +
						'外包装名称: ' +
						'<select npackagType="0" class="easyui-combobox" id="accounting_wpackagtype_combox_'+ wpackagTypeCount +'" name="accountingWPackagList['+ wpackagTypeCount +'].wpackagType" style="width: 104px;" data-options="valueField:\'id\',textField:\'text\',value:\'W\',panelHeight:\'auto\',editable:false,url:\'businessComBox_MerchandiseWPackag_5.html\',onSelect:function(){addAccountingFn.packagTypeOnSelect(this);}" />' +
						'<br><input id="accounting_wpackagtype_elsename_input_'+ wpackagTypeCount +'" name="accountingWPackagList['+ wpackagTypeCount +'].elseName" class="easyui-validatebox" style="width: 100px; display:none;" disabled data-options="required:true" validtype="length[0,10]" />' +
					'</td>' +
					'<td id="accounting_wpackagtype_units_table_td_'+ wpackagTypeCount +'">'+ addAccountingFn.getQuantityUnits() +'</td>' +
					'<td id="accounting_wpackagtype_region_table_td_'+ wpackagTypeCount +'"></td>' +
					'<td id="accounting_wpackagtype_remarks_table_td_'+ wpackagTypeCount +'"></td>' +
					'<td><a class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true" onclick="addAccountingFn.delWPackagType('+ wpackagTypeCount +')" /></td>' +
				'</tr>';
		$("#accounting_wpackagtype_table").append(wpackagTypeTemplate);
		$.parser.parse($('#accounting_wpackagtype_table_tr_' + wpackagTypeCount));
	},

	/**
	 * 删除外包装类型
	 * 
	 * @param _id
	 */
	delWPackagType : function(_id) {
		$("#accounting_wpackagtype_table_tr_" + _id).remove();
	},
	
	/**
	 * 外包装类型-append内容(封箱带)
	 * 
	 * @param packag
	 */
	append_accounting_wpackagType_fxd_region_table_td : function(packag) {
		var accounting_wpackagtype_fxd_region_input_template = '<input id="accounting_wpackagtype_region_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].price" value="'+ packag.price +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.nwpPackagTypeSubtotal" />';
		var accounting_wpackagtype_fxd_remarks_table_template = 
				'<table>' +
					'<tr>' +
						'<th style="width: 100px">单价(元/米):</th>' +
						'<td>' +
							'<input id="accounting_wpackagtype_remarks_unitsprice_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].unitsPrice" value="'+ packag.unitsPrice +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>使用量(元/米):</th>' +
						'<td>' +
							'<input id="accounting_wpackagtype_remarks_usequantity_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].useQuantity" value="'+ packag.useQuantity +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>备注:</th>' +
						'<td>' +
							'<textarea id="accounting_wpackagtype_remarks_remarks_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]">'+ packag.remarks +'</textarea>' +
						'</td>' +
					'</tr>' +
				'</table>';
		addAccountingFn.append_accounting_packagType('accounting_wpackagtype_region_table_td_', packag.index, accounting_wpackagtype_fxd_region_input_template);
		addAccountingFn.append_accounting_packagType('accounting_wpackagtype_remarks_table_td_', packag.index, accounting_wpackagtype_fxd_remarks_table_template);
	},
	
	/**
	 * 外包装类型-append内容(纸箱)
	 * 
	 * @param packag
	 */
	append_accounting_wpackagType_zx_region_table_td : function(packag) {
		var accounting_wpackagtype_zx_region_input_template = '<input id="accounting_wpackagtype_region_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].price" value="'+ packag.price +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.nwpPackagTypeSubtotal" />';
		var accounting_wpackagtype_zx_remarks_table_template = 
				'<table>' +
					'<tr>' +
						'<th style="width: 100px"><div>具体材质说明:</div></th>' +
						'<td>' +
							'<input id="accounting_wpackagtype_remarks_texture_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].texture" value="'+ packag.texture +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>长(cm):</th>' +
						'<td>' +
							'<input id="accounting_wpackagtype_remarks_length_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].length" value="'+ packag.length +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:function(){addAccountingFn.areaCompute(this);}" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>宽(cm):</th>' +
						'<td>' +
							'<input id="accounting_wpackagtype_remarks_width_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].width" value="'+ packag.width +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:function(){addAccountingFn.areaCompute(this);}" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>高(cm):</th>' +
						'<td>' +
							'<input id="accounting_wpackagtype_remarks_height_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].height" value="'+ packag.height +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:function(){addAccountingFn.areaCompute(this);}" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>纸箱用料面积<br>(㎡):</th>' +
						'<td>' +
							'<input id="accounting_wpackagtype_remarks_area_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].area" value="'+ packag.area +'" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4,min:0,max:9999999999" readonly="readonly" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>单价(元/只):</th>' +
						'<td>' +
							'<input id="accounting_wpackagtype_remarks_unitsprice_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].unitsPrice" value="'+ packag.unitsPrice +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:function(){addAccountingFn.areaCompute(this);}" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>纸箱用料单价<br>(元/㎡):</th>' +
						'<td>' +
							'<input id="accounting_wpackagtype_remarks_ylunitsprice_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].ylUnitsPrice" value="'+ packag.ylUnitsPrice +'" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4,min:0,max:9999999999" readonly="readonly" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>箱规:</th>' +
						'<td>' +
							'<input id="accounting_wpackagtype_remarks_specification_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].specification" value="'+ packag.specification +'" class="easyui-validatebox" style="width: 100px;" />' +
						'</td>' +
					'</tr>' +
					'<tr>' +
						'<th>备注:</th>' +
						'<td>' +
							'<textarea id="accounting_wpackagtype_remarks_remarks_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]">'+ packag.remarks +'</textarea>' +
						'</td>' +
					'</tr>' +
				'</table>';
		addAccountingFn.append_accounting_packagType('accounting_wpackagtype_region_table_td_', packag.index, accounting_wpackagtype_zx_region_input_template);
		addAccountingFn.append_accounting_packagType('accounting_wpackagtype_remarks_table_td_', packag.index, accounting_wpackagtype_zx_remarks_table_template);
	},
	
	/**
	 * 外包装类型-append内容(其他)
	 * 
	 * @param packag
	 */
	append_accounting_wpackagType_else_region_table_td : function(packag) {
		var accounting_wpackagtype_else_region_input_template = '<input id="accounting_wpackagtype_region_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].price" value="'+ packag.price +'" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.nwpPackagTypeSubtotal" />';
		var accounting_wpackagtype_else_remarks_table_template = 
				'<table>' +
					'<tr>' +
						'<th style="width: 100px">备注:</th>' +
						'<td>' +
							'<textarea id="accounting_wpackagtype_remarks_remarks_input_'+ packag.index +'" name="accountingWPackagList['+ packag.index +'].remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]">'+ packag.remarks +'</textarea>' +
						'</td>' +
					'</tr>' +
				'</table>';
		addAccountingFn.append_accounting_packagType('accounting_wpackagtype_region_table_td_', packag.index, accounting_wpackagtype_else_region_input_template);
		addAccountingFn.append_accounting_packagType('accounting_wpackagtype_remarks_table_td_', packag.index, accounting_wpackagtype_else_remarks_table_template);
	},
	
	
	/**
	 * 根据元素id，append内容
	 * 
	 * @param _id
	 * @param number
	 * @param template
	 */
	append_accounting_packagType : function(_id, number, template) {
		$('#' + _id + number).empty();
		$('#' + _id + number).append(template);
		$.parser.parse($('#' + _id + number));
	},
	
	/**
	 * 增加损耗类型
	 */
	addWastageType : function() {
		var accounting_npackagtype_combox = $('input[id^=accounting_wastagetype_input_]').length;
		if (accounting_npackagtype_combox >= 10) {
			return;
		}
		wastageTypeCount++;
		var wastageTypeTemplate = 
				'<tr id="accounting_wastagetype_table_tr_'+ wastageTypeCount +'">' +
					'<td>' +
						'损耗类型: <input id="accounting_wastagetype_input_'+ wastageTypeCount +'" name="accountingWastageList['+ wastageTypeCount +'].wastageType" class="easyui-validatebox" style="width: 100px;" />' +
					'</td>' +
					'<td id="accounting_wastagetype_units_td_'+ wastageTypeCount +'">'+ addAccountingFn.getQuantityUnits() +'</td>' +
					'<td>' +
						'<input id="accounting_wastagetype_region_input_'+ wastageTypeCount +'" name="accountingWastageList['+ wastageTypeCount +'].price" class="easyui-numberbox" style="width: 100px;" data-options="precision:4,min:0,max:9999999999,onChange:addAccountingFn.total" />' +
					'</td>' +
					'<td>' +
						'<textarea id="accounting_wastagetype_remarks_input_'+ wastageTypeCount +'" name="accountingWastageList['+ wastageTypeCount +'].remarks" class="easyui-validatebox" style="width: 420px; height: 50px;" validtype="length[0,330]"></textarea>' +
					'</td>' +
					'<td>' +
						'<a class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true" onclick="addAccountingFn.delWastageType('+ wastageTypeCount +')" />' +
					'</td>' +
				'</tr>';
		$("#accounting_wastagetype_table").append(wastageTypeTemplate);
		$.parser.parse($('#accounting_wastagetype_table_tr_' + wastageTypeCount));
	},
	
	/**
	 * 删除损耗类型
	 * 
	 * @param _id
	 */
	delWastageType : function(_id) {
		$("#accounting_wastagetype_table_tr_" + _id).remove();
	}
};

var editIndex = undefined;
var ingredientItemFn = {
	endEditing : function() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#ingredientItem_grid').datagrid('validateRow', editIndex)) {
			var ed = $('#ingredientItem_grid').datagrid('getEditor', {
				index : editIndex,
				field : 'materialType'
			});
			var text = $(ed.target).combobox('getText');
			$('#ingredientItem_grid').datagrid('getRows')[editIndex]['text'] = text;
			$('#ingredientItem_grid').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	},
	onClickRow : function(index, data) {
		if (editIndex != index) {
			if (ingredientItemFn.endEditing()) {
				$("#ingredientItem_grid").datagrid('selectRow', index).datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$("#ingredientItem_grid").datagrid('selectRow', editIndex);
			}
		}
	},
	append : function() {
		if (ingredientItemFn.endEditing()) {
			$('#ingredientItem_grid').datagrid('appendRow', {
				status : 'P'
			});
			editIndex = $('#ingredientItem_grid').datagrid('getRows').length - 1;
			$('#ingredientItem_grid').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
		}
	},
	removeit : function() {
		if (editIndex == undefined) {
			return

		}
		$('#ingredientItem_grid').datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
		editIndex = undefined;
	}
};