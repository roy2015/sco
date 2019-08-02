<#compress>
<div style="padding: 5px; overflow: hidden;">
	<form id="website_form" method="post">
		<table class="tableForm">
			<input name="websiteCode" id="websiteCode" type="hidden" readonly="readonly" value="${website.websiteCode}" />
			<tr>
				<th align="right" style="width: 80px;"><label style="color:red;">*</label><span class="txtCenter">请填入新公示网站名称：</span></th>
				<td><input name="websiteName" value="${website.websiteName}" style="120px;" data-options="required:false,validType:'length[0,100]'" /></td>
			</tr>
		</table>
	</form>
</div>
</#compress>
