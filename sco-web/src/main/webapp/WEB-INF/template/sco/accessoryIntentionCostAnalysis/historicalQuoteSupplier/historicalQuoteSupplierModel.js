var historicalQuoteSupplierGridUtil = null;
var firstIn = true;// 是否第一次进入页面
var clearSearch = false;// 清空查询
$(document).ready(
		function() {
			historicalQuoteSupplierGridUtil = utils.grid($('#historicalQuoteSupplierGrid'));
			historicalQuoteSupplierGridUtil.registerExtFilters("intentionSupplierCode", "intentionSupplierName", "intentionCode", "intentionName", "intentionCreated", "intentionCreatedEnd", "centreTypeCode",
					"smallTypeCode", "detailTypeCode", "fineTypeCodes",  "search","intentionCreateby");
			historicalQuoteSupplierGridUtil.initFilters({
				onBeforeLoad:function(obj) {
					// 清空查询
					if(clearSearch) {
						return true;
					}
					var length = $.param(obj).split("&").length;
					// 判断参数个数
					if (length < 4) {
						if(!firstIn) $.messager.alert("提示", "请输入至少一项查询条件");
						firstIn = false;
						return false;
					}
					if(length == 5 && $.param(obj).indexOf("order") > -1) {
						var param = historicalQuoteSupplierGridUtil.getFilterValue();
						if($.param(param).length > 0){
							return true;
						}
						$.messager.alert("提示", "请输入至少一项查询条件");
						return false;
					}
				},
				onLoadSuccess : function() {

					$('#historicalQuoteSupplierGrid').datagrid("clearSelections");
				}
			});
		});
