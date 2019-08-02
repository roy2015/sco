<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/parameterSet/materialwarnconfig/materialWarnConfigModel.js" />
	<#include "sco/common/materialProperties.js" />
</script>
</head>
<body>
	<div id="materialWarnConfig_toolbar">
		<form id="materialJoint_search" method="post">
			<table>
				<tr>
					<td style="text-align: right; width: 110px">原料大类：</td>
					<td><input class="easyui-combobox filterSelect" filterName="materialBigTypeCode" id="materialBigTypeCode" style="width: 124px;"
						data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'materialProperties_listMaterialBigType_5.html'" />
					</td>
					<td style="text-align: right; width: 100px">原料小类：</td>
					<td><input class="easyui-combobox filterSelect" filterName="materialSmallTypeCode" id="materialSmallTypeCode" style="width: 124px;"
						data-options="
					valueField:'id',
					textField:'text',
					editable:false 
			    " /></td>
					
					<td style="text-align: right;">公示网站原料名称：</td>
					<td><input class="easyui-combobox filterSelect" filterName="websiteMaterialCode" id="websiteMaterialName" style="width: 124px;"
						data-options="
									valueField:'id',
									textField:'text',
									editable:false
						    " onchange="materialWarnConfigFn.setWebName()" /></td>
				</tr>
				<tr>
				<td style="text-align: right; width: 100px">公示网站名称：</td>
					<td><input class="easyui-combobox filterSelect" filterName="merchandiseCode" name="merchandiseCode" id="webName" style="width:124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false
					    " /></td>
					<td style="text-align: right;">预警方式：</td>
					<td><input class="easyui-combobox filterSelect"
						data-options="
					valueField: 'id',
					textField: 'text',
					editable:false,
					url:'materialWarnConfig_listWarnType_5.html'"
						style="width: 124px;" id="warnType" /></td>
				</tr>
				<tr>
					<td style="text-align: right;">设置日期：</td>
					<td><input class="easyui-datebox list-input" id="minDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" />
					</td> 
					<td style="text-align: right;">至：</td>
					<td><input
						class="easyui-datebox list-input" id="maxDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" /></td>
					<td style="text-align: right;">设置人：</td>
					<td><input class="easyui-validatebox" filterName="createby" id="createby" style="width: 120px;" /></td>
				</tr>
				<tr>
					<td colspan="8"> <a id="search" onclick="materialWarnConfigFn.search();" plain="true"
						class="easyui-linkbutton" data-options="iconCls:'search'"> 查询 </a> <a onclick="materialWarnConfigFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询 </a><a id="insert" onclick="materialWarnConfigFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'"> 新增预警 </a> <a id="remove"
						onclick="materialWarnConfigFn.remove();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'"> 取消预警 </a></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 原料行情预警设置管理主表 -->
	<table id="materialWarnConfig_grid" fit="true" iconCls="icon-save" fitColumns="true" pagination="true" pagePosition='bottom' toolbar="#materialWarnConfig_toolbar" pageSize="20"
		pageList="[ 10, 20, 30, 40 ]" data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'merchandiseName',width:40,checkbox:true"></th>
				<th data-options="field:'materialBigName',width:150,sortable:false">原料大类名称</th>
				<th data-options="field:'materialSmallName',width:150,sortable:false">原料小类名称</th>
				<th data-options="field:'materialName',width:150,sortable:false">原料名称</th>
				<th data-options="field:'websiteName',width:100,sortable:false">公示网站名称</th>
				<th data-options="field:'warnType',width:150,sortable:false">预警方式</th>
				<th align="right"  data-options="field:'thresholdValue',width:100,sortable:true,remoteSort:true">阀值(%)</th>
				<th data-options="field:'createby',width:100,sortable:false">设置人</th>
				<th data-options="field:'created',width:100,sortable:true">设置时间</th>
			</tr>
		</thead>
	</table>
</body>
</html>
</#compress>
