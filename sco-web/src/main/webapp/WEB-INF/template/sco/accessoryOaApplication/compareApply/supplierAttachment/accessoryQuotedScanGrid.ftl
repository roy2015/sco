<#compress>
	<table id="accessoryQuotedScan_grid"  
		class="easyui-datagrid" 
		pagination="true" 
		pagePosition='bottom' 
		pageSize="5" 
		style="height:270px;width:615px;"
		pageList="[ 5, 10, 15, 20 ]"
		url="accessoryIntention_listAccessoryQuotedScan_2.html?enquiryCodes=${enquiryCodes}&supplierCodes=${supplierCodes}"
		data-options="rownumbers:true">
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:'true'">
			<th data-options="field:'intentionSupplierCode',width:150,formatter:function(value,row){
																				 if(value==null){
																				 	return row.supplierCode
																				 }else{
																				 	return value
																				 }
																				}">供应商名称</th>
			<th data-options="field:'intentionSupplierName',width:150,formatter:function(value,row){
																				 if(value==null){
																				 	return row.supplierName
																				 }else{
																				 	return value
																				 }
																				}">供应商名称</th>
			<th data-options="field:'enquiryCode',width:100">询价单号</th>
			<th data-options="field:'quotedDate',width:200,sortable:true">供应商报价日期</th>
			<th data-options="field:'created',width:100,sortable:true">报价单导入日期</th>
		</tr>
	</thead>
</table>
</#compress>
