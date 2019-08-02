<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script type="text/javascript">
<#include "sco/accessoryIntentionCostAnalysis/costAnalogy/costAnalogyForm.js" />
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
vertical-align: middle;
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
.table td input[type="text"]{width:220px;}
    </style>
</head>
<body>

<div id="costAnalogy" style="width:auto" style="margin-top:25px">

		<a id="close" onclick="costAnalogyFormFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭 </a>
		<a id="save" onclick="costAnalogyFormFn.saveFile();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">保存</a>
		<a id="import" onclick="costAnalogyFormFn.export2Excel();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">导出Excel</a>

<#setting number_format="#,##0.000">
<input name="json" id="json" type="hidden" value="${costAnalogyForm.json}" /> 
<table id="contrastResult"  class="table table-bordered" style="width:700px">
			
			<tr>
			<td><div style="width:170px"><b>品名-<br>意向品编号</b></div></td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td><div style="width:170px"><b>${supplierForm.intentionName}-<br>${supplierForm.intentionCode}</b>&nbsp;</div></td>
			</#list>
			</tr>
			<tr>
			<#if costAnalogyForm.intentionSupplierCodeShow??>
			<td><b>供应商编号-<br>供应商名称</b></td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td><b>${supplierForm.intentionSupplierCode}-<br>${supplierForm.intentionSupplierName}</b>&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.merchandiseCodeShow??>
			<td>SAP物料号</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.merchandiseCode}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<td>报价日期</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${(supplierForm.quotedDate?string("yyyy-MM-dd"))!}&nbsp;</td>
			</#list>
			</tr>
			<tr>
			<#if costAnalogyForm.quotedCountShow??>
			<td>报价数量</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${(supplierForm.quotedCount?string)!?replace(".000","")}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.contactsShow??>
			<td>联系人</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.contacts}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.phoneShow??>
			<td>联系电话</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.phone}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.companySiteShow??>
			<td>公司地址</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.companySite}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.factorySiteShow??>
			<td>工厂地址</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.factorySite}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.registerCapitalShow??>
			<td>注册资本(万元)</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${(supplierForm.registerCapital)!}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.yearTurnoverShow??>
			<td>年营业额(万元)</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${(supplierForm.yearTurnover)!}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.factoryAreaShow??>
			<td>工厂面积(㎡)</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${(supplierForm.factoryArea?string)!?replace(".000","")}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.staffCountShow??>
			<td>工人数</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${(supplierForm.staffCount?string)!?replace(".000","")}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.dailyCapacityShow??>
			<td>日产能</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.dailyCapacity}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.hzgppShow??>
			<td>合作过品牌</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.hzgpp}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.invoiceTypeShow??>
			<td>发票类型(增/普票)</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.invoiceType}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.taxRateShow??>
			<td>税率(%)</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
				<td>${supplierForm.taxRate}%&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.paymentTypeShow??>
			<td>付款方式</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.paymentType}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.proofingContentBeforeShow??>
			<td>产前样打样内容</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.proofingContentBefore}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
			<#if costAnalogyForm.proofingContentBeforeShow??>
			<td>产前样打样评价</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.proofingEvaluateBefore}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
				<#if costAnalogyForm.proofingContentAfterShow??>
			<td>非产前样打样内容</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.proofingContentAfter}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			<tr>
				<#if costAnalogyForm.proofingContentAfterShow??>
			<td>非产前样打样评价</td>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			<td>${supplierForm.proofingEvaluateAfter}&nbsp;</td>
			</#list>
			</#if>
			</tr>
			
			
			
			
			<!-- 原材料 -->
		<#list costAnalogyForm.accessoryQuotedMaterialAndSubtotalList as accessoryQuotedMaterialAndSubtotals>
		<tr>
		 
		  <#list accessoryQuotedMaterialAndSubtotals.accessoryQuotedMaterialAndSubtotalarray as accessoryQuotedMaterialAndSubtotal>
		  <#if accessoryQuotedMaterialAndSubtotal_index==0 >
		   <td style="text-align:left;vertical-align:middle;">原材料：${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.materialName}</td>
		  <#else>
		 <td style="text-align:left;">产地：${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.origin}&nbsp;<br>品牌 ：${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.brand}&nbsp;<br>价格：${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.price}&nbsp;<br>价格单位：${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.priceUnit}&nbsp;</td>
		  </#if>
		  
		     </#list>
		     </tr>
		  <tr>
		 
		  <#list accessoryQuotedMaterialAndSubtotals.accessoryQuotedMaterialAndSubtotalarray as accessoryQuotedMaterialAndSubtotal>
		  <#if accessoryQuotedMaterialAndSubtotal_index==0 >
		   <td>原材料价格 </td>
		   <#else>
		 <td style="text-align:right">&nbsp; ${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.cost} </td> 
		
		  
		 
		  </#if>
		  
		     </#list>
		     </tr>
		      
		   
		     
		     <tr>
		     <#list accessoryQuotedMaterialAndSubtotals.accessoryQuotedMaterialAndSubtotalarray as accessoryQuotedMaterialAndSubtotal>
		     <#if accessoryQuotedMaterialAndSubtotal_index==0 >
		     <td>原材料：${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.materialName}-占原材料小计占比</td> <#else>
		     
		 <td style="text-align:right"> ${(accessoryQuotedMaterialAndSubtotal.subtotal?number?string(',##0.00'))!}%</td>
		  </#if>
		   </#list>
		   </tr>
		     </#list>
		 <!-- 原材料小计 -->
		     <tr>
		     <td><b>原材料小计</b></td>
		 <#list costAnalogyForm.totalMaterialList as totalMaterial>
		 <td style="text-align:right"><b>${(totalMaterial)!}</b></td>
		 </#list>
		 </tr>
		 
		 <!-- 辅料 -->
		<#list costAnalogyForm.accessoryQuotedAccessoryAndSubtotalList as accessoryQuotedAccessoryAndSubtotals>
		  <tr>
		 
		  <#list accessoryQuotedAccessoryAndSubtotals.accessoryQuotedAccessoryAndSubtotalarray as accessoryQuotedAccessoryAndSubtotal>
		  <#if accessoryQuotedAccessoryAndSubtotal_index==0 >
		   <td>辅料：辅料名称：${accessoryQuotedAccessoryAndSubtotal.accessoryQuotedAccessory.accessoryName}</td>
		  
		  
		  <#else>
		 <td style="text-align:right">&nbsp; ${accessoryQuotedAccessoryAndSubtotal.accessoryQuotedAccessory.cost}</td>
		
		  
		  
		  </#if>
		  
		     </#list>
		     </tr>
		     
		  
		     </#list>
		 <!-- 辅料小计 -->
		     <tr>
		     <td><b>辅料小计</b></td>
		 <#list costAnalogyForm.totalAccessoryList as totalAccessory>
		 <td style="text-align:right"><b>${(totalAccessory)!}</b></td>
		 </#list>
		 </tr>
		  <tr>
		     <td><b>原辅料小计</b></td>
		 <#list costAnalogyForm.totalMaterialAccessoryList as totalMaterialAccessory>
		 <td style="text-align:right"><b>${(totalMaterialAccessory)!}</b></td>
		 </#list>
		 </tr>
		 
		
		 
		  <!-- 工艺 -->
		<#list costAnalogyForm.accessoryQuotedTechnologyAndSubtotalList as accessoryQuotedTechnologyAndSubtotals>
		  <tr>
		 
		  <#list accessoryQuotedTechnologyAndSubtotals.accessoryQuotedTechnologyAndSubtotalarray as accessoryQuotedTechnologyAndSubtotal>
		  <#if accessoryQuotedTechnologyAndSubtotal_index==0 >
		   <td>工艺：工艺名称：${accessoryQuotedTechnologyAndSubtotal.accessoryQuotedTechnology.technologyName} </td>
		
		  
		 
		  <#else>
		 <td style="text-align:right">&nbsp; ${accessoryQuotedTechnologyAndSubtotal.accessoryQuotedTechnology.cost}<br></td>
		
		  
		  
		  </#if>
		  
		     </#list>
		     </tr>
		    <!--  <tr>
		     <#list accessoryQuotedTechnologyAndSubtotals as accessoryQuotedTechnologyAndSubtotal>
		     <#if accessoryQuotedTechnologyAndSubtotal_index==0 >
		     <td>工艺小计占比</td> <#else>
		     
		 <td> ${accessoryQuotedTechnologyAndSubtotal.subtotal}%
		  </td>
		  </#if>
		   </#list>
		   </tr> -->
		     </#list>
		 <!-- 工艺小计 -->
		    <!--  <tr>
		     <td>工艺小计</td>
		 <#list costAnalogyForm.totalTechnologyList as totalTechnology>
		 <td>${(totalTechnology)!}</td>
		 </#list>
		 </tr> -->
		  <!-- 内外包装 -->
		<#list costAnalogyForm.accessoryQuotedPackingAndSubtotalList as accessoryQuotedPackingAndSubtotals>
		  <tr>
		 
		  <#list accessoryQuotedPackingAndSubtotals.accessoryQuotedPackingAndSubtotalarray as accessoryQuotedPackingAndSubtotal>
		  <#if accessoryQuotedPackingAndSubtotal_index==0 >
		   <td>内外包装材料：内外包装材料名称：${accessoryQuotedPackingAndSubtotal.accessoryQuotedPacking.packingName}</td>
		  
		  
		  <#else>
		 <td style="text-align:right">&nbsp; ${accessoryQuotedPackingAndSubtotal.accessoryQuotedPacking.cost}<br></td>
	
		  
		  
		  </#if>
		  
		     </#list>
		     </tr>
		      <!-- <tr>
		 
		  <#list accessoryQuotedPackingAndSubtotals.accessoryQuotedPackingAndSubtotalarray as accessoryQuotedPackingAndSubtotal>
		  <#if accessoryQuotedPackingAndSubtotal_index==0 >
		   <td> 损耗:
		  
		  </td>
		  <#else>
		 <td> ${accessoryQuotedPackingAndSubtotal.accessoryQuotedPacking.wastage}<br>
	
		  
		  </td>
		  </#if>
		  
		     </#list>
		     </tr> --!>
		    <!--  <tr>
		     <#list accessoryQuotedPackingAndSubtotals as accessoryQuotedPackingAndSubtotal>
		     <#if accessoryQuotedPackingAndSubtotal_index==0 >
		     <td>内外包装小计占比</td> <#else>
		     
		 <td> ${accessoryQuotedPackingAndSubtotal.subtotal}%
		  </td>
		  </#if>
		   </#list>
		   </tr> -->
		     </#list>
		 <!-- 内外包装小计 -->
		     <!-- <tr>
		     <td>内外包装小计</td>
		 <#list costAnalogyForm.totalPackingList as totalPacking>
		 <td>${(totalPacking)!}</td>
		 </#list>
		 </tr> -->
		 <!--其他要求  -->
		 <tr>
		  <td>其他成本：打样费</td>
		 <#list costAnalogyForm.accessoryQuotedElseList as accessoryQuotedElses>
		  <td style="text-align:right"><#list accessoryQuotedElses.accessoryQuotedElsearray as accessoryQuotedElse><#if accessoryQuotedElse.costType=="打样费" >${(accessoryQuotedElse.cost)!}</#if></#list></td>
		     </#list>
		      </tr>
		       <tr>
		  <td>其他成本：人工</td>
		   
		 <#list costAnalogyForm.accessoryQuotedElseList as accessoryQuotedElses>
		  <td style="text-align:right"><#list accessoryQuotedElses.accessoryQuotedElsearray as accessoryQuotedElse><#if accessoryQuotedElse.costType=="人工" >${(accessoryQuotedElse.cost)!}</#if></#list></td>
		     </#list>
		      </tr>
		       <tr>
		  <td>其他成本：运输 </td>
		 <#list costAnalogyForm.accessoryQuotedElseList as accessoryQuotedElses>
		  <td style="text-align:right"><#list accessoryQuotedElses.accessoryQuotedElsearray as accessoryQuotedElse><#if accessoryQuotedElse.costType=="运输" >${(accessoryQuotedElse.cost)!}</#if></#list></td></#list>
		      </tr>
		      
		       <tr>
		  <td>其他成本：管理</td>
		   
		 <#list costAnalogyForm.accessoryQuotedElseList as accessoryQuotedElses>
		  <td style="text-align:right"><#list accessoryQuotedElses.accessoryQuotedElsearray as accessoryQuotedElse><#if accessoryQuotedElse.costType=="管理" >${(accessoryQuotedElse.cost)!}</#if></#list></td>
		     </#list>
		     
		      </tr>
		      
		       <tr>
		  <td>其他成本：税额</td>
		   
		 <#list costAnalogyForm.accessoryQuotedElseList as accessoryQuotedElses>
		  <td style="text-align:right"><#list accessoryQuotedElses.accessoryQuotedElsearray as accessoryQuotedElse><#if accessoryQuotedElse.costType=="税额" >${(accessoryQuotedElse.cost)!}</#if></#list></td></#list>
		     
		      </tr>
		      
		       <tr>
		  <td>其他成本：税额占比 </td> 
		 
		 <#list costAnalogyForm.accessoryQuotedElseList as accessoryQuotedElses>
		 <td style="text-align:right"><#list accessoryQuotedElses.accessoryQuotedElsearray as accessoryQuotedElse><#if accessoryQuotedElse.costType=="税额占比" >${(accessoryQuotedElse.cost?string(',##0.00'))!}%</#if></#list></td>
		     </#list>
		     
		      </tr>
		      
		       <tr>
		  <td>其他成本：利润</td>
		   
		 <#list costAnalogyForm.accessoryQuotedElseList as accessoryQuotedElses>
		  <td style="text-align:right"><#list accessoryQuotedElses.accessoryQuotedElsearray as accessoryQuotedElse><#if accessoryQuotedElse.costType=="利润" >${(accessoryQuotedElse.cost)!}</#if></#list></td>
		     </#list>
		     
		      </tr>
		      
		             <tr>
		  <td>其他成本：利润占比</td>
		   
		 <#list costAnalogyForm.accessoryQuotedElseList as accessoryQuotedElses>
		  <td style="text-align:right"><#list accessoryQuotedElses.accessoryQuotedElsearray as accessoryQuotedElse><#if accessoryQuotedElse.costType=="利润占比" >${(accessoryQuotedElse.cost?string(',##0.00'))!}%</#if></#list></td>
		     </#list>
		     
		      </tr>
		<!-- 报价总计 -->
		<tr><td><b>以上总价：</b></td>
		<#list costAnalogyForm.supplierFormList as supplierForm>
		<td style="text-align:right"><b>${(supplierForm.total)!}</b></td>
		</#list>
		</tr>
		<tr><td><b>供应商报价：</b></td>
		<#list costAnalogyForm.allTotalList as allTotal>
		<td style="text-align:right"><b>${(allTotal)!}</b></td>
		</#list>
		</tr>
		<!-- 合作价格 -->
		<tr><td><b>合作价格：</b></td>
		<#list costAnalogyForm.cooperativePriceList as cooperativePrice>
		<td style="text-align:right"><input type="text" style="width: 170px;text-align:right;font-weight:bold" id="cooperativePrice${(cooperativePrice_index?string)!?replace(".000","")}" value="${(cooperativePrice)!}"  disabled="true"　readOnly="true"  onChange="costAnalogyFormFn.setInpoutValue(${cooperativePrice_index})"/></td>
		</#list>
		</tr>
		<form id="cblbfx_form" method="post">
		<tr><td><b>采购备注：</b></td>
		<#list costAnalogyForm.cooperativePriceList as cooperativePrice>
		<td style="text-align:right"><textarea  style="width: 170px;height:150px"  maxlength="333" id="remarkTextList${(cooperativePrice_index?string)!?replace(".000","")}"  name="remarkTextList[${(cooperativePrice_index?string)!?replace(".000","")}]"   onChange="costAnalogyFormFn.setInpoutValue(${(cooperativePrice_index?string)!?replace(".000","")})"></textarea></td>
		</#list>
		</tr>
		</form>
		</tbody>
		
</table>	
</div>

<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:160px" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{text:'确定',iconCls:'save',handler:function(){costAnalogyFormFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){costAnalogyFormFn.closeSaveFileDlg()}}]">
		<br/><br/>
		<div style="margin-left:25px">文件名称: <input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:120px;"/></div>		
	</div>
	</body>
</html>
</#compress>