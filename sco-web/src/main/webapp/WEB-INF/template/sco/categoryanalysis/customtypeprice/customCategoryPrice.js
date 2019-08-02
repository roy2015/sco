var cusCatPriceGrid = null;

var firstIn = true;// 是否第一次进入页面
var clearSearch = false;// 清空查询
$(document).ready(
		function() {
			cusCatPriceGrid = utils.grid($('#cusCatPriceGrid'));
			cusCatPriceGrid.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", 
					"dxRoleCode", "dlRoleCode", "centreTypeCode", "smallTypeCode", "detailTypeCode", "fineTypeCode",
					"purchaseDepartments", "rationed");
			cusCatPriceGrid.initFilters({
				onBeforeLoad : function(obj) {
					// 清空查询
					if (clearSearch) {
						return true;
					}
					if (firstIn) {
						firstIn = false;
						return false;
					}
					var length = $.param(obj).length;
					// 判断参数个数
					if (length < 1) {
						if (!firstIn) 
							window.parent.$.messager.alert("提示", "请输入至少一项查询条件");
						firstIn = false;
						return false;
					}
				},
				onLoadSuccess : function() {
					$('#cusCatPriceGrid').datagrid("clearSelections");
				}
			});
			cuCatPriceFn.addedMerFilter();//已选择商品条件过滤
		});


