<#compress>
<!DOCTYPE html>  
<html>
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTemplate/supplierEvaluateTemplate/supplierEvaluateTemplate.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<!-- 供应商考评表模板管理主表 --> 
	<div id="supplierEvaluateTemplate_toolbar">
		<form id="signedQty_search">
			<a onclick="parent.pubTab.closeCurrTab();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
				关闭
			</a><br/>
			<table style="width:860px">
				<tr>
					<td style="text-align:left;width:80px">
						考评表名称:<input filterName="templateName" id="templateName" style="width:120px;"  />
					</td>
					<td style="text-align:left;width:114px">
						考核周期:
						<input class="easyui-datebox list-input" filterName="evaluateStartDate" id="evaluateStartDate" data-options="disabled:true,required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;"/>
					</td>
					<td  style="text-align:left;width:114px">至:
						<input class="easyui-datebox list-input" filterName="evaluateEndDate" id="evaluateEndDate" data-options="disabled:true,required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;"/>
					</td>
					<td style="text-align:right;width:50px">
						考评表满分:
					</td>
					<td style="text-align:left;width:120px">
						<input id="evaluateFullScore" filterName="evaluateFullScore" style="width:120px;" class="easyui-validatebox" required="true" />
					</td>
				</tr>
			</table><br/>
			<div>评分项:</div>
			<table id="table" style="width:800px;border:1px solid grey;background-color:#efefef">
				<tr>
					<td style="text-align:left">
						<table  id="tab" style="margin-top:10px;margin-bottom:10px">
							<tr>
								<td style="text-align:right;width:70px">
									评分项名称:
								</td>
								<td style="width:120px;">
									<input filterName="evaluateItemName" id="evaluateItemName" style="width:120px;" />
								</td>
								<td style="text-align:right;width:50px">
									权重(%):
								</td>
								<td style="width:120px;">
									<input id="weight" style="width:120px;" class="easyui-validatebox" required="true" />
								</td>
								<td style="text-align:right;width:50px">
									分值:
								</td>
								<td style="width:120px;">
									<input filterName="score" id="score" style="width:120px;" />
								</td>
								<td style="text-align:right;width:70px">
									打分部门:
								</td>
								<td>
									<input filterName="departments" id="departments" style="width:124px;" />
							    </td>
							</tr>
						</table>
					</td>
				<tr>
				<tr>
					<td>
						<table style="margin-top:10px;margin-bottom:10px">
							<tr>
								<td style="text-align:right;width:80px">
									评分依据:
								</td>
								<td style="colspan='2'">
									<textarea id="according" disabled="disabled" style="width:210px;height:100px;max-width:210px;max-height:100px" ></textarea>
								</td>
								<td></td>
								<td style="text-align:right;width:80px">
									评分标准:
								</td>
								<td style="colspan='2'">
									<textarea id="standard" disabled="disabled" style="width:210px;height:100px;max-width:210px;max-height:100px" ></textarea>
								</td>
								<td></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
</#compress>