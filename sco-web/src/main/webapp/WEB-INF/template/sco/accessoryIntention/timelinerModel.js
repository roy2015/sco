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
		 $('#accessoryIntention1_grid').datagrid('reload');
	//	document.getElementById('tenderInvitation').src="accessoryIntention_addAccessoryIntention1_1.html";
		//window.location="accessoryIntention_showInvitationFrame_1.html";
	},
	click2:function(){
		 
		 $.post('accessoryIntention_ifApproved_2.html', {
			 intentionCode : '${accessoryIntention.intentionCode}'
		 }, function(data){ 
			if (data.success) {
				$('#intention-1').hide();
				 $('#intention-3').hide();
				 $('#intention-4').hide();
				 $('#intention-5').hide();
				 $('#intention-2').show();
				 $('#accessoryEnquiry_grid').datagrid('reload');
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
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
		
		 $.post('accessoryIntention_ifApproved_2.html', {
			 intentionCode : '${accessoryIntention.intentionCode}'
		 }, function(data){ 
			if (data.success) {
				 $('#intention-1').hide();
				 $('#intention-2').hide();
				 $('#intention-4').hide();
				 $('#intention-5').hide();
				 $('#intention-3').show();
				 $('#accessoryIntentionSupplier_dcxjd').datagrid('reload');
				 $('#accessoryEnquiry_dcxjd').datagrid('reload');
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
		
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
		 
		 $.post('accessoryIntention_ifApproved_2.html', {
			 intentionCode : '${accessoryIntention.intentionCode}'
		 }, function(data){ 
			if (data.success) {
				$('#intention-1').hide();
				 $('#intention-2').hide();
				 $('#intention-3').hide();
				 $('#intention-5').hide();
				 $('#intention-4').show();
				 $('#accessoryIntentionSupplier_drbjd').datagrid('reload');
				 $('#accessoryEnquiry_drbjd').datagrid('reload');
				 $('#accessoryQuotedElectronic_drbjd').datagrid('reload');
				 $('#accessoryQuotedScan_drbjd').datagrid('reload');
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
		
		/*var state = $('#state').val();
		var istate = parseInt(state);
		if (istate >= 2) {
			$('#invitationFeedback_grid').datagrid('reload');
			$('#uploadAccessoryFilesGrid').datagrid('reload');
			location.hash="rfpA";
			//document.getElementById('invitationFrame').src="invitation_showInvitationFrame_1.html?invitationId=" + $('#invitationId').val() + "#rfpA";
		} else {
			$.messager.show({title:'操作失败', msg:'供应商还未有反馈', showType:'show'});
		}*/
	},
	click5:function(){
		
		 $.post('accessoryIntention_ifApproved_2.html', {
			 intentionCode : '${accessoryIntention.intentionCode}'
		 }, function(data){ 
			if (data.success) {
				 $('#intention-1').hide();
				 $('#intention-2').hide();
				 $('#intention-3').hide();
				 $('#intention-4').hide();
				 $('#intention-5').show();
				 $('#accessoryQuotedElectronic_dyxx').datagrid('reload');
				 $('#accessoryProofing_dyxx').datagrid('reload');
			} else {
				$.messager.alert("提示", data.msg);
			}
		 },"json");
		
		
		/*var state = $('#state').val();
		var istate = parseInt(state);
		if (istate >= 4) {
			$('#invitationFeedback_grid').datagrid('reload');
			$('#uploadAccessoryFilesGrid').datagrid('reload');
			$('#invitationRRPFeedback_grid').datagrid('reload');
			location.hash="rfpfeedbackA";
			//document.getElementById('invitationFrame').src="invitation_showInvitationFrame_1.html?invitationId=" + $('#invitationId').val() + "#rfpfeedbackA";
		} else {
			$.messager.show({title:'操作失败', msg:'还未发送RFP', showType:'show'});
		}*/
	}
};