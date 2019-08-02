<#compress>
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "security/user.js" />
    </script>
</head>
<body>
<div style="padding: 5px;overflow: hidden;">
    <input id="userId" type="hidden" value="${userId}" />
    <table  id="userRoleGrid"
    		fitColumns="true"
   			class="easyui-datagrid"
		    iconCls= "icon-save"
		    url="user_listUserRole_2.html?userId=${userId}"
		    data-options="rownumbers:true,onLoadSuccess:userFn.userRoleFridLoadSuccess">
		 <thead> 
        	<tr>
                <th data-options="field:'id',width:100,sortable:true,checkbox:true">
					<span class="txtCenter">${action.text("security.role.roleId")}</span>
				</th>  
                <th data-options="field:'name',width:150,sortable:true">
                	<span class="txtCenter">${action.text("security.role")}</span>
                </th>
                <th data-options="field:'description',width:300,sortable:true">
                	<span class="txtCenter">${action.text("security.role.description")}</span>
                </th>
            </tr>
        </thead>
    </table>
</div>
</body>
</html>
</#compress>