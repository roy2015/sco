<#compress>


 <div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">移除</a>
	</div>
	
	<table  id="dg" class="easyui-datagrid" 
	         pagination = "true"
	         title="原材料"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
			 url='accessoryIntention_listAccessoryEnquiryMaterial_2.html?enquiryCode=${accessoryEnquiry.enquiryCode}'
			data-options="
				singleSelect: true,
				toolbar: '#tb',
				onClickRow: onClickRow
				
			">
		<thead>
			<tr>
				<th data-options="field:'materialName',width:260,editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}}">
                	<span class="txtCenter">原材料名称</span>
                </th>
                <th data-options="field:'material',width:260,editor:{type:'validatebox',options:{required:true,validType:'length[0,100]'}}">
                	<span class="txtCenter">材料</span>
                </th>
                <th data-options="field:'materialSize',width:230,editor:{type:'validatebox',options:{required:true,validType:'length[0,200]'}}">
                	<span class="txtCenter">尺寸</span>
                </th>
			</tr>
		</thead>
	</table>

	<script type="text/javascript">
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function append(){
		    var date=$('#dg').datagrid('getData');
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			}
		}
		function removeit(){
			if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined;
		}
	</script>
</#compress>