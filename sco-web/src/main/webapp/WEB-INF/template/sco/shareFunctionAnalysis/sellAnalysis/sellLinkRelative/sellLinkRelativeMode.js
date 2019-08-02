var linkRelativeUtil = null;
var clearSearch = false;// 清空查询
var firstIn = true;// 是否第一次进入页面
$(document).ready(function() {
			linkRelativeUtil = utils.grid($('#linkRelative_Grid'));
			// 注册查询框
			linkRelativeUtil.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName",
					 "dxRoleCode", "dlRoleCode","centreTypeCode", "smallTypeCode", 
					 "detailTypeCode", "fineTypeCode","purchaseDepartments");
			linkRelativeUtil.initFilters({
				onBeforeLoad : function(obj) {
					// 清空查询
					if (clearSearch) {
						return true;
					}
					var length = $.param(obj).split("&").length;
					// 判断参数个数
					if (length < 3) {
						if (!firstIn)
							$.messager.alert("提示", "请输入至少一项查询条件");
						firstIn = false;
						return false;
					}
					if (length == 4 && $.param(obj).indexOf("order") > -1) {
						var param = linkRelativeUtil.getFilterValue();
						if ($.param(param).length > 0) {
							return true;
						}
						$.messager.alert("提示", "请输入至少一项查询条件");
						return false;
					}
				},
				onLoadSuccess : function() {
					firstIn = false;
				}
			});
		});


