var intentionUtil=null;
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
$(document).ready(function(){
	intentionUtil=utils.grid($('#intentionGrid'));
	//注册查询框
	intentionUtil.registerExtFilters("intentionSupplierCode","intentionSupplierName","intentionCode","intentionName"
			,"createDateStart","createDateEnd","eatDateStart","eatDateEnd","centreTypeCodeElse","smallTypeCode"
			,"detailTypeCode","fineTypeCode","purchaseDepartments","rationed","purchaseType","saleType","isForetastePass");
	
	intentionUtil.initFilters({
		onBeforeLoad:function(obj) {
			//清空查询
			if(clearSearch) {
				return true;
			}
			var length = $.param(obj).split("&").length;
			//判断参数个数
			if (length < 3) {
				if(!firstIn) $.messager.alert("提示", "请输入至少一项查询条件");
				firstIn = false;
				return false;
			}
			if(length == 4 && $.param(obj).indexOf("order") > -1) {
				var param = intentionUtil.getFilterValue();
				if($.param(param).length > 0){
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
		},
		//onClickRow:intentionFn.setButtonState, //设置按钮状态
		onLoadSuccess:function(){
			firstIn = false;
			//$('#intentionGrid').datagrid("clearSelections");
			//intentionFn.setButtonState();
		},
	});
});
var intentionFn = {
	//添加商品意向品
	showInsert:function() {
		var url='merchandiseIntention_showInsertMerchandiseIntentionForm_1.html';	
		parent.addTabByUrl('意向品详情','agent',url);
	},
	//点击意向品编号超链接，进入详情页面
	showIntentionDtl : function(intentionCode,rowIndex) {
		var url='merchandiseIntention_showUpdateMerchandiseIntentionForm_1.html?intentionCode='+intentionCode;		
		parent.addTabByUrl('意向品详情','agent',url);
	},
	//查询方法
	search : function(){
		var param = intentionUtil.getFilterValue();
		
		var createDateStart=$("#createDateStart").datebox("getValue");
		var createDateEnd=$("#createDateEnd").datebox("getValue");
		//创建时间的合法性校验
		if (createDateStart == "" && createDateEnd != "") {
			$.messager.alert('提示', '<center>请输入意向品开始创建时间！</center>');	
			   return;
		}
		if (createDateEnd == "" && createDateStart != "") {
			$.messager.alert('提示', '<center>请输入意向品结束创建时间！</center>');	
			   return;
		}
		if (createDateStart > createDateEnd ) {
			$.messager.alert('提示', '<center>结束创建时间不能早于开始创建时间！</center>');
			return;
		}
		
		var eatDateStart=$("#eatDateStart").datebox("getValue");
		var eatDateEnd=$("#eatDateEnd").datebox("getValue");
		// 试吃时间的合法性校验
		if (eatDateStart == "" && eatDateEnd != "") {
			$.messager.alert('提示', '<center>请输入试吃开始时间！</center>');	
			   return;
		}
		if (eatDateEnd == "" && eatDateStart != "") {
			$.messager.alert('提示', '<center>请输入试吃结束时间！</center>');	
			   return;
		}
		if (eatDateStart > eatDateEnd ) {
			$.messager.alert('提示', '<center>试吃结束时间不能早于试吃开始时间！</center>');
			return;
		}
		/*param.createDateStart=createDateStart;
		param.createDateEnd=createDateEnd;
		param.eatDateStart=eatDateStart;
		param.eatDateEnd=eatDateEnd;*/
		
		$('#intentionGrid').datagrid('load', param);
	},
	//删除商品意向品
	remove:function(){							   
		 var intentionGrid = $('#intentionGrid').datagrid('getChecked');
			if(intentionGrid.length==0){
				$.messager.alert('提示','<center>请至少选择一条记录！</center>'); 
			}else{
				var  intentionCode= [];
				var  applicationCodes=[];
				var deleteFlag=true;
				$.each(intentionGrid, function(index, item){
					if (item.merchandiseCode != null && item.merchandiseCode != "") {
						// 判断是否勾选了有SAP物料号的意向品
						deleteFlag = false;
					}
					intentionCode.push(item.intentionCode);
					applicationCodes.push("'"+item.intentionCode+"'");
				});
				if (!deleteFlag) {
					$.messager.alert('提示', '<center>所选记录中有SAP物料号,不能删除!</center>');
					return;
				}
				utils.confirm("操作确认","确认删除商品意向品?",function(){
					utils.post("merchandiseIntention_deleteMerchandiseIntention_2.html?intentionCode="+intentionCode+"&applicationCodes="+applicationCodes,null,function(){
						//intentionUtil.refresh();
						$('#intentionGrid').datagrid('reload');
					});
				});
			}
		
	},
	//清除查询
	clear:function(){
		clearSearch = true;
		$('#merchandiseIntention_search').form('reset');
		clearComboboxOptions();//在IE8下，选中中分类，点击查询。清空查询条件时，小分类下拉框有数据
		$('#intentionGrid').datagrid('clearSelections');
		$('#intentionGrid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
	},
	refresh:function(){
		clearSearch = true;
    	$('#intentionGrid').datagrid('reload');
    	clearSearch = false;
    },
	//选择一条记录，点击报价比价，进入报价比价页面
	showCompareQuotedDtl : function() {
		var intentionGrid=$('#intentionGrid').datagrid('getChecked');
		if(intentionGrid.length==0||intentionGrid.length>1){
			$.messager.alert('提示','请选择一条记录!');
			return;
		}else{
			var intentionCode=[];
			$.each(intentionGrid, function(index, item){
				intentionCode.push(item.intentionCode);
			});
			var url='merchandiseIntention_showUpdateMerchandiseIntentionForm_1.html?intentionCode='+intentionCode+"&panelNodeId=5";		
			parent.addTabByUrl('意向品详情','agent',url);
		}
	},
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#intentionGrid').datagrid("getSelections");
		if(rows.length===1){
			$("a[id='showEdit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
		}else{
			$("a[id='showEdit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
		}	
	},
	
	//导出到Excel
	export2Excel:function(){
		var param=$.param(intentionUtil.getFilterValue());
		if (param.length < 1) {
			$.messager.alert('提示', '请输入至少一项查询条件!');
			return;
		}
		
		window.location="merchandiseIntention_exportIntentionToExcel_3.html?"+$.param(intentionUtil.getFilterValue());
		$.messager.show({
			title:'文件导出提示',
			msg:'文件正在导出中，请稍后！',
			timeout:4000,
			showType:'slide'
		});
	}
};
