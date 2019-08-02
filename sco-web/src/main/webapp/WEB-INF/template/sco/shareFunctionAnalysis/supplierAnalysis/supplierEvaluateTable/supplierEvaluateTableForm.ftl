<#compress>

    <meta charset="UTF-8">  
  
	<link rel="stylesheet" href="css/table.min.css" type="text/css" />
	<script type="text/javascript">
		<#include "sco/common/masterDataType.js" />
		<#include "sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTable/supplierEvaluateTableForm.js" />
	</script>

	<div id="supplier_toolbar" style="margin:5px 5 5px 5;padding:2px;height:150px" >
		<form id="publishSupplierEvaluateTable_form">
			<table >
				<tr>
					<td colspan='4' style="text-align:left">
						请选择需要发布考评表的供应商：
					</td>
				</tr>
				<tr>
					<td style="text-align:right;width:90px">
						供应商名称:
					</td>
					<td style="width:100px;">
						<input type="text" id="sName"  filterName="sName" style="width:100px;" />
					</td>
					<td style="text-align:right;width:90px">
						供应商编号:
					</td>
					<td style="width:100px;">
						<input type="text" id="sCode" filterName="sCode" style="width:100px;" />
					</td>
				</tr>
				<tr>
					<td style="width:190px;padding-left:40px" colspan='2' >
						<input type="radio" name="fenlei" value="SP" id="SP" onclick="publishSupplierEvaluateTableFn.changeSelect(this)"/>
						<label for="SP">商品供应商查询条件</label>
					</td>
					<td style="width:190px;padding-left:40px" colspan='2' >
						<input type="radio" name="fenlei" value="FL" id="FL" onclick="publishSupplierEvaluateTableFn.changeSelect(this)"/>
						<label for="FL">辅料供应商查询条件</label>
					</td>
				</tr>
				<tr class="spfenlei">
					<td style="text-align:right;width:90px">中分类:</td>
					<td>
						<input class="easyui-combobox filterSelect sp"  id="centreTypeCode" filterName="centreTypeCode" style="width: 100px;" 
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
							    url:'masterDataType_listCentreType_5.html'
					    " />
					</td> 
					<td style="text-align:right;width:90px">小分类:</td>
					<td>
						<input class="easyui-combobox filterSelect sp" id="smallTypeCode" filterName="smallTypeCode" style="width: 100px;"
							data-options="
								valueField:'id',
								textField:'text',
								editable:false" 
						/>
					</td>
				</tr>
				<tr  class="spfenlei">
					<td style="text-align:right;width:90px">明细类:</td>
					<td>
						<input class="easyui-combobox filterSelect sp" id="detailTypeCode" filterName="detailTypeCode" style="width: 100px;"
								data-options="
									valueField:'id',
									textField:'text',
									editable:false" 
					    />
					</td>
					<td style="text-align:right;width:90px">细分类:</td>
					<td>
						<input class="easyui-combobox filterSelect sp" id="fineTypeCode" filterName="fineTypeCode" style="width: 100px;"
							data-options="
								valueField:'id',
								textField:'text',
								editable:false" 
						/>
					</td>
				</tr>
				<tr class="flfenlei" style="display:none">
					<td style="text-align:right;width:90px">细分类:</td>
					<td colspan="3">
						<input class="easyui-combobox filterSelect fl" id="fTypeCode" filterName="fTypeCode" style="width: 100px;"
							data-options="
								valueField:'id',
								textField:'text',
								editable:false,
								url:'supplierEvaluateTable_listMinceType_5.html'"
						/>
					</td>
				</tr>
				<tr>
					<td colspan='4'><hr/></td>
				</tr>
				<tr>
					<td colspan='4'>
						<a onclick="publishSupplierEvaluateTableFn.searchSelect();" plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
							搜索
						</a>
						<a onclick="publishSupplierEvaluateTableFn.clearFilterOnDialog();" plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
							清空查询
						</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
		<table  id="publishSupplierEvaluateTable_grid"
			    fitColumns="false"
			    fit="true"
			    singleSelect="false"
				pagination = "true"
				pagePosition = 'bottom'
				pageSize = "20"
				pageList = "[ 10, 20, 30, 40 ]"
				toolbar="#supplier_toolbar"
				url='supplierEvaluateTable_listSupplier_2.html'
			    data-options="rownumbers:true">  
	        <thead>
	        	<tr>
	        		<th field="ck" checkbox="true"></th>
					<th data-options="field:'supplierCode',width:'200',align:'center'">
	                	供应商编号
	                </th>
					<th data-options="field:'supplierName',width:210,sortable:true,align:'center'">
	                	供应商名称
	                </th>
	            </tr>
	        </thead>
	    </table>
</#compress>