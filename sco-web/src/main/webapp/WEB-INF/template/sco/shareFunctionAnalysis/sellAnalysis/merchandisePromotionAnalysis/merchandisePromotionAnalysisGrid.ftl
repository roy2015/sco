<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script type="text/javascript">
	<#include "sco/common/masterDataType.js" />
	<#include "sco/shareFunctionAnalysis/sellAnalysis/merchandisePromotionAnalysis/merchandisePromotionAnalysisModel.js" />
</script>
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body>
	<#-- 工具栏 -->
	<div id="merchandisePromotionAnalysis_toolbar">
		<form id="merchandisePromotionAnalysis_search" method="post">
			<#-- 查询条件 -->
			<table style="width: 880px;">
				
				<tr>
					<td style="text-align: right; width: 80px;">供应商编号:</td>
					<td style="width: 110px;"><input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width: 120px;" /></td>
					<td style="text-align: right; width: 70px">供应商名称:</td>
					<td style="width: 110px;"><input filterName="supplierName" class="easyui-validatebox" id="supplierName" style="width: 120px;" /></td>
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
					<td style="text-align:right;width: 73px;">采购部门:</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="purchaseDepartments" id="purchaseDepartments" name="purchaseDepartments" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							
							editable:false,
							url:'businessComBox_procurementDepartments_5.html'">
					</td>
					<td style="text-align: right; width: 73px"><label style="color: red;" >*</label>地区:</td>
					<td><input class="easyui-combobox filterSelect" id="sellRegion" filterName="sellRegion" style="width: 124px;"
						data-options="
									valueField:'id',
									textField:'text',
									
									url:'masterDataType_listRegion_5.html',
									editable:false" /></td>
				<td style="text-align: right; width: 79px;"><label style="color: red;" >*</label>时间范围:</td>
					<td><input class="easyui-datebox list-input" filterName="minDate" id="minDate"
						data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" />
						</td>
						<td style="text-align: right; ">至:</td>
						 <td><input class="easyui-datebox list-input" filterName="maxDate"
						id="maxDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" /></td>
				</tr>
				<tr>
					<td  colspan="5">
						<input type="radio" name="show" value="direct" checked />只看直营门店数据<input type="radio" name="show" value="join"/>只看加盟门店数据<input type="radio" name="show" value="all"/>直营与加盟门店数据均看
					</td>
				</tr>
			</table>
			<a onclick="merchandisePromotionAnalysisFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 搜索 </a>
			<a onclick="merchandisePromotionAnalysisFn.clear()" plain="true"	class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a> 
			<a onclick="merchandisePromotionAnalysisFn.searchMerchandise()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 促销表现对比 </a>
		</form>
	</div>
	<#-- 数据表格 -->
	<table id="merchandisePromotionAnalysis_grid"
		toolbar="#merchandisePromotionAnalysis_toolbar"
	 fit="true" url="merchandisePromotionAnalysis_listMerchandise_2.html"   pagination="true"  pageSize="20" pageList="[ 10, 20, 30, 40 ]"
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th align="center" data-options="field:'check',width:40,checkbox:true"></th>
				<th data-options="field:'merchandiseCode',width:90,sortable:true">商品编号</th>
				<th data-options="field:'merchandiseName',width:120,sortable:false">商品名称</th>
				<th data-options="field:'dxRoleName',width:90,sortable:false">商品定性角色</th>
				<th data-options="field:'dlRoleName',width:90,sortable:false">商品定量角色</th>
				<th data-options="field:'centreName',width:80,sortable:false">中分类</th>
				<th data-options="field:'smallName',width:80,sortable:false">小分类</th>
				<th data-options="field:'detailName',width:80,sortable:false">明细类</th>
				<th data-options="field:'fineName',width:80,sortable:false">细分类</th>
				<th data-options="field:'purchaseDepartments',width:70,sortable:false">采购部门</th>
				<th data-options="field:'supplierCode',width:100,sortable:true">供应商编号</th>
				<th data-options="field:'supplierName',width:160,sortable:false">供应商名称</th>
			</tr>
		</thead>
	</table>	
</body>
</html>
</#compress>
