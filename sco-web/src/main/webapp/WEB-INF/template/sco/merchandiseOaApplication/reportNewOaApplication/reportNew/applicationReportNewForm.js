var priceEditIndex = [];
var sameEditIndex = [];
var sellEditIndex = [];
var materialEditIndex = [];

var centreTypeFalg = [];
var smallTypeFalg = [];
var detailTypeFalg = [];
var fineTypeFalg = [];
$(document).ready(function(){
	$('[id^=centreTypeCodeElse]').combobox({
		url:'masterDataType_listCentreType_5.html',
		onChange : function() {
			var number = reportFormFn.getObjectNumber(this);
			reportFormFn.reloadData("centreTypeCodeElse", "smallTypeCode", "SmallTypeElse", number);
		},
		onLoadSuccess:function(){
			var number = reportFormFn.getObjectNumber(this);
			if (centreTypeFalg[number] == undefined) {
				$('#centreTypeCodeElse'+number).combobox('setValue',$('#valueCentreTypeCode'+number).val());
				centreTypeFalg[number] = 'yes';
			}
		}
	});
	$('[id^=smallTypeCode]').combobox({
		onChange : function() {
			var number = reportFormFn.getObjectNumber(this);
			reportFormFn.reloadData("smallTypeCode", "detailTypeCode", "DetailType", number);
		},
		onLoadSuccess:function(){
			var number = reportFormFn.getObjectNumber(this);
			if (smallTypeFalg[number] == undefined) {
				$('#smallTypeCode'+number).combobox('setValue',$('#valueSmallTypeCode'+number).val());
				smallTypeFalg[number] = 'yes';
			}
			//console.dir($('#valueSmallTypeCode'+number).val()=='ELSE');
			if($('#valueSmallTypeCode'+number).val() == 'ELSE'){
				document.getElementById('elseTypeNameTd'+number).style.display='block'; 
				$('#detailTypeCode'+number).combobox({
   					required:false
   				});
   				$('#fineTypeCode'+number).combobox({
   					required:false
   				});
   				$('#fineTypeCode'+number).combobox('setValue', '');
   				$('#detailTypeCode'+number).combobox('setValue', '');
   				$('#detailTypeCode'+number).combobox('disable');
   				$('#fineTypeCode'+number).combobox('disable');
			}else{
				//当页面加载成功时，取消必填校验
				$('#elseTypeName'+number).validatebox({
   					required:false
   				});
			}
		}
	});
	$('[id^=detailTypeCode]').combobox({
		onChange : function() {
			var number = reportFormFn.getObjectNumber(this);
			reportFormFn.reloadData("detailTypeCode", "fineTypeCode", "FineType", number);
		},
		onLoadSuccess:function(){
			var number = reportFormFn.getObjectNumber(this);
			if (detailTypeFalg[number] == undefined) {
				$('#detailTypeCode'+number).combobox('setValue',$('#valueDetailTypeCode'+number).val());
				detailTypeFalg[number] = 'yes';
			}
		}
	});
	$('[id^=fineTypeCode]').combobox({
		onLoadSuccess:function(){
			var number = reportFormFn.getObjectNumber(this);
			if (fineTypeFalg[number] == undefined) {
				$('#fineTypeCode'+number).combobox('setValue',$('#valueFineTypeCode'+number).val());
				fineTypeFalg[number] = 'yes';
			}
			
		}
	});
});

//
$.extend($.fn.validatebox.defaults.rules, {
	// 
	validatePrice : {
		validator : function(value) {
			return true;//暂时不限制，随便填
			if (!isNaN(value)) {
				if (value > 999999999) return false;
				var first = value.indexOf(".");
				if (first != -1) {
					var tmp = value.substring(first + 1);
					if (tmp.length > 2) {
						return false;
					}
				}
			}
			return true;
		},
		message : '小数位数最多只能两位且数值不能过大'
	}
});

