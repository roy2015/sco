var addReferMerchandiseFn = null;
var addReferIntentionFn = null;
var dlg = null;
var status = null;  //提示状态

var isShowAllRemarks = false;  //是否显示所有备注
var isHideAllRemarks = true;   //隐藏所有备注
var isShowThisRemarks = false; //是否只显示本次申请商品备注
var setUnits = false;          //是否设置统一对比单位
var contrastUnits = null;      //统一对比单位
var isShowUnits = false;       //是否显示报价计量单位
var isShowSubTotal = false;    //是否只显示小计

var refreshData = null;      //刷新需要的数据
//table初始化
var tableCK = "<tr id='cklist'><td></td></tr><tr id='rowtitle'><td class='table-head'><span class='text'>成本细项</span></td></tr>";
// 自定义对象
function merchandiseObj(accountingCode, applicationCode, merchandiseCode, supplierCode) {
	    this.applicationCode = applicationCode;
		this.accountingCode = accountingCode;
		this.merchandiseCode = merchandiseCode;
		this.supplierCode = supplierCode;
}

/**
 * rowTitleName 横向表头的标题,
 * accountingCode 核算编号,
 * merchandiseCode 商品编号,
 * supplierCode 供应商编号,
 * units 报价计量单位,
 * quotedDate 报价日期
 */
function merchandise(rowTitleName,accountingCode, applicationCode, merchandiseCode, supplierCode,quantity,quotedDate) {
		this.rowTitleName = rowTitleName;
		this.accountingCode = accountingCode;
		this.applicationCode = applicationCode;
		this.merchandiseCode = merchandiseCode;
		this.supplierCode = supplierCode;
		this.quantity = quantity;
		this.quotedDate = quotedDate;
}

$(document).ready(function() {
	//初始化变量
    setUnits = false;
	contrastUnits = null;
	status = null;
	isShowAllRemarks = false;
	isShowThisRemarks = false;
	isShowUnits = false;
	isShowSubTotal = false;
	isHideAllRemarks = true;
	//创建复选框行
	$("#contrastResult").append(tableCK);
	fillHead();
	fillBody();
	$(".remaks").hide();
	$(".units").hide();
});

/**
 * 解决参数过长导致URL无法正确请求
 * @param URL
 * @param PARAMS
 * @returns {___anonymous2288_2291}
 */
function post(URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        // alert(opt.name)        
        temp.appendChild(opt);        
    }        
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
}
/**
 * 填写表头
 */
function fillHead(){
	$.ajax({type : "POST",
		url : "totalCostAnalogyAnalysis_listHead_5.html",
		data : {data:$('#data').val(),inlandImport:$('#inlandImport').val()},
		dataType : 'json',
		success : function(data) {
		    //解析后台返回的纵向的数据
			//遍历添加表头数据
			for ( var key in data) {
				var value = data[key];
				$("#contrastResult").append("<tr id='" + key + "'></tr>");
				$("#contrastResult").find("#"+key).append("<td class='table-head'><span class='text'>" + value + "</span></td>");
			}
			//给一些行加上只显示小计的标识
			$("#merchandiseName,#merchandiseCode,#supplierName,#supplierCode,#units,#quotedDate,#accountingCode,#updateby"
					+ ",#region,#yield,#zlsubtotalValue,#flsubtotalValue,#iTotalcostValue,#mTotalcostValue,"
					+ "#packagproportionValue,#deductptcostValue,#nwpackagsubtotalValue,#subTotal,#sumPrice,#mcpPrice," 
					+ "#factoryPrice,#exchangeRate,#rmbSettlementPrice,#importFeeTotal,#cdAvtTotal,#customsClearanceTotal," 
					+ "#customsClearanceTotal")
			.attr("name", "small");
	}});
}

/**
 * 填充主数据
 */
function fillBody(){
	//加载本次申请商品的分析数据
	$.ajax({type : "POST",
		url : "totalCostAnalogyAnalysis_listThisTimeApplicationMerchandise_5.html",
		data : {data:$('#data').val(),inlandImport:$('#inlandImport').val()},
		dataType : 'json',
		success : function(data) {
			addTableInfo(data, 'thisApplication');
		}
	});
}

/**
 * 填充原料,内包装,外包装,损耗的空行，控制美观
 * @param tableName 要填充的表的class名字
 */
function fillNullRow(tableName){
	var maxTrs = 1;
	var table = null;
	var trs = null;
	var tables = $("."+tableName);
	for (var i = 0; i < tables.length; i++) {
		table = tables[i];
		trs = $(table).find("tr");
		maxTrs = $(trs).length>maxTrs?$(trs).length:maxTrs;
	}
	var nullTr = "";
	for (var i = 0; i < tables.length; i++) {
		table = tables[i];
		trs = $(table).find("tr");
		if ($(trs).length == maxTrs) {
			$(trs).last().find("td").css("border-bottom","0px");
		}else{
			$(trs).last().find("td").css("border-bottom","1px solid #dddddd");
			for (var j = 0; j <(maxTrs-$(trs).length); j++) {
					if (j == (maxTrs-$(trs).length -1)) {
						nullTr = "<tr><td style='border-left:0px;border-bottom:0px;text-align:left'>&nbsp;</td><td style='text-align:right;width:85px;border-bottom:0px;'>&nbsp;</td><td class='units' style='width:30%;border-bottom:0px;display:none;'>&nbsp;</td><td class='remarks' style='width:30%;text-align:left;white-space:normal;border-bottom:0px;display:none;'>&nbsp;</td></tr>";
					}else{
						nullTr = "<tr><td style='border-left:0px;text-align:left'>&nbsp;</td><td style='text-align:right;width:85px;'>&nbsp;</td><td class='units' style='width:30%;display:none;'>&nbsp;</td><td class='remarks' style='width:30%;text-align:left;white-space:normal;display:none;'>&nbsp;</td></tr>";
					}
				$(nullTr).appendTo($(table));  //给指定的table插入空行以至于和同类的table保持一样的高度
			}
		}
	}
}

function autoRegulationMaterial(className){
	var tables = $("."+className);
	var trs = $("."+className).find("tr");
	for (var rowIndex = 0; rowIndex < trs.length; rowIndex++) {
		var maxTrHeight = 0;
		for (var i = 0; i < tables.length; i++) {
			if ($(tables[i]).find("tr").eq(rowIndex).height()>maxTrHeight) {
				maxTrHeight = $(tables[i]).find("tr").eq(rowIndex).height();
			}
		}
		for (var i = 0; i < tables.length; i++) {
			if (isHideAllRemarks) {
				$(tables[i]).find("tr").eq(rowIndex).height(25);
			}else{
				$(tables[i]).find("tr").eq(rowIndex).height(maxTrHeight);
			}
		}
	}
}

/**
 * 格式化数字保留指定位数的精度
 * @param number 要格式话的数字
 * @param precision 精度的位数
 * @returns 格式化后的结果
 */
function round(number , precision){
	if (number == null) {
		return "";
	}else if(isNaN(parseFloat(number))){
		return number;
    }else{
    	return moneyFormatter(number.toFixed(precision),precision);
    }
}

function formatterRemarks(remarks){
	//把备注中undefined或者null替换为""
	return "<div class='text'>"+((remarks == ""||remarks == null)?"&nbsp;":remarks)+"</div>";
}

/**
 * 为每列添加数据
 * @param obj 该列的对象
 * @param merchandiseType 列的类型
 */
