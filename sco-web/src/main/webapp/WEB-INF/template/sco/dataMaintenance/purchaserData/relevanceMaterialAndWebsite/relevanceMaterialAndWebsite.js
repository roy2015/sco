var releMateAndWebGrid=null;
var clearSearch = false;//清空查询
var firstIn = true;
var merchanseCodes = '${merchanseCodes}';
var supplierCodes = '${supplierCodes}';
var msStr = '${msStr}';
var oaType = '${oaType}';

$(document).ready(function(){
	releMateAndWebGrid=utils.grid($('#releMatelAndWeb_grid'));
	releMateAndWebGrid.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", 
				"dxRoleCode", "dlRoleCode", "centreTypeCode", "smallTypeCode", "detailTypeCode", "fineTypeCode",
				"materialType", "ingredientCodeName", "ifRelevanced", "materialName", "websiteCode", "priceRegion",
				"materialBigTypeName", "materialSmallTypeName", "createby");
	releMateAndWebGrid.initFilters({
		onBeforeLoad:function(obj) {
			//从商品OA界面跳转过来时
			obj.merchanseCodes = merchanseCodes;
			obj.supplierCodes = supplierCodes;
			obj.msStr = msStr;
			obj.oaType = oaType;
			if(merchanseCodes || supplierCodes) {
				firstIn = false;
				return true;
			}
			
			//清空查询
			if(clearSearch) return true;
			
			var length = $.param(obj).split("&").length;
			//判断参数个数
			if(length == 6 && !merchanseCodes && !supplierCodes && !msStr){
				if(!firstIn) {
					$.messager.alert("提示", "请输入至少一项查询条件");
				}
				firstIn = false;
				return false;
			}
			if(length == 8 && $.param(obj).indexOf("order") > -1 && !merchanseCodes && !supplierCodes ) {
				var param = releMateAndWebGrid.getFilterValue();
				if($.param(param).length > 0){
					firstIn = false;
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				firstIn = false;
				return false;
			}
		},
		
		onLoadSuccess:function(){
			$('#releMatelAndWeb_grid').datagrid("clearSelections");
			releMateAndWebFn.setButtonState();
		},
		onClickRow:releMateAndWebFn.setButtonState, //设置按钮状态
		onCheck : releMateAndWebFn.setButtonState,
		onUncheck : releMateAndWebFn.setButtonState,
		onCheckAll : releMateAndWebFn.setButtonState,
		onUncheckAll : releMateAndWebFn.setButtonState
	});
});

