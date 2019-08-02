var merchandisePriceGrid = null;
var firstIn = true;// 是否第一次进入页面
var clearSearch = false;// 清空查询

$(document).ready(
		function() {
			merchandisePriceGrid = utils.grid($('#merchandisePrice_grid'));
			merchandisePriceGrid.registerExtFilters("supplierCode",
					"supplierName", "merchandiseCode", "merchandiseName",
					"centreTypeCode", "smallTypeCode", "detailTypeCode",
					"fineTypeCode", "dxRoleCode", "dlRoleCode","purchaseDepartments",
					"regionCode","minDate", "maxDate");

			merchandisePriceGrid.initFilters({
				onBeforeLoad : function(obj) {
					// 清空查询
					if (clearSearch) {
						return true;
					}
					var length = $.param(obj).split("&").length;
					// 判断参数个数
					if (length < 3) {
						if (!firstIn)
							$.messager.alert("提示", "请输入至少一项查询条件");
						firstIn = false;
						return false;
					}
					if (length == 4 && $.param(obj).indexOf("order") > -1) {
						var param = intentionUtil.getFilterValue();
						if ($.param(param).length > 0) {
							return true;
						}
						$.messager.alert("提示", "请输入至少一项查询条件");
						return false;
					}
				},
				onLoadSuccess : function() {
					firstIn = false;
					return false;
				},
				onClickRow : merchandisePriceFn.setButtonState, // 设置按钮状态
				onCheck : merchandisePriceFn.setButtonState, // 设置按钮状态
				onUncheck : merchandisePriceFn.setButtonState, // 设置按钮状态
				onSelectAll : merchandisePriceFn.setButtonState, // 设置按钮状态
				onUnselectAll : merchandisePriceFn.setButtonState
			// 设置按钮状态
			});
		});

var merchandisePriceFn = {

	// 搜索按钮功能
	search : function() {
		var param = merchandisePriceGrid.getFilterValue();

		$('#merchandisePrice_grid').datagrid('load', param);
	},

	// 勾选或去掉所有
	checkedAll : function(obj) {
		if ($(obj).attr("checked") == "checked") {
			$("input[editcheck=editcheck]").attr("checked", true);
		} else {
			$("input[editcheck=editcheck]").attr("checked", false);
		}
	},

	// 去掉或勾选某个checkbox
	selectOne : function(obj) {
		if ($(obj).attr("checked") != "checked") {// 去掉勾选本条数据
			$("input[name=checkAll]").attr("checked", false);
			return;
		} else {// 若是选中本条数据，则需检查所有是否勾选
			var flag = true;
			$("input[editcheck=editcheck]").each(function(i, obj) {
				if ($(obj).attr("checked") != "checked") {
					flag = false;
					return;
				}
			});
			if (flag) {
				$("input[name=checkAll]").attr("checked", true);
			}
		}
	},

	// 清除查询
	clearFilter : function() {
		clearSearch = true;
		$('#merchandisePrice_search').form('reset');
		clearComboboxOptions();
		$('#merchandisePrice_grid').datagrid('clearSelections');
		$('#merchandisePrice_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		clearSearch = false;
	},

	// 刷新表格
	refresh : function() {
		clearSearch = true;
		$('#merchandisePrice_grid').datagrid('reload');
		clearSearch = false;
	},

	// 查看商品价格趋势
	searchPriceTrend : function() {

		var param = merchandisePriceGrid.getFilterValue();

		var regionCode = param.regionCode;
		var regionName = $("#regionCode").combobox("getText");
		var qlStartDate = param.minDate;
		var qlEndDate = param.maxDate;
		var itemValue = $("input[name='opinionItem']:checked").val();

		if (regionCode == null || regionCode == "") {
			$.messager.alert("提示", "请选择地区");
			return false;
		}

		if (qlStartDate == null || qlStartDate == "") {
			$.messager.alert("提示", "请输入查询开始时间");
			return false;
		}

		if (qlEndDate == null || qlEndDate == "") {
			$.messager.alert("提示", "请输入查询结束时间");
			return false;
		}

		if (itemValue == "") {
			$.messager.alert("提示", "请选择查询的数据类型");
			return false;
		}

		var count = 0;
		$("input[type=checkbox][name=ck]").each(function(i, box) {
			if ($(box).attr("checked") == "checked") {
				count++;
			}
		});

		if (count < 1) {
			$.messager.alert("提示", "请选择一个商品");
			return false;
		}

		if (count > 5) {
			$.messager.alert("提示", "若需查看图表，最多只能选择五个商品");
			return false;
		}

		var rows = $('#merchandisePrice_grid').datagrid("getSelections");
		var merchandiseCode = '';
		var supplierCode = '';
		for ( var num in rows) {
			if(num==rows.length - 1){
				merchandiseCode += rows[num].merchandiseCode;
				supplierCode+=rows[num].supplierCode;
			}else{
				merchandiseCode += rows[num].merchandiseCode+",";
				supplierCode+=rows[num].supplierCode+",";
			}
		}
		var tabName = '价格趋势查询结果';
		if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
			parent.activeTab(tabName);
			parent.refreshTab(tabName);
			return;
		}
		parent.addTabByUrl(tabName, 'agent', encodeURI("merchandisePriceTrend_showPriceTrendDetail_1.html?merchandiseCode="
				+ merchandiseCode
				+"&&supplierCode="
				+supplierCode
				+ "&&regionCode="
				+ regionCode
				+ "&&regionName="
				+ regionName
				+ "&&qlStartDate="
				+ qlStartDate
				+ "&&qlEndDate=" + qlEndDate + "&&itemValue=" + itemValue));
	},

	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#merchandisePrice_grid').datagrid("getSelections");
		if (rows.length == 1) {// 单选
			$("a[id='insert']").linkbutton("enable");
			$("a[id='gq']").linkbutton("enable");
			$("a[id='edit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
			$("a[id='ter']").linkbutton("enable");
			$("a[id='rev']").linkbutton("enable");
		} else if (rows.length > 1) { // 多选
			$("a[id='insert']").linkbutton("enable");
			$("a[id='gq']").linkbutton("disable");
			$("a[id='edit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("enable");
			$("a[id='ter']").linkbutton("enable");
			$("a[id='rev']").linkbutton("enable");
		} else {// 未选
			$("a[id='insert']").linkbutton("disable");
			$("a[id='gq']").linkbutton("disable");
			$("a[id='edit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
			$("a[id='ter']").linkbutton("disable");
			$("a[id='rev']").linkbutton("disable");
		}
	}
};