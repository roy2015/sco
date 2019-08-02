<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
     <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/accessoryOaApplication/nonFoodApply/nonFoodApplyModel.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
</head>
<body>
	<!-- 辅料意向品管理主表 --> 
	<div id="nonFoodApply_toolbar">
	 <form id="nonFoodApply_search">
		<table style="width: 950px;">
	  	<tr>
	  		<td align="right">供应商/意向供应商编号:</td>
			<td >
				<input class="easyui-validatebox" filterName="intentionSupplierCode" type="text" name="intentionSupplierCode" id="intentionSupplierCode" style="width:120px;" />
			</td>  
			<td align="right">供应商/意向供应商名称:</td>   
			<td>       
				<input class="easyui-validatebox" filterName="intentionSupplierName" type="text" name="intentionSupplierName" id="intentionSupplierName" style="width:120px;" />		  
			</td> 
	   </tr>
	  <tr>
			 <td align="right">意向品编号:</td>
			<td>
				<input class="easyui-validatebox" filterName="intentionCode" type="text" name="intentionCode" id="intentionCode" style="width:120px;" />
			</td>
			<td align="right">意向品名称:</td>
			<td>     
			    <input class="easyui-validatebox" filterName="intentionName" type="text" name="intentionName" id="intentionName" style="width:120px;" />
			</td>
		   <td align="right">意向品创建时间:</td>
			<!-- <td colspan="3"><input class="easyui-datebox" filterName="intentionCreated" type="text" name="intentionCreated" id="intentionCreated" style="width:124px;" />
			      至<input class="easyui-datebox" filterName="intentionCreatedEnd" type="text" name="intentionCreatedEnd" id="intentionCreatedEnd" style="width:124px;" />
			</td> -->
			<td><input class="easyui-datebox" filterName="intentionCreated" type="text" name="intentionCreated" id="intentionCreated" style="width:124px;" data-options="required:false,editable:false"/></td>
			<td align="right">至:</td>
		 		    <td>
		 			<input class="easyui-datebox"  filterName="intentionCreatedEnd"   id="intentionCreatedEnd"  data-options="required:false,editable:false" style="width:124px;"/>
			    </td>
	   </tr>
	  	<tr>
		   <td align="right">中分类:</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="centreTypeCode" id="centreTypeCode" style="width: 124px;" 
						data-options="
						valueField:'id',
						textField:'text',
						editable:false,
						url:'masterDataType_listCentreType_5.html'">
				</input>
			</td>
			<td align="right">小分类:</td>
			<td>     
				<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
				</input>			
			</td>
			<td align="right">明细类:</td>
			<td>
				<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
				</input>			
			</td>  
			<td align="right">细分类:</td>
			<td>
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
	   <tr>
			 <td align="right">询价单号:</td>
			<td>
				<input class="easyui-validatebox" filterName="enquiryCode" type="text" name="enquiryCode" id="enquiryCode" style="width:120px;" />
			</td>
		   <td align="right">询价单创建时间:</td>
			<td ><input class="easyui-datebox" filterName="enquiryCreated" type="text" name="enquiryCreated" id="enquiryCreated" style="width:124px;" data-options="required:false,editable:false"/></td>
			<td align="right">
			      至:</td>
			      <td><input class="easyui-datebox" filterName="enquiryCreatedEnd" type="text" name="enquiryCreatedEnd" id="enquiryCreatedEnd" style="width:124px;" data-options="required:false,editable:false"/>
			</td>
	   </tr>
	     <tr>
			 <td align="right">报价单号:</td>
			<td>
				<input class="easyui-validatebox" filterName="quotedCode" type="text" name="quotedCode" id="quotedCode"  style="width:120px;" />
			</td>
		   <td align="right">报价单上传时间:</td>
			<td ><input class="easyui-datebox" filterName="quotedCreated" type="text" name="quotedCreated" id="quotedCreated" style="width:124px;" data-options="required:false,editable:false"/></td>
			<td align="right">至:</td>
			 <td><input class="easyui-datebox" filterName="quotedCreatedEnd" type="text" name="quotedCreatedEnd" id="quotedCreatedEnd" style="width:124px;" data-options="required:false,editable:false"/>
			</td>
	   </tr>
	   <tr>
	  		<td align="right">SCO申请单号:</td>
			<td >
				<input class="easyui-validatebox" filterName="applicationCode" type="text" name="applicationCode" id="applicationCode"  style="width:120px;" />
			</td> 
			<td align="right">申请单状态:</td>
			<td>     
			    <input class="easyui-combobox filterSelect" id="applicationStatus" name="applicationStatus" filterName="applicationStatus" style="width: 124px;"
			    	data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'businessComBox_applicationStatus_5.html'">
				</input>
			</td>
			<td colspan="4"></td>
		</tr>
	  
	</table>
	</form>	
	<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
		<a onclick="nonFoodApplyFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				搜索
		</a>
		<a onclick="nonFoodApplyFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			清空查询
		</a>
		<a id="insert" onclick="nonFoodApplyFn.showInsert(null);" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.accessoryOaApplication.nonFood")>none</#if>;">
			新增申请单
		</a>
		<a id="remove" onclick="nonFoodApplyFn.remove();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.accessoryOaApplication.nonFood")>none</#if>;">
			删除申请单
		</a>
		<a id="close" onclick="nonFoodApplyFn.close();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.accessoryOaApplication.nonFood")>none</#if>;">
			关闭申请单
		</a>
		<a onclick="nonFoodApplyFn.allowOaSynchronous();" plain="true" class="easyui-linkbutton" data-options="iconCls:'active'">
			允许OA同步
		</a>
		<a onclick="nonFoodApplyFn.undoOaSynchronous();" plain="true" class="easyui-linkbutton" data-options="iconCls:'cancel'">
			撤销OA同步
		</a>
		<a id="lookApproveOpinion" onclick="nonFoodApplyFn.lookApproveOpinion();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.accessoryOaApplication.nonFood")>none</#if>;">
			查看OA审批意见
		</a>
	</table>	
