var roleGridUtil=null;
$(document).ready(function(){
	//roleFn.initDataPowerMenu();//初始化数据权限菜单
	roleGridUtil=utils.grid($('#role_grid'));
	roleGridUtil.initFilters({
		onDblClickRow:roleFn.showEdit,//双击修改
		onClickRow:roleFn.setButtonState,//设置按钮状态
		onLoadSuccess:function(){
			$('#role_grid').datagrid("clearSelections");
			roleFn.setButtonState();
		}
	});
});
var roleFn = {
	//显示添加或修改窗体
	showForm:function(isEdit){
		var title='${action.getText("common.title.add",action.getText("security.role"))}';
		var href='role_showInsertRoleForm_1.html';
		if(isEdit){//显示修改窗体
			var record=roleGridUtil.getSelectedRecord();
			if(record==null)return;
			title='${action.getText("common.title.edit",action.getText("security.role"))}';
			href='role_showUpdateRoleForm_1.html?roleId='+record.roleId;
		}
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:350,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){roleFn.submitForm(dlg);},iconCls:'save', id:'saveRole'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	//添加角色基础信息
	showInsert:function() {
		roleFn.showForm(false);
	},
	//修改角色基础信息
	showEdit:function(){
		roleFn.showForm(true);
	},
	//提交新增或修改表单
	submitForm:function(dlg){
		
		// 选中的权限
		var funcIds="";
		var nodes = $('#powertree').tree('getCheckedExt');
		
		$("a[id='saveRole']").linkbutton("disable");
		for(var i=0; i<nodes.length; i++){  
			funcIds+=funcIds==""?nodes[i].id:","+nodes[i].id;
		}
		
		var url="role_insertRole_2.html?funcIds="+funcIds;
		var roleId=$("input#roleId").val();
		if(roleId){
			url="role_updateRole_2.html?funcIds="+funcIds;
		}
		
		utils.form("role_form").submit(url,null,function(){
			dlg.dialog('close');
			roleGridUtil.refresh();
		});
		
	},
	//删除角色基础信息
	remove:function(){
		var roleIds = utils.grid($("#role_grid")).getSelectedIdString("roleId");
		if(roleIds.length<=0){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("security.role.msg.confirmSelectDeleteRole")}',"question");
			return;
		}
		utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.role.msg.confirmDeleteRole")}',function(){
			utils.post("role_deleteRole_2.html",{roleIds:roleIds},function(){
				roleGridUtil.refresh();
			});
		});
	},
	//激活角色
	enable:function(){
		var record=roleGridUtil.getSelectedRecord();
		if(record==null)return;
		utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.role.msg.confirmActive")}',function(){
			utils.post("role_enableRole_2.html",{roleId:record.roleId},function(){
				roleGridUtil.refresh();
			});
		});
	},
	//禁用角色
	disable:function(){
		var record=roleGridUtil.getSelectedRecord();
		if(record==null)return;
		utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.role.msg.confirmDisable")}',function(){
			utils.post("role_disableRole_2.html",{roleId:record.roleId},function(){
				roleGridUtil.refresh();
			});
		});
	},
	//显示copy角色权限
	showCopyRolePower:function(){
		var record=roleGridUtil.getSelectedRecord();
		if(record==null)return;
		var title='${action.getText("security.role.copyRolePower")}';
		var href='role_showCopyRoleForm_1.html?roleId='+record.roleId;
		var dlg = utils.showDlg({
			title:title,href:href,width:600,height:350,
			buttons:[{text:'${action.getText("common.button.copy")}',iconCls:'copy',handler:function(){roleFn.copyRolePower(record.roleId,dlg);}},
			        {text:'${action.getText("common.button.cancel")}',iconCls:'cancel',handler:function(){dlg.dialog('close');}}
			],
			onLoad:function(){
				var copyRoleGridUtil=utils.grid($('#copy_role_grid'));
				copyRoleGridUtil.initFilters();
			}
		});
	},
	//copy角色权限
	copyRolePower:function(roleId,dlg){
		var rows = $("#copy_role_grid").datagrid("getChecked");
		var fromRoleIds = utils.grid($('#copy_role_grid')).getSelectedIdString("roleId");
		if(fromRoleIds==null || fromRoleIds.length<=0){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("security.role.msg.confirmCopyPrivalige")}',"question");
			return;
		}
		utils.post("role_copyRolePower_2.html",{toRoleId:roleId,fromRoleIds:fromRoleIds,copyDataPower:$('input#copyDataPower').attr('checked')=='checked'?true:false},function(){
			dlg.dialog('close');
			roleGridUtil.refresh();
		});
	},
	//清除查询
	clearFilter:function(){
		roleGridUtil.clearFilter();
	},
	//处理不该被选中节点
	treeLoadSuccess:function(node,data){
		roleFn.setButtonState();//设置按钮状态
		var uncheckNode=function(node){
			if(node.children != null) {
				for(var i=0;i<node.children.length;i++){
					uncheckNode(node.children[i]);
				}
			}
			if(!node.checked){
				var n=$("#powertree").tree("find",node.id);
				$("#powertree").tree("uncheck",n.target);
			}
		};
		for(var i=0;i<data.length;i++){
			uncheckNode(data[i]);
		}
	},
	refresh:function(){
    	$('#role_treegrid').treegrid('reload');
    },
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#role_grid').datagrid("getSelections");
		if(rows.length==1){
			$("a[id='showEdit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
			$("a[id='copyRolePower']").linkbutton("enable");
			$("a[id='dataPowerMenu']").menubutton("enable");
			if(rows[0].active=='Y'){
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
			$("a[id='copyRolePower']").linkbutton("disable");
			$("a[id='dataPowerMenu']").menubutton("disable");
		}	
	},
	//以下是数据权限部分========================================================================
	//显示数据权限对话框
	showDataPowerDlg:function(id,text,href){
		var role=roleGridUtil.getSelectedRecord();
		if(role==null)return;
		var title='${action.getText("security.role.title.setRole")}'+role.name+'${action.getText("security.role.title.managed")}'+text;
		var href=href+"?roleId="+role.roleId+"&dataType="+id;
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:400,
			buttons:[{
				text:'${action.getText("common.button.close")}',
				handler:function(){
					dlg.dialog('close');
				},
				iconCls:'close'
			}]
		});
	},
	//初始化数据权限采单
	initDataPowerMenu:function(){
		$.each(Dic.DataPowerTypes,function(index,item){
			if(item.forRole!==true)return;
			var div=$("<div data-options=\"iconCls:'"+item.iconCls+"'\">"+item.text+"</div>");
			div.appendTo("#dataPowerMenuItems");
			div.click(function(){
				roleFn.showDataPowerDlg(item);
			});
		});
		$("#dataPowerMenu").menubutton();
	}
	//以上是数据权限部分========================================================================
};