<#compress>
<style>
input,textarea {
padding-left: 5px;
height: 20px;
line-height: 20px;
border: 1px solid #CCC;
background-color: #FFF;
}
</style>
<div style="padding: 5px;overflow: hidden;height:260px;">
	<form id="role_form" method="post">
		<table class="tableForm">
			<input id="roleId" name="roleId" type="hidden" value="${role.roleId}"/>
			<tr>
			    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.role.name")}: </span></th>
				<td><input name="name"  value="${role.name}" class="easyui-validatebox" minlength="1" maxlength="16" data-options="required:true,validType:'length[1,16]'" /></td>
			</tr>
			<tr>
			    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.role.description")}: </span></th>
				<td><input name="description"  value="${role.description}" maxlength="60" class="easyui-validatebox" data-options="required:false,validType:'length[0,60]'" /></td>
			</tr>
			
			<tr>
				<th><span class="txtCenter">${action.text("security.role.power")}</span></th>
				<td>
				<div class="easyui-panel" fit="true" style="height:200px;" border="true">
					<ul id="powertree" class="easyui-tree" data-options="animate:true,checkbox:true,cascadeCheck:true,onlyLeafCheck:false,url:'role_loadFuncTreeByRole_5.html?roleId=${role.roleId}',onLoadSuccess:roleFn.treeLoadSuccess" ></ul>
				</div>
				</td>
			</tr>
		</table>
	</form>
</div>
</#compress>