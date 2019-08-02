<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "relation/relation.js" />
    </script>
     <script src="${contextPath}js/easyui/datagrid-groupview.js" charset="utf-8"></script>
<body>
	<div id="relation_toolbar">
		<a onclick="relationFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
	</div>
    <table class="easyui-datagrid" id="relation_grid"
            data-options="
                singleSelect:true,
                fit:true,
                collapsible:true,
                fitColumns:true,
                toolbar:'#relation_toolbar',
                rownumbers:true,
                pagination:true,
				pagePosition:'bottom',
				pageSize:'20',
                url:'relationSupplierRetailer_listRelationSupplierRetailer_2.html',
                view:groupview,
                groupField:'nameCn',
                groupFormatter:function(value,rowData){
                    return value + '('+rowData[0].nameEn+')'+ ' - ' + rowData.length + ' 条记录';
                }
            ">
        <thead>
            <tr>
                <th data-options="field:'retailerId',width:100,sortable:true,align:'center',hidden:true">${action.text("security.retailer.retailerId")}</th>
                <th data-options="field:'nameCn',width:300,sortable:true,align:'center'">${action.text("security.retailer.nameCn")}</th>
                <th data-options="field:'nameEn',width:260,sortable:true,align:'center',hidden:true">${action.text("security.retailer.nameEn")}</th>
                <th data-options="field:'supplNo',width:180,sortable:true,align:'center'">${action.text("security.supplier.supplNo")}</th>
                <th data-options="field:'supplName',width:300,sortable:true,align:'center'">${action.text("security.supplier.supplName")}</th>
                <th data-options="field:'supplMaster',width:80,sortable:true,align:'center',formatter:function(value,rowData){
                		if(value=='Y')return '<div class=\'active\'>&nbsp</div>';
                		if(value=='N')return '<div class=\'noactive\'>&nbsp</div>';
                		return value;
                	}">${action.text("security.supplier.supplMaster")}</th>
            </tr>
            <tr>
            	 <th><input style="width:100px;" class="filterInput" filterName="nameCn"/></th>  
            	 <th><input style="width:120px;" class="filterInput" filterName="supplNo"/></th>  
            	 <th><input style="width:120px;" class="filterInput" filterName="supplName"/></th>  
				<th>
	                <select class="filterSelect" panelHeight="auto" filterName="supplMaster">
	                		<option value="">${action.text("public.all")}</option>
							<option value="Y">${action.text("security.supplier.supplMaster.Y")}</option>
							<option value="N">${action.text("security.supplier.supplMaster.N")}</option>
					</select>
                </th> 
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>