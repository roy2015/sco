var certificateOutofdateConfigGridUtil = null;
$(document).ready(function() {
	certificateOutofdateConfigGridUtil = utils.grid($('#certificateOutofdateConfig_grid'));
	certificateOutofdateConfigGridUtil.initFilters({
		noParamCanSort:true,
		onClickRow : certificateOutofdateConfigFn.setButtonState, // 设置按钮状态
		onCheck : certificateOutofdateConfigFn.setButtonState, // 设置按钮状态
		onUncheck : certificateOutofdateConfigFn.setButtonState, // 设置按钮状态
		onSelectAll : certificateOutofdateConfigFn.setButtonState, // 设置按钮状态
		onUnselectAll : certificateOutofdateConfigFn.setButtonState, // 设置按钮状态
		onLoadSuccess : function() {
			$('#certificateOutofdateConfig_grid').datagrid("clearSelections");
			certificateOutofdateConfigFn.setButtonState();
		}
	});

});
var certificateOutofdateConfigFn = {
	// 显示添加或修改窗体
	showForm : function(isEdit) {
		var title = '添加证件过期天数';
		var href = 'certificateOutofdateConfig_showInsertCertificateOutofdateConfigForm_1.html';
		if (isEdit) {// 显示修改窗体
			var record = certificateOutofdateConfigGridUtil.getSelectedRecord();
			if (record == null)
				return;
			title = '修改证件过期天数';
			href = 'certificateOutofdateConfig_showUpdateCertificateOutofdateConfigForm_1.html?configCode=' + record.configCode;
		}
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 320,
			buttons : [ {
				text : '保存',
				handler : function() {
					certificateOutofdateConfigFn.submitForm(dlg);
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
	// 添加证件过期提醒设置
	showInsert : function() {
		certificateOutofdateConfigFn.showForm(false);
	},
	// 修改证件过期提醒设置
	showEdit : function() {
		certificateOutofdateConfigFn.showForm(true);
	},
	// 提交新增或修改表单
	submitForm : function(dlg) {
		var configCode = $("input#configCode").val();
		var outofdate=$("input[name='outofdate']").val();
		if(outofdate==""){
			$.messager.alert('提示', '必填项为空');
			return;
		}
		var url = 'certificateOutofdateConfig_insertCertificateOutofdateConfig_2.html';
		if (configCode) {
			url = 'certificateOutofdateConfig_updateCertificateOutofdateConfig_2.html';
		}
		utils.form("certificateOutofdateConfig_form").submit(url, null, function(json) {
			dlg.dialog('close');
			certificateOutofdateConfigGridUtil.refresh();
		});
	},
	// 删除证件过期提醒设置
	remove : function() {
		var record = certificateOutofdateConfigGridUtil.getSelectedIdArr('configCode');
		if (record == null)
			return;
		utils.confirm("操作确认", "确认删除证件过期提醒设置?", function() {
			utils.post("certificateOutofdateConfig_deleteCertificateOutofdateConfig_2.html?configCode=" + record, null, function() {
				certificateOutofdateConfigGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	// 清除查询
	clearFilter : function() {
		certificateOutofdateConfigGridUtil.clearFilter();
	},
	refresh : function() {
		$('#certificateOutofdateConfig_grid').treegrid('reload');
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#certificateOutofdateConfig_grid').datagrid("getSelections");
		if (rows.length >= 1) {
			$("a[id='showEdit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
		} else {
			$("a[id='showEdit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
		}
	}
};