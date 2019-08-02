var firstIn = true;//第一次进入页面
var clearSearch = false;//清空查询
var supplierEvaluateTemplateGridUtil=null;
$(document).ready(function(){
	$("a[id='work']").linkbutton("disable");
	supplierEvaluateTemplateGridUtil=utils.grid($('#supplierEvaluateTemplate_grid'));
	supplierEvaluateTemplateGridUtil.registerExtFilters('templateName','status','minCreated','maxCreated','minPublishDate','maxPublishDate');
	supplierEvaluateTemplateGridUtil.initFilters({
		onClickRow : supplierEvaluateTemplateFn.setButtonState, // 设置按钮状态
		onCheck : supplierEvaluateTemplateFn.setButtonState, // 设置按钮状态
		onUncheck : supplierEvaluateTemplateFn.setButtonState, // 设置按钮状态
		onSelectAll : supplierEvaluateTemplateFn.setButtonState, // 设置按钮状态
		onUnselectAll : supplierEvaluateTemplateFn.setButtonState, // 设置按钮状态
		onLoadSuccess:function(){
			supplierEvaluateTemplateFn.setButtonState();
		}
	});
});
var flag=true;
var supplierEvaluateTemplateFn = {
		
	//搜索模板
	search:function(){
		var param=supplierEvaluateTemplateGridUtil.getFilterValue();
		if($.param(param).split('&')==""){
			$.messager.alert('提示','请输入至少一项查询条件');
			return;
		}
		var url="supplierEvaluateTemplate_listSupplierEvaluateTemplate_2.html";
		$('#supplierEvaluateTemplate_grid').datagrid({
			'url' : url,
			queryParams : param
		});
	},
	//清空查询
	clearFilter:function(){
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		$("#signedQty_search").form("reset");
		$("#supplierEvaluateTemplate_grid").datagrid('loadData',{total:0,rows:[]});
	},
	//改变考评模板编号样式
	change:function(value, row, index) {
		return '<a href=javascript:void(0) onClick=supplierEvaluateTemplateFn.openSearch("'+encodeURIComponent(row.templateCode)+'")>'+ value +'</a><br>';
	},
	//打开一个新的页面
	openSearch:function(templateCode) {
		var url="supplierEvaluateTemplate_showSupplierEvaluateTemplateByTemplateCode_1.html?templateCode="+templateCode;
		parent.addTabByUrl('供应商考评模板', 'copy', url);//新打开一个选项卡，也就是新打开一个页面
	},
	//在一个新的页面中修改考评模板
	openUpdate:function(templateCode) {
		var url="supplierEvaluateTemplate_showUpdateSupplierEvaluateTemplateByTemplateCode_1.html?templateCode="+templateCode;
		parent.addTabByUrl('供应商考评模板', 'copy', url);//新打开一个选项卡，也就是新打开一个页面
	},
	//创建供应商考评表模板-商品
	merchandiseShowInsert:function() {
		var url="supplierEvaluateTemplate_showMerchandiseSupplierEvaluateTemplate_1.html";
		parent.addTabByUrl('供应商考评模板-商品', 'copy', url);
	},
	//创建供应商考评表模板-辅料
	accessoryShowInsert:function(){
		var url="supplierEvaluateTemplate_showAccessorySupplierEvaluateTemplate_1.html";
		parent.addTabByUrl('供应商考评模板-辅料', 'copy', url);
	},
	//发布考评表模板
	publish:function(){
		var list="";
		var row = $('#supplierEvaluateTemplate_grid').datagrid("getSelections");
		if(row.length<1){
			$.messager.alert("提示","请勾选至少一条记录");
			return;
		}else if(row.length>=1){
			for(var i=0;i<row.length;i++){
				if(row[i].status != '草稿'){
					$.messager.alert("提示","只可发布草稿状态的考评表模版");
					return;
				}
			}
			for(var i=0;i<row.length;i++){
				var templateCode=row[i].templateCode;
				list=list+templateCode+",";
			}
			$.post("supplierEvaluateTemplate_publishSupplierEvaluateTemplateByTemplateCode_2.html",
					{"list":list},
					function(json){
						if (json.success) {
							parent.messagerShow({
								title : '操作成功!',
								msg : json.msg
							});
						} else {
							parent.messagerShow({
								title : '操作失败!',
								msg : json.msg
							});
						}
					},"json"
			);
			supplierEvaluateTemplateFn.search();
		}
	},
	//修改供应商考评表模板
	showEdit:function(){
		
		var row = $('#supplierEvaluateTemplate_grid').datagrid("getSelections");
		if(row.length>1){
			$.messager.alert("提示","请选择一条草稿状态的记录");
		}else if(row.length==0){
			$.messager.alert("提示","请选择一条草稿状态的记录");
		}else if(row.length==1&&row[0].status!='草稿'){
			$.messager.alert("提示","请选择一条草稿状态的记录");
		}else{
			var templateCode=row[0].templateCode;
			supplierEvaluateTemplateFn.openUpdate(templateCode);
		}
	},
	//关闭考评模版
	closeTemplate:function(){
		var row = $('#supplierEvaluateTemplate_grid').datagrid('getSelections');
		if(row.length==0){
			$.messager.alert("提示","请勾选至少一条记录");
			return;
		}else if(row.length>=1){
			for(var i=0;i<row.length;i++){
				if(row[i].status != '已发布'){
					$.messager.alert("提示","只可关闭已发布状态的考评表模版");
					return;
				}
			}
			var string='';
			for(var i=0;i<row.length;i++){
				string=string+row[i].templateCode+';';
			}
			$.post("supplierEvaluateTemplate_closeSupplierEvaluateTemplate_2.html",
					{"list":string},
					function(json){
				if(json.success){
					parent.messagerShow({
						title:'操作成功',
						msg:json.msg
					});
				}else{
					parent.messagerShow({
						title:'操作失败',
						msg:json.msg
					});
				}
			},"json");
		}
		supplierEvaluateTemplateFn.search();
	},
	//删除供应商考评表模板
	remove:function(){
		var templateCodes='';
		var record=$("#supplierEvaluateTemplate_grid").datagrid('getSelections');
		if(record.length==0){
			$.messager.alert('提示','请勾选至少一条记录');
			return;
		}
		for(var i=0;i<record.length;i++){
			if(record[i].status != '草稿'){
				$.messager.alert('提示','只可删除草稿状态的考评表模版');
				return;
			}
		}
		for(var i=0;i<record.length;i++){
			templateCodes=templateCodes+record[i].templateCode+";";
		}
		utils.confirm("操作确认","确认删除供应商考评表模板?",function(){
			utils.post("supplierEvaluateTemplate_deleteSupplierEvaluateTemplate_2.html",{'array':templateCodes},function(){
				supplierEvaluateTemplateGridUtil.refresh();
			});
		});
		supplierEvaluateTemplateFn.search();
	},
	refresh:function(){
    	$('#supplierEvaluateTemplate_grid').treegrid('reload');
    },
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#supplierEvaluateTemplate_grid').datagrid("getSelections");
		if(rows.length<1){
			$("a[id='work']").linkbutton("disable");
		}else{
			$("a[id='work']").linkbutton("enable");
		}	
	}
};