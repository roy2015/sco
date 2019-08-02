<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/categoryanalysis/detailtypeprice/detailTypePrice.js" />
	    <#include "sco/common/masterDataType.js" />
    </script>
    <script src="js/echarts/echarts.js"></script>
    
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body style="margin: 0px;">
	<#-- 保存文件对话框 -->
	<div class="easyui-dialog" id="dtSaveFileDlg" style="width:270px;height:160px" 
		data-options="
			title:'保存分析结果',
			closable:false,closed:true,modal:true,buttons:[
					{text:'确定',id:'saveFile',iconCls:'save',handler:function(){dtCatPriceFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){dtCatPriceFn.closeSaveFileDlg()}}
			]
	">
		<br/><br/>
		<span class="txtCenter">文件名：<font color="red">*</font><input id="smFileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/></span>		
	</div>
	
	<div id="dtCatPrice1_toolbar" style="padding:1px;overflow:auto;">
		<form id="dtCatPrice_search">
			<table style="width:1042px;" border="0">
				<tr>
					<td style="padding-left: 22px;width: 81px;">
						<font color="red">*</font><span style="padding-left: 26px;">中分类：</span>
					</td>
					<td style="width:30px">
						<input class="filterSelect" id="centreTypeCode" filterName="centreTypeCode" style="width: 139px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " />
					 </td>
					 <td style="text-align:right;width:110px">	
					 	<font color="red">*</font>小分类：
					 </td>
					 <td style="text-align:right;width:90px">
						<input class="filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 139px;"
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false" 
						/>
					 </td>
					 <td style="text-align:right;width:110px">
					 	<font color="red">*</font>明细类：
					 </td>
					 <td><input class="filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false" />
					</td>
				</tr>
				<tr>
					<td style="padding-left: 22px;">					    
						<font color="red">*</font><span style="padding-left: 37px;">地区：</span>
					</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="regionCode" id="regionCode" style="width: 139px;"
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								url:'masterDataType_listRegionWithoutAll_5.html',
								editable:false" />
					</td>
					<td style="text-align:right;width:110px">商品定性角色：</td>
					<td><input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width: 139px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'merchandiseRole_listQualitative_5.html'
					    " />
					</td>
					<td style="text-align:right;width:110px">商品定量角色：</td>
					<td><input class="easyui-combobox filterSelect" filterName="dlRoleCode" id="dlRoleCode" style="width: 124px;" 
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
					<td colspan="6"><font color="red" style="padding-left: 21px;">*</font>&nbsp;&nbsp;时间范围1：
						<select style="width:60px" id="startYear1" filterName="startYear1" onchange="dtCatPriceFn.selectStartYear(this,'endYear1','1')">
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:60px" id="startMonth1" filterName="startMonth1" onchange="dtCatPriceFn.selectStartMon('startYear1','startMonth1','endYear1','endMonth1')">
							<#list 1..12 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						月 
						<span style="padding-left: 41px;padding-right: 41px;">~</span>
						<select style="width:60px" id="endYear1" filterName="endYear1" onchange="dtCatPriceFn.selectEndYear(this,'startYear1','1')">
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:60px" id="endMonth1" filterName="endMonth1" onchange="dtCatPriceFn.selectEndMon('startYear1','startMonth1','endYear1','endMonth1')">
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
					<td colspan="6" style="padding-left: 27px;">&nbsp;&nbsp;时间范围2：
						<select style="width:60px" id="startYear2" filterName="startYear2" onchange="dtCatPriceFn.selectStartYear(this,'endYear2','2')">
							<option></option>
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:60px" id="startMonth2" filterName="startMonth2" onchange="dtCatPriceFn.selectStartMon('startYear2','startMonth2','endYear2','endMonth2')">
							<option></option>
							<#list 1..12 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						月 
						<span style="padding-left: 41px;padding-right: 42px;">~</span>
						<select style="width:60px" id="endYear2" filterName="endYear2" onchange="dtCatPriceFn.selectEndYear(this,'startYear2','2')">
							<option></option>
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:60px" id="endMonth2" filterName="endMonth2" onchange="dtCatPriceFn.selectEndMon('startYear2','startMonth2','endYear2','endMonth2')">
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
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font color="red">*</font>&nbsp;<input type="radio" id="netWeightType" filterName="netWeightType" name="netWeightType" checked="checked" value="1" />只看非定量装商品</label>
						<span style="padding-left: 40px;"></span>
						<label><input type="radio" name="netWeightType" value="2"/>只看定量装商品</label>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font><label>
						<input type="radio" id="direct" name="dls" checked="checked" />只看直营门店数据</label>
						<span style="padding-left: 40px;"></span>
						<label><input type="radio" id="join" name="dls" />只看加盟门店数据</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label><input type="radio" id="allJoin" name="dls" />直营与加盟门店数据均看</label>
					</td>
				</tr>
				</tr>
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">*</font>
						<label><input type="radio" name="sp" onclick="dtCatPriceFn.displayPriceRegion(false)" checked="checked" />系统自动计算价格带</label>
						<span style="padding-left: 28px;"></span>
						<label><input type="radio" id="handSetPri" name="sp" onclick="dtCatPriceFn.displayPriceRegion(true)" />手动设置价格带</label>
					</td>
				</tr>
				<tr id="title" style="display: none;">
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">注："价格大于等于"与"价格小于"的价差必须为步长的倍数</font>
					</td>
				</tr>
				<tr name="priceRegion0" style="display: none;">
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">*</font>价格大于等于：
						<input class="easyui-numberbox" data-options="min:0,precision:2" maxlength="12" id="minPrice0" name="minPrice0" style="width:80px;" onchange="dtCatPriceFn.floorPrice('0')" onblur="dtCatPriceFn.floorPriceBlur('0')" />
						元&nbsp;&nbsp;价格小于：
						<input class="easyui-numberbox" data-options="min:1,precision:2" maxlength="12" id="maxPrice0" name="maxPrice0" style="width:80px;" onchange="dtCatPriceFn.limtPrice('0')" onblur="dtCatPriceFn.limtPriceBlur('0')" />
						元&nbsp;&nbsp;价格步长：
						<input class="easyui-numberbox" data-options="min:1,precision:2" maxlength="12" id="addPrice0" name="addPrice0" style="width:80px;" onchange="dtCatPriceFn.addPrice('0')" onblur="dtCatPriceFn.addPriceBlur('0')" />
						元&nbsp;&nbsp;&nbsp;<input type="button" value="+" onclick="dtCatPriceFn.clonePriceRegion(this)" style="width: 28px;" />
					</td>
				</tr>
				<tr>
					<td colspan="6" style="height:21px">
						<span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font color="red">*</font>
							<input type="radio" id="" name="dmc" onclick="dtCatPriceFn.displayDmc(false)" checked="checked" />明细报表中显示所有符合查询条件的商品</label>
							&nbsp;&nbsp;&nbsp;&nbsp;<label><input type="radio" id="filterMer" name="dmc" onclick="dtCatPriceFn.displayDmc(true)" />明细报表中只显示指定名称/编号的商品</label>
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
					<td colspan="6" >
						&nbsp;&nbsp;
						<a onclick="dtCatPriceFn.searchAllCategory('1');"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.detailTypePrice.viewAllCategory")>none</#if>;">
							查询
						</a>
						<a onclick="dtCatPriceFn.searchDetCategory('2');"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.detailTypePrice.viewDetCategory")>none</#if>;">
							查看明细报表
						</a>
						<a onclick="dtCatPriceFn.clearSearch();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<a onclick="dtCatPriceFn.exportToExcel();"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.detailTypePrice.exportAllCategory")>none</#if>;">
							导出Excel
						</a>
						<a onclick="dtCatPriceFn.saveFile();"plain="true" class="easyui-linkbutton" data-options="iconCls:'save'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.detailTypePrice.saveAllCategory")>none</#if>;">
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
						明细类名称：<span name="dtName"></span>
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
	    <table id="dtCatPrice1"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "false"
			pagePosition = 'bottom'
			pageSize = "30"
			pageList = "[ 20, 30, 40, 50 ]"
			toolbar="#dtCatPrice1_toolbar"
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
	                <th rowspan="2" data-options="field:'SKUU',align:'right',width:100,hidden:'true',formatter:dtCatPriceFn.formatSKUU">
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
	                <th rowspan="2" data-options="field:'regionPP',align:'right',width:130,hidden:'true',formatter:dtCatPriceFn.formatRegionPP">
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
	                <th rowspan="2" data-options="field:'actSoldStorPercent',align:'right',width:130,hidden:'true',formatter:dtCatPriceFn.formatBigPercent">
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
					<th rowspan="2" data-options="field:'skuPercent',align:'right',width:120,formatter:dtCatPriceFn.formatPercent">
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
					<th rowspan="2" data-options="field:'soldPercent',align:'right',width:120,formatter:dtCatPriceFn.formatPercent">
	                	销量占比
	                </th>
					<th rowspan="2" data-options="field:'soldPricePercent',align:'right',width:100,formatter:dtCatPriceFn.formatPercent">
	                	销售金额占比
	                </th>
					<th rowspan="2" data-options="field:'grossProfitPenrcent',align:'right',width:100,formatter:dtCatPriceFn.formatPercent">
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
					<th rowspan="2" data-options="field:'vitality',align:'right',width:120,formatter:dtCatPriceFn.formatBigPercent">
	                	活跃度
	                </th>
					<th rowspan="2" data-options="field:'powerShopDayPercent',align:'right',width:120,formatter:dtCatPriceFn.formatPercent">
	                	权限店天占比
	                </th>
					<th rowspan="2" data-options="field:'soldShopPercent',align:'right',width:100,formatter:dtCatPriceFn.formatPercent">
	                	销售店天占比
	                </th>
					<th rowspan="2" data-options="field:'psdUnits',align:'right',width:100,formatter:moneyFormatter">
	                	PSD销量（KG）
	                </th>
					<th rowspan="2" data-options="field:'psdSoldPrice',align:'right',width:120,formatter:moneyFormatter">
	                	PSD销售金额（元）
	                </th>
	                <th rowspan="2" data-options="field:'psdGrossProfit',align:'right',width:100,formatter:moneyFormatter">
	                	PSD毛利额（元）
	                </th>
	                <#-- 明细界面 开始 -->
	                <th rowspan="2" data-options="field:'notLessS',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatNotLessS">
	                	高于用户所查明细类的所有商品的平均PSD销量
	                </th>
	                <th rowspan="2" data-options="field:'notLessP',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatNotLessP">
	                	高于用户所查明细类的所有商品的平均PSD销售额
	                </th>
	                <th rowspan="2" data-options="field:'notLessG',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatNotLessG">
	                	高于用户所查明细类的所有商品的平均PSD毛利额
	                </th>
	                <th rowspan="2" data-options="field:'bucketPercent',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatPercent">
	                	格斗占比
	                </th>
	                <th rowspan="2" data-options="field:'volumePercent',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatPercent">
	                	volume&space
	                </th>
	                <th rowspan="2" data-options="field:'valPercent',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatPercent">
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
  	<div id="dtChart1" style="display:none;overflow:auto;width:100%;">
		<div id="dtBar1" style="height:400px;width:200%;"></div>
	</div>
    
    <#-- 第二个Grid -->
    <div id="dtCatPrice2_toolbar" style="padding:2px;overflow:auto;">
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
						明细类名称：<span name="dtName"></span>
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
	    <table  id="dtCatPrice2"
		    fit="true"
		    iconCls= "icon-save"
			pagination = "false"
			pagePosition = 'bottom'
			pageSize = "30"
			pageList = "[ 20, 30, 40, 50 ]"
			toolbar="#dtCatPrice2_toolbar"
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
	                <th rowspan="2" data-options="field:'SKUU',align:'right',width:100,hidden:'true',formatter:dtCatPriceFn.formatSKUU">
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
	                <th rowspan="2" data-options="field:'regionPP',align:'right',width:130,hidden:'true',formatter:dtCatPriceFn.formatRegionPP">
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
	                <th rowspan="2" data-options="field:'actSoldStorPercent',align:'right',width:130,hidden:'true',formatter:dtCatPriceFn.formatBigPercent">
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
					<th rowspan="2" data-options="field:'skuPercent',align:'right',width:120,formatter:dtCatPriceFn.formatPercent">
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
					<th rowspan="2" data-options="field:'soldPercent',align:'right',width:120,formatter:dtCatPriceFn.formatPercent">
	                	销量占比
	                </th>
					<th rowspan="2" data-options="field:'soldPricePercent',align:'right',width:100,formatter:dtCatPriceFn.formatPercent">
	                	销售金额占比
	                </th>
					<th rowspan="2" data-options="field:'grossProfitPenrcent',align:'right',width:100,formatter:dtCatPriceFn.formatPercent">
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
					<th rowspan="2" data-options="field:'vitality',align:'right',width:120,formatter:dtCatPriceFn.formatBigPercent">
	                	活跃度
	                </th>
					<th rowspan="2" data-options="field:'powerShopDayPercent',align:'right',width:120,formatter:dtCatPriceFn.formatPercent">
	                	权限店天占比
	                </th>
					<th rowspan="2" data-options="field:'soldShopPercent',align:'right',width:100,formatter:dtCatPriceFn.formatPercent">
	                	销售店天占比
	                </th>
					<th rowspan="2" data-options="field:'psdUnits',align:'right',width:100,formatter:moneyFormatter">
	                	PSD销量（KG）
	                </th>
					<th rowspan="2" data-options="field:'psdSoldPrice',align:'right',width:120,formatter:moneyFormatter">
	                	PSD销售金额（元）
	                </th>
	                <th rowspan="2" data-options="field:'psdGrossProfit',align:'right',width:100,formatter:moneyFormatter">
	                	PSD毛利额（元）
	                </th>
	                <#-- 明细界面 开始 -->
	                <th rowspan="2" data-options="field:'notLessS',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatNotLessS">
	                	高于用户所查明细类的所有商品的平均PSD销量
	                </th>
	                <th rowspan="2" data-options="field:'notLessP',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatNotLessP">
	                	高于用户所查明细类的所有商品的平均PSD销售额
	                </th>
	                <th rowspan="2" data-options="field:'notLessG',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatNotLessG">
	                	高于用户所查明细类的所有商品的平均PSD毛利额
	                </th>
	                <th rowspan="2" data-options="field:'bucketPercent',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatPercent">
	                	格斗占比
	                </th>
	                <th rowspan="2" data-options="field:'volumePercent',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatPercent">
	                	volume&space
	                </th>
	                <th rowspan="2" data-options="field:'valPercent',align:'right',width:170,hidden:'true',formatter:dtCatPriceFn.formatPercent">
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
  	<div id="dtChart2" style="display:none;overflow:auto;width:100%;">
		<div id="dtBar2" style="height:400px;width:200%;"></div>
	</div>
	
</body>
</html>
</#compress>