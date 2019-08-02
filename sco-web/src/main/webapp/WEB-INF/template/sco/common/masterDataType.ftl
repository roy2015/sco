<style>
table th{text-align:right;font-weight: normal;}
</style>
<table width="100%">
	<tr>
		<th style="width:80px">中分类:</th>
		<td style="width:110px;">
			<select class="easyui-combogrid" 
				id="centreTypeCode" name= "centreTypeCode"
	    		filterName="centreType" style="width:104px;"
			    data-options="fit:true,fitColumns:true,
					onChange:masterDataTypeFn.setSmallType,
			    	panelWidth:'220',rownumbers:true,
				    idField:'id',textField:'text',editable:false,
				    url:'masterDataType_listCentreType_5.html',
				    columns:[[
							    {field:'id',title:'中分类编号',width:20},
							    {field:'text',title:'中分类名称',width:20}
				    		]]
				    ">
			</select>
		</td>
		<th style="width:70px">小分类:</th>
		<td style="width:110px;">
			<select class="easyui-combogrid" 
				id="smallTypeCode" name="smallTypeCode"
	    		filterName="smallType" style="width:104px;"
			    data-options="fit:true,fitColumns:true,
					onChange:masterDataTypeFn.setDetailType,  
			    	panelWidth:'220',rownumbers:true,
				    idField:'id',textField:'text',editable:false,
				    columns:[[
					    {field:'id',title:'小分类编号',width:20},
					    {field:'text',title:'小分类名称',width:20}
				    ]]">
			</select>
		</td>
		<th style="width:70px;">明细类:</th>
		<td style="width:110px;">
			<select class="easyui-combogrid" 
				id="detailTypeCode" name="detailTypeCode"
	    		filterName="detailType" style="width:104px;"
			    data-options="fit:true,fitColumns:true,rownumbers:true,
					onChange:masterDataTypeFn.setMinceType,  
			    	panelWidth:'220',
				    idField:'id',textField:'text',editable:false,
				    columns:[[
					    {field:'id',title:'明细类编号',width:20},
					    {field:'text',title:'明细类名称',width:20}
				    ]]">
			</select>
		</td>
		<th style="width:70px;">细分类:</th>
		<td style="width:110px;">
			<select class="easyui-combogrid" 
				id="minceTypeCode" name="minceTypeCode" 
	    		filterName="fineType" style="width:104px;"
			    data-options="fit:true,fitColumns:true,
			    	panelWidth:'220',rownumbers:true,
				    idField:'id',textField:'text',editable:false,
				    columns:[[
					    {field:'id',title:'细分类编号',width:20},
					    {field:'text',title:'细分类名称',width:20}
				    ]]">
			</select>
		</td>
	</tr>
</table>