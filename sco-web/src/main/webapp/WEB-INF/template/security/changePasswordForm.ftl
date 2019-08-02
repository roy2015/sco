<#compress>
<style type="text/css">
.tableForm{width:100%}
.tableForm th{width:80px;text-align:right}
.tableForm th,.tableForm td{ padding:2px 2px 2px 0}
.tableForm td{width:200px;}
.tableForm td input{width:160px;height:20px; padding-left:3px}
</style>
<div style="padding: 5px;overflow: hidden;">
	<form id="changePasswordForm" method="post">
	<table class="tableForm">
			<tr style="height:8px;">
				<th ></th>
				<td></td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th><span class="txtCenter">用户名</span>:</th>
				<td>
					<input name="name"  disabled="disabled"  validType="length[1,15]" value="${user.realName}" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写登录名称'" />
				</td>
			</tr>
			<tr>
				<th><span class="txtCenter">${action.getText("public.property.oldPW")}</span></th>
				<td><input name="oldpwd"  validType="length[4,32]" type="password" class="easyui-validatebox"  data-options="required:true"/></td>
			</tr>
			<tr>
				<th><span class="txtCenter">${action.getText("public.property.newPW")}</span></th>
				<td><input id="newpwd" name="newpwd" validType="length[4,32]"  type="password" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<th><span class="txtCenter">${action.getText("public.property.confirmPW")}</span></th>
				<td><input id="repeatpwd" name="repeatpwd" class="easyui-validatebox" validType="equalTo['#newpwd']" invalidMessage="两次输入密码不匹配" type="password" value="${user.newpwd}" data-options="required:true"/></td>
			</tr>
		</table>
	</form>
</div>
</#compress>