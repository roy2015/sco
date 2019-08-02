<#compress>
<div style="padding: 5px; overflow: hidden;">
	<form id="materialBigType_form" method="post">
		<table class="tableForm">
			<input name="materialBigTypeCode" id="materialBigTypeCode" type="hidden" readonly="readonly" value="${materialBigType.materialBigTypeCode}" />
			<tr>
				<td align="left" style="width: 80px;">将&nbsp;${materialBigType.materialBigTypeName}</td>
				</tr><tr>
				<td><label style="color: red">*</label>重命名为：<input name="materialBigTypeName" value="" style="width:120px;" data-options="required:false,validType:'length[0,33]'" /></td>
			</tr>
			<tr>
				<td colspan="2">重命名后该原料大类下的原料小类名称将不会被删除</td>
			</tr>
		</table>
	</form>
</div>
</#compress>
