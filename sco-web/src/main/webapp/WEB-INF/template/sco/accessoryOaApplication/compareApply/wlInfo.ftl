<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	<#include "sco/accessoryOaApplication/compareApply/wlInfo.js" />
</script>
</head>
<body>
<div id="appScheCompare_toolbar">
		<a onclick="wlInfoFn.saveWlInfo();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
			保存
		</a>
	</div>
<form id="wlInfo_form" method="post">
	  <div>
<table  id="wlInfo_grid"
		    class="easyui-datagrid"
			url='compareApply_listWlInfo_2.html?applicationCodeNow=${applicationCodeNow}&quotedCodes=${quotedCodes}'
			style="width:1100px;height:350px"
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
				<th data-options="field:'id',width:65,checkbox:true"></th>
				<th data-options="field:'intentionCode',width:120,sortable:false">
                	<span class="txtCenter">意向品/商品编号</span>
                </th>
				<th data-options="field:'intentionName',width:120,sortable:false">
                	<span class="txtCenter">意向品/商品名称</span>
                </th>
				<th data-options="field:'supplierCode',width:130,sortable:false,formatter:function(value,row){
																				 if(row.supplierCode == null) {
																					return row.intentionSupplierCode;
																				 }else {
																					return row.supplierCode;
																				 }
																				}">
                	<span class="txtCenter">供应商/意向供应商编号</span>
                </th>
				<th data-options="field:'supplierName',width:130,sortable:false,formatter:function(value,row){
																				 if(row.supplierName == null||row.supplierName =='') {
																					return row.intentionSupplierName;
																				 }else {
																					return row.supplierName;
																				 }
																				}">
                	<span class="txtCenter">供应商/意向供应商名称</span>
                </th>
				<th data-options="field:'quotedCode',width:100,sortable:false">
                	<span class="txtCenter">报价单号</span>
                </th>
				
				<th data-options="field:'enquiryCount',width:100,sortable:false">
                	<span class="txtCenter">询价数量</span>
                </th>
                <th data-options="field:'purchaseCount',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                																if(value !=null){
																				 return '<input id=purchaseCount'+rowIndex+'  onchange=wlInfoFn.testaler(\''+rowIndex+'\',\''+rowData.purchaseCount+'\') value='+rowData.purchaseCount+' >'
																				 }else{
																				 return '<input id=purchaseCount'+rowIndex+'  onchange=wlInfoFn.testaler(\''+rowIndex+'\',\''+rowData.purchaseCount+'\') value='+'\'\' />'
																				 }
																				}">
                	<span class="txtCenter">实际采购数量</span>
                </th>
                 <th data-options="field:'contractPrice',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value !=null){
																				 return '<input id=contractPrice'+rowIndex+' onchange=wlInfoFn.testaler(\''+rowIndex+'\',\''+rowData.contractPrice+'\') value='+rowData.contractPrice+' >'
																				 }else{
																				 return '<input id=contractPrice'+rowIndex+' onchange=wlInfoFn.testaler(\''+rowIndex+'\',\''+rowData.contractPrice+'\') value='+'\'\' />'
																				 }
																				}">
                	<span class="txtCenter">合同进价</span>
                </th>
                 <th data-options="field:'purchaseMoney',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value!=null){
																				 return '<input id=purchaseMoney'+rowIndex+' value='+rowData.purchaseMoney+' disabled=true/>'
																				 }else{
																				 return '<input id=purchaseMoney'+rowIndex+' value=\'\' disabled=true/>'
																				 }
																				}">
                	<span class="txtCenter">实际采购金额</span>
                </th>
                 <th data-options="field:'invoiceType',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value!=null){
																				 return '<input id=invoiceType'+rowIndex+' value='+rowData.invoiceType+'>'
																				 }else{
																				 return '<input id=invoiceType'+rowIndex+' value=\'\' />'
																				 }
																				}">
                	<span class="txtCenter">发票类型</span>
                </th>
                 <th data-options="field:'accessorySapCode',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value!=null){
																				 return '<input id=accessorySapCode'+rowIndex+' value='+rowData.accessorySapCode+'>'
																				 }else{
																				 return '<input id=accessorySapCode'+rowIndex+' value=\'\' />'
																				 }
																				}">
                	<span class="txtCenter">SAP物料号</span>
                </th>
                 <th data-options="field:'supplierSapCode' ,width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value!=null){
																				 return '<input id=supplierSapCode'+rowIndex+' value='+rowData.supplierSapCode+'>'
																				 }else{
																				 return '<input id=supplierSapCode'+rowIndex+' value=\'\' />'
																				 }
																				}">
                	<span class="txtCenter">SAP供应商号</span>
                </th>
                 <th data-options="field:'sjyjSpecification',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value!=null){
																				 return '<input id=sjyjSpecification'+rowIndex+' value='+rowData.sjyjSpecification+'>'
																				 }else{
																				 return '<input id=sjyjSpecification'+rowIndex+' value=\'\' />'
																				 }
																				}">
                	<span class="txtCenter">实际引进规格</span>
                </th>
            </tr>
           
        </thead>
    </table>
    </div>
</form>
</body>
</html>
</#compress>
		
