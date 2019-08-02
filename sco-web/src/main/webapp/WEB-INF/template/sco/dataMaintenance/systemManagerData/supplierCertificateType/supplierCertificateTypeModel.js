var supplierCertificateTypeGridUtil=null;
$(document).ready(function(){
	supplierCertificateTypeGridUtil=utils.grid($('#supplierCertificateType_grid'));
	supplierCertificateTypeGridUtil.initFilters({
		onClickRow:supplierCertificateTypeFn.setButtonState, //设置按钮状态
		onCheck : supplierCertificateTypeFn.setButtonState, // 设置按钮状态
		onUncheck : supplierCertificateTypeFn.setButtonState, // 设置按钮状态
		onSelectAll : supplierCertificateTypeFn.setButtonState, // 设置按钮状态
		onUnselectAll : supplierCertificateTypeFn.setButtonState, // 设置按钮状态
		onLoadSuccess:function(){
			$('#supplierCertificateType_grid').datagrid("clearSelections");
			supplierCertificateTypeFn.setButtonState();
		}
	});
});
var supplierCertificateTypeFn = {
	//显示添加窗体
	showForm:function(isEdit){
		var title='添加证件名称';
		var href='supplierCertificateType_showInsertSupplierCertificateTypeForm_1.html';
		var dlg=utils.showDlg({
			title:title,href:href,width:300,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){supplierCertificateTypeFn.submitForm(dlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			],onLoad:function(){
				$('#supplierCertificateType_form').form('validate');
			}
		});
		$(".window-shadow").remove();
	},
	//添加证件名称
	showInsert:function() {
		supplierCertificateTypeFn.showForm(false);
	},
	//提交新增或修改表单
	submitForm:function(dlg){
		//验证表格
		if(!$('#supplierCertificateType_form').form('validate')) {
			return;
		}
		/*var certificateTypeCode = $("input#certificateTypeCode").val();
		var certificateTypeName=$("input[name='certificateTypeName']").val().replace(/\s+/g,"");
		if(certificateTypeName==""||certificateTypeName==null){
			$.messager.alert('操作失败', "证件名称不能为空");
			return;
		}*/
		var url = 'supplierCertificateType_insertSupplierCertificateType_2.html';
		utils.form("supplierCertificateType_form").submit(url,null,function(){
			dlg.dialog('close');
			supplierCertificateTypeGridUtil.refresh();
		});
	},
	//删除证件名称
	remove:function(){
		var record=supplierCertificateTypeGridUtil.getSelectedIdArr('certificateTypeCode');
		if(record==null)return;
		utils.confirm("操作确认","确认删除证件名称?",function(){
			utils.post("supplierCertificateType_deleteSupplierCertificateType_2.html?certificateTypeCode="+record,null,function(){
				supplierCertificateTypeGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	//清除查询
	clearFilter:function(){
		supplierCertificateTypeGridUtil.clearFilter();
	},
	//查询
	refresh:function(){
    	$('#supplierCertificateType_grid').treegrid('reload');
    },
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#supplierCertificateType_grid').datagrid("getSelections");
		if(rows.length>=1){
			$("a[id='remove']").linkbutton("enable");
		}else{
			$("a[id='remove']").linkbutton("disable");
		}	
	}
};