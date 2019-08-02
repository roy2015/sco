$(document).ready(function() {
	// 中分类
	$("#compareCentreTypeCode").combobox({
		onChange : function() {
			compareQuotedTypeFn.reloadDataSmall("compareCentreTypeCode", "compareSmallTypeCode", "SmallTypeElse");
		}
	});
	// 小分类
	$("#compareSmallTypeCode").combobox({
		onChange : function() {
			compareQuotedTypeFn.reloadDataDetail("compareSmallTypeCode", "compareDetailTypeCode", "DetailType");
		}
	});
	// 明细类
	$("#compareDetailTypeCode").combobox({
		onChange : function() {
			compareQuotedTypeFn.reloadDataFine("compareDetailTypeCode", "compareFineTypeCode", "FineType");
		}
	});
});

var quotedCompareFn = {
	/*------------------------------报价比价模块---------------------------------------*/
	//导出所有报价记录
	exportAllQuoted:function(){
			var intentionCode=$('#intentionCode').val();
			url="quotedCompare_exportAllQuoted_3.html?intentionCode="+intentionCode;
			window.location=url;
			
			$.messager.show({
				title:'文件导出提示',
				msg:'文件正在导出中，请稍后！',
				timeout:4000,
				showType:'slide'
			});
    },
    //显示统一对比单位
	showConvertUnitDialog:function(){
			var intentionCode=$('#intentionCode').val();
			var title='${action.getText("common.title.add","录入统一对比单位")}';
			var href="quotedCompare_showConvertUnitDialog_1.html?intentionCode="+intentionCode;
			
			var dlg=utils.showDlg({
				title:title,href:href,width:295,height:120,
				buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){quotedCompareFn.submitConvertUnitDialog(dlg);},iconCls:'save'},
				         {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				],
				onLoad:function(){
				}
			});
			$(".window-shadow").remove();
    },
    //提交统一对比单位
    submitConvertUnitDialog:function(dlg){
    	var intentionCode=$('#intentionCode').val();
    	var convertUnit=$('#convertUnit').val();
    	if(0.00==convertUnit||0==convertUnit){
    		$.messager.alert('提示','<center>统一对比单位不能为空或0!</center>'); 
			return;
    	}
		dlg.dialog('close');
		quotedCompareFn.analysisQuoted(intentionCode,convertUnit);
    },
    //分析已有报价
    analysisQuoted:function(intentionCode,convertUnit){
			var title='${action.getText("common.title.add","报价单分析结果")}';
			var href="quotedCompare_showAnalysisQuoted_1.html?intentionCode="+intentionCode+"&convertUnit="+convertUnit;
			
			var dlg=utils.showDlg({
				title:title,href:href,width:1100,height:400,
				buttons:[
				         {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'close'}
				],
				onLoad:function(){
					$.post('quotedCompare_getCheapQuoted_2.html', {intentionCode:intentionCode,convertUnit:convertUnit}, function(json){
							$('#supplierNumbers').html(json.rows.supplierNumbers);
							$('#cheapSupplierName').html(json.rows.intentionSupplierName);
					},"json");
				}
			});
			//消除阴影
			$(".window-shadow").remove();
    },
    //查看供应商历史报价
    showHistoryQuoted:function(supplierCode){
    	var intentionSupplierCode="";
    	var  intentionCode=$('#intentionCode').val();//获取当前的意向品编号
    		if(supplierCode!=null&&supplierCode!=""){
    			//点击列表页的供应商编号连接
    			intentionSupplierCode=supplierCode;
    		}else{
    			//选中供应商，点击查看供应商历史报价
    			var quotedRecord=$('#panel5QuotedGrid').datagrid('getSelected');
    			if(quotedRecord==null){
    				$.messager.alert('提示','<center>请选择一条记录！</center>'); 
    				return;
    			};
    			intentionSupplierCode=quotedRecord.intentionSupplierCode;//用户所选的供应商编号
    		}
			var title='${action.getText("common.title.add","供应商历史报价情况")}';
			var href="quotedCompare_showHistoryQuoted_1.html?intentionSupplierCode="+intentionSupplierCode+"&intentionCode="+intentionCode;
			
			var dlg=utils.showDlg({
				title:title,href:href,width:1100,height:470,
				buttons:[
				         {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'close'}
				],
				onLoad:function(){
				}
			});
			//消除阴影
			$(".window-shadow").remove();
    },
   //导出供应商历史报价
	exportHistoryQuoted:function(intentionSupplierCode,intentionSupplierName){
		window.location = "quotedCompare_exportHistoryQuoted_3.html?" 
				+ "intentionSupplierCode=" + intentionSupplierCode
				+ "&intentionSupplierName=" + intentionSupplierName
				+ "&intentionCode=" + $("#intentionCode").val(); 
		$.messager.show({
			title:'文件导出提示',
			msg:'文件正在导出中，请稍后！',
			timeout:4000,
			showType:'slide'
		});
    },
    //弹出查看对比结果的单位输入框
	showCompareResultDialog:function(){
		// 选中需要对比的参照品
		var compareRecord=$('#panel5IntentionGrid').datagrid('getChecked');
		var intentionCode=$('#intentionCode').val();
		var  compareIntentionCodes= [];
		
			$.each(compareRecord, function(index, item){
				compareIntentionCodes.push("'"+item.intentionCode+"'");
			});
			//console.dir("intentionCode"+intentionCode);
			//console.dir("compareIntentionCodes"+compareIntentionCodes);
			if(compareRecord.length==0){
				window.parent.$.messager.alert('提示','<center>请至少选择一条记录！</center>');
			}else{
				var merchandiseQuotedUnits = null;
				var merchandiseQuotedUnitsIsSame = true;
				var refMerchandiseQuotedUnits = null;
				var refMerchandiseQuotedUnitsIsSame = true;
				$.when(
						$.ajax({type : "POST",
							url : "merchandiseQuoted_listSupplierQuoted_5.html?intentionCode="+intentionCode,
							dataType : 'json',
							success : function(data) {
								if (data != null) {
									for (var i = 0; i < data.length; i++) {
										if (i == 0) {
											merchandiseQuotedUnits = data[i].quotationUnits;
										} else {
											if (merchandiseQuotedUnits == data[i].quotationUnits) {
												merchandiseQuotedUnitsIsSame = true;
											} else {
												merchandiseQuotedUnitsIsSame = false;
												break;
											}
										}
									}
								}
							}
						}),
						$.ajax({type : "POST",
							url : "merchandiseQuoted_listRefMerchandiseQuoted_5.html?compareIntentionCodes="+compareIntentionCodes,
							dataType : 'json',
							success : function(data) {
								if (data != null) {
									for (var j = 0; j < data.length; j++) {
										if (j == 0) {
											refMerchandiseQuotedUnits=data[j].quotationUnits;
										}else{
											if (refMerchandiseQuotedUnits == data[j].quotationUnits) {
												refMerchandiseQuotedUnitsIsSame = true;
											}else{
												refMerchandiseQuotedUnitsIsSame = false;
												break;
											}
										}
									}
								}
							}
						})	
				).done(function(){
					//如果商品和意向品他们各自的单位都是相同的则继续判断他们两个的单位是否一致,如果不一致,就需要 弹出设置对比单位的窗口
					if (merchandiseQuotedUnitsIsSame&&refMerchandiseQuotedUnitsIsSame) {
						//如果商品的计量单位和参照品的计量单位一致,则不需要设置对比计量单位,直接打开对比结果,否则 弹出设置对比单位的窗口
						if (merchandiseQuotedUnits == refMerchandiseQuotedUnits) {
							quotedCompareFn.showCompareResult(intentionCode,compareIntentionCodes,merchandiseQuotedUnits);
						} else {
							var title='${action.getText("common.title.add","录入统一对比单位")}';
							var href="quotedCompare_showCompareResultDialog_1.html?intentionCode="+intentionCode
									 +"&compareIntentionCodes="+compareIntentionCodes;
							var dlg=utils.showDlg({
								title:title,href:href,width:295,height:120,
								buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){quotedCompareFn.submitCompareResultDialog(dlg);},iconCls:'save'},
								         {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
								],
								onLoad:function(){
								}
							});
						}
					}else{
						var title='${action.getText("common.title.add","录入统一对比单位")}';
						var href="quotedCompare_showCompareResultDialog_1.html?intentionCode="+intentionCode
								 +"&compareIntentionCodes="+compareIntentionCodes;
						var dlg=utils.showDlg({
							title:title,href:href,width:295,height:120,
							buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){quotedCompareFn.submitCompareResultDialog(dlg);},iconCls:'save'},
							         {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
							],
							onLoad:function(){
							}
						});
					}
				});
			}
    $(".window-shadow").remove();
    },
    //提交对比结果的单位输入框
    submitCompareResultDialog:function(dlg){
		var intentionCode=$('#intentionCode').val();
		var compareIntentionCodes=$('#compareIntentionCodes').val();
		
		var compareUnit=$('#compareUnit').val();
		   if(0.00==compareUnit||0==compareUnit){
		    	$.messager.alert('提示','<center>统一对比单位不能为0!</center>'); 
				return;
		   }
		
		dlg.dialog('close');
		quotedCompareFn.showCompareResult(intentionCode,compareIntentionCodes,compareUnit);
    },
  //查看对比结果
    showCompareResult:function(intentionCode,compareIntentionCodes,compareUnit){
    	// 选中需要对比的参照品
		/*var compareRecord=$('#panel5IntentionGrid').datagrid('getChecked');
		 var intentionCode=$('#intentionCode').val();
			var  compareIntentionCodes= [];
			$.each(compareRecord, function(index, item){
				compareIntentionCodes.push("'"+item.intentionCode+"'");
			});
			if(compareRecord.length==0){
				$.messager.alert('提示','<center>请至少选择一条记录！</center>'); 
			}else{*/
				var title='${action.getText("common.title.add","对比结果")}';
				var href="quotedCompare_showCompareResult_1.html?compareIntentionCodes="+compareIntentionCodes
						+"&intentionCode="+intentionCode+"&compareUnit="+compareUnit;
				
				var dlg=utils.showDlg({
					title:title,href:href,width:1100,height:340,
					buttons:[
					         {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'close'}
					],
					onLoad:function(){
					}
				});
				//消除阴影
				$(".window-shadow").remove();
			//}
    }
};

