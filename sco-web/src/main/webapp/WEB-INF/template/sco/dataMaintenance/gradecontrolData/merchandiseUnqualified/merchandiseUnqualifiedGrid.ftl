<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/gradecontrolData/merchandiseUnqualified/merchandiseUnqualified.js" />
		<#include "sco/common/masterDataType.js" />
    </script>
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
</head>
<body>
	<!-- 商品外部抽检不合格记录管理主表 --> 
	<div id="merchandiseUnqualified_toolbar" style="margin:5px 5 5px 5;padding:2px">
		<form id="signedQty_search">
			<table style="width:850px;">
				<tr>
					<td style="text-align:right;width:80px">
						供应商编号:
					</td>
					<td style="width:110px;">
						<input filterName="supplierCode" id="supplierCode" style="width:120px;" />
					</td>
					<td style="text-align:right;width:80px">
						供应商名称:
					</td>
					<td style="width:110px;">
						<input filterName="supplierName" id="supplierName" style="width:120px;" />
					</td>
					<td style="text-align:right;width:80px;">
						抽检日期:
					</td>
					<td>
						<input class="easyui-datebox list-input" filterName="minSpotCheckDate" id="minSpotCheckDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width:124px;"/>
					</td>
					<td style="text-align:right;width:80px;">
						至:
					</td>
					<td>
						<input class="easyui-datebox list-input" filterName="maxSpotCheckDate" id="maxSpotCheckDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width:124px;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">
						商品编号:
					</td>
					<td style="width:110px;">
						<input filterName="merchandiseCode" id="merchandiseCode" style="width:120px;" />
					</td>
					<td style="text-align:right;">
						&nbsp;商品名称:
					</td>
					<td>
						<input filterName="merchandiseName" id="merchandiseName" style="width:120px;" />
					</td>
					<td style="text-align:right;">商品定性角色:</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dxRoleCode" id="dxRoleCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'merchandiseRole_listQualitative_5.html'
					    " />
					</td>
					<td style="text-align:right;">商品定量角色:</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="dlRoleCode" id="dlRoleCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'merchandiseRole_listQuantify_5.html'
					    " />
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">中分类:</td>
					<td>
						<input class="easyui-combobox filterSelect" filterName="centreTypeCode" id="centreTypeCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " />
					</td> 
					<td style="text-align:right;">小分类:</td>
					<td>
						<input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallTypeCode" style="width: 124px;"
							data-options="
								valueField:'id',
								textField:'text',
								editable:false" 
						/>
					</td>
					<td style="text-align:right;">明细类:</td>
					<td>
						<input class="easyui-combobox filterSelect" id="detailTypeCode" filterName="detailTypeCode" style="width: 124px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false" 
					    />
					</td>
					<td style="text-align:right;">细分类:</td>
					<td>
						<input class="easyui-combobox filterSelect" id="fineTypeCode" filterName="fineTypeCode" style="width: 124px;"
							data-options="
								valueField:'id',
								textField:'text',
								editable:false" 
						/>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a onclick="merchandiseUnqualifiedFn.search()" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
							查看商品外部抽检不合格记录
						</a>
						<a onclick="merchandiseUnqualifiedFn.clearFilter();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
						<a id="upload" onclick="merchandiseUnqualifiedFn.showUpload();" plain="true" class="easyui-linkbutton" data-options="iconCls:'upload'">
							上传商品外部抽检不合格记录表
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
    <table  id="merchandiseUnqualified_grid"
		    fit="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#merchandiseUnqualified_toolbar"
			url='merchandiseUnqualified_listMerchandiseUnqualified_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
				<th data-options="field:'supplierCode',width:150,sortable:true">
                	供应商编号
                </th>
				<th data-options="field:'supplierName',width:'150'">
                	供应商名称
                </th>
				<th data-options="field:'merchandiseCode',width:150,sortable:true">
                	商品编号
                </th>
				<th data-options="field:'merchandiseName',width:150">
                	商品名称
                </th>
				<th data-options="field:'dxRoleName',width:100">
                	商品定性角色
                </th>
                <th data-options="field:'dlRoleName',width:100">
                	商品定量角色
                </th>
				<th data-options="field:'centreName',width:85">
                	中分类
                </th>
				<th data-options="field:'smallName',width:85">
                	小分类
                </th>
				<th data-options="field:'detailName',width:85">
                	明细类
                </th>
				<th data-options="field:'fineName',width:85">
                	细分类
                </th>
                <th data-options="field:'spotCheckDate',width:120,sortable:true">
                	抽检日期
                </th>
                <th data-options="field:'spotCheckBatch',width:100,sortable:true">
                	 抽检批次
                </th>
                <th data-options="field:'remarks',width:100,sortable:false">
                	备注
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>