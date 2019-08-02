<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	<#include "sco/accessoryIntention/exportEnquiryForm.js" />
</script>
</head>
<body>
<div style="padding: 5px;"></div>
	<div>
	 	<table>
	 		<tr>
	 		    <td>最晚报价日期:</td>
				<td>
					<input class="easyui-datebox filterInput" filterName="enquiryDate" type="text" name="enquiryDate" id="enquiryDate" data-options="required:true,editable:false" style="width:150px;" />
				</td>
				  <td>要求到货日期:</td>
				<td>
					<input class="easyui-datebox filterInput" filterName="receiveDate" type="text" name="receiveDate" id="receiveDate" data-options="required:false,editable:false" style="width:150px;" />
				</td>
				<td>
					<a id="exportAccessoryEnquiry" onclick="exportEnquiryFormFn.accessoryEnquiry2Excel('${accessoryIntention.intentionCode}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" >导出询价单</a>
				</td>
	 		</tr>
	 	</table>
	 </div>
	 <div style="padding: 5px;"></div>
  <table  id="accessoryIntentionSupplier_dcxjd"
		    class="easyui-datagrid"
		     title="已选供应商" 
		     singleSelect="true"
		     checkOnSelect="true"
	   		 selectOnCheck="true"
		     singleSelect="false"
			 pagination = "true"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
		     nowrap="false"
			toolbar="#accessoryIntentions_toolbar"
			url='accessoryIntention_listAccessoryIntentionSupplier_2.html?intentionCode=${accessoryIntention.intentionCode}'
		    data-options="rownumbers:true" >  
        <thead>
        	<tr>
        	    <th data-options="width:65,checkbox:true">
                </th>
				<th data-options="field:'intentionSupplierCode',width:500,sortable:true">
                	<span >供应商编号</span>
                </th>
				<th data-options="field:'intentionSupplierName',width:530,sortable:true">
                	<span >供应商名称</span>
                </th>
            </tr>
            
        </thead>
    </table>
    <div style="padding: 10px;"></div>
      <table  id="accessoryEnquiry_dcxjd"
		    class="easyui-datagrid"
		     title="已有询价单列表" 
		     singleSelect="true"
		     checkOnSelect="true"
	   		 selectOnCheck="true"
		     singleSelect="false"
			 pagination = "true"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
		     nowrap="false"
			toolbar="#accessoryIntentions_toolbar"              
			url='accessoryIntention_listAccessoryEnquiry_2.html?intentionCode=${accessoryIntention.intentionCode}'
		    data-options="rownumbers:true" >  
        <thead>
        	<tr>
        	    <th data-options="width:65,checkbox:true">
                </th>
				<th data-options="field:'enquiryCode',width:220,sortable:true">
                	<span >询价单编号</span>
                </th>
                <th data-options="field:'enquiryName',width:220,sortable:true"><span >询价单名称</span></th>
				<th data-options="field:'created',width:210,sortable:true">
                	<span >询价单创建日期</span>
                </th>
                <th data-options="field:'supplierCount',width:220,sortable:true,align:'right',formatter: function(value,row,index){
							if(value!=null && value!=''){
								return value;
							}else{
								return 0;
							}
						}">
                	<span >报价供应商数量</span>
                </th>
                <th data-options="field:'createUserName',width:155,sortable:true">
                	<span >询价单创建人</span>
                </th>
            </tr>
            
        </thead>
    </table> 
</body>
</html>
</#compress>