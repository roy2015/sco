<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
    	<#include "sco/common/materialProperties.js" />
		<#include "sco/materialmarketanalysis/accessoryintentionrecord/accessoryintentionrecord.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<div id="accIntenWarnRec_toolbar" style="padding:1px;overflow:auto;">
		<form id="accIntenWarnRec_search" method="post">
			<table style="width:695px;" border="0">
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
				</tr>
				<tr>
					<td style="text-align:right;width:77px">商品名称：</td>
					<td>
						<input class="easyui-validatebox" filterName="merchandiseName" id="merchandiseName" style="width:135px;" />
					</td>
					<td style="text-align:right;">细分类：</td>
					<td>
						<select style="width:124px" id="fineType" filterName="fineType">
							<option value=""></option>
							<option value="ALL">所有</option>
							<#list accDetTypeList as l>
								<option value="${l.id}">${l.text}</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">辅料原料名称：</td>
					<td>
						<input class="easyui-validatebox" id="accessoryName" filterName="accessoryName" style="width:135px;" />
					</td>
					<td style="text-align: right;">原料大类:</td>
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
						<input class="easyui-combobox filterSelect" filterName="region" id="region" style="width:124px;" 
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
							<input id="radioWarn" type="radio" filterName="netWeightType" name="netWeightType" onclick="accIntenWarnRecFn.showRecord('warn')" />只看预警记录
						</label>
						<span style="padding-left: 39px;"></span>
						<span id="warn" style="visibility: hidden;">
							预警日期：
							<input class="easyui-datebox list-input" id="minwarnYear" value="${startWarnDate}" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue,formatter:formatDateToMonth,onSelect:function(date){accIntenWarnRecFn.formateDateToMonth(this,date)}" style="width: 124px;" />
							至：
							<input class="easyui-datebox list-input" id="maxwarnYear" value="${endWarnDate}" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue,formatter:formatDateToMonth" style="width: 124px;" />
						</span>
						<br />
						
						<span style="padding-left: 29px;"></span>
						<label><input type="radio" name="netWeightType" onclick="accIntenWarnRecFn.showRecord('unWarn')" />只看非预警记录</label>
						<span style="padding-left: 27px;"></span>
						<span id="unWarn" style="visibility: hidden;">
							试算日期：
							<input class="easyui-datebox list-input" id="minunWarnYear" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue,formatter:formatDateToMonth,onSelect:function(date){accIntenWarnRecFn.formateDateToMonth(this,date)}" style="width: 124px;" />
							至：
							<input class="easyui-datebox list-input" id="maxunWarnYear" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue,formatter:formatDateToMonth" style="width: 124px;" />
						</span>
						<br />
						
						<span style="padding-left: 29px;"></span>
						<label><input type="radio" name="netWeightType" onclick="accIntenWarnRecFn.showRecord('allWarn')"/>查看所有记录</label>
						<span style="padding-left: 39px;"></span>
						<span id="allWarn" style="visibility: hidden;">
							试算日期：
							<input class="easyui-datebox list-input" id="minallWarnYear" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue,formatter:formatDateToMonth,onSelect:function(date){accIntenWarnRecFn.formateDateToMonth(this,date)}" style="width: 124px;" />
							至：
							<input class="easyui-datebox list-input" id="maxallWarnYear" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue,formatter:formatDateToMonth" style="width: 124px;" />
						</span>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a onclick="accIntenWarnRecFn.searchMerWarnRec();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.materialmarketAnalysis.materialWarningRecord.listMatWarnRec")>none</#if>;">
							查看预警记录
						</a>
						<a onclick="accIntenWarnRecFn.clearSearch();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
    <table  id="accIntenWarnRec_grid"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#accIntenWarnRec_toolbar"
			url='accIntRec_listAccessoryIntentionRecord_2.html'
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
				<th rowspan="2" data-options="field:'fineName',width:120">
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
				<th rowspan="2" data-options="field:'materialBigName',width:200">
                	原料大类
                </th>
				<th rowspan="2" data-options="field:'materialSmallName',width:200">
                	原料小类
                </th>
				<th rowspan="2" data-options="field:'accessoryName',width:200">
                	辅料原料名称
                </th>
				<th rowspan="2" data-options="field:'merPurPrice',width:150,align:'right'">
                	辅料商品进价
                </th>
				<th rowspan="2" data-options="field:'supPurPrice',width:150,align:'right'">
                	供应商原料采购价
                </th>
				<th rowspan="2" data-options="field:'paperWeight',width:150,align:'right'">
                	纸张克重
                </th>
				<th rowspan="2" data-options="field:'supInpPrice',width:150,align:'right'">
                	供应商原料投入价
                </th>
				<th rowspan="2" data-options="field:'websitePrice',width:150,align:'right'">
                	公示网站原料行情原价
                </th>
				<th rowspan="2" data-options="field:'webLmPrice',width:150,align:'right'">
                	公示网站上月原料行情价
                </th>
				<th rowspan="2" data-options="field:'supInpCom',width:150,align:'right'">
                	供应商原料投入价试算
                </th>
				<th rowspan="2" data-options="field:'merPriceCom',width:150,align:'right'">
                	辅料商品进价试算
                </th>
				<th rowspan="2" data-options="field:'merPriceGrowth',width:150,align:'right'">
                	辅料商品进价增长数值
                </th>
				<th rowspan="2" data-options="field:'merPricePro',width:150,align:'right',formatter:accIntenWarnRecFn.formatPercent">
                	辅料商品进价变化百分比
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>