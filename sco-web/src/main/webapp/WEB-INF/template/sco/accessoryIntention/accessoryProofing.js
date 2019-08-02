var accessoryProofingFn = {
		// 添加打样信息
		showUpdateProofing:function(intentionCode) {
			var proofingRecord=$('#accessoryQuotedElectronic_dyxx').datagrid('getSelected');
			if(proofingRecord==null){
				$.messager.alert('提示','<center>请选择一条记录！</center>'); 
				return;
			}
			var quotedCode=proofingRecord.quotedCode;
			var enquiryCode=proofingRecord.enquiryCode;
			var supplierCode=proofingRecord.supplierCode;
			var intentionSupplierCode=proofingRecord.intentionSupplierCode;
			var title='新增打样信息';
			var href="accessoryIntention_showInsertProofingForm_1.html?intentionCode="+intentionCode
			+"&quotedCode="+quotedCode+"&supplierCode="+supplierCode+"&intentionSupplierCode="+intentionSupplierCode+"&enquiryCode="+enquiryCode;
			
			var dlg=utils.showDlg({
				title:title,href:href,width:500,height:300,
				buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){accessoryProofingFn.submitProofingForm(dlg,true);},iconCls:'save'},
				        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				],
				onLoad:function(){
				}
			});
		},
		deleteAccessoryProofing:function(intentionCode){
			var proofingCode=[];
			var record = $('#accessoryProofing_dyxx').datagrid('getChecked');
			if(record.length == 0){
				$.messager.alert('提示','<center>请至少选择一条记录！</center>'); 
			}else{
			utils.confirm("操作确认","确认删除打样信息吗?",function(){
			/*$.post('accessoryIntention_deleteAccessoryProofing_2.html', {proofingCode:record.proofingCode,intentionCode:intentionCode}, function(json){
				if(json.success){
					parent.messagerShow({title:'操作成功!', msg:json.msg});
					$('#accessoryProofing_dyxx').datagrid('reload');
				}else{
					parent.messagerShow({title:'操作失败!', msg:json.msg});
					$('#accessoryProofing_dyxx').datagrid('reload');
				}
				// console.dir(json);
				// console.dir(json.rows.aaa);
			},"json");*/
				$.each(record, function(index, item) {
					proofingCode.push(item.proofingCode);
				});
				utils.post("accessoryIntention_deleteAccessoryProofing_2.html?proofingCode=" + proofingCode+"&intentionCode="+intentionCode, null, function() {
					$('#accessoryProofing_dyxx').datagrid('reload');
				});
			});
			}
			$(".window-shadow").remove();
		},
		// 导出到Excel
		exportAccessoryProofingToExcel:function(intentionCode){
	    window.location="accessoryIntention_exportAccessoryProofingToExcel_3.html?intentionCode="+intentionCode;
			
			$.messager.show({
				title:'文件导出提示',
				msg:'文件正在导出中，请稍后！',
				timeout:4000,
				showType:'slide'
			});
		},
		downloadProofingPicture:function(index){
		//	var path=$('#accessoryProofing_dyxx').datagrid('getRows')[index].path;
			var path=$('#accessoryProofing_dyxx').datagrid('selectRow',index).datagrid('getSelected').path;
			$('#accessoryProofing_dyxx').datagrid('unselectRow',index);
			window.location ="accessoryIntention_downloadProofingPicture_6.html?path="+ encodeURIComponent(path);
		},
		selectProofing :function(){
			 $("#haveRemarks").val("");
			 $('#haveProofing').show();
			 $('#noProofing').hide();
		},
		selectNoProofing :function(){
			 $('#haveRemarks').val("true");
			 $('#haveProofing').hide();
			 $('#noProofing').show();
		},
		// 提交打样信息表单
		submitProofingForm:function(dlg,isInsert){
			
			
		/*
		 * var url = 'accessoryIntention_insertProofing_2.html';
		 * 
		 * 
		 * utils.form("accessoryProofing_form").submit(url,null,function(){
		 * dlg.dialog('close'); $('#accessoryProofing_dyxx').datagrid('reload');
		 * });
		 */
			if($('#haveRemarks').val().length>0){
				$('#accessoryNoProofing_form').ajaxForm({
					url: "accessoryIntention_insertProofing_2.html",
					dataType:  'json',
					cache : false,
					success:function(json){
						// easyUIBugSolves.wartingDivHide();
						
						if (json.success){
							
							$.messager.alert('提示', "操作成功!");
							dlg.dialog('close');	
							$('#accessoryProofing_dyxx').datagrid('reload');
						}else{
							
							// $.messager.alert('快捷导入Excel数据错误提示!', msg);
							$.messager.alert('提示', json.msg);
							$('#accessoryProofing_dyxx').datagrid('reload');
						}
					 }
				});
				if(!$('#accessoryNoProofing_form').form('validate')){
					return;
				}

				$('#accessoryNoProofing_form').submit();
				
			}else{
				var fileName = $('#upload_proof').val();
				if(fileName!=''){
		  			
					if(fileName.length>100){
    		    		$.messager.alert("错误提示", "上传的文件名过长");
			    		return;
			    	}
				}
			$('#accessoryProofing_form').ajaxForm({
				url: "accessoryIntention_insertProofing_2.html",
				dataType:  'json',
				cache : false,
				success:function(json){
					// easyUIBugSolves.wartingDivHide();
					
					if (json.success){
						$('#accessoryProofing_dyxx').datagrid('reload');
						$.messager.alert('提示', "操作成功!");
						dlg.dialog('close');	
					}else{
						$('#accessoryProofing_dyxx').datagrid('reload');
						// $.messager.alert('快捷导入Excel数据错误提示!', msg);
						$.messager.alert('提示', json.msg);
						dlg.dialog('close');	
					}
				 }
			});
			if(!$('#accessoryProofing_form').form('validate')){
				return;
			}
			$('#accessoryProofing_form').submit();
			}
		},
		//计算打样周期
		jsdyzq:function(dlg,isInsert){
			var strSeparator = "-"; //日期分隔符
			   var oDate1;
			   var oDate2;
			   var iDays;
			   var askdate=$("#askProofingDate").datebox("getValue");
			   var prodate=$("#proofingDate").datebox("getValue");
			   if(askdate!=null&&prodate!=null&&askdate!=''&&prodate!=''){
			   oDate1= $("#askProofingDate").datebox("getValue").split(strSeparator);
			   oDate2= $("#proofingDate").datebox("getValue").split(strSeparator);
			   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
			   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
			   if(strDateS>strDateE){
				   $.messager.alert('提示', "打样完成日期必须大于等于要求打样日期"); 
				   return;
			   }
			   iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24+1)//把相差的毫秒数转换为天数 
			   $("#proofingCycle").val(iDays);
			   }
		}
}