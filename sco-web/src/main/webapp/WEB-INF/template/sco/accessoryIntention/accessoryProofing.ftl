<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	<#include "sco/accessoryIntention/accessoryProofing.js" />
</script>
</head>
<body>
  <table>
     <tr>
	                 <td><a id="closeForm" onclick="accessoryProofingFn.showUpdateProofing('${accessoryIntention.intentionCode}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" ;">
								新增打样信息
                         </a>
                     </td>
                 </tr> 
	 	</table>
	
	  <table  id="accessoryQuotedElectronic_dyxx"
		    class="easyui-datagrid"
		     title="已导入电子报价单列表" 
		     singleSelect="true"
		     checkOnSelect="true"
	   		 selectOnCheck="true"
			 pagination = "true"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
		     nowrap="false"
			toolbar="#toolbar"
			url='accessoryIntention_listAccessoryQuotedElectronic_2.html?intentionCode=${accessoryIntention.intentionCode}'
		    data-options="rownumbers:true" >  
        <thead>
        		<tr>
        	    <th data-options="width:65,checkbox:true">
                </th>
				<th data-options="field:'quotedCode',width:160,sortable:true">
                	<span >报价单编号</span>
                </th>
				<th data-options="field:'intentionSupplierCode',width:150,sortable:true,formatter:function(value,row){
																				 if(row.supplierCode == null) {
																					return row.intentionSupplierCode;
																				 }else {
																					return row.supplierCode;
																				 }
																				}">
                	<span >供应商编号</span>
                </th>
                <th data-options="field:'supplierName',width:300,sortable:true,formatter:function(value,row){
																				 if(row.supplierName == null) {
																					return row.intentionSupplierName;
																				 }else {
																					return row.supplierName;
																				 }
																				}">
                	<span >供应商名称</span>
                </th>
				<th data-options="field:'enquiryCode',width:150,sortable:true">
                	<span >询价单号</span>
                </th>
                <th data-options="field:'quotedDate',width:150,sortable:true">
                	<span >供应商报价日期</span>
                </th>
				<th data-options="field:'created',width:120,sortable:true">
                	<span >报价单导入日期</span>
                </th>
            </tr>
            
        </thead>
    </table>
    
    <div style="padding: 10px;"></div>
     <table>
     <tr>
	                <td><a id="closeForm" onclick="accessoryProofingFn.deleteAccessoryProofing('${accessoryIntention.intentionCode}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" ;">
								删除样品信息
                         </a>
                     </td>
                     <td>
                     <a onclick="accessoryProofingFn.exportAccessoryProofingToExcel('${accessoryIntention.intentionCode}');"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">
				导出样品信息
			</a> </td>
                 </tr> 
	 	</table>
	
	  <table  id="accessoryProofing_dyxx"
		    class="easyui-datagrid"
		     title="已录入的样品信息" 
			 pagination = "true"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
		     nowrap="true"
			toolbar="#toolbar"
			url='accessoryIntention_listAccessoryProofing_2.html?intentionCode=${accessoryIntention.intentionCode}'
		    data-options="rownumbers:true" >  
        <thead>
        	<tr>
        	    <th data-options="field:'proofingCode',width:65,checkbox:true">
                </th>
				<th data-options="field:'quotedCode',width:90,sortable:true">
                	<span >报价单编号</span>
                </th>
				<th data-options="field:'intentionSupplierCode',width:70,sortable:true,formatter:function(value,row){
																				 if(row.supplierCode == null || row.supplierCode=='null') {
																					return row.intentionSupplierCode;
																				 }else {
																					return row.supplierCode;
																				 }
																				}">
                	<span >供应商编号</span>
                </th>
                <th data-options="field:'supplierName',width:95,sortable:true,formatter:function(value,row){
																				 if(row.supplierName == null) {
																					return row.intentionSupplierName;
																				 }else {
																					return row.supplierName;
																				 }
																				}">
                	<span >供应商名称</span>
                </th>
				<th data-options="field:'enquiryCode',width:90,sortable:true">
                	<span >询价单号</span>
                </th>
                <th data-options="field:'quotedDate',width:90,sortable:true">
                	<span >供应商报价日期</span>
                </th>
				<th data-options="field:'quotedCreateDate',width:90,sortable:true">
                	<span >报价单导入日期</span>
                </th>
                <th data-options="field:'created',width:55,sortable:true,formatter:function(value,row){
																				 if(row.remarks == null) {
																					return '是';
																				 }else {
																					return '否';
																				 }
																				}">
                	<span >是否打样</span>
                </th>
                <th data-options="field:'askProofingDate',width:85,sortable:true">
                	<span >要求打样日期</span>
                </th>
                <th data-options="field:'proofingDate',width:85,sortable:true">
                	<span >打样完成日期</span>
                </th>
                <th data-options="field:'proofingCycle',width:60,sortable:true">
                	<span >打样周期</span>
                </th>
                <th data-options="field:'proofingContent',width:60,sortable:true">
                	<span >打样内容</span>
                </th>
                 <th data-options="field:'proofingEvaluate',width:60,sortable:true">
                	<span >打样评价</span>
                </th>
                 <th data-options="field:'path',width:60,sortable:true,formatter:function(value, rowData, rowIndex){
																				 if(rowData.path != null) {
																					return '<a href=# style=color:maroon;cursor:hand; onclick=accessoryProofingFn.downloadProofingPicture(\''+rowIndex+'\') >'+'有'+'</a>';
																				 }else {
																					return '无';
																				 }
																				}">
                	<span >样品图片</span>
                </th>
                 <th data-options="field:'remarks',width:30,sortable:true">
                	<span >备注</span>
                </th>
            </tr>
            
        </thead>
    </table>
</body>
</html>
</#compress>