/**
 * 商品分类联动-ComboBox控件，页面ID必须与此处ID一致
 */
$(document).ready(function() {
	// 中分类
	$("#centreTypeCode").combobox({
		onChange : function() {
			merchandiseTypeFn.reloadData("centreTypeCode", "smallTypeCode", "SmallType");
		}
	});
	// 中分类-ELSE
	$("#centreTypeCodeElse").combobox({
		onChange : function() {
			merchandiseTypeFn.reloadData("centreTypeCodeElse", "smallTypeCode", "SmallTypeElse");
		}
	});
	// 小分类
	$("#smallTypeCode").combobox({
		onChange : function() {
			merchandiseTypeFn.reloadData("smallTypeCode", "detailTypeCode", "DetailType");
		}
	});
	// 明细类
	$("#detailTypeCode").combobox({
		onChange : function() {
			merchandiseTypeFn.reloadData("detailTypeCode", "fineTypeCode", "FineType");
		}
	});
});

var merchandiseTypeFn = {
	// 重新加载
	reloadData : function(thisId, targetId, method) {
		merchandiseTypeFn.clearSelectedData(targetId);
		// 当前框的值
		var value = $("#" + thisId).combobox("getValue");
		if (!value) {
			value = 'null';
		}
		$("#" + targetId).combobox('reload', "masterDataType_list" + method + "_5.html?" + thisId + "=" + value);
	},

	// 清空下一个连动框已选择的值
	clearSelectedData : function(targetId) {
		$("#" + targetId).combobox("setValue", "");
		$("#" + targetId).combobox("clear");
	}
};