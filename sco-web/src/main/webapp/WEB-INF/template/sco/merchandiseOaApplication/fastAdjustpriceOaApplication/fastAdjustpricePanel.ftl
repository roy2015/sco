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
	        <#include "sco/merchandiseOaApplication/fastAdjustpriceOaApplication/timelinerModel.js" />
    </script>
</head>
<body>
<input type="hidden" name="applicationCode" id="applicationCode" value="${applicationCode}" />
<input type="hidden" name="intentionAndSupplierCodes" id="intentionAndSupplierCodes" value="${intentionAndSupplierCodes}" />
	<div data-options="region:'north'" style="height:100px">
		<div id="content">
			<ul id="example1" class="timeliner">
				<li id="invi_1" title="申请文件"></li>
				<li id="invi_2" title="物料信息"></li>
			</ul>
		</div>
	</div>
	<div data-options="region:'center'" style="height:520px">
	
		<div class="easyui-panel" id="intention-1" >
			<#include "sco/merchandiseOaApplication/fastAdjustpriceOaApplication/attachment/attachmentGrid.ftl" />
   	 	</div>
   	 	<div class="easyui-panel" id="intention-2" >
			<#include "sco/merchandiseOaApplication/fastAdjustpriceOaApplication/wlinfo/wlInfoFastAdjustGrid.ftl" />
   	 	</div>
	</div>
</body>
</html>