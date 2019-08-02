<#compress>
<html>
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript">
    	<#include "dic/dic.js"/>
    </script>
    <script type="text/javascript" src="js/easyui/datagrid-dnd.js">
    </script>
</head>

<body>
	<div id="dic_toolbar">
		<a id="insert" onclick="dicFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" >
			${action.getText("common.button.add")}
		</a>
		<a id="showEdit" onclick="dicFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'" >
			${action.getText("common.button.edit")}
		</a>
		<a id="remove" onclick="dicFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
			${action.getText("common.button.delete")}
		</a>
		<a id="active" onclick="dicFn.active();"plain="true" class="easyui-linkbutton" data-options="iconCls:'active'">
			 ${action.getText("dic.button.active")}
		</a>
		<a id="forbidden" onclick="dicFn.forbidden();"plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
			 ${action.getText("dic.button.close")}
		</a>
		
		<a id="top" onclick="dicFn.top();"plain="true" class="easyui-linkbutton" data-options="iconCls:'up'">
			 ${action.getText("dic.button.top")}
		</a>
		<a id="bottom" onclick="dicFn.bottom();"plain="true" class="easyui-linkbutton" data-options="iconCls:'down'">
			 ${action.getText("dic.button.bottom")}
		</a>
		
		<a id="import" onclick="dicFn.import();"plain="true" class="easyui-linkbutton" data-options="iconCls:'up'">
			 ${action.getText("dic.button.import")}
		</a>
		<a id="export" onclick="dicFn.dicExport();"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">
			 ${action.getText("dic.button.export")}
		</a>
		<a id="translateExport" onclick="dicFn.translateExport();"plain="true" class="easyui-linkbutton" data-options="iconCls:'storageArea'">
			 ${action.getText("dic.button.translateExport")}
		</a>
		
		<a onclick="dicFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<span style="font-size:12px;">${action.getText("dic.ckLanguage")}</span>&nbsp;&nbsp;
		
		<input id="language" name="language" class="easyui-combobox filterSelect" autoClear="false" 
		filterName="language" data-options="valueField:'id',textField:'text',
			data:[{
					'id': 'zh_cn',
					'text': '中文',
					'selected':true
				},{
					'id': 'en_gb',
					'text': 'English'
				}],onChange:dicFn.doFilter"/>
	</div>
	<table id="dic_dg"
		    fit="true"
		    singleSelect="true"
		    dragSelection="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#dic_toolbar"
		    rownumbers="true"
		   	checkOnSelect="true"
		    selectOnCheck="false"
			url='dic_listDic_2.html'
		>
		<thead data-options="frozen:true">
			<tr>
				<th rowspan="2" field="id" width="80" data-options="sortable:true,align:'center',checkbox:true">${action.getText("div.id")}</th>
				<th field="dicType" width="120" data-options="sortable:true,align:'center'">${action.getText("dic.dicType")}</th>
				<!--
				<th field="typeDesc" width="220" data-options="align:'center'">${action.getText("dic.typeDesc")}</th>
				-->
				<th field="status" width="80" data-options="align:'center',
				formatter:function(value,r){
	                		if(value=='Y')return '<div class=\'active\'>&nbsp</div>';
	                		if(value=='N')return '<div title=\''+r.disableMsg+'\' class=\'noactive\'>&nbsp</div>';
	                		return value;
	                	}
				">${action.getText("dic.status")}</th>
			</tr>
		</thead>
		<thead>
			<tr>
				<th field="dicKey" width="180" data-options="align:'center'">${action.getText("dic.dicKey")}</th>
				<th field="dicValue" width="180" data-options="align:'center'">${action.getText("dic.dicValue")}</th>
				<th field="valueDesc" width="200" data-options="align:'center'">${action.getText("dic.valueDesc")}</th>
				<th field="valueAlias" width="300" data-options="align:'center'">${action.getText("dic.valueAlias")}</th>
				<th field="isDefault" width="120" data-options="align:'center',
				formatter:function(value,r){
	                		if(value=='是')return '<div class=\'active\'>&nbsp</div>';
	                		if(value=='否')return null;
	                		return value;
	                	}
				">${action.getText("dic.isDefault")}</th>
			</tr>
		</thead>
		
		<thead data-options="frozen:true">
			<tr>
		        <th>
	              	<select style="width:100px;height:25px;" class="filterSelect" filterName="dicType" id="type" panelHeight="auto">
	              		<option value="">${action.getText("dic.all")}</option>
	              		<#list types as type>
	              			<option value="${type.id}">${type.text}</option>
	              		</#list>
	              	</select>
	            </th>
	            <!--
	             <th>
	              	<input style="width:200px;height:25px;" class="filterInput" filterName="typeDesc"/>
	             </th> 
	            -->
	             <th>
	              	<select style="width:60px;height:25px;" class="filterSelect" filterName="status" panelHeight="auto">
	              		<option value="">${action.getText("dic.all")}</option>
	              		<option value="Y">${action.getText("dic.active")}</option>
	              		<option value="N">${action.getText("dic.forbidden")}</option>	
	              	</select>
	            </th> 
	        </tr>
        </thead>
		<thead>
	        <tr>  
		         <th>
		           	<input style="width:160px;height:25px;" class="filterInput" filterName="dicKey"/>
		         </th>  
		          <th>
	              	<input style="width:160px;height:25px;" class="filterInput" filterName="dicValue" />
	             </th>
	              <th>
	              	<input style="width:180px;height:25px;" class="filterInput" filterName="valueDesc" />
	             </th> 
		         <th>
		           	<input style="width:280px;height:25px;" class="filterInput" filterName="valueAlias"/>
		         </th>  
		         <th>
	              	<select style="width:100px;height:25px;" class="filterSelect" filterName="isDefault" panelHeight="auto">
	              		<option value="">${action.getText("dic.all")}</option>
	              		<option value="Y">${action.getText("dic.Y")}</option>
	              		<option value="N">${action.getText("dic.N")}</option>	
	              	</select>
	            </th>             
			</tr>
		</thead>
	</table>
	
</body>
</html>
</#compress>
