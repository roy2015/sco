var supplierComprehensiveAnalysisGridUtil = null;
var firstIn = true;// 是否第一次进入页面
var clearSearch = false;// 清空查询
$(document).ready(
		function() {
			supplierComprehensiveAnalysisGridUtil = utils.grid($('#supplierComprehensiveAnalysisGrid'));
			supplierComprehensiveAnalysisGridUtil.registerExtFilters("supplierCode", "supplierName", "goodsCode", "goodsName");
			supplierComprehensiveAnalysisGridUtil.initFilters({
				onBeforeLoad:function(obj) {
					// 清空查询
					if(clearSearch) {
						return true;
					}
					var length = $.param(obj).split("&").length;
					// 判断参数个数
					if (length < 3) {
						if(!firstIn) $.messager.alert("提示", "请输入至少一项查询条件");
						firstIn = false;
						return false;
					}
					if(length == 3 && $.param(obj).indexOf("order") > -1) {
						var param = supplierComprehensiveAnalysisGridUtil.getFilterValue();
						if($.param(param).length > 0){
							return true;
						}
						$.messager.alert("提示", "请输入至少一项查询条件");
						return false;
					}
			
				},
				onLoadSuccess : function() {

					$('#supplierComprehensiveAnalysisGrid').datagrid("clearSelections");
				}
			});
		});
var supplierComprehensiveAnalysisFn = {
		// 
		showSupplierComprehensiveAnalysis:function() {
			var rows = $("#supplierComprehensiveAnalysisGrid").datagrid("getSelections");
			if(rows.length < 1) {
				window.parent.$.messager.alert("提示", "请勾选至少一条记录");
				return;
			}
			$(rows).each(function(i,obj){
				obj.supplierName=null;
				obj.supplierSite=null;
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
			var title='供应商综合分析设置';
			var href="supplierComprehensiveAnalysis_showSupplierComprehensiveAnalysisSet_1.html?rows="+json
			
			var dlg=utils.showDlg({
				title:title,href:href,width:700,height:400,
				buttons:[{text:'确认',handler:function(){supplierComprehensiveAnalysisFn.submitShowSupplierComprehensiveAnalysisSet(dlg,rows);},iconCls:'save'},
				        {text:'取消',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				],
				onLoad:function(){
				}
			});
		},
		// 供应商综合分析设置
		submitShowSupplierComprehensiveAnalysisSet:function(dlg,rows){
			var minDate=$('#minDate').datebox("getValue");
			var maxDate=$('#maxDate').datebox("getValue");
			var years=$('#years').combobox("getValue");
			var smallTypeCode=$('#smallTypeCode').combobox("getValue");
			var detailTypeCode=$('#detailTypeCode').combobox("getValue");
			var xsbx=$('#xsbx').combobox("getText");
			var zlqk=$('#zlqk').combobox("getText");
			var jhqk=$('#jhqk').combobox("getText");
			var shbx=$('#shbx').combobox("getText");
			var xpphqk=$('#xpphqk').combobox("getText");
			if(minDate==''||maxDate==''){
				$.messager.alert("提示", "时间范围不能为空");
				return;
			}
			/*if(smallTypeCode==''||detailTypeCode==''){
				$.messager.alert("提示", "小分类,明细类不能为空");
				return;
			}*/
			// 日起范围的合法性校验
			if (minDate > maxDate) {
				$.messager.alert('提示', '<center>结束时间不能早于开始时间！</center>');
				return;
			}
			  /*var strSeparator = "-"; //日期分隔符
			   var oDate1;
			   var oDate2;
			   var iDays;
			   if(minDate!=null&&maxDate!=null&&minDate!=''&&maxDate!=''){
			   oDate1= minDate.split(strSeparator);
			   oDate2= maxDate.split(strSeparator);
			   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
			   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
			   alert(strDateS);
			   alert(strDateE);
			   if(strDateS>strDateE){
				   $.messager.alert('提示', "数据范围后面的必须大于前面的"); 
				   return;
			   } 
			   }*/
			var json = JSON.stringify(rows).replace(/"([^"]*)"/g, "'$1'");
			var url = "supplierComprehensiveAnalysis_showSupplierComprehensiveAnalysis_1.html?rows="+json+"&minDate="+minDate+"&maxDate="+maxDate+"&years="+years+"&xsbx="+xsbx+"&zlqk="+zlqk+"&jhqk="+jhqk+"&shbx="+shbx+"&xpphqk="+xpphqk+"&smallTypeCode="+smallTypeCode+"&detailTypeCode="+detailTypeCode;
			parent.addTabByUrl('供应商综合分析内容', 'agent', url);
		},
		// 导出到Excel
		export2Excel : function() {
			var url = 'supplierComprehensiveAnalysis_exportDataToExcel_6.html';
			var jsons=$('#json').val().replace(new RegExp(/(%)/g), "\"");
			//jsons="["+jsons+"]";
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
		var param = supplierComprehensiveAnalysisGridUtil.getFilterValue();

		$('#supplierComprehensiveAnalysisGrid').datagrid('load', param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#supplierComprehensiveAnalysis_search').form('reset');
		clearComboboxOptions();
		// accessoryIntentionGridUtil.clearFilter();
		 $('#supplierComprehensiveAnalysisGrid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
	},
	
	
	remove : function() {
		var applicationCode = [];
		var applicationFlag = true;
		var applyGrid = $('#supplierComprehensiveAnalysisGrid').datagrid('getChecked');
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
						utils.post("supplierComprehensiveAnalysis_deleteApplication_2.html?applicationCode=" + applicationCode, null, function() {
							// intentionUtil.refresh();
							$('#supplierComprehensiveAnalysisGrid').datagrid('reload');
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
		$.post("supplierComprehensiveAnalysis_saveSearchDataForm_2.html?", {data : param,fileName : fileName}, function(data) {
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
	},
	setDetailType :function() {
		supplierComprehensiveAnalysisFn.reloadData("smallTypeCode", "detailTypeCode", "DetailType");
	},
	// 重新加载
	reloadData : function(thisId, targetId, method) {
		alert(thisId);
		alert(targetId);
		alert(method);
		//supplierComprehensiveAnalysisFn.clearSelectedData(targetId);
		// 当前框的值
		var value = $("#" + thisId).combobox("getValue");
		alert(value);
		if (!value) {
			value = 'null';
		}
		$("#" + targetId).combogrid({
			url : "masterDataType_list" + method + "_5.html?" + thisId + "=" + value
		});
	},

	// 清空下一个连动框已选择的值
	clearSelectedData : function(targetId) {
		alert(targetId);
		var g = $("#" + targetId).combogrid('grid');
		alert(g);
		$(g).datagrid("clearSelections");
		$("#" + targetId).combogrid("setValue", "");
		$("#" + targetId).combobox("clear");
	}

};