
var dhInfoFn = {
		// 添加大货信息
		showUpdateDhFile:function(applicationCode) {
			var applicationQuotedRecord=$('#applicationQuoted_grid').datagrid('getChecked');
			if (applicationQuotedRecord.length == 0) {
				$.messager.alert('提示', '<center>请选择一条记录！</center>');
				return;
			}
			if (applicationQuotedRecord.length > 1) {
				$.messager.alert('提示', '<center>请最多选择一条记录！</center>');
				return;
			}
			var record=$('#applicationQuoted_grid').datagrid('getSelected');
			var intentionCode=record.intentionCode;
			var supplierCode=record.supplierCode;
			var title='上传新文件';
			var href="compareApply_showInsertDhFile_1.html?intentionCode="+intentionCode+"&supplierCode="+supplierCode+"&applicationCode="+applicationCode;
			
			var dlg=utils.showDlg({
				title:title,href:href,width:600,height:440,
				buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){dhInfoFn.submitInsertDhfile(dlg);},iconCls:'save'},
				        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				],
				onLoad:function(){
				}
			});
		},
		submitInsertDhfile:function(dlg){
			var uploadVar = $('#uploadCompare').val();
			if (uploadVar == '') {
				$.messager.alert('上传文件错误提示', '<center>请选择文件!</center>');
				return;
			}
			if(uploadVar!=''){
				if(uploadVar.length>100){
		    		$.messager.alert("错误提示", "上传的文件名过长");
		    		return;
		    	}
			}
			var fileType= $('#fileType').combobox("getValue");
			var fileTypeOther=$('#fileTypeOther').val();
			if(fileType=='其他' && fileTypeOther.length<1){
				$.messager.alert('错误提示', '<center>其他类型必须填写!</center>');
				return;
			}
			if(fileType=='其他' && fileTypeOther.length>10){
				$.messager.alert('错误提示', '<center>其他类型填写内容超长!</center>');
				return;
			}
			utils.form("dhinfo_form").submit("compareApply_insertDhInfo_2.html",null,function(){
				   dlg.dialog('close');
				   $('#dhInfo_grid').datagrid('reload');
				  });
				},
			showFileTypeOther:function(){
				var fileType= $('#fileType').combobox("getValue");
				if(fileType=='其他'){
					 $('#otherType').show();
				}else{
					$('#otherType').hide();
				}
			},

			deleteDhInfo:function(intentionCode){
				var dhId=[];
				var record = $('#dhInfo_grid').datagrid('getChecked');
				if (record.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
					return;
				} else{
					$.each(record, function(index, item) {
						dhId.push(item.id);
					});
				utils.confirm("操作确认","确认删除大货信息吗?",function(){
					utils.post("compareApply_deleteDhInfo_2.html?dhId="+dhId,null,function(){
						$('#dhInfo_grid').datagrid('reload');
					});
				});
				}
			},
			downloadDhFile:function(path){
				window.location ="compareApply_downloadDhFile_6.html?path="+ encodeURIComponent(path);
			}
};
