<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
    	<#include "sco/categoryanalysis/customtypeprice/allCategory.js" />
    </script>
    <script src="js/echarts/echarts.js"></script>
    
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body>
	<#-- 保存文件对话框 -->
	<div class="easyui-dialog" id="cusAllFileDlg" style="width:270px;height:160px" 
		data-options="
			title:'保存分析结果',
			closable:false,closed:true,modal:true,buttons:[
					{text:'确定',id:'saveFile',iconCls:'save',handler:function(){cusAllFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){cusAllFn.closeSaveFileDlg()}}
			]
	">
		<br/><br/>
		<span class="txtCenter">文件名：<font color="red">*</font><input id="cusAllFileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/></span>		
	</div>
	
	<input type="hidden" id="allList" value="${allList}" />
	<div id="cuAllPrice1_toolbar" style="padding:1px;overflow:auto;height:174px">
		<form id="cusAll_form" method="post">
			<table style="width:1042px;" border="0">
				<tr>
					<td colspan="6">
						&nbsp;&nbsp;
						<a onclick="cusAllFn.exportToExcel();"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.customCategoryPrice.exportCategory")>none</#if>;">
							导出Excel
						</a>
						<a onclick="cusAllFn.saveFile();"plain="true" class="easyui-linkbutton" data-options="iconCls:'save'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.customCategoryPrice.saveCategory")>none</#if>;">
							保存
						</a>
						<a onclick="cusAllFn.closePage();"plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
							关闭
						</a>
					</td>
				</tr>
			</table>
		</from>
		<div id="con1">
			<table style="padding:2px;white-space:nowrap;border-top:1px solid #222;margin-top:5px;width:100%">
				<tr name="priceCal1">
					<td style="padding-left: 15px;padding-top:10px;">
						占SKU数量最多价格带为：${paraMap.pubMap.part10}
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						销量占比最大价格带为：${paraMap.pubMap.part11}</span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						销售额占比最大价格带为：${paraMap.pubMap.part12}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
						毛利额占比最大价格带为：${paraMap.pubMap.part13}
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						时间范围1：${paraMap.pubMap.searchDate1}
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">				
						${paraMap.pubMap.merTitle}
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						地区：${paraMap.pubMap.regionName}
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						${paraMap.pubMap.directCon}
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						${paraMap.pubMap.priceSetCon}
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div style="height:480px;">
	    <table id="cusAllPrice1"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "false"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#cuAllPrice1_toolbar"
		    data-options="rownumbers:true"
		>  
	        <thead>
	        	<tr>
					<th data-options="field:'regionName',width:130">
	                	地区
	                </th>
					<th data-options="field:'regionStoreSum',align:'right',width:120,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	地区总门店数
	                </th>
					<th data-options="field:'regionSKUSum',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	地区SKU总数
	                </th>
					<th data-options="field:'regionPriceStr',align:'right',width:120">
	                	地区价格带（元）
	                </th>
					<th data-options="field:'skuUnits',align:'right',width:120,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	SKU数量
	                </th>
					<th data-options="field:'skuPercent',align:'right',width:120,formatter:cusAllFn.formatPercent">
	                	SKU数量占比
	                </th>
					<th data-options="field:'soldUnits',align:'right',width:120,formatter:moneyFormatter">
	                	销量
	                </th>
					<th data-options="field:'soldPrice',align:'right',width:120,formatter:moneyFormatter">
	                	销售金额（元）
	                </th>
					<th data-options="field:'grossProfit',align:'right',width:120,formatter:moneyFormatter">
	                	毛利额（元）
	                </th>
					<th data-options="field:'soldPercent',align:'right',width:120,formatter:cusAllFn.formatPercent">
	                	销量占比
	                </th>
					<th data-options="field:'soldPricePercent',align:'right',width:100,formatter:cusAllFn.formatPercent">
	                	销售金额占比
	                </th>
					<th data-options="field:'grossProfitPenrcent',align:'right',width:100,formatter:cusAllFn.formatPercent">
	                	毛利额占比
	                </th>
					<th data-options="field:'powerShopDay',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	权限店天
	                </th>
					<th data-options="field:'soldShopDay',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	销售店天
	                </th>
					<th data-options="field:'vitality',align:'right',width:120,formatter:cusAllFn.formatBigPercent">
	                	活跃度
	                </th>
					<th data-options="field:'powerShopDayPercent',align:'right',width:120,formatter:cusAllFn.formatPercent">
	                	权限店天占比
	                </th>
					<th data-options="field:'soldShopPercent',align:'right',width:100,formatter:cusAllFn.formatPercent">
	                	销售店天占比
	                </th>
					<th data-options="field:'psdUnits',align:'right',width:100,formatter:moneyFormatter">
	                	PSD销量（KG）
	                </th>
					<th data-options="field:'psdSoldPrice',align:'right',width:110,formatter:moneyFormatter">
	                	PSD销售金额（元）
	                </th>
	                <th data-options="field:'psdGrossProfit',align:'right',width:100,formatter:moneyFormatter">
	                	PSD毛利额（元）
	                </th>
					<th data-options="field:'notLessPsdUnitsSku',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD销量的SKU数
	                </th>
					<th data-options="field:'lessPsdUnitsSku',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD销量的SKU数
	                </th>
					<th data-options="field:'notLessPsdSoldPrice',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD销售额的SKU数
	                </th>
	                <th data-options="field:'lessPsdSoldPrice',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD销售额的SKU数
	                </th>
	                <th data-options="field:'notLessPsdGrossProfit',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD毛利额的SKU数
	                </th>
	                <th data-options="field:'lessPsdGrossProfit',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD毛利额的SKU数
	                </th>
	            </tr>
	        </thead>
	    </table>
    </div>
   	<#-- 统计表 -->
  	<div id="cusAllChart1" style="display:none;overflow:auto;">
		<div id="cusAllBar1" style="height:400px;width:200%;"></div>
	</div>
    
    <#-- 第二个Grid -->
    <div id="cuAllPrice2_toolbar" style="padding:2px;overflow:auto;">
		<div id="con2">
			<table style="padding:2px;white-space:nowrap;margin-top:5px">
				<tr name="priceCal2">
					<td style="padding-left: 15px;">
						占SKU数量最多价格带为：${paraMap.pubMap.part20}
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						销量占比最大价格带为：${paraMap.pubMap.part21}
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						销售额占比最大价格带为：${paraMap.pubMap.part22}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
						毛利额占比最大价格带为：${paraMap.pubMap.part23}
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						时间范围2：${paraMap.pubMap.searchDate2}
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">				
						${paraMap.pubMap.merTitle}
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						地区：${paraMap.pubMap.regionName}
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						${paraMap.pubMap.directCon}
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						${paraMap.pubMap.priceSetCon}
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="allGrid2" style="height:420px;">
	    <table id="cusAllPrice2"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "false"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#cuAllPrice2_toolbar"
		    data-options="rownumbers:true"
		>  
	        <thead>
	        	<tr>
					<th data-options="field:'NULL',width:75,checkbox:true,hidden:true">
	                </th>
					<th data-options="field:'regionName',width:130">
	                	地区
	                </th>
					<th data-options="field:'regionStoreSum',align:'right',width:120,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	地区总门店数
	                </th>
					<th data-options="field:'regionSKUSum',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	地区SKU总数
	                </th>
					<th data-options="field:'regionPriceStr',align:'right',width:120">
	                	地区价格带（元）
	                </th>
					<th data-options="field:'skuUnits',align:'right',width:120,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	SKU数量
	                </th>
					<th data-options="field:'skuPercent',align:'right',width:120,formatter:cusAllFn.formatPercent">
	                	SKU数量占比
	                </th>
					<th data-options="field:'soldUnits',align:'right',width:120,formatter:moneyFormatter">
	                	销量
	                </th>
					<th data-options="field:'soldPrice',align:'right',width:120,formatter:moneyFormatter">
	                	销售金额（元）
	                </th>
					<th data-options="field:'grossProfit',align:'right',width:120,formatter:moneyFormatter">
	                	毛利额（元）
	                </th>
					<th data-options="field:'soldPercent',align:'right',width:120,formatter:cusAllFn.formatPercent">
	                	销量占比
	                </th>
					<th data-options="field:'soldPricePercent',align:'right',width:100,formatter:cusAllFn.formatPercent">
	                	销售金额占比
	                </th>
					<th data-options="field:'grossProfitPenrcent',align:'right',width:100,formatter:cusAllFn.formatPercent">
	                	毛利额占比
	                </th>
					<th data-options="field:'powerShopDay',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	权限店天
	                </th>
					<th data-options="field:'soldShopDay',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	销售店天
	                </th>
					<th data-options="field:'vitality',align:'right',width:120,formatter:cusAllFn.formatBigPercent">
	                	活跃度
	                </th>
					<th data-options="field:'powerShopDayPercent',align:'right',width:120,formatter:cusAllFn.formatPercent">
	                	权限店天占比
	                </th>
					<th data-options="field:'soldShopPercent',align:'right',width:100,formatter:cusAllFn.formatPercent">
	                	销售店天占比
	                </th>
					<th data-options="field:'psdUnits',align:'right',width:100,formatter:moneyFormatter">
	                	PSD销量（KG）
	                </th>
					<th data-options="field:'psdSoldPrice',align:'right',width:110,formatter:moneyFormatter">
	                	PSD销售金额（元）
	                </th>
	                <th data-options="field:'psdGrossProfit',align:'right',width:100,formatter:moneyFormatter">
	                	PSD毛利额（元）
	                </th>
					<th data-options="field:'notLessPsdUnitsSku',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD销量的SKU数
	                </th>
					<th data-options="field:'lessPsdUnitsSku',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD销量的SKU数
	                </th>
					<th data-options="field:'notLessPsdSoldPrice',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD销售额的SKU数
	                </th>
	                <th data-options="field:'lessPsdSoldPrice',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD销售额的SKU数
	                </th>
	                <th data-options="field:'notLessPsdGrossProfit',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD毛利额的SKU数
	                </th>
	                <th data-options="field:'lessPsdGrossProfit',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD毛利额的SKU数
	                </th>
	            </tr>
	        </thead>
	    </table>
    </div>
    <#-- 统计表 -->
  	<div id="cusAllChart2" style="display:none;overflow:auto;">
		<div id="cusAllBar2" style="height:400px;width:200%;"></div>
	</div>
	
</body>
</html>
</#compress>