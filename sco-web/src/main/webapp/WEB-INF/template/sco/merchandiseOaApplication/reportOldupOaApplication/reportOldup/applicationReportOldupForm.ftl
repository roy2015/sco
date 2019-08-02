<#compress>
	<style>
		.checkTableForm th{text-align:center;}
	</style>
	<script type="text/javascript" >
			<#include "sco/merchandiseOaApplication/reportOldupOaApplication/reportOldup/applicationReportOldupForm.js" />
	</script>
	<div>
		<form id="reportOldup_form" method="post">
			<table>
				<tr>
					<th>
						<a id="saveForm" onclick="reportOldupFormFn.submitReportForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
								保存
						</a>
						<a id="saveForm" onclick="reportOldupFormFn.submitReportCGForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
								保存草稿
							</a>
						<a id="closeForm" onclick="reportOldupFormFn.closeForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
								关闭
						</a>
					</th>
				</tr>
			</table>
			<div class="easyui-tabs" data-options="fit:true,border:true" style="width:89%;height:2830px;">
				<#list applicationList as applicationReportOldup>
					<div title="商品${applicationReportOldup_index+1}" >
						<input type="hidden" value="${applicationReportOldup.reportCode}" name="applicationList[${applicationReportOldup_index}].reportCode" id="reportCode${applicationReportOldup_index}" />
						<!--1.商品基础信息-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 3px;"><b>商品基础信息</b></legend>
							<table class="tableForm" >
								<tr>
								    <th align="right" ><font color="red">*</font>商品名称：</th>
									<td>
										<input type="hidden" name="applicationList[${applicationReportOldup_index}].merchandiseCode"  value="${applicationReportOldup.merchandiseCode}" />
										<input name="applicationList[${applicationReportOldup_index}].merchandiseName"  value="${applicationReportOldup.merchandiseName}" class="easyui-validatebox" style="width:210px;" data-options="required:true,validType:'length[0,33]'" />
									</td>
									 <th align="right" ><font color="red">*</font>研发：</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].development"  value="${applicationReportOldup.development}" class="easyui-combobox" style="width:210px;" data-options="required:true,valueField:'id',textField:'text',panelHeight:'auto',editable:false,url:'businessComBox_developmentList_5.html'" />
									</td>
									 <th align="right" ><font color="red">*</font>商品定性角色：</th>
									<td>
										<input type="hidden" name="applicationList[${applicationReportOldup_index}].dxRoleName" id="${applicationReportOldup_index}dxRoleCode" />
										<input name="applicationList[${applicationReportOldup_index}].dxRoleCode" id="dxRoleCode${applicationReportOldup_index}" value="${applicationReportOldup.dxRoleCode}" class="easyui-combobox" style="width:210px;" data-options="required:true,valueField:'id',textField:'text',editable:false,url:'merchandiseRole_listQualitative_5.html'" />
									</td>
								</tr>
								<tr>
									<th align="right" ><font color="red">*</font>商品定量角色：</th>
									<td>
										<input type="hidden" name="applicationList[${applicationReportOldup_index}].dlRoleName" id="${applicationReportOldup_index}dlRoleCode" />
										<input name="applicationList[${applicationReportOldup_index}].dlRoleCode" id="dlRoleCode${applicationReportOldup_index}" value="${applicationReportOldup.dlRoleCode}" class="easyui-combobox" style="width:214px;" data-options="required:true,valueField:'id',textField:'text',editable:false,url:'merchandiseRole_listQuantify_5.html'" />
									</td>
								
								   <th  align="right" ><font color="red">*</font>中分类：</th>
									<td >
										<input id="valueCentreTypeCode${applicationReportOldup_index}" type="hidden" value="${applicationReportOldup.centreTypeCode}" />
										<input type="hidden" name="applicationList[${applicationReportOldup_index}].centreTypeName" id="${applicationReportOldup_index}centreTypeCode" />
										<input name="applicationList[${applicationReportOldup_index}].centreTypeCode" id="centreTypeCode${applicationReportOldup_index}" class="easyui-combobox" style="width:210px;"  data-options="required:true,valueField:'id',textField:'text',editable:false"/>
								   	</td>
									<th  align="right" ><font color="red">*</font>小分类：</th>
									<td>
										<input id="valueSmallTypeCode${applicationReportOldup_index}" type="hidden" value="${applicationReportOldup.smallTypeCode}" />
										<input type="hidden" name="applicationList[${applicationReportOldup_index}].smallTypeName" id="${applicationReportOldup_index}smallTypeCode" />
										<input name="applicationList[${applicationReportOldup_index}].smallTypeCode" id="smallTypeCode${applicationReportOldup_index}" class="easyui-combobox" style="width:210px;"  data-options="required:true,valueField:'id',textField:'text',editable:false"/>
									</td>
								</tr>
								<tr>  
								    <th align="right" ><font color="red">*</font>明细类：</th>
									<td>
										<input id="valueDetailTypeCode${applicationReportOldup_index}" type="hidden" value="${applicationReportOldup.detailTypeCode}" />
										<input type="hidden" name="applicationList[${applicationReportOldup_index}].detailTypeName" id="${applicationReportOldup_index}detailTypeCode" />
										<input name="applicationList[${applicationReportOldup_index}].detailTypeCode" id="detailTypeCode${applicationReportOldup_index}" class="easyui-combobox" style="width:214px;"  data-options="required:true,valueField:'id',textField:'text',editable:false"/>
									</td>
								    <th  align="right" >细分类：</th>
									<td >
										<input id="valueFineTypeCode${applicationReportOldup_index}" type="hidden" value="${applicationReportOldup.fineTypeCode}" />
										<input type="hidden" name="applicationList[${applicationReportOldup_index}].fineTypeName" id="${applicationReportOldup_index}fineTypeCode" />
										<input name="applicationList[${applicationReportOldup_index}].fineTypeCode" id="fineTypeCode${applicationReportOldup_index}" class="easyui-combobox" style="width:210px;"  data-options="required:false,valueField:'id',textField:'text',editable:false"/>
								   	</td>
								    <th align="right"><font color="red">*</font>供应商编号：</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].supplierCode" readonly="readonly" value="${applicationReportOldup.supplierCode}" class="easyui-validatebox" style="width:207px;background:lightgray;" data-options="required:true,validType:'length[0,30]'" />
									</td>
								</tr>
								<tr>    
								    <th align="right"><font color="red">*</font>供应商全称：</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].supplierName"  value="${applicationReportOldup.supplierName}" class="easyui-validatebox" style="width:210px;" data-options="required:true,validType:'length[0,33]'" />
									</td>
								    <th align="right"><font color="red">*</font>供应商所在地：</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].supplierSite"  value="${applicationReportOldup.supplierSite}" class="easyui-validatebox" style="width:207px;" data-options="required:true,validType:'length[0,100]'" />
									</td>
								    <th align="right" ><font color="red">*</font>现有小分类SKU数：</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].smallTypeSku"  value="<#if applicationReportOldup.smallTypeSku?? >${applicationReportOldup.smallTypeSku?c}</#if>" class="easyui-numberbox" style="width:207px;" data-options="required:true,min:0,max:999999999" />
									</td>
								</tr>
								<tr>	
								    <th align="right" ><font color="red">*</font>现有明细类SKU数：</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].detailTypeSku"  value="<#if applicationReportOldup.detailTypeSku?? >${applicationReportOldup.detailTypeSku?c}</#if>" class="easyui-numberbox" style="width:210px;" data-options="required:true,min:0,max:999999999" />
									</td>
								</tr>
							</table>
						</fieldset>
						<!-- 2.商品主要检验标准-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>商品主要检验标准</b></legend>
							<table  class="tableForm checkTableForm" >
								<tr>
									<th style="text-align:right;width:90px;"></th>
									<th style="text-align:left;width:150px;">原标准</th>
									<th style="text-align:left;width:150px;">现标准</th>
								</tr>
								<tr>
									<th style="text-align:right"><font color="red">*</font>色泽</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].oldStandardColour"  value="<#if (applicationReportOldup.oldStandardColour)?? >${applicationReportOldup.oldStandardColour}</#if>" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].newStandardColour"  value="<#if applicationReportOldup.newStandardColour?? >${applicationReportOldup.newStandardColour}</#if>" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
								</tr>
								<tr>
									<th style="text-align:right"><font color="red">*</font>滋气味</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].oldStandardSmell"  value="<#if applicationReportOldup.oldStandardSmell?? >${applicationReportOldup.oldStandardSmell}</#if>" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].newStandardSmell"  value="<#if applicationReportOldup.newStandardSmell?? >${applicationReportOldup.newStandardSmell}</#if>" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
								</tr>
								<tr>
									<th style="text-align:right"><font color="red">*</font>组织形态</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].oldStandardFrom"  value="<#if applicationReportOldup.oldStandardFrom?? >${applicationReportOldup.oldStandardFrom}</#if>" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].newStandardForm"  value="<#if applicationReportOldup.newStandardForm?? >${applicationReportOldup.newStandardForm}</#if>" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
								</tr>
								<tr>
									<th style="text-align:right"><font color="red">*</font>水份含量</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].oldMoistureContent"  value="${applicationReportOldup.oldMoistureContent}" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].newMoistureContent"  value="${applicationReportOldup.newMoistureContent}" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
								</tr>
							</table>
						</fieldset>
						
						<!-- 3.历年上下市时间-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>历年上下市时间</b></legend>
							<table  id='upDown_Grid${applicationReportOldup_index}'
									class="easyui-datagrid"
									pagination = "true"
									style="height:200px"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportOldup_listUpDownMarketOldup_2.html?intentionCode=${applicationReportOldup.merchandiseCode}&supplierCode=${applicationReportOldup.supplierCode}"
									data-options="rownumbers:true"> 
									 <thead>
										 	<tr>
										    <th data-options="field:'upDate',width:250,sortable:true,halign:'left'">
							                	上市时间
							                </th>
											<th data-options="field:'downDate',width:250,sortable:false,halign:'left'">
							                	下市时间
							                </th>    
										    </tr>
										</thead>
								</table>
						</fieldset>
						<!-- 4.商品历史价格-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>商品历史价格</b></legend>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportOldupFormFn.appendPriceOld(${applicationReportOldup_index})">增加</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportOldupFormFn.removePriceOld(${applicationReportOldup_index})">移除</a>
							<table  id='merchandisePriceOld_Grid${applicationReportOldup_index}'
									class="easyui-datagrid"
									fitColumns="true"  
									style="height:250px"  
									iconCls= "icon-save"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportOldup_listHistoryPriceOldup_2.html?piceType=old&intentionCode=${applicationReportOldup.merchandiseCode}&supplierCode=${applicationReportOldup.supplierCode}&applicationCode=${applicationCode}"
									data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandisePriceOld_toolbar${applicationReportOldup_index}',rownumbers:true,onClickRow: function(index){reportOldupFormFn.onClickRowPriceOld('${applicationReportOldup_index}',index)}"> 
									 <thead>
										 	<tr>
										 		<th data-options="field:'stockSite',width:250,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}}">
													进货地区
											    </th>
												<th data-options="field:'purchasePrice',width:100,halign:'left',align:'right',formatter:moneyFormatter,editor:{type:'numberbox',options:{required:true,min:0,max:999999999,precision:2}}">
													采购价格
											    </th>
												<th data-options="field:'purchaseUnits',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">
										        	采购单位
											    </th>
											    <th data-options="field:'sellRegion',width:250,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}} ">
									       	  		销售地区
											    </th>     
												<th data-options="field:'sellPrice',width:100,halign:'left',align:'right',formatter:moneyFormatter,editor:{type:'numberbox',options:{required:true,min:0,max:999999999,precision:2}}">
													销售价格
											    </th>
												<th data-options="field:'sellUnits',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">
								        			销售单位
											    </th>
												<th data-options="field:'profitRatePercent',width:100,halign:'left',formatter:reportOldupFormFn.formatterPercent,required:false,precision:2,align:'right',editable:false,styler:function(value,row,index){
													return 'background-color:lightgray;';
												}">
											                     毛利率
											    </th>
											 </tr>
										</thead>
								</table>
						</fieldset>
						<!-- 4.商品本次价格-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>商品本次价格</b></legend>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportOldupFormFn.appendPriceNow(${applicationReportOldup_index})">增加</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportOldupFormFn.removePriceNow(${applicationReportOldup_index})">移除</a>
							<table  id='merchandisePriceNow_Grid${applicationReportOldup_index}'
									class="easyui-datagrid" 
									fitColumns="true" 
									style="height:250px"  
									iconCls= "icon-save"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportOldup_listHistoryPriceOldup_2.html?piceType=now&intentionCode=${applicationReportOldup.merchandiseCode}&supplierCode=${applicationReportOldup.supplierCode}&applicationCode=${applicationCode}"
									data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandisePriceNow_toolbar${applicationReportOldup_index}',rownumbers:true,onClickRow: function(index){reportOldupFormFn.onClickRowPriceNow('${applicationReportOldup_index}',index)}"> 
									 <thead>
										 	<tr>
												<th data-options="field:'stockSite',width:250,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}}">
											    	进货地区
											    </th>
												<th data-options="field:'purchasePrice',width:100,align:'right',halign:'left',formatter:moneyFormatter,editor:{type:'numberbox',options:{required:true,min:0,max:999999999,precision:2}}">
										         	采购价格
											    </th>
												<th data-options="field:'purchaseUnits',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">
											    	采购单位
											    </th>
											    <th data-options="field:'sellRegion',width:250,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}} ">
											    	销售地区
											    </th>     
												<th data-options="field:'sellPrice',width:100,align:'right',halign:'left',formatter:moneyFormatter,editor:{type:'numberbox',options:{required:true,min:0,max:999999999,precision:2}}">
										         	销售价格
											    </th>
												<th data-options="field:'sellUnits',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">
											    	销售单位
											    </th>
												<th data-options="field:'profitRatePercent',width:100,align:'right',halign:'left',formatter:reportOldupFormFn.formatterPercent,required:false,precision:2,editable:false,styler:function(value,row,index){
													return 'background-color:lightgray;';
												}">
											                         毛利率
											    </th>
										     </tr>
										</thead>
								</table>
						</fieldset>
						<!--本次价格比历史价格高-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>本次价格比历史价格高</b></legend>
							<table  id='merchandisePriceCompare_Grid${applicationReportOldup_index}'
								class="easyui-datagrid" 
								fitColumns="true"
								style="height:200px"
								pagination = "true"
								pagePosition = 'bottom'
								pageSize = "5"
								pageList = "[ 5, 10, 15, 20 ]"
								url="reportOldup_listComparePriceOldup_2.html?piceType='old'&intentionCode=${applicationReportOldup.merchandiseCode}&supplierCode=${applicationReportOldup.supplierCode}&applicationCode=${applicationCode}"
								data-options="rownumbers:true">
								<thead>
								 	<tr>
										<th data-options="field:'stockSite',width:150,halign:'left'">
									         	进货地区
									    </th>
										<th data-options="field:'purchasePrice',align:'right',halign:'left',width:150,formatter:moneyFormatter">
									         	采购价格
								        </th>
										<th data-options="field:'purchaseUnits',width:150,halign:'left'">
									    		采购单位
									    </th>
									    <th data-options="field:'purchasePricePercent',align:'right',halign:'left',width:150,formatter:reportOldupFormFn.formatterPercent">
									         	采购价格百分比
									    </th>
									    <th data-options="field:'sellRegion',width:150,halign:'left'">
									         	销售地区
									    </th>     
										<th data-options="field:'sellPrice',align:'right',halign:'left',width:150,formatter:moneyFormatter">
									         	销售价格
									    </th>
										<th data-options="field:'sellUnits',width:150,halign:'left'">
									         	销售单位
									    </th>
										<th data-options="field:'sellPricePercent',align:'right',halign:'left',width:150,formatter:reportOldupFormFn.formatterPercent">
									         	销售价格百分比
									     </th>
										<th data-options="field:'profitRatePercent',align:'right',halign:'left',width:150,formatter:reportOldupFormFn.formatterPercent">
									         	毛利率
									    </th>
									 </tr> 
								</thead>
							</table>
						</fieldset>
						<!--商品原料-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>商品原料</b></legend>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportOldupFormFn.appendMaterial(${applicationReportOldup_index})">增加</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportOldupFormFn.removeMaterial(${applicationReportOldup_index})">移除</a>
							<table  id='merchandiseMaterial_Grid${applicationReportOldup_index}'
									class="easyui-datagrid" 
									fitColumns="true"
									style="height:250px"  
									iconCls= "icon-save"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportOldup_listMerchandiseMaterialPriceOld_2.html?intentionCode=${applicationReportOldup.merchandiseCode}&supplierCode=${applicationReportOldup.supplierCode}&applicationCode=${applicationCode}"
									data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers:true,onClickRow: function(index){reportOldupFormFn.onClickRowMaterial('${applicationReportOldup_index}',index)}"> 
									 <thead>
								     	<tr>
											<th data-options="field:'materialName',width:150,halign:'left',editor:{type:'validatebox',options:{required:false,validType:'length[0,33]'}}">
								             	主原料名称
								            </th>
											<th data-options="field:'materialSite',width:150,halign:'left',editor:{type:'validatebox',options:{required:false,validType:'length[0,33]'}}">
								             	主原料产地
								            </th>
											<th data-options="field:'materialDate',width:150,halign:'left',editor:{type:'validatebox',options:{required:false,validType:'length[0,33]'}}">
								             	主原料收购时间
								            </th>
											<th data-options="field:'materialCount',width:100,halign:'left',editor:{type:'validatebox',options:{required:false,validType:'length[0,33]'}}">
								             	现有主原料库存
								            </th>
											<th data-options="field:'sellDate',width:150,halign:'left',editor:{type:'validatebox',options:{required:false,min:0,validType:'length[0,33]'}}">
								             	预估成品可销售时长
								            </th>
											<th data-options="field:'remarks',width:150,halign:'left',editor:{type:'validatebox',options:{required:false,validType:'length[0,300]'}}">
							             		备注
								            </th>
								         </tr>
									</thead>
							</table>
							<div style="padding-top: 17px;">	
								<p style="padding:8px;">采购员分析：请对市场原料进行综合分析，包含：主要产地、原料特点、规格差异、原料价格以及我司选用的原料原因等进行具体分析。</p>
								<textarea name="applicationList[${applicationReportOldup_index}].purchaseAnalysis"  class="easyui-validatebox" style="width:100%;height:120px" data-options="required:false,validType:'length[0,1000]'" >${applicationReportOldup.purchaseAnalysis}</textarea>
							</div>
						</fieldset>
						<!--5.同类商品市场零售价-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>同类商品市场零售价</b></legend>
							<table>	
								<tr>
									<td>
										<label><input type="radio" style="width:20px;" <#if applicationReportOldup.isHaveSameReport?? >checked="checked"<#else><#if applicationReportOldup.reportCode?? ><#else>checked="checked"</#if></#if> id="haveSamePart${applicationReportOldup_index}" name="samePart${applicationReportOldup_index}" value="1" onclick="reportOldupFormFn.changeSameRaido('${applicationReportOldup_index}','1')"/>有</label>
									</td>
									<td>
										<label><input type="radio" style="width:20px;" <#if applicationReportOldup.isHaveSameReport?? ><#else><#if applicationReportOldup.reportCode?? >checked="checked"</#if></#if> id="notHaveSamePart${applicationReportOldup_index}" name="samePart${applicationReportOldup_index}" value="2" onclick="reportOldupFormFn.changeSameRaido('${applicationReportOldup_index}','2')" />无</label>
									</td>
							   </tr>
							</table>
							<div id="notSame_div${applicationReportOldup_index}" <#if applicationReportOldup.isHaveSameReport?? >style="display:none"<#else><#if applicationReportOldup.reportCode?? ><#else>style="display:none"</#if></#if> >
								暂无同类商品
							</div>
							<div id="same_div${applicationReportOldup_index}" <#if applicationReportOldup.isHaveSameReport?? ><#else><#if applicationReportOldup.reportCode?? >style="display:none"</#if></#if> >
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportOldupFormFn.appendSame(${applicationReportOldup_index})">增加</a>
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportOldupFormFn.removeSame(${applicationReportOldup_index})">移除</a>
								<br/><label><font color="red">注：必须包含主要竞品、以及市场销量前三品牌</font></label>
								<table  id='merchandiseSame_Grid${applicationReportOldup_index}'
									class="easyui-datagrid" 
									fitColumns="true" 
									style="height:250px"  
									iconCls= "icon-save"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportOldup_listSameMerchandiseOldup_2.html?intentionCode=${applicationReportOldup.merchandiseCode}&supplierCode=${applicationReportOldup.supplierCode}&applicationCode=${applicationCode}"
									data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandiseSame_toolbar${applicationReportOldup_index}',rownumbers:true,onClickRow: function(index){reportOldupFormFn.onClickRowSame('${applicationReportOldup_index}',index)}"> 
									 <thead>
								     	<tr>
											<th data-options="field:'trademark',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
								             	品牌
								            </th>
											<th data-options="field:'merchandiseName',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
								             	商品名称
								            </th>
											<th data-options="field:'sellChannel',width:250,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
								             	销售渠道
								            </th>
											<th data-options="field:'sellUnits',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
								             	销售单位
								            </th>
											<th data-options="field:'sellPrice',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:['length[0,300]']}}">
								             	零售价（元/销售单位）
								            </th>
											<th data-options="field:'kgPrice',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:['length[0,300]']}}">
								             	折合公斤价（元/公斤）
								            </th>
								            <th data-options="field:'remarks',width:150,halign:'left',editor:{type:'validatebox',options:{required:false,validType:'length[0,300]'}}">
							             		备注
							             	</th>
										</tr>
									</thead>
								</table>
						  	</div>
						</fieldset>
						<!--6.商品销售预计-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>商品销售预计</b></legend>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportOldupFormFn.appendSell(${applicationReportOldup_index})">增加</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportOldupFormFn.removeSell(${applicationReportOldup_index})">移除</a>
							<table class="tableForm" >
								<tr>
									<th align="left" ><font color="red">*</font>预计销售总门店数：</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].anticipatedSellStoreQuantit"  value="<#if applicationReportOldup.anticipatedSellStoreQuantit?? >${applicationReportOldup.anticipatedSellStoreQuantit?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,min:0,max:999999999" />
									</td>
									<th align="left" ><font color="red">*</font>预计销售量(最小单位/单店单天)：</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].anticipatedSellQuantity"  value="<#if applicationReportOldup.anticipatedSellQuantity?? >${applicationReportOldup.anticipatedSellQuantity?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,min:0,max:999999999,precision:2" />
									</td>
									<th align="right"><font color="red">*</font>预计毛利额(最小单位/单店单天)：</th>
									<td>
										<input name="applicationList[${applicationReportOldup_index}].anticipatedSellProfit"  value="<#if applicationReportOldup.anticipatedSellProfit?? >${applicationReportOldup.anticipatedSellProfit?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,min:0,max:999999999,precision:2" />
									</td>
								</tr>
							</table>
							<table id='merchandiseSell_Grid${applicationReportOldup_index}' 
								class="easyui-datagrid" 
								style="height:230px"  
								iconCls= "icon-save"
								pagination = "true"
								pagePosition = 'bottom'
								pageSize = "5"
								pageList = "[ 5, 10, 15, 20 ]"
								url="reportOldup_listAnticipatedSellOldup_2.html?intentionCode=${applicationReportOldup.merchandiseCode}&supplierCode=${applicationReportOldup.supplierCode}&applicationCode=${applicationCode}"
								data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandiseSell_toolbar${applicationReportOldup_index}',rownumbers:true,onClickRow: function(index){reportOldupFormFn.onClickRowSell('${applicationReportOldup_index}',index)}">
								 <thead>
							     	<tr>
										<th data-options="field:'sellRegion',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}} ">
							             	销售区域
							            </th>
										<th data-options="field:'sellStoreCount',align:'right',halign:'left',width:150,editor:{type:'numberbox',options:{required:true,min:0,max:999999999}},formatter:reportOldupFormFn.formatterSellStoreCount">
							             	预计销售门店数
							            </th>
							         </tr>
							    </thead> 
							</table>
						</fieldset>
						<!--6.采购员意见-->
					    <fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>采购员意见</b></legend>
							<textarea name="applicationList[${applicationReportOldup_index}].purchaseOpinion"  class="easyui-validatebox" style="width:100%;height:150px" data-options="required:true,validType:'length[0,1000]'" >${applicationReportOldup.purchaseOpinion}</textarea>
						</fieldset>
				  	</div>
				</#list>
			</div>	
		</form>
	</div>
</#compress>