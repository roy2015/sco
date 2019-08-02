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
	<div>
		<label>时间范围：${date}</label><br/>
		<label>工厂：${warehouseCodeSplit}</label><br/>
	</div>
	<table border="1">
		<tr>
			<td colspan="17">商品促销进价调整报表</td>
		</tr>
		<tr>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>商品定量角色</th>
			<th>商品定性角色</th>
			<th>中分类</th>
			<th>小分类</th>
			<th>明细类</th>
			<th>细分类</th>
			<th>供应商编号</th>
			<th>供应商名称</th>
			<th>工厂编号</th>
			<th>促销进价开始结束日期</th>
			<th>促销档期</th>
			<th>促销活动名称</th>
			<th>促销进价（元/kg或元/件）</th>
			<th>促销期间采购量（kg/件）</th>
			<th>促销期间采购额（元）</th>
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
			<td>${detail.fineName}</td>
			<td>${detail.supplierCode}</td>
			<td>${detail.supplierName}</td>
			<td>${detail.warehouseCode}</td>
			<td>${detail.priceDate}</td>
			<td>${detail.promotionSchedule}</td>
			<td>${detail.promotionName}</td>
			<td style="text-align: right">${(detail.purchasePrice?string(",##0.00"))!}</td>
			<td style="text-align: right">${(detail.receiptRationed?string(",##0.00"))!}</td>
			<td style="text-align: right">${(detail.receiptTotalPrice?string(",##0.00"))!}</td>
		</tr>
		</#list>
	</table>
</body>
</html>
