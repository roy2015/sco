<#compress>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<#include "inc/easyui.ftl" />
	<script type="text/javascript">
		<#include "sco/common/masterDataType.js" />
		<#include "sco/common/warehouseSiteType.js" />
		<#include "sco/shareFunctionAnalysis/purchasingAnalysis/merchandiseStockDetail/merchandiseStockDetailModel.js" />
	</script>
	<link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body>
	<#-- 工具栏 -->
	<div id="merchandiseStockDetail_toolbar" style="margin: 5px 5 5px 5; padding: 3px">
		<form id="merchandiseStockDetail_search" method="post">
			<#-- 查询条件 -->
			<table style="width: 850px;">
				<tr>
					<td style="text-align: right; width: 80px;">供应商编号:</td>
					<td style="width: 80px;"><input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width: 120px;" /></td>
					<td style="text-align: right; width: 80px">供应商名称:</td>
					<td><input filterName="supplierName" class="easyui-validatebox" id="supplierName" style="width: 120px;" /></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 77px">商品编号:</td>
					<td><input filterName="merchandiseCode" class="easyui-validatebox" id="merchandiseCode" style="width: 120px;" /></td>
					<td style="text-align: right; width: 77px">商品名称:</td>
					<td><input filterName="merchandiseName" class="easyui-validatebox" id="merchandiseName" style="width: 120px;" /></td>
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
					<td><input class="easyui-combobox filterSelect" filterName="centreTypeCode" id="centreTypeCode" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " /></td>
					<td style="text-align: right; width: 73px">小分类:</td>
					<td><input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								editable:false" /></td>
					<td style="text-align: right; width: 73px">明细类:</td>
					<td><input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
						data-options="
									valueField:'id',
									textField:'text',
									editable:false" /></td>
					<td style="text-align: right; width: 73px">细分类:</td>
					<td><input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineTypeCode" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								editable:false" /></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 73px"><label style="color: red;" >*</label>工厂:</td>
					<td><input class="easyui-combobox filterSelect" id="warehouseCode" filterName="warehouseCode" style="width: 124px;"
						data-options="
									valueField:'id',
									textField:'id',
									url:'merchandiseStockDetail_listWarehouse_5.html',
									editable:false,	
									multiple:true" /></td>
					<td style="text-align: right; width: 73px"><label style="color: red;" >*</label>库存地点:</td>
					<td><input class="easyui-combobox filterSelect" id="sellRegion" filterName="sellRegion" style="width: 124px;"
						data-options="
									valueField:'id',
									textField:'id',
									url:'merchandiseStockDetail_listWarehouseSite_5.html',
									editable:false,
									multiple:true" /></td>
				<td style="text-align: right; width: 79px;"><label style="color: red;" >*</label>到货时间:</td>
					<td><input class="easyui-datebox list-input" filterName="minDate" id="minDate"
						data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" />
						</td>
						<td style="text-align: right; ">至:</td>
						<td> <input class="easyui-datebox list-input" filterName="maxDate"
						id="maxDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" /></td>
				</tr>
				<tr>
					<td colspan="5">
						<input type="radio" name="show" value="2" checked />只查非定量装商品<input type="radio" name="show" value="1"/>只查定量装商品<input type="radio" name="show" value="3"/>非定量与定量商品均查
					</td>
				</tr>
				<tr>
					<td  colspan="5">
						<input type="radio" name="type" value="day" checked />查看日明细<input type="radio" name="type" value="month"/>查看月明细
					</td>
				</tr>
			</table>
			<a onclick="merchandiseStockDetailFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 搜索 </a>
			<a onclick="merchandiseStockDetailFn.clear()" plain="true"	class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a> 
			<a onclick="merchandiseStockDetailFn.searchMerchandise()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 查看明细报表 </a>
		</form>
	</div>
	<#-- 数据表格 -->
	<table id="merchandiseStockDetail_grid"
		toolbar="#merchandiseStockDetail_toolbar"
		url="merchandiseStockDetail_listMerchandise_2.html"
	 	fit="true"   
		pagination="true"  
	 	pageSize="20" 
	 	pageList="[ 10, 20, 30, 40 ]"
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th align="center" data-options="field:'check',width:40,checkbox:true"></th>
				<th data-options="field:'merchandiseCode',width:120,sortable:true"><span class="txtCenter">商品编号</span></th>
				<th data-options="field:'merchandiseName',width:150,sortable:false"><span class="txtCenter">商品名称</span></th>
				<th data-options="field:'dxRoleName',width:100,sortable:false"><span class="txtCenter">商品定性角色</span></th>
				<th data-options="field:'dlRoleName',width:100,sortable:false"><span class="txtCenter">商品定量角色</span></th>
				<th data-options="field:'centreName',width:80,sortable:false"><span class="txtCenter">中分类</span></th>
				<th data-options="field:'smallName',width:80,sortable:false"><span class="txtCenter">小分类</span></th>
				<th data-options="field:'detailName',width:80,sortable:false"><span class="txtCenter">明细类</span></th>
				<th data-options="field:'fineName',width:80,sortable:false"><span class="txtCenter">细分类</span></th>
				<th data-options="field:'supplierCode',width:100,sortable:true"><span class="txtCenter">供应商编号</span></th>
				<th data-options="field:'supplierName',width:150,sortable:false"><span class="txtCenter">供应商名称</span></th>
			</tr>
		</thead>
	</table>	
</body>
</html>
</#compress>
