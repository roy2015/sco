<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/purchaserData/relevanceAccessoryAndWebsite/relevanceAccessoryAndWebsite.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<div id="relAccAndWebsite_toolbar" style="margin:5px 5 5px 5;padding:2px">
		<form id="releAccWeb_search">
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
						<input class="easyui-validatebox" filterName="intentionCode" id="intentionCode" style="width:120px;" />
					</td>
					<td style="text-align:right;width:125px">商品名称：</td>
					<td style="width:125px;">
						<input class="easyui-validatebox" filterName="intentionName" id="intentionName" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">细分类：</td>
					<td>
						<select style="width:124px" id="fineTypeCodes" filterName="fineTypeCodes">
							<option value=""></option>
							<option value="ALL">所有</option>
							<#list accDetTypeList as l>
								<option value="${l.id}">${l.text}</option>
							</#list>
						</select>
					</td>
					<td style="text-align:right;">辅料原料名称：</td>
					<td>
						<input class="easyui-validatebox" filterName="accessoryName" id="accessoryName" style="width:120px;" />
					</td>
					<td style="text-align:right;">是否已关联公示网站原料：</td>
					<td colspan=3>
						<select style="width:124px" id="ifRelevanced" filterName="ifRelevanced">
							<option value=""></option>
							<option value="ALL">所有</option>
							<option value="N">未关联</option>
							<option value="Y">已关联</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a onclick="releAccAndWebFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" >
							搜索
						</a>
						<a onclick="releAccAndWebFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<a id="link" onclick="releAccAndWebFn.linkMaterialWebsite();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add',disabled:'true'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.relevanceAccessoryAndWebsite.add")>none</#if>;">
							关联辅料
						</a>
						<a id="remove" onclick="releAccAndWebFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.relevanceAccessoryAndWebsite.del")>none</#if>;">
							取消关联
						</a>
						<a onclick="releAccAndWebFn.exportToExcel();"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.relevanceAccessoryAndWebsite.export")>none</#if>;">
							导出Excel
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
    <table  id="releAccAndWeb_grid"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#relAccAndWebsite_toolbar"
			url='relevanceAccessoryAndWebsite_listRelevanceAccessoryAndWebsite_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th rowspan="2" data-options="field:'NULL',checkbox:'true'">
				<th rowspan="2" data-options="field:'intentionCode',width:150,sortable:true">
                	商品编号
                </th>
				<th rowspan="2" data-options="field:'intentionName',width:200">
                	商品名称
                </th>
				<th rowspan="2" data-options="field:'supplierCode',width:150,sortable:true,formatter:releAccAndWebFn.formatterSupplierCode">
                	供应商编号
                </th>
				<th rowspan="2" data-options="field:'supplierName',width:200,formatter:releAccAndWebFn.formatterSupplierName">
                	供应商名称
                </th>
				<th rowspan="2" data-options="field:'fineTypeName',width:150">
                	细分类
                </th>
				<th rowspan="2" data-options="field:'accessoryName',width:250">
                	辅料原料名称
                </th>
				<th rowspan="2" data-options="field:'weight',align:'right',width:100">
                	克重
                </th>
				<th rowspan="2" data-options="field:'websiteMaterialName',width:250">
                	公示网站原料名称
                </th>
				<th rowspan="2" data-options="field:'websiteName',width:250">
                	公示网站名称
                </th>
				<th rowspan="2" data-options="field:'websiteUrl',width:250,formatter:releAccAndWebFn.openWebUrl">
                	公示网站地址
                </th>
				<th rowspan="2" data-options="field:'priceRegion',width:100">
                	价格地区
                </th>
				<th rowspan="2" data-options="field:'materialBigTypeName',width:250">
                	原料大类
                </th>
				<th rowspan="2" data-options="field:'materialSmallTypeName',width:250">
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