<#compress>
	<input id="userId" type="hidden" value="${userId}" />
    <table  id="agentGrid"
    		fitColumns="true"
   			class="easyui-datagrid"
		    fit="true"
		    url="agent_listMyAgent_2.html?userId=${userId}"
		    data-options="rownumbers:true,onLoadSuccess:userFn.agentFridLoadSuccess">
		 <thead> 
        	<tr>
                <th data-options="field:'userId',width:100,sortable:true,checkbox:true">
					<span class="txtCenter">${action.text("security.user.userId")}</span>
				</th>  
                <th data-options="field:'loginName',width:100,sortable:true">
					<span class="txtCenter">${action.text("security.user.loginName")}</span>
				</th>  
                <th data-options="field:'realName',width:150,sortable:true">
                	<span class="txtCenter">${action.text("security.user.realName")}</span>
                </th>
                <th data-options="field:'roleNames',width:300,sortable:true">
                	<span class="txtCenter">${action.text("security.user.userRole")}</span>
                </th>
            </tr>
        </thead>
    </table>
</#compress>