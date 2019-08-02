<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
   	 <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/shareFunctionAnalysis/sellAnalysis/sellYearOnYearAnalysis/sellYearOnYearModel.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
</head>

<body>
	<div id="sellYearOnYear_toolbar">
		<form id="sellYearOnYear_search" >
			<table >
				<#-- 查询条件 -->
				<tr>
					<td style="text-align:right;width:125px">供应商编号：</td>
					<td style="width:125px">
						<input class="easyui-validatebox" filterName="supplierCode" name="supplierCode" id="supplierCode" style="width: 120px;" />
					</td>
					<td style="text-align:right;width:125px">供应商名称：</td>
					<td style="width:125px">
						<input class="easyui-validatebox" filterName="supplierName"  name="supplierName" id="supplierName" style="width: 120px;" />
					</td>
					<td style="width:125px"></td>
					<td style="width:125px"></td>
					<td style="width:125px"></td>
					<td style="width:125px"></td>
				</tr>
				<tr>
					<td align="right">商品编号：</td>
					<td>
						<input class="easyui-validatebox" filterName="merchandiseCode" name="merchandiseCode" id="merchandiseCode" style="width: 120px;" />
					</td>
					<td align="right">商品名称：</td>
					<td>
						<input class="easyui-validatebox" filterName="merchandiseName" name="merchandiseName" id="merchandiseName" style="width: 120px;" />
					</td>
					<td align="right">商品定性角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dxRoleCode" name="dxRoleCode" id="dxRoleCode" style="width: 124px;"
								data-options="
								valueField:'id',
								textField:'text',
								editable:false,
								url:'merchandiseRole_listQualitative_5.html'
					     "/>
					</td>
					<td align="right">商品定量角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dlRoleCode" name="dlRoleCode" id="dlRoleCode" style="width: 124px;"
								data-options="
								valueField:'id',
								textField:'text',
								editable:false,
								url:'merchandiseRole_listQuantify_5.html'
					    "/>
					</td>
				</tr>
				<tr>
					<td align="right">中分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="centreTypeCode" name="centreTypeCode" id="centreTypeCode" style="width: 124px;"
								data-options="
								valueField:'id',
								textField:'text',
								editable:false,
								url:'masterDataType_listCentreType_5.html'
						  "/>
					</td>
					<td align="right">小分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="smallTypeCode" name="smallTypeCode" id="smallTypeCode" style="width: 124px;"
								data-options="
								valueField:'id',
								textField:'text',
								editable:false"/>
					</td>
					<td align="right">明细类：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="detailTypeCode" name="detailTypeCode" id="detailTypeCode" style="width: 124px;"
								data-options="
								valueField:'id',
								textField:'text',
								editable:false"/>
					</td>
					<td align="right">细分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="fineTypeCode" name="fineTypeCode" id="fineTypeCode" style="width: 124px;"
								data-options="
								valueField:'id',
								textField:'text',
								editable:false"/>
				   </td>
				</tr>
				<tr>
					<td align="right">采购部门：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="purchaseDepartments" name="purchaseDepartments" id="purchaseDepartments" style="width: 124px;"
							data-options="
							valueField:'text',
							textField:'text',
							panelHeight:'auto',
							editable:false,
							url:'businessComBox_procurementDepartments_5.html'" />
					</td>
					<td align="right"><font color="red">*</font>地区：</td>
					<td>
						<input class="easyui-combobox" name="sellRegion" id="sellRegion" style="width: 124px;"
								data-options="
								valueField:'id',
								textField:'text',
								editable:false,
								url:'masterDataType_listRegion_5.html'"/>
					</td>
					<td align="right"><font color="red">*</font>时间范围：</td>
					<td >
					    <input class="easyui-datebox"  name="minDate" id="minDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMaxDateValue,formatter:yearFormatter"  style="width: 124px;" /> 
				    </td>
				    <td align="right">至：</td>
					<td >
					    <input class="easyui-datebox"  name="maxDate" id="maxDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMinDateValue,formatter:yearFormatter" style="width: 124px;" />
				    </td>
				</tr>
				<tr>
					<td align="right">查看同比年数：</td>
					<td>
						<input class="easyui-numberbox"  name="seeYear" id="seeYear" style="width: 120px;" data-options="min:1,max:3"/>
					</td>
					<td align="right">同比分析类型：</td>
					<td>
						<select class="easyui-combobox"  name="analysisType" id="analysisType" style="width: 124px;" data-options="panelHeight:'auto'">
								<option value="product">单品销售同比</option>
								<option value="detail">明细类销售同比</option>
								<option value="small">小分类销售同比</option>
								<option value="big">整体销售同比</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<input type="radio" id="gjz" name="rationed"  value="gjz" checked />只查非定量装商品
						<input type="radio" id="dlz" name="rationed" value="dlz"/>只查定量装商品
						<input type="radio" id="gjzAndDlz" name="rationed" value="gjzAndDlz"/>定量装和非定量装均看
					</td>
				</tr>
				<tr>
					<td  colspan="8">
						<input type="radio" name="directJoin" value="direct" checked />只看直营门店数据
						<input type="radio" name="directJoin" value="join"/>只看加盟门店数据
						<input type="radio" name="directJoin" value="all"/>直营与加盟门店数据均看
					</td>
				</tr>
		</table>
	</form>
	<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
		<tr>
			<a onclick="sellYearOnYearFn.search();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				搜索
			</a>
			<a onclick="sellYearOnYearFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
				${action.getText("common.button.clearSearches")}
			</a>
			<a onclick="sellYearOnYearFn.sellDetailReport();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				查看明细报表
			</a>
		</tr>
	</table>		
</div>
	<table  id="sellYearOnYear_Grid"
		    fit="true"
		    fitColumns="true"
		    singleSelect="true"
		    checkOnSelect="false"
	   		selectOnCheck="false"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#sellYearOnYear_toolbar"
			url='sellYearOnYear_listSellYearOnYear_2.html'
		    data-options="rownumbers:true"> 
		<thead>
			<tr>
				<th rowspan="2" data-options="field:'NULL',width:40,checkbox:true"></th>
				<th data-options="field:'merchandiseCode',width:100,sortable:true">
					<span>商品编号</span>
				</th>
				<th data-options="field:'merchandiseName',width:150,sortable:false">
					<span>商品名称</span>
				</th>
				<th data-options="field:'dxRoleName',width:100,sortable:false">
					<span>商品定性角色</span>
				</th>
				<th data-options="field:'dlRoleName',width:100,sortable:false">
					<span>商品定量角色</span>
				</th>
				<th data-options="field:'centreTypeCode',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.centreTypeName;
							}else{
								return value;
							}
						}">
					<span>中分类</span>
				</th>
				<th data-options="field:'smallTypeCode',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.smallTypeName;
							}else{
								return value;
							}
						}">
					<span>小分类</span>
				</th>
				<th data-options="field:'detailTypeCode',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.detailTypeName;
							}else{
								return value;
							}
						}">
					<span>明细类</span>
				</th>
				<th data-options="field:'fineTypeCode',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.fineTypeName;
							}else{
								return value;
							}
						}">
					<span>细分类</span>
				</th>
				<th data-options="field:'supplierCode',width:100,sortable:true">
					<span>供应商编号</span>
				</th>
				<th data-options="field:'supplierName',width:150,sortable:false">
					<span>供应商名称</span>
				</th>
			</tr>
		</thead>
	</table>
</body>
</html>
</#compress>