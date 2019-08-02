var attachmentGridUtil = null;
$(document).ready(function() {
	attachmentGridUtil = utils.grid($('#fiUploadFile_grid'));
	attachmentGridUtil.initFilters({
		noParamCanSort:true,
		onLoadSuccess : function() {
			firstIn = false;
			$('#fiUploadFile_grid').datagrid("clearSelections");
		}
	});
});
var attachmentFn = {
	//上传文件
	importFile : function() {
		var rows = $('#fiAttachment_grid').datagrid("getSelections");
		if (rows.length != 1) {
			window.parent.$.messager.alert('提示', '请选择一个商品(至多一个商品)!');
			return;
		}
		var uploadVar = $("#applicationFile").val();
		if (uploadVar == "") {
			window.parent.$.messager.alert('提示', '请选择上传文件');
			return;
		}else{
			rows = rows[0];
			var file = $("#fiUploadFile");
			var intentionCode = rows.intentionCode;
			//供应商编号
			var intentionSupplierCode = rows.intentionSupplierCode;
			if (!rows.intentionSupplierCode) {
				intentionSupplierCode = rows.supplierCode;
			} 

			$("#uploadAttachmentForm").form("submit",
					{
						url : "fastImportAttachment_uploadApplicationAttachment_2.html?intentionCode=" + intentionCode 
							+ "&intentionSupplierCode=" + intentionSupplierCode 
							+ "&applicationCode=${applicationCode}&intentionAndSupplierCodes=${intentionAndSupplierCodes}",
						success : function(data) {
							var json = $.parseJSON(data);
							var msg = json.msg;
							if (json.success) {
								$.messager.show({
									title : '提示',
									msg : msg
								});
								$("#applicationFile").val("");
								file.after(file.clone().val(""));
								file.remove();
								attachmentGridUtil.refresh();
							}else{
								window.parent.$.messager.alert("提示", msg);
							}
						}
					});
		}
	},
	
	// 删除申请文件
	remove : function() {
		var applicationFileGrid = $('#fiUploadFile_grid').datagrid("getChecked");
		if (applicationFileGrid.length == 0) {
			window.parent.$.messager.alert('提示', '请至少选择一个申请文件');
			return;
		}else{
			var reportCodes = [];
			$.each(applicationFileGrid, function(index, item) {
				reportCodes.push("'"+item.reportCode+"'");
			});
			
			window.parent.utils.confirm("操作确认", "确认删除申请文件?", function() {
				utils.post("fastImportAttachment_completeDeleteApplicationFiles_2.html?reportCodes="+reportCodes
						+"&applicationCode=${applicationCode}&intentionAndSupplierCodes=${intentionAndSupplierCodes}", null, function() {
					$('#fiUploadFile_grid').datagrid("clearSelections");
					$('#fiUploadFile_grid').datagrid('reload');
				},function(){
					$('.window-shadow').remove();
				});
			});
		}
	},
	
	// 点击文件名称下载文件
	formatFileName : function(value, row, index) {
		return '<a href=javascript:void(0) onclick=attachmentFn.downloadOriginalFile("' + encodeURIComponent(row.path) + '")>' + value + '</a><br>';
	},
	
	// 下载文件
	downloadOriginalFile : function(filePath) {
		window.location = "fastImportAttachment_downloadOriginalFile_6.html?path="+ filePath;
	}
	
};
