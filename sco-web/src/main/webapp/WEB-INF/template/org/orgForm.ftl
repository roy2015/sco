<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="org_form" method="post">
		<table class="tableForm">
			<input id="orgId" type="hidden" name="orgId"  value="${org.orgId}"/>
			<input name="orgPid" type="hidden" value="${org.orgPid}"/>
			<tr>
				<th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.org.parentName")}: </span></th>
				<td>
				<#if test="${org.parent??}">
					<input value='' class="easyui-validatebox" disabled="disabled" />
				<#else>
					<input value='${org.parent.name}' class="easyui-validatebox" disabled="disabled" />
				</#if>
				</td>
			    <th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.org.name")}: </span></th>
				<td><input name="name"  value="${org.name}" class="easyui-validatebox" data-options="required:true,validType:'length[0,56]'" /></td>
			</tr>
			<tr>
			    <th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.org.orgCode")}: </span></th>
				<td><input name="orgCode"  value="${org.orgCode}" class="easyui-validatebox" data-options="required:true,validType:'length[0,500]'" /></td>
			    <th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.org.contact")}: </span></th>
			    <td><input name="contact"  value="${org.contact}" class="easyui-validatebox" data-options="required:false,validType:'length[0,256]'" /></td>
			</tr>
			<tr>
			    <th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.org.mobile")}: </span></th>
				<td><input name="mobile"  value="${org.mobile}" class="easyui-validatebox" data-options="required:false,validType:'length[0,30]'" /></td>
			    <th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.org.phone")}: </span></th>
				<td><input name="phone"  value="${org.phone}" class="easyui-validatebox" data-options="required:false,validType:'length[0,40]'" /></td>
			</tr>
			<tr>
			    <th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.org.fax")}: </span></th>
				<td><input name="fax"  value="${org.fax}" class="easyui-validatebox" data-options="required:false,validType:'length[0,30]'" /></td>
			</tr>
			<tr>
				<th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.org.address")}: </span></th>
				<td colspan="3"><input name="address" style="width:452px" value="${org.address}" class="easyui-validatebox" data-options="required:false,validType:'length[0,256]'" /></td>
			</tr>
			<tr>
				<th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.org.description")}: </span></th>
				<td colspan="3"><textarea name="description" style="font-size:13px;width:450px" rowspan="3" class="easyui-validatebox" data-options="required:false,validType:'length[0,512]'">${org.description}</textarea></td>
			</tr>
		</table>
	</form>
</div>
</#compress>