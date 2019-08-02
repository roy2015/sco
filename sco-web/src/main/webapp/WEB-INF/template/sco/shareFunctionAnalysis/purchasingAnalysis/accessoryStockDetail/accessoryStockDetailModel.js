var accessoryStockDetailGridUtil = null;
var firstIn = true;// 是否第一次进入页面
$(function() {
	accessoryStockDetailGridUtil = utils.grid($('#accessoryStockDetail_grid'));
	accessoryStockDetailGridUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName","fineTypeCode","warehouseCode","sellRegion","minDate","maxDate");
	accessoryStockDetailGridUtil.initFilters({
		onBeforeLoad : function(obj) {
			// 清空查询
			if (firstIn) {
				return false;
			}
		}
	});
});
var accessoryStockDetailFn = {
	search : function() {
		firstIn=false;
		var sn = $.trim($("#supplierName").val());
		var sc = $.trim($("#supplierCode").val());
		var mn = $.trim($("#merchandiseName").val());
		var mc = $.trim($("#merchandiseCode").val());
		if (sn == "") {
			$("#supplierName").val("");
		}
		if (sc == "") {
			$("#supplierCode").val("");
		}
		if (mn == "") {
			$("#merchandiseName").val("");
		}
		if (mc == "") {
			$("#merchandiseCode").val("");
		}
		var param = accessoryStockDetailGridUtil.getFilterValue();
		if (param.warehouseCode==null||param.sellRegion == null || param.minDate==null || param.maxDate== null) {
			$.messager.alert('操作失败', "必选项未选择");
			return;
		}
		$('#accessoryStockDetail_grid').datagrid('load', param);
	},
	searchMerchandise : function() {
		var row = $('#accessoryStockDetail_grid').datagrid("getSelections");
		if (row.length < 1) {
			$.messager.alert("提示", "请勾选至少一条辅料查询记录");
			return;
		}
		var  merchandiseAndSupplierCodes=[];
		$.each(row, function(index, item){
			//根据商品编号和供应商编号查询同比信息
			merchandiseAndSupplierCodes.push(item.merchandiseCode+":"+item.supplierCode);
		});
		var regionCode = $("#sellRegion").combobox("getValues");
		var warehouseCode = $("#warehouseCode").combobox("getValues");
		var minDate = $("#minDate").datebox('getValue');
		var maxDate = $("#maxDate").datebox("getValue");
		var type = $('input[name="type"]:checked');
		if(type[0].value=="month"){
			var dtArr = minDate.split("-");
			var myDate = new Date(dtArr[0], dtArr[1], dtArr[2]);
		    var year = myDate.getFullYear();
		    var month = myDate.getMonth();
		    if(month==0){
		    	year--;
		    	month=12;
		    }
		    if (month<10){
		        month = "0"+month;
		    }
		    minDate=year+"-"+month+"-"+"01";
		    var dtArr = maxDate.split("-");
			var myDate = new Date(dtArr[0], dtArr[1], dtArr[2]);
		    var year = myDate.getFullYear();
		    var month = myDate.getMonth();
		    if(month==0){
		    	year--;
		    	month=12;
		    }
		    if (month<10){
		        month = "0"+month;
		    }
		    maxDate=year+"-"+month+"-"+"01";
		}
		if (regionCode != "" && minDate != "") {
			var url = "accessoryStockDetail_showAccessoryStockDetailInfoGrid_1.html?merchandiseAndSupplierCodes=" + merchandiseAndSupplierCodes + 
						"&regionCode="+ regionCode + 
						"&warehouseCode="+warehouseCode+
						"&minDate=" + minDate + 
						"&maxDate=" + maxDate+
						"&searchType="+type[0].value;
			var tabName = '进货明细报表-辅料';
			if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
				parent.activeTab(tabName);
				parent.refreshTab(tabName);
				return;
			}
			parent.addTabByUrl(tabName, 'agent', url);
		} else {
			$.messager.alert("提示", "请选择必须搜索项");
		}

	},
	clear : function() {
		firstIn=true;
		$("#accessoryStockDetail_search").form('reset');
		$("#accessoryStockDetail_grid").datagrid('loadData', {
			total : 0,
			rows : []
		});
	}
};