<#compress>
		<input type="hidden" id="selectedMd" />
		<table id="addMd_grid" 
			style="height:300px"
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			data-options="url:'merchandise_listMerchandiseByParam_2.html',
			fit:true,
			fitColumns:true,
			pagination:true,
			pagePosition:'bottom'">  
			<thead>
				<tr>  
					<th rowspan="2" data-options="field:'HH',checkbox:true"></th>
					<th data-options="field:'merchandiseCode',width:120,halign:'center',formatter:signedQuantityFn.selectedRow">
						<span class="txtCenter">商品编号</span>
					</th>
					<th data-options="field:'merchandiseName',width:140,halign:'center'">
						<span class="txtCenter">商品名称</span>
					</th>
				</tr>
				<tr>
					<th><input style="width:100px;" class="filterInput" filterName="merchandiseCode"/></th>  
					<th><input style="width:120px;" class="filterInput" filterName="merchandiseName"/></th>  
				</tr>  
			</thead>  
		</table> 
</#compress>