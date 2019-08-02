$(document).ready(function(){

	$('#example1').timeliner();
	try {
		
			$('.node1').trigger('click');
		
	
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
		 $('#intention-7').hide();
		 $('#intention-8').hide();
		 $('#supplierAttachmentA_grid').datagrid('reload');
		 $('#supplierAttachmentA_grid_sc').datagrid('reload');
		 $('#supplierAttachmentA_grid_gl').datagrid('reload');
	//	document.getElementById('tenderInvitation').src="accessoryIntention_addAccessoryIntention1_1.html";
		//window.location="accessoryIntention_showInvitationFrame_1.html";
	},
	click2:function(){
		 $('#intention-1').hide();
		 $('#intention-2').show();
		 $('#intention-3').hide();
		 $('#intention-4').hide();
		 $('#intention-5').hide();
		 $('#intention-6').hide();
		 $('#intention-7').hide();
		 $('#intention-8').hide();
		 $('#supplierCertificateA_grid').datagrid('reload');
		 $('#supplierCertificateA_grid_gl').datagrid('reload');
	//	document.getElementById('tenderInvitation').src="accessoryIntention_addAccessoryIntention2_1.html";
		//window.location="accessoryIntention_showInvitationFrame1_1.html";
		//$("#tenderInvitation").attr("src","accessoryIntention_showInvitationFrame_2.html");
		/*var id = $('#id').val();
		if (id != null && id != '') {
			location.hash="sendA";
			//document.getElementById('invitationFrame').src="invitation_showInvitationFrame_1.html?invitationId=" + $('#invitationId').val() + "#sendA";
		} else {
			$.messager.show({title:'操作失败', msg:'邀请函还未保存', showType:'show'});
		}*/
	},
	click3:function(){
		//alert(333);
		 $('#intention-1').hide();
		 $('#intention-2').hide();
		 $('#intention-3').show();
		 $('#intention-4').hide();
		 $('#intention-5').hide();
		 $('#intention-6').hide();
		 $('#intention-7').hide();
		 $('#intention-8').hide();
		/*var state = $('#state').val();
		var istate = parseInt(state);
		if (istate >= 1) {
			$('#invitationFeedback_grid').datagrid('reload');
			location.hash="feedbackA";
			//document.getElementById('invitationFrame').src="invitation_showInvitationFrame_1.html?invitationId=" + $('#invitationId').val() + "#feedbackA";
		} else {
			$.messager.show({title:'操作失败', msg:'还未发送邀请函', showType:'show'});
		}*/
	},
	click4:function(){
		 $('#intention-1').hide();
		 $('#intention-2').hide();
		 $('#intention-3').hide();
		 $('#intention-4').show();
		 $('#intention-5').hide();
		 $('#intention-6').hide();
		 $('#intention-7').hide();
		 $('#intention-8').hide();
		 $.post('reports_listPartMyReportType_5.html', null, function(data){
			 $(data).each(function(i, obj){
				 $("#rptTypeCode").append("<option value="+ obj.id +">"+obj.text+"</option>");
			 });
		 },"json");
		 
		 utils.grid($('#committeeOaReportAnalyGrid')).initFilters({
			noParamCanSort:true,
//			onBeforeLoad:function() {
//				comOaReportAnalyFn.setDatagridHeight();
//			},
			onLoadSuccess:function(){
				$('#committeeOaReportAnalyGrid').datagrid('clearSelections');
			}
		 });//启用查询条件
		 utils.grid($('#uploadOaReportAnalyGrid')).initFilters({
			noParamCanSort:true,
//			onBeforeLoad:function() {
//				comOaReportAnalyFn.setDatagridHeight();
//			},
			onLoadSuccess:function(){
				$('#uploadOaReportAnalyGrid').datagrid('clearSelections');
			}
		 });//启用查询条件
		 utils.grid($('#purOrderOaReportAnalyGrid')).initFilters({
			noParamCanSort:true,
//			onBeforeLoad:function() {
//				comOaReportAnalyFn.setDatagridHeight();
//			},
			onLoadSuccess:function(){
				$('#purOrderOaReportAnalyGrid').datagrid('clearSelections');
			}
		 });//启用查询条件
	},
	click5:function(){
		 $('#intention-1').hide();
		 $('#intention-2').hide();
		 $('#intention-3').hide();
		 $('#intention-4').hide();
		 $('#intention-5').show();
		 $('#intention-6').hide();
		 $('#intention-7').hide();
		 $('#intention-8').hide();
		
		 //跳转到OA系统，查看OA审批意见
		 var applicationCode = $("input#applicationCodeNow").val();
		 var url = "committeeApply_lookApproveOpinion_2.html";
		 $.ajax({
				type : "post",
				url : url,
				async : false,//同步
				data : {applicationCode : applicationCode},
				dataType: "json",
				success : function(data){
					if(data.success) {
						var rows = data.rows;
						var codes = rows.split(":");
						window.open("http://eip.laiyifen.com/oa/lyf/cgwyhjjd.nsf/0/"+codes[0]+"?opendocument");
					} else {
						$.messager.alert('提示', '<center>没有OA系统同步数据，不能查看OA审批意见!</center>');
					}
				}
			});
		 
		 /*$.post(url+"?applicationCode="+applicationCode, null, function(json){
				if(json.success){
					var rows=json.rows;
					var codes=rows.split(":");
					window.open("http://eip.laiyifen.com/oa/lyf/cgwyhjjd.nsf.nsf/0/"+codes[0]+"?opendocument");
				}else{
					$.messager.alert('提示', '<center>没有OA系统同步数据，不能查看OA审批意见!</center>');
				}
		},"json");*/
	},
	click6:function(){
		 $('#intention-1').hide();
		 $('#intention-2').hide();
		 $('#intention-3').hide();
		 $('#intention-4').hide();
		 $('#intention-5').hide();
		
		 $('#intention-7').hide();
		 $('#intention-8').hide();
		 $.post('committeeApply_ifApproved_2.html', {
			 applicationCode : '${applicationCodeNow}'
		 }, function(data){ 
			if (data.success) {
				 $('#intention-6').show();
				 $('#wlInfo_grid').datagrid('reload');
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
		
	}
	,
	click7:function(){
		 $('#intention-1').hide();
		 $('#intention-2').hide();
		 $('#intention-3').hide();
		 $('#intention-4').hide();
		 $('#intention-5').hide();
		 $('#intention-6').hide();
		 $('#intention-7').show();
		 $('#intention-8').hide();
		 utils.grid($('#appScheCommittGrid')).initFilters({
			 noParamCanSort:true,
			 onLoadSuccess:function(data){
				 editIndex = undefined;
				 $('#appScheCommittGrid').datagrid('clearSelections');
				 for (var i in data.rows) {
					 $('#appScheCommittGrid').datagrid('beginEdit', i);
					 $('#aogDate' + i).datebox({
						 editable:false,
						 onHidePanel:appScheCommitteeFn.calculRealityFlowDate
					 });
				 }
			 }
		 	/*,
			onDblClickRow : function(rowIndex, rowData){
				$(this).datagrid('beginEdit', rowIndex);
				if (editIndex != rowIndex){
					 if (appScheCommitteeFn.endEditing()){
						 editIndex = rowIndex;
						 $('#appScheCommittGrid').datagrid('selectRow', rowIndex)
						 .datagrid('beginEdit', rowIndex);
					 } else {
						 $('#appScheCommittGrid').datagrid('selectRow', editIndex);
					 }
				}
			}*/
		 });//启用查询条件
	}
	,
	click8:function(){
		 $('#intention-1').hide();
		 $('#intention-2').hide();
		 $('#intention-3').hide();
		 $('#intention-4').hide();
		 $('#intention-5').hide();
		 $('#intention-6').hide();
		 $('#intention-7').hide();
		 
		 $.post('committeeApply_ifApproved_2.html', {
			 applicationCode : '${applicationCodeNow}'
		 }, function(data){ 
			if (data.success) {
				 $('#intention-8').show();
				 utils.grid($('#applicationQuoted_grid')).initFilters({});
				 utils.grid($('#dhInfo_grid')).initFilters({});
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
		 
	}
};