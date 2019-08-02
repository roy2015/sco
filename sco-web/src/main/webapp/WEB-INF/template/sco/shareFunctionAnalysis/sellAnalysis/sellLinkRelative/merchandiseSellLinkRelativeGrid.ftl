<#compress>
<!DOCTYPE html>
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
   	 <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/shareFunctionAnalysis/sellAnalysis/sellLinkRelative/merchandiseSellLinkRelativeModel.js" />
    </script>
</head>
<body>
<div >
	<input type="hidden" name="sellRegion" id="sellRegion" value="${sellRegion}" />
	<input type="hidden" name="startDate" id="startDate" value="<#if startDate??>${startDate?string('yyyy-MM-dd')}</#if>" />
	<input type="hidden" name="endDate" id="endDate" value="<#if endDate??>${endDate?string('yyyy-MM-dd')}</#if>" />
	<input type="hidden" name="merchandiseCodes" id="merchandiseCodes" value="${merchandiseCodes}" />
	<input type="hidden" name="merchandiseAndSupplierCodes" id="merchandiseAndSupplierCodes" value="${merchandiseAndSupplierCodes}" />
	<input type="hidden" name="directJoin" id="directJoin" value="${directJoin}" />
	<input type="hidden" name="rationed" id="rationed" value="${rationed}" />
	<input type="hidden" name="linkRelativeCycle" id="linkRelativeCycle" value="${linkRelativeCycle}" />
	<input type="hidden" name="cycleCount" id="cycleCount" value="${cycleCount}" />
	
	<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
		<a onclick="productSellFn.close()" plain="true" class="easyui-linkbutton" data-options="iconCls:'close'">关闭</a> 
		<a onclick="productSellFn.saveFile();" plain="true"class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a> 
		<a onclick="productSellFn.exportProductSellExcel()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'">导出Execl</a>
	</table>
</div>
	<table  id="productSell_Grid"
			class="easyui-datagrid"
			title="商品信息"
			fitColumns="true"
		    singleSelect="true"
		    checkOnSelect="false"
	   		selectOnCheck="false"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "10"
			pageList = "[ 10, 20, 30, 40 ]"
			url="merchandiseLinkRelative_listMerchandiseLinkRelative_2.html?merchandiseAndSupplierCodes=${merchandiseAndSupplierCodes}&rationed=${rationed}"
		    data-options="rownumbers:true"> 
		<thead>
			<tr>
				<th data-options="field:'merchandiseCode',width:100,sortable:false,align:'left',halign:'center'">
					<span>商品编号</span>
				</th>
				<th data-options="field:'merchandiseName',width:150,sortable:false,align:'left',halign:'center'">
					<span>商品名称</span>
				</th>
				<th data-options="field:'dxRoleName',width:100,sortable:false,align:'left',halign:'center'">
					<span>商品定性角色</span>
				</th>
				<th data-options="field:'dlRoleName',width:100,sortable:false,align:'left',halign:'center'">
					<span>商品定量角色</span>
				</th>
				<th data-options="field:'centreTypeName',width:100,sortable:false,align:'left',halign:'center'">
					<span>中分类</span>
				</th>
				<th data-options="field:'smallTypeName',width:100,sortable:false,align:'left',halign:'center'">
					<span>小分类</span>
				</th>
				<th data-options="field:'detailTypeName',width:100,sortable:false,align:'left',halign:'center'">
					<span>明细类</span>
				</th>
				<th data-options="field:'fineTypeName',width:100,sortable:false,align:'left',halign:'center'">
					<span>细分类</span>
				</th>
				<th data-options="field:'supplierCode',width:100,sortable:false,align:'left',halign:'center'">
					<span>供应商编号</span>
				</th>
				<th data-options="field:'supplierName',width:150,sortable:false,align:'left',halign:'center'">
					<span>供应商名称</span>
				</th>
			</tr>
		</thead>
	</table>
	
<label>商品环比信息：<br/>
<#if (directJoin??)&&(directJoin=='direct')>
只看直营门店数据
<#elseif (directJoin??)&&(directJoin=='join')>
 只看加盟门店数据
<#else>
 直营与加盟门店数据均看
</#if><br/>
<#if (rationed??)&&(rationed=='gjz') >
只查非定量装商品
<#elseif (rationed??)&&(rationed=='dlz') >
只查定量装商品
<#else>
定量装和非定量装均看
</#if>	<br/>
 环比周期的天数：${linkRelativeCycle}<br/>
 时间范围：<font id="timeRange"><#if startDate??>${startDate?string('yyyy-MM-dd')}</#if>至<#if endDate??>${endDate?string('yyyy-MM-dd')}</#if></font>
</label>

