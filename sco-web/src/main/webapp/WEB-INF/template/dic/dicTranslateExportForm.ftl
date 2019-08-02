<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="dicTranslateExport_fm" method="post">
		<table width="100%" border="0" cellpadding="4" cellspacing="0" bordercolor="#EEEEEE" >
		<tr>
			<td align="right"><span style="font-size:12px;font-weight:100;" >${action.getText("dic.exportSourceLanguage")}</span></td>
			<td><input id="sourceLanguage" name="language" class="easyui-combobox " data-options="valueField:'id',textField:'text',
					data:[{
							'id': 'zh_cn',
							'text': '中文',
							'selected':true
						},{
							'id': 'en_gb',
							'text': 'English'
						}]"/>
			</td>
		</tr>
		<tr>
			<td align="right"><span style="font-size:12px;align:center;">${action.getText("dic.exportTargetLanguage")}</span></td>
			<td><input id="targetLanguage" name="language" class="easyui-combobox " data-options="valueField:'id',textField:'text',
					data:[{
							'id': 'zh_cn',
							'text': '中文',
							'selected':true
						},{
							'id': 'en_gb',
							'text': 'English'
						}]"/>
			</td>
		</tr>
		</table>
	</form>
</div>
</#compress>