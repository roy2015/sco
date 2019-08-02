<style>
table th{text-align:right;font-weight: normal;}
</style>
<table width="100%">
	<tr>
		<!--<th style="width:80px">供应商编号-供应商名称:</th>-->
		<td style="width:150px;">
			<!-- <select class="easyui-combogrid" 
				id="supplierCode" 
				name= "supplierName"
	    		filterName="supplierCode" 
	    		style="width:104px;"
	    		fitColumns:true,
			    data-options="fit:true
				panelWidth:'220',
				rownumbers:true,
				idField:'id',
				textField:'text',
				editable:false,
				url:'supplierData_listAllSupplier_5.html',
				columns:[[
							 {field:'id',title:'供应商编号',width:20},
							 {field:'text',title:'供应商名称',width:20}
				    	]]
				 ">
			</select>-->
			
			<select name="supplierCode" id="supplierCode" class="easyui-combogrid filterSelect" filterName="supplierCode" style="width:150px;"
						 		data-options="panelWidth:330,
						 		rownumbers:true,
					            idField:'id',
					            textField:'text',
					            mode: 'remote',
					            fitColumns:true,
					            pagination:false,
					            url:'supplierData_listAllSupplierFl_5.html',
					            columns:[[
					                {field:'id',title:'供应商编号',width:50},
					                {field:'text',title:'供应商名称',width:60}
					            ]]
						"/>
		</td>
	</tr>
</table>