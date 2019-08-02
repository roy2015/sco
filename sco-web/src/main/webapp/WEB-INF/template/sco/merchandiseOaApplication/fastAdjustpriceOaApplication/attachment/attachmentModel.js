var attachmentGridUtil = null;
$(document).ready(function() {
	attachmentGridUtil = utils.grid($('#applicationFile_grid'));
});
var attachmentFn = {
	// 上传文件
	uploadFile : function() {
		var rows = $('#attachment_grid').datagrid("getSelected");
		
		if (rows==null||rows.length ==0) {
			window.parent.$.messager.alert('提示', '请选择一个商品!');
			return;
		}else if(rows.length>1){
			window.parent.$.messager.alert('提示', '请只选择一个商品!');
			return;
		}else{
			var merchandiseCode=rows.merchandiseCode;
			var supplierCode = rows.supplierCode;
			var uploadVar = $("#applicationFile").val();
			if (uploadVar == "") {
				window.parent.$.messager.alert('提示', '请选择上传文件');
				return;
			}else{
				var file = $("#uploadFile");
				$("#uploadAttachmentForm").form("submit",
						{
							url : "fastAdjustprice_uploadApplicationAttachment_2.html?merchandiseCode=" + merchandiseCode + "&intentionSupplierCode=" + supplierCode + "&applicationCode="
									+ $("input#applicationCode").val() + "&intentionAndSupplierCodes=" + $("input#intentionAndSupplierCodes").val(),
							success : function(data) {
								var json = $.parseJSON(data);
								var msg = json.msg;
								if (json.success) {
									$.messager.show({
										title : '提示',
										msg : msg
									});
								}else{
									$.messager.show({
										title : '提示',
										msg : msg
									});
								}
								$("#applicationFile").val("");
								file.after(file.clone().val(""));
								file.remove();
								$("#uploadFile").val("");
								attachmentGridUtil.refresh();
							}
						});
			}
		}
	},
	// 删除申请文件
	remove : function() {
		var applicationCode = $("input#applicationCode").val();
		var intentionAndSupplierCodes = $("input#intentionAndSupplierCodes").val();
		var applicationFileGrid = $('#applicationFile_grid').datagrid("getChecked");
		if (applicationFileGrid.length==0) {
			window.parent.$.messager.alert('提示', '请至少选择一个供申请文件');
			return;
		}else{
			var reportCodes = [];
			$.each(applicationFileGrid, function(index, item) {
				reportCodes.push("'"+item.reportCode+"'");
			});
			
			window.parent.utils.confirm("操作确认", "确认删除申请文件?", function() {
				utils.post("fastAdjustprice_deleteApplicationFiles_2.html?reportCodes="+reportCodes+"&applicationCode="+applicationCode+"&intentionAndSupplierCodes="+intentionAndSupplierCodes, null, function() {
					$('#applicationFile_grid').datagrid('reload');
					//attachmentGridUtil.refresh();
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
		window.location = "fastAdjustprice_downloadOriginalFile_6.html?path="+ filePath;
	}
	
};
