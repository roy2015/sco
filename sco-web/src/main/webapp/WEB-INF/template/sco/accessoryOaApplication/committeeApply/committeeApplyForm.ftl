<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="accessoryQuotedElectronic_form" method="post">
		<table class="tableForm">
			<input name="quotedCode" id="quotedCode" type="hidden" readonly="readonly" value="${accessoryQuotedElectronic.quotedCode}"/>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("accessoryQuotedElectronic.accessoryQuotedElectronic.enquiryCode")}: </span></th>
						
						<td><input name="enquiryCode"  value="${accessoryQuotedElectronic.enquiryCode}" class="easyui-validatebox" data-options="required:false,validType:'length[0,30]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("accessoryQuotedElectronic.accessoryQuotedElectronic.intentionCode")}: </span></th>
						
						<td><input name="intentionCode"  value="${accessoryQuotedElectronic.intentionCode}" class="easyui-validatebox" data-options="required:false,validType:'length[0,30]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("accessoryQuotedElectronic.accessoryQuotedElectronic.intentionSupplierCode")}: </span></th>
						
						<td><input name="intentionSupplierCode"  value="${accessoryQuotedElectronic.intentionSupplierCode}" class="easyui-validatebox" data-options="required:false,validType:'length[0,30]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("accessoryQuotedElectronic.accessoryQuotedElectronic.supplierCode")}: </span></th>
						
						<td><input name="supplierCode"  value="${accessoryQuotedElectronic.supplierCode}" class="easyui-validatebox" data-options="required:false,validType:'length[0,30]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("accessoryQuotedElectronic.accessoryQuotedElectronic.quotedDate")}: </span></th>
						
					    <td><input name="quotedDate"  value="<#if accessoryQuotedElectronic.quotedDate??>${accessoryQuotedElectronic.quotedDate?string('yyyy-MM-dd')}</#if>" class="easyui-datebox" data-options="required:false,formatter:dateFormatter,parser:dateParser" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("accessoryQuotedElectronic.accessoryQuotedElectronic.path")}: </span></th>
						
						<td><input name="path"  value="${accessoryQuotedElectronic.path}" class="easyui-validatebox" data-options="required:false,validType:'length[0,100]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 280px;"><span class="txtCenter">${action.getText("accessoryQuotedElectronic.accessoryQuotedElectronic.remarks")}: </span></th>
						
						<td><input name="remarks"  value="${accessoryQuotedElectronic.remarks}" class="easyui-validatebox" data-options="required:false,validType:'length[0,1000]'" /></td>
					</tr>
		</table>
	</form>
</div>
</#compress>