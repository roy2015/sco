<#compress>
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/accessoryOaApplication/committeeApply/supplierCertificate/supplierCertificateAModel.js" />
</script>
<!-- 意向品和供应商列表 -->
<div  style="height: 323px;">
<table id="supplierCertificateA_grid" class="easyui-datagrid" title="本次申请引进的商品列表" fit="true" singleSelect="false" pagination="true" pagePosition='bottom' pageSize="10" pageList="[ 10, 15, 20,50 ]"
	fitColumns="true" url="supplierAttachmentA_listQuotedRecord_2.html?quotedCode=${quotedCodes}" data-options="rownumbers:true">
	<thead>
		<tr>
			<th align="center" data-options="checkbox:true"></th>
			<th data-options="field:'intentionCode',sortable:false">意向品编号</th>
			<th data-options="field:'intentionName',width:10,sortable:false">意向品名称</th>
			<th
				data-options="field:'intentionSupplierCode',sortable:false,formatter:function(value,row){
																				 if(value==null){
																				 	return row.supplierCode
																				 }else{
																				 	return value
																				 }
																				} ">供应商/意向品供应商编号</th>
			<th
				data-options="field:'intentionSupplierName',width:10,sortable:false,formatter:function(value,row){
																				 if(value==null){
																				 	return row.supplierName
																				 }else{
																				 	return value
																				 }
																				} ">供应商/意向品供应商名称</th>
		</tr>
	</thead>
</table>
</div>
<div style="padding: 10px;"></div>
<a onclick="supplierCertificateAFn.addSCM()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 查看系统证件库 </a>
<a onclick="supplierCertificateAFn.add()" plain="true" class="easyui-linkbutton" data-options="iconCls:'upload'"> 上传新证件 </a>
<div id="supplierCertificateA_toolbar">
	<a id="remove" onclick="supplierCertificateAFn.remove()" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'"> 移除 </a>
</div>
<table id="supplierCertificateA_grid_gl" class="easyui-datagrid" toolbar="#supplierCertificateA_toolbar" title="已选择/上传证件列表：" fit="true" singleSelect="false" pagination="true" pagePosition='bottom'
	fitColumns="true" pageSize="5" pageList="[ 5, 10, 15, 20 ]" url="supplierCertificateA_listSupplierCertificateA_2.html?applicationCode=${applicationCodeNow}" data-options="rownumbers:true">
	<thead>
		<tr>
			<th align="center" data-options="checkbox:true"></th>
			<th
				data-options="field:'intentionSupplierName',width:10,sortable:false,formatter:function(value,row){
																				 if(value==null){
																				 	return row.supplierName
																				 }else{
																				 	return value
																				 }
																				} ">供应商/意向品供应商名称</th>
			<th data-options="field:'certificateTypeName',width:8,sortable:false,formatter:supplierCertificateAFn.downloadFile">证件名称</th>
			<th data-options="field:'createby',sortable:false">上传人</th>
			<th data-options="field:'createdate',sortable:false">上传日期</th>
			<th
				data-options="field:'startDate',width:10,sortable:false,formatter:function(value,row){
																				 if(value==null&&row.endDate==null){
																				 	return '无有效期'
																				 }else if(value!=null&&row.endDate==null){
																				 	return value+'~永久';
																				 }else{
																				 	return value+'~'+row.endDate
																				 }
																				}">证件有效期</th>
		</tr>
	</thead>
</table>
</#compress>
