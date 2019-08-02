var merMatSupQuoGrid = null;
var flag = true;// 第一次进入页面
$(document).ready(function() {
	merMatSupQuoGrid = utils.grid($('#merMatSupQuo_grid'));
	merMatSupQuoGrid.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", "dxRoleCode",
				"dlRoleCode", "centreTypeCode", "smallTypeCode", "detailTypeCode", "fineTypeCode", "materialType", 
				"ingredientCodeName", "startYear", "endYear");
	merMatSupQuoGrid.initFilters({
		onBeforeLoad : function(obj) {
			if (flag) {
				flag = false;
				return false;
			}
		},
		onLoadSuccess : function() {
			$('#merMatSupQuo_grid').datagrid("clearSelections");
		}
	});
});

var merMatSupQuoGridFn = {

	// 选择开始年份
	selectStartYear : function(val) {
		if (val > $("#endYear").val()) {
			$("#endYear").val(val);
		}
	},

	// 选择结束年份
	selectEndYear : function(val) {
		if (val < $("#startYear").val()) {
			$("#startYear").val(val);
		}
	},
	
	//查询商品原料数据
	searchMerMate : function() {
		var param = merMatSupQuoGrid.getFilterValue();
		$('#merMatSupQuo_grid').datagrid('load', param);
	},
	
	//清空查询
	clearFilter : function() {
		$('#merMatSupQuo_search').form('reset');
		clearComboboxOptions();
		$('#merMatSupQuo_grid').datagrid('clearSelections');
		$('#merMatSupQuo_grid').datagrid('loadData', {total:0, rows:[]});
		$("#merDataTab").html("");
	},
	
	//查看商品原料历史价格
	listMerMateHisPrice:function(){
		var rows = $('#merMatSupQuo_grid').datagrid('getSelections');
		if (rows.length != 1 ) {
			window.parent.$.messager.alert('提示', '请只选择一个供应商');
			return;
		}
		showLoading();
		$.post('merchMateSuppQuote_listMerMateHisPrice_1.html',
				{
					rows : JSON.stringify(rows),
					startYear : $("#startYear").val(),
					endYear : $("#endYear").val()
				}, function(data) {
					$("#merDataTab").html(data);
					$("#msq_search").show();
					$('.msg_bg').remove();
		},"html");
	},
	
	//显示图表
	showChart:function(data, i, curYear, curMonth) {
		var obj = merMatSupQuoGridFn.getChartData(data, i, curYear, curMonth);
		merMatSupQuoGridFn.createChart(obj, i)
	},
	
	//设置图表所需参数
	getChartData:function(data, i, curYear, curMonth) {
		var lengendArr = new Array();
		var seriesArr = new Array();
		var pArr =  new Array();
		obj = data[i];
		
		lengendArr[0] = '投料表内供应商原料采购价格';
		for (var j in obj.accList) {
			var acc = obj.accList[j];
			pArr[j] = acc.price; 
		}
		seriesArr[0] = {
				name :  '投料表内供应商原料采购价格',
				type : 'line',
				data : pArr
		};
		var oYear = obj.priceYear;
		
		for (var k in obj.webList) {
			var web = obj.webList[k];
			if (web.priceYear == null || web.priceYear == "") continue;
			lengendArr[lengendArr.length] = web.priceYear;
			pArr =  new Array();
			for (var l = 1; l < 13; l++) {
				if (oYear <= curYear) {
					for(var x in web.list) {
						var z = web.list[x];
						if(oYear == z.priceDate && z.priceYear == l){
							pArr[l-1] = z.price;
						}
					}
					//当这个月的数据不存在是
					if(!pArr[l-1] || (oYear == curYear && l > curMonth)) {
						pArr[l-1] = 0;
					}
				} else {
					pArr[l-1] = 0;//大于当前年全为0
				}
			}
			seriesArr[seriesArr.length] = {
				name :  web.priceYear,
				type : 'line',
				data : pArr
			};
		}
		
		lengendArr[lengendArr.length] = '商品进价';
		pArr =  new Array();
		for (var m in obj.purList) {
			pArr[m] = obj.purList[m].price;
		}
		seriesArr[seriesArr.length] = {
			name :  '商品进价',
			type : 'line',
			data : pArr
		};
		
		lengendArr[lengendArr.length] = '商品售价';
		pArr =  new Array();
		for (var m in obj.soldList) {
			pArr[m] = obj.soldList[m].price;
		}
		seriesArr[seriesArr.length] = {
			name :  '商品售价',
			type : 'line',
			data : pArr
		};
		
		return new Array(lengendArr, seriesArr);
	},
	
	//创建图表
	createChart:function(obj, id) {
		var option = {
			  title : {
			      text: '',
			      subtext: ''
			  },
			  tooltip : {
			      trigger: 'item',
			      formatter:function(obj){
			    	var val = obj[2];
			    	if (val == null || val == 0) val = 0.00;
	            	return obj[0] + '<br>' + obj[1] + ':' + moneyFormatter(val);//光标移入图像时显示数据
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
			          //boundaryGap: [0, 0], // 坐标轴两端空白策略，数组内数值代表百分比
			          axisLabel : {
			        	  formatter : '{value}'
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
			var myChart = ec.init(document.getElementById("chart"+id));
			myChart.setOption(option);
		});
	},
	
	//导出数据
	exportMerMat:function() {
		var rows = $('#merMatSupQuo_grid').datagrid('getSelections');
		if (rows.length != 1 ) {
			window.parent.$.messager.alert('提示', '请只选择一个供应商');
			return;
		}
		showRightDownMsg('提示', '数据导出中,请稍后...', 5000, 'slide');
		
		$("#merMatSupQuo_search").form('submit', {
			url : 'merchMateSuppQuote_exportMerMat_6.html',
			onSubmit : function(param) {
				param.startYear = $("#startYear").val();
				param.endYear = $("#endYear").val();
				param.rows = JSON.stringify(rows);
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
		var rows = $('#merMatSupQuo_grid').datagrid('getSelections');
		if (rows.length != 1 ) {
			window.parent.$.messager.alert('提示', '请只选择一个供应商');
			return;
		}
		$("#merMatSaveFileDlg").window('open');
	},
	
	// 提交填写文件名称的对话框
	submitSaveFileDlg:function() {
		var fileName = $.trim($("#merMatFileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			return;
		}
		var rows = $('#merMatSupQuo_grid').datagrid('getSelections');
		$("#saveFile").linkbutton("disable");
		$.post("merchMateSuppQuote_saveMerMat_2.html", {
			fileName : fileName,
			startYear : $("#startYear").val(),
			endYear : $("#endYear").val(),
			rows : JSON.stringify(rows)
		}, function(data) {
			var json = $.parseJSON(data);
			var msg = json.msg;
			if (json.success) {// 成功
				$("#merMatFileName").val("");// 清空填写的值
				$("#merMatSaveFileDlg").window('close');// 关闭窗口
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
		$("#merMatFileName").val("");//清空填写的值
        $("#merMatSaveFileDlg").window('close');//关闭窗口
	}
	
};