var uploadIngredientItemFn = {
	/**
	 * 上传投料表
	 */
	uploadIngredientItem : function() {
		$("#msg").html('');
		var ingredientItemFile = $('#ingredientItemFile').val();
		if (ingredientItemFile == '') {
			$.messager.alert('提示', '请选择上传文件', 'error');
			return;
		}
		if (!E2E_checkFileExt_custom(ingredientItemFile, [ '.XLSX' ])) {
			$.messager.alert('提示', '上传文件格式不正确,请上传07版本及以上的Excel文件', 'error');
			return;
		}
		// 判断核算表编号是否为空
		if ($('#ingredientCode').val() == '') {
			if ($('#accountingType').attr('checked') == 'checked') {
				$('#inlandImport').val('INLAND');
			} else {
				$('#inlandImport').val('IMPORT');
			}
		}
		var file = $("#file");
		$('#ingredientItemUploadForm').form('submit', {
			url : 'ingredient_uploadIngredientItem_2.html',
			onSubmit : function(param) {
				showLoading();
			},
			success : function(data) {
				var json = $.parseJSON(data);
				$('.msg_bg').remove();
				file.after(file.clone().val(""));
				file.remove();
				$('#ingredientItemFile').val('');
				$("#msg").html("导入消息:<br>" + json.msg);
			}
		});
	}
};