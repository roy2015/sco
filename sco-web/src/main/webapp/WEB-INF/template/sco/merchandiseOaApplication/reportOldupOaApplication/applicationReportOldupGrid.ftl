<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/merchandiseOaApplication/reportOldupOaApplication/applicationReportOldupGrid.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
</head>
<body>
<!-- 申请报告(老品新上)管理主表 --> 
<div id="applicationReportOldup_toolbar">
		<form id="applicationReportOldup_search">
		<table>
	  	<tr>
	  		<td align="right">供应商编号：</td>
			<td >
				<input class="easyui-validatebox" filterName="supplierCode" type="text" name="supplierCode" id="supplierCode" data-options="required:false,editable:false" style="width:120px;" />
			</td>  
			<td align="right">供应商名称：</td>   
			<td>       
				<input class="easyui-validatebox" filterName="supplierName" type="text" name="supplierName" id="supplierName" data-options="required:false,editable:false" style="width:120px;" />		  
			</td> 
			<td align="right">商品编号：</td>
			<td>
				<input class="easyui-validatebox" filterName="merchandiseCode" type="text" name="merchandiseCode" id="merchandiseCode" data-options="required:false,editable:false" style="width:120px;" />
			</td>
			<td align="right">商品名称：</td>
			<td>     
			    <input class="easyui-validatebox " filterName="merchandiseName" type="text" name="merchandiseName" id="merchandiseName" data-options="required:false,editable:false" style="width:120px;" />
			</td>
	   </tr>
	  	<tr>
		   <td align="right">中分类：</td>
			<td>
				<input class="easyui-combobox filterSelect" filterName="centreTypeCode" id="centreTypeCode" style="width:124px;"  
						data-options="
						valueField:'id',
						textField:'text',
						editable:false,
						url:'masterDataType_listCentreType_5.html'">
				</input>
			</td>
			<td align="right">小分类：</td>
			<td>     
				<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width:124px;" 
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
				</input>			
			</td>
			<td align="right">明细类：</td>
			<td>
				<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width:124px;" 
						data-options="
						valueField:'id',
						textField:'text',
						editable:false">
				</input>			
			</td>  
			<td align="right">细分类：</td>   
			<td>       
				<input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineTypeCode" style="width:124px;" 
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
				<input class="easyui-combobox filterSelect" id="purchaseDepartments" name="purchaseDepartments" filterName="purchaseDepartments" style="width:124px;" 
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
			    <input class="easyui-combobox filterSelect"  id="rationed" name="rationed" filterName="rationed" style="width:124px;" 
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
				<input class="easyui-combobox filterSelect" id="purchaseType" name="purchaseType" filterName="purchaseType"  style="width:124px;" 
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
				<input class="easyui-combobox filterSelect"  id="saleType" name="saleType" filterName="saleType" style="width:124px;" 
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
				<input class="easyui-combobox filterSelect" id="dxRoleCode" name="dxRoleCode" filterName="dxRoleCode" style="width:124px;"  
					data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'merchandiseRole_listQualitative_5.html'">
				</input>
			</td>
			<td align="right">商品定量角色：</td>
			<td>
				<input class="easyui-combobox filterSelect" id="dlRoleCode" name="dlRoleCode" filterName="dlRoleCode" style="width:124px;"  
					data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'merchandiseRole_listQuantify_5.html'">
				</input>
			</td>
			<td align="right">SCO申请单号：</td>
			<td >
				<input class="easyui-validatebox " filterName="applicationCode" type="text" name="applicationCode" id="applicationCode" data-options="required:false,editable:false" style="width:120px;" />
			</td>
			<td align="right">申请单状态：</td>
			<td>     
			    <input class="easyui-combobox filterSelect" id="applicationStatus" name="applicationStatus" filterName="applicationStatus" style="width:124px;" 
			    	data-options="
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					editable:false,
					url:'businessComBox_merchandiseApplicationStatus_5.html'">
				</input>
			</td> 
		 </tr>
		 <tr>	
			 <td align="right">申请单提交时间：</td>
			 <td>
				<input class="easyui-datebox filterInput" filterName="applicationDateStart" type="text" name="applicationDateStart" id="applicationDateStart" data-options="required:false,editable:false" style="width:124px;" />
			</td>
			<td align="right">至：</td>
			<td>
				<input class="easyui-datebox filterInput" filterName="applicationDateEnd" type="text" name="applicationDateEnd" id="applicationDateEnd" data-options="required:false,editable:false" style="width:124px;" />
	    	</td>
	    </tr>
	</table>
	</form>	
	<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
		<a onclick="reportOldupFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				搜索
		</a>
		<a onclick="reportOldupFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
			${action.getText("common.button.clearSearches")}
		</a>
		<a id="exportAllApplication" onclick="reportOldupFn.exportAllApplication();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			导出申请单
		</a>
		<a id="insert" onclick="reportOldupFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			${action.getText("common.button.add")}
		</a>
		<a id="remove" onclick="reportOldupFn.remove();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			${action.getText("common.button.delete")}
		</a>
		<a id="remove" onclick="reportOldupFn.createTbpmOldup();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			新增创建OA老品新上申请
		</a>
		<a id="remove" onclick="reportOldupFn.openTbpmDb();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			打开OA待办
		</a>
		<!--<a onclick="reportOldupFn.syncApplication();" plain="true" class="easyui-linkbutton" data-options="iconCls:'active'">
			允许OA同步
		</a>
		<a onclick="reportOldupFn.undoApplication();" plain="true" class="easyui-linkbutton" data-options="iconCls:'noactive'">
			撤销OA同步
		</a>-->
		<a id="insert" onclick="reportOldupFn.lookApproveOpinion();" plain="true" class="easyui-linkbutton" data-options="iconCls:'return'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.oaApplication")>none</#if>;">
			查看OA审批意见
		</a>
	</table>	
