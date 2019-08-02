<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/purchaserData/accessoryFineType/accessoryFineTypeModel.js" />
    </script>
</head>
<body>
	<!-- 辅料细分类维护管理主表 --> 
	<div id="accessoryFineType_toolbar">
		<a id="insert" onclick="accessoryFineTypeFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" >
			新增
		</a>
		<a id="remove" onclick="accessoryFineTypeFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" >
			删除
		</a>
	</div>
    <table  id="accessoryFineType_grid"
		    fit="true"
		    fitColumns="true"
		    singleSelect="false"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#accessoryFineType_toolbar"
			url='accessoryFineType_listAccessoryFineType_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th data-options="width:40,checkbox:true"></th>
				<th data-options="field:'fineTypeName',width:150,sortable:true">
                	辅料细分类名称
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>