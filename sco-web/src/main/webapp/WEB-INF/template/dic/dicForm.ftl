<#compress>
<div id="dicForm_toolbar">	
	<input type="hidden" id="id" name="id" value="${dic.id}"/>
	<table style="width:550px">
		<tr>
			<td><span style="font-size:12px;">${action.getText("dic.dicType")}</span></td>
			<td><input id="dicType" name="dicType" class="easyui-combobox"  data-options="url:'dic_listDicType_5.html',valueField:'id',textField:'text',validType:'length[1,24]',required:true" value="${dic.dicType}"/>
			</td>
			<td><span style="font-size:12px;">${action.getText("dic.typeDesc")}</span></td>
			<td><input id="typeDesc" name="typeDesc" class="easyui-validatebox" data-options="validType:'length[1,100]'"   value="${dic.typeDesc}"></td>
		<tr>
		<tr>
			<td><span style="font-size:12px;">${action.getText("dic.dicKey")}</span></td>
			<td><input id="dicKey" name="dicKey" class="easyui-validatebox" data-options="validType:'length[1,24]'" required="true" value="${dic.dicKey}"></td>
			<td><span style="font-size:12px;">${action.getText("dic.isDefault")}</span></td>
			<td> <input id="isDefault" type="checkbox" ></td>
		</tr>
		<tr>
			<td><span style="font-size:12px;">${action.getText("dic.valueDesc")}</span></td>
			<td colspan="3"><input id="valueDesc" style="width:300px;" name="valueDesc" class="easyui-validatebox" data-options="validType:'length[1,300]'"  value="${dic.valueDesc}"></td>
			
		</tr>
		<tr>
			<td colspan="4">
				<a id="insertDicLocale" onclick="dicFn.insertDicLocale();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" >
					${action.getText("common.button.add")}
				</a>
				<!--
				<a id="showEditDicLocale" onclick="dicFn.showEditDicLocale();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'" >
					${action.getText("common.button.edit")}
				</a>
				-->
				<a id="removeDicLocale" onclick="dicFn.removeDicLocale();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
					${action.getText("common.button.delete")}
				</a>
			</td>
		</tr>
	</table>
</div>

<table  id="dicForm_dg"
	    fit="true"
	    fitColumns="true"
	    singleSelect="true"
		toolbar="#dicForm_toolbar"
	    rownumbers="true"
	    >
	<thead>
		<tr>
			<th field="language" width="120"  data-options="align:'center',
				 formatter:function(value,row){
					return value;
				 },
				editor:{
					type:'combobox',
					options:{
						valueField:'id',
						textField:'text',
						editable:false,
						required:true,
						data:[{
								'id': 'zh_cn',
								'text': '中文',
								'selected':true
							 },{
								'id': 'en_gb',
								'text': 'English'
							 }]
					}
				}
				">${action.getText("dic.language")}</th>
			<th field="dicValue" width="140"  data-options="align:'center',
				formatter:function(value,row){
						return value;
					 },
				editor:{
					type:'validatebox',
					options:{
						required:true,
						validType:'length[1,300]'
					}
					
				}
				"
			>${action.getText("dic.dicValue")}</th>
			<th field="valueAlias" width="220"  class="easyui-validatebox" data-options="align:'center',editor:'text'">${action.getText("dic.valueAlias")}</th>
		</tr>
		<!--
			<tr>
				<th>
	              	<input style="width:100px;height:25px;" class="easyui-combobox"  filterName="language"  data-options="url:'dic_listLanguage_5.html',valueField:'id',textField:'text',required:true" />	
	            </th>
	            <th>
	              	<input style="width:120px;height:25px;" class="filterInput" filterName="dicValue"/>
	            </th> 
		        <th>
		           	<input style="width:200px;height:25px;" class="filterInput" filterName="valueAlias"/>
		        </th>  
			</tr>
		-->
	</thead>
</table>
</#compress>