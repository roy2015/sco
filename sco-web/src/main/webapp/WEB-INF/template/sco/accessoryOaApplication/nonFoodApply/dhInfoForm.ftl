<#compress>
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	<#include "sco/accessoryOaApplication/nonFoodApply/dhInfo.js" />
</script>
</head>
<body>

<div style="padding: 5px;overflow: hidden">
	<form id="dhinfo_form" method="post" enctype="multipart/form-data">
		<table class="tableForm">
			<input name="intentionCode" id="intentionCode" type="hidden"  value="${intentionCode}"/>
			<input name="supplierCode" id="supplierCode" type="hidden"  value="${supplierCode}"/>
			<input name="applicationCode" id="applicationCode" type="hidden"  value="${applicationCode}"/>
					
					 <tr>
					    <th align="right" style="width: 55px;"><span class="txtCenter"><font color="red">*</font>请选择文件类型:</span></th>
						<td>
							<select id="fileType" name="fileType" panelHeight="auto"    style="width:200px;"   class="easyui-combobox" data-options="required:true,editable:false, onChange:dhInfoFn.showFileTypeOther" >
									 <option value="封样产品图片">封样产品图片</option>
									 <option value="物资ID卡">物资ID卡</option>
									 <option value="其他">其他</option>
							</select>
						</td>
					   
					</tr>
					<tr id="otherType" style="display:none">
					    <th align="right" style="width: 80px;"><span class="txtCenter"><font color="red">*</font>请填写文件类型:</span></th>
						<td>
							<input name="fileTypeOther"  id="fileTypeOther" class="easyui-validatebox" data-options="required:false" />
						</td>
					</tr>
					<tr>
				   <td style="text-align:right;"><font color="red">*</font>选择文件:</td>
				   <td>
					<input type="text" class="file" id="txt" style="width:0px;"/>
					<input class="input_file" size="30" type="file" onchange="txt.value=this.value" id="uploadNonFood" name="upload" style="width:200px;" />
				   </td>
			</tr>
		</table>
		</form>
		
</div>
</body>
</html>
</#compress>