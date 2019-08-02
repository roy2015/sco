<#compress>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>来伊份SCO成本分析系统</title>
<#include "inc/easyui.ftl" />
<script type="text/javascript" charset="UTF-8">
	<#include "index.js" />
</script>
<script>
//全局消息警告框，避免在另外的TAB中看不到提示
var publicAlert=function(title,msg){
	$.messager.alert(title,msg);
}
</script>
</head>
<body class="easyui-layout">
	<div id="app_north" data-options="region:'north',href:'index_showNorth_1.html'" style="height: 78px;overflow: hidden;background:#1F5AA0 repeat-x left top;" ></div>
	<div data-options="region:'west',title:'功能导航',href:'go_layout_west.html'" style="width: 230px;overflow: hidden;"></div>
<!--
	<div data-options="region:'east',title:'日历',split:true,href:'go_layout_east.html'" style="width: 200px;overflow: hidden;"></div>
-->
	<div id="frame_mainPanel" data-options="region:'center',title:'SCO成本分析系统',href:'go_layout_center.html'" style="overflow: hidden;"></div>
	<div data-options="region:'south',href:'go_layout_south.html'" style="height: 20px;overflow: hidden;">
	</div>

	<div id="publicMessageDlg" closed="true"  class="easyui-dialog" style="width:680px;height:480px"
            data-options="
            	closable:false,
            	cache:false,
            	buttons: '#publicMessageDlg-buttons',
            	modal:true
        	">
    </div>
    <div id="publicMessageDlg-buttons">
		<@eubt id="publicMessageDlgCancelBt" do="close" text="关闭"  plain=false>$('#publicMessageDlg').dialog('close')</@eubt>
	</div>

	<#include "inc/isIe.ftl" />
</body>
</html>
</#compress>