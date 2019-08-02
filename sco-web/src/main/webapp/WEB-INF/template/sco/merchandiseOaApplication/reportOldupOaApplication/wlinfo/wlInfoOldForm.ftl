<#if (dataList?size >= 0)>
	<div style="padding: 7px;"></div>
	&nbsp;&nbsp;注：若本次申请不涉及SAP物料号或供应商编号的变更，则请填入现有SAP物料号和供应商编号<p/>
	<div style="padding: 7px;"></div>
</#if>

<#list dataList as dl>
	<table border="1" class="wlInfo">
		<tr>
			<th></th>
			<th style="text-align:left;width:130px;">商品编号</th>
			<th style="text-align:left;width:410px;">商品名称</th>
			<th style="text-align:left;width:130px;">供应商编号</th>
			<th style="text-align:left;width:410px;">供应商名称</th>
			<th style="text-align:left;width:130px;">SAP物料号变更为</th>
			<th style="text-align:left;width:130px;">SAP供应商编号变更为</th>
			<#list dl.areaList as la>
				<th style="text-align:left;">
					${la.region}
					<input type="hidden" name="priceName${dl_index}" value="${la.region}" />
				</th>
			</#list>	
		</tr>
		<tr>
			<td><input type="checkbox" name="selectData" value="${dl_index}" /></td>
			<td>${dl.intentionCode}		<#-- 隐藏域 --><input name="intentionCode${dl_index}" type="hidden" value="${dl.intentionCode}" /></td>
			<td>${dl.intentionName}</td>
			<td>${dl.supplierCode}		<#-- 隐藏域 --><input name="supplierCode${dl_index}" type="hidden" value="${dl.supplierCode}" /></td>
			<td>${dl.supplierName}</td>
			<td><input class="easyui-validatebox" name="accessorySAPCode${dl_index}" value="${dl.accessorySAPCode}" maxlength="30" style="width:130px" /></td>
			<td><input class="easyui-validatebox" name="supplierSAPCode${dl_index}" value="${dl.supplierSAPCode}" maxlength="30" style="width:130px" /></td>
			<#list dl.areaList as la>
				<td style="text-align:right;"><input class="easyui-numberbox" name="sumPrice${la_index}${dl_index}" <#if la.sumPrice?exists>value="${la.sumPrice?c}"</#if> maxlength="12" style="width:130px;text-align: right;" /></td>
			</#list>
		</tr>
	</table>
</#list>
