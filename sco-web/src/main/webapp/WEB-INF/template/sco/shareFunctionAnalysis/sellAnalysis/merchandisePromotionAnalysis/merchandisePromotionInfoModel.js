var bool=false;
$(document).ready(function() {
	grid = utils.grid($('#merchandisePromotionInfo_grid_poolInfo'));
	grid.initFilters({
		onLoadSuccess : function(data) {
			// $("#line")
			if(bool==false){
				optionData();
			}
		}
	});
});
var merchandisePromotionInfoFn = {
	search : function() {
		var length = $("div[id*=line").length;  //查出所有名字是以line开头的span	
		for (var i=0;i<length/2;i++){
			if(i==0){
				$("#line"+i).html("");
				$("#lines"+i).html("");
			}else{
				$("#line"+i).remove();
				$("#lines"+i).remove();
			}
		}
		bool=false;
		var row = $('#merchandisePromotionAnalysis_grid').datagrid("getSelections");
		if (row.length == 0) {
			$.messager.alert("提示", "请至少选择一个促销信息");
			return;
		}
		var param = {};
		param["merchandiseCode"] = $("#merchandiseCode").val();
		param["supplierCode"] = $("#supplierCode").val();
		$("#merchandiseGrid").datagrid({
			url : "merchandisePromotionAnalysis_listMerchandiseInfo_2.html",
			queryParams : param
		});
		var minDate = "";
		var maxDate = "";
		var day = "";
		var sellRegion="";
		for ( var i in row) {
			if (i == row.length - 1) {
				minDate += row[i].minDate;
				maxDate += row[i].maxDate;
				day += row[i].dayA;
				sellRegion+=row[i].sellRegion;
			} else {
				minDate += row[i].minDate + ",";
				maxDate += row[i].maxDate + ",";
				day += row[i].dayA + ",";
				sellRegion+=row[i].sellRegion+",";
			}
		}
		param["minDate"] = minDate;
		param["maxDate"] = maxDate;
		param["dayA"] = day;
		$("#selectMinDate").val(minDate);
		$("#selectMaxDate").val(maxDate);
		$("#day").val(day);
		param["searchTable"] = $("#show").val();
		param["sellRegion"] = sellRegion;
		$("#merchandisePromotionInfo_grid_pool").datagrid({
			url : "merchandisePromotionAnalysis_listMerchandisePromotionAnalysisDetail_2.html",
			queryParams : param
		});
		$("#merchandisePromotionInfo_grid_poolInfo").datagrid({
			url : "merchandisePromotionAnalysis_listMerchandisePromotionAnalysisDetailInfo_2.html",
			queryParams : param
		});
	},// 填写保存文件名称
	saveFile : function() {
		var row = $('#merchandiseGrid').datagrid("getRows");
		if (row == "") {
			$.messager.alert("提示", "数据为空不能保存");
			return;
		}
		$("#saveFileDlg").window('open');// 打开窗口
	},
	// 关闭界面
	closeSaveFileDlg : function() {
		$("#fileName").val("");// 清空填写的值
		$("#saveFileDlg").window('close');// 关闭窗口
	},// 提交填写文件名称的对话框
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
		var url = "merchandisePromotionAnalysis_saveSearchDataForm_2.html?";
		var row = $('#merchandisePromotionAnalysis_grid').datagrid("getSelections");
		var minDate = "";
		var maxDate = "";
		var day = "";
		var sellRegion="";
		for ( var i in row) {
			if (i == row.length - 1) {
				minDate += row[i].minDate;
				maxDate += row[i].maxDate;
				day += row[i].dayA;
				sellRegion+=row[i].sellRegion;
			} else {
				minDate += row[i].minDate + ",";
				maxDate += row[i].maxDate + ",";
				day += row[i].dayA + ",";
				sellRegion+=row[i].sellRegion+",";
			}
		}
		$("a[id='save']").linkbutton("disable");
		var prarm = "minDate=" + minDate + 
					"&maxDate=" + maxDate + 
					"&dayA=" + day + 
					"&searchTable=" + $("#show").val() + 
					"&sellRegion="+ sellRegion + 
					"&merchandiseCode=" + $("#merchandiseCode").val() + 
					"&supplierCode=" + $("#supplierCode").val();
		
		url += prarm;
		$.post(url, {
			fileName : fileName,
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
	},
	execlMerchandise : function() {
		var row = $('#merchandiseGrid').datagrid("getRows");
		if (row == "") {
			$.messager.alert("提示", "数据为空不能导出");
			return;
		}
		var url = "merchandisePromotionAnalysis_exportMerchandisePromotionInfoToExcel_6.html?";
		var row = $('#merchandisePromotionAnalysis_grid').datagrid("getSelections");
		var minDate = "";
		var maxDate = "";
		var day = "";
		var sellRegion="";
		for ( var i in row) {
			if (i == row.length - 1) {
				minDate += row[i].minDate;
				maxDate += row[i].maxDate;
				day += row[i].dayA;
				sellRegion+=row[i].sellRegion;
			} else {
				minDate += row[i].minDate + ",";
				maxDate += row[i].maxDate + ",";
				day += row[i].dayA + ",";
				sellRegion+=row[i].sellRegion+",";
			}
		}
		var prarm = "minDate=" + minDate + 
					"&maxDate=" + maxDate + 
					"&dayA=" + day + 
					"&searchTable=" + $("#show").val() + 
					"&sellRegion="+ sellRegion+ 
					"&merchandiseCode=" + $("#merchandiseCode").val() + 
					"&supplierCode=" + $("#supplierCode").val();
		url += prarm;
		window.location = url;
		$.messager.show({
			title : '提示',
			msg : '数据导出中,请稍后...',
			timeout : 4000,
			showType : 'slide'
		});
	},
	// 格式百分
	formatterPer : function(value, row) {
		if (value != null) {
			return moneyFormatter(value) + '%';
		} else {
			return value;
		}
	},
	//格式活跃度
	formatterActive : function(value, row) {
		if(value>100){
		 	return 100.00+'%';
		 }else if(value!=null){
		 	return moneyFormatter(value)+'%';
		 }else{
		 	return value;
		 }
	},
	// 格式销售单位
	formatterUnit : function(value, row) {
		if (row.bfb != '' && row.bfb != null) {
			if (value != null) {
				return moneyFormatter(value) + '%';
			} 
		}
		if(value!=null){
			return moneyFormatter(value);
		}else{
			return value;
		}
	},
	// 格式销售单位1
	formatterUnit1 : function(value, row) {
		if (row.bfb != '' && row.bfb != null) {
			if (value != null) {
				return moneyFormatter(value) + '%';
			} 
		}
		if(value!=null){
			return formatterCount(value);
		}else{
			return value;
		}
	},
	bfb : function(value, row) {
		if (row.bfb != '' && row.bfb != null) {
			if (value != null) {
				return moneyFormatter(value) + '%';
			} 
		}
		if (value != null) {
			return moneyFormatter(value);
		} else {
			return value;
		}
	},
	bfb1 : function(value, row) {
		if (row.bfb != '' && row.bfb != null) {
			if (value != null) {
				return moneyFormatter(value) + '%';
			} 
		}
		if (value != null) {
			return formatterCount(value);
		}else{
			return value;
		}
	},
	clear : function() {
		
		merchandisePromotionInfoFn.clearTable();
	},
	clearTable : function() {
		bool=true;
		$('#merchandiseGrid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		$('#merchandisePromotionInfo_grid_pool').datagrid('loadData', {
			total : 0,
			rows : []
		});
		$('#merchandisePromotionInfo_grid_poolInfo').datagrid('loadData', {
			total : 0,
			rows : []
		});
		var length = $("div[id*=line").length;  //查出所有名字是以line开头的span
		
		for (var i=0;i<length/2;i++){
			if(i==0){
				$("#line"+i).html("");
				$("#lines"+i).html("");
			}else{
				$("#line"+i).remove();
				$("#lines"+i).remove();
			}
		}

	},
	// 格式化时间
	formattDate : function(value, row) {
		var date = new Date(value);
		var month = date.getMonth() + 1;
		if (10 > month) {
			month = '0' + month;
		}
		var day = date.getDate();
		if (10 > day) {
			day = '0' + day;
		}
		return date.getFullYear() + '-' + month + '-' + day;
	}
};
var priceChangeDateArr = [], sellPriceGainsArr = [], psdSalesGainsArr = [], psdResultsGainsArr = [], psdGrossProfitGainsArr = [];
var priceChangeDateArrs = [], sellPriceGainsArrs = [], psdSalesGainsArrs = [], psdResultsGainsArrs = [], psdGrossProfitGainsArrs = [];
function optionData() {
	require.config({
		paths : {
			echarts : 'js/echarts'
		}
	});
	var row = $('#merchandisePromotionAnalysis_grid').datagrid("getSelections");
	recursion(row, 0);
}

