<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script type="text/javascript">
	<#include "sco/common/masterDataType.js" />
	<#include "sco/shareFunctionAnalysis/sellAnalysis/marketPromotionAnalysis/marketPromotionAnalysisModel.js" />
</script>
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body style="background-color: #fafafa;">
	<#-- 工具栏 -->
	<div id="marketPromotionAnalysis_toolbar" >
		<form id="marketPromotionAnalysis_search" method="post">
			<table style="width: 820px;">
				<#-- 查询条件 -->
				<tr>
					<td style="text-align: right; width: 73px"><label style="color: red;" >*</label>地区:</td>
					<td><input class="easyui-combobox filterSelect" id="sellRegion" filterName="sellRegion" style="width: 124px;"
						data-options="
									valueField:'id',
									textField:'text',
									
									url:'masterDataType_listRegion_5.html',
									editable:false" /></td>
					
				</tr>
				<tr>
				<td style="text-align: right; width: 79px;"><label style="color: red;" >*</label>促销档期1:</td>
					<td colspan="3"><input class="easyui-datebox list-input" filterName="minDate" id="minDate"
						data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" /> 至 <input class="easyui-datebox list-input" filterName="maxDate"
						id="maxDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" /></td>
				</tr>
				<tr>
				<td style="text-align: right; width: 79px;">促销档期2:</td>
					<td colspan="3"><input class="easyui-datebox list-input" filterName="minDates" id="minDates"
						data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" /> 至 <input class="easyui-datebox list-input" filterName="maxDates"
						id="maxDates" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" /></td>
				</tr>
				<tr>
					<td colspan="5">
						<input type="radio" id="DL" name="DL" value="2" checked />只查非定量装商品<input type="radio" name="DL" value="1"/>只查定量装商品<input type="radio" name="DL" value="3"/>定量非定量装商品均查
					</td>
				</tr>
				<tr>
					<td  colspan="5">
						<input type="radio" id="show" name="show" value="direct" checked />只看直营门店数据<input type="radio" name="show" value="join"/>只看加盟门店数据<input type="radio" name="show" value="all"/>直营与加盟门店数据均看
					</td>
				</tr>
			</table>
			<a onclick="marketPromotionAnalysisFn.searchMarket()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 促销表现对比 </a>
			<a onclick="marketPromotionAnalysisFn.searchDetail()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 查看促销明细 </a>
			<a onclick="marketPromotionAnalysisFn.clear()" plain="true"	class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a> 
			<a onclick="marketPromotionAnalysisFn.saveFile()" plain="true"class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a> 
			<a onclick="marketPromotionAnalysisFn.execlMarket()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'"> 导出Execl </a>
		</form>
	</div>
	<#-- 数据表格 -->
	<div id="tableOne" class="marketPromotionAnalysis" style="height:460px;width:1190px;display:none;">
	<table id="marketPromotionAnalysis_grid_one" class="easyui-datagrid" fit="true"  pagination="true" pagePosition='bottom'  pageSize="20" pageList="[ 10, 20, 30, 40 ]"
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th colspan="3">促销档期信息</th>
				<th colspan="8">促销商品销售信息</th>
				<th colspan="3">促销商品占比情况</th>
				<th colspan="6">所有商品销售信息</th>
			</tr>
			<tr>
				<th data-options="field:'sellRegion',width:120,sortable:false">地区</th>
				<th data-options="field:'time',width:160,sortable:false">促销档期</th>
				<th align="right"data-options="field:'sku',width:120,sortable:false,formatter:marketPromotionAnalysisFn.bfb1">促销SKU数</th>
				<th align="right"data-options="field:'sellQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">销售量（KG）</th>
				<th align="right"data-options="field:'sellTotalPrice',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">销售额(元)</th>
				<th align="right"data-options="field:'sellProfit',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">PSD销售量（KG）</th>
				<th align="right"data-options="field:'psdSellTotalPrice',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfit',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">PSD毛利额(元)</th>
				<th align="right"data-options="field:'permissionStoreQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb1">权限店天数</th>
				<th align="right"data-options="field:'sellStoreQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb1">销售店天数</th>
				<th align="right"data-options="field:'sellQuantityProportionM',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">销售量占比(占所有商品)</th>
				<th align="right"data-options="field:'sellTotalPriceProportionM',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">销售额占比(占所有商品)</th>
				<th align="right"data-options="field:'sellProfitProportionM',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">毛利额占比(占所有商品)</th>
				<th align="right"data-options="field:'sellQuantityS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">销售量（KG）</th>
				<th align="right"data-options="field:'sellTotalPriceS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">销售额(元)</th>
				<th align="right"data-options="field:'sellProfitS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantityS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">PSD销售量（KG）</th>
				<th align="right"data-options="field:'psdSellTotalPriceS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfitS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.bfb">PSD毛利额(元)</th>
			</tr>
		</thead>
	</table>
	</div>
	<div id="tableTwo"  class="marketPromotionAnalysis" style="height:220px;width:1190px;display:none;">
	<table id="marketPromotionAnalysis_grid_two" class="easyui-datagrid" fit="true"  pagination="true" pagePosition='bottom' pageSize="20" pageList="[ 10, 20, 30, 40 ]"
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th colspan="19">商品销售信息</th>
			</tr>
			<tr>
				<th data-options="field:'merchandiseCode',width:120,sortable:false">商品编号</th>
				<th data-options="field:'merchandiseName',width:120,sortable:false">商品名称</th>
				<th data-options="field:'dxRoleName',width:100,sortable:false">商品定性角色</th>
				<th data-options="field:'dlRoleName',width:100,sortable:false">商品定量角色</th>
				<th data-options="field:'centreName',width:100,sortable:false">中分类</th>
				<th data-options="field:'smallName',width:100,sortable:false">小分类</th>
				<th data-options="field:'detailName',width:100,sortable:false">明细类</th>
				<th data-options="field:'supplierCode',width:100,sortable:false">供应商编号</th>
				<th data-options="field:'supplierName',width:100,sortable:false">供应商名称</th>
				<th data-options="field:'time',width:160,sortable:false">销售档期</th>
				<th align="right"data-options="field:'sellQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">销售量（KG）</th>
				<th align="right"data-options="field:'sellTotalPrice',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">销售额(元)</th>
				<th align="right"data-options="field:'sellProfit',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD销售量（KG）</th>
				<th align="right"data-options="field:'psdSellTotalPrice',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfit',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD毛利额(元)</th>
				<th align="right"data-options="field:'permissionStoreQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit1">权限店天数</th>
				<th align="right"data-options="field:'sellStoreQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit1">销售店天数</th>
				<th align="right"data-options="field:'active',width:60,sortable:false,formatter:marketPromotionAnalysisFn.formatterActive">活跃度</th>
			</tr>
		</thead>
	</table>
	</div>
	<div id="tableThree" class="marketPromotionAnalysis" style="height:240px;width:1190px;display:none;">
	<table id="marketPromotionAnalysis_grid_three" class="easyui-datagrid" fit="true" pagination="true" pagePosition='bottom' pageSize="20" pageList="[ 10, 20, 30, 40 ]"
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th colspan="19">商品销售信息</th>
				<th colspan="9">商品占比信息</th>
				<th colspan="9">商品所在明细类销售信息</th>
				<th colspan="9">商品所在小分类销售信息</th>
				<th colspan="9">所有商品销售信息</th>
			</tr>
			<tr>
				<th data-options="field:'merchandiseCode',width:120,sortable:false">商品编号</th>
				<th data-options="field:'merchandiseName',width:150,sortable:false">商品名称</th>
				<th data-options="field:'dxRoleName',width:100,sortable:false">商品定性角色</th>
				<th data-options="field:'dlRoleName',width:100,sortable:false">商品定量角色</th>
				<th data-options="field:'centreName',width:100,sortable:false">中分类</th>
				<th data-options="field:'smallName',width:100,sortable:false">小分类</th>
				<th data-options="field:'detailName',width:100,sortable:false">明细类</th>
				<th data-options="field:'supplierCode',width:100,sortable:false">供应商编号</th>
				<th data-options="field:'supplierName',width:100,sortable:false">供应商名称</th>
				<th data-options="field:'sellDate',width:100,sortable:false">销售日期</th>
				<th align="right" data-options="field:'sellQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">销售量（KG）</th>
				<th align="right"data-options="field:'sellTotalPrice',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">销售额(元)</th>
				<th align="right"data-options="field:'sellProfit',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD销售量（KG）</th>
				<th align="right"data-options="field:'psdSellTotalPrice',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfit',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD毛利额(元)</th>
				<th align="right"data-options="field:'permissionStoreQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit1">权限店天数</th>
				<th align="right"data-options="field:'sellStoreQuantity',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit1">销售店天数</th>
				<th align="right"data-options="field:'active',width:60,sortable:false,formatter:marketPromotionAnalysisFn.formatterActive">活跃度</th>
				<th align="right"data-options="field:'sellQuantityProportionM',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">销售量占比(占所有商品)</th>
				<th align="right"data-options="field:'sellTotalPriceProportionM',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">销售额占比(占所有商品)</th>
				<th align="right"data-options="field:'sellProfitProportionM',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">毛利额占比(占所有商品)</th>
				<th align="right"data-options="field:'sellQuantityProportionS',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">销售量占比(占小分类)</th>
				<th align="right"data-options="field:'sellTotalPriceProportionS',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">销售额占比(占小分类)</th>
				<th align="right"data-options="field:'sellProfitProportionS',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">毛利额占比(占小分类)</th>
				<th align="right"data-options="field:'sellQuantityProportionD',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">销售量占比(占明细类)</th>
				<th align="right"data-options="field:'sellTotalPriceProportionD',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">销售额占比(占明细类)</th>
				<th align="right"data-options="field:'sellProfitProportionD',width:150,sortable:false,formatter:marketPromotionAnalysisFn.formatterPer">毛利额占比(占明细类)</th>
				<th align="right"data-options="field:'sellQuantityD',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">销售量（KG）</th>
				<th align="right"data-options="field:'sellTotalPriceD',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">销售额(元)</th>
				<th align="right"data-options="field:'sellProfitD',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityPD',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">平均销售量（KG）</th>
				<th align="right"data-options="field:'sellTotalPricePD',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">平均销售额(元)</th>
				<th align="right"data-options="field:'sellProfitPD',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">平均毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantityD',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD销售量（KG）</th>
				<th align="right"data-options="field:'psdSellTotalPriceD',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfitD',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">销售量（KG）</th>
				<th align="right"data-options="field:'sellTotalPriceS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">销售额(元)</th>
				<th align="right"data-options="field:'sellProfitS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityPS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">平均销售量（KG）</th>
				<th align="right"data-options="field:'sellTotalPricePS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">平均销售额(元)</th>
				<th align="right"data-options="field:'sellProfitPS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">平均毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantityS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD销售量（KG）</th>
				<th align="right"data-options="field:'psdSellTotalPriceS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfitS',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityA',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">销售量（KG）</th>
				<th align="right"data-options="field:'sellTotalPriceA',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">销售额(元)</th>
				<th align="right"data-options="field:'sellProfitA',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityPA',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">平均销售量（KG）</th>
				<th align="right"data-options="field:'sellTotalPricePA',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">平均销售额(元)</th>
				<th align="right"data-options="field:'sellProfitPA',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">平均毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantityA',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD销售量（KG）</th>
				<th align="right"data-options="field:'psdSellTotalPriceA',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfitA',width:100,sortable:false,formatter:marketPromotionAnalysisFn.formatterUnit">PSD毛利额(元)</th>
			</tr>
		</thead>
	</table>
	</div>
	<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:130px;" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{id:'save',text:'确定',iconCls:'save',handler:function(){marketPromotionAnalysisFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){marketPromotionAnalysisFn.closeSaveFileDlg()}}
			]
	">
		<label>将分析结果保存为：</label>
		<br/><br/>
		<input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/>		
	</div>
</body>
</html>
</#compress>
