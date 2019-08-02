<#compress>
  	<link rel="stylesheet"  href="${copoxtPath}css/sco/merStandProInfo.css" type="text/css" />
	<div style="padding: 18px;overflow: hidden;">
		<form id="standPro_form" method="post">
			<input type="hidden" name="configCode" <#if standProInfo?exists>value="${standProInfo.configCode}"</#if> />
			<table class="tableForm" border="0">
				<tr>
					<td style="text-align:right;"><span><font color="red">*</font>生效日期:</span></td>
					<td>
						<input class="easyui-datebox" name="startDate" 
							<#if standProInfo?exists>value="${standProInfo.startDate?string("yyyy-MM-dd")}" disabled="disabled" </#if>
								data-options="required:true,editable:false" style="width:104px;"/>
					</td>
					<td style="text-align:right;">总日期:</td>
					<td>
						<input class="easyui-validatebox" name="sumDay" <#if standProInfo?exists>value="${standProInfo.sumDay?c}" disabled="disabled" </#if>
							data-options="required:false,editable:false" disabled="true" style="width:97px;height:18px" />
						<input type="hidden" name="sumDay" <#if standProInfo?exists>value="${standProInfo.sumDay?c}"</#if> >
					</td>
					<td style="text-align:right;">结束日期:</td>
					<td>
						<input class="easyui-datebox" name="endDate" 
							<#if standProInfo?exists && standProInfo.endDate?exists>value="${standProInfo.endDate?string("yyyy-MM-dd")}" </#if>
								data-options="required:false,editable:false" style="width:104px;"/>
					</td>
				</tr>
			</table>
			<div class="main_tu">
				<div class="wen01"><input <#if standProInfo?exists>value="${standProInfo.processDate1?c}" disabled="disabled"</#if> name="processDate1" id="processDate1" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen02"><input <#if standProInfo?exists>value="${standProInfo.processDate2?c}" disabled="disabled"</#if> name="processDate2" id="processDate2" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen03"><input <#if standProInfo?exists>value="${standProInfo.processDate3?c}" disabled="disabled"</#if> name="processDate3" id="processDate3" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen04"><input <#if standProInfo?exists>value="${standProInfo.processDate4?c}" disabled="disabled"</#if> name="processDate4" id="processDate4" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen05"><input <#if standProInfo?exists>value="${standProInfo.processDate5?c}" disabled="disabled"</#if> name="processDate5" id="processDate5" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen06"><input <#if standProInfo?exists>value="${standProInfo.processDate6?c}" disabled="disabled"</#if> name="processDate6" id="processDate6" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen07"><input <#if standProInfo?exists>value="${standProInfo.processDate7?c}" disabled="disabled"</#if> name="processDate7" id="processDate7" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen08"><input <#if standProInfo?exists>value="${standProInfo.processDate8?c}" disabled="disabled"</#if> name="processDate8" id="processDate8" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen09"><input <#if standProInfo?exists>value="${standProInfo.processDate9?c}" disabled="disabled"</#if> name="processDate9" id="processDate9" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen10"><input <#if standProInfo?exists>value="${standProInfo.processDate10?c}" disabled="disabled"</#if> name="processDate10" id="processDate10" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen11"><input <#if standProInfo?exists>value="${standProInfo.processDate11?c}" disabled="disabled"</#if> name="processDate11" id="processDate11" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
				<div class="wen12"><input <#if standProInfo?exists>value="${standProInfo.processDate12?c}" disabled="disabled"</#if> name="processDate12" id="processDate12" onchange="merStandProInfoFn.sumProcessDay(this.id, this.value)" class="easyui-numberbox" data-options="precision:0,required:true" maxlength="12" /></div>
			</div>
		</form>
	</div>
</#compress>