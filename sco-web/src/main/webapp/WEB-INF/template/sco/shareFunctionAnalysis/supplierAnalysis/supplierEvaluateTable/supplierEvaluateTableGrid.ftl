<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTable/supplierEvaluateTableModel.js" />
    </script>
</head>
<body>
	<!-- 供应商考评表管理主表 --> 
	<div id="supplierEvaluateTable_toolbar">
		<form id="signedQty_search" method="post">
			<table style="width: 820px;">
				<#-- 查询条件 -->
				<tr>
					<td style="text-align: right; width: 100px">考评模板名称:</td>
					<td style="width: 110px;"><input class="easyui-validatebox" filterName="templateName" id="templateName" style="width: 120px;" /></td>
					<td style="text-align: right; width: 100px">考评模板发布人:</td>
					<td style="width: 110px;"><input class="easyui-validatebox" filterName="createbySE" id="createbySE" style="width: 120px;" /></td>
					<td style="text-align: right; width: 100px;">考评模板状态:</td>
					<td><select style="width: 124px;" filterName="status" id="status" >
							<option value=""> </option>
							<option value="YGB">已关闭</option>
							<option value="YFB">已发布</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 80px">供应商名称:</td>
					<td style="width: 110px;"><input class="easyui-validatebox" filterName="supplierName" id="supplierName" style="width: 120px;" /></td>
					<td style="text-align: right; width: 70px">供应商编号:</td>
					<td style="width: 110px;"><input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width: 120px;" /></td>
					<td style="text-align: right; width: 100px;">考评表创建人:</td>
					<td style="width: 110px;"><input class="easyui-validatebox" filterName="createby" id="createby" style="width: 120px;" /></td>
				</tr>			
				<tr>
					<td style="text-align: right; width: 100px;">考评模板发布日期:</td>
					<td><input class="easyui-datebox list-input" filterName="minPublishDate" id="minPublishDate"
						data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" /> 
					</td>
					<td style="text-align: right; width: 100px;">至:</td>
					<td> <input class="easyui-datebox list-input" filterName="maxPublishDate"
						id="maxPublishDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" /></td>
				</tr>
				<tr>
					<td style="text-align: right; width: 79px;">考核周期:</td>
					<td><input class="easyui-datebox list-input" filterName="minCreated" id="minCreated"
						data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" />
					</td>
					<td style="text-align: right; width: 79px;"> 至:</td>
					<td> <input class="easyui-datebox list-input" filterName="maxCreated"
						id="maxCreated" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" /></td>
				</tr>
			</table>
		<a id="showEdit" onclick="supplierEvaluateTableFn.searchTemplate('hide');"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" >
			搜索考评模板
		</a>
		<a id="showEdit" onclick="supplierEvaluateTableFn.searchTable('show');"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" >
			搜索考评表
		</a>
		<a id="clear" onclick="supplierEvaluateTableFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'" >
			清空查询
		</a>
		<a id="df" onclick="supplierEvaluateTableFn.mark();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			打分
		</a>
		<a id="fb" onclick="supplierEvaluateTableFn.publishTable();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			发布考评表
		</a>
		</form>
	</div>
    <table  id="supplierEvaluateTable_grid"
		    class="easyui-datagrid"
		    fit="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#supplierEvaluateTable_toolbar"
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th align="center" data-options="field:'check',width:40,checkbox:true"></th>
				<th data-options="field:'templateCode',width:90,sortable:false,formatter:supplierEvaluateTableFn.change" >
                	考评模版编号
                </th>
				<th data-options="field:'templateName',width:250,sortable:false">
                	考评模版名称
                </th>
				<th data-options="field:'evaluateDate',width:150,sortable:false">
                	考核周期
                </th>
				<th data-options="field:'publishDate',width:110,sortable:false">
                	考评模版发布日期
                </th>
                <th data-options="field:'status',width:90,sortable:false">
                	考评模版状态
                </th>
                <th data-options="field:'evaluateTableCode',width:90,sortable:false,formatter:supplierEvaluateTableFn.changeStyle" >
                	考评表编号
                </th>
				<th data-options="field:'createby',width:90,sortable:false">
                	考评表发布人
                </th>
				<th data-options="field:'supplierCode',width:140,sortable:false">
                	供应商编号
                </th>
				<th data-options="field:'supplierName',width:110,sortable:false">
                	供应商名称
                </th>
                <th align="right" data-options="field:'scoreCG',width:90,sortable:false">
                	采购评分
                </th>
                <th align="right" data-options="field:'scorePK',width:90,sortable:false" >
                	品控评分
                </th>
				<th align="right" data-options="field:'scoreKC',width:90,sortable:false">
                	库存评分
                </th>
                <th align="right" data-options="field:'scorePL',width:90,sortable:false">
                	品类评分
                </th>
				<th align="right" data-options="field:'scoreTotal',width:140,sortable:false">
                	总分
                </th>
				<th align="right" data-options="field:'scoreRank',width:110,sortable:false">
                	总分排行
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>