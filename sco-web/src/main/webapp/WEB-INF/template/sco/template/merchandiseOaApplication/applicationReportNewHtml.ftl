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
	<#list applicationList as applicationReportNew>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品基础信息</b>
		</legend>
		<table>
			<tr>
				<td>意向品名称：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.intentionName}" /></td>
				<td>研发：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.development}" /></td>
				<td>商品定性角色：</td>
				<td colspan="3"><input type="text" style="width: 180px;" value="${applicationReportNew.dxRoleName}" /></td>
			</tr>
			<tr>
				<td>商品定量角色：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.dlRoleName}" /></td>
				<td>中分类：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.centreTypeName}" /></td>
				<td>小分类：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.smallTypeName}" /></td> <#if applicationReportNew.smallTypeName == "其他">
				<td>自定义小分类名称：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.elseTypeName}" /></td> </#if>
			</tr>
			<tr>
				<td>明细类：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.detailTypeName}" /></td>
				<td>细分类：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.fineTypeName}" /></td>
				<td>供应商编号：
				</td>
				<td colspan="3"><input type="text" style="width: 180px;" value="${applicationReportNew.supplierCode}" /></td>
			</tr>
			<tr>
				<td>供应商全称：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.supplierName}" /></td>
				<td>供应商属性：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.supplierAttribute}" /></td>
				<td>供应商所在地：</td>
				<td colspan="3"><input type="text" style="width: 180px;" value="${applicationReportNew.supplierSite}" /></td>
			</tr>
			<tr>
				<td>品控访厂结果：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.visitFactory}" /></td>
				<td>引进后小分类SKU数：</td>
				<td><#if applicationReportNew.smallTypeSku?? ><input type="text" style="width: 180px;" value="${applicationReportNew.smallTypeSku?c}" /></#if>
				</td>
				<td>引进后明细类SKU数：</td>
				<td colspan="3"><#if applicationReportNew.detailTypeSku?? ><input type="text" style="width: 180px;" value="${applicationReportNew.detailTypeSku?c}" /></#if>
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品试吃反馈</b>
		</legend>
		<table>
			<tr>
				<td>试吃日期：</td>
				<td><input type="text" style="width: 180px;" value="${(applicationReportNew.foretasteDate?string(" yyyy-MM-dd"))!}" /></td>
				<td>评分：</td>
				<td><#if applicationReportNew.foretasteGrade?? ><input type="text" style="width: 180px;" value="${applicationReportNew.foretasteGrade?string(" 0.00")}" /></#if>
				</td>
				<td>试吃意见：</td>
				<td><textarea style="width:402px;height:50px"> ${applicationReportNew.foretasteEvaluate} </textarea></td>
			</tr>
		</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品主要检验标准</b>
		</legend>
		<table>
			<tr>
				<td>色泽</td>
				<td><input type="text" style="width: 680px;" value="${applicationReportNew.colour}" /></td>
			</tr>
			<tr>
				<td>滋气味</td>
				<td><input type="text" style="width: 680px;" value="${applicationReportNew.smell}" /></td>
			</tr>
			<tr>
				<td>组织形态</td>
				<td><input type="text" style="width: 680px;" value="${applicationReportNew.form}" /></td>
			</tr>
			<tr>
				<td>水份含量</td>
				<td><input type="text" style="width: 680px;" value="${applicationReportNew.moistureContent}" /></td>
			</tr>
		</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品价格</b>
		</legend>
		<table class="bordertable" cellspacing="0" style="width: 100%">
			<tr>
				<td>进货地区</td>
				<td>采购价格</td>
				<td>采购单位</td>
				<td>销售地区</td>
				<td>销售价格</td>
				<td>销售单位</td>
				<td>毛利率</td>
			</tr>
			<#if applicationReportNew.priceList?size gt 0> <#list applicationReportNew.priceList as priceList>
			<tr>
				<td>${priceList.stockSite}</td>
				<td style="text-align: right"><#if priceList.purchasePrice?exists>${priceList.purchasePrice?string("#,##0.00")}</#if></td>
				<td>${priceList.purchaseUnits}</td>
				<td>${priceList.sellRegion}</td>
				<td style="text-align: right"><#if priceList.sellPrice?exists>${priceList.sellPrice?string("#,##0.00")}</#if></td>
				<td>${priceList.sellUnits}</td>
				<td style="text-align: right"><#if priceList.profitRatePercent?exists>${((priceList.profitRatePercent)?replace("%","")?number)?string("0.00")}%</#if></td>
			</tr>
			</#list> </#if>
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
			<#list applicationReportNew.materialList as materList>
			<tr>
				<td>${materList.materialName}</td>
				<td>${materList.materialSite}</td>
				<td>${materList.materialDate}</td>
				<td>${materList.materialCount}</td>
				<td>${materList.sellDate}</td>
				<td>${materList.remarks}</td>
			</tr>
			</#list>
		</table>
	</fieldset>
	<div >
		<p style="padding: 4px;font-size: 13px;">采购员分析：请对市场原料进行综合分析，包含：主要产地、原料特点、规格差异、原料价格以及我司选用的原料原因等进行具体分析。</p>
		<textarea style="width: 100%; height: 120px">${applicationReportNew.purchaseAnalysis}</textarea>
	</div>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>同类商品市场零售价</b>
		</legend>
		<table class="bordertable" cellspacing="0" style="width: 100%">
			<#if applicationReportNew.sameList?size gt 0>
			<tr>
				<td>品牌</td>
				<td>商品名称</td>
				<td>销售渠道</td>
				<td>销售单位</td>
				<td>零售价(元/销售单位)</td>
				<td>折合公斤价(元/公斤)</td>
				<td>备注</td>
			</tr>
			<#list applicationReportNew.sameList as sameList>
			<tr>
				<td>${sameList.trademark}</td>
				<td>${sameList.merchandiseName}</td>
				<td>${sameList.sellChannel}</td>
				<td>${sameList.sellUnits}</td>
				<td>${sameList.sellPrice}</td>
				<td>${sameList.kgPrice}</td>
				<td>${sameList.remarks}</td>
			</tr>
			</#list> <#else>
			<tr>
				<td>暂无同类商品</td>
			</tr>
			</#if>
		</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>商品销售预计</b>
		</legend>
		<table>
			<tr>
				<td>预计销售总门店数：</td>
				<td><input type="text" style="width: 180px;" value="${applicationReportNew.anticipatedSellStoreQuantit}" /></td>
				<td>预计销售量(最小单位/单店单天)：</td>
				<td><#if applicationReportNew.anticipatedSellQuantity?exists><input type="text" style="width: 180px;" value="${applicationReportNew.anticipatedSellQuantity?string(" #,##0.00")}" /></#if>
				</td>
				<td>预计毛利额(最小单位/单店单天)：</td>
				<td><#if applicationReportNew.anticipatedSellProfit?exists><input type="text" style="width: 180px;" value="${applicationReportNew.anticipatedSellProfit?string(" #,##0.00")}" /></#if>
				</td>
			</tr>
		</table>
		<table class="bordertable" cellspacing="0" style="width: 100%">
			<tr>
				<td>销售区域</td>
				<td>预计销售门店数</td>
			</tr>
			<#if applicationReportNew.sellList?size gt 0> <#list applicationReportNew.sellList as sellList>
			<tr>
				<td>${sellList.sellRegion}</td>
				<td>${sellList.sellStoreCount}</td>
			</tr>
			</#list> </#if>
		</table>
	</fieldset>
	<fieldset style="margin-top: 10px; border: 1px solid #CCC;" data-options="fit:true">
		<legend style="color: #666; padding: 0 3px;">
			<b>采购员意见</b>
		</legend>
		<div >
		<textarea style="width: 100%; height: 150px">${applicationReportNew.purchaseOpinion}</textarea>
		</div>
	</fieldset>
	<#if (applicationList?size) - (applicationReportNew_index) != 1>
	<hr style="width: 1800px; border-top: 8px solid #696566; margin: 36 0;" />
	</#if> </#list>
</body>
</html>