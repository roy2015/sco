<#compress>
<!DOCTYPE html>  
<html>  
	<head>  
		
	</head>
	<body>
		<#-- 间隔层 -->
		<div style="height:20px"></div>
		
		<a id="closeBtn">关闭</a> 
		
		<table class="table table-bordered table-condensed">
			<tr>
				<th style="text-align:left;width:120px;">商品编号</th>
				<th style="text-align:left;width:120px;">商品名称</th>
				<th style="text-align:left;width:120px;">供应商编号</th>
				<th style="text-align:left;width:120px;">供应商名称</th>
				<th style="text-align:left;width:120px;">商品定性角色</th>
				<th style="text-align:left;width:120px;">商品定量角色</th>
				<th style="text-align:left;width:120px;">中分类</th>
				<th style="text-align:left;width:120px;">小分类</th>
				<th style="text-align:left;width:120px;">明细类</th>
				<th style="text-align:left;width:120px;">细分类</th>
				<th style="text-align:right;width:120px;">投料占比</th>
				<th style="text-align:left;width:120px;">关联人</th>
				<th style="text-align:left;width:120px;">关联时间</th>
			</tr>
			<#list dataList as l>
				<tr >
					<td style="text-align:left;width:120px;">${l.merchandiseCode}</td>
					<td style="text-align:left;width:120px;">${l.merchandiseName}</td>
					<td style="text-align:left;width:120px;">${l.supplierCode}</td>
					<td style="text-align:left;width:120px;">${l.supplierName}</td>
					<td style="text-align:left;width:120px;">${l.dxRoleName}</td>
					<td style="text-align:left;width:120px;">${l.dlRoleName}</td>
					<td style="text-align:left;width:120px;">${l.centreName}</td>
					<td style="text-align:left;width:120px;">${l.smallName}</td>
					<td style="text-align:left;width:120px;">${l.detailName}</td>
					<td style="text-align:left;width:120px;">${l.fineName}</td>
					<td style="text-align:right;width:120px;"><#if l.inputCost?exists><#if l.inputCost = 0>0<#else>${l.inputCost?c}%</#if></#if></td>
					<td style="text-align:left;width:120px;">${l.createby}</td>
					<td style="text-align:left;width:120px;"><#if l.created?exists>${(l.created)?string("yyyy-MM-dd")}</#if></td>
				</tr>
			</#list>
		</table>
	</body>
</html>
</#compress>