var supplierCertificateAFn = {
	// 上传（添加）
	add : function() {
		var rows = $('#supplierCertificateA_grid').datagrid("getSelections");
		if (rows.length != 1) {
			$.messager.alert('提示', '请选择一个供应商');
			return;
		}
		var supplierCode = rows[0].intentionSupplierCode;
		if (rows[0].intentionSupplierCode == null || rows[0].intentionSupplierCode == "") {
			supplierCode = rows[0].supplierCode;
		}
		var title = '上传证件';
		var href = 'supplierCertificateA_showInsertSupplierCertificateAForm_1.html?supplierCode=' + supplierCode;
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 410,
			height : 250,
			buttons : [ {
				text : '确定上传',
				id : 'sbt',
				handler : function() {
					supplierCertificateAFn.submitForm(dlg);
				},
				iconCls : 'save'
			}, {
				text : '关闭',
				handler : function() {
					dlg.dialog('close');
				},
				iconCls : 'close'
			} ]
		});
		$(".window-shadow").remove();
	},
	// 查看添加
	addSCM : function() {
		var rows = $('#supplierCertificateA_grid').datagrid("getSelections");
		if (rows.length != 1) {
			$.messager.alert('提示', '请选择一个供应商');
			return;
		}
		var supplierCode = rows[0].intentionSupplierCode;
		if (rows[0].intentionSupplierCode == null || rows[0].intentionSupplierCode == "") {
			supplierCode = rows[0].supplierCode;
		}
		var title = '查看系统证件库';
		var href = 'supplierCertificateA_showSupplierCertificateAForm_1.html?supplierCode=' + supplierCode;
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 630,
			height : 350,
			buttons : [ {
				text : '确定选用',
				id : 'sbt',
				handler : function() {
					supplierCertificateAFn.submitFormSCM(dlg);
				},
				iconCls : 'save'
			}, {
				text : '关闭',
				handler : function() {
					dlg.dialog('close');
				},
				iconCls : 'close'
			} ]
		});
		$(".window-shadow").remove();
		$("#supplierCertificate_grid").datagrid('reload');
	},
	// 从EXCEL导入
	submitForm : function(dlg) {
		if (!$("#supplierCertificate_form").form('validate'))
			return;
		var file = $("#uploadInfo").val();
		if (file == '') {
			$.messager.alert("提示", "请选择需上的证件文件");
			return;
		}
		showLoading();
		var row = $('#supplierCertificateA_grid').datagrid("getSelected");
		var url = "supplierCertificateA_insertSupplierCertificate_2.html?applicationCode=" + $("input#applicationCodeNow").val()+"&type=af&quotedCode="+$("input#quotedCodes").val()+"&quotedCodeScan="+row.quotedCode;
		utils.form("supplierCertificate_form").submit(url, null, function() {
			$("#supplierCertificateA_grid_gl").datagrid("reload");
			dlg.dialog('close');
		});
		$('.msg_bg').remove();
	},
	// 提交查看添加
	submitFormSCM : function(dlg) {
		var rows = $("#supplierCertificate_grid").datagrid('getSelections');
		if (rows.length < 1) {
			$.messager.alert('提示', '请至少选择一个证件');
			return;
		}
		var row = $('#supplierCertificateA_grid').datagrid("getSelected");
		var applicationCode = "";
		var certificateCode = "";
		var supplierCode = "";
		var quotedCodeScan="";
		for ( var i in rows) {
			quotedCodeScan+=row.quotedCode+",";
			applicationCode += $("input#applicationCodeNow").val() + ",";
			supplierCode += rows[i].supplierCode + ",";
			certificateCode += rows[i].certificateCode + ",";
		}
		var url = "supplierCertificateA_insertSupplierCertificateA_2.html?applicationCode=" + applicationCode + 
					"&certificateCode=" + certificateCode + "&supplierCode=" + supplierCode+"&type=af&quotedCode="+$("input#quotedCodes").val()+"&quotedCodeScan="+quotedCodeScan;
		utils.post(url, null, function() {
			$("#supplierCertificateA_grid_gl").datagrid("reload");
			dlg.dialog('close');
		});
	},
	// 日期框改变
	setValidDate : function(ifShow) {
		var len = $("tr[id=validDate]").length;
		if (ifShow && len == 0) {
			var tr = "<tr id='validDate'>" + "<td></td>" + "<td style='text-align:left;'>" + "<input name='startDate' id='minDate' style='width:97px;float:left'/>" + "至"
					+ "<span id='max'><input name='endDate' id='maxDate' style='width:97px;'/></span>"
					+ "<label><input type='radio' id='isperpetual' name='isperpetual' value='Y' onclick='supplierCertificateAFn.display()' style='width:10px;display: inline-block;'>永久</label>"
					+ "</td>" + "</tr>";
			$(tr).insertAfter($("#addDate"));

			$("#minDate").datebox({
				editable : false,
				required : true,
				onHidePanel : utils.dateFilter.setMaxDateValue
			});
			$("#maxDate").datebox({
				editable : false,
				required : true,
				onHidePanel : utils.dateFilter.setMinDateValue
			});
		} else if (!ifShow && len == 1) {
			$("#validDate").remove();
		}
	},
	// 删除供应商证件
	remove : function() {
		var rows = $('#supplierCertificateA_grid_gl').datagrid("getSelections");
		if (rows.length < 1) {
			$.messager.alert('提示', '请选择证件');
			return;
		}
		var supplierCode = "";
		var applicationCode = "";
		var certificateCode = "";
		var paths="";
		for ( var i in rows) {
			applicationCode+=$("input#applicationCodeNow").val()+",";
			if (rows[i].intentionSupplierCode == null || rows[i].intentionSupplierCode == "") {
				supplierCode += rows[i].supplierCode + ",";
			} else {
				supplierCode += rows[i].intentionSupplierCode + ",";
			}
			certificateCode += rows[i].certificateCode + ",";
			paths+=encodeURIComponent(rows[i].path)+",";
		}
		utils.confirm("操作确认", "确认删除供应商证件件?", function() {
			utils.post("supplierCertificateA_deleteSupplierCertificateA_2.html", {
				applicationCode : applicationCode,
				supplierCode : supplierCode,
				certificateCode : certificateCode,
				paths:paths,
				quotedCode:$("input#quotedCodes").val(),
				type:'af'
			}, function() {
				$("#supplierCertificateA_grid_gl").datagrid("reload");
			});
		});
		$(".window-shadow").remove();
	},

	// 隐藏证件最大的有效日期
	display : function() {
		var val = $("#showRegion").val();
		if (val == "1") {
			$("#max").css("display", "none");
			$("#showRegion").val("2");
		} else {
			$("#max").css("display", "inline-block");
			$("#isperpetual").attr("checked", false);
			$("#showRegion").val("1");
		}
	},
	// 改变单元格
	downloadFile : function(value, row, index) {
		return '<a href=javascript:void(0) onClick=supplierCertificateAFn.downloadOriginalFile("' + encodeURIComponent(row.path) + '","' + row.certificateCode + '")>' + value + '</a><br>';
	},
	// 下载供应商证件
	downloadOriginalFile : function(filePath,certificateCode) {
		window.location = "supplierCertificateA_downloadOriginalFile_6.html?path=" + filePath+"&certificateCode="+certificateCode;
	}
};
