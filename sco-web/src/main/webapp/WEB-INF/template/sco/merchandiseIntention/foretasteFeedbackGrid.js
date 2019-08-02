var feedbackFn = {
  //添加试吃反馈
    showInsertForetaste:function() {
		var intentionCode=$('#intentionCode').val();
		var panel6SupplierGrid = $('#panel6SupplierGrid').datagrid('getChecked');
		
		if(panel6SupplierGrid==null||panel6SupplierGrid.length==0){
			window.parent.$.messager.alert('提示','<center>请选择一条记录！</center>'); 
			return;
		}else if(panel6SupplierGrid.length>1){
			window.parent.$.messager.alert('提示','<center>请只选择一条记录！</center>'); 
			return;
		}
		
		var  intentionSupplierCode= "";//供应商编号
		$.each(panel6SupplierGrid, function(index, item){
			intentionSupplierCode=item.intentionSupplierCode;
		});
		
		var title='${action.getText("common.title.add","新增试吃反馈")}';
		var href="foretasteFeedback_showInsertForetasteForm_1.html?intentionCode="+intentionCode
		+"&intentionSupplierCode="+intentionSupplierCode;
		
		var dlg=utils.showDlg({
			title:title,href:href,width:550,height:240,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){feedbackFn.submitForetasteForm(dlg,true);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			],
			onLoad:function(){
			}
		});
		//消除阴影
		$(".window-shadow").remove();
	},
	//修改试吃反馈
	showUpdateForetaste:function(){
		var foretasteRecord=$('#panel6ForetasteGrid').datagrid('getChecked');
		if(foretasteRecord==null){
			window.parent.$.messager.alert('提示','<center>请选择一条记录！</center>'); 
			return;
		}else{
			if(foretasteRecord.length==1){
				var  feedbackCode= "";
				$.each(foretasteRecord, function(index, item){
					feedbackCode=item.feedbackCode;
				});
				var title='${action.getText("common.title.edit","修改试吃反馈")}';
				var href="foretasteFeedback_showUpdateForetasteForm_1.html?feedbackCode="+feedbackCode;
				
				var dlg=utils.showDlg({
					title:title,href:href,width:550,height:240,
					buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){feedbackFn.submitForetasteForm(dlg,false);},iconCls:'save'},
					        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
					],
					onLoad:function(){
					}
				});
				//消除阴影
				$(".window-shadow").remove();
			}else{
				window.parent.$.messager.alert('提示','<center>请只选择一条记录！</center>'); 
				return;
			}
		}
		
	},
	//提交新增或修改试吃反馈表单
	submitForetasteForm:function(dlg,isInsert){
		var url="";
		if(isInsert){//提交新增表单
			url = 'foretasteFeedback_insertForetaste_2.html';
		}else{//提交修改表单
			var foretasteRecord=$('#panel6ForetasteGrid').datagrid('getChecked');
			if(foretasteRecord==null){
				window.parent.$.messager.alert('提示','<center>请选择一条记录！</center>'); 
				return;
			}else{
				if(foretasteRecord.length==1){
					url='foretasteFeedback_updateForetaste_2.html';
				}else{
					window.parent.$.messager.alert('提示','<center>请只选择一条记录！</center>'); 
					return;
				}
			}
		}

		utils.form("foretasteFeedback_form").submit(url,null,function(){
			dlg.dialog('close');
			$('#panel6ForetasteGrid').datagrid('reload');
		});
	},
	//删除试吃反馈
	deleteForetaste:function(){
		var foretasteRecord=$('#panel6ForetasteGrid').datagrid('getChecked');
		if(foretasteRecord==null||foretasteRecord.length==0){
			window.parent.$.messager.alert('提示','<center>请选择至少一条记录!</center>'); 
			return;
		}else{
			var  feedbackCodes= [];
			$.each(foretasteRecord, function(index, item){
				feedbackCodes.push("'"+item.feedbackCode+"'");
			});
			
			utils.confirm("操作确认","确认删除试吃反馈?",function(){
				utils.post("foretasteFeedback_deleteForetaste_2.html?feedbackCodes="+feedbackCodes,null,function(){
					$('#panel6ForetasteGrid').datagrid('reload');
				});
			});
		}
	},
	//试吃通过
	passForetaste:function(){
		var intentionCode=$('#intentionCode').val();//意向品编号
		var panel6SupplierGrid=$('#panel6SupplierGrid').datagrid('getChecked');
		if(panel6SupplierGrid==null||panel6SupplierGrid.length==0){
			$.messager.alert('提示','<center>请选择至少一条记录!</center>'); 
			return;
		}else{
			var  intentionAndSupplierCodes= [];
			var foretastePassFalg=true;
			$.each(panel6SupplierGrid, function(index, item){
				if(item.isForetastePass=='是'){
					foretastePassFalg=false;
				}else{
					intentionAndSupplierCodes.push(intentionCode+":"+item.intentionSupplierCode);
				}
			});
			
			if(!foretastePassFalg){
				$.messager.alert('提示','<center>您所勾选的记录中存在已经试吃通过的供应商，请勿重复操作!</center>'); 
				return;
			}
			
			utils.confirm("操作确认","确认试吃通过?",function(){
				utils.post("foretasteFeedback_updateForetasteIsPass_2.html?intentionAndSupplierCodes="+intentionAndSupplierCodes+"&isForetastePass=Y",null,function(){
					$('#panel6SupplierGrid').datagrid('reload');
				});
			});
		}
	},
	//取消试吃通过
	cancelPassForetaste:function(){
		var intentionCode=$('#intentionCode').val();//意向品编号
		var panel6SupplierGrid=$('#panel6SupplierGrid').datagrid('getChecked');
		if(panel6SupplierGrid==null||panel6SupplierGrid.length==0){
			$.messager.alert('提示','<center>请选择至少一条记录!</center>'); 
			return;
		}else{
			var  intentionAndSupplierCodes= [];
			var foretastePassFalg=true;
			$.each(panel6SupplierGrid, function(index, item){
				if(item.isForetastePass=='否'){
					foretastePassFalg=false;
				}else{
					intentionAndSupplierCodes.push(intentionCode+":"+item.intentionSupplierCode);
				}
			});
			
			if(!foretastePassFalg){
				$.messager.alert('提示','<center>您所勾选的记录中存在未试吃通过的供应商，无法取消试吃通过!</center>'); 
				return;
			}
			
			utils.confirm("操作确认","确认取消试吃通过?",function(){
				utils.post("foretasteFeedback_updateForetasteIsPass_2.html?intentionAndSupplierCodes="+intentionAndSupplierCodes+"&isForetastePass=N",null,function(){
					$('#panel6SupplierGrid').datagrid('reload');
				});
			});
		}
	}
	
};
