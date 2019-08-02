<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
     <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/accessoryIntentionCostAnalysis/totalCostAnalysis/totalCostAnalysisModel.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
</head>
<body>
	<!-- 辅料意向品管理主表 --> 
	<div id="totalcostanalysis_toolbar">
	 <form id="totalcostanalysis_search">
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
		   <td align="right">意向品创建时间:</td>
			<!-- <td colspan="3"><input class="easyui-datebox" filterName="intentionCreated" type="text" name="intentionCreated" id="intentionCreated" style="width:124px;" />
			      至<input class="easyui-datebox" filterName="intentionCreatedEnd" type="text" name="intentionCreatedEnd" id="intentionCreatedEnd" style="width:124px;" />
			</td> -->
			<td><input class="easyui-datebox" filterName="intentionCreated" type="text" name="intentionCreated" id="intentionCreated" style="width:124px;"data-options="required:false,editable:false" /></td>
			<td align="right">至:</td>
		 		    <td>
		 			<input class="easyui-datebox"  filterName="intentionCreatedEnd"   id="intentionCreatedEnd"  data-options="required:false,editable:false" style="width:124px;"/>
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
	   <tr>
			 <td align="right">询价单号:</td>
			<td>
				<input class="easyui-validatebox" filterName="enquiryCode" type="text" name="enquiryCode" id="enquiryCode" style="width:120px;" />
			</td>
		   <td align="right">询价单创建时间:</td>
			<td ><input class="easyui-datebox" filterName="enquiryCreated" type="text" name="enquiryCreated" id="enquiryCreated" style="width:124px;" data-options="required:false,editable:false"/></td>
			<td align="right">
			      至:</td>
			      <td><input class="easyui-datebox" filterName="enquiryCreatedEnd" type="text" name="enquiryCreatedEnd" id="enquiryCreatedEnd" style="width:124px;" data-options="required:false,editable:false"/>
			</td>
	   </tr>
	  
	</table>
	</form>	
	<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
		<a onclick="totalcostanalysisFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				搜索
		</a>
		<a onclick="totalcostanalysisFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			清空查询
		</a>
		<a id="insert" onclick="totalcostanalysisFn.showTotalCostAnalysis();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.accessoryIntentionCostAnalysis.totalCostAnalysis")>none</#if>;">
			总价分析
		</a>
		
	</table>	
</div>

<table  id="totalcostanalysisGrid"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40,50 ]"
			toolbar="#totalcostanalysis_toolbar"
			url='totalcostanalysis_listTotalcostanalysisIntentionApplication_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th  data-options="width:40,checkbox:true">
                	
                </th>
				<th data-options="field:'intentionCode',width:120,sortable:true">
                	<span class="txtCenter">意向品编号</span>
                </th>
				<th data-options="field:'intentionName',width:150,sortable:true">
                	<span class="txtCenter">意向品名称</span>
                </th>
				<th data-options="field:'intentionSupplierCode',width:150,sortable:true,formatter:function(value,row){
																				 if(row.supplierCode == null) {
																					return row.intentionSupplierCode;
																				 }else {
																					return row.supplierCode;
																				 }
																				}">
                	<span class="txtCenter">供应商/意向供应商编号</span>
                </th>
                <th data-options="field:'supplierName',width:150,sortable:true,formatter:function(value,row){
																				 if(row.supplierName == null) {
																					return row.intentionSupplierName;
																				 }else {
																					return row.supplierName;
																				 }
																				}">
                	<span class="txtCenter">供应商/意向供应商名称</span>
                </th>
			
				<th data-options="field:'centreTypeName',width:80,sortable:true">
                	<span class="txtCenter">中分类</span>
                </th>
				<th data-options="field:'smallTypeName',width:80,sortable:true">
                	<span class="txtCenter">小分类</span>
                </th>
                <th data-options="field:'detailTypeName',width:80,sortable:true">
                	<span class="txtCenter">明细类</span>
                </th>
				<th data-options="field:'fineTypeName',width:80,sortable:true">
                	<span class="txtCenter">细分类</span>
                </th>
				<th data-options="field:'enquiryCode',width:100,sortable:true">
                	<span class="txtCenter">询价单号</span>
                </th>
                <th data-options="field:'enquiryName',width:100,sortable:true">
                	<span class="txtCenter">询价单名称</span>
                </th>
                <th data-options="field:'supplierCount',width:100,sortable:true,align:'right'">
                	<span class="txtCenter">报价供应商数量</span>
                </th>
			    <th data-options="field:'line',width:100,sortable:true,align:'right'">
                	<span class="txtCenter">供应商报价排行</span>
                </th>
                <th data-options="field:'enquiryCreated',width:100,sortable:true">
                	<span class="txtCenter">询价单创建日期</span>
                </th>
                <th data-options="field:'enquiryCreateby',width:100,sortable:true">
                	<span class="txtCenter">询价单创建人</span>
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>