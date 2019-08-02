<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/parameterSet/qlRemindConfig/qlRemindConfigModel.js" />
    </script>
</head>
<body>
	<!-- 签量提醒设置管理主表 --> 
	<div id="qlRemindConfig_toolbar">
		<a id="insert" onclick="qlRemindConfigFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.qlremindconfig.insert")>none</#if>;">
			${action.getText("common.button.add")}
		   </a>
		<a id="showEdit" onclick="qlRemindConfigFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.qlremindconfig.edit")>none</#if>;">
			${action.getText("common.button.edit")}
		</a>
		<a id="remove" onclick="qlRemindConfigFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.qlremindconfig.delete")>none</#if>;">
			${action.getText("common.button.delete")}
		</a>

	</div>
    <table  id="qlRemindConfig_grid"
		    fit="true"
		    singleSelect="false"
			pagination = "true"
			pagePosition = 'bottom'
			selectOnCheck="true"
			checkOnSelect="true"
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#qlRemindConfig_toolbar"
			url='qlRemindConfig_listQlRemindConfig_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
                <th field="ck" checkbox="true"></th>
				<th data-options="field:'thresholdValue',width:160,sortable:true">
                	<span class="txtCenter" >签量已完成百分比阀值(%)
                	</span>
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>