<#compress>
<script type="text/javascript">
	/**
	 * 商品分类联动-ComboBox控件，页面ID必须与此处ID一致
	 */
	$(document).ready(function() {
		// 中分类
		$("#centreType").combobox({
			onChange : function() {
				merchandiseTypesFn.reloadData("centreType", "smallType", "SmallType");
			}
		});
		// 小分类
		$("#smallType").combobox({
			onChange : function() {
				merchandiseTypesFn.reloadData("smallType", "detailType", "DetailType");
			}
		});
	});

	var merchandiseTypesFn = {
		// 重新加载
		reloadData : function(thisId, targetId, method) {
			merchandiseTypeFn.clearSelectedData(targetId);
			// 当前框的值
			var value = $("#" + thisId).combobox("getValue");
			if (!value) {
				value = 'null';
			}
			$("#" + targetId).combobox('reload', "masterDataType_list" + method + "_5.html?" + thisId + "Code=" + value);
		},

		// 清空下一个连动框已选择的值
		clearSelectedData : function(targetId) {
			$("#" + targetId).combobox("setValue", "");
			$("#" + targetId).combobox("clear");
		}
	};
</script>
<div style="padding: 5px; overflow: hidden;">
	<form id="merchandiseFineType_form" method="post">
		<table class="tableForm">
			<tr>
				<td style="text-align: right; width: 73px"><label style="color: red;" >*</label>中分类:</td>
				<td><input class="easyui-combobox filterSelect" filterName="centreType" id="centreType" style="width: 124px;"
					data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " /></td>
					    </tr>
					    <tr>
				<td style="text-align: right; width: 73px"><label style="color: red;" >*</label>小分类:</td>
				<td><input class="easyui-combobox filterSelect" id="smallType" filterName="smallType" style="width: 124px;"
					data-options="
								valueField:'id',
								textField:'text',
								editable:false" /></td>
								</tr>
								<tr>
				<td style="text-align: right; width: 73px"><label style="color: red;" >*</label>明细类:</td>
				<td><input class="easyui-combobox filterSelect" id="detailType" name="detailTypeCode" filterName="detailType" style="width: 124px;"
					data-options="
									valueField:'id',
									textField:'text',
									editable:false" /></td>
			</tr>
			<tr>
				<th align="right" style="width: 80px;"><label style="color: red;" >*</label>请填入细分类名称: </th>
				<td><input name="fineTypeName" class="easyui-validatebox" style="width:120px;" data-options="required:false,validType:'length[1,33]'" /></td>
			</tr>
		</table>
	</form>
</div>
</#compress>
