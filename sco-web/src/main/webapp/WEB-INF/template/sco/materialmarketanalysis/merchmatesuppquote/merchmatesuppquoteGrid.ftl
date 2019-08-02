<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/common/masterDataType.js" />
		<#include "sco/materialmarketanalysis/merchmatesuppquote/merchmatesuppquote.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body style="margin: 0px;">
	<div id="merMatSupQuo_toolbar" style="padding:1px;overflow:auto;">
		<form id="merMatSupQuo_search" method="post">
			<table style="width:900px;" border="0">
				<tr>
					<td style="text-align:right;width:100px">供应商编号：</td>
					<td style="width:110px;">
						<input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width:135px;" />
					</td>
					<td style="text-align:right;width:90px">供应商名称：</td>
					<td style="width:110px;">
							<input class="easyui-validatebox" filterName="supplierName" id="supplierName" style="width:120px;" />
						</td>
					<td style="text-align:right;width:77px">商品编号：</td>
					<td style="width:110px;">
						<input class="easyui-validatebox" filterName="merchandiseCode" id="merchandiseCode" style="width:120px;" />
					</td>
					<td style="text-align:right;width:77px">商品名称：</td>
					<td>
						<input class="easyui-validatebox" filterName="merchandiseName" id="merchandiseName" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">商品定性角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width: 139px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'merchandiseRole_listQualitative_5.html'
					    " />
					</td>
					<td style="text-align:right;">商品定量角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dlRoleCode" id="dlRoleCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'merchandiseRole_listQuantify_5.html'
					    " />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;width:73px">中分类：</td>
					<td>
						<input class="filterSelect" id="centreTypeCode" filterName="centreType" style="width: 139px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " />
					</td> 
					<td style="text-align:right;width:73px">小分类：</td>
					<td>
						<input class="filterSelect" id="smallTypeCode" filterName="smallType" style="width: 124px;"
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false" 
						/>
					</td>
					<td style="text-align:right;width:73px">明细类：</td>
					<td>
						<input class="filterSelect" id="detailTypeCode" filterName="detailType" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									panelHeight:'220',
									editable:false" />
					</td>
					<td style="text-align:right;width:73px">细分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineType" style="width: 124px;"
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false" 
						/>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">商品原料类型：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="materialType" filterName="materialType" style="width: 139px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'relevanceMaterialAndWebsite_listMerchandiseMaterialType_5.html'
					    " />
					</td>
					<td style="text-align:right;">商品原料名称：</td>
					<td>
						<input class="easyui-validatebox" id="ingredientCodeName" filterName="ingredientCodeName" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;"><font color="red">*</font>价格时间范围：</td>
					<td colspan="2">
						<select style="width:60px" id="startYear" filterName="startYear" onchange="merMatSupQuoGridFn.selectStartYear(this.value)">
							<#list 1999..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						至
						<select style="width:60px" id="endYear" filterName="endYear" onchange="merMatSupQuoGridFn.selectEndYear(this.value)">
							<#list 1999..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a onclick="merMatSupQuoGridFn.searchMerMate();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.materialmarketAnalysis.merchmatesuppquote.listMerMat")>none</#if>;">
							搜索商品原料
						</a>
						<a onclick="merMatSupQuoGridFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<span style="padding-left: 66px;"></span>
						<a id="link" onclick="merMatSupQuoGridFn.listMerMateHisPrice()" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.materialmarketAnalysis.merchmatesuppquote.listMerMat")>none</#if>;">
							查看商品原料历史价格
						</a>
						<a onclick="merMatSupQuoGridFn.exportMerMat()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.materialmarketAnalysis.merchmatesuppquote.exportMerMat")>none</#if>;">
							导出Excel
						</a>
						<a onclick="merMatSupQuoGridFn.saveData()" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.materialmarketAnalysis.merchmatesuppquote.saveData")>none</#if>;">
							保存
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="height:450px">
	    <table  id="merMatSupQuo_grid"
			    fit="true"
			    iconCls= "icon-save"
				pagination = "true"
				pagePosition = 'bottom'
				pageSize = "20"
				pageList = "[ 10, 20, 30, 40 ]"
				toolbar="#merMatSupQuo_toolbar"
				url='merchMateSuppQuote_listMerchdiseMaterial_2.html'
			    data-options="rownumbers:true">  
	        <thead>
	        	<tr>
	        		<th rowspan="2" data-options="field:'NULL',checkbox:'true'">
					<th rowspan="2" data-options="field:'merchandiseCode',sortable:true,width:180">
	                	商品编号
	                </th>
					<th rowspan="2" data-options="field:'merchandiseName',width:220">
	                	商品名称
	                </th>
					<th rowspan="2" data-options="field:'supplierCode',sortable:true,width:180">
	                	供应商编号
	                </th>
					<th rowspan="2" data-options="field:'supplierName',width:220">
	                	供应商名称
	                </th>
					<th rowspan="2" data-options="field:'dxRoleName',width:200">
						商品定性角色
	                </th>
					<th rowspan="2" data-options="field:'dlRoleName',width:200">
						商品定量角色
	                </th>
					<th rowspan="2" data-options="field:'centreName',width:200">
	                	中分类
	                </th>
					<th rowspan="2" data-options="field:'smallName',width:200">
	                	小分类
	                </th>
					<th rowspan="2" data-options="field:'detailName',width:200">
	                	明细类
	                </th>
					<th rowspan="2" data-options="field:'fineName',width:200">
	                	细分类
	                </th>
					<th rowspan="2" data-options="field:'materialTypeName',width:100">
	                	商品原料类型
	                </th>
					<th rowspan="2" data-options="field:'ingredientCodeName',width:200">
	                	商品原料名称
	                </th>
	            </tr>
	        </thead>
	    </table>
	</div>
	
	<#-- 保存文件对话框 -->
	<div class="easyui-dialog" id="merMatSaveFileDlg" style="width:270px;height:160px" 
		data-options="
			title:'保存分析结果',
			closable:false,closed:true,modal:true,buttons:[
					{text:'确定',id:'saveFile',iconCls:'save',handler:function(){merMatSupQuoGridFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){merMatSupQuoGridFn.closeSaveFileDlg()}}
			]
	">
		<br/><br/>
		<span class="txtCenter">文件名：<font color="red">*</font><input id="merMatFileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/></span>		
	</div>
	
	<#-- 间隔层 -->
	<div style="height:20px"></div>
    <div id="merDataTab"></div>
</body>
</html>
</#compress>