var dtCatPrice1 = null;
var dtCatPrice2 = null;

var firstIn = true;// 是否第一次进入页面
var clearSearch = false;// 清空查询
$(document).ready(
		function() {
			
			dtCatPrice1 = utils.grid($('#dtCatPrice1'));
			dtCatPrice1.registerExtFilters("centreTypeCode", "smallTypeCode", "detailTypeCode", "regionCode", "dxRoleCode", "dlRoleCode", 
					"startYear1", "startMonth1", "endYear1", "endMonth1", "startYear2", "startMonth2", "endYear2","endMonth2",
					"merchandiseName", "merchandiseCode");
			dtCatPrice1.initFilters({
				onBeforeLoad : function(obj) {
					// 清空查询
					if (clearSearch) {
						return true;
					}
					var length = $.param(obj).split("&").length;
					// 判断参数个数
					if (length < 3) {
						if (!firstIn)
							window.parent.$.messager.alert("提示", "请输入至少一项查询条件");
//						firstIn = false;
						return false;
					}
					/*if (length == 4 && $.param(obj).indexOf("order") > -1) {
						var param = dtCatPrice1.getFilterValue();
						if ($.param(param).length > 0) {
							return true;
						}
						window.parent.$.messager.alert("提示", "请输入至少一项查询条件");
						return false;
					}*/
				},
				onLoadSuccess : function(data) {
					$('#dtCatPrice1').datagrid("clearSelections");
					dtCatPriceFn.allShowChart(data.rows, "1");
				}
			});
			
			dtCatPrice2 = utils.grid($('#dtCatPrice2'));
			dtCatPrice2.registerExtFilters("centreTypeCode", "smallTypeCode", "detailTypeCode", "regionCode", "dxRoleCode", "dlRoleCode", "startYear1", 
					"startMonth1", "endYear1", "endMonth1", "startYear2", "startMonth2", "endYear2", "endMonth2",
					"merchandiseName", "merchandiseCode");
			dtCatPrice2.initFilters({
				onBeforeLoad : function(obj) {
					// 清空查询
					if (clearSearch) {
						return true;
					}
					var length = $.param(obj).split("&").length;
					
					// 判断参数个数
					if (length < 3) {
						if (!firstIn)
							window.parent.$.messager.alert("提示", "请输入至少一项查询条件");
						firstIn = false;
						return false;
					}
					/*if (length == 4 && $.param(obj).indexOf("order") > -1) {
						var param = dtCatPrice2.getFilterValue();
						if ($.param(param).length > 0) {
							return true;
						}
						window.parent.$.messager.alert("提示", "请输入至少一项查询条件");
						return false;
					}*/
				},
				onLoadSuccess : function(data) {
					$('#dtCatPrice2').datagrid("clearSelections");
					dtCatPriceFn.allShowChart(data.rows, "2");
				}
			});
	}
);

//用于隐藏和显示列
var smCol = ['regionStoreSum', 'regionSKUSum', 'regionPriceStr', 'skuUnits', 'skuPercent', 
              'notLessPsdUnitsSku', 'lessPsdUnitsSku', 'notLessPsdSoldPrice', 'lessPsdSoldPrice',
              'notLessPsdGrossProfit', 'lessPsdGrossProfit'];
var smDetCol = ['SKUU', 'merchandiseCode', 'merchandiseName', 'supplierCode', 'supplierName',
              'dxRoleName', 'dlRoleName', 'centreTypeName', 'smallTypeName', 'detailTypeName',
              'regionPP', 'powerCount', 'powerPercent', 'aCount', 'bCount', 'cCount', 'dCount',
              'actSoldStorCount', 'actSoldStorPercent', 'notLessS', 'notLessP', 'notLessG', 
              'bucketPercent', 'volumePercent', 'valPercent'];

