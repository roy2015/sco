<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
    <script type="text/javascript" >
	    <#if accounting.inlandImport == "INLAND">
	    	<#include "sco/merchandiseCostAnalysis/accountingIngredient/accounting/inlandAccountingModel.js" />
		<#else>
			<#include "sco/merchandiseCostAnalysis/accountingIngredient/accounting/importAccountingModel.js" />
		</#if>
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'center'" style="padding-left: 4px;padding-right: 4px;">
<form id="accounting_form" action="#" method="post">
	<div id="addAccounting_toolbar">
		<input id="accounting_inlandImport" name="accounting.inlandImport" class="easyui-validatebox" type="hidden" value="${accounting.inlandImport}">
		<input id="accounting_intentionCode" name="accounting.intentionCode" class="easyui-validatebox" type="hidden" value="${accounting.intentionCode}">
		<input id="accounting_intentionSupplierCode" name="accounting.intentionSupplierCode" class="easyui-validatebox" type="hidden" value="${accounting.intentionSupplierCode}">
		<input id="accounting_merchandiseCode" name="accounting.merchandiseCode" class="easyui-validatebox" type="hidden" value="${accounting.merchandiseCode}">
		<input id="accounting_supplierCode" name="accounting.supplierCode" class="easyui-validatebox" type="hidden" value="${accounting.supplierCode}">
		<input id="accounting_save" class="easyui-validatebox" type="hidden" value="">
		<a onclick="addAccountingFn.saveAccounting();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">保存</a>
		<a onclick="addAccountingFn.closeWindow();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭</a>
		<a onclick="addAccountingFn.showUploadScan('accounting');" plain="true" class="easyui-linkbutton" data-options="iconCls:'upload'">上传扫描版核算表</a>
		<a onclick="addAccountingFn.showUploadScan('ingredient');" plain="true" class="easyui-linkbutton" data-options="iconCls:'upload'">上传扫描版投料表</a>
		<a id="removeScanSccounting" onclick="addAccountingFn.removeScan('accounting');" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">删除扫描版核算表</a>
		<a id="removeScanIngredient" onclick="addAccountingFn.removeScan('ingredient');" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">删除扫描版投料表</a>
	</div>
	<div>
		<table  id="addAccounting_grid"
				title="本次录入核算表的商品/意向品"
				fit="false"
				iconCls=""
				rownumbers="true"
				url='accounting_loadAccountingBo_2.html?intentionCode=${accounting.intentionCode}&intentionSupplierCode=${accounting.intentionSupplierCode}&merchandiseCode=${accounting.merchandiseCode}&supplierCode=${accounting.supplierCode}<#if operate??>&accountingCode=${accounting.accountingCode}</#if>'
				>
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'intentionCode',width:100,sortable:false,formatter:function(value,row){
																				 if(row.merchandiseCode == null) {
																					return value;
																				 }else {
																					return row.merchandiseCode;
																				 }
																				}">意向品/商品编号</th>
					<th data-options="field:'intentionName',width:140,sortable:false,formatter:function(value,row){
																				 if(row.merchandiseName == null) {
																					return value;
																				 }else {
																					return row.merchandiseName;
																				 }
																				}">意向品/商品名称</th>
				</tr>
			</thead>
			<thead>
				<tr>
					<th data-options="field:'intentionSupplierCode',width:130,sortable:false,formatter:function(value,row){
																				 if(row.supplierCode == null) {
																					return value;
																				 }else {
																					return row.supplierCode;
																				 }
																				}">供应商/意向供应商编号</th>
					<th data-options="field:'intentionSupplierName',width:140,sortable:false,formatter:function(value,row){
																				 if(row.supplierName == null) {
																					return value;
																				 }else {
																					return row.supplierName;
																				 }
																				}">供应商/意向供应商名称</th>
					<th data-options="field:'merchandiseDxRoleName',width:80,sortable:false">商品定性角色</th>
					<th data-options="field:'merchandiseDlRoleName',width:80,sortable:false">商品定量角色</th>
					<th data-options="field:'intentionPurchaseDepartments',width:60,sortable:false,formatter:function(value,row){
																				 if(row.merchandiseCode == null) {
																					return value;
																				 }else {
																					return row.purchaseDepartments;
																				 }
																				}">采购部门</th>
					<th data-options="field:'rationed',width:70,sortable:false,formatter:function(value,row){
																				 if(row.merchandiseCode == null) {
																					return value;
																				 }else {
																					return row.netWeight;
																				 }
																				}">是否定量装</th>
					<th data-options="field:'purchaseType',width:60,sortable:false">采购类型</th>
					<th data-options="field:'saleType',width:60,sortable:false,formatter:function(value,row){
																				 if(row.merchandiseCode == null) {
																					return value;
																				 }else {
																					return row.storageForm;
																				 }
																				}">销售方式</th>
					<th data-options="field:'centreTypeName',width:60,sortable:false">中分类</th>
					<th data-options="field:'smallTypeName',width:60,sortable:false,formatter:function(value,row){
																				 if(row.intentionSmallTypeCode != null && row.intentionSmallTypeCode == 'ELSE') {
																					return row.elseTypeName;
																				 }else {
																					return value;
																				 }
																				}">小分类</th>
					<th data-options="field:'detailTypeName',width:60,sortable:false">明细类</th>
					<th data-options="field:'fineTypeName',width:60,sortable:false">细分类</th>
					<th data-options="field:'accountingScanPath',width:80,sortable:false,formatter:function(value,row){
																				 if(value == null) {
																					return '无';
																				 }else {
																					return '<a href=javascript:addAccountingFn.downloadScan(\'accounting\')>有</a>';
																				 }
																				}">扫描版核算表</th>
					<th data-options="field:'ingredientScanPath',width:80,sortable:false,formatter:function(value,row){
																				 if(value == null) {
																					return '无';
																				 }else {
																					return '<a href=javascript:addAccountingFn.downloadScan(\'ingredient\')>有</a>';
																				 }
																				}">扫描版投料表</th>
				</tr>
			</thead>
		</table>
		<p>
		<table>
			<tr>
				<td align="left">核算/投料表编号：</td>
				<td style="width: 100px;">
					${accounting.accountingCode}
					<input id="accounting_accountingCode" name="accounting.accountingCode" value="${accounting.accountingCode}" class="easyui-validatebox" data-options="required:false,editable:false" style="width: 100px;display: none;" readonly="readonly" />
				</td>
				<td align="left">核算/投料表审批状态：</td>
				<td>
					<#if accountingBo??>${accountingBo.applicationStatusValue}</#if>
					<input id="accounting_applicationStatus" value="<#if accountingBo??>${accountingBo.applicationStatus}</#if>" class="easyui-validatebox" data-options="required:false,editable:false" style="width: 100px;display: none;" />
					<input id="accounting_applicationStatusValue" value="<#if accountingBo??>${accountingBo.applicationStatusValue}</#if>" class="easyui-validatebox" data-options="required:false,editable:false" style="width: 100px;display: none;" readonly="readonly" />
				</td>
			</tr>
		</table>
		<p>
	</div>
	<div class="easyui-accordion" data-options="onSelect:addAccountingFn.eachIngredient">
		<div title="投料表">
			<#include "sco/merchandiseCostAnalysis/accountingIngredient/ingredient/ingredientGrid.ftl" />
		</div>
		<div title="核算表" >
			<#if accounting.inlandImport == "INLAND">
				<#include "sco/merchandiseCostAnalysis/accountingIngredient/accounting/inlandAccountingForm.ftl" />
			<#else>
				<#include "sco/merchandiseCostAnalysis/accountingIngredient/accounting/importAccountingForm.ftl" />
			</#if>
		</div>
	</div>
</form>
</div>
</body>
</html>
</#compress>