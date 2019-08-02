
var appScheNewFn = {
	
	//保存
	save:function(){
		var rows = $("#appScheNewGrid").datagrid("getSelections");
		if(rows.length < 1) {
			window.parent.$.messager.alert("提示", "请勾选至少一条记录");
			return;
		}
		
		//验证
		var msg = "";
		$(rows).each(function(i, obj){
			var i = $('#appScheNewGrid').datagrid('getRowIndex', obj);//当前数据所在行
			$('#appScheNewGrid').datagrid('endEdit', i);//结束在编辑的行
			// 获取编辑
			msg += appScheNewFn.validateData(obj, i)+"\r\n";
			obj.applicationCode='${applicationCodeNow}';
		});
//		$('#appScheNewGrid').datagrid('acceptChanges');
		//是否通过验证
		if($.trim(msg).length > 0) {
			$("#errMsg").html(msg);
			$("#msgDlg").window('open');//打开窗口
			return;
		}
		
		var url='applicationScheduleNew_insertApplicationScheduleNew_2.html';
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
						$('#appScheNewGrid').datagrid('load');
						$('#appScheNewGrid').datagrid('clearSelections');
					} else {
						$.messager.alert("提示", "保存失败");
					}
		},"json");
	},
	
	//验证数据合法性
	validateData:function(obj, i) {
		var accessoryCode = obj.accessoryCode;
		var accessoryName = obj.accessoryName;
		var supplierCode = obj.supplierCode;
		var supplierName = obj.supplierName;
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
		 $('#appScheNewGrid').datagrid('endEdit', editIndex);//结束在编辑的行
		 $('#appScheNewGrid').datagrid('unselectRow', editIndex);//结束在编辑的行
		return true;
		/* 校验改行
		 * if (editIndex == undefined){return true;}
		 alert(editIndex);
		 if ($('#appScheNewGrid').datagrid('validateRow', editIndex)){
			 var ed = $('#appScheNewGrid').datagrid('getEditor', {index:editIndex,field:'factoryNo'});
			 var factoryName = $(ed.target).combobox('getText');
			 $('#appScheNewGrid').datagrid('getRows')[editIndex]['factoryName'] = factoryName;
			 $('#appScheNewGrid').datagrid('endEdit', editIndex);
			 editIndex = undefined;
			 return true;
		 } else {
			 return false;
		 }*/
	 },
	
	 //关闭消息dlg
	 closeErrDlg : function() {
		$("#errMsg").html("");
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
	}
};