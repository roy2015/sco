var accIntenWarnRecGrid=null;
var firstIn = true;
$(document).ready(function(){
	$('#radioWarn').click();
	accIntenWarnRecGrid=utils.grid($('#accIntenWarnRec_grid'));
	accIntenWarnRecGrid.registerExtFilters('supplierCode', 'supplierName', 'merchandiseCode', 'merchandiseName', 
			'fineType','accessoryName', 'materialBigTypeCode', 'materialSmallTypeCode', 'materialCode', 
			'webName', 'region');
	accIntenWarnRecGrid.initFilters({
		onBeforeLoad:function(obj) {
			if (firstIn) {
				obj.firstIn = firstIn;
				return true;
			}
		}
	});
	
});

var accIntenWarnRecFn = {
	
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
	
		accIntenWarnRecFn.setWebName();
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
		if (!val || val == 0) {
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
		for (var i in accIntenWarnRecFn.selectRecord) {
			var dateId = accIntenWarnRecFn.selectRecord[i];
			if (id == dateId) {
				$('#' + dateId).css('visibility', 'visible');
				accIntenWarnRecFn.record = dateId;
			} else {
				$('#' + dateId).css('visibility', 'hidden');
			}
		}
	},
	
	//搜索商品原料
	searchMerWarnRec:function() {
		if (!accIntenWarnRecFn.record) {
			$.messager.alert('提示', '请选择需查询的记录范围');
			return;
		}
		firstIn = false;
		
		var param = accIntenWarnRecGrid.getFilterValue();
		param.start = $('#min' + accIntenWarnRecFn.record + 'Year').datebox('getValue');
		param.end = $('#max' + accIntenWarnRecFn.record + 'Year').datebox('getValue');
		param.recordType = accIntenWarnRecFn.record;
		$('#accIntenWarnRec_grid').datagrid('load', param);
	},
	
	//清空查询
	clearSearch:function() {
		$('#accIntenWarnRec_search').form('reset');
		clearComboboxOptions();
		accIntenWarnRecFn.showRecord('')
		accIntenWarnRecFn.record = null;
		$('#accIntenWarnRec_grid').datagrid('loadData', {total:0, rows:[]});
	}
	
};