var merchandiseDxRoleGridUtil = null;
var merchandiseDlRoleGridUtil = null;
$(document).ready(function() {
	merchandiseDxRoleGridUtil = utils.grid($('#merchandiseDxRole_grid'));
	merchandiseDxRoleGridUtil.initFilters({
		onClickRow : merchandiseDxRoleFn.setButtonState, // 设置按钮状态
		onCheck : merchandiseDxRoleFn.setButtonState, // 设置按钮状态
		onUncheck : merchandiseDxRoleFn.setButtonState, // 设置按钮状态
		onSelectAll : merchandiseDxRoleFn.setButtonState, // 设置按钮状态
		onUnselectAll : merchandiseDxRoleFn.setButtonState, // 设置按钮状态
		onLoadSuccess : function() {
			$('#merchandiseDxRole_grid').datagrid("clearSelections");
			merchandiseDxRoleFn.setButtonState();
		}
	});
	merchandiseDlRoleGridUtil = utils.grid($('#merchandiseDlRole_grid'));
	merchandiseDlRoleGridUtil.initFilters({
		onClickRow : merchandiseDlRoleFn.setButtonState, // 设置按钮状态
		onCheck : merchandiseDlRoleFn.setButtonState, // 设置按钮状态
		onUncheck : merchandiseDlRoleFn.setButtonState, // 设置按钮状态
		onSelectAll : merchandiseDlRoleFn.setButtonState, // 设置按钮状态
		onUnselectAll : merchandiseDlRoleFn.setButtonState, // 设置按钮状态
		onLoadSuccess : function() {
			$('#merchandiseDlRole_grid').datagrid("clearSelections");
			merchandiseDlRoleFn.setButtonState();
		}
	});
});
var merchandiseDxRoleFn = {
	// 显示添加或修改窗体
	showForm : function(isEdit) {
		var title = '添加商品定性角色';
		var href = 'merchandiseDxRole_showInsertMerchandiseDxRoleForm_1.html';
		var dlg = utils.showDlg({
			title : title,
			width:250,
			href : href,
			buttons : [ {
				text : '提交',
				handler : function() {
					merchandiseDxRoleFn.submitForm(dlg);
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
	// 添加商品定性角色
	showInsert : function() {
		merchandiseDxRoleFn.showForm(false);
	},
	// 提交新增或修改表单
	submitForm : function(dlg) {
		var roleName = $("input[name='roleName']").val().replace(/\s+/g,"");
		if(roleName==""){
			$.messager.alert('提示', '角色名称不能为空或者空格');
			return;
		}
		var url = 'merchandiseDxRole_insertMerchandiseDxRole_2.html';
		utils.form("merchandiseDxRole_form").submit(url, null, function() {
			dlg.dialog('close');
			merchandiseDxRoleGridUtil.refresh();
		});
	},
	// 删除商品定性角色
	remove : function() {
		var record = merchandiseDxRoleGridUtil.getSelectedIdArr('roleCode');
		if (record == null)
			return;
		utils.confirm("操作确认", "确认删除商品定性角色?", function() {
			utils.post("merchandiseDxRole_deleteMerchandiseDxRole_2.html?roleCode=" + record, null, function() {
				merchandiseDxRoleGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	// 清除查询
	clearFilter : function() {
		merchandiseDxRoleGridUtil.clearFilter();
	},
	//刷新
	refresh : function() {
		$('#merchandiseDxRole_grid').treegrid('reload');
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#merchandiseDxRole_grid').datagrid("getSelections");
		if (rows.length >= 1) {
			$("a[id='dx_remove']").linkbutton("enable");
		} else {
			$("a[id='dx_remove']").linkbutton("disable");
		}
	}
};
var merchandiseDlRoleFn = {
	// 显示添加或修改窗体
	showForm : function(isEdit) {
		var title = '添加商品定量角色';
		var href = 'merchandiseDlRole_showInsertMerchandiseDlRoleForm_1.html';
		var dlg = utils.showDlg({
			title : title,
			width:250,
			href : href,
			buttons : [ {
				text : '提交',
				handler : function() {
					merchandiseDlRoleFn.submitForm(dlg);
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
	// 添加商品定量角色
	showInsert : function() {
		merchandiseDlRoleFn.showForm(false);
	},
	// 提交新增或修改表单
	submitForm : function(dlg) {
		var roleName = $("input[name='roleName']").val().replace(/\s+/g,"");
		if(roleName==""){
			$.messager.alert('提示', '角色名称不能为空或者空格');
			return;
		}
		var url = 'merchandiseDlRole_insertMerchandiseDlRole_2.html';
		utils.form("merchandiseDlRole_form").submit(url, null, function() {
			dlg.dialog('close');
			merchandiseDlRoleGridUtil.refresh();
		});
	},
	// 删除商品定量角色
	remove : function() {
		var record = merchandiseDlRoleGridUtil.getSelectedIdArr('roleCode');
		if (record == null)
			return;
		utils.confirm("操作确认", "确认删除商品定量角色?", function() {
			utils.post("merchandiseDlRole_deleteMerchandiseDlRole_2.html?roleCode=" + record, null, function() {
				merchandiseDlRoleGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	// 清除查询
	clearFilter : function() {
		merchandiseDlRoleGridUtil.clearFilter();
	},
	//刷新
	refresh : function() {
		$('#merchandiseDlRole_grid').treegrid('reload');
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#merchandiseDlRole_grid').datagrid("getSelections");
		if (rows.length >= 1) {
			$("a[id='dl_remove']").linkbutton("enable");
		} else {
			$("a[id='dl_remove']").linkbutton("disable");
		}
	}
};