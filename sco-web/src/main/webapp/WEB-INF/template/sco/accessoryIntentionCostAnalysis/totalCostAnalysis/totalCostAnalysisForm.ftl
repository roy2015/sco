<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script type="text/javascript">
<#include "sco/accessoryIntentionCostAnalysis/totalCostAnalysis/totalCostAnalysisForm.js" />
</script>
 <link rel="stylesheet" href="css/table.min.css" type="text/css" />
     <style type="text/css" media="screen" type="text/css">
    	#contrastResult{
    	 cellspacing:0;
    	 cellpadding:0;
    	}
    	.showremarks{
    	text-align:center;
    	width:700px;
    	font-size: 12px;
    	padding:0px;
    	}
    	.hideremarks{
    	text-align:center;
    	width:200px;
    	font-size: 12px;
    	padding:0px;
    	}
    	#rowtitle td{
    	font-size: 13px;
    	font-weight:bold;
    	background-color:rgb(0,146,240);
    	}
    	#deductptcostValue td{
    	color : red;
    	}
    	#nwpackagsubtotalValue td{
    	color : red;
    	}
    	#subTotal td{
    	color : red;
    	}
    	<!--控制投料表的边框-->
    	#innertable{ 
		border-collapse: collapse;
		border: 0px solid #999;
		} 
		#innertable td { 
		border-top: 0; 
		border-right: 1px solid #999; 
		border-bottom: 1px solid #999; 
		border-left: 0; 
		} 
		/*table样式*/
		.table-bordered {
border: 1px solid #ddd;
border-collapse: separate;
border-left: 0;
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
border-radius: 4px;
}
.table {

/* margin-bottom: 20px; */
}
table {
background-color: transparent;
border-collapse: collapse;
border-spacing: 0;
}
table {
display: table;
border-collapse: separate;
border-spacing: 0px;
border-color: gray;
}
.table-bordered thead:first-child tr:first-child>th:first-child, .table-bordered tbody:first-child tr:first-child>td:first-child, .table-bordered tbody:first-child tr:first-child>th:first-child {
-webkit-border-top-left-radius: 4px;
border-top-left-radius: 4px;
-moz-border-radius-topleft: 4px;
}
.table-bordered caption+thead tr:first-child th, .table-bordered caption+tbody tr:first-child th, .table-bordered caption+tbody tr:first-child td, .table-bordered colgroup+thead tr:first-child th, .table-bordered colgroup+tbody tr:first-child th, .table-bordered colgroup+tbody tr:first-child td, .table-bordered thead:first-child tr:first-child th, .table-bordered tbody:first-child tr:first-child th, .table-bordered tbody:first-child tr:first-child td {
border-top: 0;
}
.table caption+thead tr:first-child th, .table caption+thead tr:first-child td, .table colgroup+thead tr:first-child th, .table colgroup+thead tr:first-child td, .table thead:first-child tr:first-child th, .table thead:first-child tr:first-child td {
border-top: 0;
}
.table thead th {
vertical-align: bottom;
}
.table-bordered th, .table-bordered td {
border-left: 1px solid #ddd;
}
.table th {
font-weight: bold;
background-color:#ececec;
}
.table th, .table td {
padding: 3px;
line-height: 1.5;
text-align: left;
vertical-align: top;
border-top: 0px solid #ddd;
white-space: pre-wrap;
word-break:break-all; /*支持IE，chrome，FF不支持*/
word-wrap:break-word;
}
th {
font-weight: bold;
}
td, th {
display: table-cell;
vertical-align: inherit;
}
</style>
</head>
<body>
<div id="totalcostanalysis" style="width:auto" style="margin-top:25px">
<form id="zjfx_form" method="post" enctype="multipart/form-data">


<a id="close" onclick="totalcostanalysisFormFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭 </a>
		<a id="save" onclick="totalcostanalysisFormFn.saveFile();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">保存</a>
		<a id="import" onclick="totalcostanalysisFormFn.export2Excel();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">导出Excel</a>

