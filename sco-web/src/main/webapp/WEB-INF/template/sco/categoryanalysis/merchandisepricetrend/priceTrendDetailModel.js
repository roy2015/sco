$(document).ready(
	function() {
		merchandisePriceDetailGrid = utils.grid($('#merchandisePriceDetail_grid'));
		merchandisePriceDetailGrid.initFilters({
			onLoadSuccess : function() {
			}
		});
	}
);

var priceTrendDetailFn = {
	// 导出到Excel
	export2Excel : function() {
		var merchandiseCode = $("#merchandiseCode").val();
		var regionCode = $("#regionCode").val();
		var itemValue = $("#itemValue").val();
		var qlStartDate = $("#qlStartDate").val();
		var qlEndDate = $("#qlEndDate").val();
		var supplierCode=$("#supplierCode").val();
		url = "merchandisePriceTrend_exportDataToExcel_6.html?merchandiseCode="
				+ merchandiseCode + "&&regionCode=" + regionCode+ "&&supplierCode=" + supplierCode
				+ "&&qlStartDate=" + qlStartDate + "&&qlEndDate=" + qlEndDate
				+ "&&itemValue=" + itemValue;
		window.location = url;
		$.messager.show({
			title : '提示',
			msg : '数据导出中,请稍后...',
			timeout : 4000,
			showType : 'slide'
		});
	},
	// 填写保存文件名称
	saveFile : function() {
		$("#saveFileDlg").window('open');// 打开窗口
	},

	// 关闭界面
	closeSaveFileDlg : function() {
		$("#fileName").val("");// 清空填写的值
		$("#saveFileDlg").window('close');// 关闭窗口
	},

	closeWindow : function() {
		parent.pubTab.closeTab('价格趋势查询结果');
	},

	// 提交填写文件名称的对话框
	submitSaveFileDlg : function() {
		var fileName = $.trim($("#fileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			return;
		}
		$("a[id='save']").linkbutton("disable");
		var merchandiseCode = $("#merchandiseCode").val();
		var regionCode = $("#regionCode").val();
		var itemValue = $("#itemValue").val();
		var qlStartDate = $("#qlStartDate").val();
		var qlEndDate = $("#qlEndDate").val();
		var supplierCode=$("#supplierCode").val();
		$.post(
				"merchandisePriceTrend_saveSearchDataForm_2.html?merchandiseCode="
						+ merchandiseCode + "&&regionCode=" + regionCode
						+"&&supplierCode=" + supplierCode
						+ "&&qlStartDate=" + qlStartDate + "&&qlEndDate="
						+ qlEndDate + "&&itemValue=" + itemValue,{
							fileName : fileName
						}, function(data) {
					var json = $.parseJSON(data);
					var msg = json.msg;
					if (json.success) {// 成功
						$("#fileName").val("");// 清空填写的值
						$("#saveFileDlg").window('close');// 关闭窗口
						$.messager.show({
							title : '提示',
							msg : msg
						});
					} else {// 失败
						$.messager.alert("提示", msg);
					}
					$("a[id='save']").linkbutton("enable");
				});
	}
};

// 售价涨幅、PSD销量涨幅、PSD业绩涨幅、PSD毛利涨幅的数据追加百分号
function formatValue(value) {
	return moneyFormatter(value) + "%";
}
//格式千分位
function formatterUnit(value,row){
	if(value!=null){
		return moneyFormatter(value);
	}else{
		return value;
	}
}
//格式千分位
function formatterUnit1(value,row){
	if(value!=null){
		return formatterCount(value);
	}else{
		return value;
	}
}
$(document).ready(function() {
	grid = utils.grid($('#merchandisePriceDetail_grid'));

	grid.initFilters({
		onLoadSuccess : function(data) {
			optionData(data);
		}
	});
});
var priceChangeDateArr = null, sellPriceGainsArr = null, psdSalesGainsArr = null, psdResultsGainsArr = null, psdGrossProfitGainsArr = null;

function optionData(data) {
	require.config({
		paths : {
			echarts : 'js/echarts'
		}
	});
	recursion(0);
}
var num=0;
function recursion(i){
	var merchandiseCodes = $("#merchandiseCode").val().split(",");
	var supplierCodes=$("#supplierCode").val().split(",");
	var regionCode = $("#regionCode").val();
	var itemValue = $("#itemValue").val();
	var qlStartDate = $("#qlStartDate").val();
	var qlEndDate = $("#qlEndDate").val();
	var show="";
	var url="merchandisePriceTrend_listMerchandisePriceTrend_5.html?";	
		url+="merchandiseCode="+merchandiseCodes[i]
			+"&&supplierCode="+supplierCodes[i]
			+"&&regionCode="+regionCode
			+"&&itemValue="+itemValue
			+"&&qlStartDate="+qlStartDate
			+"&&qlEndDate="+qlEndDate;
		$.post(url, function(data) {
			priceChangeDateArr = [];
			sellPriceGainsArr = [];
			psdSalesGainsArr = [];
			psdResultsGainsArr = [];
			psdGrossProfitGainsArr = [];
			var json = $.parseJSON(data);
			if(json!=""){
				show="商品编号:"+json[0].merchandiseCode+" 供应商编号:"+json[0].supplierCode;
			}
			for (j = 0; j < json.length; j++) {
				if(j==0){
					var line="<div id='line"+(num+1)+"' style='height:400px;width:100%;float:left;'></div>";
					if(i!=0&&json!=""){
						$("#line"+num).after(line);
						num++;
					}
				}
				priceChangeDateArr.push(json[j].priceChangeDate);
				sellPriceGainsArr.push(json[j].sellPriceGains);
				psdSalesGainsArr.push(json[j].psdSalesGains);
				psdResultsGainsArr.push(json[j].psdResultsGains);
				psdGrossProfitGainsArr.push(json[j].psdGrossProfitGains);
			}
			var lineOption = {
					// color:['#3cb371', '#b8860b', '#30e0e0' ],
					title : {
						subtext : show
					},
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						data : [ '售价涨幅', 'PSD销量涨幅', 'PSD业绩涨幅', 'PSD毛利涨幅' ]
					},
					toolbox : {
						show : false,
						feature : {
							mark : {
								show : true
							},
							dataView : {
								show : true,
								readOnly : false
							},
							magicType : {
								show : true,
								type : [ 'line', 'bar' ]
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					calculable : false,
					xAxis : [ {
						type : 'category',
						boundaryGap : false
					// data : priceChangeDateArr
					} ],
					yAxis : [ {
						type : 'value',
						axisLabel : {
							formatter : '{value} %'
						}
					} ],
					series : [ {
						name : '售价涨幅',
						type : 'line'
					// data : []
					}, {
						name : 'PSD销量涨幅',
						type : 'line'
					// data : []
					}, {
						name : 'PSD业绩涨幅',
						type : 'line'
					// data : []
					}, {
						name : 'PSD毛利涨幅',
						type : 'line'
					// data : []
					} ]
				};
			// 为报表赋值
			lineOption.xAxis[0].data = priceChangeDateArr;
			lineOption.series[0].data = sellPriceGainsArr;
			lineOption.series[1].data = psdSalesGainsArr;
			lineOption.series[2].data = psdResultsGainsArr;
			lineOption.series[3].data = psdGrossProfitGainsArr;
			require([ 'echarts', 'echarts/chart/line' ], function(ec) {
				if(json!=""){
					var lineDom = $("#line"+num)[0];
					var myChart = ec.init(lineDom);
					myChart.setOption(lineOption);
				}
				i++;
				if (merchandiseCodes.length == i) {
					return;
				}
				recursion(i);
			});	
		});	
	
}