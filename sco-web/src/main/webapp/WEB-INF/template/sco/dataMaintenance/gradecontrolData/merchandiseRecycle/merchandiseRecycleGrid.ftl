<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/gradecontrolData/merchandiseRecycle/merchandiseRecycle.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
       <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<!-- 商品回收记录管理主表 --> 
	<div id="merchandiseRecycle_toolbar">
		<form id="signedQty_search">
			<table class="table table-condensed" style="width:1000px;table-layout:fixed;">
				<tr>
					<td style="text-align:right;width:125px">
						供应商编号：
					</td>
					<td style="width:125px;">
						<input filterName="supplierCode" id="supplierCode" style="width:120px;" />
					</td>
					<td style="text-align:right;width:125px">
						供应商名称：
					</td>
					<td style="width:125px;">
						<input filterName="supplierName" id="supplierName" style="width:120px;" />
					</td>
					<td style="text-align:right;width:125px;">
						回收日期：
					</td>
					<td>
						<input class="easyui-datebox list-input" filterName="marStartDate" id="minConcessionReceiveDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;"/>
					</td>
					<td style="text-align:right;width:125px;">
						至：
					</td>
					<td style="width:125px;">
					<input class="easyui-datebox list-input" filterName="marEndDate" id="maxConcessionReceiveDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;width:125px">
						商品编号：
					</td>
					<td style="width:125px;">
						<input filterName="merchandiseCode" id="merchandiseCode" style="width:120px;" />
					</td>
					<td style="text-align:right;width:77px">
						商品名称：
					</td>
					<td>
						<input filterName="merchandiseName" id="merchandiseName" style="width:120px;" />
					</td>
					<td style="text-align:right;width:79px;">商品定性角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'merchandiseRole_listQualitative_5.html'
					    " />
					</td>
					<td style="text-align:right;width:79px;">商品定量角色：</td>
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
					<td style="text-align:right;width:73px">中分类：</td>
					<td>
						<input class="filterSelect" filterName="centreTypeCode" id="centreTypeCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " />
					</td> 
					<td style="text-align:right;width:73px">小分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
							data-options="
								valueField:'id',
								textField:'text',
								editable:false" 
						/>
					</td>
					<td style="text-align:right;width:73px">明细类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false" 
					    />
					</td>
					<td style="text-align:right;width:73px">细分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineTypeCode" style="width: 124px;"
							data-options="
								valueField:'id',
								textField:'text',
								editable:false" 
						/>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a onclick="merchandiseRecycleFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
							查看商品回收记录
						</a>
						<a onclick="merchandiseRecycleFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<a id="upload" onclick="merchandiseRecycleFn.showUpload();" plain="true" class="easyui-linkbutton" data-options="iconCls:'uploadData'" style="display: <#if !action.hasFuncPower('com.powere2e.sco.datamaintenance.gradecontroldata.merchandiserecycle.upload')>none</#if>">
							上传商品回收表
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
    <table  id="merchandiseRecycle_grid"
    		border=0
		    fit="true"
		    singleSelect="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#merchandiseRecycle_toolbar"
			url='merchandiseRecycle_listMerchandiseRecycle_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
				<th data-options="field:'supplierCode',width:120,sortable:true">供应商编号</th>
				<th data-options="field:'supplierName',width:'120'">供应商名称</th>
				<th data-options="field:'merchandiseCode',width:120,sortable:true">商品编号</th>
				<th data-options="field:'merchandiseName',width:120">商品名称</th>
				<th data-options="field:'dxRoleName',width:110">商品定性角色</th>
                <th data-options="field:'dlRoleName',width:110">商品定量角色</th>
				<th data-options="field:'centreName',width:110">中分类</th>
				<th data-options="field:'smallName',width:110">小分类</th>
				<th data-options="field:'detailName',width:110">明细类</th>
				<th data-options="field:'fineName',width:110">细分类</th>
                <th data-options="field:'recycleCount',width:110">回收数量</th>
                <th data-options="field:'recycleDate',width:120,sortable:true">回收日期</th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>