var historicalQuoteSupplierFn = {
		// 
		showHistoricalQuoteSupplier:function() {
			var rows = $("#historicalQuoteSupplierGrid").datagrid("getSelections");
			if(rows.length < 1) {
				window.parent.$.messager.alert("提示", "请勾选至少一条记录");
				return;
			}
			var title='供应商历史报价分析设置';
			var href="historicalQuoteSupplier_showHistoricalQuoteSupplierSet_1.html"
			
			var dlg=utils.showDlg({
				title:title,href:href,width:500,height:300,
				buttons:[{text:'确认',handler:function(){historicalQuoteSupplierFn.submitShowHistoricalQuoteSupplierSet(dlg,rows);},iconCls:'save'},
				        {text:'取消',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				],
				onLoad:function(){
				}
			});
		},
		// 提交总价分析设置
		submitShowHistoricalQuoteSupplierSet:function(dlg,rows){
			var isYorN=$('#haveHistoricalQuoteSupplierSetForm input[name="cause"]:checked ').val();
			$(rows).each(function(i,obj){
				obj.centreTypeName=null;
				obj.detailTypeName=null;
				obj.enquiryCreateby=null;
				obj.enquiryCreated=null;
				obj.fineTypeName=null;
				obj.intentionName=null;
				obj.intentionSupplierName=null;
				obj.supplierName=null;
				obj.smallTypeName=null;
				obj.accessorySapCode=null;
				obj.quotedCreated=null;
				obj.quotedDate=null;
				obj.enquiryCreateby=null;
				obj.enquiryCreated=null;
				obj.applicationCreateby=null;
				obj.applicationStatus=null;
				//删除为空的字段
				for(var key in obj){  
		            try{  
		                var value = eval("obj['" +  key +"']");  
		                if(value==null ||value=='null'||value==''){
		                	delete obj[key];  
		                }
		            }catch(e){}  
		        }  
			      
			  }); 
			//var json = encodeURIComponent(JSON.stringify(rows));
			var json = JSON.stringify(rows).replace(/"([^"]*)"/g, "'$1'");
			var url = "historicalQuoteSupplier_showHistoricalQuoteSupplier_1.html?rows="+json+"&isYorN="+isYorN;
			parent.addTabByUrl('供应商历史报价分析内容', 'agent', url);
		},
		// 导出到Excel
		export2Excel : function() {
			var url = 'historicalQuoteSupplier_exportDataToExcel_6.html';
			var jsons=$('#json').val().replace(new RegExp(/(@%)/g), "\"");
			//jsons="["+jsons+"]";
			//console.info(jsons);
			var json1=JSON.parse(jsons);
			
			//var json =JSON.stringify(json1).replace(/"([^"]*)"/g, "'$1'");
		//	var post={date:JSON.stringify(json1).replace(/"([^"]*)"/g, "'$1'")};//JSON.stringify(json)把json转化成字符串
        //    $.post(url,post);
            $("#zjfx_form").form('submit', {
				url : url,
				onSubmit : function(param) {
					param.date = JSON.stringify(json1).replace(/"([^"]*)"/g, "'$1'");
				},
				success : function(data) {
					var json = $.parseJSON(data);
					if (json.success) {
						parent.messagerShow({
							title : '操作成功!',
							msg : json.msg
						});
					}else{
						$.messager.alert('提示', json.msg);
						/*parent.messagerShow({
							title : '操作失败!',
							msg : json.msg
						});*/
					}
				}
			});
		//	window.location = url;
			
		},
	// 查询方法
	search : function() {
		var param = historicalQuoteSupplierGridUtil.getFilterValue();

	
		param.search='search';
		$('#historicalQuoteSupplierGrid').datagrid('load', param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#historicalQuoteSupplier_search').form('reset');
		clearComboboxOptions();
		// accessoryIntentionGridUtil.clearFilter();
		 $('#historicalQuoteSupplierGrid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
	},
	
	
	remove : function() {
		var applicationCode = [];
		var applicationFlag = true;
		var applyGrid = $('#historicalQuoteSupplierGrid').datagrid('getChecked');
		if (applyGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
			return;
		} else {
			$.each(applyGrid, function(index, item) {
				if (item.applicationStatus == "CG" && item.applicationCode != null && item.applicationCode != "") {
					applicationCode.push(item.applicationCode);
				} else {
					applicationFlag = false;
					return;
				}

			});
			if (applicationFlag) {
				if (applicationCode.length > 0) {
					utils.confirm("操作确认", "确认删除OA申请吗?", function() {
						utils.post("historicalQuoteSupplier_deleteApplication_2.html?applicationCode=" + applicationCode, null, function() {
							// intentionUtil.refresh();
							$('#historicalQuoteSupplierGrid').datagrid('reload');
						});
					});
				} else {
					$.messager.alert('提示', '<center>请勾选至少一条有效的记录</center>');
					return;
				}
			} else {
				$.messager.alert('提示', '<center>所选记录申请单号必须存在，并且是草稿状态</center>');
			}
		}
	},
	close : function() {
		parent.pubTab.closeCurrTab();
	},
	// 填写保存文件名称
	saveFile : function() {
		$("#saveFileDlg").window('open');// 打开窗口
	},
	// 提交填写文件名称的对话框
	submitSaveFileDlg : function() {
		var fileName = $.trim($("#fileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			return;
		}
		//获取分析表的html代码
		var param =$("#contrastResult").html();
		$.post("historicalQuoteSupplier_saveSearchDataForm_2.html?", {data : param,fileName : fileName}, function(data) {
			var json = $.parseJSON(data);
			var msg = json.msg;
			if (json.success) {// 成功
				$("#fileName").val("");// 清空填写的值
				$("#saveFileDlg").window('close');// 关闭窗口
				$.messager.show({
					title : '提示',
					msg : msg
				});
			} else {// 失败
				$.messager.alert("提示", msg);
			}
		});
	},
	// 是否察看所有报价数量
	doShowBjsl : function() {
		if($('#allbjsl').combobox('getValues')=='否'){
			$('#txbjsl').show();	
			$('#zckbjsl').validatebox({
				required : true
			});
		}else{
			$('#txbjsl').hide();
			$('#zckbjsl').validatebox({
				required : false
			});
		}
	}
	
};