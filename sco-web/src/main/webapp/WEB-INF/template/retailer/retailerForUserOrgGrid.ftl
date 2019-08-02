<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
<body>
	<div style="padding: 5px;overflow: hidden;height:260px">
    <table  id="retailer_grid"
		    class="easyui-datagrid"
		    fit="true"
		    singleSelect="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			url='retailer_listRetailerForUser_2.html'
		    data-options="rownumbers:true,onDblClickRow:function(index,row){userFn.setOrg(row.retailerId,row.nameCn)}">  
        <thead>
        	<tr>
        		<th data-options="field:'nameCn',width:120,sortable:true">
                	<span class="txtCenter">${action.text("security.retailer.nameCn")}</span>
                </th>
        		<th data-options="field:'nameEn',width:120,sortable:true">
                	<span class="txtCenter">${action.text("security.retailer.nameEn")}</span>
                </th>
                
				<th data-options="field:'phone',width:120,sortable:true">
                	<span class="txtCenter">${action.text("security.retailer.phone")}</span>
                </th>
				<th data-options="field:'email',width:100,sortable:true">
                	<span class="txtCenter">${action.text("security.retailer.email")}</span>
                </th>
				<th data-options="field:'representive',width:120,sortable:true">
                	<span class="txtCenter">${action.text("security.retailer.representive")}</span>
                </th>
				<th data-options="field:'address',width:220,sortable:true">
                	<span class="txtCenter">${action.text("security.retailer.address")}</span>
                </th>
				<th data-options="field:'description',width:220,sortable:true">
                	<span class="txtCenter">${action.text("security.retailer.description")}</span>
                </th>
            </tr>
            <tr>
            	<th><input style="width:100px;" class="filterInput" filterName="nameCn"/></th>  
            	<th><input style="width:100px;" class="filterInput" filterName="nameEn"/></th>  
                <th><input style="width:100px;" class="filterInput" filterName="phone"/></th>  
                <th><input style="width:75px;" class="filterInput" filterName="eamil"/></th>  
                <th><input style="width:100px;" class="filterInput" filterName="representive"/></th>  
                <th><input style="width:200px;" class="filterInput" filterName="address"/></th>  
                <th><input style="width:200px;" class="filterInput" filterName="description"/></th>  
            </tr>
        </thead>
    </table>
    </div>
    <div style="margin:10px 20px 10px 10px;" align="right">
    	<a id="submitRetailer" class="easyui-linkbutton" data-options="iconCls:'save'" 
    		onclick="javascript:
    			var row = $('#retailer_grid').datagrid('getSelections');
    			if(row.length==0){
    				$.messager.alert('提示','请选择一个零售商再确认!','info');
    				return;
    			}
    			userFn.setOrg(row[0].retailerId,row[0].nameCn);">
    		${action.getText("common.button.confirm")}
    	</a>
    	<a id="cancelRetailer" class="easyui-linkbutton" data-options="iconCls:'cancel'" 
    		onclick="userFn.orgSimpleDlg.dialog('close');">
    		${action.getText("common.button.cancel")}
    	</a>
	</div>
</body>
</html>
</#compress>