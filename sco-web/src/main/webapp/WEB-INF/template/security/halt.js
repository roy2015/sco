var haltGridUtil=null;
var nonHaltGridUtil=null;
$(document).ready(function(){
	haltGridUtil=utils.grid($('#halt_grid'));
	haltGridUtil.initFilters({
		onLoadSuccess:function(data){
			haltFn.setButtonState();
		},
		onClickRow:function(rowIndex, rowData){
			haltFn.setButtonState(rowIndex,rowData);
		},
		view:detailview,
		detailFormatter:function(index,row){
			return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
		},
		onExpandRow: function(index,row){
			$('#ddv-'+index).datagrid({
				url:'halt_listHaltUser_2.html?haltId='+row.haltId,
				fitColumns:true,
				idField:"userId",
				singleSelect:true,
				rownumbers:true,
				loadMsg:'',
				height:'auto',
				toolbar:[{
					iconCls: 'icon-add',
					text:'${action.getText("common.button.add")}',
					handler: function(){//显示添加运维用户界面
						var title='${action.getText("common.title.add.user")}';
						var href='halt_showInsertHaltUserForm_1.html?haltId='+row.haltId;
						var dlg=utils.showDlg({
							title:title,href:href,width:600,height:350,
							buttons:[{
								text:'${action.getText("common.button.add")}',
								handler:function(){// 添加运维用户
									var userIds = nonHaltGridUtil.getSelectedIdArr("userId");
									if(userIds==null||userIds.length<=0)return;
									utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.halt.msg.confirmInsert")}',function(){
										utils.post("halt_insertHaltUser_2.html",{haltId:row.haltId,userIds:userIds},function(){
											dlg.dialog('close');
											$('#ddv-'+index).datagrid("load");
										});
									});
								},
								iconCls:'add'
							},{
								text:'${action.getText("common.button.cancel")}',
								handler:function(){dlg.dialog('close');},
								iconCls:'cancel'
							}],onLoad:function(){
								nonHaltGridUtil=utils.grid($('#nonHaltUser_grid'));
								nonHaltGridUtil.initFilters({
									onLoadSuccess:function(){
										$('#nonHaltUser_grid').datagrid("clearSelections");
									}
								});
							}
						});
					}
				},{
					iconCls: 'icon-remove',
					text:'${action.getText("common.button.delete")}',
					handler: function(){//删除运维用户
						var viewGridUtil=utils.grid($('#ddv-'+index));
						var record = viewGridUtil.getSelectedRecord();
						if(record==null)return;
						utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.halt.msg.confirmDelete")}',function(){
							utils.post("halt_deleteHaltUser_2.html",{haltId:row.haltId,userId:record.userId},function(){
								$('#ddv-'+index).datagrid("load");
							});
						});
					}
				}],
				columns:[[
				          {field:'userId',title:'${action.text("security.halt.userId")}',width:80,hidden:true},
				          {field:'loginName',title:'${action.text("security.halt.loginName")}',width:80},
				          {field:'realName',title:'${action.text("security.halt.realName")}',width:80},
				          {field:'roleNames',title:'${action.text("security.halt.userRole")}',width:200}
				]],
				onResize:function(){
					$('#halt_grid').datagrid('fixDetailRowHeight',index);
				},
				onLoadSuccess:function(){
					setTimeout(function(){
						$('#halt_grid').datagrid('fixDetailRowHeight',index);
					},100);
				}
			});
			$('#halt_grid').datagrid('fixDetailRowHeight',index);
		}
	});
});
var haltFn={
		//显示添加或修改窗体
		showForm:function(isEdit){
			var title='${action.getText("common.title.add",action.getText("security.halt"))}';
			var href='halt_showInsertHaltForm_1.html';
			if(isEdit){//显示修改窗体
				var record=haltGridUtil.getSelectedRecord();
				if(record==null)return;
				title='${action.getText("common.title.edit",action.getText("security.halt"))}';
				href='halt_showUpdateHaltForm_1.html?haltId='+record.haltId;
			}
			var dlg=utils.showDlg({
				title:title,href:href,width:600,height:350,
				buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){haltFn.submitForm(dlg);},iconCls:'save'},
				        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				]
			});
		},
		//添加停机通知管理
		showInsert:function() {
			haltFn.showForm(false);
		},
		//修改停机通知管理
		showEdit:function(){
			haltFn.showForm(true);
		},
		//提交新增或修改表单
		submitForm:function(dlg){
			var haltId = $("form#halt_form input#haltId").val();
			var state = $("form#halt_form input#state").val();
			var href = "halt_insertHalt_2.html";
			if(haltId){
				href = "halt_updateHalt_2.html?state="+state;
			}
			utils.form("halt_form").submit(href,null,function(){
				dlg.dialog('close');
				haltGridUtil.refresh();
			});
		},
		//开机
		turnOn:function(){
			var record=haltGridUtil.getSelectedRecord();
			if(record==null)return;
			utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.halt.msg.confirmStartServer")}',function(){
				utils.post("halt_turnOn_2.html",{haltId:record.haltId},function(){
					haltGridUtil.refresh();
				});
			});
		},
		//停机
		turnOff:function(){
			var record=haltGridUtil.getSelectedRecord();
			if(record==null)return;
			utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.halt.msg.confirmTurnOff")}',function(){
				utils.post("halt_turnOff_2.html",{haltId:record.haltId},function(){
					haltGridUtil.refresh();
				});
			});
		},
		//删除停机通知管理
		remove:function(){
			var record=haltGridUtil.getSelectedRecord();
			if(record==null)return;
			utils.confirm('${action.getText("pub.msg.confirm")}','${action.getText("security.halt.msg.confirmDeleteNotice")}',function(){
				utils.post("halt_deleteHalt_2.html",{haltId:record.haltId},function(){
					haltGridUtil.refresh();
				});
			});
		},
		//清除查询
		clearFilter:function(){
			haltGridUtil.clearFilter();
		},
		//设置按钮状态
		setButtonState:function(index,record){
			if(record){
				$("a[id='showEdit']").linkbutton("enable");
				$("a[id='remove']").linkbutton("enable");
				if(record.state=='Y'){
					$("a[id='turnOn']").linkbutton("disable");
					$("a[id='turnOff']").linkbutton("enable");
				}else{
					$("a[id='turnOn']").linkbutton("enable");
					$("a[id='turnOff']").linkbutton("disable");
				}
			}else{
				$("a[id='showEdit']").linkbutton("disable");
				$("a[id='remove']").linkbutton("disable");
				$("a[id='turnOn']").linkbutton("disable");
				$("a[id='turnOff']").linkbutton("disable");
			}	
		}
};