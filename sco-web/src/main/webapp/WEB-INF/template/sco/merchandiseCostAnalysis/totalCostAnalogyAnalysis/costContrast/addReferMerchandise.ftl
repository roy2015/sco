<#compress>
    <script type="text/javascript" >
		<#include "sco/common/masterDataType.js" />
		<#include "sco/merchandiseCostAnalysis/totalCostAnalogyAnalysis/costContrast/addReferMerchandise.js" />
    </script>
    	
	<!-- 添加参照品-商品 --> 
	<div id="addReferMerchandise_toolbar">
		<form id="signedQty_search">
			<table class="table table-condensed" style="width: 1000px;">
			<tr style="height:15px;">
					<td colspan="8"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><a id="ok" onclick="addReferMerchandiseFn.ok();" plain="true" class="easyui-linkbutton" data-options="iconCls:'active'">选择</a></td>
					<td colspan="6"><a id="close" onclick="addReferMerchandiseFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭</a></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><strong>搜索已引进商品的审批通过价格记录</strong></td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td style="text-align:right;width:125px">供应商编号：</td>
					<td style="width:125px;">
						<input filterName="supplierCode" id="refMSupplierCode" style="width:120px;" />
					</td>
					<td style="text-align:right;width:125px">供应商名称：</td>
					<td style="width:125px;">
						<input filterName="supplierName" id="refMSupplierName" style="width:120px;" />
					</td>
					<td style="width:125px;"></td>
					<td style="width:125px;"></td>
					<td style="width:125px;"></td>
					<td style="width:125px;"></td>
				</tr>
				<tr>
					<td align="right">商品编号：</td>
					<td><input filterName="merchandiseCode" type="text" id="refMMerchandiseCode" data-options="required:false,editable:false" style="width: 120px;" /></td>
					<td align="right">商品名称：</td>
					<td><input filterName="merchandiseName" type="text" id="refMMerchandiseName" data-options="required:false,editable:false" style="width: 120px;" /></td>
					<td style="text-align:right;width:79px;">商品定性角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="merchandiseDxRoleCode" id="dxRoleCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'merchandiseRole_listQualitative_5.html'"/>
					</td>
					<td style="text-align:right;width:79px;">商品定量角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="merchandiseDlRoleCode" id="dlRoleCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'merchandiseRole_listQuantify_5.html'"/>
					</td>
				</tr>
				<tr>
					<td align="right">中分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="centreTypeCode" id="centreTypeCodeElse" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'masterDataType_listCentreType_5.html'"/>
					</td>
					<td align="right">小分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false"/>
					</td>
					<td align="right">明细类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false"/>
					</td>
					<td align="right">细分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false"/>
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
								    url:'businessComBox_procurementDepartments_5.html'"/>
					</td>
					<td align="right">是否定量：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="netWeight" id="netWeight" style="width: 124px;" 
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
								    url:'businessComBox_purchaseType_5.html'"/>
					</td>
					<td align="right">销售方式：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="saleType" id="saleType" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'masterDataType_listSaleType_5.html'"/>
					</td>
				</tr>
				<tr>
					<td align="right">报价币种：</td>
					<td>
					    <input class="easyui-combobox filterSelect" filterName="quotedCurrency" id="quotedCurrency" style="width: 124px;" 
									data-options="
										valueField:'id',
										textField:'text',
										editable:false,
									    url:'businessComBox_quotedCurrencyList_5.html'"/>
					</td>
					<td align="right">核算/投料表新建日期：</td>
					<td>
					    <input class="easyui-datebox" filterName="minCreateDate" name="minCreateDate" id="minCreateDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" /> 
					</td>
					<td align="right">至：</td>
					<td colspan="3">
						<input class="easyui-datebox" filterName="maxCreateDate" name="maxCreateDate" id="maxCreateDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" />
					</td>
				</tr>
				<tr>
					<td></td><td></td>
					<td  align="right">OA审批通过日期：</td>
					<td>
						<input class="easyui-datebox" filterName="minApproveDate" name="minApproveDate" id="minApproveDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;"/>
					</td>
					<td  align="right">至：</td>
					<td>
						<input class="easyui-datebox" filterName="maxApproveDate" name="maxApproveDate" id="maxApproveDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;"/>
					</td>
				</tr>
			</table>
			<div style="width:300px;" align="right">
		       <input type="checkbox" name="lastrecord"/>只搜索商品最后一次通过审批的价格记录</br>
		      </div>
			</form>
		<div style="width:200px;" align="right">
			<a id="search" onclick="addReferMerchandiseFn.search();" plain="true" class="easyui-linkbutton" data-options="iconCls:'find'"> 搜索 </a>
			<a id="clear" onclick="addReferMerchandiseFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a>
		</div>
	</div>
	
    <table  id="addReferMerchandise_grid"
			fit="true"
			selectOnCheck="true"
			checkOnSelect="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#addReferMerchandise_toolbar"
			url='totalCostAnalogyAnalysis_listAddReferMerchandise_2.html'
			data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',width:40,checkbox:true"></th>
				<th data-options="field:'merchandiseCode',width:80,sortable:true">商品编号</th>
				<th data-options="field:'merchandiseName',width:150,sortable:false">商品名称</th>
				<th data-options="field:'supplierCode',width:80,sortable:true">供应商编号</th>
				<th data-options="field:'supplierName',width:150,sortable:false">供应商名称</th>
				<th data-options="field:'merchandiseDxRoleName',width:100,sortable:false">商品定性角色</th>
				<th data-options="field:'merchandiseDlRoleName',width:100,sortable:false">商品定量角色</th>
				<th data-options="field:'purchaseDepartments',width:90,sortable:false">采购部门</th>
				<th data-options="field:'netWeight',width:90,sortable:false,sortable:false">是否定量装</th>
				<th data-options="field:'purchaseType',width:90,sortable:false">采购类型</th>
				<th data-options="field:'storageForm',width:90,sortable:false">销售方式</th>
				<th data-options="field:'centreTypeName',width:90,sortable:false">中分类</th>
				<th data-options="field:'smallTypeName',width:80,sortable:false">小分类</th>
				<th data-options="field:'detailTypeName',width:80,sortable:false">明细类</th>
				<th data-options="field:'fineTypeName',width:80,sortable:false">细分类</th>
				<th data-options="field:'applicationCode',width:120,sortable:true">SCO申请单号</th>
				<th data-options="field:'accountingCode',width:120,sortable:true">核算表/投料表编号</th>
				<th data-options="field:'sumPrice',width:90,sortable:true">总价</th>
				<th data-options="field:'contractPrice',width:90,sortable:true">合作价格</th>
				<th data-options="field:'oaApproveDate',width:150,sortable:true">审批通过日期</th>
				<th data-options="field:'oaContacts',width:100,sortable:false">审批申请人</th>
			</tr>
		</thead>
	</table>
</#compress>