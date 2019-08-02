<#compress>
	<script type="text/javascript" >
		<#include "sco/merchandiseOaApplication/fastImportOaApplication/wlinfo/wlInfoFastImport.js" />
    </script>
    
	<div id="fastAdjustMer_toolbar">
		<a onclick="fastAdjustWlInfoFn.showInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			新增物料信息记录
		</a>
	</div>
	<div>
		<table id="fastImportMerGrid" 
			title="本次快速引进的商品列表"
			fitColumns="true"
			pagination = "true"
			pagePosition = "bottom"
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#fastAdjustMer_toolbar"
			singleSelect = "true"
			style="height:270px"
			data-options="rownumbers:true,
			url : 'wlInfoFastAdjust_listWlInfoFastAdjust_2.html?applicationCode=${applicationCode}',
			onLoadSuccess:function(){
				$('#fastImportMerGrid').datagrid('clearSelections');
			}
		">  
			<thead>
				<tr>
					<th data-options="field:'intentionCode',width:170,halign:'left'">
						商品编号
					</th>
						<th data-options="field:'intentionName',width:250,halign:'left'">
						商品名称
					</th>
					<th rowspan="2" data-options="field:'supplierCode',width:170,halign:'left'">
						供应商编号
					</th>
					<th rowspan="2" data-options="field:'supplierName',width:250,halign:'left'">
						供应商名称
					</th>
					<th rowspan="2" data-options="field:'region',width:120,halign:'left'">
						是否已填写物料信息
					</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div style="padding: 20px;"></div>
	<div id="fastAdjustWlInfo_toolbar">
		<a onclick="fastAdjustWlInfoFn.remove();" plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
			删除物料信息记录
		</a>
		<a onclick="fastAdjustWlInfoFn.showEdit();" plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'">
			修改物料信息记录
		</a>
	</div>
	<div>
		<table id="fastImportWlInfoGrid" 
			title="已填写物料信息"
			fitColumns="true"
			pagination = "true"
			pagePosition = "bottom"
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#fastAdjustWlInfo_toolbar"
			style="height:400px"
			data-options="rownumbers:true,
			url : 'wlInfoFastAdjust_listWlInfoDetail_2.html?applicationCode=${applicationCode}'
		">  
			<thead>
				<tr>
					<th rowspan="2" data-options="field:'NULL',	checkbox:true,halign:'left'"></th>
					<th data-options="field:'intentionCode',width:150,halign:'left'">
						商品编号
					</th>
						<th data-options="field:'intentionName',width:200,halign:'left'">
						商品名称
					</th>
					<th rowspan="2" data-options="field:'supplierCode',width:140,halign:'left'">
						供应商编号
					</th>
					<th rowspan="2" data-options="field:'supplierName',width:200,halign:'left'">
						供应商名称
					</th>
					<th rowspan="2" data-options="field:'accessorySAPCode',width:200,halign:'left'">
						SAP物料号
					</th>
					<th rowspan="2" data-options="field:'supplierSAPCode',width:200,halign:'left'">
						SAP供应商号
					</th>
					<th rowspan="2" data-options="field:'region',width:160,halign:'left'">
						进货地区
					</th>
					<th rowspan="2" data-options="field:'sumPrice',width:120,align:'right',halign:'right'">
						合同价格
					</th>
				</tr>
			</thead>
		</table>
	</div>
</#compress>