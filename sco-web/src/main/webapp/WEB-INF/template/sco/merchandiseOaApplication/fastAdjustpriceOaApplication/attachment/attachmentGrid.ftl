<#compress>
	<script type="text/javascript" src="${copoxtPath}js/gridEdit.js" charset="utf-8"></script>
    <script type="text/javascript" src="${copoxtPath}js/plugin/jquery.form.js" charset="utf-8"></script>
	<link rel="stylesheet" href="css/table.min.css" type="text/css" />
	<script type="text/javascript">
		<#include "sco/merchandiseOaApplication/fastAdjustpriceOaApplication/attachment/attachmentModel.js" />
	</script>
	<div style="height: 280px;">
		<table  id="attachment_grid" 
				class="easyui-datagrid" 
				title="本次快速调价商品列表" 
				fit="true" 
				fitColumns="true"
				singleSelect="true" 
				pagination="true" 
				pagePosition='bottom' 
				pageSize="5" pageList="[ 5, 10, 15, 20 ]"
				url="fastAdjustprice_listApplicationFileMerchandise_2.html?intentionAndSupplierCodes=${intentionAndSupplierCodes}" 
				data-options="rownumbers:true">
			 <thead>
	        	<tr>
	        		<th  data-options="width:40,checkbox:true,halign:'left'"></th>
					<th data-options="field:'merchandiseCode',width:150,sortable:true,halign:'left'">
	                	商品编号
	                </th>
					<th data-options="field:'merchandiseName',width:300,sortable:false,halign:'left'">
	                	商品名称
	                </th>
					<th data-options="field:'supplierCode',width:150,sortable:true,halign:'left'">
	                	供应商编号
	                </th>
					<th data-options="field:'supplierName',width:250,sortable:false,halign:'left'">
	                	供应商名称
	                </th>
	            </tr>
	        </thead>
		</table>
	</div>
	
	<div style="padding: 10px;"></div>
	<form id="uploadAttachmentForm" method="post" enctype="multipart/form-data" style="padding-bottom: 2px;">
		<label style="width: 93px; float: left; padding-top: 3px; padding-left: 6px;">上传扫描版附件</label>
		<input name="applicationFile" type="text" id="applicationFile" class="file" style="width: 0px;" />
		<input type="file" class="input_file" id="uploadFile" name="upload" onchange="document.getElementById('applicationFile').value=this.value;" style="width: 200px" /> &nbsp;
		<input class="input_file" type="button" value="导入" id="subBtn" onClick="attachmentFn.uploadFile()" style="margin-top: 3px; margin-left: 0px" />
	</form>
	<div style="padding: 10px;"></div>
	
	<div id="supplierAttachmentM_toolbar_sc">
		<a id="remove" onclick="attachmentFn.remove()" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'"> 删除附件 </a>
	</div>
	<div style="height: 300px;">
		<table  id="applicationFile_grid" 
				class="easyui-datagrid" 
				toolbar="#supplierAttachmentM_toolbar_sc" 
				title="已上传文件列表:" 
				fit="true" 
				singleSelect="false" 
				pagination="true" 
				pagePosition='bottom'
				pageSize="5" pageList="[ 5, 10, 15, 20 ]" 
				url="fastAdjustprice_listApplicationFiles_2.html?applicationCode=${applicationCode}&intentionAndSupplierCodes=${intentionAndSupplierCodes}"
				data-options="rownumbers:true">
			<thead>
				<tr>
					<th data-options="field:'reportCode',width:40,checkbox:true,halign:'left'"></th>
					<th data-options="field:'reportFileName',width:250,sortable:false,halign:'left',formatter:attachmentFn.formatFileName">
	                	申请文件名称
	                </th>
					<th data-options="field:'uploadDate',width:150,sortable:false,halign:'left',formatter: function(value,row,index){
								  var   str=value;
						         if(str!=undefined && (str!=null)){  
						             var  arr=str.split('.');
						             str=arr[0];
						           	 str=str.replace(/-/g,'/');
						         	 var date=new Date(str);
						          	 return  date.format('yyyy-MM-dd');
						          }else{
						          	return '';
						          }
							}">
	                	上传日期
	                </th>
			</thead>
		</table>
	</div>
</#compress>
