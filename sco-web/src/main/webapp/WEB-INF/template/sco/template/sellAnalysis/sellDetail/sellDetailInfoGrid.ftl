<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style>
		table {
			border-collapse: collapse;
			border: none;
			width: 100%;
			font-family:"微软雅黑";
			font-size:10pt;
			white-space : nowrap;
		}
		td, th {
			border: solid #ddd 1px;
			padding:5px;
			white-space : nowrap;
		}
		th {
			background-color: #EEEEEE;
			white-space : nowrap;
		}
		label{
			font-family:"微软雅黑";
			font-size:10pt;
			white-space : nowrap;
			padding:5px;
		}
	</style>
</head>
<body>
	<label>明细表：</label>
	<table>
		<tr>
			<th>地区</th>
			<th>销售日期</th>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>供应商编号</th>
			<th>供应商名称</th>
			<th>商品定性角色</th>
			<th>商品定量角色</th>
			<th>中分类</th>
			<th>小分类</th>
			<th>明细类</th>
			<th>细分类</th>
			<th>采购部门</th>
			<th>销售量（基本价格单位）</th>
			<th>销售额（元）</th>
			<th>毛利额（元）</th>
			<th>销售量占比(占所有商品)</th>
			<th>销售额占比(占所有商品)</th>
			<th>毛利额占比(占所有商品)</th>
			<th>销售量占比(占小分类)</th>
			<th>销售额占比(占小分类)</th>
			<th>毛利额占比(占小分类)</th>
			<th>销售量占比(占明细类)</th>
			<th>销售额占比(占明细类)</th>
			<th>毛利额占比(占明细类)</th>
			<th>PSD销售量（基本价格单位）</th>
			<th>PSD销售额（元）</th>
			<th>PSD毛利额（元）</th>
			<th>权限数</th>
			<th>权限店天数</th>
			<th>销售店天数</th>
			<th>活跃度</th>
			<th>A级店数</th>
			<th>B级店数</th>
			<th>C级店数</th>
			<th>D级店数</th>
		</tr>
		<#list dataList as list>
		<tr>
			<td>${list.sellRegion}</td>
			<td>${list.sellDate}</td>
			<td>${list.merchandiseCode}</td>
			<td>${list.merchandiseName}</td>
			<td>${list.supplierCode}</td>
			<td>${list.supplierName}</td>
			<td>${list.dxRoleName}</td>
			<td>${list.dlRoleName}</td>
			<td>${list.centreName}</td>
			<td>${list.smallName}</td>
			<td>${list.detailName}</td>
			<td>${list.fineName}</td>
			<td>${list.purchaseDepartments}</td>
			<td style="text-align:right">${(list.sellQuantity?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.sellTotalPrice?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.sellProfit?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.sellQuantityProportionM?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellTotalPriceProportionM?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellProfitProportionM?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellQuantityProportionS?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellTotalPriceProportionS?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellProfitProportionS?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellQuantityProportionD?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellTotalPriceProportionD?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellProfitProportionD?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.psdSellQuantity?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.psdSellTotalPrice?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.psdSellProfit?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.sumStore?string(",##0"))!}</td>
			<td style="text-align:right">${(list.permissionStoreQuantity?string(",##0"))!}</td>
			<td style="text-align:right">${(list.sellStoreQuantity?string(",##0"))!}</td>
			<td style="text-align:right"><#if list.active&gt;100 >100.00</#if><#if list.active&lt;=100 >${list.active?string(",##0.00")}</#if>%</td>
			<td style="text-align:right">${(list.storeA?string(",##0"))!}</td>
			<td style="text-align:right">${(list.storeB?string(",##0"))!}</td>
			<td style="text-align:right">${(list.storeC?string(",##0"))!}</td>
			<td style="text-align:right">${(list.storeD?string(",##0"))!}</td>
		</tr>
		</#list>
	</table>
	<div style="padding: 15px;"></div>
	<label>汇总表：</label>
	<table>
		<tr>
			<th>地区</th>
			<th>销售日期</th>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>供应商编号</th>
			<th>供应商名称</th>
			<th>商品定性角色</th>
			<th>商品定量角色</th>
			<th>中分类</th>
			<th>小分类</th>
			<th>明细类</th>
			<th>细分类</th>
			<th>采购部门</th>
			<th>销售量</th>
			<th>销售额</th>
			<th>毛利额</th>
			<th>销售量占比(占所有商品)</th>
			<th>销售额占比(占所有商品)</th>
			<th>毛利额占比(占所有商品)</th>
			<th>销售量占比(占小分类)</th>
			<th>销售额占比(占小分类)</th>
			<th>毛利额占比(占小分类)</th>
			<th>销售量占比(占明细类)</th>
			<th>销售额占比(占明细类)</th>
			<th>毛利额占比(占明细类)</th>
			<th>PSD销售量</th>
			<th>PSD销售额</th>
			<th>PSD毛利额</th>
			<th>权限数</th>
			<th>权限店天数</th>
			<th>销售店天数</th>
			<th>活跃度</th>
			<th>A级店数</th>
			<th>B级店数</th>
			<th>C级店数</th>
			<th>D级店数</th>
		</tr>
		<#list dataLists as list>
		<tr>
			<td>${list.sellRegion}</td>
			<td>${list.minDate}~${list.maxDate}</td>
			<td>${list.merchandiseCode}</td>
			<td>${list.merchandiseName}</td>
			<td>${list.supplierCode}</td>
			<td>${list.supplierName}</td>
			<td>${list.dxRoleName}</td>
			<td>${list.dlRoleName}</td>
			<td>${list.centreName}</td>
			<td>${list.smallName}</td>
			<td>${list.detailName}</td>
			<td>${list.fineName}</td>
			<td>${list.purchaseDepartments}</td>
			<td style="text-align:right">${(list.sellQuantity?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.sellTotalPrice?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.sellProfit?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.sellQuantityProportionM?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellTotalPriceProportionM?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellProfitProportionM?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellQuantityProportionS?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellTotalPriceProportionS?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellProfitProportionS?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellQuantityProportionD?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellTotalPriceProportionD?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.sellProfitProportionD?string(",##0.00"))!}%</td>
			<td style="text-align:right">${(list.psdSellQuantity?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.psdSellTotalPrice?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.psdSellProfit?string(",##0.00"))!}</td>
			<td style="text-align:right">${(list.sumStore?string(",##0"))!}</td>
			<td style="text-align:right">${(list.permissionStoreQuantity?string(",##0"))!}</td>
			<td style="text-align:right">${(list.sellStoreQuantity?string(",##0"))!}</td>
			<td style="text-align:right"><#if list.active&gt;100 >100.00</#if><#if list.active&lt;=100 >${list.active?string(",##0.00")}</#if>%</td>
			<td style="text-align:right">${(list.storeA?string(",##0"))!}</td>
			<td style="text-align:right">${(list.storeB?string(",##0"))!}</td>
			<td style="text-align:right">${(list.storeC?string(",##0"))!}</td>
			<td style="text-align:right">${(list.storeD?string(",##0"))!}</td>
		</tr>
		</#list>
	</table>
</body>
</html>