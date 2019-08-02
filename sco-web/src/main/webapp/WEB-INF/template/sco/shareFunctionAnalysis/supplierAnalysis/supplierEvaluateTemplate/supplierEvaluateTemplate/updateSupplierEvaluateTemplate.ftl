<#compress>
<!DOCTYPE html>  
<html>
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTemplate/supplierEvaluateTemplate/updateSupplierEvaluateTemplate.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body class="easyui-layout">
<div data-options="region:'center'">
	<!-- 供应商考评表模板-商品 --> 
	<div id="supplierEvaluateTemplate_toolbar">
		<form id="signedQty_search"  method="post" enctype="multipart/form-data">
			<a onclick="updateSupplierEvaluateTemplateFn.save('${supplierEvaluateTemplateList[0].templateCode}','${supplierEvaluateTemplateList[0].templateType}');" id="save" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
				保存
			</a>
			<a onclick="parent.pubTab.closeCurrTab();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
				关闭
			</a><br/>
			<table style="width:860px">
				<tr>
					<td style="text-align:right;width:60px">
						<label style="color: red;" >*</label>考评表名称:
					</td>
					<td style="text-align:left;width:104px">
						<input id="templateName" style="width:120px;" value="${supplierEvaluateTemplateList[0].templateName}" class="easyui-validatebox" data-options="required:true,validType:'length[1,33]'" />
					</td>
					<td style="text-align:right;width:50px">
						<label style="color: red;" >*</label>考核周期:
					</td>
					<td style="text-align:left;width:104px">
						<input class="easyui-datebox list-input" value="${(supplierEvaluateTemplateList[0].evaluateStartDate?string("yyyy-MM-dd"))!}" id="minCreated" data-options="required:true,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;"/>
					</td>
					<td style="text-align:right;width:10px">
						至:
					</td>
					<td style="text-align:left;width:104px">
						<input class="easyui-datebox list-input" value="${(supplierEvaluateTemplateList[0].evaluateEndDate?string("yyyy-MM-dd"))!}" id="maxCreated" data-options="required:true,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;"/>
					</td>
					<td style="text-align:right;width:60px">
						<label style="color: red;" >*</label>考评表满分:
					</td>
					<td style="text-align:left;width:104px">
						<input id="fullScore" value="${supplierEvaluateTemplateList[0].evaluateFullScore}" style="width:120px;" class="easyui-numberbox" data-options="required:true" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
					</td>
				</tr>
			</table><br/>
			<div>评分项:</div>
			<#list supplierEvaluateTemplateList as list>
			<table id="table">
				<tr>
					<td>
						<table style="width:800px;border:1px solid grey">
							<tr>
								<td style="text-align:left">
									<table id="tab"style="margin-top:10px;margin-bottom:10px">
										<tr>
											<td style="text-align:right;width:70px">
												<label style="color: red;" >*</label>评分项名称:
											</td>
											<td style="width:120px;">
												<input id="evaluateItemName#{list_index}" value="${list.evaluateItemName}" style="width:120px;" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]'" />
											</td>
											<td style="text-align:right;width:60px">
												<label style="color: red;" >*</label>权重(%):
											</td>
											<td style="width:120px;">
												<input id="weight" style="width:120px;" value="${list.weight}" class="easyui-validatebox" data-options="required:true" />
											</td>
											<#if list.templateType!='FL'>
											<td style="text-align:right;width:70px">
												<label style="color: red;" >*</label>打分部门:
											</td>
											<td>
												<input id="departments#{list_index}" value="${list.departments}" class="easyui-combobox filterSelect" style="width: 124px;" 
													data-options="
														valueField:'text',
														textField:'text',
														panelHeight:'auto',
														editable:false,
														required:true, 
													    url:'businessComBox_demartments_5.html'
											    " />
										    </td>
										    </#if>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table style="margin-top:10px;margin-bottom:10px">
										<tr>
											<td style="text-align:right;width:80px">
												评分依据:
											</td>
											<td>
												<textarea id="according#{list_index}" style="width:210px;height:100px;max-width:210px;max-height:100px" class="easyui-validatebox" validtype="length[0,330]">${list.according}</textarea>
											</td>
											<td></td>
											<td style="text-align:right;width:80px">
												评分标准:
											</td>
											<td>
												<textarea id="standard#{list_index}" style="width:210px;height:100px;max-width:210px;max-height:100px" class="easyui-validatebox" validtype="length[0,330]">${list.standard}</textarea>
											</td>
											<td></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<div style="margin-top:-80px;text-align:left">
							<a onclick="updateSupplierEvaluateTemplateFn.addMarkOptions('${list.templateType}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'"></a>
							<a onclick="updateSupplierEvaluateTemplateFn.removeMarkOptions(this);" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'"></a>
						</div>
					</td>
				</tr>
			</table>
			
			</#list>
			
		</form>
	</div>
	</div>
</body>
</html>
</#compress>