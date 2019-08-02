
var readedBytes = 0;//上传监控 已读  
var totalBytes = 0; //总数
var uploadErroMsg = "上传失败" ;
var error = '0';

var uploadFn = {
	// 上传文件
	uploadAccessoryFiles:function(path, paraData, fileReg, msg, form, fileInput, fileBtn, grid) {

		error = "0";
		readedBytes = 0;
        totalBytes = 0;

		var fileVal = $("#"+fileInput).val();
		if('' == fileVal){
			$.messager.alert("提示","请选择要上传的附件信息","warning");
			return;
		}else{
			if ('' != fileReg) {
				var newFileName = fileVal.split('.');
				newFileName = newFileName[newFileName.length-1];
				if (newFileName.search(fileReg) == -1) {
					$.messager.alert("提示", "您所上传的文件类型不符合要求"+msg, "warning");
					return;
				}
			}

			//$.post("re/servlet/ReRemoveUploadSession",null,function(){uploadFn.doUpload(path, paraData, fileReg, msg, form, fileInput, fileBtn, grid);});

			uploadFn.doUpload(path, paraData, fileReg, msg, form, fileInput, fileBtn, grid);

			/*// 先清空session
			$.ajax({
				url : 're/servlet/ReRemoveUploadSession',
				type : 'post',
				success : function() {
					uploadFn.doUpload(path, paraData, fileReg, msg, form, fileInput, fileBtn, grid);
				},
				error:function() {
					$.messager.alert("提示","上传失败","info",function(){
						$('#'+fileBtn).linkbutton('enable');//按钮可用
						$("#"+grid).datagrid("reload");
						readedBytes = 0 ;
						totalBytes = 0;
						var file = $('#'+fileInput);
						file.after(file.clone().val("")); 
						file.remove(); 
					});
				}
			});*/
		}
	},

	doUpload:function(path, paraData, fileReg, msg, form, fileInput, fileBtn, grid) {
		var timestamp = Date.parse(new Date());

		var options = {
			url : 're/servlet/UploadAccessory?timestamp='+timestamp+'&'+$.param(paraData, undefined),
			type : 'post',
			beforeSubmit:function(params){
				$('#'+fileBtn).linkbutton('disable');//上传按钮置灰
			},
			success : function(fileName) {
				if (fileName != '') {
					try {
						var rtnObject = $.parseJSON(fileName);
						var rtnArr = rtnObject.rows[0].split(",");
						
						error = rtnArr[0];

//							$('#msg').hide();// 进度条隐藏
//							$('#msg').progressbar("setValue", 0);

						if(error == '1'){
			            	readedBytes = 0;//清空
							totalBytes = 0;
			            	uploadErroMsg = rtnArr[1];
							/*xp ie8上传失败时使用*/
//								$('#msg').hide();//进度条隐藏
							//$('#msg').progressbar('setValue', 0);
							$('#'+fileBtn).linkbutton('enable');//按钮可用
							$.messager.alert("提示",uploadErroMsg);
							//清空上传组件
							var file = $('#'+fileInput);
							file.after(file.clone().val("")); 
							file.remove();
							//$("#uploadAccessoryFilesGrid").datagrid("reload");
							return;
			            }

						/*var index = rtnArr[0].lastIndexOf('.') ;
						var showName = '' ;
						if(index == -1){
							showName = rtnArr[0] ;
						}else{
							showName = rtnArr[0].substring(0,rtnArr[0].lastIndexOf('.') );
						}
						$("#"+grid).datagrid("appendRow", {
							showName :showName,
							uploadDate : rtnArr[1],
							showName1 : rtnArr[0]
						});*/
						$.messager.alert("提示","上传成功","info",function(){
							$('#msg').hide();//进度条隐藏
							$('#'+fileBtn).linkbutton('enable');//按钮可用
							$("#"+grid).datagrid("reload");
							readedBytes = 0;
					        totalBytes = 0;
					        //清空上传组件
					        var file = $('#'+fileInput);
							file.after(file.clone().val("")); 
							file.remove(); 
						});
					} catch (e) {
						$('#msg').hide();//进度条隐藏
						$('#'+fileBtn).linkbutton('enable');//按钮可用
						$("#"+grid).datagrid("reload");
						readedBytes = 0;
				        totalBytes = 0;
				        //清空上传组件
				        var file = $('#'+fileInput);
						file.after(file.clone().val("")); 
						file.remove(); 
					}
				} else {
					$.messager.alert("提示","上传失败","info",function(){
						$('#msg').hide();//进度条隐藏
						$('#'+fileBtn).linkbutton('enable');//按钮可用
						readedBytes = 0;
						totalBytes = 0;
						//清空上传组件
						var file = $('#'+fileInput);
						file.after(file.clone().val("")); 
							file.remove(); 
					});
				}
			},
			error : function() {
				$('#msg').hide();//进度条隐藏
				$('#'+fileBtn).linkbutton('enable');//按钮可用
				$.messager.alert("提示",uploadErroMsg,"warning");
			}
		};
		/*servlet上传文件*/
		$("#"+form).ajaxSubmit(options);
		try {
			uploadFn.ajaxBackState(timestamp);
		} catch (e) {}
	},

	ajaxBackState:function(timestamp){
//		var flag = true ;
		$.ajax({
			url : 're/servlet/UploadAccessory?uploadStatus=uploadStatus&timestamp=' + timestamp,
			type : 'post',
			// 成功的话就把datagrid之中的选中行删除
			success : function(result) {
				if (result != '') {
					var obj = eval("("+result+")");  
		            readedBytes = obj.readedBytes;
		            totalBytes = obj.totalBytes;
		            //error = obj.error;
				}
//		            error = obj.error;

//		            if(error==1){
//		            	$('#msg').hide();//进度条隐藏
//		            	flag = false ;
//		            	uploadErroMsg = obj.statusMsg;
//		            	return ;
//		            }
//				} else {
//				}
			}
		});
//		 if(flag==true){
			 uploadFn.progressbar(readedBytes,totalBytes,timestamp);
//		 }
	},

	progressbar:function(readedBytes11,totalBytes11,timestamp){
		if (error == "1") {
			$('#msg').hide();// 进度条隐藏
			$('#msg').progressbar("setValue", 0);

			$.post("re/servlet/ReRemoveUploadSession?timestamp="+timestamp);

			return;
		}

		if (readedBytes11 == totalBytes11 && readedBytes11 != 0 && totalBytes11 != 0) {
			$('#msg').hide();// 进度条隐藏
			$('#msg').progressbar("setValue", 0);

			$.post("re/servlet/ReRemoveUploadSession?timestamp="+timestamp);

			return;
		}

		$('#msg').show();
		var iPerc = (readedBytes11 > 0) ? (readedBytes11 / totalBytes11) * 100 : 0; // percentages
		$('#msg').progressbar({
			    value: iPerc.toFixed(1)
			});
		//if(contractUploadStoreFlag == true){
		/*if(readedBytes>0 && readedBytes == totalBytes){
			return;
		}else{
			setTimeout("uploadFn.ajaxBackState('"+path+"')",500); 
		}*/

		//setTimeout("uploadFn.ajaxBackState('"+path+"')",200);
		setTimeout(function(){
			uploadFn.ajaxBackState(timestamp);
		}, 200);
	},

	//附件下载
	downloadAccessoryFiles:function(grid){
		var row = $("#"+grid).datagrid("getSelected");
		if(row == null) {
			$.messager.alert("提示","请选择需要下载的附件信息","warning");
			return;
		}
		var isfirefox = '0';
		if (window.navigator.userAgent.toLowerCase().indexOf("firefox") > -1) {
			isfirefox = '1';
		}
		window.location = "otherFile_downloadAccessoryFiles_2.html?id="+row.id + '&isfirefox=' + isfirefox;
	},

	//修改页面：删除附件
	deleteAccessoryFilesPageUploadRow:function(grid) {
		// 返回的是选中的行这个对象(行的对象)
		var row = $("#"+grid).datagrid("getSelected");
		if(row == null) {
			$.messager.alert("提示","请选择需要删除的附件信息","warning");
			return;
		}
		// 传递附件名称到后台进行删除
		var id = row.id;
		utils.confirm("操作确认","确认删除该附件?",function(){
			$.ajax({
				url : 'otherFile_deleteAccessoryFiles_2.html',
				type : 'post',
				data : {"id":id},
				// 成功的话就把datagrid之中的选中行删除
				success : function(data) {
					// 取得行的index索引
					var rowIndex = $("#"+grid).datagrid("getRowIndex",row);
					// 按照索引删除行
					$("#"+grid).datagrid("deleteRow",rowIndex);
				}
			});
		});
	}
}