<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/categoryanalysis/customtypeprice/customCategoryPrice.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
    
    <script type="text/javascript" src="js/easyui/datagrid-filter.js"></script>
</head>

<body class="easyui-layout">
	<#-- 显示错误消息 -->
	<div class="easyui-dialog" id="msgDlg" style="width:665px;height:400px" 
		data-options="
			title:'提示',
			closable:false,closed:true,modal:true,buttons:[
					{
						text:'关闭',iconCls:'close',
						handler:function(){
							cuCatPriceFn.closeMsgDlg();
						}
					}
			]
	">
		<textArea id="cpErrMsg" readonly="true" style="margin: 0px; width: 630px; height: 300px;border:0;resize: none;padding-left: 13px"></textArea>
	</div>
	
    <div data-options="region:'center'">
    	<#-- 工具栏 -->
		<div id="cusPrice_toolbar" style="padding:1px;overflow:auto;">
			<form id="cusPrice_search">
				<table style="width:838px;" border="0">
					<#-- 查询条件 -->
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
					</tr>
					<tr>
						<td style="text-align:right;width:77px">
							商品编号：
						</td>
						<td style="width:110px;">
							<input class="easyui-validatebox" filterName="merchandiseCode" id="merchandiseCode" style="width:120px;" />
						</td>
						<td style="text-align:right;width:77px">
							商品名称：
						</td>
						<td>
							<input class="easyui-validatebox" filterName="merchandiseName" id="merchandiseName" style="width:120px;" />
						</td>
						<td style="width: 90px;text-align:right">商品定性角色：</td>
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
						<td style="width: 90px;text-align:right">商品定量角色：</td>
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
							<input class="filterSelect" filterName="centreTypeCode" id="centreTypeCode" style="width: 124px;" 
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
							<input class="filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									panelHeight:'220',
									editable:false" 
							/>
						</td>
						<td style="text-align:right;width:73px">明细类：</td>
						<td>
							<input class="filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
									data-options="
										valueField:'id',
										textField:'text',
										panelHeight:'220',
										editable:false" />
						</td>
						<td style="text-align:right;width:73px">细分类：</td>
						<td>
							<input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									panelHeight:'220',
									editable:false" 
							/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 77px">采购部门：</td>
						<td>
							<input class="easyui-combobox filterSelect" id="purchaseDepartments" name="purchaseDepartments" filterName="purchaseDepartments" style="width: 124px;"
								data-options="
								valueField:'text',
								textField:'text',
								panelHeight:'220',
								editable:false,
								url:'businessComBox_procurementDepartments_5.html'" />
						</td>
						<td colspan="4">
							<label style="padding-left: 44px;"><font color="red">*</font>&nbsp;<input type="radio" id="rationed" filterName="rationed" name="rationed" checked="checked" value="gjz" />&nbsp;&nbsp;只看非定量装商品</label>
							<label style="padding-left: 76px;"><input type="radio" name="rationed" value="dlz"/>&nbsp;&nbsp;只看定量装商品</label>
						</td>
					</tr>
					<tr>
						<td colspan="6" >
							&nbsp;&nbsp;
							<a onclick="cuCatPriceFn.searchMer();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.customCategoryPrice.searchMerchandise")>none</#if>;">
								搜索
							</a>
							<a onclick="cuCatPriceFn.clearSearch();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
								清空查询
							</a>
							<a onclick="cuCatPriceFn.addMer();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.customCategoryPrice.addMerchandise")>none</#if>;">
								添加到已选择商品列表
							</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<#-- 数据表格 -->
		<div style="height:456px;">
			<table  id="cusCatPriceGrid"
				    fit="true"
				    iconCls= "icon-save"
					pagination = "true"
					pagePosition = 'bottom'
					pageSize = "20"
					pageList = "[ 20, 30, 40, 50 ]"
				    url='customTypePrice_listMerchandise_2.html'
					toolbar="#cusPrice_toolbar"
				    data-options="rownumbers:true">   
		        <thead>
		        	<tr>
						<th rowspan="2" data-options="field:'NULL',checkbox:true"></th>
						<th rowspan="2" data-options="field:'merchandiseCode',width:150,sortable:true">
		                	商品编号
		                </th>
						<th rowspan="2" data-options="field:'merchandiseName',width:220">
		                	商品名称
		                </th>
		                <th rowspan="2" data-options="field:'supplierCode',width:120,sortable:true">
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
						<th rowspan="2" data-options="field:'centreTypeName',width:120">
		                	中分类
		                </th>
						<th rowspan="2" data-options="field:'smallTypeName',width:120">
		                	小分类
		                </th>
						<th rowspan="2" data-options="field:'detailTypeName',width:120">
		                	明细类
		                </th>
						<th rowspan="2" data-options="field:'fineTypeName',width:120">
		                	细分类
		                </th>
		            </tr>
		        </thead>
		    </table>
		</div>
		
		<#-- 已添加的商品 -->
		<div id="added_toolbar" style="margin:5px 5 5px 5;padding:3px">
			<table>
				<tr>
					<td>
						<a onclick="cuCatPriceFn.clearMerchandise();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<a onclick="cuCatPriceFn.delMerchandise();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.customCategoryPrice.delMerchandise")>none</#if>;">
							从列表中移除
						</a>
					</td>
				</tr>
			</table>
		</div>
		<div style="height:420px;">
			<table
				id="addedMerchandiseGrid"
				title="已选择商品列表"
				fit="true"
				toolbar="#added_toolbar"
				data-options="rownumbers:true,
				remoteSort:false"
			>
				<thead>
		        	<tr>
						<th rowspan="2" data-options="field:'NULL',checkbox:true"></th>
						<th rowspan="2" data-options="field:'merchandiseCode',width:150,sortable:true">
		                	商品编号
		                </th>
						<th rowspan="2" data-options="field:'merchandiseName',width:220">
		                	商品名称
		                </th>
		                <th rowspan="2" data-options="field:'supplierCode',width:120,sortable:true">
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
						<th rowspan="2" data-options="field:'centreTypeName',width:120">
		                	中分类
		                </th>
						<th rowspan="2" data-options="field:'smallTypeName',width:120">
		                	小分类
		                </th>
						<th rowspan="2" data-options="field:'detailTypeName',width:120">
		                	明细类
		                </th>
						<th rowspan="2" data-options="field:'fineTypeName',width:120">
		                	细分类
		                </th>
		            </tr>
		        </thead>
		        <thead>
					<tr></tr>
		        </thead>
	    	</table>
		</div>
		<div>
			<table border="0" style="height:120px;">
				<tr>
					<td style="padding-left: 10px;" colspan="6"><b>价格带分析功能选项</b></td>
				</tr>
				<tr>
					<td colspan="6"><font color="red" style="padding-left: 21px;">*</font>&nbsp;&nbsp;时间范围1：
						<select style="width:60px" id="startYear1" filterName="startYear1" onchange="cuCatPriceFn.selectStartYear(this,'endYear1','1')">
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:60px" id="startMonth1" filterName="startMonth1" onchange="cuCatPriceFn.selectStartMon('startYear1','startMonth1','endYear1','endMonth1')">
							<#list 1..12 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						月 
						<span style="padding-left: 8px;padding-right: 8px;">至</span>
						<select style="width:60px" id="endYear1" filterName="endYear1" onchange="cuCatPriceFn.selectEndYear(this,'startYear1','1')">
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:60px" id="endMonth1" filterName="endMonth1" onchange="cuCatPriceFn.selectEndMon('startYear1','startMonth1','endYear1','endMonth1')">
							<#list 1..12 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						月 
						&nbsp;&nbsp;
						（时间跨度最长12 个月）
					</td>
				</tr>
				<tr>
					<td colspan="6" style="padding-left: 27px;">&nbsp;&nbsp;时间范围2：
						<select style="width:60px" id="startYear2" filterName="startYear2" onchange="cuCatPriceFn.selectStartYear(this,'endYear2','2')">
							<option></option>
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:60px" id="startMonth2" filterName="startMonth2" onchange="cuCatPriceFn.selectStartMon('startYear2','startMonth2','endYear2','endMonth2')">
							<option></option>
							<#list 1..12 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						月 
						<span style="padding-left: 8px;padding-right: 8px;">至</span>
						<select style="width:60px" id="endYear2" filterName="endYear2" onchange="cuCatPriceFn.selectEndYear(this,'startYear2','2')">
							<option></option>
							<#list 2010..2024 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						年
						<select style="width:60px" id="endMonth2" filterName="endMonth2" onchange="cuCatPriceFn.selectEndMon('startYear2','startMonth2','endYear2','endMonth2')">
							<option></option>
							<#list 1..12 as year>
								<option value="${year?c}">${year?c}</option>
							</#list>
						</select>
						月 
						&nbsp;&nbsp;
						（时间跨度最长12 个月）
					</td>
				</tr>
				<tr>
					<td colspan="5">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>
						<span style="padding-left: 34px;">地区：</span>
						<input class="easyui-combobox" id="searchRegionCode" style="width: 139px;"
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								url:'masterDataType_listRegionWithoutAll_5.html',
								editable:false" />
					</td>
				</tr>
				<tr>
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font><label>
						<input type="radio" id="direct" name="dls" checked="checked" />只看直营门店数据</label>
						<span style="padding-left: 40px;"></span>
						<label><input type="radio" id="join" name="dls" />只看加盟门店数据</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label><input type="radio" id="allJoin" name="dls" />直营与加盟门店数据均看</label>
					</td>
				</tr>
				</tr>
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">*</font>
						<label><input type="radio" name="sp" onclick="cuCatPriceFn.displayPriceRegion(false)" checked="checked" />系统自动计算价格带</label>
						<span style="padding-left: 28px;"></span>
						<label><input type="radio" id="handSetPri" name="sp" onclick="cuCatPriceFn.displayPriceRegion(true)" />手动设置价格带</label>
					</td>
				</tr>
				<tr id="title" style="display: none;">
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">注："价格大于等于"与"价格小于"的价差必须为步长的倍数</font>
					</td>
				</tr>
				<tr name="priceRegion0" style="display: none;">
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">*</font>价格大于等于：
						<input class="easyui-numberbox" data-options="min:0,precision:2" maxlength="12" id="minPrice0" name="minPrice0" style="width:80px;" onchange="cuCatPriceFn.floorPrice('0')" onblur="cuCatPriceFn.floorPriceBlur('0')" />
						元&nbsp;&nbsp;价格小于：
						<input class="easyui-numberbox" data-options="min:1,precision:2" maxlength="12" id="maxPrice0" name="maxPrice0" style="width:80px;" onchange="cuCatPriceFn.limtPrice('0')" onblur="cuCatPriceFn.limtPriceBlur('0')" />
						元&nbsp;&nbsp;价格步长：
						<input class="easyui-numberbox" data-options="min:1,precision:2" maxlength="12" id="addPrice0" name="addPrice0" style="width:80px;" onchange="cuCatPriceFn.addPrice('0')" onblur="cuCatPriceFn.addPriceBlur('0')" />
						元&nbsp;&nbsp;&nbsp;<input type="button" value="+" onclick="cuCatPriceFn.clonePriceRegion(this)" style="width: 28px;" />
					</td>
				</tr>
				<tr>
					<td>
						<a onclick="cuCatPriceFn.searchData('A');" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.customCategoryPrice.viewAllCategory")>none</#if>;">
							查看价格带汇总报表
						</a>
						<a onclick="cuCatPriceFn.searchData('D');" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.categoryanalysis.customCategoryPrice.viewDetCategory")>none</#if>;">
							查看明细报表
						</a>
					</td>
				</tr>
			</table>
		</div>
    </div>
</body>  
</html>
</#compress>