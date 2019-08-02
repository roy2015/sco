/**
 * 原料属性-ComboBox控件，页面ID必须与此处ID一致
 */
$(document).ready(function() {
	// 原料大类绑定
	$("#materialBigTypeCode").combobox({
		onChange : function() {
			materialPropertiesFn.reloadData("materialBigTypeCode", "materialSmallTypeCode", "MaterialSmallType");
		}
	});

	// 原料小类绑定
	$("#materialSmallTypeCode").combobox({
		onChange : function() {
			materialPropertiesFn.reloadData("materialSmallTypeCode", "websiteMaterialName", "WebsiteMaterialName");
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
		$("#" + targetId).combobox('reload', "materialProperties_list" + method + "_5.html?" + thisId + "=" + value);
	},

	// 清空下一个连动框已选择的值
	clearSelectedData : function(targetId) {
		$("#" + targetId).combobox("setValue", "");
		$("#" + targetId).combobox("clear");
	}
};