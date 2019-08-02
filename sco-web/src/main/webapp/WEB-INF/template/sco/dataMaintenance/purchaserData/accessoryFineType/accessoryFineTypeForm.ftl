<#compress>
<div style="padding: 5px; overflow: hidden;">
	<form id="accessoryFineType_form" method="post">
		<table class="tableForm">
			<input name="fineTypeCode" id="fineTypeCode" type="hidden" readonly="readonly" value="${accessoryFineType.fineTypeCode}" />
			<tr>
				<td style="width: 140px;"><label style="color: red">*</label>请填入新细分类的名称:</td>
				<td><input name="fineTypeName" style="width:120px;" class="easyui-validatebox" data-options="required:false,validType:'length[0,33]'" /></td>
			</tr>
		</table>
	</form>
</div>
</#compress>
