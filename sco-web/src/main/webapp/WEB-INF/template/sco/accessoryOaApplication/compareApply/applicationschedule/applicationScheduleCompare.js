
var appScheCompareApplyFn = {
	
	//保存
	save:function(){
		var rows = $("#appScheCompareGrid").datagrid("getSelections");
		if(rows.length < 1) {
			window.parent.$.messager.alert("提示", "请勾选至少一条记录");
			return;
		}
		
		//验证
		var msg = "";
		var flag = true;
		$(rows).each(function(i, obj){
			var i = $('#appScheCompareGrid').datagrid('getRowIndex', obj);//当前数据所在行
			
			var aogDate = $('#aogDate' + i)[0];
			obj.aogDate = $(aogDate).datebox('getValue')
			
			obj.normalFlowDate = $('#normalFlowDate' + i).val();
			if(isNaN(obj.normalFlowDate)) {
				flag = false;
				$('#normalFlowDate' + i).val('');
				$('#differenceDate' + i).text('');
				return;
			}
			$('#appScheCompareGrid').datagrid('endEdit', i);//结束在编辑的行
			// 获取编辑
			msg += appScheCompareApplyFn.validateData(obj, i)+"\r\n";
			obj.applicationCode='${applicationCodeNow}';
		});
		if (!flag) {
			appScheCompareApplyFn.beginEdit();
			window.parent.$.messager.alert('提示', '正常作业流程时间必须是数字');
			return;
		}
//		$('#appScheCompareGrid').datagrid('acceptChanges');
		//是否通过验证
		if($.trim(msg).length > 0) {
			$("#cpErrMsg").html(msg);
			appScheCompareApplyFn.beginEdit();
			$("#msgDlg").window('open');//打开窗口
			return;
		}
		
		var url='applicationScheduleCompare_insertApplicationScheduleCompare_2.html';
		$.post(url,
				{
				rows : JSON.stringify(rows),
				applicationCode:'${applicationCodeNow}',
				quotedCodes : '${quotedCodes}'
				}, function(data) {
					if (data.success) {
						$.messager.show({
							title:'提示',
							msg:'保存成功',
							timeout:4000,
							showType:'slide'
						});
						$('#appScheCompareGrid').datagrid('load');
						$('#appScheCompareGrid').datagrid('clearSelections');
					} else {
						$.messager.alert("提示", "保存失败");
					}
		},"json");
	},
	
	//开启编辑
	beginEdit:function() {
		var rows = $("#appScheCompareGrid").datagrid("getRows");
		$(rows).each(function(i, obj){
			$('#appScheCompareGrid').datagrid('beginEdit', i);//开启编辑
			$('#aogDate' + i).datebox({
				editable:false,
				onHidePanel:appScheCompareApplyFn.calculRealityFlowDate
			});
		});
	},
	
	//验证数据合法性
	validateData:function(obj, i) {
		var accessoryCode = obj.intentionCode;
		var accessoryName = obj.intentionName;
		var supplierCode = obj.intentionSupplierCode;
		var supplierName = obj.intentionSupplierName;
		var quotedCode = obj.quotedCode;
		var enquiryCount = obj.enquiryCount;
		
		if (!obj.sjwgDate) {
			obj.sjwgDate = null;
		}
		if (!obj.orderDate) {
			obj.orderDate = null;
		}
		if (!obj.aogDate) {
			obj.aogDate = null;
		}
		if(!accessoryCode || !accessoryName || !supplierCode
				|| !supplierName || !quotedCode || !enquiryCount) {
			return "第" + (i + 1) + "行中的必填项缺少数据;";
		}
		return "";
	},
	
	//关闭功能
	close:function() {
		parent.pubTab.closeCurrTab();
	},
	
	//结束编辑状态
	endEditing:function(){
//		 $('#appScheCompareGrid').datagrid('endEdit', editIndex);//结束在编辑的行
		 $('#appScheCompareGrid').datagrid('unselectRow', editIndex);//结束在编辑的行
		return true;
		/* 校验改行
		 * if (editIndex == undefined){return true;}
		 alert(editIndex);
		 if ($('#appScheCompareGrid').datagrid('validateRow', editIndex)){
			 var ed = $('#appScheCompareGrid').datagrid('getEditor', {index:editIndex,field:'factoryNo'});
			 var factoryName = $(ed.target).combobox('getText');
			 $('#appScheCompareGrid').datagrid('getRows')[editIndex]['factoryName'] = factoryName;
			 $('#appScheCompareGrid').datagrid('endEdit', editIndex);
			 editIndex = undefined;
			 return true;
		 } else {
			 return false;
		 }*/
	 },
	
	 //关闭消息dlg
	 closeErrDlg : function() {
		$("#cpErrMsg").html("");
		$("#msgDlg").dialog('close');
	},

	// 日期格式化
	formatDate:function(date) {
		if(!date) return "";
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		if (m < 10) {
			m='0'+m;
		}
		var d = date.getDate();
		if (d < 10) {
			d='0'+d;
		}
		return y+'-'+m+'-'+d;
	},
	
	//计算实际作业流程时间
	calculRealityFlowDate:function() {
		var dateId = $(this).attr('id');
		var index = dateId.substring(7);//id
		var aogDate = $(this).datebox('getValue');//实际到货日期
		var subscribeBillDate = $('#subscribeBillDate' + index).html();//收到申购单日期
		
		var val = '';
		if (aogDate != null && aogDate != '' 
			&& subscribeBillDate != null && subscribeBillDate != '') {
			val = dateDiff(aogDate, subscribeBillDate);
			if(val < 0) {
				val--;
			} else {
				val++;
			}
		}
		$('#realityFlowDate' + index).html(val);
		appScheCompareApplyFn.calculDifferenceDate(index);
	},
	
	//计算差异天数
	calculDifferenceDate:function(index) {
		var normalFlowDate = $('#normalFlowDate' + index).val();
		var realityFlowDate = $('#realityFlowDate' + index).text();
		var differenceDate = '';
		if (!isNaN(normalFlowDate) && normalFlowDate && realityFlowDate) {
			differenceDate = normalFlowDate - realityFlowDate;
			/*if (differenceDate < 0) {
				differenceDate--;
			} else {
				differenceDate++;
			}*/
		}
		$('#differenceDate' + index).text(differenceDate);
	}
	
};