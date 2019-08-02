<#compress>
<head>  
    <script type="text/javascript" src="${copoxtPath}js/gridEdit.js" charset="utf-8"></script>
    <script type="text/javascript" src="${copoxtPath}js/plugin/jquery.form.js" charset="utf-8"></script>
    <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css"/>

 </head>

<div style="padding: 5px;" id="panel5History_toolbar">
 <input type="hidden"  name="intentionSupplierCode" id="intentionSupplierCode" value="${intentionSupplierCode}" />
 <input type="hidden"  name="intentionCode" id="intentionCode" value="${intentionCode}" />
 	<table >
	 		<tr>
				<td><font  style="font-weight:bold;" size="2">供应商信息： </font></td>
	 		</tr>
	 		<tr>
	 			<th align="right" style="width: 80px;"><span >公司名称： </span></th>
				<td colspan="3">
					<input name="companyName"  value="${lastQuoted.companyName}" class="easyui-validatebox" readonly="readonly" style="width:285px;background:lightgray;"/>
				</td>
	 		</tr>
	 		<tr>
				<th align="right" style="width: 80px;"><span >公司地址： </span></th>
				<td colspan="3">
					<input name="companySite"  value="${lastQuoted.companySite}" class="easyui-validatebox" readonly="readonly" style="width:285px;background:lightgray;"/>
			   </td>	 		
		   </tr>
		   <tr>
				<th align="right" style="width: 80px;"><span >联系人姓名： </span></th>
				<td >
					<input name="contactsName"  value="${lastQuoted.contactsName}" class="easyui-validatebox" readonly="readonly" style="width:100px;background:lightgray;"/>
			   </td>
			   <th align="right" style="width: 80px;"><span >联系人电话：</span></th>
				<td >
					<input name="contactsPhone"  value="${lastQuoted.contactsPhone}" class="easyui-validatebox" readonly="readonly" style="width:100px;background:lightgray;"/>
			   </td>	 		
		   </tr>
		   <tr>
			    <td style="width:120px">
					<a id="exportHistoryQuoted" onclick="quotedCompareFn.exportHistoryQuoted('${intentionSupplierCode}','${lastQuoted.intentionSupplierName}');" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" >导出Excel</a>
				</td>
		  </tr>
	</table>
 	
 	<div>
 	 <table id="panel5HistoryGrid"
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
		     style="width:1060px;height:280px"
			 toolbar="#panel5_HistoryToolbar"
			 url="quotedCompare_listHistoryQuoted_2.html?intentionSupplierCode=<#if intentionSupplierCode?? >${intentionSupplierCode}<#else></#if>"
		     data-options="rownumbers:true"> 
		   
		     <thead>
		   		<tr>
		   			<th data-options="field:'intentionSupplierCode',width:40" hidden="true"></th>
		   			<th data-options="field:'intentionSupplierName',width:40" hidden="true"></th>
		   			<th data-options="field:'intentionCode',width:120">
						<span >意向品编号</span>
					</th>
					<th data-options="field:'intentionName',width:300">
						<span >意向品名称</span>
					</th>
					<th data-options="field:'centreTypeCode',width:110,sortable:true,formatter: function(value,row,index){
							if(value!=null){
								return row.centreTypeName;
							}else{
								return value;
							}
						}">
                		<span >中分类</span>
	                </th>
					<th data-options="field:'smallTypeCode',width:110,sortable:true,formatter: function(value,row,index){
							if(value!=null){
								return row.smallTypeName;
							}else{
								return value;
							}
						}">
	                	<span >小分类</span>
	                </th>
					<th data-options="field:'detailTypeCode',width:110,sortable:true,formatter: function(value,row,index){
							if(value!=null){
								return row.detailTypeName;
							}else{
								return value;
							}
						}">
	                	<span >明细类</span>
	                </th>
					<th data-options="field:'fineTypeCode',width:110,sortable:true,formatter: function(value,row,index){
							if(value!=null){
								return row.fineTypeName;
							}else{
								return value;
							}
						}">
	                	<span >细分类</span>
	                </th>
					<th rowspan="2" data-options="field:'created',width:160,sortable:true,formatter: function(value,row,index){
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
	                	<span >意向品创建日期</span>
	                </th>
					<th  data-options="field:'quotationUnits',width:120,align:'right'">
						<span >基本计量单位(kg)</span>
					</th>
					<th  data-options="field:'packingType',width:100">
						<span >包装方式</span>
					</th>
					<th  data-options="field:'quotedDate',width:160,formatter: function(value,row,index){
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
					<th  data-options="field:'price',width:100,align:'right',
					formatter:function(value){
						return moneyFormatter(value);
					}">
						<span style="right">首次报价价格</span>
					</th>
					<th  data-options="field:'lastQuotedDate',width:160,formatter: function(value,row,index){
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
					<th  data-options="field:'lastPrice',width:100,align:'right',
					formatter:function(value){
						return moneyFormatter(value);
					}">
						<span style="right">最后一次报价价格</span>
					</th>
					<th  data-options="field:'priceGap',width:180,align:'right'">
						<span style="right">最后一次报价比首次报价价差</span>
					</th>
					<th  data-options="field:'supplierQuotedCounts',width:100,align:'right'">
						<span style="right">共报价次数</span>
					</th>
					<th  data-options="field:'supplierNumbers',width:150,align:'right'">
						<span style="right">共参与报价供应商数量</span>
					</th>
					<th  data-options="field:'priceRank',width:100,align:'right'">
						<span style="right">报价排名</span>
					</th>
		   		</tr>
		   	</thead>
	</table> 
	</div>
	
</div>	
</#compress>