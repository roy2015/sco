<#compress>
<form  id="func_form">
		<input id="position" name ="position" type="hidden"/>
		<input id="funcId" name ="funcId" type="hidden" value="${func.funcId}"/>
		<input id="funcPid" name ="funcPid" type="hidden" value="${func.funcPid}"/>
		<input name ="orderNo" type="hidden" value="${func.orderNo}"/>
		<table class="tableForm" cellpadding="0" cellspacing="0">
			<tr>
				<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.name")}: </span></span></td>
				<td colspan="3"><input value="${func.name}" id="name" class="easyui-validatebox"  type=text name="name" data-options="required:true"/></td>
			</tr>
			<tr>
				<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.funcType")}: </span></span></td><td>
					<input id="funcType" class="easyui-combobox" name="funcType"  
    				data-options="required:true,value:'${func.funcType}',valueField:'id',textField:'text',data:[{id:'M',text:'菜单'},{id:'B',text:'按钮'},{id:'O',text:'其他'}]" />
				</td>
				<td>IconCls:</td><td>
				<input id="iconCls" class="easyui-combobox" name="iconcls"  
    			data-options="value:'${func.iconcls}',valueField:'text',textField:'text',url:'func_loadIconCssList_5.html',
	    			formatter: function(row){
						return '<div class='+row.text+' style=\'height:16px;text-align:left;padding-left:20px\'>'+row.text+'</div>';
					}
    			" />
				</td>
			</tr>
			<tr>
				<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.funcKey")}: </span></span></td><td colspan="3"><input value="${func.funcKey}" id="funcKey" class="easyui-validatebox"  type=text   name="funcKey"/></td>
			</tr>
			<tr>
				<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.src")}:</span></span></td><td colspan="3"><input value="${func.src}" id="src" class="easyui-validatebox"  type=text  name="src"/></td>
			</tr>
			<tr>
				<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.description")}:</span></span></td><td colspan="3"><textarea style="height:40px;" value="${func.description}" id="description" class="easyui-validatebox"  type=text name="description"/></td>
			</tr>
		</table>
</form>  
</#compress>