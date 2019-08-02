<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
     <link rel="stylesheet" href="css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/accessoryIntention/accessoryIntention.js" />
        <#include "sco/common/masterDataType.js" />
    </script>
</head>
<body>

	<!-- 辅料意向品管理主表 --> 
	<div id="accessoryIntention_toolbar" >
           <form id="accessoryIntention_search">
           <table style="width: 1050px;"> 
		  		<tr>
		  		<td align="right">供应商/意向供应商编号:</td>
		  		<td ><input class="easyui-validatebox" filterName="supplierCode" id="supplierCode"  style="width:120px;"/></td>
		  		<td align="right">供应商/意向供应商名称:</td>
		  		<td ><input class="easyui-validatebox" filterName="supplierName" id="supplierName"  style="width:120px;"/></td>
		  		<td align="right">意向品编号:</td>
		  		<td ><input class="easyui-validatebox" filterName="intentionCode" id="intentionCode"  type="text"  style="width:120px;"/></td>
		  		<td align="right">意向品名称:</td>
		  		<td ><input class="easyui-validatebox" filterName="intentionName"  id="intentionName"  style="width:120px;"/></td>
		  		</tr>
			  
			   <tr>
			     <td align="right">意向品创建时间:</td>
				<td > <input class="easyui-datebox"   filterName="created"  id="minUpdatedDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;"/></td>
		 		   <td align="right">至:</td>
		 		    <td>
		 			<input class="easyui-datebox"  filterName="createdEnd"   id="maxUpdatedDate"  data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;"/>
			    </td>
				
		  		</tr>
	      <tr>
		<td align="right">中分类:</td>
			<td >
				<input class="easyui-combobox filterSelect" filterName="centreTypeCode" id="centreTypeCode" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'masterDataType_listCentreType_5.html'
								    ">
				</input>
			</td>
			<td align="right">小分类:</td>
			<td >     
				<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false">
				</input>			
			</td>
			<td align="right">明细类:</td>
			<td >
				<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false">
				</input>			
			</td>  
		<td align="right">细分类:</td>
			<td >
				<input class="easyui-combobox filterSelect" filterName="fineTypeCodes" id="fineTypeCodes" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'accessoryIntention_listMinceType_5.html'
								    ">
				</input>
			</td>
	</tr>
	</table>

<a onclick="accessoryIntentionFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
			搜索
		</a>
		<a onclick="accessoryIntentionFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			清空查询
		</a>
		<a onclick="accessoryIntentionFn.addIntention();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			新增意向品
		</a>
		
		<a onclick="accessoryIntentionFn.delIntention();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.accessoryIntention.delete")>none</#if>;">
			删除意向品
		</a>
</form>
	</div>
    <table  id="accessoryIntention_grid"
		    fit="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#accessoryIntention_toolbar"
			url='accessoryIntention_listAccessoryIntention_2.html'
		    data-options="rownumbers:true" >  
        <thead>
        	<tr>
        	 <th data-options="width:65,checkbox:true">
                </th>
        	<th data-options="field:'intentionCode',width:120,sortable:true,
					formatter : function(value, rowData, rowIndex) {
					return '<a href=# style=color:maroon;cursor:hand; onclick=accessoryIntentionFn.editIntention(\''+rowData.intentionCode+'\') >'+value+'</a>'
					}">
                	<span >意向品编号</span>
                </th>
				<th data-options="field:'intentionName',width:160,sortable:true">
                	<span >意向品名称</span>
                </th>
                <th data-options="field:'soaNo',width:80,sortable:true">
                	<span >SAP物料号</span>
                </th>
				<th data-options="field:'centreTypeCode',width:80,sortable:true,formatter: function(value,row,index){
							if(value!=null){
								return row.centreTypeName;
							}else{
								return value;
							}
						}">
                	<span >中分类</span>
                </th>
				<th data-options="field:'smallTypeCode',width:80,sortable:true,formatter: function(value,row,index){
							if(value!=null){
								return row.smallTypeName;
							}else{
								return value;
							}
						}">
                	<span >小分类</span>
                </th>
				<th data-options="field:'detailTypeCode',width:80,sortable:true,formatter: function(value,row,index){
							if(value!=null){
								return row.detailTypeName;
							}else{
								return value;
							}
						}">
                	<span >明细类</span>
                </th>
				<th data-options="field:'fineTypeCodes',width:80,sortable:true,formatter: function(value,row,index){
							if(value!=null){
								return row.fineTypeName;
							}else{
								return value;
							}
						}">
                	<span >细分类</span>
                </th>
				<th data-options="field:'created',width:120,sortable:true">
                	<span >意向品创建时间</span>
                </th>
				<th data-options="field:'createUserName',width:80,sortable:false">
                	<span >意向品创建人</span>
                </th>
				<th data-options="field:'enquiryCount',width:80,sortable:true,align:'right'">
                	<span >询价单数量</span>
                </th>
				<th data-options="field:'supplierCount',width:100,sortable:true,align:'right',formatter: function(value,row,index){
							if(value!=null && value!=''){
								return value;
							}else{
								return 0;
							}
						}">
                	<span >报价供应商数量</span>
                </th>
            </tr>
           
        </thead>
    </table>
</body>
</html>
</#compress>