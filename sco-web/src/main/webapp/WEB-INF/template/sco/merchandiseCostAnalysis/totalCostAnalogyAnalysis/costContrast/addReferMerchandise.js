var addReferMerchandiseGridUtil = null; // 添加参照品商品grid工具
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
$(document).ready(
		function() {
			addReferMerchandiseGridUtil = utils.grid($('#addReferMerchandise_grid'));
			addReferMerchandiseGridUtil.registerExtFilters(
				"refMSupplierCode","refMSupplierName", "refMMerchandiseCode", "refMMerchandiseName",
				"dxRoleCode", "dlRoleCode",
				"minApproveDate", "maxApproveDate","minCreateDate","maxCreateDate",
				"centreTypeCodeElse","smallTypeCode", "detailTypeCode", "fineTypeCode",
				"purchaseDepartments","netWeight","purchaseType","saleType",
				"quotedCurrency");
			addReferMerchandiseGridUtil.initFilters({
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
					$('#addReferMerchandise_grid').datagrid("clearSelections");
				}
			});
			
			$("input[name='lastrecord']").click(function(){
				if ($(this).attr('checked') == 'checked') {
					$('#minCreateDate').combo("clear");
					$('#maxCreateDate').combo("clear");
					$('#minApproveDate').combo("clear");
					$('#maxApproveDate').combo("clear");
					$('#minCreateDate').combo("disable");
					$('#maxCreateDate').combo("disable");
					$('#minApproveDate').combo("disable");
					$('#maxApproveDate').combo("disable");
				}else{
					$('#minCreateDate').combo("enable");
					$('#maxCreateDate').combo("enable");
					$('#minApproveDate').combo("enable");
					$('#maxApproveDate').combo("enable");
				}
			});
		});
//添加参照品商品功能方法
addReferMerchandiseFn = {
	search : function() {
		var param = addReferMerchandiseGridUtil.getFilterValue();
		param.lastrecord = $("input[name='lastrecord']").attr("checked");
		$('#addReferMerchandise_grid').datagrid('load', param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#minCreateDate').combo("enable");
		$('#maxCreateDate').combo("enable");
		$('#minApproveDate').combo("enable");
		$('#maxApproveDate').combo("enable");
		$('#signedQty_search').form('reset');
		clearComboboxOptions();
		$('#addReferMerchandise_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		clearSearch = false;
	},
	ok : function() {
		var data = "";
		var checkedInfo = [];
		var checkedRows = $('#addReferMerchandise_grid').datagrid('getChecked');
		if (checkedRows.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
			return;
		} else {
			$.each(checkedRows,function(index, item) {
								// 创建自定义对象
								    var temp = new merchandiseObj(
										item.accountingCode,
										item.applicationCode,
										item.merchandiseCode,
										item.supplierCode);
								// 往数组里放入对象
								checkedInfo.push(temp);
							});
			data = JSON.stringify(checkedInfo).replace(/"([^"]*)"/g, "'$1'");
			$.ajax({
				type : "POST",
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
						if (obj == null) continue;
						if (obj["accounting"]["inlandImport"] != null) {
							if ($("#inlandImport").val() != obj["accounting"]["inlandImport"]) {
								$.messager.alert("提示", "不可同时选择使用非进口核算表和使用进口核算表!");
								return;
							}
						}
						var costAnalysisData = costContrastFn.getAllMerchandise();
						$.each(JSON.parse(costAnalysisData.replace(/\'/g,"\"")),function(i,pData){
							$.each(data,function(j,jData){
								if (jData["accounting"]["accountingCode"] != null) {
									if (jData["accounting"]["accountingCode"] == pData["accountingCode"]) {
										pass = false;
									}
								}else{
									if (jData["accounting"]["merchandiseCode"] == pData["merchandiseCode"]
									  &&jData["accounting"]["supplierCode"] == pData["supplierCode"]
									 &&jData["accounting"]["applicationCode"] == pData["applicationCode"]
									) {
										pass = false;
									}
								}
							})
						});
					}
					if (pass) {
						addTableInfo(data, 'referMerchandise');
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
		var rows = $('#addReferMerchandise_grid').datagrid("getSelections");
		if (rows.length > 0) {
			$("a[id='ok']").linkbutton("enable");
		} else {
			$("a[id='ok']").linkbutton("disable");
		}
	}
};
