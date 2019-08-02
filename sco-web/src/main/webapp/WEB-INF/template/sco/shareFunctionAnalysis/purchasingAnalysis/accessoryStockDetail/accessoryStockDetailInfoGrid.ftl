<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script src="js/echarts/echarts.js"></script>
<script type="text/javascript">
	<#include "sco/shareFunctionAnalysis/purchasingAnalysis/accessoryStockDetail/accessoryStockDetailInfoModel.js" />
</script>

<link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body>
	<input type="hidden" name="minDate" id="minDate" value="${minDate}" />
	<input type="hidden" name="maxDate" id="maxDate" value="${maxDate}" />
	<input type="hidden" name="regionCode" id="sellRegion" value="${regionCode}" />
	<input type="hidden" name="warehouseCode" id="warehouseCode" value="${warehouseCode}" />
	<input type="hidden" name="merchandiseAndSupplierCodes" id="merchandiseAndSupplierCodes" value="${merchandiseAndSupplierCodes}" />
	<input type="hidden" name="type" id="type" value="${type}" />
	
	<#-- 工具栏 -->
	<div id="accessoryStockInfo_toolbar" style="margin: 5px 5 5px 5; padding: 3px">
		<#-- 查询条件 -->
		<a onclick="accessoryStockInfoFn.close()" plain="true"	class="easyui-linkbutton" data-options="iconCls:'close'">关闭</a> 
		<a onclick="accessoryStockInfoFn.execlMerchandise()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'"> 导出Execl </a>
		<a onclick="accessoryStockInfoFn.saveFile()" plain="true"class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a> 
	</div>
	<div>
		<label>时间范围：${minDateShow}~${maxDateShow}</label><br/>
		<label>${typeName}</label><br/>
		<label>工厂：${warehouseCodeSplit}</label><br/>
		<label>库存地点：${regionCodeSplit}</label><br/>
	</div>
	<#-- 数据表格 -->
	<div id="tableInfo" class="accessoryStockInfo" style="height:400px;">
	<table id="accessoryStockInfo_grid"  fit="true" class="easyui-datagrid"  pagination="true" pagePosition='bottom' pageSize="20" pageList="[ 10, 20, 30, 40 ]"
		data-options="rownumbers:true" 
		url="accessoryStockDetail_listAccessoryStockDetailDetailInfo_2.html?merchandiseAndSupplierCodes=${merchandiseAndSupplierCodes}&minDate=${minDate}&maxDate=${maxDate}&regionCode=${regionCode}&type=${type}&warehouseCode=${warehouseCode}">
		<thead>
			<tr>
				<th align="left"data-options="field:'realityReceiptDate',width:100">到货日期</th>
				<th align="left"data-options="field:'merchandiseCode',width:150">商品编号</th>
				<th align="left"data-options="field:'merchandiseName',width:150">商品名称</th>
				<th align="left"data-options="field:'fineName',width:100">细分类</th>
				<th align="left"data-options="field:'supplierCode',width:150">供应商编号</th>
				<th align="left"data-options="field:'supplierName',width:150">供应商名称</th>
				<th align="left"data-options="field:'warehouseCode',width:100">工厂编号</th>
				<th align="left"data-options="field:'regionCode',width:100">库存地点</th>
				<th align="right"data-options="field:'receiptRationedM',width:100,formatter:accessoryStockInfoFn.formatterUnit">商品到货量（基本价格单位）</th>
				<th align="right"data-options="field:'receiptTotalPriceM',width:100,formatter:accessoryStockInfoFn.formatterUnit">商品到货额（元）</th>
				<th align="right"data-options="field:'receiptRationed',width:100,formatter:accessoryStockInfoFn.formatterUnit">公司总到货量（基本价格单位）</th>
				<th align="right"data-options="field:'receiptTotalPrice',width:100,formatter:accessoryStockInfoFn.formatterUnit">公司总到货额（元）</th>
				<th align="right"data-options="field:'receiptRationedMA',width:150,formatter:accessoryStockInfoFn.formatterNum">到货量占公司整体比重</th>
				<th align="right"data-options="field:'receiptTotalPriceMA',width:150,formatter:accessoryStockInfoFn.formatterNum">到货额占公司整体比重</th>
			</tr>
		</thead>
	</table>
	</div>
	<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:130px;" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{id:'save',text:'确定',iconCls:'save',handler:function(){accessoryStockInfoFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){accessoryStockInfoFn.closeSaveFileDlg()}}
			]
	">
		<label>将分析结果保存为：</label>
		<br/><br/>
		<input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/>		
	</div>
</body>
</html>
</#compress>
