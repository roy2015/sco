<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/purchaserData/merchandisePromotionPurchaseData/merchandisePromotionPurchaseDataModel.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
<#-- 工具栏 -->
	<div id="merchandisePromotionPurchaseData_toolbar">
		<form id="merchandisePromotionPurchaseData_search" method="post">
			<table style="width: 823px;">
				<#-- 查询条件 -->
				<tr>
					<td style="text-align: right; width: 80px">活动档期：</td>
					<td style="width: 110px;"><input class="easyui-validatebox" filterName="promotionSchedule" id="promotionSchedule" style="width: 120px;" /></td>
					<td style="text-align: right; width: 95px">促销活动名称：</td>
					<td style="width: 110px;"><input class="easyui-validatebox" filterName="promotionName" id="promotionName" style="width: 120px;" /></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 77px">商品编号：</td>
					<td><input class="easyui-validatebox" filterName="merchandiseCode" id="merchandiseCode" style="width: 120px;" /></td>
					<td style="text-align: right; width: 77px">供应商编号：</td>
					<td><input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width: 120px;" /></td>
					<td style="text-align: right; width: 79px;">
						促销进价日期
					</td>
					<td>
						<input class="easyui-datebox list-input" filterName="minDate" id="minDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" /> 
					</td>
					<td style="text-align: right;">
						至：
					</td>
					<td>
						<input class="easyui-datebox list-input" filterName="maxDate" id="maxDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" />
					</td>
				</tr>
			</table>
			<a onclick="merchandisePromotionPurchaseDataFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				查询已有记录
			</a>
			<a onclick="merchandisePromotionPurchaseDataFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" >
				删除已有记录
			</a>
			<a onclick="merchandisePromotionPurchaseDataFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
				清空查询
			</a>
			<a id="upload" onclick="merchandisePromotionPurchaseDataFn.showUpload();" plain="true" class="easyui-linkbutton" data-options="iconCls:'upload'">
				上传商品促销进货价格
			</a>
		</form>
	</div>
    <table  id="merchandisePromotionPurchaseData_grid"
		    fit="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#merchandisePromotionPurchaseData_toolbar"
			url='merchandisePromotionPurchaseData_listMerchandisePromotionPurchaseData_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
				<th data-options="field:'promotionName',width:150,sortable:false">促销名称</th>
				<th data-options="field:'promotionSchedule',width:150,sortable:false">促销档期</th>
				<th data-options="field:'merchandiseCode',width:150,sortable:true">商品编号</th>
				<th data-options="field:'merchandiseName',width:150,sortable:true">商品名称</th>
				<th data-options="field:'supplierCode',width:150,sortable:true">供应商编号</th>
				<th data-options="field:'supplierName',width:150,sortable:true">供应商名称</th>
				<th data-options="field:'minDate',width:150,sortable:true">促销开始时间</th>
				<th data-options="field:'maxDate',width:150,sortable:true">促销结束时间</th>
				<th align="right" data-options="field:'price',width:100,sortable:true,formatter:merchandisePromotionPurchaseDataFn.formatterUnit">促销进货价</th>
				<th data-options="field:'syncDate',width:150,sortable:true">上传时间</th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>