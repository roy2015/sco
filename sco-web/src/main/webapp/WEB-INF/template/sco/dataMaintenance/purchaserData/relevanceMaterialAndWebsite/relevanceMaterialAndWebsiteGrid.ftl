<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/purchaserData/relevanceMaterialAndWebsite/relevanceMaterialAndWebsite.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<div id="relevanceMaterialAndWebsite_toolbar" style="margin:5px 5 5px 5;padding:5px">
		<form id="releMatWeb_search">
			<table class="table table-condensed" style="width:1000px;table-layout:fixed;">
				<tr>
					<td style="text-align:right;width:125px">供应商编号：</td>
					<td style="width:125px;">
						<input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width:120px;" />
					</td>
					<td style="text-align:right;width:125px">供应商名称：</td>
					<td style="width:125px;">
							<input class="easyui-validatebox" filterName="supplierName" id="supplierName" style="width:120px;" />
						</td>
					<td style="text-align:right;width:145px">商品编号：</td>
					<td style="width:125px;">
						<input class="easyui-validatebox" filterName="merchandiseCode" id="merchandiseCode" style="width:120px;" />
					</td>
					<td style="text-align:right;width:125px">商品名称：</td>
					<td style="width:125px;">
						<input class="easyui-validatebox" filterName="merchandiseName" id="merchandiseName" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">商品定性角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'merchandiseRole_listQualitative_5.html'
					    " />
					</td>
					<td style="text-align:right;">商品定量角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dlRoleCode" id="dlRoleCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'merchandiseRole_listQuantify_5.html'
					    " />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;width:73px">中分类：</td>
					<td>
						<input class="filterSelect" filterName="centreType" id="centreTypeCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " />
					</td> 
					<td style="text-align:right;width:73px">小分类：</td>
					<td>
						<input class="filterSelect" id="smallTypeCode" filterName="smallType" style="width: 124px;"
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false" 
						/>
					</td>
					<td style="text-align:right;width:73px">明细类：</td>
					<td>
						<input class="filterSelect" id="detailTypeCode" filterName="detailType" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									panelHeight:'220',
									editable:false" />
					</td>
					<td style="text-align:right;width:73px">细分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineType" style="width: 124px;"
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false" 
						/>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">商品原料类型：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="materialType" id="materialType" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'relevanceMaterialAndWebsite_listMerchandiseMaterialType_5.html'
					    " />
					</td>
					<td style="text-align:right;">商品原料名称：</td>
					<td>
						<input class="easyui-validatebox" filterName="ingredientCodeName" id="ingredientCodeName" style="width:120px;" />
					</td>
					<td style="text-align:right;">是否已关联公示网站原料：
					<td colspan="2">
						<select style="width:124px" id="ifRelevanced" filterName="ifRelevanced">
							<option value=""></option>
							<option value="ALL">所有</option>
							<option value="Y">已关联</option>
							<option value="N">未关联</option>
							<option value="NOT">暂不关联</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">公示网站原料名称：</td>
					<td>
						<input class="easyui-validatebox" filterName="materialName" id="materialName" style="width:120px;" />
					</td>
					<td style="text-align:right;">公示网站名称：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="websiteCode" id="websiteCode" style="width:124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
								url:'websiteNameMaintenance_listWebsiteNameMaintenanceInfo_5.html'
					    " />
					</td>
					<td style="text-align:right;">价格地区：</td>
					<td>
						<input class="easyui-validatebox" filterName="priceRegion" id="priceRegion" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">原料大类：</td>
					<td>
						<input class="easyui-validatebox" filterName="materialBigTypeName" id="materialBigTypeName" style="width:120px;" />
					</td>
					<td style="text-align:right;">原料小类：</td>
					<td>
						<input class="easyui-validatebox" filterName="materialSmallTypeName" id="materialSmallTypeName" style="width:120px;" />
					</td>
					<td style="text-align:right;">关联人：</td>
					<td colspan="3">
						<input class="easyui-validatebox" filterName="createby" id="createby" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a onclick="releMateAndWebFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" >
							搜索
						</a>
						<a onclick="releMateAndWebFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<a id="link" onclick="releMateAndWebFn.linkMaterialWebsite();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add',disabled:'true'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite.add")>none</#if>;">
							关联原料
						</a>
						<a id="remove" onclick="releMateAndWebFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite.del")>none</#if>;">
							取消关联
						</a>
						<a id="notLink" onclick="releMateAndWebFn.notLink();"plain="true" class="easyui-linkbutton" data-options="iconCls:'receive',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite.notLink")>none</#if>;">
							暂不关联
						</a>
						<a onclick="releMateAndWebFn.exportToExcel();"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite.export")>none</#if>;">
							导出Excel
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
    <table  id="releMatelAndWeb_grid"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#relevanceMaterialAndWebsite_toolbar"
			url='relevanceMaterialAndWebsite_listRelevanceMaterialAndWebsite_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th rowspan="2" data-options="field:'NULL',checkbox:'true'">
				<th rowspan="2" data-options="field:'merchandiseCode',width:140,sortable:true">
                	商品编号
                </th>
				<th rowspan="2" data-options="field:'merchandiseName',width:260">
                	商品名称
                </th>
				<th rowspan="2" data-options="field:'supplierCode',width:140,sortable:true">
                	供应商编号
                </th>
				<th rowspan="2" data-options="field:'supplierName',width:220">
                	供应商名称
                </th>
				<th rowspan="2" data-options="field:'ifRelevanced',width:170,formatter:releMateAndWebFn.fomatLink">
					是否已关联公示网站原料
                </th>
				<th rowspan="2" data-options="field:'inputCost',align:'right',width:120,formatter:releMateAndWebFn.fomatPercentWithoutMul">
					投入成本占比
                </th>
				<th rowspan="2" data-options="field:'dxRoleName',width:200">
					商品定性角色
                </th>
				<th rowspan="2" data-options="field:'dlRoleName',width:200">
					商品定量角色
                </th>
				<th rowspan="2" data-options="field:'centreName',width:200">
                	中分类
                </th>
				<th rowspan="2" data-options="field:'smallName',width:200">
                	小分类
                </th>
				<th rowspan="2" data-options="field:'detailName',width:200">
                	明细类
                </th>
				<th rowspan="2" data-options="field:'fineName',width:200">
                	细分类
                </th>
				<th rowspan="2" data-options="field:'materialTypeName',width:120">
                	商品原料类型
                </th>
				<th rowspan="2" data-options="field:'ingredientCodeName',width:300">
                	商品原料名称
                </th>
				<th rowspan="2" data-options="field:'materialName',width:330">
                	公示网站公示网站原料名称
                </th>
				<th rowspan="2" data-options="field:'websiteName',width:330">
                	公示网站名称
                </th>
				<th rowspan="2" data-options="field:'websiteUrl',width:350,formatter:releMateAndWebFn.openWebUrl">
                	公示网站地址
                </th>
				<th rowspan="2" data-options="field:'priceRegion',width:200">
                	价格地区
                </th>
				<th rowspan="2" data-options="field:'materialBigTypeName',width:180">
                	原料大类
                </th>
				<th rowspan="2" data-options="field:'materialSmallTypeName',width:180">
                	原料小类
                </th>
				<th rowspan="2" data-options="field:'createby',width:100">
                	关联人
                </th>
				<th rowspan="2" data-options="field:'created',width:100,sortable:true">
                	关联时间
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>