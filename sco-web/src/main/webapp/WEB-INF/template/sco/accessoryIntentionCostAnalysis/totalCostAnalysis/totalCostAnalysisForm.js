var isSubmit=false;
var totalcostanalysisFormFn = {
		// 导出到Excel
		export2Excel : function() {
			var url = 'totalcostanalysis_exportDataToExcel_6.html';
			var jsons=$('#json').val().replace(new RegExp(/(@%)/g), "\"");
			var remark=$('#remark').val().replace(new RegExp(/(@%)/g), "\"");;
			//jsons="["+jsons+"]";
		//	console.info(jsons);
			var json1=JSON.parse(jsons);
			
			//var json =JSON.stringify(json1).replace(/"([^"]*)"/g, "'$1'");
		//	var post={date:JSON.stringify(json1).replace(/"([^"]*)"/g, "'$1'")};//JSON.stringify(json)把json转化成字符串
        //    $.post(url,post);
            $("#zjfx_form").form('submit', {
				url : url,
				onSubmit : function(param) {
					param.date = JSON.stringify(json1).replace(/"([^"]*)"/g, "'$1'");
					param.remark=remark;
				},
				success : function(data) {
					var json = $.parseJSON(data);
					if (json.success) {
						parent.messagerShow({
							title : '操作成功!',
							msg : json.msg
						});
					}else{
						$.messager.alert('提示', json.msg);
						/*parent.messagerShow({
							title : '操作失败!',
							msg : json.msg
						});*/
					}
				}
			});
		//	window.location = url;
			
		},
	
	
	// 填写保存文件名称
	saveFile : function() {
		$("#saveFileDlg").window('open');// 打开窗口
	},
	//关闭界面
	closeSaveFileDlg:function() {
		$("#fileName").val("");//清空填写的值
        $("#saveFileDlg").window('close');//关闭窗口
	},
	// 提交填写文件名称的对话框
	submitSaveFileDlg : function() {
		var fileName = $.trim($("#fileName").val());
		if (fileName) {// 校验文件合法性
			if (!E2E_validate_fileName(fileName)) {
				$.messager.alert("提示", "文件名称不合法");
				return;
			}
		} else {
			$.messager.alert("提示", "文件名称不能为空");
			return;
		}
		if(isSubmit){
			return;
		}
		isSubmit=true;
		//获取分析表的html代码
		var param =$("#contrastResult").html();
		$.post("totalcostanalysis_saveSearchDataForm_2.html?", {data : param,fileName : fileName}, function(data) {
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
			isSubmit=false;
		});
	},
	close : function() {
		parent.pubTab.closeTab('总成本分析内容');
	},
	setInpoutValue : function() {
		var remarkId="remark";	
		var value=$("#"+remarkId).val();
		$("#"+remarkId).attr("value",value); 
		}
	
};