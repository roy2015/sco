<#compress>
<link rel="stylesheet" type="text/css" href="${contextPath}css/icons.css"  />
<script type="text/javascript" charset="UTF-8">
$(document).ready(function(){
    $('#tree').tree({
		lines : false,
		onClick:function(node){
			addTab(node);
		},
		onDblClick : function(node) {
			if (node.state == 'closed') {
				$(this).tree('expand', node.target);
			} else {
				$(this).tree('collapse', node.target);
			}
		}
	});
});
       
</script>
<div class="easyui-panel" fit="true" border="false">
					<ul id="tree" 
					url='func!run.do?dotyp=5&ac=loadMyMenu'
					 style="margin-top: 5px;"></ul>		 
</div>
<div style="height:100px">
		<table class="tableForm">
			<tr>
			    <th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("public.property.currentAgent")}</span></th>
				<td><input id="objectTyp" name="objectTyp" value="" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,data:Dic.OrgTypes,valueField:'id',value:'V'"/></td>
				<th align="right" style="width: 80px;"><span class="txtCenter">${action.getText("public.property.currentVendor")}</span></th>
				<td><input id="objectTyp" name="objectTyp" value="" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,data:Dic.OrgTypes,valueField:'id',value:'V'"/></td>
			</tr>
		</table>	 
</div>
</#compress>