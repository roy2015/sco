<#compress>
<form  id="func_form" method="post">
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
				<input id="funcType" class="easyui-combobox" name="funcType"  editable="false"
				data-options="panelHeight:'auto',required:true,value:'${func.funcType}',valueField:'id',textField:'text',
					data:[{id:'M',text:'${action.getText("security.func.funcType.M")}'},
						{id:'B',text:'${action.getText("security.func.funcType.B")}'},
						{id:'U',text:'${action.getText("security.func.funcType.U")}'},
						{id:'R',text:'${action.getText("security.func.funcType.R")}'},
						{id:'O',text:'${action.getText("security.func.funcType.O")}'}],
					onSelect: function(rec){
						if (rec.id == 'U'||rec.id == 'R')
							$('#dataTypeId').show();
						else
						 	$('#dataTypeId').hide();
					}
					" />
			</td>
			<td>${action.getText("security.func.iconcls")}:</td><td>
			<input id="iconCls" class="easyui-combobox" name="iconcls"  
			data-options="value:'${func.iconcls}',valueField:'text',textField:'text',url:'func_loadIconCssList_5.html',
    			formatter: function(row){
					return '<div class='+row.text+' style=\'height:16px;text-align:left;padding-left:20px\'>'+row.text+'</div>';
				}
			" />
			</td>
		</tr>
		<tr id = 'dataTypeId' >
			<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.dataTypeId")}: </span></span></td><td colspan="3"><input value="${func.dataTypeId}" id="dataTypeId" class="easyui-validatebox"  type=text   name="dataTypeId"/></td>
		</tr>
		<tr>
			<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.funcKey")}: </span></span></td><td colspan="3"><input value="${func.funcKey}" id="funcKey" class="easyui-validatebox"  type=text   name="funcKey"/></td>
		</tr>
		<tr>
			<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.src")}:</span></span></td><td colspan="3"><input value="${func.src}" id="src" class="easyui-validatebox"  type=text  name="src"/></td>
		</tr>
		<tr>
			<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.description")}:</span></span></td><td colspan="3"><textarea style="height:40px;font-size:12px;" id="description" class="easyui-validatebox"  name="description">${func.description}</textarea></td>
		</tr>
		<tr id='disableMsgRow'>
			<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.disableMsg")}:</span></span></td><td colspan="3"><textarea style="height:40px;color:red;font-size:12px;" id="disableMsg" class="easyui-validatebox" name="disableMsg">${func.disableMsg}</textarea></td>
		</tr>
		<tr>
			<td><span style="width: 80px;"><span class="txtCenter">${action.getText("security.func.isFree")}:</span></span></td><td><input id="isFree" type="checkbox"  name="isFree" style='width:15px'></td>
		</tr>
	</table>
</form>  
</#compress>