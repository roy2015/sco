<#compress>
<script type="text/javascript" >
	<#include "supplier/supplierDataPower.js" />
</script>
	<div id="supplierManaged_toolbar">
		<a id="clear" onclick="supplierDatapowerFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
		<a id="deleteControlledSupplier" onclick="supplierDatapowerFn.deleteControlledSupplier();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
			${action.getText("user.button.deleteControlledSupplier")}
		</a>
		<a id="showAddUncontrolledSupplierForm" onclick="supplierDatapowerFn.showAddUncontrolledSupplierForm();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			${action.getText("user.button.addUncontrolledSupplier")}
		</a>
	</div>
	<div id="supplierNotManaged_toolbar">
		<a id="clear" onclick="supplierDatapowerFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
		<a id="addUncontrolledSupplier" onclick="supplierDatapowerFn.addUncontrolledSupplier();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			${action.getText("user.button.addSelectedSupplier")}
		</a>
		<input type="checkbox" id="manageAllSupplier" value = "N"
			<#if ('${grantWay}'=='A')>checked="checked"</#if>
			<#if ('${forUserOrRole}'=='ROLE')>
				onclick = "supplierDatapowerFn.toogleSuperDatapower();"
			<#else>
				style = "display:none"
			</#if>
		/>
		<#if ('${grantWay}'=='A'||'${forUserOrRole}'=='ROLE')>
        	<span style = "color:red">${action.getText("supplier.button.canManagedSupplier")}</span>
		</#if>
	</div>
	<#--tabs控件-->
	<div id="supplierDatapower_tabs" class="easyui-tabs"
		 data-options="fit:true,border:false">
		<#--受控制的用户数据所在panel-->
		<div id="controlled" 
			 title="${action.getText('user.title.controlledSupplier')}">
		  <table id="supplierManaged_grid" toolbar='#supplierManaged_toolbar' 
        	data-options="url:'${src}?forUserOrRole=${forUserOrRole}&isControlled=true&userId=${userId}&roleId=${roleId}&dataType=${dataType}',
        				  fit:true,
        				  fitColumns:true,
        				  pagination:true,
        				  pagePosition:'bottom'">  
		    <thead>
		        <tr>  
		        	<th rowspan="2" data-options="field:'supplId',width:140,sortable:true,checkbox:true">
                		<span class="txtCenter">${action.text("security.supplier.supplId")}</span>
                	</th>
		        	<th data-options="field:'supplName',width:140,sortable:true">
                		<span class="txtCenter">${action.text("security.supplier.supplName")}</span>
                	</th>
                	<th data-options="field:'phone',width:120,sortable:true">
                		<span class="txtCenter">${action.text("security.supplier.phone")}</span>
                	</th>
		        </tr>
		        <tr>
		            <th><input style="width:120px;" class="filterInput" filterName="supplName"/></th>  
		            <th><input style="width:100px;" class="filterInput" filterName="phone"/></th>  
		        </tr>  
		    </thead>  
		  </table> 
		</div>
		<#--不受控制的用户数据所在panel-->
		<div id="uncontrolled" 
			 title="${action.getText('user.title.uncontrolledSupplier')}">
		  <table id="supplierNotManaged_grid" toolbar='#supplierNotManaged_toolbar' 
        	data-options="url:'${src}?forUserOrRole=${forUserOrRole}&isControlled=false&userId=${userId}&roleId=${roleId}&dataType=${dataType}',
        				  fitColumns:true,
        				  fit:true,
        				  pagination:true,
        				  pagePosition:'bottom'">  
		    <thead>  
		        <tr>  
		        	<th rowspan="2" data-options="field:'supplId',width:140,sortable:true,checkbox:true">
                		<span class="txtCenter">${action.text("security.supplier.supplId")}</span>
                	</th>
		        	<th data-options="field:'supplName',width:140,sortable:true">
                		<span class="txtCenter">${action.text("security.supplier.supplName")}</span>
                	</th>
                	<th data-options="field:'phone',width:120,sortable:true">
                		<span class="txtCenter">${action.text("security.supplier.phone")}</span>
                	</th>
		        </tr> 
		        <tr>
		            <th><input style="width:120px;" class="filterInput" filterName="supplName"/></th>  
		            <th><input style="width:120px;" class="filterInput" filterName="phone"/></th>  
		        </tr>   
		    </thead>
		  </table>
		</div>
	</div>

</#compress>