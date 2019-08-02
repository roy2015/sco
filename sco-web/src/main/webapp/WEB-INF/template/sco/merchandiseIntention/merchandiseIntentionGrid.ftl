<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
   	 <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/merchandiseIntention/merchandiseIntentionModel.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
</head>
<body>
	<!-- 商品意向品管理主表 --> 
<div id="merchandiseIntention_toolbar">
<form id="merchandiseIntention_search">
	<table >
	  	<tr>
	  		<td align="right">供应商/意向供应商编号：</td>
			<td >
				<input class="easyui-validatebox" filterName="intentionSupplierCode"  name="intentionSupplierCode" id="intentionSupplierCode" data-options="required:false,editable:false" style="width:120px;" />
			</td>  
			<td align="right">供应商/意向供应商名称：</td>   
			<td>       
				<input class="easyui-validatebox" filterName="intentionSupplierName"  name="intentionSupplierName" id="intentionSupplierName" data-options="required:false,editable:false" style="width:120px;" />		  
			</td> 
		    <td align="right">意向品编号：</td>
			<td>
				<input class="easyui-validatebox" filterName="intentionCode"  name="intentionCode" id="intentionCode" data-options="required:false,editable:false" style="width:120px;" />
			</td>
			<td align="right">意向品名称：</td>
			<td>     
			    <input class="easyui-validatebox" filterName="intentionName"  name="intentionName" id="intentionName" data-options="required:false,editable:false" style="width:120px;" />
			</td>
	   </tr>
	  <tr>
		   <td align="right">意向品创建时间：</td>
			<td>
				<input class="easyui-datebox filterInput" filterName="createDateStart" name="createDateStart" id="createDateStart" data-options="required:false,editable:false" style="width:124px;" />
			</td>
			<td align="right">至：</td>
			<td>
			      <input class="easyui-datebox filterInput" filterName="createDateEnd"  name="createDateEnd" id="createDateEnd" data-options="required:false,editable:false" style="width:124px;" />
			</td>
			<td align="right">试吃日期：</td>
			<td>
				<input class="easyui-datebox filterInput" filterName="eatDateStart"  name="eatDateStart" id="eatDateStart" data-options="required:false,editable:false" style="width:124px;" />
			</td> 
			<td align="right">至：</td>
			<td>
			    <input class="easyui-datebox filterInput" filterName="eatDateEnd"  name="eatDateEnd" id="eatDateEnd" data-options="required:false,editable:false" style="width:124px;" />		  
			</td>
	   </tr>
	  	<tr>
		   <td align="right">中分类：</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="centreTypeCode" name="centreTypeCode" id="centreTypeCodeElse" style="width:124px;" 
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
				<input class="easyui-combobox filterSelect" filterName="smallTypeCode" name="smallTypeCode" id="smallTypeCode"style="width:124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false">
				</input>			
			</td>
			<td align="right">明细类：</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="detailTypeCode" name="detailTypeCode" id="detailTypeCode" style="width:124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false">
				</input>			
			</td>  
			<td align="right">细分类：</td>   
			<td>       
				<input class="easyui-combobox filterSelect" filterName="fineTypeCode" name="fineTypeCode"  id="fineTypeCode"  style="width:124px;"
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
				<input class="easyui-combobox filterSelect" filterName="purchaseDepartments" name="purchaseDepartments" id="purchaseDepartments" style="width:124px;"
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
			    <input class="easyui-combobox filterSelect" filterName="rationed" name="rationed" id="rationed" style="width:124px;"
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
				<input class="easyui-combobox filterSelect" filterName="purchaseType" name="purchaseType" id="purchaseType" style="width:124px;"
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
				<input class="easyui-combobox filterSelect" filterName="saleType" name="saleType" id="saleType" style="width:124px;"
					data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'masterDataType_listSaleType_5.html'">
				</input>  
			</td> 
	   </tr>
	   <tr>
		   <td align="right">是否试吃通过：</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="isForetastePass" name="isForetastePass" id="isForetastePass" style="width:124px;"
					data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'businessComBox_foretasteType_5.html'">
				</input>
			</td>
	   </tr>
	</table>
