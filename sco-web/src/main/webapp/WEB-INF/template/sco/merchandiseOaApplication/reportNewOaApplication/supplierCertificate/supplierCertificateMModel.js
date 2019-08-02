var supplierCertificateMGridUtil = null;
var supplierCertificateMGridUtilGL = null;
$(document).ready(function() {
	supplierCertificateMGridUtil = utils.grid($('#supplierCertificateM_grid'));
	supplierCertificateMGridUtilGL=utils.grid($('#supplierCertificateM_grid_gl'));
});
var supplierCertificateMFn = {
	// 上传（添加）
	add : function() {
		var rows = $('#supplierCertificateM_grid').datagrid("getSelections");
		if (rows.length != 1) {
			window.parent.$.messager.alert('提示', '请选择一个供应商');
			return;
		}
		var supplierCode = rows[0].intentionSupplierCode;
		if (rows[0].intentionSupplierCode == null || rows[0].intentionSupplierCode == "") {
			supplierCode = rows[0].supplierCode;
		}
		var title = '上传证件';
		var href = 'supplierCertificateM_showInsertSupplierCertificateMForm_1.html?supplierCode=' + supplierCode;
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 410,
			height : 250,
			buttons : [ {
				text : '确定上传',
				id : 'sbt',
				handler : function() {
					supplierCertificateMFn.submitForm(dlg);
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
		var rows = $('#supplierCertificateM_grid').datagrid("getSelections");
		if (rows.length != 1) {
			window.parent.$.messager.alert('提示', '请选择一个供应商');
			return;
		}
		var supplierCode = rows[0].intentionSupplierCode;
		if (rows[0].intentionSupplierCode == null || rows[0].intentionSupplierCode == "") {
			supplierCode = rows[0].supplierCode;
		}
		var title = '查看系统证件库';
		var href = 'supplierCertificateM_showSupplierCertificateMForm_1.html?supplierCode=' + supplierCode;
		var dlg = utils.showDlg({
			title : title,
			href : href,
			width : 630,
			height : 350,
			buttons : [ {
				text : '确定选用',
				id : 'sbt',
				handler : function() {
					supplierCertificateMFn.submitFormSCM(dlg);
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
		var file = $('#upload').val();
		if (file == '') {
			window.parent.$.messager.alert("提示", "请选择需上的证件文件");
			return;
		}
		showLoading();
		var url = 'supplierCertificateM_insertSupplierCertificate_2.html?applicationCode=' + $("input#applicationCode").val() + "&intentionAndSupplierCodes="
				+ $("input#intentionAndSupplierCodes").val()+"&applicationType=mn";
		utils.form("supplierCertificate_form").submit(url, null, function() {
			supplierCertificateMGridUtilGL.refresh();
			dlg.dialog('close');
		});
		$(".window-shadow").remove();
		$('.msg_bg').remove();
	},
	// 提交查看添加
	submitFormSCM : function(dlg) {
		var rows = $("#supplierCertificate_grid").datagrid('getSelections');
		var row=$("#supplierCertificate_grid").datagrid('getSelections');
		if (row.length <1) {
			window.parent.$.messager.alert('提示', '请选择一条证件');
			return;
		}
		var applicationCode = "";
		var certificateCode = "";
		var supplierCode = "";
		for ( var i in rows) {
			applicationCode += $("input#applicationCode").val() + ",";
			supplierCode += rows[i].supplierCode + ",";
			certificateCode += rows[i].certificateCode + ",";
		}
		var url = "supplierCertificateM_insertSupplierCertificateM_2.html?applicationCode=" + applicationCode + "&certificateCode=" + certificateCode + "&supplierCode=" + supplierCode
				+ "&intentionAndSupplierCodes=" + $("input#intentionAndSupplierCodes").val()+"&applicationType=MERCHANDISE_NEW";
		utils.post(url, null, function() {
			supplierCertificateMGridUtilGL.refresh();
			dlg.dialog('close');
		});
	},
	// 日期框改变
	setValidDate : function(ifShow) {
		var len = $("tr[id=validDate]").length;
		if (ifShow && len == 0) {
			var tr = "<tr id='validDate'>" + "<td></td>" + "<td style='text-align:left;'>" + "<input name='startDate' id='minDate' style='width:97px;float:left'/>" + "至"
					+ "<span id='max'><input name='endDate' id='maxDate' style='width:97px;'/></span>"
					+ "<label><input type='radio' id='isperpetual' name='isperpetual' value='Y' onclick='supplierCertificateMFn.display()' style='width:10px;display: inline-block;'>永久</label>"
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
		var rows = $('#supplierCertificateM_grid_gl').datagrid("getSelections");
		if (rows.length < 1) {
			window.parent.$.messager.alert('提示', '请选择证件');
			return;
		}
		var supplierCode = "";
		var applicationCode = rows[0].applicationCode;
		var certificateCode = "";
		var paths="";
		for ( var i in rows) {
			if(i!=rows.length-1){
				if (rows[i].intentionSupplierCode == null || rows[i].intentionSupplierCode == "") {
					supplierCode += rows[i].supplierCode + ",";
				} else {
					supplierCode += rows[i].intentionSupplierCode + ",";
				}
				certificateCode += rows[i].certificateCode + ",";
				paths+=encodeURIComponent(rows[i].path)+",";
			}else{
				if (rows[i].intentionSupplierCode == null || rows[i].intentionSupplierCode == "") {
					supplierCode += rows[i].supplierCode;
				} else {
					supplierCode += rows[i].intentionSupplierCode
				}
				certificateCode += rows[i].certificateCode;
				paths+=encodeURIComponent(rows[i].path);
			}
		}
		utils.confirm("操作确认", "确认删除供应商证件件?", function() {
			utils.post("supplierCertificateM_deleteSupplierCertificateM_2.html", {
				applicationCode : applicationCode,
				intentionAndSupplierCodes : $("input#intentionAndSupplierCodes").val(),
				supplierCode : supplierCode,
				paths:paths,
				certificateCode : certificateCode,
				applicationType:"MERCHANDISE_NEW"
			}, function() {
				supplierCertificateMGridUtilGL.refresh();
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
		return '<a href=javascript:void(0) onClick=supplierCertificateMFn.downloadOriginalFile("' + encodeURIComponent(row.path) + '")>' + value + '</a><br>';
	},
	// 下载供应商证件
	downloadOriginalFile : function(filePath) {
		window.location = "supplierCertificateM_downloadOriginalFile_6.html?path=" + filePath;
	},
	formatterDate:function(value,row){
		if(value==null&&row.endDate==null){
		 	return '无有效期'
		 }else if(value!=null&&row.endDate==null){
		 	return value+'- 永久';
		 } else if (value != null && row.endDate != null) {
			return value + '~' + row.endDate;
		}
	}
};
