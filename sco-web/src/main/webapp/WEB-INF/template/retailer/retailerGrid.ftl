<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "retailer/retailer.js" />
    </script>
    
<body>
	<div id="retailer_toolbar">
		<a id="insert" onclick="retailerFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.escm.security.retailer.insert")>none</#if>;">
			${action.getText("common.button.add")}
		</a>
		<a id="showEdit" onclick="retailerFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.retailer.update")>none</#if>;">
			${action.getText("common.button.edit")}
		</a>
		<a id="remove" onclick="retailerFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.retailer.delete")>none</#if>;">
			${action.getText("common.button.delete")}
		</a>
		<a onclick="retailerFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
	</div>
    <table  id="retailer_grid"
		    class="easyui-datagrid"
		    fit="true"
		    singleSelect="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#retailer_toolbar"
			url='retailer_listRetailer_2.html'
		    data-options="rownumbers:true">  
        <thead data-options="frozen:true">
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
          </thead>
          <thead>
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
          <thead data-options="frozen:true">
            <tr>
            	<th><input style="width:100px;" class="filterInput" filterName="nameCn"/></th>  
            	<th><input style="width:100px;" class="filterInput" filterName="nameEn"/></th>  
                <th><input style="width:100px;" class="filterInput" filterName="phone"/></th>  
            </tr>
        </thead>
        <thead>
             <tr>
                <th><input style="width:75px;" class="filterInput" filterName="email"/></th>  
                <th><input style="width:100px;" class="filterInput" filterName="representive"/></th>  
                <th><input style="width:200px;" class="filterInput" filterName="address"/></th>  
                <th><input style="width:200px;" class="filterInput" filterName="description"/></th>  
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>