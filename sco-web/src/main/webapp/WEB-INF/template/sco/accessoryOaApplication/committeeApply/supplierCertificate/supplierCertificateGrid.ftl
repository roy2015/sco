<#compress>
	<table id="supplierCertificate_grid"  
		class="easyui-datagrid" 
		pagination="true" 
		pagePosition='bottom' 
		pageSize="20" 
		style="height:270px;width:615px;"
		pageList="[ 10, 20, 30, 40 ]"
		url="supplierCertificate_listSupplierCertificateAll_2.html?supplierCode=${supplierCertificate.supplierCode}"
		data-options="rownumbers:true">
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:'true'">
			<th data-options="field:'certificateTypeName',width:150">证件名称</th>
			<th data-options="field:'createby',width:100">上传人</th>
			<th data-options="field:'created',width:100,sortable:true">上传日期</th>
			<th data-options="field:'validRegion',width:200,sortable:true">证件有效期</th>
		</tr>
	</thead>
</table>
</#compress>
