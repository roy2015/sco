var materialWarnConfigGridUtil = null;
$(document).ready(function() {
	$("a[id='insert']").linkbutton("disable");
	$("a[id='remove']").linkbutton("disable");
	materialWarnConfigGridUtil = utils.grid($('#materialWarnConfig_grid'));
	materialWarnConfigGridUtil.registerExtFilters("materialBigTypeCode",
			"materialSmallTypeCode",
			"merchandiseCode",
			"websiteMaterialCode",
			"warnType","minDate",
			"maxDate","createby");
	materialWarnConfigGridUtil.initFilters({
		onClickRow : materialWarnConfigFn.setButtonState, // 设置按钮状态
		onCheck : materialWarnConfigFn.setButtonState, // 设置按钮状态
		onUncheck : materialWarnConfigFn.setButtonState, // 设置按钮状态
		onSelectAll : materialWarnConfigFn.setButtonState, // 设置按钮状态
		onUnselectAll : materialWarnConfigFn.setButtonState, // 设置按钮状态
		onLoadSuccess : function() {
			$('#materialWarnConfig_grid').datagrid("clearSelections");
			materialWarnConfigFn.setButtonState();
		}
	});
	$("#websiteMaterialName").combobox({
		onChange : function() {
			materialWarnConfigFn.setWebName();
		}
	});
});
var materialWarnConfigFn = {
	// 显示添加或修改窗体
	showForm : function(isEdit) {
		var record = materialWarnConfigGridUtil.getSelectedRecord();
		if (record == null)
			return;
		var title = '预警添加';
		var href = 'materialWarnConfig_showInsertMaterialWarnConfigForm_1.html';
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 600,
			buttons : [ {
				text : '确定',
				handler : function() {
					materialWarnConfigFn.submitForm(dlg);
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
	//设置网站名称
	setWebName:function(){
		$("#webName").combobox("setValue", "");
		$("#webName").combobox("clear");
		var materialCode = $("#websiteMaterialName").combobox("getValue");
		if (!materialCode) {
			materialCode = 'null';
		}
		$("#webName").combobox('reload', "websiteMaterial_listMaterialWebNameOption_5.html?" 
				+ "materialCode=" + materialCode);
	},
	// 添加原料行情预警设置
	showInsert : function() {
		materialWarnConfigFn.showForm(false);
	},
	// 提交新增表单
	submitForm : function(dlg) {
		var record = materialWarnConfigGridUtil.getSelectedRecord();
		var url = 'materialWarnConfig_insertMaterialWarnConfig_2.html';
		var warnType = $("#warnTypeSelect").combobox('getValue');
		var thresholdValue = $("input[name='thresholdValue'").val();
		if (warnType != ""  && thresholdValue != "") {
			if(thresholdValue==""){
				thresholdValue=0;
			}
			utils.post(url, {
				materialSmallTypeCode : record.materialSmallTypeCode,
				websiteCode : record.websiteCode,
				materialCode : record.materialCode,
				thresholdValue : thresholdValue,
				warnType : warnType,
				materialBigTypeCode : record.materialBigTypeCode
			}, function() {
				dlg.dialog('close');
				materialWarnConfigGridUtil.refresh();
			});
		} else {
			$.messager.alert('操作失败', "必填项未填完整");
		}
	},
	// 删除原料行情预警设置
	remove : function() {
		var record = materialWarnConfigGridUtil.getSelectedIdArr('configCode');
		if (record == null)
			return;
		utils.confirm("操作确认", "确认删除原料行情预警设置?", function() {
			utils.post("materialWarnConfig_deleteMaterialWarnConfig_2.html?configCode=" + record, null, function() {
				materialWarnConfigGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	// 查询
	search : function() {
		var url = "materialWarnConfig_listMaterialWarnConfig_2.html";
		var warnType=$("#warnType").combobox('getValue');
//		if(warnType=="W"){
//			warnType="1";
//		}
		$('#materialWarnConfig_grid').datagrid({
			url : url,
			queryParams :{
				materialBigCode : $("#materialBigTypeCode").combobox('getValue'),
				materialSmallCode : $("#materialSmallTypeCode").combobox('getValue'),
				materialCode : $("#websiteMaterialName").combobox('getValue'),
				websiteCode : $("#webName").combobox('getValue'),
				warnType :warnType,
				created : $("#minDate").datebox('getValue'),
				createds : $("#maxDate").datebox('getValue'),
				createby : $("#createby").val()
			}
		});
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		$("input:text").val("");
		$(".filterSelect").combobox('setValue', '');
		$("#minDate").datebox("setValue","");
		$("#maxDate").datebox("setValue","");
		$('#materialWarnConfig_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
	},
	refresh : function() {
		$('#materialWarnConfig_grid').treegrid('reload');
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#materialWarnConfig_grid').datagrid("getSelections");
		if (rows.length >= 1) {
			$("a[id='insert']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
		} else {
			$("a[id='insert']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
		}
	}
};