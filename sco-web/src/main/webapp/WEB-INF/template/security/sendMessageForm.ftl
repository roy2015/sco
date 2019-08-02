<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="sendMessage_form" method="post">
		<table class="tableForm">
			<tr>
			    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.onlineUser.messageHead")}: </span></th>
				<td><input name="messageHead"  value="" class="easyui-validatebox" /></td>
			</tr>
			<tr>
			    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.onlineUser.messageContent")}: </span></th>
				<td><textarea name="messageContent" colspan="5" value="" class="easyui-validatebox" /></td>
			</tr>
			<tr>
				<th align="right"><input name="isLock" type="checkbox" />${action.getText("security.onlineUser.lock")}</th>
				<td></td>
			</tr>
		</table>
	</form>
</div>
</#compress>