function addMerchandiseData(obj, merchandiseType){
	//获取横向表头下的所有的行
	var trList = $('#rowtitle').nextAll();
	var value = null;    // 值
	var remarks = null;  // 备注
	var units = null;    //报价计量单位
	// 遍历table的每一行,根据行的id去要读取的对象里取值
	for (var i = 0; i < trList.length; i++) {
	   value = '';
	   remarks = '';
	   if (obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == '') {
		    units = '';
		} else {
			units = obj['accounting']['quantity'] == null?'':(obj['accounting']['quantity'].toFixed(3)+obj['accounting']['units']);
		}
	   var row = trList[i];            // 获取当前遍历的行
	   var trId = $(row).attr('id');   // 获取当前行的Id
	   try {
			   	   if ($("#inlandImport").val() == "INLAND") {
			   		switch (trId) {
					case "merchandiseName":
						value = obj['accounting']['merchandiseName'] == null?obj['accounting']['intentionName']:obj['accounting']['merchandiseName'];
						remarks = "no"; //标志位no表示没有备注
						break;
					case "merchandiseCode":
						value = obj['accounting']['merchandiseCode'] == null?obj['accounting']['intentionCode']:obj['accounting']['merchandiseCode'];
						remarks = "no";
						break;
					case "supplierName":
						value = obj['accounting']['supplierName'] == null?obj['accounting']['intentionSupplierName']:obj['accounting']['supplierName'];
						remarks = "no";
						break;
					case "supplierCode":
						value = obj['accounting']['supplierCode'] == null?obj['accounting']['intentionSupplierCode']:obj['accounting']['supplierCode'];
						remarks = "no";
						break;
					case "units":
						value = units;
						remarks = "no";
						break;
					case "quotedDate":
						value = obj['accounting']['quotedDate'];
						remarks = "no";
						break;
					case "accountingCode":
						value = obj['accounting']['accountingCode'];
						remarks = "no";
						break;
					case "updateby":
						//因为快速调价的地区需要OA申请单号所以暂把OA申请单号藏在该处,供导出使用
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							value = "<input type='hidden' value='"+obj['oaApplicationCode']+"'></input>";
						} else {
							value = obj['accounting']['updateUserName']+"<input type='hidden' value='"+obj['oaApplicationCode']+"'></input>";
						}
						remarks = "no";
						break;
					case "region":
						//值
						var value = "<table class='regiontable innerTable'><tr>";
						$.each(obj['accountingRegionList'], function(index, region){
							value += "<td><span class='text' style='white-space:pre-wrap;'>" + region['region'] + "</span></td>";
						});
						units ="报价计量单位";
						remarks = "";
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
						         "<td class='remarks'><span class='text'>"+remarks+"</span></td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "material":
							value = "<table class='materialtable innerTable'>";
			           		var tr = "";
			           		if(obj['ingredientItemList']==''){
			           		remarks ="";
		           			tr = "<tr><td><span class='text'>"+""+""+"</span></td>" +
		           					 "<td class='price' style='width:85px'><span class='text'>"+""+"</span></td>";		  
		           			if (setUnits) {
		           				tr += "<td class='units'><span class='text'>"+""+"</span></td>";	
							} else {
								tr += "<td class='units'><span class='text'>"+""+"</span></td>";
							}
		           			tr += "<td class='remarks'>"+formatterRemarks(remarks)+"</td></tr>";
		           			value += tr;
			           		}else{
			           		for ( var index in obj['ingredientItemList']) {
			           			var material = obj['ingredientItemList'][index];
			           			if (material == null) {
			           				continue;
			           				
								}
			           			remarks = "原料采购价格(元/kg) : " + round(material["purchasePrice"],4) + "</br>" +
					           				  "原料投入量(kg) : " + round(material["inputCount"],4) + "</br>" +
					           				  "原料投入成本(元) : " + round(material["inputCost"],4) + "</br>" +
					           				  "备注  : " + material["remarks"];
			           			tr = "<tr><td><span class='text'>"+(material["materialType"]=="ZL"?"主料-":"辅料-")+material["materialName"]+"</span></td>" +
			           					 "<td class='price' style='width:85px'><span class='text'>"+round(material["avgCost"],4)+"</span></td>";		  
			           			if (setUnits) {
			           				tr += "<td class='units'><span class='text'>"+units+"</span></td>";	
								} else {
									tr += "<td class='units'><span class='text'>1.000"+obj['accounting']['units']+"</span></td>";
								}
			           			tr += "<td class='remarks'>"+formatterRemarks(remarks)+"</td></tr>";
			           			value += tr;
			           		}
			           		}
			           		value +="</table>";
			           		addInnerTableCell(trId, value);
					continue;
					case "npackag":
						value = "<table class='npackagtable innerTable'>";
		           		var tr = "";
		           		for ( var index in obj['accountingNPackagList']) {
		           			var npackag = obj['accountingNPackagList'][index];
		           			if (npackag == null) {
		   						continue;
		   					}
		           			switch (npackag['npackagType']) {
								case "FHD_JM":
								case "FHD_LSM":
									remarks = "具体材质&厚度  : " + npackag['texture'] + "</br>" + 
											  "公斤价格(元) : " + round(npackag['kgPrice'],3) + "</br>" +
							                  "重量占比(%) : " + round(npackag['weightProportion'],3) + "</br>" + 
							                  "备注  : " + npackag['remarks'];
									break;
								case "FHD_ZD":
									remarks = "具体材质&厚度  : " + npackag['texture'] + "</br>" + 
									          "尺寸(cm) : " + npackag['materialSize'] + "</br>" +
					                          "单个克重(g) : " + round(npackag['weight'],3) + "</br>" + 
					                          "单价(元) : " + round(npackag['unitsPrice'],3) + "</br>" +
					                          "公斤价格(元) : " + round(npackag['kgPrice'],3) + "</br>" +
					                          "数量  : " + round(npackag['quantity'],3) + "</br>" +
					                          "备注  : " + npackag['remarks'];
									break;
								case "ST":
									remarks = "具体材质&厚度  : " + npackag['texture'] + "</br>" + 
					                          "单个克重(g) : " + round(npackag['weight'],3) + "</br>" + 
					                          "公斤价格(元) : " + round(npackag['kgPrice'],3) + "</br>" +
					                          "重量占比(%) : " + round(npackag['weightProportion'],3) + "</br>" + 
					                          "备注  : " + npackag['remarks'];
									break;
								case "TYJ":
									remarks =  "单个克重(g) : " + round(npackag['weight'],3) + "</br>" + 
									           "尺寸(cm) : " + npackag['materialSize'] + "</br>" +
					                           "单价(元) : " + round(npackag['unitsPrice'],3) + "</br>" +
					                           "数量  : " + round(npackag['quantity'],3) + "</br>" +
					                           "备注  : " + npackag['remarks'];
									break;
								case "NDD":
									remarks =  "具体材质&厚度  : " + npackag['texture'] + "</br>" + 
						             		   "尺寸(cm) : " + npackag['materialSize'] + "</br>" +
					                           "单价(元) : " + round(npackag['unitsPrice'],3) + "</br>" +
					                           "数量  : " + round(npackag['quantity'],3) + "</br>" +
					                           "备注  : " + npackag['remarks'];
									break;
								case "BQ":
								case "ZZL":
									remarks =  "具体材质&厚度  : " + npackag['texture'] + "</br>" + 
						             		   "尺寸(cm) : " + npackag['materialSize'] + "</br>" +
					                           "数量  : " + round(npackag['quantity'],3) + "</br>" +
					                           "工艺要求  : " + npackag['technologyRequirements'] + "</br>" +
					                           "备注   : " + npackag['remarks'];
									break;
								case "ELSE":
									remarks =  "备注  : " + npackag['remarks'];
									break;
								default:
									remarks = "";
								    break;
							}
		           			tr = "<tr><td><span class='text'>"+npackag["npackagName"]+"</span></td>" +
		           					 "<td class='price' style='width:85px'><span class='text'>"+round(npackag["price"],3)+"</span></td>" +
		           					 "<td class='units'><span class='text'>"+units+"</span></td>" +
		           					 "<td class='remarks'>"+formatterRemarks(remarks)+"</td></tr>";
		           			value += tr;
					}
		           	value +="</table>";
		           	addInnerTableCell(trId, value);
					continue;
					case "wpackag":
						value = "<table class='wpackagtable innerTable'>";
		           		var tr = "";
		           		for ( var index in obj['accountingWPackagList']) {
		           			var wpackag = obj['accountingWPackagList'][index];
		           			if (wpackag == null) {
		   						continue;
		   					}
								switch (wpackag['wpackagType']) {
								case "FXD":
									remarks = "单价(元/米) : " + round(wpackag['unitsPrice'],3) + "</br>" + 
											  "使用量(元/米) : " + round(wpackag['useQuantity'],3) + "</br>" + 
											  "备注   : " + wpackag['remarks'];
									break;
								case "ZX":
									remarks = "具体材质说明:" + wpackag['texture'] + "</br>" + 
									 	      "长(cm) : " + round(wpackag['length'],3) + "</br>" + 
									 	      "宽(cm) : " + round(wpackag['width'],3) + "</br>" + 
									 	      "高(cm) : " + round(wpackag['height'],3) + "</br>" + 
									 	      "纸箱用料面积(㎡) : " + round(wpackag['area'],3) + "</br>" + 
							 	     	      "单价(元/只) : " + round(wpackag['unitsPrice'],3) + "</br>" + 
							 	     	      "纸箱用料单价(元/㎡) : " + round(wpackag['ylUnitsPrice'],3) + "</br>" + 
									 	  	  "箱规  : " + wpackag['specification'] + "</br>" + 
									 	      "备注  : " + wpackag['remarks'];
									break;
								case "ELSE":
									remarks = "备注  : " + wpackag['remarks'];
									break;
								default:
									remarks = "";
									break;
								}
								tr = "<tr><td><span class='text'>"+wpackag["wpackagName"]+"</span></td>" +
										 "<td class='price' style='width:85px'><span class='text'>"+round(wpackag["price"],3)+"</span></td>" +
										 "<td class='units'><span class='text'>"+units+"</span></td>" +
										 "<td class='remarks'>"+formatterRemarks(remarks)+"</td></tr>";
		           			    value += tr;
					}
		           	value +="</table>";
		           	addInnerTableCell(trId, value);
					continue;
					case "wastage":
						value = "<table class='wastagetable innerTable'>";
		           		var tr = "";
		           		for ( var index in obj['accountingWastageList']) {
		           			var wastage = obj['accountingWastageList'][index];
		           			if (wastage == null) {
		   						continue;
		   					}
		           			tr = "<tr><td><span class='text'>"+wastage["wastageType"]+"</span></td>" +
		           				     "<td class='price' style='width:85px'><span class='text'>"+round(wastage["price"],3)+"</span></td>" +
		           				     "<td class='units'><span class='text'>"+units+"</span></td>" +
		           				     "<td class='remarks'>"+formatterRemarks(wastage["remarks"])+"</td></tr>";
		           			value += tr;
						}
		           	value +="</table>";
		           	addInnerTableCell(trId, value);
					continue;
		           case "yield":
		           	var countSum = 0;
		           	var costSum = 0;
		           	var avgSum = 0;
						value = round(obj['accountingCostItem']['yieldValue'],2) == ""?"":(round(obj['accountingCostItem']['yieldValue'],2)+"%");
						$.each(obj['ingredientItemList'],function(index,item){
							countSum+=item["inputCount"];
							costSum+=item["inputCost"];
							avgSum+=item["avgCost"];
						});
						remarks = "投入量总计(kg) : " + round(countSum,4) + "</br>" +
								  "投入成本总计(元) : " + round(costSum,4) + "</br>" +
								  "平均成品原料成本总计(元/kg) : " + round(avgSum,2) + "</br>" +
								  "产品生产量(kg) : " + round(obj['ingredient']['productCount'],4) + "</br>" +
								  "成品含水率(%) : " + obj['ingredient']['moisture'] + "</br>" +
								  "备注 : " + obj['accountingCostItem']['yieldRemarks'];
						units = "1.000"+obj['accounting']['units'];
						break;
					case "zlsubtotalValue":
						value = round(obj['accountingCostItem']['zlsubtotalValue'],2);
						remarks = obj['accountingCostItem']['zlsubtotalRemarks'];
						units = "1.000"+obj['accounting']['units'];
						break;
					case "flsubtotalValue":
						value = round(obj['accountingCostItem']['flsubtotalValue'],2);
						remarks = obj['accountingCostItem']['flsubtotalRemarks'];	
						units = "1.000"+obj['accounting']['units'];
						break;
					case "iTotalcostValue":
						value = round(obj['accountingCostItem']['itotalcostValue'],2);
						remarks = obj['accountingCostItem']['itotalcostRemarks'];
						units = "1.000"+obj['accounting']['units'];
						break;
					case "mTotalcostValue":
						value = round((obj['accountingCostItem']['mtotalcostValue']),2);
						remarks = obj['accountingCostItem']['mtotalcostRemarks'];	
						break;
					case "packagproportionValue":
						value = round(obj['accountingCostItem']['packagproportionValue'],2) == ""?"":(round(obj['accountingCostItem']['packagproportionValue'],2)+'%');
						remarks = obj['accountingCostItem']['packagproportionRemarks'];	
						break;
					case "deductptcostValue":
						value = round(obj['accountingCostItem']['deductptcostValue'],2);
						remarks = obj['accountingCostItem']['deductptcostRemarks'];	
						break;
					case "nwpackagsubtotalValue":
						value = round(obj['accountingCostItem']['nwpackagsubtotalValue'],2);
						remarks = obj['accountingCostItem']['nwpackagsubtotalRemarks'];	
						break;
					case "wecPrice":
						  value = round(obj['accountingWec']['price'],3);
					    remarks = "耗水(元/t成品) : " + round(obj['accountingWec']['water'],3) +"</br>" + 
					    		  "耗油(元/t成品) : " + round(obj['accountingWec']['oil'],3) + "</br>" + 
								  "耗电(元/t成品) : " + round(obj['accountingWec']['electricity'],3) + "</br>" + 
								  "耗气(元/t成品) : " + round(obj['accountingWec']['gas'],3) + "</br>" + 
								  "耗煤(元/t成品) : " + round(obj['accountingWec']['coal'],3) + "</br>" + 
								  "合计(元/kg成品) : " + round(obj['accountingWec']['total'],3) + "</br>" + 
								  "备注 : " + obj['accountingWec']['remarks'];
						break;
					case "sbzjwhPrice":
						value = round(obj['accountingSbzjwh']['price'],3);
						remarks = "设备总价(万元) : " + round(obj['accountingSbzjwh']['totalPrice'],3) + "</br>" + 
								  "折旧年限(年) : " + round(obj['accountingSbzjwh']['ageLimit'],3) + "</br>" + 
								  "折旧值(万元/年) : " + round(obj['accountingSbzjwh']['depreciation'],3) + "</br>"+ 
								  "产能值(t成品/年) : " + round(obj['accountingSbzjwh']['capacity'],3) + "</br>" + 
								  "合计折旧值(元/kg成品) : " + round(obj['accountingSbzjwh']['total'],3) + "</br>" + 
								  "备注  : " + obj['accountingSbzjwh']['remarks'];
						break;
					case "ampPrice":
						  value = round(obj['accountingManpower']['price'],3);
						remarks = "车间工人数(人次) : " + round(obj['accountingManpower']['manpowerCount'],3) + "</br>" + 
								  "平均工资(元/人/月) : " + round(obj['accountingManpower']['avgWage'],3) + "</br>" + 
								  "月产量(t) : " + round(obj['accountingManpower']['monthCapacity'],3) + "</br>" + 
								  "每kg成品(元/kg) : " + round(obj['accountingManpower']['unitsWage'],3) + "</br>" + 
								  "备注  : " + obj['accountingManpower']['remarks'];
						break;
					case "amaPrice":
						value = round(obj['accountingManage']['price'],3);
						$.each(obj['accountingManageRegionList'],function(index,manageRegion){
							remarks += "进货地区 : " + manageRegion['region'] + "</br>" + 
					           		   "占比(%) : " + round(manageRegion['proportion'],3) + "</br>"
						});
						remarks += "备注 : " + obj['accountingManage']['remarks'];
						break;
					case "afrPrice":
						value = "<table class='innerTable'><tr>";
						for (var index = 0; index < obj['accountingRegionList'].length; index++) {
							var freightRegion = obj['accountingFreightRegionList'][index];
							if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}else{
								value += "<td class='price'><span class='text'>" + round(freightRegion['price'],3) + "</span></td>";
								remarks += "进货地区 : " + freightRegion['region'] + "</br>" + 
								           "总公里数(km) : "+ round(freightRegion['sumKm'],3) + "</br>";
							}
						}
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							remarks = "";
						}else{
							remarks += "单位成本(元/"+obj['accountingFreight']['units']+") : " + round(obj['accountingFreight']['unitsCost'],3) +"</br>"+
	              			           "备注 : " + obj['accountingFreight']['remarks'];
						}
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
				                 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "atrPrice":
						value = "<table class='innerTable'><tr>";
						for (var index = 0; index < obj['accountingRegionList'].length; index++) {
							var taxRegion = obj['accountingTaxRegionList'][index];
							if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}else{
								value += "<td class='price'><span class='text'>" + round(taxRegion['price'],3) + "</span></td>";
								remarks += "进货地区 : " + taxRegion['region'] + "</br>" + 
							    		   "占比(%) : "+ round(taxRegion['proportion'],3) + "</br>";
							}
						}
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							remarks = "";
						}else{
							remarks += "税率 (%): " + round(obj['accountingTax']['taxRate'],3) + "</br>"+
							           "备注 : " + obj['accountingTax']['remarks'];
						}
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
		                 	 	 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "aprPrice":
						value = "<table class='innerTable'><tr>";
						for (var index = 0; index < obj['accountingRegionList'].length; index++) {
							var profitRegion = obj['accountingProfitRegionList'][index];
							if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}else{
								value += "<td class='price'><span class='text'>" + round(profitRegion['price'],3) + "</span></td>";
								remarks += "进货地区 : " + profitRegion['region'] + "</br>" + 
									 	   "占比(%) : " + round(profitRegion['proportion'],3) + "</br>";
							}
						}
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							remarks = "";
						}else{
							remarks += "备注 : " + obj['accountingProfit']['remarks'];
						}
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
		                 		 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "subTotal":
						value = "<table class='innerTable'><tr>";
						for (var index = 0; index < obj['accountingRegionList'].length; index++) {
							var elsesubtotalRegion = obj['accountingElsesubtotalRegionList'][index];
							if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}else{
								value += "<td class='price'><span class='text'>" + round(elsesubtotalRegion['subtotal'],2) + "</span></td>";
							}
						}
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							remarks = "";
						}else{
							remarks = obj['accountingElsesubtotal']['remarks'];
						}
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
		                     	 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "sumPrice":
						value = "<table class='innerTable'><tr>";
						for (var index = 0; index < obj['accountingRegionList'].length; index++) {
							var aggregateRegion = obj['accountingAggregateRegionList'][index];
							if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}else{
								value += "<td class='price'><span class='text'>" + round(aggregateRegion['sumPrice'],2) + "</span></td>";
							}
						}
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							remarks = "";
						}else{
							remarks = obj['accountingAggregate']['remarks'];
						}
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
		                 		 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "mcpPrice":
						value = "<table class='innerTable'><tr>";
						for (var j = 0; j < obj['accountingRegionList'].length; j++) {
							if (merchandiseType == "referMerchandise") {
								var price = round(obj['merchandiseContractPrices'][j]['price'],2);
								value += "<td class='price'><span class='text'>" + price + "</span></td>";
							}else{
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}
						}
						remarks = "";
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
                		 	     "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					default:
						break;
			        }
			   }else{
			   //进口
			   		switch (trId) {
					case "merchandiseName":
						value = obj['accounting']['merchandiseName'] == null?obj['accounting']['intentionName']:obj['accounting']['merchandiseName'];
						remarks = "no"; //标志为no表示没有备注
						break;
					case "merchandiseCode":
						value = obj['accounting']['merchandiseCode'] == null?obj['accounting']['intentionCode']:obj['accounting']['merchandiseCode'];
						remarks = "no";
						break;
					case "supplierName":
						value = obj['accounting']['supplierName'] == null?obj['accounting']['intentionSupplierName']:obj['accounting']['supplierName'];
						remarks = "no";
						break;
					case "supplierCode":
						value = obj['accounting']['supplierCode'] == null?obj['accounting']['intentionSupplierCode']:obj['accounting']['supplierCode'];
						remarks = "no";
						break;
					case "units":
						value = units;
						remarks = "no";
						break;
					case "quotedDate":
						value = obj['accounting']['quotedDate'];
						remarks = "no";
						break;
					case "accountingCode":
						value = obj['accounting']['accountingCode'];
						remarks = "no";
						break;
					case "updateby":
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							value = "<input type='hidden' value='"+obj['oaApplicationCode']+"'></input>";
						} else {
							value = obj['accounting']['updateUserName']+"<input type='hidden' value='"+obj['oaApplicationCode']+"'></input>";
						}
						remarks = "no";
						break;
					case "region":
						//值
						var value = "<table class='regiontable innerTable'><tr>";
						$.each(obj['accountingRegionList'], function(index, region){
							value += "<td><span class='text' style='white-space:pre-wrap;'>" + region['region'] + "</span></td>";
						});
						units ="报价计量单位";
						remarks = "";
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
						         "<td class='remarks'><span class='text'>"+remarks+"</span></td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "material":
							value = "<table class='materialtable innerTable'>";
			           		var tr = "";
			           		if(obj['ingredientItemList']==''){
			           		remarks ="";
		           			tr = "<tr><td><span class='text'>"+""+""+"</span></td>" +
		           					 "<td class='price' style='width:85px'><span class='text'>"+""+"</span></td>";		  
		           			if (setUnits) {
		           				tr += "<td class='units'><span class='text'>"+""+"</span></td>";	
							} else {
								tr += "<td class='units'><span class='text'>"+""+"</span></td>";
							}
		           			tr += "<td class='remarks'>"+formatterRemarks(remarks)+"</td></tr>";
		           			value += tr;
			           		}else{
			           		for ( var index in obj['ingredientItemList']) {
			           			var material = obj['ingredientItemList'][index];
			           			if (material == null) {
			           				continue;
								}
			           			remarks = "原料采购价格(元/kg) : " + round(material["purchasePrice"],4) + "</br>" +
					           				  "原料投入量(kg) : " + round(material["inputCount"],4) + "</br>" +
					           				  "原料投入成本(元) : " + round(material["inputCost"],4) + "</br>" +
					           				  "备注  : " + material["remarks"];
			           			tr = "<tr><td><span class='text'>"+(material["materialType"]=="ZL"?"主料-":"辅料-")+material["materialName"]+"</span></td>" +
			           					 "<td class='price' style='width:85px'><span class='text'>"+round(material["avgCost"],4)+"</span></td>";		  
			           			if (setUnits) {
			           				tr += "<td class='units'><span class='text'>"+units+"</span></td>";	
								} else {
									tr += "<td class='units'><span class='text'>1.000"+obj['accounting']['units']+"</span></td>";
								}
			           			tr += "<td class='remarks'>"+formatterRemarks(remarks)+"</td></tr>";
			           			value += tr;
								
			           		}
			           		}
			           		value +="</table>";
			           		addInnerTableCell(trId, value);
					continue;
					case "npackag":
						value = "<table class='npackagtable innerTable'>";
		           		var tr = "";
		           		for ( var index in obj['accountingNPackagList']) {
		           			var npackag = obj['accountingNPackagList'][index];
		           			if (npackag == null) {
		   						continue;
		   					}
		           			switch (npackag['npackagType']) {
								case "FHD_JM":
								case "FHD_LSM":
									remarks = "具体材质&厚度  : " + npackag['texture'] + "</br>" + 
											  "公斤价格(元) : " + round(npackag['kgPrice'],3) + "</br>" +
							                  "重量占比(%) : " + round(npackag['weightProportion'],3) + "</br>" + 
							                  "备注  : " + npackag['remarks'];
									break;
								case "FHD_ZD":
									remarks = "具体材质&厚度  : " + npackag['texture'] + "</br>" + 
									          "尺寸(cm) : " + npackag['materialSize'] + "</br>" +
					                          "单个克重(g) : " + round(npackag['weight'],3) + "</br>" + 
					                          "单价(元) : " + round(npackag['unitsPrice'],3) + "</br>" +
					                          "公斤价格(元) : " + round(npackag['kgPrice'],3) + "</br>" +
					                          "数量  : " + round(npackag['quantity'],3) + "</br>" +
					                          "备注  : " + npackag['remarks'];
									break;
								case "ST":
									remarks = "具体材质&厚度  : " + npackag['texture'] + "</br>" + 
					                          "单个克重(g) : " + round(npackag['weight'],3) + "</br>" + 
					                          "公斤价格(元) : " + round(npackag['kgPrice'],3) + "</br>" +
					                          "重量占比(%) : " + round(npackag['weightProportion'],3) + "</br>" + 
					                          "备注  : " + npackag['remarks'];
									break;
								case "TYJ":
									remarks =  "单个克重(g) : " + round(npackag['weight'],3) + "</br>" + 
									           "尺寸(cm) : " + npackag['materialSize'] + "</br>" +
					                           "单价(元) : " + round(npackag['unitsPrice'],3) + "</br>" +
					                           "数量  : " + round(npackag['quantity'],3) + "</br>" +
					                           "备注  : " + npackag['remarks'];
									break;
								case "NDD":
									remarks =  "具体材质&厚度  : " + npackag['texture'] + "</br>" + 
						             		   "尺寸(cm) : " + npackag['materialSize'] + "</br>" +
					                           "单价(元) : " + round(npackag['unitsPrice'],3) + "</br>" +
					                           "数量  : " + round(npackag['quantity'],3) + "</br>" +
					                           "备注  : " + npackag['remarks'];
									break;
								case "BQ":
								case "ZZL":
									remarks =  "具体材质&厚度  : " + npackag['texture'] + "</br>" + 
						             		   "尺寸(cm) : " + npackag['materialSize'] + "</br>" +
					                           "数量  : " + round(npackag['quantity'],3) + "</br>" +
					                           "工艺要求  : " + npackag['technologyRequirements'] + "</br>" +
					                           "备注   : " + npackag['remarks'];
									break;
								case "ELSE":
									remarks =  "备注  : " + npackag['remarks'];
									break;
								default:
									remarks = "";
								    break;
							}
		           			tr = "<tr><td><span class='text'>"+npackag["npackagName"]+"</span></td>" +
		           					 "<td class='price' style='width:85px'><span class='text'>"+round(npackag["price"],3)+"</span></td>" +
		           					 "<td class='units'><span class='text'>"+units+"</span></td>" +
		           					 "<td class='remarks'>"+formatterRemarks(remarks)+"</td></tr>";
		           			value += tr;
					}
		           	value +="</table>";
		           	addInnerTableCell(trId, value);
					continue;
					case "wpackag":
						value = "<table class='wpackagtable innerTable'>";
		           		var tr = "";
		           		for ( var index in obj['accountingWPackagList']) {
		           			var wpackag = obj['accountingWPackagList'][index];
		           			if (wpackag == null) {
		   						continue;
		   					}
								switch (wpackag['wpackagType']) {
								case "FXD":
									remarks = "单价(元/米) : " + round(wpackag['unitsPrice'],3) + "</br>" + 
											  "使用量(元/米) : " + round(wpackag['useQuantity'],3) + "</br>" + 
											  "备注   : " + wpackag['remarks'];
									break;
								case "ZX":
									remarks = "具体材质说明:" + wpackag['texture'] + "</br>" + 
									 	      "长(cm) : " + round(wpackag['length'],3) + "</br>" + 
									 	      "宽(cm) : " + round(wpackag['width'],3) + "</br>" + 
									 	      "高(cm) : " + round(wpackag['height'],3) + "</br>" + 
									 	      "纸箱用料面积(㎡) : " + round(wpackag['area'],3) + "</br>" + 
							 	     	      "单价(元/只) : " + round(wpackag['unitsPrice'],3) + "</br>" + 
							 	     	      "纸箱用料单价(元/㎡) : " + round(wpackag['ylUnitsPrice'],3) + "</br>" + 
									 	  	  "箱规  : " + wpackag['specification'] + "</br>" + 
									 	      "备注  : " + wpackag['remarks'];
									break;
								case "ELSE":
									remarks = "备注  : " + wpackag['remarks'];
									break;
								default:
									remarks = "";
									break;
								}
								tr = "<tr><td><span class='text'>"+wpackag["wpackagName"]+"</span></td>" +
										 "<td class='price' style='width:85px'><span class='text'>"+round(wpackag["price"],3)+"</span></td>" +
										 "<td class='units'><span class='text'>"+units+"</span></td>" +
										 "<td class='remarks'>"+formatterRemarks(remarks)+"</td></tr>";
		           			    value += tr;
					}
		           	value +="</table>";
		           	addInnerTableCell(trId, value);
					continue;
					case "wastage":
						value = "<table class='wastagetable innerTable'>";
		           		var tr = "";
		           		for ( var index in obj['accountingWastageList']) {
		           			var wastage = obj['accountingWastageList'][index];
		           			if (wastage == null) {
		   						continue;
		   					}
		           			tr = "<tr><td><span class='text'>"+wastage["wastageType"]+"</span></td>" +
		           				     "<td class='price' style='width:85px'><span class='text'>"+round(wastage["price"],3)+"</span></td>" +
		           				     "<td class='units'><span class='text'>"+units+"</span></td>" +
		           				     "<td class='remarks'><span class='text'>"+wastage["remarks"]+"</span></td></tr>";
		           			value += tr;
						}
		           	value +="</table>";
		           	addInnerTableCell(trId, value);
				   continue;
		           case "yield":
	        	    var countSum = 0;
		           	var costSum = 0;
		           	var avgSum = 0;
						value = round(obj['accountingCostItem']['yieldValue'],2) == ""?"":(round(obj['accountingCostItem']['yieldValue'],2)+"%");
						$.each(obj['ingredientItemList'],function(index,item){
							countSum+=item["inputCount"];
							costSum+=item["inputCost"];
							avgSum+=item["avgCost"];
						});
						remarks = "投入量总计(kg) : " + round(countSum,4) + "</br>" +
								  "投入成本总计(元) : " + round(costSum,4) + "</br>" +
								  "平均成品原料成本总计(元/kg) : " + round(avgSum,2) + "</br>" +
								  "产品生产量(kg) : " + round(obj['ingredient']['productCount'],4) + "</br>" +
								  "成品含水率(%) : " + obj['ingredient']['moisture'] + "</br>" +
								  "备注 : " + obj['accountingCostItem']['yieldRemarks'];
						units = "1.000"+obj['accounting']['units'];
						break;
					case "zlsubtotalValue":
						value = round(obj['accountingCostItem']['zlsubtotalValue'],2);
						remarks = obj['accountingCostItem']['zlsubtotalRemarks'];
						units = "1.000"+obj['accounting']['units'];
						break;
					case "flsubtotalValue":
						value = obj['accountingCostItem']['flsubtotalValue']==null?"0.00":round(obj['accountingCostItem']['flsubtotalValue'],2);
						remarks = obj['accountingCostItem']['flsubtotalRemarks'];	
						units = "1.000"+obj['accounting']['units'];
						break;
					case "factoryPrice"://商品报价
						value = round(obj['accountingFactoryPrice']['price'],4);
						remarks = "币种 : " + obj['accountingFactoryPrice']['currency'] +"</br>" + 
			    		  	   	  "付款方式 : " + obj['accountingFactoryPrice']['paymentType'] + "</br>" + 
						          "备注 : " + obj['accountingFactoryPrice']['remarks'];
						break;
					case "exchangeRate"://汇率
						value = round(obj['accountingExchangerate']['exchangerate'],4) == ""?"":(round(obj['accountingExchangerate']['exchangerate'],4));
						remarks = "参考日期 : " + obj['accountingExchangerate']['referenceDate'] +"</br>" + 
	    		  		          "参考银行 : " + obj['accountingExchangerate']['referenceBank'] + "</br>" + 
				                  "备注 : " + obj['accountingExchangerate']['remarks'];	
						break;
					case "rmbSettlementPrice"://商品人民币结算价格
						value = round(obj['accountingCostItem']['rmbSettlementPrice'],4);
						remarks = obj['accountingCostItem']['rmbSettlementPriceRemarks'];	
						break;
					case "oceanfreight"://海运费/空运费
						var containerType = obj['accountingOceanfreight']['containerTypeString'];
						value = round(obj['accountingOceanfreight']['price'],4);
						remarks = "运输时段 : " + (obj['accountingOceanfreight']['transportStartDate'] == null?"":obj['accountingOceanfreight']['transportStartDate'].toString().substring(0,10)+"到"+obj['accountingOceanfreight']['transportEndDate'].toString().substring(0,10))+"</br>" + 
	  		               		  "出发港 : " + obj['accountingOceanfreight']['starting'] + "</br>" + 
	  		               		  "到达港 : " + obj['accountingOceanfreight']['destination'] + "</br>" + 
	  		               		  "货柜类型 : " + containerType + "</br>" + 
	  		               		  "货柜尺寸 : " + obj['accountingOceanfreight']['containerSize'] + "</br>" + 
	  		                      "单价(元/货柜) : " + round(obj['accountingOceanfreight']['unitPrice'],4) + "</br>" + 
	  		                      "货柜内容物数量&重量 : " + obj['accountingOceanfreight']['containerCapacity'] + "</br>" + 
	  		               		  "计算方式 : " + obj['accountingOceanfreight']['computeType'] + "</br>" + 
	  		               		    "备注 : " + obj['accountingOceanfreight']['remarks'];	
						break;
					case "orderFee"://换单费
						value = round(obj['accountingCostItem']['updateOrderFee'],4);
						remarks = obj['accountingCostItem']['updateOrderFeeRemarks'];
						break;
					case "premium"://保险费
						value = round(obj['accountingCostItem']['premium'],4);
						remarks = obj['accountingCostItem']['premiumRemarks'];	
						break;
					case "customscharges"://报关服务费
						value = round(obj['accountingCustomscharges']['price'],4);
						remarks =  "报关费 : " + obj['accountingCustomscharges']['customscharges'] +"</br>" + 
			               		   "港杂费 : " + obj['accountingCustomscharges']['portSurcharge'] + "</br>" + 
			               		   "滞港费 : " + obj['accountingCustomscharges']['demurrageCharge'] + "</br>" + 
			               		   "污箱费 : " + obj['accountingCustomscharges']['containerDirtynessChange'] + "</br>" + 
			               		   "其他费用 : " + obj['accountingCustomscharges']['elseFee'] + "</br>" + 
			               		   "备注 : " + obj['accountingCustomscharges']['remarks'];	
						break;
					case "importFeeTotal"://进口费用小计
						value = round(obj['accountingCostItem']['importFeeTotal'],4);
						remarks = obj['accountingCostItem']['importFeeTotalRemarks'];	
						break;
					case "customsduties"://关税
						value = round(obj['accountingCustomsduties']['price'],4);
						remarks =   "HS编码 : " + obj['accountingCustomsduties']['hsCode'] +"</br>" + 
			               		    "税率(%) : " + round(obj['accountingCustomsduties']['taxRate'],4) + "</br>" + 
			               	        "关税计算方式 : " + obj['accountingCustomsduties']['customsdutiesComputeType'] + "</br>" + 
			               		    "备注 : " + obj['accountingCustomsduties']['remarks'];
						break;
					case "addedvaluetax"://增值税
						value = round(obj['accountingAddedvaluetax']['price'],4);
						remarks =   "税率(%) : " + round(obj['accountingAddedvaluetax']['taxRate'],4) +"</br>" + 
			               		    "增值税计算方式  : " + obj['accountingAddedvaluetax']['addedvaluetaxComputeType'] + "</br>" + 
			               		    "备注 : " + obj['accountingAddedvaluetax']['remarks'];	
						break;
					case "cdAvtTotal"://关税、增值税小计
						value = round(obj['accountingCostItem']['cdAvtTotal'],4);
						remarks = obj['accountingCostItem']['cdAvtTotalRemarks'];	
						break;
					case "customsClearanceTotal"://清关后商品总成本
						value = round(obj['accountingCostItem']['customsClearanceTotal'],4);
						remarks = obj['accountingCostItem']['customsClearanceTotalRemark'];	
						break;
					case "packagproportionValue"://包装占比
						value = round(obj['accountingCostItem']['packagproportionValue'],2) == ""?"":(round(obj['accountingCostItem']['packagproportionValue'],2)+'%');
						remarks = obj['accountingCostItem']['packagproportionRemarks'];	
						break;
					case "deductptcostValue"://扣除包装后原材料总成本
						value = round(obj['accountingCostItem']['deductptcostValue'],4);
						remarks = obj['accountingCostItem']['deductptcostRemarks'];	
						break;
					case "nwpackagsubtotalValue"://内外包装材料价格小计
						value = round(obj['accountingCostItem']['nwpackagsubtotalValue'],4);
						remarks = obj['accountingCostItem']['nwpackagsubtotalRemarks'];	
						break;
					case "taxDiffer"://税差
						value = round(obj['accountingTaxDiffer']['price'],4);
						remarks = "税差计算方式 : " + obj['accountingTaxDiffer']['taxDifferComputeType'] +"</br>" + 
	               		   		  "备注 : " + obj['accountingTaxDiffer']['remarks'];
						break;
					case "interest"://利息
						value = round(obj['accountingInterest']['price'],4);
						remarks = "贷款利率(%) : " + round(obj['accountingInterest']['loanRate'],4) +"</br>" + 
							      "利息计算方式 : " + obj['accountingInterest']['interestComputeType'] +"</br>" + 
   		   		                  "备注 : " + obj['accountingInterest']['remarks'];
						break;
					case "wecPrice"://水电煤
						  value = round(obj['accountingWec']['price'],4);
					    remarks = "耗水(元/t成品) : " + round(obj['accountingWec']['water'],4) +"</br>" + 
					    		  "耗油(元/t成品) : " + round(obj['accountingWec']['oil'],4) + "</br>" + 
								  "耗电(元/t成品) : " + round(obj['accountingWec']['electricity'],4) + "</br>" + 
								  "耗气(元/t成品) : " + round(obj['accountingWec']['gas'],4) + "</br>" + 
								  "耗煤(元/t成品) : " + round(obj['accountingWec']['coal'],4) + "</br>" + 
								  "合计(元/kg成品) : " + round(obj['accountingWec']['total'],4) + "</br>" + 
								  "备注 : " + obj['accountingWec']['remarks'];
						break;
					case "sbzjwhPrice"://设备折旧及维护
						value = round(obj['accountingSbzjwh']['price'],4);
						remarks = "设备总价(万元) : " + round(obj['accountingSbzjwh']['totalPrice'],4) + "</br>" + 
								  "折旧年限(年) : " + round(obj['accountingSbzjwh']['ageLimit'],4) + "</br>" + 
								  "折旧值(万元/年) : " + round(obj['accountingSbzjwh']['depreciation'],4) + "</br>"+ 
								  "产能值(t成品/年) : " + round(obj['accountingSbzjwh']['capacity'],4) + "</br>" + 
								  "合计折旧值(元/kg成品) : " + round(obj['accountingSbzjwh']['total'],4) + "</br>" + 
								  "备注   : " + obj['accountingSbzjwh']['remarks'];
						break;
					case "ampPrice"://人工
						  value = round(obj['accountingManpower']['price'],4);
						remarks = "车间工人数(人次)  : " + round(obj['accountingManpower']['manpowerCount'],4) + "</br>" + 
								  "平均工资(元/人/月) : " + round(obj['accountingManpower']['avgWage'],4) + "</br>" + 
								  "月产量(t)  : " + round(obj['accountingManpower']['monthCapacity'],4) + "</br>" + 
								  "每kg成品(元/kg) : " + round(obj['accountingManpower']['unitsWage'],4) + "</br>" + 
								  "备注  : " + obj['accountingManpower']['remarks'];
						break;
					case "amaPrice"://管理
						value = round(obj['accountingManage']['price'],4);
						$.each(obj['accountingManageRegionList'],function(index,manageRegion){
							remarks += "进货地区 : " + manageRegion['region'] + "</br>" + 
					           		   "占比(%) : " + round(manageRegion['proportion'],4) + "</br>";
						});
						remarks += "备注 : " + obj['accountingManage']['remarks'];
						break;
					case "afrPrice"://运输
						value = "<table class='innerTable'><tr>";
						for (var index = 0; index < obj['accountingRegionList'].length; index++) {
							var freightRegion = obj['accountingFreightRegionList'][index];
							if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}else{
								value += "<td class='price'><span class='text'>" + round(freightRegion['price'],4) + "</span></td>";
								remarks += "进货地区 : " + freightRegion['region'] + "</br>" + 
								  			"总公里数(km) : "+ round(freightRegion['sumKm'],4) + "</br>";
							}
						}
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							remarks = "";
						}else{
							remarks += "单位成本(元/"+obj['accountingFreight']['units']+") : " + round(obj['accountingFreight']['unitsCost'],4)+"</br>"+
				              		   "备注 : " + obj['accountingFreight']['remarks'];
						}
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
		                 		 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "atrPrice"://税收
						value = "<table class='innerTable'><tr>";
						for (var index = 0; index < obj['accountingRegionList'].length; index++) {
							var taxRegion = obj['accountingTaxRegionList'][index];
							if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}else{
								value += "<td class='price'><span class='text'>" + round(taxRegion['price'],4) + "</span></td>";
								remarks += "进货地区 : " + taxRegion['region'] + "</br>" + 
							    		   "占比(%) : "+ round(taxRegion['proportion'],4) + "</br>";
							}
						}
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							remarks = "";
						}else{
							remarks += "税率 (%): " + round(obj['accountingTax']['taxRate'],4) + "</br>"+
							   		   "备注 : " + obj['accountingTax']['remarks'];
						}
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
                		 		 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "aprPrice"://利润
						value = "<table class='innerTable'><tr>";
						for (var index = 0; index < obj['accountingRegionList'].length; index++) {
							var profitRegion = obj['accountingProfitRegionList'][index];
							if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}else{
								value += "<td class='price'><span class='text'>" + round(profitRegion['price'],4) + "</span></td>";
								remarks += "进货地区 : " + profitRegion['region'] + "</br>" + 
										   "占比(%) : " + round(profitRegion['proportion'],4) + "</br>";
							}
						}
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							remarks = "";
						}else{
							remarks += "备注 : " + obj['accountingProfit']['remarks'];
						}
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
       		 		 			 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "subTotal"://其它成本小计
						value = "<table class='innerTable'><tr>";
						for (var index = 0; index < obj['accountingRegionList'].length; index++) {
							var elsesubtotalRegion = obj['accountingElsesubtotalRegionList'][index];
							if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}else{
								value += "<td class='price'><span class='text'>" + round(elsesubtotalRegion['subtotal'],4) + "</span></td>";
							}
						}
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							remarks = "";
						}else{
							remarks = obj['accountingElsesubtotal']['remarks'];
						}
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
	 		 			 		 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "sumPrice"://总价
						value = "<table class='innerTable'><tr>";
						for (var index = 0; index < obj['accountingRegionList'].length; index++) {
							var aggregateRegion = obj['accountingAggregateRegionList'][index];
							if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}else{
								value += "<td class='price'><span class='text'>" + round(aggregateRegion['sumPrice'],2) + "</span></td>";
							}
						}
						if(obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == ''){
							remarks = "";
						}else{
							remarks = obj['accountingAggregate']['remarks'];
						}
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
	 			 		 		 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					case "mcpPrice"://合作价格
						value = "<table class='innerTable'><tr>";
						for (var j = 0; j < obj['accountingRegionList'].length; j++) {
							if (merchandiseType == "referMerchandise") {
								var price = round(obj['merchandiseContractPrices'][j]['price'],2);
								value += "<td class='price'><span class='text'>" + price + "</span></td>";
							}else{
								value += "<td class='price'><span class='text'>&nbsp;</span></td>";
							}
						}
						remarks = "";
						value += "<td class='units'><span class='text'>"+units+"</span></td>"+
		 		 		 		 "<td class='remarks'>"+formatterRemarks(remarks)+"</td>";
						value += "</tr></table>";
						addInnerTableCell(trId, value);
						continue;
					default:
						break;
			        }
			   }
	   } catch (e) {
		   value = "";
		   remarks = "";
	   }
	   
		// 如果最终还是未找到则赋值/
		if (value == null || value == undefined) {
			value = "";
		}
		if (remarks == null||remarks == undefined) {
			remarks = "&nbsp";
		}
		//把备注中undefined或者null替换为""
		remarks = remarks.replace(/undefined/ig, "").replace(/null/ig, "");
		//把值中undefined或者null替换为"/"
		value = value.toString().replace(/undefined/ig, "").replace(/null/ig, "");
		addCell(trId, obj, value, remarks, units, merchandiseType);
	}
}

