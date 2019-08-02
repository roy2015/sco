var signedQtyGid=null;
var firstIn = true;//是否第一次进入页面
var clearSearch = false;//清空查询
$(document).ready(function(){
	signedQtyGid=utils.grid($('#signedQuantity_grid'));
	signedQtyGid.registerExtFilters("supplierCode", "supplierName", "merchandiseCode", "merchandiseName", 
			"centreTypeCode", "smallTypeCode", "detailTypeCode", "fineTypeCode","dxRoleCode","dlRoleCode", "qlCode", 
			"qlCreateby", "minUpdatedDate", "maxUpdatedDate", "qlStatus", "gqCode", "isSatisfyBeforeQl");
	signedQtyGid.initFilters({
//		onDblClickRow:signedQuantityFn.showEdit, // 双击修改
		onBeforeLoad:function(obj) {
			//清空查询
			if(clearSearch) {
				return true;
			}
			var length = $.param(obj).split("&").length;
			//判断参数个数
			if (length < 3) {
				if(!firstIn) $.messager.alert("提示", "请输入至少一项查询条件");
				firstIn = false;
				return false;
			}
			if(length == 4 && $.param(obj).indexOf("order") > -1) {
				var param = signedQtyGid.getFilterValue();
				if($.param(param).length > 0){
					return true;
				}
				$.messager.alert("提示", "请输入至少一项查询条件");
				return false;
			}
		},
		onLoadSuccess:function() {
			firstIn = false;
			$('#signedQuantity_grid').datagrid("clearSelections");
			signedQuantityFn.setButtonState();
		},
		onClickRow:signedQuantityFn.setButtonState,
		onCheck:signedQuantityFn.setButtonState,
		onUncheck:signedQuantityFn.setButtonState,
		onCheckAll:signedQuantityFn.setButtonState,
		onUncheckAll:signedQuantityFn.setButtonState
	});
});

//验证签量数量必须大于0
$.extend($.fn.validatebox.defaults.rules, {
	validateQlCount : {
		validator : function(value) {
			if (new Number(value) <= 0) {
				return false;
			}
			return true;
		},
		message : '签量数量必须大于0'
	}
});

