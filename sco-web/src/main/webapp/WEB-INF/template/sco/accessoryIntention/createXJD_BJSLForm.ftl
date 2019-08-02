<#compress>


 <div id="bjsl" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append5()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit5()">移除</a>
	</div>
	
	<table  id="dg5" class="easyui-datagrid" 
	         pagination = "true"
	         title="报价数量"
			 pagePosition = "bottom"
			 pageSize = "5"
			 pageList = "[ 5, 10, 15, 20 ]"
			 url='accessoryIntention_listAccessoryEnquiryQuotedCount_2.html?enquiryCode=${accessoryEnquiry.enquiryCode}'
			data-options="
				singleSelect: true,
				toolbar: '#bjsl',
				onClickRow: onClickRow5,
				
			">
		<thead>
			<tr>
				<th data-options="field:'quotedCount',width:750,editor:{type:'numberbox',options:{required:true,min:0,max:9999999999,precision:0}},
					formatter:function(value){
						 return formatterCount(value);
						} "  >
                	<span class="txtCenter">报价数量</span>
                </th>
                
			</tr>
		</thead>
	</table>

	<script type="text/javascript">
		var editIndex5 = undefined;
		function endEditing5(){
			if (editIndex5 == undefined){return true}
			if ($('#dg5').datagrid('validateRow', editIndex5)){
				$('#dg5').datagrid('endEdit', editIndex5);
				editIndex5 = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow5(index){
			if (editIndex5 != index){
				if (endEditing5()){
					$('#dg5').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex5 = index;
				} else {
					$('#dg5').datagrid('selectRow', editIndex5);
				}
			}
		}
		function append5(){
		    var date=$('#dg5').datagrid('getData');
			if (endEditing5()){
				$('#dg5').datagrid('appendRow',{status:'P'});
				editIndex5 = $('#dg5').datagrid('getRows').length-1;
				$('#dg5').datagrid('selectRow', editIndex5)
						.datagrid('beginEdit', editIndex5);
			}
			
		}
		function removeit5(){
			if (editIndex5 == undefined){return}
			$('#dg5').datagrid('cancelEdit', editIndex5)
					.datagrid('deleteRow', editIndex5);
			editIndex5 = undefined;
		}
		
	</script>
</#compress>