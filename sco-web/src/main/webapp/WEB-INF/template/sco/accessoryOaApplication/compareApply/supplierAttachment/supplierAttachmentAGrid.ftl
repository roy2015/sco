<#compress>
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/accessoryOaApplication/compareApply/supplierAttachment/supplierAttachmentAModel.js" />
</script>
<!-- 供应商附件管理主表 -->
<div id="tb1" style="height: 223px;">
	<table id="supplierAttachmentA_grid" class="easyui-datagrid" title="本次申请引进的商品列表" fit="true" singleSelect="false" pagination="true" pagePosition='bottom' pageSize="5" pageList="[ 5, 10, 20, 50 ]"
		url="supplierAttachmentA_listQuotedRecord_2.html?quotedCode=${quotedCodes}" data-options="rownumbers:true">

		<thead>
			<tr>
				<th align="center" data-options="width:40,checkbox:true"></th>
				<th data-options="field:'intentionCode',width:105,sortable:false">意向品编号</th>
				<th data-options="field:'intentionName',width:110,sortable:false">意向品名称</th>
				<th
					data-options="field:'intentionSupplierCode',width:140,sortable:false,formatter:function(value,row){
																				 if(value==null){
																				 	return row.supplierCode
																				 }else{
																				 	return value
																				 }
																				} ">供应商/意向品供应商编号</th>
				<th
					data-options="field:'intentionSupplierName',width:140,sortable:false,formatter:function(value,row){
																				 if(value==null){
																				 	return row.supplierName
																				 }else{
																				 	return value
																				 }
																				} ">供应商/意向品供应商名称</th>
					<th data-options="field:'enquiryCode',width:90,sortable:false">询价单号</th>
					<th data-options="field:'enquiryCreated',width:95,sortable:false">询价单创建日期</th>
					<th data-options="field:'enquiryCreateby',width:80,sortable:false">询价单创建人</th>
					<th data-options="field:'quotedCode',width:90,sortable:false">报价单号</th>
					<th data-options="field:'quotedDate',width:95,sortable:false">供应商报价日期</th>
					<th data-options="field:'quotedCreated',width:95,sortable:false">报价单上传日期</th>
			</tr>
		</thead>
	</table>
</div>
<div style="padding: 10px;"></div>
<form id="uploadMDForm" method="post" enctype="multipart/form-data" style="padding-bottom: 2px;">
	<a onclick="supplierAttachmentAFn.searchAQS()" plain="true" class="easyui-linkbutton"  data-options="iconCls:'search'">关联系统中已有扫描版报价单 </a>
	<br/>
	<label>上传扫描版附件</label>
	<select id="intentionSupper" style="width: 150px;" name="fileType" onchange="supplierAttachmentAFn.change()">
		<option value=""> </option>
		<option value="供应商公函">供应商公函</option>
		<option value="其它">其它</option>
	</select>
	<label
		class="otherName" style="display: none;">请填写附件类型：</label><input id="otherName" name="elseType" class="easyui-validatebox otherName" style="display: none;width:120px;"
		data-options="required:false,validType:'length[1,10]'" />
	<br/>
	
	<input type="text" class="file" id="viewfileSp" style="width: 0px;" name="viewfileOmr" />
	<input type="file" id="file" style="height:19px" class="input_file" name="upload" onchange="document.getElementById('viewfileSp').value=this.value;" /> &nbsp;
		
	<input class="input_file" type="button" value="导入" id="subBtn" onClick="supplierAttachmentAFn.importFromExcel()" style="margin-top: 3px; margin-left: 0px;" />