/**
 * 添加单元格
 * @param trId 该单元格所在行的Id
 * @param obj 该列的对象
 * @param value 该单元格的值
 * @param remarks 该单元格的备注
 * @param units 报价计量单位
 * @param listType 该列的类型
 */
function addCell(trId, obj, value, remarks, units, listType) {
	//如果是核算编号,需要加超链接
	if (trId == "accountingCode") {
		$('#' + trId).append("<td><a style='text-decoration:none;' onclick='openEditAccounting(this)' href='javascript:void(0)'><span class='text'>" + value + "</span></a></td>");
	} else {
		//如果没有备注比如前几行的 不需要后面额外追加单元格
		if (remarks=="no"){
			$('#' + trId).append("<td><span class='text'>" + value + "</span></td>");
		} else {//否则执行添加带备注的单元格
			if (obj['accounting']['accountingCode'] == null||obj['accounting']['accountingCode'] == '') {
	       		value = "";
				remarks ="";
				units = "";
			}
			$('#' + trId).append("<td>" + appendTableCell(obj,value,remarks,units,listType,trId) + "</td>");
		}
	}
}

/**
 * 添加地区,原料,内外包装,损耗
 */
function addInnerTableCell(trId,value){
	value = value.replace(/undefined/g, "").replace(/null/g, ""); //把undefined或者null替换成空
	$('#' + trId).append("<td>" + value + "</td>");
}
/**
 * 添加带备注的单元格
 * @param obj 该列的对象
 * @param value 值
 * @param remarks 备注
 * @param listType 该列的类型
 * @param units 报价计量单位
 * @param trId 改行的Id
 */
