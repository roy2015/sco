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
    </style>
</head>
<body>
<form id="zjfx_form" method="post" enctype="multipart/form-data">
<div>

<a id="close" onclick="totalcostanalysisFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭 </a>
		<a id="save" onclick="totalcostanalysisFn.saveFile();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">保存</a>
		<a id="import" onclick="totalcostanalysisFn.export2Excel();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">导出Excel</a>
</div>
</form>
<div style="padding: 5px;overflow: hidden">
<table id="contrastResult" border='1'  >
			<tr>
			<th style="text-align:center">成本项名称</th>
			<#list costAnalogyForm.supplierFormList as supplierForm>
			</th>
			<th style="text-align:center">
			<table  >
			<tr>
			<#if supplierForm.intentionSupplierCodeShow??>
			<td>供应商编号-供应商名称:${supplierForm.intentionSupplierCode}-${supplierForm.intentionSupplierName}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.merchandiseCodeShow??>
			<td>SAP物料号:${supplierForm.merchandiseCode}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.quotedCountShow??>
			<td>报价数量:${supplierForm.quotedCount}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.contactsShow??>
			<td>联系人:${supplierForm.contacts}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.phoneShow??>
			<td>联系方式:${supplierForm.phone}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.companySiteShow??>
			<td>供应商地址:${supplierForm.companySite}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.registerCapitalShow??>
			<td>注册资金:${supplierForm.registerCapital}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.yearTurnoverShow??>
			<td>年营业额:${supplierForm.yearTurnover}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.factoryAreaShow??>
			<td>工厂面积:${supplierForm.factoryArea}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.staffCountShow??>
			<td>工人数:${supplierForm.staffCount}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.dailyCapacityShow??>
			<td>日产能:${supplierForm.dailyCapacity}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.hzgppShow??>
			<td>合作过品牌:${supplierForm.hzgpp}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.invoiceTypeShow??>
			<td>发票类型(增/普票):${supplierForm.invoiceType}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.paymentTypeShow??>
			<td>付款方式:${supplierForm.paymentType}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.proofingContentShow??>
			<td>打样评价:${supplierForm.proofingContent}</td>
			</#if>
			</tr>
			<tr>
			<#if supplierForm.proofingEvaluateShow??>
			<td>产前样说明:${supplierForm.proofingEvaluate}</td>
			</#if>
			</tr>
			
			</table>
			</th>
			</#list>
			</tr>
		<#list costAnalogyForm.accessoryQuotedMaterialAndSubtotalList as accessoryQuotedMaterialAndSubtotals>
		  <tr>
		  <td> 原材料名称:${accessoryQuotedMaterialAndSubtotals[0].accessoryQuotedMaterial.materialName}<br>
		 原材料产地:<br>  原材料品牌:<br>原材料损耗:<br>
		  
		  </td>
		  <#list accessoryQuotedMaterialAndSubtotals as accessoryQuotedMaterialAndSubtotal>
		 <td> ${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.materialName}<br>
		 ${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.origin}<br>  ${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.brand}<br>${accessoryQuotedMaterialAndSubtotal.accessoryQuotedMaterial.wastage}<br>
		  
		  </td>
		  
		  
		     </#list>
		     </tr>
		     <tr><td>原材料小计占比:</td> 
		     <#list accessoryQuotedMaterialAndSubtotals as accessoryQuotedMaterialAndSubtotal>
		 <td> ${accessoryQuotedMaterialAndSubtotal.subtotal}%
		  </td>
		   </#list>
		   </tr>
		     </#list>
		 <!-- 原材料小计 -->
		     <tr>
		     <td>原材料小计</td>
		 <#list costAnalogyForm.totalMaterialList as totalMaterial>
		 <td>${totalMaterial}</td>
		 </#list>
		 </tr>
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
</html>
</#compress>