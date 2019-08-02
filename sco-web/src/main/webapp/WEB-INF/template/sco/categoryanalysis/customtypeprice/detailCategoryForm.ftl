<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
    	<#include "sco/categoryanalysis/customtypeprice/detailCategory.js" />
    </script>
    <script src="js/echarts/echarts.js"></script>
    
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body>
	<#-- 保存文件对话框 -->
	<div class="easyui-dialog" id="cusDetFileDlg" style="width:270px;height:160px" 
		data-options="
			title:'保存分析结果',
			closable:false,closed:true,modal:true,buttons:[
					{text:'确定',id:'saveFile',iconCls:'save',handler:function(){cusDetFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){cusDetFn.closeSaveFileDlg()}}
			]
	">
		<br/><br/>
		<span class="txtCenter">文件名：<font color="red">*</font><input id="cusDetFileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/></span>		
	</div>
	
	<input type="hidden" id="allList" value="${allList}" />
	<div id="cuDetPrice1_toolbar" style="padding:1px;overflow:auto;height:140px">
		<form id="cusDet_form" method="post" enctype="multipart/form-data">
			<table style="width:1042px;" border="0">
				<tr>
					<td colspan="6">
						&nbsp;&nbsp;
						<a onclick="cusDetFn.exportToExcel();"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.customCategoryPrice.exportCategory")>none</#if>;">
							导出Excel
						</a>
						<a onclick="cusDetFn.saveFile();"plain="true" class="easyui-linkbutton" data-options="iconCls:'save'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.customCategoryPrice.saveCategory")>none</#if>;">
							保存
						</a>
						<a onclick="cusDetFn.closePage();"plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">
							关闭
						</a>
					</td>
				</tr>
			</table>
		</from>
		<div id="con1">
			<hr/>
			<table style="padding:2px;">
				<tr>
					<td style="padding-left: 15px;">
						时间范围1：${paraMap.pubMap.searchDate1}
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
	    <table id="cusDetPrice1"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "30"
			pageList = "[ 20, 30, 40, 50 ]"
			toolbar="#cuDetPrice1_toolbar"
		    data-options="rownumbers:true"
		>  
        	<thead>
	        	<tr>
					<th rowspan="2" data-options="field:'regionName',width:100">
	                	地区
	                </th>
					<th rowspan="2" data-options="field:'regionStoreSum',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	门店数
	                </th>
					<th rowspan="2" data-options="field:'merchandiseCode',width:100">
	                	商品编号
	                </th>
					<th rowspan="2" data-options="field:'merchandiseName',width:220">
	                	商品名称
	                </th>
					<th rowspan="2" data-options="field:'supplierCode',width:120">
	                	供应商编号
	                </th>
					<th rowspan="2" data-options="field:'supplierName',width:220">
	                	供应商名称
	                </th>
					<th rowspan="2" data-options="field:'dxRoleName',width:120">
	                	商品定性角色
	                </th>
					<th rowspan="2" data-options="field:'dlRoleName',width:120">
	                	商品定量角色
	                </th>
					<th rowspan="2" data-options="field:'centreTypeName',width:120">
	                	中分类
	                </th>
					<th rowspan="2" data-options="field:'smallTypeName',width:180,hidden:'true'">
	                	小分类
	                </th>
	                <th rowspan="2" data-options="field:'detailTypeName',width:180,hidden:'true'">
	                	明细类
	                </th>
	                <th rowspan="2" data-options="field:'regionPrice',align:'right',width:130">
	                	所属价格带
	                </th>
	                <th rowspan="2" data-options="field:'powerCount',align:'right',width:130,formatter:moneyFormatter">
	                	权限数
	                </th>
	                <th rowspan="2" data-options="field:'powerPercent',align:'right',width:130,formatter:subStrLength">
	                	权限开通率
	                </th>
	                <th rowspan="2" data-options="field:'aCount',align:'right',width:130,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	A
	                </th>
	                <th rowspan="2" data-options="field:'bCount',align:'right',width:130,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	B
	                </th>
	                <th rowspan="2" data-options="field:'cCount',align:'right',width:130,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	C
	                </th>
	                <th rowspan="2" data-options="field:'dCount',align:'right',width:130,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	D
	                </th>
	                <th rowspan="2" data-options="field:'actSoldStorCount',align:'right',width:130,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	实际销售门店数
	                </th>
	                <th rowspan="2" data-options="field:'actSoldStorPercent',align:'right',width:130,formatter:cusDetFn.formatBigPercent">
	                	实际占权限门店比
	                </th>
					<th rowspan="2" data-options="field:'soldUnits',align:'right',width:120,formatter:moneyFormatter">
	                	销量
	                </th>
					<th rowspan="2" data-options="field:'soldPrice',align:'right',width:120,formatter:moneyFormatter">
	                	销售金额
	                </th>
					<th rowspan="2" data-options="field:'grossProfit',align:'right',width:120,formatter:moneyFormatter">
	                	毛利额
	                </th>
					<th rowspan="2" data-options="field:'soldPercent',align:'right',width:120,formatter:cusDetFn.formatPercent">
	                	销量占比
	                </th>
					<th rowspan="2" data-options="field:'soldPricePercent',align:'right',width:100,formatter:cusDetFn.formatPercent">
	                	销售金额占比
	                </th>
					<th rowspan="2" data-options="field:'grossProfitPenrcent',align:'right',width:100,formatter:cusDetFn.formatPercent">
	                	毛利额占比
	                </th>
					<th rowspan="2" data-options="field:'powerShopDay',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	权限店天
	                </th>
					<th rowspan="2" data-options="field:'soldShopDay',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	销售店天
	                </th>
					<th rowspan="2" data-options="field:'vitality',align:'right',width:120,formatter:cusDetFn.formatBigPercent">
	                	活跃度
	                </th>
					<th rowspan="2" data-options="field:'powerShopDayPercent',align:'right',width:120,formatter:cusDetFn.formatPercent">
	                	权限店天占比
	                </th>
					<th rowspan="2" data-options="field:'soldShopPercent',align:'right',width:100,formatter:cusDetFn.formatPercent">
	                	销售店天占比
	                </th>
					<th rowspan="2" data-options="field:'psdUnits',align:'right',width:100,formatter:moneyFormatter">
	                	PSD销量
	                </th>
					<th rowspan="2" data-options="field:'psdSoldPrice',align:'right',width:100,formatter:moneyFormatter">
	                	PSD销售金额
	                </th>
	                <th rowspan="2" data-options="field:'psdGrossProfit',align:'right',width:100,formatter:moneyFormatter">
	                	PSD毛利额
	                </th>
	                 <th rowspan="2" data-options="field:'notLessPsdUnitsSku',align:'right',width:170,formatter:moneyFormatter">
	                	高于用户所选商品的平均PSD销量
	                </th>
	                <th rowspan="2" data-options="field:'notLessPsdSoldPrice',align:'right',width:170,formatter:moneyFormatter">
	                	高于用户所选商品的平均PSD销售额
	                </th>
	                <th rowspan="2" data-options="field:'notLessPsdGrossProfit',align:'right',width:170,formatter:moneyFormatter">
	                	高于用户所选商品的平均PSD毛利额
	                </th>
	                <th rowspan="2" data-options="field:'bucketPercent',align:'right',width:170,formatter:cusDetFn.formatPercent">
	                	格斗占比
	                </th>
	                <th rowspan="2" data-options="field:'volumePercent',align:'right',width:170,formatter:cusDetFn.formatPercent">
	                	volume&space
	                </th>
	                <th rowspan="2" data-options="field:'valPercent',align:'right',width:170,formatter:cusDetFn.formatPercent">
	                	value&space
	                </th>
	            </tr>
	        </thead>
	    </table>
    </div>
    
    <#-- 第二个Grid -->
    <div id="cuDetPrice2_toolbar" style="padding:2px;overflow:auto;">
		<div id="con2">
			<table style="padding:2px;">
				<tr>
					<td style="padding-left: 15px;">
						时间范围2：${paraMap.pubMap.searchDate2}
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
	<div id="allGrid2" style="height:430px;">
	    <table id="cusDetPrice2"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "30"
			pageList = "[ 20, 30, 40, 50 ]"
			toolbar="#cuDetPrice2_toolbar"
		    data-options="rownumbers:true"
		>  
	        <thead>
	        	<tr>
					<th rowspan="2" data-options="field:'regionName',width:100">
	                	地区
	                </th>
					<th rowspan="2" data-options="field:'regionStoreSum',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	门店数
	                </th>
					<th rowspan="2" data-options="field:'merchandiseCode',width:100">
	                	商品编号
	                </th>
					<th rowspan="2" data-options="field:'merchandiseName',width:220">
	                	商品名称
	                </th>
					<th rowspan="2" data-options="field:'supplierCode',width:120">
	                	供应商编号
	                </th>
					<th rowspan="2" data-options="field:'supplierName',width:220">
	                	供应商名称
	                </th>
					<th rowspan="2" data-options="field:'dxRoleName',width:120">
	                	商品定性角色
	                </th>
					<th rowspan="2" data-options="field:'dlRoleName',width:120">
	                	商品定量角色
	                </th>
					<th rowspan="2" data-options="field:'centreTypeName',width:120">
	                	中分类
	                </th>
					<th rowspan="2" data-options="field:'smallTypeName',width:180,hidden:'true'">
	                	小分类
	                </th>
	                <th rowspan="2" data-options="field:'detailTypeName',width:180,hidden:'true'">
	                	明细类
	                </th>
	                <th rowspan="2" data-options="field:'regionPrice',align:'right',width:130">
	                	所属价格带
	                </th>
	                <th rowspan="2" data-options="field:'powerCount',align:'right',width:130,formatter:moneyFormatter">
	                	权限数
	                </th>
	                <th rowspan="2" data-options="field:'powerPercent',align:'right',width:130,formatter:subStrLength">
	                	权限开通率
	                </th>
	                <th rowspan="2" data-options="field:'aCount',align:'right',width:130,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	A
	                </th>
	                <th rowspan="2" data-options="field:'bCount',align:'right',width:130,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	B
	                </th>
	                <th rowspan="2" data-options="field:'cCount',align:'right',width:130,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	C
	                </th>
	                <th rowspan="2" data-options="field:'dCount',align:'right',width:130,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	D
	                </th>
	                <th rowspan="2" data-options="field:'actSoldStorCount',align:'right',width:130,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	实际销售门店数
	                </th>
	                <th rowspan="2" data-options="field:'actSoldStorPercent',align:'right',width:130,formatter:cusDetFn.formatBigPercent">
	                	实际占权限门店比
	                </th>
					<th rowspan="2" data-options="field:'soldUnits',align:'right',width:120,formatter:moneyFormatter">
	                	销量
	                </th>
					<th rowspan="2" data-options="field:'soldPrice',align:'right',width:120,formatter:moneyFormatter">
	                	销售金额
	                </th>
					<th rowspan="2" data-options="field:'grossProfit',align:'right',width:120,formatter:moneyFormatter">
	                	毛利额
	                </th>
					<th rowspan="2" data-options="field:'soldPercent',align:'right',width:120,formatter:cusDetFn.formatPercent">
	                	销量占比
	                </th>
					<th rowspan="2" data-options="field:'soldPricePercent',align:'right',width:100,formatter:cusDetFn.formatPercent">
	                	销售金额占比
	                </th>
					<th rowspan="2" data-options="field:'grossProfitPenrcent',align:'right',width:100,formatter:cusDetFn.formatPercent">
	                	毛利额占比
	                </th>
					<th rowspan="2" data-options="field:'powerShopDay',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	权限店天
	                </th>
					<th rowspan="2" data-options="field:'soldShopDay',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	销售店天
	                </th>
					<th rowspan="2" data-options="field:'vitality',align:'right',width:120,formatter:cusDetFn.formatBigPercent">
	                	活跃度
	                </th>
					<th rowspan="2" data-options="field:'powerShopDayPercent',align:'right',width:120,formatter:cusDetFn.formatPercent">
	                	权限店天占比
	                </th>
					<th rowspan="2" data-options="field:'soldShopPercent',align:'right',width:100,formatter:cusDetFn.formatPercent">
	                	销售店天占比
	                </th>
					<th rowspan="2" data-options="field:'psdUnits',align:'right',width:100,formatter:moneyFormatter">
	                	PSD销量
	                </th>
					<th rowspan="2" data-options="field:'psdSoldPrice',align:'right',width:100,formatter:moneyFormatter">
	                	PSD销售金额
	                </th>
	                <th rowspan="2" data-options="field:'psdGrossProfit',align:'right',width:100,formatter:moneyFormatter">
	                	PSD毛利额
	                </th>
	                 <th rowspan="2" data-options="field:'notLessPsdUnitsSku',align:'right',width:170,formatter:moneyFormatter">
	                	高于用户所选商品的平均PSD销量
	                </th>
	                <th rowspan="2" data-options="field:'notLessPsdSoldPrice',align:'right',width:170,formatter:moneyFormatter">
	                	高于用户所选商品的平均PSD销售额
	                </th>
	                <th rowspan="2" data-options="field:'notLessPsdGrossProfit',align:'right',width:170,formatter:moneyFormatter">
	                	高于用户所选商品的平均PSD毛利额
	                </th>
	                <th rowspan="2" data-options="field:'bucketPercent',align:'right',width:170,formatter:cusDetFn.formatPercent">
	                	格斗占比
	                </th>
	                <th rowspan="2" data-options="field:'volumePercent',align:'right',width:170,formatter:cusDetFn.formatPercent">
	                	volume&space
	                </th>
	                <th rowspan="2" data-options="field:'valPercent',align:'right',width:170,formatter:cusDetFn.formatPercent">
	                	value&space
	                </th>
	            </tr>
	        </thead>
	    </table>
    </div>
	
</body>
</html>
</#compress>