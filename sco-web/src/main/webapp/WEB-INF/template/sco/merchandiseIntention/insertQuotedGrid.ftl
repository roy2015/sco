<#compress>
<head>  
    <script type="text/javascript" src="${copoxtPath}js/gridEdit.js" charset="utf-8"></script>
    <script type="text/javascript" src="${copoxtPath}js/plugin/jquery.form.js" charset="utf-8"></script>
    <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css"/>
	<script type="text/javascript" >
		<#include "sco/merchandiseIntention/insertQuotedGrid.js" />
	</script>
 </head>
<body>
 <input type="hidden" name="intentionCode" id="intentionCode" value="${merchandiseIntention.intentionCode}" />
	<div>
	 	<table>
	 		<tr>
				<td>
					<a id="showInsertQuoted" onclick="insertQuotedFn.showInsertQuoted();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >录入报价单</a>
				</td>
	 		</tr>
	 	</table>
	 </div>
 	
 	 <table id="panel4SupplierGrid"
		     class="easyui-datagrid"
		     title="已选供应商" 
		     singleSelect="true"
		     checkOnSelect="true"
	   		 selectOnCheck="true"
			 pagination = "true"
			 pagePosition = 'bottom'
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
		     nowrap="true"
			 toolbar="#panel4_toolbar"
			 url="merchandiseIntention_listIntentionSupplierMerchandise_2.html?intentionCode=<#if merchandiseIntention.intentionCode?? >${merchandiseIntention.intentionCode}<#else></#if>"
		     data-options="rownumbers:true"> 
		   
		    <thead>
		   		<tr>
		   			<th data-options="field:'supplierAddress'" hidden="true"></th>
		   			<th data-options="field:'NULL',checkbox:true"></th>
		   			<th data-options="field:'intentionSupplierCode',width:150">
						<span >供应商编号</span>
					</th>
					<th data-options="field:'intentionSupplierName',width:250">
						<span >供应商名称</span>
					</th>
		   		</tr>
		   	</thead>
	</table> 
	
	<#-- 已录入的报价单 -->
	<div style="padding: 15px;"></div>
	<div>
	 	<table>
	 		<tr>
				<td>
					<a id="showUpdateQuoted" onclick="insertQuotedFn.showUpdateQuoted();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >修改</a>
				</td>
				<td>
					<a id="deleteQuoted" onclick="insertQuotedFn.deleteQuoted();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >删除</a>
				</td>
	 		</tr>
	 	</table>
	 </div>	
	 <table id="panel4QuotedGrid"
		     class="easyui-datagrid"
		     title="已录入的报价单" 
		     singleSelect="true"
		     checkOnSelect="true"
	   		 selectOnCheck="true"
			 pagination = "true"
			 pagePosition = 'bottom'
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
		     nowrap="true"
			 toolbar="#panel4_QuotedToolbar"
			 url="merchandiseQuoted_listMerchandiseQuoted_2.html?intentionCode=<#if merchandiseIntention.intentionCode?? >${merchandiseIntention.intentionCode}<#else></#if>"
		     data-options="rownumbers:true"> 
		   
		    <thead>
		   		<tr>
		   			<th data-options="field:'quotationCode',width:40" hidden="true"></th>
		   			<th data-options="field:'NULL',width:40,checkbox:true"></th>
		   			<th data-options="field:'intentionSupplierCode',width:120">
						<span >供应商编号</span>
					</th>
					<th data-options="field:'intentionSupplierName',width:300">
						<span >供应商名称</span>
					</th>
					<th data-options="field:'companySite',width:300">
						<span >公司地址</span>
					</th>
					<th data-options="field:'quotedCurrency',width:100">
						<span >报价币种</span>
					</th>
					<th data-options="field:'quotedDate',width:200,formatter: function(value,row,index){
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
						<span >报价日期</span>
					</th>
					<th data-options="field:'price',width:100,align:'right',formatter:moneyFormatter">
						<span style="right">价格</span>
					</th>
					<th data-options="field:'quotationUnits',width:100,align:'right',formatter:insertQuotedFn.moneyFormatter">
						<span style="right">基本计量单位(kg)</span>
					</th>
					<th data-options="field:'minRationed',width:100,align:'right'">
						<span style="right">最小起订量</span>
					</th>
					<th data-options="field:'quotedEndDate',width:200,formatter: function(value,row,index){
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
						<span >报价有效期至</span>
					</th>
					<th data-options="field:'packingType',width:100">
						<span >包装方式</span>
					</th>
					<th data-options="field:'paymentType',width:100">
						<span >付款方式</span>
					</th>
					<th data-options="field:'deliveryType',width:100">
						<span >交货方式</span>
					</th>
					<th data-options="field:'remarks',width:100">
						<span >供应商备注</span>
					</th>
					<th data-options="field:'contactsName',width:100">
						<span >联系人姓名</span>
					</th>
					<th data-options="field:'contactsPhone',width:100">
						<span >联系人电话</span>
					</th>
					<th data-options="field:'contactsEmail',width:150">
						<span >联系人邮箱</span>
					</th>
					<th data-options="field:'contactsFax',width:100">
						<span >联系人传真</span>
					</th>
		   		</tr>
		   	</thead>
	</table> 
</body>	
</#compress>