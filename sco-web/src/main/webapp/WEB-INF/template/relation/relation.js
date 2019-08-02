var relationGridUtil=null;
$(document).ready(function(){
	relationGridUtil=utils.grid($('#relation_grid'));
	relationGridUtil.initFilters({
		onLoadSuccess:function(){
			$('#retailer_grid').datagrid("clearSelections");
		}
	});
});

var relationFn = {
	//清除查询
	clearFilter:function(){
		relationGridUtil.clearFilter();
	}
};