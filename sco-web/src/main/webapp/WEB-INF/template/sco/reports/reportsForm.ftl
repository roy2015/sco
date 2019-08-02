<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="reports_form" method="post">
		<table class="tableForm">
			<input name="reportsCode" id="reportsCode" type="hidden" readonly="readonly" value="${reports.reportsCode}"/>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("reports.reports.reportsTypeCode")}: </span></th>
						
						<td><input name="reportsTypeCode"  value="${reports.reportsTypeCode}" class="easyui-validatebox" data-options="required:false,validType:'length[0,30]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("reports.reports.reportsName")}: </span></th>
						
						<td><input name="reportsName"  value="${reports.reportsName}" class="easyui-validatebox" data-options="required:false,validType:'length[0,100]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 280px;"><span class="txtCenter">${action.getText("reports.reports.reportsUrl")}: </span></th>
						
						<td><input name="reportsUrl"  value="${reports.reportsUrl}" class="easyui-validatebox" data-options="required:false,validType:'length[0,300]'" /></td>
					</tr>
		</table>
	</form>
</div>
</#compress>