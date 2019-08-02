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
	<table  id="copy_user_grid"
		    class="easyui-datagrid"
		    fit="false"
		    fitColumns="true"
		    iconCls= "icon-copy"
		    singleSelect="false"
		    url='user_showCopyPowerList_2.html?fromUserId=${user.userId}'
			> 
		${action.text("security.user.copyOtherDataPower")}“${user.loginName}”
        <thead>
        	<tr>
        		<th rowspan="2" data-options="field:'userId',width:75,sortable:true,checkbox:true">
                	<span class="txtCenter">${action.text("security.user.userId")}</span>
                </th>
				<th data-options="field:'loginName',width:150,sortable:true">
                	<span class="txtCenter">${action.text("security.user.loginName")}</span>
                </th>
				<th data-options="field:'realName',width:300,sortable:true">
                	<span class="txtCenter">${action.text("security.user.realName")}</span>
                </th>
            </tr>
            <tr>
                <th><input style="width:150;" class="filterInput" filterName="loginName"/></th>  
                <th><input style="width:300;" class="filterInput" filterName="realName"/></th>   
            </tr>
        </thead>
    </table>
     <input id="copyDataPower" name="copyDataPower" type="checkbox" checked="checked" />${action.getText("security.user.copyDataPower")}
</div>
</body>
</html>
</#compress>