var linkRelativeFn = {
	// 查询
	search : function() {
		var param = linkRelativeUtil.getFilterValue();
		var rationed = $("input[name='rationed']:checked").val();
		param.rationed=rationed;
		
		$('#linkRelative_Grid').datagrid('load', param);
	},
	// 清除查询
	clearFilter : function() {
		$('.datagrid-sort-desc,.datagrid-sort-asc').removeClass('datagrid-sort-desc datagrid-sort-asc');
		clearSearch = true;
		$('#linkRelative_search').form('reset');
		clearComboboxOptions();//在IE8下，选中中分类，点击查询。清空查询条件时，小分类下拉框有数据
		$('#linkRelative_Grid').datagrid('clearSelections');
		$('#linkRelative_Grid').datagrid('loadData', {
			total : 0,
			rows : []
		});
		clearSearch = false;
	},
	refresh : function() {
		clearSearch = true;
		$('#linkRelative_Grid').datagrid('reload');
		clearSearch = false;
	},
	//查看环比分析
	sellDetailReport : function() {
		var linkRelativeGrid = $('#linkRelative_Grid').datagrid('getChecked');
		/*if (linkRelativeGrid.length == 0) {
			$.messager.alert('提示', '<center>请至少选择一条商品记录!</center>');
		} else {*/
			var sellRegion=$("#sellRegion").combobox('getValue');
			var startDate = $("#minDate").datebox('getValue');
			var endDate = $("#maxDate").datebox('getValue');
			var directJoin =  $("input[name='directJoin']:checked").val();//直营或加盟
			var rationed =  $("input[name='rationed']:checked").val();//公斤装或定量装
			var linkRelativeCycle = $("#linkRelativeCycle").val();//查看年数
			//区域是否必填校验
			if (sellRegion == "") {
				$.messager.alert('提示', '<center>请输入必选条件地区!</center>');
				return;
			}
			// 日起范围的合法性校验
			if (startDate == "" || endDate == "") {
				$.messager.alert('提示', '<center>请输入必选条件时间范围!</center>');
				return;
			}
			if (startDate > endDate) {
				$.messager.alert('提示', '<center>结束时间不能早于开始时间!</center>');
				return;
			}
			if (linkRelativeCycle == "") {
				$.messager.alert('提示', '<center>请输入环比周期!</center>');
				return;
			}
			//获取时间范围
			var dates1=new Date(""+startDate.substr(0,4),""+startDate.substr(5,2)-1,""+startDate.substr(8,2));
			var dates2=new Date(""+endDate.substr(0,4),""+endDate.substr(5,2)-1,""+endDate.substr(8,2));
			var day=(dates2-dates1)/86400000+1;
			//判断环比周期是否可以被时间范围整除
			var cycleCount=day/linkRelativeCycle;//环比次数
			if(day%linkRelativeCycle!=0){
				$.messager.alert('提示', '<center>环比周期必须可以被时间范围的天数整除!</center>');
				return;
			}else{
				//环比次数必须<=5
				if(cycleCount>5){
					$.messager.alert('提示', '<center>时间范围中的天数除以环比周期的结果必须小于等于5!</center>');
					return;
				}
			}
			
			var  merchandiseCodes=[];
			var  merchandiseAndSupplierCodes=[];
			$.each(linkRelativeGrid, function(index, item){
				merchandiseCodes.push("'"+item.merchandiseCode+"'");
				//根据商品编号和供应商编号查询同比信息
				merchandiseAndSupplierCodes.push(item.merchandiseCode+":"+item.supplierCode);
			});
			var url = "";
			//查看环比类型
			var analysisType = $("#analysisType").combobox("getValue");
			var tabName = "";
			if("product"==analysisType){
				if (linkRelativeGrid.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条商品记录!</center>');
					return ;
				} 
				//单品
				tabName="单品环比结果";
				url = "merchandiseLinkRelative_showProductLinkRelativeGrid_1.html?"+
				"sellRegion="+sellRegion+
				"&startDate=" + startDate + 
				"&endDate=" + endDate + 
				"&directJoin=" + directJoin+
				"&merchandiseCodes=" + merchandiseCodes+
				"&merchandiseAndSupplierCodes=" + merchandiseAndSupplierCodes+
				"&rationed="+rationed+
				"&linkRelativeCycle="+linkRelativeCycle+
				"&cycleCount="+cycleCount;
			}else if("detail"==analysisType){
				if (linkRelativeGrid.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条商品记录!</center>');
					return ;
				} 
				//明细类
				tabName="明细类环比结果";
				url = "detailLinkRelative_showDetailLinkRelativeGrid_1.html?"+
				"sellRegion="+sellRegion+
				"&startDate=" + startDate + 
				"&endDate=" + endDate + 
				"&directJoin=" + directJoin+
				"&merchandiseCodes=" + merchandiseCodes+
				"&merchandiseAndSupplierCodes=" + merchandiseAndSupplierCodes+
				"&rationed="+rationed+
				"&linkRelativeCycle="+linkRelativeCycle+
				"&cycleCount="+cycleCount;
			}else if("small"==analysisType){
				if (linkRelativeGrid.length == 0) {
					$.messager.alert('提示', '<center>请至少选择一条商品记录!</center>');
					return;
				} 
				//小分类
				tabName="小分类环比结果";
				url = "smallLinkRelative_showSmallLinkRelativeGrid_1.html?"+
				"sellRegion="+sellRegion+
				"&startDate=" + startDate + 
				"&endDate=" + endDate + 
				"&directJoin=" + directJoin+
				"&merchandiseCodes=" + merchandiseCodes+
				"&merchandiseAndSupplierCodes=" + merchandiseAndSupplierCodes+
				"&rationed="+rationed+
				"&linkRelativeCycle="+linkRelativeCycle+
				"&cycleCount="+cycleCount;
			}else if("big"==analysisType){
				//整体
				tabName="整体环比结果";
				url = "marketLinkRelative_showMarketLinkRelativeGrid_1.html?"+
				"sellRegion="+sellRegion+
				"&startDate=" + startDate + 
				"&endDate=" + endDate + 
				"&directJoin=" + directJoin+
				"&merchandiseCodes=" + merchandiseCodes+
				"&merchandiseAndSupplierCodes=" + merchandiseAndSupplierCodes+
				"&rationed="+rationed+
				"&linkRelativeCycle="+linkRelativeCycle+
				"&cycleCount="+cycleCount;
			}
			
			if (parent.isExistsTab(tabName)) { // 判断当前要打开的选项卡是否存在，若是，选中存在的选项卡
				parent.activeTab(tabName);
				parent.refreshTab(tabName);
				return;
			}
			parent.addTabByUrl(tabName, 'agent', url);
			
		//}
	},
};