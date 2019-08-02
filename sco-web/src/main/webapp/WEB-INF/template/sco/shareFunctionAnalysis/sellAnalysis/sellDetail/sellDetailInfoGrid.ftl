<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script type="text/javascript">
	<#include "sco/shareFunctionAnalysis/sellAnalysis/sellDetail/sellDetaiInfoModel.js" />
</script>
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<input type="hidden" name="minDate" id="minDate" value="${minDate}" />
	<input type="hidden" name="maxDate" id="maxDate" value="${maxDate}" />
	<input type="hidden" name="sellRegion" id="sellRegion" value="${sellRegion}" />
	<input type="hidden" name="supplierCode" id="supplierCode" value="${supplierCode}" />
	<input type="hidden" name="merchandiseCode" id="merchandiseCode" value="${merchandiseCode}" />
	<input type="hidden" name="show" id="show" value="${show}" />
	<input type="hidden" name="dl" id="dl" value="${dl}" />
	
	<#-- 按钮 -->
	<div>
		<a onclick="sellDetailInfoFn.close()" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭</a> 
		<a onclick="sellDetailInfoFn.saveFile();" plain="true"class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a> 
		<a onclick="sellDetailInfoFn.export2Excel()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'"> 导出Execl </a>
	</div>
	<div id="detailInfo" style="height:300px;">
	<#-- 数据表格 -->
	<table id="sellDetailInfo_grid" fit="true"class="easyui-datagrid" pagination="true" pagePosition='bottom' pageSize="20" title="明细表：" pageList="[ 10, 20, 30, 40 ]"
		 url="sellDetail_listSellDetailInfo_2.html?supplierCode=${supplierCode}&merchandiseCode=${merchandiseCode}&minDate=${minDate}&maxDate=${maxDate}&show=${show}&sellRegion=${sellRegion}&dl=${dl}" data-options="rownumbers:true" >
		<thead>
			<tr>
				<th data-options="field:'sellRegion',width:120,sortable:true">地区</th>
				<th data-options="field:'sellDate',width:120,sortable:false">销售日期</th>
				<th data-options="field:'merchandiseCode',width:150,sortable:true">商品编号</th>
				<th data-options="field:'merchandiseName',width:150,sortable:false">商品名称</th>
				<th data-options="field:'supplierCode',width:150,sortable:true">供应商编号</th>
				<th data-options="field:'supplierName',width:150,sortable:false">供应商名称</th>
				<th data-options="field:'dxRoleName',width:100,sortable:false">商品定性角色</th>
				<th data-options="field:'dlRoleName',width:100,sortable:false">商品定量角色</th>
				<th data-options="field:'centreName',width:100,sortable:false">中分类</th>
				<th data-options="field:'smallName',width:100,sortable:false">小分类</th>
				<th data-options="field:'detailName',width:100,sortable:false">明细类</th>
				<th data-options="field:'fineName',width:100,sortable:false">细分类</th>
				<th align="right" data-options="field:'purchaseDepartments',width:70,sortable:false">采购部门</th>
				<th align="right" data-options="field:'sellQuantity',width:150,sortable:false,formatter:sellDetailInfoFn.formatterUnit">销售量（基本价格单位）</th>
				<th align="right" data-options="field:'sellTotalPrice',width:100,sortable:false,formatter:sellDetailInfoFn.formatterUnit">销售额（元）</th>
				<th align="right" data-options="field:'sellProfit',width:100,sortable:false,formatter:sellDetailInfoFn.formatterUnit">毛利额（元）</th>
				<th align="right" data-options="field:'sellQuantityProportionM',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售量占比(占所有商品)</th>
				<th align="right" data-options="field:'sellTotalPriceProportionM',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售额占比(占所有商品)</th>
				<th align="right" data-options="field:'sellProfitProportionM',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">毛利额占比(占所有商品)</th>
				<th align="right" data-options="field:'sellQuantityProportionS',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售量占比(占小分类)</th>
				<th align="right" data-options="field:'sellTotalPriceProportionS',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售额占比(占小分类)</th>
				<th align="right" data-options="field:'sellProfitProportionS',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">毛利额占比(占小分类)</th>
				<th align="right" data-options="field:'sellQuantityProportionD',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售量占比(占明细类)</th>
				<th align="right" data-options="field:'sellTotalPriceProportionD',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售额占比(占明细类)</th>
				<th align="right" data-options="field:'sellProfitProportionD',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">毛利额占比(占明细类)</th>
				<th align="right" data-options="field:'psdSellQuantity',width:100,sortable:false,formatter:sellDetailInfoFn.formatterUnit">PSD销售量（基本价格单位）</th>
				<th align="right" data-options="field:'psdSellTotalPrice',width:150,sortable:false,formatter:sellDetailInfoFn.formatterUnit">PSD销售额（元）</th>
				<th align="right" data-options="field:'psdSellProfit',width:100,sortable:false,formatter:sellDetailInfoFn.formatterUnit">PSD毛利额（元）</th>
				<th align="right" data-options="field:'sumStore',width:80,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">权限数</th>
				<th align="right" data-options="field:'permissionStoreQuantity',width:100,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">权限店天数</th>
				<th align="right" data-options="field:'sellStoreQuantity',width:100,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">销售店天数</th>
				<th align="right" data-options="field:'active',width:80,sortable:false,formatter:sellDetailInfoFn.formatterActive">活跃度</th>
				<th align="right" data-options="field:'storeA',width:60,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">A级店数</th>
				<th align="right" data-options="field:'storeB',width:60,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">B级店数</th>
				<th align="right" data-options="field:'storeC',width:60,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">C级店数</th>
				<th align="right" data-options="field:'storeD',width:60,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">D级店数</th>
			</tr>
		</thead>
	</table>
	</div>
	<div style="padding: 10px;"></div>
	<div id="detailCount" style="height:200px;">
	<#-- 数据表格 -->
	<table id="sellDetailCount_grid" fit="true" class="easyui-datagrid"  pagination="true" pagePosition='bottom' title="汇总表：" pageSize="20" pageList="[ 10, 20, 30, 40 ]"
		 url="sellDetail_listSellDetailInfos_2.html?supplierCode=${supplierCode}&merchandiseCode=${merchandiseCode}&minDate=${minDate}&maxDate=${maxDate}&show=${show}&sellRegion=${sellRegion}&dl=${dl}" data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'sellRegion',width:120,sortable:true">地区</th>
				<th data-options="field:'sellDate',width:150,sortable:false,formatter:function(value,row){
																				 
																				 return row.minDate+'~'+row.maxDate
																				 
																				}">销售日期</th>
				<th data-options="field:'merchandiseCode',width:120,sortable:true">商品编号</th>
				<th data-options="field:'merchandiseName',width:120,sortable:false">商品名称</th>
				<th data-options="field:'supplierCode',width:100,sortable:true">供应商编号</th>
				<th data-options="field:'supplierName',width:80,sortable:false">供应商名称</th>
				<th data-options="field:'dxRoleName',width:80,sortable:false">商品定性角色</th>
				<th data-options="field:'dlRoleName',width:80,sortable:false">商品定量角色</th>
				<th data-options="field:'centreName',width:80,sortable:false">中分类</th>
				<th data-options="field:'smallName',width:80,sortable:false">小分类</th>
				<th data-options="field:'detailName',width:80,sortable:false">明细类</th>
				<th data-options="field:'fineName',width:80,sortable:false">细分类</th>
				<th data-options="field:'purchaseDepartments',width:70,sortable:false">采购部门</th>
				<th align="right" data-options="field:'sellQuantity',width:150,sortable:false,formatter:sellDetailInfoFn.formatterUnit">销售量（基本价格单位）</th>
				<th align="right" data-options="field:'sellTotalPrice',width:90,sortable:false,formatter:sellDetailInfoFn.formatterUnit">销售额（元）</th>
				<th align="right" data-options="field:'sellProfit',width:90,sortable:false,formatter:sellDetailInfoFn.formatterUnit">毛利额（元）</th>
				<th align="right" data-options="field:'sellQuantityProportionM',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售量占比(占所有商品)</th>
				<th align="right" data-options="field:'sellTotalPriceProportionM',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售额占比(占所有商品)</th>
				<th align="right" data-options="field:'sellProfitProportionM',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">毛利额占比(占所有商品)</th>
				<th align="right" data-options="field:'sellQuantityProportionS',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售量占比(占小分类)</th>
				<th align="right" data-options="field:'sellTotalPriceProportionS',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售额占比(占小分类)</th>
				<th align="right" data-options="field:'sellProfitProportionS',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">毛利额占比(占小分类)</th>
				<th align="right" data-options="field:'sellQuantityProportionD',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售量占比(占明细类)</th>
				<th align="right" data-options="field:'sellTotalPriceProportionD',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">销售额占比(占明细类)</th>
				<th align="right" data-options="field:'sellProfitProportionD',width:150,sortable:false,formatter:sellDetailInfoFn.formatterPer">毛利额占比(占明细类)</th>
				<th align="right" data-options="field:'psdSellQuantity',width:150,sortable:false,formatter:sellDetailInfoFn.formatterUnit">PSD销售量（基本价格单位）</th>
				<th align="right" data-options="field:'psdSellTotalPrice',width:90,sortable:false,formatter:sellDetailInfoFn.formatterUnit">PSD销售额（元）</th>
				<th align="right" data-options="field:'psdSellProfit',width:90,sortable:false,formatter:sellDetailInfoFn.formatterUnit">PSD毛利额（元）</th>
				<th align="right" data-options="field:'sumStore',width:60,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">权限数</th>
				<th align="right" data-options="field:'permissionStoreQuantity',width:80,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">权限店天数</th>
				<th align="right" data-options="field:'sellStoreQuantity',width:80,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">销售店天数</th>
				<th align="right" data-options="field:'active',width:60,sortable:false,formatter:sellDetailInfoFn.formatterActive">活跃度</th>
				<th align="right" data-options="field:'storeA',width:60,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">A级店数</th>
				<th align="right" data-options="field:'storeB',width:60,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">B级店数</th>
				<th align="right" data-options="field:'storeC',width:60,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">C级店数</th>
				<th align="right" data-options="field:'storeD',width:60,sortable:false,formatter:sellDetailInfoFn.formatterUnit1">D级店数</th>
			</tr>
		</thead>
	</table>
	</div>
	<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:130px;" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{id:'save',text:'确定',iconCls:'save',handler:function(){sellDetailInfoFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){sellDetailInfoFn.closeSaveFileDlg()}}
			]
	">
		<label>将分析结果保存为：</label>
		<br/><br/>
		<input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/>		
	</div>
	<label>说明:销售明细报表的明细表中不体现无销售记录的日期的商品权限数，但销售明细报表的汇总表中包含无销售记录日期的商品权限数</label>
</body>
</html>
</#compress>
