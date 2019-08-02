var accessoryIntentionGridUtil=null;
var firstIn = true;// 是否第一次进入页面
var clearSearch = false;// 清空查询
$(document).ready(function(){
	accessoryIntentionGridUtil=utils.grid($('#accessoryIntention_grid'));
	accessoryIntentionGridUtil.registerExtFilters("supplierCode", "supplierName", "minUpdatedDate", "maxUpdatedDate", 
			"intentionCode", "intentionName", "centreTypeCode", "smallTypeCode", "detailTypeCode", 
			"fineTypeCodes","search");
	accessoryIntentionGridUtil.initFilters({
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
				var param = accessoryIntentionGridUtil.getFilterValue();
				if($.param(param).length > 0){
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
		},
		onLoadSuccess:function() {
			firstIn = false;
			$('#accessoryIntention_grid').datagrid("clearSelections");
		}

	});
});

var accessoryIntentionFn = {
		search : function() {
			var param = accessoryIntentionGridUtil.getFilterValue();
			param.search='search';
			$('#accessoryIntention_grid').datagrid('load', param);
		},
		
		
		
		
		setSmallType:function(newValue, oldValue) {
			accessoryIntentionFn.reloadData("centreType", "smallType", "SmallType", "centreType=");
		},
		
		// 查询明细类
		setDetailType:function(newValue, oldValue) {
			accessoryIntentionFn.reloadData("smallType", "detailType", "DetailType");
		},
		
		/*
		 * //查询细分类 setMinceType:function(newValue, oldValue) {
		 * accessoryIntentionFn.reloadData("detailType", "minceType",
		 * "MinceType"); },
		 */
		// 重新加载
		reloadData:function(thisId, targetId, method) {
			accessoryIntentionFn.clearSelectedData(targetId);
			// 当前框的值
			var value = $("#" + thisId).combobox("getValue");
			if(!value) {
				value = 'null';
			}
			$("#" + targetId).combogrid({ url: "accessoryIntention_list"+method+"_5.html?"+thisId+"="+value});
		},
		
		// 清空下一个连动框已选择的值
		clearSelectedData:function(targetId) {
			var g = $("#" + targetId).combogrid('grid');	
			$(g).datagrid("clearSelections");
			$("#" + targetId).combogrid("setValue", "");
			$("#" + targetId).combobox("clear");
		},
	// 添加记录
	showInsert:function() {
		accessoryIntentionGridUtil.append();
	},
	// 修改记录
	showEdit:function(){
		accessoryIntentionGridUtil.startRowEdit();
	},
	

	
	// 取消新增或修改操作
	cancel:function(){
		accessoryIntentionGridUtil.reject();
	},
	
	// 删除一行
	remove:function(){
		accessoryIntentionGridUtil.remove("accessoryIntention_deleteAccessoryIntention_2.html");
	},
	// 清除查询
	clearFilter:function(){
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');

		clearSearch = true;
		$('#accessoryIntention_search').form('reset');
		clearComboboxOptions();
	// accessoryIntentionGridUtil.clearFilter();
		$('#accessoryIntention_grid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
	},
	addIntention:function(){
		var url = "accessoryIntention_addIntention_1.html";
		parent.addTabByUrl('辅料意向品详情', 'agent', url);
	},
	editIntention:function(intentionCode){
		var url = "accessoryIntention_addIntention_1.html?intentionCode="+intentionCode
		parent.addTabByUrl('辅料意向品详情', 'agent', url);
	},
	editEnquiry:function(enquiryCode){
		var url = "accessoryIntention_showInsertXJDForm_1.html?enquiryCode="+enquiryCode
		parent.addTabByUrl('修改询价单', 'agent', url);
	},
	delIntention:function(){
		var intentionCode=[];
		var record = $('#accessoryIntention_grid').datagrid('getChecked');
		if(record.length == 0){
			$.messager.alert('提示','<center>请至少选择一条记录！</center>'); 
		}else{
		utils.confirm("操作确认","确认删除辅料意向品?",function(){
		/*$.post('accessoryIntention_deleteAccessoryIntention_2.html', {intentionCode:record.intentionCode}, function(json){
			if(json.success){
				parent.messagerShow({title:'操作成功!', msg:json.msg});
				$('#accessoryIntention_grid').datagrid('reload');	
			}else{
				parent.messagerShow({title:'操作失败!', msg:json.msg});
				$('#accessoryIntention_grid').datagrid('reload');	
			}
			// console.dir(json);
			// console.dir(json.rows.aaa);
		},"json");*/
			$.each(record, function(index, item) {
				intentionCode.push(item.intentionCode);
			});
			utils.post("accessoryIntention_deleteAccessoryIntention_2.html?intentionCode="+intentionCode, null, function() {
				$('#accessoryIntention_grid').datagrid('reload');
			});

		});
		}
		$(".window-shadow").remove();
	},

  // 清空下一个连动框已选择的值
	clearSelectedData:function(targetId) {
		var g = $("#" + targetId).combogrid('grid');	
		$(g).datagrid("clearSelections");
		$("#" + targetId).combogrid("setValue", "");
		$("#" + targetId).combobox("clear");
	},
	importScanQuoted:function(){
		var intentionCode=$('#accessoryIntentionCode').val();
		var quotedDate=$("#quotedDate").datebox("getValue");
		if (quotedDate ==null || quotedDate == "") {
			$.messager.alert('提示','<center>请选择报价日期！</center>'); 
			return;
		}
		var accessoryIntentionSupplierGrid = $('#accessoryIntentionSupplier_drbjd').datagrid('getSelected');
		if(accessoryIntentionSupplierGrid==null){
			$.messager.alert('提示','<center>请选择供应商！</center>'); 
			return;
		}
		var accessoryEnquiryGrid = $('#accessoryEnquiry_drbjd').datagrid('getSelected');
		if(accessoryEnquiryGrid==null){
			$.messager.alert('提示','<center>请选择询价单！</center>'); 
			return;
		}
		var  intentionSupplierCode=accessoryIntentionSupplierGrid.intentionSupplierCode;// 供应商编号
		var  enquiryCode=accessoryEnquiryGrid.enquiryCode;// 询价单编号
	// 获取文件域信息
	 $('#uploadAccessoryQuotedForm').ajaxForm({
			url: "accessoryIntention_uploadScanQuotedExcel_2.html?intentionCode="+intentionCode+"&intentionSupplierCode="+intentionSupplierCode
				+"&quotedDate="+quotedDate+"&enquiryCode="+enquiryCode,
			dataType:  'json',
			cache : false,
			success:function(json){
				// easyUIBugSolves.wartingDivHide();
				
				if (json.success){
					$.messager.alert('提示', "导入扫描版报价单成功!");
				}else{
					// $.messager.alert('快捷导入Excel数据错误提示!', msg);
					$.messager.alert('提示', "导入扫描版报价单失败!");
				}
				$("#accessoryQuotedFile").val("");
				$("#accessoryUploadQuoted").val("");
				$('#accessoryIntentionSupplier_drbjd').datagrid('reload');
				$('#accessoryEnquiry_drbjd').datagrid('reload');
				$('#accessoryQuotedScan_drbjd').datagrid('reload');
			 }
		});
			// 验证上传格式
	 var uploadVar = $('#upload').val();
		if(uploadVar==''){
				$.messager.alert('错误提示','<center>请选择文件!</center>');  
		}else{
			
	   		 $.messager.show({
					title:'提示',
					msg:'导入文件中，请稍后！',
					timeout:2000,
					showType:'slide'
				});
	   		 	// easyUIBugSolves.wartingDivShow('panel4_toolbar');
	    		$('#uploadAccessoryQuotedForm').submit();
	    	
		}
	}
	





};