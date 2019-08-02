<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
<body>
	<div style="padding: 5px;overflow: hidden;height:260px">
    <table  id="supplier_grid"
		    class="easyui-datagrid"
		    fit="true"
		    singleSelect="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			url='supplier_listSupplierForUser_2.html'
		    data-options="rownumbers:true,onDblClickRow:function(index,row){userFn.setOrg(row.supplId,row.supplName)}">  
        <thead>
        	<tr>
        		<th data-options="field:'supplNo',width:140,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.supplNo")}</span>
                </th>
        		<th data-options="field:'supplName',width:140,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.supplName")}</span>
                </th>
                <th data-options="field:'supplMaster',width:75,sortable:true,formatter:function(value,rowData){
                		if(value=='Y')return '<div class=\'active\'>&nbsp</div>';
                		if(value=='N')return '<div class=\'noactive\'>&nbsp</div>';
                		return value;
                	}">
                	<span class="txtCenter">${action.text("security.supplier.supplMaster")}</span>
                </th>
                
				<th data-options="field:'phone',width:120,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.phone")}</span>
                </th>
				<th data-options="field:'email',width:100,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.email")}</span>
                </th>
				<th data-options="field:'contact',width:100,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.contact")}</span>
                </th>
				<th data-options="field:'address',width:200,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.address")}</span>
                </th>
				<th data-options="field:'description',width:200,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.description")}</span>
                </th>
            </tr>
            <tr>
            	 <th><input style="width:120px;" class="filterInput" filterName="supplNo"/></th>  
            	 <th><input style="width:120px;" class="filterInput" filterName="supplName"/></th>  
				<th>
	                <select class="filterSelect" panelHeight="auto" filterName="supplMaster">
	                		<option value="">${action.text("public.all")}</option>
							<option value="Y">${action.text("security.supplier.supplMaster.Y")}</option>
							<option value="N">${action.text("security.supplier.supplMaster.N")}</option>
					</select>
                </th> 
                <th><input style="width:100px;" class="filterInput" filterName="phone"/></th>  
                <th><input style="width:75px;" class="filterInput" filterName="eamil"/></th>  
                <th><input style="width:90px;" class="filterInput" filterName="contact"/></th>  
                <th><input style="width:190px;" class="filterInput" filterName="address"/></th>  
                <th><input style="width:190px;" class="filterInput" filterName="description"/></th>  
            </tr>
        </thead>
    </table>
    </div>
    <div style="margin:10px 20px 10px 10px;" align="right">
    	<a id="submitSupplier" class="easyui-linkbutton" data-options="iconCls:'save'" 
    		onclick="javascript:
    			var row = $('#supplier_grid').datagrid('getSelections');
    			if(row.length==0){
    				$.messager.alert('提示','请选择一个供应商再确认!','info');
    				return;
    			}
    			userFn.setOrg(row[0].supplId,row[0].supplName);">
    		${action.getText("common.button.confirm")}
    	</a>
    	<a id="cancelSupplier" class="easyui-linkbutton" data-options="iconCls:'cancel'" 
    		onclick="userFn.orgSimpleDlg.dialog('close');">
    		${action.getText("common.button.cancel")}
    	</a>
	</div>
</body>

</html>
</#compress>