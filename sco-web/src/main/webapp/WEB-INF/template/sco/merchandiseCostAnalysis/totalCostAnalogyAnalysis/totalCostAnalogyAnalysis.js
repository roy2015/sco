var totalCostAnalogyAnalysisGridUtil = null;
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
function merchandiseObj(accountingCode,applicationCode,merchandiseCode,supplierCode,intentionCode,intentionSupplierCode){
	var obj=new Object();
	obj.accountingCode=accountingCode;
	obj.applicationCode=applicationCode;
	obj.merchandiseCode=merchandiseCode == null?intentionCode:merchandiseCode;
	obj.supplierCode=supplierCode == null?intentionSupplierCode:supplierCode;
	return obj;
	} 
/**
 * 检查是否已存在该元素
 * 
 * @param applicationMerchandiseInfo 要检查的集合
 * @param item 要检查的元素
 * @returns {Boolean} true 存在,false 不存在
 */
function isExist(applicationMerchandiseInfo,item) {
	var flag = false;
	$.each(applicationMerchandiseInfo,function(index,obj){
		if (obj.accountingCode == item.accountingCode&&obj.merchandiseCode == item.merchandiseCode&& obj.supplierCode == item.supplierCode) 
		{
			flag = true;
		}
	});
    return flag;
}
$(document).ready(
		function() {
			totalCostAnalogyAnalysisGridUtil = utils
					.grid($('#totalCostAnalogyAnalysis_grid'));
			totalCostAnalogyAnalysisGridUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName",
					"centreTypeCodeElse", "smallTypeCode", "detailTypeCode", "fineTypeCode",
					"purchaseDepartments","rationed","purchaseType","saleType",
					"accountingCode","updateby","minUpdated","maxUpdated",
					"applicationCode","applicationStatus","minApproveDate","maxApproveDate",
					"merchandiseDxRoleCode","merchandiseDlRoleCode");
			totalCostAnalogyAnalysisGridUtil.initFilters({
				onBeforeLoad : function(obj) {
					// 是否第一次进入页面
					if (firstIn) {
						firstIn = false;
						return false;
					}
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
					} else if (length == 4
							&& $.param(obj).indexOf('order') > -1) {
						return false;
					}
				},
				onLoadSuccess : function() {
					$('#totalCostAnalogyAnalysis_grid').datagrid(
							"clearSelections");
				},
			});
		});
var totalCostAnalogyAnalysisFn = {
	search : function() {
		var param = totalCostAnalogyAnalysisGridUtil.getFilterValue();
		if ($('#lastUpdated').is(':checked')) {
			param.lastUpdated = $('#lastUpdated').is(':checked');
		}
		$('#totalCostAnalogyAnalysis_grid').datagrid('load', param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#signedQty_search').form('reset');
		clearComboboxOptions();
		$('#totalCostAnalogyAnalysis_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		clearSearch = false;
	},
	// 成本对比
	costContrast : function() {
		var applicationMerchandiseInfo = [];
		//往后台发送的数据
		var data = "";
		// 获取选中的行
		var checkedRows = $('#totalCostAnalogyAnalysis_grid').datagrid(
				'getChecked');
		if (checkedRows.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
			return;
		} else {
			// 遍历选中的行
			var isExistInlandImport = false;
			var inlandImport = "";
			$.each(checkedRows, function(index, item) {
				//判断是否同时存在进口与非进口
				if (index==0) {
					inlandImport = item.inlandImport;
				}else
				{
					if (inlandImport != item.inlandImport) {
						isExistInlandImport = true;
					}
				}
				//把要进行成本分析的行放入集合
				var temp = new merchandiseObj(item.accountingCode,item.applicationCode,item.merchandiseCode,item.supplierCode,item.intentionCode ,item.intentionSupplierCode);
				if(!isExist(applicationMerchandiseInfo,temp)){
					applicationMerchandiseInfo.push(temp);
				}
			});
			if (isExistInlandImport) {
				$.messager.alert('提示','<center>不可同时选择使用非进口核算表和使用进口核算表的商品作为本次申请商品！</center>');
				return;
			}
			//转换数组
			data =JSON.stringify(applicationMerchandiseInfo).replace(/"([^"]*)"/g, "'$1'");
			var url = "totalCostAnalogyAnalysis_showCostContrast_1.html?data="+data+"&inlandImport="+inlandImport;
			var tabName = '总成本类比结果';
			if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，关闭存在的选项卡
				parent.pubTab.closeTab(tabName);
			}
			parent.addTabByUrl(tabName, 'copy', url);
		}
	},
	/**
	 * 查看核算表详情
	 */
	showLoad : function(accountingCode) {
		var url = 'accounting_showLoadAccountingForm_1.html';
		// 判断核算表编号是否为空
		if (accountingCode != null) {
			url += '?accountingCode=' + accountingCode;
		} else {
			return;
		}
		parent.addTabByUrl("核算/投料表", "agent", url);
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
	}
};