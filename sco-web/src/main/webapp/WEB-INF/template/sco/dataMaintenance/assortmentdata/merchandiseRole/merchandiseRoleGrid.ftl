<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/assortmentdata/merchandiseRole/merchandiseRoleModel.js" />
    </script>
</head>
<body>
	<div id="dx" style="height:280px;">
	<!-- 商品定性角色管理主表 --> 
	<div id="merchandiseDxRole_toolbar">
		<a id="dx_insert" onclick="merchandiseDxRoleFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			添加
		</a>
		<a id="dx_remove" onclick="merchandiseDxRoleFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
			删除
		</a>
	</div>
    <table  id="merchandiseDxRole_grid"
		    fit="true"
		    title="商品定性角色"
		    singleSelect="false"
			toolbar="#merchandiseDxRole_toolbar"
			url='merchandiseDxRole_listMerchandiseDxRole_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th align="center" data-options="field:'merchandiseCode',width:40,checkbox:true"></th>
				<th data-options="field:'roleName',width:100">
                	角色名称
                </th>
            </tr>
        </thead>
    </table>
    </div>
    <!-- 商品定性角色管理主表 --> 
	  <div id="dl" style="height:280px;">
	<div id="merchandiseDlRole_toolbar">
		<a id="dl_insert" onclick="merchandiseDlRoleFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			添加
		</a>
		<a id="dl_remove" onclick="merchandiseDlRoleFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
			删除
		</a>
	</div>
    <table  id="merchandiseDlRole_grid"
		    fit="true"
		    title="商品定量角色"
		    singleSelect="false"
			toolbar="#merchandiseDlRole_toolbar"
			url='merchandiseDlRole_listMerchandiseDlRole_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th align="center" data-options="field:'merchandiseCode',width:40,checkbox:true"></th>
				<th data-options="field:'roleName',width:100">
                	角色名称
                </th>
            </tr>
        </thead>
    </table>
    </div>
</body>
</html>
</#compress>