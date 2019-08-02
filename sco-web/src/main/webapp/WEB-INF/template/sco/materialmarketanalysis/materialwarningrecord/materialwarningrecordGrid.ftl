<#compress>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<#include "inc/easyui.ftl" />
	<script type="text/javascript">
		<#include "sco/common/materialProperties.js" />
		<#include "sco/materialmarketanalysis/materialwarningrecord/materialwarningrecord.js" />
	</script>
	<link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
</head>
<body style="margin: 0px;">
	<div id="materialWarnRec_toolbar" style="padding:1px;overflow:auto;">
		<form id="materialWarnRec_search" method="post">
			<table style="width:700px;" border="0">
				<tr>
					<td style="text-align: right;">原料大类：</td>
					<td><input class="filterSelect" filterName="materialBigTypeCode" id="materialBigTypeCode" style="width: 124px;"
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
				    <td style="text-align: right; width: 130px;">公示网站原料名称：</td>
					<td><input class="easyui-combobox filterSelect" filterName="materialCode" id="materialCode" style="width: 124px;"
						data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'220',
							editable:false
					    " />
					</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 100px">公示网站名称：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="websiteCode" name="websiteCode" id="webName" style="width:124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false
					    " />
				   	</td>
				   	<td style="text-align:right;">地区：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="region" name="region" id="region" style="width:124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false
					    " />
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">预警日期：</td>
					<td>
						<input class="easyui-datebox list-input" filterName="startYear" id="minDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue,formatter:formatDateToMonth" style="width: 124px;" />
					</td> 
					<td style="text-align: right;">至：</td>
					<td>
						<input class="easyui-datebox list-input" filterName="endYear" id="maxDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue,formatter:formatDateToMonth" style="width: 124px;" />
					</td>
					<td style="text-align: right;">预警方式：</td>
					<td>
						<input class="easyui-combobox filterSelect"
							style="width:124px" id="warnType" filterName="warnType"
							data-options="
								valueField: 'id',
								textField: 'text',
								editable:false,
								panelHeight:'220',
								url:'materialWarnRec_listWarnTypeOption_5.html'"
						/>
					</td>
				</tr>
				<tr>
					<td colspan="8"> 
						<a onclick="matWarnRecFn.searchMatWarnRec()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 
							查看预警记录
						</a> 
						<a onclick="matWarnRecFn.clearSearch();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="height:480px">
		<table id="materialWarnRec_grid" fit="true" 
			toolbar="#materialWarnRec_toolbar" 
			singleSelected=true
			url='materialWarnRec_listMatWarnRec_2.html'
			pagination="true"
			pagePosition='bottom' 
			pageSize="20"
			pageList="[ 10, 20, 30, 40 ]" 
			data-options="rownumbers:true,
				singleSelect:true"
		>
			<thead>
				<tr>
					<th data-options="field:'created',width:90">预警日期</th>
					<th data-options="field:'materialBigName',width:150">原料大类</th>
					<th data-options="field:'materialSmallName',width:150">原料小类</th>
					<th data-options="field:'materialName',width:150">公示网站原料名称</th>
					<th data-options="field:'websiteName',width:180">公示网站名称</th>
					<th data-options="field:'regionName',width:90">地区</th>
					<th data-options="field:'warnType',width:150">预警方式</th>
					<th data-options="field:'lasMonthAvgPrice',width:100,align:'right'">上月平均价格</th>
					<th data-options="field:'momAvgMonthPrice',width:100,align:'right'">同环比月均价格</th>
					<th data-options="field:'momChangeChange',width:100,align:'right',formatter:matWarnRecFn.formatPercent">同环比变化幅度</th>
					<th data-options="field:'skuCount',width:100,align:'right',formatter:matWarnRecFn.formatterSku">关联SKU数</th>
				</tr>
			</thead>
		</table>
	</div>
	<#-- 商品列表 -->
	<div id="merList"></div>
	
</body>
</html>
</#compress>
