var insertQuotedFn = {
	//添加报价单
	showInsertQuoted:function() {
		var intentionCode=$('#intentionCode').val();
		var panel4SupplierGrid = $('#panel4SupplierGrid').datagrid('getSelected');
		if(panel4SupplierGrid==null){
			$.messager.alert('提示','<center>请选择一条记录！</center>'); 
			return;
		}
		var  intentionSupplierCode=panel4SupplierGrid.intentionSupplierCode;//供应商编号
		
		var title='${action.getText("common.title.add","新增报价单")}';
		var href="merchandiseQuoted_showInsertQuotedForm_1.html?intentionCode="+intentionCode
		+"&intentionSupplierCode="+intentionSupplierCode;
		
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:440,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){insertQuotedFn.submitQuotedForm(dlg,true);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			],
			onLoad:function(){
			}
		});
		//消除阴影
		$(".window-shadow").remove();
	},
	//修改报价单
	showUpdateQuoted:function(){
		var quotedRecord=$('#panel4QuotedGrid').datagrid('getSelected');
		if(quotedRecord==null){
			$.messager.alert('提示','<center>请选择一条记录！</center>'); 
			return;
		}
		var quotationCode=quotedRecord.quotationCode
		var title='${action.getText("common.title.edit","修改报价单")}';
		var href="merchandiseQuoted_showUpdateQuotedForm_1.html?quotationCode="+quotationCode;
		
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:440,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){insertQuotedFn.submitQuotedForm(dlg,false);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			],
			onLoad:function(){
			}
		});
		//消除阴影
		$(".window-shadow").remove();
	},
	//提交新增或修改报价单表单
	submitQuotedForm:function(dlg,isInsert){
		var url="";
		if(isInsert){//提交新增表单
			url = 'merchandiseQuoted_insertQuoted_2.html';
		}else{//提交修改表单
			var quotedRecord=$('#panel4QuotedGrid').datagrid('getSelected');
			if(quotedRecord==null){
				$.messager.alert('提示','<center>请选择一条记录！</center>'); 
				return;
			};
			url='merchandiseQuoted_updateQuoted_2.html';
		}

		utils.form("merchandiseQuoted_form").submit(url,null,function(){
			dlg.dialog('close');
			$('#panel4QuotedGrid').datagrid('reload');
		});
	},
	//删除报价单
	deleteQuoted:function(){
		var quotedRecord=$('#panel4QuotedGrid').datagrid('getSelected');
		if(quotedRecord==null){
			$.messager.alert('提示','<center>请选择一条记录！</center>'); 
			return;
		};
		utils.confirm("操作确认","确认删除报价单?",function(){
			utils.post("merchandiseQuoted_deleteQuotedById_2.html",{quotationCode:quotedRecord.quotationCode},function(){
				$('#panel4QuotedGrid').datagrid('reload');
			});
		});
		//消除阴影
		$(".window-shadow").remove();
	},
	
	//千分位
	moneyFormatter:function(value) {
		return moneyFormatter(value, 3);
	}
	
};
