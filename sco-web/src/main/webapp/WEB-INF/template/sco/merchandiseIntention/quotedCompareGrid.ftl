<#compress>
<script type="text/javascript" >
	<#include "sco/merchandiseIntention/quotedCompareGrid.js" />
</script>

<body>
	<input type="hidden"  name="intentionCode" id="intentionCode" value="${merchandiseIntention.intentionCode}" />
	<div>
	 	<table >
	 		<tr>
				<td>
					<a id="exportAllQuoted" onclick="quotedCompareFn.exportAllQuoted();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >导出Excel</a>
				</td>
				<td>
					<a id="showConvertUnitDialog" onclick="quotedCompareFn.showConvertUnitDialog();" plain="true" class="easyui-linkbutton" data-options="iconCls:'plant'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >分析已有报价记录</a>
				</td>
				<td>
					<a id="showHistoryQuoted" onclick="quotedCompareFn.showHistoryQuoted('');" plain="true" class="easyui-linkbutton" data-options="iconCls:'plant'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >查看供应商历史报价</a>
				</td>
	 		</tr>
	 	</table>
	 </div>
 	
 	 <table id="panel5QuotedGrid"
		     class="easyui-datagrid"
		     title="已有报价记录" 
		     singleSelect="true"
		     checkOnSelect="true"
	   		 selectOnCheck="true"
			 pagination = "true"
			 pagePosition = 'bottom'
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
			 fitColumns="true" 
		     nowrap="true"
			 toolbar="#panel5_QuotedToolbar"
			 url="merchandiseQuoted_listMerchandiseQuoted_2.html?intentionCode=<#if merchandiseIntention.intentionCode?? >${merchandiseIntention.intentionCode}<#else></#if>"
		     data-options="rownumbers:true"> 
		   
		     <thead>
		   		<tr>
		   			<th  data-options="field:'quotationCode'" hidden="true"></th>
		   			<th  rowspan="2" data-options="field:'NULL',checkbox:true"></th>
		   			<th data-options="field:'intentionSupplierCode',width:150,sortable:true,
		   				formatter:function(value,rowData,rowIndex){
			   				var intentionSupplierCode = '\''+rowData.intentionSupplierCode+'\'';
			   				return '<a href=# style=color:maroon;curson:hand; onclick=quotedCompareFn.showHistoryQuoted(' + intentionSupplierCode + ')>' + value + '</a>'
		   				}">
						<span >供应商编号</span>
					</th>
					<th  data-options="field:'intentionSupplierName',width:160">
						<span >供应商名称</span>
					</th>
					<th  rowspan="2" data-options="field:'lastContactsName',width:80">
						<span >联系人</span>
					</th>
					<th rowspan="2" data-options="field:'lastContactsPhone',width:100">
						<span >联系人电话</span>
					</th>
					<th rowspan="2" data-options="field:'quotedDate',width:80,sortable:true,formatter: function(value,row,index){
							var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 return  date.format('yyyy-MM-dd');
					          }else{
					          	return '';
					          }
						}">
						<span >报价日期</span>
					</th>
					<th rowspan="2" data-options="field:'price',width:100,align:'right',formatter:function(value){
						return moneyFormatter(value);
					}">
						<span style="right">报价价格</span>
					</th>
					<th rowspan="2" data-options="field:'quotationUnits',width:100,align:'right'">
						<span style="right">基本计量单位(kg)</span>
					</th>
					<th rowspan="2" data-options="field:'packingType',width:100">
						<span >包装方式</span>
					</th>
		   		</tr>
		   		<tr>
	                <th><input style="height:14px;" class="filterInput" filterName="intentionSupplierCode"/></th>  
	                <th><input style="height:14px;" class="filterInput" filterName="intentionSupplierName"/></th>  
           	   </tr>
		   	</thead>
	</table> 
	
	
	<#--搜索条件-->
	<div style="padding: 15px;"></div>
	<form id="panel5IntentionGrid_search">
		<table>
	  		<tr style="margin-top:10px">
		  		<td align="right" style="height:25px">供应商/意向供应商编号：</td>
				<td >
					<input class="easyui-validatebox" filterName="compareIntentionSupplierCode"  name="compareIntentionSupplierCode" id="compareIntentionSupplierCode" data-options="required:false,editable:false" style="width:120px;"/>
				</td>  
				<td align="right">供应商/意向供应商名称：</td>   
				<td>       
					<input class="easyui-validatebox" filterName="compareIntentionSupplierName" name="compareIntentionSupplierName" id="compareIntentionSupplierName" data-options="required:false,editable:false" style="width:120px;"/>		  
				</td> 
			    <td align="right">意向品/商品编号：</td>
				<td>
					<input class="easyui-validatebox" filterName="compareIntentionCode" name="compareIntentionCode" id="compareIntentionCode"  data-options="required:false,editable:false" style="width:120px;"/>
				</td>
				<td align="right">意向品/商品名称：</td>
				<td>     
				    <input class="easyui-validatebox" filterName="compareIntentionName" name="compareIntentionName" id="compareIntentionName" data-options="required:false,editable:false" style="width:120px;"/>
				</td>
			</tr>
			<tr style="margin-top:10px">
				<td align="right" style="height:25px">意向品创建时间：</td>
				<td>
					<input class="easyui-datebox filterInput" filterName="compareCreateDateStart" name="compareCreateDateStart" id="compareCreateDateStart" data-options="required:false,editable:false" style="width:124px;"/>
				</td>
				<td align="right">至：</td>
				<td>
				      <input class="easyui-datebox filterInput" filterName="compareCreateDateEnd" name="compareCreateDateEnd" id="compareCreateDateEnd" data-options="required:false,editable:false" style="width:124px;"/>
				</td>
			</tr>
			<tr style="margin-top:10px">
		   		<td align="right" style="height:25px">中分类：</td>
				<td>
					<input class="easyui-combobox filterSelect" filterName="compareCentreTypeCode" name="compareCentreTypeCode" id="compareCentreTypeCode" style="width:124px;" 
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
				    <input class="easyui-combobox filterSelect" filterName="compareSmallTypeCode" name="compareSmallTypeCode"  id="compareSmallTypeCode" style="width:124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
					</input>	
				</td>
				<td align="right">明细类：</td>
				<td>
					<input class="easyui-combobox filterSelect" filterName="compareDetailTypeCode" name="compareDetailTypeCode" id="compareDetailTypeCode" style="width:124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
					</input>
				</td>  
				<td align="right">细分类：</td>   
				<td>       
					<input class="easyui-combobox filterSelect" filterName="compareFineTypeCode" name="compareFineTypeCode" id="compareFineTypeCode" style="width:124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
					</input>
				</td> 
			</tr>
			<tr style="margin-top:10px">
			   	<td align="right" style="height:25px">采购部门：</td>
				<td>
					<input class="easyui-combobox filterSelect" filterName="comparePurchaseDepartments" name="comparePurchaseDepartments" id="comparePurchaseDepartments"  style="width:124px;"
						data-options="
						valueField:'id',
						textField:'text',
						panelHeight:'auto',
						editable:false,
						url:'businessComBox_procurementDepartments_5.html'">
					</input>
				</td>
				<td align="right">是否定量：</td>
				<td>     
					<input class="easyui-combobox filterSelect" filterName="compareRationed" name="compareRationed" id="compareRationed" style="width:124px;"
						data-options="
						valueField:'id',
						textField:'text',
						panelHeight:'auto',
						editable:false,
						url:'businessComBox_rationed_5.html'">
					</input>
				</td>
				<td align="right">采购类型：</td>
				<td>
					<input class="easyui-combobox filterSelect" filterName="comparePurchaseType" name="comparePurchaseType" id="comparePurchaseType"  style="width:124px;"
						data-options="
						valueField:'id',
						textField:'text',
						panelHeight:'auto',
						editable:false,
						url:'businessComBox_purchaseType_5.html'">
					</input>
				</td>  
				<td align="right">销售方式：</td>   
				<td>       
					<input class="easyui-combobox filterSelect" filterName="compareSaleType" name="compareSaleType" id="compareSaleType" style="width:124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false,
						url:'masterDataType_listSaleType_5.html'">
					</input>   
				</td> 
			</tr>
		  	<tr style="margin-top:10px">
				<td colspan="8" style="height:25px">
					<label><font color="red">*</font><input type="radio" style="width:20px;" id="somePart" name="somePart" value="1" />只搜索已引进商品</label>
					<label><input type="radio" style="width:20px;" id="somePart1" name="somePart" value="2" />只搜索未引进商品</label>
					<label><input type="radio" style="width:20px;" id="somePart2" name="somePart" value="3" />同时搜索已引进和未引进商品</label>
				</td>
		   </tr>
		</table>
	</form>	
	<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
		<tr>
			<a onclick="timelinerFn.searchCompare();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				搜索
			</a>
			<a onclick="timelinerFn.clearCompare();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
				${action.getText("common.button.clearSearches")}
			</a>
			<a onclick="quotedCompareFn.showCompareResultDialog();"plain="true" class="easyui-linkbutton" data-options="iconCls:'plant'">
				查看对比商品
			</a>
		</tr>
	</table>
	
	<table  id="panel5IntentionGrid"
		    title="对比商品列表" 
		    style="height:250px"
		    singleSelect="true"
		    checkOnSelect="false"
	   		selectOnCheck="false"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "5"
			pageList = "[ 5, 10, 15, 20 ]"
			toolbar="#panel5MerchandiseIntention_toolbar"
			url="quotedCompare_listCompareMerchandise_2.html?currentIntentionCode=<#if merchandiseIntention.intentionCode?? >${merchandiseIntention.intentionCode}<#else></#if>"
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th  rowspan="2" data-options="field:'isTrueMerchandise',width:40,checkbox:true"></th>   
				<th data-options="field:'intentionCode',width:140,sortable:true">
                	<span >意向品/商品编号</span>
                </th>
				<th data-options="field:'intentionName',width:200,sortable:false">
                	<span >意向品/商品名称</span>
                </th>
                <th data-options="field:'intentionSupplierCode',width:140,sortable:true">
                	<span >供应商/意向供应商编号</span>
                </th>
				<th data-options="field:'intentionSupplierName',width:200,sortable:false">
                	<span >供应商/意向供应商名称</span>
                </th>
				<th data-options="field:'purchaseDepartments',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.purchaseDepartmentsName;
							}else{
								return value;
							}
						}">
                	<span >采购部门</span>
                </th>
				<th data-options="field:'rationed',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.rationedName;
							}else{
								return value;
							}
						}">
                	<span >是否定量装</span>
                </th>
				<th data-options="field:'purchaseType',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.purchaseTypeName;
							}else{
								return value;
							}
						}">
                	<span >采购类型</span>
                </th>
				<th data-options="field:'saleType',width:110,sortable:false">
                	<span >销售方式</span>
                </th>
				<th data-options="field:'centreTypeCode',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.centreTypeName;
							}else{
								return value;
							}
						}">
                	<span >中分类</span>
                </th>
				<th data-options="field:'smallTypeCode',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.smallTypeName;
							}else{
								return value;
							}
						}">
                	<span >小分类</span>
                </th>
				<th data-options="field:'detailTypeCode',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.detailTypeName;
							}else{
								return value;
							}
						}">
                	<span >明细类</span>
                </th>
				<th data-options="field:'fineTypeCode',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.fineTypeName;
							}else{
								return value;
							}
						}">
                	<span >细分类</span>
                </th>
            </tr>
        </thead>
    </table>
</body>	
</#compress>