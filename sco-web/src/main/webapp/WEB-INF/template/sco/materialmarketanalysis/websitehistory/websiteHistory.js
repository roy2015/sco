var webHisGrid=null;
var clearSearch = false;//清空查询
var firstIn = true;
$(document).ready(function(){
	$("#materialSmallTypeCode").combobox({
		onChange : function() {
			webHisFn.smallTypeChange();
		}
	});
	$("#materialCode").combobox({
		onChange : function() {
			webHisFn.mNameChange();
		}
	});
	
	webHisGrid=utils.grid($('#webHis_Grid'));
	webHisGrid.registerExtFilters('materialBigTypeCode', 'materialSmallTypeCode', 'materialCode', 'webName',
			'region', 'startYear', 'endYear');
	webHisGrid.initFilters({
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
					var param = webHisGrid.getFilterValue();
					if($.param(param).length > 0){
						return true;
					}
					$.messager.alert('提示', '请输入至少一项查询条件');
					return false;
				}
			}
		}
	});
	
});

var webHisFn = {
	
	//变化值小类
	smallTypeChange:function() {
		$("#materialCode").combobox("setValue", "");
		$("#materialCode").combobox("clear");
		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
		}
		$("#materialCode").combobox('reload', "websiteMaterial_listMaterialNameOption_5.html?" +
				"materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode);
	},
	
	//原料名称变动
	mNameChange:function() {
		$("#region").combobox("setValue", "");
		$("#region").combobox("clear");
		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		var materialCode = $("#materialCode").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode || !materialCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$("#region").combobox('reload', "websiteMaterial_listMaterialRegionOption_5.html?" 
				+ "materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode
				+ "&materialCode=" + materialCode);
	
		webHisFn.setWebName();
	},
	
	//设置网站名称
	setWebName:function(){
		$("#webName").combobox("setValue", "");
		$("#webName").combobox("clear");
		var materialBigTypeCode = $("#materialBigTypeCode").combobox("getValue");
		var materialSmallTypeCode = $("#materialSmallTypeCode").combobox("getValue");
		var materialCode = $("#materialCode").combobox("getValue");
		if (!materialBigTypeCode || !materialSmallTypeCode || !materialCode) {
			materialBigTypeCode = 'null';
			materialSmallTypeCode = 'null';
			materialCode = 'null';
		}
		$("#webName").combobox('reload', "websiteMaterial_listMaterialWebNameOption_5.html?" 
				+ "materialBigTypeCode=" + materialBigTypeCode + "&materialSmallTypeCode=" + materialSmallTypeCode
				+ "&materialCode=" + materialCode);
	
	},
	
	//查看历史价格
	searchHisPrice:function() {
		webHisFn.searchData("websiteHistory_listHisPrice_1.html", 1);
	},
	
	flag:true,
	merGrid:null,
	searchData:function(url, panel) {
		
		var obj = webHisGrid.getFilterValue();
		var param = $.param(obj);
		if (param.split("&").length < 7) {
			window.parent.$.messager.alert("提示", "请输入必填条件");
			return;
		}
		showLoading();
		var arrShow =$("#showChart")[0]; 
		if ($($("#showChart")[0]).attr("checked") == "checked") obj.showChart = "true";

		var param = $.param(obj);
		$.post(url, param, function(data) {
			$("#dataTab").html(data);
			if (panel == 2) {
				$("#dataTab").css("height", "300px");
				webHisFn.merGrid = utils.grid($('#webHisMer_grid'));
				webHisFn.merGrid.registerExtFilters('materialBigTypeCode', 'materialSmallTypeCode', 'materialCode', 'webName',
						'region');
				webHisFn.merGrid.initFilters({
					onBeforeLoad:function(obj) {
						if (webHisFn.flag){
							webHisFn.flag = false;
							return false;
						}
					}
				});
				var param = webHisFn.merGrid.getFilterValue();
				$('#webHisMer_grid').datagrid('load', param);
				$("#showPanel").val(2);
			} else {
				webHisFn.flag = true;
				$("#dataTab").css("height", "auto");
				$("#showPanel").val(1);
			}
			$("#searchPara").css("display","block");
			webHisFn.fillSearchPara();
			$('.msg_bg').remove();
		}, "html");
	},
	
	//填写表格上方条件
	fillSearchPara:function() {
		//表格上方条件
		$("#matBigType").html($("#materialBigTypeCode").combobox("getText"));
		$("#matSmType").html($("#materialSmallTypeCode").combobox("getText"));
		$("#matName").html($("#materialCode").combobox("getText"));
		$("#sitName").html($("#webName").combobox("getText"));
		$("#reginName").html($("#region").combobox("getText"));
	},
	
	//设置图表所需参数
	getChartData:function(data,flag) {
		var lengendArr = new Array();
		var seriesArr = new Array();
		$(data).each(function(i, obj) {
			lengendArr[i] = flag == 1 ? obj.priceYear : obj.priceYear + "同比增长";
			var pArr =  new Array();
			$(obj.list).each(function(j, l){
				if (flag == 1) {//图表1为价格
					if (l.price != null) {
						pArr[j] = (l.price).toFixed(2);
					}
				} else {//图表2为增长率
					if (l.incParcent != null) {
						pArr[j] = (l.incParcent*100).toFixed(2);
					}
				}
			});
			seriesArr[i] = {
				name : flag == 1 ? obj.priceYear : obj.priceYear + "同比增长",
				type : 'line',
				data : pArr
			};
		});
		return new Array(lengendArr, seriesArr);
	},
	
	//创建图表
	createChart:function(obj,id) {
		var formatYVal = '{value}';
		var formatLab = '';
		if (id == 'hisInc') {
			formatYVal = '{value}%';
			formatLab = '%';
		}
		var option = {
				  title : {
				      text: '',
				      subtext: ''
				  },
				  tooltip : {
				      trigger: 'item',
				      formatter:function(obj){
		            	return obj[0] + '<br>' + obj[1] + ':' + obj[2] + formatLab;//光标移入图像时显示数据
				      }
				  },
				  legend: {
				      data : obj[0]
				  },
				  xAxis : [
				      {
				          type : 'category',
				          boundaryGap : false,
				          data : ['1','2','3','4','5','6','7','8','9','10','11','12']
				      }
				  ],
				  yAxis : [
				      {
				          type : 'value',
				          boundaryGap: [0, 0], // 坐标轴两端空白策略，数组内数值代表百分比
				          axisLabel : {
				        	  formatter : formatYVal
						  }
				      }
				  ],
				  series : obj[1]
			};
			require.config({
				paths : {
					echarts : 'js/echarts'
				}
			});
			require([ 'echarts', 'echarts/chart/line' ], function(ec) {
				var myChart = ec.init(document.getElementById(id));
				myChart.setOption(option);
			});
	},
	
	//图表1
	showHisPrice:function(data) {
		var obj = webHisFn.getChartData(data, 1);
		webHisFn.createChart(obj, "hisPrice")
	},
	
	//图表2
	hisInc:function(data) {
		var obj = webHisFn.getChartData(data, 2);
		webHisFn.createChart(obj, "hisInc")
	},
	
	//清空查询
	clearSearch:function() {
		if ($("#showPanel").val() != 2){
			$("#dataTab").html("");
			$("#webHis_search").form('reset');
			clearComboboxOptions('materialCode', 'webName', 'region');
			$("#searchPara").css("display","none");
		} else {
			$("#webHis_search").form('reset');
			clearComboboxOptions('materialCode', 'webName', 'region');
			webHisFn.flag = true;
			webHisFn.merGrid.clearFilter();
			webHisFn.flag = false;
			$('.datagrid-sort-desc,.datagrid-sort-asc')
				.removeClass('datagrid-sort-desc datagrid-sort-asc');
			$('#webHisMer_grid').datagrid('loadData', {total:0, rows:[]});
			$("#showPanel").val(2);
		}
		webHisFn.fillSearchPara();
	},
	
	//选择开始年份
	selectStartYear:function(val) {
		if (val > $("#endYear").val()) {
			$("#endYear").val(val);
		}
	},

	//选择结束年份
	selectEndYear:function(val) {
		if (val < $("#startYear").val()) {
			$("#startYear").val(val);
		}
	},
	
	//导出数据
	exportData:function() {
		var val = $("#showPanel").val();
		if(!val) {
			window.parent.$.messager.alert('提示', '请先执行查询功能');
			return;
		}
		var obj = webHisGrid.getFilterValue();
		var param = $.param(obj);
		if (param.split("&").length < 7) {
			window.parent.$.messager.alert("提示", "请输入必填条件");
			return;
		}
		$("#matSmType").html($("#materialSmallTypeCode").combobox("getText"));
		$("#matName").html($("#materialCode").combobox("getText"));
		$("#sitName").html($("#webName").combobox("getText"));
		$("#reginName").html($("#region").combobox("getText"));
		
		var url = 'websiteHistory_exportHisPrice_6.html';
		if(val == 2) {
			var param = "";
			if (webHisFn.merGrid != null) {
				param = $.param(webHisFn.merGrid.getFilterValue());
			}
			var data1 = $('#webHisMer_grid').datagrid('getRows');
			if (data1.length < 1) {
				window.parent.$.messager.alert('提示', '当前未查询到任何数据');
				return;
			}
			url = "websiteHistory_exportHisMerchandise_6.html?" + param;
		}
		
		$.messager.show({
			title:'提示',
			msg:'数据导出中,请稍后...',
			timeout:5000,
			showType:'slide'
		});
		
		$("#webHis_search").form('submit', {
			url : url,
			onSubmit : function(param) {
				param.materialBigTypeName = $("#materialBigTypeCode").combobox("getText");
				param.materialSmallTypeName = $("#materialSmallTypeCode").combobox("getText");
				param.materialName = $("#materialCode").combobox("getText");
				param.websiteName = $("#webName").combobox("getText");
				param.priceRegionName = $("#region").combobox("getText");
				param.startYear = $("#startYear").val();
				param.endYear = $("#endYear").val();
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
				}
			}
		});
	},
	
	//保存文件
	saveData:function() {
		var val = $("#showPanel").val();
		if(!val) {
			window.parent.$.messager.alert('提示', '请先执行查询功能');
			return;
		}
		
		var obj = webHisGrid.getFilterValue();
		if ($.param(obj).split("&").length < 7) {
			window.parent.$.messager.alert("提示", "请输入必填条件");
			return;
		}
		
		if(val == 2) {
			var data1 = $('#webHisMer_grid').datagrid('getRows');
			if (data1.length < 1) {
				window.parent.$.messager.alert('提示', '当前未查询到任何数据');
				return;
			}
		}
		$("#weHisSaveFileDlg").window('open');
	},
	
	// 提交填写文件名称的对话框
	submitSaveFileDlg:function() {
		var fileName = $.trim($("#webHisFileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			return;
		}
		var url = "";
		if ($("#showPanel").val() == 1) {
			var obj = webHisGrid.getFilterValue();
			if ($($("#showChart")[0]).attr("checked") == "checked") obj.showChart = "true";

			url = "websiteHistory_saveHistoryPrice_2.html?" +  $.param(obj);
		} else {
			var param = "";
			if (webHisFn.merGrid != null) {
				param = $.param(webHisFn.merGrid.getFilterValue());
			}
			url = "websiteHistory_saveHistoryMerchandise_2.html?" + param;
		}
		
		$("#saveFile").linkbutton("disable");
		$.post(url, {
			fileName : fileName,
			materialBigTypeName : $("#materialBigTypeCode").combobox("getText"),
			materialSmallTypeName : $("#materialSmallTypeCode").combobox("getText"),
			materialName : $("#materialCode").combobox("getText"),
			websiteName : $("#webName").combobox("getText"),
			priceRegionName : $("#region").combobox("getText"),
			startYear : $("#startYear").val(),
			endYear : $("#endYear").val()
		}, function(data) {
			var json = $.parseJSON(data);
			var msg = json.msg;
			if (json.success) {// 成功
				$("#webHisFileName").val("");// 清空填写的值
				$("#weHisSaveFileDlg").window('close');// 关闭窗口
				$.messager.show({
					title : '提示',
					msg : msg
				});
			} else {// 失败
				window.parent.$.messager.alert("提示", msg);
			}
			$("#saveFile").linkbutton("enable");
		});
	},
	
	//关闭界面
	closeSaveFileDlg:function() {
		$("#webHisFileName").val("");//清空填写的值
        $("#weHisSaveFileDlg").window('close');//关闭窗口
	},
	
	//查看商品列表
	searchMer:function() {
		if ($("#showPanel").val() != 2){
			webHisFn.searchData("websiteHistory_showSearchMerMain_1.html", 2);
		} else {
			var obj = webHisGrid.getFilterValue();
			var param = $.param(obj);
			if (param.split("&").length < 7) {
				window.parent.$.messager.alert("提示", "请输入必填条件");
				return;
			}
			var param = webHisFn.merGrid.getFilterValue();
			$('#webHisMer_grid').datagrid('load', param);
			webHisFn.fillSearchPara();
		}
	}
		
};