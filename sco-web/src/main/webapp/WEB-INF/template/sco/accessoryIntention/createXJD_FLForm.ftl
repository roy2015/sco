<#compress>


 <div id="fl" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append2()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit2()">移除</a>
	</div>
	
	<table  id="dg2" class="easyui-datagrid" 
	         pagination = "true"
	         title="辅料"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
			 url='accessoryIntention_listAccessoryEnquiryAccessory_2.html?enquiryCode=${accessoryEnquiry.enquiryCode}'
			data-options="
				singleSelect: true,
				toolbar: '#fl',
				onClickRow: onClickRow2
				
			">
		<thead>
			<tr>
				<th data-options="field:'accessoryName',width:260,editor:{type:'validatebox',options:{required:true,validType:'length[0,33]'}}">
                	<span class="txtCenter">辅料名称</span>
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
		var editIndex2 = undefined;
		function endEditing2(){
			if (editIndex2 == undefined){return true}
			if ($('#dg2').datagrid('validateRow', editIndex2)){
				$('#dg2').datagrid('endEdit', editIndex2);
				editIndex2 = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow2(index){
			if (editIndex2 != index){
				if (endEditing2()){
					$('#dg2').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex2 = index;
				} else {
					$('#dg2').datagrid('selectRow', editIndex2);
				}
			}
		}
		function append2(){
		    var date=$('#dg2').datagrid('getData');
			if (endEditing2()){
				$('#dg2').datagrid('appendRow',{status:'P'});
				editIndex2 = $('#dg2').datagrid('getRows').length-1;
				$('#dg2').datagrid('selectRow', editIndex2)
						.datagrid('beginEdit', editIndex2);
			}
		}
		function removeit2(){
			if (editIndex2 == undefined){return}
			$('#dg2').datagrid('cancelEdit', editIndex2)
					.datagrid('deleteRow', editIndex2);
			editIndex2 = undefined;
		}
	</script>
</#compress>