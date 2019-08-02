<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
<#include "sco/accessoryIntentionCostAnalysis/historicalQuoteSupplier/historicalQuoteSupplierModel.js" />
</script>
</head>
<body class="easyui-layout">

<div style="padding: 5px;overflow: hidden">
<div  id="haveHistoricalQuoteSupplierSetForm">
	<form id="historicalQuoteSupplierSet_form" method="post" enctype="multipart/form-data">
		<table class="table table-condensed" style="width:100px">
			<tr>
			<td>是否只查看该供应商针对所选商品的已引进的报价记录？</td>
			<td><input type="radio" name="cause" value="是" checked="checked" />是</td>
			<td><input type="radio" name="cause" value="否"  />否</td>
			</tr>
		</table>
		
		</form>
		</div>
</div>
</body>
</html>
</#compress>