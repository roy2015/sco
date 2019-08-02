<#compress>
<style>
	input,textarea {
		padding-left: 5px;
		height: 20px;
		line-height: 20px;
		border: 1px solid #CCC;
		background-color: #FFF;
	}
</style>
<div style="padding: 5px;overflow: hidden;">
	<form id="user_form" method="post">
		<table class="tableForm">
			<input id="userId" name="userId" type="hidden" value="${user.userId}"/>
			<input id="objectTyp" type="hidden" name="objectTyp"  value="O" />
			<input id="objectName" type="hidden"  name="orgName" value="SCO" />
			<input id="objectId" type="hidden" name="orgId"  value="10001" />
			<#assign flag = "${user.userId}" />
			<tr>
			    <th align="right"><span class="txtCenter">${action.getText("security.user.loginName")}: </span></th>
				<td>
				<#if flag==''>
					<input name="loginName" style="width:140px" value="${user.loginName}" validType="length[1,20]" class="easyui-validatebox" data-options="required:'true'" />
				<#else>
					<input name="loginName" style="width:140px" disabled="disabled" validType="length[1,20]" value="${user.loginName}" class="easyui-validatebox" data-options="required:'true'" />
				</#if>
				</td>
			    <th align="right"><span class="txtCenter">${action.getText("security.user.realName")}: </span></th>
				<td><input name="realName" style="width:140px" value="${user.realName}" class="easyui-validatebox" data-options="required:true,validType:'length[1,60]'" /></td>
			</tr>
			<tr>
				<th align="right"><span class="txtCenter">${action.getText("security.user.sex")}: </span></th>
				<td><input id="sex" name="sex" style="width:147px" editable="false" panelHeight="auto" value="${user.sex}" class="easyui-combobox" data-options="data:Dic.Sex,valueField:'id'" /></td>
			    <th align="right"><span class="txtCenter">${action.getText("security.user.email")}: </span></th>
				<td><input name="email" style="width:140px" value="${user.email}" class="easyui-validatebox" data-options="required:true,validType:['length[5,64]','email']" /></td>
			</tr>
			<#if flag==''>
			<tr>
			    <th align="right"><span class="txtCenter">${action.getText("security.user.passwd")}: </span></th>
				<td><input id="passwd" style="width:140px" name="passwd" value="${user.passwd}" type="password" class="easyui-validatebox" data-options="required:true,validType:'length[4,32]'" /></td>
				<th align="right" style="width:140px"><span class="txtCenter">${action.getText("security.user.confirmPasswd")}: </span></th>
				<td><input id="confirmPasswd" style="width:140px" name="confirmPasswd" class="easyui-validatebox" validType="equalTo['#passwd']" invalidMessage="两次输入密码不匹配" type="password" value="${user.passwd}" data-options="required:'true'"/></td>
			</tr>
			</#if>
			<tr>
			    <th align="right"><span class="txtCenter">${action.getText("security.user.phone")}: </span></th>
				<td><input name="phone" style="width:140px"  value="${user.phone}" class="easyui-validatebox" data-options="required:false,validType:'length[0,40]'" /></td>
			    <th align="right"><span class="txtCenter">${action.getText("security.user.mobile")}: </span></th>
				<td><input name="mobile" style="width:140px" value="${user.mobile}" class="easyui-validatebox" data-options="required:false,validType:'length[0,30]'" /></td>
			</tr>
				<!-- 
				<tr id="orgInfo">
			    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("security.user.objectTyp")}: </span></th>
				<th align="right" style="width: 100px;"><span class="txtCenter">${action.getText("security.user.objectId")}: </span></th>
				<input id="objectTyp" name="objectTyp" hidden="true" style="width:185px" value="${user.objectTyp}" class="easyui-combobox" data-options="required:true,panelHeight:'auto',hidden:'hidden',editable:false,data:Dic.OrgTypes,valueField:'id',onChange:function(){userFn.clearOrgName()}"/></td>
		 	    <input id="objectName" style="width:180px" name="orgName"  panelHeight="auto" value="${user.objectName}" class="easyui-combobox" data-options="required:true,editable:false"/>
				-->
			<tr>
			    <th align="right"><span class="txtCenter">${action.getText("security.user.description")}: </span></th>
				<td colspan="3"><textArea  name="description" type="textarea" value="${user.description}" style="height:40px;width:<#if flag==''>462px<#else>420px</#if>;font-size:12px;" class="easyui-validatebox" data-options="required:false,editable:false,validType:'length[0,2000]'">${user.description}</textArea></td>
			</tr>
		</table>
	</form>
</div>
</#compress>