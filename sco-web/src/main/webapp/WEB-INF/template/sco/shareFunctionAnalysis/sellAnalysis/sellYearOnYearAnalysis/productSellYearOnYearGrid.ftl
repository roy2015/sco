<#compress>
<!DOCTYPE html>
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
   	 <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
   	 <link rel="stylesheet"  href="${copoxtPath}css/sco/report.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/shareFunctionAnalysis/sellAnalysis/sellYearOnYearAnalysis/productSellYearOnYearModel.js" />
    </script>
    <style>
	    .table th,.table td {
		line-height:25px;
		padding:0 8px;
		}
		.table td {
			border: dotted #BBB 1px;
		}
		.table th {
			font-weight: normal;
			border: solid #ddd 1px;
		}
		.table th {
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
<div >
	<input type="hidden" name="sellRegion" id="sellRegion" value="${sellRegion}" />
	<input type="hidden" name="startDate" id="startDate" value="${startDate}" />
	<input type="hidden" name="endDate" id="endDate" value="${endDate}" />
	<input type="hidden" name="merchandiseCodes" id="merchandiseCodes" value="${merchandiseCodes}" />
	<input type="hidden" name="merchandiseAndSupplierCodes" id="merchandiseAndSupplierCodes" value="${merchandiseAndSupplierCodes}" />
	<input type="hidden" name="directJoin" id="directJoin" value="${directJoin}" />
	<input type="hidden" name="seeYear" id="seeYear" value="${seeYear}" />
	<input type="hidden" name="rationed" id="rationed" value="${rationed}" />
	
	<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
		<a onclick="productSellFn.close()" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭</a> 
		<a onclick="productSellFn.saveFile();" plain="true"class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a> 
		<a onclick="productSellFn.exportProductSellExcel()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">导出Execl</a>
	</table>
</div>
	<table  id="productSell_Grid"
			class="easyui-datagrid"
			title="商品信息"
			fitColumns="true"
		    singleSelect="true"
		    checkOnSelect="false"
	   		selectOnCheck="false"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "10"
			pageList = "[ 10, 20, 30, 40 ]"
			url="sellYearOnYear_listProductInfo_2.html?merchandiseAndSupplierCodes=${merchandiseAndSupplierCodes}"
		    data-options="rownumbers:true"> 
		<thead>
			<tr>
				<th data-options="field:'merchandiseCode',width:100,sortable:false,align:'left',halign:'center'">
					<span>商品编号</span>
				</th>
				<th data-options="field:'merchandiseName',width:150,sortable:false,align:'left',halign:'center'">
					<span>商品名称</span>
				</th>
				<th data-options="field:'dxRoleName',width:100,sortable:false,align:'left',halign:'center'">
					<span>商品定性角色</span>
				</th>
				<th data-options="field:'dlRoleName',width:100,sortable:false,align:'left',halign:'center'">
					<span>商品定量角色</span>
				</th>
				<th data-options="field:'centreTypeName',width:100,sortable:false,align:'left',halign:'center'">
					<span>中分类</span>
				</th>
				<th data-options="field:'smallTypeName',width:100,sortable:false,align:'left',halign:'center'">
					<span>小分类</span>
				</th>
				<th data-options="field:'detailTypeName',width:100,sortable:false,align:'left',halign:'center'">
					<span>明细类</span>
				</th>
				<th data-options="field:'fineTypeName',width:100,sortable:false,align:'left',halign:'center'">
					<span>细分类</span>
				</th>
				<th data-options="field:'supplierCode',width:100,sortable:false,align:'left',halign:'center'">
					<span>供应商编号</span>
				</th>
				<th data-options="field:'supplierName',width:150,sortable:false,align:'left',halign:'center'">
					<span>供应商名称</span>
				</th>
			</tr>
		</thead>
	</table>
	
<label style="padding:0px;">商品同比信息：<br/>
	 <#if (directJoin??)&&(directJoin=='direct') >
	 只看直营门店数据
	 <#elseif (directJoin??)&&(directJoin=='join') >
	 只看加盟门店数据
	 <#else>
	 直营与加盟门店数据均看
	 </#if>
	 <br/>
	 <#if (rationed??)&&(rationed=='gjz') >
	  只查非定量装商品
	<#elseif (rationed??)&&(rationed=='dlz') >
	只查定定量装商品
	<#else>
	定量装和非定量装均看
	</#if>	
	<br/>
 数据时间范围：<font id="timeRange">${startDate} 至 ${endDate}</font>
</label>
<div >
	<table class="table table-bordered table-condensed" style="margin-left:10px">
		<thead >
			<#if (merchandiseList!=null)&&(merchandiseList?size>0)>
				<tr>
					<th css="th_color" style="text-align:center" rowspan="2">月份</th>
					<#if seeYear==3>
					<th style="text-align:center" colspan="8">销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="8">销售额(元)</th>
					<th style="text-align:center" colspan="8">毛利额(元)</th>
					<th style="text-align:center" colspan="8">PSD销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="8">PSD销售额(元)</th>
					<th style="text-align:center" colspan="8">PSD毛利额(元)</th>
					<#elseif seeYear==2>
					<th style="text-align:center" colspan="6">销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="6">销售额(元)</th>
					<th style="text-align:center" colspan="6">毛利额(元)</th>
					<th style="text-align:center" colspan="6">PSD销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="6">PSD销售额(元)</th>
					<th style="text-align:center" colspan="6">PSD毛利额(元)</th>
					<#else>
					<th style="text-align:center" colspan="4">销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="4">销售额(元)</th>
					<th style="text-align:center" colspan="4">毛利额(元)</th>
					<th style="text-align:center" colspan="4">PSD销售量${(rationed=='dlz')?string('(件)','(KG)')}</th>
					<th style="text-align:center" colspan="4">PSD销售额(元)</th>
					<th style="text-align:center" colspan="4">PSD毛利额(元)</th>
					</#if>
				</tr>
				<tr>
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>
					
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>	
					
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>	
					
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>	
					
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>	
					
					<th style="text-align:center">${firstYear?string("#.##")}</th>
					<th style="text-align:center">${firstYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${lastYear?string("#.##")}</th>
					<th style="text-align:center">${lastYear?string("#.##")}同比增长</th>
					<#if seeYear==3>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					<th style="text-align:center">${fourthYear?string("#.##")}</th>
					<th style="text-align:center">${fourthYear?string("#.##")}同比增长</th>
					<#elseif seeYear==2>
					<th style="text-align:center">${beforeLastYear?string("#.##")}</th>
					<th style="text-align:center">${beforeLastYear?string("#.##")}同比增长</th>
					</#if>	
				</tr>
			<#else>
				<tr>
					<th style="text-align:center">月份</th>
					<th style="text-align:center">销售量</th>
					<th style="text-align:center">销售额</th>
					<th style="text-align:center">毛利额</th>
					<th style="text-align:center">PSD销售量</th>
					<th style="text-align:center">PSD销售额</th>
					<th style="text-align:center">PSD毛利额</th>
				</tr>
			</#if>
		</thead>
		<tbody>
		  	<#list merchandiseList as merchandise>
			  	<tr>
		  			<td style="text-align:right"><#if (merchandise.sellMonth)!><#if merchandise.sellMonth?length gt 5>${merchandise.sellMonth?substring(5,7)}<#else>总计</#if></#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellQuantity)!>${merchandise.sellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellQuantityChange)!&&(merchandise.sellQuantityChange != 0)>${merchandise.sellQuantityChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellQuantity)!>${merchandise.lastSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellQuantityChange)!&&(merchandise.lastSellQuantityChange != 0)>${merchandise.lastSellQuantityChange}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforeSellQuantity)!>${merchandise.beforeSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellQuantityChange)!&&(merchandise.beforeSellQuantityChange != 0)>${merchandise.beforeSellQuantityChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellQuantity)!>${merchandise.fourthSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellQuantityChange)!&&(merchandise.fourthSellQuantityChange != 0)>${merchandise.fourthSellQuantityChange}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforeSellQuantity)!>${merchandise.beforeSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellQuantityChange)!&&(merchandise.beforeSellQuantityChange != 0)>${merchandise.beforeSellQuantityChange}%</#if></td>
		  			</#if>
		  			
		  			<td style="text-align:right"><#if (merchandise.sellTotalPrice)!>${merchandise.sellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellTotalPriceChange)!&&(merchandise.sellTotalPriceChange != 0)>${merchandise.sellTotalPriceChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellTotalPrice)!>${merchandise.lastSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellTotalPriceChange)!&&(merchandise.lastSellTotalPriceChange != 0)>${merchandise.lastSellTotalPriceChange}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforeSellTotalPrice)!>${merchandise.beforeSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellTotalPriceChange)!&&(merchandise.beforeSellTotalPriceChange != 0)>${merchandise.beforeSellTotalPriceChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellTotalPrice)!>${merchandise.fourthSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellTotalPriceChange)!&&(merchandise.fourthSellTotalPriceChange != 0)>${merchandise.fourthSellTotalPriceChange}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforeSellTotalPrice)!>${merchandise.beforeSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellTotalPriceChange)!&&(merchandise.beforeSellTotalPriceChange != 0)>${merchandise.beforeSellTotalPriceChange}%</#if></td>
		  			</#if>
		  			
		  			<td style="text-align:right"><#if (merchandise.sellProfit)!>${merchandise.sellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.sellProfitChange)!&&(merchandise.sellProfitChange != 0)>${merchandise.sellProfitChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellProfit)!>${merchandise.lastSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastSellProfitChange)!&&(merchandise.lastSellProfitChange != 0)>${merchandise.lastSellProfitChange}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforeSellProfit)!>${merchandise.beforeSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellProfitChange)!&&(merchandise.beforeSellProfitChange != 0)>${merchandise.beforeSellProfitChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellProfit)!>${merchandise.fourthSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthSellProfitChange)!&&(merchandise.fourthSellProfitChange != 0)>${merchandise.fourthSellProfitChange}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforeSellProfit)!>${merchandise.beforeSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforeSellProfitChange)!&&(merchandise.beforeSellProfitChange != 0)>${merchandise.beforeSellProfitChange}%</#if></td>
		  			</#if>
		  			
		  			<td style="text-align:right"><#if (merchandise.psdSellQuantity)!>${merchandise.psdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.psdSellQuantityChange)!&&(merchandise.psdSellQuantityChange != 0)>${merchandise.psdSellQuantityChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellQuantity)!>${merchandise.lastPsdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellQuantityChange)!&&(merchandise.lastPsdSellQuantityChange != 0)>${merchandise.lastPsdSellQuantityChange}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellQuantity)!>${merchandise.beforePsdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellQuantityChange)!&&(merchandise.beforePsdSellQuantityChange != 0)>${merchandise.beforePsdSellQuantityChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellQuantity)!>${merchandise.fourthPsdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellQuantityChange)!&&(merchandise.fourthPsdSellQuantityChange != 0)>${merchandise.fourthPsdSellQuantityChange}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellQuantity)!>${merchandise.beforePsdSellQuantity?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellQuantityChange)!&&(merchandise.beforePsdSellQuantityChange != 0)>${merchandise.beforePsdSellQuantityChange}%</#if></td>
		  			</#if>
		  			
		  			<td style="text-align:right"><#if (merchandise.psdSellTotalPrice)!>${merchandise.psdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.psdSellTotalPriceChange)!&&(merchandise.psdSellTotalPriceChange != 0)>${merchandise.psdSellTotalPriceChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellTotalPrice)!>${merchandise.lastPsdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellTotalPriceChange)!&&(merchandise.lastPsdSellTotalPriceChange != 0)>${merchandise.lastPsdSellTotalPriceChange}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellTotalPrice)!>${merchandise.beforePsdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellTotalPriceChange)!&&(merchandise.beforePsdSellTotalPriceChange != 0)>${merchandise.beforePsdSellTotalPriceChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellTotalPrice)!>${merchandise.fourthPsdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellTotalPriceChange)!&&(merchandise.fourthPsdSellTotalPriceChange != 0)>${merchandise.fourthPsdSellTotalPriceChange}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellTotalPrice)!>${merchandise.beforePsdSellTotalPrice?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellTotalPriceChange)!&&(merchandise.beforePsdSellTotalPriceChange != 0)>${merchandise.beforePsdSellTotalPriceChange}%</#if></td>
		  			</#if>
		  			
		  			<td style="text-align:right"><#if (merchandise.psdSellProfit)!>${merchandise.psdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.psdSellProfitChange)!&&(merchandise.psdSellProfitChange != 0)>${merchandise.psdSellProfitChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellProfit)!>${merchandise.lastPsdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.lastPsdSellProfitChange)!&&(merchandise.lastPsdSellProfitChange != 0)>${merchandise.lastPsdSellProfitChange}%</#if></td>
		  			<#if seeYear==3>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellProfit)!>${merchandise.beforePsdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellProfitChange)!&&(merchandise.beforePsdSellProfitChange != 0)>${merchandise.beforePsdSellProfitChange}%</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellProfit)!>${merchandise.fourthPsdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.fourthPsdSellProfitChange)!&&(merchandise.fourthPsdSellProfitChange != 0)>${merchandise.fourthPsdSellProfitChange}%</#if></td>
		  			<#elseif seeYear==2>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellProfit)!>${merchandise.beforePsdSellProfit?string(",##0.00")}</#if></td>
		  			<td style="text-align:right"><#if (merchandise.beforePsdSellProfitChange)!&&(merchandise.beforePsdSellProfitChange != 0)>${merchandise.beforePsdSellProfitChange}%</#if></td>
		  			</#if>
		        </tr>	
			</#list>
			
	</tbody>
	</table>
 </div>	
 <div class="easyui-dialog" id="saveFileDlg" style="width:320px;height:160px" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{id:'save',text:'确定',iconCls:'save',handler:function(){productSellFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){productSellFn.closeSaveFileDlg()}}]">
		<br/><br/>
		<div style="margin-left:25px">文件名称: <input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:200px;"/></div>		
 </div>
</body>
</html>
</#compress>
