<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "security/onlineUser.js" />
    </script>
</head>
<body>
	<!-- 在线用户基础信息管理主表 --> 
	<div id="onlineUser_toolbar">
		<a onclick="onlineUserFn.kickOff();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" style="display:<#if !action.hasFuncPower("com.escm.security.systemManager.online.kickOff")>none</#if>;">
			${action.getText("common.button.kickOff")}
		</a>
		<a onclick="onlineUserFn.sendMessage();"plain="true" class="easyui-linkbutton" data-options="iconCls:''" style="display:<#if !action.hasFuncPower("com.escm.security.systemManager.online.sendMessage")>none</#if>;">
			${action.getText("common.button.sendMessage")}
		</a>
		<a onclick="onlineUserFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
	</div>
    <table  id="onlineUser_grid"
		    class="easyui-datagrid"
		    fit="true"
		    iconCls= "icon-save"
		    singleSelect="true"
			toolbar="#onlineUser_toolbar"
			url='onlineUser_listOnlineUser_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th data-options="field:'loginName',width:150,sortable:false">
                	<span class="txtCenter">${action.text("security.onlineUser.loginName")}</span>
                </th>
				<th data-options="field:'realName',width:150,sortable:false">
                	<span class="txtCenter">${action.text("security.onlineUser.realName")}</span>
                </th>
				<th rowspan=2  data-options="field:'objectName',width:200,sortable:false">
                	<span class="txtCenter">${action.text("security.user.objectId")}</span>
                </th>
                <th rowspan=2  data-options="field:'objectTyp',width:200,sortable:false,formatter:Dic.formatter('OrgTypes')">
                	<span class="txtCenter">${action.text("security.user.objectTyp")}</span>
                </th>
				<th rowspan=2 data-options="field:'email',width:150,sortable:false">
                	<span class="txtCenter">${action.text("security.user.email")}</span>
                </th>
				<th rowspan=2 data-options="field:'phone',width:100,sortable:false">
                	<span class="txtCenter">${action.text("security.user.phone")}</span>
                </th>
				<th rowspan=2 data-options="field:'mobile',width:100,sortable:false">
                	<span class="txtCenter">${action.text("security.user.mobile")}</span>
                </th>
                <th rowspan="2" data-options="field:'ipAddress',width:150,sortable:false">
                	<span class="txtCenter">${action.text("security.onlineUser.ip")}</span>
                </th>
                <th rowspan="2" data-options="field:'loginTime',width:150,sortable:false">
                	<span class="txtCenter">${action.text("security.onlineUser.loginTime")}</span>
                </th>
            </tr>
            <!--<tr>
                <th><input style="width:60px;" class="filterInput" filterName="loginName"/></th>  
                <th><input style="width:60px;" class="filterInput" filterName="realName"/></th>  
            </tr>-->
        </thead>
    </table>
</body>
</html>
</#compress>