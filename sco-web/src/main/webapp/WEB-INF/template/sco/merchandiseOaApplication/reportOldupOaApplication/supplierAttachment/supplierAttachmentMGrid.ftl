<#compress>
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/merchandiseOaApplication/reportOldupOaApplication/supplierAttachment/supplierAttachmentMModel.js" />
</script>
<!-- 供应商附件管理主表 -->
<div id="tb1" style="height: 223px;">
	<table id="supplierAttachmentM_grid" class="easyui-datagrid" title="本次申请引进的商品列表" fitColumns="true" fit="true" singleSelect="true" pagination="true" pagePosition='bottom' pageSize="5" pageList="[ 5, 10, 15, 20 ]"
		url="supplierAttachmentM_listMerchandiseSupplier_2.html?intentionAndSupplierCodes=${intentionAndSupplierCodes}" data-options="rownumbers:true">

		<thead>
			<tr>
				<th data-options="width:40,checkbox:true,halign:'left'"></th>
				<th data-options="field:'merchandiseCode',width:150,sortable:false,halign:'left'">商品编号</th>
				<th data-options="field:'merchandiseName',width:150,sortable:false,halign:'left'">商品名称</th>
				<th data-options="field:'supplierCode',width:150,sortable:false,halign:'left'">供应商编号</th>
				<th data-options="field:'supplierName',width:150,sortable:false,halign:'left'">供应商名称</th>
			</tr>
		</thead>
	</table>
</div>
<div style="padding: 10px;"></div>
<form id="uploadMDForm" method="post" enctype="multipart/form-data">
	<label>上传扫描版附件</label>
	<select id="intentionSupper" style="width: 150px;" name="attachmentType" onchange="supplierAttachmentMFn.change()">
		<option value=""> </option>
		<option value="感官标准">感官标准</option>
		<option value="其它">其它</option>
	</select>
	<label
		class="otherName" style="display: none;">请填写附件类型：</label><input id="otherName" name="elseAttachmentType" class="easyui-validatebox otherName" style="display: none;"
		data-options="required:false,validType:'length[1,10]'" />
	<input name="viewfileOmr" type="text" id="viewfileSp" class="file" style="width: 0px;" />
	<input type="file" id="file" style="width: 200px" class="input_file" id="uploadSp" name="upload" onchange="document.getElementById('viewfileSp').value=this.value;" /> &nbsp;
	<input class="input_file" type="button" value="导入" id="subBtn" onClick="supplierAttachmentMFn.importFromExcel()" style="margin-top: 3px; margin-left: 0px" />
</form>
<div style="padding: 10px;"></div>
<div id="tb2" style="height: 273px;">
	<div id="supplierAttachmentM_toolbar_sc">
		<a onclick="supplierAttachmentMSCFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'back'"> 刷新 </a> <a id="remove" onclick="supplierAttachmentMSCFn.remove()" plain="true"
			class="easyui-linkbutton" data-options="iconCls:'delete'"> 删除附件 </a>
	</div>
	<table id="supplierAttachmentM_grid_sc" class="easyui-datagrid" toolbar="#supplierAttachmentM_toolbar_sc" title="已上传文件列表:" fit="true" fitColumns="true" singleSelect="false" pagination="true" pagePosition='bottom'
		pageSize="5" pageList="[ 5, 10, 15, 20 ]" data-options="rownumbers:true"
		url="supplierAttachmentM_listSupplierAttachmentM_2.html?applicationCode=${applicationCode}&intentionAndSupplierCodes=${intentionAndSupplierCodes}">
		<thead>
			<tr>
				<th data-options="width:40,checkbox:true,halign:'left'"></th>
				<th data-options="field:'merchandiseCode',width:150,sortable:true,halign:'left'">商品编号</th>
				<th data-options="field:'merchandiseName',width:150,sortable:false,halign:'left'">商品名称</th>
				<th data-options="field:'supplierCode',width:150,sortable:true,halign:'left'">供应商编号</th>
				<th data-options="field:'supplierName',width:150,sortable:false,halign:'left'">供应商名称</th>
				<th data-options="field:'attachmentType',width:150,sortable:false,halign:'left',formatter:supplierAttachmentMSCFn.downloadFile">附件类型</th>
			</tr>
			<tr>
				<th></th>
				<th><input style="width: 100px; height: 14px;" name="intentionCode_sc" class="filterInput" filterName="merchandiseCode"/></th>
				<th><input style="width: 100px; height: 14px;" name="intentionName_sc" class="filterInput" filterName="merchandiseName"/></th>
				<th><input style="width: 100px; height: 14px;" name="intentionSupplierCode_sc" class="filterInput" filterName="supplierCode"/></th>
				<th><input style="width: 100px; height: 14px;" name="intentionSupplierName_sc" class="filterInput" filterName="supplierName"/></th>
				<th><select id="supplierAttachmentType" class="filterSelect" filterName="attachmentType">
						<option value=""> </option>
						<option value="感官标准">感官标准 </option>
						<option value="其它">其它</option>
					</select>
				</th>
			</tr>
		</thead>
	</table>
</div>
<div style="padding: 10px;"></div>
<div id="tb3" style="height: 273px;">
	<div id="supplierAttachmentM_toolbar_gl">
		<a onclick="supplierAttachmentMGLFn.searchGL()" plain="true" class="easyui-linkbutton" data-options="iconCls:'back'"> 刷新 </a>
	</div>
	<table id="supplierAttachmentM_grid_gl" class="easyui-datagrid" toolbar="#supplierAttachmentM_toolbar_gl" title="已关联扫描版 核算表或投料表列表:" fit="true" fitColumns="true" singleSelect="false" pagination="true" pagePosition='bottom'
		pageSize="5" pageList="[ 5, 10, 15, 20 ]" data-options="rownumbers:true" url="supplierAttachmentM_listSupplierAttachmentMInFrom_2.html?applicationCode=${applicationCode}&intentionAndSupplierCodes=${intentionAndSupplierCodes}">
		<thead>
			<tr>
				<th data-options="field:'merchandiseCode',width:150,sortable:true,halign:'left'">商品编号</th>
				<th data-options="field:'merchandiseName',width:150,sortable:false,halign:'left'">商品名称</th>
				<th data-options="field:'supplierCode',width:150,sortable:true,halign:'left'">供应商编号</th>
				<th data-options="field:'supplierName',width:150,sortable:false,halign:'left'">供应商名称</th>
				<th data-options="field:'typeCode',width:100,sortable:false,halign:'left',formatter:supplierAttachmentMGLFn.downloadFileGL">附件类型</th>
				<th data-options="field:'createDate',width:100,sortable:true,halign:'left'">报价日期</th>
			</tr>
			<tr>
				<th><input style="width: 100px; height: 14px;" name="intentionCode_gl" class="filterInput" filterName="merchandiseCode"/></th>
				<th><input style="width: 100px; height: 14px;" name="intentionName_gl" class="filterInput" filterName="merchandiseName"/></th>
				<th><input style="width: 100px; height: 14px;" name="intentionSupplierCode_gl" class="filterInput" filterName="supplierCode"/></th>
				<th><input style="width: 100px; height: 14px;" name="intentionSupplierName_gl" class="filterInput" filterName="supplierName"/></th>
				<th><select id="type_gl" class="filterSelect" filterName="typeCode">
					<option value="" ></option>
					<option value="1" >投料表</option>
					<option value="2" >核算表</option>
				</select>
				</th>
				<th></th>
			</tr>
		</thead>
	</table>
</div>
</#compress>
