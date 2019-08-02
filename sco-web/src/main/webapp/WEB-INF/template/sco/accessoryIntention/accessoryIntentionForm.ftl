<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/accessoryIntention/accessoryIntentionForm.js" />
</script>
</head>
<body>
 <div>
	 
					<a id="saveForm" onclick="accessoryIntentionFormFn.save();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
							保存
					</a>
					<a id="closeForm" onclick="accessoryIntentionFormFn.closeTab();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
							关闭
					</a>
	 			
	 </div>
	<form id="accessoryIntention_form" method="post">
		<table class="tableForm" style="width:650px;">
			<tr>
				<td style="text-align: right;"><font color="red">*</font>意向品编号:</th>
				<td><input name="intentionCode" id="intentionCode" readonly="readonly"  value="${accessoryIntention.intentionCode}" class="easyui-validatebox" style="background-color: rgb(235, 235, 228);width: 150px;"/></td>
				<td style="text-align: right;"><font color="red">*</font>意向品名称:</th>
				<td><input name="intentionName" value="${accessoryIntention.intentionName}" class="easyui-validatebox" data-options="required:true,validType:'length[1,33]'" style="width: 150px;" /></td>
			</tr>
			<tr>
				<td style="text-align: right;"><font color="red">*</font>中分类:</td>
				<td>
					<input name="centreTypeCode" id="centreTypeCode" class="easyui-combobox" style="width: 154px;"
						data-options="required:true,valueField:'id',textField:'text',panelHeight:'220',editable:false" />
				</td>
				<td style="text-align: right;">小分类:</td>
				<td>
					<input name="smallTypeCode" id="smallTypeCode" class="easyui-combobox" style="width: 154px;"
						data-options="required:false,valueField:'id',textField:'text',panelHeight:'220',editable:false" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">明细类:</td>
				<td><input name="detailTypeCode" id="detailTypeCode" class="easyui-combobox" style="width: 154px;"
					data-options="required:false,valueField:'id',textField:'text',panelHeight:'220',editable:false" /></td>
				<td style="text-align: right;"><font color="red">*</font>细分类:</td>
				<td><input name="fineTypeCodes" id="fineTypeCodes" value="${accessoryIntention.fineTypeCodes}" class="easyui-combobox" style="width: 154px;"
					data-options="required:true,valueField:'id',textField:'text',panelHeight:'220',editable:false,url:'accessoryIntention_listMinceType_5.html'" /></td>
			</tr>
			
			
			<tr>
						<td style="text-align: right;">选择供应商:</td>
						<td>
							<input  id="supplier" name="supplierCode" style="width: 154px;"  class="easyui-combobox list-input" data-options="editable:false" />
							<div id="sp">
								<div style="width:350px;height:340px;">
									<table  id="supplier_grid"
											    class="easyui-datagrid"
											    border="none"
											    fit="true"
											    iconCls= "icon-save"
											    singleSelect="true"
												pagination = "true"
												pagePosition = 'bottom'
												pageSize = "20"
												pageList = "[ 10, 20, 40, 60 ]"
												url='supplierData_listAllSupplierFl_2.html'
											    data-options="rownumbers:true,onBeforeLoad:accessoryIntentionFormFn.initSupplierCombox,onLoadSuccess:accessoryIntentionFormFn.initSupplierGrid"> 
										 <thead>
											<tr>
											    <th data-options="field:'id',width:150,sortable:false">
								                	<span >供应商编号</span>
								                </th>
											    <th data-options="field:'text',width:150,sortable:false">
								                	<span >供应商名称</span>
								                </th>
								            </tr>
								            <tr>
												<th><input style="width:80px;height:14px;float:left;" class="filterInput" filterName="id"/></th>
												<th><input style="width:80px;height:14px;float:left;" class="filterInput" filterName="text"/></th>
								            </tr>
								        </thead>
								    </table>
								</div>
							</div>
							<a id="saveForm" onclick="accessoryIntentionFormFn.relateSupplier();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'" ;">
							添加
							</a>
						</td>
						<td align="left">
							
						</td>
						<td></td>
				</tr>
		</table>
	</form>
		<!-- 下面为关联供应商模块-->
			<table>		
	             <tr>
	             	<td>
							<a id="closeForm" onclick="accessoryIntentionFormFn.createSupplier('${accessoryIntention.intentionCode}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" ;">
							创建意向供应商</a>
					</td>
	                 <td><a id="closeForm" onclick="accessoryIntentionFormFn.cancelSupplier('${accessoryIntention.intentionCode}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'cancel'" ;">
								取消已选择
                         </a>
                     </td>
                 </tr> 
	 	</table>
	  <table  id="accessoryIntentionSupplier_grid"
		    class="easyui-datagrid"
		     title="已选供应商" 
			 pagination = "true"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
		     nowrap="false"
			toolbar="#accessoryIntentions_toolbar"
			url='accessoryIntention_listAccessoryIntentionSupplier_2.html?intentionCode=${accessoryIntention.intentionCode}'
		    data-options="rownumbers:true" >  
        <thead>
        	<tr>
        	    <th data-options="width:65,checkbox:true">
                </th>
				<th data-options="field:'intentionSupplierCode',width:500,sortable:true">
                	<span >供应商编号</span>
                </th>
				<th data-options="field:'intentionSupplierName',width:530,sortable:true">
                	<span >供应商名称</span>
                </th>
            </tr>
            
        </thead>
    </table>
</body>
</html>
</#compress>