<#compress>
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "security/role.js" />
    </script>
</head>
<body>
<div style="padding: 10px;">
	<table  id="copy_role_grid"
		    class="easyui-datagrid"
		    fit="false"
		    fitColumns="true"
		    iconCls= "icon-copy"
		    singleSelect="false"
			url='role_showCopyRoleList_2.html?fromRoleId=${role.roleId}'> 
		${action.text("security.role.copyOtherDataPower")}“${role.name}”
        <thead>
        	<tr>
        		<th rowspan="2" data-options="field:'roleId',width:75,sortable:true,checkbox:true">
                	<span class="txtCenter">${action.text("security.role.roleId")}</span>
                </th>
				<th data-options="field:'name',width:150,sortable:true">
                	<span class="txtCenter">${action.text("security.role.name")}</span>
                </th>
                <th data-options="field:'description',width:300,sortable:true">
                	<span class="txtCenter">${action.text("security.role.description")}</span>
                </th>
            </tr>
            <tr>
                <th><input style="width:150;" class="filterInput" filterName="name"/></th>  
                <th><input style="width:300;" class="filterInput" filterName="description"/></th>  
            </tr>
        </thead>
    </table>
     <input id="copyDataPower" name="copyDataPower" type="checkbox" checked="checked" />${action.getText("security.user.copyDataPower")}
</div>
</body>
</html>
</#compress>