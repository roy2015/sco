<#compress>
<head>  
    <script type="text/javascript" src="${copoxtPath}js/gridEdit.js" charset="utf-8"></script>
    <script type="text/javascript" src="${copoxtPath}js/plugin/jquery.form.js" charset="utf-8"></script>
    <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css"/>
 </head>
 
<div style="padding: 5px;overflow: hidden;">
	<form id="convertUnit_form" method="post">
		<table class="tableForm">
			<input name="intentionCode" id="intentionCode" type="hidden"  value="${intentionCode}"/>
					<tr>
					    <th align="right"><font color="red">*</font>选择统一对比单位：</th>
						<td >
							<input id="convertUnit" name="convertUnit"  value="${convertUnit}" class="easyui-numberbox" style="width:120px;" data-options="required:true,precision:2" /><td>kg</td>
						</td>
					</tr>
		</table>
	</form>
</div>
</#compress>