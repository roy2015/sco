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
				white-space : nowrap
			}
			td, th {
				border: solid #ddd 1px;
				padding:10px;
			}
			th {
				background-color: #EEEEEE;
			}
			.word{
				border: solid #ddd 0px;
				padding:10px;
				line-height: 1.6;
				font-weight: normal;
				font-family : "微软雅黑";
				font-size : 14px;
				white-space : nowrap
			}
		</style>
	</head>
	<body>
		<div class="word">
			原料大类：${materialBigTypeName}
			<br/>
			原料小类：${materialSmallTypeName}
			<br/>
			原料名称：${materialName}
			<br/>
			公示网站名称：${websiteName}
			<br/>
			地区：${priceRegionName}
		</div>	
		<table>
			<thead>
				<tr>
					<th style="text-align:left;width:120px;">序号</th>
					<th style="text-align:left;width:120px;">商品编号</th>
					<th style="text-align:left;width:120px;">商品名称</th>
					<th style="text-align:left;width:120px;">供应商编号</th>
					<th style="text-align:left;width:120px;">供应商名称</th>
					<th style="text-align:left;width:120px;">商品定性角色</th>
					<th style="text-align:left;width:120px;">商品定量角色</th>
					<th style="text-align:left;width:120px;">中分类</th>
					<th style="text-align:left;width:120px;">小分类</th>
					<th style="text-align:left;width:120px;">明细类</th>
					<th style="text-align:left;width:120px;">细分类</th>
					<th style="text-align:left;width:120px;">关联人</th>
					<th style="text-align:left;width:120px;">关联时间</th>
				</tr>
			</thead>
			<tbody>
			  	<#list dataList as l>
				  	<tr>
				  		<td style="text-align:left;width:120px;">${l_index+1}</td>
				  		<td style="text-align:left;width:120px;">${l.merchandiseCode}</td>
				  		<td style="text-align:left;width:120px;">${l.merchandiseName}</td>
				  		<td style="text-align:left;width:120px;">${l.supplierCode}</td>
				  		<td style="text-align:left;width:120px;">${l.supplierName}</td>
				  		<td style="text-align:left;width:120px;">${l.dxRoleName}</td>
				  		<td style="text-align:left;width:120px;">${l.dlRoleName}</td>
				  		<td style="text-align:left;width:120px;">${l.centreName}</td>
				  		<td style="text-align:left;width:120px;">${l.smallName}</td>
				  		<td style="text-align:left;width:120px;">${l.detailName}</td>
				  		<td style="text-align:left;width:120px;">${l.fineName}</td>
				  		<td style="text-align:left;width:120px;">${l.createby}</td>
				  		<td style="text-align:left;width:120px;">${l.created?string("yyyy-MM-dd")}</td>
			        </tr>	
				</#list>
			</tbody>
		</table>
	</body>
</html>