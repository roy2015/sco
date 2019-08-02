<#compress>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "inc/easyui.ftl" />
<link rel="stylesheet" href="css/table.min.css" type="text/css" />
<script type="text/javascript">
	<#include "sco/dataMaintenance/assortmentdata/merchandiseFineType/merchandiseFineTypeModel.js" />
	<#include "sco/common/masterDataType.js" />
</script>
</head>
<body>
	<!-- 商品细分类维护管理主表 -->
	<div id="merchandiseFineType_toolbar">
		<form id="merchandiseFineType_search">
			<table>
				<tr>
					<td style="text-align: right; width: 45px">中分类:</td>
					<td><input class="easyui-combobox filterSelect" filterName="centreType" id="centreTypeCode" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " /></td>
					<td style="text-align: right; width: 60px">小分类:</td>
					<td><input class="easyui-combobox filterSelect" id="smallTypeCode" filterName="smallType" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								editable:false" /></td>
					<td style="text-align: right; width: 60px">明细类:</td>
					<td><input class="easyui-combobox filterSelect" id="detailTypeCode" name="detailTypeCode" filterName="detailType" style="width: 124px;"
						data-options="
									valueField:'id',
									textField:'text',
									editable:false" /></td>
				</tr>
			</table>
			<a id="search" onclick="merchandiseFineTypeFn.search();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'"> 查询 </a> 
			<a onclick="merchandiseFineTypeFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'"> 清空查询 </a> 
			<a id="insert" onclick="merchandiseFineTypeFn.showInsert();" plain="true" class="easyui-linkbutton"	data-options="iconCls:'add'"> 添加 </a> 
			<a id="remove" onclick="merchandiseFineTypeFn.remove();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'"> 删除 </a>
		</form>
	</div>
	<table id="merchandiseFineType_grid" fit="true" singleSelect="false" pagination="true" pagePosition='bottom' pageSize="20" pageList="[ 10, 20, 30, 40 ]"
		toolbar="#merchandiseFineType_toolbar" data-options="rownumbers:true" >
		<thead>
			<tr>
				<th data-options="field:'merchandiseCode',width:40,checkbox:true"></th>
				<th data-options="field:'centreTypeName',width:150">中分类</th>
				<th data-options="field:'smallTypeName',width:150">小分类</th>
				<th data-options="field:'detailTypeName',width:150">明细类</th>
				<th data-options="field:'fineTypeName',width:150">细分类</th>
			</tr>
		</thead>
	</table>
</body>
</html>
</#compress>