var addedIndex = new Array();
var addedMerchandise = new Array();
var cuCatPriceFn = {
	
	//已添加商品表格过滤
	addedMerFilter:function() {
        var dg = $('#addedMerchandiseGrid').datagrid();
		dg.datagrid('enableFilter', 
		    [{
				field : 'status',
				type : 'combobox',
				options : {
					panelHeight : 'auto',
					data : [ {
						value : '',
						text : 'All'
					}, {
						value : 'P',
						text : 'P'
					}, {
						value : 'N',
						text : 'N'
					} ],
					onChange : function(value) {
						if (value == '') {
							dg.datagrid('removeFilterRule', 'status');
						} else {
							dg.datagrid('addFilterRule', {
								field : 'status',
								op : 'equal',
								value : value
							});
						}
						// dg.datagrid('doFilter');
					}
				}
			}]
		);
	},
	
	// 查询商品
	searchMer : function() {
		var param = cusCatPriceGrid.getFilterValue();
		if ($("#rationed").attr("checked") == "checked") {
			param.rationed = "gjz";
		} else {
			param.rationed = "dlz"; 
		}
		$('#cusCatPriceGrid').datagrid('load', param);
	},
	
	//清除在已选择商品中输入的查询条件
	clearMerchandise:function() {
		$('#addedMerchandiseGrid').parent().find('.datagrid-sort-desc,.datagrid-sort-asc')
			.removeClass('datagrid-sort-desc datagrid-sort-asc');
		$('#addedMerchandiseGrid').datagrid('removeFilterRule');
		$('#addedMerchandiseGrid').datagrid('doFilter');
	},
	
	//清除查询
	clearSearch:function(){
		clearSearch = true;
		$('#cusPrice_search').form('reset');
		clearComboboxOptions();
		$('#cusCatPriceGrid').parent().find('.datagrid-sort-desc,.datagrid-sort-asc')
			.removeClass('datagrid-sort-desc datagrid-sort-asc');
		$('#cusCatPriceGrid').datagrid('loadData',{total:0,rows:[]});
		$('#cusCatPriceGrid').datagrid('clearSelections');
		clearSearch = false;
	},
	
	mCodeSCodes : '',
	firAdd : true,
	//添加商品
	addMer:function() {
		
		var rows = $('#cusCatPriceGrid').datagrid('getSelections');
		if (rows.length < 1 ) {
			window.parent.$.messager.alert('提示', '请选择至少一条商品记录');
			return; 
		}
		var existsData = '';
		var flag = false;//本次选择的是否有添加成功的数据
		for (var i in rows) {
			var tmp = rows[i].merchandiseCode + "-" + rows[i].merchandiseName 
						+ "-" + rows[i].supplierCode + "-" + rows[i].supplierName;
			if (cuCatPriceFn.mCodeSCodes.indexOf(tmp) >= 0) {
				existsData += tmp + " 商品已存在\r\n";//已经存在的商品
			} else {
				cuCatPriceFn.mCodeSCodes += tmp + ",";
				var index = $('#addedMerchandiseGrid').datagrid('getRows').length;
				addedIndex[tmp] = index;
				
				if (cuCatPriceFn.firAdd) {
					addedMerchandise[index] = rows[i];
				}
				$('#addedMerchandiseGrid').datagrid('appendRow', rows[i]);
				flag = true;
			}
		}
		cuCatPriceFn.firAdd = false;
		if (existsData.length > 0) {
			$("#cpErrMsg").html(existsData);
			$("#msgDlg").window('open');//打开窗口
			$(".window-shadow").remove();
		}
		if (flag) {
			window.parent.$.messager.show({
				title:'提示',
				msg:'添加成功',
				timeout:5000,
				showType:'slide'
			});
		}
		_befFilterRows = {total:addedMerchandise.length , rows : addedMerchandise};
		$('#addedMerchandiseGrid').datagrid('doFilter');
	},
	
	//关闭消息框
	closeMsgDlg:function() {
		$("#cpErrMsg").html("");
		$("#msgDlg").dialog('close');
	},
	
	//删除已添加的商品
	delMerchandise:function() {
		var rows = $('#addedMerchandiseGrid').datagrid('getSelections');
		if (rows.length < 1 ) {
			window.parent.$.messager.alert('提示', '请选择至少一条商品记录');
			return; 
		}
		window.parent.utils.confirm("操作确认","确认删除 ?",function(){
			var del = '';
			for (var i in rows) {
				var tmp = rows[i].merchandiseCode + "-" + rows[i].merchandiseName 
					+ "-" + rows[i].supplierCode + "-" + rows[i].supplierName;
		
				cuCatPriceFn.mCodeSCodes = cuCatPriceFn.mCodeSCodes.replace(tmp,"");//去掉全局变量中对应的数据
				//删除记录(过滤前)
				del += addedIndex[tmp] + ",";
				//删除过滤后显示的
				var index = $('#addedMerchandiseGrid').datagrid('getRowIndex', rows[i]);
				$('#addedMerchandiseGrid').datagrid('deleteRow', index);
			}
			cuCatPriceFn.formatAddedMer(del);
			$('#addedMerchandiseGrid').datagrid('loadData', 
					{total:addedMerchandise.length , rows : addedMerchandise});
			$('#addedMerchandiseGrid').datagrid('unselectAll');
			_befFilterRows = {total:addedMerchandise.length , rows : addedMerchandise};
			$('#addedMerchandiseGrid').datagrid('doFilter');
		});
	},
	
	//重新整理已添加的商品
	formatAddedMer:function(del){
		var tmpMer = new Array();
		var delArr = del.split(",");
		var k = 0;
		for (var j in addedMerchandise) {
			addedIndex = new Array();
			var ifDel = false;
			for (var h in delArr) {
				if (j == delArr[h]) {
					ifDel = true;
					if (ifDel) break;
				}
			}
			if (!ifDel) {
				tmpMer[k] = addedMerchandise[j];
				k++;
			}
		}
		addedMerchandise = tmpMer;
		for (var i in addedMerchandise) {
			var rows = addedMerchandise[i];
			var tmp = rows.merchandiseCode + "-" + rows.merchandiseName 
				+ "-" + rows.supplierCode + "-" + rows.supplierName;
			addedIndex[tmp] = i;
		}
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
		cuCatPriceFn.selectStartMon('startYear' + i, 'startMonth' + i, 'endYear' + i, 'endMonth' + i);
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
				$("#" + endM).val(startMV + ""); 
			}
		}
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
		cuCatPriceFn.selectEndMon('startYear'+i, 'startMonth'+i, 'endYear'+i, 'endMonth'+i);
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
				$("#" + startM).val(endMV + ""); 
			}
		}
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
			+ '<input class="easyui-numberbox" style="width:80px;" id=minPrice'+ length +' name=minPrice'+ length +' maxlength="12" onchange="cuCatPriceFn.floorPrice('+"'"+length+"'"+')" onblur="cuCatPriceFn.floorPriceBlur('+"'"+length+"'"+')"/>'
			+ '&nbsp;元&nbsp;&nbsp;价格小于：&nbsp;'
			+ '<input class="easyui-numberbox" style="width:80px;" id=maxPrice'+ length +' name=maxPrice'+ length +' maxlength="12" onchange="cuCatPriceFn.limtPrice('+"'"+length+"'"+')" onblur="cuCatPriceFn.limtPriceBlur('+"'"+length+"'"+')" />'
			+ '&nbsp;元&nbsp;&nbsp;价格步长：&nbsp;'
			+ '<input class="easyui-numberbox" style="width:80px;" id=addPrice'+ length +' name=addPrice'+ length +' maxlength="12" onchange="cuCatPriceFn.addPrice('+"'"+length+"'"+')" onblur="cuCatPriceFn.addPriceBlur('+"'"+length+"'"+')" />'
			+ '&nbsp;元&nbsp;&nbsp;&nbsp;<input type="button" value="+" onclick="cuCatPriceFn.clonePriceRegion(this)" '
			+ 'style="width: 28px;" />'
			+ '&nbsp;&nbsp;&nbsp;<input type="button" value="-" onclick="cuCatPriceFn.removeCurrentRow(this)" '
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
		cuCatPriceFn.addPrice(i);
	},
	
	floorPriceBlur:function(i) {
		if (!$('#minPrice' + i).val()) {
			$('#maxPrice' + i).numberbox('setValue', ' ');
			$('#addPrice' + i).numberbox('setValue', ' ');
		} else {
			cuCatPriceFn.vlidPrice(i, '#maxPrice' + i, '#addPrice' + i)
		}
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
		cuCatPriceFn.addPrice(i);
	},
	
	limtPriceBlur:function(i) {
		if (!$('#minPrice' + i).val()) {
			$('#maxPrice' + i).numberbox('setValue', ' ');
		}
		cuCatPriceFn.vlidPrice(i, '#maxPrice' + i, '#addPrice' + i)
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
	
	addPriceBlur:function(i) {
		if (!$('#minPrice' + i).val() || !$('#maxPrice' + i).val()) {
			$('#addPrice' + i).numberbox('setValue', ' ');
		}
		cuCatPriceFn.vlidPrice(i, '#UNKNOW', '#addPrice' + i)
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
	
	//查看价格带汇总报表
	searchData:function(showPanel) {
		if(addedMerchandise.length < 1){
			window.parent.$.messager.alert('提示', "已选商品列表为空，请先选择需要分析的商品");
			return;
		}
		
		var msg = cuCatPriceFn.validateSearchCondition();
		if(msg.length > 1) {
			window.parent.$.messager.alert('提示', msg);
			return; 
		}
		
		var param = cuCatPriceFn.getSaveAndExportPara();
		var selectedData = '';
		for (var i in addedMerchandise) {
			selectedData += "'" + addedMerchandise[i].merchandiseCode + "-" + addedMerchandise[i].supplierCode + "',";
		}
		param.selectedData = selectedData.substring(0, selectedData.length - 1);//所选商品
		param = $.param(param);
		if ("A" == showPanel) {
			parent.addTabByUrl("自选商品价格带汇总报表", 'powertree', "customTypePrice_listCollectData_1.html?" + param);
		} else {
			parent.addTabByUrl("自选商品价格带明细报表", 'copy', "customTypePrice_listDetailData_1.html?" + param);
		}
	},
	
	//获取保存、导出所需参数
	getSaveAndExportPara:function() {
		var param2 = cuCatPriceFn.generatePara('2', {});
//		param2.rows = JSON.stringify(addedMerchandise);
		
		//日期区间
		param2.firSYear1 = $('#startYear1').val();
		param2.startYear1 = param2.firSYear1;
		
		param2.firSMonth1 = $('#startMonth1').val();
		param2.startMonth1 = param2.firSMonth1;
		
		param2.firEYear1 = $('#endYear1').val();
		param2.endYear1 = param2.firEYear1;
		
		param2.firEMonth1 = $('#endMonth1').val();
		param2.endMonth1 = param2.firEMonth1;
		
		param2.merTitle = "所有商品:自选商品";//商品范围
		param2.module = "CM";//模块名称
		return param2;
	},
	
	//验证查询添加是否合理
	validateSearchCondition:function() {
		var msg = '';
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
		//地区
		var allCatRegion = $('#searchRegionCode').combobox('getValue');
		if(!allCatRegion) {
			msg += '地区为必填条件<br>';
		}
		//手动设置价格带
		if ($('#handSetPri').attr('checked')=='checked') {
			$("tr[name*=priceRegion").each(function(i, obj){
				var name = $(obj).attr("name");
				cuCatPriceFn.floorPriceBlur(name.substring(name.length - 1));
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
	
	//查询参数
	generatePara:function(di, param) {
		//日期
		param.startYear = $('#startYear' + di).val();
		param.startYear2 = param.startYear;
		param.startMonth = $('#startMonth' + di).val();
		param.startMonth2 = param.startMonth;
		
		param.endYear = $('#endYear' + di).val();
		param.endYear2 = param.endYear;
		param.endMonth = $('#endMonth' + di).val();
		param.endMonth2 = param.endMonth;
//		param.time = param.startYear+"-"+param.startMonth
//				+"~"+param.endYear+"-"+param.endMonth;
		//公共部分
		param.regionCode = $('#searchRegionCode').combobox('getValue');
		param.regionName = $('#searchRegionCode').combobox('getText');
		
		//直营、加盟数据
		if ($('#direct').attr('checked') == 'checked') {
			param.dataType = 'D';
			param.directCon = "只看直营门店数据";
		} else if ($('#join').attr('checked') == 'checked') {
			param.dataType = 'J';
			param.directCon = "只看加盟门店数据";
		} else {
			param.dataType = 'A';
			param.directCon = "直营与加盟门店数据均看";
		}
		
		//价格区间
		if ($('#handSetPri').attr('checked') == 'checked') {
			param.priceSetCon = "手动设置价格带";
			var limitMaxPrice = 0 ;
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
			param.priceSetCon = "系统自动计算价格带";
		}
		return param;
	}
	
};