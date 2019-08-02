<#compress>
<head>  
    <script type="text/javascript" src="${copoxtPath}js/gridEdit.js" charset="utf-8"></script>
    <script type="text/javascript" src="${copoxtPath}js/plugin/jquery.form.js" charset="utf-8"></script>
    <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css"/>

 </head>

<div style="padding: 5px;" id="panel5Analysis_toolbar">
 <input type="hidden"  name="intentionCode" id="intentionCode" value="${intentionCode}" />
 <input type="hidden"  name="convertUnit" id="convertUnit" value="${convertUnit}" />
 	<table >
	 		<tr>
				<td><font  style="font-weight:bold;" size="2">报价分析结果简报：</font></td>
	 		</tr>
	 		<tr>
				<td>本意向品供有 <font color="red"><span name="supplierNumbers" id="supplierNumbers" value="${supplierNumbers}"></span></font> 供应商报价</td>
	 		</tr>
	 		<tr>
				<td>供应商  <font color="red"><span name="cheapSupplierName" id="cheapSupplierName" value="${intentionSupplierName}"></span></font>  报价最低</td>
	 		</tr>
	 	</table>
 	
 	<div>
 	 <table id="panel5AnalysisGrid"
		     class="easyui-datagrid"
		     title="报价单分析结果" 
		     singleSelect="true"
		     checkOnSelect="true"
	   		 selectOnCheck="true"
			 pagination = "true"
			 pagePostion="buttom"
			 pageSize="5"
			 pageList="[5,10,15,20]"
		     nowrap="true"
		     style="width:1060px;height:250px"
			 toolbar="#panel5_AnalysisToolbar"
			 url="quotedCompare_analysisQuoted_2.html?intentionCode=<#if intentionCode?? >${intentionCode}<#else></#if>&convertUnit=<#if convertUnit?? >${convertUnit}<#else></#if>"
		     data-options="rownumbers:true"> 
		   
		     <thead>
		   		<tr>
		   			<th halign="left" data-options="field:'intentionSupplierCode',width:120">
						<span >供应商编号</span>
					</th>
					<th halign="left" data-options="field:'intentionSupplierName',width:300">
						<span >供应商名称</span>
					</th>
					<th data-options="field:'quotationUnits',width:120,align:'right'">
						<span style="right">基本计量单位(kg)</span>
					</th>
					<th data-options="field:'packingType',width:100">
						<span >包装方式</span>
					</th>
					<th data-options="field:'quotedDate',width:160,formatter: function(value,row,index){
							var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 return  date.format('yyyy-MM-dd');
					          }else{
					          	return '';
					          }
						}">
						<span >首次报价日期</span>
					</th>
					<th data-options="field:'price',width:100,align:'right',
					formatter:function(value){
						return moneyFormatter(value);
					}">
						<span style="right">首次报价价格</span>
					</th>
					<th data-options="field:'lastQuotedDate',width:160,formatter: function(value,row,index){
							var   str=value;
					         if(str!=undefined && (str!=null)){  
					             var  arr=str.split('.');
					             str=arr[0];
					           	 str=str.replace(/-/g,'/');
					         	 var date=new Date(str);
					          	 return  date.format('yyyy-MM-dd');
					          }else{
					          	return '';
					          }
						}">
						<span >最后一次报价日期</span>
					</th>
					<th data-options="field:'lastPrice',width:100,align:'right',
					formatter:function(value){
						return moneyFormatter(value);
					}">
						<span style="right">最后一次报价价格</span>
					</th>
					<th data-options="field:'priceGap',width:180,align:'right'">
						<span style="right">最后一次报价比首次报价价差</span>
					</th>
					<th data-options="field:'priceRange',width:150">
						<span >意向品报价价格带</span>
					</th>
					<th data-options="field:'supplierQuotedCounts',width:100,align:'right'">
						<span style="right">共报价次数</span>
					</th>
					<th data-options="field:'supplierNumbers',width:150,align:'right'">
						<span style="right">共参与报价供应商数量</span>
					</th>
					<th data-options="field:'priceRank',width:100,align:'right'">
						<span style="right">报价排名</span>
					</th>
		   		</tr>
		   	</thead>
	</table> 
	</div>
	
</div>	
</#compress>