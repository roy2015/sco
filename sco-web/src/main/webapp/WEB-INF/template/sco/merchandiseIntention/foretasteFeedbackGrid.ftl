<#compress>
<head>  
    <script type="text/javascript" src="${copoxtPath}js/gridEdit.js" charset="utf-8"></script>
    <script type="text/javascript" src="${copoxtPath}js/plugin/jquery.form.js" charset="utf-8"></script>
    <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css"/>
	<script type="text/javascript" >
		<#include "sco/merchandiseIntention/foretasteFeedbackGrid.js" />
	</script>
 </head>

<body>
 <input type="hidden"  name="intentionCode" id="intentionCode" value="${merchandiseIntention.intentionCode}" />
	<div>
	 	<table >
	 		<tr>
				<td>
					<a id="showInsertForetaste" onclick="feedbackFn.showInsertForetaste();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >录入试吃反馈</a>
				</td>
				<td>
					<a id="showInsertForetaste" onclick="feedbackFn.passForetaste();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >试吃通过</a>
				</td>
				<td>
					<a id="showInsertForetaste" onclick="feedbackFn.cancelPassForetaste();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >取消试吃通过</a>
				</td>
	 		</tr>
	 	</table>
	 </div>
 	 <table id="panel6SupplierGrid"
		     class="easyui-datagrid"
		     title="已选供应商" 
		     singleSelect="true"
		     checkOnSelect="false"
	   		 selectOnCheck="false"
			 pagination = "true"
			 pagePosition = 'bottom'
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
			 fitColumns="true" 
		     nowrap="true"
			 toolbar="#panel6_toolbar"
			 url="merchandiseIntention_listIntentionSupplierMerchandise_2.html?intentionCode=<#if merchandiseIntention.intentionCode?? >${merchandiseIntention.intentionCode}<#else></#if>"
		     data-options="rownumbers:true"> 
		   
		    <thead>
		   		<tr>
		   			<th data-options="field:'supplierAddress'" hidden="true"></th>
		   			<th data-options="field:'NULL',checkbox:true"></th>
		   			<th data-options="field:'intentionSupplierCode',width:100">
						<span >供应商编号</span>
					</th>
					<th data-options="field:'intentionSupplierName',width:100">
						<span >供应商名称</span>
					</th>
					<th data-options="field:'isForetastePass',width:100">
						<span >是否试吃通过</span>
					</th>
					<th data-options="field:'foretastePassDate',width:100">
						<span >试吃通过日期</span>
					</th>
		   		</tr>
		   	</thead>
	</table> 
	
	<#-- 已录入的试吃反馈 -->
	<div style="padding: 15px;"></div>
	<div>
	 	<table >
	 		<tr>
				<td>
					<a id="showUpdateForetaste" onclick="feedbackFn.showUpdateForetaste();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >修改</a>
				</td>
				<td>
					<a id="deleteForetaste" onclick="feedbackFn.deleteForetaste();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" <#if merchandiseIntention.intentionCode?? >enabled<#else>disabled</#if> >删除</a>
				</td>
	 		</tr>
	 	</table>
	 </div>	
	 <table id="panel6ForetasteGrid"
		     class="easyui-datagrid"
		     title="已录入的试吃反馈" 
		     singleSelect="true"
		     checkOnSelect="false"
	   		 selectOnCheck="false"
			 pagination = "true"
			 pagePosition = 'bottom'
			 pageSize = "5"
			 style="height:250px"
			 pageList = "[ 5, 10, 15, 20 ]"
			 fitColumns="true" 
		     nowrap="true"
			 toolbar="#panel6_ForetasteToolbar"
			 url="foretasteFeedback_listForetasteFeedback_2.html?intentionCode=<#if merchandiseIntention.intentionCode?? >${merchandiseIntention.intentionCode}<#else></#if>"
		     data-options="rownumbers:true"> 
		   
		    <thead>
		   		<tr>
		   			<th data-options="field:'feedbackCode'" hidden="true"></th>
		   			<th data-options="field:'NULL',checkbox:true"></th>
		   			<th data-options="field:'intentionSupplierCode',width:120">
						<span >供应商编号</span>
					</th>
					<th data-options="field:'intentionSupplierName',width:130">
						<span >供应商名称</span>
					</th>
					<th data-options="field:'foretasteDate',width:100">
						<span >试吃反馈日期</span>
					</th>
					<th data-options="field:'foretasteNumber',width:100,align:'right'">
						<span style="right">试吃人数</span>
					</th>
					<th data-options="field:'foretasteFull',width:100,align:'right'">
						<span style="right">试吃满分</span>
					</th>
					<th data-options="field:'foretasteGrade',width:100,precision:2,align:'right',formatter:moneyFormatter">
						<span style="right">试吃评分</span>
					</th>
					<th data-options="field:'evaluate',width:100">
						<span >试吃评价</span>
					</th>
					
		   		</tr>
		   	</thead>
	</table> 
</body>	
</#compress>