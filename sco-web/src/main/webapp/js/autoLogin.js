
$(document).ready( function(){
	
	if (!window.ActiveXObject)
    	alert("Sorry, your browser does not support automatic signing In. \nIf you want to singing in automatically, please use the IE core browsers. \nOtherwise , please enter your user name and password in the next page.");
    else {
    	try{
			var WshNetwork = new ActiveXObject("WScript.Network");
			$('#username').val(WshNetwork.UserName);
			$('#password').val("123456");
			
			submitLoginForm();
		}
		catch(e)
		{
				alert("Sorry, the security settings are not right：" + e.description + "\nYou can use the following solutions：\n 1、Enter your user name and password in the next page；\n 2、Changing the security settings of IE explorer。\n    Tools> Internet Options>Security>Internet> Custom Level> ActiveX Controls and plug-ins>Look for ‘Initializing and running the script of the ActiveX controls which not marked as safe’, set to start");
		}
    }
    
    function submitLoginForm()
    {
		var userName = $("#username").attr("value");
		var password = $("#password").attr("value");
		var param = {
			dotyp : "2",
			ac : "login",
			autoLogin : "100",
			CheckCode : "autoLogin",
			loginName : userName,
			password : password
		};
		$.ajax({
			type : "POST",
			url : "/login!run.do",
			dataType : "json",
			data : param,
			success : callback,
			error : function(msg) {
				show("登录失败，未连接服务器!");
			}
		});
    }
    
    function callback(data) {
		var json = eval(data);
		var userName = document.getElementById('username');

		if (data.success == true) {
			window.location = '/index.html';
		} else {
			show(data.msg);
		}
	};
	
	function show(msg) {
		$("#wrong").html(msg);
		$("#wrong").css("display", "");
	};
	
	
});