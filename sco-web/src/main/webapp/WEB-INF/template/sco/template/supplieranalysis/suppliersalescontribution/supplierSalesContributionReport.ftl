<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<style>
			body {
				font-family : "微软雅黑";
				font-size : 12px;
			}
			table {
				border-collapse: collapse;
				border: none;
				width: 100%;
				white-space : nowrap;
				font-family : "微软雅黑";
				font-size : 12px;
			}
			td, th {
				border: dotted #ddd 1px;
				padding:5px;
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
		<label>供应商销售贡献度排行:</label>
		<table>
			<tr>
				<th style="text-align:left">供应商编号</th>
				<th style="text-align:left">供应商名称</th>
				
				<th style="text-align:right">销售金额（元）</th>
				<th style="text-align:right">销售金额同比（元）</th>
				<th style="text-align:right">销售金额占比</th>
				<th style="text-align:right">销售金额占比同比</th>
				<th style="text-align:right">销售金额占比排名</th>
				<th style="text-align:right">销售金额占比同比排名</th>
				
				<th style="text-align:right">销量(公斤)</th>
				<th style="text-align:right">销量同比(公斤)</th>
				<th style="text-align:right">销量占比</th>
				<th style="text-align:right">销量占比同比</th>
				<th style="text-align:right">销量占比排名</th>
				<th style="text-align:right">销量占比同比排名</th>
				
				<th style="text-align:right">毛利额（元）</th>
				<th style="text-align:right">毛利额同比（元）</th>
				<th style="text-align:right">毛利额占比</th>
				<th style="text-align:right">毛利额占比同比</th>
				<th style="text-align:right">毛利额占比排名</th>
				<th style="text-align:right">毛利额占比同比排名</th>
				
				<th style="text-align:right">进货金额（元）</th>
				<th style="text-align:right">进货金额同比（元）</th>
				<th style="text-align:right">进货金额占比</th>
				<th style="text-align:right">进货金额占比同比</th>
				<th style="text-align:right">进货金额占比排名</th>
				<th style="text-align:right">进货金额占比同比排名</th>
				
				<th style="text-align:right">进货量(公斤)</th>
				<th style="text-align:right">进货量同比(公斤)</th>
				<th style="text-align:right">进货量占比</th>
				<th style="text-align:right">进货量占比同比</th>
				<th style="text-align:right">进货量占比排名</th>
				<th style="text-align:right">进货量占比同比排名</th>
			</tr>
			  <#list contributionList as contribution>
			  	<tr>
		  			<td style="text-align:left"><#if (contribution.supplierCode)!>${contribution.supplierCode}</#if></td>
		  			<td style="text-align:left"><#if (contribution.supplierName)!>${contribution.supplierName}</#if></td>
		  			
		  			<td style="text-align:right"><#if (contribution.sellTotalPrice)!>${contribution.sellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastSellTotalPrice)!>${contribution.lastSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (contribution.sellTotalPriceChange)!>${((contribution.sellTotalPriceChange)?replace("%","")?number)?string("#,##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastSellTotalPriceChange)!>${((contribution.lastSellTotalPriceChange)?replace("%","")?number)?string("#,##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (contribution.sellTotalPriceRank)!>${contribution.sellTotalPriceRank?c}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastSellTotalPriceRank)!>${contribution.lastSellTotalPriceRank?c}</#if></td>
		  			
		  			<td style="text-align:right"><#if (contribution.sellQuantity)!>${contribution.sellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastSellQuantity)!>${contribution.lastSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (contribution.sellQuantityChange)!>${((contribution.sellQuantityChange)?replace("%","")?number)?string("#,##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastSellQuantityChange)!>${((contribution.lastSellQuantityChange)?replace("%","")?number)?string("#,##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (contribution.sellQuantityRank)!>${contribution.sellQuantityRank?c}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastSellQuantityRank)!>${contribution.lastSellQuantityRank?c}</#if></td>
		  			
		  			<td style="text-align:right"><#if (contribution.sellProfit)!>${contribution.sellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastSellProfit)!>${contribution.lastSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (contribution.sellProfitChange)!>${contribution.sellProfitChange}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastSellProfitChange)!>${contribution.lastSellProfitChange}</#if></td>
		  			<td style="text-align:right"><#if (contribution.sellProfitRank)!>${contribution.sellProfitRank?c}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastSellProfitRank)!>${contribution.lastSellProfitRank?c}</#if></td>
		  			
		  			<td style="text-align:right"><#if (contribution.receiptPrice)!>${contribution.receiptPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastReceiptPrice)!>${contribution.lastReceiptPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (contribution.receiptPriceChange)!>${contribution.receiptPriceChange}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastReceiptPriceChange)!>${contribution.lastReceiptPriceChange}</#if></td>
		  			<td style="text-align:right"><#if (contribution.receiptPriceRank)!>${contribution.receiptPriceRank?c}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastReceiptPriceRank)!>${contribution.lastReceiptPriceRank?c}</#if></td>
		  			
		  			<td style="text-align:right"><#if (contribution.receiptRationed)!>${contribution.receiptRationed?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastReceiptRationed)!>${contribution.lastReceiptRationed?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (contribution.receiptRationedChange)!>${((contribution.receiptRationedChange)?replace("%","")?number)?string("#,##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastReceiptRationedChange)!>${((contribution.lastReceiptRationedChange)?replace("%","")?number)?string("#,##0.00")}%</#if></td>
		  			<td style="text-align:right"><#if (contribution.receiptRationedRank)!>${contribution.receiptRationedRank?c}</#if></td>
		  			<td style="text-align:right"><#if (contribution.lastReceiptRationedRank)!>${contribution.lastReceiptRationedRank?c}</#if></td>
		        </tr>
			</#list>
		</table>
	</body>
</html>