<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript">
		<#include "sco/common/materialProperties.js" />
		<#include "sco/materialmarketanalysis/websitehistory/websiteHistory.js" />
    </script>
    
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>

<body style="margin: 0px;">
	<div id="webHis_toolbar" style="padding:1px;overflow:auto;border:0px">
		<form id="webHis_search" method="post">
			<table style="width:680px;" border="0">
				<tr>
					<td style="text-align:right;"><font color="red">*</font>原料大类：</td>
					<td>
						<input class="filterSelect" filterName="materialBigTypeCode" name="materialBigTypeCode" id="materialBigTypeCode" style="width:139px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
								url:'materialProperties_listMaterialBigType_5.html'
					    " />
					</td>
					<td style="text-align:right;"><font color="red">*</font>原料小类：</td>
					<td>
						<input class="filterSelect" filterName="materialSmallTypeCode" name="materialSmallTypeCode" id="materialSmallTypeCode" style="width:124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false
						" />
					</td>
					<td style="text-align:right;"><font color="red">*</font>原料名称：</td>
					<td>
						<input class="filterSelect" filterName="materialCode" name="materialCode" id="materialCode" style="width:124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false
					    " />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;"><font color="red">*</font>公示网站名称：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="websiteCode" name="websiteCode" id="webName" style="width:139px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false
					    " />
					</td>
					<td style="text-align:right;"><font color="red">*</font>地区：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="priceRegion" name="priceRegion" id="region" style="width:124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false
					    " />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;"><font color="red">*</font>价格时间范围：</td>
					<td colspan="2">
						<select style="width:60px" id="startYear" filterName="startYear" onchange="webHisFn.selectStartYear(this.value)">
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						至
						<select style="width:60px" id="endYear" filterName="endYear" onchange="webHisFn.selectEndYear(this.value)">
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">
						<label>
							<input type="checkbox" id="showChart">同时查看图表
						</label>
					</td>
					<td><input type="hidden" id="showPanel"></td>
				</tr>
				<tr>
					<td colspan="6">
						<a onclick="webHisFn.searchHisPrice();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.materialmarketAnalysis.websitehistory.listHistPrice")>none</#if>;">
							查看历史价格
						</a>
						<a onclick="webHisFn.searchMer()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.materialmarketAnalysis.websitehistory.searchMer")>none</#if>;">
							查看商品列表
						</a>
						<a onclick="webHisFn.clearSearch()" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<span style="padding-right: 91px;"></span>
						<a onclick="webHisFn.exportData()" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.materialmarketAnalysis.websitehistory.exportHistPrice")>none</#if>;">
							导出Excel
						</a>
						<a onclick="webHisFn.saveData()" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.materialmarketAnalysis.websitehistory.saveHistPrice")>none</#if>;">
							保存
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<#-- 保存文件对话框 -->
	<div class="easyui-dialog" id="weHisSaveFileDlg" style="width:270px;height:160px" 
		data-options="
			title:'保存分析结果',
			closable:false,closed:true,modal:true,buttons:[
					{text:'确定',id:'saveFile',iconCls:'save',handler:function(){webHisFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){webHisFn.closeSaveFileDlg()}}
			]
	">
		<br/><br/>
		<span class="txtCenter">文件名：<font color="red">*</font><input id="webHisFileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/></span>		
	</div>
	
	<#-- 数据 -->
	<div>
		<table id="webHis_Grid" toolbar="#webHis_toolbar"></table>
	</div>
	<div id="searchPara" style="display:none">
		<table class="table table-condensed" border="0">
			<tr>
				<td style="width:33%">原料大类：<span id="matBigType"></span></td>
			</tr>
			<tr>
				<td style="width:33%">原料小类：<span id="matSmType"></span></td>
			</tr>
			<tr>
				<td style="width:33%">原料名称：<span id="matName"></span></td>
			</tr>
			<tr>
				<td>公示网站名称：<span id="sitName"></span></td>
			</tr>
			<tr>
				<td>地区：<span id="reginName"></span></td>
			</tr>
		</table>
	</div>
	<div id="dataTab" style="overflow:auto;height:20px"></div>
</body>
</html>
</#compress>