<#compress>
<!DOCTYPE html>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <link rel="stylesheet" href="css/table.min.css" type="text/css" />
    <#include "inc/easyui.ftl" />
    <script type="text/javascript" >
		<#include "sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTemplate/supplierEvaluateTemplate/supplierEvaluateTemplateModel.js" />
    </script>
</head>
<body>
	<!-- 供应商考评表管理主表 --> 
	<div id="supplierEvaluateTemplate_toolbar">
	<#-- 查询条件 -->
		<form id="signedQty_search" method="post">
			<table style="width: 820px;">
				<tr>
					<td style="text-align: right; width: 100px">考评模板名称:</td>
					<td style="width: 110px;"><input class="easyui-validatebox" filterName="templateName" id="templateName" style="width: 120px;" /></td>
					<td style="text-align: right; width: 100px;">考评模板状态:</td>
					<td><input class="easyui-combobox filterSelect" filterName="status" id="status" style="width: 124px;"
						data-options="
								valueField:'id',
								textField:'text',
								
								editable:false,
							    url:'businessComBox_evaluateTemplateStatus_5.html'
					    " />
					</td>
				</tr>
				<tr>
					<td style="text-align: right; width: 79px;">考评模板创建日期:</td>
					<td><input class="easyui-datebox list-input" filterName="minCreated" id="minCreated"
						data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" /> 
					</td>
					<td style="text-align: right; width: 79px;">至:</td>
					<td> <input class="easyui-datebox list-input" filterName="maxCreated"
						id="maxCreated" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" /></td>
				</tr>	
				<tr>
					<td style="text-align: right; width: 100px;">考评目标发布日期:</td>
					<td><input class="easyui-datebox list-input" filterName="minPublishDate" id="minPublishDate"
						data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMaxDateValue" style="width: 124px;" /> 
					</td>
					<td style="text-align: right; width: 100px;">至:</td>
					<td> <input class="easyui-datebox list-input" filterName="maxPublishDate"
						id="maxPublishDate" data-options="required:false,editable:false,onHidePanel:utils.dateFilter.setMinDateValue" style="width: 124px;" /></td>
				</tr>
			</table>
		<a id="showEdit" onclick="supplierEvaluateTemplateFn.search();"plain="true" class="easyui-linkbutton" data-options="iconCls:'search'" >
			搜索模板
		</a>
		<a id="clear" onclick="supplierEvaluateTemplateFn.clearFilter();"plain="true" class="easyui-linkbutton" data-options="iconCls:'clear'" >
			清空查询
		</a>
		<a onclick="supplierEvaluateTemplateFn.merchandiseShowInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			创建考评表模版-商品
		</a>
		<a onclick="supplierEvaluateTemplateFn.accessoryShowInsert();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			创建考评表模版-辅料
		</a>
		<a id="work" onclick="supplierEvaluateTemplateFn.showEdit();"plain="true" class="easyui-linkbutton" data-options="iconCls:'edit'">
			修改考评表模板
		</a>
		<a id="work" onclick="supplierEvaluateTemplateFn.publish();" plain="true" class="easyui-linkbutton" data-options="iconCls:'add'">
			发布考评表模版
		</a>
		<a id="work" onclick="supplierEvaluateTemplateFn.closeTemplate();"plain="true" class="easyui-linkbutton" data-options="iconCls:'save'">
			关闭考评表模板
		</a>
		<a id="work" onclick="supplierEvaluateTemplateFn.remove();"plain="true" class="easyui-linkbutton" data-options="iconCls:'delete'">
			删除模版草稿
		</a>
		</form>
	</div>
    <table  id="supplierEvaluateTemplate_grid"
		    class="easyui-datagrid"
		    fit="true"
			pagination = "true"
			pagePosition = 'bottom'
			pageSize = "20"
			pageList = "[ 10, 20, 30, 40 ]"
			toolbar="#supplierEvaluateTemplate_toolbar"
		    data-options="rownumbers:true">  
        <thead>
        	<tr>
        		<th align="center" data-options="field:'check',width:40,checkbox:true"></th>
				<th data-options="field:'templateCode',width:150,sortable:true,formatter:supplierEvaluateTemplateFn.change" >
                	考评模版编号
                </th>
				<th data-options="field:'templateName',width:250,sortable:true">
                	考评模版名称
                </th>
				<th data-options="field:'evaluateDate',width:150,sortable:true">
                	考核周期
                </th>
				<th data-options="field:'publishDate',width:150,sortable:true">
                	考评模版发布日期
                </th>
                <th data-options="field:'status',width:150,sortable:true">
                	考评模版状态
                </th>
            </tr>
        </thead>
    </table>
</body>
</html>
</#compress>