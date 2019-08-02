<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <script type="text/javascript">
    	
    </script>
</head>
<body>
    <table  id="webHisMer_grid"
		    iconCls= "icon-save"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			fit="true"
			pageList = "[ 10, 20, 30, 40 ]"
			url='websiteHistory_listMerchandise_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
				<th data-options="field:'merchandiseCode',width:150">
                	商品编号
                </th>
				<th data-options="field:'merchandiseName',width:240">
                	商品名称
                </th>
				<th data-options="field:'supplierCode',width:150">
                	供应商编号
                </th>
				<th data-options="field:'supplierName',width:220">
                	供应商名称
                </th>
				<th data-options="field:'dxRoleName',width:200">
					商品定性角色
                </th>
				<th data-options="field:'dlRoleName',width:200">
					商品定量角色
                </th>
				<th data-options="field:'centreName',width:200">
                	中分类
                </th>
				<th data-options="field:'smallName',width:200">
                	小分类
                </th>
				<th data-options="field:'detailName',width:200">
                	明细类
                </th>
				<th data-options="field:'fineName',width:200">
                	细分类
                </th>
				<th data-options="field:'createby',width:100">
                	关联人
                </th>
				<th rowspan="2" data-options="field:'created',width:100,sortable:true">
                	关联时间
                </th>
            </tr>
        </thead>
        <thead>
            <tr>
                <th><input style="width:60px;" class="filterInput" filterName="merchandiseCode"/></th>  
                <th><input style="width:60px;" class="filterInput" filterName="merchandiseName"/></th>  
                <th><input style="width:60px;" class="filterInput" filterName="supplierCode"/></th>  
                <th><input style="width:60px;" class="filterInput" filterName="supplierName"/></th>  
				<th>
					<select class="filterSelect" filterName="dxRoleName" style="border: ridge 1px #B5AFAF;height:18px;width:188px">
				 		<option value=""></option>
				 		<#list dxList as l>
				 			<option value="${l.id}">${l.text}</option>
				 		</#list>
				 	</select>
				</th>
				<th>
					<select class="filterSelect" filterName="dlRoleName" style="border: ridge 1px #B5AFAF;height:18px;width:188px">
						<option value=""></option>
				 		<#list dlList as l>
				 			<option value="${l.id}">${l.text}</option>
				 		</#list>
					 </select>
				</th>
                <th>
                	<select class="filterSelect" filterName="centreName" style="border: ridge 1px #B5AFAF;height:18px;width:188px">
						<option value=""></option>
				 		<#list cList as l>
				 			<option value="${l.id}">${l.text}</option>
				 		</#list>
					 </select>
                </th>
                <th>
                	<select class="filterSelect" filterName="smallName" style="border: ridge 1px #B5AFAF;height:18px;width:188px">
						<option value=""></option>
				 		<#list sList as l>
				 			<option value="${l.id}">${l.text}</option>
				 		</#list>
					 </select>
                </th>
                <th>
					<select class="filterSelect" filterName="detailName" style="border: ridge 1px #B5AFAF;height:18px;width:188px">
						<option value=""></option>
						<#list dList as l>
							<option value="${l.id}">${l.text}</option>
						</#list>
					</select>
                </th>
                <th>
                	<select class="filterSelect" filterName="fineName" style="border: ridge 1px #B5AFAF;height:18px;width:188px">
						<option value=""></option>
						<#list fList as l>
							<option value="${l.id}">${l.text}</option>
						</#list>
					</select>
                </th>
                <th><input style="width:80px;" class="filterInput" filterName="createby"/></th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>