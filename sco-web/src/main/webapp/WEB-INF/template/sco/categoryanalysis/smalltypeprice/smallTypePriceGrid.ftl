<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/categoryanalysis/smalltypeprice/smallTypePrice.js" />
	    <#include "sco/common/masterDataType.js" />
    </script>
    <script src="js/echarts/echarts.js"></script>
    
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body style="margin: 0px;">
	<#-- 保存文件对话框 -->
	<div class="easyui-dialog" id="smSaveFileDlg" style="width:270px;height:160px" 
		data-options="
			title:'保存分析结果',
			closable:false,closed:true,modal:true,buttons:[
					{text:'确定',id:'saveFile',iconCls:'save',handler:function(){smCatPriceFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){smCatPriceFn.closeSaveFileDlg()}}
			]
	">
		<br/><br/>
		<span class="txtCenter">文件名：<font color="red">*</font><input id="smFileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/></span>		
	</div>
	
	<div id="smCatPrice1_toolbar" style="padding:1px;overflow:auto;">
		<form id="smCatPrice_search">
			<table style="width:1042px;" border="0">
				<tr>
					<td style="padding-left: 22px;" colspan="3">
						<font color="red">*</font><span style="padding-left: 44px;">中分类：<span>
						<input class="filterSelect" id="centreTypeCode" filterName="centreTypeCode" style="width: 140px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " />
					    &nbsp;&nbsp;
						<span style="padding-left: 35px;"><font color="red">*</font>小分类：</span>
						<input class="filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 140px;"
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false" 
						/>
					    &nbsp;&nbsp;
						<font color="red">*</font>地区：
						<input class="easyui-combobox filterSelect" filterName="regionCode" id="regionCode" style="width: 139px;"
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								url:'masterDataType_listRegionWithoutAll_5.html',
								editable:false" />
					</td>
				</tr>
				<tr>
					<td>
						<span style="padding-left: 34px;">商品定性角色：</span>
						<input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width: 140px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'merchandiseRole_listQualitative_5.html'
					    " />
						<span style="padding-left: 16px;">商品定量角色：</span>
						<input class="easyui-combobox filterSelect" filterName="dlRoleCode" id="dlRoleCode" style="width: 140px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'merchandiseRole_listQuantify_5.html'
					    " />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<font color="red" style="padding-left: 21px;">*</font>
						<span style="padding-left: 21px;">时间范围1：</span>
						<select style="width:61px" id="startYear1" filterName="startYear1" onchange="smCatPriceFn.selectStartYear(this,'endYear1','1')">
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:60px" id="startMonth1" filterName="startMonth1" onchange="smCatPriceFn.selectStartMon('startYear1','startMonth1','endYear1','endMonth1')">
							<#list 1..12 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						月 
						<span style="padding: 33px;">~</span>
						<select style="width:60px" id="endYear1" filterName="endYear1" onchange="smCatPriceFn.selectEndYear(this,'startYear1','1')">
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:61px" id="endMonth1" filterName="endMonth1" onchange="smCatPriceFn.selectEndMon('startYear1','startMonth1','endYear1','endMonth1')">
							<#list 1..12 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						月 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						（时间跨度最长12 个月）
					</td>
				</tr>
				<tr>
					<td colspan="3" style="padding-left: 29px;">
						<span style="padding-left: 23px;">时间范围2：</span>
						<select style="width:61px" id="startYear2" filterName="startYear2" onchange="smCatPriceFn.selectStartYear(this,'endYear2','2')">
							<option></option>
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:60px" id="startMonth2" filterName="startMonth2" onchange="smCatPriceFn.selectStartMon('startYear2','startMonth2','endYear2','endMonth2')">
							<option></option>
							<#list 1..12 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						月 
						<span style="padding: 33px;">~</span>
						<select style="width:60px" id="endYear2" filterName="endYear2" onchange="smCatPriceFn.selectEndYear(this,'startYear2','2')">
							<option></option>
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:61px" id="endMonth2" filterName="endMonth2" onchange="smCatPriceFn.selectEndMon('startYear2','startMonth2','endYear2','endMonth2')">
							<option></option>
							<#list 1..12 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						月 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						（时间跨度最长12 个月）
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font color="red">*</font>&nbsp;<input type="radio" id="netWeightType" filterName="netWeightType" name="netWeightType" checked="checked" value="1" />只看非定量装商品</label>
						<span style="padding-left: 40px;"></span>
						<label><input type="radio" name="netWeightType" value="2"/>只看定量装商品</label>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font><label>
						<input type="radio" id="direct" name="dls" checked="checked" />只看直营门店数据</label>
						<span style="padding-left: 40px;"></span>
						<label><input type="radio" id="join" name="dls" />只看加盟门店数据</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label><input type="radio" id="allJoin" name="dls" />直营与加盟门店数据均看</label>
					</td>
				</tr>
				</tr>
					<td colspan="2">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">*</font>
						<label><input type="radio" name="sp" onclick="smCatPriceFn.displayPriceRegion(false)" checked="checked" />系统自动计算价格带</label>
						<span style="padding-left: 28px;"></span>
						<label><input type="radio" id="handSetPri" name="sp" onclick="smCatPriceFn.displayPriceRegion(true)" />手动设置价格带</label>
					</td>
				</tr>
				<tr id="title" style="display: none;">
					<td colspan="2">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">注："价格大于等于"与"价格小于"的价差必须为步长的倍数</font>
					</td>
				</tr>
				<tr name="priceRegion0" style="display: none;">
					<td colspan="2">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">*</font>价格大于等于：
						<input class="easyui-numberbox" data-options="min:0,precision:2" maxlength="12" id="minPrice0" name="minPrice0" style="width:80px;" onchange="smCatPriceFn.floorPrice('0')" onblur="smCatPriceFn.floorPriceBlur('0')" />
						元&nbsp;&nbsp;价格小于：
						<input class="easyui-numberbox" data-options="min:1,precision:2" maxlength="12" id="maxPrice0" name="maxPrice0" style="width:80px;" onchange="smCatPriceFn.limtPrice('0')" onblur="smCatPriceFn.limtPriceBlur('0')" />
						元&nbsp;&nbsp;价格步长：
						<input class="easyui-numberbox" data-options="min:1,precision:2" maxlength="12" id="addPrice0" name="addPrice0" style="width:80px;" onchange="smCatPriceFn.addPrice('0')" onblur="smCatPriceFn.addPriceBlur('0')" />
						元&nbsp;&nbsp;&nbsp;<input type="button" value="+" onclick="smCatPriceFn.clonePriceRegion(this)" style="width: 28px;" />
					</td>
				</tr>
				<tr>
					<td colspan="2" >
						<span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font color="red">*</font>
							<input type="radio" id="" name="dmc" onclick="smCatPriceFn.displayDmc(false)" checked="checked" />明细报表中显示所有符合查询条件的商品</label>
							&nbsp;&nbsp;&nbsp;&nbsp;<label><input type="radio" id="filterMer" name="dmc" onclick="smCatPriceFn.displayDmc(true)" />明细报表中只显示指定名称/编号的商品</label>
					 	</span>
					 	<span id="dmcc" style="visibility: hidden;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品名称：
							<input id="merchandiseName" filterName="merchandiseName" style="width:120px;" />
							&nbsp;&nbsp;
							商品编号：&nbsp;<input id="merchandiseCode" filterName="merchandiseCode" style="width:120px;" />
						</span>  
					</td>
				</tr>
				<tr>
					<td colspan="2" >
						&nbsp;&nbsp;
						<a onclick="smCatPriceFn.searchAllCategory('1');"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.smallTypePrice.viewAllCategory")>none</#if>;">
							查询
						</a>
						<a onclick="smCatPriceFn.searchDetCategory('2');"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.smallTypePrice.viewDetCategory")>none</#if>;">
							查看明细报表
						</a>
						<a onclick="smCatPriceFn.clearSearch();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<a onclick="smCatPriceFn.exportToExcel();"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.smallTypePrice.exportAllCategory")>none</#if>;">
							导出Excel
						</a>
						<a onclick="smCatPriceFn.saveFile();"plain="true" class="easyui-linkbutton" data-options="iconCls:'save'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.smallTypePrice.saveAllCategory")>none</#if>;">
							保存
						</a>
						<input type="hidden" id="showPanel"><#-- 查看汇总还是明细 -->
					</td>
				</tr>
			</table>
		</form>
		<div style="display:none" id="con1">
			<table style="padding:2px;white-space:nowrap;border-top:1px solid #222;margin-top:5px;width: 100%;">
				<tr name="priceCal1">
					<td style="padding-left: 15px;">
						占SKU数量最多价格带为：<span id="totalSkuUnit1"></span>
						&nbsp;&nbsp;&nbsp;&nbsp;	
						&nbsp;&nbsp;&nbsp;						
						销量占比最大价格带为：<span id="totalSoldUnit1"></span>
						&nbsp;&nbsp;&nbsp;&nbsp;	
						&nbsp;&nbsp;&nbsp;
						销售额占比最大价格带为：<span id="totalSoldPri1"></span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						毛利额占比最大价格带为：<span id="totalGrossfit1"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						时间范围1：<span name="t1"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">				
						小分类名称：<span name="smName"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						地区：<span name="regionCon"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						<span name="netWeightTypeCon"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						<span name="directCon"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						<span name="priceSetCon"></span>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div style="height:550px;">
	    <table id="smCatPrice1"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "false"
			pagePosition = 'bottom'
			pageSize = "30"
			pageList = "[ 20, 30, 40, 50 ]"
			toolbar="#smCatPrice1_toolbar"
		    data-options="rownumbers:true"
		>  
	        <thead>
	        	<tr>
					<th rowspan="2" data-options="field:'NULL',width:75,checkbox:true,hidden:true">
	                </th>
					<th rowspan="2" data-options="field:'regionName',width:130">
	                	地区
	                </th>
					<th rowspan="2" data-options="field:'regionStoreSum',align:'right',width:120,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	地区总门店数
	                </th>
					<th rowspan="2" data-options="field:'regionSKUSum',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	地区SKU总数
	                </th>
	                <#-- 明细界面开始 -->
	                <th rowspan="2" data-options="field:'SKUU',align:'right',width:100,hidden:'true',formatter:smCatPriceFn.formatSKUU">
	                	门店数
	                </th>
	                <th rowspan="2" data-options="field:'merchandiseCode',width:130,hidden:'true'">
	                	商品编号
	                </th>
	    			<th rowspan="2" data-options="field:'merchandiseName',width:200,hidden:'true'">
	                	商品名称
	                </th>
	                <th rowspan="2" data-options="field:'supplierCode',width:130,hidden:'true'">
	                	供应商编号
	                </th>
	                <th rowspan="2" data-options="field:'supplierName',width:200,hidden:'true'">
	                	供应商名称
	                </th>
	                <th rowspan="2" data-options="field:'dxRoleName',width:180,hidden:'true'">
	                	商品定性角色
	                </th>
	                <th rowspan="2" data-options="field:'dlRoleName',width:180,hidden:'true'">
	                	商品定量角色
	                </th>
	                <th rowspan="2" data-options="field:'centreTypeName',width:180,hidden:'true'">
	                	中分类
	                </th>
	                <th rowspan="2" data-options="field:'smallTypeName',width:180,hidden:'true'">
	                	小分类
	                </th>
	                <th rowspan="2" data-options="field:'detailTypeName',width:180,hidden:'true'">
	                	明细类
	                </th>
	                <th rowspan="2" data-options="field:'regionPP',align:'right',width:130,hidden:'true',formatter:smCatPriceFn.formatRegionPP">
	                	所属价格带
	                </th>
	                <th rowspan="2" data-options="field:'powerCount',align:'right',width:130,hidden:'true',formatter:moneyFormatter">
	                	权限数
	                </th>
	                <th rowspan="2" data-options="field:'powerPercent',align:'right',width:130,hidden:'true',formatter:subStrLength">
	                	权限开通率
	                </th>
	                <th rowspan="2" data-options="field:'aCount',align:'right',width:130,hidden:'true',formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	A
	                </th>
	                <th rowspan="2" data-options="field:'bCount',align:'right',width:130,hidden:'true',formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	B
	                </th>
	                <th rowspan="2" data-options="field:'cCount',align:'right',width:130,hidden:'true',formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	C
	                </th>
	                <th rowspan="2" data-options="field:'dCount',align:'right',width:130,hidden:'true',formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	D
	                </th>
	                <th rowspan="2" data-options="field:'actSoldStorCount',align:'right',width:130,hidden:'true',formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	实际销售门店数
	                </th>
	                <th rowspan="2" data-options="field:'actSoldStorPercent',align:'right',width:130,hidden:'true',formatter:smCatPriceFn.formatBigPercent">
	                	实际占权限门店比
	                </th>
	                <#-- 明细界面 结束-->
	                
					<th rowspan="2" data-options="field:'regionPriceStr',align:'right',width:120">
	                	地区价格带（元）
	                </th>
					<th rowspan="2" data-options="field:'skuUnits',align:'right',width:120,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	SKU数量
	                </th>
					<th rowspan="2" data-options="field:'skuPercent',align:'right',width:120,formatter:smCatPriceFn.formatPercent">
	                	SKU数量占比
	                </th>
					<th rowspan="2" data-options="field:'soldUnits',align:'right',width:120,formatter:moneyFormatter">
	                	销量（KG）
	                </th>
					<th rowspan="2" data-options="field:'soldPrice',align:'right',width:120,formatter:moneyFormatter">
	                	销售金额（元）
	                </th>
					<th rowspan="2" data-options="field:'grossProfit',align:'right',width:120,formatter:moneyFormatter">
	                	毛利额（元）
	                </th>
					<th rowspan="2" data-options="field:'soldPercent',align:'right',width:120,formatter:smCatPriceFn.formatPercent">
	                	销量占比
	                </th>
					<th rowspan="2" data-options="field:'soldPricePercent',align:'right',width:100,formatter:smCatPriceFn.formatPercent">
	                	销售金额占比
	                </th>
					<th rowspan="2" data-options="field:'grossProfitPenrcent',align:'right',width:100,formatter:smCatPriceFn.formatPercent">
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
					<th rowspan="2" data-options="field:'vitality',align:'right',width:120,formatter:smCatPriceFn.formatBigPercent">
	                	活跃度
	                </th>
					<th rowspan="2" data-options="field:'powerShopDayPercent',align:'right',width:120,formatter:smCatPriceFn.formatPercent">
	                	权限店天占比
	                </th>
					<th rowspan="2" data-options="field:'soldShopPercent',align:'right',width:100,formatter:smCatPriceFn.formatPercent">
	                	销售店天占比
	                </th>
					<th rowspan="2" data-options="field:'psdUnits',align:'right',width:100,formatter:moneyFormatter">
	                	PSD销量（KG）
	                </th>
					<th rowspan="2" data-options="field:'psdSoldPrice',align:'right',width:110,formatter:moneyFormatter">
	                	PSD销售金额（元）
	                </th>
	                <th rowspan="2" data-options="field:'psdGrossProfit',align:'right',width:100,formatter:moneyFormatter">
	                	PSD毛利额（元）
	                </th>
	                <#-- 明细界面 开始 -->
	                <th rowspan="2" data-options="field:'notLessS',align:'right',width:270,hidden:'true',formatter:smCatPriceFn.formatNotLessS">
	                	高于用户所查小分类的所有商品的平均PSD销量
	                </th>
	                <th rowspan="2" data-options="field:'notLessP',align:'right',width:270,hidden:'true',formatter:smCatPriceFn.formatNotLessP">
	                	高于用户所查小分类的所有商品的平均PSD销售额
	                </th>
	                <th rowspan="2" data-options="field:'notLessG',align:'right',width:270,hidden:'true',formatter:smCatPriceFn.formatNotLessG">
	                	高于用户所查小分类的所有商品的平均PSD毛利额
	                </th>
	                <th rowspan="2" data-options="field:'bucketPercent',align:'right',width:170,hidden:'true',formatter:smCatPriceFn.formatPercent">
	                	格斗占比
	                </th>
	                <th rowspan="2" data-options="field:'volumePercent',align:'right',width:170,hidden:'true',formatter:smCatPriceFn.formatPercent">
	                	volume&space
	                </th>
	                <th rowspan="2" data-options="field:'valPercent',align:'right',width:170,hidden:'true',formatter:smCatPriceFn.formatPercent">
	                	value&space
	                </th>
					<#-- 明细界面 结束 -->
					<th rowspan="2" data-options="field:'notLessPsdUnitsSku',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD销量的SKU数
	                </th>
					<th rowspan="2" data-options="field:'lessPsdUnitsSku',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD销量的SKU数
	                </th>
					<th rowspan="2" data-options="field:'notLessPsdSoldPrice',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD销售额的SKU数
	                </th>
	                <th rowspan="2" data-options="field:'lessPsdSoldPrice',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD销售额的SKU数
	                </th>
	                <th rowspan="2" data-options="field:'notLessPsdGrossProfit',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD毛利额的SKU数
	                </th>
	                <th rowspan="2" data-options="field:'lessPsdGrossProfit',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD毛利额的SKU数
	                </th>
	            </tr>
	        </thead>
	    </table>
    </div>
   	<#-- 统计表 -->
  	<div id="smChart1" style="display:none;overflow:auto;width:100%;">
		<div id="smBar1" style="height:400px;width:200%;"></div>
	</div>
    
    <#-- 第二个Grid -->
    <div id="smCatPrice2_toolbar" style="padding:2px;overflow:auto;">
		<div style="display:none" id="con2">
			<table style="padding:2px;white-space:nowrap;margin-top:5px;width: 100%;">
				<tr name="priceCal2">
					<td style="padding-left: 15px;">
						占SKU数量最多价格带为：<span id="totalSkuUnit2"></span>	
						&nbsp;&nbsp;&nbsp;&nbsp;	
						&nbsp;&nbsp;&nbsp;					
						销量占比最大价格带为：<span id="totalSoldUnit2"></span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						销售额占比最大价格带为：<span id="totalSoldPri2"></span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						毛利额占比最大价格带为：<span id="totalGrossfit2"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						时间范围2：<span name="t2"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">				
						小分类名称：<span name="smName"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						地区：<span name="regionCon"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						<span name="netWeightTypeCon"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						<span name="directCon"></span>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 15px;">
						<span name="priceSetCon"></span>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div style="height:350px;">
	    <table  id="smCatPrice2"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "false"
			pagePosition = 'bottom'
			pageSize = "30"
			pageList = "[ 20, 30, 40, 50 ]"
			toolbar="#smCatPrice2_toolbar"
		    data-options="rownumbers:true"
		>  
	        <thead>
	        	<tr>
					<th rowspan="2" data-options="field:'NULL',width:75,checkbox:true,hidden:true">
	                </th>
					<th rowspan="2" data-options="field:'regionName',width:130">
	                	地区
	                </th>
					<th rowspan="2" data-options="field:'regionStoreSum',align:'right',width:120,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	地区总门店数
	                </th>
					<th rowspan="2" data-options="field:'regionSKUSum',align:'right',width:100,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	地区SKU总数
	                </th>
	                <#-- 明细界面开始 -->
	                <th rowspan="2" data-options="field:'SKUU',align:'right',width:100,hidden:'true',formatter:smCatPriceFn.formatSKUU">
	                	门店数
	                </th>
	                <th rowspan="2" data-options="field:'merchandiseCode',width:130,hidden:'true'">
	                	商品编号
	                </th>
	    			<th rowspan="2" data-options="field:'merchandiseName',width:200,hidden:'true'">
	                	商品名称
	                </th>
	                <th rowspan="2" data-options="field:'supplierCode',width:130,hidden:'true'">
	                	供应商编号
	                </th>
	                <th rowspan="2" data-options="field:'supplierName',width:200,hidden:'true'">
	                	供应商名称
	                </th>
	                <th rowspan="2" data-options="field:'dxRoleName',width:180,hidden:'true'">
	                	商品定性角色
	                </th>
	                <th rowspan="2" data-options="field:'dlRoleName',width:180,hidden:'true'">
	                	商品定量角色
	                </th>
	                <th rowspan="2" data-options="field:'centreTypeName',width:180,hidden:'true'">
	                	中分类
	                </th>
	                <th rowspan="2" data-options="field:'smallTypeName',width:180,hidden:'true'">
	                	小分类
	                </th>
	                <th rowspan="2" data-options="field:'detailTypeName',width:180,hidden:'true'">
	                	明细类
	                </th>
	                <th rowspan="2" data-options="field:'regionPP',align:'right',width:130,hidden:'true',formatter:smCatPriceFn.formatRegionPP">
	                	所属价格带
	                </th>
	                <th rowspan="2" data-options="field:'powerCount',align:'right',width:130,hidden:'true',formatter:moneyFormatter">
	                	权限数
	                </th>
	                <th rowspan="2" data-options="field:'powerPercent',align:'right',width:130,hidden:'true',formatter:subStrLength">
	                	权限开通率
	                </th>
	                <th rowspan="2" data-options="field:'aCount',align:'right',width:130,hidden:'true',formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	A
	                </th>
	                <th rowspan="2" data-options="field:'bCount',align:'right',width:130,hidden:'true',formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	B
	                </th>
	                <th rowspan="2" data-options="field:'cCount',align:'right',width:130,hidden:'true',formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	C
	                </th>
	                <th rowspan="2" data-options="field:'dCount',align:'right',width:130,hidden:'true',formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	D
	                </th>
	                <th rowspan="2" data-options="field:'actSoldStorCount',align:'right',width:130,hidden:'true',formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	实际销售门店数
	                </th>
	                <th rowspan="2" data-options="field:'actSoldStorPercent',align:'right',width:130,hidden:'true',formatter:smCatPriceFn.formatBigPercent">
	                	实际占权限门店比
	                </th>
	                <#-- 明细界面 结束-->
	                
					<th rowspan="2" data-options="field:'regionPriceStr',align:'right',width:120">
	                	地区价格带（元）
	                </th>
					<th rowspan="2" data-options="field:'skuUnits',align:'right',width:120,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	SKU数量
	                </th>
					<th rowspan="2" data-options="field:'skuPercent',align:'right',width:120,formatter:smCatPriceFn.formatPercent">
	                	SKU数量占比
	                </th>
					<th rowspan="2" data-options="field:'soldUnits',align:'right',width:120,formatter:moneyFormatter">
	                	销量（KG）
	                </th>
					<th rowspan="2" data-options="field:'soldPrice',align:'right',width:120,formatter:moneyFormatter">
	                	销售金额（元）
	                </th>
					<th rowspan="2" data-options="field:'grossProfit',align:'right',width:120,formatter:moneyFormatter">
	                	毛利额（元）
	                </th>
					<th rowspan="2" data-options="field:'soldPercent',align:'right',width:120,formatter:smCatPriceFn.formatPercent">
	                	销量占比
	                </th>
					<th rowspan="2" data-options="field:'soldPricePercent',align:'right',width:100,formatter:smCatPriceFn.formatPercent">
	                	销售金额占比
	                </th>
					<th rowspan="2" data-options="field:'grossProfitPenrcent',align:'right',width:100,formatter:smCatPriceFn.formatPercent">
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
					<th rowspan="2" data-options="field:'vitality',align:'right',width:120,formatter:smCatPriceFn.formatBigPercent">
	                	活跃度
	                </th>
					<th rowspan="2" data-options="field:'powerShopDayPercent',align:'right',width:120,formatter:smCatPriceFn.formatPercent">
	                	权限店天占比
	                </th>
					<th rowspan="2" data-options="field:'soldShopPercent',align:'right',width:100,formatter:smCatPriceFn.formatPercent">
	                	销售店天占比
	                </th>
					<th rowspan="2" data-options="field:'psdUnits',align:'right',width:100,formatter:moneyFormatter">
	                	PSD销量（KG）
	                </th>
					<th rowspan="2" data-options="field:'psdSoldPrice',align:'right',width:110,formatter:moneyFormatter">
	                	PSD销售金额（元）
	                </th>
	                <th rowspan="2" data-options="field:'psdGrossProfit',align:'right',width:100,formatter:moneyFormatter">
	                	PSD毛利额（元）
	                </th>
	                <#-- 明细界面 开始 -->
	                <th rowspan="2" data-options="field:'notLessS',align:'right',width:270,hidden:'true',formatter:smCatPriceFn.formatNotLessS">
	                	高于用户所查小分类的所有商品的平均PSD销量
	                </th>
	                <th rowspan="2" data-options="field:'notLessP',align:'right',width:270,hidden:'true',formatter:smCatPriceFn.formatNotLessP">
	                	高于用户所查小分类的所有商品的平均PSD销售额
	                </th>
	                <th rowspan="2" data-options="field:'notLessG',align:'right',width:270,hidden:'true',formatter:smCatPriceFn.formatNotLessG">
	                	高于用户所查小分类的所有商品的平均PSD毛利额
	                </th>
	                <th rowspan="2" data-options="field:'bucketPercent',align:'right',width:170,hidden:'true',formatter:smCatPriceFn.formatPercent">
	                	格斗占比
	                </th>
	                <th rowspan="2" data-options="field:'volumePercent',align:'right',width:170,hidden:'true',formatter:smCatPriceFn.formatPercent">
	                	volume&space
	                </th>
	                <th rowspan="2" data-options="field:'valPercent',align:'right',width:170,hidden:'true',formatter:smCatPriceFn.formatPercent">
	                	value&space
	                </th>
					<#-- 明细界面 结束 -->
					<th rowspan="2" data-options="field:'notLessPsdUnitsSku',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD销量的SKU数
	                </th>
					<th rowspan="2" data-options="field:'lessPsdUnitsSku',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD销量的SKU数
	                </th>
					<th rowspan="2" data-options="field:'notLessPsdSoldPrice',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD销售额的SKU数
	                </th>
	                <th rowspan="2" data-options="field:'lessPsdSoldPrice',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD销售额的SKU数
	                </th>
	                <th rowspan="2" data-options="field:'notLessPsdGrossProfit',align:'right',width:170,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	大于等于PSD毛利额的SKU数
	                </th>
	                <th rowspan="2" data-options="field:'lessPsdGrossProfit',align:'right',width:150,formatter:function(val){
						return moneyFormatter(val, 0);
					}">
	                	小于PSD毛利额的SKU数
	                </th>
	            </tr>
	        </thead>
	    </table>
    </div>
    <#-- 统计表 -->
  	<div id="smChart2" style="display:none;overflow:auto;width:100%;">
		<div id="smBar2" style="height:400px;width:200%;"></div>
	</div>
	
</body>
</html>
</#compress>