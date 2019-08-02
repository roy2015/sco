var centreTypeFalg = undefined;
var smallTypeFalg = undefined;
var detailTypeFalg = undefined;
var fineTypeFalg = undefined;
$(document).ready(function(){
	$('#centreTypeCodeElse').combobox({
		url:'masterDataType_listCentreType_5.html',
		onChange : function() {
			merchandiseTypeFn.reloadData("centreTypeCodeElse", "smallTypeCode", "SmallTypeElse");
		},
		onLoadSuccess:function(){
			if('<#if merchandiseIntention.centreTypeCode??>1<#else></#if>' == 1 && centreTypeFalg == undefined){
				$('#centreTypeCodeElse').combobox('setValue','${merchandiseIntention.centreTypeCode}');
				centreTypeFalg = 'yes';
			}
			
		}
	});
	$('#smallTypeCode').combobox({
		onChange : function() {
			merchandiseTypeFn.reloadData("smallTypeCode", "detailTypeCode", "DetailType");
		},
		onLoadSuccess:function(){
			if('<#if merchandiseIntention.smallTypeCode??>1<#else></#if>' == 1 && smallTypeFalg == undefined){
				$('#smallTypeCode').combobox('setValue','${merchandiseIntention.smallTypeCode}');
				smallTypeFalg = 'yes';
			}
			if('<#if merchandiseIntention.smallTypeCode??&& merchandiseIntention.smallTypeCode=="ELSE">ELSE<#else></#if>' == 'ELSE'){
				document.getElementById('elseTypeNameTd').style.display='block'; 
				document.getElementById('eleTd').style.display='block'; 
				$('#detailTypeCode').combobox({
   					required:false
   				});
   				$('#fineTypeCode').combobox({
   					required:false
   				});
   				$('#fineTypeCode').combobox('setValue', '');
   				$('#detailTypeCode').combobox('setValue', '');
   				$('#detailTypeCode').combobox('disable');
   				$('#fineTypeCode').combobox('disable');
			}else{
				//当页面加载成功时，取消必填校验
				$('#elseTypeName').validatebox({
   					required:false
   				});
			}
		}
	});
	$('#detailTypeCode').combobox({
		onChange : function() {
			merchandiseTypeFn.reloadData("detailTypeCode", "fineTypeCode", "FineType");
		},
		onLoadSuccess:function(){
			if('<#if merchandiseIntention.detailTypeCode??>1<#else></#if>' == 1 && detailTypeFalg == undefined){
				$('#detailTypeCode').combobox('setValue','${merchandiseIntention.detailTypeCode}');
				detailTypeFalg = 'yes';
			}
		}
	});
	$('#fineTypeCode').combobox({
		onLoadSuccess:function(){
			if('<#if merchandiseIntention.fineTypeCode??>1<#else></#if>' == 1 && fineTypeFalg == undefined){
				$('#fineTypeCode').combobox('setValue','${merchandiseIntention.fineTypeCode}');
				fineTypeFalg = 'yes';
			}
		}
	});
	
	intentionSupplierGridUtil=utils.grid($('#intentionSupplierGrid'));
	intentionSupplierGridUtil.initFilters({
		noParamCanSort:true
	});
});

