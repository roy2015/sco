<#compress>
<head>  
    <script type="text/javascript" src="${copoxtPath}js/gridEdit.js" charset="utf-8"></script>
    <script type="text/javascript" src="${copoxtPath}js/plugin/jquery.form.js" charset="utf-8"></script>
    <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css"/>
 </head>
 
<div style="padding: 5px;overflow: hidden;">
	<form id="intentionSupplier_form" method="post">
		<input name="intentionCode" id="intentionCode" type="hidden"  value="${intentionCode}"/>
		<table class="tableForm">
					<tr>
					    <th align="right"><font color="red">*</font>意向供应商编号：</th>
						<td >
							<input id="intentionSupplierCode" name="intentionSupplierCode"  value="${intentionSupplierCode}" style="width:120px;background:lightgray;" readonly="readonly" class="easyui-validatebox" data-options="required:false" />
						</td>
					</tr>
					<tr>	
						<th align="right"><font color="red">*</font>意向供应商全称：</th>
						<td >
							<input id="intentionSupplierName" name="intentionSupplierName"   class="easyui-validatebox" style="width:120px;" data-options="required:true,validType:'length[0,30]'" />
						</td>
					</tr>
		</table>
	</form>
</div>
</#compress>