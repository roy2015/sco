<#compress>
<head>  
    <script type="text/javascript" src="${copoxtPath}js/gridEdit.js" charset="utf-8"></script>
    <script type="text/javascript" src="${copoxtPath}js/plugin/jquery.form.js" charset="utf-8"></script>
    <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css"/>

 </head>

<div style="padding: 5px;" id="panel5Analysis_toolbar">
 <input type="hidden"  name="intentionCode" id="intentionCode" value="${intentionCode}" />
 <input type="hidden"  name="compareIntentionCodes" id="compareIntentionCodes" value="${compareIntentionCodes}" />
 <input type="hidden" name="compareUnit" id="compareUnit" value="${compareUnit}" />
 
 	<div>
 	 <table id="panel5CompareResultGrid"
		     class="easyui-datagrid"
		     title="查看对比结果" 
		     singleSelect="true"
		     checkOnSelect="true"
	   		 selectOnCheck="true"
			 pagination = "true"
			 pagePostion="buttom"
			 pageSize="5"
			 pageList="[5,10,15,20]"
		     nowrap="true"
		     style="width:1060px;height:250px"
			 toolbar="#panel5_CompareResultToolbar"
			 url="quotedCompare_listCompareResult_2.html?intentionCode=<#if intentionCode?? >${intentionCode}<#else></#if>&compareIntentionCodes=<#if compareIntentionCodes?? >${compareIntentionCodes}<#else></#if>&compareUnit=<#if compareUnit?? >${compareUnit}<#else></#if>"
		     data-options="rownumbers:true"> 
		   
		     <thead>
		   		<tr>
		   			<th halign="left" data-options="field:'intentionSupplierCode',width:120">
						<span >供应商编号</span>
					</th>
					<th halign="left" data-options="field:'intentionSupplierName',width:300">
						<span >供应商名称</span>
					</th>
					<th data-options="field:'quotationUnits',width:120,formatter:function(value){
																					   return moneyFormatter(value,3);
																			        }">
						<span >基本计量单位(kg)</span>
					</th>
					<th data-options="field:'packingType',width:100">
						<span >包装方式</span>
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
					<th data-options="field:'lastPrice',width:100,align:'right',formatter:function(value){
																					   return moneyFormatter(value,3);
																			        }">
						<span style="right">最后一次报价价格</span>
					</th>
					<th data-options="field:'priceRange',width:200">
						<span >参照品价格带</span>
					</th>
					<th data-options="field:'priceRank',width:120,align:'right'">
						<span style="right">报价在参照品中排行</span>
					</th>
					<th data-options="field:'avgPrice',width:100,align:'right'">
						<span style="right">参照品平均价格</span>
					</th>
					<th data-options="field:'priceGap',width:200,align:'right'">
						<span style="right">报价高于参照品平均价格</span>
					</th>
		   		</tr>
		   	</thead>
	</table> 
	</div>
	
</div>	
</#compress>