</form>	
<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
		<tr>
			<a onclick="intentionFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				搜索
			</a>
			<a onclick="intentionFn.clear();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
				${action.getText("common.button.clearSearches")}
			</a>
			<a id="insert" onclick="intentionFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.merchandiseIntention.insert")>none</#if>;">
				${action.getText("common.button.add")}
			</a>
			<a id="remove" onclick="intentionFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.merchandiseIntention.delete")>none</#if>;">
				${action.getText("common.button.delete")}
			</a>
			<a onclick="intentionFn.export2Excel();"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">
				导出意向品列表
			</a>
			<a onclick="intentionFn.showCompareQuotedDtl();"plain="true" class="easyui-linkbutton" data-options="iconCls:'plant'">
				报价比价
			</a>
		</tr>
	</table>
</div>
    <table  id="intentionGrid"
		    fit="true"
		    singleSelect="true"
		    checkOnSelect="false"
	   		selectOnCheck="false"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#merchandiseIntention_toolbar"
			url='merchandiseIntention_listMerchandiseIntention_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th  rowspan="2" data-options="field:'NULL',width:40,checkbox:true"></th>   
				<th data-options="field:'intentionCode',width:120,sortable:true,
					formatter : function(value, rowData, rowIndex) {
					return '<a href=# style=color:maroon;cursor:hand; onclick=intentionFn.showIntentionDtl(\''+rowData.intentionCode+'\',\''+rowIndex+'\') >'+value+'</a>'
					}">
                	<span >意向品编号</span>
                </th>
				<th data-options="field:'intentionName',width:250,sortable:false">
                	<span >意向品名称</span>
                </th>
				<th data-options="field:'intentionSupplierCode',width:150,sortable:true">
                	<span >供应商/意向供应商编号</span>
                </th>
				<th data-options="field:'intentionSupplierName',width:250,sortable:false">
                	<span >供应商/意向供应商名称</span>
                </th>
				<th data-options="field:'merchandiseCode',width:150,sortable:true">
                	<span >SAP物料号</span>
                </th>
				<th data-options="field:'purchaseDepartments',width:110,sortable:false,formatter: function(value,row,index){
							if(value=='INLAND'){
								return '国内';
							}else if(value=='IMPORT'){
								return '国际';
							}else{
								return value;
							}
						}">
                	<span >采购部门</span>
                </th>
				<th data-options="field:'rationed',width:110,sortable:false,formatter: function(value,row,index){
							if(value=='GJZ'){
								return '公斤装';
							}else if(value=='DLZ'){
								return '定量装';
							}else{
								return value;
							}
						}">
                	<span >是否定量装</span>
                </th>
				<th data-options="field:'purchaseType',width:110,sortable:false,formatter: function(value,row,index){
							if(value=='ZC'){
								return '直采';
							}else if(value=='FZC'){
								return '非直采';
							}else{
								return value;
							}
						}">
                	<span >采购类型</span>
                </th>
				<th data-options="field:'saleType',width:110,sortable:false">
                	<span >销售方式</span>
                </th>
				<th data-options="field:'centreTypeName',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.centreTypeName;
							}else{
								return value;
							}
						}">
                	<span >中分类</span>
                </th>
				<th data-options="field:'smallTypeName',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.smallTypeName;
							}else{
								return value;
							}
						}">
                	<span >小分类</span>
                </th>
				<th data-options="field:'detailTypeName',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.detailTypeName;
							}else{
								return value;
							}
						}">
                	<span >明细类</span>
                </th>
				<th data-options="field:'fineTypeName',width:110,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.fineTypeName;
							}else{
								return value;
							}
						}">
                	<span >细分类</span>
                </th>
				<th rowspan="2" data-options="field:'created',width:140,sortable:true,formatter: function(value,row,index){
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
                	<span >意向品创建日期</span>
                </th>
                <th data-options="field:'createUserName',width:110,sortable:false">
                	<span >意向品创建人</span>
                </th>
				<th rowspan="2" data-options="field:'quotedDate',width:140,sortable:true,formatter: function(value,row,index){
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
                	<span >最后一次报价日期</span>
                </th>
				<th rowspan="2" data-options="field:'foretasteDate',width:140,sortable:true">
                	<span >最后一次试吃日期</span>
                </th>
				<th rowspan="2" data-options="field:'foretasteGrade',width:110,sortable:false,precision:2,align:'right',formatter:moneyFormatter">
                	<span style="right">最后一次试吃评分</span>
                </th>
                <th rowspan="2" data-options="field:'evaluate',width:200,sortable:false">
                	<span >最后一次试吃反馈</span>
                </th>
                 <th rowspan="2" data-options="field:'isForetastePass',width:80,sortable:true">
                	<span >是否试吃通过</span>
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>