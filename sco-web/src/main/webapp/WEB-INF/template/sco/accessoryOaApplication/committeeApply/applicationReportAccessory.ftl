<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	<#include "sco/accessoryOaApplication/committeeApply/applicationReportAccessory.js" />
</script>
<style type="text/css">
table{
white-space:nowrap;
}
.tablewidth td,.tablewidth th{padding:5px 0}
.tablewidth td{width:180px}
.tablewidth th{width:180px;text-align:left; font-weight: bold;}
</style>
</head>
<body class="easyui-layout">
<div>
	 	<table>
	 	<input name="subscribeAccessoryCount" id="subscribeAccessoryCount" type="hidden"  value="${subscribeAccessoryCount}"/>
	 	<input name="quotedCodes" id="quotedCodes" type="hidden"  value="${quotedCodes}"/>
	 	<input name="applicationCodeNow" id="applicationCodeNow" type="hidden"  value="${applicationCodeNow}"/>
	 		<tr>
	 			<th>
					<a id="saveForm" onclick="applicationReportAccessoryFn.saveCommitteeReport('${quotedCodes}','${applicationCodeNow}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'" ;">
							保存
					</a>
					
	 			</th>
	 		</tr>
	 	</table>
	 </div>
<form id="applicationReportAccessory_form" method="post">
<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
<legend  style="color:#666;padding:0 3px;"><b>本案提报采购委员会原因:</b></legend>
<table >
	<tbody>
		<tr>
		<td style="padding:10px"><input type="radio" name="cause" value="本次合同估值符合要求" <#if applicationReportAccessory.cause=="本次合同估值符合要求"> checked="checked" </#if> onclick="applicationReportAccessoryFn.doShowAmount()"/>本次合同估值符合要求</td>
		<td style="padding:10px"><input type="radio" name="cause" value="累计采购金额符合要求" <#if applicationReportAccessory.cause=="累计采购金额符合要求"> checked="checked" </#if> onclick="applicationReportAccessoryFn.doShowLjAmount()"/>累计采购金额符合要求</td>
		<td style="padding:10px"><input type="radio" name="cause" value="项目对公司发展具有一定影响" <#if applicationReportAccessory.cause=="项目对公司发展具有一定影响"> checked="checked" </#if> onclick="applicationReportAccessoryFn.doHideAll()"/>项目对公司发展具有一定影响</td>
		 </tr>
		 <#if applicationReportAccessory.cause=="">
		 <tr id="amount" style="display:none;"><td colspan="3">本次合同估值超过<input type="text" class="easyui-numberbox" data-options="min:0,max:9999999999,precision:2"  id="gzMoney" name="gzMoney" style="width: 120px;" value="${applicationReportAccessory.gzMoney}" />万元</td></tr>
		 <tr id="ljamount" style="display:none;"><td></td><td colspan="2"><input type="text" class="easyui-validatebox" data-options="validType:'length[0,10]'"  id="accumulativeYear" name="accumulativeYear" style="width: 120px;" value="${applicationReportAccessory.accumulativeYear}" />累计金额超过<input type="text" class="easyui-numberbox" data-options="min:0,max:9999999999,precision:2"  id="ljMoney" name="ljMoney" style="width: 120px;" value="${applicationReportAccessory.ljMoney}" />万元</td></tr>
		 <#elseif applicationReportAccessory.cause=="本次合同估值符合要求">
		 <tr id="amount" ><td colspan="3">本次合同估值超过<input type="text" class="easyui-numberbox" data-options="min:0,max:9999999999,precision:2"  id="gzMoney" name="gzMoney" style="width: 120px;" value="${applicationReportAccessory.gzMoney}" />万元</td></tr>
		 <tr id="ljamount" style="display:none;"><td></td><td colspan="2"><input type="text" class="easyui-validatebox" data-options="validType:'length[0,10]'"  id="accumulativeYear" name="accumulativeYear" style="width: 120px;" value="${applicationReportAccessory.accumulativeYear}" />累计金额超过<input type="text" class="easyui-numberbox" data-options="min:0,max:9999999999,precision:2"  id="ljMoney" name="ljMoney" style="width: 120px;" value="${applicationReportAccessory.ljMoney}" />万元</td></tr>
		 <#elseif applicationReportAccessory.cause=="累计采购金额符合要求">
		 <tr id="amount" style="display:none;"><td colspan="3">本次合同估值超过<input type="text" class="easyui-numberbox" data-options="min:0,max:9999999999,precision:2"  id="gzMoney" name="gzMoney" style="width: 120px;" value="${applicationReportAccessory.gzMoney}" />万元</td></tr>
		 <tr id="ljamount"><td></td><td colspan="2"><input type="text" class="easyui-validatebox" data-options="validType:'length[0,10]'"  id="accumulativeYear" name="accumulativeYear" style="width: 120px;" value="${applicationReportAccessory.accumulativeYear}" />累计金额超过<input type="text" class="easyui-numberbox" data-options="min:0,max:9999999999,precision:2"  id="ljMoney" name="ljMoney" style="width: 120px;" value="${applicationReportAccessory.ljMoney}" />万元</td></tr>
		 <#elseif applicationReportAccessory.cause=="项目对公司发展具有一定影响">
		 <tr id="amount" style="display:none;"><td colspan="3">本次合同估值超过<input type="text" class="easyui-numberbox" data-options="min:0,max:9999999999,precision:2"  id="gzMoney" name="gzMoney" style="width: 120px;" value="${applicationReportAccessory.gzMoney}" />万元</td></tr>
		 <tr id="ljamount" style="display:none;"><td></td><td colspan="2"><input type="text" class="easyui-validatebox" data-options="validType:'length[0,10]'"  id="accumulativeYear" name="accumulativeYear" style="width: 120px;" value="${applicationReportAccessory.accumulativeYear}" />累计金额超过<input type="text" class="easyui-numberbox" data-options="min:0,max:9999999999,precision:2"  id="ljMoney" name="ljMoney" style="width: 120px;" value="${applicationReportAccessory.ljMoney}" />万元</td></tr>
		 </#if>
	</tbody>
