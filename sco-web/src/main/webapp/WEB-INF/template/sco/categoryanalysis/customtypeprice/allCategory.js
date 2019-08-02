var cusAllPrice1 = null;
var cusAllPrice2 = null;

$(document).ready(function() {
	cusAllPrice1 = utils.grid($('#cusAllPrice1'));
	cusAllPrice1.initFilters({
		onLoadSuccess : function(data) {
			cusAllFn.allShowChart(data.rows, "1");
			$(".datagrid-mask").remove();
			$(".datagrid-mask-msg").remove();
		}
	});
	$('#cusAllPrice1').datagrid('loadData', {total:'${paraMap.d1Size}', rows:${paraMap.data1Json}});
	
	cusAllPrice2 = utils.grid($('#cusAllPrice2'));
	cusAllPrice2.initFilters({
		onLoadSuccess : function(data) {
			if (data.rows.length < 1 && !'${paraMap.flag2}') $("#allGrid2").hide();
			cusAllFn.allShowChart(data.rows, "2");
		}
	});
	$('#cusAllPrice2').datagrid('loadData', {total:'${paraMap.d2Size}', rows:${paraMap.data2Json}});
	
});

var cusAllFn = {

	//关闭当前页面
	closePage : function() {
		parent.pubTab.closeCurrTab();
	},
	
	// 百分比格式
	formatPercent : function(val) {
		if (!val || val == 0) {
			return '0.00%';
		} else {
			val = (val * 100).toFixed(2);
			return val + '%';
		}
	},

	// 大于1显示100%
	formatBigPercent : function(val) {
		if (val >= 1)
			return '100.00%';
		return cusAllFn.formatPercent(val);
	},
	
	//处理json对象
	replaceJsonStr:function() {
		var param = $("#allList").val().replace(new RegExp(/(%)/g), "\"");
		return JSON.stringify(JSON.parse(param)).replace(/"([^"]*)"/g, "'$1'");
	},

	//显示消息
	showMsg : function(msg) {
		$.messager.show({
			title:'提示',
			msg:msg,
			timeout:5000,
			showType:'slide'
		});
	},
	
	// 导出到Excel
	exportToExcel : function() {
		var data1 = $('#cusAllPrice1').datagrid('getRows');
		var data2 = $('#cusAllPrice2').datagrid('getRows');
		// 是否查出数据
		if (data1.length < 1 && data2.length < 1) {
			window.parent.$.messager.alert('提示', '当前未查询到任何数据');
			return;
		}
		
		cusAllFn.showMsg('数据导出中,请稍后...');
		
		$("#cusAll_form").form('submit', {
			url : "customTypePrice_exportAllCategoryToExcel_6.html",
			onSubmit : function(param) {
				param.rows = cusAllFn.replaceJsonStr();
			},
			success : function(data) {
				var json = $.parseJSON(data);
				if (json.success) {
					
				}else{
					window.parent.$.messager.alert("提示", "导出文件出错");
				}
			}
		});
	},

	// 填写保存文件名称
	saveFile : function() {
		var data1 = $('#cusAllPrice1').datagrid('getRows');
		var data2 = $('#cusAllPrice2').datagrid('getRows');
		// 是否查出数据
		if (data1.length < 1 && data2.length < 1) {
			window.parent.$.messager.alert('提示', '当前未查询到任何数据');
			return;
		}

		$("#cusAllFileDlg").window('open');// 打开窗口
	},

	// 提交填写文件名称的对话框
	submitSaveFileDlg : function() {
		var fileName = $.trim($("#cusAllFileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			return;
		}
		$("#saveFile").linkbutton("disable");
		$("#cusAll_form").form('submit', {
			url : "customTypePrice_saveAllCategory_2.html",
			onSubmit : function(param) {
				param.fileName = fileName;
				param.rows = cusAllFn.replaceJsonStr();
			},
			success : function(data) {
				var json = $.parseJSON(data);
				if (json.success) {
					$("#cusAllFileName").val("");// 清空填写的值
					$("#cusAllFileDlg").window("close");// 关闭窗口
					cusAllFn.showMsg(json.msg);
				}else{
					window.parent.$.messager.alert("提示", json.msg);
				}
				$("#saveFile").linkbutton("enable");
			}
		});
	},

	// 关闭界面
	closeSaveFileDlg : function() {
		$("#cusAllFileName").val("");// 清空填写的值
		$("#cusAllFileDlg").window('close');// 关闭窗口
	},

	// 所有商品价格带统计表
	allShowChart : function(data, index) {
		if (data.length < 1) {
			return;
		}
		$('#cusAllChart' + index).show();
		var lengendArr = new Array();
		var seriesArr = new Array();
		$(data).each(function(i, obj) {
			var skuUnits = obj.skuUnits;
			lengendArr[i] = obj.regionPriceStr;// 所有组
			
			//格式化位数
			var oskuPercent = subStrLength(obj.skuPercent * 100);
			var osoldPercent = subStrLength(obj.soldPercent * 100);
			var osoldPricePercent = subStrLength(obj.soldPricePercent * 100);
			var ogrossProfitPenrcent = subStrLength(obj.grossProfitPenrcent * 100);
			var ovitality = (obj.vitality > 1 ? 100 : subStrLength(obj.vitality * 100));
			var opowerShopDayPercent = subStrLength(obj.powerShopDayPercent * 100);
			var osoldShopPercent = subStrLength(obj.soldShopPercent * 100);
			var onotLessUnitsSku = subStrLength((obj.notLessPsdUnitsSku / skuUnits) * 100);
			var onotLessSoldPrice = subStrLength((obj.notLessPsdSoldPrice / skuUnits) * 100);
			var onotLessGrossProfit = subStrLength((obj.notLessPsdGrossProfit / skuUnits) * 100);
			
			seriesArr[i] = {
				name : obj.regionPriceStr,
				type : 'bar',
				itemStyle : {
					normal : {
						label : { show : true, formatter : function(obj) {
							if (obj.value == 0 || !obj.value) {
								return "0.00%";
							}
							return new Number(obj.value).toFixed(2) + "%";
							}
						}
					// 柱子顶上显示值
					}
				},
				data : [
						oskuPercent, osoldPercent, osoldPricePercent, ogrossProfitPenrcent,
						ovitality, opowerShopDayPercent, osoldShopPercent, onotLessUnitsSku,
						onotLessSoldPrice,onotLessGrossProfit
					   ]
			};
		});
		
		require.config({
			paths : {
				echarts : 'js/echarts'
			}
		});

		require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
			var lineDom = $("#cusAllBar" + index)[0];
			barOption = {
				// color:['#3cb371', '#b8860b', '#30e0e0' ],
				title : {
					text : '',
					subtext : ''
				},
				tooltip : {
					trigger : 'item',
					formatter : function(obj) {
						return obj[0] + '<br>' + obj[1] + ":" + obj[2] + "%";// 光标移入图像时显示数据
					}
				},
				legend : {
					itemGap : 10,
					data : lengendArr
				},
				calculable : false,
				xAxis : [ {
					type : 'category',
					boundaryGap : true,
					data : [ 'SKU数量占比', '销量占比', '销售金额占比', '毛利额占比', '活跃度', '权限店天占比',
							'销售店天占比', '大于等于PSD销量的SKU数占比', '大于等于PSD销售额的SKU数占比',
							'大于等于PSD毛利额的SKU数占比' ]
				} ],
				yAxis : [ {
					type : 'value',
					boundaryGap : [ 0, 0 ], // 坐标轴两端空白策略，数组内数值代表百分比
					axisLabel : {
						formatter : '{value}%'
					}
				} ],
				series : seriesArr
			};
			var chart = ec.init(lineDom);
			chart.setOption(barOption);
		});
	}
	
};