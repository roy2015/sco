<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/purchaserData/merStandProInfo/merStandProInfo.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<div id="merStandProInfo_toolbar" style="margin:5px 5 5px 5;padding:5px">
		<form id="standProInfo_form" method="post">
			<table border="0">
				<tr>
					<td>
						<a onclick="merStandProInfoFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
							查询
						</a>
						<a onclick="merStandProInfoFn.add();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.merStandProInfo.add")>none</#if>;">
							新增
						</a>
						<a id="edit" onclick="merStandProInfoFn.edit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit',disabled:'true'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.merStandProInfo.edit")>none</#if>;">
							修改
						</a>
						<a id="remove" onclick="merStandProInfoFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.merStandProInfo.del")>none</#if>;">
							删除
						</a>
						<#-- <a onclick="merStandProInfoFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a> -->
					</td>
				</tr>
			</table>
		</form>
	</div>
    <table  id="merStandProInfo_grid"
		    fit="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#merStandProInfo_toolbar"
			url='merStandProInfo_listMerStandProInfo_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th rowspan="2" data-options="field:'configCode',checkbox:'true'">
				<th rowspan="2" data-options="field:'startDate',width:120">
                	生效日期
                </th>
				<th rowspan="2" data-options="field:'sumDay',width:120,align:'right'">
                	总天数
                </th>
				<th rowspan="2" data-options="field:'createby',width:140">
                	操作人
                </th>
				<th rowspan="2" data-options="field:'created',width:140">
                	操作日期
                </th>
				<th rowspan="2" data-options="field:'endDate',width:120">
                	结束日期
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>