<#compress>
	<script type="text/javascript" >
		<#include "sco/merchandiseOaApplication/reportOldupOaApplication/reportAnalysis/oldOaReportAnalysis.js" />
    </script>
	<#-- <div class="easyui-tabs" data-options="fit:true,border:true" style="height:570px;"> -->
		<div style="padding:5px;">
			<#-- <fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
				<legend style="color:#666;padding:0 5px;"><b>选择需要关联的报表</b></legend> -->
				<table>
					<tr>
						<td style="text-align:right;"><b>选择需要关联的报表</b></td>
					</tr>
					<tr>
						<td style="text-align:right;width:130px">
							<font color="red">*</font>报表类型：
						</td>
						<td style="text-align:right;width:175px;">
							<input class="easyui-combobox" id="reportType" style="width:155px;" 
								data-options="
									valueField:'id',
									textField:'text',
									panelWidth:'166',
									panelHeight:'200',
									editable:false,
									url:'reports_listMyReportTypeForMOA_5.html'
						    " />
						</td>
						<td style="text-align:right;width:100px;">
							<a onclick="oldOaReportAnalyFn.linkReport()" plain="true" class="easyui-linkbutton" data-options="iconCls:'agent'" >关联报表</a>
						</td>
		 			</tr>
				</table>	
			<#--</fieldset>
			 已关联报表列表 
			<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
				<legend  style="color:#666;padding:0 5px;"><b>已关联的报表</b></legend> -->
			 	<#-- 报表关联列表 -->
				<div style="padding:12px;">
					<table id="oldOaReportAnalyGrid" 
						title="已关联的报表"
						pagination = "true"
						pagePosition = "bottom"
						pageSize = "20"
						pageList = "[ 10, 20, 30, 40 ]"
						toolbar="#oldOaReportAna_toolbar"
						style="height:430px"
						data-options="rownumbers:true,
						url : 'analysisReportOld_listAnalysisReportOld_2.html?applicationCode=${applicationCode}',
						onBeforeLoad:function() {
							oldOaReportAnalyFn.setDatagridHeight();
						},
						onLoadSuccess:function(){
							$('#oldOaReportAnalyGrid').datagrid('clearSelections');
						}
					">  
						<thead>
							<tr>
								<th rowspan="2" data-options="field:'NULL',	checkbox:true"></th>
								<th data-options="field:'reportsTypeName',width:190,halign:'left'">
									报表类型
								</th>
									<th data-options="field:'reportsName',width:180,halign:'left',formatter:oldOaReportAnalyFn.formatFileName">
									报表名称
								</th>
								<th rowspan="2" data-options="field:'created',halign:'left',width:120,sortable:true">
									保存日期
								</th>
								<th rowspan="2" data-options="field:'createby',halign:'left',width:180">
									保存人姓名
								</th>
							</tr>
						</thead>
						<thead>
							 <th>
							 	<select class="filterSelect" id="oldrptTypeCode" filterName="reportsTypeName" style="border: ridge 1px #ccc;height:18px">
							 		<option value="">所有</option>
							 	</select>
							 </th>  
							 <th><input style="width:60px;height:14px" class="filterInput" filterName="reportsName"/></th>  
						</thead>
					</table>
				</div>
				<div id="oldOaReportAna_toolbar">
					<a onclick="oldOaReportAnalyFn.confirmRemoveLink();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
						取消关联报表
					</a>
					<div class="easyui-dialog" id="removeDlg" style="width:320px;height:160px" 
						data-options="
							title:'取消关联',
							closable:false,closed:true,modal:true,buttons:[
									{text:'取消关联',iconCls:'save',handler:function(){oldOaReportAnalyFn.removeLink()}},
									{text:'关闭页面',iconCls:'close',handler:function(){oldOaReportAnalyFn.closeDlg()}}
							]
					">
						<br>
						<span class="txtCenter">
							&nbsp;&nbsp;&nbsp;是否确认取消关联此分析报表？<br>
							&nbsp;&nbsp;&nbsp;取消关联后，此分析报表<span style="color:red">不会</span>被删除。
						</span>	
					</div>	
				</div>
			<#-- </fieldset> -->
		</div>
	<#-- </div> -->
</#compress>