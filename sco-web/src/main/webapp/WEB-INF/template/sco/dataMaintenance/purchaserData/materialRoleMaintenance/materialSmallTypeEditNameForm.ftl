<#compress>
<div style="padding: 5px; overflow: hidden;">
	<form id="materialSmallType_form" method="post">
		<table class="tableForm">
			<input name="materialSmallTypeCode" id="materialSmallTypeCode" type="hidden" readonly="readonly" value="${materialSmallType.materialSmallTypeCode}" />
			<tr>
				<td align="right" style="width: 80px;">将&nbsp;${materialSmallType.materialSmallTypeName}</td>
				</tr><tr>
				<td><label style="color: red">*</label>重命名为：<input name="materialSmallTypeName" style="width:120px;" class="easyui-validatebox" minlength="1" maxlength="33" data-options="required:true,validType:'length[1,33]'" /></td>
			</tr>
			<tr>
				<td colspan="2">重命名后该原料小类下的原料名称将不会被删除</td>
			</tr>
		</table>
	</form>
</div>
</#compress>
