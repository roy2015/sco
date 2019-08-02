$(document).ready(function(){
	var importmissionGrid=$('#importmission_grid');
	utils.grid(importmissionGrid).initFilters({
			onLoadSuccess:function(data){
				if(data.msg!==""){
					parent.publicAlert('提示','以下文件导入完成：'+data.msg);
				}  
			}
	});
});
var importmission = {

	// 设置importmissionMaster
		importmissionMaster : function() {
		var grid = $('#importmission_grid');
		var importmission = utils.grid(grid).getSelectedRecord();
		if (importmission == null)
			return;
		var p = parent.sy.dialog({
			href : 'go_importmission_importmissionlist.html',
			width : 600,
			height : 400,
			resizable : true,
			onLoad : function() {
				var importmissionGrid = p.find('#importmission_grid');
				p.find("#importmission_toolbar").remove();
				importmissionGrid.datagrid({
					url : 'importmission!run.do?dotyp=2&ac=importmissionMaster',
					queryParams : {
						clientId : importmission.clientId
					},
					onLoadSuccess : function(data) {
						utils.grid(importmissionGrid).selectRows(function(row) {
							return row.checked == true;
						});
					}
				});
			}
		});
	},
	// 清除查询
	clearSearch : function() {
		var grid = $('#importmission_grid');
		utils.grid(grid).clearFilter();
	}
};