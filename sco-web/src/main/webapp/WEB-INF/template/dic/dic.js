var dicGrid=null;
var editIndex = undefined;
var record=null;
var isDefaultRow=null;
var isSelect="Y";
$(document).ready(function(){
	dicGrid=utils.grid($('#dic_dg'));
	// 注册语言过滤框
	dicGrid.registerExtFilters("language");
	dicGrid.initFilters({
		
		onClickRow:dicFn.setButtonState,//设置按钮状态
		onDblClickRow:dicFn.showEdit,//双击编辑
		//onEndEdit:dicFn.doFilters(),
		
		onLoadSuccess:function(){
			$('#dic_dg').datagrid('enableDnd');
			$('#dic_dg').datagrid('clearSelections');
			if(isSelect==="Y"){
				dicFn.doFilters();
				isSelect="N";
			}
			dicFn.setButtonState();
		},
		qureryParams:{
			language:$("#language").val()
		},
		onDragEnter:function(targetRow, sourceRow){
			var dicType=$("#type").val();
			if(dicType===""){
				$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("dic.msg.chooseType")}');
				return false;
			}
		},
		onDrop:function(targetRow, sourceRow,point){
			var dicType=$("#type").val();
			var tagetOrder=null;
//			console.info(targetRow.keyOrder);
//			console.info(sourceRow.keyOrder);
//			console.info(point);
//			console.info(dicType);
			tagetOrder=targetRow.keyOrder;
//			if(point==='top'){
//				tagetOrder=targetRow.keyOrder-1;
//			}
//			if(point==='bottom'){
//				tagetOrder=targetRow.keyOrder;
//			}
			$.post("dic_completeMove_2.html", {dicType:dicType,sourceOrder:sourceRow.keyOrder,tagetOrder:tagetOrder,point:point}, function(){
				$('#dic_dg').datagrid('reload');
			},"json");
		}
	});
});
var dicFn={
	//显示添加或修改窗体
	showForm:function(isEdit){
		var title='${action.getText("common.title.add",action.getText("dic.dic.title.insert"))}';
		var href='dic_showDicForm_1.html';
		if(!isEdit){
			//清除选中的row
			$('#dic_dg').datagrid("clearSelections");
		}
		if(isEdit){//显示修改窗体
			//获取dicGrid中要修改的记录
			record=dicGrid.getSelectedRecord();
			isDefaultRow=$('#dic_dg').datagrid('getSelected');//是否默认值得回显
			//console.info(isDefaultRow.isDefault);
			if(record==null)return;
			title='${action.getText("common.title.edit",action.getText("dic.dic.title.update"))}';
			href='dic_showDicEidtForm_1.html?id='+record.id;
		}
		dlg = utils.showDlg({
			title:title,href:href,width:600,height:350,
			onLoad:function(){
				var dicFormGrid=utils.grid($('#dicForm_dg'));
				if(isEdit){
					if(isDefaultRow.isDefault==='是'){
						document.getElementById("isDefault").checked=true;
					}else{
						document.getElementById("isDefault").checked=false;
					}
					var language=$("#language").combobox('getValue');
					$('#dicForm_dg').datagrid({
						url:"dic_listDicLocale_2.html?id="+record.id+"&language="+language
					});
				}
				dicFormGrid.initFilters({
					onBeforeLoad:function(){
						dicFn.setDicLocaleButtonState();
					},
					onClickRow:dicFn.setDicLocaleButtonState,//设置按钮状态
					onLoadSuccess:function(){
						//dicFn.setDicLocaleButtonState();
						$("#isDefault").checked=false;
						var rows = $("#dicForm_dg").datagrid("getRows");
						for (var i = 0; i < rows.length; i++) {
							$("#dicForm_dg").datagrid("beginEdit", i);
						}
					}
				});
				//选择类型时联想到类型描述
				 $("#dicType").combobox({  
			         onChange:function(){ 
			     		var typeId=$("#dicType").combobox('getValue');
			     		$.post("dic_listTypes_2.html", {dicType:typeId}, function(data){
			     			//console.info(data.rows);
			     			$("#typeDesc").val(data.rows);
						},"json");
			         }
				 }) ; 
				
				//设置编辑状态下键和类别不可更改
				if(isEdit){
					//$('#dicType').combo('readonly', true);
					$('#dicType').combobox('disable');			
					$("#dicKey").attr('disabled','disabled');
				}
				//新增时，判断该类型下新增的键是否已存在！
				if(!isEdit){
					$("#dicKey").blur(function(){ 
						var typeId=$("#dicType").combobox('getValue');
						//var typeText=$("#dicType").combobox('getText');
						//var typeDesc=$('#typeDesc').val();
						var dicKey=$("#dicKey").val();
						$.post("dic_existDicTypeAndKey_2.html",{dicType:typeId,dicKey:dicKey} , function(data){
							if(data.rows){
								$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("dic.msg.confirmDicTypeKey")}',"question");
								return false;
							}
						},"json");
					});
				}
			},
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){dicFn.submitForm();},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){dlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},	
	
	//新增
	showInsert:function() {
		dicFn.showForm(false);
	},
	
	//编辑dic相关信息
	showEdit:function(){
		dicFn.showForm(true);
	},
	
	submitForm:function(){
		var id=$("#id").val();
		//var row=$('#dic_dg').datagrid('getSelected');
		var typeId=$("#dicType").combobox('getValue');
//		console.info(typeId);
//		var typeText=$("#dicType").combobox('getText');
//		console.info(typeText);
		var typeDesc=$('#typeDesc').val();
		var dicKey=$("#dicKey").val();
		var valueDesc=$("#valueDesc").val();
		var isDefault = document.getElementById("isDefault"); 
		var language=$("#language").combobox('getValue');
		//console.info(isDefault.checked);
		var rows=$("#dicForm_dg").datagrid("getRows");
		
		//结束编辑状态
		for(var i=0; i < rows.length; i++){
			if($("#dicForm_dg").datagrid("validateRow", i)){
				$('#dicForm_dg').datagrid('endEdit',i);
			}
		}
		var isEmpty=false;
		for(var i=0; i < rows.length; i++){
			if(rows[i].language==""||rows[i].language==null){
				isEmpty=true;
				return isEmpty;
			}
		}
		if(rows.length==0||isEmpty){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("dic.msg.confirmChooselanguage")}',"question");
			return false;
		}
		var url='dic_insertDic_2.html';
		if(id){
			url='dic_updateDic_2.html';
		}
		var is=$("#typeDesc").validatebox("isValid")&&$("#dicKey").validatebox("isValid")&&$("#dicType").combobox("isValid");
		if(!is){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("dic.msg.fullInformation")}',"question");
			return false;
		}
		var arr=new Array();
		var isrepeat=true;
		//判断语言类型是否重复
		for(var i=0;i<rows.length;i++)
		{
			arr[i]=rows[i];
			for(var j=0;j<i;j++){
				if(arr[i].language===arr[j].language){
					isrepeat=false;
				}
			}
		}
		//重复
		if(!isrepeat){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("dic.msg.languagerepeat")}',"question");
			//开启编辑模式
			var dicFormRows = $("#dicForm_dg").datagrid("getRows");
			for (var i = 0; i < dicFormRows.length; i++) {
				$("#dicForm_dg").datagrid("beginEdit", i);
			}
			return false;
		}
		
		$.post(url,{id:id,dicType:typeId,dicKey:dicKey,typeDesc:typeDesc,isDefault:isDefault.checked,valueDesc:valueDesc,language:language,rows:JSON.stringify(rows)}, function(data){
			if(data){
				dlg.dialog('close');
				$('#dic_dg').datagrid('reload');
			}
		},"json");
	},
	
	//添加dic Locale相关信息
	insertDicLocale:function(){
		var length = $("#dicForm_dg").datagrid("getRows").length;
		$("#dicForm_dg").datagrid("appendRow", {id:'',language:'',dicValue:'',valueAlias:''});
		$("#dicForm_dg").datagrid("beginEdit", length);
	},
	
	//清空查询
	clearFilter:function(){
		dicGrid.clearFilter();
	},
	
	//过滤语言
	doFilter:function(){
		if(dicGrid!=null){
			var params = dicGrid.getFilterValue();
			params.language = $("#language").combo("getValue");
			dicGrid.doFilter(params);
		}
	},
	doFilters:function(){
		if(dicGrid!=null){
			var params = dicGrid.getFilterValue();
			params.language = 'zh_cn';
			dicGrid.doFilter(params);
		}
	},
	//
	//删除dic基础信息
	remove:function(){
		var language=$("#language").combobox('getValue');
		var status=null;
		//console.info(language);
		var rows=$('#dic_dg').datagrid('getChecked');
		//console.info(rows);
		if(rows==null)return false;
		for(var i=0;i<rows.length;i++){
			if(rows[i].status==="Y"){
				status="Y";
			}
		}
		if(status==="Y"){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("dic.msg.deleteStatus")}',"question");
			return false;
		}
		var ids='';
		for(var i=0;i<rows.length;i++){
			var id=rows[i].id;
			if(i!=rows.length-1){
				ids=ids+id+",";
			}else{
				ids=ids+id;
			}
		}
		utils.confirm('${action.getText("dic.dic.title.deleteConfirm")}','${action.getText("dic.msg.confirmDeleteDic")}',function(){
			$.post("dic_deleteDics_2.html", {ids:ids,language:language}, function(){
				$('#dic_dg').datagrid('reload');
			},"json");
		});
	},
	
	//删除DicLocale
	removeDicLocale:function(){
		var dicLocaleId =$('#dicForm_dg').datagrid("getRowIndex",$('#dicForm_dg').datagrid('getSelected'));
		$("#dicForm_dg").datagrid("deleteRow",dicLocaleId);
	},
	
	//激活
	active:function(){
		//var row=$('#dic_dg').datagrid('getSelected');
		var rows=$('#dic_dg').datagrid('getChecked');
		utils.confirm('${action.getText("dic.dic.title.confirm")}','${action.getText("dic.msg.confirmActiveDic")}',function(){
			$.post("dic_activeStatus_2.html", {rows:JSON.stringify(rows)}, function(){
				$('#dic_dg').datagrid('reload');
			},"json");
		});
	},
	
	//禁用
	forbidden:function(){
		//var row=$('#dic_dg').datagrid('getSelected');
		var rows=$('#dic_dg').datagrid('getChecked');
		utils.confirm('${action.getText("dic.dic.title.confirm")}','${action.getText("dic.msg.confirmNoActiveDic")}',function(){
			$.post("dic_noActiveStatus_2.html", {rows:JSON.stringify(rows)}, function(){
				$('#dic_dg').datagrid('reload');
			},"json");
		});
	},
	
	//置顶
	top:function(){
		var url='dic_completeMoveTop_2.html';
		dicFn.topOrBottom(url);
	},
	
	//置末
	bottom:function(){
		var url='dic_completeMoveBottom_2.html';
		dicFn.topOrBottom(url);
	},
	
	//置顶或值末
	topOrBottom:function(url){
		var dicType=$("#type").val();
		if(dicType===""){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("dic.msg.chooseType")}',"question");
			return false;
		}
		var row=$('#dic_dg').datagrid('getSelected');
		if(row==null)return false;
		$.post(url, {dicType:dicType,sourceOrder:row.keyOrder}, function(){
			$('#dic_dg').datagrid('reload');
		},"json");
	},
	
	//显示导入导出窗体
	showImportForm:function(){
		var title='${action.getText("common.title.add",action.getText("dic.dic.title.import"))}';
		var href='dic_showDicImportForm_1.html';
		importDlg = utils.showDlg({
			title:title,href:href,width:500,height:180,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){dicFn.importSubmitForm(importDlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){importDlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	
	//dic导入
	import:function(){
		dicFn.showImportForm();
	},
	importSubmitForm:function(importDlg){
		//var language=$("#language").combobox('getValue');
		var url='dic_completeImport_2.html';
		utils.form("dicImport_fm").submit(url,null,function(){
			importDlg.dialog('close');
			$('#dic_dg').datagrid('reload');
		});
	},
	
	//dic导出
	dicExport:function(){
		window.location='dic_export_3.html?'+$.param(dicGrid.getFilterValue());
	},
	//dic翻译导出
	translateExport:function(){
		var title='${action.getText("common.title.add",action.getText("dic.dic.title.translateExport"))}';
		var href='dic_showtranslateExport_1.html';
		importDlg = utils.showDlg({
			title:title,href:href,width:400,height:180,
			buttons:[{text:'${action.getText("common.button.submit")}',handler:function(){dicFn.translateExportSubmit(importDlg);},iconCls:'save'},
			        {text:'${action.getText("common.button.cancel")}',handler:function(){importDlg.dialog('close');},iconCls:'cancel'}
			]
		});
	},
	
	translateExportSubmit:function(importDlg){
		var sourceLanguage=$("#sourceLanguage").combobox('getValue');
		var targetLanguage=$("#targetLanguage").combobox('getValue');
		if(sourceLanguage===targetLanguage){
			$.messager.alert('${action.getText("pub.msg.prompt")}','${action.getText("dic.msg.translateExportInformation")}',"question");
			return null;
		}
		window.location="dic_translateExport_3.html?"+$.param(dicGrid.getFilterValue())+"&sourceLanguage="+sourceLanguage+"&targetLanguage="+targetLanguage;
		importDlg.dialog('close');
	},
	
	//设置dic_dg按钮状态
	setButtonState:function(){
		//var rows = $('#dic_dg').datagrid("getChecked");
		var rows = $('#dic_dg').datagrid("getSelections");
		//console.info(rows);
		//var row=$('#dic_dg').datagrid('getSelected');
		if(rows.length>0){
			$("#showEdit").linkbutton("enable");
			$("#remove").linkbutton("enable");
			$("#top").linkbutton("enable");
			$("#bottom").linkbutton("enable");
		}else{
			$("#showEdit").linkbutton("disable");
			$("#remove").linkbutton("disable");
			$("#top").linkbutton("disable");
			$("#bottom").linkbutton("disable");
		}
	},
	
	//设置dicForm_dg按钮状态
	setDicLocaleButtonState:function(){
		var rowDicLocale=$("#dicForm_dg").datagrid("getSelections");
		var rows=$("#dicForm_dg").datagrid("getRows");
		if(rowDicLocale.length>0&&rows.length>0){
			$("#showEditDicLocale").linkbutton("enable");
			$("#removeDicLocale").linkbutton("enable");
		}else{
			$("#showEditDicLocale").linkbutton("disable");
			$("#removeDicLocale").linkbutton("disable");
		}
	},
};