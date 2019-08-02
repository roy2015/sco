<#compress>
<script type="text/javascript" >
	<#include "retailer/retailerDataPower.js" />
</script>
	<div id="retailerManaged_toolbar">
		<a id="clear" onclick="retailerDatapowerFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
		<a id="deleteControlledRetailer" onclick="retailerDatapowerFn.deleteControlledRetailer();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
			${action.getText("user.button.deleteControlledRetailer")}
		</a>
		<a id="showAddUncontrolledRetailerForm" onclick="retailerDatapowerFn.showAddUncontrolledRetailerForm();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			${action.getText("user.button.addUncontrolledRetailer")}
		</a>
	</div>
	<div id="retailerNotManaged_toolbar">
		<a id="clear" onclick="retailerDatapowerFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
		<a id="addUncontrolledRetailer" onclick="retailerDatapowerFn.addUncontrolledRetailer();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			${action.getText("user.button.addSelectedRetailer")}
		</a>
		<input type="checkbox" id="manageAllRetailer" value = "N"
			<#if ('${grantWay}'=='A')>checked="checked"</#if>
			<#if ('${forUserOrRole}'=='ROLE')>
				onclick = "retailerDatapowerFn.toogleSuperDatapower();"
			<#else>
				style = "display:none"
			</#if>
		/>
		<#if ('${grantWay}'=='A'||'${forUserOrRole}'=='ROLE')>
        	<span style = "color:red">${action.getText("retailer.button.canManagedReatiler")}</span>
		</#if>
	</div>
	<#--tabs控件-->
	<div id="retailerDatapower_tabs" class="easyui-tabs"
		 data-options="fit:true,border:false">
		<#--受控制的用户数据所在panel-->
		<div id="controlled" 
			 title="${action.getText('user.title.controlledRetailer')}">
		  <table id="retailerManaged_grid" toolbar='#retailerManaged_toolbar' 
        	data-options="url:'${src}?forUserOrRole=${forUserOrRole}&isControlled=true&userId=${userId}&roleId=${roleId}&dataType=${dataType}',
        				  fit:true,
        				  fitColumns:true,
        				  pagination:true,
        				  pagePosition:'bottom'">  
		    <thead>
		        <tr>  
		        	<th rowspan="2" data-options="field:'retailerId',width:10,sortable:true,checkbox:true">
		            	<span class="txtCenter">${action.getText("security.dataPower.retailerId")} </span>
		            </th>
		            <th data-options="field:'nameCn',width:80,sortable:true">
		            	<span class="txtCenter">${action.getText("security.dataPower.nameCn")} </span>
		            </th>  
		            <th data-options="field:'representive',width:80,sortable:true">
						<span class="txtCenter">${action.getText("security.dataPower.representive")} </span>
					</th>
					<th rowspan="2" data-options="field:'phone',width:80,sortable:true">
						<span class="txtCenter">${action.getText("security.dataPower.phone")} </span>
					</th>
		        </tr>
		        <tr>
		            <th><input style="width:140px;" class="filterInput" filterName="nameCn"/></th>  
		            <th><input style="width:140px;" class="filterInput" filterName="representive"/></th>  
		        </tr>  
		    </thead>  
		  </table> 
		</div>
		<#--不受控制的用户数据所在panel-->
		<div id="uncontrolled" 
			 title="${action.getText('user.title.uncontrolledRetailer')}">
		  <table id="retailerNotManaged_grid" toolbar='#retailerNotManaged_toolbar' 
        	data-options="url:'${src}?forUserOrRole=${forUserOrRole}&isControlled=false&userId=${userId}&roleId=${roleId}&dataType=${dataType}',
        				  fitColumns:true,
        				  fit:true,
        				  pagination:true,
        				  pagePosition:'bottom'">  
		    <thead>  
		        <tr>  
		        	<th rowspan="2" data-options="field:'retailerId',width:10,sortable:true,checkbox:true">
		            	<span class="txtCenter">${action.getText("security.dataPower.retailerId")} </span>
		            </th>
		            <th data-options="field:'nameCn',width:80,sortable:true">
		            	<span class="txtCenter">${action.getText("security.dataPower.nameCn")} </span>
		            </th>  
		            <th data-options="field:'representive',width:80,sortable:true">
						<span class="txtCenter">${action.getText("security.dataPower.representive")} </span>
					</th>
					<th rowspan="2" data-options="field:'phone',width:80,sortable:true">
						<span class="txtCenter">${action.getText("security.dataPower.phone")} </span>
					</th>
		        </tr> 
		        <tr>
		            <th><input style="width:140px;" class="filterInput" filterName="nameCn"/></th>  
		            <th><input style="width:140px;" class="filterInput" filterName="representive"/></th>  
		        </tr>   
		    </thead>
		  </table>
		</div>
	</div>

</#compress>