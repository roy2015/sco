<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="retailer_form" method="post">
		<table class="tableForm">
			<input id="retailerId" name="retailerId" type="hidden"  readonly="readonly" value="${retailer.retailerId}"/>
					<tr>
					    <th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.retailer.nameCn")}: </span></th>
						<td><input name="nameCn"  value="${retailer.nameCn}" class="easyui-validatebox" data-options="required:true,validType:'length[1,60]'" /></td>
					 	<th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.retailer.nameEn")}: </span></th>
						<td>
							<input name="nameEn"  value="${retailer.nameEn}" class="easyui-validatebox" data-options="required:false,validType:'length[0,60]'" />
						</td>
						<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.retailer.representive")}: </span></th>
						<td><input name="representive"  value="${retailer.representive}" class="easyui-validatebox" data-options="required:false,validType:'length[0,40]'" /></td>
						 <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.retailer.email")}: </span></th>
						<td><input name="email"  value="${retailer.email}" class="easyui-validatebox" data-options="required:false,validType:['length[0,64]','email']" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.retailer.phone")}: </span></th>
						<td><input name="phone"  value="${retailer.phone}" class="easyui-validatebox" data-options="required:false,validType:'length[0,40]'" /></td>
					   </tr>
					<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.retailer.address")}: </span></th>
						<td colspan='3'><input style="height:50px;width: 467px;" name="address" value="${retailer.address}" class="easyui-validatebox" data-options="required:false,validType:'length[0,200]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.retailer.description")}: </span></th>
						<td colspan='3'><textarea style="width: 465px;height: 60px;" name="description" class="easyui-validatebox" data-options="required:false,validType:'length[0,2000]'">${retailer.description}</textarea></td>
					</tr>
					
		</table>
	</form>
</div>
</#compress>