</table>
</fieldset>
<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
<legend  style="color:#666;padding:0 3px;"><b>提审采购内容:</b></legend>
	<table class="tableForm">
		<tbody>
			<tr>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>提审采购内容:</span></th>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,validType:'length[0,160]'"  id="content" name="content" style="width: 180px;" value="${applicationReportAccessory.content}" /></td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>提审部门:</span></th>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,33]'"  id="departments" name="departments" value="${applicationReportAccessory.departments!'辅料采购部'}" style="width: 120px;"  /></td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>经办人:</span></th>
				<td ><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,10]'"  id="responsiblePerson" name="responsiblePerson" readonly="readonly" style="background-color: rgb(235, 235, 228);width: 120px;"
					value="${applicationReportAccessory.responsiblePerson}" /></td>
			</tr>
			<tr>
				<th align="right" style="width: 124px;"><span class="txtCenter"><font color="red">*</font>收到申购日期:</span></th>
				<td><input class="easyui-datebox filterInput" filterName="subscribeBillDate" type="text" name="subscribeBillDate" id="subscribeBillDate" data-options="required:true,editable:false"
					style="width: 124px;"  value='${(applicationReportAccessory.subscribeBillDate?string("yyyy-MM-dd"))!}'/></td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>提审日期:</span></th>
				<td><input class="easyui-datebox filterInput" filterName="auditDate" type="text" name="auditDate" id="auditDate" data-options="required:true,editable:false" style="width: 124px;"
					value='${(applicationReportAccessory.auditDate?string("yyyy-MM-dd"))!}' /></td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>紧急程度:</span></th>
				<td ><select id="emergency" name="emergency" panelHeight="auto" value="${applicationReportAccessory.emergency}" style="width: 124px;"
					class="easyui-combobox" data-options="required:true,editable:false">
						<option value=""></option>
						<option<#if applicationReportAccessory.emergency=="紧急"> selected="selected" </#if> value="紧急" >紧急</option>
						<option<#if applicationReportAccessory.emergency=="一般"> selected="selected" </#if> value="一般" >一般</option>
				</select></td>
			</tr>
			<tr>
				<th align="right" style="width: 124px;"><span class="txtCenter"><font color="red">*</font>要求到货日期:</span></th>
				<td><input class="easyui-datebox filterInput" filterName="requirementsDwDate" type="text" name="requirementsDwDate" id="requirementsDwDate" data-options="required:true,editable:false"
					style="width: 124px;"  value='${(applicationReportAccessory.requirementsDwDate?string("yyyy-MM-dd"))!}'/></td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>活动档期名称:</span></th>
				<td colspan="3"><input type="text" class="easyui-validatebox" data-options="required:true,validType:'length[0,33]'"  id="activityDqDate" name="activityDqDate" style="width: 120px;"
					value="${applicationReportAccessory.activityDqDate}" /></td>
			</tr>
			<tr>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>是否有申购单:</span></th>
				<td><select id="haveornotSubscribeBill" name="haveornotSubscribeBill" panelHeight="auto" value="${applicationReportAccessory.haveornotSubscribeBill}" style="width: 124px;"
					class="easyui-combobox" data-options="required:true,editable:false">
						<option value=""></option>
						<option<#if applicationReportAccessory.haveornotSubscribeBill=="有"> selected="selected" </#if> value="有" >有</option>
						<option<#if applicationReportAccessory.haveornotSubscribeBill=="无"> selected="selected" </#if> value="无" >无</option>
				</select></td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>是否审立项目:</span></th>
				<td colspan="3"><select id="isslItem" name="isslItem" panelHeight="auto" value="${applicationReportAccessory.isslItem}" style="width: 124px;" class="easyui-combobox"
					data-options="required:true,editable:false, onChange:applicationReportAccessoryFn.doShowManager " >
						<option value=""></option>
						<option <#if applicationReportAccessory.isslItem=="是"> selected="selected" </#if> value="是" >是</option>
						<option <#if applicationReportAccessory.isslItem=="否"> selected="selected" </#if> value="否" >否</option>
				</select></td>
				</tr>
				<#if applicationReportAccessory.isslItem=="是">
				<tr id="manager">
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>项目经理:</span></th>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,validType:'length[0,10]'"  id="projectManager" name="projectManager" style="width: 120px;" value="${applicationReportAccessory.projectManager}" /></td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>项目预算:</span></th>
				<td colspan="3"><input type="text" class="easyui-numberbox" data-options="required:true,min:0,max:9999999999,precision:2"  id="projectBudget" name="projectBudget" style="width: 120px;" value="${applicationReportAccessory.projectBudget}" /></td>
			</tr>
			<#else>
			<tr id="manager" style="display:none;">
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>项目经理:</span></th>
				<td><input type="text" class="easyui-validatebox" data-options="validType:'length[0,10]'"  id="projectManager" name="projectManager" style="width: 120px;" value="${applicationReportAccessory.projectManager}" /></td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>项目预算:</span></th>
				<td colspan="3"><input type="text" class="easyui-numberbox" data-options="min:0,max:9999999999,precision:2"  id="projectBudget" name="projectBudget" style="width: 120px;" value="${applicationReportAccessory.projectBudget}" /></td>
			</tr>
			</#if>
			<tr>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>单项合同估值:</span></th>
				<td><input type="text" class="easyui-numberbox" data-options="required:true,min:0,max:9999999999,precision:2"  id="dxhtgz" name="dxhtgz" style="width: 120px;" value="${applicationReportAccessory.dxhtgz}" />万元</td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>采购累计:</span></th>
				<td colspan="3"><input type="text" class="easyui-numberbox" data-options="required:true,min:0,max:9999999999,precision:2"  id="cglj" name="cglj" style="width: 120px;" value="${applicationReportAccessory.cglj}" />万元</td>
			</tr>
			<tr>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>使用范畴:</span></th>
				<td colspan="6"><textarea style="width: 820px;" id="syfw" name="syfw" class="easyui-validatebox" data-options="required:true,validType:'length[0,333]'">${applicationReportAccessory.syfw}</textarea></td>
			</tr>
		</tbody>
	</table>
