<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <#include "inc/easyui.ftl" />
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
    <script type="text/javascript" >
		<#include "sco/dataMaintenance/systemManagerData/supplierCertificateType/supplierCertificateTypeModel.js" />
    </script>
</head>
<body>
	<!-- 证件名称管理主表 --> 
	<div id="supplierCertificateType_toolbar">
		<a id="insert" onclick="supplierCertificateTypeFn.showInsert();"plain="true" class="easyui-linkbutton" data-options="iconCls:'add'" >
			新增
		</a>
		<a id="remove" onclick="supplierCertificateTypeFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'" >
			删除
		</a>
	</div>
    <table  id="supplierCertificateType_grid"
		    fit="true"
		    fitColumns="true"
		    singleSelect="false"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#supplierCertificateType_toolbar"
			url='supplierCertificateType_listSupplierCertificateType_2.html'
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th align="center" data-options="width:40,checkbox:true"></th>
				<th data-options="field:'certificateTypeName',width:200">
                	证件名称
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>