<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "org/org.js" />
    </script>
</head>
<body>
	<!-- 组织基础信息管理主表 --> 
	<div id="org_toolbar">
		<a id="insert" onclick="orgFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.escm.security.org.insert")>none</#if>;">
			${action.getText("common.button.add")}
		</a>
		<a id="showEdit" onclick="orgFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.org.update")>none</#if>;">
			${action.getText("common.button.edit")}
		</a>
		<#--<a id="remove" onclick="orgFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:true">
			${action.getText("common.button.delete")}
		</a>-->
		<a id="enable" onclick="orgFn.enable();"plain="true" class="easyui-linkbutton" data-options="iconCls:'active',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.org.enable")>none</#if>;">
			${action.getText("common.button.active")}
		</a>
		<a id="disable" onclick="orgFn.disable();"plain="true" class="easyui-linkbutton" data-options="iconCls:'close',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.org.disable")>none</#if>;">
			${action.getText("common.button.disable")}
		</a>
	</div>
    <table  id="org_treegrid" class="easyui-treegrid"
		    data-options="
		    	iconCls:'icon-save',
		    	toolbar:'#org_toolbar',
		    	fit:true,
		    	url:'org_loadOrgTree_5.html',
		    	idField:'orgId',
		    	treeField:'name',
		    	onLoadSuccess:orgFn.onLoadSuccess
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