var compareQuotedTypeFn = {
		// 重新加载
		reloadDataSmall : function(thisId, targetId, method) {
			compareQuotedTypeFn.clearSelectedData(targetId);
			// 当前框的值
			var value = $("#" + thisId).combobox("getValue");
			if (!value) {
				value = 'null';
			}
			$("#" + targetId).combobox('reload', "masterDataType_list" + method + "_5.html?centreTypeCodeElse=" + value);
		},
		// 重新加载
		reloadDataDetail : function(thisId, targetId, method) {
			compareQuotedTypeFn.clearSelectedData(targetId);
			// 当前框的值
			var value = $("#" + thisId).combobox("getValue");
			if (!value) {
				value = 'null';
			}
			$("#" + targetId).combobox('reload', "masterDataType_list" + method + "_5.html?smallTypeCode=" + value);
		},
		// 重新加载
		reloadDataFine : function(thisId, targetId, method) {
			compareQuotedTypeFn.clearSelectedData(targetId);
			// 当前框的值
			var value = $("#" + thisId).combobox("getValue");
			if (!value) {
				value = 'null';
			}
			$("#" + targetId).combobox('reload', "masterDataType_list" + method + "_5.html?detailTypeCode=" + value);
		},

		// 清空下一个连动框已选择的值
		clearSelectedData : function(targetId) {
			$("#" + targetId).combobox("setValue", "");
			$("#" + targetId).combobox("clear");
		}
	};
