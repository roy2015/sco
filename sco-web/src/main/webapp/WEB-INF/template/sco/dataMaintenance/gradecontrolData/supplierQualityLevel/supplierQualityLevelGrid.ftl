<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/gradecontrolData/supplierQualityLevel/supplierQualityLevel.js" />
        <#include "sco/common/masterDataType.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
<!-- 供应商年度质量星级数据表 --> 
	<div id="supplierQualityLevel_toolbar">
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
					<td style="text-align:right;width:110px;">
						质量星级年度：
					</td>
					<td colspan=3>
					    <input type="text" class="easyui-numberbox" filterName="marStartDate" id="minsupplierQualityLevelDate" style="width:120px;" data-options="min:0"/>
						至：
						<input type="text" class="easyui-numberbox" filterName="marEndDate" id="maxsupplierQualityLevelDate" style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a onclick="supplierQualityLevelFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
							查看质量星级记录
						</a>
						<a onclick="supplierQualityLevelFn.clearFilter()" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<a id="upload" onclick="supplierQualityLevelFn.showUpload()" plain="true" class="easyui-linkbutton" data-options="iconCls:'uploadData'" style="display: <#if !action.hasFuncPower('com.powere2e.sco.datamaintenance.gradecontroldata.supplierqualitylevel.upload')>none</#if>;">
							上传质量星级表
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
    <table  id="supplierQualityLevel_grid"
        	border=0
		    fit="true"
		    singleSelect="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#supplierQualityLevel_toolbar"
			url='supplierQualityLevel_listSupplierQualityLevel_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        	    <th data-options="field:'supplierCode',width:120,sortable:true">供应商编号</th>
				<th data-options="field:'supplierName',width:'120'">供应商名称</th>
				<th data-options="field:'qualityLevelYear',width:120,sortable:true">质量星级年度</th>
				<th data-options="field:'qualityLevel',width:120,sortable:true,align:'right'">质量星级</th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>