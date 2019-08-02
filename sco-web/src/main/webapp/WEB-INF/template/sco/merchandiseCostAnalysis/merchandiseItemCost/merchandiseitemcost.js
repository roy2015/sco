var mechandiseItemCostGridUtil = null;
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
var itemValue = "material";
var costValue = "lastcost";
var isNullNpackage = false;
var isNullWpackage = false;
var isNullElsecost = false;
var isNullImportcost = false;
var isNullHistory = false;
$(document).ready(
		function() {
			// 隐藏元素
			$('#grid').hide();
			$('#historycost').hide();
			mechandiseItemCostGridUtil = utils
					.grid($('#merchandiseItemCost_grid'));
			mechandiseItemCostGridUtil.registerExtFilters("supplierCode",
					"supplierName", "merchandiseCode", "merchandiseName",
					 "companySite", "npackagName", "wpackagName",
					"elseCost", "importCost", "dxRoleCode", "dlRoleCode",
					"centreTypeCodeElse", "smallTypeCode", "detailTypeCode",
					"fineTypeCode","minQuotedDate","maxQuotedDate","applicationStatus");
			mechandiseItemCostGridUtil.initFilters({
				onBeforeLoad : function(obj) {
					// 是否第一次进入页面
					if (firstIn) {
						merchandiseItemCostFn.setButtonState();
						firstIn = false;
						return false;
					}
					// 清空查询
					if (clearSearch) {
						return true;
					}
					var length = $.param(obj).split("&").length;
					// 判断参数个数
					if (length < 0) {
						if (!firstIn) {
							$.messager.alert("提示", "请输入至少一项查询条件");
						}
						firstIn = false;
						return false;
					} else if (length == 4
							&& $.param(obj).indexOf('order') > -1) {
						return false;
					}
				},
				onLoadSuccess : function() {
					$('#merchandiseItemCost_grid').datagrid("clearSelections");
					merchandiseItemCostFn.setButtonState();
				}
			});
			$("input[name='item']").change(function() {
				itemValue = $(this).val();
				$("div[name='subItem']").hide();
				$("#" + itemValue).show();
			});
			$("input[name='cost']").change(function() {
				costValue = $(this).val();
				if (costValue == "lastcost") {
					$("#maxQuotedDate").datebox("setValue", "");
					$("#minQuotedDate").datebox("setValue", "");
				}
				$("div[name='cost']").hide();
				$("#" + costValue).show();
			});
		});
