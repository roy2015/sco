<#compress>
	<div style="padding: 15px;overflow: hidden;">
		<form id="purOrder_form" method="post" enctype="multipart/form-data">
			<table class="tableForm">
				<tr>
					<td style="text-align:right;width:115px"><font color="red">*</font>请填写申购单名称:</td>
					<td style="width:175px">
						<input class="easyui-validatebox" name="reportsName" style="width:168px;" minlength="1" maxlength="33" data-options="required:true,validType:'length[1,33]'" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;"><font color="red">*</font>选择文件:</td>
					<td style="width:175px">
						<input type="text" class="file" id="purTxt" style="width:0px;"/>
						<input class="input_file" size="30" type="file" onchange="purTxt.value=this.value" id="upload" name="upload" style="width:175px;" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</#compress>