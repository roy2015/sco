<#compress>
	<script type="text/javascript" src="${copoxtPath}js/gridEdit.js" charset="utf-8"></script>
    <script type="text/javascript" src="${copoxtPath}js/plugin/jquery.form.js" charset="utf-8"></script>
	<link rel="stylesheet" href="css/table.min.css" type="text/css" />
	<script type="text/javascript">
		<#include "sco/merchandiseOaApplication/fastImportOaApplication/attachment/attachmentModel.js" />
	</script>
	<div style="height: 263px;">
		<table  id="fiAttachment_grid" 
				class="easyui-datagrid" 
				title="本次快速引进商品列表" 
				fit="true" 
				fitColumns="true"
				singleSelect="true" 
				pagination="true" 
				pagePosition='bottom' 
				pageSize="5" pageList="[ 5, 10, 15, 20 ]"
				url="fastImportAttachment_listIntentionSupplier_2.html?intentionAndSupplierCodes=${intentionAndSupplierCodes}"
				data-options="rownumbers:true"
		>
			 <thead>
				<tr>
					<th data-options="width:40,checkbox:true,halign:'left'"></th>
					<th data-options="field:'intentionCode',width:160,sortable:false,halign:'left'">意向品编号</th>
					<th data-options="field:'intentionName',width:200,sortable:false,halign:'left'">意向品名称</th>
					<th data-options="field:'intentionSupplierCode',width:160,sortable:false,halign:'left',
						formatter:function(value,row){
							if(value==''||value==null){
								return row.supplierCode;	
							}else{
								return value;
							}
					}">供应商编号</th>
					<th data-options="field:'intentionSupplierName',width:210,sortable:false,halign:'left',
					formatter:function(value,row){
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
	<form id="uploadAttachmentForm" method="post" enctype="multipart/form-data" style="padding-bottom: 2px;">
		<label style="width: 93px; float: left; padding-top: 3px; padding-left: 6px;">上传扫描版附件</label>
		<input name="applicationFile" type="text" id="applicationFile" class="file" style="width: 0px;" />
		<input type="file" class="input_file" id="fiUploadFile" name="upload" onchange="document.getElementById('applicationFile').value=this.value;" style="width: 200px" /> &nbsp;
		<input class="input_file" type="button" value="导入" id="subBtn" onClick="attachmentFn.importFile()" style="margin-top: 3px; margin-left: 0px" />
	</form>
	<div style="padding: 10px;"></div>
	
	<div id="fiAttachmentM_toolbar">
		<a id="remove" onclick="attachmentFn.remove()" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'"> 删除附件 </a>
	</div>
	<table  id="fiUploadFile_grid" 
			toolbar="#fiAttachmentM_toolbar" 
			title="已上传文件列表" 
			style="height:300px"
			singleSelect="false" 
			pagination="true" 
			pagePosition='bottom'
			pageSize="5" pageList="[ 5, 10, 15, 20 ]" 
			url="fastImportAttachment_listApplicationFiles_2.html?applicationCode=${applicationCode}&intentionAndSupplierCodes=${intentionAndSupplierCodes}"
			data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'reportCode',width:40,checkbox:true,halign:'left'"></th>
				<th data-options="field:'reportFileName',width:250,sortable:false,halign:'left',formatter:attachmentFn.formatFileName">
	            	申请文件名称
	            </th>
				<th data-options="field:'uploadDate',width:150,sortable:false,halign:'left',formatter: function(value,row,index){
					  var str = value;
			         if(str != undefined && (str != null)){  
			             var arr = str.split('.');
			             str = arr[0];
			           	 str = str.replace(/-/g,'/');
			         	 var date = new Date(str);
			          	 return date.format('yyyy-MM-dd');
			          }else{
			          	return '';
			          }
				}">
	            	上传日期
	            </th>
		</thead>
	</table>
</#compress>
