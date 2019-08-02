<#compress>
<div style="padding:5px;overflow:hidden;">
	<form id="foretasteFeedback_form" method="post">
		<table class="tableForm">
			<input name="feedbackCode" id="feedbackCode" type="hidden" value="${foretasteFeedback.feedbackCode}"/>
			<input name="intentionCode" id="intentionCode" type="hidden"  value="${foretasteFeedback.intentionCode}"/>
			<input name="intentionSupplierCode" id="intentionSupplierCode" type="hidden"  value="${foretasteFeedback.intentionSupplierCode}"/>
					<tr>
					    <th align="right"><font color="red">*</font>试吃反馈日期：</th>
					    <td>
					    	<input name="foretasteDate"  value="<#if foretasteFeedback.foretasteDate??>${foretasteFeedback.foretasteDate?string('yyyy-MM-dd HH:mm:ss')}</#if>" style="width:124px;" class="easyui-datetimebox" data-options="required:true,editable:false" />
					    </td>
					    <th align="right">试吃人数：</th>
						<td>
							<input name="foretasteNumber"  value="<#if foretasteFeedback.foretasteNumber?? >${foretasteFeedback.foretasteNumber?c}</#if>" style="width:120px;" class="easyui-numberbox" data-options="required:false,min:0,max:9999999999" />
						</td>
					</tr>
					<tr>
					 	<th align="right"><font color="red">*</font>试吃评分：</th>
						<td>
							<input name="foretasteGrade"  value="<#if foretasteFeedback.foretasteGrade?? >${foretasteFeedback.foretasteGrade?c}</#if>" style="width:120px;" class="easyui-numberbox" data-options="required:true,precision:2,min:0,max:9999999999" />
						</td>
					    <th align="right"><font color="red">*</font>试吃满分：</th>
						<td>
							<input name="foretasteFull"  value="<#if foretasteFeedback.foretasteFull?? >${foretasteFeedback.foretasteFull?c}</#if>" style="width:120px;" class="easyui-numberbox" data-options="required:true,min:0,max:9999999999" />
						</td>
					   
					</tr>
					<tr>
					    <th align="right"><font color="red">*</font>试吃评价：</th>
						<td colspan="3">
							<textarea name="evaluate"  class="easyui-validatebox" style="width:367px;height:100px" data-options="required:true,validType:'length[0,333]'" >${foretasteFeedback.evaluate}</textarea>
						</td>						
					</tr>
		</table>
	</form>
</div>
</#compress>