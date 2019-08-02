var websiteGridUtil=null;
$(document).ready(function(){
	websiteGridUtil=utils.grid($('#website_grid'));
	$("a[id='remove']").linkbutton("disable");
	websiteGridUtil.registerExtFilters("websiteCode", "materialName", "priceRegion","updateby");
	websiteGridUtil.initFilters({
		noParamCanSort:true,
		onClickRow : websiteFn.setButtonState, // 设置按钮状态
		onCheck : websiteFn.setButtonState, // 设置按钮状态
		onUncheck : websiteFn.setButtonState, // 设置按钮状态
		onSelectAll : websiteFn.setButtonState, // 设置按钮状态
		onUnselectAll : websiteFn.setButtonState, // 设置按钮状态
		onLoadSuccess:function(){
			$('#website_grid').datagrid("clearSelections");
			websiteFn.setButtonState();
		}
	});
});
var websiteFn = {
	//显示添加或修改窗体
	showForm:function(isEdit){
		var title='添加公示网站名称';
		var href='websiteNameMaintenance_showInsertWebsiteNameMaintenanceForm_1.html';
		var dlg=utils.showDlg({
			title:title,href:href,width:300,
			buttons:[{text:'保存',handler:function(){websiteFn.submitForm(dlg);},iconCls:'save'},
			        {text:'取消',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
		$(".window-shadow").remove();
	},
	//添加公示网站名称维护
	showInsert:function() {
		websiteFn.showForm(false);
	},
	//提交新增或修改表单
	submitForm:function(dlg){
		var url = 'websiteNameMaintenance_insertWebsiteNameMaintenance_2.html';
		if ($.trim($("input[name='websiteName'").val()) != "") {
			utils.form("website_form").submit(url, null, function() {
				dlg.dialog('close');
				$("#websiteCode").combobox("reload");
				websiteGridUtil.refresh();
			});
		} else {
			$.messager.alert('操作失败', "必填信息为空");
		}
	},
	//格式化网站地址
	formattUrl:function(value,row){
		 return "<a href="+"'http://"+value +"'"+"target='_Blank' title="+value+">" + value + "</a>";
	},
	//删除公示网站名称维护
	remove:function(){
		var record=websiteGridUtil.getSelectedIdArr('websiteCode');
		if(record==null)return;
		utils.confirm("操作确认","确认删除公示网站名称维护?",function(){
			utils.post("websiteNameMaintenance_deleteWebsiteNameMaintenance_2.html?websiteCode="+record,null,function(){
				websiteGridUtil.refresh();
			});
		});
		$(".window-shadow").remove();
	},
	//查询
	search:function(){
		var url = "websiteNameMaintenance_listWebsiteNameMaintenance_2.html";
		var param = websiteGridUtil.getFilterValue();
		$('#website_grid').datagrid({
			'url' : url,
			queryParams : param
		});
		$('#website_grid').datagrid("load",param);
	},
	//清除查询
	clearFilter:function(){
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		$("#websiteCode").combobox('setValues', '');
		$("input:text").val("");
		$('#website_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
	},
	//刷新
	refresh:function(){
    	$('#website_grid').treegrid('reload');
    },
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#website_grid').datagrid("getSelections");
		if(rows.length>=1){
			$("a[id='remove']").linkbutton("enable");
		}else{
			$("a[id='remove']").linkbutton("disable");
		}	
	}
};