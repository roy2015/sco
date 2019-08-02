<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script src="js/echarts/echarts.js"></script>
<script type="text/javascript">
	<#include "sco/shareFunctionAnalysis/purchasingAnalysis/merchandisePromotionBuyPriceAdjust/merchandisePromotionBuyPriceAdjustInfoModel.js" />
</script>

<link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body>
	<input type="hidden" name="minDate" id="minDate" value="${minDate}" />
	<input type="hidden" name="maxDate" id="maxDate" value="${maxDate}" />
	<input type="hidden" name="warehouseCode" id="warehouseCode" value="${warehouseCode}" />
	<input type="hidden" name="merchandiseAndSupplierCodes" id="merchandiseAndSupplierCodes" value="${merchandiseAndSupplierCodes}" />
	<input type="hidden" name="show" id="show" value="${show}" />
	
	<#-- 工具栏 -->
	<div id="erchandisePromotionBuyPriceAdjustInfo_toolbar" style="margin: 5px 5 5px 5; padding: 3px">
		<#-- 查询条件 -->
		<a onclick="merchandisePromotionBuyPriceAdjustInfoFn.close()" plain="true"	class="easyui-linkbutton" data-options="iconCls:'close'">关闭</a> 
		<a onclick="merchandisePromotionBuyPriceAdjustInfoFn.execlMerchandise()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'"> 导出Execl </a>
		<a onclick="merchandisePromotionBuyPriceAdjustInfoFn.saveFile()" plain="true"class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a> 
	</div>
	<div>
		<label>促销时间范围：${minDate}~${maxDate}</label><br/>
		<label>工厂：${warehouseCodeSplit}</label><br/>
	</div>
	<#-- 数据表格 -->
	<div id="tableInfo" style="height:550px;">
	<table id="erchandisePromotionBuyPriceAdjustInfo_grid"  
			fit="true" 
			class="easyui-datagrid"  
			pagination="true" 
			pagePosition='bottom' 
			pageSize="20" 
			pageList="[ 10, 20, 30, 40 ]"
			data-options="rownumbers:true" 
			url="merchandisePromotionBuyPriceAdjust_listMerchandisePromotionBuyPriceAdjustDetailInfo_2.html?merchandiseAndSupplierCodes=${merchandiseAndSupplierCodes}&minDate=${minDate}&maxDate=${maxDate}&show=${show}&warehouseCode=${warehouseCode}">
		<thead>
			<tr>
				<th align="left"data-options="field:'merchandiseCode',width:150">商品编号</th>
				<th align="left"data-options="field:'merchandiseName',width:200">商品名称</th>
				<th align="left"data-options="field:'dlRoleName',width:100">商品定量角色</th>
				<th align="left"data-options="field:'dxRoleName',width:100">商品定性角色</th>
				<th align="left"data-options="field:'centreName',width:100">中分类</th>
				<th align="left"data-options="field:'smallName',width:100">小分类</th>
				<th align="left"data-options="field:'detailName',width:100">明细类</th>
				<th align="left"data-options="field:'fineName',width:100">细分类</th>
				<th align="left"data-options="field:'supplierCode',width:150">供应商编号</th>
				<th align="left"data-options="field:'supplierName',width:150">供应商名称</th>
				<th align="left"data-options="field:'warehouseCode',width:150">工厂编号</th>
				<th align="left"data-options="field:'priceDate',width:200">促销进价开始结束日期</th>
				<th align="left"data-options="field:'promotionSchedule',width:200">促销档期</th>
				<th align="left"data-options="field:'promotionName',width:200">促销活动名称</th>
				<th align="right"data-options="field:'purchasePrice',width:180,formatter:merchandisePromotionBuyPriceAdjustInfoFn.formatterUnit">促销进价（元/kg或元/件）</th>
				<th align="right"data-options="field:'receiptRationed',width:150,formatter:merchandisePromotionBuyPriceAdjustInfoFn.formatterUnit">促销期间采购量（kg/件）</th>
				<th align="right"data-options="field:'receiptTotalPrice',width:120,formatter:merchandisePromotionBuyPriceAdjustInfoFn.formatterUnit">促销期间采购额（元）</th>
			</tr>
		</thead>
	</table>
	</div>
	<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:130px;" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{id:'save',text:'确定',iconCls:'save',handler:function(){merchandisePromotionBuyPriceAdjustInfoFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){merchandisePromotionBuyPriceAdjustInfoFn.closeSaveFileDlg()}}
			]
	">
		<label>将报表结果保存为：</label>
		<br/><br/>
		<input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/>		
	</div>
</body>
</html>
</#compress>
