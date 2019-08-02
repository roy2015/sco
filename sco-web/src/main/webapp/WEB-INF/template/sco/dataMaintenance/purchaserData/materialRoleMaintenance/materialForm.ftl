<#compress>
<script type="text/javascript">
	$(document).ready(function() {
		// 原料大类绑定
		$("#BigTypeCode").combobox({
			onChange : function() {
				materialPropertiesFn.reloadData("BigTypeCode", "SmallTypeCode", "MaterialSmallType");
			}
		});
	});

	var materialPropertiesFn = {
		// 重新加载
		reloadData : function(thisId, targetId, method) {
			materialPropertiesFn.clearSelectedData(targetId);
			// 当前框的值
			var value = $("#" + thisId).combobox("getValue");
			if (!value) {
				value = 'null';
			}
			$("#" + targetId).combobox('reload', "materialProperties_list" + method + "_5.html?material" + thisId + "=" + value);
		},
		// 清空下一个连动框已选择的值
		clearSelectedData : function(targetId) {
			$("#" + targetId).combobox("setValue", "");
			$("#" + targetId).combobox("clear");
		}
	};
</script>
<div style="padding: 5px; overflow: hidden;">
	<form id="material_form" method="post">
		<table class="tableForm">
			<tr>
				<td style="text-align: right; width: 160px"><label style="color: red;" >*</label>原料大类：</td>
				<td><input class="easyui-combobox filterSelect" name="materialBigTypeCode" id="BigTypeCode" style="width: 124px;"
					data-options="
					valueField:'id',
					textField:'text',
					editable:false,
					url:'materialProperties_listMaterialBigType_5.html'
				    
			    " /></td>
			</tr>
			<tr>
				<td style="text-align: right; width: 170px"><label style="color: red;" >*</label>原料小类：</td>
				<td><input class="easyui-combobox filterSelect" name="materialSmallTypeCode" id="SmallTypeCode" style="width: 124px;"
					data-options="
					valueField:'id',
					textField:'text',
					editable:false 
			    " /></td>
			</tr>
			<tr>
				<th align="right" style="width: 55px;"><label style="color: red;" >*</label>原料名称：</th>
				<td colspan=""><input name="materialName" class="easyui-validatebox"style="width: 120px;" data-options="required:false,validType:'length[1,30]'" /></td>				
			</tr>
			<tr>
				<th align="right" style="width: 55px;"><label style="color: red;" >*</label>价格地区：</th>
				<td colspan=""><input name="materialRegionName" class="easyui-validatebox" data-options="required:false,validType:'length[1,30]'" style="width:120px;"/></td>
			</tr>
			<tr>
				<td style="text-align: right; width: 100px"><label style="color: red;" >*</label>公示网站名称：</td>
				<td colspan="3"><input class="easyui-combobox filterSelect" name="websiteCode" id="websiteName" style="width: 124px;"
					data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'websiteNameMaintenance_listWebsiteNameMaintenanceInfo_5.html'
					    " /></td>
			</tr>
			<tr>
				<th align="right" style="width: 80px;"><label style="color: red;" >*</label>公示网站地址：htpp://</th>
				<td colspan="3"><input name="websiteUrl"style="width:120px;" class="easyui-validatebox" data-options="required:false,validType:'length[1,200]'" /></td>
			</tr>
		</table>
	</form>
</div>
</#compress>
