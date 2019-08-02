<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<head>
	<style>
	body {
	font-family: "微软雅黑";
	font-size: 12px;
	white-space: nowrap;
	}
	div{
		margin:15px 0px;
	}
	span{
		line-height:17px;
	}

	table {
		border-collapse: collapse;
		border: none;
		font-family: "微软雅黑";
		font-size: 12px;
		white-space: nowrap;
	}
	th,td {
		line-height:25px;
		padding:0 8px;
	}
	td {
		border: dotted #BBB 1px;
	}
	th {
		font-weight: normal;
		border: solid #ddd 1px;
	}
	th {
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
	<script language="javascript" type="text/javascript">
		window.onload = function (){
			var first_row = document.getElementById("first_row");
			document.getElementById("title").setAttribute("colspan",first_row.cells.length); 
		}
	</script>
</head>
<body>
	<table border="1" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<th id="title">
				商品分项类成本类比数据报表
			</th>
		</tr>
		<tr id="first_row">					
			<th style="text-align:left">商品/意向品编号</th>
			<th style="text-align:left">商品/意向品名称</th>
			<th style="text-align:left">供应商/意向供应商编号</th>
			<th style="text-align:left">供应商/意向供应商名称</th>
			<th style="text-align:left">商品定性角色</th>
			<th style="text-align:left">商品定量角色</th>
			<th style="text-align:left">采购部门</th>
			<th style="text-align:left">是否定量装</th>
			<th style="text-align:left">采购类型</th>
			<th style="text-align:left">销售方式</th>
			<th style="text-align:left">中分类</th>
			<th style="text-align:left">小分类</th>
			<th style="text-align:left">明细类</th>
			<th style="text-align:left">细分类</th>
			
			<#if (itemValue == "material")>
			<#--原料-->
			<th style="text-align:left">原料类型</th>
			<th style="text-align:left">原料名称</th>
			<th style="text-align:left">原料产地</th>
			<th style="text-align:left">原料品牌</th>
			<th style="text-align:left">原料等级与规格</th>
			<th style="text-align:right">原料采购价格(元/kg)</th>
			<th style="text-align:right">原料投入量(kg)</th>
			<th style="text-align:right">原料投入成本(元)</th>
			<th style="text-align:right">平均成品原料成本(元/kg)</th>
			<th style="text-align:right">投入量占比(%)</th>
			<th style="text-align:right">成品含水率(%)</th>
			<th style="text-align:right">得率(%)</th>
			</#if>
			
			<#if (itemValue == "npackag")>
			<#--内包装-->
			<#if (selectComboxValue == "FHD_JM"||selectComboxValue == "FHD_LSM")>
			<#--复合袋-卷膜和拉伸膜-->
			<th style="text-align:left">包装类型</th>
			<th style="text-align:left">包装名称</th>
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">具体材质&厚度</th>
			<th style="text-align:right">公斤价格</th>
			<th style="text-align:right">重量占比(%)</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "FHD_ZD")>
			<#--复合袋-制袋-->
			<th style="text-align:left">包装类型</th>
			<th style="text-align:left">包装名称</th>
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">具体材质&厚度</th>
			<th style="text-align:left">尺寸(cm)</th>
			<th style="text-align:right">单个克重(g)</th>
			<th style="text-align:right">单价(元)</th>
			<th style="text-align:right">公斤价格(元)</th>
			<th style="text-align:right">数量</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "ST")>
			<#--塑拖-->
			<th style="text-align:left">包装类型</th>
			<th style="text-align:left">包装名称</th>
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">具体材质&厚度</th>
			<th style="text-align:right">单个克重(g)</th>
			<th style="text-align:right">公斤价格(元)</th>
			<th style="text-align:right">重量占比(%)</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "TYJ")>
			<#--脱氧剂-->
			<th style="text-align:left">包装类型</th>
			<th style="text-align:left">包装名称</th>
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:right">单个克重(g)</th>
			<th style="text-align:left">尺寸(cm)</th>
			<th style="text-align:right">单价(元)</th>
			<th style="text-align:right">数量</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "NDD")>
			<#--内胆袋-->
			<th style="text-align:left">包装类型</th>
			<th style="text-align:left">包装名称</th>
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">具体材质&厚度</th>
			<th style="text-align:left">尺寸(cm)</th>
			<th style="text-align:right">单价(元)</th>
			<th style="text-align:right">数量</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "BQ"||selectComboxValue == "ZZL")>
			<#--标签和纸张类-->
			<th style="text-align:left">包装类型</th>
			<th style="text-align:left">包装名称</th>
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">具体材质&克重</th>
			<th style="text-align:left">尺寸(cm)</th>
			<th style="text-align:right">数量</th>
			<th style="text-align:left">工艺要求</th>
			<th style="text-align:left">备注</th>
			<#else>
			</#if>
			</#if>
			<#if ((itemValue == "npackag"&&selectComboxValue == "ELSE")||(itemValue == "wpackag"&&selectComboxValue == "ELSE"))>
			<#--其他-->
			<th style="text-align:left">包装类型</th>
			<th style="text-align:left">包装名称</th>
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">备注</th>
			</#if>
			
			<#if (itemValue == "wpackag")>
			<#--外包装-->
			<#if (selectComboxValue == "FXD")>
			<#--封箱带-->
			<th style="text-align:left">包装类型</th>
			<th style="text-align:left">包装名称</th>
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:right">单位(元/米)</th>
			<th style="text-align:right">使用量(元/米)</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "ZX")>
			<#--纸箱-->
			<th style="text-align:left">包装类型</th>
			<th style="text-align:left">包装名称</th>
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">具体材质说明</th>
			<th style="text-align:left">长*宽*高(cm)</th>
			<th style="text-align:right">纸箱用料面积(㎡)</th>
			<th style="text-align:right">单价(元/只)</th>
			<th style="text-align:right">纸箱用料单价(元/㎡)</th>
			<th style="text-align:left">箱规</th>
			<th style="text-align:left">备注</th>
			<#else>
			</#if>
			</#if>
			
			<#if (itemValue == "elsecost")>
			<#--其他成本-->
			<#if (selectComboxValue == "SH")>
			<#--损耗-->
			<th style="text-align:left">损耗类型</th>
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "SDM")>
			<#--水电煤-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:right">耗水(元/t成品)</th>
			<th style="text-align:right">耗电(元/t成品)</th>
			<th style="text-align:right">耗气(元/t成品)</th>
			<th style="text-align:right">耗煤(元/t成品)</th>
			<th style="text-align:right">耗油(元/t成品)</th>
			<th style="text-align:right">合计(kg成品)</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "SBZJWH")>
			<#--设备折旧及维护-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:right">设备总价(万元)</th>
			<th style="text-align:right">折旧年限(年)</th>
			<th style="text-align:right">报价值(万元/年)</th>
			<th style="text-align:right">产能值(t成品/年)</th>
			<th style="text-align:right">合计折旧值(元/kg成品)</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "RG")>
			<#--人工-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:right">车间工人数(人次)</th>
			<th style="text-align:right">平均工资(元/人/月)</th>
			<th style="text-align:right">月产量(t)</th>
			<th style="text-align:right">每kg成品(元/kg)</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "GL")>
			<#--管理-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">进货地区</th>
			<th style="text-align:right">占比(%)</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "YS")>
			<#--运输-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">工厂所在地</th>
			<th style="text-align:left">进货地区</th>
			<th style="text-align:right">总公里数</th>
			<th style="text-align:right">单位成本</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "SS")>
			<#--税收-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">进货地区</th>
			<th style="text-align:right">占比(%)</th>
			<th style="text-align:right">税率(%)</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "LR")>
			<#--利润-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">进货地区</th>
			<th style="text-align:right">占比(%)</th>
			<th style="text-align:left">备注</th>
			<#else>
			</#if>
			</#if>
			
			<#if (itemValue == "importcost")>
			<#--进口相关成本-->
			<#if (selectComboxValue == "CC")>
			<#--商品报价-->
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">商品报价</th>
			<th style="text-align:left">币种</th>
			<th style="text-align:left">付款方式</th>
			<th style="text-align:left">商品报价备注</th>
			<th style="text-align:right">汇率(%)</th>
			<th style="text-align:left">汇率参考日期</th>
			<th style="text-align:left">汇率参考银行</th>
			<th style="text-align:left">汇率备注</th>
			<th style="text-align:right">商品人民币结算价格</th>
			<th style="text-align:left">商品人民币结算价格备注</th>
			<#elseif (selectComboxValue == "HY"||selectComboxValue == "KY")>
			<#--海运费 ,空运费-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">出发港</th>
			<th style="text-align:left">到达港</th>
			<th style="text-align:left">货柜类型</th>
			<th style="text-align:left">货柜尺寸</th>
			<th style="text-align:right">单价(元/货柜)</th>
			<th style="text-align:left">货柜内容物数量&重量</th>
			<th style="text-align:left">计算方式</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "HD")>
			<#--换单费-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "BX")>
			<#--保险费-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "BG")>
			<#--报关服务费-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:right">报关费</th>
			<th style="text-align:right">港杂费</th>
			<th style="text-align:right">滞港费</th>
			<th style="text-align:right">污箱费</th>
			<th style="text-align:right">其他费用</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "GS")>
			<#--关税-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:left">HS编码</th>
			<th style="text-align:right">税率(%)</th>
			<th style="text-align:left">关税计算方式</th>
			<th style="text-align:left">备注</th>
			<#elseif (selectComboxValue == "ZZ")>
			<#--增值税-->
			<th style="text-align:right">报价</th>
			<th style="text-align:left">报价计量单位</th>
			<th style="text-align:right">税率(%)</th>
			<th style="text-align:left">增值税计算方式</th>
			<th style="text-align:left">备注</th>
			<#else>
			</#if>
			</#if>
			
			<th style="text-align:left">核算/投料表编号</th>
			<th style="text-align:left">报价日期</th>
			<th style="text-align:left">SCO申请单号</th>
			<th style="text-align:left">OA审批状态</th>
			<th style="text-align:right">X001地区合作价格</th>
			<th style="text-align:left">审批通过日期</th>
			<th style="text-align:left">SCO申请单创建人</th>
		</tr>
		<#list dataList as list>
			<tr>
			    <#if (list.merchandiseCode??)>
			    <td style="text-align:left"><#if (list.merchandiseCode)!>${list.merchandiseCode}</#if></td>
			    <#elseif (list.intentionCode??)>
			    <td style="text-align:left"><#if (list.intentionCode)!>${list.intentionCode}</#if></td>
			    <#else>
			    <td style="text-align:left"></td>
			    </#if>
			    
			    <#if (list.merchandiseName??)>
			    <td style="text-align:left"><#if (list.merchandiseName)!>${list.merchandiseName}</#if></td>
			    <#elseif (list.intentionName??)>
			    <td style="text-align:left"><#if (list.intentionName)!>${list.intentionName}</#if></td>
			    <#else>
			    <td style="text-align:left"></td>
			    </#if>
			    
			    <#if (list.supplierCode??)>
			    <td style="text-align:left"><#if (list.supplierCode)!>${list.supplierCode}</#if></td>
			    <#elseif (list.intentionSupplierCode??)>
			    <td style="text-align:left"><#if (list.intentionSupplierCode)!>${list.intentionSupplierCode}</#if></td>
			    <#else>
			    <td style="text-align:center"></td>
			    </#if>
			    
			    <#if (list.supplierName??)>
			    <td style="text-align:left"><#if (list.supplierName)!>${list.supplierName}</#if></td>
			    <#elseif (list.intentionSupplierName??)>
			    <td style="text-align:left"><#if (list.intentionSupplierName)!>${list.intentionSupplierName}</#if></td>
			    <#else>
			    <td style="text-align:left"></td>
			    </#if>
			    
			    <td style="text-align:left"><#if (list.merchandiseDxRoleName)!>${list.merchandiseDxRoleName!}</#if></td>
				<td style="text-align:left"><#if (list.merchandiseDlRoleName)!>${list.merchandiseDlRoleName!}</#if></td>
				
				<#if (list.intentionPurchaseDepartments??)>
			    <td style="text-align:left"><#if (list.intentionPurchaseDepartments)!>${list.intentionPurchaseDepartments}</#if></td>
			    <#elseif (list.purchaseDepartments??)>
			    <td style="text-align:left"><#if (list.purchaseDepartments)!>${list.purchaseDepartments}</#if></td>
			    <#else>
			    <td style="text-align:left"></td>
			    </#if>
			    
			    <#if (list.rationed??)>
			    <td style="text-align:left"><#if (list.rationed)!>${list.rationed}</#if></td>
			    <#elseif (list.netWeight??)>
			    <td style="text-align:left"><#if (list.netWeight)!>${list.netWeight}</#if></td>
			    <#else>
			    <td style="text-align:left"></td>
			    </#if>
			    
				<td style="text-align:left"><#if (list.purchaseType)!>${list.purchaseType!}</#if></td>
				
				<#if (list.saleType??)>
			    <td style="text-align:left"><#if (list.saleType)!>${list.saleType}</#if></td>
			    <#elseif (list.storageForm??)>
			    <td style="text-align:left"><#if (list.storageForm)!>${list.storageForm}</#if></td>
			    <#else>
			    <td style="text-align:left"></td>
			    </#if>
			    
				<td style="text-align:left"><#if (list.centreTypeName)!>${list.centreTypeName!}</#if></td>
				
				<#if (list.smallTypeName??)>
			    <td style="text-align:left"><#if (list.smallTypeName)!>${list.smallTypeName!}</#if></td>
			    <#elseif (list.intentionSmallTypeCode??&&intentionSmallTypeCode=='ELSE')>
			    <td style="text-align:left"><#if (list.elseTypeName)!>${list.elseTypeName}</#if></td>
			    <#else>
			    <td style="text-align:left"></td>
			    </#if>
			    
				<td style="text-align:left"><#if (list.detailTypeName)!>${list.detailTypeName!}</#if></td>
				<td style="text-align:left"><#if (list.fineTypeName)!>${list.fineTypeName!}</#if></td>
				
				<#if (itemValue == "material")>
				<#--原料-->
				<td style="text-align:left"><#if (list.materialType)!>${list.materialType!}</#if></td>
				<td style="text-align:left"><#if (list.materialName)!>${list.materialName!}</#if></td>
				<td style="text-align:left"><#if (list.materialOrigin)!>${list.materialOrigin!}</#if></td>
				<td style="text-align:left"><#if (list.materialBrand)!>${list.materialBrand!}</#if></td>
				<td style="text-align:left"><#if (list.materialLevelSpecification)!>${list.materialLevelSpecification!}</#if></td>
				<td style="text-align:right"><#if (list.purchasePrice)!>${list.purchasePrice?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.inputCount)!>${list.inputCount!}</#if></td>
				<td style="text-align:right"><#if (list.inputCost)!>${list.inputCost?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.avgCost)!>${list.avgCost?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.inputCountProportion)!>${list.inputCountProportion?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.moisture)!>${list.moisture!}</#if></td>
				<td style="text-align:right"><#if (list.yield)!>${list.yield?string(",##0.00")}</#if></td>
				</#if>
				<#if (itemValue == "npackag")>
				<#--内包装-->
				<#if (selectComboxValue == "FHD_JM"||selectComboxValue == "FHD_LSM")>
				<#--复合袋-卷膜和拉伸膜-->
				<td style="text-align:left"><#if (list.packagType)!>${list.packagType!}</#if></td>
				<td style="text-align:left"><#if (list.packagName)!>${list.packagName!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.texture)!>${list.texture!}</#if></td>
				<td style="text-align:right"><#if (list.kgPrice)!>${list.kgPrice?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.weightProportion)!>${list.weightProportion?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "FHD_ZD")>
				<#--复合袋-制袋-->
				<td style="text-align:left"><#if (list.packagType)!>${list.packagType!}</#if></td>
				<td style="text-align:left"><#if (list.packagName)!>${list.packagName!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.texture)!>${list.texture!}</#if></td>
				<td style="text-align:left"><#if (list.materialSize)!>${list.materialSize!}</#if></td>
				<td style="text-align:right"><#if (list.weight)!>${list.weight?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.unitsPrice)!>${list.unitsPrice?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.kgPrice)!>${list.kgPrice?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.quantity)!>${list.quantity!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "ST")>
				<#--塑拖-->
				<td style="text-align:left"><#if (list.packagType)!>${list.packagType!}</#if></td>
				<td style="text-align:left"><#if (list.packagName)!>${list.packagName!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.texture)!>${list.texture!}</#if></td>
				<td style="text-align:right"><#if (list.weight)!>${list.weight?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.kgPrice)!>${list.kgPrice?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.weightProportion)!>${list.weightProportion?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "TYJ")>
				<#--脱氧剂-->
				<td style="text-align:left"><#if (list.packagType)!>${list.packagType!}</#if></td>
				<td style="text-align:left"><#if (list.packagName)!>${list.packagName!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:right"><#if (list.weight)!>${list.weight?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.materialSize)!>${list.materialSize!}</#if></td>
				<td style="text-align:right"><#if (list.unitsPrice)!>${list.unitsPrice?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.quantity)!>${list.quantity!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "NDD")>
				<#--内胆袋-->
				<td style="text-align:left"><#if (list.packagType)!>${list.packagType!}</#if></td>
				<td style="text-align:left"><#if (list.packagName)!>${list.packagName!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.texture)!>${list.texture!}</#if></td>
				<td style="text-align:left"><#if (list.materialSize)!>${list.materialSize!}</#if></td>
				<td style="text-align:right"><#if (list.unitsPrice)!>${list.unitsPrice?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.quantity)!>${list.quantity!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "BQ"||selectComboxValue == "ZZL")>
				<#--标签和纸张类-->
				<td style="text-align:left"><#if (list.packagType)!>${list.packagType!}</#if></td>
				<td style="text-align:left"><#if (list.packagName)!>${list.packagName!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.texture)!>${list.texture!}</#if></td>
				<td style="text-align:left"><#if (list.materialSize)!>${list.materialSize!}</#if></td>
				<td style="text-align:right"><#if (list.quantity)!>${list.quantity!}</#if></td>
				<td style="text-align:left"><#if (list.technologyRequirements)!>${list.technologyRequirements!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#else>
				</#if>
				</#if>
				<#if ((itemValue == "npackag"&&selectComboxValue == "ELSE")||(itemValue == "wpackag"&&selectComboxValue == "ELSE"))>
				<#--其他-->
				<td style="text-align:left"><#if (list.packagType)!>${list.packagType!}</#if></td>
				<td style="text-align:left"><#if (list.packagName)!>${list.packagName!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				</#if>
				
				<#if (itemValue == "wpackag")>
				<#--外包装-->
				<#if (selectComboxValue == "FXD")>
				<#--封箱带-->
				<td style="text-align:left"><#if (list.packagType)!>${list.packagType!}</#if></td>
				<td style="text-align:left"><#if (list.packagName)!>${list.packagName!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:right"><#if (list.unitsPrice)!>${list.unitsPrice?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.useQuantity)!>${list.useQuantity?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "ZX")>
				<#--纸箱-->
				<td style="text-align:left"><#if (list.packagType)!>${list.packagType!}</#if></td>
				<td style="text-align:left"><#if (list.packagName)!>${list.packagName!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.texture)!>${list.texture!}</#if></td>
				<td style="text-align:left"><#if (list.lwh)!>${list.lwh!}</#if></td>
				<td style="text-align:right"><#if (list.area)!>${list.area?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.unitsPrice)!>${list.unitsPrice?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.ylUnitsPrice)!>${list.ylUnitsPrice?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.specification)!>${list.specification!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#else>
				</#if>
				</#if>
				
				<#if (itemValue == "elsecost")>
				<#--其他成本项-->
				<#if (selectComboxValue == "SH")>
				<#--损耗-->
				<td style="text-align:right"><#if (list.wastageType)!>${list.wastageType!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "SDM")>
				<#--水电煤-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:right"><#if (list.water)!>${list.water?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.electricity)!>${list.electricity?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.gas)!>${list.gas?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.coal)!>${list.coal?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.oil)!>${list.oil?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.total)!>${list.total?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "SBZJWH")>
				<#--设备折旧及维护-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:right"><#if (list.totalPrice)!>${list.totalPrice?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.ageLimit)!>${list.ageLimit?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.depreciation)!>${list.depreciation?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.capacity)!>${list.capacity?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.total)!>${list.total?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "RG")>
				<#--人工-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:right"><#if (list.manpowerCount)!>${list.manpowerCount!}</#if></td>
				<td style="text-align:right"><#if (list.avgWage)!>${list.avgWage?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.monthCapacity)!>${list.monthCapacity?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.unitsWage)!>${list.unitsWage?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>				
				<#elseif (selectComboxValue == "GL")>
				<#--管理-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.region)!>${list.region!}</#if></td>
				<td style="text-align:right"><#if (list.proportion)!>${list.proportion?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "YS")>
				<#--运输-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.companySite)!>${list.companySite!}</#if></td>
				<td style="text-align:left"><#if (list.region)!>${list.region!}</#if></td>
				<td style="text-align:right"><#if (list.sumKm)!>${list.sumKm?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.unitsCost)!>${list.unitsCost?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "SS")>
				<#--税收-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.region)!>${list.region!}</#if></td>
				<td style="text-align:right"><#if (list.proportion)!>${list.proportion?string(",##0.00")}</#if></td>
				<td style="text-align:right"><#if (list.taxRate)!>${list.taxRate?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "LR")>
				<#--利润-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.region)!>${list.region!}</#if></td>
				<td style="text-align:right"><#if (list.proportion)!>${list.proportion?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#else>
				</#if>
				</#if>
				
				<#if (itemValue == "importcost")>
				<#--其他成本项-->
				<#if (selectComboxValue == "CC")>
				<#--商品报价-->
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.currency)!>${list.currency!}</#if></td>
				<td style="text-align:left"><#if (list.paymentType)!>${list.paymentType!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<td style="text-align:right"><#if (list.exchangerate)!>${list.exchangerate!}</#if></td>
				<td style="text-align:left"><#if (list.referenceDate)!>${list.referenceDate!}</#if></td>
				<td style="text-align:left"><#if (list.referenceBank)!>${list.referenceBank!}</#if></td>
				<td style="text-align:left"><#if (list.aeRemarks)!>${list.aeRemarks!}</#if></td>
				<td style="text-align:right"><#if (list.rmbSettlementPrice)!>${list.rmbSettlementPrice?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.rmbSettlementPriceRemarks)!>${list.rmbSettlementPriceRemarks!}</#if></td>
				<#elseif (selectComboxValue == "HY"||selectComboxValue == "KY")>
				<#--海运费 ,空运费-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.starting)!>${list.starting!}</#if></td>
				<td style="text-align:left"><#if (list.destination)!>${list.destination!}</#if></td>
				<td style="text-align:left"><#if (list.containerType)!>${list.containerType!}</#if></td>
				<td style="text-align:left"><#if (list.containerSize)!>${list.containerSize!}</#if></td>
				<td style="text-align:right"><#if (list.unitPrice)!>${list.unitPrice?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.containerCapacity)!>${list.containerCapacity!}</#if></td>
				<td style="text-align:left"><#if (list.computeType)!>${list.computeType!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "HD")>
				<#--换单费-->
				<td style="text-align:right"><#if (list.updateOrderFee)!>${list.updateOrderFee?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.updateOrderFeeRemarks)!>${list.updateOrderFeeRemarks!}</#if></td>
				<#elseif (selectComboxValue == "BX")>
				<#--保险费-->
				<td style="text-align:right"><#if (list.premium)!>${list.premium?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.premiumRemarks)!>${list.premiumRemarks!}</#if></td>				
				<#elseif (selectComboxValue == "BG")>
				<#--报关服务费-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:right"><#if (list.customscharges)!>${list.customscharges!}</#if></td>
				<td style="text-align:right"><#if (list.portSurcharge)!>${list.portSurcharge!}</#if></td>
				<td style="text-align:right"><#if (list.demurrageCharge)!>${list.demurrageCharge!}</#if></td>
				<td style="text-align:right"><#if (list.containerDirtynessChange)!>${list.containerDirtynessChange!}</#if></td>
				<td style="text-align:right"><#if (list.elseFee)!>${list.elseFee!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "GS")>
				<#--关税-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.hsCode)!>${list.hsCode!}</#if></td>
				<td style="text-align:right"><#if (list.taxRate)!>${list.taxRate?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.customsdutiesComputeType)!>${list.customsdutiesComputeType!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "ZZ")>
				<#--税收-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:right"><#if (list.taxRate)!>${list.taxRate?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.addedvaluetaxComputeType)!>${list.addedvaluetaxComputeType!}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#elseif (selectComboxValue == "LR")>
				<#--利润-->
				<td style="text-align:right"><#if (list.price)!>${list.price?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.units)!>${list.units!}</#if></td>
				<td style="text-align:left"><#if (list.region)!>${list.region!}</#if></td>
				<td style="text-align:right"><#if (list.proportion)!>${list.proportion?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.remarks)!>${list.remarks!}</#if></td>
				<#else>
				</#if>
				</#if>
				<td style="text-align:left"><#if (list.accountingCode)!>${list.accountingCode!}</#if></td>
				<td style="text-align:left"><#if (list.quotedDate)!>${(list.quotedDate?string("yyyy-MM-dd HH:mm:ss"))!}</#if></td>
				<td style="text-align:left"><#if (list.applicationCode)!>${list.applicationCode!}</#if></td>
				<td style="text-align:left"><#if (list.applicationStatusValue)!>${list.applicationStatusValue!}</#if></td>
				<td style="text-align:right"><#if (list.x001Region)!>${list.x001Region?string(",##0.00")}</#if></td>
				<td style="text-align:left"><#if (list.oaApproveDate)!>${(list.oaApproveDate?string("yyyy-MM-dd HH:mm:ss"))!}</#if></td>
				<td style="text-align:left"><#if (list.oaContacts)!>${list.oaContacts!}</#if></td>
			</tr>
			</#list>
	</table>
</body>
</html>