function appendTableCell(obj,value,remarks,units,listType,trId){
	return "<table class='innerTable'>" +
				"<tr>" +
					"<td class='price'><span class='text'>"+value+"</span></td>" +
					"<td class='units'><span class='text'>"+units+"</span></td>" +
					"<td class='remarks'>"+formatterRemarks(remarks)+"</td>" +
				"</tr>" +
			"</table>"
}

/**
 * 构建成本分析页面及数据
 * @param data 要添加的对象
 * @param listType 表头的类型
 */
function addTableInfo(data, listType) {
	if (data == null) return;
	for ( var i in data) {
		if (data[i] == null) continue;
		//添加表头的复选框
		$("#cklist").append("<td><input name='ck' type='checkbox'/></td>");
		//添加不同的表头
		switch (listType) {
		case 'thisApplication':
			$('#rowtitle').append("<td><span class='text'>本次申请商品-供应商报价</span></td>");
			break;
		case 'referMerchandise':
			$('#rowtitle').append("<td><span class='text'>参照商品-历史合作报价</span></td>");
			break;
		case 'referIntention':
			$('#rowtitle').append("<td><span class='text'>参照意向品-历史合作报价</span></td>");
			break;
		default:
			$('#rowtitle').append("<td><span class='text'></span></td>");
			break;
		}
		//添加列的数据
		addMerchandiseData(data[i],listType);
		//如果是设置计量单位的,需要把转换好的列插入到指定的列的后面
		if (setUnits) {
			//获取核算表编号行
			var accountingCodeTr = $('#contrastResult').find('#accountingCode');
			//获取核算编号的最后一个单元格
			var lastAccounting = $(accountingCodeTr).children('td').last();
			//获取核算编号的最后一个单元格的索引
			var lastIndex = $(lastAccounting).index();
			//获取要转换计量单位的列的索引
			var index = $(accountingCodeTr).find('td:contains('+$(lastAccounting).text()+')').first().index();
			//获取相应的标题行
			var listTitle = $('#rowtitle').find('td').eq(index).text();
			//修改标题行
			$('#rowtitle').find('td').last().text(listTitle+'-报价单位转换后');
			//获取对比结果的所有行
			var trList = $('#contrastResult').children().children('tr');
			//把装换好的列插入到对应的要转换单位的列的后面
			$.each(trList,function(i,n){
				var convertTd = $(n).children('td').eq(lastIndex);
				$(convertTd).insertAfter($(n).children('td').eq(index));
			});
		}
	}
	//去掉本次申请商品的选择框
	var thisApplicationTds = $("#rowtitle").find("td:contains(本次申请商品)");
	for (var i = 0; i < thisApplicationTds.length; i++) {
		var index = $(thisApplicationTds[i]).index();
		$("#cklist").find("td").eq(index).empty();
	}
	fillNullRow("materialtable");
	fillNullRow("npackagtable");
	fillNullRow("wpackagtable");
	fillNullRow("wastagetable");
	//初始化成本分析页面的状态
	$(".remarks").hide(); //隐藏备注
	$(".units").hide(); //隐藏报价计量单位
	isShowAllRemarks = false;
	isHideAllRemarks = true;
	isShowThisRemarks = false;
	isShowUnits = false;
}

