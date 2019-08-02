<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="agentParamsForm" method="post">
		<table width="280px" border="0" cellpadding="0" cellspacing="0" bordercolor="#EEEEEE" >
			<tr height="30" align="left">
			  <th width="80" align="right"><span style="margin-right:5px;font-weight: normal;">${action.text("pub.title.proxyUser")}：</span></th>
			  <td>
			  <input id="currentAgentId" name="currentAgentId" type="hidden" value="${currentAgentId}"/>
			  <input id="currentAgentName" name="currentAgentName" type="hidden" value="${currentAgentName}"/>
			  	<input id="agentUserId" name="agentUserId" style="width:180px;" class="easyui-combogrid" data-options="panelWidth:450,
                																		 idField:'userId',
                																		 textField:'loginName',
                																		 mode: 'remote',
                																		 required:true,
                																		 missingMessage:'${action.text("pub.msg.selectProxyUser")}',
                																		 url:'agent_listCurrentUserAgentTarget_2.html',
                																		 columns:[[
                																			{field:'userId',title:'${action.text("user.userId")}',width:60},  
                    																		{field:'loginName',title:'${action.text("user.name")}',width:60},  
                    																		{field:'realName',title:'${action.text("user.realName")}',width:80} 
            																			 ]],
            																			 fitColumns: true
                																		 " />
			  </td>
		    </tr>
		</table>
	</form>
</div>
</#compress>