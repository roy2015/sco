<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="dicImport_fm" method="post" enctype="multipart/form-data">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#EEEEEE" >
			<tr>
				<td align="center"><span style="font-size:12px;" >${action.getText("dic.ckLanguage")}</span></td>
				<td ><input id="language" name="language" class="easyui-combobox " data-options="valueField:'id',textField:'text',
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
				<td align="center"><span style="font-size:12px;align:center;">${action.getText("dic.chooseFile")}</span></td>
				<td >
					<input type="file" id="upload" name="upload" />
				</td>
			</tr>
		</table>
	</form>
</div>
</#compress>