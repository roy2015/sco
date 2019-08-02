<#compress>
<div style="padding: 4px;overflow: hidden;">
	<form id="webMaterial_form" method="post">
		<input type="hidden" id="materialCode" name="materialCode" value="${website.materialCode}">
		<input type="hidden" id="priceRegionCode" name="priceRegionCode" value="${website.priceRegionCode}">
		<table style="width:494px;height:120px" border="0">
			<tr>
				<td style="text-align:right;width:121px;">原料大类：</td>
				<td>
					<select style="width:100px" disabled="disabled">
						<option value="">${website.materialBigTypeName}</option>
					</select>
				</td>
				<td style="text-align:right;width:80px;">原料小类：</td>
				<td>
					<select style="width:100px" disabled="disabled">
						<option value="">${website.materialSmallTypeName}</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">公示网站原料名称：</td>
				<td colspan="3">
					<select style="width:325px" disabled="disabled">
						<option value="">${website.websiteMaterialName}</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">公示网站名称：</td>
				<td>
					<select style="width:100px" disabled="disabled">
						<option value="">${website.websiteName}</option>
					</select>
				</td>
				<td style="text-align:right;">价格地区：</td>
				<td>
					<select style="width:101px" disabled="disabled">
						<option value="">${website.priceRegion}</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;"><font color="red">*</font>价格日期：</td>
				<td>
					<input class="easyui-datebox list-input" name="priceDate" data-options="required:true,editable:false" style="width:100px;"/>
				</td>
				<td style="text-align:right;"><font color="red">*</font>价格：</td>
				<td>
					<input class="easyui-numberbox" name="price" data-options="required:true,precision:2" maxlength="12" style="width:96px;height:17px"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</#compress>