var myChart = new Array();
var dtCatPriceFn = {
	
	//百分比格式
	formatPercent:function(val) {
		if (!val || val == 0) {
			return '0.00%';
		} else {
			val = (val*100).toFixed(2);
			return val + '%';
		}
	},
	
	//大于1显示100%
	formatBigPercent:function(val) {
		if (val >= 1) return '100.00%';
		return dtCatPriceFn.formatPercent(val);
	},
	
	//SKUU
	formatSKUU:function(val, rowData) {
		return moneyFormatter(rowData.regionStoreSum, 0);
	},
	//regionPP
	formatRegionPP:function(val, rowData) {
		return rowData.regionPrice;
	},
	//notLessS
	formatNotLessS:function(val, rowData) {
		return moneyFormatter(rowData.notLessPsdUnitsSku);
	},
	//notLessP
	formatNotLessP:function(val, rowData) {
		return moneyFormatter(rowData.notLessPsdSoldPrice);
	},
	//notLessG
	formatNotLessG:function(val, rowData) {
		return moneyFormatter(rowData.notLessPsdGrossProfit);
	},
	
	// 隐藏\显示价格带
	displayPriceRegion:function(flag) {
		if (flag) {
			$('tr[name*=priceRegion]').show();
			$('#title').show();
		} else {
			$('tr[name*=priceRegion]').hide();
			$('#title').hide();
		}
	},
	
	//隐藏\显示明细报表中只显示指定名称/编号的商品
	displayDmc:function(flag) {
		if (flag) {
			$('#dmcc').css('visibility', 'visible');
		} else {
			$('#merchandiseCode').val('');
			$('#merchandiseName').val('');
			$('#dmcc').css('visibility', 'hidden');
			
		}
	},
	
	displayChart:function() {
		$('#dtChart1').hide();
		$('#dtChart2').hide();
	},
	
	//手动设置价格带
	clonePriceRegion : function(btn){
		var length = $("tr[name*=priceRegion").length;
		if (length == 10) {
			window.parent.$.messager.alert('提示', '手动设置价格带不能超过10条');
			return;
		}
		
		var data = '<tr name="priceRegion'+ length+'">'
			+ '<td colspan="6" style="padding-left: 3px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
			+ '价格大于等于：&nbsp;'
			+ '<input class="easyui-numberbox" style="width:80px;" id=minPrice'+ length +' name=minPrice'+ length +' maxlength="12" onchange="dtCatPriceFn.floorPrice('+"'"+length+"'"+')" onblur="dtCatPriceFn.floorPriceBlur('+"'"+length+"'"+')"/>'
			+ '&nbsp;元&nbsp;&nbsp;价格小于：&nbsp;'
			+ '<input class="easyui-numberbox" style="width:80px;" id=maxPrice'+ length +' name=maxPrice'+ length +' maxlength="12" onchange="dtCatPriceFn.limtPrice('+"'"+length+"'"+')" onblur="dtCatPriceFn.limtPriceBlur('+"'"+length+"'"+')" />'
			+ '&nbsp;元&nbsp;&nbsp;价格步长：&nbsp;'
			+ '<input class="easyui-numberbox" style="width:80px;" id=addPrice'+ length +' name=addPrice'+ length +' maxlength="12" onchange="dtCatPriceFn.addPrice('+"'"+length+"'"+')" onblur="dtCatPriceFn.addPriceBlur('+"'"+length+"'"+')" />'
			+ '&nbsp;元&nbsp;&nbsp;&nbsp;<input type="button" value="+" onclick="dtCatPriceFn.clonePriceRegion(this)" '
			+ 'style="width: 28px;" />'
			+ '&nbsp;&nbsp;&nbsp;<input type="button" value="-" onclick="dtCatPriceFn.removeCurrentRow(this)" '
			+ 'style="width: 28px;" />';
			+ '</td></tr>'
		var trClone = $(data).clone(true);
		trClone.insertAfter($(btn).parent().parent());//插入到按钮后面
		//设置class属性
		$('tr[name=priceRegion'+length+']').find('.easyui-numberbox').each(
			function(index, obj){
				var i = 0;
				if(index > 0) i = 1;
				$(obj).numberbox({   
				    min:i,
				    precision:2
				}); 
			}
		);
	},
	
	//删除价格带
	removeCurrentRow : function(obj){
		$(obj).parent().parent().remove();
	},
	
	//查看汇总表
	searchAllCategory:function() {
		dtCatPriceFn.displayChart();//隐藏图表
		$("#con1").hide();
		$("#con2").hide();
		
		$('#dtCatPrice1').datagrid('loadData', {total:0, rows:[]});
		$('#dtCatPrice2').datagrid('loadData', {total:0, rows:[]});
		
		dtCatPriceFn.showOrHiddenCol('showColumn', 'hideColumn');
		dtCatPriceFn.searchData('A', dtCatPrice1);
	},
	
	//查看明细报表
	searchDetCategory:function() {
		dtCatPriceFn.displayChart();//隐藏图表
		$("#con1").hide();
		$("#con2").hide();
		
		$('#dtCatPrice1').datagrid('loadData', {total:0, rows:[]});
		$('#dtCatPrice2').datagrid('loadData', {total:0, rows:[]});
		dtCatPriceFn.showOrHiddenCol('hideColumn', 'showColumn');
		dtCatPriceFn.searchData('D', dtCatPrice1);
	},
	
	//隐藏或显示列
	showOrHiddenCol:function(flag1, flag2) {
		for (var i in smCol) {//所有列
			$("#dtCatPrice1").datagrid(flag1, smCol[i]);
		}
		
		for (var i in smCol) {//所有列
			$("#dtCatPrice2").datagrid(flag1, smCol[i]);
		}

		//------------------------------------------------
		
		for (var i in smDetCol) {//明细列
			$("#dtCatPrice1").datagrid(flag2, smDetCol[i]);
		}
		for (var i in smDetCol) {//明细列
			$("#dtCatPrice2").datagrid(flag2, smDetCol[i]);
		}
		
		//销量列的单位
		var noFlag = $('#netWeightType').attr('checked') == 'checked';//是否为非定量
		if (flag1 == "showColumn") {//查询所有
			if (noFlag) {//查询非定量
				noFlag = "销量（KG）";
			} else {// 查询定量
				noFlag = "销量（件）";
			}
		} else {
			noFlag = "销量";
		}
		$('#dtCatPrice1').datagrid('getColumnOption','soldUnits').title = noFlag;
		$('#dtCatPrice2').datagrid('getColumnOption','soldUnits').title = noFlag;
//		$('#allCatPrice1').datagrid();
		
	},
	
	//查询数据公共方法
	searchData:function(showPanel, dataGrid) {
		
		$('#showPanel').val(showPanel);//标示查询的是汇总还是明细
		var msg = dtCatPriceFn.validateSearchCondition();
		if(msg.length > 1) {
			window.parent.$.messager.alert('提示', msg);
			return; 
		}
		var param1 = dtCatPriceFn.generatePara('1', dataGrid.getFilterValue());
		if ('D' == showPanel) {//明细统计
			param1.merchandiseCode = $('#merchandiseCode').val();
			param1.merchandiseName = $('#merchandiseName').val();
			
			var filterMer = $('#filterMer').attr('checked') == 'checked';
			if (filterMer && param1.merchandiseCode == '' && param1.merchandiseName == '') {
				window.parent.$.messager.alert('提示', '商品编号与商品名称请至少填一项');
				return;
			}
			
			$('tr[name=priceCal1]').hide();
			$("#dtBar1").html("");
			$("#dtCatPrice1").datagrid("loadData", []);
			loading('dtCatPrice1');
			$.ajax({
				type : "POST",
				url : 'detailTypePrice_listDetailData_2.html',
				data : param1,
				dataType : 'JSON',
				success : function(data) {
					dtCatPriceFn.pageData(1,data.rows);
				}
			});
			
			/*$('#dtCatPrice1').datagrid({			//grid查询
				url : 'detailTypePrice_listDetailData_2.html',
				queryParams : param1
			});*/
		} else {//所有价格带统计
			param1.merchandiseCode = null;
			param1.merchandiseName = null;
			
			dtCatPriceFn.calculateTotal(1, param1);//上方统计
			$('#dtCatPrice1').datagrid({			//grid查询
				pagination : false,
				data : [],
				url : 'detailTypePrice_listCollectData_2.html',
				queryParams : param1
			});
			$('tr[name=priceCal1]').show();
		}
		$('#con1').show();
		
		if($('#startYear2').val()) {//加载第二个datagrid
			var param2 = dtCatPriceFn.generatePara('2', dataGrid.getFilterValue());
			//日期区间1
			param2.firSYear1 = $('#startYear1').val();
			param2.firSMonth1 = $('#startMonth1').val();
			
			param2.firEYear1 = $('#endYear1').val();
			param2.firEMonth1 = $('#endMonth1').val();
			
			if ('D' == showPanel) {//明细数据
				param2.merchandiseCode = $('#merchandiseCode').val();
				param2.merchandiseName = $('#merchandiseName').val();

				$('tr[name=priceCal2]').hide();
				$("#dtBar2").html("");
				$("#dtCatPrice2").datagrid("loadData", []);
				loading('dtCatPrice2');
				$.ajax({
					type : "POST",
					url : 'detailTypePrice_listDetailData_2.html',
					data : param2,
					dataType : 'JSON',
					success : function(data) {
						dtCatPriceFn.pageData(2,data.rows);
					}
				});
				/*$('#dtCatPrice2').datagrid({
					url : 'detailTypePrice_listDetailData_2.html',
					queryParams : param2
				});*/
			} else {			//所有数据
				param2.merchandiseCode = null;
				param2.merchandiseName = null;
				
				dtCatPriceFn.calculateTotal(2, param2);
				$('#dtCatPrice2').datagrid({
					pagination : false,
					data : [],
					url : 'detailTypePrice_listCollectData_2.html',
					queryParams : param2
				});
				$('tr[name=priceCal2]').show();
			}
			$('#con2').show();
		}
	},
	
	//导出到Excel
	exportToExcel:function() {
		var data1 = $('#dtCatPrice1').datagrid('getRows');
		var data2 = $('#dtCatPrice2').datagrid('getRows');
		//是否查出数据
		if(data1.length < 1 && data2.length < 1) {
			window.parent.$.messager.alert('提示', '当前未查询到任何数据');
			return; 
		}
		//必填条件
		var msg = dtCatPriceFn.validateSearchCondition();
		if(msg.length > 1) {
			window.parent.$.messager.alert('提示', msg);
			return; 
		}
		
		var param2 = dtCatPriceFn.getSaveAndExportPara();
		var url = "detailTypePrice_exportAllCategoryToExcel_6.html?";
		if ($('#showPanel').val() == 'D') {
			param2.merchandiseCode = $('#merchandiseCode').val();
			param2.merchandiseName = $('#merchandiseName').val();
			
			var filterMer = $('#filterMer').attr('checked') == 'checked';
			if (filterMer && param2.merchandiseCode == '' && param2.merchandiseName == '') {
				window.parent.$.messager.alert('提示', '商品编号与商品名称请至少填一项');
				return;
			}
			
			url = "detailTypePrice_exportDetCategoryToExcel_6.html?";
		} else {
			param2.merchandiseCode = null;
			param2.merchandiseName = null;
		}
		
		window.location = url + $.param(param2);
		$.messager.show({
			title:'提示',
			msg:'数据导出中,请稍后...',
			timeout:5000,
			showType:'slide'
		});
	},
	
	//datagrid上方数据统计
	calculateTotal : function(i, param) {
		$.ajax({
			type : "POST",
			url : "detailTypePrice_listCalculateTotal_2.html",
			data : param,
			dataType : 'JSON',
			success : function(data) {
				if (data.success) {//查询成功
					var value = data.rows;
					if (value.indexOf("|") >= 0) {//有数据
						var arr = value.split("|");
						dtCatPriceFn.setCalculateTotal(i, arr[0], arr[1], arr[2], arr[3]);
					} else {//无数据
						dtCatPriceFn.setCalculateTotal(i, "", "", "", "");
					}
				} else {//查询出错
					window.parent.$.messager.alert('提示', '服务器繁忙');
				}
			},
			error:function() {//返回出错
				window.parent.$.messager.alert('提示', '服务器繁忙');
			}
		});
	},
	
	// 设置统计值
	setCalculateTotal:function(i, sku, unit, price, gross) {
		$("#totalSkuUnit" + i).html(sku);
		$("#totalSoldUnit" + i).html(unit);
		$("#totalSoldPri" + i).html(price);
		$("#totalGrossfit" + i).html(gross);
	},
	
	//查询参数
	generatePara:function(di, param) {
		//日期
		param.startYear = $('#startYear' + di).val();
		param.startMonth = $('#startMonth' + di).val();
		
		param.endYear = $('#endYear' + di).val();
		param.endMonth = $('#endMonth' + di).val();
		$("span[name=t"+di+"]").html(
				param.startYear+"-"+(param.startMonth.length < 2 ? '0' + param.startMonth : param.startMonth)
				+"~"+param.endYear+"-"+(param.endMonth.length < 2 ? '0' + param.endMonth : param.endMonth));
		
		//定量、非定量装
		if ($('#netWeightType').attr('checked') == 'checked') {
			param.netWeightType = '1';
			$("span[name=netWeightTypeCon]").html("只看非定量装商品");
		} else {
			param.netWeightType = '2';
			$("span[name=netWeightTypeCon]").html("只看定量装商品");
		} 
		
		//价格区间
		if ($('#handSetPri').attr('checked') == 'checked') {
			var limitMaxPrice = 0 ;
			$("span[name=priceSetCon]").html("手动设置价格带");
			var minPrice = '';
			var maxPrice = '';
			var addPrice = '';
			var priceLen = $("tr[name*=priceRegion").length ;
			for (var i = 0; i < priceLen ; i++) {
				minPrice += $('#minPrice' + i).val() + ','
				var t = $('#maxPrice' + i).val();
				maxPrice +=  t + ',';
				if(di == 2 && t > limitMaxPrice) {
					limitMaxPrice = t; 
				}
				addPrice += $('#addPrice' + i).val() + ',';
			}
			param.handSetPri = '1';//手动设置
			param.minPrice = minPrice.substring(0, minPrice.length - 1);
			param.maxPrice = maxPrice.substring(0, maxPrice.length - 1);
			param.addPrice = addPrice.substring(0, addPrice.length - 1);
			if(di == 2) param.limitMaxPrice = limitMaxPrice;
		} else {
			$("span[name=priceSetCon]").html("系统自动计算价格带");
		}
		
		//直营、加盟数据
		if ($('#direct').attr('checked') == 'checked') {
			param.dataType = 'D';
			$("span[name=directCon]").html("只看直营门店数据");
		} else if ($('#join').attr('checked') == 'checked') {
			param.dataType = 'J';
			$("span[name=directCon]").html("只看加盟门店数据");
		} else {
			param.dataType = 'A';
			$("span[name=directCon]").html("直营与加盟门店数据均看");
		}
		return param;
	},
	
	//所有商品价格带统计表
	allShowChart:function(data, index) {
		myChart[index] = null;
		if ("D" == $('#showPanel').val()) return;
		if (data.length < 1) {
			$('#dtChart' + index).hide();
			return;
		}
		$('#dtChart' + index).show();
		var lengendArr = new Array();
		var seriesArr = new Array();
		$(data).each(function(i, obj) {
			var skuUnits = obj.skuUnits;
			lengendArr[i] = obj.regionPriceStr;// 所有组
			
			//格式化位数
			var oskuPercent = subStrLength(obj.skuPercent * 100);
			var osoldPercent = subStrLength(obj.soldPercent * 100);
			var osoldPricePercent = subStrLength(obj.soldPricePercent * 100);
			var ogrossProfitPenrcent = subStrLength(obj.grossProfitPenrcent*100);
			var ovitality = (obj.vitality > 1 ? 100 : subStrLength(obj.vitality * 100));
			var opowerShopDayPercent = subStrLength(obj.powerShopDayPercent * 100);
			var osoldShopPercent = subStrLength(obj.soldShopPercent * 100);
			var onotLessUnitsSku= subStrLength((obj.notLessPsdUnitsSku/skuUnits) * 100);
			var onotLessSoldPrice = subStrLength((obj.notLessPsdSoldPrice/skuUnits) * 100);
			var onotLessPsdGrossProfit = subStrLength((obj.notLessPsdGrossProfit/skuUnits) * 100);
			
			seriesArr[i] = { name:obj.regionPriceStr, type:'bar', itemStyle: { 
					normal: { label : { show : true , formatter : function(obj){
						if (obj.value == 0 || !obj.value) {
							return "0.00%";
						}
						return new Number(obj.value).toFixed(2) + "%";
						}}//柱子顶上显示值 
					} 
				},
				data:[oskuPercent, osoldPercent, osoldPricePercent, ogrossProfitPenrcent,
				      ovitality, opowerShopDayPercent, osoldShopPercent, onotLessUnitsSku,
				      onotLessSoldPrice, onotLessPsdGrossProfit
				      ]
			};
		});

		require.config({
			paths : {
				echarts : 'js/echarts'
			}
		});

		require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
			var lineDom = $("#dtBar" + index)[0];
			barOption = {
				// color:['#3cb371', '#b8860b', '#30e0e0' ],
				title : {
					text : '',
					subtext : ''
				},
				tooltip : {
					trigger : 'item',
			      	formatter:function(obj){
		            	return obj[0] + '<br>' + obj[1] + ":" + obj[2] + "%";//光标移入图像时显示数据
			        }
				},
				legend : {
					itemGap: 10, 
					data : lengendArr
				},
				calculable : false,
				xAxis : [ {
					type : 'category',
					boundaryGap : true,
					data : [ 'SKU数量占比', '销量占比', '销售金额占比', '毛利额占比', '活跃度', '权限店天占比',
					         '销售店天占比', '大于等于PSD销量的SKU数占比', '大于等于PSD销售额的SKU数占比',
					         '大于等于PSD毛利额的SKU数占比']
				} ],
				yAxis : [ {
					type : 'value',
					boundaryGap: [0, 0], // 坐标轴两端空白策略，数组内数值代表百分比
					axisLabel : {
						formatter : '{value}%'
					}
				} ],
				series : seriesArr
			};
			var chart = ec.init(lineDom);
			chart.setOption(barOption);
			myChart[index] = chart.getDataURL("png");
		});
	},
	
	//填写保存文件名称
	saveFile:function() {
		var data1 = $('#dtCatPrice1').datagrid('getRows');
		var data2 = $('#dtCatPrice2').datagrid('getRows');
		//是否查出数据
		if(data1.length < 1 && data2.length < 1) {
			window.parent.$.messager.alert('提示', '当前未查询到任何数据');
			return; 
		}
		//必填条件
		var msg = dtCatPriceFn.validateSearchCondition();
		if(msg.length > 1) {
			window.parent.$.messager.alert('提示', msg);
			return; 
		}
		
		$("#dtSaveFileDlg").window('open');//打开窗口
	},

	//提交填写文件名称的对话框
	submitSaveFileDlg:function() {
		var fileName = $.trim($("#smFileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			return;
		}

		var param2 = dtCatPriceFn.getSaveAndExportPara();
		var url = "detailTypePrice_saveAllCategory_2.html?";
		if ($('#showPanel').val() == 'D') {//保存明细数据
			param2.merchandiseCode = $('#merchandiseCode').val();
			param2.merchandiseName = $('#merchandiseName').val();
			
			var filterMer = $('#filterMer').attr('checked') == 'checked';
			if (filterMer && param2.merchandiseCode == '' && param2.merchandiseName == '') {
				window.parent.$.messager.alert('提示', '商品编号与商品名称请至少填一项');
				return;
			}
			
			url = "detailTypePrice_saveDetCategory_2.html?";
		} else {
			param2.merchandiseCode = null;
			param2.merchandiseName = null;
		}
		
		$("#saveFile").linkbutton("disable");
		$.post(url + $.param(param2), {
			fileName : fileName
		}, function(data) {
			var json = $.parseJSON(data);
			var msg = json.msg;
			if (json.success) {// 成功
				$("#smFileName").val("");// 清空填写的值
				$("#dtSaveFileDlg").window('close');// 关闭窗口
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

	//获取保存、导出所需参数
	getSaveAndExportPara:function() {
		var param2 = dtCatPriceFn.generatePara('2', dtCatPrice1.getFilterValue());
		//日期区间1
		param2.firSYear1 = $('#startYear1').val();
		param2.firSMonth1 = $('#startMonth1').val();
		
		param2.firEYear1 = $('#endYear1').val();
		param2.firEMonth1 = $('#endMonth1').val();
		//公共部分
		param2.regionName = $('#regionCode').combobox('getText');
		//定量、非定量装
		if ($('#netWeightType').attr('checked') == 'checked') {
			param2.netWeightTypeCon = "只看非定量装商品";
		} else {
			param2.netWeightTypeCon = "只看定量装商品";
		} 
		//直营、加盟数据
		if ($('#direct').attr('checked') == 'checked') {
			param2.directCon = "只看直营门店数据";
		} else if ($('#join').attr('checked') == 'checked') {
			param2.directCon = "只看加盟门店数据";
		} else {
			param2.directCon = "直营与加盟门店数据均看";
		}
		if ($('#handSetPri').attr('checked') == 'checked') {
			param2.priceSetCon = "手动设置价格带";
		} else {
			param2.priceSetCon = "系统自动计算价格带";
		}
		param2.merTitle = $('#detailTypeCode').combobox('getText');
		param2.module = "DE";//模块名称
		return param2;
	},
	
	//关闭界面
	closeSaveFileDlg:function() {
		$("#smFileName").val("");//清空填写的值
        $("#dtSaveFileDlg").window('close');//关闭窗口
	},
	
	// 清空查询
	clearSearch:function() {
		clearSearch = true;
		$('#dtCatPrice_search').form('reset');
		clearComboboxOptions();
		dtCatPriceFn.displayPriceRegion(false);//隐藏价格带
		dtCatPriceFn.displayDmc(false);//隐藏明细报表中只显示指定名称/编号的商品
		dtCatPriceFn.displayChart();//隐藏图表
		myChart = new Array();
		$("#con1").hide();
		$("#con2").hide();
		
		$('#dtCatPrice1').datagrid('clearSelections');
		$('#dtCatPrice1').datagrid('loadData', {total:0, rows:[]});

		$('#dtCatPrice1').datagrid('clearSelections');
		$('#dtCatPrice2').datagrid('loadData', {total:0, rows:[]});
		
		clearSearch = false;
	},
	
	//选则开始年份
	selectStartYear:function(obj, tarId, i) {
		var tar = $("#" + tarId); 
		if(!obj.value) {
			$(tar).val("");
			$('#startMonth' + i).val("");
			$('#endMonth' + i).val("");
			return;
		} else {
			if (new Number(obj.value) - new Number($(tar).val()) > 0) {
				$(tar).val(obj.value); 
			}
		}
		dtCatPriceFn.selectStartMon('startYear' + i,'startMonth' + i,'endYear' + i,'endMonth' + i);
	},

	//选则结束年份
	selectEndYear:function(obj, tarId, i) {
		var tar = $("#" + tarId); 
		if(!obj.value) {//结束时间为""
			$(tar).val("");
			$('#startMonth' + i).val("");
			$('#endMonth' + i).val("");
			return;
		} else if (!$(tar).val()) {//开始时间为""
			$(tar).val(obj.value);
		} else {
			if (new Number(obj.value) - new Number($(tar).val()) < 0) {
				$(tar).val(obj.value); 
			}
		}
		dtCatPriceFn.selectEndMon('startYear'+i,'startMonth'+i,'endYear'+i,'endMonth'+i);
	},
	
	//选则开始月份
	selectStartMon:function(startY, startM, endY, endM) {
		var startYV = new Number($("#" + startY).val());
		var endYV = new Number($("#" + endY).val());
		if (startYV == 0) {//未选择开始年份
			window.parent.$.messager.alert('提示', '请先选择年份');
			$('#'+startM).val("");
			$('#'+endM).val("");
			return;
		}
		var startMV = new Number($("#" + startM).val());
		var endMV = new Number($("#" + endM).val());
		if(startMV == 0 || endMV == 0 && startY != 0) {
			$("#" + startM).val("1");
			$("#" + endM).val("1");
		} else {
			if (endYV - startYV == 0 && startMV - endMV > 0) {
				$("#" + endM).val(startMV+""); 
			}
		}
	},

	//选则结束月份
	selectEndMon:function(startY, startM, endY, endM) {
		var startYV = new Number($("#" + startY).val());
		var endYV = new Number($("#" + endY).val());
		
		if (startYV == 0) {//未选择开始年份
			window.parent.$.messager.alert('提示', '请先选择年份');
			$('#'+startM).val("");
			$('#'+endM).val("");
			return;
		}
		var startMV = new Number($("#" + startM).val());
		var endMV = new Number($("#" + endM).val());
		if(startM == 0 || endMV == 0 && startY != 0) {
			$("#" + startM).val("1");
			$("#" + endM).val("1");
		} else {
			if (endYV - startYV == 0 && startMV - endMV > 0) {
				$("#" + startM).val(endMV+""); 
			}
		}
	},
	
	//起步价
	floorPrice:function(i) {
		var minP = new Number($("#minPrice" +i).val());
		var maxP = new Number($("#maxPrice" +i).val());
		if (!$("#maxPrice" +i).val()) {
			return ;
		} else if (maxP - minP <= 0) {
			$('#maxPrice' + i).numberbox('setValue', ' ');
			$('#addPrice' + i).numberbox('setValue', ' ');
			window.parent.$.messager.alert("提示", "结束价格必须大于开始价格");
			return;
		}
		dtCatPriceFn.addPrice(i);
	},
	
	//最大价格
	limtPrice:function(i) {
		var minP = new Number($('#minPrice' + i).val());
		var maxP = new Number($('#maxPrice' + i).val());
		if (maxP - minP <= 0) {
			$('#addPrice' + i).numberbox('setValue', ' ');
			$('#maxPrice' + i).numberbox('setValue', ' ');
			window.parent.$.messager.alert("提示", "结束价格必须大于开始价格");
			return;
		}
		dtCatPriceFn.addPrice(i);
	},
	
	//价格步长
	addPrice:function(i) {
		var min = $('#minPrice' + i).val();
		var max = $('#maxPrice' + i).val();
		var add = $('#addPrice' + i).val();
		if (!min || !max) {
			$('#addPrice' + i).numberbox('setValue', ' ');
			window.parent.$.messager.alert('提示', '请先填写价格范围');
		} else if(!add) {
			return ;
		} else if(new Number(max) - new Number(min) <= 0) {
			$('#addPrice' + i).numberbox('setValue', ' ');
			$('#maxPrice' + i).numberbox('setValue', ' ');
			window.parent.$.messager.alert("提示", "结束价格必须大于开始价格");
		} else {
			if ((new Number(max) - new Number(min))%new Number(add) != 0){
				$('#addPrice' + i).numberbox('setValue', ' ');
				window.parent.$.messager.alert('提示', '价格大于等于与价格小于的价差必须为步长的整数倍数');
			}
		}
	},
	
	floorPriceBlur:function(i) {
		if (!$('#minPrice' + i).val()) {
			$('#maxPrice' + i).numberbox('setValue', ' ');
			$('#addPrice' + i).numberbox('setValue', ' ');
		} else {
			dtCatPriceFn.vlidPrice(i, '#maxPrice' + i, '#addPrice' + i)
		}
	},
	
	limtPriceBlur:function(i) {
		if (!$('#minPrice' + i).val()) {
			$('#maxPrice' + i).numberbox('setValue', ' ');
		}
		dtCatPriceFn.vlidPrice(i, '#maxPrice' + i, '#addPrice' + i)
	},
	
	addPriceBlur:function(i) {
		if (!$('#minPrice' + i).val() || !$('#maxPrice' + i).val()) {
			$('#addPrice' + i).numberbox('setValue', ' ');
		}
		dtCatPriceFn.vlidPrice(i, '#UNKNOW', '#addPrice' + i)
	},
	
	vlidPrice:function(i, id1, id2) {
		var min = $('#minPrice' + i).val();
		var max = $('#maxPrice' + i).val();
		var add = $('#addPrice' + i).val();
		var minP = new Number(min);
		var maxP = new Number(max);
		var addP = new Number(add);
		if (maxP - minP <= 0) {
			$(id1).numberbox('setValue', ' ');
			$(id2).numberbox('setValue', ' ');
		} else if((new Number(max) - new Number(min))%new Number(add) != 0) {
			$(id2).numberbox('setValue', ' ');
		}
	},
	
	//验证查询添加是否合理
	validateSearchCondition:function() {
		var msg = '';
		//中分类
		var centreTypeCode = $('#centreTypeCode').combobox('getValue');
		if(!centreTypeCode) {
			msg += '中分类为必填条件<br>';
		} 
		//小分类
		var smallTypeCode = $('#smallTypeCode').combobox('getValue');
		if(!smallTypeCode) {
			msg += '小分类为必填条件<br>';
		} 
		//明细类
		var detailTypeCode = $("#detailTypeCode").combobox('getValue');
		if (!detailTypeCode) {
			msg += '明细类为必填条件<br>';
		} else {
			$("span[name=dtName]").html($('#detailTypeCode').combobox('getText'));
		}
		
		//地区
		var allCatRegion = $('#regionCode').combobox('getValue');
		if(!allCatRegion) {
			msg += '地区为必填条件<br>';
		} else {
			$("span[name=regionCon]").html($('#regionCode').combobox('getText'));
		}
		//时间范围1
		var y1 = new Number($("#endYear1").val()) - new Number($("#startYear1").val());
		var m1 = new Number($("#endMonth1").val()) - new Number($("#startMonth1").val());
		if (y1 > 0 || (y1 == 0 && m1 < 0)) {
			msg += '时间范围1的最长时间跨度为12个月<br>';
		}
		//时间范围2
		var y2 = new Number($('#endYear2').val()) - new Number($('#startYear2').val());
		var m2 = new Number($('#endMonth2').val()) - new Number($('#startMonth2').val());
		if (y2 > 0 || (y2 == 0 && m2 < 0)) {
			msg += '时间范围2的最长时间跨度为12个月<br>';
		}
		//手动设置价格带
		if ($('#handSetPri').attr('checked')=='checked') {
//			if ($("tr[name*=priceRegion").length > 10) {
//				msg += '手动设置价格带不能超过10条';
//			}
			$("tr[name*=priceRegion").each(function(i, obj){
				var name = $(obj).attr("name");
				dtCatPriceFn.floorPriceBlur(name.substring(name.length - 1));
				var min = $("#minPrice" +i).val();
				var max = $("#maxPrice" +i).val();
				var add = $("#addPrice" +i).val();
				if (!min || !max || !add) {
					msg += '第'+(i+1)+'条价格带数据填写不完整<br>';
				}
			});
		}
		return msg;
	},
	
	pageData:function(index,data) {
		clearSearch = true;
		$("#dtCatPrice" + index).datagrid({
			pagination : true,
			url:'',
			data : data.slice(0, 30)
		});
		var pager = $("#dtCatPrice" + index).datagrid("getPager");
		pager.pagination({
			total : data.length,
			onSelectPage : function(pageNo, pageSize) {
				var start = (pageNo - 1) * pageSize;
				var end = start + pageSize;
				$("#dtCatPrice" + index).datagrid("loadData",
						data.slice(start, end));
				pager.pagination('refresh', {
					total : data.length,
					pageNumber : pageNo
				});
			}
		}); 
		clearSearch = false;
	}
	
};