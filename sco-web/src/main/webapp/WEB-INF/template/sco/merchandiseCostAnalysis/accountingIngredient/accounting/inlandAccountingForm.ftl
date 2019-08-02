<#compress>
<head>
<style>

th, td {;
	height: 25px;
	padding-left: 3px;
	font-size: 12px;
	font-family: "微软雅黑";
	font-weight: normal;
}

table {
	border-color: #ccc;
	border-collapse:collapse;
	white-space:nowrap;
}
</style>
</head>

<body>
	<table>
		<tr>
			<td align="right">报价币种:</td>
			<td style="width: 100px;">
				<input id="accounting_quoted_currency" name="accounting.quotedCurrency" value="${accounting.quotedCurrency}" class="easyui-validatebox" style="width: 96px;" data-options="required:true" validtype="length[0,13]">
			</td>
			<td align="right" style="width: 80px;">报价日期:</td>
			<td style="width: 150px;" >
				<input id="accounting_quoted_date" name="accounting.quotedDate" value="<#if accounting.quotedDate??>${accounting.quotedDate?string('yyyy-MM-dd HH:mm:ss')}</#if>" class="easyui-datetimebox" ifRemoveReset="true" style="width: 150px;" data-options="required:true,editable:false" />
			</td>
			<td align="right" style="width: 100px;">报价计量单位:</td>
			<td>
				<input id="accounting_quantity" name="accounting.quantity" value="<#if accounting.quantity??>${accounting.quantity?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="required:true,precision:3,min:0,max:9999999999,onChange:addAccountingFn.unitsOnChange"/>
				<select id="accounting_units" name="accounting.units" class="easyui-combobox" data-options="required:true,panelHeight:'auto',editable:false,value:'${accounting.units}',onSelect:function(){addAccountingFn.unitsOnChange();}">
					<option value="KG">KG</option>
					<option value="L">L</option>
				</select> (非公斤装商品请填写净含量)
			</td>
		</tr>
		<tr>
			<td colspan="6" style="padding-left: 0px;">
				<table id="region_table">
					<#if accountingRegionList?? && (accountingRegionList?size>0)>
						<#list accountingRegionList as accountingRegion>
							<tr id="region_table_tr_${accountingRegion_index}">
								<td align="right">进货地区:</td>
								<td>
									<select id="region_${accountingRegion_index}" name="accountingRegionList[${accountingRegion_index}].region" class="easyui-combobox"  style="width: 100px" 
											data-options="editable:false,required:true,multiple:true,
												valueField:'id',
												textField:'text',
											    url:'masterDataType_listWarehouseOption_5.html',
											    onSelect:function(){addAccountingFn.regionOnSelect(this);},
											    onUnselect:function(){addAccountingFn.regionOnSelect(this);},
											    onLoadSuccess:function(){
											    	$(this).combobox('setValues', '${accountingRegion.region}'.replace(/\s+/g,'').split(','));
											    	addAccountingFn.regionOnSelect(this);
											    }
											    ">
									</select>
									
								</td>
								<td>
									<#if accountingRegion_index = 0>
										<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addAccountingFn.addRegion()" />
									<#else>
										<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="addAccountingFn.delRegion(${accountingRegion_index})" />
									</#if>
								</td>
							</tr>
						</#list>
					<#else>
						<tr id="region_table_tr_0">
							<td align="right">进货地区:</td>
							<td>
								<select id="region_0" name="accountingRegionList[0].region" class="easyui-combobox"  style="width: 100px" 
										data-options="editable:false,required:true,multiple:true,
											valueField:'id',
											textField:'text',
										    url:'masterDataType_listWarehouseOption_5.html',
										    onSelect:function(){addAccountingFn.regionOnSelect(this);},
										    onUnselect:function(){addAccountingFn.regionOnSelect(this);}
										    ">
								</select>
							</td>
							<td>
								<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addAccountingFn.addRegion()" />
							</td>
						</tr>
					</#if>
				</table>
			</td>
		</tr>
	</table>
	<p>
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<th style="width: 210px;">成本项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<th style="width: 100px;">报价计量单位&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
			<th style="padding-left: 0px;">
				<table id="accounting_region_table" border="1" cellspacing="0" cellpadding="0" frame=void>
					<tr>
						<#if accountingRegionList?? && (accountingRegionList?size>0)>
							<#list accountingRegionList as accountingRegion>
								<td id="accounting_region_table_td_${accountingRegion_index}" style="width: 110px;"></td>
							</#list>
						<#else>
							<td id="accounting_region_table_td_0" style="width: 110px;"></td>
						</#if>
					</tr>
				</table>
			</th>
			<th style="width: 442px;">备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<th style="width: 31px">增加&nbsp;&nbsp;</th>
		</tr>

		<tr>
			<td id="accounting_ingredient_td" colspan="5" style="padding-left: 0px;">
				<table id="accounting_ingredient_table" style="width: 100%" border="1" cellspacing="0" cellpadding="0" frame=void>
				
				</table>
			</td>
		</tr>
		<tr>
			<td>投料表:得率(%)</td>
			<td>/</td>
			<td>
				<input id="accounting_ingredient_yield_region_input" name="accountingCostItem.yieldValue" value="<#if accountingCostItem?? && accountingCostItem.yieldValue??>${accountingCostItem.yieldValue}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
			</td>
			<td>
				<textarea id="accounting_ingredient_yield_remarks_input" name="accountingCostItem.yieldRemarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"><#if accountingCostItem?? && accountingCostItem.yieldRemarks??>${accountingCostItem.yieldRemarks}</#if></textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>投料表:主料小计(投入成本)</td>
			<td id="accounting_ingredient_zlsubtotal_units_td"></td>
			<td>
				<input id="accounting_ingredient_zlsubtotal_region_input" name="accountingCostItem.zlsubtotalValue" value="<#if accountingCostItem?? && accountingCostItem.zlsubtotalValue??>${accountingCostItem.zlsubtotalValue?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
			</td>
			<td>
				<textarea id="accounting_ingredient_zlsubtotal_remarks_input" name="accountingCostItem.zlsubtotalRemarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"><#if accountingCostItem?? && accountingCostItem.zlsubtotalRemarks??>${accountingCostItem.zlsubtotalRemarks}</#if></textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>投料表:辅料小计(投入成本)</td>
			<td id="accounting_ingredient_flsubtotal_units_td"></td>
			<td>
				<input id="accounting_ingredient_flsubtotal_region_input" name="accountingCostItem.flsubtotalValue" value="<#if accountingCostItem?? && accountingCostItem.flsubtotalValue??>${accountingCostItem.flsubtotalValue?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
			</td>
			<td>
				<textarea id="accounting_ingredient_flsubtotal_remarks_input" name="accountingCostItem.flsubtotalRemarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"><#if accountingCostItem?? && accountingCostItem.flsubtotalRemarks??>${accountingCostItem.flsubtotalRemarks}</#if></textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>投料表:商品总投入成本</td>
			<td id="accounting_ingredient_totalcost_units_td"></td>
			<td>
				<input id="accounting_ingredient_totalcost_region_input" name="accountingCostItem.itotalcostValue" value="<#if accountingCostItem?? && accountingCostItem.itotalcostValue??>${accountingCostItem.itotalcostValue?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
			</td>
			<td>
				<textarea id="accounting_ingredient_totalcost_remarks_input" name="accountingCostItem.itotalcostRemarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"><#if accountingCostItem?? && accountingCostItem.itotalcostRemarks??>${accountingCostItem.itotalcostRemarks}</#if></textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>原材料总成本</td>
			<td id="accounting_material_totalcost_units_td"></td>
			<td>
				<input id="accounting_material_totalcost_region_input" name="accountingCostItem.mtotalcostValue" value="<#if accountingCostItem?? && accountingCostItem.mtotalcostValue??>${accountingCostItem.mtotalcostValue?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:2,min:0,max:9999999999,onChange:addAccountingFn.deductPackagTotalCostValue" />
			</td>
			<td>
				<textarea id="accounting_material_totalcost_remarks_input" name="accountingCostItem.mtotalcostRemarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"><#if accountingCostItem?? && accountingCostItem.mtotalcostRemarks??>${accountingCostItem.mtotalcostRemarks}</#if></textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>包装占比(%)</td>
			<td>/</td>
			<td>
				<input id="accounting_packagproportion_region_input" name="accountingCostItem.packagproportionValue" value="<#if accountingCostItem?? && accountingCostItem.packagproportionValue??>${accountingCostItem.packagproportionValue?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:2,min:0,max:9999999999,onChange:addAccountingFn.deductPackagTotalCostValue" />
			</td>
			<td>
				<textarea id="accounting_packagproportion_remarks_input" name="accountingCostItem.packagproportionRemarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"><#if accountingCostItem?? && accountingCostItem.packagproportionRemarks??>${accountingCostItem.packagproportionRemarks}</#if></textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>扣除包装后原材料总成本</td>
			<td id="accounting_deductptcost_units_td"></td>
			<td>
				<input id="accounting_deductptcost_region_input" name="accountingCostItem.deductptcostValue" value="<#if accountingCostItem?? && accountingCostItem.deductptcostValue??>${accountingCostItem.deductptcostValue?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
			</td>
			<td>
				<textarea id="accounting_deductptcost_remarks_input" name="accountingCostItem.deductptcostRemarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"><#if accountingCostItem?? && accountingCostItem.deductptcostRemarks??>${accountingCostItem.deductptcostRemarks}</#if></textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="5" style="padding-left: 0px;">
				<table id="accounting_npackagtype_table" style="width: 100%; white-space: normal;" border="1" cellspacing="0" cellpadding="0" frame=void>
					<#if accountingNPackagList?? && (accountingNPackagList?size>0)>
						<#list accountingNPackagList as accountingNPackag>
							<tr id="accounting_npackagtype_table_tr_${accountingNPackag_index}">
								<td style="width: 210px;">
									内包装名称: 
									<select class="easyui-combobox" id="accounting_npackagtype_combox_${accountingNPackag_index}" name="accountingNPackagList[${accountingNPackag_index}].npackagType" style="width: 104px;" 
											data-options="
												valueField:'id',
												textField:'text',
												panelHeight:'auto',
												value:'${accountingNPackag.npackagType}',
												editable:false,
											    url:'businessComBox_MerchandiseNPackag_5.html',
											    onSelect:function(){addAccountingFn.packagTypeOnSelect(this);},
											    onLoadSuccess:function(){
											    	var packag = new Object();
											    	packag.index = '${accountingNPackag_index}';
											    	packag.npackagType = '${accountingNPackag.npackagType}';
											    	packag.elseName = '${accountingNPackag.elseName}';
											    	packag.price = '<#if accountingNPackag.price??>${accountingNPackag.price?c}</#if>';
											    	packag.texture = '${accountingNPackag.texture}';
											    	packag.kgPrice = '<#if accountingNPackag.kgPrice??>${accountingNPackag.kgPrice?c}</#if>';
											    	packag.weightProportion = '<#if accountingNPackag.weightProportion??>${accountingNPackag.weightProportion?c}</#if>';
											    	packag.materialSize = '${accountingNPackag.materialSize}';
											    	packag.weight = '<#if accountingNPackag.weight??>${accountingNPackag.weight?c}</#if>';
											    	packag.unitsPrice = '<#if accountingNPackag.unitsPrice??>${accountingNPackag.unitsPrice?c}</#if>';
											    	packag.quantity = '<#if accountingNPackag.quantity??>${accountingNPackag.quantity?c}</#if>';
											    	packag.technologyRequirements = '${accountingNPackag.technologyRequirements}';
											    	addAccountingFn.nPackagTypeOnLoadSuccess(packag);
											    }
											    " />
									<textarea id="accounting_npackagtype_textarea_${accountingNPackag_index}" class="easyui-validatebox" style="width: 315px; height: 50px;" hidden="true">${accountingNPackag.remarks}</textarea>
									<br>
									<input id="accounting_npackagtype_elsename_input_${accountingNPackag_index}" name="accountingNPackagList[${accountingNPackag_index}].elseName" value="${accountingNPackag.elseName}" class="easyui-validatebox" style="width: 100px; <#if accountingNPackag.npackagType=='ELSE'>display:inline;<#else>display:none;" disabled</#if> data-options="required:true" validtype="length[0,10]" />
								</td>
								<td id="accounting_npackagtype_units_table_td_${accountingNPackag_index}" style="width: 100px;"></td>
								<td id="accounting_npackagtype_region_table_td_${accountingNPackag_index}"></td>
								<td id="accounting_npackagtype_remarks_table_td_${accountingNPackag_index}" style="width: 445px; padding-left: 0px;"></td>
								<td style="width: 31px;">
									<#if accountingNPackag_index = 0>
										<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addAccountingFn.addNPackagType()" />
									<#else>
										<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="addAccountingFn.delNPackagType(${accountingNPackag_index})" />
									</#if>
								</td>
							</tr>
						</#list>
					<#else>
						<tr id="accounting_npackagtype_table_tr_0">
							<td style="width: 210px;">
								内包装名称:
								<select class="easyui-combobox" id="accounting_npackagtype_combox_0" name="accountingNPackagList[0].npackagType" style="width: 104px;" 
										data-options="
											valueField:'id',
											textField:'text',
											value:'W',
											panelHeight:'auto',
											editable:false,
										    url:'businessComBox_MerchandiseNPackag_5.html',
										    onSelect:function(){addAccountingFn.packagTypeOnSelect(this);}
										    " />
								<br>
								<input id="accounting_npackagtype_elsename_input_0" class="easyui-validatebox" style="width: 100px; display:none;" disabled data-options="required:true" validtype="length[0,10]" />
							</td>
							<td id="accounting_npackagtype_units_table_td_0" style="width: 100px;"></td>
							<td id="accounting_npackagtype_region_table_td_0"></td>
							<td id="accounting_npackagtype_remarks_table_td_0" style="width: 445px; padding-left: 0px;"></td>
							<td style="width: 31px;">
								<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addAccountingFn.addNPackagType()" />
							</td>
						</tr>
					</#if>
				</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="5" style="padding-left: 0px;">
				<table id="accounting_wpackagtype_table" style="width: 100%; white-space: normal;" border="1" cellspacing="0" cellpadding="0" frame=void>
					<#if accountingWPackagList?? && (accountingWPackagList?size>0)>
						<#list accountingWPackagList as accountingWPackag>
							<tr id="accounting_wpackagtype_table_tr_${accountingWPackag_index}">
								<td style="width: 210px;">
									外包装名称:
									<select id="accounting_wpackagtype_combox_${accountingWPackag_index}" name="accountingWPackagList[${accountingWPackag_index}].wpackagType" class="easyui-combobox" style="width: 104px;" 
											data-options="
												valueField:'id',
												textField:'text',
												value:'${accountingWPackag.wpackagType}',
												panelHeight:'auto',
												editable:false,
											    url:'businessComBox_MerchandiseWPackag_5.html',
											    onSelect:function(){addAccountingFn.packagTypeOnSelect(this);},
											    onLoadSuccess:function(){
											    	var packag = new Object();
											    	packag.index = '${accountingWPackag_index}';
											    	packag.wpackagType = '${accountingWPackag.wpackagType}';
											    	packag.elseName = '${accountingWPackag.elseName}';
											    	packag.price = '<#if accountingWPackag.price??>${accountingWPackag.price?c}</#if>';
											    	packag.unitsPrice = '<#if accountingWPackag.unitsPrice??>${accountingWPackag.unitsPrice?c}</#if>';
											    	packag.useQuantity = '<#if accountingWPackag.useQuantity??>${accountingWPackag.useQuantity?c}</#if>';
											    	packag.texture = '${accountingWPackag.texture}';
											    	packag.length = '<#if accountingWPackag.length??>${accountingWPackag.length?c}</#if>';
											    	packag.width = '<#if accountingWPackag.width??>${accountingWPackag.width?c}</#if>';
											    	packag.height = '<#if accountingWPackag.height??>${accountingWPackag.height?c}</#if>';
											    	packag.quantity = '<#if accountingWPackag.area??>${accountingWPackag.area?c}</#if>';
											    	packag.ylUnitsPrice = '<#if accountingWPackag.ylUnitsPrice??>${accountingWPackag.ylUnitsPrice?c}</#if>';
											    	packag.specification = '${accountingWPackag.specification}';
											    	addAccountingFn.wPackagTypeOnLoadSuccess(packag);
											    }
											    " />
									<textarea id="accounting_wpackagtype_textarea_${accountingWPackag_index}" class="easyui-validatebox" style="width: 315px; height: 50px;" hidden="true">${accountingWPackag.remarks}</textarea>
									<br>
									<input id="accounting_wpackagtype_elsename_input_${accountingWPackag_index}" name="accountingWPackagList[${accountingWPackag_index}].elseName" value="${accountingWPackag.elseName}" class="easyui-validatebox" style="width: 100px; <#if accountingWPackag.wpackagType=='ELSE'>display:inline;<#else>display:none;" disabled</#if> data-options="required:true" validtype="length[0,10]" />
								</td>
								<td id="accounting_wpackagtype_units_table_td_${accountingWPackag_index}" style="width: 100px;"></td>
								<td id="accounting_wpackagtype_region_table_td_${accountingWPackag_index}"></td>
								<td id="accounting_wpackagtype_remarks_table_td_${accountingWPackag_index}" style="width: 445px; padding-left: 0px;"></td>
								<td style="width: 31px;">
									<#if accountingWPackag_index = 0>
										<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addAccountingFn.addWPackagType()" />
									<#else>
										<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="addAccountingFn.delWPackagType(${accountingWPackag_index})" />
									</#if>
								</td>
							</tr>
						</#list>
					<#else>
						<tr id="accounting_wpackagtype_table_tr_0">
							<td style="width: 210px;">
								外包装名称:
								<select id="accounting_wpackagtype_combox_0" name="accountingWPackagList[0].wpackagType" class="easyui-combobox" style="width: 104px;" 
										data-options="
											valueField:'id',
											textField:'text',
											value:'W',
											panelHeight:'auto',
											editable:false,
										    url:'businessComBox_MerchandiseWPackag_5.html',
										    onSelect:function(){addAccountingFn.packagTypeOnSelect(this);}
										    " /><br>
								<input id="accounting_wpackagtype_elsename_input_0" class="easyui-validatebox" style="width: 100px; display:none;" disabled="disabled" data-options="required:true" validtype="length[0,10]" />
							</td>
							<td id="accounting_wpackagtype_units_table_td_0" style="width: 100px;"></td>
							<td id="accounting_wpackagtype_region_table_td_0"></td>
							<td id="accounting_wpackagtype_remarks_table_td_0" style="width: 445px; padding-left: 0px;"></td>
							<td style="width: 31px;">
								<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addAccountingFn.addWPackagType()" />
							</td>
						</tr>
					</#if>
				</table>
			</td>
		</tr>
		<tr>
			<td>内外包装材料小计</td>
			<td id="accounting_nwpackagsubtotal_units_td"></td>
			<td>
				<input id="accounting_nwpackagsubtotal_region_input" name="accountingCostItem.nwpackagsubtotalValue" value="<#if accountingCostItem?? && accountingCostItem.nwpackagsubtotalValue??>${accountingCostItem.nwpackagsubtotalValue?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
			</td>
			<td>
				<textarea id="accounting_nwpackagsubtotal_remarks_input" name="accountingCostItem.nwpackagsubtotalRemarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"><#if accountingCostItem?? && accountingCostItem.nwpackagsubtotalRemarks??>${accountingCostItem.nwpackagsubtotalRemarks}</#if></textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="5" style="padding-left: 0px;">
				<table id="accounting_wastagetype_table" style="width: 100%" border="1" cellspacing="0" cellpadding="0" frame=void>
					<#if accountingWastageList?? && (accountingWastageList?size>0)>
						<#list accountingWastageList as accountingWastage>
							<tr id="accounting_wastageType_table_tr_${accountingWastage_index}">
								<td style="width: 210px;">
									损耗类型:
									<input id="accounting_wastagetype_input_${accountingWastage_index}" name="accountingWastageList[${accountingWastage_index}].wastageType" value="${accountingWastage.wastageType}" class="easyui-validatebox" style="width: 100px;" data-options="required:true" validtype="length[0,10]" />
								</td>
								<td id="accounting_wastagetype_units_td_${accountingWastage_index}" style="width: 100px;"></td>
								<td>
									<input id="accounting_wastagetype_region_input_0" name="accountingWastageList[${accountingWastage_index}].price" value="<#if accountingWastage.price??>${accountingWastage.price?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.total" />
								</td>
								<td style="width: 442px;">
									<textarea id="accounting_wastagetype_remarks_input_0" name="accountingWastageList[${accountingWastage_index}].remarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]">${accountingWastage.remarks}</textarea>
								</td>
								<td style="width: 31px;">
									<#if accountingWastage_index = 0>
										<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addAccountingFn.addWastageType()" />
									<#else>
										<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addAccountingFn.delWastageType(${accountingWastage_index})" />
									</#if>
								</td>
							</tr>
						</#list>
					<#else>
						<tr id="accounting_wastageType_table_tr_0" style="width: 100%" border="1" cellspacing="0" cellpadding="0" frame=void>
							<td style="width: 210px;">
								损耗类型:
								<input id="accounting_wastagetype_input_0" name="accountingWastageList[0].wastageType" class="easyui-validatebox" style="width: 100px;" data-options="required:true" validtype="length[0,10]" />
							</td>
							<td id="accounting_wastagetype_units_td_0" style="width: 100px;"></td>
							<td>
								<input id="accounting_wastagetype_region_input_0" name="accountingWastageList[0].price" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.total" />
							</td>
							<td style="width: 442px;">
								<textarea id="accounting_wastagetype_remarks_input_0" name="accountingWastageList[0].remarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"></textarea>
							</td>
							<td style="width: 31px;">
								<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addAccountingFn.addWastageType()" />
							</td>
						</tr>
					</#if>
				</table>
			</td>
		</tr>
		<tr>
			<td>水电煤</td>
			<td id="accounting_wec_units_td"></td>
			<td>
				<input id="accounting_wec_region_input" name="accountingWec.price" value="<#if accountingWec?? && accountingWec.price??>${accountingWec.price?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.total" />
			</td>
			<td style="padding-left: 0px;">
				<table style="width: 100%">
					<tr>
						<th style="width: 100px;">耗水(元/t成品):</th>
						<td>
							<input id="accounting_wec_remarks_water_input" name="accountingWec.water" value="<#if accountingWec?? && accountingWec.water??>${accountingWec.water?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.wecTotalCompute" /> 
						</td>
					</tr>
					<tr>
						<th>耗油(元/t成品):</th>
						<td>
							<input id="accounting_wec_remarks_oil_input" name="accountingWec.oil" value="<#if accountingWec?? && accountingWec.oil??>${accountingWec.oil?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.wecTotalCompute" /> 
						</td>
					</tr>
					<tr>
						<th>耗电(元/t成品):</th>
						<td>
							<input id="accounting_wec_remarks_electricity_input" name="accountingWec.electricity" value="<#if accountingWec?? && accountingWec.electricity??>${accountingWec.electricity?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.wecTotalCompute" /> 
						</td>
					</tr>
					<tr>
						<th>耗气(元/t成品):</th>
						<td>
							<input id="accounting_wec_remarks_gas_input" name="accountingWec.gas" value="<#if accountingWec?? && accountingWec.gas??>${accountingWec.gas?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.wecTotalCompute" /> 
						</td>
					</tr>
					<tr>
						<th>耗煤(元/t成品):</th>
						<td>
							<input id="accounting_wec_remarks_coal_input" name="accountingWec.coal" value="<#if accountingWec?? && accountingWec.coal??>${accountingWec.coal?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.wecTotalCompute" /> 
						</td>
					</tr>
					<tr>
						<th>合计(元/kg成品):</th>
						<td>
							<input id="accounting_wec_remarks_total_input" name="accountingWec.total" value="<#if accountingWec?? && accountingWec.total??>${accountingWec.total?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:3,min:0,max:9999999999" readonly="readonly" /> 
						</td>
					</tr>
					<tr>
						<th>备注:</th>
						<td>
							<textarea id="accounting_wec_remarks_input" name="accountingWec.remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]"><#if accountingWec?? && accountingWec.remarks??>${accountingWec.remarks}</#if></textarea>
						</td>
					</tr>
				</table>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>设备折旧及维护</td>
			<td id="accounting_sbzjwh_units_td"></td>
			<td>
				<input id="accounting_sbzjwh_region_input" name="accountingSbzjwh.price" value="<#if accountingSbzjwh?? && accountingSbzjwh.price??>${accountingSbzjwh.price?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.total" />
			</td>
			<td style="padding-left: 0px;">
				<table style="width: 100%">
					<tr>
						<th style="width: 100px;">设备总价(万元):</th>
						<td>
							<input id="accounting_sbzjwh_remarks_totalprice_input" name="accountingSbzjwh.totalPrice" value="<#if accountingSbzjwh?? && accountingSbzjwh.totalPrice??>${accountingSbzjwh.totalPrice?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999" />
						</td>
					</tr>
					<tr>
						<th>折旧年限(年):</th>
						<td>
							<input id="accounting_sbzjwh_remarks_agelimit_input" name="accountingSbzjwh.ageLimit" value="<#if accountingSbzjwh?? && accountingSbzjwh.ageLimit??>${accountingSbzjwh.ageLimit?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999" />
						</td>
					</tr>
					<tr>
						<th>折旧值(万元/年):</th>
						<td>
							<input id="accounting_sbzjwh_remarks_depreciation_input" name="accountingSbzjwh.depreciation" value="<#if accountingSbzjwh?? && accountingSbzjwh.depreciation??>${accountingSbzjwh.depreciation?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.depreciationTotalCompute" />
						</td>
					</tr>
					<tr>
						<th>产能值(t成品/年):</th>
						<td>
							<input id="accounting_sbzjwh_remarks_capacity_input" name="accountingSbzjwh.capacity" value="<#if accountingSbzjwh?? && accountingSbzjwh.capacity??>${accountingSbzjwh.capacity?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.depreciationTotalCompute" />
						</td>
					</tr>
					<tr>
						<th>合计折旧值<br>(元/kg成品):</th>
						<td>
							<input id="accounting_sbzjwh_remarks_depreciationtotal_input" name="accountingSbzjwh.total" value="<#if accountingSbzjwh?? && accountingSbzjwh.total??>${accountingSbzjwh.total?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:3,min:0,max:9999999999" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th>备注:</th>
						<td>
							<textarea id="accounting_sbzjwh_remarks_input" name="accountingSbzjwh.remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]"><#if accountingSbzjwh?? && accountingSbzjwh.remarks??>${accountingSbzjwh.remarks}</#if></textarea>
						</td>
					</tr>
				</table>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>人工</td>
			<td id="accounting_manpower_units_td"></td>
			<td>
				<input id="accounting_manpower_region_input" name="accountingManpower.price" value="<#if accountingManpower?? && accountingManpower.price??>${accountingManpower.price?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.total" />
			</td>
			<td style="padding-left: 0px;">
				<table style="width: 100%">
					<tr>
						<th style="width: 100px;">车间工人数<br>(人次):</th>
						<td>
							<input id="accounting_manpower_remarks_manpowercount_input" name="accountingManpower.manpowerCount" value="<#if accountingManpower?? && accountingManpower.manpowerCount??>${accountingManpower.manpowerCount?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:0,min:0,max:2147483647,onChange:addAccountingFn.unitsWageCompute" />
						</td>
					</tr>
					<tr>
						<th>平均工资<br>(元/人/月):</th>
						<td>
							<input id="accounting_manpower_remarks_avgwage_input" name="accountingManpower.avgWage" value="<#if accountingManpower?? && accountingManpower.avgWage??>${accountingManpower.avgWage?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.unitsWageCompute" />
						</td>
					</tr>
					<tr>
						<th>月产量(t):</th>
						<td>
							<input id="accounting_manpower_remarks_monthcapacity_input" name="accountingManpower.monthCapacity" value="<#if accountingManpower?? && accountingManpower.monthCapacity??>${accountingManpower.monthCapacity?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.unitsWageCompute" />
						</td>
					</tr>
					<tr>
						<th>每kg成品(元/kg):</th>
						<td>
							<input id="accounting_manpower_remarks_unitswage_input" name="accountingManpower.unitsWage" value="<#if accountingManpower?? && accountingManpower.unitsWage??>${accountingManpower.unitsWage?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:3,min:0,max:9999999999" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th>备注:</th>
						<td>
							<textarea id="accounting_manpower_remarks_input" name="accountingManpower.remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]"><#if accountingManpower?? && accountingManpower.remarks??>${accountingManpower.remarks}</#if></textarea>
						</td>
					</tr>
				</table>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>管理</td>
			<td id="accounting_manage_units_td"></td>
			<td>
				<input id="accounting_manage_region_input" name="accountingManage.price" value="<#if accountingManage?? && accountingManage.price??>${accountingManage.price?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.total" />
			</td>
			<td style="padding-left: 0px;">
				<table id="accounting_manage_remarks_region_table">
					<#if accountingManageRegionList?? && (accountingManageRegionList?size>0)>
						<#list accountingManageRegionList as accountingManageRegion>
							<tr id="accounting_manage_remarks_region_table_tr_${accountingManageRegion_index}">
								<th style="width: 100px;">进货地区:</th>
								<td style="width: 100px;">
									<input id="accounting_manage_remarks_region_${accountingManageRegion_index}" name="accountingManageRegionList[${accountingManageRegion_index}].region" value="${accountingManageRegion.region}"  class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />
								</td>
								<th style="width: 100px;">占比(%):</th>
								<td style="width: 100px;">
									<input id="accounting_manage_remarks_proportion_input_${accountingManageRegion_index}" name="accountingManageRegionList[${accountingManageRegion_index}].proportion" value="<#if accountingManageRegion.proportion??>${accountingManageRegion.proportion?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
								</td>
							</tr>
						</#list>
					<#else>
						<tr id="accounting_manage_remarks_region_table_tr_0">
							<th style="width: 100px;">进货地区:</th>
							<td style="width: 100px;">
								<input id="accounting_manage_remarks_region_0" name="accountingManageRegionList[0].region" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />
							</td>
							<th style="width: 100px;"">占比(%):</th>
							<td style="width: 100px;">
								<input id="accounting_manage_remarks_proportion_input_0" name="accountingManageRegionList[0].proportion" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
							</td>
						</tr>
					</#if>
				</table>
				<table>
					<tr>
						<th style="width: 100px;">备注:</th>
						<td>
							<textarea id="accounting_manage_remarks_input" name="accountingManage.remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]"><#if accountingManage?? && accountingManage.remarks??>${accountingManage.remarks}</#if></textarea>
						</td>
					</tr>
				</table>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>运输</td>
			<td id="accounting_freight_units_td"></td>
			<td style="padding-left: 0px;">
				<table id="accounting_freight_region_table">
					<tr>
						<#if accountingFreightRegionList?? && (accountingFreightRegionList?size>0)>
							<#list accountingFreightRegionList as accountingFreightRegion>
								<td id="accounting_freight_region_table_td_${accountingFreightRegion_index}">
									<input id="accounting_freight_region_input_${accountingFreightRegion_index}" name="accountingFreightRegionList[${accountingFreightRegion_index}].price" value="<#if accountingFreightRegion.price??>${accountingFreightRegion.price?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.total" />
								</td>
							</#list>
						<#else>
							<td id="accounting_freight_region_table_td_0">
								<input id="accounting_freight_region_input_0" name="accountingFreightRegionList[0].price" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.total" />
							</td>
						</#if>
					</tr>
				</table>
			</td>
			<td style="padding-left: 0px;">
				<table id="accounting_freight_remarks_region_table">
					<#if accountingFreightRegionList?? && (accountingFreightRegionList?size>0)>
						<#list accountingFreightRegionList as accountingFreightRegion>
							<tr id="accounting_freight_remarks_region_table_tr_${accountingFreightRegion_index}">
								<th style="width: 100px;">进货地区:</th>
								<td style="width: 100px;">
									<input id="accounting_freight_remarks_region_${accountingFreightRegion_index}" name="accountingFreightRegionList[${accountingFreightRegion_index}].region" value="${accountingFreightRegion.regionName}" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />
								</td>
								<th style="width: 100px;">总公里数(km):</th>
								<td style="width: 100px;">
									<input id="accounting_freight_remarks_sumkm_input_${accountingFreightRegion_index}" name="accountingFreightRegionList[${accountingFreightRegion_index}].sumKm" value="<#if accountingFreightRegion.sumKm??>${accountingFreightRegion.sumKm?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999" />
								</td>
							</tr>
						</#list>
					<#else>
						<tr id="accounting_freight_remarks_region_table_tr_0">
							<th style="width: 100px;">进货地区:</th>
							<td style="width: 100px;">
								<input id="accounting_freight_remarks_region_0" name="accountingFreightRegionList[0].region" value="" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />
							</td>
							<th style="width: 100px;">总公里数(km):</th>
							<td style="width: 100px;">
								<input id="accounting_freight_remarks_sumkm_input_0" name="accountingFreightRegionList[0].sumKm" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999" />
							</td>
						</tr>
					</#if>
					
				</table>
				<table>
					<tr>
						<th style="width: 100px;">单位成本:</th>
						<td>
							<input id="accounting_freight_remarks_unitscost_input" name="accountingFreight.unitsCost" value="<#if accountingFreight?? && accountingFreight.unitsCost??>${accountingFreight.unitsCost?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999" />元
							<select id="accounting_freight_remarks_units_input" name="accountingFreight.units" class="easyui-combobox" data-options="panelHeight:'auto',<#if accountingFreight?? && accountingFreight.units??>value:'${accountingFreight.units}',</#if>editable:false">
								<option value="每立方米">每立方米</option>
								<option value="每公斤" <#if accountingFreight?? && accountingFreight.units?? && accountingFreight.units == "每公斤">selected="selected"</#if>>每公斤</option>
							</select>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<th style="width: 100px">备注:</th>
						<td colspan="3">
							<textarea id="accounting_freight_remarks_input" name="accountingFreight.remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]"><#if accountingFreight?? && accountingFreight.remarks??>${accountingFreight.remarks}</#if></textarea>
						</td>
					</tr>
				</table>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>税收</td>
			<td id="accounting_tax_units_td"></td>
			<td style="padding-left: 0px;">
				<table id="accounting_tax_region_table">
					<tr>
						<#if accountingTaxRegionList?? && (accountingTaxRegionList?size>0)>
							<#list accountingTaxRegionList as accountingTaxRegion>
								<td id="accounting_tax_region_table_td_${accountingTaxRegion_index}">
									<input id="accounting_tax_region_input_${accountingTaxRegion_index}" name="accountingTaxRegionList[${accountingTaxRegion_index}].price" value="<#if accountingTaxRegion.price??>${accountingTaxRegion.price?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.total" />
								</td>
							</#list>
						<#else>
							
							<td id="accounting_tax_region_table_td_0">
								<input id="accounting_tax_region_input_0" name="accountingTaxRegionList[0].price" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999,onChange:addAccountingFn.total" />
							</td>
						</#if>
					</tr>
				</table>
			</td>

			<td style="padding-left: 0px;">
				<table id="accounting_tax_remarks_region_table">
					<#if accountingTaxRegionList?? && (accountingTaxRegionList?size>0)>
						<#list accountingTaxRegionList as accountingTaxRegion>
							<tr id="accounting_tax_remarks_region_table_tr_${accountingTaxRegion_index}">
								<th style="width: 100px;">进货地区:</th>
								<td style="width: 100px;">
									<input id="accounting_tax_remarks_region_${accountingTaxRegion_index}" name="accountingTaxRegionList[${accountingTaxRegion_index}].region" value="${accountingTaxRegion.region}" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />
								</td>
								<th style="width: 100px;">占比(%):</th>
								<td style="width: 100px;">
									<input id="accounting_tax_remarks_proportion_input_${accountingTaxRegion_index}" name="accountingTaxRegionList[${accountingTaxRegion_index}].proportion" value="<#if accountingTaxRegion.proportion??>${accountingTaxRegion.proportion?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
								</td>
							</tr>
						</#list>
					<#else>
						<tr id="accounting_tax_remarks_region_table_tr_0">
							<th style="width: 100px;">进货地区:</th>
							<td style="width: 100px;">
								<input id="accounting_tax_remarks_region_0" name="accountingTaxRegionList[0].region" value="" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />
							</td>
							<th style="width: 100px;">占比(%):</th>
							<td style="width: 100px;">
								<input id="accounting_tax_remarks_proportion_input_0" name="accountingTaxRegionList[0].proportion" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
							</td>
						</tr>
					</#if>
				</table>
				<table>
					<tr>
						<th style="width: 100px;">税率(%):</th>
						<td>
							<input id="accounting_tax_remarks_taxrate_input" name="accountingTax.taxRate" value="<#if accountingTax?? && accountingTax.taxRate??>${accountingTax.taxRate?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:0,max:9999999999" />
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<th style="width: 100px;">备注:</th>
						<td>
							<textarea id="accounting_tax_remarks_input" name="accountingTax.remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]"><#if accountingTax?? && accountingTax.remarks??>${accountingTax.remarks}</#if></textarea>
						</td>
					</tr>
				</table>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>利润</td>
			<td id="accounting_profit_units_td"></td>
			<td style="padding-left: 0px;">
				<table id="accounting_profit_region_table">
					<tr>
						<#if accountingProfitRegionList?? && (accountingProfitRegionList?size>0)>
							<#list accountingProfitRegionList as accountingProfitRegion>
								<td id="accounting_profit_region_table_td_${accountingProfitRegion_index}">
									<input id="accounting_profit_region_input_${accountingProfitRegion_index}" name="accountingProfitRegionList[${accountingProfitRegion_index}].price" value="<#if accountingProfitRegion.price??>${accountingProfitRegion.price?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:-9999999999,max:9999999999,onChange:addAccountingFn.total" />
								</td>
							</#list>
						<#else>
							<td id="accounting_profit_region_table_td_0">
								<input id="accounting_profit_region_input_0" name="accountingProfitRegionList[0].price" class="easyui-numberbox" style="width: 100px;" data-options="precision:3,min:-9999999999,max:9999999999,onChange:addAccountingFn.total" />
							</td>
						</#if>
					</tr>
				</table>
			</td>

			<td style="padding-left: 0px;">
				<table id="accounting_profit_remarks_region_table">
					<#if accountingProfitRegionList?? && (accountingProfitRegionList?size>0)>
						<#list accountingProfitRegionList as accountingProfitRegion>
							<tr id="accounting_profit_remarks_region_table_tr_${accountingProfitRegion_index}">
								<th style="width: 100px;">进货地区:</th>
								<td style="width: 100px;">
									<input id="accounting_profit_remarks_region_${accountingProfitRegion_index}" name="accountingProfitRegionList[${accountingProfitRegion_index}].region" value="${accountingProfitRegion.regionName}" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />
								</td>
								<th style="width: 100px;">占比(%):</th>
								<td style="width: 100px;">
									<input id="accounting_profit_remarks_proportion_input_${accountingProfitRegion_index}" name="accountingProfitRegionList[${accountingProfitRegion_index}].proportion" value="<#if accountingProfitRegion.proportion??>${accountingProfitRegion.proportion?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
								</td>
							</tr>
						</#list>
					<#else>
						<tr id="accounting_profit_remarks_region_table_tr_0">
							<th style="width: 100px;">进货地区:</th>
							<td style="width: 100px;">
								<input id="accounting_profit_remarks_region_0" name="accountingProfitRegionList[0].region" class="easyui-validatebox" style="width: 100px; background-color: rgb(235, 235, 228);" readonly="readonly" />
							</td>
							<th style="width: 100px;">占比(%):</th>
							<td style="width: 100px;">
								<input id="accounting_profit_remarks_proportion_input_0" name="accountingProfitRegionList[0].proportion" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
							</td>
						</tr>
					</#if>
					
				</table>
				<table>
					<tr>
						<th style="width: 100px;">备注:</th>
						<td>
							<textarea id="accounting_profit_remarks_input" name="accountingProfit.remarks" class="easyui-validatebox" style="width: 315px; height: 50px;" validtype="length[0,330]"><#if accountingProfit?? && accountingProfit.remarks??>${accountingProfit.remarks}</#if></textarea>
						</td>
					</tr>
				</table>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>其它成本小计</td>
			<td id="accounting_elsesubtotal_units_td"></td>
			<td style="padding-left: 0px;">
				<table id="accounting_elsesubtotal_region_table">
					<tr>
						<#if accountingElsesubtotalRegionList?? && (accountingElsesubtotalRegionList?size>0)>
							<#list accountingElsesubtotalRegionList as accountingElsesubtotalRegion>
								<td id="accounting_elsesubtotal_region_table_td_${accountingElsesubtotalRegion_index}">
									<input id="accounting_elsesubtotal_region_${accountingElsesubtotalRegion_index}" name="accountingElsesubtotalRegionList[${accountingElsesubtotalRegion_index}].region" value="${accountingElsesubtotalRegion.region}" class="easyui-validatebox" style="width: 100px; display: none;" />
									<input id="accounting_elsesubtotal_region_input_${accountingElsesubtotalRegion_index}" name="accountingElsesubtotalRegionList[${accountingElsesubtotalRegion_index}].subtotal" value="<#if accountingElsesubtotalRegion.subtotal??>${accountingElsesubtotalRegion.subtotal?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
								</td>
							</#list>
						<#else>
							<td id="accounting_elsesubtotal_region_table_td_0">
								<input id="accounting_elsesubtotal_region_0" name="accountingElsesubtotalRegionList[0].region" class="easyui-validatebox" style="width: 100px; display: none;" />
								<input id="accounting_elsesubtotal_region_input_0" name="accountingElsesubtotalRegionList[0].subtotal" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
							</td>
						</#if>
					</tr>
				</table>
			</td>
			<td>
				<textarea id="accounting_elsesubtotal_remarks_input" name="accountingElsesubtotal.remarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"><#if accountingElsesubtotal?? && accountingElsesubtotal.remarks??>${accountingElsesubtotal.remarks}</#if></textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>总价</td>
			<td id="accounting_aggregate_units_td"></td>
			<td style="padding-left: 0px;">
				<table id="accounting_aggregate_region_table">
					<tr>
						<#if accountingAggregateRegionList?? && (accountingAggregateRegionList?size>0)>
							<#list accountingAggregateRegionList as accountingAggregateRegion>
								<td id="accounting_aggregate_region_table_td_${accountingAggregateRegion_index}">
									<input id="accounting_aggregate_region_${accountingAggregateRegion_index}" name="accountingAggregateRegionList[${accountingAggregateRegion_index}].region" value="${accountingAggregateRegion.region}" class="easyui-validatebox" style="width: 100px; display: none;" />
									<input id="accounting_aggregate_region_input_${accountingAggregateRegion_index}" name="accountingAggregateRegionList[${accountingAggregateRegion_index}].sumPrice" value="<#if accountingAggregateRegion.sumPrice??>${accountingAggregateRegion.sumPrice?c}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
								</td>
							</#list>
						<#else>
							<td id="accounting_aggregate_region_table_td_0">
								<input id="accounting_aggregate_region_0" name="accountingAggregateRegionList[0].region" class="easyui-validatebox" style="width: 100px; display: none;" />
								<input id="accounting_aggregate_region_input_0" name="accountingAggregateRegionList[0].sumPrice" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:2,min:0,max:9999999999" readonly="readonly" />
							</td>
						</#if>
					</tr>
				</table>
			</td>
			<td>
				<textarea id="accounting_aggregate_remarks_input" name="accountingAggregate.remarks" class="easyui-validatebox" style="width: 430px; height: 50px;" validtype="length[0,330]"><#if accountingAggregate?? && accountingAggregate.remarks??>${accountingAggregate.remarks}</#if></textarea>
			</td>
			<td></td>
		</tr>
	</table>
</body>
</#compress>