function recursion(row, i){
	var url = "merchandisePromotionAnalysis_listMerchandisePromotionAnalysisDetailMap_5.html?";
	var line="<div id='line"+i+"' style='height:400px;width:50%;float:left;'></div>";
	var lines="<div id='lines"+i+"' style='height:400px;width:50%;float:left;'></div>";
	if(i!=0){
		$("#lines"+(i-1)).after(line);
		$("#line"+i).after(lines);
	}
	var prarm = "minDate=" + row[i].minDate + 
				"&maxDate=" + row[i].maxDate + 
				"&dayA=" + row[i].dayA + 
				"&searchTable=" + $("#show").val() + 
				"&sellRegion="+ row[i].sellRegion + 
				"&merchandiseCode=" + $("#merchandiseCode").val() + 
				"&supplierCode=" + $("#supplierCode").val();
	url += prarm;
	$.post(url, function(data) {
		priceChangeDateArr = [];
		sellPriceGainsArr = [];
		psdSalesGainsArr = [];
		psdResultsGainsArr = [];
		psdGrossProfitGainsArr = [];
		priceChangeDateArrs = [];
		sellPriceGainsArrs = [];
		psdSalesGainsArrs = [];
		psdResultsGainsArrs = [];
		psdGrossProfitGainsArrs = [];
		var lineOptions = {
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '单品日销售额VS促销后开始日期当天销售额', '单品平均销售额vs促销后开始日期当天销售额', '公司日销售额vs促销后开始日期当天销售额', '公司平均销售额vs促销后开始日期当天销售额' ]
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
					name : '单品日销售额VS促销后开始日期当天销售额',
					type : 'line'
				// data : []
				}, {
					name : '单品平均销售额vs促销后开始日期当天销售额',
					type : 'line'
				// data : []
				}, {
					name : '公司日销售额vs促销后开始日期当天销售额',
					type : 'line'
				// data : []
				}, {
					name : '公司平均销售额vs促销后开始日期当天销售额',
					type : 'line'
				// data : []
				} ]
			};
		var lineOption = {
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '单品日销量VS促销后开始日期当天销量', '单品平均销量vs促销后开始日期当天销量', '公司日销量vs促销后开始日期当天销量', '公司平均销售量vs促销后开始日期当天销量' ]
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
					name : '单品日销量VS促销后开始日期当天销量',
					type : 'line'
				// data : []
				}, {
					name : '单品平均销量vs促销后开始日期当天销量',
					type : 'line'
				// data : []
				}, {
					name : '公司日销量vs促销后开始日期当天销量',
					type : 'line'
				// data : []
				}, {
					name : '公司平均销售量vs促销后开始日期当天销量',
					type : 'line'
				// data : []
				} ]
			};
		var json = $.parseJSON(data);
		for (j = 0; j < json.length; j++) {
			priceChangeDateArr.push(json[j].time);
			sellPriceGainsArr.push(json[j].sellQuantityDB);
			psdSalesGainsArr.push(json[j].sellQuantityPDB);
			psdResultsGainsArr.push(json[j].sellQuantityAB);
			psdGrossProfitGainsArr.push(json[j].sellQuantityPAB);

			priceChangeDateArrs.push(json[j].time);
			sellPriceGainsArrs.push(json[j].sellTotalPriceDB);
			psdSalesGainsArrs.push(json[j].sellTotalPricePDB);
			psdResultsGainsArrs.push(json[j].sellTotalPriceAB);
			psdGrossProfitGainsArrs.push(json[j].sellTotalPricePAB);
			
		}
		
		// 为报表赋值
		lineOption.xAxis[0].data = priceChangeDateArr;
		lineOption.series[0].data = sellPriceGainsArr;
		lineOption.series[1].data = psdSalesGainsArr;
		lineOption.series[2].data = psdResultsGainsArr;
		lineOption.series[3].data = psdGrossProfitGainsArr;
		// 为报表赋值
		lineOptions.xAxis[0].data = priceChangeDateArrs;
		lineOptions.series[0].data = sellPriceGainsArrs;
		lineOptions.series[1].data = psdSalesGainsArrs;
		lineOptions.series[2].data = psdResultsGainsArrs;
		lineOptions.series[3].data = psdGrossProfitGainsArrs;

		require([ 'echarts', 'echarts/chart/line' ], function(ec) {
			var lineDom = $("#line"+i)[0];
			var myChart = ec.init(lineDom);
			myChart.setOption(lineOption);
			
			var lineDoms = $("#lines"+i)[0];
			var myCharts = ec.init(lineDoms);
			myCharts.setOption(lineOptions);
			i++;
			if (row.length == i) {
				return;
			}
			recursion(row, i);
		});
	});
}