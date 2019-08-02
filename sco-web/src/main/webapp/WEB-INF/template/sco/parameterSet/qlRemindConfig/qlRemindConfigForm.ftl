<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="qlRemindConfig_form" method="post">
	 <input name="configCode" type="hidden" value="${qlRemindConfig.configCode}"/>
                   签量已完成百分比超过:
     <input id="thresholdValue" style="width:120px;" name="thresholdValue" value="${qlRemindConfig.thresholdValue}" class="easyui-numberbox" data-options="required:true,precision:2" />
     %时提醒用户
	</form>
</div>
</#compress>