</fieldset>
<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
<legend  style="color:#666;padding:0 3px;"><b>申购产品信息</b></legend>
	<table class="tableForm">
		<tbody>
			<#list subscribeAccessoryList as subscribeAccessory>
			<tr>
				<th align="right" style="width: 120px;"><span class="txtCenter">产品名称:</span></th>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,200]'"  id="subscribeAccessoryTextList[${subscribeAccessory_index}].accessoryName" name="subscribeAccessoryTextList[${subscribeAccessory_index}].accessoryName"
					 style="width: 180px;" value="${subscribeAccessory.accessoryName}" /></td>
				<th align="right" style="width: 120px;"><span class="txtCenter">询价单号:</span></th>
				<td><input type="text" class="easyui-validatebox" data-options="required:true,editable:false,validType:'length[0,100]'"  id="subscribeAccessoryTextList[${subscribeAccessory_index}].enquiryCode" name="subscribeAccessoryTextList[${subscribeAccessory_index}].enquiryCode"
					readonly="readonly" style="background-color: rgb(235, 235, 228);width: 120px;" value="${subscribeAccessory.enquiryCode}" /></td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>询价数量:</span></th>
				<td><select  id="subscribeAccessoryTextList[${subscribeAccessory_index}].quotedCount" name="subscribeAccessoryTextList[${subscribeAccessory_index}].enquiryCount" filterName="subscribeAccessoryTextList[${subscribeAccessory_index}].enquiryCount" style="width: 124px;" class="easyui-combobox" data-options="required:true,editable:false">
						<option value=""></option> <#list subscribeAccessory.accessoryEnquiryQuotedCountList as list>
						<option<#if list.quotedCount==subscribeAccessory.enquiryCount> selected="selected" </#if> value="${list.quotedCount}">${list.quotedCount}</option> </#list>
						<select></td>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>采购数量:</span></th>
				<td><input type="text" class="easyui-numberbox" data-options="required:true,validType:'length[0,10]'"  id="subscribeAccessoryTextList[${subscribeAccessory_index}].purchaseCount" name="subscribeAccessoryTextList[${subscribeAccessory_index}].purchaseCount"
					style="width: 120px;" value="${subscribeAccessory.purchaseCount}" /></td>
			</tr>
			<tr>
				<th align="right" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>规格/制作要求:</span></th>
				<td colspan="5"><table>
				<tr><td><table class="tablewidth" ><tr><th>原材料名称</th><th>材料</th><th>尺寸</th></tr><#list subscribeAccessory.accessoryEnquiryMaterialList as accessoryEnquiryMaterial><tr><td>${accessoryEnquiryMaterial.materialName}</td><td>${accessoryEnquiryMaterial.material}</td><td>${accessoryEnquiryMaterial.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="tablewidth" ><tr><th>辅料名称</th><th>材料</th><th>尺寸</th></tr><#list subscribeAccessory.accessoryEnquiryAccessoryList as accessoryEnquiryAccessory><tr><td>${accessoryEnquiryAccessory.accessoryName}</td><td>${accessoryEnquiryAccessory.material}</td><td>${accessoryEnquiryAccessory.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="tablewidth" ><tr><th>内外包装材料名称</th><th>材料</th><th>尺寸</th></tr><#list subscribeAccessory.accessoryEnquiryPackingList as accessoryEnquiryPacking><tr><td>${accessoryEnquiryPacking.packingName}</td><td>${accessoryEnquiryPacking.packingMaterial}</td><td>${accessoryEnquiryPacking.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="tablewidth" ><tr><th>工艺名称</th><th>工艺要求</th></tr><#list subscribeAccessory.accessoryEnquiryTechnologyList as accessoryEnquiryTechnology><tr><td>${accessoryEnquiryTechnology.technologyName}</td><td>${accessoryEnquiryTechnology.technologyInfo}</td></tr> </#list></table></td></tr>
				<tr><td><table class="tablewidth" ><tr><th>要求名称</th><th>要求内容</th></tr><#list subscribeAccessory.accessoryEnquiryElseList as accessoryEnquiryElse><tr><td>${accessoryEnquiryElse.name}</td><td>${accessoryEnquiryElse.info}</td></tr> </#list></table></td></tr>
				</table></td>
				<td><input type="hidden"   id="subscribeAccessoryTextList[${subscribeAccessory_index}].intentionCode" name="subscribeAccessoryTextList[${subscribeAccessory_index}].intentionCode"
					value="${subscribeAccessory.intentionCode}" /></td>
			</tr>
			</#list>
		</tbody>
	</table>
	</fieldset>
	<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
	<table class="tableForm">
		<tbody>
			<tr>
				<th align="left" style="width: 120px;"><span class="txtCenter"><font color="red">*</font>经办意见:</span></th>
				<td colspan="6"><textarea style="width: 820px;height:240px" name="opinion" id="opinion" class="easyui-validatebox" data-options="required:true,validType:'length[0,1000]'">${applicationReportAccessory.opinion}</textarea></td>
			</tr>
		</tbody>
	</table>
</fieldset>
</form>
</body>
</html>
</#compress>