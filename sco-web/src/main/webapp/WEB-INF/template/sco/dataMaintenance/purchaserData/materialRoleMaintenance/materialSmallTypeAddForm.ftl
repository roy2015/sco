<#compress>
<div style="padding: 5px; overflow: hidden;">
	<form id="materialSmallType_form" method="post">
		<table class="tableForm">
			<tr>
				<td align="right" style="width: 80px;"><label style="color:red;">*</label>原料大类：</td>
				<td><input class="easyui-combobox filterSelect" name="materialBigTypeCode" id="bigTypeCode" style="width: 124px;"
					data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'materialProperties_listMaterialBigType_5.html'
			    " /></td>
			</tr>
			<tr>
				<th align="right" style="width: 80px;"><label style="color: red">*</label>请填入原料小类名称：</th>
				<td><input name="materialSmallTypeName" style="width:120px;" class="easyui-validatebox" minlength="1" maxlength="33" data-options="required:true,validType:'length[1,33]'" /></td>
			</tr>
		</table>
	</form>
</div>
</#compress>
