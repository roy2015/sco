<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
     <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
</head>
<body>
    <table  id="lackFileNewGrid"
    		class="easyui-datagrid"
		    title="商品新品引进OA申请列表" 
		    fit="true"
		    iconCls= "icon-save"
		    singleSelect="true"
		    checkOnSelect="false"
	   		selectOnCheck="false"
		    singleSelect="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#lackFileNew_toolbar"
			url='reportNew_listNotHaveMustReportNew_2.html?applicationCode=${applicationCode}'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th data-options="field:'applicationCode',width:250,sortable:false">
                	申请单号
                </th>
				<th data-options="field:'merchandiseCode',width:150,sortable:true">
                	商品编号
                </th>
				<th data-options="field:'supplierCode',width:150,sortable:true">
                	供应商编号
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>