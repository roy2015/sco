$(document).ready(function(){
	$('#example1').timeliner();
	$('.node1').trigger('click');
});
var timelinerFn = {
	
	click1:function(){
	 $('#intention-1').panel("open");
	 $('#intention-2').panel("close");
	},
	
	click2:function(){
		 $('#intention-1').panel("close");
		 $.post('wlInfoNew_ifApproved_2.html', {
			 applicationCode : '${applicationCode}'
		 }, function(data) {
			if (data.success) {
				$('#intention-2').panel("open");
				utils.grid($('#fastImportMerGrid')).initFilters({
					onLoadSuccess : function() {
						$('#fastImportMerGrid').datagrid('clearSelections');
					}
				});// 启用查询条件
				utils.grid($('#fastImportWlInfoGrid')).initFilters({
					onLoadSuccess : function() {
						$('#fastImportWlInfoGrid').datagrid('clearSelections');
					}
				});// 启用查询条件
			} 
			else {
				$.messager.alert("提示", "OA审批尚未通过，不可操作物料信息界面");
			}
		},"json");
	}
};