<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<style>
	body {
			font-family : "微软雅黑";
			font-size : 12px;
		}
		table {
			border-collapse: collapse;
			border: none;
			width: 100%;
			font-family : "微软雅黑";
			font-size : 12px;
			white-space : nowrap
		}
		td, th {
			border: dotted #ddd 1px;
			padding:5px;
			white-space : nowrap;
		}
		th {
			background-color: #EEEEEE;
			white-space : nowrap;
		}
		th {
			font-weight:normal;
			border: solid #ddd 1px;
			background: #FDFDFD;  <!-- 一些不支持背景渐变的浏览器 -->
			background: -moz-linear-gradient(top,  #FDFDFD 0%, #F5F5F5 100%); <!-- 兼容Firefox浏览器 -->
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FDFDFD), color-stop(100%,#F5F5F5)); <!-- 兼容chrome/Safari浏览器 -->
			background: -webkit-linear-gradient(top,  #FDFDFD 0%,#F5F5F5 100%);
			background: -o-linear-gradient(top,  #FDFDFD 0%,#ffffff 100%);
			background: -ms-linear-gradient(top,  #000000 0%,#ffffff 100%);
			background: linear-gradient(to bottom,  #FDFDFD 0%,#F5F5F5 100%);
			filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#FDFDFD', endColorstr='#F5F5F5',GradientType=0 );  <!-- 兼容IE浏览器 -->
		}
		:root {filter:none;}
	</style>
</head>
<body>
	<div>
		<label>地区：${sellRegion}</label><br/> 
		<label>促销档期1：${time1}</label><br/> 
		<#if (time2!="")>
			<label>促销档期2：${time2}</label><br/> 
		</#if>
		<label>${searchType}</label><br/> 
		<label>${searchTable}</label>
	</div>
	<#if (saveType=="1")>
	<table border="1">
			<tr>
				<th colspan="3">促销档期信息</th>
				<th colspan="8">促销商品销售信息</th>
				<th colspan="3">促销商品占比情况</th>
				<th colspan="6">所有商品销售信息</th>
			</tr>
			<tr>
				<th>地区</th>
				<th>促销档期</th>
				<th>促销SKU数</th>
				<th>销售量（KG）</th>
				<th>销售额(元)</th>
				<th>毛利额(元)</th>
				<th>PSD销售量（KG）</th>
				<th>PSD销售额(元)</th>
				<th>PSD毛利额(元)</th>
				<th>权限店天数</th>
				<th>销售店天数</th>
				<th>销售量占比(占所有商品)</th>
				<th>销售额占比(占所有商品)</th>
				<th>毛利额占比(占所有商品)</th>
				<th>销售量（KG）</th>
				<th>销售额(元)</th>
				<th>毛利额(元)</th>
				<th>PSD销售量（KG）</th>
				<th>PSD销售额(元)</th>
				<th>PSD毛利额(元)</th>
			</tr>
			<#list dataList as list>
			<tr>
				<td>${list.sellRegion}</td>
				<td>${list.time}</td>
				<td style="text-align:right"><#if list.bfb!="%">${(list.sku?string(",##0"))!}</#if><#if list.bfb=="%">${(list.sku?string(",##0.00"))!}%</#if></td>
				<td style="text-align:right">${(list.sellQuantity?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right">${(list.sellTotalPrice?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right">${(list.sellProfit?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right">${(list.psdSellQuantity?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right">${(list.psdSellTotalPrice?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right">${(list.psdSellProfit?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right"><#if list.bfb!="%">${(list.permissionStoreQuantity?string(",##0"))!}</#if><#if list.bfb=="%">${(list.permissionStoreQuantity?string(",##0.00"))!}%</#if></td>
				<td style="text-align:right"><#if list.bfb!="%">${(list.sellStoreQuantity?string(",##0"))!}</#if><#if list.bfb=="%">${(list.sellStoreQuantity?string(",##0.00"))!}%</#if></td>
				<td style="text-align:right">${(list.sellQuantityProportionM?string(",##0.00"))!}<#if list.bfb=="%" || list.sellQuantityProportionM!="" >%</#if></td>
				<td style="text-align:right">${(list.sellTotalPriceProportionM?string(",##0.00"))!}<#if list.bfb=="%" || list.sellTotalPriceProportionM!="" >%</#if></td>
				<td style="text-align:right">${(list.sellProfitProportionM?string(",##0.00"))!}<#if list.bfb=="%" || list.sellProfitProportionM!="">%</#if></td>
				<td style="text-align:right">${(list.sellQuantityS?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right">${(list.sellTotalPriceS?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right">${(list.sellProfitS?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right">${(list.psdSellQuantityS?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right">${(list.psdSellTotalPriceS?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
				<td style="text-align:right">${(list.psdSellProfitS?string(",##0.00"))!}<#if list.bfb=="%">%</#if></td>
			</tr>
			</#list>
	</table>
	</#if>
	<#if  (saveType=="2")>
	<table border="1">
			<tr>
				<th colspan="19">商品销售信息</th>
			</tr>
			<tr>
				<th>商品编号</th>
				<th>商品名称</th>
				<th>商品定性角色</th>
				<th>商品定量角色</th>
				<th>中分类</th>
				<th>小分类</th>
				<th>明细类</th>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>销售档期</th>
				<th>销售量（KG）</th>
				<th>销售额(元)</th>
				<th>毛利额(元)</th>
				<th>PSD销售量（KG）</th>
				<th>PSD销售额(元)</th>
				<th>PSD毛利额(元)</th>
				<th>权限店天数</th>
				<th>销售店天数</th>
				<th>活跃度</th>
			</tr>
			<#list dataList as detail>
			<tr>
				<td>${detail.merchandiseCode}</td>
				<td>${detail.merchandiseName}</td>
				<td>${detail.dxRoleName}</td>
				<td>${detail.dlRoleName}</td>
				<td>${detail.centreName}</td>
				<td>${detail.smallName}</td>
				<td>${detail.detailName}</td>
				<td>${detail.supplierCode}</td>
				<td>${detail.supplierName}</td>
				<td>${detail.time}</td>
				<td style="text-align:right">${detail.sellQuantity?string(",##0.00")}</td>
				<td style="text-align:right">${detail.sellTotalPrice?string(",##0.00")}</td>
				<td style="text-align:right">${detail.sellProfit?string(",##0.00")}</td>
				<td style="text-align:right">${detail.psdSellQuantity?string(",##0.00")}</td>
				<td style="text-align:right">${detail.psdSellTotalPrice?string(",##0.00")}</td>
				<td style="text-align:right">${detail.psdSellProfit?string(",##0.00")}</td>
				<td style="text-align:right">${detail.permissionStoreQuantity?string(",##0")}</td>
				<td style="text-align:right">${detail.sellStoreQuantity?string(",##0")}</td>
				<td style="text-align:right">${detail.active?string(",##0.00")}<#if detail.active!="" >%</#if></td>
			</tr>
			</#list>
	</table>
	<br/>
	<br/>
	<table border="1">
			<tr>
				<th colspan="19">商品销售信息</th>
				<th colspan="9">商品占比信息</th>
				<th colspan="9">商品所在明细类销售信息</th>
				<th colspan="9">商品所在小分类销售信息</th>
				<th colspan="9">所有商品销售信息</th>
			</tr>
			<tr>
				<th>商品编号</th>
				<th>商品名称</th>
				<th>商品定性角色</th>
				<th>商品定量角色</th>
				<th>中分类</th>
				<th>小分类</th>
				<th>明细类</th>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>销售日期</th>
				<th>销售量（KG）</th>
				<th>销售额(元)</th>
				<th>毛利额(元)</th>
				<th>PSD销售量（KG）</th>
				<th>PSD销售额(元)</th>
				<th>PSD毛利额(元)</th>
				<th>权限店天数</th>
				<th>销售店天数</th>
				<th>活跃度</th>
				<th>销售量占比(占所有商品)</th>
				<th>销售额占比(占所有商品)</th>
				<th>毛利额占比(占所有商品)</th>
				<th>销售量占比(占小分类)</th>
				<th>销售额占比(占小分类)</th>
				<th>毛利额占比(占小分类)</th>
				<th>销售量占比(占明细类)</th>
				<th>销售额占比(占明细类)</th>
				<th>毛利额占比(占明细类)</th>
				<th>销售量（KG）</th>
				<th>销售额(元)</th>
				<th>毛利额(元)</th>
				<th>平均销售量（KG）</th>
				<th>平均销售额(元)</th>
				<th>平均毛利额(元)</th>
				<th>PSD销售量（KG）</th>
				<th>PSD销售额(元)</th>
				<th>PSD毛利额(元)</th>
				<th>销售量（KG）</th>
				<th>销售额(元)</th>
				<th>毛利额(元)</th>
				<th>平均销售量（KG）</th>
				<th>平均销售额(元)</th>
				<th>平均毛利额(元)</th>
				<th>PSD销售量（KG）</th>
				<th>PSD销售额(元)</th>
				<th>PSD毛利额(元)</th>
				<th>销售量（KG）</th>
				<th>销售额(元)</th>
				<th>毛利额(元)</th>
				<th>平均销售量（KG）</th>
				<th>平均销售额(元)</th>
				<th>平均毛利额(元)</th>
				<th>PSD销售量（KG）</th>
				<th>PSD销售额(元)</th>
				<th>PSD毛利额(元)</th>
			</tr>
			<#list dataLists as details>
			<tr>
				<td>${details.merchandiseCode }</td>
				<td>${details.merchandiseName }</td>
				<td>${details.dxRoleName }</td>
				<td>${details.dlRoleName }</td>
				<td>${details.centreName }</td>
				<td>${details.smallName }</td>
				<td>${details.detailName }</td>
				<td>${details.supplierCode }</td>
				<td>${details.supplierName }</td>
				<td style="text-align:right">${details.sellDate}</td>
				<td style="text-align:right">${details.sellQuantity?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellTotalPrice?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellProfit?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellQuantity?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellTotalPrice?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellProfit?string(",##0.00")}</td>
				<td style="text-align:right">${details.permissionStoreQuantity?string(",##0")}</td>
				<td style="text-align:right">${details.sellStoreQuantity?string(",##0")}</td>
				<td style="text-align:right"><#if details.active&gt;100 >100.00</#if><#if details.active&lt;=100 >${details.active?string(",##0.00")}</#if>%</td>
				<td style="text-align:right">${details.sellQuantityProportionM?string(",##0.00")}<#if details.sellQuantityProportionM!="" >%</#if></td>
				<td style="text-align:right">${details.sellTotalPriceProportionM?string(",##0.00")}<#if details.sellTotalPriceProportionM!="" >%</#if></td>
				<td style="text-align:right">${details.sellProfitProportionM?string(",##0.00")}<#if details.sellProfitProportionM!="" >%</#if></td>
				<td style="text-align:right">${details.sellQuantityProportionS?string(",##0.00")}<#if details.sellQuantityProportionS!="" >%</#if></td>
				<td style="text-align:right">${details.sellTotalPriceProportionS?string(",##0.00")}<#if details.sellTotalPriceProportionS!="" >%</#if></td>
				<td style="text-align:right">${details.sellProfitProportionS?string(",##0.00")}<#if details.sellProfitProportionS!="" >%</#if></td>
				<td style="text-align:right">${details.sellQuantityProportionD?string(",##0.00")}<#if details.sellQuantityProportionD!="" >%</#if></td>
				<td style="text-align:right">${details.sellTotalPriceProportionD?string(",##0.00")}<#if details.sellTotalPriceProportionD!="" >%</#if></td>
				<td style="text-align:right">${details.sellProfitProportionD?string(",##0.00")}<#if details.sellProfitProportionD!="" >%</#if></td>
				<td style="text-align:right">${details.sellQuantityD?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellTotalPriceD?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellProfitD?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellQuantityPD?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellTotalPricePD?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellProfitPD?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellQuantityD?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellTotalPriceD?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellProfitD?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellQuantityS?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellTotalPriceS?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellProfitS?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellQuantityPS?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellTotalPricePS?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellProfitPS?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellQuantityS?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellTotalPriceS?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellProfitS?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellQuantityA?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellTotalPriceA?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellProfitA?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellQuantityPA?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellTotalPricePA?string(",##0.00")}</td>
				<td style="text-align:right">${details.sellProfitPA?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellQuantityA?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellTotalPriceA?string(",##0.00")}</td>
				<td style="text-align:right">${details.psdSellProfitA?string(",##0.00")}</td>
			</tr>
			</#list>
	</table>
	</#if>
</body>
</html>