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
	<script type="text/javascript" src='js/jquery.form.js'></script>
	
	<script type="text/javascript" >
			<#include "sco/accessoryIntention/timelinerModel.js" />
    </script>
</head>
<body style="padding-left:5px">
	<div data-options="region:'north'" style="height:100px">
		<div id="content">
			<ul id="example1" class="timeliner">
				<li id="invi_1" title="意向品信息"></li>
				<li id="invi_2" title="询价单信息"></li>
				<li id="invi_3" title="导出询价单"></li>
		        <li id="invi_4" title="导入报价单"></li>
		        <li id="invi_5" title="打样信息"></li>
			</ul>
		</div>
	</div>
	<div data-options="region:'center'" >
	<input name="intentionCode" id="intentionCode" type="hidden"  value="${accessoryIntention.intentionCode}"/>
		<div class="easyui-panel" id="intention-1" >
			<#include "sco/accessoryIntention/accessoryIntentionForm.ftl" />
   	 	</div>
	    <div class="easyui-panel" id="intention-2" >
			<#include "sco/accessoryIntention/accessoryEnquiryForm.ftl" />
	    </div>
		<div class="easyui-panel" id="intention-3" >
			<#include "sco/accessoryIntention/exportEnquiryForm.ftl" />
	    </div>
	    <div class="easyui-panel" id="intention-4" >
			<#include "sco/accessoryIntention/importQuotation.ftl" />
	    </div>
	    <div class="easyui-panel" id="intention-5" >
			<#include "sco/accessoryIntention/accessoryProofing.ftl" />
	    </div>	</div>
	
	
</body>
</html>