<table  id="merchandiseSellLinkRelative_Grid"
			class="easyui-datagrid"
		    title="商品环比信息"
		    singleSelect="true"
		    checkOnSelect="false"
	   		selectOnCheck="false"
			pagination = "false"
			url="merchandiseLinkRelative_listProductLinkRelativeGrid_2.html?merchandiseAndSupplierCodes=${merchandiseAndSupplierCodes}&startDate=${startDate?string('yyyy-MM-dd')}
				 &endDate=${endDate?string('yyyy-MM-dd')}&directJoin=${directJoin}&linkRelativeCycle=${linkRelativeCycle}&cycleCount=${cycleCount}&sellRegion=${sellRegion}&rationed=${rationed}"
		    data-options="rownumbers:true"> 
		<thead>
			<tr>
				<th data-options="field:'sellDate',width:150,sortable:false,align:'right',halign:'center'">
					<span>日期</span>
				</th>
				<th data-options="field:'sellQuantity',width:100,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>销售量${(rationed=='dlz')?string('(件)','(KG)')}</span>
				</th>
				<th data-options="field:'beforeSellQuantity',width:120,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>环比销售量${(rationed=='dlz')?string('(件)','(KG)')}</span>
				</th>
				<th data-options="field:'sellQuantityChange',width:120,sortable:false,align:'right',halign:'center',formatter: function(value,row,index){
							if(value!=null&&value!=0){
								return moneyFormatter(row.sellQuantityChange,2)+'%';
							}else{
								return '';
							}
						}">
					<span>销售量环比增长</span>
				</th>
				<th data-options="field:'sellTotalPrice',width:100,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>销售额(元)</span>
				</th>
				<th data-options="field:'beforeSellTotalPrice',width:120,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>环比销售额(元)</span>
				</th>
				<th data-options="field:'sellTotalPriceChange',width:120,sortable:false,align:'right',halign:'center',formatter: function(value,row,index){
							if(value!=null){
								return moneyFormatter(row.sellTotalPriceChange,2)+'%';
							}else{
								return '0.00%';
							}
						}">
					<span>销售额环比增长</span>
				</th>
				<th data-options="field:'sellProfit',width:100,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>毛利额(元)</span>
				</th>
				<th data-options="field:'beforeSellProfit',width:120,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>环比毛利额(元)</span>
				</th>
				<th data-options="field:'sellProfitChange',width:120,sortable:false,align:'right',halign:'center',formatter: function(value,row,index){
							if(value!=null&&value!=0){
								return moneyFormatter(row.sellProfitChange,2)+'%';
							}else{
								return '';
							}
						}">
					<span>毛利额环比增长</span>
				</th>
				<th data-options="field:'psdSellQuantity',width:120,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>PSD销售量${(rationed=='dlz')?string('(件)','(KG)')}</span>
				</th>
				<th data-options="field:'beforePsdSellQuantity',width:150,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>环比PSD销售量${(rationed=='dlz')?string('(件)','(KG)')}</span>
				</th>
				<th data-options="field:'psdSellQuantityChange',width:150,sortable:false,align:'right',halign:'center',formatter: function(value,row,index){
							if(value!=null&&value!=0){
								return moneyFormatter(row.psdSellQuantityChange,2)+'%';
							}else{
								return '';
							}
						}">
					<span>PSD销售量环比增长</span>
				</th>
				<th data-options="field:'psdSellTotalPrice',width:120,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>PSD销售额(元)</span>
				</th>
				<th data-options="field:'beforePsdSellTotalPrice',width:150,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>环比PSD销售额(元)</span>
				</th>
				<th data-options="field:'psdSellTotalPriceChange',width:150,sortable:false,align:'right',halign:'center',formatter: function(value,row,index){
							if(value!=null&&value!=0){
								return moneyFormatter(row.psdSellTotalPriceChange,2)+'%';
							}else{
								return '';
							}
						}">
					<span>PSD销售额环比增长</span>
				</th>
				<th data-options="field:'psdSellProfit',width:120,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>PSD毛利额(元)</span>
				</th>
				<th data-options="field:'beforePsdSellProfit',width:150,sortable:false,align:'right',halign:'center',formatter:function(value){
			 																						   return moneyFormatter(value,2);
																							        }">
					<span>环比PSD毛利额(元)</span>
				</th>
				<th data-options="field:'psdSellProfitChange',width:150,sortable:false,align:'right',halign:'center',formatter: function(value,row,index){
							if(value!=null&&value!=0){
								return moneyFormatter(row.psdSellProfitChange,2)+'%';
							}else{
								return '';
							}
						}">
					<span>PSD毛利额环比增长</span>
				</th>
			</tr>
		</thead>
	</table>
	<div class="easyui-dialog" id="saveFileDlg" style="width:320px;height:160px" 
			data-options="
				title:'请输入文件名称',
				closable:false,closed:true,modal:true,buttons:[
						{id:'save',text:'确定',iconCls:'save',handler:function(){productSellFn.submitSaveFileDlg()}},
						{text:'关闭',iconCls:'close',handler:function(){productSellFn.closeSaveFileDlg()}}]">
			<br/><br/>
			<div style="margin-left:25px">文件名称: <input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:200px;"/></div>		
	</div>
</body>
</html>
</#compress>
