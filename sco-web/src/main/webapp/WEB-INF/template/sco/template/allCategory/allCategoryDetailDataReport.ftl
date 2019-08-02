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
	<#-- 表格1 -->
	<div>
		<label>时间范围1：${pubMap.searchDate1}</label><br/>
		<label>${pubMap.merTitle}</label><br/>
		<label>地区：${pubMap.regionName}</label><br/> 
		<#if pubMap.module != 'CM'>
			<label>${pubMap.netWeightTypeCon}</label><br/> 
		</#if>
		<label>${pubMap.directCon}</label><br/> 
		<label>${pubMap.priceSetCon}</label>
	</div>
	<table>
		<tr>
			<th >地区</th>
			<th >门店数</th>
			<th >商品编号</th>
			<th >商品名称</th>
			<th >供应商编号</th>
			<th >供应商名称</th>
			<th >商品定性角色</th>
			<th >商品定量角色</th>
			<th >中分类</th>
			<th >小分类</th>
			<th >明细类</th>
			<th >所属价格带</th>
			<th >权限数</th>
			<th >权限开通率</th>
			<th >A</th>
			<th >B</th>
			<th >C</th>
			<th >D</th>
			<th >实际销售门店数</th>
			<th >实际占权限门店比</th>
			<th >销量</th>
			<th >销售金额</th>
			<th >毛利额</th>
			<th >销量占比</th>
			<th >销售金额占比</th>
			<th >毛利额占比</th>
			<th >权限店天</th>
			<th >销售店天</th>
			<th >活跃度</th>
			<th >权限店天占比</th>
			<th >销售店天占比</th>
			<th >PSD销量</th>
			<th >PSD销售金额</th>
			<th >PSD毛利额</th>
			<th >
				<#if pubMap.module = 'ALL'>
					高于所有商品平均PSD销量
				<#elseif pubMap.module = 'SM'>
					高于用户所查小分类的所有商品的平均PSD销量
				<#elseif pubMap.module = 'DE'>
					高于用户所查明细类的所有商品的平均PSD销量
				<#else>
					高于用户所选商品的平均PSD销量
				</#if>
			</th>
			<th >
				<#if pubMap.module = 'ALL'>
					高于所有商品平均PSD销售额
				<#elseif pubMap.module = 'SM'>
					高于用户所查小分类的所有商品的平均PSD销售额
				<#elseif pubMap.module = 'DE'>
					高于用户所查明细类的所有商品的平均PSD销售额
				<#else>
					高于用户所选商品的平均PSD销售额
				</#if>
			</th>
			<th >
				<#if pubMap.module = 'ALL'>
					高于所有商品平均PSD毛利额
				<#elseif pubMap.module = 'SM'>
					高于用户所查小分类的所有商品的平均PSD毛利额
				<#elseif pubMap.module = 'DE'>
					高于用户所查明细类的所有商品的平均PSD毛利额
				<#else>
					高于用户所选商品的平均PSD毛利额
				</#if>
			</th>
			<th >格斗占比</th>
			<th >volume&space</th>
			<th >value&space</th>
		</tr>
		
		<#list data1 as l>
			<tr>
				<td >${l.regionName}</td>
				<td style="text-align: right;padding-right: 2px;"><#if l.regionStoreSum?exists>${l.regionStoreSum?string("#,##0")}</#if></td>
				<td >${l.merchandiseCode}</td>
				<td >${l.merchandiseName}</td>
				<td >${l.supplierCode}</td>
				<td >${l.supplierName}</td>
				<td >${l.dxRoleName}</td>
				<td >${l.dlRoleName}</td>
				<td >${l.centreTypeName}</td>
				<td >${l.smallTypeName}</td>
				<td >${l.detailTypeName}</td>
				<td style="text-align: right;padding-right: 2px;">${l.regionPrice}</td>
				<td style="text-align: right;padding-right: 2px;"><#if l.powerCount?exists>${l.powerCount?string("#,##0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.powerPercent?exists>${l.powerPercent?string("0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.aCount?exists>${l.aCount?string("#,##0")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.bCount?exists>${l.bCount?string("#,##0")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.cCount?exists>${l.cCount?string("#,##0")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.dCount?exists>${l.dCount?string("#,##0")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.actSoldStorCount?exists>${l.actSoldStorCount?string("#,##0")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.actSoldStorPercent?exists><#if l.actSoldStorPercent &lt; 1>${l.actSoldStorPercent?string("0.00%")}<#else>100.00%</#if></#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldUnits?exists>${l.soldUnits?string("#,##0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldPrice?exists>${l.soldPrice?string("#,##0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.grossProfit?exists>${l.grossProfit?string("#,##0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldPercent?exists>${l.soldPercent?string("0.00%")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldPricePercent?exists>${l.soldPricePercent?string("0.00%")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.grossProfitPenrcent?exists>${l.grossProfitPenrcent?string("0.00%")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.powerShopDay?exists>${l.powerShopDay?string("#,##0")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldShopDay?exists>${l.soldShopDay?string("#,##0")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.vitality?exists><#if l.vitality &lt; 1>${l.vitality?string("0.00%")}<#else>100.00%</#if></#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.powerShopDayPercent?exists>${l.powerShopDayPercent?string("0.00%")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldShopPercent?exists>${l.soldShopPercent?string("0.00%")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.psdUnits?exists>${l.psdUnits?string("#,##0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.psdSoldPrice?exists>${l.psdSoldPrice?string("#,##0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.psdGrossProfit?exists>${l.psdGrossProfit?string("#,##0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdUnitsSku?exists>${l.notLessPsdUnitsSku?string("#,##0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdSoldPrice?exists>${l.notLessPsdSoldPrice?string("#,##0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdGrossProfit?exists>${l.notLessPsdGrossProfit?string("#,##0.00")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.bucketPercent?exists>${l.bucketPercent?string("0.00%")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.volumePercent?exists>${l.volumePercent?string("0.00%")}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.valPercent?exists>${l.valPercent?string("0.00%")}</#if></td>
			</tr>
		</#list>
	</table>
	
	
	<#-- 表格2 -->
	<#if data2?exists && flag2>
	<div>
		<label>时间范围2：${pubMap.searchDate2}</label><br/>
		<label>${pubMap.merTitle}</label><br/>
		<label>地区：${pubMap.regionName}</label><br/> 
		<#if pubMap.module != 'CM'>
			<label>${pubMap.netWeightTypeCon}</label><br/> 
		</#if>
		<label>${pubMap.directCon}</label><br/> 
		<label>${pubMap.priceSetCon}</label>
	</div>
		<table>
			<tr>
				<th >地区</th>
				<th >门店数</th>
				<th >商品编号</th>
				<th >商品名称</th>
				<th >供应商编号</th>
				<th >供应商名称</th>
				<th >商品定性角色</th>
				<th >商品定量角色</th>
				<th >中分类</th>
				<th >小分类</th>
				<th >明细类</th>
				<th >所属价格带</th>
				<th >权限数</th>
				<th >权限开通率</th>
				<th >A</th>
				<th >B</th>
				<th >C</th>
				<th >D</th>
				<th >实际销售门店数</th>
				<th >实际占权限门店比</th>
				<th >销量</th>
				<th >销售金额</th>
				<th >毛利额</th>
				<th >销量占比</th>
				<th >销售金额占比</th>
				<th >毛利额占比</th>
				<th >权限店天</th>
				<th >销售店天</th>
				<th >活跃度</th>
				<th >权限店天占比</th>
				<th >销售店天占比</th>
				<th >PSD销量</th>
				<th >PSD销售金额</th>
				<th >PSD毛利额</th>
				<th >
				<#if pubMap.module = 'ALL'>
					高于所有商品平均PSD销量
				<#elseif pubMap.module = 'SM'>
					高于用户所查小分类的所有商品的平均PSD销量
				<#elseif pubMap.module = 'DE'>
					高于用户所查明细类的所有商品的平均PSD销量
				<#else>
					高于用户所选商品的平均PSD销量
				</#if>
				</th>
				<th >
					<#if pubMap.module = 'ALL'>
						高于所有商品平均PSD销售额
					<#elseif pubMap.module = 'SM'>
						高于用户所查小分类的所有商品的平均PSD销售额
					<#elseif pubMap.module = 'DE'>
						高于用户所查明细类的所有商品的平均PSD销售额
					<#else>
						高于用户所选商品的平均PSD销售额
					</#if>
				</th>
				<th >
					<#if pubMap.module = 'ALL'>
						高于所有商品平均PSD毛利额
					<#elseif pubMap.module = 'SM'>
						高于用户所查小分类的所有商品的平均PSD毛利额
					<#elseif pubMap.module = 'DE'>
						高于用户所查明细类的所有商品的平均PSD毛利额
					<#else>
						高于用户所选商品的平均PSD毛利额
					</#if>
				</th>
				<th >格斗占比</th>
				<th >volume&space</th>
				<th >value&space</th>
			</tr>
			
			<#list data2 as l>
				<tr>
					<td >${l.regionName}</td>
					<td style="text-align: right;padding-right: 2px;"><#if l.regionStoreSum?exists>${l.regionStoreSum?string("#,##0")}</#if></td>
					<td >${l.merchandiseCode}</td>
					<td >${l.merchandiseName}</td>
					<td >${l.supplierCode}</td>
					<td >${l.supplierName}</td>
					<td >${l.dxRoleName}</td>
					<td >${l.dlRoleName}</td>
					<td >${l.centreTypeName}</td>
					<td >${l.smallTypeName}</td>
					<td >${l.detailTypeName}</td>
					<td style="text-align: right;padding-right: 2px;">${l.regionPrice}</td>
					<td style="text-align: right;padding-right: 2px;"><#if l.powerCount?exists>${l.powerCount?string("#,##0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.powerPercent?exists>${l.powerPercent?string("0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.aCount?exists>${l.aCount?string("#,##0")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.bCount?exists>${l.bCount?string("#,##0")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.cCount?exists>${l.cCount?string("#,##0")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.dCount?exists>${l.dCount?string("#,##0")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.actSoldStorCount?exists>${l.actSoldStorCount?string("#,##0")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.actSoldStorPercent?exists><#if l.actSoldStorPercent &lt; 1>${l.actSoldStorPercent?string("0.00%")}<#else>100.00%</#if></#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.soldUnits?exists>${l.soldUnits?string("#,##0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.soldPrice?exists>${l.soldPrice?string("#,##0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.grossProfit?exists>${l.grossProfit?string("#,##0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.soldPercent?exists>${l.soldPercent?string("0.00%")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.soldPricePercent?exists>${l.soldPricePercent?string("0.00%")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.grossProfitPenrcent?exists>${l.grossProfitPenrcent?string("0.00%")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.powerShopDay?exists>${l.powerShopDay?string("#,##0")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.soldShopDay?exists>${l.soldShopDay?string("#,##0")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.vitality?exists><#if l.vitality &lt; 1>${l.vitality?string("0.00%")}<#else>100.00%</#if></#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.powerShopDayPercent?exists>${l.powerShopDayPercent?string("0.00%")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.soldShopPercent?exists>${l.soldShopPercent?string("0.00%")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.psdUnits?exists>${l.psdUnits?string("#,##0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.psdSoldPrice?exists>${l.psdSoldPrice?string("#,##0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.psdGrossProfit?exists>${l.psdGrossProfit?string("#,##0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdUnitsSku?exists>${l.notLessPsdUnitsSku?string("#,##0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdSoldPrice?exists>${l.notLessPsdSoldPrice?string("#,##0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdGrossProfit?exists>${l.notLessPsdGrossProfit?string("#,##0.00")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.bucketPercent?exists>${l.bucketPercent?string("0.00%")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.volumePercent?exists>${l.volumePercent?string("0.00%")}</#if></td>
					<td style="text-align: right;padding-right: 2px;"><#if l.valPercent?exists>${l.valPercent?string("0.00%")}</#if></td>
				</tr>
			</#list>
		</table>
	</#if>
	
</body>
</html>