var northFn = {
		//退出系统
		logout:function(b) {
			$('#sessionInfoDiv').html('');
			$.post('login!run.do?dotyp=2&ac=loginOut', function() {
				window.location = "login.html";
			});
		},
		//显示修改密码界面
		showChangPwdForm:function(){
			var title="${action.getText("pub.msg.changePassword")}";
			var href="currentUser_showChangePassword_1.html";
			var dlg=utils.showDlg({
				title:title,href:href,width:260,height:220,
				buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){northFn.changePassword(dlg);},iconCls:'save'},
				        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				]
			});
		},
		//提交修改密码请求
		changePassword : function(dlg) {
			utils.form('#changePasswordForm').submit('currentUser_changePassword_2.html',null,function(){
				dlg.dialog('close');
			});
		},
		//显示修改个人信息界面
		showLoggedUserInfo:function(){
			var title='${action.getText("pub.title.changePersonInfo")}';
			var href="currentUser_showLoggedUserInfo_1.html";
			var dlg=utils.showDlg({
				title:title,href:href,width:600,height:250,
				buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){northFn.updateLoggedUserInfo(dlg);},iconCls:'save'},
				        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				],
				onLoad:function(){
					$("input#objectTyp").combobox("disable");
					$("input#objectName").attr("disabled", true);
					$("#openTime").datebox({disabled:true});
					$("#closeTime").datebox({disabled:true});
				}
			});
		},
		//提交修改的个人信息
		updateLoggedUserInfo:function(dlg){
			utils.form('#user_form').submit('currentUser_updateLoggedUserInfo_2.html',null,function(){
				dlg.dialog('close');
			});
		},
		//显示当前登陆用户设置代理目标的对话框
		showSetAgentTarget:function(){
			var title='${action.getText("pub.title.selectAgentObject")}';
			var href="currentUser_showChangeAgentForm_1.html";
			var dlg=utils.showDlg({
				title:title,href:href,width:300,height:120,
				buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){northFn.setAgentUser();},iconCls:'save'},
				        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				],
				onLoad:function(){
					var currentAgentId = $('#currentAgentId').val();
					var currentAgentName = $('#currentAgentName').val();
					$('#agentUserId').combogrid('setValue',currentAgentId);
					$('#agentUserId').combogrid('setText',currentAgentName);
				}
			});
		},
	setAgentUser : function() {
		$('#agentParamsForm').form('submit', {  
    		url: 'currentUser_changeAgentUser_2.html',  
    		onSubmit: function(){
    			return $(this).form('validate');
    		},  
    		success:function(data){
    			var json = $.parseJSON(data);
        		if (json.success)
        		{
        			parent.messagerShow({title:'Success', msg:'${action.text("public.success")}'});
        			$('#agentEditDlg').dialog('close');
        			window.location ="index.html";
        		} else
        			$.messager.alert('Error', json.msg);
    		}
		});  
	},
	
	//显示数据权限切换的对话框
	showChangeDataPower:function(){
		var title='数据权限切换';
		var href="currentUser_ShowChangeDatapowerForm_1.html";
		var dlg=utils.showDlg({
			title:title,href:href,width:300,height:150,
			buttons:[/*{text:'${action.getText("common.button.submit")}',handler:function(){northFn.changeDataPower(dlg);},iconCls:'save'},*/
			        {text:'${action.getText("common.button.close")}',handler:function(){dlg.dialog('close');},iconCls:'close'}
			],
			onLoad:function(){
				$("[id$='_DataPowerSelector']").each(function(selector){
					$(this).data("initDlg",true);//标记为刚弹出对话框，以避免在弹出对话框时触发onSelect事件
					var text=$(this).attr("defaultText");
					$(this).combogrid('setText',text);
				});
			}
		});
	},
	//提交数据权限切换
	changeDataPower:function(dataType){
		var combogridId='#'+dataType+'_DataPowerSelector';
		var dataId = $(combogridId).combo('getValue');
		var dataText = $(combogridId).combo('getText');
		var param = {dataType:dataType,dataId:dataId,dataText:dataText};
		utils.post("currentUser_changeDatapower_2.html",param);
	},

	//显示修改系统消息的界面
	showUpdateMassage:function(){
		var title='修改系统消息';
		var href="sysParam_showUpdateMassageForm_1.html";
		var dlg=utils.showDlg({
			title:title,href:href,width:500,height:120,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){northFn.setSystemMessage(dlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			],
			onLoad:function(){
				
			}
		});
	},
	
	setSystemMessage : function(dlg) {
		$('#messageParamsForm').form('submit', {  
    		url: 'sysParam_setSystemMessage_2.html',  
    		onSubmit: function(){
    			return $(this).form('validate');
    		},  
    		success:function(data){
    			$("font[id=pubMsg]").html($("input[id=pubMsgVal]").val());
    			parent.messagerShow({title:'操作成功', msg:'${action.text("public.success")}'});
    			dlg.dialog('close');
    		}
		});  
	},
	downloadUserManual : function(isSupplier){
		if(isSupplier==0){
			window.location="download_downloadSupplierUserManual_6.html";
		}else{			
			window.location="download_downloadUserManual_6.html";
		}
		$.messager.show({
			title:'文件下载提示',
			msg:'文件正在下载中，请稍后！',
			timeout:4000,
			showType:'slide'
		});
	}
	
};