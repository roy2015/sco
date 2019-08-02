$(document).ready(function(){
	$('#example1').timeliner();
	try {
		$('.node1').trigger('click');
	} catch (e){}
});
var timelinerFn = {
	click1:function(){
		 $('#intention-1').panel("open");
		 $('#intention-2').panel("close");
		 
		 $('#attachment_grid').datagrid('reload');
		 $('#attachmentFiles_grid').datagrid('reload');
	},
	click2:function(){
		
		 $('#intention-1').panel("close");
		 
		 $.post('wlInfoNew_ifApproved_2.html', {
			 applicationCode : '${applicationCode}'
		 }, function(data) {
			if (data.success) {
				$('#intention-2').panel("open");
				utils.grid($('#fastAdjustMerGrid')).initFilters({
					noParamCanSort:true,
					onLoadSuccess : function() {
						$('#fastAdjustMerGrid').datagrid('clearSelections');
					}
				});// 启用查询条件
				utils.grid($('#fastAdjustWlInfoGrid')).initFilters({
					noParamCanSort:true,
					onLoadSuccess : function() {
						$('#fastAdjustWlInfoGrid').datagrid('clearSelections');
					}
				});// 启用查询条件
			} else {
				$.messager.alert("提示", "OA审批尚未通过，不可操作物料信息界面");
			}
		},"json");
	}
	
};