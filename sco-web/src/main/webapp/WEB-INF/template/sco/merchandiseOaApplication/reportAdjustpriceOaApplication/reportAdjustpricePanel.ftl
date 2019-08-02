<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<#include "inc/easyui.ftl" />

	<link rel="stylesheet" href="css/timeliner/reset.css">
	<!--主要样式-->
	<link rel="stylesheet" href="css/timeliner/timeliner.css">
	<link rel="stylesheet" href="css/timeliner/timeliner_styling.css">
	<script type="text/javascript" src='js/jquery.timeliner.min.js'></script>
	<script type="text/javascript" >
	        <#include "sco/merchandiseOaApplication/reportAdjustpriceOaApplication/timelinerModel.js" />
    </script>
</head>
<body>
<input type="hidden" name="applicationCode" id="applicationCode" value="${applicationCode}" />
<input type="hidden" name="intentionAndSupplierCodes" id="intentionAndSupplierCodes" value="${intentionAndSupplierCodes}" />
<input type="hidden" name="applicationList" id="applicationList" value="${applicationList}" />
	<div data-options="region:'north'" style="height:80px;overflow-y:hidden">
		<div id="content">
			<ul id="example1" class="timeliner">
				<li id="invi_1" title="供应商附件"></li>
				<li id="invi_2" title="申请报告"></li>
				<li id="invi_3" title="分析报告"></li>
				<li id="invi_4" title="物料信息"></li>
				<li id="invi_5" title="审批意见"></li>
				<li id="invi_6" title="关联原料"></li>
			</ul>
		</div>
	</div>
	<div data-options="region:'center'" style="height:520px">
	
		<div class="easyui-panel" id="intention-1" >
			<#include "sco/merchandiseOaApplication/reportAdjustpriceOaApplication/supplierAttachment/supplierAttachmentMGrid.ftl" />
   	 	</div>
   	 	<div class="easyui-panel" id="intention-2" >
			<#include "sco/merchandiseOaApplication/reportAdjustpriceOaApplication/reportAdjustprice/applicationReportAdjustpriceForm.ftl" />
   	 	</div>
   	 	<div class="easyui-panel" id="intention-3" >
			<#include "sco/merchandiseOaApplication/reportAdjustpriceOaApplication/reportAnalysis/adjustPriceOaReportAnalysisGrid.ftl" />
   	 	</div>
   	 	<div class="easyui-panel" id="intention-4" >
			<#include "sco/merchandiseOaApplication/reportAdjustpriceOaApplication/wlinfo/wlInfoAdjustFtl.ftl" />
   	 	</div>
   	 	<div class="easyui-panel" id="intention-5" >
   	 	</div>
   	 	<div class="easyui-panel" id="intention-6" >
   	 	</div>
	</div>
</body>
</html>