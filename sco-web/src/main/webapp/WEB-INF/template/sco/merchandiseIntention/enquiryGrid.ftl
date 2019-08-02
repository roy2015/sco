<#compress>
<script type="text/javascript" >
	<#include "sco/merchandiseIntention/enquiryGrid.js" />
</script>
<body> 
<input type="hidden"  name="intentionCode" id="intentionCode" value="${merchandiseIntention.intentionCode}" />
	<div>
	 	<table>
	 		<tr>
				<td>
					最晚报价日期 <input class="easyui-datebox filterInput" filterName="enquiryDate" type="text" name="enquiryDate" id="enquiryDate" data-options="required:true,editable:false" style="width:150px;" />
				</td>
				<td>
					<a id="exportEnquiry" onclick="exportEnquiryFn.exportEnquiry();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >导出询价单</a>
				</td>
	 		</tr>
	 	</table>
	 </div>
 	
 	 <table id="panel2SupplierGrid"
		     class="easyui-datagrid"
		     title="已选供应商" 
		     singleSelect="true"
		     checkOnSelect="true"
	   		 selectOnCheck="true"
			 pagination = "true"
			 pagePosition = 'bottom'
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
		     nowrap="true"
			 toolbar="#panel2_toolbar"
			 url="merchandiseIntention_listIntentionSupplierMerchandise_2.html?intentionCode=<#if merchandiseIntention.intentionCode?? >${merchandiseIntention.intentionCode}<#else></#if>"
		     data-options="rownumbers:true"> 
		   
		    <thead>
		   		<tr>
		   			<th data-options="field:'supplierAddress'" hidden="true"></th>
		   			<th data-options="field:'NULL',checkbox:true"></th>
		   			<th data-options="field:'intentionSupplierCode',width:150">
						<span >供应商编号</span>
					</th>
					<th data-options="field:'intentionSupplierName',width:250">
						<span >供应商名称</span>
					</th>
		   		</tr>
		   	</thead>
	</table> 
</body>	
</#compress>