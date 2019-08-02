var accessoryFineTypeGridUtil=null;
$(document).ready(function(){
	accessoryFineTypeGridUtil=utils.grid($('#accessoryFineType_grid'));
	accessoryFineTypeGridUtil.initFilters({
		noParamCanSort:true,
		onClickRow : accessoryFineTypeFn.setButtonState, // 设置按钮状态
		onCheck : accessoryFineTypeFn.setButtonState, // 设置按钮状态
		onUncheck : accessoryFineTypeFn.setButtonState, // 设置按钮状态
		onSelectAll : accessoryFineTypeFn.setButtonState, // 设置按钮状态
		onUnselectAll : accessoryFineTypeFn.setButtonState, // 设置按钮状态
		onLoadSuccess:function(){
			$('#accessoryFineType_grid').datagrid("clearSelections");
			accessoryFineTypeFn.setButtonState();
		}
	});
});
var accessoryFineTypeFn = {
	//显示添加或修改窗体
	showForm:function(isEdit){
		var title='添加辅料细分类';
		var href='accessoryFineType_showInsertAccessoryFineTypeForm_1.html';
		var dlg=utils.showDlg({
			title:title,href:href,width:320,height:120,
			buttons:[{text:'保存',handler:function(){accessoryFineTypeFn.submitForm(dlg);},iconCls:'save'},
			        {text:'取消',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
		$(".window-shadow").remove();
	},
	//添加辅料细分类维护
	showInsert:function() {
		accessoryFineTypeFn.showForm(false);
	},
	//提交新增或修改表单
	submitForm:function(dlg){
		var fineTypeCode = $("input#fineTypeCode").val();
		var url = 'accessoryFineType_insertAccessoryFineType_2.html';
		var fineTypeName=$.trim($("input[name='fineTypeName']").val());
		if (fineTypeName != "") {
			utils.form("accessoryFineType_form").submit(url, null, function() {
				dlg.dialog('close');
				accessoryFineTypeGridUtil.refresh();
			});
		}else{
			$.messager.alert('操作失败', "必填项不能为空");
		}
	},
	//删除辅料细分类维护
	remove:function(){
		var record=accessoryFineTypeGridUtil.getSelectedIdArr('fineTypeCode');
		if(record==null)return;
		utils.confirm("操作确认","确认删除辅料细分类维护?",function(){
			utils.post("accessoryFineType_deleteAccessoryFineType_2.html?fineTypeCode="+record,null,function(){
				accessoryFineTypeGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	//刷新
	refresh:function(){
    	$('#accessoryFineType_grid').treegrid('reload');
    },
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#accessoryFineType_grid').datagrid("getSelections");
		if(rows.length>=1){
			$("a[id='remove']").linkbutton("enable");
		}else{
			$("a[id='remove']").linkbutton("disable");
		}	
	}
};