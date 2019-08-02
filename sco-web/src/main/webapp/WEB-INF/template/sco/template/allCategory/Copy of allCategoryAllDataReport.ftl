<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style>
		table {
			border-collapse: collapse;
			border: none;
			width: 1800px;
			font-family : "微软雅黑";
			font-size : 13px;
			white-space : nowrap
		}
		td, th {
			border: solid #ddd 1px;
			padding:10px;
		}
		th {
			background-color: #EEEEEE;
		}
	</style>
	
	<script src="${jsPath}echarts.js"></script>
	<script>
		function allShowChart(data, index) {
			var lengendArr = new Array();
			var seriesArr = new Array();
		
			for ( var i in data) {
				var obj = data[i];
				var skuUnits = obj.skuUnits;
				lengendArr[i] = obj.regionPrice;// 所有组
				seriesArr[i] = {
					name : obj.regionPrice,
					type : 'bar',
					itemStyle : {
						normal : {
							label : {
								show : true,
								formatter : function(obj) {
									if (obj.value == 0 || !obj.value) {
										return 0;
									}
									return obj.value + "%";
								}
							}
						// 柱子顶上显示值
						}
					},
					data : [ (obj.skuPercent * 100), (obj.soldPercent * 100),
							(obj.soldPricePercent * 100),
							(obj.grossProfitPenrcent * 100), (obj.vitality * 100),
							(obj.powerShopDayPercent * 100),
							(obj.soldShopPercent * 100),
							((obj.notLessPsdUnitsSku / skuUnits) * 100),
							((obj.notLessPsdSoldPrice / skuUnits) * 100),
							((obj.notLessPsdGrossProfit / skuUnits) * 100) ]
				};
			}
		
			require.config({
				paths : {
					echarts : '${jsPath}'
				}
			});
		
			// 使用
			require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById("allBar" + index));
				var option = {
					title : {
						text : '',
						subtext : ''
					},
					tooltip : {
						trigger : 'item',
						formatter : function(obj) {
							return obj[0] + '<br>' + obj[1] + ":" + obj[2] + "%";// 光标移入图像时显示数据
						}
					},
					legend : {
						itemGap : 10,
						data : lengendArr
					},
					calculable : false,
					xAxis : [ {
						type : 'category',
						boundaryGap : true,
						data : [ 'SKU数量占比', '销量占比', '销售金额占比', '毛利额占比', '活跃度', '权限店天占比',
								'销售店天占比', '大于等于PSD销量的SKU数占比', '大于等于PSD销售额的SKU数占比',
								'大于等于PSD毛利额的SKU数占比' ]
					} ],
					yAxis : [ {
						type : 'value',
						boundaryGap : [ 0, 0 ], // 坐标轴两端空白策略，数组内数值代表百分比
						axisLabel : {
							formatter : '{value}%'
						}
					} ],
					series : seriesArr
				};
		
				// 为echarts对象加载数据
				myChart.setOption(option);
			});
		}
	</script>
