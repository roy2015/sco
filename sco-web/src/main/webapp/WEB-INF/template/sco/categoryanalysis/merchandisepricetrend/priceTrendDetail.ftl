<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script src="js/echarts/echarts.js"></script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<form>
		<input type="hidden" id="merchandiseCode" name="merchandiseCode" value="${merchandiseCode}">
		<input type="hidden" id="supplierCode" name="supplierCode" value="${supplierCode}">
		<input type="hidden" id="itemValue" name="itemValue" value="${itemValue}">		
		<input type="hidden" id="regionCode" name="regionCode" value="${regionCode}">
		<input type="hidden" id="qlStartDate" name="qlStartDate" value="${qlStartDate}">
		<input type="hidden" id="qlEndDate" name="qlEndDate" value="${qlEndDate}">

		<div style="margin:5px">
			<a id="close" onclick="priceTrendDetailFn.closeWindow();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'"> 关闭 </a>
			<a id="save" onclick="priceTrendDetailFn.saveFile();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a>
			<a id="excel" onclick="priceTrendDetailFn.export2Excel();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'"> 导出Excel </a>
		</div>
	<!-- 条件-->
	<div style="margin:5px 5 5px 5;padding:3px">
	 	<table>
	 			<tr>
					<td>时间范围</td>
					<td >
						${qlStartDate} 至 ${qlEndDate}
					</td>
				</tr>
				<tr>
					<td>地区</td>
					<td >
						${regionName}
					</td>
				</tr>
				<tr>
					<td >
						<#if (itemValue == "1")>
						只看直营门店数据 
						</#if>
						<#if (itemValue == "2")>
						只看加盟门店数据 
						</#if>
						<#if (itemValue == "3")>
						直营与加盟门店数据均看
						</#if>
					 </td>
				</tr>
	 	</table>
	 </div>
		<#-- 数据表格 -->
	    <table id="merchandisePriceDetail_grid"
			    fit="false"
			    iconCls= "icon-save"
				pagination = "false"
			    url="merchandisePriceTrend_listMerchandisePriceTrend_2.html?merchandiseCode=${merchandiseCode}&&supplierCode=${supplierCode}&&itemValue=${itemValue}&&regionCode=${regionCode}&&qlStartDate=${qlStartDate}&&qlEndDate=${qlEndDate}"
			    data-options="rownumbers:false" >
        	<thead>  
        		<tr>  
					<th rowspan="2" data-options="field:'merchandiseCode',width:100,sortable:true">
	                	商品编号
	                </th>
					<th rowspan="2" data-options="field:'merchandiseName',width:100,sortable:false">
	                	商品名称
	                </th>
					<th rowspan="2" data-options="field:'centreName',width:100,sortable:false">
	                	中分类
	                </th>
					<th rowspan="2" data-options="field:'smallName',width:100,sortable:false">
	                	小分类
	                </th>
					<th rowspan="2" data-options="field:'detailName',width:100,sortable:false">
	                	明细类
	                </th>
					<th rowspan="2" data-options="field:'supplierCode',width:100,sortable:false">
	                	供应商编号
	                </th>
					<th rowspan="2" data-options="field:'supplierName',width:100,sortable:false">
	                	供应商名称
	                </th>
				    <th rowspan="2" data-options="field:'specification',width:80,sortable:false">
			        	商品规格
			        </th>
			        <th rowspan="2" data-options="field:'regionName',width:80,sortable:false">
			        	地区
			        </th>
			        <th rowspan="2" data-options="field:'priceChangeDate',width:100,sortable:false">
			        	价格维护日期
			        </th>
			         <th align="right" rowspan="2" data-options="field:'sellPrice',width:90,sortable:false"  formatter="formatterUnit">
			        	售价（元）
			        </th>
			         <th align="right" rowspan="2" data-options="field:'sellQuantity',width:100,sortable:false" formatter="formatterUnit">
			        	销售量（KG/件）
			        </th>
			         <th align="right" rowspan="2" data-options="field:'sellTotalPrice',width:90,sortable:false" formatter="formatterUnit">
			        	销售额（元）
			        </th>
			        <th align="right" rowspan="2" data-options="field:'sellProfit',width:90,sortable:false" formatter="formatterUnit">
			        	毛利额（元）
			        </th>
			        <th align="right" rowspan="2" data-options="field:'sellStoreQuantity',width:80,sortable:false" formatter="formatterUnit1">
			        	销售店天
			        </th>
			        <th align="right" rowspan="2" data-options="field:'permissionStoreQuantity',width:80,sortable:false" formatter="formatterUnit1">
			        	权限店天
			        </th>
			         <th align="right" rowspan="2" data-options="field:'activeDegrees',width:80,sortable:false">
			        	活跃度
			        </th>
			        <th align="right" rowspan="2" data-options="field:'psdSellQuantity',width:120,sortable:false" formatter="formatterUnit"> 
			        	PSD销售量（KG/件）
			        </th>
			        <th align="right" rowspan="2" data-options="field:'psdSellTotalPrice',width:90,sortable:false" formatter="formatterUnit">
			        	PSD业绩（元）
			        </th>
			        <th align="right" rowspan="2" data-options="field:'psdSellProfit',width:90,sortable:false" formatter="formatterUnit">
			        	PSD毛利（元）
			        </th>
			        <th align="right" rowspan="2" data-options="field:'sellPriceGains',width:80,sortable:false" formatter="formatValue">
			        	售价涨幅
			        </th>
			         <th align="right" rowspan="2" data-options="field:'psdSalesGains',width:90,sortable:false" formatter="formatValue">
			        	PSD销售量涨幅
			        </th>
			        <th align="right" rowspan="2" data-options="field:'psdResultsGains',width:80,sortable:false" formatter="formatValue">
			        	PSD业绩涨幅
			        </th>
			        <th align="right" rowspan="2" data-options="field:'psdGrossProfitGains',width:80,sortable:false" formatter="formatValue">
			        	PSD毛利涨幅
			        </th>
			    </tr>
	         </thead>
	    </table>
	    <div id="line0" style="height:400px">
	    	
	    </div>
	        <script type="text/javascript" >
		   <#include "sco/categoryanalysis/merchandisepricetrend/priceTrendDetailModel.js" />
	</script>
	
	<div class="panel window" style="display: none; width: 258px; left: 548px; top: 253.5px;">
		<div class="panel-header panel-header-noborder window-header" style="width: 258px;">
			<div class="panel-title">请输入文件名称</div>
			<div class="panel-tool"></div>
		</div>
		<div class="easyui-dialog panel-body panel-body-noborder window-body" 
			id="saveFileDlg" 
			data-options="
				title:'请输入文件名称',
				closable:false,closed:true,modal:true,buttons:[
				{id:'save',text:'确定',iconCls:'save',handler:function(){priceTrendDetailFn.submitSaveFileDlg()}},
				{text:'关闭',iconCls:'close',handler:function(){priceTrendDetailFn.closeSaveFileDlg()}}
			]"
			 title="" 
			 style="overflow: hidden; width: 256px; height: 124px;">
		<div class="panel" style="display: block; width: 256px; text-align: center;">
		<div style="width: 256px; height: 89px;" title="" class="panel-body panel-body-noheader panel-body-noborder dialog-content" id="">
<br><br>
	<span class="txtCenter"><input id="fileName" class="easyui-validatebox validatebox-text" data-options="validType:'validateVarName'" style="width:190px;"></span>
	</div>
	</div>
	<div class="dialog-button">
		<a href="javascript:void(0)" class="l-btn" id=""><span class="l-btn-left"><span class="l-btn-text save l-btn-icon-left">确定</span></span></a>
		<a href="javascript:void(0)" class="l-btn" id=""><span class="l-btn-left"><span class="l-btn-text close l-btn-icon-left">关闭</span></span></a>
	</div>
</div>
</div>
</form>
</body>
</html>
</#compress>