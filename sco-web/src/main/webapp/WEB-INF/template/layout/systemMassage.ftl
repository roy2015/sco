<#compress>
<div style="padding: 5px;overflow: hidden;">
	<form id="messageParamsForm" method="post">
		<input name="paramId"  value="200" type="hidden"/>
		<table width="96%" border="0" cellpadding="0" cellspacing="0" bordercolor="#EEEEEE" >
			<tr height="30" align="left">
			  <th width="80" align="right"><span style="margin-right:5px;font-weight: normal;">${action.getText("pub.title.systemMassage")}：</span></th>
			  <td>
			  	<input class="easyui-validatebox" type="text" style="width:100%;" id="pubMsgVal" name="paramValue" data-options="required:true,  validType:'length[0, 64]'"></input>
			  </td>
		    </tr>
		</table>
	</form>
</div>
</#compress>