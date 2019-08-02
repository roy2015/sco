<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/gradecontrolData/supplierVisitFactory/supplierVisitFactory.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<!-- 供应商年度巡厂得分管理主表 --> 
	<div id="supplierVisitFactory_toolbar">
		<form id="signedQty_search">
			<table class="table table-condensed" style="width:1000px;table-layout:fixed;">
				<tr>
					<td style="text-align:right;width:125px">
						供应商编号：
					</td>
					<td style="width:125px;">
						<input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width:120px;" />
					</td>
					<td style="text-align:right;width:125px">
						供应商名称：
					</td>
					<td style="width:125px;">
						<input class="easyui-validatebox" filterName="supplierName" id="supplierName" style="width:120px;" />
					</td>
					<td style="text-align:right;width:125px;">
						巡检日期：
					</td>
					<td colspan="3">
					    <input type="text" class="easyui-numberbox" filterName="marStartDate" id="minsupplierVisitFactoryDate" style="width:120px;" data-options="min:0"/>
						至：
						<input type="text" class="easyui-numberbox" filterName="marEndDate" id="maxsupplierVisitFactoryDate" style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a onclick="supplierVisitFactoryFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
							查看巡检得分记录
						</a>
						<a onclick="supplierVisitFactoryFn.clearFilter()" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<a id="upload" onclick="supplierVisitFactoryFn.showUpload()" plain="true" class="easyui-linkbutton" data-options="iconCls:'uploadData'" style="display: <#if !action.hasFuncPower('com.powere2e.sco.datamaintenance.gradecontroldata.suppliervisitactory.upload')>none</#if>;">
							上传巡检得分表
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
    <table  id="supplierVisitFactory_grid"
    		border=0
		    fit="true"
		    singleSelect="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#supplierVisitFactory_toolbar"
			url='supplierVisitFactory_listSupplierVisitFactory_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        	    <th data-options="field:'supplierCode',width:70,sortable:true">供应商编号</th>
				<th data-options="field:'supplierName',width:'140'">供应商名称</th>
				<th data-options="field:'floorArea',width:100,align:'right'">占地面积(平米)</th>
				<th data-options="field:'plantArea',width:100,align:'right'">车间面积(平米)</th>
                <th data-options="field:'enterpriseCountPeople',width:80,align:'right'">企业总人数</th>
				<th data-options="field:'yearTotalOutput',width:105,align:'right'">年总产值(千万)</th>
				<th data-options="field:'visitFactoryDate',width:85,sortable:true">巡厂日期</th>
                <th data-options="field:'fullScore',width:50,align:'right'">满分</th>
				<th data-options="field:'qualifiedScore',width:60,align:'right'">合格分</th>
				<th data-options="field:'score',width:70,sortable:true,align:'right'">巡厂得分</th>
                <th data-options="field:'visitFactoryOpinion',width:130">巡厂意见</th>
				<th data-options="field:'visitFactoryPrincipal',width:100">巡检负责人</th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>