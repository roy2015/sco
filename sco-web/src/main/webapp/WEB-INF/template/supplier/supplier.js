var supplierGridUtil=null;
$(document).ready(function(){
	//roleFn.initDataPowerMenu();//初始化数据权限菜单
	supplierGridUtil=utils.grid($('#supplier_grid'));
	supplierGridUtil.initFilters({
		onDblClickRow:supplierFn.showEdit,//双击修改
		onClickRow:supplierFn.setButtonState,//设置按钮状态
		onLoadSuccess:function(){
			$('#supplier_grid').datagrid("clearSelections");
			supplierFn.setButtonState();
		}
	});
});

var supplierFn = {
	//显示添加或修改窗体
	showForm:function(isEdit){
		var title='${action.getText("common.title.insert",action.getText("security.supplier.insert"))}';
		var href='supplier_showInsertSupplierForm_1.html';
		if(isEdit){//显示修改窗体
			var record=supplierGridUtil.getSelectedRecord();
			if(record==null)return;
			title='${action.getText("common.title.edit",action.getText("security.supplier.update"))}';
			href='supplier_showUpdateSupplierForm_1.html?supplId='+record.supplId;
		}
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:350,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){supplierFn.submitForm(dlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	//添加角色基础信息
	showInsert:function() {
		supplierFn.showForm(false);
	},
	//修改角色基础信息
	showEdit:function(){
		supplierFn.showForm(true);
	},
	submitForm:function(dlg){
		var supplId = $("input#supplId").val();
		var url = 'supplier_insertSupplier_2.html';
		if(supplId){
			url = 'supplier_updateSupplier_2.html';
		}
		utils.form("supplier_form").submit(url,null,function(){
			dlg.dialog('close');
			supplierGridUtil.refresh();
	       });
	},
	//删除供应商信息
	remove:function(){
		var supplier = utils.grid($("#supplier_grid")).getSelectedRecord();
		if(supplier == null){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("security.supplier.msg.confirmSelectDeleteSupplier")}',"question");
			return;
		}
		utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.supplier.msg.confirmDeleteSupplier")}',function(){
			utils.post("supplier_deleteSupplier_2.html",{supplId:supplier.supplId},function(){
				supplierGridUtil.refresh();
			});
		});
	},
	//设置组织
	setOrg:function(row){
		//userFn.setOrg(row.supplNo, row.supplName);
	},
	//清除查询
	clearFilter:function(){
		supplierGridUtil.clearFilter();
	},
	
	refresh:function(){
    	$('#supplier_grid').treegrid('reload');
    },
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#supplier_grid').datagrid("getSelections");
		if(rows.length==1){
			$("a[id='showEdit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
		}else{
			$("a[id='showEdit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
		}	
	}
};