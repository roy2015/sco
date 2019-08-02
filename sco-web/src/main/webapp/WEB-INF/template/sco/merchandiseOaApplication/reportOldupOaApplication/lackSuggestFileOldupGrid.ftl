<#compress>

<div style="padding: 5px;overflow: hidden;">
<form id="lackFileOldup_form" method="post">
<input type="hidden" name="applicationCode" id="applicationCode" value="${applicationCode}" />
<#list lackFileList as lackFileOldup>    
<div title="商品${lackFileOldup_index+1}" >
	<fieldset style="margin-top:10px;border:1px solid #CCC;" data-options="fit:true">
		<!--1.商品基础信息-->
		<table class="tableForm" >
			<tr>
				 <th align="right" >申请单号</th>
				<td>
					<input name="lackFileList[${lackFileOldup_index}].applicationCode" readonly="readonly" value="${lackFileOldup.applicationCode}" style="background:lightgray;" class="easyui-validatebox" />
				</td>
			    <th align="right" >意向品编号</th>
				<td>
					<input name="lackFileList[${lackFileOldup_index}].merchandiseCode" readonly="readonly" value="${lackFileOldup.merchandiseCode}" style="background:lightgray;" class="easyui-validatebox"  />
				</td>
				  <th align="right" >意向品名称</th>
				<td>
					<input name="lackFileList[${lackFileOldup_index}].merchandiseName" readonly="readonly" value="${lackFileOldup.merchandiseName}" style="background:lightgray;" class="easyui-validatebox"  />
				</td>
				 <th align="right" >供应商编号</th>
				<td>
					<input name="lackFileList[${lackFileOldup_index}].supplierCode" readonly="readonly" value="${lackFileOldup.supplierCode}" style="background:lightgray;" class="easyui-validatebox" />
				</td>
			<tr>	
			</tr>	
				<th align="right" >供应商名称</th>
				<td>
					<input name="lackFileList[${lackFileOldup_index}].supplierName" readonly="readonly" value="${lackFileOldup.supplierName}" style="background:lightgray;" class="easyui-validatebox"  />
				</td>
				 <th align="right" >缺少文件名称</th>
				<td>
					<input name="lackFileList[${lackFileOldup_index}].lackFileName" readonly="readonly" value="${lackFileOldup.lackFileName}" style="background:lightgray;" class="easyui-validatebox" />
				</td>
				 <th align="right" >缺少文件版本</th>
				<td>
					<input name="lackFileList[${lackFileOldup_index}].lackFileVersions" readonly="readonly" value="${lackFileOldup.lackFileVersions}" style="background:lightgray;" class="easyui-validatebox" />
				</td>
				<th align="right" >缺少文件原因</th>
				<td>
					<input name="lackFileList[${lackFileOldup_index}].lackFileCause"  value="${lackFileOldup.lackFileCause}"  class="easyui-validatebox"  data-options="required:true,validType:'length[1,333]'"/>
				</td>
			</tr>
		</table>
	 </fieldset>	
  </div>
</#list>
</form>
</div>
</#compress>