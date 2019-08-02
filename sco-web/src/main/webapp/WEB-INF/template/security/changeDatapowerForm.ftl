<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="changeDatapowerForm" method="post">
		<table width="280px" border="0" cellpadding="0" cellspacing="0" bordercolor="#EEEEEE" >
			<tr height="30" align="left">
			  <th width="80" align="right"><span style="margin-right:5px;font-weight: normal;">${action.text("retailer.title.selectRetailer")}</span></th>
			  <td>
			  
			  	<input id = "R_DataPowerSelector" style="width:180px;" value="${R_dataId}" defaultText="${R_dataText}"
			  							 class="easyui-combogrid" 
			  							 data-options="panelWidth:450,
										 idField:'retailerId',
										 textField:'nameCn',
										 mode: 'remote',
										 required:true,
										 missingMessage:'${action.text("retailer.msg.selectRetailer")}',
										 url:'retailer_listRetailerForChangeDataPower_2.html',
										 pagination:true,
										 columns:[[
										 	{field:'retailerId',title:'${action.text("security.dataPower.retailerId")}',width:60},  
											{field:'nameCn',title:'${action.text("security.dataPower.nameCn")}',width:60},  
											{field:'representive',title:'${action.text("security.dataPower.representive")}',width:80},
											{field:'phone',title:'${action.text("security.dataPower.phone")}',width:80} 
										 ]],
										 fitColumns: true,
										 onChange:function(newValue,oldValue){northFn.changeDataPower('R',newValue,'nameCn');}
										 " />
			  </td>
		    </tr>
		    
		    <tr height="30" align="left">
			  <th width="80" align="right"><span style="margin-right:5px;font-weight: normal;">${action.text("supplier.title.selectSupplier")}</span></th>
			  <td>
			  <input id = "S_DataPowerSelector" style="width:180px;" value="${S_dataId}" defaultText="${S_dataText}"
			  							 class="easyui-combogrid" 
			  							 data-options="panelWidth:450,
										 idField:'supplId',
										 textField:'supplName',
										 mode: 'remote',
										 required:true,
										 missingMessage:'${action.text("supplier.msg.selectSupplier")}',
										 url:'supplier_listSupplierForChangeDataPower_2.html',
										 pagination:true,
										 columns:[[
										 	{field:'supplId',title:'${action.text("security.supplier.supplId")}',width:60},  
											{field:'supplName',title:'${action.text("security.supplier.supplName")}',width:60},  
											{field:'phone',title:'${action.text("security.supplier.phone")}',width:80} 
										 ]],
										 fitColumns: true,
										 onChange:function(newValue,oldValue){northFn.changeDataPower('S',newValue,'supplName');}
										 " />
			  </td>
		    </tr>
		</table>
	</form>
</div>
</#compress>