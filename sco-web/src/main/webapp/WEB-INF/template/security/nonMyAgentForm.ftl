<#compress>
<div id="setAgentTitleDiv">
	<form id="setAgentTitleForm" method="post" enctype="multipart/form-data">
		<table class="r-table" style="margin-top:3px">
			<tr>
				<td style="text-align:right">StartTime:</td>
				<td><input id="startTime" name="startTime"  class="easyui-datetimebox r-bginput" style="width:140px" data-options="required:false"></td>
				
				<td style="text-align:right">EndTime:</td>
				<td><input id="endTime" name="endTime"  class="easyui-datetimebox r-bginput" style="width:140px" data-options="required:false"></td>
				
				<td><a onclick="userFn.setAgentSave();"plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
						${action.getText("common.button.save")}
					</a>
				</td>
			</tr>
		</table>
	</form>
</div>
	<input id="userId" type="hidden" value="${userId}" />
    <table  id="nonAgentGrid"
    		fitColumns="true"
   			class="easyui-datagrid"
   			singleSelect="true"
		    fit="true"
		    pagination="true"
			pagePosition='bottom'
			pageSize="20"
			pageList="[ 10, 20, 30, 40 ]"
			border="false"
			toolbar="#setAgentTitleDiv"
		    url="agent_listNonMyAgent_2.html?userId=${userId}"
		    data-options="rownumbers:true,onLoadSuccess:userFn.agentFridLoadSuccess">
		 <thead> 
        	<tr>
                <!--<th data-options="field:'userId',width:100,sortable:true">
					<span class="txtCenter">${action.text("security.user.userId")}</span>
				</th>  -->
                <th data-options="field:'loginName',width:100,sortable:false">
					<span class="txtCenter">${action.text("security.user.loginName")}</span>
				</th>  
                <th data-options="field:'realName',width:150,sortable:false">
                	<span class="txtCenter">${action.text("security.user.realName")}</span>
                </th>
                <th data-options="field:'roleNames',width:300,sortable:false">
                	<span class="txtCenter">${action.text("security.user.userRole")}</span>
                </th>
            </tr>
        </thead>
    </table>
</#compress>