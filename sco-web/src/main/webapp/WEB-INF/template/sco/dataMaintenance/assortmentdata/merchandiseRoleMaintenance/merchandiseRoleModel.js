var merchandiseGridUtil = null;
var roleinfo = null;
$(document).ready(
		function() {
			merchandiseGridUtil = utils.grid($('#merchandise_grid'));
			$("a[id='showEdit']").linkbutton("disable");
			merchandiseGridUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", "centreTypeCode", "smallTypeCode", "detailTypeCode", "fineTypeCode",
					"dxRoleCode", "dlRoleCode");
			merchandiseGridUtil.initFilters({
				onClickRow : merchandiseFn.setButtonState, // 设置按钮状态
				onCheck : merchandiseFn.setButtonState, // 设置按钮状态
				onUncheck : merchandiseFn.setButtonState, // 设置按钮状态
				onSelectAll : merchandiseFn.setButtonState, // 设置按钮状态
				onUnselectAll : merchandiseFn.setButtonState, // 设置按钮状态
				onLoadSuccess : function() {
					$('#merchandise_grid').datagrid("clearSelections");
					merchandiseFn.setButtonState();
				}
			});
		});
var merchandiseFn = {
	// 显示添加或修改窗体
	showForm : function(role) {
		roleinfo = role;
		if (role == "DX") {
			title = '将选中的商品的定性角色修改为以下';
		} else {
			title = '将选中的商品的定量角色修改为以下';
		}
		var record = merchandiseGridUtil.getSelectedIdArr('merchandiseCode');
		if (record == null) {
			return;
		}
		href = 'merchandiseRole_showUpdateMerchandise' + role + 'RoleForm_1.html';
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 250,
			buttons : [ {
				text : '提交',
				handler : function() {
					merchandiseFn.submitForm(dlg);
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
	// 修改商品定性角色
	showEditdx : function() {
		merchandiseFn.showForm('DX');
	},
	// 修改商品定量角色
	showEditdl : function() {
		merchandiseFn.showForm('DL');
	},
	// 提交新增或修改表单
	submitForm : function(dlg) {
		var url = 'merchandiseRole_updateMerchandiseRole_2.html';
		var merchandiseCode = merchandiseGridUtil.getSelectedIdArr('merchandiseCode');
		var supplierCode = merchandiseGridUtil.getSelectedIdArr('supplierCode');
		var dx = "";
		var dl = "";
		if (roleinfo == "DX") {
			dx = $("#dxRoleCode_xg").combobox('getValue');
			if (dx == "") {
				$.messager.alert('操作失败', "必选项未选择");
				return;
			}
		} else {
			dl = $("#dlRoleCode_xg").combobox('getValue');
			if (dl == "") {
				$.messager.alert('操作失败', "必选项未选择");
				return;
			}
		}
		utils.post(url + "?merchandiseCode=" + merchandiseCode+"&supplierCode="+supplierCode, {
			dlRoleCode : dl,
			dxRoleCode : dx
		}, function() {
			dlg.dialog('close');
			merchandiseGridUtil.refresh();
		});
		$(".window-shadow").remove();
	},// 查询
	search : function() {
		var url = "merchandise_listMerchandise_2.html";
		var param = merchandiseGridUtil.getFilterValue();
		if(JSON.stringify(param)=="{}"){
			$.messager.alert('查询失败', "至少输入一个搜索项");
			return;
		}
		$('#merchandise_grid').datagrid({
			'url' : url,
			queryParams : param
		});
		$('#merchandise_grid').datagrid("load",param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		$("#centreTypeCode").combobox('setValues', '');
		$("#smallTypeCode").combobox('setValues', '');
		$("#detailTypeCode").combobox('setValues', '');
		$("#fineTypeCode").combobox('setValues', '');
		$("#dxRoleCode").combobox('setValues', '');
		$("#dlRoleCode").combobox('setValues', '');
		$("input:text").val("");
		$('#merchandise_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
	},
	refresh : function() {
		$('#merchandise_grid').treegrid('reload');
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#merchandise_grid').datagrid("getSelections");
		if (rows.length >= 1) {
			$("a[id='showEdit']").linkbutton("enable");
		} else {
			$("a[id='showEdit']").linkbutton("disable");
		}
	},
	// 从EXCEL导入
	importFromExcel : function() {
		var dlg = utils.showDlg({
			title : '上传商品角色',
			href : 'merchandiseRole_importMerchandiseRole_1.html',
			width : 400,
			height : 200,
			buttons : [ {
				text : '关闭',
				handler : function() {
					dlg.dialog('close');
				},
				iconCls : 'close'
			} ]
		});
		$(".window-shadow").remove();
	}
};