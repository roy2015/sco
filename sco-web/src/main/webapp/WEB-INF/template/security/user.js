var userGridUtil=null;
var agentGridUtil=null;
$(document).ready(function(){
	//userFn.initDataPowerMenu();//初始化数据权限菜单
	userGridUtil=utils.grid($('#user_grid'));
	userGridUtil.initFilters({
		onDblClickRow:userFn.showEdit,//双击修改
		onClickRow:userFn.setButtonState,//设置按钮状态
		onLoadSuccess:function(){
			$('#user_grid').datagrid("clearSelections");
			userFn.setButtonState();
		}
	});
});
var userFn = {
	//显示添加或修改窗体
	showForm:function(isEdit){
		var title='${action.getText("common.title.add",action.getText("security.user"))}';
		var href='user_showInsertUserForm_1.html';
		if(isEdit){//显示修改窗体
			var record=userGridUtil.getSelectedRecord();
			if(record==null)return;
			title='${action.getText("common.title.edit",action.getText("security.user"))}';
			href='user_showUpdateUserForm_1.html?userId='+record.userId;
		}
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:300,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){userFn.submitForm(dlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	//添加用户基础信息
	showInsert:function() {
		userFn.showForm(false);
	},
	//修改用户基础信息
	showEdit:function(){
		userFn.showForm(true);
	},
	//显示批量注册页面
	showBatchInsert:function(){
		var dlg = utils.showDlg({
			title:'${action.getText("user.title.batchAdd",action.getText("security.user"))}',
			href:"user_showBatchInsertUserForm_1.html",
			width:480,
			height:250,
			buttons:[/*{
				text:'${action.getText("common.button.download.template.excel")}',
				handler:function(){
					userFn.downloadExcelTemplate();
				},
				iconCls:'excel'
				},*/{
				text:'${action.getText("common.button.submit")}',
				handler:function(){
					utils.confirm("确认操作", "确认要批量注册用户", function(){
						utils.form("batchInsertUserForm").submit("user_batchInsertUser_2.html",null,function(){
							userGridUtil.refresh();
						});
						dlg.dialog("close");
					});
				},
				iconCls:'add'
			},{
				text:'${action.getText("common.button.cancel")}',
				handler:function(){dlg.dialog('close');},
				iconCls:'cancel'
			}]
		});
	},
	//导出用户信息
	exportUser:function(){
		window.location = "user_exportUser_3.html?"+$.param(userGridUtil.getFilterValue());
	},
	//提交新增或修改表单
	submitForm:function(dlg){
		var userId = $("#userId").val();
		var url = "user_insertUser_2.html";
		if(userId){
			url = "user_updateUser_2.html";
		}
		utils.form("user_form").submit(url,null,function(){
			dlg.dialog('close');
			userGridUtil.refresh();
		});
	},
	//激活用户
	enable:function(){
		var record=userGridUtil.getSelectedRecord();
		if(record==null)return;
		utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.user.msg.confirmActive")}',function(){
			utils.post("user_enable_2.html",{userId:record.userId},function(){
				userGridUtil.refresh();
			});
		});
	},
	//禁用用户
	disable:function(){
		var record=userGridUtil.getSelectedRecord();
		if(record==null)return;
		utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.user.msg.confirmDisable")}',function(){
			utils.post("user_disable_2.html",{userId:record.userId},function(){
				userGridUtil.refresh();
			});
		});
	},
	//设置用户角色窗体
	setUserRole:function(isEdit){
		var record=userGridUtil.getSelectedRecord();
		if(record==null)return;
		var title='${action.getText("user.title.setUserRole")}';
		var href='user_showUserRoleForm_1.html?userId='+record.userId;
		var dlg=utils.showDlg({
			title:title,href:href,width:540,height:300,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){userFn.setUserRole();},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	//清除查询
	clearFilter:function(){
		userGridUtil.clearFilter();
	},
	//处理要选中的行
	userRoleFridLoadSuccess:function(data){
		var rows = data.rows;
		for (var i=0;i<rows.length;i++) {
			var row = rows[i];
			if(row.checked){
				var index=$("#userRoleGrid").datagrid("getRowIndex",row);
				$("#userRoleGrid").datagrid("checkRow",index);
			}
		}
	},
	//显示重置密码界面
	showResetPassword:function(){
		var record=userGridUtil.getSelectedRecord();
		if(record==null)return;
		var dlg=utils.showDlg({
			title:'${action.getText("user.title.resetPassword")}',
			href:"user_showResetPassword_1.html?"+$.param({loginName:record.loginName}),
			width:450,height:250,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){userFn.resetPassword(dlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},// 重置密码
	resetPassword:function(dlg){
		utils.form("resetPassword_form").submit("user_resetPassword_2.html",null,function(){
			dlg.dialog('close');
			userGridUtil.refresh();
		});
	},
	//显示设置角色界面
	setUserRole:function(){
		var record=userGridUtil.getSelectedRecord();
		if(record==null)return;
		var title='${action.getText("user.title.setUserRole")}';
		var href='user_showUserRoleForm_1.html?userId='+record.userId;
		var dlg = utils.showDlg({
			title:title,
			href:href,
			width:600,
			height:350,
			buttons:[
			    {text:'${action.getText("user.button.setUserRole")}',
				iconCls:'save',
				handler:function(){
					var roleIds=utils.grid($('#userRoleGrid')).getSelectedIdString("roleId");
					var userId=$("#userId").val();
					utils.post("user_setRoles_2.html",{userId:userId,roleIds:roleIds},function(){
						dlg.dialog('close');
						userGridUtil.refresh();
					});
				}},
				{text:'${action.getText("common.button.cancel")}',
				iconCls:'cancel',
				handler:function(){
					dlg.dialog('close');
				}}
			]
		});
	},
	// 处理分配角色界面的勾选状态
	userRoleFridLoadSuccess:function(data){
		var rows = data.rows;
		for (var i = 0; i < rows.length; i++) {
			if(rows[i].checked){
				$("#userRoleGrid").datagrid("selectRow",i);
			}
		}
	},
	// 处理设置代理用户界面的勾选状态
	agentFridLoadSuccess:function(data){
		var rows = data.rows;
		for (var i = 0; i < rows.length; i++) {
			if(rows[i].checked){
				$("#agentGrid").datagrid("selectRow",i);
			}
		}
	},
	agentDlg:null,
	// 设置代理
	setAgent:function(){
		var record=userGridUtil.getSelectedRecord();
		if(record==null)return;
		var title="${action.getText('user.title.do')}"+record.loginName+"${action.getText('user.title.setAgent')}";
		var href='agent_showAgentForm_1.html?userId='+record.userId;
		var dlg = utils.showDlg({
			title:title,
			href:href,
			width:600,
			height:350,
			onLoad:function(){
				agentGridUtil = utils.grid($('#agentGrid'));
			},
			toolbar:[
			    {text:'${action.getText("user.button.addAgent")}',
				iconCls:'save',
				handler:function(){
					var agentIds=agentGridUtil.getSelectedIdArr("userId");
					var userId=$("#userId").val();
					userFn.agentDlg=utils.showDlg({
						title:'${action.getText("security.agent.title.nonMyAgentList")}',
						href:"agent_showNonMyAgentForm_1.html?userId="+record.userId,
						width:600,
						height:350,
						buttons:[
						   /* {text:'${action.getText("user.button.addAgent")}',
							iconCls:'save',
							handler:function(){
								var agentIds=utils.grid($('#nonAgentGrid')).getSelectedIdArr("userId");
								var userId=$("#userId").val();
								utils.post("agent_setAgent_2.html",{userId:userId,agentIds:agentIds},function(){
									dlg.dialog('close');
									userGridUtil.refresh();
									agentGridUtil.refresh();
								});
							}},*/
							{text:'${action.getText("common.button.cancel")}',
							iconCls:'cancel',
							handler:function(){
								userFn.agentDlg.dialog('close');
							}}
						]
					});
				}},
				{text:'${action.getText("user.button.remove.agent")}',
				iconCls:'delete',
				handler:function(){
					var userId = $("input#userId").val();
					var agentIds = agentGridUtil.getSelectedIdArr("userId");
					if(agentIds!=null&&agentIds.length>0){
						utils.post("agent_deleteAgent_2.html",{userId:userId,agentIds:agentIds},function(){
							agentGridUtil.refresh();
						});
					}
				}}
			],
			buttons:[
				{text:'${action.getText("common.button.close")}',
				iconCls:'cancel',
				handler:function(){
					dlg.dialog('close');
				}}
			]
		});
	},
	
	//提交选择代理用户
	setAgentSave:function(){
		var record=$('#nonAgentGrid').datagrid("getSelected");
		if (record==null) {
			$.messager.alert('${action.getText("pub.msg.prompt")}', 
					'${action.getText("security.agent.title.selectMyAgent")}', "Warning");
			return;
		}
		var agentIds=record.userId;
		var userId=$("#userId").val();
		var startTime= $('#startTime').datetimebox('getValue');
		var endTime=$('#endTime').datetimebox('getValue');
//		if(startTime!=null&&startTime!="" && endTime!=null&&endTime!=""){
			if(startTime==null||startTime=="" && endTime==null || endTime==""){
				utils.post("agent_setAgent_2.html",{userId:userId,agentIds:agentIds,startTime:startTime,endTime:endTime},function(){
					userFn.agentDlg.dialog('close');
					utils.grid($('#agentGrid')).refresh();
//					userFn.agentAdddlg.dialog('close');
//					userGridUtil.refresh();
				});
			}else if(startTime!=null&&startTime!="" && endTime!=null&&endTime!="" && startTime<endTime){
				utils.post("agent_setAgent_2.html",{userId:userId,agentIds:agentIds,startTime:startTime,endTime:endTime},function(){
					userFn.agentDlg.dialog('close');
					utils.grid($('#agentGrid')).refresh();
//					userGridUtil.refresh();
				});
			}else{
				$.messager.alert('${action.getText("common.message.prompt")}', '${action.getText("common.message.timeRangeCheck")}');
			}
//		}
	},
	
	// 显示copy权限界面
	showCopyUserPower:function(){
		var record=userGridUtil.getSelectedRecord();
		if(record==null)return;
		var title='${action.getText("user.title.copyPower")}';
		var href="user_showCopyPowerForm_1.html?loginName="+record.loginName;
		var dlg = utils.showDlg({
			title:title,href:href,width:600,height:350,
			buttons:[{text:'${action.getText("common.button.copy")}',iconCls:'copy',handler:function(){userFn.copyUserPower(record.userId,dlg);}},
			        {text:'${action.getText("common.button.cancel")}',iconCls:'cancel',handler:function(){dlg.dialog('close');}}
			],
			onLoad:function(){
				var copyUserGridUtil=utils.grid($('#copy_user_grid'));
				copyUserGridUtil.initFilters();
			}
		});
	},
	// copy权限
	copyUserPower:function(userId, dlg){
		var rows = $("#copy_user_grid").datagrid("getChecked");
		var fromUserIds = utils.grid($('#copy_user_grid')).getSelectedIdString("userId");
		if(fromUserIds==null || fromUserIds.length<=0){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("security.user.msg.confirmCopyPrivilege")}',"question");
			return;
		}
		utils.post("user_copyUserPower_2.html",{toUserId:userId,fromUserIds:fromUserIds,copyDataPower:$('input#copyDataPower').attr('checked')=='checked'?true:false},function(){
			dlg.dialog('close');
			userGridUtil.refresh();
		});
	},
	showOrgDlg:function(obj){
		var objValue = $("#objectTyp").combo('getValue'); 
		if(objValue == null || objValue == ""){
			return ;
		}
		var title="${action.getText('common.title.orgList')}";
		var href=Dic.findById("OrgTypes",objValue).href;
		userFn.orgSimpleDlg=utils.showDlg({
			title:title,href:href,width:600,height:350,closed:true 
			/*buttons:[{
				text:'${action.getText("common.button.cancel")}',
				handler:function(){
					orgSimpleDlg.dialog('close');
				},
				iconCls:'cancel'
			}]*/
		});
		userFn.orgSimpleDlg.dialog('open');
	},
	setOrg:function(id, name){
		$("input#objectName").combobox("setValue",name);
		$("input#objectId").val(id);
		userFn.orgSimpleDlg.dialog('close');
	},
	//清空组织名称框的值
	clearOrgName:function(newValue, oldValue){
		$("input#objectName").combobox("setValue", "");
		$("input#objectId").val("");
	},
	//设置按钮状态
	setButtonState:function(index,record){
		if(record){
			$("a[id='showEdit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
			$("a[id='setUserRole']").linkbutton("enable");
			$("a[id='copyPrivilege']").linkbutton("enable");
			$("a[id='resetPassword']").linkbutton("enable");
			$("a[id='setAgent']").linkbutton("enable");
			$("a[id='dataPowerMenu']").menubutton("enable");
			if(record.active=='Y'){
				$("a[id='enable']").linkbutton("disable");
				$("a[id='disable']").linkbutton("enable");
			}else{
				$("a[id='enable']").linkbutton("enable");
				$("a[id='disable']").linkbutton("disable");
			}
		}else{
			$("a[id='showEdit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
			$("a[id='enable']").linkbutton("disable");
			$("a[id='disable']").linkbutton("disable");
			$("a[id='setUserRole']").linkbutton("disable");
			$("a[id='copyPrivilege']").linkbutton("disable");
			$("a[id='resetPassword']").linkbutton("disable");
			$("a[id='setAgent']").linkbutton("disable");
			$("a[id='dataPowerMenu']").menubutton("disable");
		}	
	},
	//以下是数据权限部分========================================================================
	//显示数据权限对话框
	showDataPowerDlg:function(id,text,href){
		var user=userGridUtil.getSelectedRecord();
		if(user==null)return;
		var title='${action.getText("user.title.setUser")}'+user.loginName+'${action.getText("user.title.managed")}'+text;
		var href=href+"?userId="+user.userId+"&dataType="+id;
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:400,
			buttons:[{
				text:'${action.getText("common.button.cancel")}',
				handler:function(){
					dlg.dialog('close');
				},
				iconCls:'cancel'
			}]
		});
	},
	//初始化数据权限采单
	initDataPowerMenu:function(){
		/*$.each(Dic.DataPowerTypes,function(index,item){
			if(item.forUser!==true)return;
			//var hasFunc = '${action.hasFuncPower("com.escm.datapower")}';
			if(item.funcKey != 'com.escm.datapower.vendor')return;
			var div=$("<div data-options=\"iconCls:'"+item.iconCls+"'\">"+item.text+"</div>");
			div.appendTo("#dataPowerMenuItems");
			div.click(function(){
				userFn.showDataPowerDlg(item);
			});
		});
		$("#dataPowerMenu").menubutton();*/
	}
	//以上是数据权限部分========================================================================
	,downloadExcelTemplate:function(){
		window.location = "user_downloadExcelTemplate_6.html";
	}
};