var supplierManagedGridUtil=null;
var supplierNotManagedGridUtil=null;
$(document).ready(function(){
	supplierManagedGridUtil=utils.grid($('#supplierManaged_grid'));
	supplierManagedGridUtil.initFilters({
		onLoadSuccess:function(){
			var checked = $('#manageAllSupplier').attr('checked');
			if(checked){
				$("a[id='deleteControlledSupplier']").linkbutton("disable");
			}
		}
	});
	supplierNotManagedGridUtil=utils.grid($('#supplierNotManaged_grid'));
	supplierNotManagedGridUtil.initFilters({
		onLoadSuccess:function(){
			var checked = $('#manageAllSupplier').attr('checked');
			if(checked){
				$("a[id='addUncontrolledSupplier']").linkbutton("disable");
			}
		}
	});
});
var supplierDatapowerFn = {
	//清除查询
	clearFilter:function(){
//		var p = $(window.parent.document.getElementsByClassName("tabs-selected"));
		var tab = $('#supplierDatapower_tabs').tabs('getSelected');
		var index = $('#supplierDatapower_tabs').tabs('getTabIndex',tab);
		if(index == 0){
			$('#supplierManaged_grid').datagrid("uncheckAll");
			supplierManagedGridUtil.clearFilter();
		}else if(index == 1){
			$('#supplierNotManaged_grid').datagrid("uncheckAll");
			supplierNotManagedGridUtil.clearFilter();
		}
	},
	//显示预管理零售商界面
	showAddUncontrolledSupplierForm:function(){
		$("#supplierDatapower_tabs").tabs("select",1);
	},
	//删除已管理的零售商
	deleteControlledSupplier:function(){
		var dataIds=supplierManagedGridUtil.getSelectedIdArr("supplId");
		if(dataIds.length==0){
			$.messager.alert('${action.text("pub.msg.prompt")}','${action.text("supplier.msg.selectCancelSupplier")}',"warning");
			return;
		}
		var param=supplierDatapowerFn.createParamter();
		if(param==null)return;
		param.dataIds=dataIds;
		var scr = "";
		if(param.forUserOrRole=='USER'){
			scr = "supplier_deleteUserManagedSupplier_2.html";
		}else{
			scr = "supplier_deleteRoleManagedSupplier_2.html";
		}
		utils.post(scr,param,function(){
			$('#supplierManaged_grid').datagrid("clearSelections");
			supplierManagedGridUtil.refresh();
			supplierNotManagedGridUtil.refresh();
		});
	},
	//添加预管理的零售商
	addUncontrolledSupplier:function(){
		var dataIds=supplierNotManagedGridUtil.getSelectedIdArr("supplId");
		if(dataIds.length==0){
			$.messager.alert('${action.text("pub.msg.prompt")}',
					'${action.text("supplier.msg.selectSupplier")}',"warning");
			return;
		}
		var param=supplierDatapowerFn.createParamter();
		if(param==null)return;
		param.dataIds=dataIds;
		var scr = "";
		if(param.forUserOrRole=='USER'){
			scr = "supplier_insertUserManagedSupplier_2.html";
		}else{
			scr = "supplier_insertRoleManagedSupplier_2.html";
		}
		utils.post(scr,param,function(){
			$('#supplierNotManaged_grid').datagrid("clearSelections");
			supplierManagedGridUtil.refresh();
			supplierNotManagedGridUtil.refresh();
		});
	},
	//创建添加或删除管理对象时向后台传的参数，需要区别是用户数据权限，还是角色数据权限
	createParamter:function(){
		var forUserOrRole='${forUserOrRole}';
		var param={forUserOrRole:forUserOrRole,dataType:'S'};
		if(forUserOrRole=='USER'){
			var user=userGridUtil.getSelectedRecord();
			if(user==null)return null;
			param.userId=user.userId;
		}else if(forUserOrRole=='ROLE'){
			var role=roleGridUtil.getSelectedRecord();
			if(role==null)return null;
			param.roleId=role.roleId;
		}
		return param;
	},
	//设置或取消某角色对零售商为无限制访问的权限
	toogleSuperDatapower:function(checkbox){
		var checked = $('#manageAllSupplier').attr('checked');
		if(checked){
			$.messager.confirm('${action.text("pub.msg.confirm")}','${action.text("supplier.msg.confirmManagedSupplier")}',function(r){
				if(r){
					var role=roleGridUtil.getSelectedRecord();
					if(role==null)return null;
					var param={dataType:'S'};
					param.roleId=role.roleId;
					utils.post("supplier_setSuperDatapower_2.html",param,function(){
						$("a[id='deleteControlledSupplier']").linkbutton("disable");
						$("a[id='addUncontrolledSupplier']").linkbutton("disable");
						supplierManagedGridUtil.refresh();
						supplierNotManagedGridUtil.refresh();
					});
				}else{
					$('#manageAllSupplier').attr('checked',null);
				}
			});
		}else{
			$.messager.confirm('${action.text("pub.msg.confirm")}','${action.text("supplier.msg.deleteManagedSupplier")}',function(r){
				if(r){
					var role=roleGridUtil.getSelectedRecord();
					if(role==null)return null;
					var param={dataType:'S'};
					param.roleId=role.roleId;
					utils.post("supplier_delSuperDatapower_2.html",param,function(){
						$("a[id='deleteControlledSupplier']").linkbutton("enable");
						$("a[id='addUncontrolledSupplier']").linkbutton("enable");
						supplierManagedGridUtil.refresh();
						supplierNotManagedGridUtil.refresh();
					});
				}else{
					$('#manageAllSupplier').attr('checked','checked');
				}
			});
		}
	}
};