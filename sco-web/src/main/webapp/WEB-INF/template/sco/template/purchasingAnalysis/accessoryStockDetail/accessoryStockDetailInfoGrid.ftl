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
		<label>${typeName}</label><br/>
		<label>工厂：${warehouseCodeSplit}</label><br/>
		<label>库存地点：${regionCodeSplit}</label><br/>
	</div>
	<table  border="1">
		<tr>
			<th>到货日期</th>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>细分类</th>
			<th>供应商编号</th>
			<th>供应商名称</th>
			<th>工厂编号</th>
			<th>库存地点</th>
			<th >商品到货量（基本价格单位）</th>
			<th >商品到货额（元）</th>
			<th >公司总到货量（基本价格单位）</th>
			<th >公司总到货额（元）</th>
			<th>到货量占公司整体比重</th>
			<th>到货额占公司整体比重</th>
		</tr>
			
		<#list dataList as detail>
			<tr>
				<td>${detail.realityReceiptDate}</td>
				<td>${detail.merchandiseCode}</td>
				<td>${detail.merchandiseName}</td>
				<td>${detail.fineName}</td>
				<td>${detail.supplierCode}</td>
				<td>${detail.supplierName}</td>
				<td>${detail.warehouseCode}</td>
				<td>${detail.regionCode}</td>
				<td style="text-align:right">${(detail.receiptRationedM?string(",##0.00"))!}</td>
				<td style="text-align:right">${(detail.receiptTotalPriceM?string(",##0.00"))!}</td>
				<td style="text-align:right">${(detail.receiptRationed?string(",##0.00"))!}</td>
				<td style="text-align:right">${(detail.receiptTotalPrice?string(",##0.00"))!}</td>
				<td style="text-align:right">${(detail.receiptRationedMA?string(",##0.00"))!}<#if detail.receiptRationedMA!="" >%</#if></td>
				<td style="text-align:right">${(detail.receiptTotalPriceMA?string(",##0.00"))!}<#if detail.receiptTotalPriceMA!="" >%</#if></td>
			</tr>
		</#list>
	</table>
</body>
</html>