var intentionFormFn = {
	//提交新增或修改表单
	submitForm:function(){
		var url = 'merchandiseIntention_insertMerchandiseIntention_2.html';
		
		utils.form("merchandiseIntention_form").submit(url,null,function(){
			//dlg.dialog('close');
			//intentionUtil.refresh();
		});
	},
    //关闭
    closeTab : function() {
		parent.pubTab.closeTab('意向品详情');
	},
	//关联供应商
	relateSupplier:function(){
		//意向品编号
		var intentionCode=$('#intentionCode').val();
		//待关联的供应商编号
		var supplierCode=$('#supplier').combobox('getValue');
		if(supplierCode==null || supplierCode==""){
			window.parent.$.messager.alert('提示', '<center>请选择一个供应商！</center>');
			return ;
		}
		var url = 'merchandiseIntention_insertIntentionSupplierMerchandise_2.html?intentionCode='+intentionCode+'&supplierCode='+supplierCode;
		$.ajax({
			url : url,
			type:'post',
			dataType:'json',
			success:function(json){
				var msg = json.msg;
				if (!json.success) {
					window.parent.$.messager.alert("添加供应商提示",msg,'info');
				}else{
					//dlg.dialog('close');
					/*$.messager.show({
						title:'还原主数据提示',
						msg:msg,
						timeout:2000,
						showType:'slide'
					});*/
					window.parent.$.messager.alert("添加供应商提示",msg,'info');
					 $('#intentionSupplierGrid').datagrid('reload'); 
				}
			}
		});
    },
    //显示新增意向供应商页面
    showCreateSupplierForm:function(){
    	var intentionCode=$('#intentionCode').val();
		var title='${action.getText("common.title.add","创建意向供应商")}';
		var href="merchandiseIntention_showCreateSupplierForm_1.html?intentionCode="+intentionCode;
		
		var dlg=utils.showDlg({
			title:title,href:href,width:300,height:160,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){intentionFormFn.submitCreateSupplierForm(dlg);},iconCls:'save'},
			         {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			],
			onLoad:function(){
			}
		});
		//消除阴影
		$(".window-shadow").remove();
    },  
    //新增意向供应商
    submitCreateSupplierForm:function(dlg){
    	var intentionCode=$('#intentionCode').val();
    	var intentionSupplierCode = $("input#intentionSupplierCode").val();
    	var intentionSupplierName = $("input#intentionSupplierName").val();
		
    	var url = 'merchandiseIntention_insertIntentionSupplier_2.html';
    	utils.form("intentionSupplier_form").submit(url,null,function(){
   		 	 dlg.dialog('close');
   		 	 //重新加载comboGrid的数据
   		 	 //$('#supplierCode').combogrid('grid').datagrid('load');
   		 	 //重新加载combobox的数据
   		 	 $('#supplier').combobox('reload');
    		 $('#intentionSupplierGrid').datagrid('reload');
		});
    },
    //取消关联供应商
	cancelSupplier:function(){
		 var intentionSupplierGrid = $('#intentionSupplierGrid').datagrid('getChecked');
		 var intentionCode=$('#intentionCode').val();
			var  intentionSupplierCodes= [];//用于取消关联供应商
			var supplierCodes=[];//用于判断意向品关联的供应商是否OA申请
			$.each(intentionSupplierGrid, function(index, item){
				intentionSupplierCodes.push(item.intentionSupplierCode);
				supplierCodes.push("'"+item.intentionSupplierCode+"'");
			});
			if(intentionSupplierGrid.length==0){
				window.parent.$.messager.alert('提示','<center>请至少选择一条供应商记录！</center>'); 
			}else{
				$.messager.confirm('提示', '确定取消关联供应商?', function(r){
					if(r){
						var url="merchandiseIntention_deleteIntentionSupplierMerchandise_2.html?intentionCode="+intentionCode
							+"&intentionSupplierCodes="+intentionSupplierCodes+"&supplierCodes="+supplierCodes;
						$.ajax({
							url : url,
							type:'post',
							dataType:'json',
							success:function(json){
								var msg = json.msg;
								if (json.success) {
									window.parent.$.messager.alert("取消供应商提示",msg);
									$('#intentionSupplierGrid').datagrid('reload'); 
								}else{
									window.parent.$.messager.alert("取消供应商提示",msg);
								}
							}
						});
					}
				});
				$(".window-shadow").remove();
			}
    },
    //初始化项目grid
	initSupplierGrid:function(){
		var recordGridUtil=utils.grid($('#supplier_grid'));
		recordGridUtil.initFilters({
			noParamCanSort:true,
			onSelect:function(idx, data){
				$('#supplier').combo('setValue', data.id).combo('setText', data.text).combo('hidePanel');
			}
		});
	},
	//初始化项目下拉框
	initSupplierCombox:function(){
		$('#supplier').combo({
			editable:true,
			panelWidth:400,
			panelHeight:350
		});
		$('#sp').appendTo($('#supplier').combo('panel'));

		$('#supplier').combo('setValue', $("#supplier").combobox('getValue')).combo('setText',  $("#supplier").combobox('getText'));
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