/**
 * 刷新成本分析结果页面
 * @param data 数据
 */
function refreshMerchandise(data){
	var merList = eval(refreshData);
	if (data == null) {
		return ;
	}
	for (var i = 0; i < merList.length; i++) {
		$("#cklist").append("<td><input name='ck' type='checkbox'/></td>");
		var listTitle = merList[i]["rowTitleName"];
		$('#rowtitle').append("<td><span class='text'>"+listTitle+"</span></td>");
		//添加每一列的数据
		if (JSON.stringify(listTitle).indexOf("本次申请商品") != -1) {
			addMerchandiseData(data[i],"thisApplication");
		} else if(JSON.stringify(listTitle).toString().indexOf("参照商品") != -1){
			addMerchandiseData(data[i],"referMerchandise");
		}else if(JSON.stringify(listTitle).toString().indexOf("参照意向品") != -1){
			addMerchandiseData(data[i],"referIntention");
		}else{
			addMerchandiseData(data[i],"convert");
		}
	}
		$(".remarks").hide();
		$(".units").hide();
		//把显示备注的状态改为false;
		isShowAllRemarks = false;
		isHideAllRemarks = true;
		isShowThisRemarks = false;
		isShowUnits = false;
		fillNullRow("materialtable");
		fillNullRow("npackagtable");
		fillNullRow("wpackagtable");
		fillNullRow("wastagetable");
}

