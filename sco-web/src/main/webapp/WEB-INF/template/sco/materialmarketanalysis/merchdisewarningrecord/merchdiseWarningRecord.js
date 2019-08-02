var merWarnRecGrid=null;
var firstIn = true;
$(document).ready(function(){
	
	merWarnRecGrid=utils.grid($('#merWarnRec_grid'));
	merWarnRecGrid.registerExtFilters('supplierCode', 'supplierName', 'merchandiseCode', 'merchandiseName', 
			'dxRoleCode', 'dlRoleCode', 'centreTypeCode', 'smallTypeCode', 'detailTypeCode', 'fineTypeCode', 
			'materialType', 'ingredientCodeName', 'materialBigTypeCode', 'materialSmallTypeCode', 'materialCode', 
			'webName', 'priceRegion');
	merWarnRecGrid.initFilters({
		onBeforeLoad:function(obj) {
			if (firstIn) {
				obj.firstIn = firstIn;
				return true;
			}
		}
	});
	
});

var merWarnRecFn = {
	
	//原料名称变动
	mNameChange:function() {
		$("#region").combobox("setValue", "");
		$("#region").combobox("clear");
		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		var materialCode = $("#materialCode").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode || !materialCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$("#region").combobox('reload', "websiteMaterial_listMaterialRegionOption_5.html?" 
				+ "materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode
				+ "&materialCode=" + materialCode);
	
		merWarnRecFn.setWebName();
	},
	
	//设置网站名称
	setWebName:function(){
		$("#webName").combobox("setValue", "");
		$("#webName").combobox("clear");
		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		var materialCode = $("#materialCode").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode || !materialCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$("#webName").combobox('reload', "websiteMaterial_listMaterialWebNameOption_5.html?" 
				+ "materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode
				+ "&materialCode=" + materialCode);
	
	},
	
	formateDateToMonth:function(obj, date) {
//		$(obj).datebox('setValue', '6/1/2016');
	},
	
	//百分比格式
	formatPercent:function(val) {
		if (val == null || val == "") {
			return ;
		} else if (val == 0) {
			return 0;
		} else {
			val = (val).toFixed(2);
			return new Number(val)+'%';
		}
	},
	
	selectRecord : new Array('warn', 'unWarn', 'allWarn'),
	record : null,
	//选择需查看的记录
	showRecord:function(id) {
		for (var i in merWarnRecFn.selectRecord) {
			var dateId = merWarnRecFn.selectRecord[i];
			if (id == dateId) {
				$('#' + dateId).css('visibility', 'visible');
				merWarnRecFn.record = dateId;
			} else {
				$('#' + dateId).css('visibility', 'hidden');
			}
		}
	},
	
	//搜索商品原料
	searchMerWarnRec:function() {
		if (!merWarnRecFn.record) {
			$.messager.alert('提示', '请选择需查询的记录范围');
			return;
		}
		firstIn = false;
		var param = merWarnRecGrid.getFilterValue();
		param.start = $('#min' + merWarnRecFn.record + 'Year').datebox('getValue');
		param.end = $('#max' + merWarnRecFn.record + 'Year').datebox('getValue');
		param.recordType = merWarnRecFn.record;
		$('#merWarnRec_grid').datagrid('load', param);
	},
	
	//清空查询
	clearSearch:function() {
		$('#merWarnRec_search').form('reset');
		clearComboboxOptions();
		merWarnRecFn.showRecord('')
		merWarnRecFn.record = null;
		$('#merWarnRec_grid').datagrid('loadData', {total:0, rows:[]});
	}
	
};