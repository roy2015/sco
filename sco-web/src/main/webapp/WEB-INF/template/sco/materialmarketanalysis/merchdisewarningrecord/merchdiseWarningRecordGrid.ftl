<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/common/masterDataType.js" />
		<#include "sco/common/materialProperties.js" />
		<#include "sco/materialmarketanalysis/merchdisewarningrecord/merchdiseWarningRecord.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<div id="merWarnRec_toolbar" style="padding:1px;overflow:auto;">
		<form id="merWarnRec_search" method="post">
			<table style="width:900px;" border="0">
				<tr>
					<td style="text-align:right;width:100px">供应商编号：</td>
					<td style="width:110px;">
						<input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width:135px;" />
					</td>
					<td style="text-align:right;width:90px">供应商名称：</td>
					<td style="width:110px;">
							<input class="easyui-validatebox" filterName="supplierName" id="supplierName" style="width:120px;" />
						</td>
					<td style="text-align:right;width:77px">商品编号：</td>
					<td style="width:110px;">
						<input class="easyui-validatebox" filterName="merchandiseCode" id="merchandiseCode" style="width:120px;" />
					</td>
					<td style="text-align:right;width:77px">商品名称：</td>
					<td>
						<input class="easyui-validatebox" filterName="merchandiseName" id="merchandiseName" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">商品定性角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width: 139px;" 
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
						<input class="filterSelect" id="centreTypeCode" filterName="centreType" style="width: 139px;" 
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
						<input class="easyui-combobox filterSelect" id="materialType" filterName="materialType" style="width: 139px;" 
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
						<input class="easyui-validatebox" id="ingredientCodeName" filterName="ingredientCodeName" style="width:120px;" />
					</td>
					<td style="text-align: right;">原料大类：</td>
					<td>
						<input class="filterSelect" filterName="materialBigTypeCode" id="materialBigTypeCode" style="width: 124px;"
							data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'220',
							editable:false,
							url:'materialProperties_listMaterialBigType_5.html'
					    " />
					</td>
					<td style="text-align: right; width: 80px">原料小类：</td>
					<td><input class="easyui-combobox filterSelect" filterName="materialSmallTypeCode" id="materialSmallTypeCode" style="width: 124px;"
						data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'220',
							editable:false 
				    	" />
				    </td>
				</tr>
				<tr>
					<td style="text-align: right; width: 130px;">公示网站原料名称：</td>
					<td>
						<input class="easyui-validatebox" id="materialCode" filterName="materialCode" style="width:135px;" />
					</td>
					<td style="text-align: right; width: 100px">公示网站名称：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="websiteCode" name="websiteCode" id="webName" style="width:124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
								url:'websiteNameMaintenance_listWebsiteNameMaintenanceInfo_5.html'
					    " />
				   	</td>
				   	<td style="text-align:right;">地区：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="priceRegion" name="priceRegion" id="priceRegion" style="width:124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
								url:'merWarningRec_listRegion_5.html'
					    " />
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<span style="padding-left: 20px;"></span>
						<font color="red">*</font>
						<label>
							<input type="radio" filterName="netWeightType" name="netWeightType" onclick="merWarnRecFn.showRecord('warn')" />只看预警记录
						</label>
						<span style="padding-left: 39px;"></span>
						<span id="warn" style="visibility: hidden;">
							预警日期：
							<input class="easyui-datebox list-input" id="minwarnYear" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue,formatter:formatDateToMonth,onSelect:function(date){merWarnRecFn.formateDateToMonth(this,date)}" style="width: 124px;" />
							至：
							<input class="easyui-datebox list-input" id="maxwarnYear" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue,formatter:formatDateToMonth" style="width: 124px;" />
						</span>
						<br />
						
						<span style="padding-left: 29px;"></span>
						<label><input type="radio" name="netWeightType" onclick="merWarnRecFn.showRecord('unWarn')" />只看非预警记录</label>
						<span style="padding-left: 27px;"></span>
						<span id="unWarn" style="visibility: hidden;">
							试算日期：
							<input class="easyui-datebox list-input" id="minunWarnYear" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue,formatter:formatDateToMonth,onSelect:function(date){merWarnRecFn.formateDateToMonth(this,date)}" style="width: 124px;" />
							至：
							<input class="easyui-datebox list-input" id="maxunWarnYear" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue,formatter:formatDateToMonth" style="width: 124px;" />
						</span>
						<br />
						
						<span style="padding-left: 29px;"></span>
						<label><input type="radio" name="netWeightType" onclick="merWarnRecFn.showRecord('allWarn')"/>查看所有记录</label>
						<span style="padding-left: 39px;"></span>
						<span id="allWarn" style="visibility: hidden;">
							试算日期：
							<input class="easyui-datebox list-input" id="minallWarnYear" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue,formatter:formatDateToMonth,onSelect:function(date){merWarnRecFn.formateDateToMonth(this,date)}" style="width: 124px;" />
							至：
							<input class="easyui-datebox list-input" id="maxallWarnYear" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue,formatter:formatDateToMonth" style="width: 124px;" />
						</span>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a onclick="merWarnRecFn.searchMerWarnRec();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.materialmarketAnalysis.merchmatesuppquote.listMerMat")>none</#if>;">
							搜索商品原料
						</a>
						<a onclick="merWarnRecFn.clearSearch();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
    <table  id="merWarnRec_grid"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#merWarnRec_toolbar"
			url='merWarningRec_listMerchdiseWarningRecord_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
				<th rowspan="2" data-options="field:'created',width:100">
                	预警日期
                </th>
				<th rowspan="2" data-options="field:'merchandiseCode',width:150">
                	商品编号
                </th>
				<th rowspan="2" data-options="field:'merchandiseName',width:220">
                	商品名称
                </th>
				<th rowspan="2" data-options="field:'supplierCode',width:150">
                	供应商编号
                </th>
				<th rowspan="2" data-options="field:'supplierName',width:220">
                	供应商名称
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
				<th rowspan="2" data-options="field:'materialName',width:200">
                	公示网站原料名称
                </th>
				<th rowspan="2" data-options="field:'websiteName',width:200">
                	公示网站名称
                </th>
				<th rowspan="2" data-options="field:'regionName',width:120">
                	地区
                </th>
				<th rowspan="2" data-options="field:'materialBigTypeName',width:200">
                	原料大类
                </th>
				<th rowspan="2" data-options="field:'materialSmallTypeName',width:200">
                	原料小类
                </th>
				<th rowspan="2" data-options="field:'ingredientCodeName',width:200">
                	商品原料名称
                </th>
				<th rowspan="2" data-options="field:'materialTypeName',width:90">
                	商品原料类别
                </th>
				<th rowspan="2" data-options="field:'merPurPrice',width:90,align:'right'">
                	商品进价
                </th>
				<th rowspan="2" data-options="field:'suppMatePurPrice',width:120,align:'right'">
                	供应商原料采购价
                </th>
				<th rowspan="2" data-options="field:'inputCost',width:90,align:'right',formatter:merWarnRecFn.formatPercent">
                	投料占比
                </th>
				<th rowspan="2" data-options="field:'suppMateInputPrice',width:120,align:'right'">
                	供应商原料投入价
                </th>
				<th rowspan="2" data-options="field:'websiteMatePrice',width:130,align:'right'">
                	公示网站原料行情原价
                </th>
				<th rowspan="2" data-options="field:'websiteLastMatePrice',width:140,align:'right'">
                	上月公示网站原料行情价
                </th>
				<th rowspan="2" data-options="field:'suppMateInputTest',width:130,align:'right'">
                	供应商原料投入价试算
                </th>
				<th rowspan="2" data-options="field:'merPurTest',width:120,align:'right'">
                	商品进价试算
                </th>
				<th rowspan="2" data-options="field:'merPurIncrease',width:120,align:'right'">
                	商品进价增长数值
                </th>
				<th rowspan="2" data-options="field:'merPurPercent',width:120,align:'right',formatter:merWarnRecFn.formatPercent">
                	商品进价变化百分比
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>