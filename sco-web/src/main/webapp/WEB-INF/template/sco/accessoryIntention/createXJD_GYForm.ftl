<#compress>


 <div id="gy" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append4()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit4()">移除</a>
	</div>
	
	<table  id="dg4" class="easyui-datagrid"  
	         pagination = "true"
	         title="工艺"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
			 url='accessoryIntention_listAccessoryEnquiryTechnology_2.html?enquiryCode=${accessoryEnquiry.enquiryCode}'
			data-options="
				singleSelect: true,
				toolbar: '#gy',
				onClickRow: onClickRow4
				
			">
		<thead>
			<tr>
				<th data-options="field:'technologyName',width:360,editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}}">
                	<span class="txtCenter">工艺名称</span>
                </th>
                <th data-options="field:'technologyInfo',width:390,editor:{type:'validatebox',options:{required:true,validType:'length[0,133]'}}">
                	<span class="txtCenter">工艺要求</span>
                </th>
			</tr>
		</thead>
	</table>

	<script type="text/javascript">
		var editIndex4 = undefined;
		function endEditing4(){
			if (editIndex4 == undefined){return true}
			if ($('#dg4').datagrid('validateRow', editIndex4)){
				$('#dg4').datagrid('endEdit', editIndex4);
				editIndex4 = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow4(index){
			if (editIndex4 != index){
				if (endEditing4()){
					$('#dg4').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex4 = index;
				} else {
					$('#dg4').datagrid('selectRow', editIndex4);
				}
			}
		}
		function append4(){
		    var date=$('#dg4').datagrid('getData');
			if (endEditing4()){
				$('#dg4').datagrid('appendRow',{status:'P'});
				editIndex4 = $('#dg4').datagrid('getRows').length-1;
				$('#dg4').datagrid('selectRow', editIndex4)
						.datagrid('beginEdit', editIndex4);
			}
		}
		function removeit4(){
			if (editIndex4 == undefined){return}
			$('#dg4').datagrid('cancelEdit', editIndex4)
					.datagrid('deleteRow', editIndex4);
			editIndex4 = undefined;
		}
	</script>
</#compress>