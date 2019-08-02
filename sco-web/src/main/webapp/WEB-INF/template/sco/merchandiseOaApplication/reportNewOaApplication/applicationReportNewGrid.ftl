<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
     <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/merchandiseOaApplication/reportNewOaApplication/applicationReportNewGrid.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
</head>
<body>
<!-- 申请报告(新品引进)管理主表 --> 
<div id="applicationReportNew_toolbar">
	 <form id="applicationReportNew_search">
		<table>
	  	<tr>
	  		<td align="right">SCO申请单号：</td>
			<td >
				<input class="easyui-validatebox" filterName="applicationCode" type="text" name="applicationCode" id="applicationCode" data-options="required:false,editable:false" style="width:120px;" />
			</td> 
	  		<td align="right">供应商/意向供应商编号：</td>
			<td >
				<input class="easyui-validatebox" filterName="intentionSupplierCode" type="text" name="intentionSupplierCode" id="intentionSupplierCode" data-options="required:false,editable:false" style="width:120px;" />
			</td>  
			<td align="right">供应商/意向供应商名称：</td>   
			<td>       
				<input class="easyui-validatebox" filterName="intentionSupplierName" type="text" name="intentionSupplierName" id="intentionSupplierName" data-options="required:false,editable:false" style="width:120px;" />		  
			</td> 
	   </tr>
	  <tr>
			<td align="right">意向品编号：</td>
			<td>
				<input class="easyui-validatebox" filterName="intentionCode" type="text" name="intentionCode" id="intentionCode" data-options="required:false,editable:false" style="width:120px;" />
			</td>
			<td align="right">意向品名称：</td>
			<td>     
			    <input class="easyui-validatebox" filterName="intentionName" type="text" name="intentionName" id="intentionName" data-options="required:false,editable:false" style="width:120px;" />
			</td>
		    <td align="right">意向品创建时间：</td>
			<td >
				<input class="easyui-datebox filterInput" filterName="createDateStart" type="text" name="createDateStart" id="createDateStart" data-options="required:false,editable:false" style="width:124px;" />
			</td>
			<td align="right">至：</td>
			<td >
			    <input class="easyui-datebox filterInput" filterName="createDateEnd" type="text" name="createDateEnd" id="createDateEnd" data-options="required:false,editable:false" style="width:124px;" />
			</td>
	   </tr>
	  	<tr>
		    <td align="right">中分类：</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="centreTypeCode" name="centreTypeCode" id="centreTypeCodeElse" style="width:124px;" 
						data-options="
						valueField:'id',
						textField:'text',
						editable:false,
						url:'masterDataType_listCentreType_5.html'">
				</input>
			</td>
			<td align="right">小分类：</td>
			<td>     
				<input class="easyui-combobox filterSelect" filterName="smallTypeCode" name="smallTypeCode"  id="smallTypeCode" style="width:124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
				</input>			
			</td>
			<td align="right">明细类：</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="detailTypeCode" name="detailTypeCode"  id="detailTypeCode" style="width:124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
				</input>			
			</td>  
			<td align="right">细分类：</td>   
			<td>       
				<input class="easyui-combobox filterSelect" filterName="fineTypeCode" name="fineTypeCode"  id="fineTypeCode" style="width:124px;"
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
				</input>		
			</td> 
	   </tr>
	  	<tr>
		  <td align="right">采购部门：</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="purchaseDepartments"  name="purchaseDepartments" id="purchaseDepartments" style="width:124px;"
					data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'businessComBox_procurementDepartments_5.html'">
				</input>	
			</td>
			<td align="right">是否定量：</td>
			<td>     
			    <input class="easyui-combobox filterSelect" filterName="rationed"  name="rationed" id="rationed"style="width:124px;"
					data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'businessComBox_rationed_5.html'">
				</input>
			</td>
			<td align="right">采购类型：</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="purchaseType" name="purchaseType" id="purchaseType" style="width:124px;"
					data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'businessComBox_purchaseType_5.html'">
				</input>
			</td>  
			<td align="right">销售方式：</td>   
			<td>  
				<input class="easyui-combobox filterSelect" filterName="saleType" name="saleType" id="saleType" style="width:124px;"
					data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'masterDataType_listSaleType_5.html'">
				</input>    
			</td> 
	   </tr>
	   <tr>
		   <td align="right">商品定性角色：</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="dxRoleCode" name="dxRoleCode"  id="dxRoleCode" style="width:124px;"  
					data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'merchandiseRole_listQualitative_5.html'">
				</input>
			</td>
			<td align="right">商品定量角色：</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="dlRoleCode" name="dlRoleCode" id="dlRoleCode"  style="width:124px;"  
					data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'merchandiseRole_listQuantify_5.html'">
				</input>
			</td>
	   </tr>
	   <tr>
	   		<td align="right">新品引进申请单状态：</td>
			<td>     
			    <input class="easyui-combobox filterSelect" filterName="applicationStatus" name="applicationStatus"  id="applicationStatus" style="width:124px;"
			    	data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'businessComBox_merchandiseApplicationStatus_5.html'">
				</input>
			</td>
			<td align="right">新品引进申请单提交时间：</td>
			<td >
				<input class="easyui-datebox filterInput" filterName="applicationDateStart" type="text" name="applicationDateStart" id="applicationDateStart" data-options="required:false,editable:false" style="width:124px;" />
			</td>
			<td align="right">至：</td>
			<td>
				<input class="easyui-datebox filterInput" filterName="applicationDateEnd" type="text" name="applicationDateEnd" id="applicationDateEnd" data-options="required:false,editable:false" style="width:124px;" />
			</td>
	   </tr>
	   <tr>
	   	    <td align="right">巡厂申请单状态：</td>
			<td>     
			    <input class="easyui-combobox filterSelect" filterName="visitApplicationStatus" name="visitApplicationStatus"  id="visitApplicationStatus" style="width:124px;"
			    	data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'businessComBox_visitApplicationStatus_5.html'">
				</input>
			</td>
			<td align="right">巡厂申请单提交时间：</td>
			<td >
				<input class="easyui-datebox filterInput" filterName="visitDateStart" type="text" name="visitDateStart" id="visitDateStart" data-options="required:false,editable:false" style="width:124px;" />
			</td>
			<td align="right">至：</td>
			<td>
				<input class="easyui-datebox filterInput" filterName="visitDateEnd" type="text" name="visitDateEnd" id="visitDateEnd" data-options="required:false,editable:false" style="width:124px;" />
			</td>
	   </tr>
	    <tr>
	   	    <td align="right">包装设计申请单状态：</td>
			<td>     
			    <input class="easyui-combobox filterSelect" filterName="packageApplicationStatus" name="packageApplicationStatus"  id="packageApplicationStatus" style="width:124px;"
			    	data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'businessComBox_visitApplicationStatus_5.html'">
				</input>
			</td>
			<td align="right">包装设计申请提交日期：</td>
			<td >
				<input class="easyui-datebox filterInput" filterName="packageDateStart" type="text" name="packageDateStart" id="packageDateStart" data-options="required:false,editable:false" style="width:124px;" />
			</td>
			<td align="right">至：</td>
			<td>
				<input class="easyui-datebox filterInput" filterName="packageDateEnd" type="text" name="packageDateEnd" id="packageDateEnd" data-options="required:false,editable:false" style="width:124px;" />
			</td>
	   </tr>
	</table>
	</form>	
	<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
		<a onclick="reportNewFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				搜索
		</a>
		<a onclick="reportNewFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
		<a id="exportAllApplication" onclick="reportNewFn.exportAllApplication();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			导出申请单
		</a>
		<a id="insert" onclick="reportNewFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			${action.getText("common.button.add")}
		</a>
		<a id="remove" onclick="reportNewFn.remove();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			${action.getText("common.button.delete")}
		</a>
		<a id="remove" onclick="reportNewFn.createTbpmNew();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			新增创建OA新品引进申请
		</a>
		<a id="remove" onclick="reportNewFn.openTbpmDb();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			打开OA待办
		</a>
		<a id="remove" onclick="reportNewFn.createTbpmVisit();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			新增创建OA巡厂申请
		</a>
		<a id="remove" onclick="reportNewFn.createTbpmPackage();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			新增创建OA包装进度申请
		</a>
		<!--<a onclick="reportNewFn.syncApplication();" plain="true" class="easyui-linkbutton" data-options="iconCls:'active'">
			允许OA同步
		</a>
		<a onclick="reportNewFn.undoApplication();" plain="true" class="easyui-linkbutton" data-options="iconCls:'noactive'">
			撤销OA同步
		</a>-->
		<a id="insert" onclick="reportNewFn.lookApproveOpinion();" plain="true" class="easyui-linkbutton" data-options="iconCls:'return'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			查看OA审批意见
		</a>
	</table>	
