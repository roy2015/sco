<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/dataMaintenance/assortmentdata/merchandiseRoleMaintenance/merchandiseRoleModel.js" />
	<#include "sco/common/masterDataType.js" />
</script>
</head>
<body>
	<!-- 商品角色管理主表 -->
	<div id="merchandise_toolbar">
		<form id="signedQty_search">
			<table>
				<tr>
					<td style="text-align: right; width: 60px;">供应商编号:</td>
					<td style="width: 80px;"><input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width: 120px;" /></td>
					<td style="text-align: right; width: 110px">供应商名称:</td>
					<td><input filterName="supplierName" id="supplierName" style="width: 120px;" /></td>
				</tr>
				<tr>
					<td style="text-align: right;">商品编号:</td>
					<td><input filterName="merchandiseCode" id="merchandiseCode" style="width: 120px;" /></td>
					<td style="text-align: right;">商品名称:</td>
					<td><input filterName="merchandiseName" id="merchandiseName" style="width: 120px;" /></td>
					<td style="text-align: right; width: 100px;">商品定性角色:</td>
					<td><input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'merchandiseRole_listQualitative_5.html'
					    " />
					</td>
					<td style="text-align: right; width: 100px;">商品定量角色:</td>
					<td><input class="easyui-combobox filterSelect" filterName="dlRoleCode" id="dlRoleCode" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'merchandiseRole_listQuantify_5.html'
					    " /></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 73px">中分类:</td>
					<td><input class="easyui-combobox filterSelect" filterName="centreType" id="centreTypeCode" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " /></td>
					<td style="text-align: right; width: 73px">小分类:</td>
					<td><input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallType" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								editable:false" /></td>
					<td style="text-align: right; width: 73px">明细类:</td>
					<td><input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailType" style="width: 124px;"
						data-options="
									valueField:'id',
									textField:'text',
									editable:false" /></td>
					<td style="text-align: right; width: 73px">细分类:</td>
					<td><input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineType" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								editable:false" /></td>
				</tr>
				<tr>
					<td><a onclick="merchandiseFn.search();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 查询 </a></td>
					<td><a onclick="merchandiseFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a></td>
					<td><a id="showEdit" onclick="merchandiseFn.showEditdx();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'"> 修改定性角色 </a></td>
					<td><a id="showEdit" onclick="merchandiseFn.showEditdl();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'"> 修改定量角色 </a></td>
					<td colspan="2"><a onclick="merchandiseFn.importFromExcel();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'"> 批量导入商品角色 </a></td>
				</tr>
			</table>
		</form>
	</div>
	<table id="merchandise_grid" class="easyui-datagrid" fit="true" pagination="true" pagePosition='bottom' pageSize="20" pageList="[ 10, 20, 30, 40 ]" toolbar="#merchandise_toolbar"
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th align="center" data-options="field:'NULL',width:40,checkbox:true"></th>
				<th data-options="field:'merchandiseCode',width:150,sortable:true">商品编号</th>
				<th data-options="field:'merchandiseName',width:150,sortable:false">商品名称</th>
				<th data-options="field:'dxRoleName',width:100,sortable:false">商品定性角色</th>
				<th data-options="field:'dlRoleName',width:100,sortable:false">商品定量角色</th>
				<th data-options="field:'centreName',width:90,sortable:false">中分类</th>
				<th data-options="field:'smallName',width:90,sortable:false">小分类</th>
				<th data-options="field:'detailName',width:90,sortable:false">明细类</th>
				<th data-options="field:'fineName',width:90,sortable:false">细分类</th>
				<th data-options="field:'supplierCode',width:150,sortable:true">供应商编号</th>
				<th data-options="field:'supplierName',width:150,sortable:false">供应商名称</th>
			</tr>
		</thead>
	</table>
</body>
</html>
</#compress>
