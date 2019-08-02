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
		<label>库存地点：${regionCodeSplit}</label><br/>
	</div>
	<table border="1">
		<tr>
			<td colspan="14">商品长期调价汇总表</td>
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
			<th>调整后价格采购量</th>
			<th>调整后价格采购额</th>
			<th>成本增减情况</th>
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
			<td style="text-align: right">${detail.receiptRationed?string(",##0.00")}</td>
			<td style="text-align: right">${detail.receiptTotalPrice?string(",##0.00")}</td>
			<td style="text-align: right">${detail.increaseDecrease?string(",##0.00")}</td>
		</tr>
		</#list>
	</table>
	<br />
	<br />
	<table border="1">
		<tr>
			<td colspan="20" >商品长期调价明细表</td>
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
			<th>库存地点</th>
			<th>调整后价格维护日期</th>
			<th>调整前价格</th>
			<th>调整后价格</th>
			<th>调整后价差</th>
			<th>调整后价差百分比</th>
			<th>调整后价格采购量</th>
			<th>调整后价格采购额</th>
			<th>成本增减情况</th>
		</tr>
		<#list dataLists as details>
			<tr>
				<td>${details.merchandiseCode}</td>
				<td>${details.merchandiseName}</td>
				<td>${details.dxRoleName}</td>
				<td>${details.dlRoleName}</td>
				<td>${details.centreName}</td>
				<td>${details.smallName}</td>
				<td>${details.detailName}</td>
				<td>${details.fineName}</td>
				<td>${details.supplierCode}</td>
				<td>${details.supplierName}</td>
				<td>${details.warehouseCode}</td>
				<td>${details.regionCode}</td>
				<td>${details.priceDate}</td>
				<td style="text-align:right">${(details.purchasePriceO?string(",##0.00"))!}</td>
				<td style="text-align:right">${(details.purchasePriceN?string(",##0.00"))!}</td>
				<td style="text-align:right">${(details.purchasePriceC?string(",##0.00"))!}</td>
				<td style="text-align:right"><#if details.purchasePriceB!="" >${details.purchasePriceB?string(",##0.00")}%</#if></td>
				<td style="text-align:right">${(details.receiptRationed?string(",##0.00"))!}</td>
				<td style="text-align:right">${(details.receiptTotalPrice?string(",##0.00"))!}</td>
				<td style="text-align:right">${(details.increaseDecrease?string(",##0.00"))!}</td>
			</tr>
		</#list>
	</table>
</body>
</html>
