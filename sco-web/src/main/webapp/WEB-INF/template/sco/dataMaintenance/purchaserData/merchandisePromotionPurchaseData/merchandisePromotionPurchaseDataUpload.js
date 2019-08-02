$(document).ready(function(){});

var merchandisePromotionPurchaseDataUploadFn = {
	
	//下载商品外部抽检不合格记录表模板
	downloadExcelMold:function(){
		window.location = "merchandisePromotionPurchaseData_downloadMerchandisePromotionPurchaseDataTemplate_3.html";  //商品外部抽检不合格记录表维护模板
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
		var fileExt = [".XLSX"];
		if(!E2E_checkFileExt_custom(val, fileExt)) {
			$.messager.alert('提示',
				'上传文件格式不正确,请上传07版本及以上的Excel文件');
			return ;
		}
		showLoading();
		var file = $("#file");
		$("#uploadMDForm").form("submit", {
			url : "merchandisePromotionPurchaseData_completeImportMerchandisePromotionPurchaseData_2.html",
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