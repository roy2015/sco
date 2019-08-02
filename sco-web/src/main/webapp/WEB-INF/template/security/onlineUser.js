var onlineUserGridUtil=null;
$(document).ready(function(){
	onlineUserGridUtil=utils.grid($('#onlineUser_grid'));
	onlineUserGridUtil.initFilters({
	});
});
var onlineUserFn = {
	//剔除用户
	kickOff:function(){
		var record=onlineUserGridUtil.getSelectedRecord();
		if(record==null)return;
		utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.onlineUser.msg.confirmKickOff")}',function(){
			utils.post("onlineUser_kickOffOnlineUser_2.html",{userId:record.userID},function(){
				onlineUserGridUtil.refresh();
			});
		});
	},
	//发送消息
	sendMessage:function(){
		var title='${action.getText("common.title.sendMessage")}';
		var href='onlineUser_showSendMessageForm_1.html';
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:350,
			buttons:[{text:'${action.getText("common.button.send")}',handler:function(){alert('${action.getText("security.onlineUser.msg.sendMsgSuccess")}');},iconCls:''},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	//清除查询
	clearFilter:function(){
		onlineUserGridUtil.clearFilter();
	}
};