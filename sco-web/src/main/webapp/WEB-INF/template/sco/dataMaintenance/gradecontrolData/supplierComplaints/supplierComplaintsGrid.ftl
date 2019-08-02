<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/gradecontrolData/supplierComplaints/supplierComplaints.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<!-- 供应商年度千万元客诉管理主表 --> 
	<div id="supplierComplaints_toolbar">
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
						客诉年度：
					</td>
					<td colspan="3">
					<input type="text" class="easyui-numberbox" filterName="marStartDate" id="minsupplierComplaintsDate" style="width:120px;" data-options="min:0"/>
						至：
						<input type="text" class="easyui-numberbox" filterName="marEndDate" id="maxsupplierComplaintsDate" style="width:120px;"/>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a onclick="supplierComplaintsFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
							查看客诉记录
						</a>
						<a onclick="supplierComplaintsFn.clearFilter()" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<a id="upload" onclick="supplierComplaintsFn.showUpload()" plain="true" class="easyui-linkbutton" data-options="iconCls:'uploadData'" style="display: <#if !action.hasFuncPower('com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints.upload')>none</#if>;">
							上传客诉记录表
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
    <table  id="supplierComplaints_grid"
    		border=0
		    fit="true"
		    singleSelect="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#supplierComplaints_toolbar"
			url='supplierComplaints_listSupplierComplaints_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        	    <th data-options="field:'supplierCode',width:120,sortable:true">供应商编号</th>
				<th data-options="field:'supplierName',width:'120'">供应商名称</th>
				<th data-options="field:'complaintsYear',width:120,sortable:true">客诉年度</th>
				<th data-options="field:'complaintsCount',width:120,sortable:true,align:'right'">客诉数量</th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>