/***
 * 更新报价计量单位的提示信息(每个0.5s)
 */
function updateUnitsTip () {
	//更改设置计量单位的提示
	var tempUnits = null;
	var tdUnits = $("#units").children("td").first().nextAll().not("td:contains('/')");
	var count = 0;
	//判断设置的对比单位示范和现有的一致,如果一致则提示单位相同,不需要设置
	$.each(tdUnits,function(i,n){
		if (i == 0) {
			tempUnits = $(n).text();
		}else{
			if($(n).text() == tempUnits){
				count ++;
			}
		}
	});
	if (count == tdUnits.length -1) {
		status = "same";
	}else{
		status = "diff";
	}
	$("#unitsTip").empty();
	if (status == "same") {
		$("#unitsTip").text("以下成本对比分析表中所有商品/意向品的核算表与投料表中的所有报价计量单位均一致，该计量单位为："+tempUnits);
	}
	if (status == "diff") {
		$("#unitsTip").text("以下成本对比分析表中包含多种报价计量单位");
		if (setUnits&&contrastUnits!=null) {
			$("#unitsTip").text($("#unitsTip").text()+",目前已增加统一对比单位："+contrastUnits+"KG");
		}else{
			$("#unitsTip").text($("#unitsTip").text()+",目前并未设置统一对比单位");
		}
	}
	
}

/***
 * 定时矫正Table(每0.5s)
 */
function rightTable(){
	var regions = $("#region").children().first().nextAll();
	var maxTdLength = 0;
	var maxTextLength = 0;
	var minTdWidth = 250;
	var innerRegionTds = null;
	var maxTextLength = 0;
	if (regions != null) {
		for (var i = 0; i < regions.length; i++) {
			if ($(regions[i]) != null) {
				if($(regions[i]).find("td").length>maxTdLength){
					maxTdLength=$(regions[i]).find("td").length-2;
					innerRegionTds = $(regions[i]).find("td");
				}
			}
		}
		if (innerRegionTds != null) {
			for (var i = 0; i < innerRegionTds.length; i++) {
				if($(innerRegionTds[i]).not(".units").not(".remarks").text().length>maxTextLength){
					maxTextLength=$(innerRegionTds[i]).not(".units").not(".remarks").text().length;
				}
			}
		}
	}
	var regionTdWidth = 100*maxTdLength>minTdWidth?100*maxTdLength:minTdWidth;
	var tdCount = $("#rowtitle").children().first().nextAll().length;
	//控制table的宽度,根据内容来实时增加,10是padding的宽度,95是计量单位的宽度,210是备注的宽度
	if (isHideAllRemarks) {
		if (isShowUnits) {
			$("#contrastResult").width(tdCount*(regionTdWidth+10+95)+160);
		} else {
			$("#contrastResult").width(tdCount*(regionTdWidth+10)+160);
		}
	}else{
		if (isShowUnits) {
			$("#contrastResult").width(tdCount*(regionTdWidth+10+300)+160);
		} else {
			$("#contrastResult").width(tdCount*(regionTdWidth+10+205)+160);
		}
	}
	autoRegulationMaterial("materialtable");
	autoRegulationMaterial("npackagtable");
	autoRegulationMaterial("wpackagtable");
	autoRegulationMaterial("wastagetable");
	//美化整体table的每列的宽度
	var tableWidth = $("#contrastResult").width();
	var tdWidth = (tableWidth-160)/tdCount;
	$("#contrastResult").children().children().first().children().first().nextAll().width(tdWidth);
	if (isHideAllRemarks) {
		$.each($(".innerTable"),function(i,n){
			$(n).width("100%");
			$(n).height(25);
		});
	}else{
		$.each($(".innerTable"),function(i,n){
			$(n).width($(n).parent().width());
			$(n).height($(n).parent().height());
		});
	}
};


