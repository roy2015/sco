var releAccAndWebGrid=null;
var clearSearch = false;//清空查询
var firstIn = true;
$(document).ready(function(){
	releAccAndWebGrid=utils.grid($('#releAccAndWeb_grid'));
	releAccAndWebGrid.registerExtFilters("supplierCode", "supplierName", "intentionCode", "intentionName", 
				"fineTypeCodes", "accessoryName", "ifRelevanced");
	releAccAndWebGrid.initFilters({
		onBeforeLoad:function(obj) {
			//清空查询
			if(clearSearch) {
				return true;
			}
			var length = $.param(obj).split("&").length;
			//判断参数个数
			if (length < 3) {
				if(!firstIn) $.messager.alert("提示", "请输入至少一项查询条件");
				firstIn = false;
				return false;
			}
			if(length == 4 && $.param(obj).indexOf("order") > -1) {
				var param = releAccAndWebGrid.getFilterValue();
				if($.param(param).length > 0){
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
		},
		
		onLoadSuccess:function(){
			$('#releAccAndWeb_grid').datagrid("clearSelections");
			releAccAndWebFn.setButtonState();
		},
		onClickRow:releAccAndWebFn.setButtonState, //设置按钮状态
		onCheck : releAccAndWebFn.setButtonState,
		onUncheck : releAccAndWebFn.setButtonState,
		onCheckAll : releAccAndWebFn.setButtonState,
		onUncheckAll : releAccAndWebFn.setButtonState
	});
});

var releAccAndWebFn = {
	
	//显示供应商编号
	formatterSupplierCode:function(value, row) {
		if (row.supplierCode){
			return row.supplierCode;
		} else {
			return row.intentionSupplierCode;
		}
	},
	
	//显示供应商名称
	formatterSupplierName:function(value, row) {
		if (row.supplierName){
			return row.supplierName;
		} else {
			return row.intentionSupplierName;
		}
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
	
	//搜索按钮功能
	search:function() {
		var param = releAccAndWebGrid.getFilterValue();
		$('#releAccAndWeb_grid').datagrid('load', param);
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
		var rows = $('#releAccAndWeb_grid').datagrid("getSelections");
		if(rows.length != 1) {
			$.messager.alert("提示", "请只选择一条记录");
			return;
		}
		var title='关联辅料与公示网站';
		var href = 'relevanceAccessoryAndWebsite_showInsertRelevanceAccessoryAndWebsiteForm_1.html';
		var dlg=utils.showDlg({
			title:title,href:href,width:550,height:230,
			buttons:[{text:'关联',handler:function(){releAccAndWebFn.submitForm(dlg);},iconCls:'save'},
			        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'close'}
			],
			onLoad:function() {
				$('#materialSmallTypeCode').combobox({
					onChange : function() {
						releAccAndWebFn.smallTypeChange();
					}
				});
				$('#mName').combobox({
					onChange : function() {
						releAccAndWebFn.mNameChange();
					}
				});
				$('#region').combobox({
					onChange : function() {
						releAccAndWebFn.regionChange();
					}
				});
				$('#webName').combobox({
					onChange : function() {
//						releAccAndWebFn.webNameChange();
					}
				});
				$('#webUrl').combobox({
					onChange : function() {
//						releAccAndWebFn.webNameChange();
					}
				});
				$('#releAccdWeb_form').form('validate');
			}
		});
	},

	//提交新增或修改表单
	submitForm:function(dlg){
		if(!$('#releAccdWeb_form').form('validate')) {
			return;
		} 
		var rows = $('#releAccAndWeb_grid').datagrid('getSelections');
		
		$('form[id=releAccdWeb_form] > input[id=accessoryCode]').val(rows[0].accessoryCode);
		$('form[id=releAccdWeb_form] > input[id=intentionCode]').val(rows[0].intentionCode);
		$('form[id=releAccdWeb_form] > input[id=intentionSupplierCode]').val(releAccAndWebFn.formatterSupplierCode(null, rows[0]));
		//$('input[id=supplierCode]').val(rows[0].supplierCode);
		
		var url = 'relevanceAccessoryAndWebsite_insertRelevanceAccessoryAndWebsite_2.html';
		utils.form('releAccdWeb_form').submit(url,null,function(){
			dlg.dialog('close');
			releAccAndWebGrid.refresh();
		});
	},
	
	//变化值小类
	smallTypeChange:function() {
		$('#mName').combobox('setValue', '');
		$('#mName').combobox('clear');
		var materialBigTypeCode = $('#materialBigTypeCode').combobox('getValue');
		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox('getValue');
		if (!materialBigTypeCode || !materialSmallTypeCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
		}
		$('#mName').combobox('reload', "websiteMaterial_listMaterialNameOption_5.html?" +
				"materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode);
	},
	
	//辅料名称变动
	mNameChange:function() {
		$('#region').combobox('setValue', '');
		$('#region').combobox('clear');
		var materialBigTypeCode = $('#materialBigTypeCode').combobox('getValue');
		var materialSmallTypeCode = $('#materialSmallTypeCode').combobox('getValue');
		var materialCode = $('#mName').combobox('getValue');
		if (!materialBigTypeCode || !materialSmallTypeCode || !materialCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$('#region').combobox('reload', "websiteMaterial_listMaterialRegionOption_5.html?" 
				+ "materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode
				+ "&materialCode=" + materialCode);
	},
	
	//设置网站名称、地址
	regionChange:function() {
		releAccAndWebFn.setWebName();
		releAccAndWebFn.setWebUrl();
	},
	
	//设置网站名称
	setWebName:function(){
		$('#webName').combobox('setValue', '');
		$('#webName').combobox('clear');
		var materialBigTypeCode = $('#materialBigTypeCode').combobox('getValue');
		var materialSmallTypeCode = $('#materialSmallTypeCode').combobox('getValue');
		var materialCode = $('#mName').combobox('getValue');
		var materialRegionCode = $("#region").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode || !materialCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$('#webName').combobox('reload', "websiteMaterial_listMaterialWebNameOption_5.html?" 
				+ "materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode
				+ "&materialCode=" + materialCode + "&materialRegionCode=" + materialRegionCode);
	
	},
	
	//设置网站地址
	setWebUrl:function(){
		$('#webUrl').combobox('setValue', '');
		$('#webUrl').combobox('clear');
		var materialBigTypeCode = $('#materialBigTypeCode').combobox('getValue');
		var materialSmallTypeCode = $('#materialSmallTypeCode').combobox('getValue');
		var materialCode = $('#mName').combobox('getValue');
		var materialRegionCode = $("#region").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode || !materialCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$('#webUrl').combobox('reload', "websiteMaterial_listMaterialWebUrlOption_5.html?" 
				+ "materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode
				+ "&materialCode=" + materialCode + "&materialRegionCode=" + materialRegionCode);
	
	},
	//删除辅料与网站关联关系
	remove:function(){
		var rows = $('#releAccAndWeb_grid').datagrid('getSelections');
		if(rows.length < 1) {
			$.messager.alert('提示', '请勾选至少一条记录');
			return;
		}
		var ids = '';
		for (var i in rows) {
			if(rows[i].id) {
				ids += "'"+rows[i].id+"',"; 
			}
		}
		if(ids.length < 1) {
			$.messager.alert('提示', '所选的数据均未与公示网站关联');
			return;
		}
		ids = ids.substring(0, ids.length - 1);
		utils.confirm('操作确认', '确认取消关联?',function(){
			utils.post('relevanceAccessoryAndWebsite_deleteRelevanceAccessoryAndWebsite_2.html',
					{ids:ids},function(){
						releAccAndWebGrid.refresh();
			});
		});
	},
	
	//导出到Excel
	exportToExcel:function() {
		var param = $.param(releAccAndWebGrid.getFilterValue());
		if(param.length < 1){
			$.messager.alert("提示", "请输入至少一项查询条件");
			return;
		}
		url="relevanceAccessoryAndWebsite_exportDataToExcel_6.html?" + param;
		window.location=url;
		$.messager.show({
			 title:'提示',
				msg:'数据导出中,请稍后...',
				timeout:4000,
				showType:'slide'
		});
	},
	
	//清除查询
	clearFilter:function(){
		clearSearch = true;
		//releMateAndWebGrid.clearFilter();//datagrid会重新查询
		$('#releAccWeb_search').form('reset');
		$('.datagrid-sort-desc,.datagrid-sort-asc')
			.removeClass('datagrid-sort-desc datagrid-sort-asc');
		$('#releAccAndWeb_grid').datagrid('loadData',{total:0,rows:[]});
		$('#releAccAndWeb_grid').datagrid('clearSelections');
		clearSearch = false;
	},
	
	refresh:function(){
    	$('#releAccAndWeb_grid').treegrid('reload');
    },
    
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#releAccAndWeb_grid').datagrid("getSelections");
		if (rows.length == 1) {
			$("a[id='link']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
		} else if (rows.length > 1) {
			$("a[id='link']").linkbutton("disable");
			$("a[id='remove']").linkbutton("enable");
		} else {
			$("a[id='link']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
		}	
	}
	
};