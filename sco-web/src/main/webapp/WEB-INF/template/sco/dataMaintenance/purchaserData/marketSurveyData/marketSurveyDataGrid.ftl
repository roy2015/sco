<#compress>
<!DOCTYPE html>  
<html>  
	<head>  
	    <meta charset="UTF-8">  
	    <#include "inc/easyui.ftl" />
	    <script type="text/javascript" >
			<#include "sco/dataMaintenance/purchaserData/marketSurveyData/marketSurveyData.js" />
			<#include "sco/common/masterDataType.js" />
	    </script>
	    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
	</head>

	<body>
		<!-- 竞品价格 维护主表 --> 
		<div id="marketSurveyData_toolbar" style="margin:5px 5 5px 5;padding:2px;overflow:auto;">
			<form id="signedQty_search">
				<table style="width:950px;" border="0">
					<tr>
						<td style="text-align:right;width:80px">
							供应商编号：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width:120px;" />
						</td>
						<td style="text-align:right;width:70px">
							供应商名称：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" filterName="supplierName" id="supplierName" style="width:120px;" />
						</td>
						<td style="text-align:right;width:80px">
							卖场名称：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" id="jpmcName" filterName="jpmcName" style="width:120px;" />
						</td>
						<td style="text-align:right;width:70px">
							卖场地区：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" id="mcRegion" filterName="mcRegion" style="width:120px;" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;width:77px">
							商品编号：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" filterName="merchandiseCode" id="merchandiseCode" style="width:120px;" />
						</td>
						<td style="text-align:right;width:77px">
							&nbsp商品名称：
						</td>
						<td>
							<input class="easyui-validatebox" filterName="merchandiseName" id="merchandiseName" style="width:120px;" />
						</td>
						<td style="text-align:right;width:79px;">商品定性角色：</td>
						<td>
							<input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width: 124px;" 
								data-options="
									valueField:'id',
									textField:'text',
									panelHeight:'220',
									editable:false,
								    url:'merchandiseRole_listQualitative_5.html'
						    " />
						</td>
						<td style="text-align:right;width:79px;">商品定量角色：</td>
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
							<input class="filterSelect" filterName="centreType" id="centreTypeCode" style="width: 124px;" 
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
						<td style="text-align:right;width:79px;">
							市调价格日期：
						</td>
						<td>
							<input class="easyui-datebox list-input" filterName="marStartDate" id="minMarketSurveyDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;"/>
						</td>
						<td style="text-align:right;">
							至：
						</td>
						<td colspan="5">
							<input class="easyui-datebox list-input" filterName="marEndDate" id="maxMarketSurveyDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="8">
							<a onclick="marketDataFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
								查看市调数据记录
							</a>
							<a onclick="marketDataFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
								清空查询
							</a>
							<a id="upload" onclick="marketDataFn.showUpload();"plain="true" class="easyui-linkbutton" data-options="iconCls:'upload'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData.upload")>none</#if>;">
								上传市调表
							</a>
							<a id="remove" onclick="marketDataFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData.delete")>none</#if>;">
								删除市调记录
							</a>
							<a onclick="marketDataFn.export2Excel();"plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData.export")>none</#if>;">
								导出Excel
							</a>
							<a id="save" onclick="marketDataFn.saveFile();"plain="true" class="easyui-linkbutton" data-options="iconCls:'save'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData.save")>none</#if>;">
								保存
							</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	    <table  id="marketSurveyData_grid"
			    fit="true"
			    iconCls= "icon-save"
				pagination = "true"
				pagePosition = 'bottom'
				pageSize = "20"
				pageList = "[ 10, 20, 30, 40 ]"
				toolbar="#marketSurveyData_toolbar"
				url='marketSurveyData_listMarketSurveyData_2.html'
			    data-options="rownumbers:true">  
	        <thead>
	        	<tr>
					<th rowspan="2" data-options="field:'NULL',width:75,checkbox:true">
	                </th>
					<th rowspan="2" data-options="field:'merchandiseCode',width:130,sortable:true">
	                	商品编号
	                </th>
					<th rowspan="2" data-options="field:'merchandiseName',width:220">
	                	商品名称
	                </th>
					<th rowspan="2" data-options="field:'supplierCode',width:130,sortable:true">
	                	供应商编号
	                </th>
					<th rowspan="2" data-options="field:'supplierName',width:220">
	                	供应商名称
	                </th>
					<th rowspan="2" data-options="field:'dxRoleName',width:120">
	                	商品定性角色
	                </th>
					<th rowspan="2" data-options="field:'dlRoleName',width:120">
	                	商品定量角色
	                </th>
					<th rowspan="2" data-options="field:'centreName',width:120">
	                	中分类
	                </th>
					<th rowspan="2" data-options="field:'smallName',width:120">
	                	小分类
	                </th>
					<th rowspan="2" data-options="field:'detailName',width:120">
	                	明细类
	                </th>
					<th rowspan="2" data-options="field:'fineName',width:120">
	                	细分类
	                </th>
					<th rowspan="2" data-options="field:'sellPrice',align:'right',width:100,sortable:true,formatter:moneyFormatter">
	                	商品售价（元）
	                </th>
					<th rowspan="2" data-options="field:'storageForm',width:100,sortable:true">
	                	价格单位
	                </th>
					<th rowspan="2" data-options="field:'netWeight',align:'right',width:100,formatter:moneyFormatter">
	                	净含量（kg）
	                </th>
					<th rowspan="2" data-options="field:'discountPrice',width:100,align:'right',formatter:marketDataFn.discountPrice">
	                	折后公斤价（元）
	                </th>
					<th rowspan="2" data-options="field:'jpmcName',width:200">
	                	卖场名称
	                </th>
					<th rowspan="2" data-options="field:'mcRegion',width:120">
	                	卖场地区
	                </th>
					<th rowspan="2" data-options="field:'jpPriceUnits',width:130,sortable:true">
	                	竞品价格单位（元/kg）
	                </th>
					<th rowspan="2" data-options="field:'jpContent',align:'right',width:130,formatter:moneyFormatter">
	                	竞品净含量（kg）
	                </th>
					<th rowspan="2" data-options="field:'marketSurveyDate',width:100,sortable:true">
	                	市调价格日期
	                </th>
					<th rowspan="2" data-options="field:'marketSurveyPrice',width:100,align:'right',sortable:true,formatter:moneyFormatter">
	                	市调售价（元）
	                </th>
					<th rowspan="2" data-options="field:'mdiscountPrice',width:120,align:'right',formatter:marketDataFn.mdiscountPrice">
	                	竞品折后公斤价（元）
	                </th>
	            </tr>
	        </thead>
	    </table>
	    
		<div class="easyui-dialog" id="saveFileDlg" style="width:270px;height:160px" 
			data-options="
				title:'请输入文件名称',
				closable:false,closed:true,modal:true,buttons:[
						{text:'确定',id:'saveFile',iconCls:'save',handler:function(){marketDataFn.submitSaveFileDlg()}},
						{text:'关闭',iconCls:'close',handler:function(){marketDataFn.closeSaveFileDlg()}}
				]
		">
			<br/><br/>
			<span class="txtCenter"><input id="fileName" class="easyui-validatebox" data-options="validType:'validateVarName'" style="width:190px;"/></span>		
		</div>
	</body>
</html>
</#compress>