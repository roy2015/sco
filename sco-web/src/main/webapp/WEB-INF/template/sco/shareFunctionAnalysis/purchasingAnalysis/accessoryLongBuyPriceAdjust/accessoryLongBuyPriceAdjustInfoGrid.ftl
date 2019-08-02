<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script src="js/echarts/echarts.js"></script>
<script type="text/javascript">
	<#include "sco/shareFunctionAnalysis/purchasingAnalysis/accessoryLongBuyPriceAdjust/accessoryLongBuyPriceAdjustInfoModel.js" />
</script>

<link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body>
	<input type="hidden" name="minDate" id="minDate" value="${minDate}" />
	<input type="hidden" name="maxDate" id="maxDate" value="${maxDate}" />
	<input type="hidden" name="regionCode" id="sellRegion" value="${regionCode}" />
	<input type="hidden" name="warehouseCode" id="warehouseCode" value="${warehouseCode}" />
	<input type="hidden" name="merchandiseAndSupplierCodes" id="merchandiseAndSupplierCodes" value="${merchandiseAndSupplierCodes}" />
	<input type="hidden" name="show" id="show" value="${show}" />
	
	<#-- 工具栏 -->
	<div id="erchandiseLongBuyPriceAdjustInfo_toolbar" style="margin: 5px 5 5px 5; padding: 3px">
		<#-- 查询条件 -->
		<a onclick="accessoryLongBuyPriceAdjustInfoFn.close()" plain="true"	class="easyui-linkbutton" data-options="iconCls:'close'">关闭</a> 
		<a onclick="accessoryLongBuyPriceAdjustInfoFn.execlMerchandise()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'"> 导出Execl </a>
		<a onclick="accessoryLongBuyPriceAdjustInfoFn.saveFile()" plain="true"class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a> 
	</div>
	<div>
		<label>时间范围：${minDate}~${maxDate}</label><br/>
		<label>工厂：${warehouseCodeSplit}</label><br/>
		<label>库存地点：${regionCodeSplit}</label><br/>
	</div>
	<#-- 数据表格 -->
	<div id="tableInfo" style="height:200px;">
	<table id="accessoryLongBuyPriceAdjustInfo_grid"  fit="true" class="easyui-datagrid"
		data-options="rownumbers:true" 
		url="accessoryLongBuyPriceAdjust_listAccessoryLongBuyPriceAdjustDetail_2.html?merchandiseAndSupplierCodes=${merchandiseAndSupplierCodes}&minDate=${minDate}&maxDate=${maxDate}&show=${show}&regionCode=${regionCode}&warehouseCode=${warehouseCode}">
		<thead>
			<tr>
				<th align="left"data-options="field:'merchandiseCode',width:150">商品编号</th>
				<th align="left"data-options="field:'merchandiseName',width:200">商品名称</th>
				<th align="left"data-options="field:'fineName',width:100">细分类</th>
				<th align="left"data-options="field:'supplierCode',width:150">供应商编号</th>
				<th align="left"data-options="field:'supplierName',width:150">供应商名称</th>
				<th align="left"data-options="field:'warehouseCode',width:150">工厂编号</th>
				<th align="right"data-options="field:'receiptRationed',width:100,formatter:accessoryLongBuyPriceAdjustInfoFn.formatterUnit">调整后价格采购量</th>
				<th align="right"data-options="field:'receiptTotalPrice',width:100,formatter:accessoryLongBuyPriceAdjustInfoFn.formatterUnit">调整后价格采购额</th>
				<th align="right"data-options="field:'increaseDecrease',width:100,formatter:accessoryLongBuyPriceAdjustInfoFn.formatterUnit">成本增减情况</th>
			</tr>
		</thead>
	</table>
	</div>
	<div id="tableDetail"  style="height:330px;">
	<table id="accessoryLongBuyPriceAdjustDetail_grid"  fit="true" class="easyui-datagrid" 
		data-options="rownumbers:true" 
		url="accessoryLongBuyPriceAdjust_listAccessoryLongBuyPriceAdjustDetailInfo_2.html?merchandiseAndSupplierCodes=${merchandiseAndSupplierCodes}&minDate=${minDate}&maxDate=${maxDate}&show=${show}&regionCode=${regionCode}&warehouseCode=${warehouseCode}">
		<thead>
			<tr>
				<th align="left"data-options="field:'merchandiseCode',width:150">商品编号</th>
				<th align="left"data-options="field:'merchandiseName',width:200">商品名称</th>
				<th align="left"data-options="field:'fineName',width:100">细分类</th>
				<th align="left"data-options="field:'supplierCode',width:150">供应商编号</th>
				<th align="left"data-options="field:'supplierName',width:150">供应商名称</th>
				<th align="left"data-options="field:'warehouseCode',width:150">工厂编号</th>
				<th align="left"data-options="field:'regionCode',width:150">库存地点</th>
				<th align="left"data-options="field:'priceDate',width:150">调整后价格维护日期</th>
				<th align="right"data-options="field:'purchasePriceO',width:150,formatter:accessoryLongBuyPriceAdjustInfoFn.formatterUnit">调整前价格</th>
				<th align="right"data-options="field:'purchasePriceN',width:150,formatter:accessoryLongBuyPriceAdjustInfoFn.formatterUnit">调整后价格</th>
				<th align="right"data-options="field:'purchasePriceC',width:150,formatter:accessoryLongBuyPriceAdjustInfoFn.formatterUnit">调整后价差</th>
				<th align="right"data-options="field:'purchasePriceB',width:150,formatter:function(value,row){
							if(value==null){
								return '';
							}else{
								return moneyFormatter(value)+'%';
							}
						}">调整后价差百分比</th>
				<th align="right"data-options="field:'receiptRationed',width:100,formatter:accessoryLongBuyPriceAdjustInfoFn.formatterUnit">调整后价格采购量</th>
				<th align="right"data-options="field:'receiptTotalPrice',width:100,formatter:accessoryLongBuyPriceAdjustInfoFn.formatterUnit">调整后价格采购额</th>
				<th align="right"data-options="field:'increaseDecrease',width:100,formatter:accessoryLongBuyPriceAdjustInfoFn.formatterUnit">成本增减情况</th>
			</tr>
		</thead>
	</table>
	</div>
	<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:130px;" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{id:'save',text:'确定',iconCls:'save',handler:function(){accessoryLongBuyPriceAdjustInfoFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){accessoryLongBuyPriceAdjustInfoFn.closeSaveFileDlg()}}
			]
	">
		<label>将报表结果保存为：</label>
		<br/><br/>
		<input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/>		
	</div>
</body>
</html>
</#compress>
