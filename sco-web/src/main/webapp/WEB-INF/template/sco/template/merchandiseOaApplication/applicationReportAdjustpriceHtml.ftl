<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style type="text/css">
table {
	white-space: nowrap;
	border-collapse: collapse;
	font-size: 13px;
	width: 100%;
}

table th {
	text-align: right
}

.bordertable table {
	border-collapse: collapse;
	border: none;
	width: 100%;
}

.bordertable td, .bordertable th {
	border: solid #ccc 1px;
}

table input {
	border: 1px solid #ccc;
	padding: 4px;
	border-radius: 5px;
}

table td, table th {
	padding: 5px
}

table th {
	text-align: left;
	font-weight: inherit;
}
</style>
</head>
<body>
<#list applicationList as applicationReportAdjustprice>
<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品基础信息</b>
		</legend>
	<table>
		<tr>
			<td>商品名称：</td>		
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.merchandiseName}" /></td>
			<td>研发：</td>			
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.development}" /></td>
			<td>商品定性角色：</td>	
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.dxRoleName}" /></td>
		</tr>
		<tr>
			<td>商品定量角色：</td>
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.dlRoleName}" /></td>
			<td>中分类：</td>
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.centreTypeName}" /></td>
			<td>小分类：</td>
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.smallTypeName}" /></td>
		</tr>
		<tr>
			<td>明细类：</td>
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.detailTypeName}" /></td>
			<td>细分类：</td>
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.fineTypeName}" /></td>
			<td>供应商编号：</td>
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.supplierCode}" /></td>
		</tr>
		<tr>
			<td>供应商全称：</td>
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.supplierName}" /></td>
			<td>供应商所在地：</td>
			<td><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.supplierSite}" /></td>
			<td>现有小分类SKU数：</td>
			<td><#if applicationReportAdjustprice.smallTypeSku?? ><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.smallTypeSku?c}" /></#if></td>
		</tr>
		<tr>
			<td>现有明细类SKU数</td>
			<td colspan=5><#if applicationReportAdjustprice.detailTypeSku?? ><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.detailTypeSku?c}" /></#if></td>
		</tr>
	</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品主要检验标准</b>
		</legend>
	<table>
		<tr>
			<td></td>
			<td>原标准</td>
			<td>本次调价申请</td>
		</tr>
		<tr>
			<td>色泽</td>
			<td><input type="text" style="width: 480px;" value="${applicationReportAdjustprice.oldStandardColour}" /></td>
			<td><input type="text" style="width: 480px;" value="${applicationReportAdjustprice.newStandardColour}" /></td>
		</tr>
		<tr>
			<td>滋气味</td>
			<td><input type="text" style="width: 480px;" value="${applicationReportAdjustprice.oldStandardSmell}" /></td>
			<td><input type="text" style="width: 480px;" value="${applicationReportAdjustprice.newStandardSmell}" /></td>
		</tr>
		<tr>
			<td>组织形态</td>
			<td><input type="text" style="width: 480px;" value="${applicationReportAdjustprice.oldStandardFrom}" /></td>
			<td><input type="text" style="width: 480px;" value="${applicationReportAdjustprice.newStandardForm}" /></td>
		</tr>
		<tr>
			<td>水份含量</td>
			<td>
				<input type="text" style="width: 480px;" value="${applicationReportAdjustprice.oldMoistureContent}" />
			</td>
			<td>
				<input type="text" style="width: 480px;" value="${applicationReportAdjustprice.newMoistureContent}" />
			</td>
		</tr>
	</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品历史价格</b>
		</legend>
	<table class="bordertable" cellspacing="0" style="width: 100%">
		<tr>
			<td>进货地区</td>
			<td >采购价格</td>
			<td>采购单位</td>
			<td>销售地区</td>
			<td >销售价格</td>
			<td>销售单位</td>
			<td >毛利率</td>
		</tr>
		<#if applicationReportAdjustprice.oldPriceList?size gt 0>
			<#list applicationReportAdjustprice.oldPriceList as oldPriceList>
				<tr>
					<td>${oldPriceList.stockSite}</td>
					<td style="text-align:right"><#if oldPriceList.purchasePrice?exists>${oldPriceList.purchasePrice?string("#,##0.00")}</#if></td>
					<td>${oldPriceList.purchaseUnits}</td>
					<td>${oldPriceList.sellRegion}</td>
					<td style="text-align:right"><#if oldPriceList.sellPrice?exists>${oldPriceList.sellPrice?string("#,##0.00")}</#if></td>
					<td>${oldPriceList.sellUnits}</td>
					<td style="text-align:right"><#if oldPriceList.profitRatePercent?exists>${((oldPriceList.profitRatePercent)?replace("%","")?number)?string("0.00")}%</#if></td>
				</tr>
			</#list>
		</#if>
	</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品本次价格</b>
		</legend>
	<table class="bordertable" cellspacing="0" style="width: 100%">
		<tr>
			<td>进货地区</td>
			<td >采购价格</td>
			<td>采购单位</td>
			<td>销售地区</td>
			<td >销售价格</td>
			<td>销售单位</td>
			<td>毛利率</td>
		</tr>
		<#if applicationReportAdjustprice.nowPriceList?size gt 0>
			<#list applicationReportAdjustprice.nowPriceList as nowPriceList>
				<tr>
					<td>${nowPriceList.stockSite}</td>
					<td style="text-align:right"><#if nowPriceList.purchasePrice?exists>${nowPriceList.purchasePrice?string("#,##0.00")}</#if></td>
					<td>${nowPriceList.purchaseUnits}</td>
					<td>${nowPriceList.sellRegion}</td>
					<td style="text-align:right"><#if nowPriceList.sellPrice?exists>${nowPriceList.sellPrice?string("#,##0.00")}</#if></td>
					<td>${nowPriceList.sellUnits}</td>
					<td style="text-align:right"><#if nowPriceList.profitRatePercent?exists>${((nowPriceList.profitRatePercent)?replace("%","")?number)?string("0.00")}%</#if></td>
				</tr>
			</#list>
		</#if>
	</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>本次价格比历史价格高</b>
		</legend>
	<table class="bordertable" cellspacing="0" style="width: 100%">
		<tr>
			<td>进货地区</td>
			<td >采购价格</td>
			<td>采购单位</td>
			<td >采购价格百分比</td>
			<td>销售地区</td>
			<td >销售价格</td>
			<td>销售单位</td>
			<td >销售价格百分比</td>
			<td >毛利率</td>
		</tr>
		<#if applicationReportAdjustprice.nowThanOldPriceList?size gt 0>
			<#list applicationReportAdjustprice.nowThanOldPriceList as nowThanOldPriceList>
				<tr>
					<td>${nowThanOldPriceList.stockSite}</td>
					<td style="text-align:right"><#if nowThanOldPriceList.purchasePrice?exists>${nowThanOldPriceList.purchasePrice?string("#,##0.00")}</#if></td>
					<td>${nowThanOldPriceList.purchaseUnits}</td>
					<td style="text-align:right"><#if nowThanOldPriceList.purchasePricePercent?exists>${((nowThanOldPriceList.purchasePricePercent)?replace("%","")?number)?string("0.00")}%</#if></td>
					<td>${nowThanOldPriceList.sellRegion}</td>
					<td style="text-align:right"><#if nowThanOldPriceList.sellPrice?exists>${nowThanOldPriceList.sellPrice?string("#,##0.00")}</#if></td>
					<td>${nowThanOldPriceList.sellUnits}</td>
					<td style="text-align:right"><#if nowThanOldPriceList.sellPricePercent?exists>${((nowThanOldPriceList.sellPricePercent)?replace("%","")?number)?string("0.00")}%</#if></td>
					<td style="text-align:right"><#if nowThanOldPriceList.profitRatePercent?exists>${((nowThanOldPriceList.profitRatePercent)?replace("%","")?number)?string("0.00")}%</#if></td>
				</tr>
			</#list>
		</#if>
	</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品原料</b>
		</legend>
	<table class="bordertable" cellspacing="0" style="width: 100%">
		<tr>
			<td>主原料名称</td>
			<td>主原料产地</td>
			<td>主原料收购时间</td>
			<td>现有主原料库存</td>
			<td>预估成品可销售时长</td>
			<td>备注</td>
		</tr>
		<#if applicationReportAdjustprice.materialList?size gt 0>
			<#list applicationReportAdjustprice.materialList as materialList>
				<tr>
					<td>${materialList.materialName}</td>
					<td>${materialList.materialSite}</td>
					<td>${materialList.materialDate}</td>
					<td>${materialList.materialCount}</td>
					<td>${materialList.sellDate}</td>
					<td>${materialList.remarks}</td>
				</tr>
			</#list>
		</#if>
	</table>
