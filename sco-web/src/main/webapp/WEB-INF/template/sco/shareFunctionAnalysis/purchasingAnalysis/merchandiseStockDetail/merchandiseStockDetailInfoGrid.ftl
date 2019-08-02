<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script src="js/echarts/echarts.js"></script>
<script type="text/javascript">
	<#include "sco/shareFunctionAnalysis/purchasingAnalysis/merchandiseStockDetail/merchandiseStockDetailInfoModel.js" />
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
	<input type="hidden" name="type" id="type" value="${type}" />
	
	<#-- 工具栏 -->
	<div id="merchandiseStockInfo_toolbar" style="margin: 5px 5 5px 5; padding: 3px">
		<#-- 查询条件 -->
		<a onclick="merchandiseStockInfoFn.close()" plain="true"	class="easyui-linkbutton" data-options="iconCls:'close'">关闭</a> 
		<a onclick="merchandiseStockInfoFn.execlMerchandise()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'"> 导出Execl </a>
		<a onclick="merchandiseStockInfoFn.saveFile()" plain="true"class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a> 
	</div>
	<div>
		<label>时间范围：${minDateShow}~${maxDateShow}</label><br/>
		<label>${showName}</label><br/>
		<label>${typeName}</label><br/>
		<label>工厂：${warehouseCodeSplit}</label><br/>
		<label>库存地点：${regionCodeSplit}</label><br/>
	</div>
	<#-- 数据表格 -->
	<div id="tableInfo" class="merchandiseStockInfo" style="height:400px;">
	<#setting number_format="0.##">
	<table id="merchandiseStockInfo_grid"  fit="true" class="easyui-datagrid"  pagination="true" pagePosition='bottom' pageSize="20" pageList="[ 10, 20, 30, 40 ]"
		data-options="rownumbers:true" 
		url="merchandiseStockDetail_listMerchandiseStockDetailDetailInfo_2.html?merchandiseAndSupplierCodes=${merchandiseAndSupplierCodes}&minDate=${minDate}&maxDate=${maxDate}&show=${show}&regionCode=${regionCode}&type=${type}&warehouseCode=${warehouseCode}">
		<thead>
			<tr>
				<th align="left"data-options="field:'realityReceiptDate',width:100">到货日期</th>
				<th align="left"data-options="field:'merchandiseCode',width:150">商品编号</th>
				<th align="left"data-options="field:'merchandiseName',width:150">商品名称</th>
				<th align="left"data-options="field:'dlRoleName',width:100">商品定量角色</th>
				<th align="left"data-options="field:'dxRoleName',width:100">商品定性角色</th>
				<th align="left"data-options="field:'centreName',width:100">中分类</th>
				<th align="left"data-options="field:'smallName',width:100">小分类</th>
				<th align="left"data-options="field:'detailName',width:100">明细类</th>
				<th align="left"data-options="field:'fineName',width:100">细分类</th>
				<th align="left"data-options="field:'supplierCode',width:150">供应商编号</th>
				<th align="left"data-options="field:'supplierName',width:150">供应商名称</th>
				<th align="left"data-options="field:'warehouseCode',width:100">工厂编号</th>
				<th align="left"data-options="field:'regionCode',width:100">库存地点</th>
				<th align="right"data-options="field:'receiptRationedM',width:150,formatter:merchandiseStockInfoFn.formatterUnit">商品到货量（基本价格单位）</th>
				<th align="right"data-options="field:'receiptTotalPriceM',width:110,formatter:merchandiseStockInfoFn.formatterUnit">商品到货额（元）</th>
				<th align="right"data-options="field:'receiptRationedS',width:150,formatter:merchandiseStockInfoFn.formatterUnit">小分类到货量（基本价格单位）</th>
				<th align="right"data-options="field:'receiptTotalPriceS',width:110,formatter:merchandiseStockInfoFn.formatterUnit">小分类到货额（元）</th>
				<th align="right"data-options="field:'receiptRationedMS',width:150,formatter:merchandiseStockInfoFn.formatterNum">到货量占小分类比重</th>
				<th align="right"data-options="field:'receiptTotalPriceMS',width:150,formatter:merchandiseStockInfoFn.formatterNum">到货额占小分类比重</th>
				<th align="right"data-options="field:'receiptRationed',width:150,formatter:merchandiseStockInfoFn.formatterUnit">公司总到货量（基本价格单位）</th>
				<th align="right"data-options="field:'receiptTotalPrice',width:110,formatter:merchandiseStockInfoFn.formatterUnit">公司总到货额（元）</th>
				<th align="right"data-options="field:'receiptRationedMA',width:150,formatter:merchandiseStockInfoFn.formatterNum">到货量占公司整体比重</th>
				<th align="right"data-options="field:'receiptTotalPriceMA',width:150,formatter:merchandiseStockInfoFn.formatterNum">到货额占公司整体比重</th>
			</tr>
		</thead>
	</table>
	</div>
	<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:130px;" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{id:'save',text:'确定',iconCls:'save',handler:function(){merchandiseStockInfoFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){merchandiseStockInfoFn.closeSaveFileDlg()}}
			]
	">
		<label>将分析结果保存为：</label>
		<br/><br/>
		<input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/>		
	</div>
</body>
</html>
</#compress>
