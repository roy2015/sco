<#compress>
	<script type="text/javascript" >
		<#include "sco/merchandiseOaApplication/reportNewOaApplication/wlinfo/wlInfoNew.js" />
    </script>
    <style>
		.wlInfo{
			padding : 10px;
			border : none;
			//font-size : 12px;
			font-family : "微软雅黑";
			border: dotted #ddd 1px;
			white-space : nowrap;
			border-collapse : collapse;
		}
		.wlInfo td {
			padding:5px;
		}
		.wlInfo th {
			padding:0px 5px;
			line-height: 25px;
			font-weight:normal;
			border: solid #ddd 1px;
			background: #FDFDFD;  <!-- 一些不支持背景渐变的浏览器 -->
			background: -moz-linear-gradient(top,  #FDFDFD 0%, #F5F5F5 100%); <!-- 兼容Firefox浏览器 -->
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FDFDFD), color-stop(100%,#F5F5F5)); <!-- 兼容chrome/Safari浏览器 -->
			background: -webkit-linear-gradient(top,  #FDFDFD 0%,#F5F5F5 100%);
			background: -o-linear-gradient(top,  #FDFDFD 0%,#ffffff 100%);
			background: -ms-linear-gradient(top,  #000000 0%,#ffffff 100%);
			background: linear-gradient(to bottom,  #FDFDFD 0%,#F5F5F5 100%);
			filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#FDFDFD', endColorstr='#F5F5F5',GradientType=0 );  <!-- 兼容IE浏览器 -->
		}
	</style>
    
	<div style="padding: 5px;">
	 	<table>
	 		<tr>
				<td>
					<a onclick="wlInfoNewFn.save()" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'" >保存</a>
				</td>
				<td>
					<a onclick="wlInfoNewFn.close()" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'" >关闭</a>
				</td>
	 		</tr>
	 	</table>
	 	
	 	<form id="newWlInfoForm" style="padding: 5px;overflow: auto;height: 320px;" method="post">
	 		<input type="hidden" name="applicationCode" value="${applicationCode}" />
			<input type="hidden" name="intentionAndSupplierCodes" value="${intentionAndSupplierCodes}" />
	 		<div id="newDataForm" style="width:2200px;"></div>
	 	</form>
	</div>	
</#compress>