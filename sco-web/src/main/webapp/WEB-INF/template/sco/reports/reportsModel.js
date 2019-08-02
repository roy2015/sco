var reportsGridUtil=null;
var firstIn = true;// 是否第一次进入页面
var clearSearch = false;// 清空查询
$(document).ready(function(){
	reportsGridUtil=utils.grid($('#reports_grid'));
	reportsGridUtil.registerExtFilters("reportsTypeName", "reportsName", "oaStatus", "oaNo", 
			"minUpdatedDate","maxUpdatedDate");
	reportsGridUtil.initFilters({
		noParamCanSort:true,
		hasDataCanSort:true,
		onBeforeLoad:function(obj) {
			// 清空查询
			if(clearSearch) {
				return true;
			}
			var length = $.param(obj).split("&").length;
			// 判断参数个数
			if (firstIn) {
				firstIn = false;
				return false;
			}
			
		},
		
		onLoadSuccess:function(){
			firstIn = false;
			$('#reports_grid').datagrid("clearSelections");
			reportsFn.setButtonState();
		}
	});
});
var reportsFn = {
	search : function() {
		var param = reportsGridUtil.getFilterValue();
		$('#reports_grid').datagrid('load', param);
		},
	//显示添加或修改窗体
	showForm:function(isEdit){
		var title='${action.getText("common.title.add",action.getText("reports.reports.title.insert"))}';
		var href='reports_showInsertReportsForm_1.html';
		if(isEdit){//显示修改窗体
			var record=reportsGridUtil.getSelectedRecord();
			if(record==null)return;
			title='${action.getText("common.title.edit",action.getText("reports.reports.title.update"))}';
			href='reports_showUpdateReportsForm_1.html?reportsCode='+record.reportsCode;
		}
		var dlg=utils.showDlg({
			title:title,href:href,width:600,height:350,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){reportsFn.submitForm(dlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	//添加我的报表
	showInsert:function() {
		reportsFn.showForm(false);
	},
	/*//修改我的报表
	showEdit:function(){
		reportsFn.showForm(true);
	},*/
	//提交新增或修改表单
	submitForm:function(dlg){
		var reportsCode = $("input#reportsCode").val();
		var url = 'reports_insertReports_2.html';
		if(reportsCode){
			url = 'reports_updateReports_2.html';
		}
		utils.form("reports_form").submit(url,null,function(){
			dlg.dialog('close');
			reportsGridUtil.refresh();
		});
	},
	//删除我的报表
	remove:function(){
		var reportsCode=[];
		var record = $('#reports_grid').datagrid('getChecked');
		if(record.length == 0){
			$.messager.alert('提示','<center>请至少选择一条记录！</center>'); 
		}else{
		utils.confirm("操作确认","确认删除我的报表?",function(){
			$.each(record, function(index, item) {
				reportsCode.push(item.reportsCode);
			});
			utils.post("reports_deleteReports_2.html?reportsCode="+reportsCode,null,function(){
				reportsGridUtil.refresh();
			});
		});
		}
	},
	//清除查询
	clearFilter:function(){
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#reports_search').form('reset');
		clearComboboxOptions();
	//	accessoryIntentionGridUtil.clearFilter();
		$('#reports_grid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
	},
	refresh:function(){
    	$('#reports_grid').treegrid('reload');
    },
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#reports_grid').datagrid("getSelections");
		if(rows.length===1){
			$("a[id='showEdit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
		}else{
			$("a[id='showEdit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
		}	
	},
	
	//导出到Excel
	export2Excel:function(){
		url="reports_exportReportsToExcel_2.html?"+$.param(reportsGridUtil.getFilterValue());
		window.location=url;
	},
	//从EXCEL导入
	importFromExcel:function(){
		utils.submitFormWidthFile({
			url:'reports_importReports_2.html',
			fileElementId:'reportsFile',
			success:function(json){
				if(json.success){
					$.messager.show({title:'操作成功', msg:json.msg, showType:'show'});
				}else{
					$.messager.alert('操作失败', json.msg);
				}
			}
		});
	},
	//下载模板
	downloadTpl:function(){
		window.location="reports_downloadReportsTpl_2.html";
	},
	showUrl:function(value,rowData,nfsFilePath){
		return "<a href='" + encodeURI(nfsFilePath + rowData.reportsUrl) + "' target='_blank'>" + value + "</a>";
	}
};