/**
 * 修改核算表/投料表
 * 
 * @param accounting 核算表的a标签
 */
function openEditAccounting(accounting){
	//获取对应的值
   	var accountingCode = $(accounting).text();
	var url = "accounting_showLoadAccountingForm_1.html";
	var url_param = '?accountingCode=' + accountingCode;
	//添加选项卡
	parent.addTabByUrl("核算/投料表"+accountingCode, "icon-save", url + url_param);
}
window.setInterval("updateUnitsTip()",500);
window.setInterval("rightTable()",500);

// 分析结果页面功能方法
var costContrastFn = {
	/**
	 * 关闭
	 */
	close : function() {
		parent.pubTab.closeCurrTab();
	},
	/**
	 * 刷新
	 */
	refresh : function() {
		refreshData =  costContrastFn.getAllMerchandise();
		$("#contrastResult").children().html("");
		$("#contrastResult").children().append(tableCK);
		fillHead();
		$.post("totalCostAnalogyAnalysis_listThisTimeApplicationMerchandise_5.html",{"data":refreshData,"inlandImport":$("#inlandImport").val()},
			function(data) {
			refreshMerchandise($.parseJSON(data));
			//去掉本次申请商品的复选框
			var thisApplicationTds = $("#rowtitle").find("td:contains(本次申请商品)");
			for (var i = 0; i < thisApplicationTds.length; i++) {
				var index = $(thisApplicationTds[i]).index();
				$("#cklist").find("td").eq(index).empty();
			}
		});
		isShowAllRemarks = false;
		isShowThisRemarks = false;
		isHideAllRemarks = true;
		isShowUnits = false;
		isShowSubTotal = false;
		$("#onlyShowsubtotal").attr("checked",false);
	},
	/**
	 * 填写保存文件名称
	 */
	saveFile : function() {
		$("#saveFileDlg").window('open');// 打开窗口
	},
	/**
	 * 关闭界面
	 */
	closeSaveFileDlg : function() {
		$("#fileName").val("");// 清空填写的值
		$("#saveFileDlg").window('close');// 关闭窗口
	},
	/**
	 *  提交填写文件名称的对话框
	 */
	submitSaveFileDlg : function() {
		$("#save").linkbutton("disable");
		var fileName = $.trim($("#fileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				$("#save").linkbutton("enable");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			$("#save").linkbutton("enable");
			return;
		}
		//获取分析表的html代码
		var param =$("#contrastResult").html();
		//获取一共有多少列
		var cols = $("#rowtitle").find("td:contains('本次申请商品')");
		var rowTitleName = null;
		var accountingCode = null;
		var merchandiseCode = null;
		var supplierCode = null;
		var quotedDate = null;
		var tempList = [];
		var merSup = [];//用于存储商品编号+供应商编号下最大的日期
		for (var i = 0; i < cols.length; i++) {
			rowTitleName = $("#rowtitle td").eq($(cols[i]).index()).text();
			accountingCode = $("#accountingCode td").eq($(cols[i]).index()).text();
			merchandiseCode = $("#merchandiseCode td").eq($(cols[i]).index()).text();
			supplierCode = $("#supplierCode td").eq($(cols[i]).index()).text();
			quotedDate = $("#quotedDate td").eq($(cols[i]).index()).text();
			if (rowTitleName.indexOf("报价单位转换后") > 0) {
				continue;
			}
			var obj = new merchandise("",accountingCode, null, merchandiseCode, supplierCode,0,quotedDate);
			tempList.push(obj);
			var index = merchandiseCode + supplierCode ;//根据商品编号+供应商编号来存储日期
			var value = quotedDate;//当前值
			var date = merSup[index];
			
			var curdate = new Date(Date.parse(value.replace(/-/g, "/")));
			var maxDate = !date ? "" :  new Date(Date.parse(date.replace(/-/g, "/")));
			if (!date || maxDate < curdate) {
				merSup[index] = value;
			}
		}
		var filtered = []; //用于存储过滤后的数据
		var existsMaxDateCount = [];//用于存储商品编号+供应商编号下相同最大日期的数据个数
		var ifCount = false;
		for (var i in tempList) {
			var obj = tempList[i];
			var index = obj.merchandiseCode + obj.supplierCode ;//根据商品编号+供应商编号来存储日期
			if (merSup[index] == obj.quotedDate) {
				var count = existsMaxDateCount[index];
				var tCount = (!count ? new Number(0) : new Number(count)) + new Number(1);
				if (tCount > 1) {//刚开始肯定没有 加了之后第一次为1,后面如果大于1,每次加完就里面判断了是否大于1,所以上面可以不需要判断
					ifCount = true;
					break;//遇到一个重复的即报错,这里用break
				}
				existsMaxDateCount[index] = tCount;//将商品编号+供应商编号对应的最大日期数量存进数组中
				filtered.push(tempList[i]);//
			}
		}
		//如果在商品编号+供应商编号下有最大价格日期大于1的数据,则重复   
		if (ifCount) {
			$.messager.alert("提示", "报价日期重复!");
			$("#save").linkbutton("enable");
			return;
		}
		//转换数组
		var info = JSON.stringify(filtered).replace(/"([^"]*)"/g, "'$1'");
		var width = $("#contrastResult").css("width");
		$.post("totalCostAnalogyAnalysis_saveSearchDataForm_2.html?", {data : param, info : info, fileName : fileName, width : width}, function(data) {
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
			$("#save").linkbutton("enable");
		});
	},
	/**
	 * 导出到Excel
	 */
	export2Excel : function() {
		var url = "totalCostAnalogyAnalysis_exportDataToExcel_6.html";
		url += "?setUnits="+setUnits+"&contrastUnits="+contrastUnits+"&isShowAllRemarks="+isShowAllRemarks+
		"&isShowThisRemarks="+isShowThisRemarks+"&isShowUnits="+isShowUnits+"&isShowSubTotal="+isShowSubTotal+
		"&isHideAllRemarks="+isHideAllRemarks+"&inlandImport="+$("#inlandImport").val();
		//转换数组
		//url += "&data="+costContrastFn.getAllMerchandise();
		//解决参数过长导致URL无法正确请求
		post(url,{data:costContrastFn.getAllMerchandise()});
		/*window.location = url;*/
		$.messager.show({
			title : '提示',
			msg : '数据导出中,请稍后...',
			timeout : 4000,
			showType : 'slide'
		});
	},
	/**
	 * 添加参照品意向品
	 */
	addReferIntention : function() {
		if (setUnits) {
			$.messager.alert("提示", "请先取消设置统一对比单位");
			return;
		}
		var title = "添加参照品-意向品";
		var href = "totalCostAnalogyAnalysis_showAddReferIntention_1.html";
		dlg = utils.showDlg({
			title : title,
			href : href,
			width : 1160,
			height : 500
		});
	},
	/**
	 * 添加参照品商品
	 */
	addReferMerchandise : function() {
		if (setUnits) {
			$.messager.alert("提示", "请先取消设置统一对比单位");
			return;
		}
		var title = "添加参照品-商品";
		var href = "totalCostAnalogyAnalysis_showAddReferMerchandise_1.html";
		dlg = utils.showDlg({
			title : title,
			href : href,
			width : 1160,
			height : 500
		});
	},
	/**
	 * 移除参照品
	 */
	removeRefer : function() {
		var length = $('#cklist').find("input:checked").length;
		if (length == 0) {
			$.messager.alert("提示", "请选择至少一个参照品");
			return;
		}
		//获取选中的td
		var checkedTds = $('#cklist').find("input:checked");
		//获取所有的tr行
		var trList = $("#contrastResult").children().children("tr")
		
		if (setUnits) {
			$.each(checkedTds,function(i,n){
				//获取选中的td的index
				var checkedTdIndex = $(n).parent().index();
				//查找与要删除的列具有相同核算边行的列的索引
			    var checkedAccountingCode = $("#accountingCode").find("td").eq(checkedTdIndex).text();
			    var sameAccountingCodeRefs = $("#accountingCode").find("td:contains("+checkedAccountingCode+")");
			    //删除与要删除的列具有相同核算边行的列
			    $.each(sameAccountingCodeRefs,function(index,refOjb){
			    	var tempIndex = $(refOjb).index();
			    	//遍历所有行,隐藏对应index的td
					$.each(trList,function(index,tr){
						var td = $(tr).children().eq(tempIndex)
						td.detach();
					})
			    })
			})
		}else{
			$.each(checkedTds,function(i,n){
				//获取选中的td的index
				var checkedTdIndex = $(n).parent().index();
				var trList = $("#contrastResult").children().children("tr")
				//遍历所有行,隐藏对应index的td
				$.each(trList,function(index,tr){
					var td = $(tr).children().eq(checkedTdIndex)
					td.detach();
				})
			});
		}
	},
	/**
	 * 修改核算表/投料表
	 */
	editAccounting : function() {
		var length = $('#cklist').find("input:checked").length;
		if(length == 0){
			$.messager.alert("提示", "请选择至少一个本次申请商品");
			return;
		}else{
			if (length > 1) {
				$.messager.alert("提示", "不能勾选多条数据");
				return;
			}
			var checkedTdIndex = $('#cklist').find("input:checked").parent().index();
			//获取对应的值
			var accountingCode = $("#accountingCode").children().eq(checkedTdIndex).text();
			if (accountingCode == null || accountingCode == '' || accountingCode == '/') {
				$.messager.alert("提示", "不能勾选没有核算/投料表编号的商品!");
				return;
			}
			var url = "accounting_showLoadAccountingForm_1.html";
			var url_param = '?accountingCode=' + accountingCode;
			//添加选项卡
			parent.addTabByUrl("核算/投料表"+accountingCode, "icon-save", url + url_param);
		}
	},
	/**
	 * 向右移动
	 */
	right : function() {
		var length = $('#cklist').find("input:checked").length;
		if (length > 1) {
			$.messager.alert("提示", "不能勾选多条数据");
		}else{
			//获取选中的td的index
			var checkedTdIndex = $('#cklist').find("input:checked").parent().index();
			if (checkedTdIndex == $('#cklist').children().length -1) {
				$.messager.alert("提示", "该参照品已在最右侧，不可再向右移动");
				return;
			}
			var trList = $("#contrastResult").children().children("tr");
			$.each(trList,function(index,tr){
					var td = $(tr).children().eq(checkedTdIndex);
					td.insertAfter($(td).next());
			});
		}
	},
	/**
	 * 向左移动
	 */
	left : function() {
		var length = $('#cklist').find("input:checked").length;
		if (length>1) {
			$.messager.alert("提示", "不能勾选多条数据");
		}else{
			//获取选中的td的index
			var  checkedTdIndex = $('#cklist').find("input:checked").parent().index();
			var prevTd = $('#rowtitle').children().eq(checkedTdIndex-1);
			if ($(prevTd).html().indexOf("本次申请商品") != -1) {
				$.messager.alert("提示", "该参照品已在最左侧，不可再向左移动");
				return;
			}
			var trList = $("#contrastResult").children().children("tr");
			//判断是否是最左边
			if (checkedTdIndex != 1&&checkedTdIndex != -1) {
				$.each(trList,function(index,tr){
					var td = $(tr).children().eq(checkedTdIndex);
					td.insertBefore($(td).prev());
				});
			}
		}
	},
	/**
	 * 设置统一对比单位
	 */
	setContrastUnits : function() {
		if($("#units").find("td:contains('KG')").length>0&&$("#units").find("td:contains('L')").length>0){
			$.messager.alert("提示", "当前分析结果中同时存在KG及L计量单位，不可设置统一对比单位");
		}else{
			var title = '设置统一对比单位';
			var href = 'totalCostAnalogyAnalysis_showSetContrastUnits_1.html';
			dlg = utils.showDlg({
				title : title,
				href : href,
				width : 400,
				height : 150,
				buttons : [ {
					text : '确定',
					handler : function() {
						costContrastFn.submitForm(dlg);
					},
					iconCls : 'ok'
				}, {
					text : '关闭',
					handler : function() {
						dlg.dialog('close');
					},
					iconCls : 'cancel'
				} ]
			});
		}
	},
	/**
	 * 提交设置统一对比单位
	 * @param dlg 弹出框对象
	 */
	submitForm : function(dlg) {
		var temp = "";//临时存储对比单位
		temp = $("#contrastUnits").val();
		if (temp == null || temp == "") {
			$.messager.alert("提示", "请填入统一对比单位数值");
			return;
		}
		var tdUnits = $("#units").children("td").first().nextAll().not("td:contains('/')");
		//判断设置的对比单位示范和现有的一致,如果一致则提示单位相同,不需要设置
		var count = 0;
		$.each(tdUnits,function(i,n){
			if(parseFloat($(n).text().replace("KG","")) == parseFloat(temp)){
				count++;
			}
		});
		//如果所有的报价计量单位和要设置的一致则弹出提示，不需要设置
		if (count == tdUnits.length) {
			$.messager.alert("提示", "现有成本分析表中的报价单位均与您填的单位相同，不需要转换");
			return;
		}
		//如果设置过了报价计量单位，则取消之前的，根据本次设置的来变化
		if (setUnits) {
			costContrastFn.cancelContrastUnits();
		}
		var data = costContrastFn.getAllMerchandise(temp);
		$.ajax({type : "POST",
			url : "totalCostAnalogyAnalysis_setContrastUnits_5.html?data=" + data+"&&units="+temp,
			dataType : 'json',
			success : function(data) {
				//把设置统一对比单位的状态改为true
				setUnits = true;
				dlg.dialog('close');
				//添加分析数据
				addTableInfo(data, 'convert');
				contrastUnits = temp;
			}
	});
		
	},
	/**
	 * 取消设置统一对比单位
	 */
	cancelContrastUnits : function() {
		var indexs = $("#rowtitle").children("td:contains('报价单位转换后')");
		if (indexs.length==0) {
			$.messager.alert("提示", "当前成本分析表中不存在转换对比单位后的记录，无需移除");
			return;
		}
		var trList = $("#contrastResult").children().children("tr");
		$.each(indexs,function(i,obj){
			var unitsIndex = $(obj).index();
			//遍历所有行,移除对应index的td	
			$.each(trList,function(index,tr){
					var td = $(tr).children().eq(unitsIndex);
					$(td).remove();
			});
		});
		//把计量单位的值置为null
		contrastUnits = null;
		//把设置统一对比单位的状态改为false
		setUnits = false;
	},
	/**
	 * 显示所有备注
	 */
	showAllRemarks : function() {
		$(".remarks").show();
		// 把显示所有备注的状态改为true
		isShowAllRemarks = true;
		isHideAllRemarks = false;
		isShowThisRemarks = false;
	},
	/**
	 * 只显示本次申请商品备注
	 */
	onlyShowThisTimeRemarks : function() {
		//把只显示本次申请商品的备注的状态改为true
		isShowAllRemarks = false;
		isHideAllRemarks = false;
		isShowThisRemarks = true;
		onlyShowThisTimeRemarks();
	},
	/**
	 * 隐藏备注
	 */
	hidenRemarks : function() {
		if (isHideAllRemarks) {
			$.messager.alert("提示", "当前成本分析表中未显示备注，无需隐藏");
			return;
		}
		$(".remarks").hide();
		//把显示备注的状态改为false;
		isShowAllRemarks = false;
		isHideAllRemarks = true;
		isShowThisRemarks = false;
	},
	/**
	 * 显示/隐藏报价计量单位
	 */
	showHidenUnits : function() {
		$(".units").toggle();
		if($(".units").is(':hidden')){
			isShowUnits = false;
		}else{
			isShowUnits = true;
		}
	},
	/**
	 * 只显示小计
	 * @param smallCK  只显示小计的jquery对象
	 */
	onlyShowSmall : function(smallCK) {
		if ($(smallCK).attr('checked') == 'checked') {
			$('#rowtitle').nextAll('tr[name!="small"]').hide();
			//把显示小计的状态改为fasle;
			isShowSubTotal = true;
		} else {
			$('#rowtitle').nextAll('tr[name!="small"]').show();
			//把显示小计的状态改为true;
			isShowSubTotal = false;
		}
	},
	/**
	 * 获取分析结果里的基本信息(商品编号，商品名称，供应商编号，供应商名称，核算编号，对应列的Title，对应列的报价计量单位)
	 */
	getAllMerchandise: function(units){
		var merList = [];
		//获取一共有多少列
		var cols = $("#rowtitle").find("td");
		var rowTitleName = null;
		var accountingCode = null;
		var applicationCode = null;
		var merchandiseCode = null;
		var supplierCode = null;
		var quantity = null;
		var quotedDate = null;
		for (var i = 1; i < cols.length; i++) {
			rowTitleName = $("#rowtitle td").eq(i).text();
			accountingCode = $("#accountingCode td").eq(i).text();
			applicationCode = $("#updateby td").eq(i).find("input").val();
			merchandiseCode = $("#merchandiseCode td").eq(i).text();
			supplierCode = $("#supplierCode td").eq(i).text();
			quantity = $("#units td").eq(i).text().replace("KG","").replace("L","");
			quotedDate = $("#quotedDate td").eq(i).text();
			if (units == null||quantity != units) {
				var temp = new merchandise(rowTitleName, accountingCode, applicationCode, merchandiseCode, supplierCode, quantity==''?1:quantity, quotedDate);
				merList.push(temp);
			}
		}
		return JSON.stringify(merList).replace(/"([^"]*)"/g, "'$1'");
	}
};

