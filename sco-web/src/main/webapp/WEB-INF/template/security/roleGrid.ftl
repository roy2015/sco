<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "security/role.js" />
    </script>
</head>
<body>
	<!-- 角色基础信息管理主表 --> 
	<div id="role_toolbar">
		<a id="insert" onclick="roleFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.escm.security.role.insert")>none</#if>;">
			${action.getText("common.button.add")}
		</a>
		<a id="showEdit" onclick="roleFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.role.update")>none</#if>;">
			${action.getText("role.button.edit")}
		</a>
		<a id="remove" onclick="roleFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.role.delete")>none</#if>;">
			${action.getText("common.button.delete")}
		</a>
		<a onclick="roleFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
		<!--<a id="enable" onclick="roleFn.enable();"plain="true" class="easyui-linkbutton" data-options="iconCls:'active',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.role.enable")>none</#if>;">
			${action.getText("common.button.active")}
		</a>!
		<a id="disable" onclick="roleFn.disable();"plain="true" class="easyui-linkbutton" data-options="iconCls:'close',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.role.disable")>none</#if>;">
			${action.getText("common.button.disable")}
		</a>
		<a id="copyRolePower" onclick="roleFn.showCopyRolePower();"plain="true" class="easyui-linkbutton" data-options="iconCls:'copy',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.role.copyPowerByRole")>none</#if>;">
			${action.getText("common.button.copy")}
		</a>-->
		<a id="dataPowerMenu" href="#" class = "easyui-menubutton" data-options="menu:'#dataPowerMenuItems',iconCls:'privilege'" style="display:<#if !action.hasFuncPower("com.escm.role.datapower")>none</#if>;">${action.getText("public.button.datePowerManage")}</a>
		<div id="dataPowerMenuItems">
			<#list dataPowerList as dp>
				<#if action.hasFuncPower("${dp.funcKey}")>
					<div onclick="roleFn.showDataPowerDlg('${dp.dataTypeId}','${dp.name}','${dp.src}');" data-options="iconCls:'${dp.iconCls}'"+>${dp.name}</div>
				</#if>
			</#list>
		</div>
	</div>
    <table  id="role_grid"
		    class="easyui-datagrid"
		    fit="true"
		    singleSelect="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#role_toolbar"
			url='role_listRole_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        	<!--<th rowspan="2" data-options="field:'roleId',width:75,sortable:true">
                	<span class="txtCenter">${action.text("security.role.roleId")}</span>
                </th>
                <th data-options="field:'active',width:75,sortable:true,formatter:function(value,rowData){
                		if(value=='Y')return '<div class=\'active\'>&nbsp</div>';
                		if(value=='N')return '<div class=\'noactive\'>&nbsp</div>';
                		return value;
                	}">
                	<!--formatter:Dic.formatter('Active')
                	 <th>
	                <select class="filterSelect" panelHeight="auto" filterName="active">
	                		<option value="">${action.text("public.all")}</option>
							<option value="Y">${action.text("security.role.active.Y")}</option>
							<option value="N">${action.text("security.role.active.N")}</option>
					</select>
                </th> 
                	<span class="txtCenter">${action.text("security.role.active")}</span>
                </th>
                  <th rowspan="2" data-options="field:'created',width:128,sortable:true">
                	<span class="txtCenter">${action.text("public.property.created")}</span>
                </th>
                <th rowspan="2" data-options="field:'createUserName',width:75,sortable:true">
                	<span class="txtCenter">${action.text("public.property.createby")}</span>
                </th>
                <th rowspan="2" data-options="field:'updated',width:128,sortable:true">
                	<span class="txtCenter">${action.text("public.property.updated")}</span>
                </th>
                <th rowspan="2" data-options="field:'updateUserName',width:75,sortable:true">
                	<span class="txtCenter">${action.text("public.property.updateby")}</span>
                </th>
                	-->
				<th data-options="field:'name',width:300,sortable:true">
                	<span class="txtCenter">${action.text("security.role.name")}</span>
                </th>
				<th data-options="field:'description',width:360,sortable:true">
                	<span class="txtCenter">${action.text("security.role.description")}</span>
                </th>
            </tr>
            <tr>
                <th><input style="width:250px;" class="filterInput" filterName="name"/></th>  
                <th><input style="width:350px;" class="filterInput" filterName="description"/></th>  
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>