<#compress>
	<style>
		.checkTableForm th{text-align:center;}
	</style>
	<script type="text/javascript" >
		<#include "sco/merchandiseOaApplication/reportAdjustpriceOaApplication/reportAdjustprice/applicationReportAdjustpriceForm.js" />
	</script>
	
	<body class="easyui-layout">  
		<#-- <div class="easyui-layout" style="height:500px;"> -->
		<div data-options="region:'center'">	
		
			<form id="reportAdjustprice_form" method="post">
				<table>
					<tr>
						<th>
							<a id="saveForm" onclick="reportAdjustpriceFormFn.submitReportForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
								保存
							</a>
							<a id="saveForm" onclick="reportAdjustpriceFormFn.submitReportCGForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
								保存草稿
							</a>
							<a id="closeForm" onclick="reportAdjustpriceFormFn.closeForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
								关闭
							</a>
						</th>
					</tr>
				</table>
				
				<div class="easyui-tabs" data-options="fit:true,border:true" style="width:89%;height:2376px;">
					<#list applicationList as applicationReportAdjustprice>
						<div style="padding:10px;" title="商品${applicationReportAdjustprice_index+1}" > 
							<input type="hidden" value="${applicationReportAdjustprice.reportCode}" name="applicationList[${applicationReportAdjustprice_index}].reportCode" id="reportCode${applicationReportAdjustprice_index}" />
							<table>	
								<tr>
									<td>
										<label><input type="radio" style="width:20px;" <#if applicationReportAdjustprice.priceAdjust?? ><#if applicationReportAdjustprice.priceAdjust=='down'>checked="checked"</#if><#else>checked="checked"</#if> name="applicationList[${applicationReportAdjustprice_index}].priceAdjust" value="down"/>价格下调</label>
									</td>
									<td>
										<label><input type="radio" style="width:20px;" <#if (applicationReportAdjustprice.priceAdjust??) && (applicationReportAdjustprice.priceAdjust=='up')>checked="checked"</#if> name="applicationList[${applicationReportAdjustprice_index}].priceAdjust" value="up" />价格上调</label>
									</td>
								</tr>
							</table>
							<!--1.商品基础信息-->
							<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
								<legend  style="color:#666;padding:0 3px;"><b>商品基础信息</b></legend>
								<table class="tableForm" >
									<tr>
										<th align="right" ><font color="red">*</font>商品名称：</th>
										<td>
											<input type="hidden" name="applicationList[${applicationReportAdjustprice_index}].merchandiseCode"  value="${applicationReportAdjustprice.merchandiseCode}" />
											<input name="applicationList[${applicationReportAdjustprice_index}].merchandiseName"  value="${applicationReportAdjustprice.merchandiseName}" class="easyui-validatebox" style="width:184px;" data-options="required:true,validType:'length[0,33]'" />
										</td>
										<th align="right" ><font color="red">*</font>研发：</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].development"  value="${applicationReportAdjustprice.development}" class="easyui-combobox" style="width:188px;" data-options="required:true,valueField:'id',textField:'text',panelHeight:'auto',editable:false,url:'businessComBox_developmentList_5.html'" />
										</td>
											<th align="right" ><font color="red">*</font>商品定性角色：</th>
										<td>
											<input type="hidden" name="applicationList[${applicationReportAdjustprice_index}].dxRoleName" id="${applicationReportAdjustprice_index}dxRoleCode" />
											<input name="applicationList[${applicationReportAdjustprice_index}].dxRoleCode" id="dxRoleCode${applicationReportAdjustprice_index}" value="${applicationReportAdjustprice.dxRoleCode}" class="easyui-combobox" style="width:188px;" data-options="required:true,valueField:'id',textField:'text',editable:false,url:'merchandiseRole_listQualitative_5.html'" />
										</td>
									</tr>
									<tr>	
										<th align="right" ><font color="red">*</font>商品定量角色：</th>
										<td>
											<input type="hidden" name="applicationList[${applicationReportAdjustprice_index}].dlRoleName" id="${applicationReportAdjustprice_index}dlRoleCode" />
											<input name="applicationList[${applicationReportAdjustprice_index}].dlRoleCode" id="dlRoleCode${applicationReportAdjustprice_index}" value="${applicationReportAdjustprice.dlRoleCode}" class="easyui-combobox" style="width:188px;" data-options="required:true,valueField:'id',textField:'text',editable:false,url:'merchandiseRole_listQuantify_5.html'" />
										</td>
										<th  align="right" ><font color="red">*</font>中分类：</th>
										<td >
											<input id="valueCentreTypeCode${applicationReportAdjustprice_index}" type="hidden" value="${applicationReportAdjustprice.centreTypeCode}" />
											<input type="hidden" name="applicationList[${applicationReportAdjustprice_index}].centreTypeName" id="${applicationReportAdjustprice_index}centreTypeCode" />
											<input name="applicationList[${applicationReportAdjustprice_index}].centreTypeCode" id="centreTypeCode${applicationReportAdjustprice_index}"   class="easyui-combobox" style="width:188px;"  data-options="required:true,valueField:'id',textField:'text',editable:false"/>
										</td>
										<th  align="right" ><font color="red">*</font>小分类：</th>
										<td>
											<input id="valueSmallTypeCode${applicationReportAdjustprice_index}" type="hidden" value="${applicationReportAdjustprice.smallTypeCode}" />
											<input type="hidden" name="applicationList[${applicationReportAdjustprice_index}].smallTypeName" id="${applicationReportAdjustprice_index}smallTypeCode" />
											<input name="applicationList[${applicationReportAdjustprice_index}].smallTypeCode" id="smallTypeCode${applicationReportAdjustprice_index}"  class="easyui-combobox" style="width:188px;"  data-options="required:true,valueField:'id',textField:'text',editable:false"/>
										</td>
									</tr>
									<tr>   
										<th align="right" ><font color="red">*</font>明细类：</th>
										<td>
											<input id="valueDetailTypeCode${applicationReportAdjustprice_index}" type="hidden" value="${applicationReportAdjustprice.detailTypeCode}" />
											<input type="hidden" name="applicationList[${applicationReportAdjustprice_index}].detailTypeName" id="${applicationReportAdjustprice_index}detailTypeCode" />
											<input name="applicationList[${applicationReportAdjustprice_index}].detailTypeCode" id="detailTypeCode${applicationReportAdjustprice_index}"  class="easyui-combobox" style="width:188px;"  data-options="required:true,valueField:'id',textField:'text',editable:false"/>
										</td>
										<th  align="right" >细分类：</th>
										<td >
											<input id="valueFineTypeCode${applicationReportAdjustprice_index}" type="hidden" value="${applicationReportAdjustprice.fineTypeCode}" />
											<input type="hidden" name="applicationList[${applicationReportAdjustprice_index}].fineTypeName" id="${applicationReportAdjustprice_index}fineTypeCode" />
											<input name="applicationList[${applicationReportAdjustprice_index}].fineTypeCode" id="fineTypeCode${applicationReportAdjustprice_index}"   class="easyui-combobox" style="width:188px;"  data-options="required:false,valueField:'id',textField:'text',editable:false"/>
										</td>
										<th align="right"><font color="red">*</font>供应商编号：</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].supplierCode"  readonly="readonly" value="${applicationReportAdjustprice.supplierCode}" class="easyui-validatebox" style="width:184px;background:lightgray;" data-options="required:true,validType:'length[0,30]'" />
										</td>
									</tr>
									<tr>    
										<th align="right"><font color="red">*</font>供应商全称：</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].supplierName"  value="${applicationReportAdjustprice.supplierName}" class="easyui-validatebox" style="width:184px;" data-options="required:true,validType:'length[0,33]'" />
										</td>
										<th align="right"><font color="red">*</font>供应商所在地：</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].supplierSite"  value="${applicationReportAdjustprice.supplierSite}" class="easyui-validatebox" style="width:184px;" data-options="required:true,validType:'length[0,100]'" />
										</td>
										<th align="right" ><font color="red">*</font>现有小分类SKU数：</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].smallTypeSku"  value="<#if applicationReportAdjustprice.smallTypeSku?? >${applicationReportAdjustprice.smallTypeSku?c}</#if>" class="easyui-numberbox" style="width:184px;" data-options="required:true,min:0,max:999999999" />
										</td>
									</tr>
									<tr>	
										<th align="right" ><font color="red">*</font>现有明细类SKU数：</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].detailTypeSku"  value="<#if applicationReportAdjustprice.detailTypeSku?? >${applicationReportAdjustprice.detailTypeSku?c}</#if>" class="easyui-numberbox" style="width:184px;" data-options="required:true,min:0,max:999999999" />
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
										<th style="text-align:left;width:150px;">本次调价申请</th>
									</tr>
									<tr>
										<th style="text-align:right"><font color="red">*</font>色泽</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].oldStandardColour"  value="<#if (applicationReportAdjustprice.oldStandardColour)?? >${applicationReportAdjustprice.oldStandardColour}</#if>" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
										</td>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].newStandardColour"  value="<#if applicationReportAdjustprice.newStandardColour?? >${applicationReportAdjustprice.newStandardColour}</#if>" class="easyui-validatebox"  style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
										</td>
									</tr>
									<tr>
										<th style="text-align:right"><font color="red">*</font>滋气味</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].oldStandardSmell"  value="<#if applicationReportAdjustprice.oldStandardSmell?? >${applicationReportAdjustprice.oldStandardSmell}</#if>" class="easyui-validatebox"  style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
										</td>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].newStandardSmell"  value="<#if applicationReportAdjustprice.newStandardSmell?? >${applicationReportAdjustprice.newStandardSmell}</#if>" class="easyui-validatebox"  style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
										</td>
									</tr>
									<tr>
										<th style="text-align:right"><font color="red">*</font>组织形态</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].oldStandardFrom"  value="<#if applicationReportAdjustprice.oldStandardFrom?? >${applicationReportAdjustprice.oldStandardFrom}</#if>" class="easyui-validatebox"  style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
										</td>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].newStandardForm"  value="<#if applicationReportAdjustprice.newStandardForm?? >${applicationReportAdjustprice.newStandardForm}</#if>" class="easyui-validatebox"  style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
										</td>
									</tr>
									<tr>
										<th style="text-align:right"><font color="red">*</font>水份含量</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].oldMoistureContent"  value="${applicationReportAdjustprice.oldMoistureContent}" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
										</td>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].newMoistureContent"  value="${applicationReportAdjustprice.newMoistureContent}" class="easyui-validatebox" style="width:420px;" data-options="required:true,validType:'length[0,500]'" />
										</td>
									</tr>
								</table>
							</fieldset>
							<!-- 3.商品历史价格-->
							<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
								<legend  style="color:#666;padding:0 5px;"><b>商品历史价格</b></legend>
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportAdjustpriceFormFn.appendPriceOld(${applicationReportAdjustprice_index})">增加</a>
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportAdjustpriceFormFn.removePriceOld(${applicationReportAdjustprice_index})">移除</a>
								<table  id='merchandisePriceOld_Grid${applicationReportAdjustprice_index}'
									class="easyui-datagrid"
									fitColumns="true"
									style="height:250px"  
									fit="true"
									iconCls= "icon-save"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportAdjustprice_listHistoryPriceAdjustprice_2.html?piceType=old&intentionCode=${applicationReportAdjustprice.merchandiseCode}&supplierCode=${applicationReportAdjustprice.supplierCode}&applicationCode=${applicationCode}"
									data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandisePriceOld_toolbar${applicationReportAdjustprice_index}',rownumbers:true,onClickRow: function(index){reportAdjustpriceFormFn.onClickRowPriceOld('${applicationReportAdjustprice_index}',index)}"> 
									<thead>
										<tr>
											<th data-options="field:'stockSite',halign:'left',width:250,editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}}">
												进货地区
											</th>
											<th data-options="field:'purchasePrice',halign:'left',width:100,align:'right',formatter:moneyFormatter,editor:{type:'numberbox',options:{required:true,min:0,max:999999999,precision:2}}">
												采购价格
											</th>
											<th data-options="field:'purchaseUnits',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">
												采购单位
											</th>
											<th data-options="field:'sellRegion',halign:'left',width:250,editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}} ">
												销售地区
											</th>     
											<th data-options="field:'sellPrice',halign:'left',width:100,align:'right',formatter:moneyFormatter,editor:{type:'numberbox',options:{required:true,min:0,max:999999999,precision:2}}">
												销售价格
											</th>
											<th data-options="field:'sellUnits',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">
												销售单位
											</th>
											<th data-options="field:'profitRatePercent',halign:'left',width:100,align:'right',formatter:reportAdjustpriceFormFn.formatterPercent,required:false,precision:2,editable:false,styler:function(value,row,index){
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
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportAdjustpriceFormFn.appendPriceNow(${applicationReportAdjustprice_index})">增加</a>
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportAdjustpriceFormFn.removePriceNow(${applicationReportAdjustprice_index})">移除</a>
								<table  id='merchandisePriceNow_Grid${applicationReportAdjustprice_index}'
									class="easyui-datagrid" 
									fitColumns="true"
									style="height:250px"  
									fit="true"
									iconCls= "icon-save"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportAdjustprice_listHistoryPriceAdjustprice_2.html?piceType=now&intentionCode=${applicationReportAdjustprice.merchandiseCode}&supplierCode=${applicationReportAdjustprice.supplierCode}&applicationCode=${applicationCode}"
									data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandisePriceNow_toolbar${applicationReportAdjustprice_index}',rownumbers:true,onClickRow: function(index){reportAdjustpriceFormFn.onClickRowPriceNow('${applicationReportAdjustprice_index}',index)}"> 
									<thead>
										<tr>
											<th data-options="field:'stockSite',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}}">
												进货地区
											</th>
											<th data-options="field:'purchasePrice',halign:'left',width:250,align:'right',formatter:moneyFormatter,editor:{type:'numberbox',options:{required:true,min:0,max:999999999,precision:2}}">
												采购价格
											</th>
											<th data-options="field:'purchaseUnits',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">
												采购单位
											</th>
											<th data-options="field:'sellRegion',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}} ">
												销售地区
											</th>     
											<th data-options="field:'sellPrice',halign:'left',width:150,align:'right',formatter:moneyFormatter,editor:{type:'numberbox',options:{required:true,min:0,max:999999999,precision:2}}">
												销售价格
											</th>
											<th data-options="field:'sellUnits',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">
												销售单位
											</th>
											<th data-options="field:'profitRatePercent',halign:'left',width:100,align:'right',formatter:reportAdjustpriceFormFn.formatterPercent,required:false,precision:2,editable:false,styler:function(value,row,index){
												return 'background-color:lightgray;';
											}">
												毛利率
											</th>
										</tr>
									</thead>
								</table>
							</fieldset>
							<!-- 5.本次价格比历史价格高-->
							<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
								<legend  style="color:#666;padding:0 5px;"><b>本次价格比历史价格高</b></legend>
								<table  id='merchandisePriceCompare_Grid${applicationReportAdjustprice_index}'
									class="easyui-datagrid" 
									style="height:200px"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportAdjustprice_listComparePriceAdjustprice_2.html?piceType='old'&intentionCode=${applicationReportAdjustprice.merchandiseCode}&supplierCode=${applicationReportAdjustprice.supplierCode}&applicationCode=${applicationCode}"
									data-options="rownumbers:true"> 
									<thead>
										<tr>
											<th data-options="field:'stockSite',halign:'left',width:100">
												进货地区
											</th>
											<th data-options="field:'purchasePrice',halign:'left',width:100,align:'right',formatter:moneyFormatter">
												采购价格
											</th>
											<th data-options="field:'purchaseUnits',halign:'left',width:150">
												采购单位
											</th>
											<th data-options="field:'purchasePricePercent',halign:'left',width:100,align:'right',formatter:reportAdjustpriceFormFn.formatterPercent">
												采购价格百分比
											</th>
											<th data-options="field:'sellRegion',halign:'left',width:100">
												销售地区
											</th>     
											<th data-options="field:'sellPrice',halign:'left',width:100,align:'right',formatter:moneyFormatter">
												销售价格
											</th>
											<th data-options="field:'sellUnits',halign:'left',width:150">
												销售单位
											</th>
											<th data-options="field:'sellPricePercent',halign:'left',width:100,align:'right',formatter:reportAdjustpriceFormFn.formatterPercent">
												销售价格百分比
											</th>
											<th data-options="field:'profitRatePercent',halign:'left',width:90,align:'right',formatter:reportAdjustpriceFormFn.formatterPercent">
												毛利率
											</th>
										</tr>   
									</thead>
								</table>
							</fieldset>
							<!--6.商品原料-->
							<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
								<legend  style="color:#666;padding:0 5px;"><b>商品原料</b></legend>
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportAdjustpriceFormFn.appendMaterial(${applicationReportAdjustprice_index})">增加</a>
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportAdjustpriceFormFn.removeMaterial(${applicationReportAdjustprice_index})">移除</a>
								<table  id='merchandiseMaterial_Grid${applicationReportAdjustprice_index}'
									class="easyui-datagrid" 
									fitColumns="true"
									style="height:250px"  
									iconCls= "icon-save"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportAdjustprice_listMerchandiseMaterialAdjustprice_2.html?intentionCode=${applicationReportAdjustprice.merchandiseCode}&supplierCode=${applicationReportAdjustprice.supplierCode}&applicationCode=${applicationCode}"
									data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandiseMaterial_toolbar${applicationReportAdjustprice_index}',rownumbers:true,onClickRow: function(index){reportAdjustpriceFormFn.onClickRowMaterial('${applicationReportAdjustprice_index}',index)}"> 
									<thead>
										<tr>
											<th data-options="field:'materialName',halign:'left',width:150,editor:{type:'validatebox',options:{required:false,validType:'length[0,33]'}}">
												主原料名称
											</th>
											<th data-options="field:'materialSite',halign:'left',width:150,editor:{type:'validatebox',options:{required:false,validType:'length[0,33]'}}">
												主原料产地
											</th>
											<th data-options="field:'materialDate',halign:'left',width:150,editor:{type:'validatebox',options:{required:false,validType:'length[0,33]'}}">
												主原料收购时间
											</th>
											<th data-options="field:'materialCount',halign:'left',width:100,editor:{type:'validatebox',options:{required:false,validType:'length[0,33]'}}">
												现有主原料库存
											</th>
											<th data-options="field:'sellDate',halign:'left',width:150,editor:{type:'validatebox',options:{required:false,min:0,validType:'length[0,33]'}}">
												预估成品可销售时长
											</th>
											<th data-options="field:'remarks',halign:'left',width:150,editor:{type:'validatebox',options:{required:false,validType:'length[0,300]'}}">
												备注
											</th>
										</tr>
									</thead>
								</table>
								<div style="padding-top: 17px;">	
									<p style="padding:8px;">采购员分析：请对市场原料进行综合分析，包含：主要产地、原料特点、规格差异、原料价格以及我司选用的原料原因等进行具体分析。</p>
									<textarea name="applicationList[${applicationReportAdjustprice_index}].purchaseAnalysis"  class="easyui-validatebox" style="width:100%;height:120px" data-options="required:false,validType:'length[0,1000]'" >${applicationReportAdjustprice.purchaseAnalysis}</textarea>
								</div>
							</fieldset>
							<!--7.商品签量-->
							<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
								<legend  style="color:#666;padding:0 5px;"><b>商品签量</b></legend>
								<table class="tableForm" style="width:94%;">
									<tr>
										<th align="right" style="padding-left: 103px;">签量日期：</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].qlDate"  value="<#if applicationReportAdjustprice.qlDate?? >${applicationReportAdjustprice.qlDate?string('yyyy-MM-dd')}</#if>" class="easyui-datebox" style="width:217px;" data-options="required:false,editable:false" />
										</td>
										<th align="right" style="padding-left: 251px;">签量数量（kg）：</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].atpQuantitySum"  value="<#if applicationReportAdjustprice.atpQuantitySum?? >${applicationReportAdjustprice.atpQuantitySum?c}</#if>" class="easyui-numberbox" style="width:100%;margin-left:-3px" data-options="required:false,min:0,max:999999999,precision:2" />
										</td>
									</tr>
									<tr>	
										<th align="right" >总签量已完成量（kg）：</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].atpQuantitySumAccomplish"  value="<#if applicationReportAdjustprice.atpQuantitySumAccomplish?? >${applicationReportAdjustprice.atpQuantitySumAccomplish?c}</#if>" class="easyui-numberbox" style="width:214px;" data-options="required:false,min:0,max:999999999,precision:2" />
										</td>
										<th align="right" >总签量未完成量（kg）：</th>
										<td>
											<input name="applicationList[${applicationReportAdjustprice_index}].atpQuantitySumUnfinished"  value="<#if applicationReportAdjustprice.atpQuantitySumUnfinished?? >${applicationReportAdjustprice.atpQuantitySumUnfinished?c}</#if>" class="easyui-numberbox" style="width:100%;margin-left:-3px" data-options="required:false,min:0,max:999999999,precision:2" />
										</td>
									</tr>
									<tr>
										<th  align="right" >备注：</th>
										<td colspan="3">
											<textarea name="applicationList[${applicationReportAdjustprice_index}].remarks"  class="easyui-validatebox"  style="width:100%;height:60px" data-options="required:false,validType:'length[0,333]'" >${applicationReportAdjustprice.remarks}</textarea>
										</td>
									</tr>
								</table>
							</fieldset>
							<!--8.同类商品市场零售价-->
							<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
								<legend  style="color:#666;padding:0 5px;"><b>同类商品市场零售价</b></legend>
								<table>	
									<tr>
										<td>
											<label><input type="radio" style="width:20px;" <#if applicationReportAdjustprice.isHaveSameReport?? >checked="checked"<#else><#if applicationReportAdjustprice.reportCode?? ><#else>checked="checked"</#if></#if> id="haveSamePart${applicationReportAdjustprice_index}" name="samePart${applicationReportAdjustprice_index}" value="1" onclick="reportAdjustpriceFormFn.changeSameRaido('${applicationReportAdjustprice_index}','1')"/>有</label>
										</td>
										<td>
											<label><input type="radio" style="width:20px;" <#if applicationReportAdjustprice.isHaveSameReport?? ><#else><#if applicationReportAdjustprice.reportCode?? >checked="checked"</#if></#if> id="notHaveSamePart${applicationReportAdjustprice_index}" name="samePart${applicationReportAdjustprice_index}" value="2" onclick="reportAdjustpriceFormFn.changeSameRaido('${applicationReportAdjustprice_index}','2')" />无</label>
										</td>
									</tr>
								</table>
								<div id="notSame_div${applicationReportAdjustprice_index}" <#if applicationReportAdjustprice.isHaveSameReport?? >style="display:none"<#else><#if applicationReportAdjustprice.reportCode?? ><#else>style="display:none"</#if></#if> >
									暂无同类商品
								</div>
								<div id="same_div${applicationReportAdjustprice_index}" <#if applicationReportAdjustprice.isHaveSameReport?? ><#else><#if applicationReportAdjustprice.reportCode?? >style="display:none"</#if></#if> >
									<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportAdjustpriceFormFn.appendSame(${applicationReportAdjustprice_index})">增加</a>
									<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportAdjustpriceFormFn.removeSame(${applicationReportAdjustprice_index})">移除</a>
									<br/><label><font color="red">注：必须包含主要竞品、以及市场销量前三品牌</font></label>
									<table  id='merchandiseSame_Grid${applicationReportAdjustprice_index}'
										class="easyui-datagrid" 
										fitColumns="true"
										style="height:250px"  
										iconCls= "icon-save"
										pagination = "true"
										pagePosition = 'bottom'
										pageSize = "5"
										pageList = "[ 5, 10, 15, 20 ]"
										url="reportAdjustprice_listSameMerchandiseAdjustprice_2.html?intentionCode=${applicationReportAdjustprice.merchandiseCode}&supplierCode=${applicationReportAdjustprice.supplierCode}&applicationCode=${applicationCode}"
										data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandiseSame_toolbar${applicationReportAdjustprice_index}',rownumbers:true,onClickRow: function(index){reportAdjustpriceFormFn.onClickRowSame('${applicationReportAdjustprice_index}',index)}"> 
										<thead>
											<tr>
												<th data-options="field:'trademark',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
													品牌
												</th>
												<th data-options="field:'merchandiseName',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
													商品名称
												</th>
												<th data-options="field:'sellChannel',width:250,editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
													销售渠道
												</th>
												<th data-options="field:'sellUnits',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
													销售单位
												</th>
												<th data-options="field:'sellPrice',width:150,editor:{type:'validatebox',options:{required:true,validType:['length[0,300]']}}">
													零售价(元/销售单位)
												</th>
												<th data-options="field:'kgPrice',width:150,editor:{type:'validatebox',options:{required:true,validType:['length[0,300]']}}">
													折合公斤价(元/公斤)
												</th>
												<th data-options="field:'remarks',width:150,editor:{type:'validatebox',options:{required:false,validType:'length[0,300]'}}">
								             		备注
								             	</th>
											</tr>
										</thead>
									</table>
								</div>
							</fieldset>
							<!--9.采购员意见-->
							<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
								<legend  style="color:#666;padding:0 5px;"><b>采购员意见</b></legend>
								<textarea name="applicationList[${applicationReportAdjustprice_index}].purchaseOpinion"  class="easyui-validatebox" style="width:100%;height:150px" data-options="required:true,validType:'length[0,1000]'" >${applicationReportAdjustprice.purchaseOpinion}</textarea>
							</fieldset>
						</div>
					</#list>
				</div>	
			</form>
		</div>
	</body>
</#compress>