<#compress>
<!DOCTYPE html>  
<html>
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
    	<#include "sco/common/masterDataType.js" />
    	<#include "layout/center.js" />
		<#include "sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTemplate/supplierEvaluateTemplate/accessorySupplierEvaluateTemplate.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<!-- 供应商考评表模板-商品 --> 
	<div id="supplierEvaluateTemplate_toolbar">
		<form id="signedQty_search"  method="post" enctype="multipart/form-data">
			<a onclick="supplierEvaluateTemplateFn.save();" id="save" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
				保存
			</a>
			<a onclick="parent.pubTab.closeCurrTab();" id="close" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
				关闭
			</a><br/>
			<table style="width:860px">
				<tr>
					<td style="text-align:right;width:60px">
						<label style="color: red;" >*</label>考评表名称:
					</td>
					<td style="text-align:left;width:104px">
						<input id="templateName" style="width:120px;" class="easyui-validatebox"  data-options="required:true,validType:'length[1,33]'" />
					</td>
					<td style="text-align:right;width:50px">
						<label style="color: red;" >*</label>考核周期:
					</td>
					<td style="text-align:left;width:104px">
						<input class="easyui-datebox list-input"  id="minCreated" data-options="required:true,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;"/>
					</td>
					<td style="text-align:right;width:10px">
						至:				
					</td>
					<td style="text-align:left;width:104px">
						<input class="easyui-datebox list-input"  id="maxCreated" data-options="required:true,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;"/>
					</td>
					<td style="text-align:right;width:60px">
						<label style="color: red;" >*</label>考评表满分:
					</td>
					<td style="text-align:left;width:104px">
						<input id="fullScore" style="width:120px;" class="easyui-numberbox" data-options="required:true" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
					</td>
				</tr>
			</table><br/>
			<div>评分项:</div>
			<table id="table">
				<tr>
					<td>
						<table style="width:800px;border:1px solid grey">
							<tr>
								<td style="text-align:left">
									<table style="margin-top:10px;margin-bottom:10px">
										<tr>
											<td style="text-align:right;width:70px">
												<label style="color: red;" >*</label>评分项名称:
											</td>
											<td style="width:120px;">
												<input id="evaluateItemName" style="width:120px;" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]'" />
											</td>
											<td style="text-align:right;width:60px">
												<label style="color: red;" >*</label>权重(%):
											</td>
											<td style="width:120px;">
												<input id="weight" style="width:120px;" class="easyui-validatebox" data-options="required:true" />
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
											<td>
												<textarea id="according" style="width:210px;height:100px;max-width:210px;max-height:100px;disabled:disabled" class="easyui-validatebox" validtype="length[0,333]" ></textarea>
											</td>
											<td></td>
											<td style="text-align:right;width:80px">
												评分标准:
											</td>
											<td>
												<textarea id="standard" style="width:210px;height:100px;max-width:210px;max-height:100px"class="easyui-validatebox" validtype="length[0,333]"></textarea>
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
							<a onclick="supplierEvaluateTemplateFn.addMarkOptions();" plain="true" class="easyui-linkbutton"  data-options="iconCls:'add'"></a>
							<a onclick="supplierEvaluateTemplateFn.removeMarkOptions(this);" plain="true" class="easyui-linkbutton"  data-options="iconCls:'close'"></a>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
</#compress>