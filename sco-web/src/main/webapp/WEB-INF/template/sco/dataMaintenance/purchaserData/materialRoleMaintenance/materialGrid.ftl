<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/dataMaintenance/purchaserData/materialRoleMaintenance/materialModel.js" />
	<#include "sco/common/materialProperties.js" />
</script>
</head>
<body class="easyui-layout">
<div data-options="region:'center'">
	<div id="materialBigType" style="height: 250px;">
		<!-- 原料大类管理主表 -->
		<div id="materialBigType_toolbar">
			<a id="insert" onclick="materialBigTypeFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'"> 添加 </a> 
			<a id="showEdit_big" onclick="materialBigTypeFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'"> 重命名</a> 
			<a id="remove_big" onclick="materialBigTypeFn.remove();" plain="true" class="easyui-linkbutton"	data-options="iconCls:'delete'"> 删除 </a>
		</div>
		<table id="materialBigType_grid" title="原料大类列表" fit="true" fitColumns="true" singleSelect="false" pagination="true" pagePosition='bottom' pageSize="5" pageList="[ 5, 10, 15, 20 ]"
			toolbar="#materialBigType_toolbar" url='materialBigType_listMaterialBigType_2.html' data-options="rownumbers:true">
			<thead>
				<tr>
					<th data-options="width:40,checkbox:true"></th>
					<th data-options="field:'materialBigTypeName',width:150">原料大类名称</th>
					<th data-options="field:'createby',width:100,formatter:function(value,row){
																				 if(row.updateby!=null){
																				 	return row.updateby;
																				 }else{
																				 	return value;
																				 }
																				} ">操作人</th>
					<th data-options="field:'createds',width:100">操作日期</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="materialSmallType" style="height: 250px;">
		<!-- 原料小类管理主表 -->
		<div id="materialSmallType_toolbar">
			<form id="materialJoint_search" method="post">
				<table style="width: 390px; font-size: 12px;">
					<tr>
						<td style="text-align: right; width: 90px">原料大类：</td>
						<td><input class="easyui-combobox filterSelect" filterName="materialBigTypeCode" id="materialBigTypeCode" style="width: 124px;"
							data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'materialProperties_listMaterialBigType_5.html'
				    
			    " /></td>
						<td style="text-align: right; width: 100px">原料小类：</td>
						<td><input class="easyui-combobox filterSelect" filterName="materialSmallTypeCode" id="materialSmallTypeCode" style="width: 124px;"
							data-options="
					valueField:'id',
					textField:'text',
					editable:false 
			    " /></td>
					</tr>
				</table>
			</form>
			<a onclick="materialSmallTypeFn.search();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 搜索 </a> <a onclick="materialSmallTypeFn.clearFilter();" plain="true"
				class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a> <a id="insert" onclick="materialSmallTypeFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
				添加 </a><a id="remove_small" onclick="materialSmallTypeFn.remove();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'"> 删除 </a> <a id="showEdit_small"
				onclick="materialSmallTypeFn.showEditName();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'"> 重命名 </a> <a id="showEdit_small" onclick="materialSmallTypeFn.showEditBig();"
				plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'"> 更改所属大类 </a>
		</div>
		<table id="materialSmallType_grid" title="原料小类列表" fitColumns="true" fit="true" singleSelect="false" pagination="true" pagePosition='bottom' pageSize="5" pageList="[ 5, 10, 15, 20 ]"
			toolbar="#materialSmallType_toolbar" data-options="rownumbers:true">
			<thead>
				<tr>
					<th data-options="width:40,checkbox:true"></th>
					<th data-options="field:'materialBigTypeName',width:150">原料大类名称</th>
					<th data-options="field:'materialSmallTypeName',width:150">原料小类名称</th>
					<th data-options="field:'createby',width:100,formatter:function(value,row){
																				 if(row.updateby!=null){
																				 	return row.updateby;
																				 }else{
																				 	return value
																				 }
																				}">操作人</th>
					<th data-options="field:'createds',width:100">操作日期</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="material" style="height: 300px;">
		<!-- 原料管理主表 -->
		<div id="material_toolbar">
			<form id="material_search" method="post">
				<table style="width: 610px; font-size: 12px;">
					<tr>
						<td style="text-align: right; width: 90px">原料大类：</td>
						<td><input class="easyui-combobox filterSelect" filterName="materialBigTypeCode" id="materialBig" style="width: 124px;"
							data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'materialProperties_listMaterialBigType_5.html'
				    
			    " /></td>
						<td style="text-align: right; width: 110px">原料小类：</td>
						<td><input class="easyui-combobox filterSelect" filterName="materialSmallTypeCode" id="materialSmall" style="width: 124px;"
							data-options="
					valueField:'id',
					textField:'text',
					editable:false 
			    " /></td>
					</tr>
					<tr>
						<td style="text-align: right;">原料名称：</td>
						<td><input class="easyui-combobox filterSelect" filterName="materialName" id="websiteMaterialName" style="width: 124px;"
							data-options="
									valueField:'text',
									textField:'text',
									editable:false
						    " /></td>
						<td style="text-align: right;">公示网站名称：</td>
						<td><input class="easyui-combobox filterSelect" filterName="websiteCode" id="websiteCode" style="width: 124px;"
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'websiteNameMaintenance_listWebsiteNameMaintenanceInfo_5.html'
					    " /></td>
						<td align="right" style="width: 80px;">价格地区：</td>
						<td><input filterName="priceRegion" id="priceRegion" style="width:120px;" class="easyui-validatebox" data-options="required:false,validType:'length[0,100]'" /></td>
					</tr>
				</table>
			</form>
			<a onclick="materialFn.search();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 查询 </a> 
			<a onclick="materialFn.clearFilter();" plain="true" class="easyui-linkbutton"data-options="iconCls:'clear'"> 清空查询 </a>
			<a id="insert" onclick="materialFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'"> 新增 </a>
			<a id="remove" onclick="materialFn.remove();" plain="true"class="easyui-linkbutton" data-options="iconCls:'delete'" > 删除 </a>
		</div>
		<table id="material_grid" title="原料列表" fit="true" fitColumns="true" singleSelect="false" pagination="true" pagePosition='bottom' pageSize="5" pageList="[ 5, 10, 15, 20 ]"
			toolbar="#material_toolbar" data-options="rownumbers:true" style="width:100%;">
			<thead>
				<tr>
					<th data-options="width:40,checkbox:true"></th>
					<th data-options="field:'materialBigTypeName',width:150">原料大类名称</th>
					<th data-options="field:'materialSmallTypeName',width:150">原料小类名称</th>
					<th data-options="field:'materialName',width:150">原料名称</th>
					<th data-options="field:'websiteName',width:150">公示网站名称</th>
					<th data-options="field:'websiteUrl',width:150,formatter:materialSmallTypeFn.formattUrl">公示网站地址</th>
					<th data-options="field:'priceRegion',width:100">价格地区</th>
					<th data-options="field:'createby',width:100,formatter:function(value,row){
																				 if(row.updateby!=null){
																				 	return row.updateby
																				 }else{
																				 	return value
																				 }
																				}">操作人</th>
					<th data-options="field:'created',width:100">操作日期</th>

				</tr>
			</thead>
		</table>
	</div>
</div>
</body>
</html>
</#compress>
