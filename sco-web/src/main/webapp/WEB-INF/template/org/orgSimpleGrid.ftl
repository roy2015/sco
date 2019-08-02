<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
    </script>
</head>
<body>
    <table  id="org_simple_treegrid" class="easyui-treegrid"
		    data-options="
		    	iconCls:'icon-save',
		    	fit:true,
		    	url:'org_loadOrgTree_5.html',
		    	idField:'orgId',
		    	treeField:'name',
		    	onDblClickRow:userFn.setOrg
				">  
        <thead>
        	<tr>
				<th data-options="field:'name',width:180,sortable:true">
                	<span class="txtCenter">${action.text("security.org.name")}</span>
                </th>
				<th data-options="field:'orgCode',width:100,sortable:true">
                	<span class="txtCenter">${action.text("security.org.orgCode")}</span>
                </th>
				<th data-options="field:'isactive',width:50,sortable:true,formatter:function(value,rowData){
                		if(value=='Y')return '<div class=\'active\'>&nbsp</div>';
                		if(value=='N')return '<div class=\'noactive\'>&nbsp</div>';
                		return value;
                	}">
                	<span class="txtCenter">${action.text("security.org.isactive")}</span>
                </th>
				<th data-options="field:'contact',width:120,sortable:true">
                	<span class="txtCenter">${action.text("security.org.contact")}</span>
                </th>
				<th data-options="field:'phone',width:120,sortable:true">
                	<span class="txtCenter">${action.text("security.org.phone")}</span>
                </th>
				<th data-options="field:'mobile',width:120,sortable:true">
                	<span class="txtCenter">${action.text("security.org.mobile")}</span>
                </th>
				<th data-options="field:'fax',width:120,sortable:true">
                	<span class="txtCenter">${action.text("security.org.fax")}</span>
                </th>
				<th data-options="field:'address',width:320,sortable:true">
                	<span class="txtCenter">${action.text("security.org.address")}</span>
                </th>
				<th data-options="field:'description',width:320,sortable:true">
                	<span class="txtCenter">${action.text("security.org.description")}</span>
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>