</form>
<#setting number_format="#,##0.000">
<input name="json" id="json" type="hidden" value="${totalcostanalysisForm.json}" /> 

<table id="contrastResult" class="table table-bordered" style="width:700px">
           <#if totalcostanalysisForm.intentionSupplierCodeShow??>
			<tr>
			<td rowspan="${totalcostanalysisForm.showLine}" style="text-align:center;vertical-align: middle;"><div style=" width:120px"><b>意向品名称</b></div></td>
			<td rowspan="${totalcostanalysisForm.showLine}" style="text-align:center;vertical-align: middle;"><div style=" width:320px"><b>询价单意向品规格</b></div></td>
			<td rowspan="${totalcostanalysisForm.showLine}" style="text-align:center;vertical-align: middle;"><div style=" width:120px"><b>报价数量</b></div></td>
			<td rowspan="${totalcostanalysisForm.showLine}" style="text-align:center;vertical-align: middle;"><div style=" width:120px"><b>采购数量</b></div></td>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td><div style=" width:220px"><b>供应商编号-公司名称：<br>${supplierForm.intentionSupplierCode}-<br>${supplierForm.intentionSupplierName}</b></div></td>
			</#list>
			</tr>
			</#if>
			<#if totalcostanalysisForm.contactsShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>联系人：${supplierForm.contacts}</td>
			</#list>
			</tr>
			</#if>
			<#if totalcostanalysisForm.phoneShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>联系电话：${supplierForm.phone}</td>
			</#list>
			</tr>
			</#if>
			<#if totalcostanalysisForm.companySiteShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>公司地址：<br>${supplierForm.companySite}</td>
		    </#list>
			</tr>
			</#if>
			<#if totalcostanalysisForm.factorySiteShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>工厂地址：<br>${supplierForm.factorySite}</td>
			</#list>
			</tr>
			</#if>
			<#if totalcostanalysisForm.registerCapitalShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>注册资本(万元)：${(supplierForm.registerCapital)!}</td>
			</#list>
			</tr>
			</#if>
			
			<#if totalcostanalysisForm.yearTurnoverShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>年营业额(万元)：${(supplierForm.yearTurnover)!}</td>
			</#list>
			</tr>
			</#if>
			
			<#if totalcostanalysisForm.factoryAreaShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>工厂面积(㎡)：${(supplierForm.factoryArea)!}</td>
			</#list>
			</tr>
			</#if>
			
			<#if totalcostanalysisForm.staffCountShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>工人数：${(supplierForm.staffCount?string)!?replace(".000","")}</td>
			</#list>
			</tr>
			</#if>
			
			<#if totalcostanalysisForm.dailyCapacityShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>日产能：${supplierForm.dailyCapacity}</td>
			</#list>
			</tr>
			</#if>
			
			<#if totalcostanalysisForm.hzgppShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>合作过品牌：
			${supplierForm.hzgpp}</td>
			</#list>
			</tr>
			</#if>
			<#if totalcostanalysisForm.invoiceTypeShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>发票类型(增/普票)：${supplierForm.invoiceType}</td>
			</#list>
			</tr>
			</#if>
			
			<#if totalcostanalysisForm.quotedCurrencyShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>报价币种：${supplierForm.quotedCurrency}</td>
			</#list>
			</tr>
			</#if>
			
			<#if totalcostanalysisForm.taxRateShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>税率(%)：${supplierForm.taxRate}%</td>
			</#list>
			</tr>
			</#if>
			
			<#if totalcostanalysisForm.paymentTypeShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>付款方式：${supplierForm.paymentType}</td>
			</#list>
			</tr>
			</#if>
			<#if totalcostanalysisForm.proofingContentBeforeShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>产前样打样内容：<br>${supplierForm.proofingContentBefore}</td>
			</#list>
			</tr>
			</#if>
			
			<#if totalcostanalysisForm.proofingContentBeforeShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>产前样打样评价：<br>${supplierForm.proofingEvaluateBefore}</td>
			</#list>
			</tr>
			</#if>
			<#if totalcostanalysisForm.proofingContentAfterShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>非产前样打样内容：<br>${supplierForm.proofingContentAfter}</td>
			</#list>
			</tr>
			</#if>
			
			<#if totalcostanalysisForm.proofingContentAfterShow??>
			<tr>
			<#list totalcostanalysisForm.supplierFormList as supplierForm>
			<td>非产前样打样评价：<br>${supplierForm.proofingEvaluateAfter}</td>
			</#list>
			</tr>
			</#if>
			
		<#list totalcostanalysisForm.intentionFormList as intentionForm>
		
		  <#list intentionForm.enquiryList as enquiry>
		 
		     <#list enquiry.accessoryEnquiryQuotedCountList as accessoryEnquiryQuotedCount>
		 <tr>    
		<#if accessoryEnquiryQuotedCount_index==0 && enquiry_index==0>
		<td rowspan="${intentionForm.count}" style="vertical-align: middle;"><b>${intentionForm.intentionName}</b></td>
		</#if>
		<#if accessoryEnquiryQuotedCount_index==0 >
		<div style="width:330px"><td rowspan="${enquiry.accessoryEnquiryQuotedCountList?size}">
		   <table class="table table-bordered">
				<tr><td><table class="table table-bordered"><tr><td><div style="width:110px"><b>原材料名称</b></div></td><td><div style="width:110px"><b>材料</b></div></td><td><div style="width:110px"><b>尺寸</b></div></td></tr><#list enquiry.accessoryEnquiryMaterialList as accessoryEnquiryMaterial><tr><td>${accessoryEnquiryMaterial.materialName}</td><td>${accessoryEnquiryMaterial.material}</td><td>${accessoryEnquiryMaterial.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="table table-bordered"><tr><td><div style="width:110px"><b>辅料名称</b></div></td><td><div style="width:110px"><b>材料</b></div></td><td><div style="width:110px"><b>尺寸</b></div></td></tr><#list enquiry.accessoryEnquiryAccessoryList as accessoryEnquiryAccessory><tr><td>${accessoryEnquiryAccessory.accessoryName}</td><td>${accessoryEnquiryAccessory.material}</td><td>${accessoryEnquiryAccessory.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="table table-bordered"><tr><td><div style="width:110px"><b>内外包装材料名称</b></div></td><td><div style="width:110px"><b>材料</b></div></td><td><div style="width:110px"><b>尺寸</b></div></td></tr><#list enquiry.accessoryEnquiryPackingList as accessoryEnquiryPacking><tr><td>${accessoryEnquiryPacking.packingName}</td><td>${accessoryEnquiryPacking.packingMaterial}</td><td>${accessoryEnquiryPacking.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="table table-bordered"><tr><td><div style="width:165px"><b>工艺名称</b></div></td><td><div style="width:165px"><b>工艺要求</b></div></td></tr><#list enquiry.accessoryEnquiryTechnologyList as accessoryEnquiryTechnology><tr><td>${accessoryEnquiryTechnology.technologyName}</td><td>${accessoryEnquiryTechnology.technologyInfo}</td></tr> </#list></table></td></tr>
				<tr><td><table class="table table-bordered"><tr><td><div style="width:165px"><b>要求名称</b></div></td><td><div style="width:165px"><b>要求内容</b></div></td></tr><#list enquiry.accessoryEnquiryElseList as accessoryEnquiryElse><tr><td>${accessoryEnquiryElse.name}</td><td>${accessoryEnquiryElse.info}</td></tr> </#list></table></td></tr>
		   </table>
		</td></div>
				
		 </#if>
		        <td>${(accessoryEnquiryQuotedCount.quotedCount?string)!?replace(".000","")}</td>
		         <td>${(accessoryEnquiryQuotedCount.purchaseCount?string)!?replace(".000","")}</td>
		       <#list accessoryEnquiryQuotedCount.quotedFormList as quotedForm>
		       <#if quotedForm.lastQuotedRank??>
		       <td><b>最后一次报价排行：${quotedForm.lastQuotedRank}</b><br><b>最后一次报价生产周期(天)：${(quotedForm.lastProductionCycle?string)!?replace(".000","")}</b><br><#else><td></#if><#list quotedForm.quotedDetailFormList as del><#if totalcostanalysisForm.allbjd??><b>第${((del_index+1)?string)!?replace(".000","")}次报价日期：<#if del.quotedDate??>${del.quotedDate?string("yyyy-MM-dd")}</#if></b>&nbsp;<br>报价单价(元)：${del.unitPrice}&nbsp; <br>报价总价(元)：${(del.quotedTotal)!}<br><#else><b>最后一次报价日期：</b><#if del.quotedDate??>${del.quotedDate?string("yyyy-MM-dd")}</#if>&nbsp;<br>报价单价(元)：${del.unitPrice}&nbsp;<br>报价总价(元)：${(del.quotedTotal)!}</#if></#list></td>
		       </#list>
		         </tr>
		     </#list>
		   </#list>
		</#list>
		
		<#-- <#list totalcostanalysisForm.intentionTotalFormList as intentionTotalForm>
		 <tr> 
		          <td colspan="2">最后一次询价单报价合计：</td>
		          <td >${intentionTotalForm.quotedCount}</td>
		          <td ></td>
		         
		    <#list intentionTotalForm.quotedTotalFormList as quotedTotalForm>
		     <#if quotedTotalForm.quotedDetailTotalFormList?size gt 1> 
		    <td >${quotedTotalForm.lastQuotedRank}<br>
		          <#list quotedTotalForm.quotedDetailTotalFormList as quotedDetailTotalForm>
		          第${quotedDetailTotalForm_index+1}次报价合计：${(quotedDetailTotalForm.quotedTotal)!}<br>
		           </#list>
		      </td>
		       <#else>
		       <td >${quotedTotalForm.lastQuotedRank}<br>
		          <#list quotedTotalForm.quotedDetailTotalFormList as quotedDetailTotalForm>
		          最后一次报价合计：${(quotedDetailTotalForm.quotedTotal)!}<br>
		           </#list>
		      </td>
		       </#if>
		      </#list>
		</#list>
		
		<#if totalcostanalysisForm.intentionTotalFormList??>
		<#list totalcostanalysisForm.intentionTotalFormList as intentionTotalForm>
		 <tr> 
		          <td colspan="2">最后一次询价单报价合计(换成普票)：</td>
		          <td >${intentionTotalForm.quotedCount}</td>
		          <td ></td>
		         
		    <#list intentionTotalForm.quotedTotalFormList as quotedTotalForm>
		     <#if quotedTotalForm.quotedDetailTotalFormListHs?size gt 1>  
		    <td >${quotedTotalForm.lastQuotedRank}<br>
		          <#list quotedTotalForm.quotedDetailTotalFormListHs as quotedDetailTotalForm>
		         
		          第${quotedDetailTotalForm_index+1}次报价合计：${(quotedDetailTotalForm.quotedTotal)!}<br>
		           </#list>
		           </td>
		          <#else>
		          <td >${quotedTotalForm.lastQuotedRank}<br>
		          <#list quotedTotalForm.quotedDetailTotalFormListHs as quotedDetailTotalForm>
		         
		          最后一次报价合计：${(quotedDetailTotalForm.quotedTotal)!}
		         
		           </#list>
		           
		      </td>
		      </#if>
		      </#list>
		      </tr>
		</#list>
		</#if> -->
		 <tr><td colspan="4">采购备注：</td>
		
		<td colspan="${totalcostanalysisForm.supplierFormList?size}"><input type="text" id="remark" maxlength="100" value="" style="width:100%;" onChange="totalcostanalysisFormFn.setInpoutValue()"/></td>
		
		</tr> 
		</tbody>
</table>	
</div>
<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:160px" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{text:'确定',iconCls:'save',handler:function(){totalcostanalysisFormFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){totalcostanalysisFormFn.closeSaveFileDlg()}}]">
		<br/><br/>
		<div style="margin-left:25px">文件名称: <input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:120px;"/></div>		
	</div>
	
	</body>
	
</html>
</#compress>