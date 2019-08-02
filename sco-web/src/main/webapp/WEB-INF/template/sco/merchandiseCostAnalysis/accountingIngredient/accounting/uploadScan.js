var uploadScanFn = {
	/**
	 * 上传核算/投料表扫描版
	 */
	uploadScan : function() {
		var file = $('#file');
		if ($('#scanFile').val() == '') {
			$.messager.alert('提示', '请选择上传文件', 'error');
			return;
		}
		$("#uploadScanForm").form('submit', {
			url : 'accounting_uploadAccountingIngredientScan_2.html',
			onSubmit : function(param) {
				showLoading();
			},
			success : function(data) {
				var json = $.parseJSON(data);
				$('.msg_bg').remove();
				if (json.success) {
					parent.messagerShow({
						title : '操作成功!',
						msg : json.msg
					});
					$('#scanFile').val('');
					file.after(file.clone().val("")); 
					file.remove();
					$('#addAccounting_grid').datagrid('load');
				}
			}
		});
	}
};