var merchandiseItemCostFn = {
	// 搜索商品及对应的核算/投料表记录
	search : function() {
		
		var param = mechandiseItemCostGridUtil.getFilterValue();
		// 判断是否选择了采购部门
		if ($('#purchaseDepartments').combobox("getValue") != null
				&& $('#purchaseDepartments').combobox("getValue") != '') {
			param.purchaseDepartments = $('#purchaseDepartments').combobox(
					"getValue");
		}
		// 判断是否选择了是否定量
		if ($('#rationed').combobox("getValue") != null
				&& $('#rationed').combobox("getValue") != '') {
			param.rationed = $('#rationed').combobox("getValue");
		}
		// 判断是否选择了采购类型
		if ($('#purchaseType').combobox("getValue") != null
				&& $('#purchaseType').combobox("getValue") != '') {
			param.purchaseType = $('#purchaseType').combobox("getValue");
		}
		// 判断是否选择了销售方式
		if ($('#saleType').combobox("getValue") != null
				&& $('#saleType').combobox("getValue") != '') {
			param.saleType = $('#saleType').combobox("getValue");
		}
		// 判断是否选择了中分类
		if ($('#centreTypeCodeElse').combobox("getValue") != null
				&& $('#centreTypeCodeElse').combobox("getValue") != '') {
			param.centreTypeCode = $('#centreTypeCodeElse')
					.combobox("getValue");
		}
		// 判断是否选择了小分类
		if ($('#smallTypeCode').combobox("getValue") != null
				&& $('#smallTypeCode').combobox("getValue") != '') {
			param.smallTypeCode = $('#smallTypeCode').combobox("getValue");
		}
		// 判断是否选择了明细类
		if ($('#detailTypeCode').combobox("getValue") != null
				&& $('#detailTypeCode').combobox("getValue") != '') {
			param.detailTypeCode = $('#detailTypeCode').combobox("getValue");
		}
		// 判断是否选择了细分类
		if ($('#fineTypeCode').combobox("getValue") != null
				&& $('#fineTypeCode').combobox("getValue") != '') {
			param.fineTypeCode = $('#fineTypeCode').combobox("getValue");
		}
		// 前半部分固定的列
		var frozen = [ [ {
			field : 'intentionCode',
			title : '商品/意向品编号',
			width : 120,
			align : 'left',
			sortable : false,
			formatter : function(value, row) {
				if (row.merchandiseCode == null) {
					return value;
				} else {
					return row.merchandiseCode;
				}
			}
		}, {
			field : 'intentionName',
			title : '商品/意向品名称',
			width : 110,
			align : 'left',
			formatter : function(value, row) {
				if (row.merchandiseCode == null) {
					return value;
				} else {
					return row.merchandiseName;
				}
			}
		}, {
			field : 'intentionSupplierCode',
			title : '供应商/意向供应商编号',
			width : 140,
			align : 'left',
			sortable : false,
			formatter : function(value, row) {
				if (row.supplierCode == null) {
					return value;
				} else {
					return row.supplierCode;
				}
			}
		}, {
			field : 'intentionSupplierName',
			title : '供应商/意向供应商名称',
			width : 140,
			align : 'left',
			formatter : function(value, row) {
				if (row.supplierName == null) {
					return value;
				} else {
					return row.supplierName;
				}
			}
		} ] ];
		// 原材料
		var material = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'materialType',
					title : '原料类型',
					width : 80,
					align : 'left'
				}, {
					field : 'materialName',
					title : '原料名称',
					width : 80,
					align : 'left'
				}, {
					field : 'materialOrigin',
					title : '原料产地',
					width : 80,
					align : 'left'
				}, {
					field : 'materialBrand',
					title : '原料品牌',
					width : 80,
					align : 'left'
				}, {
					field : 'materialLevelSpecification',
					title : '原料等级与规格',
					width : 120,
					align : 'left'
				}, {
					field : 'purchasePrice',
					title : '原料采购价格(元/kg)',
					width : 100,
					align : 'right',
					sortable : true,
					formatter : function(value){
						 			return moneyFormatter(value,4);
								}
				}, {
					field : 'inputCount',
					title : '原料投入量(kg)',
					width : 100,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,4);
					}
				}, {
					field : 'inputCost',
					title : '原料投入成本(元)',
					width : 100,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,4);
					}
				}, {
					field : 'avgCost',
					title : '平均成品原料成本(元/kg)',
					width : 120,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'inputCountProportion',
					title : '投入量占比(%)',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'moisture',
					title : '成品含水率(%)',
					width : 80,
					align : 'right',
					sortable : true
				}, {
					field : 'yield',
					title : '得率(%)',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 120,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 复合袋卷膜和拉伸膜
		var juanMo = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				},
				{
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				},
				{
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'packagType',
					title : '包装类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'packagName',
					title : '包装名称',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.packagType != null && row.packagType == 'ELSE') {
							return row.elseName;
						} else {
							return value;
						}
					}
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'texture',
					title : '具体材质&厚度',
					width : 105,
					align : 'left'
				}, {
					field : 'kgPrice',
					title : '公斤价格',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'weightProportion',
					title : '重量占比(%)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 复合袋制袋
		var zhiDai = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				},
				{
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				},
				{
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'packagType',
					title : '包装类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'packagName',
					title : '包装名称',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.packagType != null && row.packagType == 'ELSE') {
							return row.elseName;
						} else {
							return value;
						}
					}
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'texture',
					title : '具体材质&厚度',
					width : 105,
					align : 'left'
				}, {
					field : 'materialSize',
					title : '尺寸(cm)',
					width : 105,
					align : 'right'
				}, {
					field : 'weight',
					title : '单个克重(g)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'unitsPrice',
					title : '单价(元)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'kgPrice',
					title : '公斤价格(元)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'quantity',
					title : '数量',
					width : 105,
					align : 'right',
					sortable : true
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 塑拖
		var suTuo = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				},
				{
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				},
				{
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'packagType',
					title : '包装类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'packagName',
					title : '包装名称',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.packagType != null && row.packagType == 'ELSE') {
							return row.elseName;
						} else {
							return value;
						}
					}
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'texture',
					title : '具体材质&厚度',
					width : 105,
					align : 'left'
				}, {
					field : 'weight',
					title : '单个克重(g)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'kgPrice',
					title : '公斤价格(元)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'weightProportion',
					title : '重量占比(%)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 脱氧剂
		var tuoYang = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				},
				{
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				},
				{
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'packagType',
					title : '包装类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'packagName',
					title : '包装名称',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.packagType != null && row.packagType == 'ELSE') {
							return row.elseName;
						} else {
							return value;
						}
					}
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'weight',
					title : '单个克重(g)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'materialSize',
					title : '尺寸(cm)',
					width : 105,
					align : 'right'
				}, {
					field : 'unitsPrice',
					title : '单价(元)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'quantity',
					title : '数量',
					width : 105,
					align : 'right',
					sortable : true
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 内胆袋
		var neiDanDai = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				},
				{
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				},
				{
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'packagType',
					title : '包装类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'packagName',
					title : '包装名称',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.packagType != null && row.packagType == 'ELSE') {
							return row.elseName;
						} else {
							return value;
						}
					}
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'texture',
					title : '具体材质&厚度',
					width : 105,
					align : 'left'
				}, {
					field : 'materialSize',
					title : '尺寸(cm)',
					width : 105,
					align : 'right'
				}, {
					field : 'unitsPrice',
					title : '单价(元)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'quantity',
					title : '数量',
					width : 105,
					align : 'right',
					sortable : true
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 标签和纸张类
		var biaoQian = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				},
				{
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				},
				{
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'packagType',
					title : '包装类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'packagName',
					title : '包装名称',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.packagType != null && row.packagType == 'ELSE') {
							return row.elseName;
						} else {
							return value;
						}
					}
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'texture',
					title : '具体材质&克重',
					width : 105,
					align : 'left'
				}, {
					field : 'materialSize',
					title : '尺寸(cm)',
					width : 105,
					align : 'right'
				}, {
					field : 'quantity',
					title : '数量',
					width : 105,
					align : 'right',
					sortable : true
				}, {
					field : 'technologyRequirements',
					title : '工艺要求',
					width : 105,
					align : 'left'
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 其他
		var others = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				},
				{
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				},
				{
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'packagType',
					title : '包装类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'packagName',
					title : '包装名称',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.packagType != null && row.packagType == 'ELSE') {
							return row.elseName;
						} else {
							return value;
						}
					}
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];

		// 封箱带
		var fengXiang = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				},
				{
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				},
				{
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'packagType',
					title : '包装类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'packagName',
					title : '包装名称',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.packagType != null && row.packagType == 'ELSE') {
							return row.elseName;
						} else {
							return value;
						}
					}
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'unitsPrice',
					title : '单价(元/米)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'useQuantity',
					title : '使用量(元/米)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 纸箱
		var zhiXiang = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				},
				{
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				},
				{
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'packagType',
					title : '包装类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'packagName',
					title : '包装名称',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.packagType != null && row.packagType == 'ELSE') {
							return row.elseName;
						} else {
							return value;
						}
					}
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'texture',
					title : '具体材质说明',
					width : 105,
					align : 'left'
				}, {
					field : 'lwh',
					title : '长*宽*高(cm)',
					width : 105,
					align : 'right'
				}, {
					field : 'area',
					title : '纸箱用料面积(㎡)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'unitsPrice',
					title : '单价(元/只)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'ylUnitsPrice',
					title : '纸箱用料单价(元/㎡)',
					width : 140,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'specification',
					title : '箱规',
					width : 105,
					align : 'left'
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 损耗
		var sunHao = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'wastageType',
					title : '损耗类型',
					width : 80,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 水电煤
		var shuiDianMei = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'water',
					title : '耗水(元/t成品)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'electricity',
					title : '耗电(元/t成品)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'gas',
					title : '耗气(元/t成品)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'coal',
					title : '耗煤(元/t成品)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oil',
					title : '耗油(元/t成品)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'total',
					title : '合计(kg成品)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 设备折旧及维护
		var sbzjwh = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'totalPrice',
					title : '设备总价(万元)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'ageLimit',
					title : '折旧年限(年)',
					width : 105,
					align : 'right',
					sortable : true
				}, {
					field : 'depreciation',
					title : '折旧值(万元/年)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'capacity',
					title : '产能值(t成品/年)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'total',
					title : '合计折旧值(元/kg成品)',
					width : 120,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 人工
		var renGong = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'manpowerCount',
					title : '车间工人数(人次)',
					width : 105,
					align : 'right',
					sortable : true
				}, {
					field : 'avgWage',
					title : '平均工资(元/人/月)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'monthCapacity',
					title : '月产量(t)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'unitsWage',
					title : '每kg成品(元/kg)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 管理和利润
		var guanLi = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'region',
					title : '进货地区',
					width : 105,
					align : 'left'
				}, {
					field : 'proportion',
					title : '占比(%)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 运输
		var yunShu = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'companySite',
					title : '工厂所在地',
					width : 105,
					align : 'left'
				}, {
					field : 'region',
					title : '进货地区',
					width : 105,
					align : 'left'
				}, {
					field : 'sumKm',
					title : '总公里数',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'unitsCost',
					title : '单位成本',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		// 税收
		var shuiShou = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'left',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'region',
					title : '进货地区',
					width : 105,
					align : 'left'
				}, {
					field : 'proportion',
					title : '占比(%)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'taxRate',
					title : '税率(%)',
					width : 105,
					align : 'right',
					sortable : true,
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				},

				{
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		
		// 出厂
		var chuchang = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'price',
					title : '商品报价',
					width : 80,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'currency',
					title : '币种',
					width : 105,
					align : 'left'
				}, {
					field : 'paymentType',
					title : '付款方式',
					width : 105,
					align : 'left'
				}, {
					field : 'remarks',
					title : '商品报价备注',
					width : 120,
					align : 'left'
				}, {
					field : 'exchangerate',
					title : '汇率(%)',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'referenceDate',
					title : '汇率参考日期',
					width : 140,
					align : 'left'
				}, {
					field : 'referenceBank',
					title : '汇率参考银行',
					width : 105,
					align : 'left'
				}, {
					field : 'aeRemarks',
					title : '汇率备注',
					width : 105,
					align : 'left'
				}, {
					field : 'rmbSettlementPrice',
					title : '商品人民币结算价格',
					width : 120,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'rmbSettlementPriceRemarks',
					title : '商品人民币结算价格备注',
					width : 130,
					align : 'left'
				}, {
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		
		// 海,空运费
		var yunfei = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'starting',
					title : '出发港',
					width : 105,
					align : 'left'
				}, {
					field : 'destination',
					title : '到达港',
					width : 105,
					align : 'left'
				}, {
					field : 'containerType',
					title : '货柜类型',
					width : 105,
					align : 'left'
				}, {
					field : 'containerSize',
					title : '货柜尺寸',
					width : 105,
					align : 'left'
				}, {
					field : 'unitPrice',
					title : '单价(元/货柜)',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'containerCapacity',
					title : '货柜内容物数量&重量',
					width : 120,
					align : 'left'
				}, {
					field : 'computeType',
					title : '计算方式',
					width : 105,
					align : 'left'
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				}, {
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		
		// 换单费
		var huandan = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'updateOrderFee',
					title : '报价',
					width : 80,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'updateOrderFeeRemarks',
					title : '备注',
					width : 105,
					align : 'left'
				}, {
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		
		// 保险费
		var baoxian = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'premium',
					title : '报价',
					width : 80,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'premiumRemarks',
					title : '备注',
					width : 105,
					align : 'left'
				}, {
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		
		// 报关服务费
		var baoguan = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'customscharges',
					title : '报关费',
					width : 80,
					align : 'right'
				}, {
					field : 'portSurcharge',
					title : '港杂费',
					width : 80,
					align : 'right'
				}, {
					field : 'demurrageCharge',
					title : '滞港费',
					width : 80,
					align : 'right'
				}, {
					field : 'containerDirtynessChange',
					title : '污箱费',
					width : 80,
					align : 'right'
				}, {
					field : 'elseFee',
					title : '其他费用',
					width : 80,
					align : 'right'
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				}, {
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		
		// 关税
		var guanshui = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'hsCode',
					title : 'HS编码',
					width : 80,
					align : 'left'
				}, {
					field : 'taxRate',
					title : '税率(%)',
					width : 80,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'customsdutiesComputeType',
					title : '关税计算方式',
					width : 80,
					align : 'left'
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				}, {
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		
		// 增值税
		var zengzhi = [ [
				{
					field : 'merchandiseDxRoleName',
					title : '商品定性角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'merchandiseDlRoleName',
					title : '商品定量角色',
					width : 80,
					align : 'left'
				},
				{
					field : 'intentionPurchaseDepartments',
					title : '采购部门',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.purchaseDepartments;
						}
					}
				},
				{
					field : 'rationed',
					title : '是否定量装',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.netWeight;
						}
					}
				},
				{
					field : 'purchaseType',
					title : '采购类型',
					width : 80,
					align : 'left'
				},
				{
					field : 'saleType',
					title : '销售方式',
					width : 80,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null) {
							return value;
						} else {
							return row.storageForm;
						}
					}
				},
				{
					field : 'centreTypeName',
					title : '中分类',
					width : 70,
					align : 'left'
				},
				{
					field : 'smallTypeName',
					title : '小分类',
					width : 70,
					align : 'left',
					formatter : function(value, row) {
						if (row.merchandiseCode == null
								&& row.intentionSmallTypeCode == 'ELSE') {
							return row.elseTypeName;
						} else {
							return value;
						}
					}
				}, {
					field : 'detailTypeName',
					title : '明细类',
					width : 70,
					align : 'left'
				}, {
					field : 'fineTypeName',
					title : '细分类',
					width : 70,
					align : 'left'
				}, {
					field : 'price',
					title : '报价',
					width : 80,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'units',
					title : '报价计量单位',
					width : 80,
					align : 'left'
				}, {
					field : 'taxRate',
					title : '税率(%)',
					width : 80,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'addedvaluetaxComputeType',
					title : '增值税计算方式',
					width : 140,
					align : 'left'
				}, {
					field : 'remarks',
					title : '备注',
					width : 105,
					align : 'left'
				}, {
					field : 'accountingCode',
					title : '核算/投料表编号',
					width : 105,
					align : 'left',
					sortable : true,
					formatter:function(value,row){
						 if(value != null){
						 	return '<a href=javascript:merchandiseItemCostFn.showLoad()>' + value + '</a>'
						 }
						}
				}, {
					field : 'quotedDate',
					title : '报价日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationCode',
					title : 'SCO申请单号',
					width : 120,
					align : 'left',
					sortable : true
				}, {
					field : 'applicationStatusValue',
					title : 'OA审批状态',
					width : 80,
					align : 'left'
				}, {
					field : 'x001Region',
					title : 'X001地区合作价格',
					width : 105,
					align : 'right',
					formatter : function(value){
			 			return moneyFormatter(value,2);
					}
				}, {
					field : 'oaApproveDate',
					title : '审批通过日期',
					width : 140,
					align : 'left',
					sortable : true
				}, {
					field : 'createby',
					title : 'SCO申请单创建人',
					width : 105,
					align : 'left'
				} ] ];
		switch (itemValue) {
		case 'material':
			$("#merchandiseItemCost_grid").datagrid("options").columns = material;
			break;
		case 'npackag':
			switch ($("#npackagName").combo("getValue")) {
			case 'FHD_JM':
			case 'FHD_LSM':
				$("#merchandiseItemCost_grid").datagrid("options").columns = juanMo;
				break;
			case 'FHD_ZD':
				$("#merchandiseItemCost_grid").datagrid("options").columns = zhiDai;
				break;
			case 'ST':
				$("#merchandiseItemCost_grid").datagrid("options").columns = suTuo;
				break;
			case 'TYJ':
				$("#merchandiseItemCost_grid").datagrid("options").columns = tuoYang;
				break;
			case 'NDD':
				$("#merchandiseItemCost_grid").datagrid("options").columns = neiDanDai;
				break;
			case 'BQ':
			case 'ZZL':
				$("#merchandiseItemCost_grid").datagrid("options").columns = biaoQian;
				break;
			case 'W':
			case 'ELSE':
				$("#merchandiseItemCost_grid").datagrid("options").columns = others;
				break;
			}
			break;
		case 'wpackag':
			switch ($("#wpackagName").combo("getValue")) {
			case 'FXD':
				$("#merchandiseItemCost_grid").datagrid("options").columns = fengXiang;
				break;
			case 'ZX':
				$("#merchandiseItemCost_grid").datagrid("options").columns = zhiXiang;
				break;
			case 'W':
			case 'ELSE':
				$("#merchandiseItemCost_grid").datagrid("options").columns = others;
				break;
			}
			break;
		case 'elsecost':
			switch ($("#elseCost").combo("getValue")) {
			case 'SH':
				$("#merchandiseItemCost_grid").datagrid("options").columns = sunHao;
				break;
			case 'SDM':
				$("#merchandiseItemCost_grid").datagrid("options").columns = shuiDianMei;
				break;
			case 'SBZJWH':
				$("#merchandiseItemCost_grid").datagrid("options").columns = sbzjwh;
				break;
			case 'RG':
				$("#merchandiseItemCost_grid").datagrid("options").columns = renGong;
				break;
			case 'GL':
			case 'LR':
				$("#merchandiseItemCost_grid").datagrid("options").columns = guanLi;
				break;
			case 'YS':
				$("#merchandiseItemCost_grid").datagrid("options").columns = yunShu;
				break;
			case 'SS':
				$("#merchandiseItemCost_grid").datagrid("options").columns = shuiShou;
				break;
			}
			break;
		case 'importcost':
			switch ($("#importCost").combo("getValue")) {
			case 'CC':
				$("#merchandiseItemCost_grid").datagrid("options").columns = chuchang;
				break;
			case 'HY':
			case 'KY':
				$("#merchandiseItemCost_grid").datagrid("options").columns = yunfei;
				break;
			case 'HD':
				$("#merchandiseItemCost_grid").datagrid("options").columns = huandan;
				break;
			case 'BX':
				$("#merchandiseItemCost_grid").datagrid("options").columns = baoxian;
				break;
			case 'BG':
				$("#merchandiseItemCost_grid").datagrid("options").columns = baoguan;
				break;
			case 'GS':
				$("#merchandiseItemCost_grid").datagrid("options").columns = guanshui;
				break;
			case 'ZZ':
				$("#merchandiseItemCost_grid").datagrid("options").columns = zengzhi;
				break;
			}
			break;
		}
		param.itemValue = itemValue;
		param.costValue = costValue;
		var selectComboxValue = "";
		isNullNpackage = true;
		isNullWpackage = true;
		isNullElsecost = true;
		isNullImportcost = true;
		switch (itemValue) {
		case "material":
			param.materialName = $("#materialName").val();
			param.materialOrigin = $("#materialOrigin").val();
			param.materialBrand = $("#materialBrand").val();
			break;
		case "npackag":
			selectComboxValue = $("#npackagName").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullNpackage = true;
			} else {
				isNullNpackage = false;
			}
			break;
		case "wpackag":
			selectComboxValue = $("#wpackagName").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullWpackage = true;
			} else {
				isNullWpackage = false;
			}
			break;
		case "elsecost":
			selectComboxValue = $("#elseCost").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullElsecost = true;
			} else {
				isNullElsecost = false;
			}
			break;
		case "importcost":
			selectComboxValue = $("#importCost").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullImportcost = true;
			} else {
				isNullImportcost = false;
			}
			break;
		default:
			break;
		}
		if (costValue == "historycost") {
			param.minQuotedDate = $("#minQuotedDate").combobox("getValue");
			param.maxQuotedDate = $("#maxQuotedDate").combobox("getValue");
			if ((param.minQuotedDate == null || param.minQuotedDate == '')&&(param.maxQuotedDate == null || param.maxQuotedDate == '')) {
				isNullHistory = true;
			} else {
				isNullHistory = false;
			}
		}else{
			isNullHistory = false;
		}
		param.selectComboxValue = selectComboxValue
		if ((itemValue=="npackag"&&isNullNpackage)||(itemValue=="wpackag"&&isNullWpackage)||(itemValue=="elsecost"&&isNullElsecost)||(itemValue=="importcost"&&isNullImportcost)||isNullHistory) {
			$.messager.alert("提示", "请输入必填项!!");
			return;
		}
		$('#grid').show();
		// 重新渲染datagrid
		$("#merchandiseItemCost_grid").datagrid({
			singleSelect : true,
		    checkOnSelect : false,
	   		selectOnCheck : false,
			pagination : true,
			pagePosition : 'bottom',
			pageSize : "20",
			pageList : [ 10, 20, 30, 40 ],
			rownumbers : true,
			frozenColumns : frozen,
			url : "merchandiseItemCost_listMerchandiseItemCost_2.html",
			queryParams : param
		});
	},
	// 清除查询
	clearFilter : function() {
		clearSearch = true;
		$('#signedQty_search').form('reset');
		clearComboboxOptions();
		$('#merchandiseItemCost_grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		$('#grid').hide();
		itemValue = "material";
		costValue = "lastcost";
		isNullNpackage = false;
		isNullWpackage = false;
		isNullElsecost = false;
		isNullImportcost = false;
		isNullHistory = false;
		$('#historycost').hide();
		$('#importcost').hide();
		$('#elsecost').hide();
		$('#wpackag').hide();
		$('#npackag').hide();
		$('#material').show();
		clearSearch = false;
	},
	refresh : function() {
		$('#merchandiseItemCost_grid').treegrid('reload');
	},
	// 导出到Excel
	export2Excel : function() {
		var param = mechandiseItemCostGridUtil.getFilterValue();
		// 判断是否选择了采购部门
		if ($('#purchaseDepartments').combobox("getValue") != null
				&& $('#purchaseDepartments').combobox("getValue") != '') {
			param.purchaseDepartments = $('#purchaseDepartments').combobox(
					"getValue");
		}
		// 判断是否选择了是否定量
		if ($('#rationed').combobox("getValue") != null
				&& $('#rationed').combobox("getValue") != '') {
				param.rationed = $('#rationed').combobox("getValue");
		}
		// 判断是否选择了采购类型
		if ($('#purchaseType').combobox("getValue") != null
				&& $('#purchaseType').combobox("getValue") != '') {
			param.purchaseType = $('#purchaseType').combobox("getText");
		}
		// 判断是否选择了销售方式
		if ($('#saleType').combobox("getValue") != null
				&& $('#saleType').combobox("getValue") != '') {
			param.saleType = $('#saleType').combobox("getValue");
		}
		// 判断是否选择了中分类
		if ($('#centreTypeCodeElse').combobox("getValue") != null
				&& $('#centreTypeCodeElse').combobox("getValue") != '') {
			param.centreTypeCode = $('#centreTypeCodeElse')
					.combobox("getValue");
		}
		// 判断是否选择了小分类
		if ($('#smallTypeCode').combobox("getValue") != null
				&& $('#smallTypeCode').combobox("getValue") != '') {
			param.smallTypeCode = $('#smallTypeCode').combobox("getValue");
		}
		// 判断是否选择了明细类
		if ($('#detailTypeCode').combobox("getValue") != null
				&& $('#detailTypeCode').combobox("getValue") != '') {
			param.detailTypeCode = $('#detailTypeCode').combobox("getValue");
		}
		// 判断是否选择了细分类
		if ($('#fineTypeCode').combobox("getValue") != null
				&& $('#fineTypeCode').combobox("getValue") != '') {
			param.fineTypeCode = $('#fineTypeCode').combobox("getValue");
		}
		param.itemValue = itemValue;
		param.costValue = costValue;
		var selectComboxValue = "";
		isNullNpackage = true;
		isNullWpackage = true;
		isNullElsecost = true;
		isNullImportcost = true;
		switch (itemValue) {
		case "material":
			param.materialName = $("#materialName").val();
			param.materialOrigin = $("#materialOrigin").val();
			param.materialBrand = $("#materialBrand").val();
			break;
		case "npackag":
			selectComboxValue = $("#npackagName").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullNpackage = true;
			} else {
				isNullNpackage = false;
			}
			break;
		case "wpackag":
			selectComboxValue = $("#wpackagName").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullWpackage = true;
			} else {
				isNullWpackage = false;
			}
			break;
		case "elsecost":
			selectComboxValue = $("#elseCost").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullElsecost = true;
			} else {
				isNullElsecost = false;
			}
			break;
		case "importcost":
			selectComboxValue = $("#importCost").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullImportcost = true;
			} else {
				isNullImportcost = false;
			}
			break;
		default:
			break;
		}
		if (costValue == "historycost") {
			param.minQuotedDate = $("#minQuotedDate").combobox("getValue");
			param.maxQuotedDate = $("#maxQuotedDate").combobox("getValue");
			if ((param.minQuotedDate == null || param.minQuotedDate == '')&&(param.maxQuotedDate == null || param.maxQuotedDate == '')) {
				isNullHistory = true;
			} else {
				isNullHistory = false;
			}
		}else{
			isNullHistory = false;
		}
		param.selectComboxValue = selectComboxValue
		if ((itemValue=="npackag"&&isNullNpackage)||(itemValue=="wpackag"&&isNullWpackage)||(itemValue=="elsecost"&&isNullElsecost)||(itemValue=="importcost"&&isNullImportcost)||isNullHistory) {
			$.messager.alert("提示", "请输入必填项!!");
			return;
		}
		var temp = $.param(param);
		var url = "merchandiseItemCost_exportDataToExcel_6.html?" + temp
				+ "&itemValue=" + itemValue + "&selectComboxValue="
				+ selectComboxValue + "&costValue=" + costValue;
		window.location = url;
		$.messager.show({
			title : '提示',
			msg : '数据导出中,请稍后...',
			timeout : 4000,
			showType : 'slide'
		});
	},
	// 填写保存文件名称
	saveFile : function() {
		$("#saveFileDlg").window('open');// 打开窗口
	},

	// 关闭界面
	closeSaveFileDlg : function() {
		$("#fileName").val("");// 清空填写的值
		$("#saveFileDlg").window('close');// 关闭窗口
	},

	// 提交填写文件名称的对话框
	submitSaveFileDlg : function() {
		$("#dlg_save").linkbutton("disable");
		var fileName = $.trim($("#fileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				$("#dlg_save").linkbutton("enable");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			$("#dlg_save").linkbutton("enable");
			return;
		}
		var param = mechandiseItemCostGridUtil.getFilterValue();
		// 判断是否选择了采购部门
		if ($('#purchaseDepartments').combobox("getValue") != null
				&& $('#purchaseDepartments').combobox("getValue") != '') {
			param.purchaseDepartments = $('#purchaseDepartments').combobox(
					"getValue");
		}
		// 判断是否选择了是否定量
		if ($('#rationed').combobox("getValue") != null
				&& $('#rationed').combobox("getValue") != '') {
			param.rationed = $('#rationed').combobox("getValue");
		}
		// 判断是否选择了采购类型
		if ($('#purchaseType').combobox("getValue") != null
				&& $('#purchaseType').combobox("getValue") != '') {
			param.purchaseType = $('#purchaseType').combobox("getValue");
		}
		// 判断是否选择了销售方式
		if ($('#saleType').combobox("getValue") != null
				&& $('#saleType').combobox("getValue") != '') {
			param.saleType = $('#saleType').combobox("getValue");
		}
		// 判断是否选择了中分类
		if ($('#centreTypeCodeElse').combobox("getValue") != null
				&& $('#centreTypeCodeElse').combobox("getValue") != '') {
			param.centreTypeCode = $('#centreTypeCodeElse')
					.combobox("getValue");
		}
		// 判断是否选择了小分类
		if ($('#smallTypeCode').combobox("getValue") != null
				&& $('#smallTypeCode').combobox("getValue") != '') {
			param.smallTypeCode = $('#smallTypeCode').combobox("getValue");
		}
		// 判断是否选择了明细类
		if ($('#detailTypeCode').combobox("getValue") != null
				&& $('#detailTypeCode').combobox("getValue") != '') {
			param.detailTypeCode = $('#detailTypeCode').combobox("getValue");
		}
		// 判断是否选择了细分类
		if ($('#fineTypeCode').combobox("getValue") != null
				&& $('#fineTypeCode').combobox("getValue") != '') {
			param.fineTypeCode = $('#fineTypeCode').combobox("getValue");
		}
		param.itemValue = itemValue;
		param.costValue = costValue;
		var selectComboxValue = "";
		isNullNpackage = true;
		isNullWpackage = true;
		isNullElsecost = true;
		isNullImportcost = true;
		switch (itemValue) {
		case "material":
			param.materialName = $("#materialName").val();
			param.materialOrigin = $("#materialOrigin").val();
			param.materialBrand = $("#materialBrand").val();
			break;
		case "npackag":
			selectComboxValue = $("#npackagName").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullNpackage = true;
			} else {
				isNullNpackage = false;
			}
			break;
		case "wpackag":
			selectComboxValue = $("#wpackagName").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullWpackage = true;
			} else {
				isNullWpackage = false;
			}
			break;
		case "elsecost":
			selectComboxValue = $("#elseCost").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullElsecost = true;
			} else {
				isNullElsecost = false;
			}
			break;
		case "importcost":
			selectComboxValue = $("#importCost").combobox("getValue");
			if (selectComboxValue == null || selectComboxValue == '') {
				isNullImportcost = true;
			} else {
				isNullImportcost = false;
			}
			break;
		default:
			break;
		}
		if (costValue == "historycost") {
			param.minQuotedDate = $("#minQuotedDate").combobox("getValue");
			param.maxQuotedDate = $("#maxQuotedDate").combobox("getValue");
			if ((param.minQuotedDate == null || param.minQuotedDate == '')&&(param.maxQuotedDate == null || param.maxQuotedDate == '')) {
				isNullHistory = true;
			} else {
				isNullHistory = false;
			}
		}else{
			isNullHistory = false;
		}
		param.selectComboxValue = selectComboxValue
		if ((itemValue=="npackag"&&isNullNpackage)||(itemValue=="wpackag"&&isNullWpackage)||(itemValue=="elsecost"&&isNullElsecost)||(itemValue=="importcost"&&isNullImportcost)||isNullHistory) {
			$.messager.alert("提示", "请输入必填项!!");
			return;
		}
		var temp = $.param(param);
		$.post("merchandiseItemCost_saveSearchDataForm_2.html?" + temp, {
			fileName : fileName
		}, function(data) {
			var json = $.parseJSON(data);
			var msg = json.msg;
			if (json.success) {// 成功
				$("#fileName").val("");// 清空填写的值
				$("#saveFileDlg").window('close');// 关闭窗口
				$.messager.show({
					title : '提示',
					msg : msg
				});
			} else {// 失败
				$.messager.alert("提示", msg);
			}
			$("#dlg_save").linkbutton("enable");
		});
	},
	/**
	 * 查看核算表详情
	 */
	showLoad : function() {
		var record = mechandiseItemCostGridUtil.getSelectedRecord();
		if (record == null) {
			return;
		}
		var url = 'accounting_showLoadAccountingForm_1.html';
		// 判断核算表编号是否为空
		if (record.accountingCode != null) {
			url += '?accountingCode=' + record.accountingCode;
		} else {
			return;
		}
		parent.addTabByUrl("核算/投料表", "agent", url);
	},
	// 设置按钮状态
	setButtonState : function() {
		var data = $('#merchandiseItemCost_grid')
				.datagrid("getData");
		if (data.total > 0) {
			$("a[id='excel']").linkbutton("enable");
			$("a[id='save']").linkbutton("enable");
		} else {
			$("a[id='excel']").linkbutton("disable");
			$("a[id='save']").linkbutton("disable");
		}
	}
};