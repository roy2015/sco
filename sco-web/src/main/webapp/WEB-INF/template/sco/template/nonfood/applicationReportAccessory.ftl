<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
table{
white-space:nowrap;
font-size:13px;
}
table input{border:1px solid #ccc;padding: 4px;
border-radius: 5px;}
table td,table th{padding:5px}
table th{text-align:left; font-weight: inherit;}
</style>
</head>
<body>
<!-- <fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
<legend style="color:#666;padding:0 3px;"><b>本案提报采购委员会原因</b></legend>
<table>
     <tr>
		<td><input type="radio" name="cause" value="本次合同估值符合要求" <#if applicationReportAccessory.cause=="本次合同估值符合要求"> checked="checked" </#if> onclick="applicationReportAccessoryFn.doShowAmount()"/>本次合同估值符合要求</td>
		<td><input type="radio" name="cause" value="累计采购金额符合要求" <#if applicationReportAccessory.cause=="累计采购金额符合要求"> checked="checked" </#if> onclick="applicationReportAccessoryFn.doShowLjAmount()"/>累计采购金额符合要求</td>
		<td><input type="radio" name="cause" value="项目对公司发展具有一定影响" <#if applicationReportAccessory.cause=="项目对公司发展具有一定影响"> checked="checked" </#if> onclick="applicationReportAccessoryFn.doHideAll()"/>项目对公司发展具有一定影响</td>
		 </tr>
		 <#if applicationReportAccessory.cause=="">
		 <tr id="amount" style="display:none;"><td colspan="3">本次合同估值超过<input type="text"    id="gzMoney" name="gzMoney" style="width: 120px;" value="${applicationReportAccessory.gzMoney}" />万元</td></tr>
		 <tr id="ljamount" style="display:none;"><td></td><td colspan="2"><input type="text"    id="accumulativeYear" name="accumulativeYear" style="width: 120px;" value="${applicationReportAccessory.accumulativeYear}" />累计金额超过<input type="text"    id="ljMoney" name="ljMoney" style="width: 120px;" value="${applicationReportAccessory.ljMoney}" />万元</td></tr>
		  <#elseif applicationReportAccessory.cause=="本次合同估值符合要求">
		 <tr id="amount" ><td colspan="3">本次合同估值超过<input type="text"    id="gzMoney" name="gzMoney" style="width: 120px;" value="${applicationReportAccessory.gzMoney}" />万元</td></tr>
		 <tr id="ljamount" style="display:none;"><td></td><td colspan="2"><input type="text"    id="accumulativeYear" name="accumulativeYear" style="width: 120px;" value="${applicationReportAccessory.accumulativeYear}" />累计金额超过<input type="text"    id="ljMoney" name="ljMoney" style="width: 120px;" value="${applicationReportAccessory.ljMoney}" />万元</td></tr>
		 <#elseif applicationReportAccessory.cause=="累计采购金额符合要求">
		 <tr id="amount" style="display:none;"><td colspan="3">本次合同估值超过<input type="text"    id="gzMoney" name="gzMoney" style="width: 120px;" value="${applicationReportAccessory.gzMoney}" />万元</td></tr>
		 <tr id="ljamount"><td></td><td colspan="2"><input type="text"    id="accumulativeYear" name="accumulativeYear" style="width: 120px;" value="${applicationReportAccessory.accumulativeYear}" />累计金额超过<input type="text"    id="ljMoney" name="ljMoney" style="width: 120px;" value="${applicationReportAccessory.ljMoney}" />万元</td></tr>
		 <#elseif applicationReportAccessory.cause=="项目对公司发展具有一定影响">
		 <tr id="amount" style="display:none;"><td colspan="3">本次合同估值超过<input type="text"    id="gzMoney" name="gzMoney" style="width: 120px;" value="${applicationReportAccessory.gzMoney}" />万元</td></tr>
		 <tr id="ljamount" style="display:none;"><td></td><td colspan="2"><input type="text"    id="accumulativeYear" name="accumulativeYear" style="width: 120px;" value="${applicationReportAccessory.accumulativeYear}" />累计金额超过<input type="text"    id="ljMoney" name="ljMoney" style="width: 120px;" value="${applicationReportAccessory.ljMoney}" />万元</td></tr>
		 </#if>
</table>
</fieldset> -->

<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
<legend style="color:#666;padding:0 3px;"><b>提审采购内容</b></legend>
	<table class="tableForm">
	
			<tr>
				<th align="right"><span ><font color="red">*</font>采购物品名称:</span></th>
				<td><input type="text"   id="purchaseName" name="purchaseName" style="width: 180px;" value="${applicationReportAccessory.purchaseName}" /></td>
				<th align="right"><span ><font color="red">*</font>申购数量:</span></th>
				<td><input type="text"   id="purchaseCount" name="purchaseCount" style="width: 120px;" value="${applicationReportAccessory.purchaseCount}" /></td>
				<th align="right"><span ><font color="red">*</font>申购单价:</span></th>
				<td><input type="text"    id="purchasePrice" name="purchasePrice" style="width: 120px;"
					value="${applicationReportAccessory.purchasePrice}" /></td>
				<th align="right"><span ><font color="red">*</font>申购部门:</span></th>
				<td><input type="text"    id="purchaseDepartment" name="purchaseDepartment" style="width: 120px;" value="${applicationReportAccessory.purchaseDepartment}" /></td>
			</tr>
			<tr>
				<th align="right"><span ><font color="red">*</font>经办人:</span></th>
				<td><input type="text"   id="responsiblePerson" name="responsiblePerson" readonly="readonly" style="background-color: rgb(235, 235, 228);width: 120px;"
					value="${applicationReportAccessory.responsiblePerson}" /></td>
				<th align="right"><span ><font color="red">*</font>紧急程度:</span></th>
				<td><input type="text"   id="emergency" name="emergency" style="width: 120px;" value="${applicationReportAccessory.emergency}" /></td>
				<th align="right"><span ><font color="red">*</font>收到申购单日期:</span></th>
				<td><input  filterName="subscribeBillDate" type="text" name="subscribeBillDate" id="subscribeBillDate" 
					style="width: 124px;"  value='${(applicationReportAccessory.subscribeBillDate?string("yyyy-MM-dd"))!}'/></td>
				<th align="right"><span ><font color="red">*</font>要求到货日期:</span></th>
				<td><input  filterName="requirementsDwDate" type="text" name="requirementsDwDate" id="requirementsDwDate" 
					style="width: 124px;"  value='${(applicationReportAccessory.requirementsDwDate?string("yyyy-MM-dd"))!}'/></td>
			</tr>
			
	</table>
	</fieldset>
	
	<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
