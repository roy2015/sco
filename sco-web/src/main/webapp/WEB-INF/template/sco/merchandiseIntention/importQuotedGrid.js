
var importQuotedFn = {
	//导入报价单
	importQuoted:function(){
		var intentionCode=$('#intentionCode').val();
		var quotedDate=$("#quotedDate").datebox("getValue");
		if (quotedDate ==null || quotedDate == "") {
			$.messager.alert('提示','<center>请选择报价日期！</center>'); 
			return;
		}
		var panel3SupplierGrid = $('#panel3SupplierGrid').datagrid('getSelected');
		if(panel3SupplierGrid==null){
			$.messager.alert('提示','<center>请选择一条记录！</center>'); 
			return;
		}
		var  intentionSupplierCode=panel3SupplierGrid.intentionSupplierCode;//供应商编号
		
		//获取文件域信息
		 $('#uploadQuotedForm').ajaxForm({
				url: "merchandiseQuoted_uploadQuoted_2.html?intentionCode="+intentionCode+"&intentionSupplierCode="+intentionSupplierCode
					+"&quotedDate="+quotedDate,
				dataType:  'json',
				cache : false,
				success:function(json){
					//easyUIBugSolves.wartingDivHide();
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
						$.messager.alert('提示', "导入报价单成功!");
						$("#quotedFile").val("");
						var file = $("#uploadQuoted");
						file.after(file.clone().val("")); 
						file.remove();
						$('#panel3SupplierGrid').datagrid('reload');
					}else{
						//$.messager.alert('快捷导入Excel数据错误提示!', msg);
						utils.showDlg({title:'快捷导入Excel数据错误提示',height:500,width:250,maximizable:true,resizable:true,content:msg},true);
					}
					/*$("#quotedFile").val("");
					$("#uploadQuoted").val("");*/
				 }
			});
				//验证上传格式
		 var uploadVar = $('#quotedFile').val();
			if(uploadVar==''){
	   			$.messager.alert('快捷导入Excel数据错误提示','<center>请选择文件!</center>');  
			}else{
				var fileName = $('#quotedFile').val();
		    	var a=fileName.lastIndexOf(".");
		    	var fileType = fileName.substring(a+1, fileName.length).toLowerCase();
		    	if(fileType!='xlsx'){
		    		$.messager.alert("快捷导入Excel数据错误提示", "请上传以下格式的附件：xxx.xlsx");
		    	}else{
		   		 $.messager.show({
						title:'快捷导入Excel数据提示',
						msg:'导入Excel数据中，请稍后！',
						timeout:2000,
						showType:'slide'
					});
		   		 	//easyUIBugSolves.wartingDivShow('panel4_toolbar');
		    		$('#uploadQuotedForm').submit();
		    	}
			}
    }
    
};
