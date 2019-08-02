<#compress>


 <div id="qtyq" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append6()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit6()">移除</a>
	</div>
	
	<table  id="dg6" class="easyui-datagrid" 
	         pagination = "true"
	         title="其他要求"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
			 url='accessoryIntention_listAccessoryEnquiryElse_2.html?enquiryCode=${accessoryEnquiry.enquiryCode}'
			data-options="
				singleSelect: true,
				toolbar: '#qtyq',
				onClickRow: onClickRow6
				
			">
		<thead>
			<tr>
				<th data-options="field:'name',width:360,editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}} "  >
                	<span class="txtCenter">要求名称</span>
                </th>
                <th data-options="field:'info',width:390,editor:{type:'validatebox',options:{required:true,validType:'length[0,163]'}}">
                	<span class="txtCenter">要求内容</span>
                </th>
			</tr>
		</thead>
	</table>

	<script type="text/javascript">
		var editIndex6 = undefined;
		function endEditing6(){
			if (editIndex6 == undefined){return true}
			if ($('#dg6').datagrid('validateRow', editIndex6)){
				$('#dg6').datagrid('endEdit', editIndex6);
				editIndex6 = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow6(index){
			if (editIndex6 != index){
				if (endEditing6()){
					$('#dg6').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex6 = index;
				} else {
					$('#dg6').datagrid('selectRow', editIndex6);
				}
			}
		}
		function append6(){
		    var date=$('#dg6').datagrid('getData');
			if (endEditing6()){
				$('#dg6').datagrid('appendRow',{status:'P'});
				editIndex6 = $('#dg6').datagrid('getRows').length-1;
				$('#dg6').datagrid('selectRow', editIndex6)
						.datagrid('beginEdit', editIndex6);
			}
		}
		function removeit6(){
			if (editIndex6 == undefined){return}
			$('#dg6').datagrid('cancelEdit', editIndex6)
					.datagrid('deleteRow', editIndex6);
			editIndex6 = undefined;
		}
		
	</script>
</#compress>