var signedQuantityFn = {

	formatPercent:function(val) {
		if (val == null) return '';
		if (!val || val == 0) {
			return '0.00%';
		} else {
			val = (val*100).toFixed(2);
			return val + '%';
		}
	},
	
	//搜索按钮功能
	search:function() {
		var param = signedQtyGid.getFilterValue();
		$('#signedQuantity_grid').datagrid('load', param);
	},
	
	// 显示添加或改签窗口
	showForm:function(isEdit, param){
		var title='新增签量';
		var href='signedQuantity_showInsSignedQtyForm_1.html?'+param;
		if(isEdit){// 显示修改窗体
			title='改签签量';
			href='signedQuantity_showGqSignedQtyForm_1.html?'+param;
		}
		var dlg=utils.showDlg({
			title:title,href:href,width:940,height:350,
			buttons:[{text:'${action.getText("common.button.submit")}', id:'addGqSign',handler:function(){signedQuantityFn.submitForm(dlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.close")}',handler:function(){dlg.dialog('close');},iconCls:'close'}
			],
			onLoad:function() {
				$('#signedQuantity_form').form('validate');
			}
		});
	},
	
	// 新增签量单
	showInsert:function() {
		var rows = $('#signedQuantity_grid').datagrid('getSelections');
		if (rows.length != 1 ) {
			$.messager.alert("提示", "请勾选一个商品");
			return; 
		}
		var mCodes = "";
		var sCodes = "";
		var addArr = new Array();//控制重复添加
		for(var i in rows) {
			var merchandiseCode = rows[i].merchandiseCode;
			if(!addArr[merchandiseCode]){
				
				mCodes += "'" + merchandiseCode + "',";
				sCodes += "'" + rows[i].supplierCode + "',";
				addArr[merchandiseCode] = merchandiseCode;//表示同一个商品进去
			}
		}
		mCodes = mCodes.substring(0, mCodes.length - 1);
		sCodes = sCodes.substring(0, sCodes.length - 1);
		if(mCodes.split(",").length > 1) {
			$.messager.alert("提示", "只能选择一个商品");
			return ;
		}
		signedQuantityFn.showForm(false, "merchandiseCodes="
				+ mCodes
				+"&supplierCodes="+sCodes);
	},
	
	// 改签数据
	showGqEdit:function() {
		var rows = $('#signedQuantity_grid').datagrid('getSelections');
		if (rows.length != 1 ) {
			$.messager.alert('提示', '请只勾选一张签量单');
			return; 
		} 
		var obj = rows[0];
		if (obj.qlStatus != '新增' && obj.qlStatus != '改签单') {
			$.messager.alert('提示', '不可选择状态为空、已改签或已终止的签量单');
			return; 
		}
		signedQuantityFn.showForm(true, "qlCode="+obj.qlCode);
	},
	
	// 显示修改窗口
	showUpdateForm:function(param){
		var dlg = utils.showDlg({
			title : '修改签量',
			href : 'signedQuantity_showUpdateSignedQtyForm_1.html?qlCode='+param,
			width : 940,
			height : 350,
			buttons :
				[{text:'${action.getText("common.button.submit")}',id:'editSub',handler:function(){signedQuantityFn.subUpdateForm(dlg);},iconCls:'save'},
			    {text:'${action.getText("common.button.close")}',handler:function(){dlg.dialog('close');},iconCls:'close'}
			],
			onLoad:function() {
				$('#signedQuantity_form').form('validate');
			}
		});
	},
	
	//提交修改页面
	subUpdateForm:function(dlg) {
		var mCodes = $('#merchandiseCodes').val().split(',');
		var sCodes = $('#supplierCodes').val().split(',');
		var tempM = '';
		var tempS = '';
		$(mCodes).each(function(i, obj){
			if(obj != "&&"){
				if(tempM != ''){
					tempM += ',';
					tempS += ',';
				}
				tempM += obj; 
				tempS += sCodes[i];
			}
		});
		if(tempM == '') {
			$.messager.alert('提示', '至少需有一条商品记录，否则不可修改');
			return; 
		}
		$('#merchandiseCodes').val(tempM);
		$('#supplierCodes').val(tempS);
		if($("#sqtyEdit_form").form('validate')) {
			$("#editSub").linkbutton("disable");
		}
		
		utils.form("sqtyEdit_form").submit("signedQuantity_updateSignedQty_2.html",null,function(){
			dlg.dialog('close');
			signedQuantityFn.refresh();
		},function(){
			$("#editSub").linkbutton("enable");
		});
	},
	
	//勾选或去掉所有
	checkedAll:function(obj) {
		if($(obj).attr("checked") == "checked"){
			$("input[editcheck=editcheck]").attr("checked",true);
		} else {
			$("input[editcheck=editcheck]").attr("checked",false);
		}
	},
	
	//去掉或勾选某个checkbox
	selectOne:function(obj) {
		if($(obj).attr("checked") != "checked"){//去掉勾选本条数据
			$("input[name=checkAll]").attr("checked", false);
			return ;
		} else {//若是选中本条数据，则需检查所有是否勾选
			var flag = true;
			$("input[editcheck=editcheck]").each(function(i, obj){
				if($(obj).attr("checked") != "checked"){
					flag = false;
					return ;
				}
			}); 
			if(flag) {
				$("input[name=checkAll]").attr("checked", true);
			}
		}
	},
	
	//修改签量时移出商品
	editRemove:function() {
		var count = 0;
		$("input[editcheck=editcheck]").each(function(i, obj) {
				if($(obj).attr("checked") == "checked"){
					signedQuantityFn.setParaWithDel(obj);
					count++;
				}
			});
		if (count < 1 ) {
			$.messager.alert('提示', '请勾选至少一个商品');
			return; 
		}
		//若删除所有的商品，取到用来全选的checkbox的勾选
		if($("input[editcheck=editcheck]").length < 1){
			$("input[name=checkAll]").attr("checked", false);
		}
	},
	
	//删除某个商品时设置参数
	setParaWithDel:function(obj) {
		var row = $(obj).attr("id").split("&_&")[1];//当前删除的行
		
		var mCodes = $('#merchandiseCodes').val().split(',');
		var sCodes = $('#supplierCodes').val().split(',');
		//替换对应位置的数据
		mCodes[row] = '&&';
		sCodes[row] = '&&';
		$('#merchandiseCodes').val(mCodes.join(','));
		$('#supplierCodes').val(sCodes.join(','));
		$(obj).parent().parent().remove();
	},
	
	//编辑签量时显示添加商品界面
	added : '',
	showEditAddDlg:function() {
		var title='添加商品';
		var href='signedQuantity_showAddMerchandiseForm_1.html';
		var dlg=utils.showDlg({
			title:title,href:href,width:400,height:350,
			buttons:[{text:'确定',handler:function(){signedQuantityFn.addMdForm(dlg);},iconCls:'save'},
			        {text:'关闭',handler:function(){signedQuantityFn.closeMdForm(dlg);},iconCls:'close'}
			],onLoad:function(){
				var addMdGid=utils.grid($('#addMd_grid'));
				addMdGid.initFilters({
					onBeforeLoad:function(obj) {
						$('input[id=selectedMd]').val(signedQuantityFn.added);//保存已选中的商品
					},
					onLoadSuccess:function(obj) {
						//选中已选择的商品
						$(obj.rows).each(function(i, row){
							var mCode = row.merchandiseCode;
							if($('input[id=selectedMd]').val().indexOf(mCode) > 0){
								$('#addMd_grid').datagrid('selectRow', i);
							}
						});
					},
					onClickRow:signedQuantityFn.selectedMd,
					onCheck:signedQuantityFn.selectedMd,
					onUncheck:signedQuantityFn.selectedMd,
					onCheckAll:signedQuantityFn.selectedMd,
					onUncheckAll:signedQuantityFn.selectedMd
				});
			}
		});
	},
	//添加选中商品
	addMdForm:function(dlg) {
		var mCodes = signedQuantityFn.added;//将要添加的
		signedQuantityFn.closeMdForm(dlg);//调用关闭dlg事件
		var mValue = $('#merchandiseCodes').val();//隐藏框中的数量
		//过滤已经选择的商品
		var param = '';
		$(mCodes.split(",")).each(function(i, value){
			if(value && mValue.indexOf("'"+value+"'") == -1){
				param += value+',';
			}
		});
		if(mCodes.length > 0) {
			$.post('signedQuantity_addMerchandise_2.html', {
				merchandiseCodes : param
			}, function(data) {
				//需添加的商品信息
				var mArr = $('input[editcheck=editcheck]');
				var length = mArr.length;//页面显示添加的商品个数
				var input = $('#merchandiseCodes').val().split(',').length;//隐藏框中的数量
				$(data.rows).each(function(i, obj){
					var mCode = obj.merchandiseCode;
					var mName = obj.merchandiseName;
					var sCode = obj.supplierCode==null?"":obj.supplierCode;
					var sName = obj.supplierName==null?"":obj.supplierName;
					var dxRoleName = obj.dxRoleName==null?"":obj.dxRoleName;
					var dlRoleName = obj.dlRoleName==null?"":obj.dlRoleName;
					var centreName = obj.centreName==null?"":obj.centreName;
					var smallName = obj.smallName==null?"":obj.smallName;
					var detailName = obj.detailName==null?"":obj.detailName;
					var fineName = obj.fineName==null?"":obj.fineName;
					var xPrice = obj.xPrice==null?"":obj.xPrice;
					var newRow = 
					 '<tr id=header'+ (input+1) +'>'
						+'<td><input editcheck="editcheck" type="checkbox" id="\''+mCode+'\'&_&' +(input)+'" onclick="signedQuantityFn.selectOne(this)" /></td>'
						+'<td>'+mCode+'</td><td>'+mName+'</td>'
						+'<td>'+sCode+'</td><td>'+sName+'</td>'
						+'<td>'+dxRoleName+'</td><td>'+dlRoleName+'</td>'
						+'<td>'+centreName+'</td><td>'+smallName+'</td>'
						+'<td>'+detailName+'</td><td>'+fineName+'</td>'
						+'<td>'+xPrice+'</td>'
						+'<td>'
						 	+'<input addPrice="addPrice" name="\''+mCode+'\'_price" maxlength="12" style="width:60px;" />'
						+"</td>"
						+"<td>"
							+'<textArea name="\''+mCode+'\'_bak" style="height:40px;width:150px;font-size:12px;"' 
								+' class="easyui-validatebox" maxlength="1000" data-options=validType:length[0,1000]>'
							+'</textArea>'
						+'</td>'
					 +'</tr>';
					if(length == 0) {//如何当前没有添加商品
						$(newRow).insertAfter($('#header0'));
					} else {
						$(newRow).insertAfter($('#header'+input));
					}
					$('#merchandiseCodes').val(mValue+',\''+mCode+'\'');//将当前的商品添加进去
					var sValue = $('#supplierCodes').val();//隐藏框中供应商
					$('#supplierCodes').val(sValue+',\''+sCode+'\'');//添加当前的供应商
					input++;
					length++;
				});
				$("input[addPrice=addPrice]").each(function(i,obj){//设置价格输入框样式
					$(obj).numberbox({   
					    min:0,  
					    precision:2,
					    required:true
					}); 
				});
			}, 'json');
		}
	},
	//关闭添加商品dlg
	closeMdForm:function(dlg) {
		$('input[id=selectedMd]').val('');
		signedQuantityFn.added = '';
		dlg.dialog('close');
	},
	
	//勾选商品时
	selectedMd:function() {
		$("input[type=checkbox][name=HH]").each(function(i,box){
			var mCode = $('#addMd_grid').datagrid('getData').rows[i].merchandiseCode;
			if($(box).attr("checked") == "checked") {
				if(signedQuantityFn.added.indexOf(mCode) == -1)signedQuantityFn.added += "," + mCode;
			} else {
				signedQuantityFn.added = signedQuantityFn.added.replace(mCode,'');
			}
		});
	},
	//选中所有商品
	selectedAll:function() {
		$("input[type=checkbox][name=HH]").each(function(i,box){
			var mCode = $('#addMd_grid').datagrid('getData').rows[i].merchandiseCode;
			if(signedQuantityFn.added.indexOf(mCode) == -1){
				signedQuantityFn.added += "," + mCode;
				$('#addMd_grid').datagrid('selectRow', i);
			}
		});
	},
	//取消勾选所有商品
	selectedNone:function(){
		$("input[type=checkbox][name=HH]").attr("checked", false);
		signedQuantityFn.added = '';
	},
	
	// 修改签量单据
	showEdit:function(){
		var rows = $('#signedQuantity_grid').datagrid('getSelections');
		if (rows.length != 1 ) {
			$.messager.alert('提示', '请只勾选一张签量单');
			return; 
		} 
		var obj = rows[0];
		if (obj.qlStatus != '新增' && obj.qlStatus != '改签单') {
			$.messager.alert('提示', '不可选择状态为空、已改签或已终止的签量单');
			return; 
		}
		signedQuantityFn.showUpdateForm(obj.qlCode);
	},
	
	// 提交新增或改签表单
	submitForm:function(dlg){
		var qlCode = $("#signedQuantity_form input[id=qlCode]").val();
		var url = 'signedQuantity_insertSignedQuantity_2.html';
		if(qlCode){
			//判断填写数量
			var data = $('#qlCount').val();
			var done = $('#proCount').val();//已完成数量
			if (new Number(data) < new Number(done)) {
				$.messager.alert('提示', '签量数量不可小于已完成数量');
				return;
			}
			url = 'signedQuantity_completeGqSignedQty_2.html';
		}
		if ($('#signedQuantity_form').form('validate')){
			$("#addGqSign").linkbutton("disable");
		}
		utils.form("signedQuantity_form").submit(url,null,function(){
			dlg.dialog('close');
			signedQuantityFn.refresh();
		},function(){
			$("#addGqSign").linkbutton("enable");
		});
	},
	
	// 删除签量单据
	remove:function(){
		var rows = $('#signedQuantity_grid').datagrid('getSelections');
		if (rows.length < 1 ) {
			$.messager.alert('提示', '请勾选至少一张签量单');
			return; 
		} 
		var msg = '是否确认删除？';//删除时的提示消息
		var flag = true;//用于提高性能
		var qlCode = '';
		for (var i in rows) {
			var qlStatus = rows[i].qlStatus;
			if(!qlStatus){//过滤非签量单
				$.messager.alert('提示', '不可选择状态为空的签量单');
				return;
			}
			if(flag && (qlStatus == '已改签' || qlStatus =='改签单')) {//提示消息
				msg = '您所选的记录中有"已改签"或"改签单"状态的签量单，如果删除将一并删除，是否确认删除？';
				flag = false;
			}
			qlCode += "'" + rows[i].qlCode + "',";
		}
		qlCode = qlCode.substring(0, qlCode.length - 1);
		utils.confirm("确认", msg, function(){
			utils.post("signedQuantity_deleteSignedQuantity_2.html",{qlCode:qlCode},function(){
				signedQuantityFn.refresh();
			});
		});
	},
	
	//终止签量
	ter:function() {
		var rows = $('#signedQuantity_grid').datagrid('getSelections');
		if (rows.length < 1 ) {
			$.messager.alert('提示', '请勾选至少一张签量单');
			return; 
		} 
		var qlCode = '';
		for (var i in rows) {
			var qlStatus = rows[i].qlStatus;
			if(qlStatus != '新增' && qlStatus != '改签单'){
				$.messager.alert('提示', '不可终止非新增或非改签单状态的记录');
				return ;
			}
			qlCode += "'" + rows[i].qlCode + "',";
		}
		qlCode = qlCode.substring(0, qlCode.length - 1);
		utils.post("signedQuantity_terSignedQuantity_2.html",{qlCode:qlCode},function(){
			signedQuantityFn.refresh();
		});
	},
	
	//撤销终止
	revokeTer:function() {
		var rows = $('#signedQuantity_grid').datagrid('getSelections');
		if (rows.length < 1 ) {
			$.messager.alert('提示', '请勾选至少一张签量单');
			return; 
		} 
		var qlCode = '';
		for (var i in rows) {
			var qlStatus = rows[i].qlStatus;
			if(qlStatus != '已终止'){
				$.messager.alert('提示', '只可对"已终止"状态的记录进行撤销终止的操作');
				return ;
			}
			qlCode += "'" + rows[i].qlCode + "',";
		}
		qlCode = qlCode.substring(0, qlCode.length - 1);
		utils.post("signedQuantity_revokerTerSignedQuantity_2.html",{qlCode:qlCode},function(){
			signedQuantityFn.refresh();
		});
	},
	
	// 清除查询
	clearFilter:function(){
		clearSearch = true;
		$('#signedQty_search').form('reset');
		clearComboboxOptions();
		$('.datagrid-sort-desc,.datagrid-sort-asc')
			.removeClass('datagrid-sort-desc datagrid-sort-asc');
		$('#signedQuantity_grid').datagrid('clearSelections');
		$('#signedQuantity_grid').datagrid('loadData',{total:0,rows:[]});
		clearSearch = false;
	},
	
	//刷新表格
	refresh:function(){
		clearSearch = true;
    	$('#signedQuantity_grid').datagrid('reload');
    	clearSearch = false;
	},
    
	// 设置按钮状态
	setButtonState:function(index,record){
		var rows = $('#signedQuantity_grid').datagrid("getSelections");
		if (rows.length == 1) {// 单选
			$("a[id='insert']").linkbutton("enable");
			$("a[id='gq']").linkbutton("enable");
			$("a[id='edit']").linkbutton("enable");
			$("a[id='remove']").linkbutton("enable");
			$("a[id='ter']").linkbutton("enable");
			$("a[id='rev']").linkbutton("enable");
		} else if (rows.length > 1){ // 多选
			$("a[id='insert']").linkbutton("disable");
			$("a[id='gq']").linkbutton("disable");
			$("a[id='edit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("enable");
			$("a[id='ter']").linkbutton("enable");
			$("a[id='rev']").linkbutton("enable");
		} else {//未选
			$("a[id='insert']").linkbutton("disable");
			$("a[id='gq']").linkbutton("disable");
			$("a[id='edit']").linkbutton("disable");
			$("a[id='remove']").linkbutton("disable");
			$("a[id='ter']").linkbutton("disable");
			$("a[id='rev']").linkbutton("disable");
		}
	},
	
	// 导出到Excel
	exportToExcel:function(){
		var param = $.param(signedQtyGid.getFilterValue());
		if(param.length < 1){
			$.messager.alert("提示", "请输入至少一项查询条件");
			return;
		}
		url="signedQuantity_exportSignedQtyExcel_6.html?"+param;
		window.location=url;
		$.messager.show({
			 title:'提示',
				msg:'数据导出中,请稍后...',
				timeout:4000,
				showType:'slide'
		});
	},
	
	//千分位
	formatterCount:function(value) {
		return formatterCount(value);
	}
	
};