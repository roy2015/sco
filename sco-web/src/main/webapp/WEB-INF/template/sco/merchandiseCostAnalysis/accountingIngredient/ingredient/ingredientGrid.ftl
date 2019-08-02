<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
</head>
<body>
	<div id="ingredient_toolbar" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="ingredientItemFn.append()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="ingredientItemFn.removeit()">移除</a>
	</div>
	<div>
	<table  id="ingredientItem_grid"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#ingredient_toolbar',
				onClickRow: ingredientItemFn.onClickRow,
				onBeforeEdit:ingredientItemFn.ingredientItemGridOnBeforeEdit
				
			">
		<thead>
			<tr>
				<th data-options="field:'materialType',width:60,
					formatter:function(value,row){
						if(row.materialTypeName == null || row.materialTypeName == ''){
							return row.text;
						} else {
							return row.materialTypeName;
						}
                    },
					editor:{
						type:'combobox',
						options:{
							required:true,
							editable:false,
							panelHeight:'auto',
							valueField:'id',
							textField:'text',
							url:'businessComBox_materialTypeList_5.html'
						}
					}">原料类型</th>
				<th data-options="field:'materialName',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[0,10]'}}">原料名称</th>
				<th data-options="field:'materialOrigin',width:100,editor:{type:'validatebox',options:{validType:'length[0,33]'}}">原料产地</th>
				<th data-options="field:'materialBrand',width:100,editor:{type:'validatebox',options:{validType:'length[0,33]'}}">原料品牌</th>
				<th data-options="field:'materialLevelSpecification',width:100,editor:{type:'validatebox',options:{validType:'length[0,33]'}}">原料等级与规格</th>
				<th data-options="field:'purchasePrice',width:120,
					formatter:function(value,row){
						if(value != null && value != ''){
							return Number(value).toFixed(4);
						} else {
							return value;
						}
                    },editor:{type:'numberbox',options:{required:true,precision:4,min:0,max:9999999999}}" align="right">原料采购价格(元/kg)</th>
				<th data-options="field:'inputCount',width:100,
					formatter:function(value,row){
						if(value != null && value != ''){
							return Number(value).toFixed(4);
						} else {
							return value;
						}
                    },editor:{type:'numberbox',options:{required:true,precision:4,min:0,max:9999999999}}" align="right">原料投入量(kg)</th>
				<th data-options="field:'inputCost',width:100,
					formatter:function(value,row){
						if(value != null && value != ''){
							return Number(value).toFixed(4);
						} else {
							return value;
						}
                    },editor:{type:'numberbox',options:{required:true,precision:4,min:0,max:9999999999}}" align="right">原料投入成本(元)</th>
				<th data-options="field:'avgCost',width:140,
					formatter:function(value,row){
						if(value != null && value != ''){
							return Number(value).toFixed(4);
						} else {
							return value;
						}
                    },editor:{type:'numberbox',options:{required:true,precision:4,min:0,max:9999999999}}" align="right">平均成品原料成本(元/kg)</th>
				<th data-options="field:'inputCountProportionStr',width:90,
					formatter:function(value,row){
						if(value != null && value != ''){
							return Number(value).toFixed(2)
						} else {
							return value;
						}
                    },editor:{type:'numberbox',options:{precision:2,disabled:true}}" align="right">投入量占比(%)</th>
				<th data-options="field:'inputCostProportionStr',width:100,
					formatter:function(value,row){
						if(value != null && value != ''){
							return Number(value).toFixed(2)
						} else {
							return value;
						}
                    },editor:{type:'numberbox',options:{precision:2,disabled:true}}" align="right">投入成本占比(%)</th>
				<th data-options="field:'moisture',width:60,editor:{type:'validatebox',options:{validType:'length[0,33]'}}" align="right">含水率(%)</th>
				<th data-options="field:'remarks',width:100,editor:{type:'textarea',options: {rows:'4'}}">备注</th>
			</tr>
		</thead>
	</table>
	</div>
	<div>
		<p>
		<table>
			<tr>
				<td align="right">投入量总计(kg)：</td>
				<td>
					<input id="ingredientItemInputCountSum" value="<#if ingredientItemInputCountSum??>${ingredientItemInputCountSum?string('0.0000')}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4" readonly="readonly" />
				</td>
				<td align="right" style="width: 120px;">投入成本总计(元)：</td>
				<td>
					<input id="ingredientItemInputCostSum" value="<#if ingredientItemInputCostSum??>${ingredientItemInputCostSum?string('0.0000')}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4" readonly="readonly" />
				</td>
				<td align="right" style="width: 180px;">平均成品原料成本总计(元/kg)：</td>
				<td>
					<input id="ingredientItemAvgCostSum" value="<#if ingredientItemAvgCostSum??>${ingredientItemAvgCostSum?string('0.0000')}</#if>" class="easyui-numberbox" style="width: 100px; background-color: rgb(235, 235, 228);" data-options="precision:4" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td align="right">产品生成量(kg)：</td>
				<td>
					<input name="ingredient.productCount" value="<#if ingredient.productCount??>${ingredient.productCount?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:2,min:0,max:9999999999" />
				</td>
				<td align="right">得率(%)：</td>
				<td>
					<input id="ingredient_yield" name="ingredient.yield" value="<#if ingredient.yield??>${ingredient.yield?c}</#if>" class="easyui-numberbox" style="width: 100px;" data-options="precision:2,min:0,max:9999999999,onChange:addAccountingFn.ingredientYieldValue" />
				</td>
				<td align="right">成品含水率(%)：</td>
				<td>
					<input name="ingredient.moisture" value="<#if ingredient.moisture??>${ingredient.moisture}</#if>" class="easyui-validatebox" style="width: 100px;" validtype="length[0,33]" />
				</td>
			</tr>
		</table>
		<p>
	</div>
</body>
</html>
</#compress>