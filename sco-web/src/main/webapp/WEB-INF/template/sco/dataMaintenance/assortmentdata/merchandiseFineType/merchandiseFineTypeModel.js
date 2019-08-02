var merchandiseFineTypeGridUtil = null;
$(document).ready(function() {
	merchandiseFineTypeGridUtil = utils.grid($('#merchandiseFineType_grid'));
	$("a[id='remove']").linkbutton("disable");
	merchandiseFineTypeGridUtil.registerExtFilters("centreTypeCode", "smallTypeCode", "detailTypeCode");
	merchandiseFineTypeGridUtil.initFilters({
		onClickRow : merchandiseFineTypeFn.setButtonState, // 设置按钮状态
		onCheck : merchandiseFineTypeFn.setButtonState, // 设置按钮状态
		onUncheck : merchandiseFineTypeFn.setButtonState, // 设置按钮状态
		onSelectAll : merchandiseFineTypeFn.setButtonState, // 设置按钮状态
		onUnselectAll : merchandiseFineTypeFn.setButtonState, // 设置按钮状态
		onLoadSuccess : function() {
			$('#merchandiseFineType_grid').datagrid("clearSelections");
			merchandiseFineTypeFn.setButtonState();
		}
	});
});
var merchandiseFineTypeFn = {
	// 显示添加或修改窗体
	showForm : function(isEdit) {
		var title = '添加细分类';
		var href = 'merchandiseFineType_showInsertMerchandiseFineTypeForm_1.html';
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 300,
			height : 200,
			buttons : [ {
				text : '保存',
				handler : function() {
					merchandiseFineTypeFn.submitForm(dlg);
				},
				iconCls : 'save'
			}, {
				text : '取消',
				handler : function() {
					dlg.dialog('close');
				},
				iconCls : 'cancel'
			} ]
		});
		$(".window-shadow").remove();
	},
	// 添加商品细分类维护
	showInsert : function() {
		merchandiseFineTypeFn.showForm(false);
	},
	// 提交新增或修改表单
	submitForm : function(dlg) {
		var detal = $("#detailType").combobox("getValue");
		var fine = $.trim($("input[name='fineTypeName']").val());
		if (detal != "") {
			if (fine != "") {
				var url = 'merchandiseFineType_insertMerchandiseFineType_2.html';
				utils.form("merchandiseFineType_form").submit(url, null, function() {
					dlg.dialog('close');
					merchandiseFineTypeGridUtil.refresh();
				});
			} else {
				$.messager.alert('提示', "必填项为空");
			}
		} else {
			$.messager.alert('提示', "必选项未选择");
		}
	},
	// 删除商品细分类维护
	remove : function() {
		var record = merchandiseFineTypeGridUtil.getSelectedIdArr('fineTypeCode');
		if (record == null)
			return;
		utils.confirm("操作确认", "确认删除商品细分类维护?", function() {
			utils.post("merchandiseFineType_deleteMerchandiseFineType_2.html?fineTypeCode=" + record, null, function() {
				merchandiseFineTypeGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	// 查询
	search : function() {
		var url = "merchandiseFineType_listMerchandiseFineType_2.html";
		var param = merchandiseFineTypeGridUtil.getFilterValue();
		$('#merchandiseFineType_grid').datagrid({
			'url' : url,
			queryParams : param
		});
		$('#merchandiseFineType_grid').datagrid("load",param);
	},
	// 清除查询
	clearFilter : function() {
		
		$(".filterSelect").combobox('setValues', '');
		$('#merchandiseFineType_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
	},
	refresh : function() {
		$('#merchandiseFineType_grid').treegrid('reload');
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#merchandiseFineType_grid').datagrid("getSelections");
		if (rows.length >= 1) {
			$("a[id='remove']").linkbutton("enable");
		} else {
			$("a[id='remove']").linkbutton("disable");
		}
	}
};