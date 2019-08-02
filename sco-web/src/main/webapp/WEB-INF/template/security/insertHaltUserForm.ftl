<#compress>
<table id="nonHaltUser_grid"
		    class="easyui-datagrid"
		    fit="true"
		    fitColumns="true"
		    iconCls="icon-save"
		    idField="userId"
			pagination="true"
			pagePosition='bottom'
			pageSize="20"
			pageList="[ 10, 20, 30, 40 ]"
			toolbar="#nonHaltUser_toolbar"
			url='halt_listNonHaltUser_2.html?haltId=${haltId}'
		    data-options="rownumbers:true">  
    <thead>
    	<tr>
    		<th rowspan="2" data-options="field:'userId',width:80,checkbox:true">
                <span class="txtCenter">${action.text("security.user.userId")}</span>
            </th>
			<th data-options="field:'loginName',width:80,sortable:true">
                <span class="txtCenter">${action.text("security.user.loginName")}</span>
            </th>
			<th data-options="field:'realName',width:80,sortable:true">
            	<span class="txtCenter">${action.text("security.user.realName")}</span>
            </th>
            <th rowspan=2 data-options="field:'roleNames',width:200">
                <span class="txtCenter">${action.text("security.user.userRole")}</span>
            </th>
        </tr>
        <tr>
            <th><input style="width:60px;" class="filterInput" filterName="loginName"/></th>  
            <th><input style="width:60px;" class="filterInput" filterName="realName"/></th>  
        </tr>
    </thead>
</table>
</#compress>