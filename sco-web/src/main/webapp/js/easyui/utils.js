var utils = {
	// 获取一个Jquery对象
	getJqueryObj : function(idOrObj) {
		if (typeof idOrObj === "string") {
			if (idOrObj.indexOf("#") != 0)
				idOrObj = "#" + idOrObj;
			idOrObj = $(idOrObj);
		}
		return idOrObj;
	},
	// 有关表格的操作函数
	grid : function(grid) {
		grid = utils.getJqueryObj(grid);// 获取Jquery对象
		var gridUtil = {};// 重新创建该工具的包装类
		// 设置当前编辑的行号
		var setEditRowIndex = function(editIndex) {
			grid.data("editIndex", editIndex);
		};
		// 清除当前编辑的行号
		var clearEditRowIndex = function() {
			grid.data("editIndex", null);
		};
		// 获得当前编辑的行号
		var getEditRowIndex = function() {
			return grid.data("editIndex");
		};
		// 通过行号获取行记录 Evan添加
		gridUtil.getRowByIdx = function(rowIdx) {
			return grid.datagrid("getRows")[rowIdx];
		};
		gridUtil.updateRow = function(rowIdx, param) {
			grid.datagrid("updateRow", {
				index : rowIdx,
				row : param
			});
		};
		// 表格中选中记录
		gridUtil.getSelectedRecord = function(silent) {
			var rows = grid.datagrid('getSelections');
			if (rows.length == 1) {
				return rows[0];
			} else if (rows.length > 1 && !silent) {
				parent.sy.messagerAlert('提示', '同一时间只能操作一条记录！', 'error');
			} else if (!silent) {
				parent.sy.messagerAlert('提示', '请选择要操作的记录！', 'error');
			}
			return null;
		};
		// 表格中选中记录的ID字符串
		gridUtil.getSelectedIdString = function(id_name) {
			id_name = id_name || 'id';// 'id'为true，所以只有传入的字符串不为空时，该表达式才为true
			var rows = grid.datagrid('getSelections');
			var ids = null;
			for (var i = 0; i < rows.length; i++) {
				ids = ids == null ? "" : (ids + ",");
				ids += "'" + rows[i][id_name] + "'";
			}
			return ids;
		};
		// 表格中选中记录的ID数组
		gridUtil.getSelectedIdArr = function(id_name) {
			id_name = id_name || 'id';
			var rows = grid.datagrid('getSelections');
			var ids = [];
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i][id_name]);
			}
			return ids;
		};
		// 获得选中的单行行号
		gridUtil.getSelectedRowIndex = function(silent) {
			var r = gridUtil.getSelectedRecord(silent);
			if (r == null)
				return null;
			return grid.datagrid('getRowIndex', r);
		};
		gridUtil.selectRows = function(filter) {
			var rows = grid.datagrid('getRows');
			for (var i = 0; i < rows.length; i++) {
				if (filter(rows[i])) {
					grid.datagrid('selectRow', i);
				}
			}
		};
		// 获取真正的GRIDVIEW
		gridUtil.getGridView = function() {
			return grid.parent().find('.datagrid-header');// .prev(".datagrid-view2");
		};
		// 筛选
		gridUtil.setFooterStyle = function() {
			var tr = gridUtil.getGridView().find('.datagrid-ftable tr');
			tr.addClass("datagrid-td-rownumber");
			tr.find("div").addClass(
					"datagrid-cell-rownumber my-datagrid-footer");
		};
		// 筛选
		gridUtil.doFilter = function(param) {
			if (param) {
				grid.datagrid('load', param);
			} else {// edit by eric.zhu
				grid.datagrid('load', gridUtil.getFilterValue());
			}
		};
		// 筛选width extra param
		gridUtil.doFilterWithExtParam = function(extraParam) {
			var param = gridUtil.getFilterValue();
			param = $.extend(extraParam);
			gridUtil.doFilter(param);
		};
		// 刷新
		gridUtil.refresh = function() {
			grid.datagrid('reload');
		};
		// 清空筛选框
		gridUtil.clearFilter = function(filterName) {
			// 重置组件值
			var resetFilter = function(filter) {
				if (filter == null)
					return;
				if (filter.is("select")) {// 是否是select元素
					filter.val("");
				} else if (filter.hasClass("filterSelect")) {
					filter.combo("reset");
				} else if (filter.hasClass("easyui-datebox")) {
					// edit by eric.zhu
					filter.datebox("reset");
				} else {
					filter.val("");
				}
			};
			if (filterName) {
				resetFilter(gridUtil.getFilter(filterName));
			} else {
				var filters = gridUtil.getFilter();
				// edit by eric.zhu
				for (var i = 0; i < gridUtil.extFilterIds.length; i++) {
					// add extra filter
					filters.push($("#" + gridUtil.extFilterIds[i]));
				}
				for (var i = 0; i < filters.length; i++) {
					var autoClear = filters[i].attr("autoClear");
					if (autoClear === "false" || autoClear === false) {
					} else {
						resetFilter(filters[i]);// 自动清空查询
					}
				}
			}
			gridUtil.doFilter(gridUtil.getFilterValue());// 清空过滤条件后在进行一次查询
		};
		// 获取选择框的值
		gridUtil.getFilterValue = function(filterName) {
			// 获取filter根据filterName
			if (filterName) {
				var filter = gridUtil.getFilter(filterName);
				if (filter != null)
					return filter.val();
				else
					return null;
			}
			var filters = gridUtil.getFilter();
			// edit by eric.zhu
			for (var i = 0; i < gridUtil.extFilterIds.length; i++) {
				// add extra filter
				filters.push($("#" + gridUtil.extFilterIds[i]));
			}
			var values = {};
			for (var i = 0; i < filters.length; i++) {
				var name = filters[i].attr("filterName");
				var value;
				if (filters[i].is("select")) {
					if (filters[i].hasClass("easyui-combogrid")) {// combogrid
						if (filters[i].combogrid("options").multiple) {// 多选
							value = filters[i].combogrid("getValues") + "";
						} else {// 单选
							value = filters[i].combogrid("getValue");
						}
					} else {
						value = filters[i].val();
					}
				} else if (filters[i].hasClass("filterSelect")) {// select过滤框
					if (filters[i].combo("options").multiple) {// 支持多选
						value = filters[i].combo("getValues") + "";
					} else {// 不支持
						value = filters[i].combo("getValue");
					}
				} else if (filters[i].hasClass("easyui-datebox")) {
					// edit by eric.zhu
					value = filters[i].datebox("getValue");
				} else {// input过滤框
					value = filters[i].val();
				}
				if (value) {
					values[name] = value;
				}
			}
			return values;
		};
		// 获取所有筛选框,filterName:筛选框的filterName属性值
		gridUtil.getFilter = function(filterName) {
			var gridView = gridUtil.getGridView();
			if (filterName) {// 判断筛选框的类型
				var filter = gridView.find("input[filterName=" + filterName
						+ "]");
				if (filter.length > 0) {// input筛选框
					return filter;
				} else {// select筛选框
					filter = gridView.find("select[filterName=" + filterName
							+ "]");
					if (filter.length > 0)
						return filter;
					return null;
				}
			}
			var filters = [];
			// 获取非冻结列
			var fieldNames = grid.datagrid('getColumnFields');
			// 获取冻结列并加到fieldNames末尾
			fieldNames = fieldNames.concat(grid.datagrid('getColumnFields',
					true));
			for (var i = 0; i < fieldNames.length; i++) {// 遍历筛选框并将其放入filters数组中
				var filterName = fieldNames[i];
				var filter = gridView.find("input[filterName=" + filterName
						+ "]");
				if (filter.length > 0) {
					filters.push(filter);
				} else {
					filter = gridView.find("select[filterName=" + filterName
							+ "]");
					if (filter.length > 0)
						filters.push(filter);
				}
			}
			return filters;
		};
		// Edit by Eric.zhu
		gridUtil.extFilterIds = [];// extra filter box id array
		gridUtil.registerExtFilters = function() {
			for (var i = 0; i < arguments.length; i++) {
				gridUtil.extFilterIds.push(arguments[i]);
			}
		};
		// 设置筛选框
		gridUtil.initFilters = function(config) {
			var config = config || {};
			var user_onResizeColumn = config.onResizeColumn;// 用户自定义的事件响应代码
			var user_onLoadSuccess = config.onLoadSuccess;// 用户自定义的事件响应代码
			var user_onBeforeLoad = config.onBeforeLoad;// 用户自定义的事件响应代码
			delete config.onResizeColumn;
			delete config.onLoadSuccess;
			delete config.onBeforeLoad;

			/*
			 * // 重新绑定click事件 var reBindClickEvents =
			 * function(el,eventName,namespace,handler){ var headerEl = $(el);
			 * if (!headerEl) {return} headerEl.each(function(index) { var
			 * oldHandler = $._data(headerEl[index],
			 * "events")[eventName][0].handler; $(this).off(eventName + "." +
			 * namespace); $(this).on(eventName + "." + namespace, handler);
			 * $(this).on(eventName, oldHandler); }); };
			 */
			var reBindToolClickEvt = function() {
				try {
					var headerEl = $(grid.refreshBtnSelector)[0];
					$(headerEl).off("click.pagination");
					$(headerEl).on("click.pagination", grid.refreshHandlers[0]);
					$(headerEl).on("click.pagination", grid.refreshHandlers[1]);
				} catch (e) {
				}
			};

			// 判断条件是否有查询条件
			var hasParam = function(param) {
				for ( var i in param) {
					if (i != "order" && i != "sort" && i != "page"
							&& i != "rows") {
						return true;
					}
				}
				return false;
			}

			var cfg = {
				onResizeColumn : function(field, width) {// 重写onResizeColumn事件，先调整过滤框的宽度
					var filter = gridUtil.getFilter(field);
					if (filter != null && filter.length > 0) {
						if (filter.is("select")) {
							filter.width(width - 20);
						} else if (filter.hasClass("filterSelect")) {
							filter.combo({
								width : width - 20
							});
						} else {
							filter.width(width - 20);
						}
					}
					// 调节列宽
					if (user_onResizeColumn)
						user_onResizeColumn(field, width);
				},
				onBeforeLoad : function(param) {
					if(grid.datagrid("getData").total == 0){
						delete param["sort"];
						delete param['order'];
					}
					reBindToolClickEvt();
					if (!config.noParamCanSort && grid.headerClicked) {//没有查询条件情况下点击排序是否允许查询进行查询
						var filterPara = gridUtil.getFilterValue();
						if ($.isEmptyObject(filterPara)) {
							grid.headerClicked = false;
							if (config.hasDataCanSort && grid.datagrid("getRows").length > 0) {//在没有查询条件但已经查询出数据的情况下是否允许排序
							} else {
								// if (!hasParam(param)) {
								return false;
							}
						}
					}
					grid.headerClicked = false;
					clearEditRowIndex();
					if (user_onBeforeLoad) {
						return user_onBeforeLoad(param);
					}
				},
				onLoadSuccess : function(data) {
					if (data.success === false) {
						$.messager.alert('Error', data.msg);
					}
					if (user_onLoadSuccess)
						user_onLoadSuccess(data);
				}
			};
			$.extend(cfg, config);
			// 让筛选框自动适应列宽
			grid.datagrid(cfg);
			// 让表格支持光标上下导航
			// grid.datagrid("keyCtr");
			var filters = gridUtil.getFilter();
			for (var i = 0; i < filters.length; i++) {
				var filter = filters[i];
				var width = filter.parent().width() - 10;// 容器宽度
				if (filter.is("select")) {
					if (width > 0) {
//						filter.width(width);
						filter.width("96%");
					}
					filter.bind("change", function(event) {
						gridUtil.doFilter(gridUtil.getFilterValue());
					});
				} else if (filter.hasClass("filterSelect")) {
					// if(width>0)filter.combobox({width:width});
					filter.combobox({
						height : 18,
						editable : false,
						onChange : function(value) {// 响应下拉框的change事件
							gridUtil.doFilter(gridUtil.getFilterValue());
						}
					});
				} else {
					if (width > 0) {
						if (filter.hasClass("filterInput")) {
							filter.width("96%");
						} else {
							filter.width(width);
						}
					}
					filter.bind("keypress", function(event) {
						if (event.keyCode == 13) {// 响应回车事件
							gridUtil.doFilter(gridUtil.getFilterValue());
						}
					});
				}
			}
			$(".txtCenter").parent().parent().css("text-align", "center");
			// 给datagrid表头重新绑定事件
			var headerEl = grid.parent().find(".datagrid-header-row > td[field] > .datagrid-cell");
			headerEl.each(function(index) {
				var handler = $._data(headerEl[index], "events")["click"][0].handler;
				$(this).off("click.datagrid");
				$(this).on("click.datagrid", function(e) {
					grid.headerClicked = true;
				});
				$(this).on("click.datagrid", handler);
			});
			grid.refreshHandlers = [];
			grid.refreshHandlers[0] = function(e) {
				grid.headerClicked = true;
			};
			try {
				grid.refreshBtnSelector = grid.parent().parent().find(".datagrid-pager.pagination > table > tbody>tr>td:last-child>a.l-btn.l-btn-plain");
				grid.refreshBtnEl = $(grid.refreshBtnSelector)[0];
				grid.refreshHandlers[1] = $._data(grid.refreshBtnEl, "events")["click"][0].handler;
				$(grid.refreshBtnEl).off("click.pagination");
				$(grid.refreshBtnEl).on("click.pagination", grid.refreshHandlers[0]);
				$(grid.refreshBtnEl).on("click.pagination", grid.refreshHandlers[1]);
			} catch (e) {
			}
		};
		// 选中被合并单元格的所有行
		gridUtil.selectAllMergedRows = function(startRowIndex) {
			var td = gridUtil.getGridView().find(
					'tr[datagrid-row-index=' + startRowIndex + '] td[rowspan]');
			for (var i = startRowIndex; i < startRowIndex
					+ parseInt(td.attr('rowspan')); i++) {
				// var
				// tr=gridUtil.getGridView().find('tr[datagrid-row-index='+i+']');
				var tr = gridUtil.getGridView().find(
						'tr[id=' + 'datagrid-row-r1-2-' + i + ']');// Jerry
				tr.addClass('datagrid-row-selected');
			}
		};
		// 合并指定列的单元格（相邻内容一致时合并）
		gridUtil.mergeCells = function(field, mergeFields) {
			// 将一个对象中的属性值连接成一个字符串
			var compareObject = function(obj1, obj2, fieldNames) {
				var same = true;
				if ($.isArray(fieldNames)) {
					$.each(fieldNames, function(i, fieldName) {
						if (obj1[fieldName] != obj2[fieldName]) {
							same = false;
							return false;
						}
					});
				} else {
					if (obj1[fieldNames] != obj2[fieldNames])
						same = false;
				}
				return same;
			};
			// 简单合并指定的多个列
			var mergeCells = function(rowIndex, mergeFields, rowSpan) {
				if (!$.isArray(mergeFields))
					mergeFields = [ mergeFields ];
				$.each(mergeFields, function(i, mergeField) {
					grid.datagrid('mergeCells', {
						index : rowIndex,// 开始合并的行
						field : mergeField,// 合并的列
						rowspan : rowSpan
					// 合并的行数
					});
				});
			};
			// 当fieldName列的值相同时，合并mergeField列
			var mergeCell = function(data, fieldName, mergeFields) {
				var oldObj = {};
				var rowIndex = 0;
				var rowSpan = 0;
				$.each(data, function(i, r) {// 遍历数据
					if (!compareObject(oldObj, r, fieldName)) {// 如果当前内容与上一行内容不一样
						if (rowSpan > 1) {// 如果待合并的行数大于1行
							mergeCells(rowIndex, mergeFields, rowSpan);
						}
						rowIndex = i;// 该行是待合并的第一行
						rowSpan = 1;// 待合并行数
						oldObj = r;// 记录待合并的内容,方便与下一行比较
					} else {
						rowSpan++;// 该行内容与上一行相同，待合并行数加一
					}
				});
				if (rowSpan > 1) {// 合并最后遗留下的来内容
					mergeCells(rowIndex, mergeFields, rowSpan);
				}
			};
			var data = grid.datagrid("getData").rows;
			if ((typeof (field) === "string" || $.isArray(field))
					&& $.isArray(mergeFields)) {
				// 值参考属性为一个，合并列为多个
				mergeCell(data, field, mergeFields);
				return;
			}
			if (typeof (field) === "string") {// 合并一个字段
				mergeCell(data, field, field);
			} else if ($.isArray(field)) {// 要合并多个字段
				$.each(field, function(i, f) {
					mergeCell(data, f, f);
				});
			}
		};
		// 开始编辑一行,未填写index时，系统自动取当前选择行，如果选择多行则报错
		gridUtil.startRowEdit = function(index) {
			if (!index && index != 0) {// 未填写index
				var index = gridUtil.getSelectedRowIndex();
				if (index == null)
					return false;
			}
			gridUtil.reject();
			grid.datagrid('selectRow', index).datagrid('beginEdit', index);
			setEditRowIndex(index);
			return true;
		};
		gridUtil.endRowEdit = function() {
			var index = getEditRowIndex();
			if (index == null)
				return;

		};
		// 添加一行defaultValues如{name:'cfj'}
		gridUtil.append = function(defaultValues) {
			var editIndex = getEditRowIndex();
			if (editIndex || editIndex == 0) {
				$.messager.alert('提示', '您正在添加记录，请保存或取消后再添加下一条！');
				return;
			}
			var values = $.extend({}, defaultValues);
			grid.datagrid('appendRow', values);
			var editIndex = grid.datagrid('getRows').length - 1;
			grid.datagrid('selectRow', editIndex).datagrid('beginEdit',
					editIndex);
			setEditRowIndex(editIndex);
		};
		// 删除一行
		gridUtil.remove = function(url, successCallback, failureCallback) {
			if (getEditRowIndex()) {
				$.messager.alert('提示', '您正在进行编辑操作，不能删除记录！');
				return;
			}
			var index = gridUtil.getSelectedRowIndex();
			if (index == null)
				return;
			$.messager.confirm('删除确认', '确认删除该记录?', function(yes) {
				if (yes) {
					grid.datagrid('cancelEdit', index).datagrid('deleteRow',
							index);
					clearEditRowIndex();
					var rows = grid.datagrid('getChanges');
					utils.post(url, rows[0], function(json) {
						grid.datagrid('acceptChanges');
						if (successCallback)
							successCallback(json);
					}, function() {
						grid.datagrid('rejectChanges');
						if (failureCallback)
							failureCallback(json);
					});
				}
			});
		};
		// 结束编辑状态，返回修改后的一条记录
		gridUtil.getChangedRecord = function() {
			var editIndex = getEditRowIndex();
			if (!editIndex && editIndex !== 0)
				return null;
			if (grid.datagrid('validateRow', editIndex)) {
				grid.datagrid('endEdit', editIndex);
				clearEditRowIndex();
				var rows = grid.datagrid('getChanges');
				if (rows.length > 0)
					return rows[0];
			} else {
				$.messager.alert('错误提示', '当前编辑的行中还存在错误！');
				return false;
			}
			return null;
		};
		// 获得当前编辑的行号
		gridUtil.getEditRowIndex = getEditRowIndex;
		// 获取一个编辑框
		gridUtil.getEditor = function(field) {
			var index = getEditRowIndex();
			if (index != null && index != undefined) {
				var e = grid.datagrid('getEditor', {
					index : index,
					field : field
				});
				return e;
			} else {
				return null;
			}
		};
		// 保存添加或修改后的内容
		gridUtil.saveChange = function(url, successCallback, failureCallback) {
			var record = gridUtil.getChangedRecord();
			if (record == null || record == false)
				return;
			utils.post(url, record, function(json) {
				grid.datagrid('acceptChanges');
				parent.messagerShow({
					title : '操作成功',
					msg : json.msg
				});
				if (successCallback)
					successCallback(json);
			}, function(json) {
				gridUtil.reject();// 取消修改
				$.messager.alert('错误提示', json.msg);
				if (failureCallback)
					failureCallback(json);
			});
		};
		// 保存记录并上传文件
		gridUtil.saveChangeWithFile = function(url, successCallback,
				failureCallback) {
			var editIndex = getEditRowIndex();
			if (editIndex == null || editIndex == undefined)
				return;
			if (!grid.datagrid('validateRow', editIndex)) {
				$.messager.alert('错误提示', '当前编辑的行中还存在错误！');
				return;
			}
			var editIndex = getEditRowIndex();
			var editors = grid.datagrid("getEditors", editIndex);
			var data = grid.datagrid("getData");
			var row = {};
			if (editIndex < data.rows.length) {
				row = data.rows[editIndex];
				delete row.fieldMap;
			}
			var fileFieldId = null;
			$.each(editors, function(i, editor) {
				if (editor.type === "fileField") {

					fileFieldId = editor.target.attr("id");
					editor.target.attr("name", "file");
				} else {
					row[editor.field] = editor.target.val();
					if (editor.target.hasClass('combo-f')) {
						row[editor.field] = editor.target.combo('getValue');
					}
				}
			});
			if ($("#" + fileFieldId).val() == "") {
				$.messager.alert('错误提示', "请选择要上传的文件！");
				return;
			}
			utils.submitFormWidthFile({
				url : url,
				fileElementId : fileFieldId,
				data : row,
				success : function(json) {
					if (json.success) {
						grid.datagrid('endEdit', editIndex);
						clearEditRowIndex();
						grid.datagrid('acceptChanges');
						parent.messagerShow({
							title : '操作成功!',
							msg : json.msg
						});
						if (successCallback)
							successCallback(json);
					} else {

						$.messager.alert('错误提示', json.msg);
						if (failureCallback)
							failureCallback(json);
					}
				}
			});
			/*
			 * $.ajaxFileUpload({url:url,secureuri:false,dataType: 'json',
			 * fileElementId:fileFieldId, data:row, success: function (json,
			 * status){ if(json.success){ grid.datagrid('endEdit', editIndex);
			 * clearEditRowIndex(); grid.datagrid('acceptChanges');
			 * parent.messagerShow({title:'操作成功!', msg:json.msg});
			 * if(successCallback)successCallback(json); }else{
			 * $.messager.alert('错误提示', json.msg);
			 * if(failureCallback)failureCallback(json); } }, error: function
			 * (data, status, e){alert(e);} });
			 */
		};
		// 取消修改内容
		gridUtil.reject = function() {
			var editIndex = getEditRowIndex();
			if (editIndex == null || editIndex == undefined)
				return;
			grid.datagrid('endEdit', editIndex);
			grid.datagrid('rejectChanges');
			clearEditRowIndex();
		};
		return gridUtil;
	},
	treegrid : function(treegrid) {
		treegrid = utils.getJqueryObj(treegrid);// 获取Jquery对象
		var treegridUtil = {};// 重新创建该工具的包装类
		// 重新加载树表
		treegridUtil.loadTreegrid = function(url, param, mapping) {
			utils.tree(treegrid).loadTree(url, param, mapping, true);
		};
		/**
		 * 支持节点拖拽 onDrop(srcId【源ID】,desId【目标ID】,action【行为】)
		 */
		treegridUtil.onDrop = function(onDrop) {
			var nodes = treegrid.treegrid('getPanel').find('tr[node-id]');
			nodes.find('span.tree-hit').bind('mousedown.treegrid', function() {
				return false;
			});
			nodes
					.draggable(
							{
								disabled : false,
								revert : true,
								cursor : 'pointer',
								proxy : function(source) {
									var p = $(
											'<div class="tree-node-proxy tree-dnd-no"></div>')
											.appendTo('body');
									p
											.html($(source).find('.tree-title')
													.html());
									p.hide();
									return p;
								},
								deltaX : 15,
								deltaY : 15,
								onBeforeDrag : function() {
									$(this).next('tr.treegrid-tr-tree').find(
											'tr[node-id]').droppable({
										accept : 'no-accept'
									});
								},
								onStartDrag : function() {
									$(this).draggable('proxy').css({
										left : -10000,
										top : -10000
									});
								},
								onDrag : function(e) {
									if (e.button == 2)
										return;
									$(this).draggable('proxy').show();
									this.pageY = e.pageY;
								},
								onStopDrag : function() {
									$(this).next('tr.treegrid-tr-tree').find(
											'tr[node-id]').droppable({
										accept : 'tr[node-id]'
									});
								}
							})
					.droppable(
							{
								accept : 'tr[node-id]',
								onDragOver : function(e, source) {
									var pageY = source.pageY;
									var top = $(this).offset().top;
									var bottom = top + $(this).outerHeight();
									$(source).draggable('proxy').removeClass(
											'tree-dnd-no').addClass(
											'tree-dnd-yes');
									$(this).removeClass(
											'row-append row-top row-bottom');
									if (pageY > top + (bottom - top) / 2) {
										if (bottom - pageY < 5) {
											$(this).addClass('row-bottom');
										} else {
											$(this).addClass('row-append');
										}
									} else {
										if (pageY - top < 5) {
											$(this).addClass('row-top');
										} else {
											$(this).addClass('row-append');
										}
									}
								},
								onDragLeave : function(e, source) {
									$(source).draggable('proxy').removeClass(
											'tree-dnd-yes').addClass(
											'tree-dnd-no');
									$(this).removeClass(
											'row-append row-top row-bottom');
								},
								onDrop : function(e, source) {
									var action;
									if ($(this).hasClass('row-append')) {
										action = 'child';
									} else {
										action = $(this).hasClass('row-top') ? 'before'
												: 'after';
									}
									$(this).removeClass(
											'row-append row-top row-bottom');
									onDrop($(source).attr('node-id'), $(
											e.target).attr('node-id'), action);
								}
							});
		};
		return treegridUtil;
	},
	tree : function(tree) {
		tree = utils.getJqueryObj(tree);// 获取Jquery对象
		var treeUtil = {};// 重新创建该工具的包装类
		// 重新加载树
		treeUtil.loadTree = function(url, param, mapping, isTreegrid) {
			// 对象的属性重命名,recursion表示要递归的属性名（注意，如果该属性名也是要改名的，则此处应是改名后的属性名）
			var renameProperties = function(obj, nameMapping, recursion) {
				obj["attributes"] = {
					url : null
				};
				obj["state"] = "open";
				$.each(nameMapping, function(i, v) {
					var oldname = v[0];
					var newname = v[1];
					if (newname !== oldname && obj[oldname] != undefined) {
						obj[newname] = obj[oldname];
						delete obj[oldname];
					}
				});
				if (typeof recursion == "string" && obj[recursion]) {
					var arr = obj[recursion];
					$.each(arr, function(i, v) {
						arr[i] = renameProperties(arr[i], nameMapping,
								recursion);
					});
					obj[recursion] = arr;
				}
				return obj;
			};
			mapping = mapping || {};
			var idName = mapping.idName || "id";// node id, which is important
												// to load remote data
			var textName = mapping.textName || "text"; // node text to show
			var stateName = mapping.stateName || "state"; // node state,
															// 'open' or
															// 'closed', default
															// is 'open'. When
															// set to 'closed',
															// the node have
															// children nodes
															// and will load
															// them from remote
															// site
			var checkedName = mapping.checkedName || "checked"; // Indicate
																// whether the
																// node is
																// checked
																// selected.
			var childrenName = mapping.childrenName || "children"; // an array
																	// nodes
																	// defines
																	// some
																	// children
																	// nodes
			var nameMapping = [ [ idName, "id" ], [ textName, "text" ],
					[ stateName, "state" ], [ checkedName, "checked" ],
					[ childrenName, "children" ] ];
			$.getJSON(url, param, function(json) {
				var arr = json.rows;
				var nodes = [];
				$.each(arr, function(i, v) {
					nodes.push(renameProperties(v, nameMapping, "children"));
				});
				if (isTreegrid == true) {
					tree.treegrid("loadData", nodes);
				} else {
					tree.tree("loadData", nodes);
				}
			});
		};
		return treeUtil;
	},
	// 表单对话框操作封装
	formDlg : function(formDlg) {
		formDlg = utils.getJqueryObj(formDlg);// 获取Jquery对象
		var formDlgUtil = {};// 重新创建该工具的包装类
		// 配置为修改对话框
		formDlgUtil.asEditForm = function() {
			formDlg.data("formType", 2);
		};
		// 配置为新增对话框
		formDlgUtil.asInsertForm = function() {
			formDlg.data("formType", 1);
		};
		// 检查当前是否为修改对话框
		formDlgUtil.isEditForm = function() {
			return formDlg.data("formType") == 2;
		};
		// 弹出窗体，并按指定HREF加载窗体内容
		formDlgUtil.showDialog = function(title, href, params, config) {
			var cfg = config || {};
			cfg.title = title;
			formDlg.dialog(cfg);

			formDlg.data('params', params);
			formDlg.dialog("open");
			if (params) {
				href = href + "?" + params;
			}
			formDlg.dialog('refresh', href);
		};
		// 关闭该窗体
		formDlgUtil.close = function() {
			formDlg.dialog('close');
		};
		// 提交该窗体内的表单
		formDlgUtil.submitForm = function(formId, url, extraData, callback) {
			var form = utils.form($('#' + formId));
			form.submit(url, function(params) {
				$.extend(params, extraData);

			}, function(json) {
				if (callback)
					callback(json);
			});
		};
		return formDlgUtil;
	},
	// Form表单操作封装
	form : function(form) {
		form = utils.getJqueryObj(form);// 获取Jquery对象
		var formUtil = {};// 重新创建该工具的包装类
		// 提交表单，返回JSON数据
		formUtil.submit = function(url, onSubmit, successCallback,
				failureCallback) {
			form.form('submit', {
				url : url,
				onSubmit : function(params) {

					if (onSubmit)
						onSubmit(params);
					return $(this).form('validate');
				},
				success : function(data) {
					var json = $.parseJSON(data);
					if (json.success) {
						parent.messagerShow({
							title : '操作成功',
							msg : json.msg
						});
						if (successCallback)
							successCallback(json);
					} else {
						$.messager.alert('操作失败', json.msg);
						if (failureCallback)
							failureCallback(json);
					}
				}
			});
		};
		return formUtil;
	},
	// ajax操作
	post : function(url, dataObj, successCallback, failureCallback) {
		$.post(url, dataObj, function(json) {
			if (json.success) {
				parent.messagerShow({
					title : '操作成功!',
					msg : json.msg
				});
				if (successCallback)
					successCallback(json);
			} else {
				$.messager.alert('操作失败', json.msg);
				if (failureCallback)
					failureCallback(json);
			}
		}, "json");
	},
	// 创建对话框并显示(inParent表示是否在父窗口中显示)
	showDlg : function(options, inParent) {
		var dlg = null;
		var opts = $.extend({
			modal : true,
			onClose : function() {
				dlg.dialog('destroy');
			}
		}, options);
		var dlgDiv = null;
		if (inParent) {
			dlgDiv = parent.$('<div/>');
		} else {
			dlgDiv = $('<div/>');
		}
		if (options.html) {
			dlgDiv.append(options.html);
		}
		dlg = dlgDiv.dialog(opts);
		return dlg;
	},
	// AJAX方式提交文件
	submitFormWidthFile : function(config) {
		config = config || {};
		var formId = "fileForm" + new Date().getTime();
		var form = $('<form  action="" method="POST" id="' + formId
				+ '" enctype="multipart/form-data"></form>');
		var oldElement = $('#' + config.fileElementId);
		var newElement = oldElement.clone();
		$(oldElement).before(newElement);
		newElement.attr('id', config.fileElementId);
		$(oldElement).appendTo(form);
		form.appendTo($('body'));
		form.form('submit', {
			url : config.url,
			onSubmit : function(params) {
				if (config.data)
					$.extend(params, config.data);
				if ($.isFunction(config.onSubmit))
					config.onSubmit(params);
			},
			success : function(data) {
				json = $.parseJSON(data);
				form.remove();
				if ($.isFunction(config.success))
					config.success(json);
			}
		});
	},
	// 操作确认
	confirm : function(title, msg, yesCallback, noCallback) {
		$.messager.confirm(title, msg, function(yes) {
			if (yes) {
				yesCallback();
			} else if ($.isFunction(noCallback)) {
				noCallback();
			}
		});
	},
	/**
	 * 操作确认，可输入多行信息,cfg对象中可以有以下属性： title 标题 msg 提示消息 defaultMsg 默认文本内容
	 * requireMsg 文本内容是否必须 msgMaxLength 文本内容最大长度 yesCallback 确定回调函数 noCallback
	 * 取消后回调函数 width 宽度 height 高度
	 */
	prompt : function(cfg) {
		var html = '<form><div class="messager-body panel-body panel-body-noborder"><div class="messager-icon messager-question"></div><div>'
				+ cfg.msg
				+ '<div><br/><textarea rows=5 class="messager-input easyui-validatebox" data-options="required:'
				+ cfg.requireMsg
				+ ',validType:\'length[0,'
				+ (cfg.msgMaxLength || 500)
				+ ']\'">'
				+ (cfg.defaultMsg || "")
				+ '</textarea></div></form>';
		var dlg = utils.showDlg({
			title : cfg.title,
			width : cfg.width || 350,
			height : cfg.height || 220,
			html : html,
			buttons : [ {
				text : '确定',
				handler : function() {
					var txbox = dlg.find("textarea");
					var value = txbox.val();
					if (cfg.requireMsg && !txbox.validatebox("isValid")) {
						txbox.validatebox("validate");
						return;
					}
					if (cfg.yesCallback)
						cfg.yesCallback(value);
				},
				iconCls : 'save'
			}, {
				text : '取消',
				handler : function() {
					if (cfg.noCallback) {
						cfg.noCallback();
					} else {
						dlg.dialog("close");
					}
				},
				iconCls : 'cancel'
			} ],
			onOpen : function() {
				var txbox = $(this).find("textarea");
				txbox.validatebox();
			}
		});
		return dlg;
	},
	// Even.Zhao 新增 日期过滤工具
	dateFilter : {
		// 设置最大日期
		setMaxDateValue : function() {
			var minValue = $(this).datebox("getValue");
			var maxId = "max" + $(this).attr("id").substr(3);
			var maxValue = $("#" + maxId).datebox("getValue");
			if (utils.dateFilter.compareDate(minValue, maxValue)) {
				$("#" + maxId).datebox("setValue", minValue);
			}
		},
		// 设置最小日期
		setMinDateValue : function() {
			var maxValue = $(this).datebox("getValue");
			var mimId = "min" + $(this).attr("id").substr(3);
			var minValue = $("#" + mimId).datebox("getValue");

			if (utils.dateFilter.compareDate(minValue, maxValue)) {
				$("#" + mimId).datebox("setValue", maxValue);
			}
		},
		// 比较日期大小
		compareDate : function(firstDate, secondDate) {
			if (firstDate == "" || secondDate == "") {
				return true;
			}
			return ((new Date(firstDate.replace(/-/g, "//"))) > (new Date(
					secondDate.replace(/-/g, "//"))));
		}
	},
	// 数值过滤框
	numberFilter : {
		// 设置最大数值
		setMaxValue : function() {
			var minValue = $(this).combobox("getValue");
			var maxId = "max" + $(this).attr("id").substr(3);
			var maxValue = $("#" + maxId).combobox("getValue");
			if (utils.numberFilter.compare(minValue, maxValue)) {
				$("#" + maxId).combobox("setValue", minValue);
			}
		},
		// 设置最小数值
		setMinValue : function() {
			var maxValue = $(this).combobox("getValue");
			var mimId = "min" + $(this).attr("id").substr(3);
			var minValue = $("#" + mimId).combobox("getValue");
			if (utils.numberFilter.compare(minValue, maxValue)) {
				$("#" + mimId).combobox("setValue", maxValue);
			}
		},
		// 比较数值大小
		compare : function(first, second) {
			if (first == "" || second == "") {
				return true;
			}
			return first > second;
		}
	},
	// 过滤搜索
	search : function(datagrid, selector) {
		var param = datagrid.getFilterValue();
		var mins = $("input[id^=min]");
		var maxs = $("input[id^=max]");
		if (mins != null && maxs != null) {
			for (var i = 0; i < mins.length; i++) {
				for (var j = 0; j < maxs.length; j++) {
					if ($(mins[i]).attr("id").substr[3] == $(maxs[i])
							.attr("id").substr[3]) {
						param[$(mins[i]).attr("id")] = $(mins[i]).datebox(
								"getValue");
						param[$(maxs[i]).attr("id")] = $(maxs[i]).datebox(
								"getValue");
					}
				}
			}
		}
		$(selector).datagrid("reload", param);
	},
	// 上传组件
	uploadComponent : {
		// 添加文件输入框
		addFileInput : function(addBtn) {
			var lastTd = $("table#uploadTable tr:first td:last").clone(true);
			var lastTr = $("table#uploadTable tr:first").after(
					"<tr><td></td><td colspan='2'>&nbsp;&nbsp;</td></tr>");
			lastTr.next().children("td").eq(1).replaceWith(lastTd);
			$("input[value=Upload]:last")
					.replaceWith(
							"<input type='button' value='-'"
									+ " onclick='utils.uploadComponent.removeFileInput(this);' class='btn btn-primary btn-mini'>");
		},
		removeFileInput : function(removeBtn) {
			$(removeBtn).parent().parent().remove();
		}
	}

};
