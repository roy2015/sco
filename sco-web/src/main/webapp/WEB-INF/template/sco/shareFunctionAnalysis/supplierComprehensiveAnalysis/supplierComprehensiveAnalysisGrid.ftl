<#compress>
<!DOCTYPE html>  
<html>  
	<head>  
	    <meta charset="UTF-8">  
	    <#include "inc/easyui.ftl" />
	     <link rel="stylesheet"  href="${copoxtPath}css/table.min.css" type="text/css" />
	    <script type="text/javascript" >
			<#include "sco/shareFunctionAnalysis/supplierComprehensiveAnalysis/supplierComprehensiveAnalysisModel.js" />
	    </script>
	</head>
	<body>
		<!-- 辅料意向品管理主表 --> 
		<div id="supplierComprehensiveAnalysis_toolbar">
			<form id="supplierComprehensiveAnalysis_search">
				<table style="width: 950px;">
				  	<tr>
				  		<td align="right">供应商编号:</td>
						<td >
							<input class="easyui-validatebox" filterName="supplierCode" type="text" name="supplierCode" id="supplierCode" style="width:120px;" />
						</td>  
						<td align="right">供应商名称:</td>   
						<td>       
							<input class="easyui-validatebox" filterName="supplierName" type="text" name="supplierName" id="supplierName" style="width:120px;" />		  
						</td> 
						<td align="right">商品编号:</td>
						<td>
							<input class="easyui-validatebox" filterName="goodsCode" type="text" name="goodsCode" id="goodsCode" style="width:120px;" />
						</td>
						<td align="right">商品名称:</td>
						<td>     
						    <input class="easyui-validatebox" filterName="goodsName" type="text" name="goodsName" id="goodsName" style="width:120px;" />
						</td>
				   </tr>
				</table>
			</form>	
			<table class="table table-condensed" style="width:1060px;margin-bottom:5px">
				<a onclick="supplierComprehensiveAnalysisFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
					搜索
				</a>
				<a onclick="supplierComprehensiveAnalysisFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
					清空查询
				</a>
				<a id="insert" onclick="supplierComprehensiveAnalysisFn.showSupplierComprehensiveAnalysis();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.sharefunctionanalysis.supplierComprehensiveAnalysis")>none</#if>;">
					综合分析
				</a>
			</table>	
		</div>

		<table  id="supplierComprehensiveAnalysisGrid"
			    fit="true"
			    iconCls= "icon-save"
				pagination = "true"
				pagePosition = 'bottom'
				pageSize = "20"
				pageList = "[ 10, 20, 30, 40 ]"
				toolbar="#supplierComprehensiveAnalysis_toolbar"
				url='supplierComprehensiveAnalysis_listSupplierComprehensiveAnalysis_2.html'
			    data-options="rownumbers:true">  
	        <thead>
	        	<tr>
	        		<th data-options="width:40,checkbox:true"></th>
					<th data-options="field:'supplierCode',width:150,sortable:true">
	                	<span class="txtCenter">供应商编号</span>
	                </th>
	                <th data-options="field:'supplierName',width:400,sortable:true">
	                	<span class="txtCenter">供应商名称</span>
	                </th>
				
					<th data-options="field:'supplierSite',width:400,sortable:true">
	                	<span class="txtCenter">供应商地址</span>
	                </th>
	            </tr>
	        </thead>
	    </table>
	</body>
</html>
</#compress>