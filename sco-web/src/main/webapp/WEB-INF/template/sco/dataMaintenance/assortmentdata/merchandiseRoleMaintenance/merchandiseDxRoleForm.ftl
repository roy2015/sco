<#compress>
<script type="text/javascript">
	<#include "sco/common/masterDataType.js" />
</script>
<div style="padding: 5px; overflow: hidden;">
	<form id="merchandise_form" method="post">
		<label style="color: red">*</label><label>商品定性角色:</label>
		<input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode_xg" style="width: 124px;"
			data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'merchandiseRole_listQualitative_5.html'
					    " />
	</form>
</div>
</#compress>