</form>
<div style="padding: 10px;"></div>
<div id="tb2" style="height: 223px;">
	<div id="supplierAttachmentA_toolbar_sc">
		<a onclick="supplierAttachmentASacn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'back'"> 刷新 </a> 
		<a id="remove" onclick="supplierAttachmentASacn.remove()" plain="true"	class="easyui-linkbutton" data-options="iconCls:'delete'"> 删除附件 </a>
			<a onclick="supplierAttachmentASacn.downloadFile()" plain="true" class="easyui-linkbutton" data-options="iconCls:'download'"> 下载文件 </a>
	</div>
	<table id="supplierAttachmentA_grid_sc" class="easyui-datagrid" toolbar="#supplierAttachmentA_toolbar_sc" title="已上传文件列表:" fit="true" singleSelect="false" pagination="true" pagePosition='bottom'
		pageSize="5" pageList="[ 5, 10, 15, 20 ]" data-options="rownumbers:true"
		url="supplierAttachmentA_listSupplierAttachmentA_2.html?applicationCode=${applicationCodeNow}">
		<thead>
			<tr>
				<th align="center" data-options="width:40,checkbox:true"></th>
				<th data-options="field:'intentionCode',width:120,sortable:false">意向品编号</th>
				<th data-options="field:'intentionName',width:150,sortable:false">意向品名称</th>
				<th
					data-options="field:'intentionSupplierCode',width:150,sortable:false,formatter:function(value,row){
																				 if(value=='null'||value==null){
																				 	return row.supplierCode
																				 }else{
																				 	return value
																				 }
																				} ">供应商/意向品供应商编号</th>
				<th
					data-options="field:'intentionSupplierName',width:150,sortable:false,formatter:function(value,row){
																				 if(value==null){
																				 	return row.supplierName
																				 }else{
																				 	return value
																				 }
																				} ">供应商/意向品供应商名称</th>
					<th data-options="field:'enquiryCode',width:90,sortable:false">询价单号</th>
					<th data-options="field:'enquiryCreated',width:100,sortable:false">询价单创建日期</th>
					<th data-options="field:'enquiryCreateby',width:80,sortable:false">询价单创建人</th>
					<th data-options="field:'quotedCode',width:	90,sortable:false">报价单号</th>
					<th data-options="field:'quotedDate',width:100,sortable:false">供应商报价日期</th>
					<th data-options="field:'quotedCreated',width:100,sortable:false">报价单上传日期</th>
					<th data-options="field:'elseType',width:80,sortable:false">文件类型</th>
					<th data-options="field:'fileName',width:220,sortable:false,formatter:function(value,row){
							 return '<div title='+value+' >' + value + '</div>'
						}">文件名</th>
			</tr>
		</thead>
	</table>
</div>
<div style="padding: 10px;"></div>
<div id="tb3" style="height: 223px;">
	<div id="supplierAttachmentA_toolbar_gl">
		<a onclick="supplierAttachmentAGLFn.searchGL()" plain="true" class="easyui-linkbutton" data-options="iconCls:'back'"> 刷新 </a>
		<a onclick="supplierAttachmentAGLFn.deleteGL()" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">取消关联</a>
		<a onclick="supplierAttachmentAGLFn.downFileGL()" plain="true" class="easyui-linkbutton" data-options="iconCls:'download'"> 下载报价单 </a>
	</div>
	<table id="supplierAttachmentA_grid_gl" class="easyui-datagrid" toolbar="#supplierAttachmentA_toolbar_gl" title="已关联扫描版报价单:" fit="true" singleSelect="false" pagination="true" pagePosition='bottom'
		fitColumns="true" pageSize="5" pageList="[ 5, 10, 15, 20 ]" data-options="rownumbers:true" url="quotedElectronicOfScan_listQuotedElectronicOfScan_2.html?applicationCode=${applicationCodeNow}">
		<thead>
			<tr>
				<th align="center" data-options="width:40,checkbox:true"></th>
				<th data-options="field:'intentionSupplierCode',width:150,sortable:true,formatter:function(value,row){
																				 if(value=='null'||value==null){
																				 	return row.supplierCode
																				 }else{
																				 	return value
																				 }
																				}">供应商编号</th>
				<th data-options="field:'intentionSupplierName',width:150,sortable:false,formatter:function(value,row){
																				 if(value==null){
																				 	return row.supplierName
																				 }else{
																				 	return value
																				 }
																				} ">供应商名称</th>
				<th data-options="field:'quotedCodeElectronic',width:120,sortable:true">询价单号</th>
				<th data-options="field:'quotedDate',width:120,sortable:false">供应商报价日期</th>
				<th data-options="field:'createDate',width:120,sortable:false">报价单导入日期</th>
			</tr>
		</thead>
	</table>
</div>
</#compress>