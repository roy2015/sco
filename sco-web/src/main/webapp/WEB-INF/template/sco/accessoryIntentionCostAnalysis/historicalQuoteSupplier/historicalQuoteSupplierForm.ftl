<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<script type="text/javascript">
<#include "sco/accessoryIntentionCostAnalysis/historicalQuoteSupplier/historicalQuoteSupplierForm.js" />
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
width: 100%;
/* margin-bottom: 20px; */
}
table {
max-width: 100%;
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
line-height: 20px;
text-align: left;
vertical-align: top;
border-top: 0px solid #ddd;
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
<form id="lsbjfx_form" method="post" enctype="multipart/form-data">
<div>

		<a id="close" onclick="historicalQuoteSupplierFormFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭 </a>
		<a id="save" onclick="historicalQuoteSupplierFormFn.saveFile();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">保存</a>
		<a id="import" onclick="historicalQuoteSupplierFormFn.export2Excel();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">导出Excel</a>
</div>
</form>
<#setting number_format="#,##0.000">
<div id="historicalQuoteSupplier" style="width:auto" style="margin-top:25px">
<input name="json" id="json" type="hidden" value="${json}" /> 
<table id="contrastResult"  class="table table-bordered" >
			<tr>
			<td style="text-align:center;vertical-align: middle;">供应商/意向供应商编号</td>
			<td style="text-align:center;vertical-align: middle;">供应商/意向供应商名称</td>
			<td style="text-align:center;vertical-align: middle;">意向品编号</td>
			<td style="text-align:center;vertical-align: middle;">意向品名称</td>
			<td style="text-align:center;vertical-align: middle;">意向品图片</td>
			<td style="text-align:center;vertical-align: middle;">供应商最后一次报价规格</td>
			<td style="text-align:center;vertical-align: middle;">实际引进规格</td>
			<td style="text-align:center;vertical-align: middle;">供应商最后一次报价数量</td>
			<td style="text-align:center;vertical-align: middle;">供应商最后一次报价单价</td>
			<td style="text-align:center;vertical-align: middle;">实际采购数量</td>
			<td style="text-align:center;vertical-align: middle;">报价供应商数</td>
			<td style="text-align:center;vertical-align: middle;">供应商报价排行</td>
			<td style="text-align:center;vertical-align: middle;">合作价格</td>
			<td style="text-align:center;vertical-align: middle;">合作价格日期</td>
			<td style="text-align:center;vertical-align: middle;">实际采购金额</td>
			<td style="text-align:center;vertical-align: middle;">收到申购单日期</td>
			<td style="text-align:center;vertical-align: middle;">设计完稿日期</td>
			<td style="text-align:center;vertical-align: middle;">要求到货日期</td>
			<td style="text-align:center;vertical-align: middle;">竞价单提交日期</td>
			<td style="text-align:center;vertical-align: middle;">竞价单完成日期</td>
			<td style="text-align:center;vertical-align: middle;">竞价单审批完成天数</td>
			<td style="text-align:center;vertical-align: middle;">下达采购订单日期</td>
			<td style="text-align:center;vertical-align: middle;">实际到货日期</td>
			<td style="text-align:center;vertical-align: middle;">样品打样周期</td>
			<td style="text-align:center;vertical-align: middle;">正常作业流程时间(天)</td>
			<td style="text-align:center;vertical-align: middle;">实际作业时间(天)</td>
			<td style="text-align:center;vertical-align: middle;">差异天数</td>
			<td style="text-align:center;vertical-align: middle;">产前样评价</td>
			
			</tr>
		<#list historicalQuoteSupplierFormListTotal as historicalQuoteSupplierForm>
		  <tr>
		 <td style="text-align:center;vertical-align: middle;">${historicalQuoteSupplierForm.intentionSupplierCode}</td>
			<td style="text-align:center;vertical-align: middle;">${historicalQuoteSupplierForm.intentionSupplierName}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${historicalQuoteSupplierForm.intentionCode}</td>
			<td style="text-align:center;vertical-align: middle;">${historicalQuoteSupplierForm.intentionName}</td>
			<td style="text-align:center;vertical-align: middle;"><a href=# style=color:maroon;cursor:hand; onclick=historicalQuoteSupplierFormFn.downloadPicture('${historicalQuoteSupplierForm.intentionPicture}') >${historicalQuoteSupplierForm.intentionPicture}</a></td>
			<td style="text-align:center;vertical-align: middle;"> <table class="table table-bordered">
				<tr><td><table class="table table-bordered"><tr><td>原材料名称</td><td>材料</td><td>尺寸</td></tr><#list historicalQuoteSupplierForm.accessoryEnquiryMaterialList as accessoryEnquiryMaterial><tr><td>${accessoryEnquiryMaterial.materialName}</td><td>${accessoryEnquiryMaterial.material}</td><td>${accessoryEnquiryMaterial.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="table table-bordered"><tr><td>辅料名称</td><td>材料</td><td>尺寸</td></tr><#list historicalQuoteSupplierForm.accessoryEnquiryAccessoryList as accessoryEnquiryAccessory><tr><td>${accessoryEnquiryAccessory.accessoryName}</td><td>${accessoryEnquiryAccessory.material}</td><td>${accessoryEnquiryAccessory.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="table table-bordered"><tr><td>内外包装材料名称</td><td>材料</td><td>尺寸</td></tr><#list historicalQuoteSupplierForm.accessoryEnquiryPackingList as accessoryEnquiryPacking><tr><td>${accessoryEnquiryPacking.packingName}</td><td>${accessoryEnquiryPacking.packingMaterial}</td><td>${accessoryEnquiryPacking.materialSize}</td></tr> </#list></table></td></tr>
				<tr><td><table class="table table-bordered"><tr><td>工艺名称</td><td>工艺要求</td></tr><#list historicalQuoteSupplierForm.accessoryEnquiryTechnologyList as accessoryEnquiryTechnology><tr><td>${accessoryEnquiryTechnology.technologyName}</td><td>${accessoryEnquiryTechnology.technologyInfo}</td></tr> </#list></table></td></tr>
				<tr><td><table class="table table-bordered"><tr><td>要求名称</td><td>要求内容</td></tr><#list historicalQuoteSupplierForm.accessoryEnquiryElseList as accessoryEnquiryElse><tr><td>${accessoryEnquiryElse.name}</td><td>${accessoryEnquiryElse.info}</td></tr> </#list></table></td></tr>
				</table></td>
			<td style="text-align:center;vertical-align: middle;">${historicalQuoteSupplierForm.actualSpecifications}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${historicalQuoteSupplierForm.lastQuoteCount}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.lastQuoteTotal)!}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.purchaseCount)!}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.supplierCount?string)!?replace(".000","")}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.supplierRanking?string)!?replace(".000","")}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${historicalQuoteSupplierForm.cooperationPrice}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.cooperationPriceDate?string("yyyy-MM-dd"))!}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${historicalQuoteSupplierForm.purchaseMoney}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.receivedOADate?string("yyyy-MM-dd"))!}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.sjwgDate?string("yyyy-MM-dd"))!}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.requiredDeliveryDate?string("yyyy-MM-dd"))!}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.oaSubmitDate?string("yyyy-MM-dd"))!}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.oaCompleteDate?string("yyyy-MM-dd"))!}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.oaCompleteDays?string)!?replace(".000","")}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.poDate?string("yyyy-MM-dd"))!}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.actualDeliveryDate?string("yyyy-MM-dd"))!}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.proofingCycle?string)!?replace(".000","")}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.normalProcessDays?string)!?replace(".000","")}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.actualProcessDays?string)!?replace(".000","")}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${(historicalQuoteSupplierForm.differencesDays?string)!?replace(".000","")}&nbsp;</td>
			<td style="text-align:center;vertical-align: middle;">${historicalQuoteSupplierForm.proofingEvaluate}&nbsp;</td>
			
		 </tr>
		 </#list>
		 </table>
		 </div>
		 
		<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:160px" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{text:'确定',iconCls:'save',handler:function(){historicalQuoteSupplierFormFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){historicalQuoteSupplierFormFn.closeSaveFileDlg()}}]">
		<br/><br/>
		<div style="margin-left:25px">文件名称: <input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:120px;"/></div>		
	</div>
</html>
</#compress>