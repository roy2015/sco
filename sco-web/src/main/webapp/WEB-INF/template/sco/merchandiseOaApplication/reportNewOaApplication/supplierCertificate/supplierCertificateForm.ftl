<#compress>
<div style="padding: 10px;overflow: hidden;">
	<form id="supplierCertificate_form" method="post" enctype="multipart/form-data">
		<table class="tableForm" style="width:350px">
			<tr>
				<td style="width:79px"><input name="supplierCode" id="supplierCode" type="hidden" readonly="readonly" value="${supplierCertificate.supplierCode}" /></td>
			</tr>
			<tr>
				<td style="text-align:left;"><label style="color: red;" >*</label>证件名称：</td>
				<td>
					<input class="easyui-combobox" name="certificateTypeCode" style="width: 150px;" 
						data-options="
							valueField:'id',
							textField:'text',
							required:true,
							editable:false,
						    url:'supplierCertificate_listSupplierCertificateType_5.html'
			    	" />
				</td>
			</tr>
			<tr>
				<td style="text-align:left;">选择文件：</td>
				<td>
					<input type="text" class="file" id="txt" style="width:0px;"/>
					<input class="input_file" size="30" type="file" onchange="txt.value=this.value" id="upload" name="upload" style="width:200px;" />
				</td>
			</tr>
			<tr id="addDate">
				<td style="text-align:left;">证件有效期：</td>
				<td>
					<label><input type="radio" name="add" onclick="supplierCertificateMFn.setValidDate(true)" style="width:12px">有有效期</label>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="radio" name="add" checked="checked" onclick="supplierCertificateMFn.setValidDate()" style="width:12px">无有效期</label>
					<input type="hidden" id="showRegion" value="1" />
				</td>
			</tr>
		</table>
	</form>
</div>
</#compress>