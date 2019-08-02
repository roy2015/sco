<#compress>
	<script type="text/javascript" >
	    <#include "sco/common/masterDataType.js" />
		<#include "sco/merchandiseCostAnalysis/totalCostAnalogyAnalysis/costContrast/addReferIntention.js" />
    </script>
	
	<!-- 添加参照品-意向品 --> 
	<div id="addReferIntention_toolbar">
		<form id="signedQty_search">
			<table class="table table-condensed" style="width: 1150px;">
				<tr>
					<td><a id="ok" onclick="addReferIntentionFn.ok();" plain="true" class="easyui-linkbutton" data-options="iconCls:'active'">选择</a></td>
					<td colspan="7"><a id="close" onclick="addReferIntentionFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭</a></td>
				</tr>
				<tr>
					<td colspan="8"><strong>搜索意向品的核算表记录</strong></td>
				</tr>
				<tr>
					<td style="text-align:right;width:125px">供应商/意向供应商编号：</td>
					<td style="width:125px"><input filterName="supplierCode" type="text" id="refISupplierCode" data-options="required:false,editable:false" style="width: 120px;" /></td>
					<td style="text-align:right;width:125px">供应商/意向供应商名称：</td>
					<td style="width:125px"><input filterName="supplierName" type="text" id="refISupplierName" data-options="required:false,editable:false" style="width: 120px;" /></td>
					<td style="text-align:right;width:125px">意向品编号：</td>
					<td style="width:125px"><input filterName="intentionCode" type="text" id="intentionCode" data-options="required:false,editable:false" style="width: 120px;" /></td>
					<td style="text-align:right;width:125px">意向品名称：</td>
					<td style="width:125px"><input filterName="intentionName" type="text" id="intentionName" data-options="required:false,editable:false" style="width: 120px;" /></td>
				</tr>
				<tr>
					<td align="right">中分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="centreTypeCode" id="centreTypeCodeElse" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'masterDataType_listCentreType_5.html'
								    ">
						</input>
					</td>
					<td align="right">小分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false">
						</input>
					</td>
					<td align="right">明细类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false">
						</input>
					</td>
					<td align="right">细分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false">
						</input>
					</td>
				</tr>
				<tr>
					<td align="right">采购部门：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="purchaseDepartments" id="purchaseDepartments" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'businessComBox_procurementDepartments_5.html'
								    ">
						</input>
					</td>
					<td align="right">是否定量：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="rationed" id="rationed" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'businessComBox_rationed_5.html'
								    ">
						</input>
					</td>
					<td align="right">采购类型：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="purchaseType" id="purchaseType" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'businessComBox_purchaseType_5.html'
								    ">
						</input>
					</td>
					<td align="right">销售方式：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="saleType" id="saleType" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'masterDataType_listSaleType_5.html'
								    ">
						</input>
					</td>
				</tr>
				<tr>
					<td  align="right">报价日期： </td>
					<td>
						<input class="easyui-datebox " filterName="minQuotedDate" id="minQuotedDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" />
					</td>
					<td  align="right">至：</td>
					<td>
						<input class="easyui-datebox " filterName="maxQuotedDate" id="maxQuotedDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" />
					</td>
					<td align="right">报价币种：</td>
					<td colspan="3">
						<input class="easyui-combobox filterSelect" filterName="quotedCurrency" id="quotedCurrency" style="width: 124px;" 
									data-options="
										valueField:'id',
										textField:'text',
										editable:false,
										url:'businessComBox_quotedCurrencyList_5.html'"/>
					</td>
				</tr>
			</table>
			<a id="search" onclick="addReferIntentionFn.search();" plain="true" class="easyui-linkbutton" data-options="iconCls:'find'"> 搜索 </a>
			<a id="clear" onclick="addReferIntentionFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a>
		</form>
	</div>
	
    <table  id="addReferIntention_grid"
			fit="true"
			selectOnCheck="true"
			checkOnSelect="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#addReferIntention_toolbar"
			url='totalCostAnalogyAnalysis_listAddReferIntention_2.html'
			
			data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',width:40,checkbox:true"></th>
				<th data-options="field:'intentionCode',width:110,sortable:true">意向品编号</th>
				<th data-options="field:'intentionName',width:140,sortable:false">意向品名称</th>
				<th data-options="field:'supplierCode',width:140,sortable:true">供应商/意向供应商编号</th>
				<th data-options="field:'supplierName',width:150,sortable:false">供应商/意向供应商名称</th>
				<th data-options="field:'purchaseDepartments',width:90,sortable:false">采购部门</th>
				<th data-options="field:'rationed',width:90,sortable:false,sortable:false">是否定量装</th>
				<th data-options="field:'purchaseType',width:90,sortable:false">采购类型</th>
				<th data-options="field:'saleType',width:90,sortable:false">销售方式</th>
				<th data-options="field:'centreTypeName',width:90,sortable:false">中分类</th>
				<th data-options="field:'smallTypeName',width:80,sortable:false,formatter:function(value,row){
																				 if(row.intentionSmallTypeCode != null && row.intentionSmallTypeCode == 'ELSE') {
																					return row.elseTypeName;
																				 }else {
																					return value;
																				 }
																				}">小分类</th>
				<th data-options="field:'detailTypeName',width:80,sortable:false">明细类</th>
				<th data-options="field:'fineTypeName',width:80,sortable:false">细分类</th>
				<th data-options="field:'accountingCode',width:120,sortable:true">核算表/投料表编号</th>
				<th data-options="field:'quotedDate',width:150,sortable:true">报价日期</th>
				<th data-options="field:'updated',width:150,sortable:true">核算表/投料表修改日期</th>
				<th data-options="field:'updateby',width:100,sortable:false">操作人</th>
				<th data-options="field:'applicationCode',width:100,sortable:false,hidden:true"/>
			</tr>
		</thead>
	</table>
</#compress>