var ingredientItemGridUtil = null;
var editIndex = undefined;
$(document).ready(
		function() {
			//ingredientItemGridUtil = utils.grid($('#ingredientItem_grid'));
			/*ingredientItemGridUtil.initFilters({
				// onDblClickRow : ingredientItemFn.showEdit, // 双击修改
				// onClickRow : ingredientItemFn.setButtonState, // 设置按钮状态
				onLoadSuccess : function() {
					//$('#ingredientItem_grid').datagrid("clearSelections");
					
					$.each($('#ingredientItem_grid').datagrid('getRows'), function(n, value) {
						//console.info(n);
						var ed = $('#ingredientItem_grid').datagrid('getEditor', {index:n,field:'materialType'});
						var text = $(ed.target).combobox('getText');
						$('#ingredientItem_grid').datagrid('getRows')[editIndex]['text'] = text;
						$('#ingredientItem_grid').datagrid('endEdit', editIndex);
					});
				}
			});*/
			$('#ingredientItem_grid').datagrid({
				onLoadSuccess:function(){
//					console.info($('#ingredientItem_grid').datagrid('getRows'));
				}
			})
			$.extend($.fn.datagrid.defaults.editors, {
				textarea : {
					init : function(container, options) {
						var input = $('<textarea class="datagrid-editable-input" rows=' + options.rows + '></textarea>').appendTo(container);
						return input;
					},
					getValue : function(target) {
						return $(target).val();
					},
					setValue : function(target, value) {
						$(target).val(value);
					},
					resize : function(target, width) {

						var input = $(target);
						if ($.boxModel == true) {
							input.width(width - (input.outerWidth() - input.width()));
						} else {
							input.width(width);
						}
					}
				}
			});
});
var ingredientItemFn = {
	endEditing : function() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#ingredientItem_grid').datagrid('validateRow', editIndex)) {
			var ed = $('#ingredientItem_grid').datagrid('getEditor', {index:editIndex,field:'materialType'});
			var text = $(ed.target).combobox('getText');
			$('#ingredientItem_grid').datagrid('getRows')[editIndex]['text'] = text;
			$('#ingredientItem_grid').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	},
	onClickRow : function(index) {
		if (editIndex != index) {
			if (ingredientItemFn.endEditing()) {
				$('#ingredientItem_grid').datagrid('selectRow', index).datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$('#ingredientItem_grid').datagrid('selectRow', editIndex);
			}
		}
	},
	append : function() {
		if (ingredientItemFn.endEditing()) {
			$('#ingredientItem_grid').datagrid('appendRow', {
				status : 'P'
			});
			editIndex = $('#ingredientItem_grid').datagrid('getRows').length - 1;
			$('#ingredientItem_grid').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
		}
	},
	removeit : function() {
		if (editIndex == undefined) {
			return
		}
		$('#ingredientItem_grid').datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
		editIndex = undefined;
	}
};