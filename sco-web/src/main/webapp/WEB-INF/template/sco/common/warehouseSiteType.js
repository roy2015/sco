/**
 * 仓位联动
 */
$(document).ready(function() {
	// 仓位
	$("#warehouseSiteCode").combobox({
		onChange : function() {
			warehouseSiteCodeFn.reloadData("warehouseSiteCode", "sellRegion", "WarehouseSite");
		}
	});
});

var warehouseSiteCodeFn = {
	// 重新加载
	reloadData : function(thisId, targetId, method) {
		warehouseSiteCodeFn.clearSelectedData(targetId);
		// 当前框的值
		var value = $("#" + thisId).combobox("getValue");
		if (!value) {
			value = 'null';
		}
		$("#" + targetId).combobox('reload', "merchandiseStockDetail_list" + method + "_5.html?" + thisId + "=" + value);
	},

	// 清空下一个连动框已选择的值
	clearSelectedData : function(targetId) {
		$("#" + targetId).combobox("setValue", "");
		$("#" + targetId).combobox("clear");
	}
};
