<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	<#include "sco/accessoryOaApplication/committeeApply/wlInfo.js" />
</script>
</head>
<body>
<div id="appScheNonFood_toolbar">
		<a onclick="wlInfoFn.saveWlInfo();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
			保存
		</a>
	</div>
<form id="wlInfo_form" method="post">
 <#-- 	<table class="table table-bordered table-condensed" style="margin-left:10px">
		<tbody>
		<thead>
			<tr>
				<th style="text-align:center"><span class="txtCenter">意向品/商品编号:</span></th>
				<th style="text-align:center"><span class="txtCenter">意向品/商品名称:</span></th>
				<th style="text-align:center"><span class="txtCenter">供应商/意向供应商编号:</span></th>
				<th style="text-align:center"><span class="txtCenter">供应商/意向供应商名称:</span></th>
				<th style="text-align:center"><span class="txtCenter">报价单号:</span></th>
				<th style="text-align:center"><span class="txtCenter">询价数量:</span></th>
				<th style="text-align:center"><span class="txtCenter">实际采购数量:</span></th>
				<th style="text-align:center"><span class="txtCenter">合同进价:</span></th>
				<th style="text-align:center"><span class="txtCenter">实际采购金额:</span></th>
				<th style="text-align:center"><span class="txtCenter">发票类型:</span></th>
				<th style="text-align:center"><span class="txtCenter">SAP物料号:</span></th>
				<th style="text-align:center"><span class="txtCenter">SAP供应商号:</span></th>
				<th style="text-align:center"><span class="txtCenter">实际引进规格:</span></th>
			</tr>
			</thead>
			<#list wlInfoList as wlInfo>
			<tr>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].intentionCode" name="wlInfoTextList[${wlInfo_index}].intentionCode" style="width: 220px;"
					value="${wlInfo.intentionCode}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].intentionName" name="wlInfoTextList[${wlInfo_index}].intentionName" style="width: 220px;"
					value="${wlInfo.intentionName}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].supplierCode" name="wlInfoTextList[${wlInfo_index}].supplierCode" style="width: 220px;"
					value="${wlInfo.supplierCode}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].supplierName" name="wlInfoTextList[${wlInfo_index}].supplierName" style="width: 220px;"
					value="${wlInfo.supplierName}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].quotedCode" name="wlInfoTextList[${wlInfo_index}].quotedCode" style="width: 220px;"
					value="${wlInfo.quotedCode}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].enquiryCount" name="wlInfoTextList[${wlInfo_index}].enquiryCount" style="width: 220px;"
					value="${wlInfo.enquiryCount}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].purchaseCount" name="wlInfoTextList[${wlInfo_index}].purchaseCount" style="width: 220px;"
					value="${wlInfo.purchaseCount}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].contractPrice" name="wlInfoTextList[${wlInfo_index}].contractPrice" style="width: 220px;"
					value="${wlInfo.contractPrice}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].purchaseMoney" name="wlInfoTextList[${wlInfo_index}].purchaseMoney" style="width: 220px;"
					value="${wlInfo.purchaseMoney}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].invoiceType" name="wlInfoTextList[${wlInfo_index}].invoiceType" style="width: 220px;"
					value="${wlInfo.invoiceType}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].accessorySapCode" name="wlInfoTextList[${wlInfo_index}].accessorySapCode" style="width: 220px;"
					value="${wlInfo.accessorySapCode}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].supplierSapCode" name="wlInfoTextList[${wlInfo_index}].supplierSapCode" style="width: 220px;"
					value="${wlInfo.supplierSapCode}" /></td>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"
					id="wlInfoTextList[${wlInfo_index}].sjyjSpecification" name="wlInfoTextList[${wlInfo_index}].sjyjSpecification" style="width: 220px;"
					value="${wlInfo.sjyjSpecification}" /></td>
				
			</#list>
		</tbody>
	</table>  -->
	  <div>
