<#compress>


 <div id="nwbz" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append3()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit3()">移除</a>
	</div>
	
	<table  id="dg3" class="easyui-datagrid"  
	         pagination = "true"
	         title="内外包装"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
			 url='accessoryIntention_listAccessoryEnquiryPacking_2.html?enquiryCode=${accessoryEnquiry.enquiryCode}'
			data-options="
				singleSelect: true,
				toolbar: '#nwbz',
				onClickRow: onClickRow3
				
			">
		<thead>
			<tr>
				<th data-options="field:'packingName',width:260,editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}}">
                	<span class="txtCenter">内外包装材料名称</span>
                </th>
                <th data-options="field:'packingMaterial',width:260,editor:{type:'validatebox',options:{required:true,validType:'length[0,100]'}}">
                	<span class="txtCenter">材料</span>
                </th>
                <th data-options="field:'materialSize',width:230,editor:{type:'validatebox',options:{validType:'length[0,200]'}}">
                	<span class="txtCenter">尺寸</span>
                </th>
			</tr>
		</thead>
	</table>

	<script type="text/javascript">
		var editIndex3 = undefined;
		function endEditing3(){
			if (editIndex3 == undefined){return true}
			if ($('#dg3').datagrid('validateRow', editIndex3)){
				$('#dg3').datagrid('endEdit', editIndex3);
				editIndex3 = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow3(index){
			if (editIndex3 != index){
				if (endEditing3()){
					$('#dg3').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex3 = index;
				} else {
					$('#dg3').datagrid('selectRow', editIndex3);
				}
			}
		}
		function append3(){
		    var date=$('#dg3').datagrid('getData');
			if (endEditing3()){
				$('#dg3').datagrid('appendRow',{status:'P'});
				editIndex3 = $('#dg3').datagrid('getRows').length-1;
				$('#dg3').datagrid('selectRow', editIndex3)
						.datagrid('beginEdit', editIndex3);
			}
		}
		function removeit3(){
			if (editIndex3 == undefined){return}
			$('#dg3').datagrid('cancelEdit', editIndex3)
					.datagrid('deleteRow', editIndex3);
			editIndex3 = undefined;
		}
		
	</script>
</#compress>