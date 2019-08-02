var supplierAttachmentMGridUtil = null;
var supplierAttachmentMSCGridUtil = null;
var supplierAttachmentMGLGridUtil = null;
$(document).ready(function() {
	supplierAttachmentMGridUtil = utils.grid($('#supplierAttachmentM_grid'));
	supplierAttachmentMSCGridUtil = utils.grid($('#supplierAttachmentM_grid_sc'));
	supplierAttachmentMSCGridUtil.initFilters({});//系统封装的js，这样虽然里面是空的，但是开启了查询【就是筛选功能】，不能去掉
	
	supplierAttachmentMGLGridUtil = utils.grid($('#supplierAttachmentM_grid_gl'));
	supplierAttachmentMGLGridUtil.initFilters({});
});
var supplierAttachmentMFn = {
	//
	change : function() {
		$("#otherName").val($("#intentionSupper option:selected").text());
		if ($("#intentionSupper option:selected").val() == "其它") {
			$("#otherName").val("");
			$(".otherName").show();
		} else {
			$(".otherName").hide();
		}
	},
	// 从EXCEL导入
	importFromExcel : function() {
		var rows = $('#supplierAttachmentM_grid').datagrid("getSelections");
		if (rows.length != 1) {
			window.parent.$.messager.alert('提示', '请单选供应商');
			return;
		}
		var type = $("#intentionSupper option:selected").val()
		var name = $("#intentionSupper option:selected").text();
		if (type != "") {
			if (type == "其它" && $("#otherName").val() == "") {
				window.parent.$.messager.alert('提示', '请填写供应商附件类型');
				return;
			} else {
				name = $("#otherName").val();
			}
			var val = $("#viewfileSp").val();
			if (val == "") {
				window.parent.$.messager.alert('提示', '请选择上传文件');
				return;
			}
			showLoading();
			var file = $("#file");
			var record = $('#supplierAttachmentM_grid').datagrid("getSelected");
			var supplierCode = record.intentionSupplierCode;
			if (record.intentionSupplierCode == null || record.intentionSupplierCode == "") {
				supplierCode = record.supplierCode;
			}
			var merchandiseCode = record.merchandiseCode;
			if (record.merchandiseCode == null || record.merchandiseCode == "") {
				merchandiseCode = record.intentionCode;
			}
			$("#uploadMDForm").form(
					"submit",
					{
						url : "supplierAttachmentM_completeImportsupplierAttachmentM_2.html?merchandiseCode=" + merchandiseCode + "&supplierCode=" + supplierCode + "&applicationCode="
								+ $("input#applicationCode").val() + "&intentionAndSupplierCodes=" + $("input#intentionAndSupplierCodes").val() + "&applicationType=ma",
						success : function(data) {
							var json = $.parseJSON(data);
							var msg = json.msg;
							if (json.success) {
								$.messager.show({
									title : '提示',
									msg : msg
								});
								$("#viewfileSp").val("");
								file.after(file.clone().val(""));
								file.remove();
								supplierAttachmentMSCGridUtil.refresh();
							} else {
								window.parent.$.messager.alert('操作失败', msg);
							}
						}
					});
			$('.msg_bg').remove();
		} else {
			window.parent.$.messager.alert('提示', '请选择一个供应商附件类型');
		}
	}
};
var supplierAttachmentMSCFn = {
	// 刷新供应商附件
	search : function() {
		var param = supplierAttachmentMSCGridUtil.getFilterValue();
		$('#supplierAttachmentM_grid_sc').datagrid('load', param);
	},
	remove : function() {
		var rows = $('#supplierAttachmentM_grid_sc').datagrid("getSelections");
		if (rows.length < 1) {
			window.parent.$.messager.alert('提示', '请至少选择一个供应商附件');
			return;
		}
		var applicationCode = rows[0].applicationCode;
		var intentionCodes = "";
		var intentionSupplierCodes = "";
		var attachmentTypes = "";
		var elseAttachmentTypes = "";
		var paths="";
		for ( var i in rows) {
			if (i != rows.length - 1) {
				intentionSupplierCodes += rows[i].supplierCode + ",";
				intentionCodes += rows[i].merchandiseCode + ",";
				paths+=encodeURIComponent(rows[i].path)+",";
				elseAttachmentTypes += rows[i].elseAttachmentType + ",";
				attachmentTypes += rows[i].attachmentType + ",";
				
			} else {
				intentionSupplierCodes += rows[i].supplierCode;
				intentionCodes += rows[i].merchandiseCode;
				paths+=encodeURIComponent(rows[i].path);
				elseAttachmentTypes += rows[i].elseAttachmentType;
				attachmentTypes += rows[i].attachmentType;
			}
		}
		utils.confirm("操作确认", "确认删除供应商附件?", function() {
			utils.post("supplierAttachmentM_deletesupplierAttachmentM_2.html", {
				intentionAndSupplierCodes : $("input#intentionAndSupplierCodes").val(),
				applicationCode : applicationCode,
				applicationType : "ma",
				intentionCode : intentionCodes,
				intentionSupplierCode : intentionSupplierCodes,
				paths:paths,
				attachmentType : attachmentTypes,
				elseAttachmentTypes:elseAttachmentTypes
			}, function() {
				supplierAttachmentMSCGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	// 改变单元格
	downloadFile : function(value, row, index) {
		if (value == "其它") {
			return '<a href=javascript:void(0) onClick=supplierAttachmentMSCFn.downloadOriginalFile("' + encodeURIComponent(row.path) + '")>' + row.elseAttachmentType + '</a><br>';
		} else {
			return '<a href=javascript:void(0) onClick=supplierAttachmentMSCFn.downloadOriginalFile("' + encodeURIComponent(row.path) + '")>' + value + '</a><br>';
		}
	},
	// 下载供应商附件
	downloadOriginalFile : function(filePath) {
		window.location = "supplierAttachmentM_downloadOriginalFile_6.html?path=" + filePath;
	}
}
var supplierAttachmentMGLFn = {
	// 搜索
	searchGL : function() {
		var param = supplierAttachmentMGLGridUtil.getFilterValue();
		$('#supplierAttachmentM_grid_gl').datagrid('load', param);
	},
	// 改变样式
	downloadFileGL : function(value, row, index) {
		var name = "";
		var type = 0;
		if (value == "1") {
			name = "核算表";
			type = 2;
		} else if (value == "2") {
			name = "投料表";
			type = 1;
		}
		return '<a href=javascript:void(0) onClick=supplierAttachmentMGLFn.downloadOriginalFileGL("' + encodeURIComponent(row.path) + '","' + row.accountingCode + '","' + type + '")>' + name
				+ '</a><br>';
	},
	downloadOriginalFileGL : function(filePath, accountingCode, type) {
		window.location = "supplierAttachmentM_downloadFile_6.html?path=" + filePath + "&type=" + type + "&accountingCode=" + accountingCode;
	}
}