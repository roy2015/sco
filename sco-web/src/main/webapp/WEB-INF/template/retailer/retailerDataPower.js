var retailerManagedGridUtil=null;
var retailerNotManagedGridUtil=null;
$(document).ready(function(){
	retailerManagedGridUtil=utils.grid($('#retailerManaged_grid'));
	retailerManagedGridUtil.initFilters({
		onLoadSuccess:function(){
			var checked = $('#manageAllRetailer').attr('checked');
			if(checked){
				$("a[id='deleteControlledRetailer']").linkbutton("disable");
			}
		}
	});
	retailerNotManagedGridUtil=utils.grid($('#retailerNotManaged_grid'));
	retailerNotManagedGridUtil.initFilters({
		onLoadSuccess:function(){
			var checked = $('#manageAllRetailer').attr('checked');
			if(checked){
				$("a[id='addUncontrolledRetailer']").linkbutton("disable");
			}
		}
	});
});
var retailerDatapowerFn = {
	//清除查询
	clearFilter:function(){
		retailerManagedGridUtil.clearFilter();
		retailerNotManagedGridUtil.clearFilter();
	},
	//显示预管理零售商界面
	showAddUncontrolledRetailerForm:function(){
		$("#retailerDatapower_tabs").tabs("select",1);
	},
	//删除已管理的零售商
	deleteControlledRetailer:function(){
		var dataIds=retailerManagedGridUtil.getSelectedIdArr("retailerId");
		if(dataIds.length==0){
			$.messager.alert('${action.text("pub.msg.prompt")}',
					'${action.text("retailer.msg.selectCancelReatiler")}',"warning");
			return;
		}
		var param=retailerDatapowerFn.createParamter();
		if(param==null)return;
		param.dataIds=dataIds;
		var scr = "";
		if(param.forUserOrRole=='USER'){
			scr = "retailer_deleteUserManagedRetailer_2.html";
		}else{
			scr = "retailer_deleteRoleManagedRetailer_2.html";
		}
		utils.post(scr,param,function(){
			$('#retailerManaged_grid').datagrid("clearSelections");
			retailerManagedGridUtil.refresh();
			retailerNotManagedGridUtil.refresh();
		});
	},
	//添加预管理的零售商
	addUncontrolledRetailer:function(){
		var dataIds=retailerNotManagedGridUtil.getSelectedIdArr("retailerId");
		if(dataIds.length==0){
			$.messager.alert('${action.text("pub.msg.prompt")}',
					'${action.text("retailer.msg.selectReatiler")}',"warning");
			return;
		}
		var param=retailerDatapowerFn.createParamter();
		if(param==null)return;
		param.dataIds=dataIds;
		var scr = "";
		if(param.forUserOrRole=='USER'){
			scr = "retailer_insertUserManagedRetailer_2.html";
		}else{
			scr = "retailer_insertRoleManagedRetailer_2.html";
		}
		utils.post(scr,param,function(){
			$('#retailerNotManaged_grid').datagrid("clearSelections");
			retailerManagedGridUtil.refresh();
			retailerNotManagedGridUtil.refresh();
		});
	},
	//创建添加或删除管理对象时向后台传的参数，需要区别是用户数据权限，还是角色数据权限
	createParamter:function(){
		var forUserOrRole='${forUserOrRole}';
		var param={forUserOrRole:forUserOrRole,dataType:'R'};
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
		var checked = $('#manageAllRetailer').attr('checked');
		if(checked){
			$.messager.confirm('${action.text("pub.msg.confirm")}','${action.text("retailer.msg.confirmManagedReatiler")}',function(r){
				if(r){
					var role=roleGridUtil.getSelectedRecord();
					if(role==null)return null;
					var param={dataType:'R'};
					param.roleId=role.roleId;
					utils.post("retailer_setSuperDatapower_2.html",param,function(){
						$("a[id='deleteControlledRetailer']").linkbutton("disable");
						$("a[id='addUncontrolledRetailer']").linkbutton("disable");
						retailerManagedGridUtil.refresh();
						retailerNotManagedGridUtil.refresh();
					});
				}else{
					$('#manageAllRetailer').attr('checked',null);
				}
			});
		}else{
			$.messager.confirm('${action.text("pub.msg.confirm")}','${action.text("retailer.msg.deleteManagedReatiler")}',function(r){
				if(r){
					var role=roleGridUtil.getSelectedRecord();
					if(role==null)return null;
					var param={dataType:'R'};
					param.roleId=role.roleId;
					utils.post("retailer_delSuperDatapower_2.html",param,function(){
						$("a[id='deleteControlledRetailer']").linkbutton("enable");
						$("a[id='addUncontrolledRetailer']").linkbutton("enable");
						retailerManagedGridUtil.refresh();
						retailerNotManagedGridUtil.refresh();
					});
				}else{
					$('#manageAllRetailer').attr('checked','checked');
				}
			});
		}
	}
};