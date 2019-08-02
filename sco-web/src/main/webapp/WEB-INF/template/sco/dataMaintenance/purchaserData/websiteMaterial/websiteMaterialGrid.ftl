<#compress>
<!DOCTYPE html>  
<html>  
	<head>  
	    <meta charset="UTF-8">  
	    <#include "inc/easyui.ftl" />
	    <script type="text/javascript" >
			<#include "sco/dataMaintenance/purchaserData/websiteMaterial/websiteMaterial.js" />
			<#include "sco/common/materialProperties.js" />
	    </script>
	    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
	</head>
	<body>
		<!-- 公示网站原料 维护主表 --> 
		<div id="webMaterial_toolbar" style="margin:5px 5 5px 5;padding:3px;overflow:auto;">
			<input type="hidden" id="showData" value="1" />
			<form id="webMat_search">
				<table style="width:682px;" border="0">
					<tr>
						<td style="text-align:right;width: 106px;">原料大类：</td>
						<td style="width: 50px;">
							<input class="filterSelect" filterName="materialBigTypeCode" id="materialBigTypeCode" style="width:124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									panelHeight:'220',
									editable:false,
									url:'materialProperties_listMaterialBigType_5.html'
						    " />
						</td>
						<td style="text-align:right;width: 80px;">原料小类：</td>
						<td style="width: 70px;">
							<input class="filterSelect" filterName="materialSmallTypeCode" id="materialSmallTypeCode" style="width:124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									panelHeight:'220',
									editable:false 
						    " />
						</td>
						<td style="text-align:right;width:130px">公示网站原料名称：</td>
						<td>
							<input class="easyui-combobox filterSelect" filterName="websiteMaterialName" id="websiteMaterialName" style="width:124px;" 
								data-options="
									valueField:'text',
									textField:'text',
									panelHeight:'220',
									editable:false
						    " />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">公示网站名称：</td>
						<td>
							<input class="easyui-validatebox" filterName="websiteName" id="websiteName" style="width:120px;" />
						</td>
						<td style="text-align:right;">价格地区：</td>
						<td>
							<input class="easyui-validatebox" filterName="priceRegion" id="priceRegion" style="width:120px;" />
						</td>
						<td colspan="2" id="cd" style="text-align:right;">录入人：
							<input class="easyui-validatebox" filterName="createby" id="createby" style="width:120px;" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">
							价格时间范围：
						</td>
						<td>
							<input class="easyui-datebox list-input" filterName="priceDate" id="minPriceDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;"/>
						</td>
						<td style="text-align:right;">
							至：
						</td>
						<td colspan="3">
							<input class="easyui-datebox list-input" filterName="priceEndDate" id="maxPriceDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="8">
							&nbsp;
							<a onclick="webMaterialFn.showExistsData();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.websiteMaterial")>none</#if>;">
								查看已有数据
							</a>
							<a onclick="webMaterialFn.showNotExistsData();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.websiteMaterial")>none</#if>;">
								查看缺少数据
							</a>

							&nbsp;&nbsp;&nbsp;&nbsp;
							<a onclick="webMaterialFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
								清空查询
							</a>
							<a id="add" onclick="webMaterialFn.add();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.websiteMaterial.add")>none</#if>;">
								录入公示网站数据
							</a>
							<a id="remove" title="只可删除已有数据" onclick="webMaterialFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.websiteMaterial.del")>none</#if>;">
								删除记录
							</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<#-- 已存在的数据 -->
	    <table id="existsMaterial_grid"
			    fit="true"
			    iconCls= "icon-save"
				pagination = "true"
				pagePosition = 'bottom'
				pageSize = "20"
				pageList = "[ 10, 20, 30, 40 ]"
				toolbar="#webMaterial_toolbar"
			    data-options="rownumbers:true">  
	        <thead>
	        	<tr>
					<th rowspan="2" data-options="field:'materialCode',checkbox:true"></th>
					<th rowspan="2" data-options="field:'materialBigTypeName',width:90">
	                	原料大类
	                </th>
					<th rowspan="2" data-options="field:'materialSmallTypeName',width:100">
	                	原料小类
	                </th>
					<th rowspan="2" data-options="field:'websiteMaterialName',width:200">
	                	公示网站原料名称
	                </th>
					<th rowspan="2" data-options="field:'websiteName',width:200">
	                	公示网站名称
	                </th>
					<th rowspan="2" data-options="field:'priceRegion',width:150">
	                	价格地区
	                </th>
					<th rowspan="2" data-options="field:'websiteUrl',width:250,formatter:webMaterialFn.openWebUrl">
	                	公示网站地址
	                </th>
					<th rowspan="2" data-options="field:'priceDate',width:90,sortable:true">
	                	价格日期
	                </th>
					<th rowspan="2" data-options="field:'price',width:75,align:'right'">
	                	价格(元/kg)
	                </th>
					<th rowspan="2" data-options="field:'createby',width:75">
	                	录入人
	                </th>
					<th data-options="field:'created',width:90">
	                	录入日期
	                </th>
	                <th rowspan="2" data-options="field:'month',width:75,sortable:true,hidden:true">
	                	无价格月份
	                </th>
	            </tr>
	        </thead>
	    </table>
	</body>
</html>
</#compress>