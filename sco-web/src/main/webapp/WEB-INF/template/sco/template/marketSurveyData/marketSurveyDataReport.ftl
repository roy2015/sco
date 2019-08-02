<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style>
		table {
			border-collapse: collapse;
			border: none;
			width: 1800px;
			font-family : "微软雅黑";
			font-size : 13px;
			white-space : nowrap;
		}
		td, th {
			border: solid #ddd 1px;
			padding:10px;
			white-space : nowrap;
		}
		th {
			white-space : nowrap;
			background-color: #EEEEEE;
		}
	</style>
</head>
<body>
	<table border="1" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<th colspan="22">
				竞品价格市调数据报表
			</th>
		</tr>
		<tr>					
			<th style="text-align: left;padding-left: 2px;">商品编号</th>
			<th style="text-align: left;padding-left: 2px;">商品名称</th>
			<th style="text-align: left;padding-left: 2px;">供应商编号</th>
			<th style="text-align: left;padding-left: 2px;">供应商名称</th>
			<th style="text-align: left;padding-left: 2px;">商品定性角色</th>
			<th style="text-align: left;padding-left: 2px;">商品定量角色</th>
			<th style="text-align: left;padding-left: 2px;">中分类</th>
			<th style="text-align: left;padding-left: 2px;">小分类</th>
			<th style="text-align: left;padding-left: 2px;">明细类</th>
			<th style="text-align: left;padding-left: 2px;">细分类</th>
			<th style="text-align: right;padding-right: 2px;">商品售价（元）</th>
			<th style="text-align: left;padding-left: 2px;">价格单位</th>
			<th style="text-align: right;padding-right: 2px;">净含量（kg）</th>
			<th style="text-align: right;padding-right: 2px;">折后公斤价（元）</th>
			<th style="text-align: left;padding-left: 2px;">卖场名称</th>
			<th style="text-align: left;padding-left: 2px;">卖场地区</th>
			<th style="text-align: left;padding-left: 2px;">竞品价格单位（元/kg）</th>
			<th style="text-align: right;padding-right: 2px;">竞品净含量（kg）</th>
			<th style="text-align: left;padding-left: 2px;">市调价格日期</th>
			<th style="text-align: right;padding-right: 2px;">市调售价（元）</th>
			<th style="text-align: right;padding-right: 2px;">竞品折后公斤价（元）</th>
		</tr>
		<#list dataList as list>
			<tr>
				<td style="text-align: left;padding-left: 2px;">${list.merchandiseCode}</td>
				<td style="text-align: left;padding-left: 2px;">${list.merchandiseName}</td>
				<td style="text-align: left;padding-left: 2px;">${list.supplierCode}</td>
				<td style="text-align: left;padding-left: 2px;">${list.supplierName}</td>
				<td style="text-align: left;padding-left: 2px;">${list.dxRoleName}</td>
				<td style="text-align: left;padding-left: 2px;">${list.dlRoleName}</td>
				<td style="text-align: left;padding-left: 2px;">${list.centreName}</td>
				<td style="text-align: left;padding-left: 2px;">${list.smallName}</td>
				<td style="text-align: left;padding-left: 2px;">${list.detailName}</td>
				<td style="text-align: left;padding-left: 2px;">${list.fineName}</td>
				<td style="text-align: right;padding-right: 2px;">${(list.sellPrice?string("#,##0.00"))!}</td>
				<td style="text-align: left;padding-left: 2px;">${list.storageForm}</td>
				<td style="text-align: right;padding-right: 2px;">${(list.netWeight?string("#,##0.00"))!}</td>
	<#-- 折后公斤价 --><td style="text-align: right;padding-right: 2px;">${(list.discountPrice?string("#,##0.00"))!}</td>
				<td style="text-align: left;padding-left: 2px;">${list.jpmcName}</td>
				<td style="text-align: left;padding-left: 2px;">${list.mcRegion}</td>
				<td style="text-align: left;padding-left: 2px;">${list.jpPriceUnits}</td>
				<td style="text-align: right;padding-right: 2px;">${(list.jpContent?string("#,##0.00"))!}</td>
				<td style="text-align: left;padding-left: 2px;">${(list.marketSurveyDate?string("yyyy-MM-dd"))!}</td>
				<td style="text-align: right;padding-right: 2px;">${(list.marketSurveyPrice?string("#,##0.00"))!}</td>
	<#-- 竞品折后公斤价 --><td style="text-align: right;padding-right: 2px;">${(list.mdiscountPrice?string("#,##0.00"))!}</td>
			</tr>
		</#list>
	</table>
</body>
</html>