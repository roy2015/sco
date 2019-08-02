var merStandProInfoGrid = null;
var clearSearch = false;// 清空查询时无查询参数
var firstIn = true;

$(document).ready(function() {
	merStandProInfoGrid = utils.grid($('#merStandProInfo_grid'));
	merStandProInfoGrid.initFilters({
		noParamCanSort : true,
		onBeforeLoad : function(obj) {
			// 清空查询
			if (firstIn) {
				firstIn = false;
				return true;
			} else if (clearSearch) {
				return true;
			}
		},

		onLoadSuccess : function() {
			$('#merStandProInfo_grid').datagrid("clearSelections");
			merStandProInfoFn.setButtonState();
		},
		onClickRow : merStandProInfoFn.setButtonState, // 设置按钮状态
		onCheck : merStandProInfoFn.setButtonState,
		onUncheck : merStandProInfoFn.setButtonState,
		onCheckAll : merStandProInfoFn.setButtonState,
		onUncheckAll : merStandProInfoFn.setButtonState
	});
});

var merStandProInfoFn = {
		
	//查询
	search : function() {
		var param = merStandProInfoGrid.getFilterValue();
		$('#merStandProInfo_grid').datagrid('load', param);
	},
	
	//打开新增、修改窗口
	openDlg : function(title,href,flag) {
		var dlg=utils.showDlg({
			title:title, href:href, width:750,height:450,
			buttons:[{text:'保存',id:'sbt',handler:function(){merStandProInfoFn.submitForm(dlg,flag);},iconCls:'save'},
			        {text:'关闭',handler:function(){dlg.dialog('close');},iconCls:'close'}
			]
		});
	},
	
	//计算流程总天数
	sumProcessDay : function(id, value) {
		if (id != "processDate3" && id != "processDate7") {
			if(value == '') {
				$("input[name='sumDay']").val('');
			} else {
				var v1 = $("#processDate1").val();
				var v12 = $("#processDate12").val();
				//线路1
				var v4 = $("#processDate4").val();
				var v5 = $("#processDate5").val();
				var v8 = $("#processDate8").val();
				var v10 = $("#processDate10").val();
				
				//线路2
				var v2 = $("#processDate2").val();
				var v6 = $("#processDate6").val();
				var v9 = $("#processDate9").val();
				var v11 = $("#processDate11").val();
				
				if (v1 == "" || v12 == "" || v4 == "" || v5 == "" || v8 == "" || v10 == "" 
					|| v2 == "" || v6 == "" || v9 == "" || v11 == "") {
					$("input[name='sumDay']").val();
				} else {
					var sum1 = new Number((new Number(v4)).toFixed(0)) + new Number((new Number(v5)).toFixed(0))
						+ new Number((new Number(v8)).toFixed(0)) + new Number((new Number(v10)).toFixed(0));
					var sum2 = new Number((new Number(v2)).toFixed(0)) + new Number((new Number(v6)).toFixed(0))
						+ new Number((new Number(v9)).toFixed(0)) + new Number((new Number(v11)).toFixed(0));
					var sum = (sum1 > sum2 ? sum1 : sum2) + new Number(new Number(v1).toFixed(0))
						+ new Number(new Number(v12).toFixed(0));
					if (!isNaN(sum)) {
						$("input[name='sumDay']").val(sum);
					} else {
						$("#" + id).numberbox('clear');
						$("input[name='sumDay']").val('');
					}
				}
			}
		}
	},
	
	//新增
	add : function() {
		merStandProInfoFn.openDlg("新增新品引进标准进度信息", "merStandProInfo_showAddMerStandProInfoForm_1.html", false);
	},
	
	//修改
	edit : function() {
		var rows = $("#merStandProInfo_grid").datagrid("getSelections");
		if (rows.length != 1) {
			$.messager.alert("提示", "请只选择一条数据");
			return;
		}
		merStandProInfoFn.openDlg("修改新品引进标准进度信息", 
				"merStandProInfo_showEditMerStandProInfoForm_1.html?configCode="+rows[0].configCode,true);
	},
	
	//提交对话框
	submitForm:function(dlg,flag) {
		var url = "merStandProInfo_insertMerStandProInfo_2.html";
		if (flag) url = "merStandProInfo_updateMerStandProInfo_2.html";
		utils.form("standPro_form").submit(url,null,function(){
			dlg.dialog('close');
			merStandProInfoFn.search();
			clearSearch = false;
		});
	},
	
	//删除
	remove : function(){
		var configCodes = merStandProInfoGrid.getSelectedIdArr("configCode");
		if (configCodes.length < 1 ) {
			$.messager.alert('提示', '请选择至少一条记录');
			return; 
		}
		var configCode = "";
		for (var i in configCodes) {
			configCode += "'" + configCodes[i] + "',";
		}
		utils.confirm("提示","确认删除所选记录 ?",function(){
			utils.confirm("提示","是否最终确认删除所选记录 ?",function(){
				utils.post("merStandProInfo_deleteMerStandProInfo_2.html",
						{configCode:configCode.substring(0, configCode.length - 1)},function(){
					clearSearch = true;
					merStandProInfoFn.search();
					clearSearch = false;
				});
			});
		});
	},
	
	// 清除查询
	clearFilter : function() {
		$('#merStandProInfo_grid').datagrid('clearSelections');
		$('#merStandProInfo_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
	},

	// 设置按钮状态
	setButtonState : function() {
		var rows = $('#merStandProInfo_grid').datagrid("getSelections");
		if (rows.length == 0) {// 一个都没选择
			$("a[id='edit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
		} else if (rows.length == 1) {// 只选了一个
			$("a[id='edit']").linkbutton("enable");
			$("a[id='insert']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
		} else {// 选了多个
			$("a[id='edit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("enable");
		}
	}

};