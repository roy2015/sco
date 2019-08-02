<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="createSupplier_form" method="post">
		<table class="tableForm">
		<input name="intentionCode" id="intentionCode" type="hidden" readonly="readonly" value="${intentionCode}"/>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">意向供应商编号: </span></th>
						
						<td><input name="supplierCode" id="supplierCode" value="${supplierCode}" readonly="readonly" class="easyui-validatebox" style="background-color: rgb(235, 235, 228);" data-options="required:true" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter"><font color="red">*</font>意向供应商名称: </span></th>
						
						<td><input name="supplierName" id="supplierName"  class="easyui-validatebox" data-options="required:true,validType:'length[1,33]'" /></td>
					</tr>
		</table>
	</form>
</div>
</#compress>