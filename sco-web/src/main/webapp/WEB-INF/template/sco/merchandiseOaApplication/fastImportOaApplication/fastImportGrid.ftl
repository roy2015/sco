<#compress>
<!DOCTYPE html>
<html>
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript">
		<#include "sco/merchandiseOaApplication/fastImportOaApplication/fastImport.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
    <link rel="stylesheet" href="${copoxtPath}css/table.min.css" type="text/css" />
</head>

<body>
	<div id="fastImport_toolbar" style="padding:1px;overflow:auto;">
		<form id="fastImport_search" method="post" style="width:1010px">
			<table border="0">
				<tr>
					<td align="right">SCO申请单号：</td>
					<td><input class="easyui-validatebox" filterName="applicationCode" id="applicationCode" style="width:120px;" /></td>
					<td align="right">供应商/意向供应商编号：</td>
					<td><input class="easyui-validatebox" filterName="intentionSupplierCode" id="intentionSupplierCode" style="width:120px;" /></td>
					<td align="right">供应商/意向供应商名称：</td>   
					<td><input class="easyui-validatebox" filterName="intentionSupplierName" id="intentionSupplierName" style="width:120px;" /></td>
				</tr>
				<tr>
					<td align="right">意向品编号：</td>
					<td><input class="easyui-validatebox" filterName="intentionCode" id="intentionCode" style="width:120px;" /></td>
					<td align="right">意向品名称：</td>
					<td><input class="easyui-validatebox" filterName="intentionName" id="intentionName" style="width:120px;" /></td>
					<td align="right">意向品创建时间：</td>
					<td><input class="easyui-datebox filterInput" filterName="minCreateDate" name="minCreateDate" id="minCreateDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;" /></td>
					<td align="right">至：</td>
					<td><input class="easyui-datebox filterInput" filterName="maxCreateDate" name="maxCreateDate" id="maxCreateDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;" /></td>
				</tr>
				<tr>
					<td align="right">中分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="centreTypeCode" id="centreTypeCodeElse" style="width:124px;" 
							data-options="
							valueField:'id',
							textField:'text',
							editable:false,
							url:'masterDataType_listCentreType_5.html'"
						/>
					</td>
					<td align="right">小分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="smallTypeCode" id="smallTypeCode" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'220',
							editable:false" 
						/>
					</td>
					<td align="right">明细类：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="detailTypeCode" id="detailTypeCode" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							editable:false"
						/>			
					</td>  
					<td align="right">细分类：</td>   
					<td>       
						<input class="easyui-combobox filterSelect" filterName="fineTypeCode" id="fineTypeCode" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							editable:false"
						/>		
					</td> 
				</tr>
				<tr>
				  	<td align="right">采购部门：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="purchaseDepartments" id="purchaseDepartments" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							editable:false,
							url:'businessComBox_procurementDepartments_5.html'" 
						/>
					</td>
					<td align="right">是否定量：</td>
					<td>     
						<input class="easyui-combobox filterSelect" filterName="rationed" id="rationed" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							editable:false,
							url:'businessComBox_rationed_5.html'"
						/>
					</td>
					<td align="right">采购类型：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="purchaseType" id="purchaseType" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							editable:false,
							url:'businessComBox_purchaseType_5.html'"
						/>
					</td>  
					<td align="right">销售方式：</td>   
					<td>  
						<input class="easyui-combobox filterSelect" filterName="saleType" id="saleType" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							editable:false,
							url:'masterDataType_listSaleType_5.html'"
						/>
					</td> 
			   </tr>
			   <tr>
					<td align="right">商品定性角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width:124px;"  
							data-options="
							valueField:'id',
							textField:'text',
							editable:false,
							url:'merchandiseRole_listQualitative_5.html'"
						/>
					</td>
					<td align="right">商品定量角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dlRoleCode" id="dlRoleCode" style="width:124px;"  
							data-options="
							valueField:'id',
							textField:'text',
							editable:false,
							url:'merchandiseRole_listQuantify_5.html'"
						/>
					</td>
				</tr>
				<tr>
					<td align="right">快速引进申请单状态：</td>
					<td>     
						<input class="easyui-combobox filterSelect" filterName="applicationStatus" id="applicationStatus" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							editable:false,
							url:'businessComBox_merchandiseApplicationStatus_5.html'"
						/>
					</td>
					<td align="right">快速引进申请单提交日期：</td>
					<td>
						<input class="easyui-datebox filterInput" filterName="minApplicationDate" id="minApplicationDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;" />
					</td>
					<td align="right">至：</td>
					<td>
						<input class="easyui-datebox filterInput" filterName="maxApplicationDate" id="maxApplicationDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;" />
					</td>
					</tr>
					<tr>
					<td align="right">巡厂申请单状态：</td>
					<td>     
						<input class="easyui-combobox filterSelect" filterName="visitApplicationStatus" id="visitApplicationStatus" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							editable:false,
							url:'businessComBox_visitApplicationStatus_5.html'" 
						/>
					</td>
					<td align="right">巡厂申请单提交日期：</td>
					<td >
						<input class="easyui-datebox filterInput" filterName="minVisitDate" id="minVisitDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;" />
					</td>
					<td align="right">至：</td>
					<td>
						<input class="easyui-datebox filterInput" filterName="maxVisitDate" id="maxVisitDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;" />
					</td>
				</tr>
				<tr>
					<td align="right">包装设计申请单状态：</td>
					<td>     
						<input class="easyui-combobox filterSelect" filterName="packageApplicationStatus" id="packageApplicationStatus" style="width:124px;"
							data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							editable:false,
							url:'businessComBox_visitApplicationStatus_5.html'" 
						/>
					</td>
					<td align="right">包装设计申请提交日期：</td>
					<td>
						<input class="easyui-datebox filterInput" filterName="minPackageDate" id="minPackageDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;" />
					</td>
					<td align="right">至：</td>
					<td>
						<input class="easyui-datebox filterInput" filterName="maxPackageDate" id="maxPackageDate" data-options="editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;" />
					</td>
				</tr>
			</table>
		</form>	
		<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
			<a onclick="fastImportFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
				搜索
			</a>
			<a onclick="fastImportFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
				清空查询
			</a>
			<a onclick="fastImportFn.exportAllApplication();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.oaApplication.fastImport")>none</#if>;">
				导出申请单
			</a>
			<a id="insert" onclick="fastImportFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.oaApplication.fastImport")>none</#if>;">
				新增
			</a>
			<a id="remove" onclick="fastImportFn.remove();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication.fastImport")>none</#if>;">
				删除
			</a>
			<a id="remove" onclick="fastImportFn.createTbpmNew();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication.fastImport")>none</#if>;">
				新增创建OA快速引进申请
			</a>
			<a id="remove" onclick="fastImportFn.openTbpmDb();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication.fastImport")>none</#if>;">
				打开OA待办
			</a>
			<a id="remove" onclick="fastImportFn.createTbpmVisit();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication.fastImport")>none</#if>;">
				新增创建OA巡厂申请
			</a>
			<a id="remove" onclick="fastImportFn.createTbpmPackage();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.oaApplication.fastImport")>none</#if>;">
				新增创建OA包装进度申请
			</a>
			<a id="insert" onclick="fastImportFn.lookApproveOpinion();" plain="true" class="easyui-linkbutton" data-options="iconCls:'return'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.oaApplication.fastImport")>none</#if>;">
				查看OA审批意见
			</a>
		</table>	
	</div>
    <table  id="fastImportGrid"
		    fit="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 100 ]"
			toolbar="#fastImport_toolbar"
			url='fastImportApplication_listApplicationFastImport_2.html'
		    data-options="rownumbers:true">  
        <thead data-options="frozen:true">
			<tr>
				<th rowspan="2" data-options="field:'NULL',width:40,checkbox:true,halign:'left'"></th>
				<th data-options="field:'applicationCode',width:150,sortable:true,halign:'left',
					formatter : function(value, rowData, rowIndex) {
						if(value!=null && value!=''){
						return '<a href=# style=color:maroon;cursor:hand; onclick=fastImportFn.showEdit(\''+rowData.applicationCode+'\',\''+rowData.merchandiseCode+':'+rowData.supplierCode+'\',\''+rowIndex+'\') >'+value+'</a>'
						}
					}"
                	<span>SCO申请单号</span>
                </th>
				<th data-options="field:'intentionCode',width:150,sortable:true,halign:'left'">
                	<span>意向品编号</span>
                </th>
				<th data-options="field:'intentionName',width:200,halign:'left'">
                	<span>意向品名称</span>
                </th>
			</tr>
		</thead>
        <thead>
        	<tr>
				<th data-options="field:'merchandiseCode',width:150,sortable:true,halign:'left'">
                	<span>SAP物料号</span>
                </th>
				<th data-options="field:'intentionSupplierCode',width:150,sortable:true,halign:'left'">
                	<span>供应商编号</span>
                </th>
				<th data-options="field:'intentionSupplierName',width:200,halign:'left'">
                	<span>供应商名称</span>
                </th>
				<th data-options="field:'dxRoleName',width:100,halign:'left'">
                	<span>商品定性角色</span>
                </th>
				<th data-options="field:'dlRoleName',width:100,halign:'left'">
                	<span>商品定量角色</span>
                </th>
				<th data-options="field:'purchaseDepartmentsName',width:100">
                	<span>采购部门</span>
                </th>
                <th data-options="field:'rationedName',width:100,halign:'left'">
                	<span>是否定量装</span>
                </th>
				<th data-options="field:'purchaseTypeName',width:100,halign:'left'">
                	<span>采购类型</span>
                </th>
				<th data-options="field:'saleType',width:100,halign:'left'">
                	<span>销售方式</span>
                </th>
				<th data-options="field:'centreTypeName',width:100,halign:'left'">
                	<span>中分类</span>
                </th>
				<th data-options="field:'smallTypeName',width:100,halign:'left'">
                	<span>小分类</span>
                </th>
				<th data-options="field:'detailTypeName',width:100,halign:'left'">
                	<span>明细类</span>
                </th>
				<th data-options="field:'fineTypeName',width:100,halign:'left'">
                	<span>细分类</span>
                </th>
                <th data-options="field:'bzDay',halign:'left',width:100,sortable:false">
                	<span >标准总天数</span>
                </th><th data-options="field:'sjDay',halign:'left',width:100,sortable:false">
                	<span >实际总天数</span>
                </th>
				<th data-options="field:'applicationStatusName',width:200,halign:'left'">
                	<span>快速引进申请审批状态</span>
                </th>
                <th data-options="field:'applicationLink',halign:'left',width:150,sortable:false">
                	<span >快速引进申请审批环节</span>
                </th>
             	<th data-options="field:'visitApplicationStatusName',width:200,halign:'left'">
                	<span>巡厂申请审批状态</span>
                </th>
                <th data-options="field:'visitApplicationLink',halign:'left',width:150,sortable:false">
                	<span >巡厂申请审批环节</span>
                </th>
                <th data-options="field:'packageApplicationStatusName',width:200,halign:'left'">
                	<span>包装设计申请审批状态</span>
                </th>
             	<th data-options="field:'packageApplicationLink',width:150,halign:'left'">
            		<span>包装设计申请审批环节</span>
                </th>
                <th data-options="field:'createUserName',width:150,sortable:false,halign:'left'">
                	<span>意向品创建人</span>
                </th>
				<th data-options="field:'created',width:150,sortable:true,halign:'left',formatter: function(value,row,index){
					var str = value;
					if(str != undefined && (str != null)){
						var arr = str.split('.');
						str = arr[0];
						str = str.replace(/-/g,'/');
						var date = new Date(str);
						return date.format('yyyy-MM-dd');
					}else{
						return '';
					}
				}">
                	<span>样品收集日期</span>
                </th>
                <th data-options="field:'foretastePassDate',width:150,sortable:true,halign:'left',formatter: function(value,row,index){
					var str=value;
					if(str != undefined && (str != null)){  
						var arr = str.split('.');
						str = arr[0];
						str = str.replace(/-/g,'/');
						var date = new Date(str);
						return date.format('yyyy-MM-dd');
					}else{
						return '';
					}
				 }">
                	<span>试吃通过时间</span>
                </th>
                <th data-options="field:'xcsqtjDate',width:150,sortable:true,halign:'left',formatter: function(value,row,index){
					var str = value;
					if(str != undefined && (str != null)){  
						var arr = str.split('.');
						str = arr[0];
						str = str.replace(/-/g,'/');
						var date = new Date(str);
						  	 
						if(row.xcsqtjStatus == '1'){
							return '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
						}else{
							return date.format('yyyy-MM-dd');
						}
					}else{
						return '';
					}
				}">
					<span>巡厂申请提交日期</span>
				</th>   	
				<th data-options="field:'xcsqshtgDate',width:150,sortable:true,halign:'left',formatter:function(value,row,index){
					var str = value;
		         	if(str != undefined && (str != null)){  
			            var arr = str.split('.');
			            str = arr[0];
			           	str = str.replace(/-/g,'/');
			         	var date = new Date(str);
			         	 
			          	if(row.xcsqshtgStatus == '1'){
			          		return '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
			          	}else{
			          		return date.format('yyyy-MM-dd');
			          	}
		          	}else{
		          		return '';
		          	}
				}">
        			<span>巡厂申请审批通过日期</span>
                </th> 	
				<th data-options="field:'pksjxcDate',width:150,sortable:true,halign:'left',formatter:function(value,row,index){
					 var str = value;
			         if(str != undefined && (str != null)){  
			             var arr = str.split('.');
			             str = arr[0];
			           	 str = str.replace(/-/g,'/');
			         	 var date = new Date(str);
			          	
			          	 if(row.pksjxcStatus == '1'){
			          	 	return '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
			          	 }else{
			          	 	return date.format('yyyy-MM-dd');
			          	 }
			          }else{
			          	return '';
			          }
					}">
                	<span>品控实际巡厂日期</span>
				</th>
				<th data-options="field:'xpyjbgtjDate',width:150,sortable:true,halign:'left',formatter: function(value,row,index){
					 var str=value;
			         if(str != undefined && (str != null)){  
			             var arr = str.split('.');
			             str = arr[0];
			           	 str = str.replace(/-/g,'/');
			         	 var date = new Date(str);
			         	 
			          	 if(row.xpyjbgtjStatus == '1'){
			          	 	return '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
			          	 }else{
			          	 	return date.format('yyyy-MM-dd');
			          	 }
			          }else{
			          	return '';
			          }
					}">
                	<span>快速引进申请提交日期</span>
				</th>
				<th data-options="field:'xpyjbgsptgDate',width:150,sortable:true,halign:'left',formatter: function(value,row,index){
					 var str = value;
			         if(str != undefined && (str != null)){  
			             var arr = str.split('.');
			             str = arr[0];
			           	 str = str.replace(/-/g,'/');
			         	 var date = new Date(str);
			          	
			          	 if(row.xpyjbgsptgStatus == '1'){
			          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
			          	 }else{
			          	 	return date.format('yyyy-MM-dd');
			          	 }
			          }else{
			          	return '';
			          }
					}">
                	<span>快速引进审批通过日期</span>
	              </th> 	
                  <th data-options="field:'zsjsqsptgDate',width:175,sortable:true,halign:'left',formatter: function(value,row,index){
					 var str = value;
			         if(str != undefined && (str != null)){
			             var arr = str.split('.');
			             str = arr[0];
			           	 str = str.replace(/-/g,'/');
			         	 var date = new Date(str);
			          	
			          	 if(row.zsjsqsptgStatus == '1'){
			          	 	return '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
			          	 }else{
			          	 	return date.format('yyyy-MM-dd');
			          	 }
			          }else{
			          	return '';
			          }
					}">
                	<span>物料主数据申请审批通过日期</span>
				</th>
				<th data-options="field:'bzsjsqDate',width:150,sortable:true,halign:'left',formatter: function(value,row,index){
					 var str = value;
			         if(str != undefined && (str != null)){  
			             var arr = str.split('.');
			             str = arr[0];
			           	 str = str.replace(/-/g,'/');
			         	 var date = new Date(str);
			          	 
			          	 if(row.bzsjsqStatus == '1'){
			          	 	return  '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
			          	 }else{
			          	 	return date.format('yyyy-MM-dd');
			          	 }
			          }else{
			          	return '';
			          }
					}">
	            	<span>包装设计申请提交日期</span>
				</th>
				<th data-options="field:'bzsjsqwcDate',width:150,sortable:true,halign:'left',formatter: function(value,row,index){
					 var str = value;
			         if(str != undefined && (str != null)){  
			             var arr = str.split('.');
			             str = arr[0];
			           	 str = str.replace(/-/g,'/');
			         	 var date = new Date(str);
			          	 
			          	 if(row.bzsjsqwcStatus == '1'){
			          	 	return '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
			          	 }else{
			          	 	return date.format('yyyy-MM-dd');
			          	 }
			          }else{
			          	return '';
			          }
					}">
                	<span>包装设计审批通过日期</span>
				</th>
				<th data-options="field:'bzsjcgqrDate',width:150,sortable:true,halign:'left',formatter: function(value,row,index){
					 var str = value;
			         if(str != undefined && (str != null)){  
			             var arr = str.split('.');
			             str = arr[0];
			           	 str = str.replace(/-/g,'/');
			         	 var date = new Date(str);
			          	 
			          	 if(row.bzsjcgqrStatus == '1'){
			          	 	return '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
			          	 }else{
			          	 	return date.format('yyyy-MM-dd');
			          	 }
			          }else{
			          	return '';
			          }
					}">
                	<span>包装设计初稿确认日期</span>
				</th>
				<th data-options="field:'bzsjwgtgDate',width:150,sortable:true,halign:'left',formatter: function(value,row,index){
					 var str = value;
			         if(str != undefined && (str != null)){  
			             var arr = str.split('.');
			             str = arr[0];
			           	 str = str.replace(/-/g,'/');
			         	 var date = new Date(str);
			          	 
			          	 if(row.bzsjwgtgStatus == '1'){
			          	 	return '<span style=\'color:red\'>'+date.format('yyyy-MM-dd')+'</span>';
			          	 }else{
			          	 	return date.format('yyyy-MM-dd');
			          	 }
			          }else{
			          	return '';
			          }
					}">
                	<span>包装设计完稿通过日期</span>
				</th>
				<th data-options="field:'xpffDate',width:150,sortable:true,halign:'left',formatter: function(value,row,index){
					 var str = value;
			         if(str != undefined && (str != null)){  
			             var arr = str.split('.');
			             str = arr[0];
			           	 str = str.replace(/-/g,'/');
			         	 var date = new Date(str);
			          	
		          	 	 return date.format('yyyy-MM-dd');
			          	 if(row.xpffStatus == '1'){
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