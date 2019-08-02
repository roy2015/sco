var existsMaterialGrid=null;//已存在数据datagrid
var clearSearch = false;//清空查询
var firstIn = true;
$(document).ready(function(){
	//已存在数据datagrid
	existsMaterialGrid = utils.grid($('#existsMaterial_grid'));
	existsMaterialGrid.registerExtFilters('materialBigTypeCode', 'materialSmallTypeCode', 'websiteMaterialName',
			'websiteName', 'priceRegion', 'createby', 'minPriceDate', 'maxPriceDate');
	existsMaterialGrid.initFilters({
		onClickRow:webMaterialFn.setButtonState, //设置按钮状态
		onBeforeLoad:function(obj) {
			webMaterialFn.getParam(obj);//不同的datagrid排序问题
			//清空查询
			if(clearSearch) {
				return true;
			}  else if(firstIn) {//第一次进入
				firstIn = false;
				return false;
			} else if($('#showData').val() == '1'){//显示已存在的网站数据时
				var length = $.param(obj).split("&").length;
				//判断参数个数
				if (length < 3) {
					if(!firstIn) $.messager.alert('提示', '请输入至少一项查询条件');
					return false;
				}
				if(length == 4 && $.param(obj).indexOf('order') > -1) {
					var param = existsMaterialGrid.getFilterValue();
					if($.param(param).length > 0){
						return true;
					}
					$.messager.alert('提示', '请输入至少一项查询条件');
					return false;
				}
			} else {//显示不存在的网站数据时
				return true;
			}
		},
		onLoadSuccess:function(){
			$('#existsMaterial_grid').datagrid('clearSelections');
			webMaterialFn.setButtonState();
		},
		onClickRow : webMaterialFn.setButtonState, //设置按钮状态
		onCheck : webMaterialFn.setButtonState,
		onUncheck : webMaterialFn.setButtonState,
		onCheckAll : webMaterialFn.setButtonState,
		onUncheckAll : webMaterialFn.setButtonState
	});
	
});

var webMaterialFn = {
	
	//显示已有data时
	showExistsData:function() {
		$('#showData').val('1');
		$('td[id=cd]').css('display', '');
		$("a[id='remove']").linkbutton('enable');
		clearSearch = true;
		$('#existsMaterial_grid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
		
		$('#existsMaterial_grid').datagrid({
			url : 'websiteMaterial_listExistsWebsiteMaterial_2.html',
			queryParams : existsMaterialGrid.getFilterValue()
		});
		
		$('#existsMaterial_grid').datagrid('showColumn', 'priceDate');
		$('#existsMaterial_grid').datagrid('showColumn', 'price');
		$('#existsMaterial_grid').datagrid('showColumn', 'createby');
		$('#existsMaterial_grid').datagrid('showColumn', 'created');
		$('#existsMaterial_grid').datagrid('hideColumn', 'month');
	},

	//替换排序
	getParam:function(obj) {
		var val = $('#showData').val();
		if (val == '1') {
			if (obj.sort == 'month') {
				obj.sort = '';
			}
		} else if (val == '2') {
			if (obj.sort == 'priceDate') {
				obj.sort = '';
			}
		}
	},
	
	//显示缺少data时
	showNotExistsData:function() {
		$('#showData').val('2');
		$('td[id=cd]').css('display', 'none');
		$("a[id='remove']").linkbutton('disable');
		$('#existsMaterial_grid').datagrid({
			url : 'websiteMaterial_listNotExistsWebsiteMaterial_2.html',
			queryParams : existsMaterialGrid.getFilterValue()
		});
		$('#existsMaterial_grid').datagrid('hideColumn', 'priceDate');
		$('#existsMaterial_grid').datagrid('hideColumn', 'price');
		$('#existsMaterial_grid').datagrid('hideColumn', 'createby');
		$('#existsMaterial_grid').datagrid('hideColumn', 'created');
		$('#existsMaterial_grid').datagrid('showColumn', 'month');
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
	add:function() {
		var rows = $('#existsMaterial_grid').datagrid('getSelections');
		if(rows.length != 1) {
			$.messager.alert("提示", "请只选择一条记录");
			return;
		}
		var title = '新增原料价格数据';
		var href = 'websiteMaterial_showAddWebsiteMaterial_1.html?materialCode='+rows[0].materialCode
			+"&priceRegionCode="+rows[0].priceRegionCode;
		var dlg=utils.showDlg({
			title:title,href:href,width:515,height:200,
			buttons:[{text:'提交',handler:function(){webMaterialFn.submitForm(dlg);},iconCls:'save'},
			        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'close'}
			],
			onLoad:function() {
				$('#webMaterial_form').form('validate');
			}
		});
	},
	
	//提交新增或修改表单
	submitForm:function(dlg){
		var url = 'websiteMaterial_insertWebMaterialPrice_2.html';
		utils.form("webMaterial_form").submit(url,null,function(){
			dlg.dialog('close');
			existsMaterialGrid.refresh();
		});
	},
	
	//删除原料 
	remove:function(){
		var rows = $('#existsMaterial_grid').datagrid('getSelections');
		if(rows.length < 1) {
			$.messager.alert("提示", "请勾选至少一条记录");
			return;
		}
		var ids = '';
		for (var i in rows) {
			ids += "'"+rows[i].id+"',"; 
		}
		ids = ids.substring(0, ids.length - 1);
		utils.confirm('操作确认', '确认删除原料 ?', function(){
			utils.post('websiteMaterial_deleteWebsiteMaterialPrice_2.html', {ids : ids},function(){
				existsMaterialGrid.refresh();
			});
		});
	},
	
	//清除查询
	clearFilter:function(){
		clearSearch = true;
		$('#webMat_search').form('reset');
		clearComboboxOptions('websiteMaterialName');
		$('.datagrid-sort-desc,.datagrid-sort-asc')
			.removeClass('datagrid-sort-desc datagrid-sort-asc');
		$('#existsMaterial_grid').datagrid('loadData',{total:0,rows:[]});
		$('#existsMaterial_grid').datagrid('clearSelections');
		clearSearch = false;
	},
	
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#existsMaterial_grid').datagrid("getSelections");
		var flag = $('#showData').val() == '1';
		if (rows.length == 1) {//单选
			$("a[id='add']").linkbutton("enable");
			if(flag)$("a[id='remove']").linkbutton("enable");
		} else if (rows.length > 1) {//多选
			$("a[id='add']").linkbutton("disable");
			if(flag)$("a[id='remove']").linkbutton("enable");
		} else {  //未选中
			$("a[id='add']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
		}
	}
    
};