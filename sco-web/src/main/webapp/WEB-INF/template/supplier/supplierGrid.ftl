<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "supplier/supplier.js" />
    </script>
</head>
<body>
	<div id="supplier_toolbar">
		<a id="insert" onclick="supplierFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.escm.security.supplier.insert")>none</#if>;">
			${action.getText("common.button.add")}
		</a>
		<a id="showEdit" onclick="supplierFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.supplier.update")>none</#if>;">
			${action.getText("common.button.edit")}
		</a>
		<a id="remove" onclick="supplierFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.supplier.delete")>none</#if>;">
			${action.getText("common.button.delete")}
		</a>
		<a onclick="supplierFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
	</div>
    <table  id="supplier_grid"
		    class="easyui-datagrid"
		    fit="true"
		    singleSelect="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#supplier_toolbar"
			url='supplier_listSupplier_2.html'
		    data-options="rownumbers:true">  
        <thead data-options="frozen:true">
        	<tr>
        		<th data-options="field:'supplNo',width:140,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.supplNo")}</span>
                </th>
        		<th data-options="field:'supplName',width:250,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.supplName")}</span>
                </th>
                <th data-options="field:'supplMaster',width:75,sortable:true,formatter:function(value,rowData){
                		if(value=='Y')return '<div class=\'active\'>&nbsp</div>';
                		if(value=='N')return '<div class=\'noactive\'>&nbsp</div>';
                		return value;
                	}">
                	<span class="txtCenter">${action.text("security.supplier.supplMaster")}</span>
                </th>
            </tr>
        </thead>
        <thead>
        	<tr>
				<th data-options="field:'phone',width:140,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.phone")}</span>
                </th>
				<th data-options="field:'email',width:140,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.email")}</span>
                </th>
				<th data-options="field:'contact',width:100,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.contact")}</span>
                </th>
				<th data-options="field:'address',width:240,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.address")}</span>
                </th>
				<th data-options="field:'description',width:240,sortable:true">
                	<span class="txtCenter">${action.text("security.supplier.description")}</span>
                </th>
            </tr>
	    </thead>
	    <thead data-options="frozen:true">
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
            </tr>
      	</thead>
      	<thead>
          	<tr> 
                <th><input style="width:100px;" class="filterInput" filterName="phone"/></th>  
                <th><input style="width:75px;" class="filterInput" filterName="email"/></th>  
                <th><input style="width:90px;" class="filterInput" filterName="contact"/></th>  
                <th><input style="width:190px;" class="filterInput" filterName="address"/></th>  
                <th><input style="width:190px;" class="filterInput" filterName="description"/></th>  
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>