<#compress>
<!DOCTYPE html>  
<html>  
	<head>  
	    <meta charset="UTF-8">  
	    <#include "inc/easyui.ftl" />
	    <script type="text/javascript" >
	   	 	<#include "sco/common/masterDataType.js" />
	    	<#include "sco/categoryanalysis/merchandisepricetrend/merchandisePriceTrendModel.js" />
	    </script>
	    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
	</head>

	<body>
		<#-- 工具栏 -->
		<div id="merchandisePriceTrend_toolbar" style="margin:5px 5 5px 5;padding:3px">
			<form id="merchandisePrice_search">
				<table style="width:820px;">
					<#-- 查询条件 -->
					<tr>
						<td style="text-align:right;width:80px">
							供应商编号:
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width:120px;" />
						</td>
						<td style="text-align:right;width:70px">
							供应商名称:
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" filterName="supplierName" id="supplierName" style="width:120px;" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;width:77px">
							商品编号:
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" filterName="merchandiseCode" id="merchandiseCode" style="width:120px;" />
						</td>
						<td style="text-align:right;width:77px">
							商品名称:
						</td>
						<td>
							<input class="easyui-validatebox" filterName="merchandiseName" id="merchandiseName" style="width: 120px;" />
						</td>
						<td style="text-align:right;width:79px;">商品定性角色:</td>
						<td>
							<input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									
									editable:false,
								    url:'merchandiseRole_listQualitative_5.html'
						    " />
						</td>
						<td style="text-align:right;width:79px;">商品定量角色:</td>
						<td>
							<input class="easyui-combobox filterSelect" filterName="dlRoleCode" id="dlRoleCode" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									
									editable:false,
								    url:'merchandiseRole_listQuantify_5.html'
						    " />
						</td>
					</tr>
					<tr>
					<td style="text-align:right;width:73px">中分类:</td>
						<td>
							<input class="easyui-combobox filterSelect" filterName="centreType" id="centreTypeCode" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									
									editable:false,
								    url:'masterDataType_listCentreType_5.html'
						    " />
						</td> 
						<td style="text-align:right;width:73px">小分类:</td>
						<td>
							<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallType" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									
									editable:false" 
							/>
						</td>
						<td style="text-align:right;width:73px">明细类:</td>
						<td>
							<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailType" style="width: 124px;"
									data-options="
										valueField:'id',
										textField:'text',
										
										editable:false" />
						</td>
						<td style="text-align: right; width: 73px">细分类:</td>
						<td><input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineType" style="width: 124px;"
									data-options="
									valueField:'id',
									textField:'text',
									editable:false" /></td>
					</tr>
					<tr>
						<td style="text-align: right; width: 77px">采购部门:</td>
						<td>
							<input class="easyui-combobox filterSelect" id="purchaseDepartments" name="purchaseDepartments" filterName="purchaseDepartments" style="width: 124px;"
								data-options="
								valueField:'id',
								textField:'text',
								editable:false,
								url:'businessComBox_procurementDepartments_5.html'" />
						</td>
						<td style="text-align:right;width:80px">
							<label style="color: red;" >*</label>地区:
						</td>
						<td>
							<input class="easyui-combobox filterSelect" id="regionCode" filterName="regionCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									
									editable:false,
									url:'masterDataType_listRegion_5.html'
							" />
							
						</td>
						<td style="text-align: right; width: 79px;">
							<label style="color: red;" >*</label>时间范围:
						</td>
						<td>
							<input class="easyui-datebox list-input" filterName="minDate" id="minDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" /> 
						</td>
						<td style="text-align: right;">
							至:
						</td>
						<td>
							<input class="easyui-datebox list-input" filterName="maxDate" id="maxDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" />
						</td>
					</tr>
					<tr>
						<td colspan="8">
							<input type="radio" checked="checked" value="1" id="rdOnlyDirect" name="opinionItem">只看直营门店数据
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" value="2" id="rdOnlyJoin" name="opinionItem">只看加盟门店数据
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" value="3" id="rdDirectAndJoin" name="opinionItem">直营与加盟门店数据均看
						</td>
					</tr>
					<#-- 查询按钮 -->
					<tr>
						<td colspan="9">
							<a onclick="merchandisePriceFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
								搜索
							</a>
							<a onclick="merchandisePriceFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
								清空查询
							</a>
							<a id="insert" onclick="merchandisePriceFn.searchPriceTrend();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search',disabled:'true'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.add")>none</#if>;">
								查看价格趋势
							</a>
	
						</td>
					</tr>
				</table>
			</form>
		</div>
		<#-- 数据表格 -->
	    <table  id="merchandisePrice_grid"
			    fit="true"
			    iconCls= "icon-save"
				pagination = "true"
				pagePosition = 'bottom'
				pageSize = "20"
				pageList = "[ 10, 20, 30, 40 ]"
			    url='merchandisePriceTrend_listMerchandiseData_2.html'
				toolbar="#merchandisePriceTrend_toolbar"
			    data-options="rownumbers:true">
        	<thead>  
        		<tr>  
        			<th rowspan="2" data-options="field:'ck',checkbox:true"></th>
					<th rowspan="2" data-options="field:'merchandiseCode',width:150,sortable:true">
	                	<span class="txtCenter">商品编号</span>
	                </th>
					<th rowspan="2" data-options="field:'merchandiseName',width:150,sortable:false">
	                	<span class="txtCenter">商品名称</span>
	                </th>
					<th rowspan="2" data-options="field:'dxRoleName',width:100,sortable:false">
	                	<span class="txtCenter">商品定性角色</span>
	                </th>
					<th rowspan="2" data-options="field:'dlRoleName',width:100,sortable:false">
	                	<span class="txtCenter">商品定量角色</span>
	                </th>
					<th rowspan="2" data-options="field:'centreName',width:100,sortable:false">
	                	<span class="txtCenter">中分类</span>
	                </th>
					<th rowspan="2" data-options="field:'smallName',width:100,sortable:false">
	                	<span class="txtCenter">小分类</span>
	                </th>
					<th rowspan="2" data-options="field:'detailName',width:100,sortable:false">
	                	<span class="txtCenter">明细类</span>
	                </th>
	                <th rowspan="2" data-options="field:'fineName',width:100,sortable:false">
	                	<span class="txtCenter">细分类</span>
	                </th>
					<th rowspan="2" data-options="field:'supplierCode',width:150,sortable:true">
	                	<span class="txtCenter">供应商编号</span>
	                </th>
					<th rowspan="2" data-options="field:'supplierName',width:150,sortable:false">
	                	<span class="txtCenter">供应商名称</span>
	                </th>
	            </tr>
	         </thead>
	    </table>
	</body>
</html>
</#compress>