<table  id="wlInfo_grid"
		    class="easyui-datagrid"
			url='committeeApply_listWlInfo_2.html?applicationCodeNow=${applicationCodeNow}&quotedCodes=${quotedCodes}'
			style="width:1100px;height:350px"
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
				<th data-options="field:'id',width:65,checkbox:true"></th>
				<th data-options="field:'intentionCode',width:120,sortable:false">
                	<span class="txtCenter">意向品/商品编号</span>
                </th>
				<th data-options="field:'intentionName',width:120,sortable:false">
                	<span class="txtCenter">意向品/商品名称</span>
                </th>
				<th data-options="field:'supplierCode',width:130,sortable:false,formatter:function(value,row){
																				 if(row.supplierCode == null) {
																					return row.intentionSupplierCode;
																				 }else {
																					return row.supplierCode;
																				 }
																				}">
                	<span class="txtCenter">供应商/意向供应商编号</span>
                </th>
				<th data-options="field:'supplierName',width:130,sortable:false,formatter:function(value,row){
																				 if(row.supplierName == null||row.supplierName =='') {
																					return row.intentionSupplierName;
																				 }else {
																					return row.supplierName;
																				 }
																				}">
                	<span class="txtCenter">供应商/意向供应商名称</span>
                </th>
				<th data-options="field:'quotedCode',width:100,sortable:false">
                	<span class="txtCenter">报价单号</span>
                </th>
				<th data-options="field:'enquiryCount',width:100,sortable:false">
                	<span class="txtCenter">询价数量</span>
                </th>
                <th data-options="field:'purchaseCount',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                																if(value !=null){
																				 return '<input id=purchaseCount'+rowIndex+'  onchange=wlInfoFn.testaler(\''+rowIndex+'\',\''+rowData.purchaseCount+'\') value='+rowData.purchaseCount+' >'
																				 }else{
																				 return '<input id=purchaseCount'+rowIndex+'  onchange=wlInfoFn.testaler(\''+rowIndex+'\',\''+rowData.purchaseCount+'\') value='+'\'\' />'
																				 }
																				}">
                	<span class="txtCenter">实际采购数量</span>
                </th>
                 <th data-options="field:'contractPrice',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value !=null){
																				 return '<input id=contractPrice'+rowIndex+' onchange=wlInfoFn.testaler(\''+rowIndex+'\',\''+rowData.contractPrice+'\') value='+rowData.contractPrice+' >'
																				 }else{
																				 return '<input id=contractPrice'+rowIndex+' onchange=wlInfoFn.testaler(\''+rowIndex+'\',\''+rowData.contractPrice+'\') value='+'\'\' />'
																				 }
																				}">
                	<span class="txtCenter">合同进价</span>
                </th>
                 <th data-options="field:'purchaseMoney',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value!=null){
																				 return '<input id=purchaseMoney'+rowIndex+' value='+rowData.purchaseMoney+' disabled=true/>'
																				 }else{
																				 return '<input id=purchaseMoney'+rowIndex+' value=\'\' disabled=true/>'
																				 }
																				}">
                	<span class="txtCenter">实际采购金额</span>
                </th>
                 <th data-options="field:'invoiceType',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value!=null){
																				 return '<input id=invoiceType'+rowIndex+' value='+rowData.invoiceType+'>'
																				 }else{
																				 return '<input id=invoiceType'+rowIndex+' value=\'\' />'
																				 }
																				}">
                	<span class="txtCenter">发票类型</span>
                </th>
                 <th data-options="field:'accessorySapCode',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value!=null){
																				 return '<input id=accessorySapCode'+rowIndex+' value='+rowData.accessorySapCode+'>'
																				 }else{
																				 return '<input id=accessorySapCode'+rowIndex+' value=\'\' />'
																				 }
																				}">
                	<span class="txtCenter">SAP物料号</span>
                </th>
                 <th data-options="field:'supplierSapCode' ,width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value!=null){
																				 return '<input id=supplierSapCode'+rowIndex+' value='+rowData.supplierSapCode+'>'
																				 }else{
																				 return '<input id=supplierSapCode'+rowIndex+' value=\'\' />'
																				 }
																				}">
                	<span class="txtCenter">SAP供应商号</span>
                </th>
                 <th data-options="field:'sjyjSpecification',width:100,sortable:false,formatter:function(value, rowData, rowIndex){
                 																if(value!=null){
																				 return '<input id=sjyjSpecification'+rowIndex+' value='+rowData.sjyjSpecification+'>'
																				 }else{
																				 return '<input id=sjyjSpecification'+rowIndex+' value=\'\' />'
																				 }
																				}">
                	<span class="txtCenter">实际引进规格</span>
                </th>
            </tr>
           
        </thead>
    </table>
    </div>
</form>
</body>
</html>
</#compress>