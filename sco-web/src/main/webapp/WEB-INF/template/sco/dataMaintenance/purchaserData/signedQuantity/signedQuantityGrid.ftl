<#compress>
<!DOCTYPE html>  
<html>  
	<head>  
	    <meta charset="UTF-8">  
	    <#include "inc/easyui.ftl" />
	    <script type="text/javascript" >
			<#include "sco/dataMaintenance/purchaserData/signedQuantity/signedQuantity.js" />
	  		<#include "sco/common/masterDataType.js" />
	    </script>
	    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
	</head>

	<body>
		<#-- 工具栏 -->
		<div id="signedQuantity_toolbar" style="margin:5px 5 5px 5;padding:3px;overflow:auto;">
			<form id="signedQty_search">
				<table style="width:950px;" border="0">
					<#-- 查询条件 -->
					<tr>
						<td style="text-align:right;width:90px">
							供应商编号：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width:120px;" />
						</td>
						<td style="text-align:right;width:81px">
							供应商名称：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" filterName="supplierName" id="supplierName" style="width:120px;" />
						</td>
						<td style="text-align:right;width:94px">
							商品编号：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" filterName="merchandiseCode" id="merchandiseCode" style="width:120px;" />
						</td>
						<td style="text-align:right;width:94px">
							&nbsp商品名称：
						</td>
						<td>
							<input class="easyui-validatebox" filterName="merchandiseName" id="merchandiseName" style="width:120px;" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">中分类：</td>
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
						<td style="text-align:right;">小分类：</td>
						<td>
							<input class="filterSelect" id="smallTypeCode" filterName="smallType" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									panelHeight:'220',
									editable:false" 
							/>
						</td>
						<td style="text-align:right;">明细类：</td>
						<td>
							<input class="filterSelect" id="detailTypeCode" filterName="detailType" style="width: 124px;"
									data-options="
										valueField:'id',
										textField:'text',
										panelHeight:'220',
										editable:false" />
						</td>
						<td style="text-align:right;">细分类：</td>
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
						<td style="text-align:right;">
							签量单号：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" id="qlCode" filterName="qlCode" style="width:120px;" />
						</td>
						<td style="text-align:right;">
							签量人：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" id="qlCreateby" filterName="qlCreateby" style="width:120px;" />
						</td>
						<td style="text-align:right;">
							签量单状态：
						</td>
						<td>
							<select style="width:124px" name="qlStatus" id="qlStatus" filterName="qlStatus">
								<option value=""></option>
								<option value="ALL">所有</option>
								<#list qlStatusList as list>
									<option value="${list.id}">${list.text}</option>
								</#list>
							</select>
						</td>
						<td style="text-align:right;">改签单号：</td>
						<td>
							<input class="easyui-validatebox" id="gqCode" filterName="gqCode" style="width:120px;" />
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">
							签量开始日期：
						</td>
						<td>
							<input class="easyui-datebox list-input" filterName="qlStartDate" id="minUpdatedDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;"/>
						</td>
						<td style="text-align:right;">至：</td>
						<td>
							<input class="easyui-datebox list-input" filterName="qlEndDate" id="maxUpdatedDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;"/>
						</td>
						<td style="text-align:right;">商品定性角色：</td>
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
						<td colspan="8">
							是否优先满足开始日期较早的签量记录：&nbsp;&nbsp;
							<select filterName="isSatisfyBeforeQl" name="isSatisfyBeforeQl" id="isSatisfyBeforeQl" style="width:60px;">
		                		<option value=""></option>
		                		<option value="ALL">全部</option>
								<option value="Y">是</option>
								<option value="N">否</option>
							</select>
						</td>
					</tr>
					<#-- 查询按钮 -->
					<tr>
						<td colspan="9">
							<a onclick="signedQuantityFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
								搜索
							</a>
							<a onclick="signedQuantityFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
								清空查询
							</a>
							<a id="insert" onclick="signedQuantityFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add',disabled:'true'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.add")>none</#if>;">
								新增签量
							</a>
							<a id="gq" onclick="signedQuantityFn.showGqEdit();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.gq")>none</#if>;">
								改签
							</a>
							<a id="edit" onclick="signedQuantityFn.showEdit();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.edit")>none</#if>;">
								修改
							</a>
							<a id="remove" onclick="signedQuantityFn.remove();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.del")>none</#if>;">
								删除
							</a>
							<a id="ter" onclick="signedQuantityFn.ter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'close',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.terQL")>none</#if>;">
								终止签量
							</a>
							<a id="rev" onclick="signedQuantityFn.revokeTer();" plain="true" class="easyui-linkbutton" data-options="iconCls:'refresh',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.revokeTer")>none</#if>;">
								撤销终止
							</a>
							<a onclick="signedQuantityFn.exportToExcel();" plain="true" class="easyui-linkbutton" data-options="iconCls:'excel'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.signedQuantity.export")>none</#if>;">
								导出Excel
							</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<#-- 数据表格 -->
	    <table  id="signedQuantity_grid"
			    fit="true"
			    iconCls= "icon-save"
				pagination = "true"
				pagePosition = 'bottom'
				pageSize = "20"
				pageList = "[ 10, 20, 30, 40 ]"
			    url='signedQuantity_listSignedQuantity_2.html'
				toolbar="#signedQuantity_toolbar"
			    data-options="rownumbers:true">  
	        <thead>
	        	<tr>
	        		<th rowspan="2" data-options="field:'NULL',checkbox:true"></th>
					<th rowspan="2" data-options="field:'qlCode',width:120,sortable:true">
	                	签量单号
	                </th>
					<th rowspan="2" data-options="field:'qlCreated',width:110,sortable:true">
	                	签量单创建日期
	                </th>
					<th rowspan="2" data-options="field:'merchandiseCode',width:140,sortable:true">
	                	商品编号
	                </th>
					<th rowspan="2" data-options="field:'merchandiseName',width:220,sortable:false">
	                	商品名称
	                </th>
					<th rowspan="2" data-options="field:'supplierCode',width:140,sortable:true">
	                	供应商编号
	                </th>
					<th rowspan="2" data-options="field:'supplierName',width:220,sortable:false">
	                	供应商名称
	                </th>
					<th rowspan="2" data-options="field:'dxRoleName',width:120,sortable:false">
	                	商品定性角色
	                </th>
					<th rowspan="2" data-options="field:'dlRoleName',width:120,sortable:false">
	                	商品定量角色
	                </th>
					<th rowspan="2" data-options="field:'centreName',width:120,sortable:false">
	                	中分类
	                </th>
					<th rowspan="2" data-options="field:'smallName',width:120,sortable:false">
	                	小分类
	                </th>
					<th rowspan="2" data-options="field:'detailName',width:120,sortable:false">
	                	明细类
	                </th>
					<th rowspan="2" data-options="field:'fineName',width:120,sortable:false">
	                	细分类
	                </th>
					<th rowspan="2" data-options="field:'xPrice',width:120,sortable:false,align:'right',formatter:moneyFormatter">
	                	X001进价（元/kg）
	                </th>
					<th rowspan="2" data-options="field:'qlStartDate',width:120,sortable:true">
	                	签量开始日期
	                </th>
					<th rowspan="2" data-options="field:'qlPrice',width:120,sortable:false,align:'right',formatter:moneyFormatter">
	                	签量价格 （元/kg）
	                </th>
					<th rowspan="2" data-options="field:'qlCount',width:120,sortable:false,align:'right',formatter:moneyFormatter">
	                	签订数量（kg）
	                </th>
					<th rowspan="2" data-options="field:'proCount',width:120,sortable:false,align:'right',formatter:moneyFormatter">
	                	已完成量（kg）
	                </th>
					<th rowspan="2" data-options="field:'proPercent',width:120,sortable:true,align:'right',formatter:signedQuantityFn.formatPercent">
	                	已完成百分比
	                </th>
					<th rowspan="2" data-options="field:'finshDate',width:120,halign:'left',sortable:true">
	                	签量满足日期
	                </th>
					<th rowspan="2" data-options="field:'beyondCount',width:120,sortable:false,align:'right',formatter:moneyFormatter">
	                	超出量（kg）
	                </th>
					<th rowspan="2" data-options="field:'qlStatus',width:120,sortable:false">
	                	签量单状态
	                </th>
					<th rowspan="2" data-options="field:'isSatisfyBeforeQl',width:220,sortable:true">
	                	是否优先满足开始日期较早的签量记录
	                </th>
					<th rowspan="2" data-options="field:'gqCode',width:120,sortable:true">
	                	改签单号
	                </th>
					<th rowspan="2" data-options="field:'qlCreateby',width:120,sortable:false">
	                	签量人
	                </th>
					<th rowspan="2" data-options="field:'remarks',width:120,sortable:false">
	                	备注
	                </th>
	            </tr>
	         </thead>
	    </table>
	</body>
</html>
</#compress>