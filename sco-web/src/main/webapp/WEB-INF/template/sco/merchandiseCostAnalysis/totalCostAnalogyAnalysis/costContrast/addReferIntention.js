var addReferIntentionGridUtil = null;// 添加参照品意向品grid工具
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
$(document).ready(
	function() {
		addReferIntentionGridUtil = utils.grid($('#addReferIntention_grid'));
		addReferIntentionGridUtil.registerExtFilters("refISupplierCode",
				"refISupplierName", "intentionCode", "intentionName",
				"purchaseDepartments", "rationed", "purchaseType",
				"saleType", "dxRoleCode", "dlRoleCode",
				"minQuotedDate", "maxQuotedDate", "centreTypeCodeElse",
				"smallTypeCode", "detailTypeCode", "fineTypeCode","quotedCurrency");
		addReferIntentionGridUtil.initFilters({
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
				$('#addReferIntention_grid').datagrid("clearSelections");
			}
		});
    });
//添加参照品意向品功能方法
addReferIntentionFn = {
	search : function() {
		var param = addReferIntentionGridUtil.getFilterValue();
		$('#addReferIntention_grid').datagrid('load', param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#signedQty_search').form('reset');
		clearComboboxOptions();
		$('#addReferIntention_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		clearSearch = false;
	},
	ok : function() {
		var data = "";
		var checkedInfo = [];
		var checkedRows = $('#addReferIntention_grid').datagrid('getChecked');
		if (checkedRows.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
			return;
		} else {
			$.each(checkedRows,function(index, item) {
								// 创建自定义对象
								var temp = new merchandiseObj(
										item.accountingCode,
										item.applicationCode,
										item.intentionCode,
										item.supplierCode);
								// 往数组里放入对象
								checkedInfo.push(temp);
			});
			data = JSON.stringify(checkedInfo).replace(/"([^"]*)"/g, "'$1'");
			$.ajax({type : "POST",
					url : "totalCostAnalogyAnalysis_listThisTimeApplicationMerchandise_5.html",
					data : "data=" + data,
					dataType : 'json',
					success : function(data) {
						if (data == null) {
							return ;
						}
						var pass = true;
						for ( var temp in data) {
							var obj = data[temp];
							if (obj == null||obj["accounting"]["inlandImport"] == null||obj["accounting"]["inlandImport"] == '') continue;
							if ($("#inlandImport").val() != obj["accounting"]["inlandImport"]) {
								$.messager.alert("提示", "不可同时选择使用非进口核算表和使用进口核算表!");
								return;
							}
							var costAnalysisData = costContrastFn.getAllMerchandise();
							$.each(JSON.parse(costAnalysisData.replace(/\'/g,"\"")),function(i,pData){
								$.each(data,function(j,jData){
									if (jData["accounting"]["accountingCode"] == pData["accountingCode"]) {
										pass = false;
									}
								})
							});
						}
						if (pass) {
							addTableInfo(data,'referIntention');
							$(dlg).dialog("close");
							isShowAllRemarks = false;
							isHideAllRemarks = true;
							isShowThisRemarks = false;
							isShowUnits = false;
						}else{
							$.messager.alert("提示", "您勾选的参照品已存在于成本分析中");
							return;
						}
					}
			});
		}

	},
	close : function() {
		$(dlg).dialog("close");
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#addReferIntention_grid').datagrid("getSelections");
		if (rows.length > 0) {
			$("a[id='ok']").linkbutton("enable");
		} else {
			$("a[id='ok']").linkbutton("disable");
		}
	}
};