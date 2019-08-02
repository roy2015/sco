<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
     <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/accessoryIntentionCostAnalysis/historicalQuoteSupplier/historicalQuoteSupplierModel.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
</head>
<body>
	<!-- 辅料意向品管理主表 --> 
	<div id="historicalQuoteSupplier_toolbar">
	 <form id="historicalQuoteSupplier_search">
		<table style="width: 950px;">
	  	<tr>
	  		<td align="right">供应商/意向供应商编号:</td>
			<td >
				<input class="easyui-validatebox" filterName="intentionSupplierCode" type="text" name="intentionSupplierCode" id="intentionSupplierCode" style="width:120px;" />
			</td>  
			<td align="right">供应商/意向供应商名称:</td>   
			<td>       
				<input class="easyui-validatebox" filterName="intentionSupplierName" type="text" name="intentionSupplierName" id="intentionSupplierName" style="width:120px;" />		  
			</td> 
	   </tr>
	  <tr>
			 <td align="right">意向品编号:</td>
			<td>
				<input class="easyui-validatebox" filterName="intentionCode" type="text" name="intentionCode" id="intentionCode" style="width:120px;" />
			</td>
			<td align="right">意向品名称:</td>
			<td>     
			    <input class="easyui-validatebox" filterName="intentionName" type="text" name="intentionName" id="intentionName" style="width:120px;" />
			</td>
		   <td align="right">意向品创建人:</td>
		   <td>
			 <input class="easyui-validatebox" filterName="intentionCreateby" type="text" name="intentionCreateby" id="intentionCreateby" style="width:120px;" />
		</td>
	   </tr>
	  	<tr>
		   <td align="right">中分类:</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="centreTypeCode" id="centreTypeCode" style="width: 124px;" 
						data-options="
						valueField:'id',
						textField:'text',
						editable:false,
						url:'masterDataType_listCentreType_5.html'">
				</input>
			</td>
			<td align="right">小分类:</td>
			<td>     
				<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
				</input>			
			</td>
			<td align="right">明细类:</td>
			<td>
				<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
				</input>			
			</td>  
			<td align="right">细分类:</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="fineTypeCodes" id="fineTypeCodes" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'accessoryIntention_listMinceType_5.html'
								    ">
				</input>
			</td>
	   </tr>
	  
	</table>
	</form>	
	
		<a onclick="historicalQuoteSupplierFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				搜索
		</a>
		<a onclick="historicalQuoteSupplierFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			清空查询
		</a>
		<a  onclick="historicalQuoteSupplierFn.showHistoricalQuoteSupplier();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.accessoryIntentionCostAnalysis.historicalQuoteSupplierGrid")>none</#if>;">
			供应商历史报价分析
		</a>
		
	
</div>

<table  id="historicalQuoteSupplierGrid"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#historicalQuoteSupplier_toolbar"
			url='historicalQuoteSupplier_listHistoricalQuoteSupplierIntentionApplication_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th  data-options="width:40,checkbox:true">
                	
                </th>
				<th data-options="field:'intentionSupplierCode',width:150,sortable:true,formatter:function(value,row){
																				 if(row.supplierCode == null) {
																					return row.intentionSupplierCode;
																				 }else {
																					return row.supplierCode;
																				 }
																				}">
                	<span>供应商/意向供应商编号</span>
                </th>
                <th data-options="field:'supplierName',width:180,sortable:true,formatter:function(value,row){
																				 if(row.supplierName == null) {
																					return row.intentionSupplierName;
																				 }else {
																					return row.supplierName;
																				 }
																				}">
                	<span>供应商/意向供应商名称</span>
                </th>
                <th data-options="field:'intentionCode',width:120,sortable:true">
                	<span>意向品编号</span>
                </th>
				<th data-options="field:'intentionName',width:180,sortable:true">
                	<span>意向品名称</span>
                </th>
				<th data-options="field:'centreTypeName',width:80,sortable:true">
                	<span>中分类</span>
                </th>
				<th data-options="field:'smallTypeName',width:80,sortable:true">
                	<span>小分类</span>
                </th>
                <th data-options="field:'detailTypeName',width:80,sortable:true">
                	<span>明细类</span>
                </th>
				<th data-options="field:'fineTypeName',width:80,sortable:true">
                	<span>细分类</span>
                </th>
				
				<th data-options="field:'intentionCreateby',width:100,sortable:true">
                	<span>意向品创建人</span>
                </th>
				
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>