</div>

<table  id="nonFoodApplyGrid"
		    fit="true"
		    iconCls= "icon-save"
		    singleSelect="true"
		    checkOnSelect="false"
	   		selectOnCheck="false"
		    singleSelect="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 100 ]"
			toolbar="#nonFoodApply_toolbar"
			url='nonFoodApply_listNonFoodApplyIntentionApplication_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th  data-options="width:40,checkbox:true">
                <th halign="center" data-options="field:'oaApplicationCode',width:40" hidden="true"></th>
        		<th halign="center" data-options="field:'oaContacts',width:40" hidden="true"></th>		
                </th>
				<th data-options="field:'applicationCode',width:120,sortable:true,formatter : function(value, rowData, rowIndex) {
				    if(value!=null){
					return '<a href=# style=color:maroon;cursor:hand; onclick=nonFoodApplyFn.showInsert(\''+rowData.applicationCode+'\') >'+value+'</a>'
					}else{
					return value;
					}
					}">
                	<span>SCO申请单号</span>
                </th>
				<th data-options="field:'applicationCreateby',width:120,sortable:true">
                	<span>SCO申请单创建人</span>
                </th>
				<th data-options="field:'intentionCode',width:120,sortable:true">
                	<span>意向品编号</span>
                </th>
				<th data-options="field:'intentionName',width:120,sortable:true">
                	<span>意向品名称</span>
                </th>
				<th data-options="field:'intentionSupplierCode',width:150,sortable:true,formatter:function(value,row){
																				 if(row.supplierCode == null) {
																					return row.intentionSupplierCode;
																				 }else {
																					return row.supplierCode;
																				 }
																				}">
                	<span>供应商/意向供应商编号</span>
                </th>
                <th data-options="field:'supplierName',width:150,sortable:true,formatter:function(value,row){
																				 if(row.supplierName == null) {
																					return row.intentionSupplierName;
																				 }else {
																					return row.supplierName;
																				 }
																				}">
                	<span>供应商/意向供应商名称</span>
                </th>
				<th data-options="field:'applicationStatus',width:110,sortable:true,formatter:function(value,row){
																				 if(row.applicationStatus == null) {
																					return '无';
																				 }else if(row.applicationStatus == 'CG'){
																					return '草稿';
																				 }else if(row.applicationStatus == 'GB'){
																				 return '关闭';
																				 }else if(row.applicationStatus == 'YX'){
																				 return '允许OA同步';
																				 }else if(row.applicationStatus == 'SPZ'){
																				 return '审批中';
																				 }else if(row.applicationStatus == 'SPTG'){
																				 return '审批通过';
																				 }else if(row.applicationStatus == 'W'){
																				 return '无';
																				 }
																				}">
                	<span>辅料OA申请单状态</span>
                </th>
				<th data-options="field:'centreTypeName',width:80,sortable:true">
                	<span>中分类</span>
                </th>
				<th data-options="field:'smallTypeName',width:80,sortable:true">
                	<span>小分类</span>
                </th>
                <th data-options="field:'detailTypeName',width:80,sortable:true">
                	<span>明细类</span>
                </th>
				<th data-options="field:'fineTypeName',width:80,sortable:true">
                	<span>细分类</span>
                </th>
				<th data-options="field:'enquiryCode',width:100,sortable:true">
                	<span>询价单号</span>
                </th>
				<th data-options="field:'enquiryCreated',width:100,sortable:true">
                	<span>询价单创建日期</span>
                </th>
				<th data-options="field:'enquiryCreateby',width:100,sortable:true">
                	<span>询价单创建人</span>
                </th>
				<th data-options="field:'quotedCode',width:100,sortable:true">
                	<span>报价单号</span>
                </th>
				<th data-options="field:'quotedDate',width:100,sortable:true">
                	<span>供应商报价日期</span>
                </th>
				<th data-options="field:'quotedCreated',width:100,sortable:true">
                	<span>报价单上传日期</span>
                </th>
                <th data-options="field:'accessorySapCode',width:120,sortable:true">
                	<span>SAP物料号</span>
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>