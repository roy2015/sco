<#compress>
<script type="text/javascript" >
	<#include "sco/merchandiseIntention/merchandiseIntentionForm.js" />
</script>
	
<body>
	<div class="easyui-layout" style="height:672px;">  
    	<div data-options="region:'center'" style="padding:5px;">
			<div>
			 	<table>
			 		<tr>
			 			<th>
							<a id="saveForm" onclick="intentionFormFn.submitForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
									保存
							</a>
							<a id="closeForm" onclick="intentionFormFn.closeTab();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
									关闭
							</a>
			 			</th>
			 		</tr>
			 	</table>
			</div>
			<form id="merchandiseIntention_form" method="post">
				<table class="tableForm" border="0" style="width:90%">
					<tr>
						<th  align="right" style="padding-left: 39px;"><font color="red">*</font>采购部门：</th>
						<td >
					   		<select  name="purchaseDepartments" id="purchaseDepartments" class="easyui-combobox" style="width:180px;" data-options="required:true,editable:false,panelHeight:'auto',
						   		onSelect:function(rec){					   			
						   			if(rec.value=='INLAND'){
						   				$('#purchaseType').combobox('disable');
						   				$('#purchaseType').combobox('setValue', '');
						   			}else{
						   				$('#purchaseType').combobox('enable');
						   			}
						   		}
					   		">
								<#list procurementDepartmentsList as list>
									<option <#if merchandiseIntention.purchaseDepartments == list.id> selected ="selected" </#if> value="${list.id}">${list.text}</option>
								</#list>
							</select>
					   	</td>
					    <th  align="right"><font color="red">*</font>是否定量装：</th>
						<td>
							<select name="rationed" id="rationed" class="easyui-combobox" style="width:180px;"  data-options="required:true,editable:false,panelHeight:'auto'">
								<#list rationed as list>
									<option <#if merchandiseIntention.rationed == list.id> selected ="selected" </#if> value="${list.id}">${list.text}</option>
								</#list>
							</select>
						</td>
					</tr>	
					<tr>
					    <th align="right">采购类型：</th>
						<td>
							<select name="purchaseType" id="purchaseType" class="easyui-combobox" style="width:180px;" <#if (merchandiseIntention.purchaseDepartments??) && (merchandiseIntention.purchaseDepartments=='IMPORT') >enabled<#else>disabled</#if> data-options="required:false,editable:false,panelHeight:'auto'">
								<option value=""></option>
								<#list purchaseType as list>
									<option <#if merchandiseIntention.purchaseType == list.id> selected ="selected" </#if> value="${list.id}">${list.text}</option>
								</#list>
							</select>
						</td>
					    <th  align="right" >销售方式：</th>
						<td >
					   		<select name="saleType" id="saleType" class="easyui-combobox" style="width:180px;" data-options="required:false,editable:false">
								<#list saleType as list>
									<option 
										<#if merchandiseIntention.saleType?? && merchandiseIntention.saleType == list> selected ="selected" 
											<#elseif list == "格斗"> selected ="selected"
										</#if> 
										value="${list}">${list}
									</option>
								</#list>
							</select> 
					   	</td>
					</tr>	
					<tr>   	
					    <th  align="right" >意向品编号：</th>
						<td>
							<input name="intentionCode" id="intentionCode" value="${merchandiseIntention.intentionCode}" readonly="readonly" style="background:lightgray;width:176px;" class="easyui-validatebox" data-options="required:false" />
						</td>
					    <th align="right" ><font color="red">*</font>意向品名称：</th>
						<td>
							<input name="intentionName" id="intentionName" value="${merchandiseIntention.intentionName}" class="easyui-validatebox" style="width:176px;"  data-options="required:true,validType:'length[0,33]'" />
						</td>
					</tr>
					<tr>
					   	<th  align="right" ><font color="red">*</font>中分类：</th>
						<td >
							<input name="centreTypeCode" id="centreTypeCodeElse"   class="easyui-combobox" style="width:180px;"  data-options="required:true,valueField:'id',textField:'text',editable:false"/>
					   	</td>
						<th  align="right" ><font color="red">*</font>小分类：</th>
						<td>
							<input name="smallTypeCode" id="smallTypeCode" class="easyui-combobox" style="width:180px;"  data-options="required:true,valueField:'id',textField:'text',editable:false,
								onSelect:function(rec){		
						   			if(rec.text!='其他'){
						   				$('#elseTypeName').validatebox({
						   					required:false
						   				});
						   				document.getElementById('elseTypeNameTd').style.display='none'; 
						   				document.getElementById('eleTd').style.display='none'; 
						   				$('#detailTypeCode').combobox({
						   					required:true
						   				});
						   				$('#fineTypeCode').combobox({
						   					required:false
						   				});
						   			
						   				$('#detailTypeCode').combobox('enable');
						   				$('#fineTypeCode').combobox('enable'); 
						   			}else{
						   				document.getElementById('elseTypeNameTd').style.display='block'; 
						   				document.getElementById('eleTd').style.display='block'; 
						   				$('#elseTypeName').validatebox({
						   					required:true
						   				});
						   				$('#detailTypeCode').combobox({
						   					required:false
						   				});
						   				$('#fineTypeCode').combobox({
						   					required:false
						   				});
						   				$('#fineTypeCode').combobox('setValue', '');
						   				$('#detailTypeCode').combobox('setValue', '');
						   				
						   				$('#detailTypeCode').combobox('disable');
						   				$('#fineTypeCode').combobox('disable');
						   			}
						   		}	
							"/>
						</td>
						<td id="eleTd">
							<div id="elseTypeNameTd" style="display:none">
										<label>请填写自定义小分类名称：</label>
										<input name="elseTypeName" id="elseTypeName" value="<#if merchandiseIntention.elseTypeName?? >${merchandiseIntention.elseTypeName}<#else></#if>" class="easyui-validatebox" style="width:176px;" data-options="required:true"/>
							</div>
						</td>
					</tr>
					<tr>
					    <th align="right" ><font color="red">*</font>明细类：</th>
						<td>
							<input name="detailTypeCode" id="detailTypeCode"  class="easyui-combobox" style="width:180px;"  data-options="required:true,valueField:'id',textField:'text',editable:false" />
						</td>
					    <th  align="right" >细分类：</th>
						<td >
							<input name="fineTypeCode" id="fineTypeCode"   class="easyui-combobox" style="width:180px;" data-options="required:false,valueField:'id',textField:'text',editable:false"/>
					   	</td>
					</tr>
					<tr>	   	
					    <th  align="right" >订购数量：</th>
						<td>
							<input name="orderType" id="orderType" value="${merchandiseIntention.orderType}" class="easyui-validatebox" style="width:176px;" data-options="required:false,validType:'length[0,20]'" />
						</td>
					    <th align="right" ><font color="red">*</font>报价币种：</th>
						<td>
							<input class="easyui-validatebox" style="width:176px;" data-options="required:true,validType:'length[0,10]'" name="quotedCurrency" id="quotedCurrency" value="${merchandiseIntention.quotedCurrency}" />
						</td>
					</tr>
					<tr>	   	
					    <th  align="right" >付款方式：</th>
						<td colspan="4">
							<textarea name="paymentType"  id="paymentType"  class="easyui-validatebox" style="width:100%;height:50px"  data-options="required:false,validType:'length[0,33]'" >${merchandiseIntention.paymentType}</textarea>
						</td>
					</tr>
					<tr>
					    <th align="right" >交货方式：</th>
						<td	colspan="4">
							<textarea name="deliveryType"  id="deliveryType"  class="easyui-validatebox" style="width:100%;height:50px"  data-options="required:false,validType:'length[0,33]'" >${merchandiseIntention.deliveryType}</textarea>
						</td>
					</tr>
					<tr>	   	
					    <th align="right" >意向品规格：</th>
						<td	colspan="4">
							<textarea name="specification"  id="specification"  class="easyui-validatebox" style="width:100%;height:50px"  data-options="required:false,validType:'length[0,33]'" >${merchandiseIntention.specification}</textarea>
						</td>
					</tr>
					<tr>   
					    <th align="right" >包装要求：</th>
						<td	colspan="4">
							<textarea name="packingType"  id="packingType"  class="easyui-validatebox" style="width:100%;height:50px"  data-options="required:false,validType:'length[0,33]'" >${merchandiseIntention.packingType}</textarea>
						</td>
					</tr>
				</table>
			</form>

			
			<!-- 下面为关联供应商模块-->
			<div style="padding: 9px;"></div>
			<div >
				<table>
		 			<tr>
						<th align="right">
							<td style="padding-left: 5px;">选择供应商:供应商编号-供应商名称：</td>
							<td>
								<input  id="supplier" name="supplierCode" class="easyui-combobox list-input" style="width:160px;"  data-options="editable:false" />
								<div id="sp">
									<div style="width:380px;height:340px;">
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
													url='supplierData_listAllSupplier_2.html'
												    data-options="rownumbers:true,onBeforeLoad:intentionFormFn.initSupplierCombox,onLoadSuccess:intentionFormFn.initSupplierGrid"> 
											 <thead>
												<tr>
												    <th data-options="field:'id',width:150,sortable:false">
									                	<span class="txtCenter">供应商编号</span>
									                </th>
												    <th data-options="field:'text',width:180,sortable:false">
									                	<span class="txtCenter">供应商名称</span>
									                </th>
									            </tr>
									            <tr>
													<th><input style="width:130px;height:14px;" class="filterInput" filterName="id"/></th>
													<th><input style="width:160px;height:14px;" class="filterInput" filterName="text"/></th>
									            </tr>
									        </thead>
									    </table>
									</div>
								</div>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;<a id="relateSupplier" onclick="intentionFormFn.relateSupplier();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" <#if (merchandiseIntention.intentionCode?? ) >enabled<#else>disabled</#if> >添加</a>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;<a id="createSupplier" onclick="intentionFormFn.showCreateSupplierForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >创建意向供应商</a>
							</td>
							<td>
								&nbsp;&nbsp;&nbsp;<a id="cancelSupplier" onclick="intentionFormFn.cancelSupplier();" plain="true" class="easyui-linkbutton" data-options="iconCls:'cancel'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >取消供应商</a>
							</td>
						</th>	
					</tr>
				</table>
			</div>
			 
			<table id="intentionSupplierGrid"
			     title="已选供应商"
			     checkOnSelect="false"
		   		 selectOnCheck="false"
			     singleSelect="false"
				 pagination = "true"
				 pagePosition = 'bottom'
				 pageSize = "5"
				 pageList = "[ 5, 10, 15, 20 ]"
				 fitColumns="true"
				 fit="true"
				 toolbar="#supplier_toolbar"
				 url="merchandiseIntention_listIntentionSupplierMerchandise_2.html?intentionCode=<#if merchandiseIntention.intentionCode?? >${merchandiseIntention.intentionCode}<#else></#if>"
			     data-options="rownumbers:true"> 
			   
			    <thead>
			   		<tr>
			   			<th data-options="field:'NULL',width:40,checkbox:true"></th>
			   			<th data-options="field:'intentionSupplierCode',width:40">
							<span >供应商编号</span>
						</th>
						<th data-options="field:'intentionSupplierName',width:40">
							<span >供应商名称</span>
						</th>
			   		</tr>
		   		</thead>
			</table> 	
    	</div>  
	</div>  
</body>
</#compress>