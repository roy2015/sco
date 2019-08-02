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
			<#include "sco/accessoryOaApplication/committeeApply/timelinerModel.js" />
    </script>
</head>
<body>
        <input name="quotedCodes" id="quotedCodes" type="hidden"  value="${quotedCodes}"/>
	 	<input name="applicationCodeNow" id="applicationCodeNow" type="hidden"  value="${applicationCodeNow}"/>

	<div data-options="region:'north'" style="height:80px;overflow-y:hidden">
		<div id="content">
			<ul id="example1" class="timeliner">
				<li id="invi_1" title="供应商附件"></li>
				<li id="invi_2" title="供应商证件"></li>
				<li id="invi_3" title="申请报告"></li>
		        <li id="invi_4" title="分析报表"></li>
		        <li id="invi_5" title="审批意见"></li>
		        <li id="invi_6" title="物料信息"></li>
		        <li id="invi_7" title="进度信息"></li>
		        <li id="invi_8" title="大货信息"></li>
			</ul>
		</div>
	</div>
	<div data-options="region:'center'" style="height:520px;padding-left:5px;">
		<div class="easyui-panel" id="intention-1" >
			<#include "sco/accessoryOaApplication/committeeApply/supplierAttachment/supplierAttachmentAGrid.ftl" />
   	 	</div>
	    <div class="easyui-panel" id="intention-2" >
			<#include "sco/accessoryOaApplication/committeeApply/supplierCertificate/supplierCertificateAGrid.ftl" />
	    </div>
		<div class="easyui-panel" id="intention-3" >
			<#include "sco/accessoryOaApplication/committeeApply/applicationReportAccessory.ftl" />
	    </div>
	    <div class="easyui-panel" id="intention-4" >
			<#include "sco/accessoryOaApplication/committeeApply/reportAnalysis/committeeReportAnalysisGrid.ftl" />
	    </div>
	    <div class="easyui-panel" id="intention-5" >
	    </div>
	     <div class="easyui-panel" id="intention-6" >
			<#include "sco/accessoryOaApplication/committeeApply/wlInfo.ftl" />
	    </div>
	     <div class="easyui-panel" id="intention-7" >
			<#include "sco/accessoryOaApplication/committeeApply/applicationschedule/applicationScheduleCommitteeGrid.ftl" />
	    </div>
	     <div class="easyui-panel" id="intention-8" >
			<#include "sco/accessoryOaApplication/committeeApply/dhInfo.ftl" />
	    </div>	</div>
	
	
</body>
</html>
