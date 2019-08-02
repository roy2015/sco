<#compress>
<div style="padding: 10px;">
	<form id="signedQuantity_form" method="post">
		<table border="0" style="margin: 8px;height: 116px;">
			<input name="qlCode" id="qlCode" type="hidden" value="${qlCode}"/>
			<tr>
				<td colspan="6"><b>请填入改签信息：</b></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;<font color="red">*</font>签量开始日期：&nbsp;</td>
				<td>	
					<input class="easyui-datebox" name="qlStartDate" 
						<#if qlCode?exists>disabled="disabled" value="${sqty.qlStartDate?string("yyyy-MM-dd")}" </#if>
						data-options="required:true,editable:false" style="width:104px;"/>
				</td>
				<td>&nbsp;&nbsp;<font color="red">*</font>签量数量：&nbsp;</td>
				<td>		
					<input class="easyui-numberbox" id="qlCount" name="qlCount" <#if qlCode?exists>value="${sqty.qlCount?c}"</#if>
						data-options="precision:2,required:true,validType:['validateQlCount']" style="width:100px;text-align:right" maxlength="12" />kg
				</td>
					<td>&nbsp;&nbsp;已完成数量：</td>
					<td>		
						<input class="easyui-numberbox" id="proCount" disabled="disabled" value="${sqty.proCount}"
							data-options="precision:2" style="width:100px;text-align:right" />kg
					</td>
			</tr>
			<tr>
				<td colspan="6">&nbsp;
					<b style="color:red">
						*是否优先满足前一条签量？
					</b>
				</td>
			</tr>
			<tr>
				<td colspan="6">&nbsp;
					<input type="radio" <#if sqty.isSatisfyBeforeQl == 'Y'>checked="checked" </#if> 
						disabled="disabled" /> 是
					&nbsp;&nbsp;
					<input type="radio" <#if sqty.isSatisfyBeforeQl == 'N'>checked="checked" </#if>
					 	disabled="disabled" /> 否
					
				 	<input type="hidden" name="isSatisfyBeforeQl" value="${sqty.isSatisfyBeforeQl}">
				</td>
			</tr>
		</table>
		<table border="1" style="margin: 8px;word-break:keep-all;white-space:nowrap;height: 97px;">
			<tr>
				<td>商品编号</td>
				<td>商品名称</td>
				<td>供应商编号</td>
				<td>供应商名称</td>
				<#-- <td>商品定性角色</td>
				<td>商品定量角色</td>
				<td>中分类</td> -->
				<td>小分类</td>
				<td>明细类</td>
				<td>细分类</td>
				<td>X001进价（元/kg）</td>
				<td><font color="red">*</font>签量价格（元/kg）</td>
				<td>备注</td>
			</tr>
			<#list merList as list>
				<tr>
					<td>${list.merchandiseCode}</td>
					<td>${list.merchandiseName}</td>
					<td>${list.supplierCode}</td>
					<td>${list.supplierName}</td>
					<#-- <td>${list.dxRoleName}</td>
					<td>${list.dlRoleName}</td>
					<td>${list.centreName}</td> -->
					<td>${list.smallName}</td>
					<td>${list.detailName}</td>
					<td>${list.fineName}</td>
					<td style="text-align:right"><#if list.xPrice?exists>${list.xPrice?string("#,##0.00")}</#if></td>
					<td style="text-align:right">
						<#if list.qlPrice?exists>${list.qlPrice?string("#,##0.00")}</#if>
					</td>
					<td>
						<textArea name="${list.merchandiseCode}_bak" style="height:40px;width:150px;font-size:12px;" 
							class="easyui-validatebox" maxlength="333" data-options="required:false,editable:false,validType:'length[0,333]'">
							${list.remarks}
						</textArea>
					</td>
				</tr>
			</#list>
		</table>
	</form>
</div>
</#compress>