<legend style="color:#666;padding:0 3px;"><b>申购产品信息</b></legend>
	<table class="tableForm">
		<#list subscribeAccessoryList as subscribeAccessory>
			<tr>
				<th align="right"><span >产品名称:</span></th>
				<td><input type="text"    id="subscribeAccessoryTextList[${subscribeAccessory_index}].accessoryName" name="subscribeAccessoryTextList[${subscribeAccessory_index}].accessoryName"
					readonly="readonly" style="background-color: rgb(235, 235, 228);width: 180px;" value="${subscribeAccessory.accessoryName}" /></td>
				<th align="right"><span >询价单号:</span></th>
				<td><input type="text"   id="subscribeAccessoryTextList[${subscribeAccessory_index}].enquiryCode" name="subscribeAccessoryTextList[${subscribeAccessory_index}].enquiryCode"
					readonly="readonly" style="background-color: rgb(235, 235, 228);width: 120px;" value="${subscribeAccessory.enquiryCode}" /></td>
				<th align="right"><span ><font color="red">*</font>询价数量:</span></th>
				<td><input type="text"    id="enquiryCount" name="enquiryCount" style="width: 120px;" value="${subscribeAccessory.enquiryCount}" /></td>
				<th align="right"><span ><font color="red">*</font>采购数量:</span></th>
				<td><input type="text"    id="subscribeAccessoryTextList[${subscribeAccessory_index}].purchaseCount" name="subscribeAccessoryTextList[${subscribeAccessory_index}].purchaseCount"
					style="width: 120px;" value="${subscribeAccessory.purchaseCount}" /></td>
			</tr>
			<tr>
				<th align="right"><span ><font color="red">*</font>规格/制作要求:</span></th>
				<td colspan="5"><table>
				<tr><td><table class="tablewidth" ><tr><th><b>原材料名称</b></th><th><b>材料</b></th><th><b>尺寸</b></th></tr><#list subscribeAccessory.accessoryEnquiryMaterialList as accessoryEnquiryMaterial><tr><td>${accessoryEnquiryMaterial.materialName}</td><td>${accessoryEnquiryMaterial.material}</td><td>${accessoryEnquiryMaterial.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="tablewidth" ><tr><th><b>辅料名称</b></th><th><b>材料</b></th><th><b>尺寸</b></th></tr><#list subscribeAccessory.accessoryEnquiryAccessoryList as accessoryEnquiryAccessory><tr><td>${accessoryEnquiryAccessory.accessoryName}</td><td>${accessoryEnquiryAccessory.material}</td><td>${accessoryEnquiryAccessory.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="tablewidth" ><tr><th><b>内外包装材料名称</b></th><th><b>材料</b></th><th><b>尺寸</b></th></tr><#list subscribeAccessory.accessoryEnquiryPackingList as accessoryEnquiryPacking><tr><td>${accessoryEnquiryPacking.packingName}</td><td>${accessoryEnquiryPacking.packingMaterial}</td><td>${accessoryEnquiryPacking.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="tablewidth" ><tr><th><b>工艺名称</b></th><th><b>工艺要求</b></th></tr><#list subscribeAccessory.accessoryEnquiryTechnologyList as accessoryEnquiryTechnology><tr><td>${accessoryEnquiryTechnology.technologyName}</td><td>${accessoryEnquiryTechnology.technologyInfo}</td></tr> </#list></table></td></tr>
				<tr><td><table class="tablewidth" ><tr><th><b>要求名称</b></th><th><b>要求内容</b></th></tr><#list subscribeAccessory.accessoryEnquiryElseList as accessoryEnquiryElse><tr><td>${accessoryEnquiryElse.name}</td><td>${accessoryEnquiryElse.info}</td></tr> </#list></table></td></tr>
				</table></td>
				<td><input type="hidden"   id="subscribeAccessoryTextList[${subscribeAccessory_index}].intentionCode" name="subscribeAccessoryTextList[${subscribeAccessory_index}].intentionCode"
					value="${subscribeAccessory.intentionCode}" /></td>
			</tr>
			</#list>
			<tr>
				<th align="left"><span ><font color="red">*</font>经办意见:</span></th>
				<td colspan="6"><textarea style="width: 820px;height:240px" name="opinion" id="opinion"  >${applicationReportAccessory.opinion}</textarea></td>
			</tr>
	</table>
	</fieldset>
</body>
</html>
