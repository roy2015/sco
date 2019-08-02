<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="supplier_form" method="post">
		<table class="tableForm">
			<input id="supplId" name="supplId" type="hidden"  readonly="readonly" value="${supplier.supplId}"/>
					<tr>
					    <th align="right" style="width: 60px;">
					    <span class="txtCenter">${action.getText("security.supplier.supplNo")}: </span></th>
						<td><input name="supplNo" style="width:120px"  value="${supplier.supplNo}" class="easyui-validatebox" data-options="required:true,validType:'length[1,24]'" /></td>
					    <th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.supplier.supplName")}: </span></th>
						
						<td><input name="supplName" style="width: 180px;"  value="${supplier.supplName}" class="easyui-validatebox" data-options="required:true,validType:'length[1,60]'" /></td>
					</tr>
					<tr>
					 	<th align="right" style="width: 30px;"><span class="txtCenter">${action.getText("security.supplier.supplMaster")}: </span></th>
						<td>
						    <select class="easyui-combobox" editable="false" data-options="required:true" panelHeight="auto" style="width:124px;" name="supplMaster">
								<option value="N">${action.text("security.supplier.supplMaster.N")}</option>
								<option <#if supplier.supplMaster=='Y'>selected</#if> value="Y">${action.text("security.supplier.supplMaster.Y")}</option>
							</select>
						</td>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.supplier.contact")}: </span></th>
						
						<td><input name="contact" style="width: 180px;" value="${supplier.contact}" class="easyui-validatebox" data-options="required:false,validType:'length[0,40]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.supplier.phone")}: </span></th>
						<td><input name="phone" style="width: 120px;"  value="${supplier.phone}" class="easyui-validatebox" data-options="required:false,validType:'length[0,64]'" /></td>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.supplier.email")}: </span></th>
						
						<td><input name="email" style="width: 180px;"  value="${supplier.email}" class="easyui-validatebox" data-options="required:false,validType:['length[0,24]','email']" /></td>
					</tr>
					
					<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.supplier.address")}: </span></th>
						<td colspan='3'><input name="address" style="height:50px;width: 424px;"  value="${supplier.address}" class="easyui-validatebox" data-options="required:false,validType:'length[0,200]'" /></td>
					</tr>
					
					<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.supplier.description")}: </span></th>
						<td colspan='3'><textarea name="description" style="height:60px;width: 423px;" class="easyui-validatebox" data-options="required:false,validType:'length[0,2000]'">${supplier.description}</textarea></td>
					</tr>
					
		</table>
	</form>
</div>
</#compress>