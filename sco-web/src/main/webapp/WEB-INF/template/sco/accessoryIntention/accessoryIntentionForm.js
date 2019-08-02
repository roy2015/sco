var centreTypeFalg = undefined;
var smallTypeFalg = undefined;
var detailTypeFalg = undefined;
$(document).ready(function(){
	$('#centreTypeCode').combobox({
		url:'masterDataType_listCentreType_5.html',
		onChange : function() {
			merchandiseTypeFn.reloadData("centreTypeCode", "smallTypeCode", "SmallType");
		},
		onLoadSuccess:function(){
			if('<#if accessoryIntention.centreTypeCode??>1<#else></#if>' == 1 && centreTypeFalg == undefined){
				$('#centreTypeCode').combobox('setValue','${accessoryIntention.centreTypeCode}');
				centreTypeFalg = 'yes';
			}
			
		}
	});
	$('#smallTypeCode').combobox({
		onChange : function() {
			merchandiseTypeFn.reloadData("smallTypeCode", "detailTypeCode", "DetailType");
		},
		onLoadSuccess:function(){
			if('<#if accessoryIntention.smallTypeCode??>1<#else></#if>' == 1 && smallTypeFalg == undefined){
				$('#smallTypeCode').combobox('setValue','${accessoryIntention.smallTypeCode}');
				smallTypeFalg = 'yes';
			}
		}
	});
	$('#detailTypeCode').combobox({
		onLoadSuccess:function(){
			if('<#if accessoryIntention.detailTypeCode??>1<#else></#if>' == 1 && detailTypeFalg == undefined){
				$('#detailTypeCode').combobox('setValue','${accessoryIntention.detailTypeCode}');
				detailTypeFalg = 'yes';
			}
		}
	});
});

var accessoryIntentionFormFn = {
	// 保存添加或修改
	save:function(){
		var url = 'accessoryIntention_insertAccessoryIntention_2.html';
		
		utils.form("accessoryIntention_form").submit(url,null,function(){
			
		});
	},
	 // 关闭
    closeTab : function() {
		parent.pubTab.closeTab('辅料意向品详情');
	},
	 //初始化项目grid
	initSupplierGrid:function(){
		var recordGridUtil=utils.grid($('#supplier_grid'));
		recordGridUtil.initFilters({
			onSelect:function(idx, data){
				$('#supplier').combo('setValue', data.id).combo('setText', data.text).combo('hidePanel');
			}
		});
	},
	//初始化项目下拉框
	initSupplierCombox:function(){
		$('#supplier').combo({
			editable:true,
			panelWidth:360,
			panelHeight:350
		});
		$('#sp').appendTo($('#supplier').combo('panel'));

		$('#supplier').combo('setValue', $("#supplier").combobox('getValue')).combo('setText',  $("#supplier").combobox('getText'));
	},
	
	relateSupplier:function(){
		// 意向品编号
		var intentionCode=$('#intentionCode').val();
		// 待关联的供应商编号
		var supplierCode=$('#supplier').combobox('getValue');
		if(supplierCode==null || supplierCode==""){
			$.messager.alert('提示', '<center>请选择一个供应商！</center>');
			return 
		}
	// window.location='accessoryIntention_insertAccessoryIntentionSupplier_2.html?intentionCode='+intentionCode+'&supplierCode='+supplierCode;
		utils.post("accessoryIntention_insertAccessoryIntentionSupplier_2.html?intentionCode="+intentionCode+'&supplierCode='+supplierCode, "", function(rsp) {
			 $('#accessoryIntentionSupplier_grid').datagrid('reload'); 
		 });
    },
    
    createSupplier:function(intentionCode){
		var title='新增意向供应商';
		var href='accessoryIntention_showInsertSupplierForm_1.html?intentionCode='+intentionCode;
		
		var dlg=utils.showDlg({
			title:title,href:href,width:300,height:160,
			buttons:[{text:'添加',handler:function(){accessoryIntentionFormFn.submitForm(dlg);},iconCls:'save'},
			        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	
	// 添加供应商
	submitForm:function(dlg){
		var supplierName=$('#supplierName').val();
		if(supplierName==null || supplierName==""){
			$.messager.alert('提示', '<center>供应商名称不能为空！</center>');
			return 
		}
		var url = 'accessoryIntention_insertSupplierAndRelateSupplier_2.html';
		
		utils.form("createSupplier_form").submit(url,null,function(){
			dlg.dialog('close');
			$('#supplier').combobox('reload');
			 $('#accessoryIntentionSupplier_grid').datagrid('reload'); 
		});
	},
	
	cancelSupplier:function(intentionCode){
		var intentionSupplierCode=[];
		accessoryIntentionSupplierGridUtil=utils.grid($('#accessoryIntentionSupplier_grid'));
		var record = $('#accessoryIntentionSupplier_grid').datagrid('getChecked');
		if(record.length == 0){
			$.messager.alert('提示','<center>请至少选择一条记录！</center>'); 
		}else{
			utils.confirm("操作确认","确认取消关联供应商吗?",function(){
				$.each(record, function(index, item) {
					intentionSupplierCode.push(item.intentionSupplierCode);
				});
			// console.info(intentionSupplierCode);
			utils.post("accessoryIntention_cancelSupplier_2.html?intentionSupplierCode=" + intentionSupplierCode+"&intentionCode="+intentionCode, null, function() {
				$('#accessoryIntentionSupplier_grid').datagrid('reload');
			});
		
			});
		}
		$(".window-shadow").remove();
	}
};

var merchandiseTypeFn = {
		// 重新加载
		reloadData : function(thisId, targetId, method) {
			merchandiseTypeFn.clearSelectedData(targetId);
			// 当前框的值
			var value = $("#" + thisId).combobox("getValue");
			if (!value) {
				value = 'null';
			}
			$("#" + targetId).combobox('reload', "masterDataType_list" + method + "_5.html?" + thisId + "=" + value);
		},

		// 清空下一个连动框已选择的值
		clearSelectedData : function(targetId) {
			$("#" + targetId).combobox("setValue", "");
			$("#" + targetId).combobox("clear");
		}
	};