var materialGridUtil = null;
var materialBigTypeGridUtil = null;
var materialSmallTypeGridUtil = null;
var materialBig = null;
var materialSmall = null;

$(document).ready(function() {
	materialGridUtil = utils.grid($('#material_grid'));
	materialGridUtil.registerExtFilters("materialBig", "materialSmall", "websiteMaterialName", "websiteCode", "priceRegion");
	materialGridUtil.initFilters({
		onClickRow : materialFn.setButtonState, // 设置按钮状态
		onCheck : materialFn.setButtonState, // 设置按钮状态
		onUncheck : materialFn.setButtonState, // 设置按钮状态
		onSelectAll : materialFn.setButtonState, // 设置按钮状态
		onUnselectAll : materialFn.setButtonState, // 设置按钮状态
		onLoadSuccess : function() {
			$('#material_grid').datagrid("clearSelections");
			materialFn.setButtonState();
		}
	});
	materialSmallTypeGridUtil = utils.grid($('#materialSmallType_grid'));
	materialSmallTypeGridUtil.registerExtFilters("materialBigTypeCode", "materialSmallTypeCode");
	materialSmallTypeGridUtil.initFilters({
		onClickRow : materialSmallTypeFn.setButtonState, // 设置按钮状态
		onCheck : materialSmallTypeFn.setButtonState, // 设置按钮状态
		onUncheck : materialSmallTypeFn.setButtonState, // 设置按钮状态
		onSelectAll : materialSmallTypeFn.setButtonState, // 设置按钮状态
		onUnselectAll : materialSmallTypeFn.setButtonState, // 设置按钮状态
		onLoadSuccess : function() {
			$('#materialSmallType_grid').datagrid("clearSelections");
			materialSmallTypeFn.setButtonState();
		}
	});
	materialBigTypeGridUtil = utils.grid($('#materialBigType_grid'));
	materialBigTypeGridUtil.initFilters({
		onClickRow : materialBigTypeFn.setButtonState, // 设置按钮状态
		onCheck : materialBigTypeFn.setButtonState, // 设置按钮状态
		onUncheck : materialBigTypeFn.setButtonState, // 设置按钮状态
		onSelectAll : materialBigTypeFn.setButtonState, // 设置按钮状态
		onUnselectAll : materialBigTypeFn.setButtonState, // 设置按钮状态
		onLoadSuccess : function() {
			$('#materialBigType_grid').datagrid("clearSelections");
			materialBigTypeFn.setButtonState();
		}
	});
	$("a[id='showEdit_small']").linkbutton("disable");
	$("a[id='remove_small']").linkbutton("disable");
	$("a[id='remove']").linkbutton("disable");
	// 原料大类绑定
	$("#materialBig").combobox({
		onChange : function() {
			materialTypeFn.reloadData("materialBig", "materialSmall", "MaterialSmallType");
		}
	});

	// 原料小类绑定
	$("#materialSmall").combobox({
		onChange : function() {
			materialTypeFn.reloadData("materialSmall", "websiteMaterialName", "WebsiteMaterialName");
		}
	});
});
var materialTypeFn = {
	// 重新加载
	reloadData : function(thisId, targetId, method) {
		materialTypeFn.clearSelectedData(targetId);
		// 当前框的值
		var value = $("#" + thisId).combobox("getValue");
		if (!value) {
			value = 'null';
		}
		$("#" + targetId).combobox('reload', "materialProperties_list" + method + "_5.html?" + thisId + "TypeCode=" + value);
	},

	// 清空下一个连动框已选择的值
	clearSelectedData : function(targetId) {
		$("#" + targetId).combobox("setValue", "");
		$("#" + targetId).combobox("clear");
	},
	//添加或修改时改变下拉
	clearInfo:function(){
		$(".filterSelect").combobox('setValues', '');
		$(".filterSelect").combobox("reload");
	}
};
var materialFn = {
	// 显示添加或修改窗体
	showForm : function(isEdit) {
		var title = '添加原料名称下拉列表';
		var href = 'material_showInsertMaterialForm_1.html';
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 300,
			height : 270,
			buttons : [ {
				text : '保存',
				handler : function() {
					materialFn.submitForm(dlg);
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
	// 添加原料
	showInsert : function() {
		materialFn.showForm(false);
	},
	// 提交新增或修改表单
	submitForm : function(dlg) {
		var bigTypeCode = $("#BigTypeCode").combobox("getValue");
		var smallTypeCode = $("#SmallTypeCode").combobox("getValue");
		var websiteCode = $("#websiteName").combobox("getValue");
		var materialName = $.trim($("input[name='materialName']").val());
		var websiteUrl = $.trim($("input[name='websiteUrl']").val());
		var priceRegion = $.trim($("input[name='materialRegionName'").val());
		if (bigTypeCode != "" && smallTypeCode != "" && websiteCode != "" && materialName != "" && websiteUrl != ""&&priceRegion!="") {
			var url = 'material_insertMaterial_2.html';
			utils.form("material_form").submit(url, null, function() {
				dlg.dialog('close');
				materialTypeFn.clearInfo();	
				$("#websiteCode").combobox("reload");
				materialGridUtil.refresh();
			});
		} else {
			$.messager.alert('操作失败', "请填入所有必填信息");
		}
	},
	//格式化时间
	formattDate:function(value,row){
	 	var date = new Date(value);
	 	var month=date.getMonth() + 1;
	 	if(10>month){
	 		month='0'+month;
	 	}
	 	var day=date.getDate();
	 	if(10>day){
	 		day='0'+day;
	 	}
	 	return date.getFullYear() + '-' + month + '-' + day;
	},
	// 删除原料
	remove : function() {
		var record = materialGridUtil.getSelectedIdArr('materialCode');
		if (record == null)
			return;
		utils.confirm("操作确认", "删除公示网站原料名称后，与该公示网站原料名称关联的商品均将取消关联关系，是否确认删除?", function() {
			utils.post("material_deleteMaterial_2.html?materialCode=" + record, null, function() {
				materialGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	// 查询
	search : function() {
		var url = "material_listMaterial_2.html";
		var param = materialGridUtil.getFilterValue();
		$('#material_grid').datagrid({
			'url' : url,
			queryParams : param
		});
		$('#material_grid').datagrid("load",param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		$("#materialBig").combobox('setValues', '');
		$("#materialSmall").combobox('setValues', '');
		$("#websiteMaterialName").combobox('setValues', '');
		$("#websiteCode").combobox('setValues', '');
		$("#priceRegion").val("");
		$('#material_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
	},
	refresh : function() {
		$('#material_grid').treegrid('reload');
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#material_grid').datagrid("getSelections");
		if (rows.length >= 1) {
			$("a[id='remove']").linkbutton("enable");
		} else {
			$("a[id='remove']").linkbutton("disable");
		}
	}
};
var info = "";
// 原料大类
var materialBigTypeFn = {
	// 显示添加或修改窗体
	showForm : function(isEdit) {
		var title = '添加原料大类';
		var href = 'materialBigType_showInsertMaterialBigTypeForm_1.html';
		materialBig = false;
		if (isEdit) {// 显示修改窗体
			var record = materialBigTypeGridUtil.getSelectedRecord();
			if (record == null) {
				return;
			}
			materialBig = true;
			title = '修改原料大类';
			href = 'materialBigType_showUpdateMaterialBigTypeForm_1.html?materialBigTypeCode=' + record.materialBigTypeCode;
		}
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 350,
			height : 170,
			buttons : [ {
				text : '保存',
				handler : function() {
					materialBigTypeFn.submitForm(dlg);
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
	// 添加原料大类
	showInsert : function() {
		materialBigTypeFn.showForm(false);
	},
	// 修改原料大类
	showEdit : function() {
		materialBigTypeFn.showForm(true);
	},
	// 提交新增或修改表单
	submitForm : function(dlg) {
		var materialBigTypeName=$("input[name='materialBigTypeName']").val().replace(/\s+/g,"");
		if(materialBigTypeName==""){
			$.messager.alert('操作失败', "请填入原料大类名称");
			return;
		}
		var url = 'materialBigType_insertMaterialBigType_2.html';
		if (materialBig) {
			url = 'materialBigType_updateMaterialBigType_2.html';
		}
		utils.form("materialBigType_form").submit(url, null, function() {
			dlg.dialog('close');
			materialTypeFn.clearInfo();	
			materialBigTypeGridUtil.refresh();
		});
	},
	// 删除原料大类
	remove : function() {
		var record = materialBigTypeGridUtil.getSelectedIdArr('materialBigTypeCode');
		if (record == null)
			return;
		utils.confirm("操作确认", "确认删除原料大类?", function() {
			utils.post("materialBigType_deleteMaterialBigType_2.html?materialBigTypeCode=" + record, null, function() {
				materialTypeFn.clearInfo();	
				materialBigTypeGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	// 清除查询
	clearFilter : function() {
		materialBigTypeGridUtil.clearFilter();
	},
	refresh : function() {
		$('#materialBigType_grid').treegrid('reload');
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#materialBigType_grid').datagrid("getSelections");
		if (rows.length >= 1) {
			$("a[id='showEdit_big']").linkbutton("enable");
			$("a[id='remove_big']").linkbutton("enable");
		} else {
			$("a[id='showEdit_big']").linkbutton("disable");
			$("a[id='remove_big']").linkbutton("disable");
		}
	}
};
var materialSmallTypeFn = {
	// 显示添加或修改窗体
	showForm : function(isEdit) {
		var title = '原料小类新增';
		var href = 'materialSmallType_showInsertMaterialSmallTypeForm_1.html';
		materialSmall = false;
		if (isEdit) {// 显示修改窗体
			var record = materialSmallTypeGridUtil.getSelectedRecord();
			if (record == null) {
				return;
			}
			materialSmall = true;
			title = '修改原料小类';
			href = 'materialSmallType_showUpdateMaterialSmallType' + info + 'Form_1.html?materialSmallTypeCode=' + record.materialSmallTypeCode+"&materialBigTypeCode="+record.materialBigTypeCode;
		}
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 350,
			height : 170,
			buttons : [ {
				text : '确定',
				handler : function() {
					materialSmallTypeFn.submitForm(dlg);
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
	//格式化网站地址
	formattUrl:function(value,row){
		 return "<a href="+"'http://"+value +"'"+"target='_Blank' title="+value+">" + value + "</a>";
	},
	// 添加原料小类
	showInsert : function() {
		materialSmallTypeFn.showForm(false);
	},
	// 修改原料小类所属
	showEditBig : function() {
		info = "Big";
		materialSmallTypeFn.showForm(true);
	},
	// 修改原料小类
	showEditName : function() {
		info = "Name";
		materialSmallTypeFn.showForm(true);
	},
	// 提交新增或修改表单
	submitForm : function(dlg) {
		
		var url = 'materialSmallType_insertMaterialSmallType_2.html';
		if (info != "Name" && info != "Big") {
			var detal = $("#bigTypeCode").combobox("getValue");
			var fine = $.trim($("input[name='materialSmallTypeName']").val());
			if (detal == "" || fine == "") {
				$.messager.alert('操作失败', "请填入所有必填信息");
				return;
			}
		} else {
			var record = materialSmallTypeGridUtil.getSelectedRecord();
			url = "materialSmallType_updateMaterialSmallType" + info + "_2.html";
			if (info == "Big") {
				url+="?materialSmallTypeName=" + record.materialSmallTypeName;
				var big = $("#materialBigName").combobox("getValue");
				if (big == "") {
					$.messager.alert('操作失败', "请填入所有必填信息");
					return;
				}
			} else if (info == "Name") {
				url+="?materialSmallTypeCode=" + record.materialSmallTypeCode+"&materialBigTypeCode="+record.materialBigTypeCode;
				var name = $("input[name='materialSmallTypeName']").val().replace(/\s+/g,"");
				if (name == "") {
					$.messager.alert('操作失败', "请填入所有必填信息");
					return;
				}
			}
		}
		utils.form("materialSmallType_form").submit(url, null, function() {
			dlg.dialog('close');
			materialTypeFn.clearInfo();	
			materialSmallTypeGridUtil.refresh();
		});
	},
	// 删除原料小类
	remove : function() {
		var record = materialSmallTypeGridUtil.getSelectedIdArr('materialSmallTypeCode');
		if (record == null)
			return;
		utils.confirm("操作确认", "确认删除原料小类?", function() {
			utils.post("materialSmallType_deleteMaterialSmallType_2.html?materialSmallTypeCode=" + record, null, function() {
				materialTypeFn.clearInfo();	
				materialSmallTypeGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	search : function() {
		var url = "materialSmallType_listMaterialSmallType_2.html";
		var param = materialSmallTypeGridUtil.getFilterValue();
		$('#materialSmallType_grid').datagrid({
			'url' : url,
			queryParams : param
		});
		$('#materialSmallType_grid').datagrid("load",param);
	},
	// 清除查询
	clearFilter : function() {
		$("#materialBigTypeCode").combobox('setValues', '');
		$("#materialSmallTypeCode").combobox('setValues', '');
		$('#materialSmallType_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
	},
	// 刷新
	refresh : function() {
		$('#materialSmallType_grid').treegrid('reload');
	},
	// 设置按钮状态
	setButtonState : function(index, record) {
		var rows = $('#materialSmallType_grid').datagrid("getSelections");
		if (rows.length >= 1) {
			$("a[id='showEdit_small']").linkbutton("enable");
			$("a[id='remove_small']").linkbutton("enable");
		} else {
			$("a[id='showEdit_small']").linkbutton("disable");
			$("a[id='remove_small']").linkbutton("disable");
		}
	}
};