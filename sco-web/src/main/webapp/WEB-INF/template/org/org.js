var orgGridUtil=null;
$(document).ready(function(){
	$('#org_treegrid').treegrid({
		onClickRow:function(row){
			orgFn.setButtonState(null,row);
		}
	});
});
var orgFn = {
		
	onLoadSuccess:function(){
		$('#org_treegrid').treegrid("clearSelections");
		//orgGridUtil.datagrid("clearSelections");
		orgFn.setButtonState();
	},
	
	//显示添加或修改窗体
	showForm:function(isEdit){
		
		var orgPid=-1;// 默认父组织id
		var record=$("#org_treegrid").treegrid("getSelected");
		var record = orgFn.getSelectedRecord();
		if(record!=null){
			orgPid=record.orgId;//增加页面，父组织既是选中的组织
		}
		var title='${action.getText("common.title.add",action.getText("security.org"))}';
		var href='org_showInsertOrgForm_1.html?orgPid='+orgPid;
		if(isEdit){//显示修改窗体
			//var record=orgGridUtil.getSelectedRecord();
			var record = orgFn.getSelectedRecord();
			if(record==null)return;
			orgPid=record.orgPid;//修改页面，父组织是选中组织的上级组织
			title='${action.getText("common.title.edit",action.getText("security.org"))}';
			href='org_showUpdateOrgForm_1.html?orgId='+record.orgId+'&orgPid='+orgPid;
		}
		var dlg=utils.showDlg({
			title:title,href:href,width:560,height:300,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){orgFn.submitForm(dlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	//添加组织基础信息
	showInsert:function() {
		orgFn.showForm(false);
	},
	//修改组织基础信息
	showEdit:function(){
		orgFn.showForm(true);
	},
	//提交新增或修改表单
	submitForm:function(dlg){
		var orgId=$("input#orgId").val();
		var url="org_insertOrg_2.html";
		if(orgId){
			url="org_updateOrg_2.html";
		}
		utils.form("org_form").submit(url,null,function(){
			dlg.dialog('close');
			orgFn.refresh();
		});
	},
	refresh:function(){
    	$('#org_treegrid').treegrid('reload');
    },
	//激活功能树
	enable:function(){
		var record=$("#org_treegrid").treegrid("getSelected");
		if(record==null){
			$.messager.alert('${action.getText("common.message.prompt")}','${action.getText("common.message.requireSelection")}',"error");
			return;
		}
		var dlg=utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.org.msg.confirmActive")}',function(){
			utils.post("org_enableOrg_2.html",{orgId:record.orgId},function(){
				orgFn.refresh();
			});
		});
	},
	//禁用功能树
	disable:function(){
		var record=$("#org_treegrid").treegrid("getSelected");
		if(record==null){
			$.messager.alert('${action.getText("common.message.prompt")}','${action.getText("common.message.requireSelection")}',"error");
			return;
		}
		var dlg=utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.org.msg.confirmDisable")}',function(){
			utils.post("org_disableOrg_2.html",{orgId:record.orgId},function(){
				orgFn.refresh();
			});
			
		});
	},
	setOrg:function(row){
//		console.info(row);
	},
	//设置按钮状态
	setButtonState:function(index,record){
		if(record){
			$("a[id='showEdit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
			if(record.isactive=='Y'){
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
		}	
	},
	
	getSelectedRecord:function(){
		var rows = $('#org_treegrid').treegrid('getSelections');
		var record;
		if(rows.length==1){
			record = rows[0];
		}
		return record;
	}
};