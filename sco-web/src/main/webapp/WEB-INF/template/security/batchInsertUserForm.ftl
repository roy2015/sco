<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="batchInsertUserForm" method="post" enctype="multipart/form-data">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#EEEEEE" >
			<tr height="30" align="left">
			  <td colspan = 2><span style="margin-right:5px;font-weight: normal;">请选择批量注册用户模板文件：</span></td>
		    </tr>
			<tr height="30" align="left">
			  <td>
			  	<input type="file" id="upload" name="upload" />
			  </td>
			  <td>
			  	<a href="#" id="downloadUserExcelTemplate" class="easyui-linkbutton" data-options="iconCls:'excel'" 
		    		onclick="userFn.downloadExcelTemplate();">
		    		${action.getText("common.button.download.template.excel")}
		    	</a>
			  </td>
		    </tr>
		</table>
	</form>
</div>
</#compress>