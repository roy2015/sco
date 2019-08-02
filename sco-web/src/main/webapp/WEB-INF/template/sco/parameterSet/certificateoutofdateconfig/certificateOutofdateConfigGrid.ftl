<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/parameterSet/certificateoutofdateconfig/certificateOutofdateConfigModel.js" />
</script>
</head>
<body>
	<!-- 证件过期提醒设置管理主表 -->
	<div id="certificateOutofdateConfig_toolbar">
		<a id="insert" onclick="certificateOutofdateConfigFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'"> 添加 </a> <a id="showEdit"
			onclick="certificateOutofdateConfigFn.showEdit();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'"> 修改 </a> <a id="remove"
			onclick="certificateOutofdateConfigFn.remove();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'"> 删除 </a>
	</div>
	<table id="certificateOutofdateConfig_grid" fit="true" singleSelect="false" pagination="true" pagePosition='bottom' pageSize="20"
		pageList="[ 10, 20, 30, 40 ]" toolbar="#certificateOutofdateConfig_toolbar" url='certificateOutofdateConfig_listCertificateOutofdateConfig_2.html' data-options="rownumbers:true">
		<thead>
			<tr>
				<th align="center" data-options="field:'merchandiseCode',width:40,checkbox:true"></th>
				<th align="right" data-options="field:'outofdate',width:180,sortable:true">提前提醒天数</th>
			</tr>
		</thead>
	</table>
</body>
</html>
</#compress>