</head>
<body>
	<#-- 表格1 -->
	<table>
		<tr>
			<td colspan="26">
				占SKU数量最多价格带为:${pubMap.part10}
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span style="padding-left: 120px;">销量占比最大价格带为:${pubMap.part11}</span>
			</td>
		</tr>
		<tr>
			<td colspan="26">
				销售额占比最大价格带为:${pubMap.part12}
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span style="padding-left: 120px;">毛利额占比最大价格带为:${pubMap.part13}</span>
			</td>
		</tr>
		
		<tr>
			<td colspan="26">时间范围1:${pubMap.searchDate1}</td>
		</tr>
		<tr>
			<td colspan="26">${pubMap.merTitle}</td>
		</tr>
		<tr>
			<td colspan="26">地区:${pubMap.regionName}</td>
		</tr>
		<tr>
			<td colspan="26">只看(非定量/定量)装商品:${pubMap.netWeightTypeCon}</td>
		</tr>
		<tr>
			<td colspan="26">只看直营/加盟/直营与加盟均看门店数据:${pubMap.directCon}</td>
		</tr>
		<tr>
			<td colspan="26">系统自动计算/手动设置价格带:${pubMap.priceSetCon}</td>
		</tr>
		<tr>
			<th style="text-align: left;padding-left: 2px;">地区</th>
			<th style="text-align: right;padding-right: 2px;">地区总门店数</th>
			<th style="text-align: right;padding-right: 2px;">地区SKU总数</th>
			<th style="text-align: right;padding-right: 2px;">地区价格带</th>
			<th style="text-align: right;padding-right: 2px;">SKU数量</th>
			<th style="text-align: right;padding-right: 2px;">SKU数量占比</th>
			<th style="text-align: right;padding-right: 2px;">销量</th>
			<th style="text-align: right;padding-right: 2px;">销售金额</th>
			<th style="text-align: right;padding-right: 2px;">毛利额</th>
			<th style="text-align: right;padding-right: 2px;">销量占比</th>
			<th style="text-align: right;padding-right: 2px;">销售金额占比</th>
			<th style="text-align: right;padding-right: 2px;">毛利额占比</th>
			<th style="text-align: right;padding-right: 2px;">权限店天</th>
			<th style="text-align: right;padding-right: 2px;">销售店天</th>
			<th style="text-align: right;padding-right: 2px;">活跃度</th>
			<th style="text-align: right;padding-right: 2px;">权限店天占比</th>
			<th style="text-align: right;padding-right: 2px;">销售店天占比</th>
			<th style="text-align: right;padding-right: 2px;">PSD销量</th>
			<th style="text-align: right;padding-right: 2px;">PSD销售金额</th>
			<th style="text-align: right;padding-right: 2px;">PSD毛利额</th>
			<th style="text-align: right;padding-right: 2px;">大于等于PSD销量的SKU数</th>
			<th style="text-align: right;padding-right: 2px;">小于PSD销量的SKU数</th>
			<th style="text-align: right;padding-right: 2px;">大于等于PSD销售额的SKU数</th>
			<th style="text-align: right;padding-right: 2px;">小于PSD销售额的SKU数</th>
			<th style="text-align: right;padding-right: 2px;">大于等于PSD毛利额的SKU数</th>
			<th style="text-align: right;padding-right: 2px;">小于PSD毛利额的SKU数</th>
		</tr>
		<#list data1 as l>
		<tr>
			<td style="text-align: left;padding-left: 2px;">${l.regionName}</td>
			<td style="text-align: right;padding-right: 2px;"><#if l.regionStoreSum?exists>${l.regionStoreSum?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.regionSKUSum?exists>${l.regionSKUSum?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;">${l.regionPrice}</td>
			<td style="text-align: right;padding-right: 2px;"><#if l.skuUnits?exists>${l.skuUnits?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.skuPercent?exists><#if l.skuPercent = 0>0<#else>${l.skuPercent?string("#.##%")}</#if></#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.soldUnits?exists>${l.soldUnits?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.soldPrice?exists>${l.soldPrice?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.grossProfit?exists>${l.grossProfit?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.soldPercent?exists><#if l.soldPercent = 0>0<#else>${l.soldPercent?string("#.##%")}</#if></#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.soldPricePercent?exists><#if l.soldPricePercent = 0>0<#else>${l.soldPricePercent?string("#.##%")}</#if></#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.grossProfitPenrcent?exists><#if l.grossProfitPenrcent = 0>0<#else>${l.grossProfitPenrcent?string("#.##%")}</#if></#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.powerShopDay?exists>${l.powerShopDay?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.soldShopDay?exists>${l.soldShopDay?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.vitality?exists><#if l.vitality = 0>0<#elseif l.vitality &lt; 1>${l.vitality?string("#.##%")}<#else>100%</#if></#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.powerShopDayPercent?exists><#if l.powerShopDayPercent = 0>0<#else>${l.powerShopDayPercent?string("#.##%")}</#if></#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.soldShopPercent?exists><#if l.soldShopPercent = 0>0<#else>${l.soldShopPercent?string("#.##%")}</#if></#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.psdUnits?exists>${l.psdUnits?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.psdSoldPrice?exists>${l.psdSoldPrice?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.psdGrossProfit?exists>${l.psdGrossProfit?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdUnitsSku?exists>${l.notLessPsdUnitsSku?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.lessPsdUnitsSku?exists>${l.lessPsdUnitsSku?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdSoldPrice?exists>${l.notLessPsdSoldPrice?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.lessPsdSoldPrice?exists>${l.lessPsdSoldPrice?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdGrossProfit?exists>${l.notLessPsdGrossProfit?c}</#if></td>
			<td style="text-align: right;padding-right: 2px;"><#if l.lessPsdGrossProfit?exists>${l.lessPsdGrossProfit?c}</#if></td>
		</tr>
		</#list>
	</table>
	<#-- 统计1 -->
	<div id="allBar1" style="height:400px;display: none;"></div>
	
	<#-- 表格2 -->
	<#if data2?exists>
		<table>
			<tr>
				<td colspan="26">
					占SKU数量最多价格带为:${pubMap.part20}
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="padding-left: 120px;">销量占比最大价格带为:${pubMap.part21}</span>
				</td>
			</tr>
			<tr>
				<td colspan="26">
					销售额占比最大价格带为:${pubMap.part22}
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="padding-left: 120px;">毛利额占比最大价格带为:${pubMap.part23}</span>
				</td>
			</tr>
			
			<tr>
				<td colspan="26">时间范围2:${pubMap.searchDate2}</td>
			</tr>
			<tr>
				<td colspan="26">${pubMap.merTitle}</td>
			</tr>
			<tr>
				<td colspan="26">地区:${pubMap.regionName}</td>
			</tr>
			<tr>
				<td colspan="26">只看(非定量/定量)装商品:${pubMap.netWeightTypeCon}</td>
			</tr>
			<tr>
				<td colspan="26">只看直营/加盟/直营与加盟均看门店数据:${pubMap.directCon}</td>
			</tr>
			<tr>
				<td colspan="26">系统自动计算/手动设置价格带:${pubMap.priceSetCon}</td>
			</tr>
			<tr>
				<th style="text-align: left;padding-left: 2px;">地区</th>
				<th style="text-align: right;padding-right: 2px;">地区总门店数</th>
				<th style="text-align: right;padding-right: 2px;">地区SKU总数</th>
				<th style="text-align: right;padding-right: 2px;">地区价格带</th>
				<th style="text-align: right;padding-right: 2px;">SKU数量</th>
				<th style="text-align: right;padding-right: 2px;">SKU数量占比</th>
				<th style="text-align: right;padding-right: 2px;">销量</th>
				<th style="text-align: right;padding-right: 2px;">销售金额</th>
				<th style="text-align: right;padding-right: 2px;">毛利额</th>
				<th style="text-align: right;padding-right: 2px;">销量占比</th>
				<th style="text-align: right;padding-right: 2px;">销售金额占比</th>
				<th style="text-align: right;padding-right: 2px;">毛利额占比</th>
				<th style="text-align: right;padding-right: 2px;">权限店天</th>
				<th style="text-align: right;padding-right: 2px;">销售店天</th>
				<th style="text-align: right;padding-right: 2px;">活跃度</th>
				<th style="text-align: right;padding-right: 2px;">权限店天占比</th>
				<th style="text-align: right;padding-right: 2px;">销售店天占比</th>
				<th style="text-align: right;padding-right: 2px;">PSD销量</th>
				<th style="text-align: right;padding-right: 2px;">PSD销售金额</th>
				<th style="text-align: right;padding-right: 2px;">PSD毛利额</th>
				<th style="text-align: right;padding-right: 2px;">大于等于PSD销量的SKU数</th>
				<th style="text-align: right;padding-right: 2px;">小于PSD销量的SKU数</th>
				<th style="text-align: right;padding-right: 2px;">大于等于PSD销售额的SKU数</th>
				<th style="text-align: right;padding-right: 2px;">小于PSD销售额的SKU数</th>
				<th style="text-align: right;padding-right: 2px;">大于等于PSD毛利额的SKU数</th>
				<th style="text-align: right;padding-right: 2px;">小于PSD毛利额的SKU数</th>
			</tr>
			<#list data2 as l>
			<tr>
				<td style="text-align: left;padding-left: 2px;">${l.regionName}</td>
				<td style="text-align: right;padding-right: 2px;"><#if l.regionStoreSum?exists>${l.regionStoreSum?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.regionSKUSum?exists>${l.regionSKUSum?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;">${l.regionPrice}</td>
				<td style="text-align: right;padding-right: 2px;"><#if l.skuUnits?exists>${l.skuUnits?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.skuPercent?exists><#if l.skuPercent = 0>0<#else>${l.skuPercent?string("#.##%")}</#if></#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldUnits?exists>${l.soldUnits?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldPrice?exists>${l.soldPrice?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.grossProfit?exists>${l.grossProfit?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldPercent?exists><#if l.soldPercent = 0>0<#else>${l.soldPercent?string("#.##%")}</#if></#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldPricePercent?exists><#if l.soldPricePercent = 0>0<#else>${l.soldPricePercent?string("#.##%")}</#if></#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.grossProfitPenrcent?exists><#if l.grossProfitPenrcent = 0>0<#else>${l.grossProfitPenrcent?string("#.##%")}</#if></#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.powerShopDay?exists>${l.powerShopDay?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldShopDay?exists>${l.soldShopDay?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.vitality?exists><#if l.vitality = 0>0<#elseif l.vitality &lt; 1>${l.vitality?string("#.##%")}<#else>100%</#if></#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.powerShopDayPercent?exists><#if l.powerShopDayPercent = 0>0<#else>${l.powerShopDayPercent?string("#.##%")}</#if></#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.soldShopPercent?exists><#if l.soldShopPercent = 0>0<#else>${l.soldShopPercent?string("#.##%")}</#if></#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.psdUnits?exists>${l.psdUnits?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.psdSoldPrice?exists>${l.psdSoldPrice?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.psdGrossProfit?exists>${l.psdGrossProfit?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdUnitsSku?exists>${l.notLessPsdUnitsSku?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.lessPsdUnitsSku?exists>${l.lessPsdUnitsSku?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdSoldPrice?exists>${l.notLessPsdSoldPrice?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.lessPsdSoldPrice?exists>${l.lessPsdSoldPrice?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.notLessPsdGrossProfit?exists>${l.notLessPsdGrossProfit?c}</#if></td>
				<td style="text-align: right;padding-right: 2px;"><#if l.lessPsdGrossProfit?exists>${l.lessPsdGrossProfit?c}</#if></td>
			</tr>
			</#list>
		</table>
	</#if>
	<#-- 统计2 -->
	<div id="allBar2" style="height:400px;display: none;"></div>

	<script>
		<#if data1Json?exists>
			document.getElementById("allBar1").style.display = 'block';
			allShowChart(${data1Json}, '1');
		</#if>
		<#if data2Json?exists>
			document.getElementById("allBar2").style.display = 'block';
			allShowChart(${data2Json}, '2');
		</#if>
	</script>
</body>
</html>