var reportFormFn = {
		// 提交新增或修改表单
		submitReportForm : function() {
			var applicationCode = $("input#applicationCode").val();
			var intentionAndSupplierCodes = $("input#intentionAndSupplierCodes").val();
			var intentionList=intentionAndSupplierCodes.split(",");
			
			var url="reportNew_insertApplicationReportNew_2.html?applicationCode=" + applicationCode
					+ "&intentionAndSupplierCodes=" + intentionAndSupplierCodes;
			
			var priceRows = new Array();
			var sameRows = new Array();
			var sellRows = new Array();
			var materialRows = new Array();
			for(var i=0;i<intentionList.length;i++){
				 priceEditIndex[i]=undefined;
				 sameEditIndex [i]= undefined;
				 sellEditIndex [i]= undefined;
				 
				var priceGrid='#merchandisePrice_Grid'+i;
				var sameGrid='#merchandiseSame_Grid'+i;
				var sellGrid='#merchandiseSell_Grid'+i;
				var materialGrid='#merchandiseMaterial_Grid'+i;
				// 结束价格grid编辑
				if (reportFormFn.endEditPrice(i)) {
					$(priceGrid).datagrid('acceptChanges');
				} else {
					return false;
				}
				// 结束相同价格grid编辑
				if (reportFormFn.endEditSame(i)) {
					$(sameGrid).datagrid('acceptChanges');
				} else {
					return false;
				}
				// 结束销售grid编辑
				if (reportFormFn.endEditSell(i)) {
					$(sellGrid).datagrid('acceptChanges');
				} else {
					return false;
				}
				// 结束原料grid编辑
				if (reportFormFn.endEditMaterial(i)) {
					$(materialGrid).datagrid('acceptChanges');
				} else {
					return false;
				}
				//判断商品价格模块是否已经填写
				if($(priceGrid).datagrid('getRows').length<1){
					 $.messager.alert('提示', '<center>商品'+(i+1)+'商品价格模块不能为空！</center>');
					 return;   
				}
				
				var selectedRadioValue=$("input[name='samePart"+i+"']:checked").val();
				if(selectedRadioValue=="1"){
					//说明选择了"有"单选框
					if($(sameGrid).datagrid('getRows').length<1){
						$.messager.alert('提示', '<center>商品'+(i+1)+'同类商品市场零售价模块不能为空！</center>');
						 return; 
					}else{
						sameRows[i]=JSON.stringify($(sameGrid).datagrid('getRows'));
					}
				}else{
					sameRows[i]='[]';
				}

				//判断商品销售预计模块是否已经填写
				if($(sellGrid).datagrid('getRows').length<1){
					 $.messager.alert('提示', '<center>商品'+(i+1)+'商品销售预计模块不能为空！</center>');
					 return;   
				}
			
				reportFormFn.setPropertiesName(i);
			//	if($(priceGrid).datagrid('getRows').length>0){
				priceRows[i]=JSON.stringify($(priceGrid).datagrid('getRows'));
			//	}
			//	if($(sellGrid).datagrid('getRows').length>0){
				sellRows[i]=JSON.stringify($(sellGrid).datagrid('getRows'));
			//	}
			//	if($(materialGrid).datagrid('getRows').length>0){
				materialRows[i]=JSON.stringify($(materialGrid).datagrid('getRows'));
			//	}
			}
			//对form表单进行验证
			 if(!$('#reportNew_form').form('validate')){
					return;
			 }
		//20180808对比后注释掉	start
			 $("#saveForm").linkbutton("disable");
			 $("#reportNew_form").form('submit', {
					url : url,
					onSubmit : function(param) {
						param.priceRowsList = priceRows;
						param.sameRowsList = sameRows;
						param.sellRowsList = sellRows;
						param.materialRowsList = materialRows;
					},
					success : function(data) {
						var json = $.parseJSON(data);
						if (json.success) {
							parent.messagerShow({
								title : '操作成功!',
								msg : json.msg
							});
						}else{
							parent.messagerShow({
								title : '操作失败!',
								msg : json.msg
							});
						}
						$("#saveForm").linkbutton("enable");
						//保存之后，重新加载数据，将利率显示出来
						for(var k=0;k<intentionList.length;k++){
							$('#merchandisePrice_Grid'+k).datagrid('reload');
						}
					}
				});
			//20180808对比后注释掉	end
			
			/*utils.form("reportNew_form").submit(url, null, function() {
			});*/
		},
		// 提交新增或修改表单
		submitReportCGForm : function() {
			var applicationCode = $("input#applicationCode").val();
			var intentionAndSupplierCodes = $("input#intentionAndSupplierCodes").val();
			var intentionList=intentionAndSupplierCodes.split(",");
			
			var url="reportNew_insertApplicationReportNewCG_2.html?applicationCode=" + applicationCode
					+ "&intentionAndSupplierCodes=" + intentionAndSupplierCodes;
			
			var priceRows = new Array();
			var sameRows = new Array();
			var sellRows = new Array();
			var materialRows = new Array();
			for(var i=0;i<intentionList.length;i++){
				 priceEditIndex[i]=undefined;
				 sameEditIndex [i]= undefined;
				 sellEditIndex [i]= undefined;
				 
				var priceGrid='#merchandisePrice_Grid'+i;
				var sameGrid='#merchandiseSame_Grid'+i;
				var sellGrid='#merchandiseSell_Grid'+i;
				var materialGrid='#merchandiseMaterial_Grid'+i;
				// 结束价格grid编辑
				if (reportFormFn.endEditPrice(i)) {
					$(priceGrid).datagrid('acceptChanges');
				} else {
					return false;
				}
				// 结束相同价格grid编辑
				if (reportFormFn.endEditSame(i)) {
					$(sameGrid).datagrid('acceptChanges');
				} else {
					return false;
				}
				// 结束销售grid编辑
				if (reportFormFn.endEditSell(i)) {
					$(sellGrid).datagrid('acceptChanges');
				} else {
					return false;
				}
				// 结束原料grid编辑
				if (reportFormFn.endEditMaterial(i)) {
					$(materialGrid).datagrid('acceptChanges');
				} else {
					return false;
				}
				//判断商品价格模块是否已经填写
				/*if($(priceGrid).datagrid('getRows').length<1){
					 $.messager.alert('提示', '<center>商品'+(i+1)+'商品价格模块不能为空！</center>');
					 return;   
				}*/
				
				var selectedRadioValue=$("input[name='samePart"+i+"']:checked").val();
				if(selectedRadioValue=="1"){
					//说明选择了"有"单选框
					if($(sameGrid).datagrid('getRows').length<1){
						/*$.messager.alert('提示', '<center>商品'+(i+1)+'同类商品市场零售价模块不能为空！</center>');
						 return; */
					}else{
						sameRows[i]=JSON.stringify($(sameGrid).datagrid('getRows'));
					}
				}else{
					sameRows[i]='[]';
				}

				//判断商品销售预计模块是否已经填写
				/*if($(sellGrid).datagrid('getRows').length<1){
					 $.messager.alert('提示', '<center>商品'+(i+1)+'商品销售预计模块不能为空！</center>');
					 return;   
				}*/
			
				reportFormFn.setPropertiesName(i);
				
				priceRows[i]=JSON.stringify($(priceGrid).datagrid('getRows'));
				sellRows[i]=JSON.stringify($(sellGrid).datagrid('getRows'));
				materialRows[i]=JSON.stringify($(materialGrid).datagrid('getRows'));
			}
			//对form表单进行验证
			/* if(!$('#reportNew_form').form('validate')){
					return;
			 }*/
			
			 $("#saveForm").linkbutton("disable");
			 $("#reportNew_form").form('submit', {
					url : url,
					onSubmit : function(param) {
						param.priceRowsList = priceRows;
						param.sameRowsList = sameRows;
						param.sellRowsList = sellRows;
						param.materialRowsList = materialRows;
					},
					success : function(data) {
						var json = $.parseJSON(data);
						if (json.success) {
							parent.messagerShow({
								title : '操作成功!',
								msg : json.msg
							});
						}else{
							parent.messagerShow({
								title : '操作失败!',
								msg : json.msg
							});
						}
						$("#saveForm").linkbutton("enable");
						//保存之后，重新加载数据，将利率显示出来
						for(var k=0;k<intentionList.length;k++){
							$('#merchandisePrice_Grid'+k).datagrid('reload');
						}
					}
				});
			
			/*utils.form("reportNew_form").submit(url, null, function() {
			});*/
		},
		
		/**
		 * 设置下拉框选中的名称
		 */
		setPropertiesName:function(index) {
			var target = ['dxRoleCode', 'dlRoleCode', 'centreTypeCodeElse',
			              'smallTypeCode', 'detailTypeCode', 'fineTypeCode'
			             ];
			for (var i in target) {
//				console.info("#" + target[i] + index);
				var text = $("#" + target[i] + index).combobox('getText');
				$("#" + index + target[i]).val(text);
			}
		},
		
		closeForm: function() {
			parent.pubTab.closeCurrTab();
		},
		/* 价格的js */
		endEditPrice : function(gridId) {
			if (priceEditIndex[gridId] == undefined) {
				return true
			}
			if ($('#merchandisePrice_Grid'+gridId).datagrid('validateRow', priceEditIndex[gridId])) {
				$('#merchandisePrice_Grid'+gridId).datagrid('endEdit', priceEditIndex[gridId]);
				priceEditIndex[gridId] = undefined;
				return true;
			} else {
				return false;
			}
		},
		onClickRowPrice : function(gridId,index) {
			if (priceEditIndex[gridId] != index) {
				if (reportFormFn.endEditPrice(gridId)) {
					$('#merchandisePrice_Grid'+gridId).datagrid('selectRow', index).datagrid('beginEdit', index);
					priceEditIndex[gridId] = index;
				} else {
					$('#merchandisePrice_Grid'+gridId).datagrid('selectRow', priceEditIndex[gridId]);
				}
			}
		},
		appendPrice : function(gridId) {
			var date = $('#merchandisePrice_Grid'+gridId).datagrid('getData');
			if (reportFormFn.endEditPrice(gridId)) {
				$('#merchandisePrice_Grid'+gridId).datagrid('appendRow', {
					status : 'P'
				});
				priceEditIndex[gridId] = $('#merchandisePrice_Grid'+gridId).datagrid('getRows').length - 1;
				$('#merchandisePrice_Grid'+gridId).datagrid('selectRow', priceEditIndex[gridId]).datagrid('beginEdit', priceEditIndex[gridId]);
			}

		},
		removePrice : function(gridId) {
			if (priceEditIndex[gridId] == undefined) {
				return
			}
			$('#merchandisePrice_Grid'+gridId).datagrid('cancelEdit', priceEditIndex[gridId]).datagrid('deleteRow', priceEditIndex[gridId]);
			priceEditIndex[gridId] = undefined;
		},
		
		/* 同类商品的js */
		endEditSame : function(gridId) {
			if (sameEditIndex[gridId] == undefined) {
				return true
			}
			if ($('#merchandiseSame_Grid'+gridId).datagrid('validateRow', sameEditIndex[gridId])) {
				$('#merchandiseSame_Grid'+gridId).datagrid('endEdit', sameEditIndex[gridId]);
				sameEditIndex[gridId] = undefined;
				return true;
			} else {
				return false;
			}
		},
		
		// 商品原料
		endEditMaterial : function(gridId) {
			if (materialEditIndex[gridId] == undefined) {
				return true
			}
			if ($('#merchandiseMaterial_Grid'+gridId).datagrid('validateRow', materialEditIndex[gridId])) {
				$('#merchandiseMaterial_Grid'+gridId).datagrid('endEdit', materialEditIndex[gridId]);
				materialEditIndex[gridId] = undefined;
				return true;
			} else {
				return false;
			}
		},
		
		appendMaterial : function(gridId) {
			var date = $('#merchandiseMaterial_Grid'+gridId).datagrid('getData');
			if (reportFormFn.endEditMaterial(gridId)) {
				$('#merchandiseMaterial_Grid'+gridId).datagrid('appendRow', {
					status : 'P'
				});
				materialEditIndex[gridId] = $('#merchandiseMaterial_Grid'+gridId).datagrid('getRows').length - 1;
				$('#merchandiseMaterial_Grid'+gridId).datagrid('selectRow', materialEditIndex[gridId]).datagrid('beginEdit', materialEditIndex[gridId]);
			}

		},
		
		removeMaterial : function(gridId) {
			if (materialEditIndex[gridId] == undefined) {
				return
			}
			$('#merchandiseMaterial_Grid'+gridId).datagrid('cancelEdit', materialEditIndex[gridId]).datagrid('deleteRow', materialEditIndex[gridId]);
			materialEditIndex[gridId] = undefined;
		},
		//商品原料
		onClickRowMaterial : function(gridId,index) {
			if (materialEditIndex[gridId] != index) {
				if (reportFormFn.endEditMaterial(gridId)) {
					$('#merchandiseMaterial_Grid'+gridId).datagrid('selectRow', index).datagrid('beginEdit', index);
					materialEditIndex[gridId] = index;
				} else {
					$('#merchandiseMaterial_Grid'+gridId).datagrid('selectRow', materialEditIndex[gridId]);
				}
			}
		},
		
		onClickRowSame : function(gridId,index) {
			if (sameEditIndex[gridId] != index) {
				if (reportFormFn.endEditSame(gridId)) {
					$('#merchandiseSame_Grid'+gridId).datagrid('selectRow', index).datagrid('beginEdit', index);
					sameEditIndex[gridId] = index;
				} else {
					$('#merchandiseSame_Grid'+gridId).datagrid('selectRow', sameEditIndex[gridId]);
				}
			}
		},
		appendSame : function(gridId) {
			var date = $('#merchandiseSame_Grid'+gridId).datagrid('getData');
			if (reportFormFn.endEditSame(gridId)) {
				$('#merchandiseSame_Grid'+gridId).datagrid('appendRow', {
					status : 'P'
				});
				sameEditIndex[gridId] = $('#merchandiseSame_Grid'+gridId).datagrid('getRows').length - 1;
				$('#merchandiseSame_Grid'+gridId).datagrid('selectRow', sameEditIndex[gridId]).datagrid('beginEdit', sameEditIndex[gridId]);
			}

		},
		removeSame : function(gridId) {
			if (sameEditIndex[gridId] == undefined) {
				return
			}
			$('#merchandiseSame_Grid'+gridId).datagrid('cancelEdit', sameEditIndex[gridId]).datagrid('deleteRow', sameEditIndex[gridId]);
			sameEditIndex[gridId] = undefined;
		},
		
		/* 销售的js */
		endEditSell : function(gridId) {
			if (sellEditIndex[gridId] == undefined) {
				return true
			}
			if ($('#merchandiseSell_Grid'+gridId).datagrid('validateRow', sellEditIndex[gridId])) {
				$('#merchandiseSell_Grid'+gridId).datagrid('endEdit', sellEditIndex[gridId]);
				sellEditIndex[gridId] = undefined;
				return true;
			} else {
				return false;
			}
		},
		onClickRowSell : function(gridId,index) {
			if (sellEditIndex[gridId] != index) {
				if (reportFormFn.endEditSell(gridId)) {
					$('#merchandiseSell_Grid'+gridId).datagrid('selectRow', index).datagrid('beginEdit', index);
					sellEditIndex[gridId] = index;
				} else {
					$('#merchandiseSell_Grid'+gridId).datagrid('selectRow', sellEditIndex[gridId]);
				}
			}
		},
		appendSell : function(gridId) {
			var date = $('#merchandiseSell_Grid'+gridId).datagrid('getData');
			if (reportFormFn.endEditSell(gridId)) {
				$('#merchandiseSell_Grid'+gridId).datagrid('appendRow', {
					status : 'P'
				});
				sellEditIndex[gridId] = $('#merchandiseSell_Grid'+gridId).datagrid('getRows').length - 1;
				$('#merchandiseSell_Grid'+gridId).datagrid('selectRow', sellEditIndex[gridId]).datagrid('beginEdit', sellEditIndex[gridId]);
			}

		},
		removeSell : function(gridId) {
			if (sellEditIndex[gridId] == undefined) {
				return
			}
			$('#merchandiseSell_Grid'+gridId).datagrid('cancelEdit', sellEditIndex[gridId]).datagrid('deleteRow', sellEditIndex[gridId]);
			sellEditIndex[gridId] = undefined;
		},
		
		//判断是否有同类商品
		changeSameRaido:function(index,checkValue){
			if("1"==checkValue){
				//点击"有"
				$('#same_div'+index).show();
				$('#notSame_div'+index).hide();
				$("#merchandiseSame_Grid"+index).datagrid({fitColumns:true});
				$("#merchandiseSame_Grid"+index).datagrid('load');
			}else{
				//点击"无"
				//$('same_div0').style.display="none";
				$('#same_div'+index).hide();
				$('#notSame_div'+index).show();
				$("#merchandiseSame_Grid"+index).datagrid('load');
			}
		},
		
		
		// 重新加载
		reloadData : function(thisId, targetId, method, number) {
			reportFormFn.clearSelectedData(targetId+number);
			// 当前框的值
			var value = $("#" + thisId+number).combobox("getValue");
			if (!value) {
				value = 'null';
			}
			$("#" + targetId+number).combobox('reload', "masterDataType_list" + method + "_5.html?" + thisId + "=" + value);
		},

		// 清空下一个连动框已选择的值
		clearSelectedData : function(targetId) {
			$("#" + targetId).combobox("setValue", "");
			$("#" + targetId).combobox("clear");
		},
		
		getObjectNumber:function(obj){
			var _id = $(obj).attr('id');
			var number;
			number = _id.substr(_id.length - 1, _id.length);
			return number;
		},
		
		//格式化百分比
		formatterPercent:function(val) {
			if (val == '' || val == undefined) return '';
			val += ""
			if (val.indexOf('%') > -1){
				val = val.substring(0, val.length - 1);
			} 
			if (val == '0' || val == '') {
				return '0.00%';
			} else {
				val = (new Number(val)).toFixed(2);
				return val + '%';
			}
		}

	};