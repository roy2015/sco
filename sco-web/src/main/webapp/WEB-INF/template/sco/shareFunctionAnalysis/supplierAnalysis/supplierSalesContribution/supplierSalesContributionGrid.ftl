<#compress>
<!DOCTYPE html>  
<html>  
	<head>  
	    <meta charset="UTF-8">  
	    <#include "inc/easyui.ftl" />
	   	 <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
	    <script type="text/javascript" >
			<#include "sco/shareFunctionAnalysis/supplierAnalysis/supplierSalesContribution/supplierSalesContributionModel.js" />
	    </script>
	</head>

	<body>
		<div id="salesContribution_toolbar">
			<form id="salesContribution_search" >
				<table >
					<tr>
						<td style="text-align:right;width:125px"><font color="red">*</font>时间范围：</td>
						<td style="width:125px">
						    <input class="easyui-datebox filterInput"  filterName="minDate" name="minDate" id="minDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMaxDateValue,formatter:yearFormatter"  style="width: 124px;" /> 
					    </td>
					    <td style="text-align:right;width:125px">至：</td>
						<td style="width:125px">
						    <input class="easyui-datebox filterInput"  filterName="maxDate" name="maxDate" id="maxDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMinDateValue,formatter:yearFormatter" style="width: 124px;" />
					    </td>
						<td style="text-align:right;width:125px">供应商编号：</td>
						<td style="width:125px">
							<input class="easyui-validatebox" filterName="supplierCode" name="supplierCode" id="supplierCode" style="width: 120px;" />
						</td>
						<td style="text-align:right;width:125px">供应商名称：</td>
						<td style="width:125px">
							<input class="easyui-validatebox" filterName="supplierName"  name="supplierName" id="supplierName" style="width: 120px;" />
						</td>
					</tr>
				</table>
			</form>
			<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
				<tr>
					<a onclick="salesContributionFn.search();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
						查看排行
					</a>
					<a onclick="salesContributionFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
						${action.getText("common.button.clearSearches")}
					</a>
					<a onclick="salesContributionFn.saveFile();" plain="true"class="easyui-linkbutton" data-options="iconCls:'save'"> 
						保存 
					</a> 
					<a onclick="salesContributionFn.exportSalesContributionToExcel()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">
						导出Execl
					</a>
				</tr>
			</table>		
		</div>
		<table  id="salesContribution_Grid"
			    fit="true"
			    singleSelect="true"
			    checkOnSelect="false"
		   		selectOnCheck="false"
				pagination = "true"
				pagePosition = 'bottom'
				pageSize = "20"
				pageList = "[ 10, 20, 30, 40 ]"
				toolbar="#salesContribution_toolbar"
				url='salesContribution_listSupplierSalesContribution_2.html'
			    data-options="rownumbers:true"> 
			<thead>
				<tr>
					<th rowspan="2" data-options="field:'NULL',width:40,checkbox:true"></th>
					<th data-options="field:'supplierCode',width:100,sortable:true">
						<span>供应商编号</span>
					</th>
					<th data-options="field:'supplierName',width:150,sortable:false">
						<span>供应商名称</span>
					</th>
					
					<th data-options="field:'sellTotalPrice',width:150,sortable:true,align:'right',formatter:function(value){
				 																						return moneyFormatter(value,2);
																								    }">
						<span>销售金额（元）</span>
					</th>
					<th data-options="field:'lastSellTotalPrice',width:150,sortable:false,align:'right',formatter:function(value){
				 																						return moneyFormatter(value,2);
																								    }">
						<span>销售金额同比（元）</span>
					</th>
					<th data-options="field:'sellTotalPriceChange',width:150,sortable:true,align:'right',formatter:formatterStrPercent">
						<span>销售金额占比</span>
					</th>
					<th data-options="field:'lastSellTotalPriceChange',width:150,sortable:false,align:'right',formatter:formatterStrPercent">
						<span>销售金额占比同比</span>
					</th>
					<th data-options="field:'sellTotalPriceRank',width:150,sortable:true,align:'right'">
						<span>销售金额占比排名</span>
					</th>
					<th data-options="field:'lastSellTotalPriceRank',width:150,sortable:false,align:'right'">
						<span>销售金额占比同比排名</span>
					</th>
					
					<th data-options="field:'sellQuantity',width:150,sortable:true,align:'right',formatter:function(value){
				 																					return moneyFormatter(value,2);
																								 }">
						<span>销量（公斤）</span>
					</th>
					<th data-options="field:'lastSellQuantity',width:150,sortable:false,align:'right',formatter:function(value){
				 																						return moneyFormatter(value,2);
																								    }">
						<span>销量同比（公斤）</span>
					</th>
					<th data-options="field:'sellQuantityChange',width:150,sortable:true,align:'right',formatter:formatterStrPercent">
						<span>销量占比</span>
					</th>
					<th data-options="field:'lastSellQuantityChange',width:150,sortable:false,align:'right',formatter:formatterStrPercent">
						<span>销量占比同比</span>
					</th>
					<th data-options="field:'sellQuantityRank',width:150,sortable:true,align:'right'">
						<span>销量占比排名</span>
					</th>
					<th data-options="field:'lastSellQuantityRank',width:150,sortable:false,align:'right'">
						<span>销量占比同比排名</span>
					</th>
					
					<th data-options="field:'sellProfit',width:150,sortable:true,align:'right',formatter:function(value){
				 																					return moneyFormatter(value,2);
																								}">
						<span>毛利额（元）</span>
					</th>
					<th data-options="field:'lastSellProfit',width:150,sortable:false,align:'right',formatter:function(value){
				 																						return moneyFormatter(value,2);
																								    }">
						<span>毛利额同比（元）</span>
					</th>
					<th data-options="field:'sellProfitChange',width:150,sortable:true,align:'right'">
						<span>毛利额占比</span>
					</th>
					<th data-options="field:'lastSellProfitChange',width:150,sortable:false,align:'right'">
						<span>毛利额占比同比</span>
					</th>
					<th data-options="field:'sellProfitRank',width:150,sortable:true,align:'right'">
						<span>毛利额占比排名</span>
					</th>
					<th data-options="field:'lastSellProfitRank',width:150,sortable:false,align:'right'">
						<span>毛利额占比同比排名</span>
					</th>
					
					<th data-options="field:'receiptPrice',width:150,sortable:true,align:'right',formatter:function(value){
				 																					return moneyFormatter(value,2);
																								 }">
						<span>进货金额（元）</span>
					</th>
					<th data-options="field:'lastReceiptPrice',width:150,sortable:false,align:'right',formatter:function(value){
				 																						return moneyFormatter(value,2);
																								    }">
						<span>进货金额同比（元）</span>
					</th>
					<th data-options="field:'receiptPriceChange',width:150,sortable:true,align:'right'">
						<span>进货金额占比</span>
					</th>
					<th data-options="field:'lastReceiptPriceChange',width:150,sortable:false,align:'right'">
						<span>进货金额占比同比</span>
					</th>
					<th data-options="field:'receiptPriceRank',width:150,sortable:true,align:'right'">
						<span>进货金额占比排名</span>
					</th>
					<th data-options="field:'lastReceiptPriceRank',width:150,sortable:false,align:'right'">
						<span>进货金额占比同比排名</span>
					</th>
					
					<th data-options="field:'receiptRationed',width:150,sortable:true,align:'right',formatter:function(value){
				 																						return moneyFormatter(value,2);
																								    }">
						<span>进货量（公斤）</span>
					</th>
					<th data-options="field:'lastReceiptRationed',width:150,sortable:false,align:'right',formatter:function(value){
				 																						return moneyFormatter(value,2);
																								    }">
						<span>进货量同比（公斤）</span>
					</th>
					<th data-options="field:'receiptRationedChange',width:150,sortable:true,align:'right',formatter:formatterStrPercent">
						<span>进货量占比</span>
					</th>
					<th data-options="field:'lastReceiptRationedChange',width:150,sortable:false,align:'right',formatter:formatterStrPercent">
						<span>进货量占比同比</span>
					</th>
					<th data-options="field:'receiptRationedRank',width:150,sortable:true,align:'right'">
						<span>进货量占比排名</span>
					</th>
					<th data-options="field:'lastReceiptRationedRank',width:150,sortable:false,align:'right'">
						<span>进货量占比同比排名</span>
					</th>
				</tr>
			</thead>
		</table>
		
		<div class="easyui-dialog" id="saveFileDlg" style="width:320px;height:130px" 
			data-options="
				title:'请输入文件名称',
				closable:false,closed:true,modal:true,buttons:[
						{id:'save',text:'确定',iconCls:'save',handler:function(){salesContributionFn.submitSaveFileDlg()}},
						{text:'关闭',iconCls:'close',handler:function(){salesContributionFn.closeSaveFileDlg()}}
				]
				">
			<br/><br/>
			<span class="txtCenter">
				<input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:200px;"/>
			</span>		
		</div>
	</body>
</html>
</#compress>