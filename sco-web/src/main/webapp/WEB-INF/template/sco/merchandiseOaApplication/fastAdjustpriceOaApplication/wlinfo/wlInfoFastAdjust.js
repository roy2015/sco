var fastAdjustWlInfoFn = {

	// 显示添加或修改窗体
	showForm : function(isEdit) {
		var title = '';
		var href = '';
		var rows = '';
		if (isEdit) {// 显示修改窗体
			rows = $('#fastAdjustWlInfoGrid').datagrid("getSelections");
			if (rows.length != 1) {
				window.parent.$.messager.alert('提示', '请只选择一条商品记录');
				return ;
			}
			
			title = '修改物料信息';
			href = 'wlInfoFastAdjust_showUpdateWlInfoForm_1.html?applicationCode=${applicationCode}'+
				'&intentionCode='+rows[0].intentionCode+'&supplierCode='+rows[0].supplierCode+
				'&region='+encodeURIComponent(rows[0].region)+'&intentionAndSupplierCodes=${intentionAndSupplierCodes}';
		} else {
			rows = $('#fastAdjustMerGrid').datagrid("getSelections");

			if (rows.length != 1) {
				window.parent.$.messager.alert('提示', '请只选择一条商品记录');
				return ;
			}
			title = '新增物料信息';
			href = 'wlInfoFastAdjust_showInsertWlInfoForm_1.html?applicationCode=${applicationCode}&intentionAndSupplierCodes=${intentionAndSupplierCodes}';
		}
		
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 300,
			height : 150,
			buttons : [ {
				text : '保存',
				handler : function() {
					fastAdjustWlInfoFn.submitForm(dlg, isEdit);
				},
				iconCls : 'save'
			}, {
				text : '关闭',
				handler : function() {
					dlg.dialog('close');
				},
				iconCls : 'close'
			} ], onLoad : function() {
				$('#fastAdjustWlInfo_form').form('validate');
				//将申请单号及意向品、供应商等消息传递过去
				$('#faAppli').val('${applicationCode}');
				$('#faIntSup').val('${intentionAndSupplierCodes}');
				$('#faIntCode').val(rows[0].intentionCode);
				$('#faSupp').val(rows[0].supplierCode);
			}
		});
		$(".window-shadow").remove();
	},
	
	// 添加物料信息
	showInsert : function() {
		fastAdjustWlInfoFn.showForm(false);
	},
	
	// 修改物料信息
	showEdit : function() {
		fastAdjustWlInfoFn.showForm(true);
	},
	
	//提交dlg
	submitForm : function(dlg, isEdit) {
		if (!$('#fastAdjustWlInfo_form').form('validate')) return;//验证
		var href = "wlInfoFastAdjust_insertWlInfoFastAdjust_2.html";
		
		if (isEdit) {//编辑
			href = "wlInfoFastAdjust_updateWlInfo_2.html";
		}
		/*if ($("#faWlInfoRegion").combobox("getValues").length ){
			
		}*/
		utils.form("fastAdjustWlInfo_form").submit(href,null,function(){
			dlg.dialog('close');
			fastAdjustWlInfoFn.refreshData();
       },function(){
    	   $(".window-shadow").remove();
       });
	},
	
	//删除价格地区信息
	remove : function() {
		var rows = $('#fastAdjustWlInfoGrid').datagrid("getSelections");

		if (rows.length < 1) {
			window.parent.$.messager.alert('提示', '请勾选至少一条记录');
			return ;
		}
		window.parent.utils.confirm("操作确认", "确认删除选择的物料信息记录?", function(){
			$.post("wlInfoFastAdjust_deleteWlInfo_2.html",
					{
					rows : JSON.stringify(rows),
					applicationCode:'${applicationCode}',
					intentionAndSupplierCodes : '${intentionAndSupplierCodes}'
					}, function(data) {
						if (data.success) {
							$.messager.show({
								title:'提示',
								msg:'删除成功',
								timeout:4000,
								showType:'slide'
							});
							fastAdjustWlInfoFn.refreshData();
						} else {
							var msg = '删除失败';
							if (data.msg) msg = data.msg; 
							window.parent.$.messager.alert('提示', msg);
						}
			},"json");
		});
	},
	
	//刷新datagrid
	refreshData : function() {
		$('#fastAdjustMerGrid').datagrid('reload');
		$('#fastAdjustWlInfoGrid').datagrid('reload');
	}

};