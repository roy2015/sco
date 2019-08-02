<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="halt_form" method="post">
		<table class="tableForm">
			<input id="haltId" name="haltId" type="hidden" value="${halt.haltId}"/>
			<input id="state" name="state" type="hidden" value="${halt.state}"/>
			<tr>
			    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.halt.haltMsg")}: </span></th>
				<td><textarea name="haltMsg" class="easyui-validatebox" cols="10" rows="5" data-options="required:true,validType:'length[1,24]'">${halt.haltMsg}</textarea></td>
			</tr>
		</table>
	</form>
</div>
</#compress>