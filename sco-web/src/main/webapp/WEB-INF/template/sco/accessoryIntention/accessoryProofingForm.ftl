<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/accessoryIntention/accessoryProofing.js" />
</script>
</head>
<body>
	<div>
		<table class="table table-condensed" style="width:100px">
			<tr>
				<td><input type="radio" name="cause" value="有打样" checked="checked" onclick="accessoryProofingFn.selectProofing()" />有打样</td>
				<td><input type="radio" name="cause" value="无打样" onclick="accessoryProofingFn.selectNoProofing()" />无打样</td>
			</tr>
		</table>
	</div>
	<div style="padding: 8px;"></div>
	<div>
		<div id="haveProofing">
			<form id="accessoryProofing_form" method="post" enctype="multipart/form-data">
				<input name="intentionCode" id="intentionCode" type="hidden" value="${accessoryProofing.intentionCode}" /> 
				<input name="quotedCode" id="quotedCode" type="hidden" value="${accessoryProofing.quotedCode}" /> 
				<input name="enquiryCode" id="enquiryCode" type="hidden" value="${accessoryProofing.enquiryCode}" /> 
				<input name="supplierCode" id="supplierCode" type="hidden" value="${accessoryProofing.supplierCode}" /> 
				<input name="intentionSupplierCode" id="intentionSupplierCode" type="hidden" value="${accessoryProofing.intentionSupplierCode}" />
				<table class="tableForm">
					<tr>
						<td style="text-align: right;"><font color="red">*</font>打样类型:</td>
						<td>
							<select id="proofingType" name="proofingType" panelHeight="auto" value="${accessoryProofing.proofingType}" style="width: 124px;" class="easyui-combobox"
								data-options="required:true">
									<option value="产前样">产前样</option>
									<option value="非产前样">非产前样</option>
							</select>
						</td>
						<td style="text-align: right;">打样周期:</td>
						<td><input class="easyui-validatebox" data-options="editable:false,required:true" name="proofingCycle" id="proofingCycle" readonly="readonly"
							style="width: 120px; background-color: rgb(235, 235, 228);" value="${accessoryProofing.proofingCycle}" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>要求打样日期:</td>
						<td><input name="askProofingDate" id="askProofingDate" value="<#if accessoryProofing.askProofingDate??>${accessoryProofing.askProofingDate?string('yyyy-MM-dd')}</#if>"
							style="width: 124px;" class="easyui-datebox" data-options="onChange:accessoryProofingFn.jsdyzq,required:true,editable:false" />
						</td>
						<td style="text-align: right;">打样完成日期:</td>
						<td><input id="proofingDate" name="proofingDate" value="<#if accessoryProofing.proofingDate??>${accessoryProofing.proofingDate?string('yyyy-MM-dd')}</#if>" style="width: 124px;"
							class="easyui-datebox" data-options="onChange:accessoryProofingFn.jsdyzq,required:true,editable:false" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>打样内容:</td>
						<td colspan="3"><textarea name="proofingContent" style="width: 360px; height: 40px;" class="easyui-validatebox" data-options="required:true,validType:'length[1,333]'">${accessoryProofing.proofingContent}</textarea>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>打样评价:</td>
						<td colspan="3"><textarea name="proofingEvaluate" style="width: 360px; height: 40px;" class="easyui-validatebox" data-options="required:true,validType:'length[1,333]'"
								data-options="required:true">${accessoryProofing.proofingEvaluate}</textarea></td>
					</tr>
					<tr>
						<td style="text-align: right;">&nbsp&nbsp样品图片:</td>
						<td colspan="3">
							<input type="text" class="file" id="txt" style="width: 0px; visibility: hidden" />
							<input class="input_file" size="30" type="file" onchange="txt.value=this.value" id="upload_proof" name="upload" style="width: 200px;" /></td>
					</tr>
				</table>
			</form>
		</div>
		<form id="accessoryNoProofing_form" method="post">
		<input name="intentionCode" id="intentionCode" type="hidden" value="${accessoryProofing.intentionCode}" />
		<input name="quotedCode" id="quotedCode" type="hidden" value="${accessoryProofing.quotedCode}" />
		<input name="enquiryCode" id="enquiryCode" type="hidden" value="${accessoryProofing.enquiryCode}" />
		<input name="supplierCode" id="supplierCode" type="hidden" value="${accessoryProofing.supplierCode}" />
		<input name="intentionSupplierCode" id="intentionSupplierCode" type="hidden" value="${accessoryProofing.intentionSupplierCode}" />
		<input name="haveRemarks" id="haveRemarks" type="hidden" value="" />
			<div style="display: none" id="noProofing">
				<table class="tableForm">
					<tr>
						<td style="text-align: right;"><font color="red">*</font>请填入备注:</td>
						<td><textarea name="remarks" style="width: 360px; height: 40px;" class="easyui-validatebox" data-options="required:true,validType:'length[0,333]'">${accessoryProofing.remarks}</textarea></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
</#compress>
