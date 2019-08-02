<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/accessoryIntention/accessoryEnquiryForm.js" />
</script>
</head>
<body>
<div>
	 	
					<a id="insertForm" onclick="accessoryEnquiryFormFn.createXJD(true,'${accessoryIntention.intentionCode}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" ;">
							新增询价单  
					</a>
					
	 			
					<a id="delForm" onclick="accessoryEnquiryFormFn.deleteXJD();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" ;">
							删除询价单
					</a>
					
	 			
					<a id="editForm" onclick="accessoryEnquiryFormFn.editXJD();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'" ;">
							查看/修改询价单
					</a>
				
	 </div>
	 
    <table  id="accessoryEnquiry_grid"
		    class="easyui-datagrid"
		     title="已有询价单列表"
			 pagination = "true"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
		     nowrap="false"
			toolbar="#accessoryEnquiry_toolbar"              
			url='accessoryIntention_listAccessoryEnquiry_2.html?intentionCode=${accessoryIntention.intentionCode}'
		    data-options="rownumbers:true" >  
        <thead>
        	<tr>
        	    <th data-options="width:65,checkbox:true">
                </th>
				<th data-options="field:'enquiryCode',width:220,sortable:true,
					formatter : function(value, rowData, rowIndex) {
					return '<a href=# style=color:maroon;cursor:hand; onclick=accessoryEnquiryFormFn.createXJD(false,\''+rowData.enquiryCode+'\') >'+value+'</a>'
					}">
                	<span >询价单编号</span>
                </th>
                <th data-options="field:'enquiryName',width:220,sortable:true"><span >询价单名称</span></th>
				<th data-options="field:'created',width:210,sortable:true">
                	<span >询价单创建日期</span>
                </th>
               <th data-options="field:'supplierCount',width:120,sortable:true,align:'right',formatter: function(value,row,index){
							if(value!=null && value!=''){
								return value;
							}else{
								return 0;
							}
						}">
                	<span >报价供应商数量</span>
                </th>
                <th data-options="field:'attachment',width:100,sortable:true,formatter:function(value, rowData, rowIndex){
																				 if(rowData.attachment != null) {
																					return '<a href=# style=color:maroon;cursor:hand; onclick=accessoryEnquiryFormFn.downloadEnquiryFile(\''+rowIndex+'\') >'+'是'+'</a>';
																				 }else {
																					return '否';
																				 }
																				}">
                	<span >是否已上传附件</span>
                </th>
                <th data-options="field:'createUserName',width:155,sortable:true">
                	<span >询价单创建人</span>
                </th>
            </tr>
            
        </thead>
    </table>
</body>
</html>
</#compress>