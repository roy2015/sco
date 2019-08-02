<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/merchandiseCostAnalysis/merchandiseItemCost/merchandiseitemcost.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<!-- 商品分项成本类比主表 --> 
	<div id="merchandiseItemCost_toolbar">
		<form id="signedQty_search">
			<table class="table table-condensed" style="width: 1000px;">
				<tr>
					<td style="text-align:right;width:125px">供应商/意向供应商编号：</td>
					<td style="width:125px"><input filterName="supplierCode" type="text" name="supplierCode" id="supplierCode" data-options="required:false,editable:false" style="width:120px;" /></td>
					<td style="text-align:right;width:125px">供应商/意向供应商名称：</td>
					<td style="width:125px"><input filterName="supplierName" type="text" name="supplierName" id="supplierName" data-options="required:false,editable:false" style="width:120px;" /></td>
					<td style="text-align:right;width:125px">工厂所在地：</td>
					<td style="width:125px"><input filterName="companySite" type="text" name="companySite" id="companySite" data-options="required:false,editable:false" style="width:120px;" /></td>
					<td style="text-align:right;width:125px">OA审批状态：</td>
					<td style="width:125px">
						<input class="easyui-combobox filterSelect" filterName="applicationStatus" id="applicationStatus" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									panelHeight:'auto',
									editable:false,
								    url:'businessComBox_merchandiseApplicationStatus_5.html'
								    ">
						</input>
					</td>
				</tr>
				<tr>
				    <td align="right">商品/意向品编号：</td>
					<td><input filterName="merchandiseCode" type="text" name="merchandiseCode" id="merchandiseCode" data-options="required:false,editable:false" style="width:120px;" /></td>
					<td align="right">商品/意向品名称：</td>
					<td><input filterName="merchandiseName" type="text" name="merchandiseName" id="merchandiseName" data-options="required:false,editable:false" style="width:120px;" /></td>
					<td style="text-align:right;width:79px;">商品定性角色：</td>
					<td>
						<input class="easyui-combobox  filterSelect" filterName="merchandiseDxRoleCode" id="dxRoleCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'merchandiseRole_listQualitative_5.html'
					    " />
					</td>
					<td style="text-align:right;width:79px;">商品定量角色：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="merchandiseDlRoleCode" id="dlRoleCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'merchandiseRole_listQuantify_5.html'
					    " />
					</td>
				</tr>
				<tr>
					<td align="right">中分类：</td>
					<td>
						<input class="filterSelect" filterName="centreTypeCode" id="centreTypeCodeElse" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'masterDataType_listCentreType_5.html'
								    ">
						</input>
					</td>
					<td align="right">小分类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false">
						</input>
					</td>
					<td align="right">明细类：</td>
					<td>
						<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false">
						</input>
					</td>
					<td align="right">细分类：</td>
					<td>
						<input class="easyui-combobox  filterSelect" class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false">
						</input>
					</td>
				</tr>
				<tr>
					<td align="right">采购部门：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="purchaseDepartments" id="purchaseDepartments" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'businessComBox_procurementDepartments_5.html'
								    ">
						</input>
					</td>
					<td align="right">是否定量：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="rationed" id="rationed" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'businessComBox_rationed_5.html'
								    ">
						</input>
					</td>
					<td align="right">采购类型：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="purchaseType" id="purchaseType" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'businessComBox_purchaseType_5.html'
								    ">
						</input>
					</td>
					<td align="right">销售方式：</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="saleType" id="saleType" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'masterDataType_listSaleType_5.html'
								    ">
						</input>
					</td>
				</tr>
			</table>
			<div>
			    <input type="radio" checked="checked" name="item" value="material"/>原材料&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="item" value="npackag"/>内包装&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="item" value="wpackag"/>外包装&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="item" value="elsecost"/>其他成本&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="item" value="importcost"/>进口相关成本
				<div name="subItem" style="height:30px;vertical-align:middle;" id="material">
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					  原料名称：<input style="width: 120px; height: 17px; line-height: 20px;" name="materialName" id="materialName" filterName="materialName"/>&nbsp;&nbsp;&nbsp;&nbsp;
					   产地：<input style="width: 120px; height: 17px; line-height: 20px;" name="materialOrigin" id="materialOrigin" filterName="materialOrigin"/>&nbsp;&nbsp;&nbsp;&nbsp;
					   品牌：<input style="width: 120px; height: 17px; line-height: 20px;" name="materialBrand" id="materialBrand" filterName="materialBrand"/>  
				</div>
				<div name="subItem" id="npackag" style="height:30px;display:none">
				    <font color="red">*</font>内包装名称：<input class="easyui-combobox filterSelect" filterName="npackagName" id="npackagName" style="width: 124px;" 
				   					data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'businessComBox_merchandiseNPackag_5.html?noW=yes'
								    ">
						</input>
				</div>
				<div name="subItem" id="wpackag" style="height:30px;display:none">
				<font color="red">*</font>外包装名称：<input class="easyui-combobox filterSelect" filterName="wpackagName" id="wpackagName" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'businessComBox_merchandiseWPackag_5.html?noW=yes'
								    ">
						</input>
				</div>
				<div name="subItem" id="elsecost" style="height:30px;display:none">
				<font color="red">*</font>其他成本名称：<input class="easyui-combobox filterSelect" filterName="elseCost" id="elseCost" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'businessComBox_elseCost_5.html'
								    ">
						</input>
				</div>
				<div name="subItem" id="importcost" style="height:30px;display:none">
				<font color="red">*</font>进口相关成本名称：<input class="easyui-combobox filterSelect" filterName="importCost" id="importCost" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									editable:false,
								    url:'businessComBox_importRelevantCost_5.html'
								    ">
						    </input>
				</div>
		      </div>
		      <div>
		       <input style="height:22px" type="radio" checked="checked" name="cost" value="lastcost"/>只查看最后一次审批通过的成本</br>
		       <input type="radio" name="cost" value="historycost"/>查看历史成本&nbsp;&nbsp;&nbsp;&nbsp;
		          <div style="display:inline;height:20px" name="cost" id="historycost">
		             <font color="red">*</font>报价日期： <input class="easyui-datebox" filterName="minQuotedDate" name="minQuotedDate" id="minQuotedDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" /> 
					 至 ：<input class="easyui-datebox" filterName="maxQuotedDate" name="maxQuotedDate" id="maxQuotedDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" />
		          </div>
		      </div>
		<div style="margin:5px">
			<a id="search" onclick="merchandiseItemCostFn.search();" plain="true" class="easyui-linkbutton" data-options="iconCls:'find'"> 搜索 </a>
			<a id="clear" onclick="merchandiseItemCostFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a>
			<a id="excel" onclick="merchandiseItemCostFn.export2Excel();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'"> 导出Excel </a>
			<a id="save" onclick="merchandiseItemCostFn.saveFile();" plain="true" class="easyui-linkbutton" data-options="iconCls:'save'"> 保存 </a>
		</div>
	</div>
   </form>
	</div>
	<div id="grid" style="width:100%;height:100%;">
      <table  id="merchandiseItemCost_grid">
    </div>
    <div class="easyui-dialog" id="saveFileDlg" style="width:320px;height:160px" 
		data-options="
			title:'请输入文件名称',
			closable:false,closed:true,modal:true,buttons:[
					{id:'dlg_save',text:'确定',iconCls:'save',handler:function(){merchandiseItemCostFn.submitSaveFileDlg()}},
					{text:'关闭',iconCls:'close',handler:function(){merchandiseItemCostFn.closeSaveFileDlg()}}]">
		<br/><br/>
		<div style="margin-left:25px">文件名称：<input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:200px;"/></div>		
    </div>
</body>
</html>
</#compress>