/**
 * 控制只显示本次申请商品的备注
 */
function onlyShowThisTimeRemarks(){
	function tempInfo(merchandiseCode,supplierCode,quotedDate,index) {
		this.merchandiseCode = merchandiseCode;
		this.supplierCode = supplierCode;
		this.quotedDate = quotedDate;
		this.index = index
	}
	var trList = $("#contrastResult").children().children("tr");
	var tds = $("#rowtitle").find("td:contains('本次申请商品')");
	var noThisTds = $("#rowtitle").find("td:contains('参照')");
	var ss = [];
	var list = [];
	var noThisList = [];
	//排除相同物料号，但是报价日期较早的列
	var merchandiseCode = null;
	var supplierCode = null;
	var quotedDate = null;
	var merSup = [];//用于存储商品编号+供应商编号下最大的日期
	$.each(tds,function(i,n){
		merchandiseCode = $("#merchandiseCode td").eq($(n).index()).text();
		supplierCode = $("#supplierCode td").eq($(n).index()).text();
		quotedDate = $("#quotedDate td").eq($(n).index()).text();
		
		var obj = new tempInfo(merchandiseCode,supplierCode,quotedDate,$(n).index());
		ss.push(obj);
		var index = merchandiseCode + supplierCode ;//根据商品编号+供应商编号来存储日期
		var value = quotedDate;//当前值
		var date = merSup[index];
		
		var curdate = new Date(Date.parse(value.replace(/-/g, "/")));
		var maxDate = !date ? "" :  new Date(Date.parse(date.replace(/-/g, "/")));
		if (!date || maxDate < curdate) {
			merSup[index] = value;
		}
	})
	
	for (var i in ss) {
		var obj = ss[i];
		var index = obj.merchandiseCode + obj.supplierCode ;//根据商品编号+供应商编号来存储日期
		if (merSup[index] == obj.quotedDate) {
			list.push(ss[i]);
		}else{
			noThisList.push(ss[i]);
		}
	}
	
	$.each(noThisTds,function(i,n){
		merchandiseCode = $("#merchandiseCode td").eq($(n).index()).text();
		supplierCode = $("#supplierCode td").eq($(n).index()).text();
		quotedDate = $("#quotedDate td").eq($(n).index()).text();
		noThisList.push(new tempInfo(merchandiseCode,supplierCode,quotedDate,$(n).index()));
	});
	
	//本次申请商品的列宽的调整
	$.each(list,function(i,n){
		var index = n["index"];
		//遍历所有行,移除对应index的td	
		$.each(trList,function(trIndex,tr){
			var td = $(tr).children().eq(index);
			$(td).find(".remarks").show();
		})
	})
	
	//非本次申请商品的列宽的调整
	$.each(noThisList,function(i,n){
		var index = n["index"];
		//遍历所有行,移除对应index的td	
		$.each(trList,function(trIndex,tr){
			var td = $(tr).children().eq(index);
			$(td).find(".remarks").hide();
		})
	})
}
