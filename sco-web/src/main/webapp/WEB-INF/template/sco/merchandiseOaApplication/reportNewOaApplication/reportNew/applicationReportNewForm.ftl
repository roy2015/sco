<#compress>
	<script type="text/javascript" >
			<#include "sco/merchandiseOaApplication/reportNewOaApplication/reportNew/applicationReportNewForm.js" />
	</script>
	<div>
		<form id="reportNew_form" method="post">
			<table>
				<tr>
					<th>
						<a id="saveForm" onclick="reportFormFn.submitReportForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
								保存
						</a>
						<a id="saveForm" onclick="reportFormFn.submitReportCGForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
								保存草稿
							</a>
						<a id="closeForm" onclick="reportFormFn.closeForm();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
								关闭
						</a>
					</th>
				</tr>
			</table>
			<div class="easyui-tabs" id="mes" data-options="fit:true,border:true" style="width:89%;height:2015px;">
				<#list applicationList as applicationReportNew>
					<div title="商品${applicationReportNew_index+1}" >
						<!--1.商品基础信息-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 3px;"><b>商品基础信息</b></legend>
								<table class="tableForm" >
										<tr>
										    <th align="right" ><font color="red">*</font>意向品名称：</th>
											<td>
												<input type="hidden" name="applicationList[${applicationReportNew_index}].intentionCode"  value="${applicationReportNew.intentionCode}" />
												<input name="applicationList[${applicationReportNew_index}].intentionName"  value="${applicationReportNew.intentionName}" class="easyui-validatebox" style="width:120px;" data-options="required:true,validType:'length[0,33]'" />
											</td>
											 <th align="right" ><font color="red">*</font>研发：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].development"  value="${applicationReportNew.development}" class="easyui-combobox" style="width:124px;"  data-options="required:true,valueField:'id',textField:'text',panelHeight:'auto',editable:false,url:'businessComBox_developmentList_5.html'" />
											</td>
											 <th align="right" ><font color="red">*</font>商品定性角色：</th>
											<td>
												<input type="hidden" name="applicationList[${applicationReportNew_index}].dxRoleName" id="${applicationReportNew_index}dxRoleCode" />
												<input name="applicationList[${applicationReportNew_index}].dxRoleCode" id="dxRoleCode${applicationReportNew_index}" value="${applicationReportNew.dxRoleCode}" class="easyui-combobox" style="width:124px;" data-options="required:true,valueField:'id',textField:'text',editable:false,url:'merchandiseRole_listQualitative_5.html'" />
											</td>
										</tr>
										 <tr>	
											  <th align="right" ><font color="red">*</font>商品定量角色：</th>
											<td>
												<input type="hidden" name="applicationList[${applicationReportNew_index}].dlRoleName" id="${applicationReportNew_index}dlRoleCode" />
												<input name="applicationList[${applicationReportNew_index}].dlRoleCode" id="dlRoleCode${applicationReportNew_index}" value="${applicationReportNew.dlRoleCode}" class="easyui-combobox" style="width:124px;" data-options="required:true,valueField:'id',textField:'text',editable:false,url:'merchandiseRole_listQuantify_5.html'" />
											</td>
										   <th  align="right" ><font color="red">*</font>中分类：</th>
											<td >
												<input id="valueCentreTypeCode${applicationReportNew_index}" type="hidden" value="${applicationReportNew.centreTypeCode}" />
												<input type="hidden" name="applicationList[${applicationReportNew_index}].centreTypeName" id="${applicationReportNew_index}centreTypeCodeElse" />
												<input name="applicationList[${applicationReportNew_index}].centreTypeCode" id="centreTypeCodeElse${applicationReportNew_index}"  class="easyui-combobox" style="width:124px;"  data-options="required:true,valueField:'id',textField:'text',editable:false"/>
										   	</td>
											<th  align="right" ><font color="red">*</font>小分类：</th>
											<td>
												<input id="valueSmallTypeCode${applicationReportNew_index}" type="hidden" value="${applicationReportNew.smallTypeCode}" />
												<input type="hidden" name="applicationList[${applicationReportNew_index}].smallTypeName" id="${applicationReportNew_index}smallTypeCode" />
												<input name="applicationList[${applicationReportNew_index}].smallTypeCode" id="smallTypeCode${applicationReportNew_index}"  class="easyui-combobox" style="width:124px;"  data-options="required:true,valueField:'id',textField:'text',editable:false,
													onSelect:function(rec){		
											   			if(rec.text!='其他'){
											   				$('#elseTypeName${applicationReportNew_index}').validatebox({
											   					required:false
											   				});
											   				document.getElementById('elseTypeNameTd${applicationReportNew_index}').style.display='none'; 
											   				$('#detailTypeCode${applicationReportNew_index}').combobox({
											   					required:true
											   				});
											   				$('#fineTypeCode${applicationReportNew_index}').combobox({
											   					required:false
											   				});
											   			
											   				$('#detailTypeCode${applicationReportNew_index}').combobox('enable');
											   				$('#fineTypeCode${applicationReportNew_index}').combobox('enable'); 
											   			}else{
											   				document.getElementById('elseTypeNameTd${applicationReportNew_index}').style.display='block'; 
											   				$('#elseTypeName${applicationReportNew_index}').validatebox({
											   					required:true
											   				});
											   				$('#detailTypeCode${applicationReportNew_index}').combobox({
											   					required:false
											   				});
											   				$('#fineTypeCode${applicationReportNew_index}').combobox({
											   					required:false
											   				});
											   				$('#detailTypeCode${applicationReportNew_index}').combobox('setValue', '');
											   				$('#fineTypeCode${applicationReportNew_index}').combobox('setValue', '');
											   				
											   				$('#detailTypeCode${applicationReportNew_index}').combobox('disable');
											   				$('#fineTypeCode${applicationReportNew_index}').combobox('disable');
											   			}
											   		}	
												"/>
											</td>
											<td>
												<div id="elseTypeNameTd${applicationReportNew_index}"  style="display:none">
													<label>自定义小分类名称：</label>
													<input name="applicationList[${applicationReportNew_index}].elseTypeName" id="elseTypeName${applicationReportNew_index}" value="${applicationReportNew.elseTypeName}" class="easyui-validatebox" style="width:120px;" data-options="required:true"/>
												</div>
											</td>
										</tr>
										<tr>  
										    <th align="right" ><font color="red">*</font>明细类：</th>
											<td>
												<input id="valueDetailTypeCode${applicationReportNew_index}" type="hidden" value="${applicationReportNew.detailTypeCode}" />
												<input type="hidden" name="applicationList[${applicationReportNew_index}].detailTypeName" id="${applicationReportNew_index}detailTypeCode" />
												<input name="applicationList[${applicationReportNew_index}].detailTypeCode" id="detailTypeCode${applicationReportNew_index}"  class="easyui-combobox" style="width:124px;"  data-options="required:true,valueField:'id',textField:'text',editable:false"/>
											</td>
										    <th  align="right" >细分类：</th>
											<td >
												<input id="valueFineTypeCode${applicationReportNew_index}" type="hidden" value="${applicationReportNew.fineTypeCode}" />
												<input type="hidden" name="applicationList[${applicationReportNew_index}].fineTypeName" id="${applicationReportNew_index}fineTypeCode" />
												<input name="applicationList[${applicationReportNew_index}].fineTypeCode" id="fineTypeCode${applicationReportNew_index}"   class="easyui-combobox" style="width:124px;"  data-options="required:false,valueField:'id',textField:'text',editable:false"/>
										   	</td>
										    <th align="right"><font color="red">*</font>供应商编号：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].supplierCode" readonly="readonly" value="${applicationReportNew.supplierCode}" class="easyui-validatebox" style="width:120px;background:lightgray;" data-options="required:true,validType:'length[0,30]'" />
											</td>
										 </tr>
										 <tr>  
										    <th align="right"><font color="red">*</font>供应商全称：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].supplierName"  value="${applicationReportNew.supplierName}" class="easyui-validatebox" style="width:120px;" data-options="required:true,validType:'length[0,33]'" />
											</td>
										    <th align="right" ><font color="red">*</font>供应商属性：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].supplierAttribute"  value="${applicationReportNew.supplierAttribute}" class="easyui-validatebox" readonly="readonly"  style="width:120px;background:lightgray;" data-options="required:true,validType:'length[0,10]'" />
											</td>
										    <th align="right"><font color="red">*</font>供应商所在地：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].supplierSite"  value="${applicationReportNew.supplierSite}" class="easyui-validatebox" style="width:120px;" data-options="required:true,validType:'length[0,100]'" />
											</td>
										</tr>
										<tr>	
										    <th align="right" ><font color="red">*</font>品控访厂结果：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].visitFactory"  value="${applicationReportNew.visitFactory}" class="easyui-validatebox" style="width:120px;" data-options="required:true,validType:'length[0,333]'" />
											</td>
										    <th align="right" ><font color="red">*</font>引进后小分类SKU数：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].smallTypeSku"  value="<#if applicationReportNew.smallTypeSku?? >${applicationReportNew.smallTypeSku?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,min:0,max:999999999" />
											</td>
										    <th align="right" ><font color="red">*</font>引进后明细类SKU数：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].detailTypeSku"  value="<#if applicationReportNew.detailTypeSku?? >${applicationReportNew.detailTypeSku?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,min:0,max:999999999" />
											</td>
										</tr>
								</table>
						</fieldset>
						<!--2.商品试吃反馈-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>商品试吃反馈</b></legend>
							<table class="tableForm" >
									<tr>
									    <th align="right" style="width:146px"><font color="red">*</font>试吃日期：</th>
										<td>
											<input name="applicationList[${applicationReportNew_index}].foretasteDate" value="${(applicationReportNew.foretasteDate?string("yyyy-MM-dd"))!}"  class="easyui-datebox" style="width:124px;" data-options="required:true,editable:false" />
										</td>
										 <th align="right" style="width:146px"><font color="red">*</font>评分：</th>
										<td>
											<input name="applicationList[${applicationReportNew_index}].foretasteGrade"  value="<#if applicationReportNew.foretasteGrade?? >${applicationReportNew.foretasteGrade?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,precision:2,min:0,max:999999999" />
										</td>
										<th align="right" style="width:146px"><font color="red">*</font>试吃意见：</th>
										<td>
											<textarea name="applicationList[${applicationReportNew_index}].foretasteEvaluate" class="easyui-validatebox" style="width:402px;height:50px" data-options="required:true,validType:'length[0,333]'" >${applicationReportNew.foretasteEvaluate}</textarea>
										</td>
									</tr>
							</table>
						</fieldset>
						<!-- 商品主要检验标准-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>商品主要检验标准</b></legend>
							<table class="tableForm">
								<tr>
									<th align="left" ><span>色泽</span></th>
									<td>
										<input name="applicationList[${applicationReportNew_index}].colour"  value="${applicationReportNew.colour}" class="easyui-validatebox" style="width:520px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
								</tr>
								<tr>
									<th align="left" ><span >滋气味</span></th>
									<td>
										<input name="applicationList[${applicationReportNew_index}].smell"  value="${applicationReportNew.smell}" class="easyui-validatebox" style="width:520px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
								</tr>
								<tr>
									<th align="left" ><span >组织形态</span></th>
									<td>
										<input name="applicationList[${applicationReportNew_index}].form"  value="${applicationReportNew.form}" class="easyui-validatebox" style="width:520px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
								</tr>
								<tr>
									<th align="left"><span >水份含量</span></th>
									<td>
										<input name="applicationList[${applicationReportNew_index}].moistureContent"  value="${applicationReportNew.moistureContent}" class="easyui-validatebox" style="width:520px;" data-options="required:true,validType:'length[0,500]'" />
									</td>
								</tr>
							</table>
						</fieldset>
						
						<!-- 3.商品价格-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>商品价格</b></legend>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportFormFn.appendPrice(${applicationReportNew_index})">增加</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportFormFn.removePrice(${applicationReportNew_index})">移除</a>
							<table  id='merchandisePrice_Grid${applicationReportNew_index}'
									class="easyui-datagrid" 
									style="height:250px"  
									fitColumns="true" 
									iconCls= "icon-save"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportNew_listMerchandisePriceNew_2.html?intentionCode=${applicationReportNew.intentionCode}&supplierCode=${applicationReportNew.supplierCode}&applicationCode=${applicationCode}"
									data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandisePrice_toolbar${applicationReportNew_index}',rownumbers:true,onClickRow: function(index){reportFormFn.onClickRowPrice('${applicationReportNew_index}',index)}"> 
									 <thead>
										 	<tr>
											 	<th data-options="field:'stockSite',width:130,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}}">
											         	进货地区
											  	</th>
												<th data-options="field:'purchasePrice',width:100,halign:'left',align:'right',formatter:moneyFormatter,editor:{type:'numberbox',options:{required:true,min:0,max:999999999,precision:2}}">
											         	采购价格
											    </th>
												<th data-options="field:'purchaseUnits',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">
											         	采购单位
											    </th>
											    <th data-options="field:'sellRegion',width:170,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}} ">
											         	销售地区
											    </th>     
												<th data-options="field:'sellPrice',width:100,halign:'left',align:'right',formatter:moneyFormatter,editor:{type:'numberbox',options:{required:true,min:0,max:999999999,precision:2}}">
											         	销售价格
											    </th>
												<th data-options="field:'sellUnits',width:150,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">
											         	销售单位
											    </th>
												<th data-options="field:'profitRatePercent',halign:'left',width:100,align:'right',formatter:reportFormFn.formatterPercent,precision:2,editable:false,styler:function(value,row,index){
													return 'background-color:lightgray;';
												}">
											       	毛利率
											    </th>
										     </tr>
										</thead>
								</table>
						</fieldset>
						
						<!--商品原料-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>商品原料</b></legend>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportFormFn.appendMaterial(${applicationReportNew_index})">增加</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportFormFn.removeMaterial(${applicationReportNew_index})">移除</a>
							<table  id='merchandiseMaterial_Grid${applicationReportNew_index}'
									class="easyui-datagrid" 
									fitColumns="true"
									style="height:250px"  
									iconCls= "icon-save"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]"
									url="reportNew_listMerchandiseMaterialPriceNew_2.html?intentionCode=${applicationReportNew.intentionCode}&supplierCode=${applicationReportNew.supplierCode}&applicationCode=${applicationCode}"
									data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers:true,onClickRow: function(index){reportFormFn.onClickRowMaterial('${applicationReportNew_index}',index)}"> 
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
								<textarea name="applicationList[${applicationReportNew_index}].purchaseAnalysis"  class="easyui-validatebox" style="width:100%;height:120px" data-options="required:false,validType:'length[0,1000]'" >${applicationReportNew.purchaseAnalysis}</textarea>
							</div>
						</fieldset>
						
						<!--4.同类商品市场零售价-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>同类商品市场零售价</b></legend>
							<table>	
								<tr>
									<td>
										<label><input type="radio" style="width:20px;" <#if applicationReportNew.isHaveSameReport?? >checked="checked"<#else><#if applicationReportNew.reportCode?? ><#else>checked="checked"</#if></#if> id="haveSamePart${applicationReportNew_index}" name="samePart${applicationReportNew_index}" value="1" onChange="reportFormFn.changeSameRaido('${applicationReportNew_index}','1')"/>有</label>
									</td>
									<td>
										<label><input type="radio" style="width:20px;" <#if applicationReportNew.isHaveSameReport?? ><#else><#if applicationReportNew.reportCode?? >checked="checked"</#if></#if> id="notHaveSamePart${applicationReportNew_index}" name="samePart${applicationReportNew_index}" value="2" onChange="reportFormFn.changeSameRaido('${applicationReportNew_index}','2')" />无</label>
									</td>
							   </tr>
							</table>
							<div id="notSame_div${applicationReportNew_index}" <#if applicationReportNew.isHaveSameReport?? >style="display:none"<#else><#if applicationReportNew.reportCode?? ><#else>style="display:none"</#if></#if> >暂无同类商品</div>
							<div id="same_div${applicationReportNew_index}" width="100%" <#if applicationReportNew.isHaveSameReport?? ><#else><#if applicationReportNew.reportCode?? >style="display:none"</#if></#if> >
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportFormFn.appendSame(${applicationReportNew_index})">增加</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportFormFn.removeSame(${applicationReportNew_index})">移除</a>
							<br/><label><font color="red">注：必须包含主要竞品、以及市场销量前三品牌</font></label>
							<table  id='merchandiseSame_Grid${applicationReportNew_index}'
									class="easyui-datagrid" 
									style="height:220px"
									fitColumns="true" 
									iconCls= "icon-save"
									pagination = "true"
									pagePosition = 'bottom'
									pageSize = "5"
									pageList = "[ 5, 10, 15, 20 ]" 
									url="reportNew_listSameMerchandiseNew_2.html?intentionCode=${applicationReportNew.intentionCode}&supplierCode=${applicationReportNew.supplierCode}&applicationCode=${applicationCode}"
									data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandiseSame_toolbar${applicationReportNew_index}',rownumbers:true,onClickRow: function(index){reportFormFn.onClickRowSame('${applicationReportNew_index}',index)}"> 
									 <thead>
									     	<tr>
												<th data-options="field:'trademark',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
									             	品牌
									            </th>
												<th data-options="field:'merchandiseName',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
									             	商品名称
									            </th>
												<th data-options="field:'sellChannel',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
									             	销售渠道
									            </th>
												<th data-options="field:'sellUnits',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[0,300]'}}">
									             	销售单位
									            </th>
												<th data-options="field:'sellPrice',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:['length[0,300]', 'validatePrice']}}">
									             	零售价(元/销售单位)
									            </th>
												<th data-options="field:'kgPrice',halign:'left',width:150,editor:{type:'validatebox',options:{required:true,validType:['length[0,300]', 'validatePrice']}}">
									             	折合公斤价(元/公斤)
									            </th>
									            <th data-options="field:'remarks',halign:'left',width:150,editor:{type:'validatebox',options:{required:false,validType:'length[0,300]'}}">
								             		备注
								             	</th>
									         </tr>
										</thead>
								</table>
								</div>
						</fieldset>
						
						<!--5.商品销售预计-->
						<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>商品销售预计</b></legend>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="reportFormFn.appendSell(${applicationReportNew_index})">增加</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="reportFormFn.removeSell(${applicationReportNew_index})">移除</a>
								<table class="tableForm" >
										<tr>
											<th align="right"><font color="red">*</font>预计销售总门店数：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].anticipatedSellStoreQuantit"  value="<#if applicationReportNew.anticipatedSellStoreQuantit?? >${applicationReportNew.anticipatedSellStoreQuantit?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,min:0,max:999999999" />
											</td>
											<th align="right"><font color="red">*</font>预计销售量(最小单位/单店单天)：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].anticipatedSellQuantity"  value="<#if applicationReportNew.anticipatedSellQuantity?? >${applicationReportNew.anticipatedSellQuantity?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,min:0,max:999999999,precision:2" />
											</td>
											<th align="right"><font color="red">*</font>预计毛利额(最小单位/单店单天)：</th>
											<td>
												<input name="applicationList[${applicationReportNew_index}].anticipatedSellProfit"  value="<#if applicationReportNew.anticipatedSellProfit?? >${applicationReportNew.anticipatedSellProfit?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,min:0,max:999999999,precision:2" />
											</td>
										</tr>
								</table>
								<table id='merchandiseSell_Grid${applicationReportNew_index}' 
										class="easyui-datagrid" 
										style="height:220px"  
										iconCls= "icon-save"
										pagination = "true"
										pagePosition = 'bottom'
										pageSize = "5"
										pageList = "[ 5, 10, 15, 20 ]"
										url="reportNew_listSellAnticipatedNew_2.html?intentionCode=${applicationReportNew.intentionCode}&supplierCode=${applicationReportNew.supplierCode}&applicationCode=${applicationCode}"
										data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#merchandiseSell_toolbar${applicationReportNew_index}',rownumbers:true,onClickRow: function(index){reportFormFn.onClickRowSell('${applicationReportNew_index}',index)}">
										 <thead>
									     	<tr>
												<th data-options="field:'sellRegion',width:300,halign:'left',editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}} ">销售区域</th>
												<th data-options="field:'sellStoreCount',halign:'left',align:'right',width:300,editor:{type:'numberbox',options:{required:true,min:0,max:999999999}}">预计销售门店数</th>
									         </tr>
									    </thead> 
								</table>
						</fieldset>
						<!--6.采购员意见-->
					    <fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
							<legend  style="color:#666;padding:0 5px;"><b>采购员意见</b></legend>
							<textarea name="applicationList[${applicationReportNew_index}].purchaseOpinion"  class="easyui-validatebox" style="width:100%;height:150px;" data-options="required:true,validType:'length[0,1000]'" >${applicationReportNew.purchaseOpinion}</textarea>
						</fieldset>
			  		</div>
				</#list>
			</div>	
		</form>
	</div>
</#compress>