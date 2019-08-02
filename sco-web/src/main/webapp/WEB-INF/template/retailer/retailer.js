var retailerGridUtil=null;
$(document).ready(function(){
	//roleFn.initDataPowerMenu();//初始化数据权限菜单
	retailerGridUtil=utils.grid($('#retailer_grid'));
	retailerGridUtil.initFilters({
		onDblClickRow:retailerFn.showEdit,//双击修改
		onClickRow:retailerFn.setButtonState,//设置按钮状态
		onLoadSuccess:function(){
			$('#retailer_grid').datagrid("clearSelections");
			retailerFn.setButtonState();
		}
	});
});



var retailerFn = {
	//显示添加或修改窗体
	showForm:function(isEdit){
		var title='${action.getText("common.title.insert",action.getText("security.retailer.insert"))}';
		var href='retailer_showInsertRetailerForm_1.html';
		if(isEdit){//显示修改窗体
			var record=retailerGridUtil.getSelectedRecord();
			if(record==null)return;
			title='${action.getText("common.title.edit",action.getText("security.retailer.update"))}';
			href='retailer_showUpdateRetailerForm_1.html?retailerId='+record.retailerId;
		}
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:350,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){retailerFn.submitForm(dlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	//添加角色基础信息
	showInsert:function() {
		retailerFn.showForm(false);
	},
	//修改角色基础信息
	showEdit:function(){
		retailerFn.showForm(true);
	},
	submitForm:function(dlg){
		var retailerId = $("input#retailerId").val();
		var url = 'retailer_insertRetailer_2.html';
		if(retailerId){
			url = 'retailer_updateRetailer_2.html';
		}
		utils.form("retailer_form").submit(url,null,function(){
			dlg.dialog('close');
			retailerGridUtil.refresh();
	       });
	},
	//删除供应商信息
	remove:function(){
		var retailer = utils.grid($("#retailer_grid")).getSelectedRecord();
		if(retailer == null){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("security.retailer.msg.confirmSelectDeleteRetailer")}',"question");
			return;
		}
		utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.retailer.msg.confirmDeleteRetailer")}',function(){
			utils.post("retailer_deleteRetailer_2.html",{retailerId:retailer.retailerId},function(){
				retailerGridUtil.refresh();
			});
		});
	},
	
	
	//清除查询
	clearFilter:function(){
		retailerGridUtil.clearFilter();
	},
	
	refresh:function(){
    	$('#retailer_grid').treegrid('reload');
    },
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#retailer_grid').datagrid("getSelections");
		if(rows.length==1){
			$("a[id='showEdit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
		}else{
			$("a[id='showEdit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
		}	
	}
};