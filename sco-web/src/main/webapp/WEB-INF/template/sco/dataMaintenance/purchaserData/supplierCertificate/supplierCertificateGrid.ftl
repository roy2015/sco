<#compress>
<!DOCTYPE html>  
<html>  
	<head>  
	    <meta charset="UTF-8">  
	    <#include "inc/easyui.ftl" />
	    <script type="text/javascript" >
			<#include "sco/dataMaintenance/purchaserData/supplierCertificate/supplierCertificate.js" />
	    </script>
	    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
	</head>
	<body>
		<div id="supplierCertificate_toolbar" style="margin:5px 5 5px 5;padding:3px;overflow:auto;">
			<form id="supCerti_search">
				<table class="table table-condensed" style="width:1000px;table-layout:fixed;">
					<tr>
						<td style="text-align:right;width: 125px;">供应商编号：</td>
						<td style="width:125px;"><input class="easyui-validatebox" filterName="supplierCode" id="supplierCode" style="width:120px;" /></td>
						<td style="text-align:right;width: 125px;">供应商名称：</td>
						<td style="width:125px;"><input class="easyui-validatebox" filterName="supplierName" id="supplierName" style="width:120px;" /></td>
						<td style="text-align:right;width: 125px;">证件名称：</td>
						<td style="width:125px;">
							<input class="easyui-combobox filterSelect" filterName="certificateTypeCode" id="certificateTypeCode" style="width: 124px;" 
							data-options="
								valueField:'id',
								textField:'text',
								panelHeight:'220',
								editable:false,
							    url:'supplierCertificate_listSupplierCertificateType_5.html'
					    	" />
						</td>
						<td style="text-align:right;width: 125px;">证件到期日期：</td>
						<td style="width:125px;"><input class="easyui-datebox" filterName="endDate" id="endDate" data-options="editable:false" style="width:124px;"/></td>
					</tr>   						
					<tr>
						<td style="text-align:right;width: 125px">上传人：</td>
						<td style="width:125px;" colspan="7"><input class="easyui-validatebox" filterName="createby" id="createby" style="width:120px;" /></td>
					</tr>   						
					<tr>
						<td colspan="8">
							<a id="insert" onclick="supCertifiFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'">
								查看系统已有证件
							</a>
							<a onclick="supCertifiFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'">
								清空查询
							</a>
							<a id="insert" onclick="supCertifiFn.add();"plain="true" class="easyui-linkbutton" data-options="iconCls:'upload'" style="display: <#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.supplierCertificate.add")>none</#if>;">
								上传证件
							</a>
							<a id="remove" onclick="supCertifiFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete',disabled:'true'" style="display:<#if !action.hasFuncPower("com.powere2e.sco.dataMaintenance.purchaserData.supplierCertificate.del")>none</#if>;">
								删除
							</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	    <table  id="supplierCertificate_grid"
			    fit="true"
			    fitColumns="true"
			    iconCls= "icon-save"
				pagination = "true"
				pagePosition = 'bottom'
				pageSize = "20"
				pageList = "[ 10, 20, 30, 40 ]"
				toolbar="#supplierCertificate_toolbar"
				url='supplierCertificate_listSupplierCertificate_2.html'
			    data-options="rownumbers:true">  
	        <thead>
	        	<tr>
					<th rowspan="2" data-options="field:'id',checkbox:'true'">
					<th rowspan="2" data-options="field:'supplierCode',width:130,sortable:true">
	                	供应商编号
	                </th>
					<th rowspan="2" data-options="field:'supplierName',width:260">
						供应商名称
	                </th>
					<th rowspan="2" data-options="field:'certificateTypeName',width:220,formatter:supCertifiFn.downloadFile">
						证件名称
	                </th>
					<th rowspan="2" data-options="field:'createby',width:100">
						上传人
	                </th>
					<th rowspan="2" data-options="field:'created',width:100,sortable:true">
						上传日期
	                </th>
					<th rowspan="2" data-options="field:'validRegion',width:200,sortable:true">
						证件有效期
	                </th>
	            </tr>
	        </thead>
	    </table>
	</body>
</html>
</#compress>