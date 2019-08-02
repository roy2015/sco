<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script src="js/echarts/echarts.js"></script>
<script type="text/javascript">
	<#include "sco/shareFunctionAnalysis/sellAnalysis/merchandisePromotionAnalysis/merchandisePromotionInfoModel.js" />
</script>

<link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body>
	<input type="hidden" name="minDate" id="minDate" value="${minDate}" />
	<input type="hidden" name="maxDate" id="maxDate" value="${maxDate}" />
	<input type="hidden" name="sellRegion" id="sellRegion" value="${sellRegion}" />
	<input type="hidden" name="regionName" id="regionName" value="${regionName}" />
	<input type="hidden" name="supplierCode" id="supplierCode" value="${supplierCode}" />
	<input type="hidden" name="merchandiseCode" id="merchandiseCode" value="${merchandiseCode}" />
	<input type="hidden" name="show" id="show" value="${show}" />
	<input type="hidden" name="selectMinDate" id="selectMinDate" />
	<input type="hidden" name="selectMaxDate" id="selectMaxDate" />
	<input type="hidden" name="day" id="day"  />
	<div id="tableOne" style="height:200px;">
	<#-- 数据表格 -->
	<table id="merchandisePromotionAnalysis_grid" title="所选时间范围内商品促销记录"
	 fit="true" class="easyui-datagrid"   pagination="true"  pageSize="20" pageList="[ 10, 20, 30, 40 ]"
		data-options="rownumbers:true" url="merchandisePromotionAnalysis_listMerchandisePromotionAnalysis_2.html?supplierCode=${supplierCode}&merchandiseCode=${merchandiseCode}&minDate=${minDate}&maxDate=${maxDate}&show=${show}&sellRegion=${sellRegion}">
		<thead>
			<tr>
				<th align="center" data-options="field:'check',width:40,checkbox:true"></th>
				<th data-options="field:'sellRegionName',width:120">地区</th>
				<th align="right" data-options="field:'sellPrice',width:100,formatter:merchandisePromotionInfoFn.bfb">促销价格</th>
				<th data-options="field:'netWeight',width:100">进货单位</th>
				<th data-options="field:'minDate',width:100">促销开始日期</th>
				<th data-options="field:'maxDate',width:100">促销结束日期</th>
				<th align="right" data-options="field:'dayS',width:100">促销总天数</th>
				<th data-options="field:'promotionName',width:100">促销活动名称</th>
				<th align="right" data-options="field:'dayA',width:100">促销中已持续日期</th>
			</tr>
		</thead>
	</table>
	</div>
	<#-- 工具栏 -->
	<div id="merchandisePromotionInfo_toolbar" style="margin: 5px 5 5px 5; padding: 3px">
		<#-- 查询条件 -->
		<a onclick="merchandisePromotionInfoFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 分析 </a>
		<a onclick="merchandisePromotionInfoFn.clear()" plain="true"	class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a> 
		<a onclick="merchandisePromotionInfoFn.execlMerchandise()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'"> 导出Execl </a>
		<a onclick="merchandisePromotionInfoFn.saveFile()" plain="true"class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a> 
	</div>
	<div id="tableInfo">
		
	</div>
	<div id="merchandise" style="height:150px;">
	<table id="merchandiseGrid"
		title="商品信息"
	 fit="true" class="easyui-datagrid"   pagination="true"  pageSize="5" pageList="[ 5, 10, 15, 20 ]"
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'merchandiseCode',width:150">商品编号</th>
				<th data-options="field:'merchandiseName',width:150">商品名称</th>
				<th data-options="field:'dxRoleName',width:80,sortable:false">商品定性角色</th>
				<th data-options="field:'dlRoleName',width:80,sortable:false">商品定量角色</th>
				<th data-options="field:'centreName',width:70">中分类</th>
				<th data-options="field:'smallName',width:70">小分类</th>
				<th data-options="field:'detailName',width:70">明细类</th>
				<th data-options="field:'fineName',width:70">细分类</th>
				<th data-options="field:'purchaseDepartments',width:70,sortable:false">采购部门</th>
				<th data-options="field:'supplierCode',width:155">供应商编号</th>
				<th data-options="field:'supplierName',width:160">供应商名称</th>
			</tr>
		</thead>
	</table>
	</div>
	<#-- 数据表格 -->
	
	<div id="tableThree" class="merchandisePromotionInfo" style="height:230px;">
	<table id="merchandisePromotionInfo_grid_pool" title="商品促销表现对比汇总表" fit="true" class="easyui-datagrid" 
		data-options="rownumbers:true">
		<thead>
			<tr>
				<th colspan="17">商品销售信息</th>
				<th colspan="9">商品占比信息</th>
				<th colspan="9">商品所在明细类销售信息</th>
				<th colspan="9">商品所在小分类销售信息</th>
				<th colspan="9">所有商品销售信息</th>
			</tr>
			<tr>
				<th data-options="field:'sellRegion',width:120">地区</th>
				<th data-options="field:'type',width:120">纬度</th>
				<th data-options="field:'time',width:150">销售日期</th>
				<th align="right" data-options="field:'day',width:80">天数</th>
				<th align="right"data-options="field:'sellPrice',width:80,formatter:merchandisePromotionInfoFn.bfb">价格(元/kg或件)</th>
				<th align="right"data-options="field:'sellQuantity',width:80,formatter:merchandisePromotionInfoFn.bfb">销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPrice',width:80,formatter:merchandisePromotionInfoFn.bfb">销售额(元)</th>
				<th align="right"data-options="field:'sellProfit',width:80,formatter:merchandisePromotionInfoFn.bfb">毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityP',width:80,formatter:merchandisePromotionInfoFn.bfb">平均销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPriceP',width:80,formatter:merchandisePromotionInfoFn.bfb">平均销售额(元)</th>
				<th align="right"data-options="field:'sellProfitP',width:80,formatter:merchandisePromotionInfoFn.bfb">平均毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantity',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD销售量（KG/件）</th>
				<th align="right"data-options="field:'psdSellTotalPrice',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfit',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD毛利额(元)</th>
				<th align="right"data-options="field:'permissionStoreQuantity',width:80,formatter:merchandisePromotionInfoFn.bfb1">权限店天数</th>
				<th align="right"data-options="field:'sellStoreQuantity',width:80,formatter:merchandisePromotionInfoFn.bfb1">销售店天数</th>
				<th align="right"data-options="field:'active',width:60,formatter:merchandisePromotionInfoFn.formatterActive">活跃度</th>
				<th align="right"data-options="field:'sellQuantityProportionM',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售量占比(占所有商品)</th>
				<th align="right"data-options="field:'sellTotalPriceProportionM',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售额占比(占所有商品)</th>
				<th align="right"data-options="field:'sellProfitProportionM',width:150,formatter:merchandisePromotionInfoFn.formatterPer">毛利额占比(占所有商品)</th>
				<th align="right"data-options="field:'sellQuantityProportionS',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售量占比(占小分类)</th>
				<th align="right"data-options="field:'sellTotalPriceProportionS',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售额占比(占小分类)</th>
				<th align="right"data-options="field:'sellProfitProportionS',width:150,formatter:merchandisePromotionInfoFn.formatterPer">毛利额占比(占小分类)</th>
				<th align="right"data-options="field:'sellQuantityProportionD',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售量占比(占明细类)</th>
				<th align="right"data-options="field:'sellTotalPriceProportionD',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售额占比(占明细类)</th>
				<th align="right"data-options="field:'sellProfitProportionD',width:150,formatter:merchandisePromotionInfoFn.formatterPer">毛利额占比(占明细类)</th>
				<th align="right"data-options="field:'sellQuantityD',width:80,formatter:merchandisePromotionInfoFn.bfb">销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPriceD',width:80,formatter:merchandisePromotionInfoFn.bfb">销售额(元)</th>
				<th align="right"data-options="field:'sellProfitD',width:80,formatter:merchandisePromotionInfoFn.bfb">毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityPD',width:80,formatter:merchandisePromotionInfoFn.bfb">平均销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPricePD',width:80,formatter:merchandisePromotionInfoFn.bfb">平均销售额(元)</th>
				<th align="right"data-options="field:'sellProfitPD',width:80,formatter:merchandisePromotionInfoFn.bfb">平均毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantityD',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD销售量（KG/件）</th>
				<th align="right"data-options="field:'psdSellTotalPriceD',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfitD',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityS',width:80,formatter:merchandisePromotionInfoFn.bfb">销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPriceS',width:80,formatter:merchandisePromotionInfoFn.bfb">销售额(元)</th>
				<th align="right"data-options="field:'sellProfitS',width:80,formatter:merchandisePromotionInfoFn.bfb">毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityPS',width:80,formatter:merchandisePromotionInfoFn.bfb">平均销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPricePS',width:80,formatter:merchandisePromotionInfoFn.bfb">平均销售额(元)</th>
				<th align="right"data-options="field:'sellProfitPS',width:80,formatter:merchandisePromotionInfoFn.bfb">平均毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantityS',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD销售量（KG/件）</th>
				<th align="right"data-options="field:'psdSellTotalPriceS',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfitS',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityA',width:80,formatter:merchandisePromotionInfoFn.bfb">销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPriceA',width:80,formatter:merchandisePromotionInfoFn.bfb">销售额(元)</th>
				<th align="right"data-options="field:'sellProfitA',width:80,formatter:merchandisePromotionInfoFn.bfb">毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityPA',width:80,formatter:merchandisePromotionInfoFn.bfb">平均销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPricePA',width:80,formatter:merchandisePromotionInfoFn.bfb">平均销售额(元)</th>
				<th align="right"data-options="field:'sellProfitPA',width:80,formatter:merchandisePromotionInfoFn.bfb">平均毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantityA',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD销售量（KG/件）</th>
				<th align="right"data-options="field:'psdSellTotalPriceA',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfitA',width:80,formatter:merchandisePromotionInfoFn.bfb">PSD毛利额(元)</th>
			</tr>
		</thead>
	</table>
	</div>
	<div id="tableInfo" class="merchandisePromotionInfo" style="height:257px;">
	<table id="merchandisePromotionInfo_grid_poolInfo" title="商品促销表现明细表"  fit="true" class="easyui-datagrid" 
		data-options="rownumbers:true">
		<thead>
			<tr>
			    <th data-options="field:'sellRegion',width:120" rowspan="2">地区</th>
				<th data-options="field:'time',width:150" rowspan="2">销售日期</th>
				<th colspan="12">商品销售信息</th>
				<th colspan="9">商品占比信息</th>
				<th colspan="9">商品所在明细类销售信息</th>
				<th colspan="9">商品所在小分类销售信息</th>
				<th colspan="11">所有商品销售信息</th>
			</tr>
			<tr>
				<th align="right"data-options="field:'sellPrice',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">价格(元/kg或件)</th>
				<th align="right"data-options="field:'sellQuantity',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">销售量（KG/件）</th>
				<th align="right"data-options="field:'sellQuantityDB',width:200,formatter:merchandisePromotionInfoFn.formatterPer">日销售量VS促销后开始日期当天销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPrice',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">销售额(元)</th>
				<th align="right"data-options="field:'sellTotalPriceDB',width:220,formatter:merchandisePromotionInfoFn.formatterPer">日销售额VS促销后开始日期当天销售额(元)</th>
				<th align="right"data-options="field:'sellProfit',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantity',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD销售量（KG/件）</th>
				<th align="right"data-options="field:'psdSellTotalPrice',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfit',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD毛利额(元)</th>
				<th align="right"data-options="field:'permissionStoreQuantity',width:80,formatter:merchandisePromotionInfoFn.formatterUnit1">权限店天数</th>
				<th align="right"data-options="field:'sellStoreQuantity',width:80,formatter:merchandisePromotionInfoFn.formatterUnit1">销售店天数</th>
				<th align="right"data-options="field:'active',width:60,formatter:merchandisePromotionInfoFn.formatterActive">活跃度</th>
				<th align="right"data-options="field:'sellQuantityProportionM',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售量占比(占所有商品)</th>
				<th align="right"data-options="field:'sellTotalPriceProportionM',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售额占比(占所有商品)</th>
				<th align="right"data-options="field:'sellProfitProportionM',width:150,formatter:merchandisePromotionInfoFn.formatterPer">毛利额占比(占所有商品)</th>
				<th align="right"data-options="field:'sellQuantityProportionS',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售量占比(占小分类)</th>
				<th align="right"data-options="field:'sellTotalPriceProportionS',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售额占比(占小分类)</th>
				<th align="right"data-options="field:'sellProfitProportionS',width:150,formatter:merchandisePromotionInfoFn.formatterPer">毛利额占比(占小分类)</th>
				<th align="right"data-options="field:'sellQuantityProportionD',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售量占比(占明细类)</th>
				<th align="right"data-options="field:'sellTotalPriceProportionD',width:150,formatter:merchandisePromotionInfoFn.formatterPer">销售额占比(占明细类)</th>
				<th align="right"data-options="field:'sellProfitProportionD',width:150,formatter:merchandisePromotionInfoFn.formatterPer">毛利额占比(占明细类)</th>
				<th align="right"data-options="field:'sellQuantityD',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPriceD',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">销售额(元)</th>
				<th align="right"data-options="field:'sellProfitD',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityPD',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">平均销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPricePD',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">平均销售额(元)</th>
				<th align="right"data-options="field:'sellProfitPD',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">平均毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantityD',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD销售量（KG/件）</th>
				<th align="right"data-options="field:'psdSellTotalPriceD',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfitD',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityS',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPriceS',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">销售额(元)</th>
				<th align="right"data-options="field:'sellProfitS',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityPS',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">平均销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPricePS',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">平均销售额(元)</th>
				<th align="right"data-options="field:'sellProfitPS',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">平均毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantityS',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD销售量（KG/件）</th>
				<th align="right"data-options="field:'psdSellTotalPriceS',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfitS',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityA',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">销售量（KG/件）</th>
				<th align="right"data-options="field:'sellQuantityAB',width:200,formatter:merchandisePromotionInfoFn.formatterPer">日销售量VS促销后开始日期当天销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPriceA',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">销售额(元)</th>
				<th align="right"data-options="field:'sellTotalPriceAB',width:220,formatter:merchandisePromotionInfoFn.formatterPer">日销售额VS促销后开始日期当天销售额(元)</th>
				<th align="right"data-options="field:'sellProfitA',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">毛利额(元)</th>
				<th align="right"data-options="field:'sellQuantityPA',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">平均销售量（KG/件）</th>
				<th align="right"data-options="field:'sellTotalPricePA',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">平均销售额(元)</th>
				<th align="right"data-options="field:'sellProfitPA',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">平均毛利额(元)</th>
				<th align="right"data-options="field:'psdSellQuantityA',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD销售量（KG/件）</th>
				<th align="right"data-options="field:'psdSellTotalPriceA',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD销售额(元)</th>
				<th align="right"data-options="field:'psdSellProfitA',width:80,formatter:merchandisePromotionInfoFn.formatterUnit">PSD毛利额(元)</th>
			</tr>
		</thead>
	</table>
	</div>
	<div id="line0" style="height:400px;width:50%;float:left;">
	 
	    	
	</div>
	<div id="lines0" style="height:400px;width:50%;float:left;">
	 
	    	
	</div>
	<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:130px;" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{id:'save',text:'确定',iconCls:'save',handler:function(){merchandisePromotionInfoFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){merchandisePromotionInfoFn.closeSaveFileDlg()}}
			]
	">
		<label>将分析结果保存为：</label>
		<br/><br/>
		<input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/>		
	</div>
</body>
</html>
</#compress>
