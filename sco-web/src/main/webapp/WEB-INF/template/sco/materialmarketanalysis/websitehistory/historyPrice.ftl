<html>
	<head>
		<#if showChart?exists>
			<script src="js/echarts/echarts.js"></script>
		</#if>
		<style>
	body {
			font-family : "微软雅黑";
			font-size : 12px;
		}
	.easy-table {
		border-collapse: collapse;
		border: none;
		font-family: "微软雅黑";
		font-size: 12px;
	}
	.easy-table th,.easy-table td {
		line-height:25px;
		padding:0 8px;
	}
	.easy-table td {
		border: dotted #BBB 1px;
	}
	.easy-table th {
		font-weight: normal;
		border: solid #ddd 1px;
	}
	.easy-table th {
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
		<table class="easy-table">
			<thead>
				<tr>
					<th style="text-align:left;width:125px;">年月</th>
					<th style="text-align:right;width:120px;">1</th>
					<th style="text-align:right;width:120px;">2</th>
					<th style="text-align:right;width:120px;">3</th>
					<th style="text-align:right;width:120px;">4</th>
					<th style="text-align:right;width:120px;">5</th>
					<th style="text-align:right;width:120px;">6</th>
					<th style="text-align:right;width:120px;">7</th>
					<th style="text-align:right;width:120px;">8</th>
					<th style="text-align:right;width:120px;">9</th>
					<th style="text-align:right;width:120px;">10</th>
					<th style="text-align:right;width:120px;">11</th>
					<th style="text-align:right;width:120px;">12</th>
					<th style="text-align:right;width:120px;">年平均</th>
				</tr>
			</thead>
			<tbody>
			  	<#list dataList as l>
				  	<tr>
				  		<td style="text-align:left;">${l.priceYear}</td>
				  		<#list l.list as y>
				  			<td style="text-align:right;">
				  				<#if y.price?exists>${y.price?string("#,##0.00")}</#if>
				  			</td>
				  		</#list>
				  		<td style="text-align:right;">
				  			<#if l.avgPrice?exists>${l.avgPrice?string("#,##0.00")}</#if>
				  		</td>
			        </tr>	
				</#list>
			</tbody>
		</table>
		<#-- 间隔层 -->
		<div style="height:20px"></div>
		<#-- 统计 -->
		<table class="easy-table">
			<thead>
				<tr>
					<th style="text-align:left;width:125px;">年月</th>
					<th style="text-align:right;width:120px;">1</th>
					<th style="text-align:right;width:120px;">2</th>
					<th style="text-align:right;width:120px;">3</th>
					<th style="text-align:right;width:120px;">4</th>
					<th style="text-align:right;width:120px;">5</th>
					<th style="text-align:right;width:120px;">6</th>
					<th style="text-align:right;width:120px;">7</th>
					<th style="text-align:right;width:120px;">8</th>
					<th style="text-align:right;width:120px;">9</th>
					<th style="text-align:right;width:120px;">10</th>
					<th style="text-align:right;width:120px;">11</th>
					<th style="text-align:right;width:120px;">12</th>
					<th style="text-align:right;width:120px;">年平均</th>
				</tr>
			</thead>
			<tbody>
			  	<#list dataList as l>
				  	<tr>
				  		<td style="text-align:left;">${l.priceYear}同比增长</td>
				  		<#list l.list as y>
				  			<td style="text-align:right;">
				  				<#if y.incParcent?exists>${y.incParcent?string("0.00%")}</#if>
				  			</td>
				  		</#list>
				  		<td style="text-align:right;">
				  			<#if l.incParcent?exists>${l.incParcent?string("0.00%")}</#if>
				  		</td>
			        </tr>	
				</#list>
			</tbody>
		</table>

		<#if showChart?exists>	
			<#-- 间隔层 -->
			<div style="height:20px;"></div>
			<#-- 图表1 -->
			<div style="overflow:auto;width:100%;">
				<div id="hisPrice" style="height:400px;width:50%;float:left;"></div>
				<div id="hisInc" style="height:400px;width:50%;float:left;"></div>
			</div>
			<script type="text/javascript">
				webHisFn.showHisPrice(${dataListJson});
				webHisFn.hisInc(${dataListJson});
			</script>
		</#if>
		
	</body>
</html>