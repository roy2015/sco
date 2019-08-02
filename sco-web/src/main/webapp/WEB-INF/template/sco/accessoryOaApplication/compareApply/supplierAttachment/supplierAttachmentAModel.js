var supplierAttachmentAFn = {
	//查询报价记录
	searchAQS : function() {
		var rows = $('#supplierAttachmentA_grid').datagrid("getSelections");
		if (rows.length <1) {
			$.messager.alert('提示', '请选择一个供应商');
			return;
		}
		/*
		var enquiryCode = rows[0].enquiryCode;
		var supplierCode="";
		if(rows[0].supplierCode!=null&&rows[0].supplierCode!=""){
			supplierCode=rows[0].supplierCode;
		}else{
			supplierCode=rows[0].intentionSupplierCode;
		}*/
		$(rows).each(function(i,obj){
			obj.intentionCode=null;
			obj.intentionName=null;
			obj.intentionSupplierName=null;
			obj.enquiryCreated=null;
			obj.enquiryCreateby=null;
			obj.quotedDate=null;
			obj.quotedCreated=null;
			obj.supplierName=null;
			obj.quotedCode=null;
			for(var key in obj){  
	            try{  
	                var value = eval("obj['" +  key +"']");  
	                if(value==null ||value=='null'||value==''){
	                	delete obj[key];  
	                }
	                if(key=='fieldMap'){
	                	delete obj['fieldMap'];  
	                }
	            }catch(e){}  
	        }  
		      
		  }); 
		rows=JSON.stringify(rows).replace(/"([^"]*)"/g, "'$1'");
		var title = '查看系统中已有扫描版报价单';
		var href = 'supplierAttachmentA_showaccessoryQuotedScanGrid_1.html?rows=' + rows;
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 630,
			height : 350,
			buttons : [ {
				text : '确定选用',
				id : 'sbt',
				handler : function() {
					supplierAttachmentAFn.submitFormAQS(dlg);
				},
				iconCls : 'save'
			}, {
				text : '关闭',
				handler : function() {
					dlg.dialog('close');
				},
				iconCls : 'close'
			} ]
		});
		$(".window-shadow").remove();
		$("#accessoryQuotedScan_grid").datagrid('reload');
	},
	//确定报价记录
	submitFormAQS : function(dlg) {
		var rows = $("#accessoryQuotedScan_grid").datagrid('getSelections');
		if (rows.length < 1) {
			$.messager.alert('提示', '请至少选择一个扫描版报价单');
			return;
		}
		var applicationCode = "";
		var supplierCode = "";
		var intentionCode="";
		var quotedElectronicCode="";
		var quotedCodeScan="";
		var record=$("#supplierAttachmentA_grid").datagrid('getSelected');
		for ( var i in rows) {
			if (i != rows.length - 1) {
				quotedElectronicCode += record.quotedCode + ",";
				quotedCodeScan += rows[i].quotedCode + ",";
				applicationCode += $("input#applicationCodeNow").val() + ",";
				if (rows[i].supplierCode != null && rows[i].supplierCode != "") {
					supplierCode += rows[i].supplierCode + ",";
				} else {
					supplierCode += rows[i].intentionSupplierCode + ",";
				}
				intentionCode += rows[i].intentionCode + ",";
			} else {
				quotedElectronicCode += record.quotedCode;
				quotedCodeScan += rows[i].quotedCode;
				applicationCode += $("input#applicationCodeNow").val();
				if (rows[i].supplierCode != null && rows[i].supplierCode != "") {
					supplierCode += rows[i].supplierCode;
				} else {
					supplierCode += rows[i].intentionSupplierCode;
				}
				intentionCode += rows[i].intentionCode;
			}
		}
		var url = "quotedElectronicOfScan_insertQuotedElectronicOfScan_2.html?applicationCode=" + applicationCode + 
					"&quotedElectronicCode="+quotedElectronicCode+
					"&quotedCodeScan=" +quotedCodeScan + 
					"&quotedCode=" + $("input#quotedCodes").val() + 
					"&supplierCode=" + supplierCode+ 
					"&intentionCode=" + intentionCode+
					"&type=ax";
		utils.post(url, null, function() {
			$("#supplierAttachmentA_grid_gl").datagrid('reload');
			dlg.dialog('close');
		});
	},
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
		var rows = $('#supplierAttachmentA_grid').datagrid("getSelections");
		if (rows.length != 1) {
			$.messager.alert('提示', '请选择一条报价记录');
			return;
		}
		var type = $("#intentionSupper option:selected").val()
		var name = $("#intentionSupper option:selected").text();
		if (type != "") {
			if (type == "其它" && $("#otherName").val() == "") {
				$.messager.alert('提示', '请填写供应商附件类型');
				return;
			} else {
				name = $("#otherName").val();
			}
			var val = $("#viewfileSp").val();
			if (val == "") {
				$.messager.alert('提示', '请选择上传文件');
				return;
			}
			
			showLoading();
			var file = $("#file");
			var record = $('#supplierAttachmentA_grid').datagrid("getSelected");
			$("#uploadMDForm").form(
					"submit",
					{
						url : "supplierAttachmentA_completeImportsupplierAttachmentA_2.html?"+
						"intentionCode=" + record.intentionCode + 
						"&intentionSupplierCode=" +record.intentionSupplierCode + 
						"&enquiryCode=" + record.enquiryCode + 
						"&quotedCodeScan=" + $("input#quotedCodes").val()+ 
						"&quotedCode=" +record.quotedCode + 
						"&applicationCode="+ $("input#applicationCodeNow").val() + 
						"&supplierCode=" + record.intentionSupplierCode+"&type=ax",
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
								$('#supplierAttachmentA_grid_sc').datagrid("reload");
							} else {
								$.messager.alert('操作失败', msg);
							}
						}
					});
			$('.msg_bg').remove();
		} else {
			$('.msg_bg').remove();
			$.messager.alert('提示', '请选择一个供应商附件类型');
		}
	}
};
var supplierAttachmentASacn = {
	// 刷新供应商附件
	search : function() {
		$('#supplierAttachmentA_grid_sc').datagrid("reload");
	},
	// 删除供应商附件
	remove : function() {
		var rows = $('#supplierAttachmentA_grid_sc').datagrid("getSelections");
		if (rows.length < 1) {
			$.messager.alert('提示', '请至少选择一个供应商附件');
			return;
		}
		var intentionCodes = "";
		var supplierCodes = "";
		var applicationCode = "";
		var fileTypes = "";
		var quotedCode = "";
		var elseTypes="";
		var paths="";
		for ( var i in rows) {
			intentionCodes += rows[i].intentionCode + ",";
			if (rows[i].intentionSupplierCode == "" || rows[i].intentionSupplierCode == "null" || rows[i].intentionSupplierCode == null) {
				supplierCodes += rows[i].supplierCode + ",";
			} else {
				supplierCodes += rows[i].intentionSupplierCode + ",";
			}
			applicationCode += $("input#applicationCodeNow").val() + ",";
			quotedCode += rows[i].quotedCode + ",";
			fileTypes += rows[i].fileType + ",";
			elseTypes+=rows[i].elseType+",";
			paths+=encodeURIComponent(rows[i].path)+",";
		}
		utils.confirm("操作确认", "确认删除供应商附件?", function() {
			utils.post("supplierAttachmentA_deleteSupplierAttachmentA_2.html", {
				applicationCode : applicationCode,
				intentionCodes : intentionCodes,
				supplierCodes : supplierCodes,
				fileTypes : fileTypes,
				elseTypes:elseTypes,
				paths:paths,
				quotedCodeScan : $("input#quotedCodes").val(),
				quotedCode : quotedCode,
				type : 'ax'
			}, function() {
				$('#supplierAttachmentA_grid_sc').datagrid("reload");
			});
		});
		$(".window-shadow").remove();
	},
	// 下载供应商附件
	downloadFile : function() {
		var rows = $('#supplierAttachmentA_grid_sc').datagrid("getSelections");
		if (rows.length != 1) {
			$.messager.alert('提示', '请选择一个供应商附件');
			return;
		}
		window.location = "supplierAttachmentA_downloadFile_6.html?path=" + encodeURIComponent(rows[0].path);
	}
}
var supplierAttachmentAGLFn = {
	//刷新
	searchGL : function() {
		$("#supplierAttachmentA_grid_gl").datagrid('reload');
	},
	//下载
	downFileGL : function() {
		var rows = $('#supplierAttachmentA_grid_gl').datagrid("getSelections");
		if (rows.length != 1) {
			$.messager.alert('提示', '请选择一个报价单');
			return;
		}
		window.location = "quotedElectronicOfScan_downloadQuotedElectronicOfScanFile_6.html?path=" + encodeURI(encodeURI(rows[0].path));
	},
	//删除
	deleteGL:function(){
		var rows = $('#supplierAttachmentA_grid_gl').datagrid("getSelections");
		if (rows.length < 1) {
			$.messager.alert('提示', '请至少选择一个报价单');
			return;
		}
		var applicationCode="";
		var supplierCode="";
		var quotedCodeScan="";
		for(var i in rows){
			applicationCode += $("input#applicationCodeNow").val() + ",";
			if(rows[i].supplierCode==""|| rows[i].supplierCode == "null"||rows[i].supplierCode==null){
				supplierCode += rows[i].intentionSupplierCode + ",";
			}else{
				supplierCode += rows[i].supplierCode + ",";
			}
			quotedCodeScan += rows[i].quotedCodeScan + ",";
		}
		utils.post("quotedElectronicOfScan_deleteQuotedElectronicOfScan_2.html", {
			applicationCode : applicationCode,
			quotedCodeScan : quotedCodeScan,
			quotedCode:$("input#quotedCodes").val(),
			supplierCode : supplierCode,
			type:'ax'
		}, function() {
			$("#supplierAttachmentA_grid_gl").datagrid('reload');
		});
		$(".window-shadow").remove();
	}
}