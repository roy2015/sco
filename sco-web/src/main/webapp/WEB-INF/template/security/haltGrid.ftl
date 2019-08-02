<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" src="${contextPath}js/plugin/datagriddetailview.js" charset="utf-8"></script>
    <script type="text/javascript" >
		<#include "security/halt.js" />
    </script>
</head>
<body>
	<!-- 停机通知管理管理主表 --> 
	<div id="halt_toolbar">
		<a onclick="haltFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.escm.security.systemManager.halt.insert")>none</#if>;">
			${action.getText("common.button.add")}
		</a>
		<a id="showEdit" onclick="haltFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'" style="display:<#if !action.hasFuncPower("com.escm.security.systemManager.halt.update")>none</#if>;">
			${action.getText("common.button.edit")}
		</a>
		<a id="turnOn" onclick="haltFn.turnOn();"plain="true" class="easyui-linkbutton" data-options="iconCls:'active'" style="display:<#if !action.hasFuncPower("com.escm.security.systemManager.halt.turnOn")>none</#if>;">
			${action.getText("common.button.turnOn")}
		</a>
		<a id="turnOff" onclick="haltFn.turnOff();"plain="true" class="easyui-linkbutton" data-options="iconCls:'close'" style="display:<#if !action.hasFuncPower("com.escm.security.systemManager.halt.turnOff")>none</#if>;">
			${action.getText("common.button.turnOff")}
		</a>
		<a id="remove" onclick="haltFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" style="display:<#if !action.hasFuncPower("com.escm.security.systemManager.halt.delete")>none</#if>;">
			${action.getText("common.button.delete")}
		</a>
		<a id="clearFilter" onclick="haltFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
	</div>
    <table  id="halt_grid"
		    class="easyui-datagrid"
		    fit="true"
		    fitColumns="true"
		    iconCls="icon-save"
		    singleSelect="true"
			pagination="true"
			pagePosition='bottom'
			pageSize="20"
			pageList="[ 10, 20, 30, 40 ]"
			toolbar="#halt_toolbar"
			url='halt_listHalt_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
				<th data-options="field:'haltMsg',width:300,sortable:false">
                	<span class="txtCenter">${action.text("security.halt.haltMsg")}</span>
                </th>
				<th data-options="field:'state',width:100,sortable:false,formatter:function(value,rowData){
                		if(value=='Y')return '<div class=\'active\'>&nbsp</div>';
                		if(value=='N')return '<div class=\'noactive\'>&nbsp</div>';
                		return value;
                	}">
                	<span class="txtCenter">${action.text("security.halt.state")}</span>
                </th>
                <th rowspan="2" data-options="field:'haltTime',width:75,sortable:false">
                	<span class="txtCenter">${action.text("security.halt.haltTime")}</span>
                </th>
				<th rowspan="2" data-options="field:'restored',width:75,sortable:false">
                	<span class="txtCenter">${action.text("security.halt.restored")}</span>
                </th>
            </tr>
            <tr>
                <th><input style="width:500px;" class="filterInput" filterName="haltMsg"/></th>  
                <th>
	                <select class="filterSelect" panelHeight="auto" filterName="state">
	                		<option value="">${action.text("public.all")}</option>
							<option value="Y">${action.text("security.halt.state.Y")}</option>
							<option value="N">${action.text("security.halt.state.N")}</option>
					</select>
                </th>   
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>