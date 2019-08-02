<#compress>
<div style="padding: 5px; overflow: hidden;">
	<form id="materialSmallType_form" method="post">
		<table class="tableForm">
			<input name="materialSmallTypeCode" id="materialSmallTypeCode" type="hidden" readonly="readonly" value="${materialSmallType.materialSmallTypeCode}" />
			<tr>
				<td align="right" style="width: 80px;">将&nbsp;${materialSmallType.materialSmallTypeName}</td>
				</tr>
				<tr>
				<td><label style="color: red">*</label>所属大类更改为：<input class="easyui-combobox filterSelect" name="materialBigTypeCode" id="materialBigName" style="width: 124px;"
					data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'materialProperties_listMaterialBigType_5.html'
			    " /></td>
			</tr>
			<tr>
				<td colspan="2">重命名后该原料小类下的原料名称将不会被删除</td>
			</tr>
		</table>
	</form>
</div>
</#compress>
