<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="merchandiseQuoted_form" method="post">
		<table class="tableForm">
			<input name="intentionCode" id="intentionCode" type="hidden"  value="${merchandiseQuoted.intentionCode}"/>
			<input name="quotationCode" id="quotationCode" type="hidden"  value="${merchandiseQuoted.quotationCode}"/>
			<input name="intentionSupplierCode" id="intentionSupplierCode" type="hidden"  value="${merchandiseQuoted.intentionSupplierCode}"/>
					<tr>
					    <th align="right"><font color="red">*</font>公司名称：</th>
						<td colspan="3">
							<input name="companyName"  value="${merchandiseQuoted.companyName}" class="easyui-validatebox" style="width:414px;" data-options="required:true,validType:'length[0,33]'" />
						</td>
					</tr>
					<tr>
					    <th align="right"><font color="red">*</font>公司地址：</th>
						<td colspan="3">
							<input name="companySite"  value="${merchandiseQuoted.companySite}" class="easyui-validatebox" style="width:414px;" data-options="required:true,validType:'length[0,100]'" />
						</td>
					</tr>
					<tr>
					    <th align="right"><font color="red">*</font>联系人姓名：</th>
						<td>
							<input name="contactsName"  value="${merchandiseQuoted.contactsName}" class="easyui-validatebox" style="width:120px;" data-options="required:true,validType:'length[0,10]'" />
						</td>
					    <th align="right"><font color="red">*</font>联系人电话：</th>
						<td>
							<input name="contactsPhone"  value="${merchandiseQuoted.contactsPhone}" class="easyui-validatebox" style="width:120px;" data-options="required:true,validType:'length[0,30]'" />
						</td>
					</tr>
					<tr>
					    <th align="right">联系人邮箱：</th>
						<td>
							<input name="contactsEmail"  value="${merchandiseQuoted.contactsEmail}" class="easyui-validatebox" style="width:120px;" data-options="required:false,validType:'length[0,30]'" />
						</td>
					    <th align="right">联系人传真：</th>
						<td>
							<input name="contactsFax"  value="${merchandiseQuoted.contactsFax}" class="easyui-validatebox" style="width:120px;" data-options="required:false,validType:'length[0,30]'" />
						</td>
					</tr>
					 <tr>
					    <th align="right"><font color="red">*</font>报价币种：</th>
						<td>
							<input name="quotedCurrency"  value="${merchandiseQuoted.quotedCurrency}" class="easyui-validatebox" style="width:120px;"  data-options="required:true,validType:'length[1,10]'" />
							<!-- <input name="quotedCurrency"  value="${merchandiseQuoted.quotedCurrency}" class="easyui-validatebox" data-options="required:true,validType:'length[0,10]'" />-->
							<#-- <select name="quotedCurrency" panelHeight="auto" style="width:124px;" class="easyui-combobox" data-options="required:true,editable:false" >
								 <option <#if merchandiseQuoted.quotedCurrency == 'CNY'>selected="selected"</#if> value="CNY">CNY</option>
								 <option <#if merchandiseQuoted.quotedCurrency == 'USD'>selected="selected"</#if> value="USD">USD</option>
								 <option <#if merchandiseQuoted.quotedCurrency == 'EUR'>selected="selected"</#if> value="EUR">EUR</option>
							</select> -->
						</td>
					    <th align="right"><font color="red">*</font>报价日期：</th>
					    <td>
					    	<input name="quotedDate"  value="<#if merchandiseQuoted.quotedDate??>${merchandiseQuoted.quotedDate?string('yyyy-MM-dd')}</#if>" class="easyui-datebox" style="width:124px;" data-options="required:true,editable:false" />
					    </td>
					</tr>
					<tr>
					    <th align="right"><font color="red">*</font>价格：</th>
						<td>
							<!--BigDecimal类型默认没有初始化，为null，因此不能格式化显示(需要先判断是否为空再格式化)-->
							<input name="price"  value="<#if merchandiseQuoted.price?? >${merchandiseQuoted.price?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,precision:2,min:0,max:9999999999" />
						</td>
					    <th align="right" style="width:114px"><font color="red">*</font>基本计量单位(kg)：</th>
						<td>
							<input name="quotationUnits"  value="<#if merchandiseQuoted.quotationUnits?? >${merchandiseQuoted.quotationUnits?c}</#if>" class="easyui-numberbox" style="width:120px;" data-options="required:true,precision:3,min:0,max:9999999999" />
						</td>
					</tr>
					<tr>
					    <th align="right">最小起订量：</th>
						<td>
							<input name="minRationed" value="<#if merchandiseQuoted.minRationed?? >${merchandiseQuoted.minRationed}</#if>" class="easyui-validatebox" style="width:120px;" data-options="required:false,validType:'length[0,30]'" />
						</td>
					    <th align="right">报价有效期至：</th>
					    <td>
					    	<input name="quotedEndDate"  value="<#if merchandiseQuoted.quotedEndDate??>${merchandiseQuoted.quotedEndDate?string('yyyy-MM-dd')}</#if>" class="easyui-datebox" style="width:124px;" data-options="required:false,width:'180',editable:false" />
					    </td>
					</tr>
					<tr>
					    <th align="right"><font color="red">*</font>包装方式：</th>
						<td>
							<input name="packingType"  value="${merchandiseQuoted.packingType}" class="easyui-validatebox" style="width:120px;"  data-options="required:true,validType:'length[0,33]'" />
						</td>
					</tr>
					<tr>
					    <th align="right">付款方式：</th>
						<td>
							<textarea name="paymentType"  class="easyui-validatebox" style="width:124px;height:50px" data-options="required:false,validType:'length[0,33]'" >${merchandiseQuoted.paymentType}</textarea>
						</td>
					    <th align="right">交货方式：</th>
						<td>
							<textarea name="deliveryType"  class="easyui-validatebox" style="width:124px;height:50px" data-options="required:false,validType:'length[0,33]'" >${merchandiseQuoted.deliveryType}</textarea>
						</td>
					</tr>
					<tr>
					    <th align="right">供应商备注：</th>
						<td colspan="3">
							<textarea name="remarks"  class="easyui-validatebox" style="width:124px;height:50px" data-options="required:false,validType:'length[0,333]'" >${merchandiseQuoted.remarks}</textarea>
						</td>
					</tr>
		</table>
	</form>
</div>
</#compress>