var totalcostanalysisGridUtil = null;
var firstIn = true;// 是否第一次进入页面
var clearSearch = false;// 清空查询
$(document).ready(
		function() {
			totalcostanalysisGridUtil = utils.grid($('#totalcostanalysisGrid'));
			totalcostanalysisGridUtil.registerExtFilters("intentionSupplierCode", "intentionSupplierName", "intentionCode", "intentionName", "intentionCreated", "intentionCreatedEnd", "centreTypeCode",
					"smallTypeCode", "detailTypeCode", "fineTypeCodes", "enquiryCode", "enquiryCreated", "enquiryCreatedEnd", "quotedCode", "quotedCreated", "quotedCreatedEnd", "applicationCode",
					"applicationStatus", "search");
			totalcostanalysisGridUtil.initFilters({
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
						var param = totalcostanalysisGridUtil.getFilterValue();
						if($.param(param).length > 0){
							return true;
						}
						$.messager.alert("提示", "请输入至少一项查询条件");
						return false;
					}
				},
				onLoadSuccess : function() {

					$('#totalcostanalysisGrid').datagrid("clearSelections");
				}
			});
		});
var totalcostanalysisFn = {
		// 添加打样信息
		showTotalCostAnalysis:function() {
			var rows = $("#totalcostanalysisGrid").datagrid("getSelections");
			if(rows.length < 1) {
				window.parent.$.messager.alert("提示", "请勾选至少一条记录");
				return;
			}
			$(rows).each(function(i,obj){
				obj.centreTypeName=null;
				obj.detailTypeName=null;
				obj.enquiryCreateby=null;
				obj.enquiryCreated=null;
				obj.enquiryName=null;
				obj.fineTypeName=null;
				obj.intentionName=null;
				obj.smallTypeName=null;
				obj.supplierCount=null;
				obj.supplierName=null;
				obj.intentionSupplierName=null;
				obj.purchaseCount=0;
				for(var key in obj){  
		            try{  
		                var value = eval("obj['" +  key +"']");  
		                if(value==null ||value=='null'||value==''){
		                	delete obj[key];  
		                }
		            }catch(e){}  
		        }  
			      
			  }); 
			var json = JSON.stringify(rows).replace(/"([^"]*)"/g, "'$1'");
			var title='总价分析设置';
			var href="totalcostanalysis_showTotalCostAnalysisSet_1.html?rows="+json
			
			var dlg=utils.showDlg({
				title:title,href:href,width:1000,height:400,
				buttons:[{text:'确认',handler:function(){totalcostanalysisFn.submitShowTotalCostAnalysisSet(dlg,rows);},iconCls:'save'},
				        {text:'取消',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				],
				onLoad:function(){
				}
			});
		},
		// 提交总价分析设置
		submitShowTotalCostAnalysisSet:function(dlg,rows){
			var allbjd=$('#allbjd').combobox("getValue");
			var allbjsl=$('#allbjsl').combobox("getValue");
			var zzxx=$('#zzxx').combobox("getText");
			var ypxx=$('#ypxx').combobox("getValue");
			var zhfp=$('#zhfp').combobox("getValue");
			var allxjd="";
			//var allxjd=$('#allxjd').combobox("getValue");
			var txbjsl="";
			if(allbjsl=='否'){
				txbjsl=$('#zckbjsl').val();
				if(txbjsl==''){
					$.messager.alert('提示', '报价数量必须填写');
					return;
				}
			}
		/*	$(rows).each(function(i, obj){
				obj.centreTypeName=null;
				obj.detailTypeName=null;
				obj.enquiryCreateby=null;
				obj.enquiryCreated=null;
				obj.fineTypeName=null;
				obj.intentionName=null;
				obj.smallTypeName=null;
				obj.supplierCount=null;
				obj.supplierName=null;
				
			});*/
			$(rows).each(function(i,obj){
				
				obj.purchaseCount=$('#'+obj.enquiryCode).val()==''?0:$('#'+obj.enquiryCode).val();
			      
			  }); 
			
			//var json = encodeURIComponent(JSON.stringify(rows));
			var json = JSON.stringify(rows).replace(/"([^"]*)"/g, "'$1'");
		//	console.info(json);
			var url = "totalcostanalysis_showTotalCostAnalysis_1.html?rows="+json+"&allbjd="+allbjd+"&allbjsl="+allbjsl+"&zzxx="+zzxx+"&ypxx="+ypxx+"&zhfp="+zhfp+"&allxjd="+allxjd+"&txbjsl="+txbjsl;
			parent.addTabByUrl('总成本分析内容', 'agent', url);
		},
		// 导出到Excel
		export2Excel : function() {
			var url = 'totalcostanalysis_exportDataToExcel_6.html';
			var jsons=$('#json').val().replace(new RegExp(/(@%)/g), "\"");
			//jsons="["+jsons+"]";
		//	console.info(jsons);
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
		var param = totalcostanalysisGridUtil.getFilterValue();

		var intentionCreated = $("#intentionCreated").datebox("getValue");
		var intentionCreatedEnd = $("#intentionCreatedEnd").datebox("getValue");
		// 创建时间的合法性校验
		if (intentionCreated == "" && intentionCreatedEnd != "") {
			$.messager.alert('提示', '<center>请输入意向品开始创建时间！</center>');
			return;
		}
		if (intentionCreatedEnd == "" && intentionCreated != "") {
			$.messager.alert('提示', '<center>请输入意向品结束创建时间！</center>');
			return;
		}
		if (intentionCreated > intentionCreatedEnd) {
			$.messager.alert('提示', '<center>结束意向品创建时间不能早于开始创建时间！</center>');
			return;
		}

		var enquiryCreated = $("#enquiryCreated").datebox("getValue");
		var enquiryCreatedEnd = $("#enquiryCreatedEnd").datebox("getValue");
		// 询价单提交日期的合法性校验
		if (enquiryCreated == "" && enquiryCreatedEnd != "") {
			$.messager.alert('提示', '<center>请输入询价单创建开始时间！</center>');
			return;
		}
		if (enquiryCreatedEnd == "" && enquiryCreated != "") {
			$.messager.alert('提示', '<center>请输入询价单创建结束时间！</center>');
			return;
		}
		if (enquiryCreated > enquiryCreatedEnd) {
			$.messager.alert('提示', '<center>询价单创建结束时间不能早于询价单创建开始时间！</center>');
			return;
		}
		param.search='search';
		$('#totalcostanalysisGrid').datagrid('load', param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#totalcostanalysis_search').form('reset');
		clearComboboxOptions();
		// accessoryIntentionGridUtil.clearFilter();
		 $('#totalcostanalysisGrid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
	},
	
	
	remove : function() {
		var applicationCode = [];
		var applicationFlag = true;
		var applyGrid = $('#totalcostanalysisGrid').datagrid('getChecked');
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
						utils.post("totalcostanalysis_deleteApplication_2.html?applicationCode=" + applicationCode, null, function() {
							// intentionUtil.refresh();
							$('#totalcostanalysisGrid').datagrid('reload');
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
		$.post("totalcostanalysis_saveSearchDataForm_2.html?", {data : param,fileName : fileName}, function(data) {
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