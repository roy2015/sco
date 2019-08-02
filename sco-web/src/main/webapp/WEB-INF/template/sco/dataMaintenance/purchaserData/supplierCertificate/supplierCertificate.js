var supCertifiGrid=null;
var clearSearch = false;//清空查询
var firstIn = true;
$(document).ready(function(){
	supCertifiGrid=utils.grid($('#supplierCertificate_grid'));
	supCertifiGrid.registerExtFilters('supplierCode', 'supplierName', 'certificateTypeCode', 'endDate', 'createby');
	supCertifiGrid.initFilters({
		onBeforeLoad:function(obj) {
			// 清空查询
			if(clearSearch) {
				return true;
			}  else if(firstIn) {// 第一次进入
				firstIn = false;
				return false;
			} else {// 显示已存在的网站数据时
				var length = $.param(obj).split("&").length;
				// 判断参数个数
				if (length < 3) {
					if(!firstIn) $.messager.alert('提示', '请输入至少一项查询条件');
					return false;
				}
				if(length == 4 && $.param(obj).indexOf('order') > -1) {
					var param = supCertifiGrid.getFilterValue();
					if($.param(param).length > 0){
						return true;
					}
					$.messager.alert('提示', '请输入至少一项查询条件');
					return false;
				}
			}
		},
		onLoadSuccess:function(){
			$('#supplierCertificate_grid').datagrid("clearSelections");
			supCertifiFn.setButtonState();
		},
		onClickRow : supCertifiFn.setButtonState, //设置按钮状态
		onCheck : supCertifiFn.setButtonState,
		onUncheck : supCertifiFn.setButtonState,
		onCheckAll : supCertifiFn.setButtonState,
		onUncheckAll : supCertifiFn.setButtonState
	});
	
});

var supCertifiFn = {
	
	//查询数据
	search:function() {
		var param = supCertifiGrid.getFilterValue();
		$('#supplierCertificate_grid').datagrid('load', param);
	},	
	
	downloadFile:function(value, row, index) {
		return '<a href=javascript:void(0) onClick=supCertifiFn.downloadOriginalFile("'+encodeURIComponent(row.path)+'")>'+ value +'</a><br>';
	},
	
	downloadOriginalFile:function(filePath) {
		window.location = 'supplierCertificate_downloadOriginalFile_6.html?path='+filePath;
	},
	
	//显示添加或修改窗体
	add:function(){
		var title='上传证件';
		var href='supplierCertificate_showInsertSupplierCertificateForm_1.html';
		var dlg=utils.showDlg({
			title:title,href:href,width:500,height:250,
			buttons:[{text:'确定上传',id:'sbt',handler:function(){supCertifiFn.submitForm(dlg);},iconCls:'save'},
			        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'close'}
			],onLoad:function() {
				var recordGridUtil=utils.grid($('#supplier_grid'));
				recordGridUtil.initFilters({
					noParamCanSort:true,
					url:'supplierData_listMasterSupplier_2.html',
					onBeforeLoad:supCertifiFn.initSupplierCombox
				});
				supCertifiFn.setValidDate(true);
			}
		});
	},
	
	//初始供应商下拉框
	initSupplierCombox:function(){
		$('#supplierIdName').combo({
			required:true,
			editable:false,
			panelWidth:360,
			panelHeight:370
		});
		$('#supInput').appendTo($('#supplierIdName').combo('panel'));

//		$('#supplierIdName').combo('setValue', $("#supplierIdName").combobox('getValue'))
//			.combo('setText',  $("#supplierIdName").combobox('getText'));
	},
	
	onSelectSupplier:function(i,data) {
		$('#supplierIdName').combo('setValue', data.id)
			.combo('setText', data.id + '-' + data.text).combo('hidePanel');
	},
	
	//选中证件是否存在有效期
	setValidDate:function(ifShow) {
		var len = $("tr[id=validDate]").length;
		if(ifShow && len == 0) {
			var tr =  
				"<tr id='validDate'>"+
					"<td></td>"+
					"<td style='text-align:left;'>"+
						"<input name='startDate' id='minDate' style='width:97px;float:left'/>"+
						"至"+
						"<span id='max'><input name='endDate' id='maxDate' style='width:97px;'/></span>"+
						"<label><input type='radio' id='isperpetual' name='isperpetual' value='Y' onclick='supCertifiFn.display()' style='width:10px;display: inline-block;'>永久</label>"+
					"</td>"+
				"</tr>";
			$(tr).insertAfter($('#addDate'));
			
			$('#minDate').datebox({
				editable:false,
				required:true,
				onHidePanel:utils.dateFilter.setMaxDateValue
			});
			$('#maxDate').datebox({
				editable:false,
				required:true,
				onHidePanel:utils.dateFilter.setMinDateValue
			});
			
		} else if(!ifShow && len == 1) {
			$('#validDate').remove();
		}
	},
	
	//隐藏证件最大的有效日期
	display:function() {
		var val = $("#showRegion").val();
		if(val == "1") {
			$("#max").css("display", "none");
			$("#showRegion").val("2");
		} else {
			$("#max").css("display", "inline-block");
			$("#isperpetual").attr("checked", false);
			$("#showRegion").val("1");
		}
	},
	
	//提交新增或修改表单
	submitForm:function(dlg){
		if(!$('#supplierCertificate_form').form('validate')) return ;
		var file = $('#upload').val();
		if(file == '') {
			$.messager.alert("提示", "请选择需上的证件文件");
			return;
		}
		$("a[id='sbt']").linkbutton('disable');
		showLoading();
		var url = 'supplierCertificate_insertSupplierCertificate_2.html';
		utils.form("supplierCertificate_form").submit(url,null,function(){
			$('.msg_bg').remove();
			dlg.dialog('close');
			var param = supCertifiGrid.getFilterValue();
			clearSearch = true;
			$('#supplierCertificate_grid').datagrid('load', param);
			clearSearch = false;
		}, function(){
			$('.msg_bg').remove();
			$("a[id='sbt']").linkbutton("enable");
		});
	},
	
	//删除供应商证件 
	remove:function(){
		var rows = $('#supplierCertificate_grid').datagrid('getSelections');
		if (rows.length < 1 ) {
			$.messager.alert('提示', '请勾选至少一张签量单');
			return; 
		} 
		var certificateCodes = '';
		for (var i in rows) {
			certificateCodes += "'" + rows[i].certificateCode + "',";
		}
		certificateCodes = certificateCodes.substring(0, certificateCodes.length - 1);
		utils.confirm('操作确认','确认删除供应商证件 ?',function(){
			utils.post('supplierCertificate_deleteSupplierCertificate_2.html',
					{certificateCodes:certificateCodes},function(){
				clearSearch = true;
				var param = supCertifiGrid.getFilterValue();
				$('#supplierCertificate_grid').datagrid('load', param);
				clearSearch = false;
			});
		});
	},
	
	//清除查询
	clearFilter:function(){
		clearSearch = true;
//		supCertifiGrid.clearFilter();//datagrid会重新查询
		$('#supCerti_search').form('reset');
		$('.datagrid-sort-desc,.datagrid-sort-asc')
			.removeClass('datagrid-sort-desc datagrid-sort-asc');
		$('#supplierCertificate_grid').datagrid('clearSelections');
		$('#supplierCertificate_grid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
	},
	
	//设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#supplierCertificate_grid').datagrid('getSelections');
		if (rows.length == 1) {
			$("a[id='remove']").linkbutton('enable');
		} else if (rows.length > 1){
			$("a[id='remove']").linkbutton('enable');
		} else {
			$("a[id='remove']").linkbutton('disable');
		}	
	}
	
};