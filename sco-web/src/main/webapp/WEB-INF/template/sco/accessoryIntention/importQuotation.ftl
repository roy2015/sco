<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/accessoryIntention/importQuotation.js" />
</script>
</head>
<body>
	<div style="padding: 5px;">
		<input type="hidden" name="accessoryIntentionCode" id="accessoryIntentionCode" value="${accessoryIntention.intentionCode}" />
		<form id="uploadAccessoryQuotedForm" method="post" style="margin: 0px; display: inline">
			<table class="table table-condensed" style="width:200px">
				<tr>
					<td>供应商报价日期:&nbsp;&nbsp;&nbsp;</td>
					<td>
						<input class="easyui-datetimebox filterInput" filterName="quotedDate" type="text" name="quotedDate" id="quotedDate" data-options="required:true,editable:false" style="width: 150px;" />&nbsp;&nbsp;&nbsp;
					</td>
					<td>
						<input type="text" class="file" id="txt" style="width: 0px; visibility: hidden" /> <input class="input_file" type="file" onchange="txt.value=this.value" id="upload_import" name="upload" style="width: 300px;" />
					</td>
					<td>
					<a id="insertdzForm" onclick="importQuotationFn.importQuoted()" plain="true" class="easyui-linkbutton" data-options="iconCls:'upload'";"> 导入电子版报价单 </a> <a id="insertsmForm"
				onclick="importQuotationFn.importScanQuoted()" plain="true" class="easyui-linkbutton" data-options="iconCls:'upload'";"> 导入扫描版报价单 </a>
					</td>
				</tr>
			</table>
			
		</form>
	</div>
	<div style="padding: 5px;"></div>
	<table id="accessoryIntentionSupplier_drbjd" class="easyui-datagrid" title="已选供应商" singleSelect="true" checkOnSelect="true" selectOnCheck="true" pagination="true" pagePosition="bottom" pageSize="5"
		pageList="[ 5, 10, 15, 20 ]" nowrap="false" toolbar="#accessoryIntentions_toolbar" url='accessoryIntention_listAccessoryIntentionSupplier_2.html?intentionCode=${accessoryIntention.intentionCode}'
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="width:65,checkbox:true"></th>
				<th data-options="field:'intentionSupplierCode',width:500,sortable:true"><span >供应商编号</span></th>
				<th data-options="field:'intentionSupplierName',width:530,sortable:true"><span >供应商名称</span></th>
			</tr>
		</thead>
	</table>
	<div style="padding: 10px;"></div>
	<table id="accessoryEnquiry_drbjd" class="easyui-datagrid" title="已有询价单列表" singleSelect="true" checkOnSelect="true" selectOnCheck="true" pagination="true" pagePosition="bottom" pageSize="5"
		pageList="[ 5, 10, 15, 20 ]" nowrap="false" toolbar="#accessoryIntentions_toolbar" url='accessoryIntention_listAccessoryEnquiry_2.html?intentionCode=${accessoryIntention.intentionCode}'
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="width:65,checkbox:true"></th>
				<th data-options="field:'enquiryCode',width:220,sortable:true"><span >询价单编号</span></th>
				<th data-options="field:'enquiryName',width:220,sortable:true"><span >询价单名称</span></th>
				<th data-options="field:'created',width:210,sortable:true"><span >询价单创建日期</span></th>
				<th
					data-options="field:'supplierCount',width:220,sortable:true,align:'right',formatter: function(value,row,index){
							if(value!=null && value!=''){
								return value;
							}else{
								return 0;
							}
						}">
					<span >报价供应商数量</span>
				</th>
				<th data-options="field:'createUserName',width:155,sortable:true"><span >询价单创建人</span></th>
			</tr>
		</thead>
	</table>
	<div style="padding: 10px;"></div>
	<table>
		<tr>
			<td><a id="closeForm" onclick="importQuotationFn.deleteAccessoryQuotedElectronic('${accessoryIntention.intentionCode}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'";">
					删除电子版报价单 </a></td>
			<td><a id="closeForm" onclick="importQuotationFn.downloadAccessoryQuotedElectronic('${accessoryIntention.intentionCode}');" plain="true" class="easyui-linkbutton"
				data-options="iconCls:'excel'";"> 导出电子版报价单 </a></td>
		</tr>
	</table>
	<table id="accessoryQuotedElectronic_drbjd" class="easyui-datagrid" title="已导入电子报价单列表" pagination="true" pagePosition="bottom" pageSize="5" pageList="[ 5, 10, 15, 20 ]" nowrap="false"
		toolbar="#toolbar" url='accessoryIntention_listAccessoryQuotedElectronic_2.html?intentionCode=${accessoryIntention.intentionCode}' data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="width:65,checkbox:true"></th>
				<th data-options="field:'quotedCode',width:120,sortable:true"><span >报价单编号</span></th>
				<th
					data-options="field:'intentionSupplierCode',width:100,sortable:true,formatter:function(value,row){
																				 if(row.supplierCode == null) {
																					return row.intentionSupplierCode;
																				 }else {
																					return row.supplierCode;
																				 }
																				}">
					<span >供应商编号</span>
				</th>
				<th
					data-options="field:'supplierName',width:210,sortable:true,formatter:function(value,row){
																				 if(row.supplierName == null) {
																					return row.intentionSupplierName;
																				 }else {
																					return row.supplierName;
																				 }
																				}">
					<span >供应商名称</span>
				</th>
				<th data-options="field:'enquiryCode',width:120,sortable:true"><span >询价单号</span></th>
				 <th data-options="field:'enquiryName',width:200,sortable:true"><span >询价单名称</span></th>
				<th data-options="field:'quotedDate',width:150,sortable:true"><span >供应商报价日期</span></th>
				<th data-options="field:'created',width:120,sortable:true"><span >报价单导入日期</span></th>
			</tr>
		</thead>
	</table>
	<div style="padding: 10px;"></div>
	<table>
		<tr>
			<td><a id="closeForm" onclick="importQuotationFn.deleteAccessoryQuotedScan('${accessoryIntention.intentionCode}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'";">
					删除扫描版报价单 </a> <a id="closeForm" onclick="importQuotationFn.downloadAccessoryQuotedScan('${accessoryIntention.intentionCode}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'";">
					导出扫描版报价单 </a></td>
		</tr>
	</table>
	<table id="accessoryQuotedScan_drbjd" class="easyui-datagrid" title="已导入扫描版报价单列表" pagination="true" pagePosition="bottom" pageSize="5" pageList="[ 5, 10, 15, 20 ]" nowrap="false" toolbar="#toolbar"
		url='accessoryIntention_listAccessoryQuotedScan_2.html?intentionCode=${accessoryIntention.intentionCode}' data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="width:65,checkbox:true"></th>
				<th
					data-options="field:'intentionSupplierCode',width:120,sortable:true,formatter:function(value,row){
																				 if(row.supplierCode == null) {
																					return row.intentionSupplierCode;
																				 }else {
																					return row.supplierCode;
																				 }
																				}">
					<span >供应商编号</span>
				</th>
				<th
					data-options="field:'quotedCode',width:300,sortable:true,formatter:function(value,row){
																				 if(row.supplierName == null) {
																					return row.intentionSupplierName;
																				 }else {
																					return row.supplierName;
																				 }
																				}">
					<span >供应商名称</span>
				</th>
				<th data-options="field:'enquiryCode',width:120,sortable:true"><span >询价单号</span></th>
				 <th data-options="field:'enquiryName',width:210,sortable:true"><span >询价单名称</span></th>
				<th data-options="field:'quotedDate',width:150,sortable:true"><span >供应商报价日期</span></th>
				<th data-options="field:'created',width:120,sortable:true"><span >报价单导入日期</span></th>
			</tr>
		</thead>
	</table>
</body>
</html>
</#compress>
