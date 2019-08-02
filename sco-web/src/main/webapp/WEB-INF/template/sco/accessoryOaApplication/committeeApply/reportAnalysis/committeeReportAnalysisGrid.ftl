<#compress>
	<style>
		.hangju td{padding:7px 0}
	</style>
	<script type="text/javascript">
		<#include "sco/accessoryOaApplication/committeeApply/reportAnalysis/committeeReportAnalysis.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
	<#-- <div class="easyui-tabs" data-options="fit:true,border:true" style="height:560px;"> -->
		<div>
			<#-- <fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
				<legend style="color:#666;padding:0 5px;"><b>选择需要关联的报表</b></legend> -->
				<form id="rptUpload_form" method="post" enctype="multipart/form-data">
					<input type="hidden" name="applicationCode" value="${applicationCodeNow}"/>
					<input type="hidden" name="quotedCodes" value="${quotedCodes}"/>
					<table class="tableForm hangju" style="width:500px">
						<tr>
							<td>
								<span style="padding-left: 6px;"></span>
								<div style="background-color:#2F7ED8;width:20px;height:10px;display: inline-block;"></div>
								<b>选择需要关联的报表</b>
							</td>
							<td style="text-align:left;width:200px;line-height:26px" colspan="2">
								<font color="red" style="padding-left:10px">*</font>报表类型:			
								<input class="easyui-combobox" id="reportType" name="reportsTypeCode" style="width:155px;" 
									data-options="
										valueField:'id',
										textField:'text',
										panelWidth:'166',
										panelHeight:'200',
										editable:false,
										required:true,
										url:'reports_listPartMyReportType_5.html'
							    " />
							</td>
							<td style="text-align:left;">
								<a onclick="comOaReportAnalyFn.linkReport()" plain="true" class="easyui-linkbutton" data-options="iconCls:'agent'">关联报表</a>
							</td>
			 			</tr>
						<tr>
							<td>
								<span style="padding-left: 6px;"></span>
								<div style="background-color:#8BBC21;width:20px;height:10px;display: inline-block;"></div>
								<b>上传本地报表</b>
							</td>
							<td style="text-align:left;padding-left: 16px;" colspan="2">
								<input type="text" class="file" id="txt" style="width:0px;"/>
								<input class="input_file" size="30" type="file" onchange="txt.value=this.value" id="upload" name="upload" style="width:210px;" />
							</td>
							<td>
								<span style="padding-left: 9px;"></span>
								<input type="button" value="上传" style="width:64px" onclick="comOaReportAnalyFn.rptUpload()" >
							</td>
						</tr>
						<tr>
							<td>
								<span style="padding-left: 6px;"></span>
								<div style="background-color:#910000;width:20px;height:10px;display: inline-block;"></div>
								<b>上传申购单</b>
							</td>
							<td style="text-align:left;padding-left:16px;" colspan="3">
								<input type="button" value="上传申购单" style="width:75px;height:21px;" onclick="comOaReportAnalyFn.showPurOrderUploadForm()" />
							</td>
						</tr>
					</table>
				</form>	
			<#-- </fieldset>
			<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true"> -->
				<#-- <legend  style="color:#666;padding:0 5px;"><b>已关联的报表</b></legend> -->
			 	<#-- 报表关联列表 -->
				<div>
					<table id="committeeOaReportAnalyGrid" 
						title="已关联的报表"
						pagination = "true"
						pagePosition = "bottom"
						pageSize = "20"
						pageList = "[ 10, 20, 30, 40 ]"
						fitColumns="true"
						toolbar="#comOaReportAna_toolbar"
						style="height:330px"
						data-options="rownumbers:true,
						url : 'analysisReportCommittee_listAnalysisReportSystem_2.html?applicationCode=${applicationCodeNow}'
					">  
						<thead>
							<tr>
								<th rowspan="2" data-options="field:'NULL',	checkbox:true"></th>
								<th data-options="field:'reportsTypeName',width:160">
									报表类型
								</th>
								<th data-options="field:'reportsName',width:264,formatter:comOaReportAnalyFn.formatFileName">
									报表名称
								</th>
								<th rowspan="2" data-options="field:'created',width:151,sortable:true">
									保存日期
								</th>
								<th rowspan="2" data-options="field:'createby',width:100">
									报表保存人
								</th>
							</tr>
							<tr>
								<th>
							 		<select class="filterSelect" id="rptTypeCode" filterName="reportsTypeName" style="border: ridge 1px #ccc;height:18px;width:140px;">
						 				<option value="">所有</option>
								 	</select>
							 	</th>  
								<th>
							 		<input style="width:257px;height:14px" class="filterInput" filterName="reportsName"/>
							 	</th>
					 		</tr>
						</thead>
					</table>
				</div>
				<div id="comOaReportAna_toolbar">
					<a onclick="comOaReportAnalyFn.confirmRemoveLinkSystem();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
						取消关联报表
					</a>
					<div class="easyui-dialog" id="removeSystemDlg" style="width:320px;height:160px" 
						data-options="
							title:'取消关联',
							closable:false,closed:true,modal:true,buttons:[
									{text:'取消关联',iconCls:'save',handler:function(){comOaReportAnalyFn.removeLink()}},
									{text:'关闭页面',iconCls:'close',handler:function(){comOaReportAnalyFn.closeDlg()}}
							]
					">
						<br>
						<span class="txtCenter">
							&nbsp;&nbsp;&nbsp;是否确认取消关联此分析报表？<br>
							&nbsp;&nbsp;&nbsp;取消关联后，此分析报表<span style="color:red">不会</span>被删除。
						</span>
					</div>
				</div>

			<div style="padding: 20px;"></div>
			<#-- </fieldset>
			<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true"> -->
				<#-- <legend  style="color:#666;padding:0 5px;"><b>已关联的报表</b></legend> -->
			 	<#-- 报表关联列表 -->
				<div>
					<table id="uploadOaReportAnalyGrid" 
						title="已上传并关联的报表"
						pagination = "true"
						pagePosition = "bottom"
						pageSize = "20"
						pageList = "[ 10, 20, 30, 40 ]"
						fitColumns="true"
						toolbar="#upload_toolbar"
						style="height:330px"
						data-options="rownumbers:true,
						url : 'analysisReportCommittee_listAnalysisReportUpload_2.html?applicationCode=${applicationCodeNow}'					">  
						<thead>
							<tr>
								<th rowspan="2" data-options="field:'NULL',	checkbox:true"></th>
									<th data-options="field:'reportsName',width:220,formatter:comOaReportAnalyFn.formatFileName">
									报表名称
								</th>
									<th data-options="field:'createby',width:180">
									上传人姓名
								</th>
								<th rowspan="2" data-options="field:'created',width:120,sortable:true">
									上传日期
								</th>
							</tr>
						</thead>
						<thead>
							 <th><input style="width:60px;height:13px" class="filterInput" filterName="reportsName"/></th>  
							 <th><input style="width:60px;height:13px" class="filterInput" filterName="createby"/></th>  
						</thead>
					</table>
				</div>
				<div id="upload_toolbar">
					<a onclick="comOaReportAnalyFn.confirmRemoveLinkUpload();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
						删除报表
					</a>
				</div>
				<div style="padding: 20px;"></div>
			<#-- </fieldset>
			
			<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true"> -->
				<#-- <legend  style="color:#666;padding:0 5px;"><b>已关联的报表</b></legend> -->
			 	<#-- 报表关联列表 -->
				<div>
					<table id="purOrderOaReportAnalyGrid" 
						title="已上传的申购单列表"
						pagination = "true"
						pagePosition = "bottom"
						pageSize = "20"
						pageList = "[ 10, 20, 30, 40 ]"
						fitColumns="true"
						toolbar="#purOrder_toolbar"
						style="height:330px"
						data-options="rownumbers:true,
						url : 'analysisReportCommittee_listAnalysisReportPurOrder_2.html?applicationCode=${applicationCodeNow}'					">  
						<thead>
							<tr>
								<th rowspan="2" data-options="field:'NULL',	checkbox:true"></th>
									<th data-options="field:'reportsName',width:220,sortable:true,formatter:comOaReportAnalyFn.formatFileName">
									申购单名称
								</th>
									<th data-options="field:'createby',width:180">
									上传人姓名
								</th>
								<th rowspan="2" data-options="field:'created',width:120,sortable:true">
									上传日期
								</th>
							</tr>
						</thead>
						<thead>
							 <th><input style="width:60px;height:13px" class="filterInput" filterName="reportsName"/></th>  
							 <th><input style="width:60px;height:13px" class="filterInput" filterName="createby"/></th>  
						</thead>
					</table>
				</div>
				<div id="purOrder_toolbar">
					<a onclick="comOaReportAnalyFn.confirmRemoveLinkPurOrder();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
						删除申购单
					</a>
				</div>
			<#-- </fieldset> -->
		</div>
	<#-- </div> -->
</#compress>