<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	<#include "sco/accessoryOaApplication/nonFoodApply/dhInfo.js" />
</script>
</head>
<body>
<table>
     <tr>
	     <td><a id="closeForm" onclick="dhInfoFn.showUpdateDhFile('${applicationCodeNow}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" ;">
					上传新文件
	         </a>
	     </td>
	 </tr> 
</table>
<div>
<table  id="applicationQuoted_grid"
		    class="easyui-datagrid"
		    title="本次申请的意向品列表" 
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			fitColumns="true" 
			toolbar="#applicationQuoted_toolbar"
			style="width:1100px;height:250px"
			url='nonFoodApply_listApplicationQuotedForDhInfo_2.html?applicationCodeNow=${applicationCodeNow}'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
				<th data-options="width:65,checkbox:true"></th>
				<th data-options="field:'intentionCode',width:150,sortable:true">
                	<span class="txtCenter">意向品编号</span>
                </th>
				<th data-options="field:'intentionName',width:200,sortable:true">
                	<span class="txtCenter">意向品名称</span>
                </th>
				<th data-options="field:'supplierCode',width:150,sortable:true">
                	<span class="txtCenter">供应商/意向供应商编号</span>
                </th>
				<th data-options="field:'supplierName',width:300,sortable:true,formatter:function(value,row){
																				 if(row.supplierName == null) {
																					return row.intentionSupplierName;
																				 }else {
																					return row.supplierName;
																				 }
																				}">
                	<span class="txtCenter">供应商/意向供应商名称</span>
                </th>
            </tr>
        </thead>
    </table>
    </div>
     <table>
	     <tr>
	        <td>
	        	<a id="closeForm" onclick="dhInfoFn.deleteDhInfo('');" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" ;">
					删除
	             </a>
	         </td>
	     </tr> 
 	</table>
    <div>
		<table  id="dhInfo_grid"
			    title="大货信息列表" 
				pagination = "true"
				pagePosition = "bottom"
				pageSize = "5"
				pageList = "[ 5, 10, 15, 20 ]"
				url='nonFoodApply_listDhInfo_2.html?applicationCodeNow=${applicationCodeNow}'
				style="width:1100px;height:250px"
			    data-options="rownumbers:true">  
	        <thead>
	        	<tr>
					<th data-options="field:'id',width:65,checkbox:true"></th>
					<th data-options="field:'intentionCode',width:110,sortable:true">
	                	<span class="txtCenter">意向品编号</span>
	                </th>
					<th data-options="field:'intentionName',width:120,sortable:true">
	                	<span class="txtCenter">意向品名称</span>
	                </th>
					<th data-options="field:'supplierCode',width:140,sortable:true">
	                	<span class="txtCenter">供应商/意向供应商编号</span>
	                </th>
					<th data-options="field:'supplierName',width:150,sortable:true,formatter:function(value,row){
																					 if(row.supplierName == null) {
																						return row.intentionSupplierName;
																					 }else {
																						return row.supplierName;
																					 }
																					}">
	                	<span class="txtCenter">供应商/意向供应商名称</span>
	                </th>
					<th data-options="field:'fileType',width:80,sortable:true">
	                	<span class="txtCenter">文件类型</span>
	                </th>
					<th data-options="field:'fileName',width:230,sortable:true,formatter:function(value, rowData, rowIndex){
																					 if(rowData.fileName != null) {
																						return '<a href=# style=color:maroon;cursor:hand; onclick=dhInfoFn.downloadDhFile(\''+rowData.path+'\') >'+value+'</a>';
																					 }else {
																						return '无';
																					 }
																					}">
	                	<span class="txtCenter">文件名称</span>
	                </th>
					<th data-options="field:'created',width:130,sortable:true">
	                	<span class="txtCenter">上传时间</span>
	                </th>
	                <th data-options="field:'createUserName',width:80,sortable:true">
	                	<span class="txtCenter">上传人姓名</span>
	                </th>
	            </tr>
	        </thead>
	    </table>
    </div>
   </body>
</html>
</#compress>