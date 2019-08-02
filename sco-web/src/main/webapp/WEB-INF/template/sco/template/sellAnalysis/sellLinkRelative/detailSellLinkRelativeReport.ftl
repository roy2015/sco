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
	<span>明细类信息:</span>
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
		<span>明细类环比信息:<br/>
		<#if (directJoin??)&&(directJoin=='direct')>
		只看直营门店数据
		<#elseif (directJoin??)&&(directJoin=='join')>
		 只看加盟门店数据
		<#else>
		 直营与加盟门店数据均看
		</#if><br/>
		<#if (rationed??)&&(rationed=='gjz') >
		只查非定量装商品
		<#elseif (rationed??)&&(rationed=='dlz') >
		只查定定量装商品
		<#else>
		定量装和非定量装均看
		</#if>	<br/>
		 环比周期的天数：${linkRelativeCycle}<br/>
		  时间的范围：${startDate?string('yyyy-MM-dd')}至${endDate?string('yyyy-MM-dd')}<br/>
		</span>
	</div>
	<table >
				<tr>
					<th style="text-align:center">日期</th>
					<th style="text-align:center">销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center">环比销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center">销售量环比增长</th>
					<th style="text-align:center">销售额(元)</th>
					<th style="text-align:center">环比销售额(元)</th>
					<th style="text-align:center">销售额环比增长</th>
					<th style="text-align:center">毛利额(元)</th>
					<th style="text-align:center">环比毛利额(元)</th>
					<th style="text-align:center">毛利额环比增长</th>
					<th style="text-align:center">PSD销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center">环比PSD销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center">PSD销售量环比增长</th>
					<th style="text-align:center">PSD销售额(元)</th>
					<th style="text-align:center">环比PSD销售额(元)</th>
					<th style="text-align:center">PSD销售额环比增长</th>
					<th style="text-align:center">PSD毛利额(元)</th>
					<th style="text-align:center">环比PSD毛利额(元)</th>
					<th style="text-align:center">PSD毛利额环比增长</th>
				</tr>
		  <#list merchandiseList as merchandise>
			  	<tr>
		  			<td style="text-align:right"><#if (merchandise.sellDate)!>${merchandise.sellDate}</#if></td>
		  			
		  			<td style="text-align:right"><#if (merchandise.sellQuantity)!>${merchandise.sellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellQuantity)!>${merchandise.beforeSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellQuantityChange)!&&(merchandise.sellQuantityChange != 0)>${merchandise.sellQuantityChange?string(",##0.00")}%</#if></td>
		  			
		  			<td style="text-align:right"><#if (merchandise.sellTotalPrice)!>${merchandise.sellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellTotalPrice)!>${merchandise.beforeSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellTotalPriceChange)!&&(merchandise.sellTotalPriceChange != 0)>${merchandise.sellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			
		  			<td style="text-align:right"><#if (merchandise.sellProfit)!>${merchandise.sellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellProfit)!>${merchandise.beforeSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellProfitChange)!&&(merchandise.sellProfitChange != 0)>${merchandise.sellProfitChange?string(",##0.00")}%</#if></td>
		  			
		  			<td style="text-align:right"><#if (merchandise.psdSellQuantity)!>${merchandise.psdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellQuantity)!>${merchandise.beforePsdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.psdSellQuantityChange)!&&(merchandise.psdSellQuantityChange != 0)>${merchandise.psdSellQuantityChange?string(",##0.00")}%</#if></td>
		  			
		  			<td style="text-align:right"><#if (merchandise.psdSellTotalPrice)!>${merchandise.psdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellTotalPrice)!>${merchandise.beforePsdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.psdSellTotalPriceChange)!&&(merchandise.psdSellTotalPriceChange != 0)>${merchandise.psdSellTotalPriceChange?string(",##0.00")}%</#if></td>
		  			
		  			<td style="text-align:right"><#if (merchandise.psdSellProfit)!>${merchandise.psdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellProfit)!>${merchandise.beforePsdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.psdSellProfitChange)!&&(merchandise.psdSellProfitChange != 0)>${merchandise.psdSellProfitChange?string(",##0.00")}%</#if></td>
		        </tr>
		</#list>
</table>
</body>
</html>