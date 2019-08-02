<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="resetPassword_form" method="post">
		<table class="tableForm">
			<input id="userId" name="userId" type="hidden" value="${user.userId}"/>
			<tr>
				<th align="right" style="width: 60px;"><span class="txtCenter">${action.getText("security.user.loginName")}: </span></th>
				<td colspan="3"><input name="loginName"  value="${user.loginName}" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'" disabled="disabled" /></td>
			</tr>
			<tr>
				<th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.user.passwd")}: </span></th>
				<td><input id="passwd" name="passwd" type="password" class="easyui-validatebox" data-options="required:true,validType:'length[4,32]'" /></td>
			</tr>
			<tr>
				<th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.user.confirmPasswd")}: </span></th>
				<td><input id="confirmPasswd" name="confirmPasswd" class="easyui-validatebox" validType="equalTo['#passwd']" invalidMessage="两次输入密码不匹配" type="password" data-options="required:'true'"/></td>
			</tr>
		</table>
	</form>
</div>
</#compress>