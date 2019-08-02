
var importQuotationFn = {
		// 导入扫描版报价单
		importScanQuoted:function(){
			var intentionCode=$('#accessoryIntentionCode').val();
			var quotedDate=$("#quotedDate").datetimebox("getValue");
			if (quotedDate ==null || quotedDate == "") {
				window.parent.$.messager.alert('提示','<center>请选择报价日期！</center>'); 
				return;
			}
			var accessoryIntentionSupplierGrid = $('#accessoryIntentionSupplier_drbjd').datagrid('getSelected');
			if(accessoryIntentionSupplierGrid==null){
				window.parent.$.messager.alert('提示','<center>请选择供应商！</center>'); 
				return;
			}
			var accessoryEnquiryGrid = $('#accessoryEnquiry_drbjd').datagrid('getSelected');
			if(accessoryEnquiryGrid==null){
				window.parent.$.messager.alert('提示','<center>请选择询价单！</center>'); 
				return;
			}
			var  intentionSupplierCode=accessoryIntentionSupplierGrid.intentionSupplierCode;// 供应商编号
			var  enquiryCode=accessoryEnquiryGrid.enquiryCode;// 询价单编号
		// 获取文件域信息
		 $('#uploadAccessoryQuotedForm').ajaxForm({
				url: "accessoryIntention_uploadScanQuotedExcel_2.html?intentionCode="+intentionCode+"&intentionSupplierCode="+intentionSupplierCode
					+"&quotedDate="+quotedDate+"&enquiryCode="+enquiryCode,
				dataType:  'json',
				cache : false,
				success:function(json){
					// easyUIBugSolves.wartingDivHide();
				//	console.info(json);
					if (json.success){
						$.messager.alert('提示', "导入扫描版报价单成功!");
					}else{
						// $.messager.alert('快捷导入Excel数据错误提示!', msg);
						$.messager.alert('提示', json.msg);
					}
					$("#accessoryQuotedFile").val("");
					$("#accessoryUploadQuoted").val("");
					$('#accessoryIntentionSupplier_drbjd').datagrid('reload');
					$('#accessoryEnquiry_drbjd').datagrid('reload');
					$('#accessoryQuotedScan_drbjd').datagrid('reload');
				 }
			});
				// 验证上传格式
		 var uploadVar = $('#upload_import').val();
			if(uploadVar==''){
				window.parent.$.messager.alert('错误提示','<center>请选择文件!</center>');  
			}else{
                	if(uploadVar.length>100){
                		window.parent.$.messager.alert("错误提示", "上传的文件名过长");
    		    		return;
    		    	}
		   		 $.messager.show({
						title:'提示',
						msg:'导入文件中，请稍后！',
						timeout:2000,
						showType:'slide'
					});
		   		 	// easyUIBugSolves.wartingDivShow('panel4_toolbar');
		    		$('#uploadAccessoryQuotedForm').submit();
		    	
			}
		},
		// 导入电子版报价单
		importQuoted:function(){
			var intentionCode=$('#accessoryIntentionCode').val();
			var quotedDate=$("#quotedDate").datetimebox("getValue");
			if (quotedDate ==null || quotedDate == "") {
				window.parent.$.messager.alert('提示','<center>请选择报价日期！</center>'); 
				return;
			}
			var accessoryIntentionSupplierGrid = $('#accessoryIntentionSupplier_drbjd').datagrid('getSelected');
			if(accessoryIntentionSupplierGrid==null){
				window.parent.$.messager.alert('提示','<center>请选择供应商！</center>'); 
				return;
			}
			var accessoryEnquiryGrid = $('#accessoryEnquiry_drbjd').datagrid('getSelected');
			if(accessoryEnquiryGrid==null){
				window.parent.$.messager.alert('提示','<center>请选择询价单！</center>'); 
				return;
			}
			var  intentionSupplierCode=accessoryIntentionSupplierGrid.intentionSupplierCode;// 供应商编号
			var  enquiryCode=accessoryEnquiryGrid.enquiryCode;// 询价单编号
		// 获取文件域信息
		 $('#uploadAccessoryQuotedForm').ajaxForm({
				url: "accessoryIntention_uploadQuotedExcel_2.html?intentionCode="+intentionCode+"&intentionSupplierCode="+intentionSupplierCode
					+"&quotedDate="+quotedDate+"&enquiryCode="+enquiryCode,
				dataType:  'json',
				cache : false,
				success:function(json){
					// easyUIBugSolves.wartingDivHide();
					var msg=json.msg;
					if(msg!=undefined&&(msg!=null)){
					var arrM=msg.split('!');
				    if(arrM.length>0){
				    	msg='';
				    	msg=arrM[0];
				    	}
				    if(arrM.length>1){
					for(var i=1;i!=arrM.length;i++){
						msg=msg+('!<br/>'+arrM[i]);
					}
					}else{
						msg=msg+'';
					}
					}
					if (json.success){
						window.parent.$.messager.alert('提示', "导入报价单成功!");
					}else{
					//	$.messager.alert('提示', msg);
						// $.messager.alert('快捷导入Excel数据错误提示!', msg);
						utils.showDlg({title:'快捷导入Excel数据错误提示',height:500,width:250,maximizable:true,resizable:true,content:msg},true);
					}
					$("#accessoryQuotedFile").val("");
					$("#accessoryUploadQuoted").val("");
					$('#accessoryIntentionSupplier_drbjd').datagrid('reload');
					$('#accessoryEnquiry_drbjd').datagrid('reload');
					$('#accessoryQuotedElectronic_drbjd').datagrid('reload');
				 }
			});
				// 验证上传格式
		 var uploadVar = $('#upload_import').val();
			if(uploadVar==''){
				window.parent.$.messager.alert('快捷导入Excel数据错误提示','<center>请选择文件!</center>');  
			}else{
				var fileName = $('#upload_import').val();
		    	var a=fileName.lastIndexOf(".");
		    	var fileType = fileName.substring(a+1, fileName.length).toLowerCase();
                if(fileName!=''){
                	if(fileName.length>100){
                		window.parent.$.messager.alert("错误提示", "上传的文件名过长");
    		    		return;
    		    	}
				}
		    	if(fileType!='xlsx'){
		    		window.parent.$.messager.alert("快捷导入Excel数据错误提示", "请上传以下格式的附件：xxx.xlsx");
		    	}else{
		   		 $.messager.show({
						title:'快捷导入Excel数据提示',
						msg:'导入Excel数据中，请稍后！',
						timeout:2000,
						showType:'slide'
					});
		   		 	// easyUIBugSolves.wartingDivShow('panel4_toolbar');
		    		$('#uploadAccessoryQuotedForm').submit();
		    	}
			}
	},
	downloadAccessoryQuotedElectronic:function(intentionCode){
		var record = $('#accessoryQuotedElectronic_drbjd').datagrid('getChecked');
		if(record.length == 0){
			window.parent.$.messager.alert('提示','<center>请选择一条记录！</center>'); 
			return;
		}
		if(record.length> 1){
			window.parent.$.messager.alert('提示','<center>请最多选择一条记录！</center>'); 
		}else {
			record = $('#accessoryQuotedElectronic_drbjd').datagrid('getSelected');
			window.location ="accessoryIntention_downloadAccessoryQuotedElectronic_6.html?intentionCode="+intentionCode+"&quotedCode="+record.quotedCode;
		}
		$(".window-shadow").remove();
	},
	downloadAccessoryQuotedScan:function(intentionCode){
		var record = $('#accessoryQuotedScan_drbjd').datagrid('getChecked');
		if(record.length == 0){
			window.parent.$.messager.alert('提示','<center>请选择一条记录！</center>'); 
			return;
		}
		if(record.length> 1){
			window.parent.$.messager.alert('提示','<center>请最多选择一条记录！</center>'); 
		}else{
			record = $('#accessoryQuotedScan_drbjd').datagrid('getSelected');
			window.location ="accessoryIntention_downloadAccessoryQuotedScan_6.html?intentionCode="+intentionCode+"&quotedCode="+record.quotedCode;
		}
		$(".window-shadow").remove();
	},

	deleteAccessoryQuotedElectronic:function(intentionCode){
		var quotedCode=[];
		var record = $('#accessoryQuotedElectronic_drbjd').datagrid('getChecked');
		if(record.length == 0){
			window.parent.$.messager.alert('提示','<center>请至少选择一条记录！</center>'); 
		}else{
			utils.confirm("操作确认","确认删除电子版报价单吗?",function(){
/*		$.post('accessoryIntention_deleteAccessoryQuotedElectronic_2.html', {quotedCode:record.quotedCode,intentionCode:intentionCode}, function(json){
			if(json.success){
				parent.messagerShow({title:'操作成功!', msg:json.msg});
				$('#accessoryQuotedElectronic_drbjd').datagrid('reload');
			}else{
				parent.messagerShow({title:'操作失败!', msg:json.msg});
				$('#accessoryQuotedElectronic_drbjd').datagrid('reload');
			}
			// console.dir(json);
			// console.dir(json.rows.aaa);
		},"json");*/
				$.each(record, function(index, item) {
					quotedCode.push(item.quotedCode);
				});  
				$.post('accessoryIntention_deleteAccessoryQuotedElectronic_2.html', {quotedCode:quotedCode,intentionCode:intentionCode}, function(json) {
					
					if(json.success){
						parent.messagerShow({title:'操作成功!', msg:json.msg});
						$('#accessoryQuotedElectronic_drbjd').datagrid('reload');
					}else{
						window.parent.$.messager.alert("操作失败",json.msg);
						$('#accessoryQuotedElectronic_drbjd').datagrid('reload');
					}
					
				},"json");
		});
	}
		$(".window-shadow").remove();
	},
	deleteAccessoryQuotedScan:function(intentionCode){
		var quotedCode=[];
		var record = $('#accessoryQuotedScan_drbjd').datagrid('getChecked');
		if(record.length == 0){
			window.parent.$.messager.alert('提示','<center>请至少选择一条记录！</center>'); 
		}else{
		utils.confirm("操作确认","确认删除扫描版报价单吗?",function(){
		/*$.post('accessoryIntention_deleteAccessoryQuotedScan_2.html', {quotedCode:record.quotedCode,intentionCode:intentionCode}, function(json){
			if(json.success){
				parent.messagerShow({title:'操作成功!', msg:json.msg});
				$('#accessoryQuotedScan_drbjd').datagrid('reload');
			}else{
				parent.messagerShow({title:'操作失败!', msg:json.msg});
				$('#accessoryQuotedScan_drbjd').datagrid('reload');
			}
			// console.dir(json);
			// console.dir(json.rows.aaa);
		},"json");*/
			$.each(record, function(index, item) {
				quotedCode.push(item.quotedCode);
			});
			utils.post("accessoryIntention_deleteAccessoryQuotedScan_2.html?quotedCode=" + quotedCode+"&intentionCode="+intentionCode, null, function() {
				$('#accessoryQuotedScan_drbjd').datagrid('reload');
			});
		});
		}
		$(".window-shadow").remove();
	}
}