<#compress>
<script type="text/javascript">
	<#include "sco/merchandiseCostAnalysis/accountingIngredient/accounting/uploadScan.js" />
</script>
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<div style="padding: 5px; overflow: hidden;">
	<form id="uploadScanForm" method="post" enctype="multipart/form-data">
		<div>
			<div class="line">
				<input id="scanType" class="easyui-validatebox" type="hidden" name="scanType" value="${scanType}">
				<input id="accountingCode" name="accountingCode" class="easyui-validatebox" type="hidden" value="${accountingCode}">
				<input id="intentionCode" name="intentionCode" class="easyui-validatebox" type="hidden" value="${intentionCode}">
				<input id="intentionSupplierCode" name="intentionSupplierCode" class="easyui-validatebox" type="hidden" value="${intentionSupplierCode}">
				<input id="merchandiseCode" name="merchandiseCode" class="easyui-validatebox" type="hidden" value="${merchandiseCode}">
				<input id="supplierCode" name="supplierCode" class="easyui-validatebox" type="hidden" value="${supplierCode}">
				
				<input id="scanFile" name="scanFile" type="text" class="file" disabled style="width: 0px;" />
				<input id="file" name="upload" type="file" class="input_file" onchange="document.getElementById('scanFile').value=this.value;" style="width: 200px;vertical-align:middle" />&nbsp;
				<input class="input_file" type="button" value="导入" id="subBtn" onClick="uploadScanFn.uploadScan()" style="*margin-top: -5px; *margin-left: 0px;height:18px;vertical-align:middle" />
			</div>
		</div>
	</form>
</div>
</#compress>
