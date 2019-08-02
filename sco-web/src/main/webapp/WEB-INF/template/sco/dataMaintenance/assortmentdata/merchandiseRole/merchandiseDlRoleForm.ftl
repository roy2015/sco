<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="merchandiseDlRole_form" method="post" onkeydown="if(event.keyCode==13)return false;">
		<table class="tableForm">
			<input name="roleCode" id="roleCode" type="hidden" readonly="readonly" value="${merchandiseDlRole.roleCode}"/>
					<tr>
					    <th align="right" style="width: 80px;"><label style="color:red;">*</label><span class="txtCenter">角色名称: </span></th>
						
						<td><input name="roleName"  value="${merchandiseDlRole.roleName}" class="easyui-validatebox" style="width:120px;" data-options="required:false,validType:'length[0,33]'" /></td>
					</tr>
		</table>
	</form>
</div>
</#compress>