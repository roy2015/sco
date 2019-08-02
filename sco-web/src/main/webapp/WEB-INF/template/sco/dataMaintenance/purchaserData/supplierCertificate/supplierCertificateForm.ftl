<#compress>
<div style="padding: 10px;overflow: hidden;">
	<form id="supplierCertificate_form" method="post" enctype="multipart/form-data">
		<table class="tableForm" style="width:400px">
			<tr>
				<td style="text-align:right;width: 125px;"><font color="red">*</font>供应商编号-名称：</td>
				<td>
					<input id="supplierIdName" name="supplierCode" class="easyui-combobox list-input" style="width:150px" />
					<div id="supInput">
						<div style="width:350px;height:360px;">
							<table  id="supplier_grid"
									    border="none"
									    fit="true"
									    fitColumns="true"
									    iconCls= "icon-save"
									    singleSelect="true"
										pagination = "true"
										pagePosition = 'bottom'
										pageSize = "20"
										pageList = "[ 10, 20, 40, 60 ]"
									    data-options="rownumbers:true,
									    	onSelect:supCertifiFn.onSelectSupplier
									    "> 
								 <thead>
									<tr>
									    <th data-options="field:'id',width:120,halign:'center'">
						                	<span class="txtCenter">供应商编号</span>
						                </th>
									    <th data-options="field:'text',width:180,halign:'center'">
						                	<span class="txtCenter">供应商名称</span>
						                </th>
						            </tr>
						            <tr>
										<th><input style="width:100px;" class="filterInput" filterName="id"/></th>
										<th><input style="width:160px;" class="filterInput" filterName="text"/></th>
						            </tr>
						        </thead>
						    </table>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;"><font color="red">*</font>证件名称：</td>
				<td>
					<input class="easyui-combobox" name="certificateTypeCode" style="width: 150px;" 
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
				<td style="text-align:right;"><font color="red">*</font>选择文件：</td>
				<td>
					<input type="text" class="file" id="txt" style="width:0px;"/>
					<input class="input_file" size="30" type="file" onchange="txt.value=this.value" id="upload" name="upload" style="width:200px;" />
				</td>
			</tr>
			<tr id="addDate">
				<td style="text-align:right;"><font color="red">*</font>证件有效期：</td>
				<td>
					<label><input type="radio" name="add" checked="checked" onclick="supCertifiFn.setValidDate(true)" style="width:12px">有有效期</label>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="radio" name="add" onclick="supCertifiFn.setValidDate()" style="width:12px">无有效期</label>
					<input type="hidden" id="showRegion" value="1" />
				</td>
			</tr>
		</table>
	</form>
</div>
</#compress>