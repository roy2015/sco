
var accessoryEnquiryFormFn = {
	// 保存添加或修改
		deleteXJD:function(){
			var enquiryCode=[];
			var record=$('#accessoryEnquiry_grid').datagrid('getChecked');
			if(record.length == 0){
				$.messager.alert('提示','<center>请至少选择一条记录！</center>'); 
			}else{
				utils.confirm("操作确认","确认删除询价单吗?",function(){
				var intentionCode=record[0].intentionCode;
			/*$.post('accessoryIntention_deleteXJD_2.html', {enquiryCode:record.enquiryCode,intentionCode:record.intentionCode}, function(json){
				if(json.success){
					parent.messagerShow({title:'操作成功!', msg:json.msg});
					$('#accessoryEnquiry_grid').datagrid('reload');
				}else{
					parent.messagerShow({title:'操作失败!', msg:json.msg});
					$('#accessoryEnquiry_grid').datagrid('reload');
				}
				// console.dir(json);
				// console.dir(json.rows.aaa);
			},"json");*/
						$.each(record, function(index, item) {
							enquiryCode.push(item.enquiryCode);
						});
					//console.info(intentionSupplierCode);
					utils.post("accessoryIntention_deleteXJD_2.html?enquiryCode=" + enquiryCode+"&intentionCode="+intentionCode, null, function(json) {
						
						if(json.success){
							parent.messagerShow({title:'操作成功!', msg:json.msg});
							$('#accessoryEnquiry_grid').datagrid('reload');
						}else{
							parent.messagerShow({title:'操作失败!', msg:json.msg});
							$('#accessoryEnquiry_grid').datagrid('reload');
						}
					});
				});
			}
			$(".window-shadow").remove();
		},
		createXJD:function(isAdd,enquiryCode){
			var title='新增询价单';
			if(isAdd){
			var href='accessoryIntention_showInsertXJDForm_1.html';
			}else{
				var href='accessoryIntention_showInsertXJDForm_1.html?enquiryCode='+enquiryCode;
			}
			var dlg=utils.showDlg({
				title:title,href:href,fit:false,width:800,height:400,
				toolbar:[{text:'保存',handler:function(){accessoryEnquiryFormFn.saveXJD(dlg);},iconCls:'save'},
				        {text:'关闭',handler:function(){dlg.dialog('close');$('#accessoryEnquiry_grid').datagrid('reload');},iconCls:'cancel'}
				]
			});
		},
		editXJD:function(){
			var record = $('#accessoryEnquiry_grid').datagrid('getChecked');
			if(record.length == 0){
				$.messager.alert('提示','<center>请至少选择一条记录！</center>'); 
			}else if(record.length>1){
				$.messager.alert('提示','<center>最多选择一条记录！</center>'); 
			}else{
				record= $('#accessoryEnquiry_grid').datagrid('getSelected');
				var isedit=false;
				var enquiryCode=record.enquiryCode;
				accessoryEnquiryFormFn.createXJD(isedit,enquiryCode);
			}
		},
		/* 保存询价单 */
		saveXJD:function(dlg){
			
		/*	 var uploadVar = $('#upload_xjd').val();
				if(uploadVar!=''){
		  			
					var fileName = $('#upload_xjd').val();
			    	var a=fileName.lastIndexOf(".");
			    	var fileType = fileName.substring(a+1, fileName.length).toLowerCase();
			    	if(fileType!='jpg'&& fileType!='jpeg'&& fileType!='bmp'){
			    		$.messager.alert("错误提示", "只能上传.jpg,.jpeg,.bmp格式的附件");
			    		return
			    	}
				}*/
			var uploadVar = $('#upload_xjd').val();
			if(uploadVar!=''){
		    	if(uploadVar.length>100){
		    		$.messager.alert("错误提示", "上传的文件名过长");
		    		return;
		    	}
			}
			 var enquiryCode=$('#enquiryCode').val(); 
			 var intentionCode=$('#intentionCode').val(); 
			 var lastIndex5=$('#dg5').datagrid('getRows').length-1;
			 if(lastIndex5=='-1'){
				 $.messager.alert('提示', '<center>报价数量模块不能为空</center>');
					return  
			 }
			var index5=$('#dg5').datagrid('getRowIndex',
					$('#dg5').datagrid('getSelected'));
			 $('#dg5').datagrid('endEdit', index5);
			 var r5=$('#dg5').datagrid('getSelected');
			 if(r5!=null){
			 var quotedCount=r5.quotedCount;
			 if(quotedCount==undefined){
				 $.messager.alert('提示', '<center>报价数量不能为空</center>');
					return  
			 }
			 }
			 var rows5=$('#dg5').datagrid('getRows');
			 
			 var lastIndex=$('#dg').datagrid('getRows').length-1;
			 if(lastIndex=='-1'){
				 $.messager.alert('提示', '<center>原材料模块不能为空</center>');
					return  
			 }
			 var index=$('#dg').datagrid('getRowIndex',
						$('#dg').datagrid('getSelected'));
			 $('#dg').datagrid('endEdit', index);
			 var r=$('#dg').datagrid('getSelected');
			 if(r!=null){
			 var materialName=r.materialName;
			 if(materialName==undefined){
				 $.messager.alert('提示', '<center>原材料模块必填项不能为空</center>');
					return  
			 }
			 /*
				 * var material=r.material; if(material==undefined){
				 * $.messager.alert('提示', '<center>原材料模块材料不能为空</center>');
				 * return } var materialSize=r.materialSize;
				 * if(materialSize==undefined){ $.messager.alert('提示', '<center>原材料模块尺寸不能为空</center>');
				 * return }
				 */
			 }
			 var rows1=$('#dg').datagrid('getRows');
			 
			 var index2=$('#dg2').datagrid('getRowIndex',
						$('#dg2').datagrid('getSelected'));
				 $('#dg2').datagrid('endEdit', index2);
			 var rows2=$('#dg2').datagrid('getRows');
			 
			 var lastIndex3=$('#dg3').datagrid('getRows').length-1;
			 if(lastIndex3=='-1'){
				 $.messager.alert('提示', '<center>内外包装模块不能为空</center>');
					return  
			 }
			 var index3=$('#dg3').datagrid('getRowIndex',
						$('#dg3').datagrid('getSelected'));
			 $('#dg3').datagrid('endEdit', index3);
			 var r3=$('#dg3').datagrid('getSelected');
			 if(r3!=null){
			 var packingName=r3.packingName;
			 if(packingName==undefined){
				 $.messager.alert('提示', '<center>内外包装模块必填项不能为空</center>');
					return  
			 }
			/*
			 * var packingMaterial=r3.packingMaterial;
			 * if(packingMaterial==undefined){ $.messager.alert('提示', '<center>内外包装模块材料不能为空</center>');
			 * return }
			 */
			 }
			 var rows3=$('#dg3').datagrid('getRows');
			 
			// var lastIndex4=$('#dg4').datagrid('getRows').length-1;
			 var index4=$('#dg4').datagrid('getRowIndex',
						$('#dg4').datagrid('getSelected'));
				 $('#dg4').datagrid('endEdit', index4);
			// $('#dg4').datagrid('endEdit', lastIndex4);
			 var rows4=$('#dg4').datagrid('getRows');
			 
			 var index6=$('#dg6').datagrid('getRowIndex',
						$('#dg6').datagrid('getSelected'));
				 $('#dg6').datagrid('endEdit', index6);
			 var rows6=$('#dg6').datagrid('getRows');
			 
			 var quotedUnits=$('#quotedUnits').val();
			 var paymentType=$('#paymentType').val();
			 var deliveryType=$('#deliveryType').val();
			 var remarks=$('#remarks').val();
			 var quotedCurrency=$('#quotedCurrency').combobox("getValue");
			/*
			 * $.post('accessoryIntention_insertAccessoryIntentionXJD_2.html',
			 * {rows1:JSON.stringify(rows1),rows2:JSON.stringify(rows2),rows3:JSON.stringify(rows3),rows4:JSON.stringify(rows4),rows5:JSON.stringify(rows5),rows6:JSON.stringify(rows6),enquiryCode:enquiryCode,intentionCode:intentionCode,quotedCurrency:quotedCurrency,quotedUnits:quotedUnits,paymentType:paymentType,deliveryType:deliveryType,remarks:remarks},
			 * function(json){ if(json.success){
			 * parent.messagerShow({title:'操作成功!', msg:json.msg});
			 * $('#accessoryEnquiry_grid').datagrid('reload'); }else{
			 * parent.messagerShow({title:'操作失败!', msg:json.msg});
			 * $('#accessoryEnquiry_grid').datagrid('reload'); } },"json");
			 */
		/*	 $('#createXJD_form').ajaxForm({
					url: "accessoryIntention_insertAccessoryIntentionXJD_2.html?rows1="+JSON.stringify(rows1)+"&rows2="+JSON.stringify(rows2)+"&rows3="+JSON.stringify(rows3)+"&rows4="+JSON.stringify(rows4)+"&rows5="+JSON.stringify(rows5)+"&rows6="+JSON.stringify(rows6)
						+"&intentionCode="+intentionCode+"&enquiryCode="+enquiryCode+"&quotedUnits="+quotedUnits+"&paymentType="+paymentType+"&deliveryType="+deliveryType+"&remarks="+remarks+"&quotedCurrency="+quotedCurrency,
					dataType:  'json',
					cache : false,
					success:function(json){
						
						if (json.success){
							$('#accessoryEnquiry_grid').datagrid('reload');
							$.messager.alert('提示', "保存成功!");
						}else{
							$('#accessoryEnquiry_grid').datagrid('reload');
							$.messager.alert('提示', "保存失败!");
							
						}
					 }
				});
				$('#createXJD_form').submit();
				*/
			 if(!$('#createXJD_form').form('validate')){
					return;
				}
				 $("#createXJD_form").form('submit', {
						url : "accessoryIntention_insertAccessoryIntentionXJD_2.html?intentionCode="+intentionCode+"&enquiryCode="+enquiryCode,
						onSubmit : function(param) {
							param.rows1 = JSON.stringify(rows1);
							param.rows2 = JSON.stringify(rows2);
							param.rows3 = JSON.stringify(rows3);
							param.rows4 = JSON.stringify(rows4);
							param.rows5 = JSON.stringify(rows5);
							param.rows6 = JSON.stringify(rows6);
						},
						success : function(data) {
							var json = $.parseJSON(data);
							if (json.success) {
								parent.messagerShow({
									title : '操作成功!',
									msg : json.msg
								});
							}else{
								$.messager.alert('提示', json.msg);
								/*parent.messagerShow({
									title : '操作失败!',
									msg : json.msg
								});*/
							}
							dlg.dialog('close');
							$('#accessoryEnquiry_grid').datagrid('reload');
						}
					});
				 
				
			
		},
		downloadEnquiryFile:function(index){
			//	var path=$('#accessoryProofing_dyxx').datagrid('getRows')[index].path;
				var path=$('#accessoryEnquiry_grid').datagrid('selectRow',index).datagrid('getSelected').attachment;
				$('#accessoryEnquiry_grid').datagrid('unselectRow',index);
				window.location ="accessoryIntention_downloadProofingPicture_6.html?path="+ encodeURIComponent(path);
			}
}