var releMateAndWebFn = {
	
	//格式化关联状态
	fomatLink:function(val) {
		if ("N" == val) return "未关联";
		if ("Y" == val) return "已关联";
		return "暂不关联";
	},
	
	//格式百分比(乘100)
	fomatPercent:function(val) {
		if (!val || val == 0) {
			return 0;
		} else {
			val = (val*100).toFixed(2);
			return new Number(val)+"%";
		}
	},
	
	//格式百分比(不乘100)
	fomatPercentWithoutMul:function(val) {
		if (val == null) {
			return;
		} else if (val == 0) {
			return 0;
		} else {
			return val.toFixed(2)+"%";
		}
	},
	
	//搜索按钮功能
	search:function() {
		var param = releMateAndWebGrid.getFilterValue();
		$('#releMatelAndWeb_grid').datagrid('load', param);
	},
	
	//点击网站地址打开界面
	openWebUrl:function(value, row, index) {
		if(!value) return value;
		var url = value;
		if(value.indexOf("http") != 0) {
			url = "http://"+value;
		} 
		return "<a href='"+ url +"' target='_blank'>" + value + "</a>";
	},
		
	//显示添加或修改窗体
	linkMaterialWebsite:function(){
		var rows = $('#releMatelAndWeb_grid').datagrid("getSelections");
		if(rows.length != 1) {
			$.messager.alert("提示", "请只选择一条记录");
			return;
		}
		var title='关联商品原料与公示网站';
		var href = 'relevanceMaterialAndWebsite_showInsertRelevanceMaterialAndWebsiteForm_1.html?';
		var param = 'merchandiseCode=' + rows[0].merchandiseCode + "&ingredientCode=" + rows[0].materialCode
			+ "&supplierCode=" + rows[0].supplierCode + "&materialCode=" + rows[0].materialCode ;
		var dlg=utils.showDlg({
			title:title,href:href+param,width:550,height:210,
			buttons:[{text:'关联',handler:function(){releMateAndWebFn.submitForm(dlg);},iconCls:'save'},
			        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'close'}
			],
			onLoad:function() {
				$("#materialSmallTypeCode").combobox({
					onChange : function() {
						releMateAndWebFn.smallTypeChange();
					}
				});
				$("#mName").combobox({
					onChange : function() {
						releMateAndWebFn.mNameChange();
					}
				});
				$("#region").combobox({
					onChange : function() {
						releMateAndWebFn.regionChange();
					}
				});
				$("#webName").combobox({
					onChange : function() {
//						releMateAndWebFn.webNameChange();
					}
				});
				$("#webUrl").combobox({
					onChange : function() {
//						releMateAndWebFn.webNameChange();
					}
				});
				
			}
		});
	},

	//提交新增或修改表单
	submitForm:function(dlg){
		if(!$("#releMateAndWeb_form").form("validate")) {
			return;
		}  
		var url = 'relevanceMaterialAndWebsite_insertRelevanceMaterialAndWebsite_2.html';
		utils.form("releMateAndWeb_form").submit(url,null,function(){
			dlg.dialog('close');
			releMateAndWebGrid.refresh();
		});
	},
	
	//变化值小类
	smallTypeChange:function() {
		$("#mName").combobox("setValue", "");
		$("#mName").combobox("clear");
		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
		}
		$("#mName").combobox('reload', "websiteMaterial_listMaterialNameOption_5.html?" +
				"materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode);
	},
	
	//原料名称变动
	mNameChange:function() {
		$("#region").combobox("setValue", "");
		$("#region").combobox("clear");
		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		var materialCode = $("#mName").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode || !materialCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$("#region").combobox('reload', "websiteMaterial_listMaterialRegionOption_5.html?" 
				+ "materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode
				+ "&materialCode=" + materialCode);
	},
	
	//设置网站名称、地址
	regionChange:function() {
		releMateAndWebFn.setWebName();
		releMateAndWebFn.setWebUrl();
	},
	
	//设置网站名称
	setWebName:function(){
		$("#webName").combobox("setValue", "");
		$("#webName").combobox("clear");
		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		var materialCode = $("#mName").combobox("getValue");
		var materialRegionCode = $("#region").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode || !materialCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$("#webName").combobox('reload', "websiteMaterial_listMaterialWebNameOption_5.html?" 
				+ "materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode
				+ "&materialCode=" + materialCode + "&materialRegionCode=" + materialRegionCode);
	
	},
	
	//设置网站地址
	setWebUrl:function(){
		$("#webUrl").combobox("setValue", "");
		$("#webUrl").combobox("clear");
		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		var materialCode = $("#mName").combobox("getValue");
		var materialRegionCode = $("#region").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode || !materialCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$("#webUrl").combobox('reload', "websiteMaterial_listMaterialWebUrlOption_5.html?" 
				+ "materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode
				+ "&materialCode=" + materialCode + "&materialRegionCode=" + materialRegionCode);
	
	},
	//删除原料与网站关联关系
	remove:function(){
		var rows = $('#releMatelAndWeb_grid').datagrid("getSelections");
		if(rows.length < 1) {
			$.messager.alert("提示", "请勾选至少一条记录");
			return;
		}
		var ids = '';
		for (var i in rows) {
			if(rows[i].id && rows[i].ifRelevanced == 'Y') {
				ids += "'"+rows[i].id+"',"; 
			} else if (rows[i].ifRelevanced != 'Y') {
				$.messager.alert('提示', '所选记录未关联公示网站，不可取消关联');
				return;
			}
		}
		if (ids.length < 1) {
			$.messager.alert('提示', '所选的数据均未与公示网站关联');
			return; 
		}
		ids = ids.substring(0, ids.length - 1);
		utils.confirm("操作确认", "确认取消关联?",function(){
			utils.post("relevanceMaterialAndWebsite_deleteRelevanceMaterialAndWebsite_2.html",
					{ids:ids},function(){
						releMateAndWebGrid.refresh();
			});
		});
	},
	
	//导出到Excel
	exportToExcel:function() {
		var obj = releMateAndWebGrid.getFilterValue();
		
		var param = $.param(obj);
		if( param.length < 1 && !merchanseCodes){
			$.messager.alert("提示", "请输入至少一项查询条件");
			return;
		}
		
		obj.merchanseCodes = merchanseCodes;
		obj.supplierCodes = supplierCodes;
		obj.msStr = msStr;
		obj.oaType = oaType;
		obj.removePage = "true";
		
		var param = $.param(obj);
		url="relevanceMaterialAndWebsite_exportDataToExcel_6.html?" + param;
		window.location = url;
		$.messager.show({
			 title:'提示',
				msg:'数据导出中,请稍后...',
				timeout:4000,
				showType:'slide'
		});
	},
	
	//暂不关联
	notLink:function() {
		var rows = $('#releMatelAndWeb_grid').datagrid("getSelections");
		if(rows.length < 1) {
			$.messager.alert("提示", "请至少勾选一条记录");
			return;
		}
		
		var inCode = ''; // 投料编号
		var matCode = ''; // 原料编号
		for (var i in rows) {
			if(rows[i].ifRelevanced != 'N') {
				$.messager.alert('提示', '所选商品原料已关联公示网站，不可设置为暂不关联');
				return;
			} else {
				inCode += rows[i].ingredientCode + ","; 
				matCode += rows[i].materialCode + ","; 
			}
		}
		if (inCode.length < 1) {
			return; 
		}
		utils.post("relevanceMaterialAndWebsite_notLinkMaterialAndWebsite_2.html",
			{
				inCode : inCode.substring(0, inCode.length - 1),
				matCode : matCode.substring(0, matCode.length - 1)
			},function(){
				releMateAndWebGrid.refresh();
			}
		);
	},
	
	//清除查询
	clearFilter:function(){
		clearSearch = true;
		$('#releMatWeb_search').form('reset');
		clearComboboxOptions();
		$('.datagrid-sort-desc,.datagrid-sort-asc')
			.removeClass('datagrid-sort-desc datagrid-sort-asc');
		$('#releMatelAndWeb_grid').datagrid('loadData',{total:0,rows:[]});
		$('#releMatelAndWeb_grid').datagrid('clearSelections');
		//若是从审批界面跳转过来,清空查询后宣誓所有数据
		msStr = '';
		merchanseCodes = '';
		supplierCodes = '';
		oaType = '';
		clearSearch = false;
	},
	
	refresh:function(){
    	$('#releMatelAndWeb_grid').treegrid('reload');
    },
    
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#releMatelAndWeb_grid').datagrid("getSelections");
		if (rows.length == 1) {
			$("a[id='link']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
			$("a[id='notLink']").linkbutton("enable");
		} else if (rows.length > 1) {
			$("a[id='link']").linkbutton("disable");
			$("a[id='remove']").linkbutton("enable");
			$("a[id='notLink']").linkbutton("enable");
		} else {
			$("a[id='link']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
			$("a[id='notLink']").linkbutton("disable");
		}	
	}
	
};