</fieldset>
		
	<div >
		<p style="padding: 4px;font-size: 13px;">采购员分析：请对市场原料进行综合分析，包含：主要产地、原料特点、规格差异、原料价格以及我司选用的原料原因等进行具体分析。</p>
		<textarea style="width: 100%; height: 120px">${applicationReportAdjustprice.purchaseAnalysis}</textarea>
	</div>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品签量</b>
		</legend>
	<table>
		<tr>
			<td>签量日期：</td>
			<td><#if applicationReportAdjustprice.qlDate?? ><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.qlDate?string('yyyy-MM-dd')}" /><#else><input type="text" style="width: 180px;" value="&nbsp;" /></#if></td>
			<td>签量数量（kg）：</td>
			<td><#if applicationReportAdjustprice.atpQuantitySum?? ><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.atpQuantitySum?string("0.00")}" /><#else><input type="text" style="width: 180px;" value="&nbsp;" /></#if></td>
		</tr>
		<tr>
			<td style="width: 180px;">总签量已完成量（kg）：</td>
			<td><#if applicationReportAdjustprice.atpQuantitySumAccomplish?? ><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.atpQuantitySumAccomplish?string("0.00")}" /><#else><input type="text" style="width: 180px;" value="&nbsp;${applicationReportAdjustprice.atpQuantitySumAccomplish}" /></#if></td>
			<td style="width: 180px;">总签量未完成量（kg）：</td>
			<td><#if applicationReportAdjustprice.atpQuantitySumUnfinished?? ><input type="text" style="width: 180px;" value="${applicationReportAdjustprice.atpQuantitySumUnfinished?string("0.00")}" /><#else><input type="text" style="width: 180px;" value="&nbsp;${applicationReportAdjustprice.atpQuantitySumUnfinished}" /></#if></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td colspan="3"><textarea style="width:100%;height:60px">${applicationReportAdjustprice.remarks}&nbsp;</textarea></td>
		</tr>
	</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>同类商品市场零售价</b>
		</legend>
	<table class="bordertable" cellspacing="0" style="width: 100%">
		<#if applicationReportAdjustprice.sameList?size gt 0>
			<tr>
				<td>品牌</td>
				<td>商品名称</td>
				<td>销售渠道</td>
				<td>销售单位</td>
				<td>零售价(元/销售单位)</td>
				<td>折合公斤价(元/公斤)</td>
				<td>备注</td>
			</tr>
			<#list applicationReportAdjustprice.sameList as sameList>
				<tr>
					<td>${sameList.trademark}</td>
					<td>${sameList.merchandiseName}</td>
					<td>${sameList.sellChannel}</td>
					<td>${sameList.sellUnits}</td>
					<td>${sameList.sellPrice}</td>
					<td>${sameList.kgPrice}</td>
					<td>${sameList.remarks}</td>
				</tr>
			</#list>
		<#else>
			<tr>
				<td>暂无同类商品</td>
			</tr> 	
		</#if>
	</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>采购员意见</b>
		</legend>
		<div >
		<textarea style="width: 100%; height: 150px">${applicationReportAdjustprice.purchaseOpinion}</textarea>
		</div>
	</fieldset>
	<#if (applicationList?size) - (applicationReportAdjustprice_index) != 1>
		<hr style="width:1800px;border-top: 8px solid #696566;margin:36 0;"/>
	</#if>
</#list>	
</body>
</html>