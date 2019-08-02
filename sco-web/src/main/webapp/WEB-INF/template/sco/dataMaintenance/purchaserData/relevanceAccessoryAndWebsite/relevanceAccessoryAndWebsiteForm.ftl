<#compress>
<script>
	<#include "sco/common/materialProperties.js" />
</script>
<div style="padding: 18px;overflow: hidden;">
	<form id="releAccdWeb_form" method="post">
		<input type="hidden" id="accessoryCode" name="accessoryCode" />
		<input type="hidden" id="intentionCode" name="intentionCode" />
		<input type="hidden" id="intentionSupplierCode" name="intentionSupplierCode" />
		<input type="hidden" id="supplierCode" name="supplierCode" />
		<table class="tableForm" style="width:500px">
			<tr>
				<td style="text-align:right;"><font color="red">*</font>原料大类:</td>
				<td>
					<input class="filterSelect" name="materialBigTypeCode" id="materialBigTypeCode" style="width:104px;" 
						data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							editable:false,
							required:true,
							url:'materialProperties_listMaterialBigType_5.html'
				    " />
				</td>
				<td style="text-align:right;"><font color="red">*</font>原料小类:</td>
				<td>
					<input class="filterSelect" name="materialSmallTypeCode" id="materialSmallTypeCode" style="width:104px;" 
						data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							required:true,
							editable:false
				    " />
				</td>
			</tr>
			<tr>
				<td style="text-align:right;"><font color="red">*</font>公示网站原料名称:</td>
				<td>
					<input class="filterSelect" name="websiteMaterialName" id="mName" style="width:104px;" 
						data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							required:true,
							editable:false
				    " />
				</td>
				<td style="text-align:right;"><font color="red">*</font>价格地区:</td>
				<td>
					<input class="filterSelect" name="priceRegion" id="region" style="width:104px;" 
						data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							required:true,
							editable:false
				    " />
				</td>
			</tr>
			<tr>
				<td style="text-align:right;"><font color="red">*</font>公示网站名称:</td>
				<td>
					<input class="filterSelect" name="websiteCode" id="webName" style="width:104px;" 
						data-options="
							valueField:'text',
							textField:'text',
							panelHeight:'auto',
							editable:false,
							required:true
				    " />
				</td>
				<td style="text-align:right;"><font color="red">*</font>克重:</td>
				<td>
					<input class="easyui-numberbox" name="weight" data-options="required:true,precision:2" maxlength="12" style="width:100px;height:17px"/>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;"><font color="red">*</font>公示网站地址:</td>
				<td colspan="4">
					<input class="filterSelect" name="websiteUrl" id="webUrl" style="width:332px;" 
						data-options="
							valueField:'text',
							textField:'text',
							panelHeight:'auto',
							editable:false,
							required:true
				    " />
				</td>
			</tr>
		</table>
	</form>
</div>
</#compress>