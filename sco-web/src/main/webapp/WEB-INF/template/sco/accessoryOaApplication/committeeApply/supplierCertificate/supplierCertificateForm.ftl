<#compress>
<div style="padding: 10px;overflow: hidden;">
	<form id="supplierCertificate_form" method="post" enctype="multipart/form-data">
		<table class="tableForm" style="width:330px;text-align: left;">
			<tr>
				<td><input name="supplierCode" id="supplierCode" type="hidden" readonly="readonly" value="${supplierCertificate.supplierCode}" /></td>
			</tr>
			<tr>
				<td style="text-align:left;width:20px">证件名称:</td>
				<td>
					<input class="easyui-combobox" name="certificateTypeCode" style="width: 124px;" 
						data-options="
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							required:true,
							editable:false,
						    url:'supplierCertificate_listSupplierCertificateType_5.html'
			    	" />
				</td>
			</tr>
			<tr>
				<td style="text-align:left;">选择文件:</td>
				<td>
					<input type="text" class="file" id="txt" style="width:0px;"/>
					<input class="input_file" size="30" type="file" onchange="txt.value=this.value" id="uploadInfo" name="upload" style="width:200px;" />
				</td>
			</tr>
			<tr id="addDate">
				<td style="text-align:left;">证件有效期:</td>
				<td>
					<label><input type="radio" name="add" onclick="supplierCertificateAFn.setValidDate(true)" style="width:12px">有有效期</label>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="radio" name="add" checked="checked" onclick="supplierCertificateAFn.setValidDate()" style="width:12px">无有效期</label>
					<input type="hidden" id="showRegion" value="1" />
				</td>
			</tr>
		</table>
	</form>
</div>
</#compress>