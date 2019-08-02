<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "security/user.js" />
    </script>
</head>
<body>
	<!-- 用户基础信息管理主表 --> 
	<div id="user_toolbar">
		<a id="showInsert" onclick="userFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.escm.security.user.insert")>none</#if>;">
			${action.getText("common.button.add")}
		</a>
		<a id="showBatchInsert" onclick="userFn.showBatchInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.escm.security.user.batch.insert")>none</#if>;">
			${action.getText("user.button.batchAdd")}
		</a>
		<a id="showEdit" onclick="userFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'" style="display:<#if !action.hasFuncPower("com.escm.security.user.update")>none</#if>;">
			${action.getText("common.button.edit")}
		</a>
		<a id="enable" onclick="userFn.enable();"plain="true" class="easyui-linkbutton" data-options="iconCls:'active'" style="display:<#if !action.hasFuncPower("com.escm.security.user.enable")>none</#if>;">
			${action.getText("common.button.active")}
		</a>
		<a id="disable" onclick="userFn.disable();"plain="true" class="easyui-linkbutton" data-options="iconCls:'close'" style="display:<#if !action.hasFuncPower("com.escm.security.user.disable")>none</#if>;">
			${action.getText("common.button.disable")}
		</a>
		<a id="setUserRole" onclick="userFn.setUserRole();"plain="true" class="easyui-linkbutton" data-options="iconCls:'role'" style="display:<#if !action.hasFuncPower("com.escm.security.user.setRoles")>none</#if>;">
			${action.getText("user.button.setUserRole")}
		</a>
		<a id="copyPrivilege" onclick="userFn.showCopyUserPower();"plain="true" class="easyui-linkbutton" data-options="iconCls:'copy'" style="display:<#if !action.hasFuncPower("com.escm.security.user.copyPowers")>none</#if>;">
			${action.getText("user.button.copyPower")}
		</a>
		<a id="resetPassword" onclick="userFn.showResetPassword();"plain="true" class="easyui-linkbutton" data-options="iconCls:'resetpwd'" style="display:<#if !action.hasFuncPower("com.escm.security.user.resetPassword")>none</#if>;">
			${action.getText("user.button.resetPassword")}
		</a>
		<a id="setAgent" onclick="userFn.setAgent();"plain="true" class="easyui-linkbutton" data-options="iconCls:'agent'" style="display:<#if !action.hasFuncPower("com.escm.security.user.setAgent")>none</#if>;">
			${action.getText("user.button.setAgent")}
		</a>
		<a id="clearFilter" onclick="userFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
		<a id="dataPowerMenu" href="#" class = "easyui-menubutton" data-options="menu:'#dataPowerMenuItems',iconCls:'privilege'" style="display:<#if !action.hasFuncPower("com.escm.user.datapower")>none</#if>;">${action.getText("public.button.datePowerManage")}</a>
		<a id="export" onclick="userFn.exportUser();"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.escm.security.user.export")>none</#if>;">
			${action.getText("user.button.export")}
		</a>
		<div id="dataPowerMenuItems">
			<#list dataPowerList as dp>
				<div onclick="userFn.showDataPowerDlg('${dp.dataTypeId}','${dp.name}','${dp.src}');" data-options="iconCls:'${dp.iconCls}'"+>${dp.name}</div>
			</#list>
		</div>
	</div>
    <table  id="user_grid"
		    class="easyui-datagrid"
		    fit="true"
		    iconCls="icon-save"
		    singleSelect="true"
			pagination="true"
			pagePosition='bottom'
			pageSize="20"
			pageList="[ 10, 20, 30, 40 ]"
			toolbar="#user_toolbar"
			url='user_listUser_2.html'
		    data-options="rownumbers:true">  
        <thead data-options="frozen:true">
        	<tr>
        		<th data-options="field:'userId',width:180,sortable:true">
                	<span class="txtCenter">${action.text("security.user.userId")}</span>
                </th>
				<th data-options="field:'loginName',width:180,sortable:true">
                	<span class="txtCenter">${action.text("security.user.loginName")}</span>
                </th>
				<th data-options="field:'realName',width:180,sortable:true">
                	<span class="txtCenter">${action.text("security.user.realName")}</span>
                </th>
                <th data-options="field:'active',width:175,sortable:true,
                	formatter:function(value,r){
                		if(value=='Y')return '<div class=\'active\'>&nbsp</div>';
                		if(value=='N')return '<div title=\''+r.disableMsg+'\' class=\'noactive\'>&nbsp</div>';
                		return value;
                	}
                ">
                	<span class="txtCenter">${action.text("security.user.active")}</span>
                </th>
            </tr>
        </thead>
        <thead>
            <tr>
                <th data-options="field:'objectTyp',width:80,sortable:true,formatter:Dic.formatter('OrgTypes'),hidden:true">
                	<span class="txtCenter">${action.text("security.user.objectTyp")}</span>
                </th>
                <th data-options="field:'objectName',width:200,sortable:true,hidden:true">
                	<span class="txtCenter">${action.text("security.user.objectId")}</span>
                </th>
                <th rowspan=2 data-options="field:'roleNames',width:200">
                	<span class="txtCenter">${action.text("security.user.userRole")}</span>
                </th>
				<th rowspan=2 data-options="field:'email',width:150,sortable:true">
                	<span class="txtCenter">${action.text("security.user.email")}</span>
                </th>
				<th rowspan=2 data-options="field:'phone',width:100,sortable:true">
                	<span class="txtCenter">${action.text("security.user.phone")}</span>
                </th>
				<th rowspan=2 data-options="field:'mobile',width:100,sortable:true">
                	<span class="txtCenter">${action.text("security.user.mobile")}</span>
                </th>
				<th rowspan=2 data-options="field:'description',width:300,sortable:true">
                	<span class="txtCenter">${action.text("security.user.description")}</span>
                </th>
				<th rowspan=2 data-options="field:'lastlogin',width:80,sortable:true,
														formatter:function(value,row) {
                				  		 					if(value!=null) return value.substring(0,10);
                				  					   }">
                	<span class="txtCenter">${action.text("security.user.lastlogin")}</span>
                </th>
              <!--
                <th rowspan=2 data-options="field:'createUserName',width:80,sortable:true">
                	<span class="txtCenter">${action.text("public.property.createby")}</span>
                </th>
				<th rowspan=2 data-options="field:'created',width:150,sortable:true">
                	<span class="txtCenter">${action.text("public.property.created")}</span>
                </th>
                <th rowspan=2 data-options="field:'updateUserName',width:80,sortable:true">
                	<span class="txtCenter">${action.text("public.property.updateby")}</span>
                </th>
				<th rowspan=2 data-options="field:'updated',width:150,sortable:true">
                	<span class="txtCenter">${action.text("public.property.updated")}</span>
                </th>
             -->
            </tr>
        </thead>
        <thead data-options="frozen:true">
            <tr>
                <th><input style="width:60px;" class="filterInput" filterName="userId"/></th>  
                <th><input style="width:60px;" class="filterInput" filterName="loginName"/></th>  
                <th><input style="width:60px;" class="filterInput" filterName="realName"/></th>  
                <th><select class="filterSelect" panelHeight="auto" filterName="active">
                		<option value="">${action.text("public.all")}</option>
						<option value="Y">${action.text("security.user.active.Y")}</option>
						<option value="N">${action.text("security.user.active.N")}</option>
				</select></th>
			</tr>
		</thead>
        <#-- <thead>
        	<tr>
				<th><select style="width:60px;" class="filterSelect" panelHeight="auto" filterName="objectTyp" data-options="data:[{id:'',text:'全部'}].concat(Dic.OrgTypes),valueField:'id'" /></th>
                <th><input style="width:150px;" class="filterInput" filterName="objectName"/></th>  
            </tr>
        </thead> -->
    </table>
<div id="orgDemo"/>
</body>
</html>
</#compress>