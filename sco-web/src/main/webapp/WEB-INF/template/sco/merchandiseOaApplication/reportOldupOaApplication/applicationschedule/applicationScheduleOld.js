
var appScheOldFn = {
	
	//保存
	save:function(){
		var rows = $("#appScheOldGrid").datagrid("getSelections");
		if(rows.length < 1) {
			window.parent.$.messager.alert("提示", "请勾选至少一条记录");
			return;
		}
		
		//验证
		var msg = "";
		$(rows).each(function(i, obj){
			var i = $('#appScheOldGrid').datagrid('getRowIndex', obj);//当前数据所在行
			$('#appScheOldGrid').datagrid('endEdit', i);//结束在编辑的行
			// 获取编辑
			msg += appScheOldFn.validateData(obj, i)+"\r\n";
			obj.applicationCode='${applicationCode}';
		});
//		$('#appScheOldGrid').datagrid('acceptChanges');
		//是否通过验证
		if($.trim(msg).length > 0) {
			$("#errOldMsg").html(msg);
			$("#msgOldDlg").window('open');//打开窗口
			appScheOldFn.beginEdit();
			return;
		}
		
		var url='applicationScheduleOld_insertApplicationScheduleOld_2.html';
		$.post(url,
				{
				rows : JSON.stringify(rows),
				applicationCode:'${applicationCode}',
				intentionAndSupplierCodes : '${intentionAndSupplierCodes}'
				}, function(data) {
					if (data.success) {
						$.messager.show({
							title:'提示',
							msg:'保存成功',
							timeout:4000,
							showType:'slide'
						});
						$('#appScheOldGrid').datagrid('load');
						$('#appScheOldGrid').datagrid('clearSelections');
					} else {
						$.messager.alert("提示", "保存失败");
					}
		},"json");
	},
	
	//开启编辑
	beginEdit:function() {
		var rows = $("#appScheOldGrid").datagrid("getRows");
		$(rows).each(function(i, obj){
			$('#appScheOldGrid').datagrid('beginEdit', i);
		});
	},
	
	//验证数据合法性
	validateData:function(obj, i) {
		var merchandiseCode = obj.merchandiseCode;
		var merchandiseName = obj.merchandiseName;
		var supplierCode = obj.supplierCode;
		var supplierName = obj.supplierName;
		if (!obj.xcsqDate) {
			obj.xcsqDate = null;
		}
		if (!obj.pkxcDate) {
			obj.pkxcDate = null;
		}
		if (!obj.bzsjsqDate) {
			obj.bzsjsqDate = null;
		}
		if (!obj.yjbgtjDate) {
			obj.yjbgtjDate = null;
		}
		if (!obj.yjbgwcDate) {
			obj.yjbgwcDate = null;
		}
		if (!obj.zsjsqDate) {
			obj.zsjsqDate = null;
		}
		if (!obj.zsjsqwcDate) {
			obj.zsjsqwcDate = null;
		}
		if (!obj.htqdDate) {
			obj.htqdDate = null;
		}
		if (!obj.bbtgDate) {
			obj.bbtgDate = null;
		}
		if (!obj.qdgpDate) {
			obj.qdgpDate = null;
		}
		if (!obj.gyssdgpDate) {
			obj.gyssdgpDate = null;
		}
		if (!obj.gzysqrDate) {
			obj.gzysqrDate = null;
		}
		if (!obj.dyqrDate) {
			obj.dyqrDate = null;
		}
		if (!obj.spdddcDate) {
			obj.spdddcDate = null;
		}
		if (!obj.ssDate) {
			obj.ssDate = null;
		}
		var msg = "";
		if(!merchandiseCode || !merchandiseName || !supplierCode
				|| !supplierName) {
			msg = "第" + (i + 1) + "行中的必填项缺少数据;";
		}
		if(obj.remarks.length > 333) {
			msg += "第" + (i + 1) + "行中填写的备注信息过长!";
		}
		return msg;
	},
	
	//关闭功能
	close:function() {
		parent.pubTab.closeCurrTab();
	},
	
	//结束编辑状态
	endEditing:function(){
//		 $('#appScheOldGrid').datagrid('endEdit', editIndex);//结束在编辑的行
		$('#appScheOldGrid').datagrid('unselectRow', editIndex);//结束在编辑的行
		return true;
		/* 校验改行
		 * if (editIndex == undefined){return true;}
		 alert(editIndex);
		 if ($('#appScheOldGrid').datagrid('validateRow', editIndex)){
			 var ed = $('#appScheOldGrid').datagrid('getEditor', {index:editIndex,field:'factoryNo'});
			 var factoryName = $(ed.target).combobox('getText');
			 $('#appScheOldGrid').datagrid('getRows')[editIndex]['factoryName'] = factoryName;
			 $('#appScheOldGrid').datagrid('endEdit', editIndex);
			 editIndex = undefined;
			 return true;
		 } else {
			 return false;
		 }*/
	 },
	
	 //关闭消息dlg
	 closeErrDlg : function() {
		$("#errOldMsg").html("");
		$("#msgOldDlg").dialog('close');
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