</div>
    <table  id="reportOldupGrid"
		    fit="true"
		   	singleSelect="true"
		    checkOnSelect="false"
	   		selectOnCheck="false"
		    singleSelect="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 100 ]"
			toolbar="#applicationReportOldup_toolbar"
			url='reportOldup_listMerchandiseApplication_2.html'
		    data-options="rownumbers:true">  
        <thead data-options="frozen:true">
			<tr>
				<th data-options="field:'oaApplicationCode',width:40,halign:'left'" hidden="true"></th>
        		<th data-options="field:'oaContacts',width:40,halign:'left'" hidden="true"></th>
        		<th rowspan="2" data-options="field:'NULL',width:40,checkbox:true"></th>
				<th data-options="field:'applicationCode',width:150,sortable:true,halign:'left',
					formatter : function(value, rowData, rowIndex) {
						if(value!=null && value!=''){
						return '<a href=# style=color:maroon;cursor:hand; onclick=reportOldupFn.showEdit(\''+rowData.applicationCode+'\',\''+rowData.merchandiseCode+':'+rowData.supplierCode+'\',\''+rowIndex+'\') >'+value+'</a>'
						}
					}"
                	<span >SCO申请单号</span>
                </th>
				<th data-options="field:'merchandiseCode',width:150,sortable:true,halign:'left'">
                	<span >商品编号</span>
                </th>
				<th data-options="field:'merchandiseName',width:200,sortable:false,halign:'left'">
                	<span >商品名称</span>
                </th>
			</tr>
		</thead>
        <thead>
        	<tr>
				<th data-options="field:'supplierCode',width:150,sortable:true,halign:'left'">
                	<span >供应商编号</span>
                </th>
				<th data-options="field:'supplierName',width:200,sortable:false,halign:'left'">
                	<span >供应商名称</span>
                </th>
				<th data-options="field:'applicationStatus',width:200,sortable:false,halign:'left',formatter: function(value,row,index){
							if(value!=null){
								return row.applicationStatusName;
							}else{
								return value;
							}
						}">
                	<span >老品新上申请单状态</span>
                </th>
				<th data-options="field:'dxRoleCode',width:100,sortable:false,halign:'left',formatter: function(value,row,index){
							if(value!=null){
								return row.dxRoleName;
							}else{
								return value;
							}
						}">
                	<span >商品定性角色</span>
                </th>
				<th data-options="field:'dlRoleCode',width:100,sortable:false,halign:'left',formatter: function(value,row,index){
							if(value!=null){
								return row.dlRoleName;
							}else{
								return value;
							}
						}">
                	<span >商品定量角色</span>
                </th>
				<th data-options="field:'purchaseDepartments',width:100,halign:'left',sortable:false">
                	<span >采购部门</span>
                </th>
                <th data-options="field:'rationed',width:100,sortable:false,halign:'left',formatter: function(value,row,index){
							if(value!=null){
								return row.rationedName;
							}else{
								return value;
							}
						}">
                	<span >是否定量装</span>
                </th>
				<th data-options="field:'purchaseType',width:100,sortable:false,halign:'left',formatter: function(value,row,index){
							if(value!=null){
								return row.purchaseTypeName;
							}else{
								return value;
							}
						}">
                	<span >采购类型</span>
                </th>
				<th data-options="field:'saleType',width:100,sortable:false,halign:'left'">
                	<span >销售方式</span>
                </th>
				<th data-options="field:'centreTypeCode',width:100,sortable:false,halign:'left',formatter: function(value,row,index){
							if(value!=null){
								return row.centreTypeName;
							}else{
								return value;
							}
						}">
                	<span >中分类</span>
                </th>
				<th data-options="field:'smallTypeCode',width:100,sortable:false,halign:'left',formatter: function(value,row,index){
							if(value!=null){
								return row.smallTypeName;
							}else{
								return value;
							}
						}">
                	<span >小分类</span>
                </th>
				<th data-options="field:'detailTypeCode',width:100,sortable:false,halign:'left',formatter: function(value,row,index){
							if(value!=null){
								return row.detailTypeName;
							}else{
								return value;
							}
						}">
                	<span >明细类</span>
                </th>
				<th data-options="field:'fineTypeCode',width:100,sortable:false,halign:'left',formatter: function(value,row,index){
							if(value!=null){
								return row.fineTypeName;
							}else{
								return value;
							}
						}">
                	<span >细分类</span>
                </th>
				<th data-options="field:'oaApplicationName',width:150,halign:'left',sortable:false">
                	<span >申请单创建人</span>
                </th>
				<th data-options="field:'oaApplicationDate',width:150,sortable:true,formatter: function(value,row,index){
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
                	<span >申请单创建日期</span>
                </th>
                <th data-options="field:'xcsqDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >巡厂申请日期</span>
					<th data-options="field:'pkxcDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >品控巡厂日期</span>
					</th>
					<th data-options="field:'bzsjsqDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >包装设计申请日期
					</th>
					<th data-options="field:'yjbgtjDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >引进报告提交日期
					</th>
					<th data-options="field:'yjbgwcDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >引进报告完成日期</span>
					</th>
					<th data-options="field:'zsjsqDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >主数据申请日期</span>
					</th>
					<th data-options="field:'zsjsqwcDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >主数据申请完成日期</span>
					</th>
					<th data-options="field:'htqdDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >合同签订日期</span>
					</th>
					<th data-options="field:'bbtgDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >八标提供日期</span>
					</th>
					<th data-options="field:'qdgpDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >取得光盘日期</span>
					</th>
					<th data-options="field:'gyssdgpDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >供应商收到光盘日期</span>
					</th>
					<th data-options="field:'gzysqrDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >包装印刷确认日期</span>
					</th>
					<th data-options="field:'dyqrDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >大样确认日期</span>
					</th>
					<th data-options="field:'spdddcDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >商品到达大仓日期</span>
	                </th>
					<th data-options="field:'ssDate',width:150,halign:'left',formatter: function(value,row,index){
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
	                	<span >上市日期</span>
	                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>