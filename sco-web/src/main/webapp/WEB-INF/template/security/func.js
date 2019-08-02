var isFreeRow=null;
$(document).ready(function(){
	$('#func_treegrid').treegrid({
		onDblClickRow:function(row){
			funcFn.showEdit(row);
		},
		onClickRow:function(row){
			funcFn.setButtonState(null, row);
		}
	});
	/*grid.initFilters({
		onDblClickRow:funcFn.showEdit,//双击修改
		onClickRow:funcFn.setButtonState//设置按钮状态
	});*/
});
var funcFn = {
	//显示添加或修改窗体
	showForm:function(isEdit,position){
		var title='${action.getText("common.title.add",action.getText("security.func"))}';
		var href='func_showInsertFuncForm_1.html';
		//无论修改还是添加都要找到当前选中节点
		var record = funcFn.getSelectedRecord();
		isFreeRow=$('#func_treegrid').datagrid('getSelected');
		if(record==null)return;
		if(isEdit){
			
			//显示修改窗体
			title='${action.getText("common.title.edit",action.getText("security.func"))}';
			href='func_showUpdateFuncForm_1.html?funcId='+record.funcId;
		}
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:300,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){funcFn.submitForm(dlg,isEdit);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			],
			onLoad:function(){
				var funcType = $("#funcType").combobox("getValue");
				if(funcType =='U'||funcType =='R'){
					$('#dataTypeId').show();
				}else{
					$('#dataTypeId').hide();
				}
				if(!isEdit){//添加时
					funcFn.splitSelectedPower(position,record.funcKey);
					$('#position').val(position);//设置添加节点的位置
					$('#funcPid').val(record.funcId);//设置当前选中节点的ID
					$('#disableMsgRow').remove();
				}else{
					if(record.active=='Y'){$('#disableMsgRow').remove();}//激活状态时不显示禁用消息
				}
				if(isEdit){
					if(isFreeRow.isFree==='Y'){
						document.getElementById("isFree").checked=true;
					}else{
						document.getElementById("isFree").checked=false;
					}
				}
				
			}
		});
	},
	//添加功能功能基础信息
	showInsert:function(position) {
		funcFn.showForm(false,position);
	},
	//修改功能功能基础信息
	showEdit:function(){
		funcFn.showForm(true);
	},
	//提交新增或修改表单
	submitForm:function(dlg){
		var record = funcFn.getSelectedRecord();
		var parnetNode=record;
		if($("#func_form #position").val()!=="child"){
			parnetNode=$('#func_treegrid').treegrid('getParent',record.funcId);//选中节点的上级
		}
		if(parnetNode!==null&&$("#func_form #funcType").combobox("getValue")=="M"){//如果当前添加的节点是菜单,则上级必须是菜单
			var parnetType=parnetNode.funcType;
			if(parnetType!=='M'){
				$.messager.alert('${action.text("pub.msg.warn")}','${action.text("security.func.msg.menuUpMust")}');
				return;
			}
		}
		var url="func_insertFunc_2.html";// 默认是添加功能权限的地址
		var funcId=$("input#funcId").val();
		if(funcId){
			url="func_updateFunc_2.html";
		}
		if(document.getElementById("isFree").checked){
			$('#isFree').val("Y");
		}else{
			$('#isFree').val("N");
		}
		utils.form("func_form").submit(url,null,function(){
			dlg.dialog('close');
			$('#func_treegrid').treegrid('reload');
		});
	},
	//删除功能功能基础信息
	remove:function(){
		var record = funcFn.getSelectedRecord();
		if(record==null)return;
		utils.confirm('${action.text("pub.msg.confirm")}','${action.text("security.func.msg.confirmDelete")}',function(){
			utils.post("func_deleteFunc_2.html",{funcId:record.funcId},function(){
				$('#func_treegrid').treegrid('reload');
			});
		});
	},
	//让树表支持拖拽，并响应拖拽事件
	onLoadSuccess:function(){
		funcFn.setButtonState();//设置按钮状态
		utils.treegrid($('#func_treegrid')).onDrop(function(srcId,desId,action){
			var dataMsg={srcId:srcId,desId:desId,position:action};
			utils.confirm('${action.text("pub.msg.confirm")}', '${action.text("security.func.msg.confirmRemove")}', function(r){  
            	utils.post("func_moveFunc_2.html",dataMsg,function(data){
    				$('#func_treegrid').treegrid('reload');
    			});  
            }); 
		});
    },
    refresh:function(){
    	$('#func_treegrid').treegrid('reload');
    },
	//激活功能树
	enable:function(){
		var record = funcFn.getSelectedRecord();
		if(record==null)return;
		utils.confirm('${action.text("pub.msg.confirm")}','${action.text("security.func.msg.confirmActive")}',function(){
			utils.post("func_enableFunc_2.html",{funcId:record.funcId},function(){
				funcFn.refresh();
			});
		});
	},
	//禁用功能树
	disable:function(){
		var record = funcFn.getSelectedRecord();
		if(record==null)return;
		var dlg=utils.prompt({
			title:'${action.text("security.func.title.confirmDisable")}',
			msg:'${action.text("security.func.msg.disableMsg")}',
			defaultMsg:record.disableMsg,
			requireMsg:true,
			msgMaxLength:1000,
			yesCallback:function(value){
				utils.post("func_disableFunc_2.html",{funcId:record.funcId,disableMsg:value},function(){
					funcFn.refresh();
					dlg.dialog("close");
				});
			}
		});
	},
	//设置按钮状态
	setButtonState:function(index,record){
		if(record){
			$("a[id^='insert']").linkbutton("enable");
			$("a[id='showEdit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
			if(record.active=='Y'){
				$("a[id='enable']").linkbutton("disable");
				$("a[id='disable']").linkbutton("enable");
			}else{
				$("a[id='enable']").linkbutton("enable");
				$("a[id='disable']").linkbutton("disable");
			}
		}else{
			$("a[id^='insert']").linkbutton("disable");
			$("a[id='showEdit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
			$("a[id='enable']").linkbutton("disable");
			$("a[id='disable']").linkbutton("disable");
		}	
	},
	getSelectedRecord:function(){
		var rows = $('#func_treegrid').treegrid('getSelections');
		var record;
		if(rows.length==1){
			record = rows[0];
		}
		return record;
	},
	splitSelectedPower:function(position,funcKey){
		if(position != "CHILD"){
			funcKey=funcKey.substring(0,funcKey.lastIndexOf("."));
		}
		$("input#funcKey").val(funcKey);
	}
};