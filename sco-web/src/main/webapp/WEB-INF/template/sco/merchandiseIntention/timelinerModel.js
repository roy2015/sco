var quotedCompareUtil=null;
var quotedIntentionUtil=null;
var firstLoad=true;
var isSearch=false;
var loadCount=0;
var fromOtherNodeFlag=false;
$(document).ready(function(){

	$('#example1').timeliner();
	try {
		quotedCompareUtil=utils.grid($('#panel5QuotedGrid'));
		quotedCompareUtil.initFilters({
			noParamCanSort:true,
			onLoadSuccess:function(){
				$('#panel5QuotedGrid').datagrid("clearSelections");
			}
		});
		quotedIntentionUtil=utils.grid($('#panel5IntentionGrid'));
		quotedIntentionUtil.registerExtFilters("compareIntentionSupplierCode","compareIntentionSupplierName",
				"compareIntentionCode","compareIntentionName","compareCreateDateStart",
				"compareCreateDateEnd","compareCentreTypeCode","compareSmallTypeCode",
				"compareDetailTypeCode","compareFineTypeCode","comparePurchaseDepartments",
				"compareRationed","comparePurchaseType","compareSaleType");
		quotedIntentionUtil.initFilters({
			noParamCanSort:true,
			onBeforeLoad:function(obj) {
				if(isSearch){
					   //点击查询方法时
					    isSearch=true;
						var length = $.param(obj).split("&").length;
						var selectedRadio=$("input[name='somePart']:checked").val();
						//判断参数个数
						if (length < 3) {
							if (selectedRadio == null || selectedRadio == "") {
								$.messager.alert("提示", "请输入至少一项查询条件!");
								$(".window-shadow").remove();
								return false;
							}
						}else{
							if (selectedRadio == null || selectedRadio == "") {
								$.messager.alert("提示", "只搜索已引进商品、只搜索未引进商品和同时搜索已引进和未引进商品必须选中其中一个!");
								$(".window-shadow").remove();
								return false;
							}
						}
						
						if(length == 4 && $.param(obj).indexOf("order") > -1) {
							var param = quotedIntentionUtil.getFilterValue();
							if(selectedRadio != null && selectedRadio != ""){
								return true;
							}else{
								if($.param(param).length > 0){
									$.messager.alert("提示", "只搜索已引进商品、只搜索未引进商品和同时搜索已引进和未引进商品必须选中其中一个!");
									$(".window-shadow").remove();
								}else{
									$.messager.alert("提示", "请输入至少一项查询条件!");
									$(".window-shadow").remove();
								}
								return false;
							}
						}
				}else{
					if(!fromOtherNodeFlag){
						//console.dir(fromOtherNodeFlag);
						return false;//从主界面点击进入时也只需要渲染
					}else{
						//从其他节点点击该节点时，并没有进行查询，只需要把grid渲染
						$('#panel5IntentionGrid_search').form('reset');
						$('#panel5IntentionGrid').datagrid('clearSelections');
						$('#panel5IntentionGrid').datagrid('loadData',{total:0,rows:[]});
						return false;
					}
				}
			},
			onLoadSuccess:function(){
				//$('#panel5IntentionGrid').datagrid("clearSelections");
			}
		});
		//如果点击意向品编号或者新增时，显示锚点1的页面。如果选择意向品，点击报价比价，就进入锚点5的页面
		var panelNodeId=$("#panelNodeId").val();
		if (panelNodeId != null && panelNodeId != "") {
			$('.node5').trigger('click');
		} else {
			$('.node1').trigger('click');
		}
	} catch (e){}
});
var timelinerFn = {
	click1:function(){
		 $('#intention-1').show();
		 $('#intention-2').hide();
		 $('#intention-3').hide();
		 $('#intention-4').hide();
		 $('#intention-5').hide();
		 $('#intention-6').hide();
		 $('#intentionSupplierGrid').datagrid('reload');

	},
	click2:function(){
		 $('#intention-1').hide();
		 $('#intention-3').hide();
		 $('#intention-4').hide();
		 $('#intention-5').hide();
		 $('#intention-6').hide();
		 $.post('merchandiseIntention_isSaveMerchandiseIntention_2.html', {
			 intentionCode : '${merchandiseIntention.intentionCode}'
		 }, function(data){ 
			if (data.success) {
				 $('#intention-2').show();
				 fromOtherNodeFlag=true;
				 $('#panel2SupplierGrid').datagrid('reload');
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
		 
	},
	click3:function(){
		 $('#intention-1').hide();
		 $('#intention-2').hide();
		 $('#intention-4').hide();
		 $('#intention-5').hide();
		 $('#intention-6').hide();
		 
		 $.post('merchandiseIntention_isSaveMerchandiseIntention_2.html', {
			 intentionCode : '${merchandiseIntention.intentionCode}'
		 }, function(data){ 
			if (data.success) {
				 $('#intention-3').show();
				 fromOtherNodeFlag=true;
				 $('#panel3SupplierGrid').datagrid('reload');
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
		
	},
	click4:function(){
		 $('#intention-1').hide();
		 $('#intention-2').hide();
		 $('#intention-3').hide();
		 $('#intention-5').hide();
		 $('#intention-6').hide();
		 
		 $.post('merchandiseIntention_isSaveMerchandiseIntention_2.html', {
			 intentionCode : '${merchandiseIntention.intentionCode}'
		 }, function(data){ 
			if (data.success) {
				 $('#intention-4').show();
				 fromOtherNodeFlag=true;
				 $('#panel4SupplierGrid').datagrid('reload');
				 $('#panel4QuotedGrid').datagrid('reload');
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
	},
	click5:function(){
		 $('#intention-1').hide();
		 $('#intention-2').hide();
		 $('#intention-3').hide();
		 $('#intention-4').hide();
		 $('#intention-6').hide();
		
		 $.post('merchandiseIntention_isSaveMerchandiseIntention_2.html', {
			 intentionCode : '${merchandiseIntention.intentionCode}'
		 }, function(data){ 
			if (data.success) {
				 $('#intention-5').show();
				 fromOtherNodeFlag=true;
				 $('#panel5QuotedGrid').datagrid('reload');
				 $('#panel5IntentionGrid').datagrid('reload');
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
	},
	click6:function(){
		 $('#intention-1').hide();
		 $('#intention-2').hide();
		 $('#intention-3').hide();
		 $('#intention-4').hide();
		 $('#intention-5').hide();
		
		 $.post('merchandiseIntention_isSaveMerchandiseIntention_2.html', {
			 intentionCode : '${merchandiseIntention.intentionCode}'
		 }, function(data){ 
			if (data.success) {
				 $('#intention-6').show();
				 fromOtherNodeFlag=true;
				 $('#panel6SupplierGrid').datagrid('reload');
				 $('#panel6ForetasteGrid').datagrid('reload');
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
	},
	searchCompare : function(){
		isSearch=true;
		var param = quotedIntentionUtil.getFilterValue();
		//获取输入框的查询调价
		param.intentionSupplierCode=param.compareIntentionSupplierCode ;
		param.intentionSupplierName =param.compareIntentionSupplierName;
		param.intentionCode = param.compareIntentionCode;
		param.intentionName = param.compareIntentionName;
		
		param.centreTypeCode =param.compareCentreTypeCode;
		param.smallTypeCode =param.compareSmallTypeCode;
		param.detailTypeCode =param.compareDetailTypeCode;
		param.fineTypeCode =param.compareFineTypeCode;
		param.purchaseDepartments =param.comparePurchaseDepartments;
		param.rationed = param.compareRationed;
		param.purchaseType =param.comparePurchaseType;
		param.saleType = param.compareSaleType;
		
		var createDateStart = $("#compareCreateDateStart").datebox("getValue");
		var createDateEnd = $("#compareCreateDateEnd").datebox("getValue");
		//创建时间的合法性校验
		if (createDateStart == "" && createDateEnd != "") {
			$.messager.alert('提示', '<center>请输入意向品开始创建时间！</center>');
			$(".window-shadow").remove();
			   return;
		}
		if (createDateEnd == "" && createDateStart != "") {
			$.messager.alert('提示', '<center>请输入意向品结束创建时间！</center>');
			$(".window-shadow").remove();
			   return;
		}
		if (createDateStart > createDateEnd ) {
			$.messager.alert('提示', '<center>结束创建时间不能早于开始创建时间！</center>');
			$(".window-shadow").remove();
			return;
		}
		
		var selectedRadio=$("input[name='somePart']:checked").val();
		//console.dir("selectedRadio "+selectedRadio);
		/*if (selectedRadio != null && selectedRadio != "") {
			console.dir("选中true：" + selectedRadio);
		} else {
			$.messager.alert("提示", "只搜索已引进商品、只搜索未引进商品和同时搜索已引进和未引进商品必须选中其中一个!");
			return false;
		}*/
		param.somePart=selectedRadio;
		
		param.createDateStart=createDateStart;
		param.createDateEnd=createDateEnd;
		
		$('#panel5IntentionGrid').datagrid('load', param);
	},
	clearCompare:function(){
		isSearch=false;
		$('#panel5IntentionGrid_search').form('reset');
		clearComboboxOptions('compareCentreTypeCode','compareSmallTypeCode','compareDetailTypeCode','compareFineTypeCode');
		$('#panel5IntentionGrid').datagrid('clearSelections');
		$('#panel5IntentionGrid').datagrid('loadData',{total:0,rows:[]});
	}
};