<#compress>
<head>  
    <script type="text/javascript" src="${copoxtPath}js/gridEdit.js" charset="utf-8"></script>
    <script type="text/javascript" src="${copoxtPath}js/plugin/jquery.form.js" charset="utf-8"></script>
    <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css"/>
<script type="text/javascript" >
	<#include "sco/merchandiseIntention/importQuotedGrid.js" />
</script>
 </head>
<body>
 <input type="hidden"  name="intentionCode" id="intentionCode" value="${merchandiseIntention.intentionCode}" />
	  <form id="uploadQuotedForm" method="post" enctype="multipart/form-data" style="margin:0px;display: inline">
	 	<table>
	 		<tr>
				<td>
					报价日期 <input class="easyui-datebox filterInput" filterName="quotedDate" type="text" name="quotedDate" id="quotedDate" data-options="required:true,editable:false" style="width:150px;" />&nbsp;&nbsp;
				</td>
				<td>
		        	<input type="text" class="file" id="quotedFile" name="quotedFile" style="width: 0px;" />
			        <input type="file" class="input_file" id="uploadQuoted" name="uploadQuoted" onchange="document.getElementById('quotedFile').value=this.value;" style="width: 200px" />&nbsp;
					<input class="input_file" type="button" value="导入报价单" onClick="importQuotedFn.importQuoted()"  style="margin-top: 3px; margin-left: 0px" />
			   </td>
	 		</tr>
	 	</table>
	</form>
 	
 	 <table id="panel3SupplierGrid"
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
			 toolbar="#panel3_toolbar"
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