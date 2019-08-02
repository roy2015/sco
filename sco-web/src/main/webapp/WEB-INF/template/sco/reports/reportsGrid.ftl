<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/reports/reportsModel.js" />
    </script>
</head>
<body>
	<!-- 我的报表管理主表 --> 
	<div id="reports_toolbar">
	<form id="reports_search">
          <table  style="width:800px;"> 
		  		<tr>
		  		<td align="right">报表类型:</td>
		  		<td><input class="easyui-combobox filterSelect" filterName="reportsTypeName" id="reportsTypeName" style="width:124px;" 
							data-options="
								valueField:'text',
								textField:'text',
								panelWidth:'166',
          						panelHeight:'200',
								editable:false,
								url:'reports_listMyReportType_5.html'
					    " />
</td>
		  		<td align="right">报表名称:</td>
		  		<td ><input class="easyui-validatebox" filterName="reportsName" id="reportsName"  style="width:120px;"/></td>
		  		 <td align="right">报表关联的OA申请单状态:</td>
		  		<td>     
			    <input class="easyui-combobox filterSelect" id="oaStatus" name="oaStatus" filterName="oaStatus" style="width: 124px;"
			    	data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'businessComBox_applicationStatus_5.html'">
				</input>
			</td>
		  		</tr>
			   <tr>
			   <td align="right">报表关联的OA申请单号:</td>
		  		<td><input class="easyui-validatebox" filterName="oaNo" id="oaNo"  type="text"  style="width:120px;"/></td>
			     <td align="right">报表保存日期:</td>
				<td> <input class="easyui-datebox"   filterName="created"  id="minUpdatedDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;"/></td>
		 		<td align="right">至:</td>
		 		<td>
		 			<input class="easyui-datebox"  filterName="createdEnd"   id="maxUpdatedDate"  data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;"/>
			
			    </td>
				
		  		</tr>
		  		</table>
		  		<table>
	    
	</table>
		<a onclick="reportsFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
			搜索
		</a>
		<a onclick="reportsFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			清空查询
		</a>
		<a onclick="reportsFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
			删除
		</a>
		</form>
	</div>
    <table  id="reports_grid"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#reports_toolbar"
			url='reports_listReports_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        	<th data-options="width:65,checkbox:true">
                </th>
        	     <th data-options="field:'reportsCode',width:120,sortable:true,
        	     	formatter:function(value,row){
	        	     	return  reportsFn.showUrl(value,row,'${nfsFilePath}');
	        	     }">
                	<span >报表编号</span>
                </th>
				<th data-options="field:'reportsTypeName',width:120,sortable:true">
                	<span >报表类型</span>
                </th>
				<th data-options="field:'reportsName',width:260,sortable:true">
                	<span >报表名称</span>
                </th>
				<th data-options="field:'createUserName',width:100,sortable:true">
                	<span >报表保存人</span>
                </th>
                <th data-options="field:'created',width:150,sortable:true">
                	<span >报表保存日期</span>
                </th>
                <th data-options="field:'oaNo',width:150,sortable:true">
                	<span >报表关联的SCO申请单号</span>
                </th>
                <th data-options="field:'oaStatus',width:150,sortable:true,formatter:function(value,row){
																				 if(row.oaStatus == null) {
																					return '无';
																				 }else if(row.oaStatus == 'CG'){
																					return '草稿';
																				 }else if(row.oaStatus == 'GB'){
																				 return '关闭';
																				 }else if(row.oaStatus == 'YX'){
																				 return '允许OA同步';
																				 }else if(row.oaStatus == 'SPZ'){
																				 return '审批中';
																				 }else if(row.oaStatus == 'SPTG'){
																				 return '审批通过';
																				 }else if(row.oaStatus == 'W'){
																				 return '无';
																				 }
																				}">
                	<span >报表关联的SCO申请单状态</span>
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>