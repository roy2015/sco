<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style>
	body {
	font-family: "微软雅黑";
	font-size: 12px;
	white-space: nowrap;
	}
	div{
		margin:15px 0px;
	}
	span{
		line-height:17px;
	}

	table {
		border-collapse: collapse;
		border: none;
		font-family: "微软雅黑";
		font-size: 12px;
		white-space: nowrap;
	}
	th,td {
		line-height:25px;
		padding:0 8px;
	}
	td {
		border: dotted #BBB 1px;
	}
	th {
		font-weight: normal;
		border: solid #ddd 1px;
	}
	th {
		background: #FDFDFD;
		<!-- 一些不支持背景渐变的浏览器 -->
		background: -moz-linear-gradient(top,  #FDFDFD 0%, #F5F5F5 100%);
		<!-- 兼容Firefox浏览器 -->
		background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FDFDFD), color-	         stop(100%,#F5F5F5));
		<!-- 兼容chrome/Safari浏览器 -->
		background: -webkit-linear-gradient(top,  #FDFDFD 0%,#F5F5F5 100%);
		background: -o-linear-gradient(top,  #FDFDFD 0%,#ffffff 100%);
		background: -ms-linear-gradient(top,  #000000 0%,#ffffff 100%);
		background: linear-gradient(to bottom,  #FDFDFD 0%,#F5F5F5 100%);
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#FDFDFD', endColorstr='#F5F5F5',GradientType=0 );
	}
		<!-- 兼容IE浏览器 -->
	:root {
		filter: none;
	}
	</style>
</head>
<body>
	<span>明细类信息：</span>
	<table>
		<tr>
			<th style="text-align:center">明细类名称</th>
			<th style="text-align:center">SKU数</th>
		</tr>
		<#list detailTypeList as sell>
			<tr>
				<td style="text-align:center">${sell.detailTypeName}</td>
				<td style="text-align:center">${sell.detailTypeSku}</td>
			</tr>
		</#list>
	</table>
	<div>
		<span>明细类同比信息：<br/>
		<#if (directJoin??)&&(directJoin=='direct') >
		只看直营门店数据
		<#elseif (directJoin??)&&(directJoin=='join') >
		只看加盟门店数据
		<#else>
		直营与加盟门店数据均看
		</#if>
		<br/>
		 <#if (rationed??)&&(rationed=='gjz') >
		  只查非定量装商品
		<#elseif (rationed??)&&(rationed=='dlz') >
		只查定定量装商品
		<#else>
		定量装和非定量装均看
		</#if>	
		<br/>
		数据时间范围：${startDate} 至 ${endDate}
		</span>
	</div>
	<table >
				<tr>
					<th style="text-align:center" rowspan="2">月份</th>
					<#if seeYear==3>
					<th style="text-align:center" colspan="8">销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="8">销售额(元)</th>
					<th style="text-align:center" colspan="8">毛利额(元)</th>
					<th style="text-align:center" colspan="8">PSD销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="8">PSD销售额(元)</th>
					<th style="text-align:center" colspan="8">PSD毛利额(元)</th>
					<#elseif seeYear==2>
					<th style="text-align:center" colspan="6">销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="6">销售额(元)</th>
					<th style="text-align:center" colspan="6">毛利额(元)</th>
					<th style="text-align:center" colspan="6">PSD销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="6">PSD销售额(元)</th>
					<th style="text-align:center" colspan="6">PSD毛利额(元)</th>
					<#else>
					<th style="text-align:center" colspan="4">销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="4">销售额(元)</th>
					<th style="text-align:center" colspan="4">毛利额(元)</th>
					<th style="text-align:center" colspan="4">PSD销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="4">PSD销售额(元)</th>
					<th style="text-align:center" colspan="4">PSD毛利额(元)</th>
					</#if>
				</tr>
				<tr>
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>
					
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>	
					
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>	
					
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>	
					
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>	
					
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>	
				</tr>
		  <#list detailSellList as merchandise>
			  	<tr>
		  			<td style="text-align:right"><#if (merchandise.sellMonth)!><#if merchandise.sellMonth?length gt 5>${merchandise.sellMonth?substring(5,7)}<#else>总计</#if></#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellQuantity)!>${merchandise.sellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellQuantityChange)!&&(merchandise.sellQuantityChange != 0)>${merchandise.sellQuantityChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellQuantity)!>${merchandise.lastSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellQuantityChange)!&&(merchandise.lastSellQuantityChange != 0)>${merchandise.lastSellQuantityChange?string(",##0.00")}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforeSellQuantity)!>${merchandise.beforeSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellQuantityChange)!&&(merchandise.beforeSellQuantityChange != 0)>${merchandise.beforeSellQuantityChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellQuantity)!>${merchandise.fourthSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellQuantityChange)!&&(merchandise.fourthSellQuantityChange != 0)>${merchandise.fourthSellQuantityChange?string(",##0.00")}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforeSellQuantity)!>${merchandise.beforeSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellQuantityChange)!&&(merchandise.beforeSellQuantityChange != 0)>${merchandise.beforeSellQuantityChange?string(",##0.00")}%</#if></td>
		  			</#if>
		  			
		  			<td style="text-align:right"><#if (merchandise.sellTotalPrice)!>${merchandise.sellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellTotalPriceChange)!&&(merchandise.sellTotalPriceChange != 0)>${merchandise.sellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellTotalPrice)!>${merchandise.lastSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellTotalPriceChange)!&&(merchandise.lastSellTotalPriceChange != 0)>${merchandise.lastSellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforeSellTotalPrice)!>${merchandise.beforeSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellTotalPriceChange)!&&(merchandise.beforeSellTotalPriceChange != 0)>${merchandise.beforeSellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellTotalPrice)!>${merchandise.fourthSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellTotalPriceChange)!&&(merchandise.fourthSellTotalPriceChange != 0)>${merchandise.fourthSellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforeSellTotalPrice)!>${merchandise.beforeSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellTotalPriceChange)!&&(merchandise.beforeSellTotalPriceChange != 0)>${merchandise.beforeSellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			</#if>
		  			
		  			<td style="text-align:right"><#if (merchandise.sellProfit)!>${merchandise.sellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellProfitChange)!&&(merchandise.sellProfitChange != 0)>${merchandise.sellProfitChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellProfit)!>${merchandise.lastSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellProfitChange)!&&(merchandise.lastSellProfitChange != 0)>${merchandise.lastSellProfitChange?string(",##0.00")}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforeSellProfit)!>${merchandise.beforeSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellProfitChange)!&&(merchandise.beforeSellProfitChange != 0)>${merchandise.beforeSellProfitChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellProfit)!>${merchandise.fourthSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellProfitChange)!&&(merchandise.fourthSellProfitChange != 0)>${merchandise.fourthSellProfitChange?string(",##0.00")}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforeSellProfit)!>${merchandise.beforeSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellProfitChange)!&&(merchandise.beforeSellProfitChange != 0)>${merchandise.beforeSellProfitChange?string(",##0.00")}%</#if></td>
		  			</#if>
		  			
		  			<td style="text-align:right"><#if (merchandise.psdSellQuantity)!>${merchandise.psdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.psdSellQuantityChange)!>${merchandise.psdSellQuantityChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellQuantity)!>${merchandise.lastPsdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellQuantityChange)!>${merchandise.lastPsdSellQuantityChange?string(",##0.00")}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellQuantity)!>${merchandise.beforePsdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellQuantityChange)!>${merchandise.beforePsdSellQuantityChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellQuantity)!>${merchandise.fourthPsdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellQuantityChange)!>${merchandise.fourthPsdSellQuantityChange?string(",##0.00")}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellQuantity)!>${merchandise.beforePsdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellQuantityChange)!>${merchandise.beforePsdSellQuantityChange?string(",##0.00")}%</#if></td>
		  			</#if>
		  			
		  			<td style="text-align:right"><#if (merchandise.psdSellTotalPrice)!>${merchandise.psdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.psdSellTotalPriceChange)!>${merchandise.psdSellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellTotalPrice)!>${merchandise.lastPsdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellTotalPriceChange)!>${merchandise.lastPsdSellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellTotalPrice)!>${merchandise.beforePsdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellTotalPriceChange)!>${merchandise.beforePsdSellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellTotalPrice)!>${merchandise.fourthPsdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellTotalPriceChange)!>${merchandise.fourthPsdSellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellTotalPrice)!>${merchandise.beforePsdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellTotalPriceChange)!>${merchandise.beforePsdSellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			</#if>
		  			
		  			<td style="text-align:right"><#if (merchandise.psdSellProfit)!>${merchandise.psdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.psdSellProfitChange)!>${merchandise.psdSellProfitChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellProfit)!>${merchandise.lastPsdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellProfitChange)!>${merchandise.lastPsdSellProfitChange?string(",##0.00")}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellProfit)!>${merchandise.beforePsdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellProfitChange)!>${merchandise.beforePsdSellProfitChange?string(",##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellProfit)!>${merchandise.fourthPsdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellProfitChange)!>${merchandise.fourthPsdSellProfitChange?string(",##0.00")}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellProfit)!>${merchandise.beforePsdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellProfitChange)!>${merchandise.beforePsdSellProfitChange?string(",##0.00")}%</#if></td>
		  			</#if>
		        </tr>	
			</#list>
	</table>
</body>
</html>