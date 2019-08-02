<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "security/func.js" />
    </script>
    <style>
    	.row-top td{
            border-top:1px solid red;
            background:#fff;
        }
        .row-append td{
            border:0;
            background:#FBEC88;
        }
    </style>
</head>
<body>
	<!-- 功能基础信息管理主表 --> 
	<div id="func_toolbar">
		<a id="insertChild" onclick="funcFn.showInsert('CHILD');" plain="true" class="easyui-linkbutton" data-options="iconCls:'receive',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.func.insert")>none</#if>;">
			${action.getText("security.func.button.insertChild")}
		</a>
		<a id="insertBefore" onclick="funcFn.showInsert('BEFORE');" plain="true" class="easyui-linkbutton" data-options="iconCls:'up',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.func.insertBefor")>none</#if>;">
			${action.getText("security.func.button.insertBefore")}
		</a>
		<a id="insertAfter" onclick="funcFn.showInsert('AFTER');" plain="true" class="easyui-linkbutton" data-options="iconCls:'down',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.func.insertAfter")>none</#if>;">
			${action.getText("security.func.button.insertAfter")}
		</a>
		<a id="showEdit" onclick="funcFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.func.update")>none</#if>;">
			${action.getText("common.button.edit")}
		</a>
		<a id="remove" onclick="funcFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.func.delete")>none</#if>;">
			${action.getText("common.button.delete")}
		</a>
		<a id="enable" onclick="funcFn.enable();"plain="true" class="easyui-linkbutton" data-options="iconCls:'active',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.func.enable")>none</#if>;">
			${action.getText("common.button.active")}
		</a>
		<a id="disable" onclick="funcFn.disable();"plain="true" class="easyui-linkbutton" data-options="iconCls:'close',disabled:true" style="display:<#if !action.hasFuncPower("com.escm.security.func.disable")>none</#if>;">
			${action.getText("common.button.disable")}
		</a>
		<a id="refresh" onclick="funcFn.refresh();"plain="true" class="easyui-linkbutton" data-options="iconCls:'refresh'" >
			${action.getText("common.button.refresh")}
		</a>
	</div>
	<table id="func_treegrid" class="easyui-treegrid" 
	 	data-options="
	 		fit:true,  
	 		toolbar:'#func_toolbar',
            url:'func_loadFuncTree_5.html',
            idField:'funcId',
            fitColumns:true,
            treeField:'name',
            onLoadSuccess:funcFn.onLoadSuccess
            ">  
        <thead>  
            <tr>  
                <th data-options="field:'name',width:200,sortable:true">
                	<span class="txtCenter">${action.text("security.func.name")}</span>
                </th>
                <th data-options="field:'funcType',width:100,sortable:true,formatter:function(value,r){
                		if(value=='M')return '${action.getText("security.func.funcType.M")}';
                		if(value=='B')return '${action.getText("security.func.funcType.B")}';
                		if(value=='U')return '${action.getText("security.func.funcType.U")}';
                		if(value=='R')return '${action.getText("security.func.funcType.R")}';
                		if(value=='O')return '${action.getText("security.func.funcType.O")}';
                		return value;
                	}
                ">
                	<span class="txtCenter">${action.text("security.func.funcType")}</span>
                </th>
                <th data-options="field:'funcKey',width:300,sortable:true">
                	<span class="txtCenter">${action.text("security.func.funcKey")}</span>
                </th> 
                <th field="iconcls" width="150" align="left" data-options="
                	formatter:function(value){
                		if(value==null)return null;
                		return '<div class='+value+' style=\'height:16px;text-align:left;padding-left:20px\'>'+value+'</div>';
                	}
                "><span class="txtCenter">${action.text("security.func.iconcls")}</span></th>
                <th data-options="field:'src',width:300,sortable:true">
                	<span class="txtCenter">${action.text("security.func.src")}</span>
                </th>
                
                <th data-options="field:'isFree',width:75,sortable:true,
                	formatter:function(value,r){
                		if(value=='Y')return '<div class=\'active\'>&nbsp</div>';
                		if(value=='N'||value==null)return '<div title=\''+r.disableMsg+'\' class=\'noactive\'>&nbsp</div>';
                		return value;
                	}
                ">
                 <span class="txtCenter">${action.text("security.func.isFree")}</span>
                </th>
                
                <th data-options="field:'active',width:75,sortable:true,
                	formatter:function(value,r){
                		if(value=='Y')return '<div class=\'active\'>&nbsp</div>';
                		if(value=='N')return '<div title=\''+r.disableMsg+'\' class=\'noactive\'>&nbsp</div>';
                		return value;
                	}
                ">
                	<span class="txtCenter">${action.text("security.func.active")}</span>
                </th>
                
                <th data-options="field:'description',width:300,sortable:true">
                	<span class="txtCenter">${action.text("security.func.description")}</span>
                </th>  
            </tr>  
        </thead>  
    </table> 
	<div id="rightDblMenuId" class="easyui-menu" style="width:120px;">
		
	</div>
</body>
</html>
</#compress>