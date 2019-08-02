<#compress>
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/merchandiseOaApplication/reportNewOaApplication/supplierCertificate/supplierCertificateMModel.js" />
</script>
<!-- 意向品和供应商列表 -->
<div id="supplierCertificate_1" style="height: 223px;">
	<table id="supplierCertificateM_grid" class="easyui-datagrid" title="本次申请引进的商品列表" fit="true" fitColumns="true" singleSelect="false" pagination="true" pagePosition='bottom' pageSize="5" pageList="[ 5, 10, 15, 20 ]"
		url="supplierAttachmentM_listIntentionSupplier_2.html?intentionCodes=${intentionCodes}&intentionAndSupplierCodes=${intentionAndSupplierCodes}" data-options="rownumbers:true">
		<thead>
			<tr>
				<th align="center" data-options="checkbox:true"></th>
				<th data-options="field:'intentionCode',width:10,halign:'left',sortable:false">意向品编号</th>
				<th data-options="field:'intentionName',width:10,halign:'left',sortable:false">意向品名称</th>
				<th data-options="field:'intentionSupplierCode',width:10,halign:'left',sortable:false,formatter:function(value,row){
																					if(value==''||value==null){
																						return row.supplierCode;	
																					}else{
																						return value;
																					}
																			}">供应商编号</th>
				<th data-options="field:'intentionSupplierName',width:10,halign:'left',sortable:false,formatter:function(value,row){
																					if(value==''||value==null){
																						return row.supplierName;	
																					}else{
																						return value;
																					}
																			}">供应商名称</th>
			</tr>
		</thead>
	</table>
</div>
<div style="padding: 10px;"></div>
<a onclick="supplierCertificateMFn.addSCM()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 查看系统证件库 </a>
<a onclick="supplierCertificateMFn.add()" plain="true" class="easyui-linkbutton" data-options="iconCls:'upload'"> 上传新证件 </a>
<div id="supplierCertificate_2" style="height: 263px;">
	<div id="supplierCertificateM_toolbar">
		<a id="remove" onclick="supplierCertificateMFn.remove()" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'"> 移除 </a>
	</div>
	<table id="supplierCertificateM_grid_gl" class="easyui-datagrid" toolbar="#supplierCertificateM_toolbar" title="已选择/上传证件列表:" fit="true" fitColumns="true" singleSelect="false" pagination="true" pagePosition='bottom'
		pageSize="5" pageList="[ 5, 10, 15, 20 ]" url="supplierCertificateM_listSupplierCertificateM_2.html?applicationCode=${applicationCode}"
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th align="center" data-options="checkbox:true"></th>
				<th data-options="field:'supplierName',width:30,halign:'left',sortable:false">供应商名称</th>
				<th data-options="field:'certificateTypeName',width:50,halign:'left',sortable:false,formatter:supplierCertificateMFn.downloadFile">证件名称</th>
				<th data-options="field:'createby',width:20,halign:'left',sortable:false">上传人</th>
				<th data-options="field:'created',width:50,halign:'left',sortable:false">上传日期</th>
				<th data-options="field:'startDate',width:50,halign:'left',sortable:false,formatter:supplierCertificateMFn.formatterDate">证件有效期</th>
			</tr>
		</thead>
	</table>
</div>
</#compress>
