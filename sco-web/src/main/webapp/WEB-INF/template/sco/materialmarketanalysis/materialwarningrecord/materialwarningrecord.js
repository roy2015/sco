var materialWarnRecGrid = null;
var firstIn = true;
$(document).ready(function(){
	$("#materialBigTypeCode").combobox({
		onSelect : function() {
			matWarnRecFn.bigTypeChange();
		}
	});
	$("#materialCode").combobox({
		onChange : function() {
			matWarnRecFn.materialNameChange();
		}
	});
	
	materialWarnRecGrid = utils.grid($('#materialWarnRec_grid'));
	materialWarnRecGrid.registerExtFilters('materialBigTypeCode', 'materialSmallTypeCode', 'materialCode', 'webName',
			'region', 'minDate', 'maxDate', 'warnType');
	materialWarnRecGrid.initFilters({
		onBeforeLoad:function(obj) {
			obj.firstIn = firstIn;
		}
	});
	
});

var matWarnRecFn = {
	
	//大类变化
	bigTypeChange:function() {
		$("#materialCode").combobox("setValue", "");
		$("#materialCode").combobox("clear");
		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
		if (!materialBigTypeCode) {
			materialBigTypeCode = 'null';
		}
		$("#materialCode").combobox('reload', "websiteMaterial_listMaterialNameOption_5.html?" +
				"materialBigTypeCode=" + materialBigTypeCode);
	},
	
	//原料名称变动
	materialNameChange:function() {
		$("#region").combobox("setValue", "");
		$("#region").combobox("clear");
//		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
//		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		var materialCode = $("#materialCode").combobox("getValue");
		if (!materialCode) {
//			materialBigTypeCode = 'null';
//			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$("#region").combobox('reload', "websiteMaterial_listMaterialRegionOption_5.html?" 
				+ "materialCode=" + materialCode);
	
		matWarnRecFn.setWebName();
	},
	
	//设置网站名称
	setWebName:function(){
		$("#webName").combobox("setValue", "");
		$("#webName").combobox("clear");
//		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
//		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		var materialCode = $("#materialCode").combobox("getValue");
		if (!materialCode) {
//			materialBigTypeCode = 'null';
//			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$("#webName").combobox('reload', "websiteMaterial_listMaterialWebNameOption_5.html?" 
				+ "materialCode=" + materialCode);
	},
	
	//查看历史价格
	searchMatWarnRec:function() {
		firstIn = false;//第一次进入页面查询最近3个月的情况
		var param = materialWarnRecGrid.getFilterValue();
		$('#materialWarnRec_grid').datagrid('load', param);
	},
	
	//显示关联的商品
	showMer:function(materialCode, region) {
		showLoading();
		$("#merList").show();
		$.post("materialWarnRec_listMerchandise_1.html", {materialCode : materialCode, region : region}, function(data) {
			$("#merList").html(data);
			$('#closeBtn').linkbutton({ 
				plain : 'true',
			    iconCls: 'close'
			});
			$('#closeBtn').bind('click', function(){   
		        $("#merList").hide();  
		    });   
			$('.msg_bg').remove();
		}, "html");
	},
	
	//formatter关联SKU数
	formatterSku:function(val, obj) {
		if (val <= 0) return val;
		return "<a href='javascript:void(0);' onclick='matWarnRecFn.showMer("+ obj.materialCode +","+ obj.region +")'>" + val + "</a>";
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
	
	//清空查询
	clearSearch:function() {
		$("#materialWarnRec_search").form('reset');
		clearComboboxOptions('materialCode', 'webName', 'region');
		$("#merList").hide();
		$('#materialWarnRec_grid').datagrid('loadData', {total:0, rows:[]});
	}
	
};