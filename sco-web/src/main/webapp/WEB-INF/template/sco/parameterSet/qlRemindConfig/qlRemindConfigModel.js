var qlRemindConfigGridUtil = null;
$(document).ready(function() {
	qlRemindConfigGridUtil = utils.grid($('#qlRemindConfig_grid'));
	qlRemindConfigGridUtil.initFilters({
		noParamCanSort:true,
		onClickRow : qlRemindConfigFn.setButtonState, // 设置按钮状态
		onCheck : qlRemindConfigFn.setButtonState, // 设置按钮状态
		onUncheck : qlRemindConfigFn.setButtonState, // 设置按钮状态
		onSelectAll : qlRemindConfigFn.setButtonState, // 设置按钮状态
		onUnselectAll : qlRemindConfigFn.setButtonState, // 设置按钮状态
		onLoadSuccess : function() {
			$('#qlRemindConfig_grid').datagrid("clearSelections");
			qlRemindConfigFn.setButtonState();
		}
	});
});
var qlRemindConfigFn = {
	// 显示添加或修改窗体
	showForm : function(isEdit) {
		var title = '签量提醒新增';
		var href = 'qlRemindConfig_showInsertQlRemindConfigForm_1.html';
		if (isEdit) {// 显示修改窗体
			var record = qlRemindConfigGridUtil.getSelectedRecord();
			if (record == null)
				return;
			title = '签量提醒修改';
			href = 'qlRemindConfig_showUpdateQlRemindConfigForm_1.html?configCode='
					+ record.configCode;
		}
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 400,
			height : 150,
			buttons : [ {
				text : '${action.getText("common.button.submit")}',
				handler : function() {
					qlRemindConfigFn.submitForm(dlg);
				},
				iconCls : 'save'
			}, {
				text : '${action.getText("common.button.cancel")}',
				handler : function() {
					dlg.dialog('close');
				},
				iconCls : 'cancel'
			} ]
		});
	},
	// 添加签量提醒设置
	showInsert : function() {
		qlRemindConfigFn.showForm(false);
	},
	// 修改签量提醒设置
	showEdit : function() {
		qlRemindConfigFn.showForm(true);
	},
	// 提交新增或修改表单
	submitForm : function(dlg) {
		var configCode = $("input[name='configCode']").val();
		var url = 'qlRemindConfig_insertQlRemindConfig_2.html';
		if (configCode) {
			url = 'qlRemindConfig_updateQlRemindConfig_2.html';
		}
		if ($("#thresholdValue").val() < 0) {
			$.messager.alert("提示", "报警阀值应大于0");
			return;
		}


		utils.form("qlRemindConfig_form").submit(url, null, function(data) {
				dlg.dialog('close');
				qlRemindConfigGridUtil.refresh();
		});
	},
	// 删除签量提醒设置
	remove : function() {		
		var record = qlRemindConfigGridUtil.getSelectedIdString("configCode");
		if (record == null)
			return;
		utils.confirm("操作确认", "确认删除签量提醒设置?", function() {
			utils.post("qlRemindConfig_deleteQlRemindConfig_2.html", {
				configCode : record
			}, function() {
				qlRemindConfigGridUtil.refresh();
			});
		});
	},
	refresh : function() {
		$('#qlRemindConfig_grid').treegrid('reload');
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#qlRemindConfig_grid').datagrid("getSelections");
		if (rows.length != 0) {
			$("a[id='showEdit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
		} else {
			$("a[id='showEdit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
		}
	}
};