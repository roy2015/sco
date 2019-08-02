<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/dataMaintenance/purchaserData/websiteNameMaintenance/websiteNameMaintenanceModel.js" />
</script>
</head>
<body>
	<!-- 公示网站名称维护管理主表 -->
	<div id="website_toolbar">
		<table>
			<tr>
				<td style="text-align: right; width: 90px">公示网站名称：</td>
				<td><input class="easyui-combobox filterSelect" filterName="websiteCode" id="websiteCode" style="width: 124px;"
					data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'websiteNameMaintenance_listWebsiteNameMaintenanceInfo_5.html'
					    " /></td>
				<td style="text-align: right; width: 100px">原料名称：</td>
				<td style="width: 100px;"><input class="easyui-validatebox" filterName="materialName" id="materialName" style="width: 120px;" /></td>
				<td style="text-align: right; width: 100px">地区名称：</td>
				<td style="width: 100px;"><input class="easyui-validatebox" filterName="priceRegion" id="priceRegion" style="width: 120px;" /></td>
				<td style="text-align: right; width: 100px">维护人：</td>
				<td style="width: 100px;"><input class="easyui-validatebox" filterName="updateby" id="updateby" style="width: 120px;" /></td>
			</tr>
		</table>
		<a id="search" onclick="websiteFn.search();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 查询 </a> <a onclick="websiteFn.clearFilter();"
				plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a>
		<a id="insert" onclick="websiteFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'"> 新增 </a> <a id="remove" onclick="websiteFn.remove();" plain="true"
			class="easyui-linkbutton" data-options="iconCls:'delete'"> 删除 </a>
	</div>
	<table id="website_grid" fit="true" singleSelect="false" pagination="true" pagePosition='bottom' fitColumns='true' pageSize="20" pageList="[ 10, 20, 30, 40 ]" toolbar="#website_toolbar"
		 data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'merchandiseCode',width:40,checkbox:true"></th>
				<th data-options="field:'websiteName',width:150">网站名称</th>
				<th data-options="field:'materialName',width:150">原料名称</th>
				<th data-options="field:'websiteUrl',width:150,formatter:websiteFn.formattUrl">公示网站地址</th>
				<th data-options="field:'priceRegion',width:100">地区名称</th>
				<th data-options="field:'updatedate',width:135,sortable:true">最后一次价格维护日期</th>
				<th data-options="field:'updateby',width:100">维护人</th>
			</tr>
		</thead>
	</table>
</body>
</html>
</#compress>
