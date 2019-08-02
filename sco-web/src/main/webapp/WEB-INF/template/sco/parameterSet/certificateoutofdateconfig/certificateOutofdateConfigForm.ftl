<#compress>
<div style="padding: 5px; overflow: hidden;">
	<form id="certificateOutofdateConfig_form" method="post">
		<table class="tableForm">
			<input name="configCode" id="configCode" type="hidden" readonly="readonly" value="${certificateOutofdateConfig.configCode}" />
			<tr>
				<th align="right" style="width: 55px;"><label style="color: red">*</label><span class="txtCenter">证件过期前 </span></th>
				<td><input name="outofdate" style="width:120px;" class="easyui-numberbox" data-options="required:false,precision:0" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
				<th align="right" style="width: 55px;"><span class="txtCenter">天提醒用户 </span></th>
			</tr>
		</table>
	</form>
</div>
</#compress>
