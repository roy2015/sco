$(document).ready(function(){});

var supplierComplaintsUploadFn = {
	
	//下载模板
	downloadExcelMold:function(){
		window.location = "supplierComplaints_DownloadSupplierComplaintsDataTemplate_3.html";  //客诉记录模板
	},
		
	//上传Excel
	subForm : function() {
		$("#msg").html("");
		var val = $("#viewfileSp").val();
		if(val == ""){
			$.messager.alert('提示',
				'${action.getText("请选择上传文件")}');
			return;
		}
		var year = $("#year").val();
		if(year == ""){
			$.messager.alert('提示',
				'${action.getText("请填写客诉年度")}');
			return;
		}
		var fileExt = [".XLSX"];
		if(!E2E_checkFileExt_custom(val, fileExt)) {
			$.messager.alert('提示',
				'上传文件格式不正确,请上传07版本及以上的Excel文件');
			return ;
		}
		showLoading();
		var file = $("#file");
		$("#uploadMDForm").form("submit", {
			url : "supplierComplaints_completeImportSupplierComplaintsData_2.html",
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
				}else {
					$("#msg").html("<span style='color:red'>导入消息:</span><br/>"+msg);
				}
				$('.msg_bg').remove();
			}
		});
	}
	
};