</div>
    <table  id="reportNewGrid"
		    fit="true"
		    singleSelect="true"
		    checkOnSelect="false"
	   		selectOnCheck="false"
		    singleSelect="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 100 ]"
			toolbar="#applicationReportNew_toolbar"
			url='reportNew_listMerchandiseIntentionApplication_2.html'
		    data-options="rownumbers:true">  
        <thead data-options="frozen:true">
			<tr>
				<th halign="center" data-options="field:'oaApplicationCode',width:40" hidden="true"></th>
        		<th halign="center" data-options="field:'oaContacts',width:40" hidden="true"></th>
        		<th rowspan="2" data-options="field:'NULL',width:40,checkbox:true"></th>
				<th data-options="field:'applicationCode',halign:'left',width:150,sortable:true,
					formatter : function(value, rowData, rowIndex) {
						if(value!=null && value!=''){
						return '<a href=# style=color:maroon;cursor:hand; onclick=reportNewFn.showEdit(\''+rowData.applicationCode+'\',\''+rowIndex+'\') >'+value+'</a>'
						}
					}">
                	<span >SCO申请单号</span>
                </th>
				<th data-options="field:'intentionCode',halign:'left',width:150,sortable:true">
                	<span>意向品编号</span>
                </th>
				<th data-options="field:'intentionName',halign:'left',width:200,sortable:false">
                	<span>意向品名称</span>
                </th>
			</tr>
		</thead>
        <thead>
        	<tr>
				<th data-options="field:'merchandiseCode',halign:'left',width:150,sortable:true">
                	<span >SAP物料号</span>
                </th>
				<th data-options="field:'intentionSupplierCode',halign:'left',width:150,sortable:true">
                	<span >供应商编号</span>
                </th>
				<th data-options="field:'intentionSupplierName',halign:'left',width:200,sortable:false">
                	<span >供应商名称</span>
                </th>
				<!--<th data-options="field:'applicationStatus',halign:'left',width:200,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.applicationStatusName;
							}else{
								return value;
							}
						}">
                	<span >新品引进申请单状态</span>
                </th>-->
				<th data-options="field:'dxRoleCode',halign:'left',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.dxRoleName;
							}else{
								return value;
							}
						}">
                	<span >商品定性角色</span>
                </th>
				<th data-options="field:'dlRoleCode',halign:'left',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.dlRoleName;
							}else{
								return value;
							}
						}">
                	<span >商品定量角色</span>
                </th>
				<th data-options="field:'purchaseDepartments',halign:'left',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.purchaseDepartmentsName;
							}else{
								return value;
							}
						}">
                	<span >采购部门</span>
                </th>
                <th data-options="field:'rationed',halign:'left',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.rationedName;
							}else{
								return value;
							}
						}">
                	<span >是否定量装</span>
                </th>
				<th data-options="field:'purchaseType',halign:'left',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.purchaseTypeName;
							}else{
								return value;
							}
						}">
                	<span >采购类型</span>
                </th>
				<th data-options="field:'saleType',halign:'left',width:100,sortable:false">
                	<span >销售方式</span>
                </th>
				<th data-options="field:'centreTypeCode',halign:'left',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.centreTypeName;
							}else{
								return value;
							}
						}">
                	<span >中分类</span>
                </th>
				<th data-options="field:'smallTypeCode',halign:'left',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.smallTypeName;
							}else{
								return value;
							}
						}">
                	<span >小分类</span>
                </th>
				<th data-options="field:'detailTypeCode',halign:'left',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.detailTypeName;
							}else{
								return value;
							}
						}">
                	<span >明细类</span>
                </th>
				<th data-options="field:'fineTypeCode',halign:'left',width:100,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.fineTypeName;
							}else{
								return value;
							}
						}">
                	<span >细分类</span>
                </th>
				<!--<th data-options="field:'created',halign:'left',width:150,sortable:true,formatter: function(value,row,index){
							  var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 return  date.format('yyyy-MM-dd');
					          }else{
					          	return '';
					          }
						}">
                	<span >意向品创建日期</span>
                </th>
				<th data-options="field:'createUserName',halign:'left',width:150,sortable:false">
                	<span >意向品创建人</span>
                </th>-->
                
                
                <th data-options="field:'bzDay',halign:'left',width:100,sortable:false">
                	<span >标准总天数</span>
                </th><th data-options="field:'sjDay',halign:'left',width:100,sortable:false">
                	<span >实际总天数</span>
                </th>
                <!--新品引进申请审批状态-->
                <th data-options="field:'applicationStatus',halign:'left',width:200,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.applicationStatusName;
							}else{
								return value;
							}
						}">
                	<span >新品引进申请单状态</span>
                </th>
                <th data-options="field:'applicationLink',halign:'left',width:150,sortable:false">
                	<span >新品引进申请审批环节</span>
                </th>
                <th data-options="field:'visitApplicationStatus',halign:'left',width:200,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.visitApplicationStatusName;
							}else{
								return value;
							}
						}">
                	<span >巡厂申请审批状态</span>
                </th>
                <th data-options="field:'visitApplicationLink',halign:'left',width:150,sortable:false">
                	<span >巡厂申请审批环节</span>
                </th>
                <th data-options="field:'packageApplicationStatus',halign:'left',width:200,sortable:false,formatter: function(value,row,index){
							if(value!=null){
								return row.packageApplicationStatusName;
							}else{
								return value;
							}
						}">
                	<span >包装设计申请审批状态</span>
                </th>
                <th data-options="field:'packageApplicationLink',halign:'left',width:150,sortable:false">
                	<span >包装设计申请审批环节</span>
                </th>
                <!--意向品创建人-->
				<th data-options="field:'createUserName',halign:'left',width:150,sortable:false">
                	<span>意向品创建人</span>
                </th>
                <!--意向品创建日期改为"样品收集日期"-->
                <th data-options="field:'created',halign:'left',width:150,sortable:true,formatter: function(value,row,index){
							  var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 return  date.format('yyyy-MM-dd');
					          }else{
					          	return '';
					          }
						}">
                	<span>样品收集日期</span>
                </th>
                <th data-options="field:'foretastePassDate',halign:'left',width:150,sortable:true,formatter: function(value,row,index){
							  var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 return  date.format('yyyy-MM-dd');
					          }else{
					          	return '';
					          }
						}">
                	<span >试吃通过日期</span>
                </th>
                 <th data-options="field:'xcsqtjDate',halign:'left',width:150,formatter: function(value,row,index){
						 var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 
					          	 if(row.xcsqtjStatus=='1'){
					          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
					          	 }else{
					          	 	return  date.format('yyyy-MM-dd');
					          	 }
					          }else{
					          	return '';
					          }
						}">
                	<span>巡厂申请提交日期</span>
	             </th>   	
				 <th data-options="field:'xcsqshtgDate',halign:'left',width:150,formatter: function(value,row,index){
					var str=value;
			        if(str!=undefined && (str!=null)){  
		            	var arr=str.split('.');
			            str=arr[0];
			           	str=str.replace(/-/g,'/');
			         	var date=new Date(str);
			         	 
			          	if(row.xcsqshtgStatus=='1'){
			          		return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
			          	}else{
			          	 	return  date.format('yyyy-MM-dd');
			          	 }
			        }else{
	          			return '';
		          	}
				 	}">
                	<span>巡厂申请审批通过日期</span>
                 </th> 	
					<th data-options="field:'pksjxcDate',halign:'left',width:150,formatter: function(value,row,index){
						 var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	
					          	 if(row.pksjxcStatus=='1'){
					          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
					          	 }else{
					          	 	return  date.format('yyyy-MM-dd');
					          	 }
					          }else{
					          	return '';
					          }
						}">
	                	<span >品控实际巡厂日期</span>
					</th>
					<th data-options="field:'xpyjbgtjDate',halign:'left',width:150,formatter: function(value,row,index){
						 var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 
					          	 if(row.xpyjbgtjStatus=='1'){
					          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
					          	 }else{
					          	 	return  date.format('yyyy-MM-dd');
					          	 }
					          }else{
					          	return '';
					          }
						}">
	                	<span>新品引进申请提交日期</span>
	                 </th>	
                   <th data-options="field:'xpyjbgsptgDate',halign:'left',width:150,formatter: function(value,row,index){
						 var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	
					          	 if(row.xpyjbgsptgStatus=='1'){
					          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
					          	 }else{
					          	 	return  date.format('yyyy-MM-dd');
					          	 }
					          }else{
					          	return '';
					          }
						}">
	                	<span>新品引进审批通过日期</span>
	                </th> 	
                   <th data-options="field:'zsjsqsptgDate',halign:'left',width:170,formatter: function(value,row,index){
						 var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	
					          	 if(row.zsjsqsptgStatus=='1'){
					          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
					          	 }else{
					          	 	return  date.format('yyyy-MM-dd');
					          	 }
					          }else{
					          	return '';
					          }
						}">
	                	<span>物料主数据申请审批通过日期</span>
	                </th> 	
					<th data-options="field:'bzsjsqDate',halign:'left',width:150,formatter: function(value,row,index){
						 var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 
					          	 if(row.bzsjsqStatus=='1'){
					          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
					          	 }else{
					          	 	return  date.format('yyyy-MM-dd');
					          	 }
					          }else{
					          	return '';
					          }
						}">
	                	<span>包装设计申请提交日期</span>
					</th>
					<th data-options="field:'bzsjsqwcDate',halign:'left',width:150,formatter: function(value,row,index){
						 var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 
					          	 if(row.bzsjsqwcStatus=='1'){
					          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
					          	 }else{
					          	 	return  date.format('yyyy-MM-dd');
					          	 }
					          }else{
					          	return '';
					          }
						}">
	                	<span>包装设计审批通过日期</span>
					</th>
					<th data-options="field:'bzsjcgqrDate',halign:'left',width:150,formatter: function(value,row,index){
						 var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 
					          	 if(row.bzsjcgqrStatus=='1'){
					          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
					          	 }else{
					          	 	return  date.format('yyyy-MM-dd');
					          	 }
					          }else{
					          	return '';
					          }
						}">
	                	<span >包装设计初稿确认日期</span>
					</th>
					<th data-options="field:'bzsjwgtgDate',halign:'left',width:150,formatter: function(value,row,index){
						 var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 
					          	 if(row.bzsjwgtgStatus=='1'){
					          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
					          	 }else{
					          	 	return  date.format('yyyy-MM-dd');
					          	 }
					          }else{
					          	return '';
					          }
						}">
	                	<span>包装设计完稿通过日期</span>
					</th>
					<th data-options="field:'xpffDate',halign:'left',width:150,formatter: function(value,row,index){
						var str = value;
						if(str != undefined && (str != null)){  
							var arr = str.split('.');
							str = arr[0];
							str = str.replace(/-/g,'/');
							var date = new Date(str);
							if(row.xpffStatus=='1'){
								return '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
							}else{
								return date.format('yyyy-MM-dd');
							}
						}else{
							return '';
					  	}
					}">
					<span>新品发放日期</span>
				</th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>