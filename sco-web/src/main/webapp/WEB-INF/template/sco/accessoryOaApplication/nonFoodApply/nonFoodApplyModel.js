var nonFoodApplyGridUtil=null;
var firstIn = true;// 是否第一次进入页面
var clearSearch = false;// 清空查询
$(document).ready(function(){
	nonFoodApplyGridUtil=utils.grid($('#nonFoodApplyGrid'));
	nonFoodApplyGridUtil.registerExtFilters("intentionSupplierCode", "intentionSupplierName", "intentionCode", "intentionName", 
			"intentionCreated", "intentionCreatedEnd", "centreTypeCode", "smallTypeCode", "detailTypeCode", 
			"fineTypeCodes", "enquiryCode", "enquiryCreated", "enquiryCreatedEnd", "quotedCode", "quotedCreated", "quotedCreatedEnd", "applicationCode", "applicationStatus","search");
	nonFoodApplyGridUtil.initFilters({
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
				var param = nonFoodApplyGridUtil.getFilterValue();
				if($.param(param).length > 0){
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
		},
		onLoadSuccess:function(){
			
			
			
			
			$('#nonFoodApplyGrid').datagrid("clearSelections");
		}
	});
});
var nonFoodApplyFn = {
		// 添加申请报告(新品引进)
		showInsert : function(applicationCode) {
			var quotedCodes=[];
			if(applicationCode==null || applicationCode=='null' ){
			var nonFoodApplyGrid = $('#nonFoodApplyGrid').datagrid('getChecked');
			if (nonFoodApplyGrid.length == 0) {
				$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
				return;
			} else {
				var applicationFlag=true;
				$.each(nonFoodApplyGrid, function(index, item) {
					if (item.applicationStatus != null && item.applicationStatus != "") {
						//判断是否勾选已经做了OA申请记录的意向品
						applicationFlag = false;
					}
					
						quotedCodes.push(item.quotedCode);
					
				});
				if(!applicationFlag){
					$.messager.alert('提示', '<center>请勾选申请单状态是"无"的记录！</center>');
					return ;
				}
			}
			var tabName = '非食品竞价OA申请';
			}else{
				var tabName ='非食品申请单-'+applicationCode;
			}
				var url = "nonFoodApply_addNonFoodApply_1.html?quotedCodes="+quotedCodes+"&applicationCode="+applicationCode;
				if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
					parent.activeTab(tabName);
					parent.refreshTab(tabName);
					return;
				}
				parent.addTabByUrl(tabName, 'agent', url);
			
		},
		// 查询方法
		search : function() {
			var param = nonFoodApplyGridUtil.getFilterValue();

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
			var quotedCreated = $("#quotedCreated").datebox("getValue");
			var quotedCreatedEnd = $("#quotedCreatedEnd").datebox("getValue");
			// 报价单提交日期的合法性校验
			if (quotedCreated == "" && quotedCreatedEnd != "") {
				$.messager.alert('提示', '<center>请输入报价单上传开始时间！</center>');
				return;
			}
			if (quotedCreatedEnd == "" && quotedCreated != "") {
				$.messager.alert('提示', '<center>请输入报价单上传结束时间！</center>');
				return;
			}
			if (quotedCreated > quotedCreatedEnd) {
				$.messager.alert('提示', '<center>报价单上传结束时间不能早于报价单上传开始时间！</center>');
				return;
			}
			param.search='search';
			$('#nonFoodApplyGrid').datagrid('load', param);
		},
		// 清除查询
		clearFilter:function(){
			$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
			clearSearch = true;
			$('#nonFoodApply_search').form('reset');
			clearComboboxOptions();
		//	accessoryIntentionGridUtil.clearFilter();
			$('#nonFoodApplyGrid').datagrid('loadData',{total:0,rows:[]});
			clearSearch = false;
		},
		
		// 添加大货信息
		showUpdateDhFile:function(applicationCode) {
			var applicationQuotedRecord=$('#applicationQuoted_grid').datagrid('getSelected');
			if(applicationQuotedRecord==null){
				$.messager.alert('提示','<center>请选择一条记录！</center>'); 
				return;
			}
			var intentionCode=applicationQuotedRecord.intentionCode;
			var supplierCode=applicationQuotedRecord.supplierCode;
			var title='上传新文件';
			var href="nonFoodApply_showInsertDhFile_1.html?intentionCode="+intentionCode+"&supplierCode="+supplierCode+"&applicationCode="+applicationCode;
			
			var dlg=utils.showDlg({
				title:title,href:href,width:600,height:440,
				buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){nonFoodApplyFn.submitInsertDhfile(dlg);},iconCls:'save'},
				        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
				],
				onLoad:function(){
				}
			});
		},
		submitInsertDhfile:function(dlg){
			var uploadVar = $('#uploadNonFood').val();
			if (uploadVar == '') {
				$.messager.alert('上传文件错误提示', '<center>请选择文件123!</center>');
				
			}
			var fileType= $('#fileType').combobox("getValue");
			var fileTypeOther=$('#fileTypeOther').val();
			if(fileType=='其他' && fileTypeOther.length<1){
				$.messager.alert('错误提示', '<center>其他类型必须填写!</center>');
				return;
			}
			utils.form("dhinfo_form").submit("nonFoodApply_insertDhInfo_2.html",null,function(){
				   dlg.dialog('close');
				   $('#dhInfo_grid').datagrid('reload');
				  });
				},
			showFileTypeOther:function(){
				var fileType= $('#fileType').combobox("getValue");
				if(fileType=='其他'){
					 $('#otherType').show();
				}else{
					$('#otherType').hide();
				}
			},
			deleteDhInfo:function(intentionCode){
				var dhId=[];
				var record = $('#dhInfo_grid').datagrid('getChecked');
				if (record.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
					return;
				} else{
					$.each(record, function(index, item) {
						dhId.push(item.id);
					});
				utils.confirm("操作确认","确认删除大货信息吗?",function(){
					utils.post("nonFoodApply_deleteDhInfo_2.html?dhId="+dhId,null,function(){
						$('#dhInfo_grid').datagrid('reload');
					});
				});
				}
			},
			downloadDhFile:function(path){
				window.location ="nonFoodApply_downloadDhFile_6.html?path="+ encodeURIComponent(path);
			},
			//单选框操作
			doShowAmount:function(){
				 $('#ljamount').hide();
				 $('#amount').show();
				 $('#gzMoney').validatebox({
					 required:true
				 });
				 $('#accumulativeYear').validatebox({
					 required:false
				 });
				 $('#ljMoney').validatebox({
					 required:false
				 });
			},
			//单选框操作
			doShowLjAmount:function(){
				 $('#ljamount').show();
				 $('#amount').hide();
				 $('#gzMoney').validatebox({
					 required:false
				 });
				 $('#accumulativeYear').validatebox({
					 required:true
				 });
				 $('#ljMoney').validatebox({
					 required:true
				 });
			},
			//单选框操作
			doHideAll:function(){
				 $('#ljamount').hide();
				 $('#amount').hide();
				 $('#gzMoney').validatebox({
					 required:false
				 });
				 $('#accumulativeYear').validatebox({
					 required:false
				 });
				 $('#ljMoney').validatebox({
					 required:false
				 });
			},
			remove:function(){
				var applicationCode=[];
				var applicationFlag=true;
				var applyGrid = $('#nonFoodApplyGrid').datagrid('getChecked');
				if (applyGrid.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
					return;
				}else{
					$.each(applyGrid, function(index, item) {
						if (item.applicationStatus == "CG" && item.applicationCode!=null && item.applicationCode != "") {
							applicationCode.push(item.applicationCode);
						}else{
							applicationFlag=false;
							return;
						}
						
						
						
					});
					if(applicationFlag){
					if(applicationCode.length>0){
						utils.confirm("操作确认","确认删除OA申请吗?",function(){
							utils.post("nonFoodApply_deleteApplication_2.html?applicationCode="+applicationCode,null,function(){
								//intentionUtil.refresh();
								$('#nonFoodApplyGrid').datagrid('reload');
							});
						});
					}else{
						$.messager.alert('提示', '<center>请勾选至少一条有效的记录</center>');
						return;
					}
					}else{
						$.messager.alert('提示', '<center>所选记录申请单号必须存在，并且是草稿状态</center>');
					}
				}
			},
			close:function(){
				var applicationCode=[];
				var applicationFlag=true;
				var applyGrid = $('#nonFoodApplyGrid').datagrid('getChecked');
				if (applyGrid.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
					return;
				}else{
					$.each(applyGrid, function(index, item) {
						if (item.applicationStatus == "CG" && item.applicationCode!=null && item.applicationCode != "") {
							applicationCode.push(item.applicationCode);
						}else{
							applicationFlag=false;
							return;
						}
						
						
						
					});
					if(applicationFlag){
					if(applicationCode.length>0){
						utils.confirm("操作确认","确认关闭OA申请吗?",function(){
							utils.post("nonFoodApply_closeApplication_2.html?applicationCode="+applicationCode,null,function(){
								//intentionUtil.refresh();
								$('#nonFoodApplyGrid').datagrid('reload');
							});
						});
					}else{
						$.messager.alert('提示', '<center>请勾选至少一条有效的记录</center>');
						return;
					}
					}else{
						$.messager.alert('提示', '<center>所选记录申请单号必须存在，并且是草稿状态</center>');
					}
				}
			},
			allowOaSynchronous:function(){
				var applicationCode=[];
				var applicationFlag=true;
				var applyGrid = $('#nonFoodApplyGrid').datagrid('getChecked');
				if (applyGrid.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
					return;
				}else if(applyGrid.length > 1){
					$.messager.alert('提示', '<center>请最多选择一条记录！</center>');
					return;
				}else{
					$.each(applyGrid, function(index, item) {
						if (item.applicationStatus == "CG" && item.applicationCode!=null && item.applicationCode != "") {
							applicationCode.push(item.applicationCode);
						}else{
							applicationFlag=false;
							return;
						}
						
						
						
					});
					if(applicationFlag){
					if(applicationCode.length>0){
						utils.confirm("操作确认","确认允许OA同步吗?",function(){
							utils.post("nonFoodApply_allowOaSynchronous_2.html?applicationCode="+applicationCode,null,function(){
								//intentionUtil.refresh();
								$('#nonFoodApplyGrid').datagrid('reload');
							});
						});
					}else{
						$.messager.alert('提示', '<center>请勾选至少一条有效的记录</center>');
						return;
					}
					}else{
						$.messager.alert('提示', '<center>所选记录申请单号必须存在，并且是草稿状态</center>');
					}
				}
			},
			undoOaSynchronous:function(){
				var applicationCode=[];
			var applicationFlag=true;
			var applyGrid = $('#nonFoodApplyGrid').datagrid('getChecked');
			if (applyGrid.length == 0) {
				$.messager.alert('提示', '<center>请至少选择一条记录！</center>');
				return;
			}else{
				$.each(applyGrid, function(index, item) {
					if (item.applicationStatus == "YX" && item.applicationCode!=null && item.applicationCode != "") {
						applicationCode.push(item.applicationCode);
					}else{
						applicationFlag=false;
						return;
					}
					
					
					
				});
				if(applicationFlag){
				if(applicationCode.length>0){
					utils.confirm("操作确认","确认撤销OA同步吗?",function(){
						utils.post("nonFoodApply_undoOaSynchronous_2.html?applicationCode="+applicationCode,null,function(){
							//intentionUtil.refresh();
							$('#nonFoodApplyGrid').datagrid('reload');
						});
					});
				}else{
					$.messager.alert('提示', '<center>请勾选至少一条有效的记录</center>');
					return;
				}
				}else{
					$.messager.alert('提示', '<center>所选记录申请单号必须存在，并且是允许OA同步状态</center>');
				}
			}	
			},
			saveWlInfo:function(){
				var rows = $("#wlInfo_grid").datagrid("getSelections");
				if(rows.length < 1) {
					window.parent.$.messager.alert("提示", "请勾选至少一条记录");
					return;
				}
				
				//验证
			//	var msg = "";
				$(rows).each(function(i, obj){
					var i = $('#wlInfo_grid').datagrid('getRowIndex', obj);//当前数据所在行
				//	$('#wlInfo_grid').datagrid('endEdit', editIndex);//结束在编辑的行
					// 获取编辑
				//	msg += appScheNonFoodFn.validateData(obj, i)+"\r\n";
					obj.applicationCode='${applicationCodeNow}';
					obj.purchaseCount=$('#purchaseCount'+i).val();
					obj.contractPrice=$('#contractPrice'+i).val();
					obj.purchaseMoney=$('#purchaseMoney'+i).val();
					obj.invoiceType=$('#invoiceType'+i).val();
					obj.accessorySapCode=$('#accessorySapCode'+i).val();
					obj.supplierSapCode=$('#supplierSapCode'+i).val();
					obj.sjyjSpecification=$('#sjyjSpecification'+i).val();
				});
				$('#wlInfo_grid').datagrid('acceptChanges');
				//是否通过验证
				/*if($.trim(msg).length > 0) {
					$("#errMsg").html(msg);
					$("#msgDlg").window('open');//打开窗口
					return;
				}*/
				
				var url='nonFoodApply_insertWlInfo_2.html';
				$.post(url,
						{
						rows : JSON.stringify(rows),
						applicationCodeNow:'${applicationCodeNow}',
						quotedCodes : '${quotedCodes}'
						}, function(data) {
							if (data.success) {
								$.messager.show({
									title:'提示',
									msg:'保存成功',
									timeout:4000,
									showType:'slide'
								});
								$('#wlInfo_grid').datagrid('load');
								$('#wlInfo_grid').datagrid('clearSelections');
							} else {
								$.messager.alert("提示", data.msg);
							}
				},"json");
			},
			 testaler:function(index,contractPrice){
				 var count=$('#purchaseCount'+index).val();
				 var price=$('#contractPrice'+index).val();
				 if(isNaN(count)){
					 $('#purchaseCount'+index).val('');
					 $('#purchaseMoney'+index).val(0);
					 $.messager.alert('提示', '<center>实际采购数量必须是数字</center>');
					 return;
				 }else if(isNaN(price)){
					 $('#contractPrice'+index).val('');
					 $('#purchaseMoney'+index).val(0);
					 $.messager.alert('提示', '<center>合同进价必须是数字</center>');
					 return; 
				 }else{
				 var amount='';
				if(count!=''&& count!=null && price!=''&& price!=null){
					amount=count*price;
					$('#purchaseMoney'+index).val(amount);
				}else{
					$('#purchaseMoney'+index).val(0);
				}
					
				 }	
			 },
			//查看OA审批意见
			lookApproveOpinion:function(){
				var applyGrid = $('#nonFoodApplyGrid').datagrid('getChecked');
				if (applyGrid.length == 0) {
					$.messager.alert('提示', '<center>请选择一条记录！</center>');
				} else if(applyGrid.length >1){
					$.messager.alert('提示', '<center>请只选择一条申请记录！</center>');
				}else{
					var applicationCode="";//申请单号
					var oaApplicationCode="";//OA系统的申请单号
					$.each(applyGrid, function(index, item) {
						applicationCode=item.applicationCode;
						oaApplicationCode=item.oaApplicationCode;
					});
					
					//需要先判断是否做了OA申请，然后判断OA系统的信息是否为空
					if(applicationCode==null||applicationCode==""){
						$.messager.alert('提示', '<center>请选择已经做了OA申请记录！</center>');
					}else{
						if(oaApplicationCode==null||oaApplicationCode==""){
							$.messager.alert('提示', '<center>没有OA系统同步数据，不能查看OA审批意见!</center>');
						}else{
							window.open("http://eip.laiyifen.com/oa/lyf/fspgysyj.nsf/0/"+oaApplicationCode+"?opendocument");
						}
					}
				}
			}
		};