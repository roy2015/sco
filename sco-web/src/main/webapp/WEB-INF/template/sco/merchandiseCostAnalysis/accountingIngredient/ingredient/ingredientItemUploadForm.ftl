<#compress>
<script type="text/javascript">
	<#include "sco/merchandiseCostAnalysis/accountingIngredient/ingredient/ingredientItemUpload.js" />
</script>
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<div style="padding: 5px; overflow: hidden;">
	<form id="ingredientItemUploadForm" method="post" enctype="multipart/form-data">
		<div>
			<div class="line">
				<input id="ingredientCode" name="ingredientCode" class="easyui-validatebox" type="hidden" value="${ingredientCode}">
				<input id="intentionCode" name="intentionCode" class="easyui-validatebox" type="hidden" value="${intentionCode}">
				<input id="intentionSupplierCode" name="intentionSupplierCode" class="easyui-validatebox" type="hidden" value="${intentionSupplierCode}">
				<input id="merchandiseCode" name="merchandiseCode" class="easyui-validatebox" type="hidden" value="${merchandiseCode}">
				<input id="supplierCode" name="supplierCode" class="easyui-validatebox" type="hidden" value="${supplierCode}">
				<input id="inlandImport" name="inlandImport" class="easyui-validatebox" type="hidden" >
				<!-- 判断核算表编号是否为空，为空则显示选择核算表类型 -->
				<#if !ingredientCode??>
					<label><input type="radio" id="accountingType" name="accountingType" checked="checked" value="1" />非进口核算表</label>
					<label><input type="radio" name="accountingType" value="2" />进口核算表</label>
					<p />
				</#if>
				<input id="ingredientItemFile" name="ingredientItemFile" type="text" class="file" style="width: 0px;" />
				<input id="file" name="upload" type="file" class="input_file" onchange="document.getElementById('ingredientItemFile').value=this.value;" style="width: 200px;vertical-align:middle" />&nbsp;
				<input class="input_file" type="button" value="导入" id="subBtn" onClick="uploadIngredientItemFn.uploadIngredientItem()" style="*margin-top: -5px; *margin-left: 0px; height:19px;vertical-align:middle" />
				<p />
				<span id="msg" style='color:red'></span>
			</div>
		</div>
	</form>
</div>
</#compress>
