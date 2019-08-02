$(document).ready(function(){
	$('#example1').timeliner();
	try {
		$('.node1').trigger('click');
	} catch (e) {
		
	}
});
var timelinerFn = {
	click1:function(){
		 $('#intention-1').panel("open");
		 $('#intention-2').panel("close");
		 $('#intention-3').panel("close");
		 $('#intention-4').panel("close");
		 $('#intention-5').panel("close");
		 $('#intention-6').panel("close");
		 $('#supplierAttachmentM_grid').datagrid('reload');
		 $('#supplierAttachmentM_grid_sc').datagrid('reload');
		 $('#supplierAttachmentM_grid_gl').datagrid('reload');
	},
	click2:function(){
		 $('#intention-1').panel("close");
		 $('#intention-2').panel("open");
		 $('#intention-3').panel("close");
		 $('#intention-4').panel("close");
		 $('#intention-5').panel("close");
		 $('#intention-6').panel("close");
		 $('#merchandisePriceOld_Grid0').datagrid('reload');
		 $('#merchandisePriceNow_Grid0').datagrid('reload');
		 $('#merchandisePriceCompare_Grid0').datagrid('reload');
		 $('#merchandiseSame_Grid0').datagrid('reload');
		 $('#merchandiseMaterial_Grid0').datagrid('reload');
	},
	click3:function(){
		 $('#intention-1').panel("close");
		 $('#intention-2').panel("close");
		 $('#intention-3').panel("open");
		 $('#intention-4').panel("close");
		 $('#intention-5').panel("close");
		 $('#intention-6').panel("close");
		 $.post('reports_listMyReportTypeForMOA_5.html', null, function(data){
			 $(data).each(function(i, obj){
				 $("#adjustrptTypeCode").append("<option value="+ obj.id +">"+obj.text+"</option>");
			 });
		 },"json");
		 utils.grid($('#adjustPriceOaReportAnalyGrid')).initFilters({noParamCanSort:true});//启用查询条件
	},
	click4:function(){//物料信息
		 $('#intention-1').panel("close");
		 $('#intention-2').panel("close");
		 $('#intention-3').panel("close");
		 $('#intention-5').panel("close");
		 $('#intention-6').panel("close");
		 $.post('wlInfoNew_ifApproved_2.html', {
			 applicationCode : '${applicationCode}'
		 }, function(data){ 
			if (data.success) {
				$('#intention-4').panel("open");
				wlInfoAdjustFn.loadData();
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
	},
	click5:function(){
		 $('#intention-1').panel("close");
		 $('#intention-2').panel("close");
		 $('#intention-3').panel("close");
		 $('#intention-4').panel("close");
		 $('#intention-5').panel("open");
		 $('#intention-6').panel("close");
		 
		 //跳转到OA系统，查看OA审批意见
//		 $.post("reportNew_findApproveOpinion_2.html?applicationCode="+'${applicationCode}', null, function(json){
//			 if(json.success){
//				 var rows=json.rows;
//				 var codes=rows.split(":");
//				 var url="http://eip.laiyifen.com/oa/?oaApplicationCode="+codes[0]+"&oaContacts="+codes[1];
				 var url = "wlInfoNew_ifApproved_2.html";
				 $.ajax({
						type : "post",
						url : url,
						async : false,//同步
						data : {applicationCode : '${applicationCode}'},
						dataType: "json",
						success : function(data){
							if ( data.rows != null && data.rows.applicationStatus != "CG") {
								var url = "http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/"
										+ "newproduct/common/approvalHistoryPageToSco.caf?formId="+data.rows.oaApplicationCode;
//										"http://eip.laiyifen.com/tbpm/cordys/laiyifen/common/html/inbox/inbox.htm?"
//										+"data=%3Cdata%20xmlns%3D%22http%3A%2F%2Fschemas.cordys.com%2Ftask%2F1.0%2F%22%2F%3E"
//										+"&organization=o%3Dlaiyifen%2Ccn%3Dcordys%2Ccn%3DdefaultInst1%2Co%3Dlaiyifen"
								window.open(url);
					 		} else {
						 		$.messager.alert('提示','仅能查看已经做了OA申请且申请状态不为草稿的记录!');
						 	}
						}
					});
				 
	 			 
		 		 /*$.post('wlInfoNew_ifApproved_2.html', {
					 applicationCode : '${applicationCode}'
				 }, function(data) {
					 if ( data.rows != null && data.rows.applicationStatus != "CG") {
						 var url = "http://eip.laiyifen.com/tbpm/cordys/User Interface/com/laiyifen/"
								+ "newproduct/common/approvalHistoryPageToSco.caf?formId="+data.rows.oaApplicationCode;
//								"http://eip.laiyifen.com/tbpm/cordys/laiyifen/common/html/inbox/inbox.htm?"
//								+"data=%3Cdata%20xmlns%3D%22http%3A%2F%2Fschemas.cordys.com%2Ftask%2F1.0%2F%22%2F%3E"
//								+"&organization=o%3Dlaiyifen%2Ccn%3Dcordys%2Ccn%3DdefaultInst1%2Co%3Dlaiyifen"
							window.open(url);
					 	} else {
					 		$.messager.alert('提示','仅能查看已经做了OA申请且申请状态不为草稿的记录!');
					 	}
				}, "json");*/
//			}else{
//				$.messager.alert('提示', '<center>没有OA系统同步数据，不能查看OA审批意见!</center>');
//			}
//		},"json");
	},
	click6:function(){
		 $('#intention-1').panel("close");
		 $('#intention-2').panel("close");
		 $('#intention-3').panel("close");
		 $('#intention-4').panel("close");
		 $('#intention-5').panel("close");
		 
		 $.post('wlInfoNew_ifApproved_2.html', {
			 applicationCode : '${applicationCode}'
		 }, function(data){ 
				if (data.success) {
					window.parent.pubTab.showTab('关联商品原料与公示网站原料', 'powertree', 
						"relevanceMaterialAndWebsite_showRelevanceMaterialAndWebsiteMain_1.html?" +
						"intentionAndSupplierCodes=${intentionAndSupplierCodes}&oaType=ADJ");
				} else {
					$.messager.alert("提示", "OA审批尚未通过，不可关联原料");
				}
		},"json");
	}
	
};