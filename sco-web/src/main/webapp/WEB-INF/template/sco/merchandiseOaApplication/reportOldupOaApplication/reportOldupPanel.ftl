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
	        <#include "sco/merchandiseOaApplication/reportOldupOaApplication/timelinerModel.js" />
    </script>
</head>
<body style="padding-left:5px;">
<input type="hidden" name="applicationCode" id="applicationCode" value="${applicationCode}" />
<input type="hidden" name="intentionAndSupplierCodes" id="intentionAndSupplierCodes" value="${intentionAndSupplierCodes}" />
<input type="hidden" name="applicationList" id="applicationList" value="${applicationList}" />
	<div data-options="region:'north'" style="height:80px">
		<div id="content">
			<ul id="example1" class="timeliner">
				<li id="invi_1" title="供应商附件"></li>
				<li id="invi_2" title="申请报告"></li>
				<li id="invi_3" title="分析报告"></li>
				<li id="invi_4" title="物料信息"></li>
				<li id="invi_5" title="进度信息"></li>
				<li id="invi_6" title="审批意见"></li>
				<li id="invi_7" title="关联原料"></li>
			</ul>
		</div>
	</div>
	<div data-options="region:'center'" style="height:520px">
	
		<div class="easyui-panel" id="intention-1" >
			<#include "sco/merchandiseOaApplication/reportOldupOaApplication/supplierAttachment/supplierAttachmentMGrid.ftl" />
   	 	</div>
   	 	<div class="easyui-panel" id="intention-2" >
			<#include "sco/merchandiseOaApplication/reportOldupOaApplication/reportOldup/applicationReportOldupForm.ftl" />
   	 	</div>
   	 	<div class="easyui-panel" id="intention-3" >
			<#include "sco/merchandiseOaApplication/reportOldupOaApplication/reportAnalysis/oldOaReportAnalysisGrid.ftl" />
   	 	</div>
   	 	<div class="easyui-panel" id="intention-4" >
			<#include "sco/merchandiseOaApplication/reportOldupOaApplication/wlinfo/wlInfoOldFtl.ftl" />
   	 	</div>
   	 	<div class="easyui-panel" id="intention-5" >
			<#include "sco/merchandiseOaApplication/reportOldupOaApplication/applicationschedule/applicationScheduleOldGrid.ftl" />
   	 	</div>
   	 	<div class="easyui-panel" id="intention-6" >
   	 	</div>
   	 	<div class="easyui-panel" id="intention-7" >
   	 	</div>
	</div>
</body>
</html>
