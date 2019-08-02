<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="marketSurveyData_form" method="post">
		<table class="tableForm">
			<input name="merchandise_code" id="merchandise_code" type="hidden" readonly="readonly" value="${marketSurveyData.merchandise_code}"/>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("smms.marketSurveyData.merchandiseCode")}: </span></th>
						
						<td><input name="merchandiseCode"  value="${marketSurveyData.merchandiseCode}" class="easyui-validatebox" data-options="required:false,validType:'length[0,30]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("smms.marketSurveyData.jpmcName")}: </span></th>
						
						<td><input name="jpmcName"  value="${marketSurveyData.jpmcName}" class="easyui-validatebox" data-options="required:false,validType:'length[0,100]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("smms.marketSurveyData.mcRegion")}: </span></th>
						
						<td><input name="mcRegion"  value="${marketSurveyData.mcRegion}" class="easyui-validatebox" data-options="required:false,validType:'length[0,100]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("smms.marketSurveyData.jpPriceUnits")}: </span></th>
						
						<td><input name="jpPriceUnits"  value="${marketSurveyData.jpPriceUnits}" class="easyui-validatebox" data-options="required:false,validType:'length[0,30]'" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("smms.marketSurveyData.jpContent")}: </span></th>
						
						<td><input name="jpContent"  value="${marketSurveyData.jpContent}" class="easyui-numberbox" data-options="required:false,precision:2" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("smms.marketSurveyData.marketSurveyDate")}: </span></th>
						
					    <td><input name="marketSurveyDate"  value="<#if marketSurveyData.marketSurveyDate??>${marketSurveyData.marketSurveyDate?string('yyyy-MM-dd')}</#if>" class="easyui-datebox" data-options="required:false,formatter:dateFormatter,parser:dateParser" /></td>
					</tr>
					<tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter">${action.getText("smms.marketSurveyData.marketSurveyPrice")}: </span></th>
						
						<td><input name="marketSurveyPrice"  value="${marketSurveyData.marketSurveyPrice}" class="easyui-numberbox" data-options="required:false,precision:2" /></td>
					</tr>
		</table>
	</form>
</div>
</#compress>