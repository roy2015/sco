<html>
	<head>
		<script src="js/echarts/echarts.js"></script>
	</head>
	<body>
	
		<div id="msq_search" style="display:none">
			<table class="table table-condensed" border="0">
				<tr>
					<td>商品编号：${relMatWeb.merchandiseCode}</td>
				</tr>
				<tr>
					<td>商品名称：${relMatWeb.merchandiseName}</td>
				</tr>
				<tr>
					<td>供应商编号：${relMatWeb.supplierCode}</td>
				</tr>
				<tr>
					<td>供应商名称：${relMatWeb.supplierName}</td>
				</tr>
				<tr>
					<td>商品原料类型：${relMatWeb.materialTypeName}</td>
				</tr>
				<tr>
					<td>商品原料名称：${relMatWeb.ingredientCodeName}</td>
				</tr>
			</table>
		</div>
	
		<#list dataList as dl>
			<#-- 间隔层 -->
			<div style="height:20px"></div>
			
			<table class="table table-bordered table-condensed">
				<thead>
					<tr>
						<th style="text-align:left;width:154px;">${dl.priceYear}年</th>
						<th style="text-align:right;width:120px;">1月</th>
						<th style="text-align:right;width:120px;">2月</th>
						<th style="text-align:right;width:120px;">3月</th>
						<th style="text-align:right;width:120px;">4月</th>
						<th style="text-align:right;width:120px;">5月</th>
						<th style="text-align:right;width:120px;">6月</th>
						<th style="text-align:right;width:120px;">7月</th>
						<th style="text-align:right;width:120px;">8月</th>
						<th style="text-align:right;width:120px;">9月</th>
						<th style="text-align:right;width:120px;">10月</th>
						<th style="text-align:right;width:120px;">11月</th>
						<th style="text-align:right;width:120px;">12月</th>
						<th style="text-align:right;width:120px;">年平均</th>
					</tr>
				</thead>
				<tbody>
			  		<tr>
			  			<td>投料表内供应商原料采购价格</td>
				  		<#list dl.accList as al>
							<td style="text-align:right;width:120px;">${(al.price?string("#,##0.00"))!}</td>
						</#list>
						<td></td>
			  		</tr>
			  		<#list dl.webList as w>
				  		<tr <#if !(w.priceYear?exists)>style="display:none"</#if>>
				  			<td>${w.priceYear}</td> <#-- 网站名称 -->
				  			
				  			<#assign addFlag = true />
				  			<#assign sumPrice = 0 />
				  			<#if dl.priceYear?number gte curYear> 
				  				<#assign addFlag = false />
				  			</#if>
				  			
					  		<#list 1..12 as m>
				  				<td style="text-align:right;width:120px;">
				  					<#assign bd = -999999 /><#-- 当月是否有值 -->
				  					<#list w.list as z>
				  						<#if z.priceYear = m && z.priceDate = dl.priceYear>
				  							<#if dl.priceYear?number lte curYear>
					  							<#if z.price?exists>
					  								<#if ((dl.priceYear?number = curYear && m?number lte curMonth) || (dl.priceYear?number &lt; curYear))>	
						  								<#assign bd = z.price />
							  							${z.price?string("#,##0.00")}
						  							</#if>
						  						<#else>
						  							<#assign addFlag = false />
					  							</#if>
					  							<#if addFlag = true>
					  								<#assign sumPrice = (sumPrice + z.price) />
					  							</#if>
				  							</#if>
				  						</#if>
				  					</#list>
				  				</td>
				  				<#if bd = -999999 >
				  					<#assign addFlag = false />
				  				</#if>
					  		</#list>
				  			<td style="text-align:right;width:120px;">
				  				<#if dl.priceYear?number &lt; curYear> <#-- 只有小于当前年才有可能有值 -->
				  					<#if addFlag = true>${(sumPrice/12)?string("#,##0.00")}</#if>
				  				</#if>
				  			</td>
				  		</tr>
					</#list>
			  		<tr>
			  			<td>投料占比</td>
				  		<#list dl.ingList as il>
				  			<td style="text-align:right;width:120px;"><#if il.price?exists>${il.price?string("0.00")}%</#if></td>
						</#list>
						<td></td>
			  		</tr>
			  		<tr>
			  			<td>商品进价</td>
				  		<#list dl.purList as pl>
				  			<td style="text-align:right;width:120px;">${(pl.price?string("#,##0.00"))!}</td>
						</#list>
						<td></td>
			  		</tr>
			  		<tr>
			  			<td>商品售价</td>
				  		<#list dl.soldList as sl>
				  			<td style="text-align:right;width:120px;">${(sl.price?string("#,##0.00"))!}</td>
						</#list>
						<td></td>
			  		</tr>
				</tbody>
			</table>
			
			<#-- 图表 -->
			<div style="overflow:auto;">
				<div id="chart${dl_index}" style="height:400px;"></div>
			</div>
			<script type="text/javascript">
				merMatSupQuoGridFn.showChart(${dataListJson}, ${dl_index}, ${curYear?c}, ${curMonth?c});
			</script>
		</#list>
	</body>
</html>