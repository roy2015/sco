jQuery(function($) {//On Ready
	var url = '#';
	function show(msg) {
		var o = document.getElementById("wrong");
		o.innerHTML = "<img src='images/index.gif'/>"+msg;
		o.style.display = "";
	};
	
	function hide(o) {
		var o = document.getElementById(o);
		o.style.display = "none";
		window.location = url;
	};

	function changeImg() {
		var imgSrc = $("#imgObj");
		var src = imgSrc.attr("src");
		imgSrc.attr("src", chgUrl(src));
	};
	
	//时间戳    
	function chgUrl(url) {
		var timestamp = (new Date()).valueOf();
		url = url.substring(0, 50);
		if ((url.indexOf("&") >= 0)) {
			url = url + "×tamp=" + timestamp;
		} else {
			url = url + "?timestamp=" + timestamp;
		}
		return url;
	};
	
	function subForm() {
		var checkCode = $("#checkcode").attr("value");
		var userName = $("#username").attr("value");
		var password = $("#password").attr("value");
		var param = {
			dotyp : "2",
			ac : "login",
			CheckCode : checkCode,
			loginName : userName,
			password : password
		};
		$.ajax({
			type : "POST",
			url : "login!run.do",
			dataType : "json",
			data : param,
			success : callback,
			error : function(msg) {
				show("登录失败，未连接服务器!");
			}
		});
		//$('#loginBtn').attr("disabled","true");
	};

	function callback(data) {
		var json = eval(data);
		var userName = document.getElementById('username');
		//setCookie('EWMS_USER_ID',userName.value);

		if (data.success == true) {
			if (document.getElementById("remUser").checked == true) {
				setCookie('EWMS_USER_ID', userName.value);
			} else {
				deleteCookie('EWMS_USER_ID');
			}
			;
			window.location = 'index.html';
		} else {
			changeImg();
			show(data.msg);
		}
	};

	function checkForm() {
		var userName = document.getElementById("username").value;
		if (userName == "") {
			show('用户名或密码不能为空!');
			changeImg();
			document.getElementById("username").focus();
			return false;
		}

		var password = document.getElementById("password").value;
		if (password == "") {
			show('用户名或密码不能为空!');
			changeImg();
			document.getElementById("password").focus();
			return false;
		}

		var checkCode = document.getElementById("checkcode").value;
		if (checkCode.length != 4) {
			show('请录入4位校验码!');
			changeImg();
			document.getElementById("checkcode").focus();
			return false;
		}
		subForm();
	};

	function setCookie(name, value, expires, path, domain, secure) {
		document.cookie = name + "=" + escape(value)
				+ ((expires) ? "; expires=" + expires : "")
				+ ((path) ? "; path=" + path : "")
				+ ((domain) ? "; domain=" + domain : "")
				+ ((secure) ? "; secure" : "");
	};

	function getCookie(name) {
		var arg = name + "=";
		var alen = arg.length;
		var clen = document.cookie.length;
		var i = 0;
		while (i < clen) {
			var j = i + alen;
			if (document.cookie.substring(i, j) == arg) {
				return getCookieVal(j);
			}
			i = document.cookie.indexOf(" ", i) + 1;
			if (i == 0)
				return "";
		}
		return;
	};
	
	function getCookieVal(offset) {
		var endstr = document.cookie.indexOf(";", offset);
		if (endstr == -1) {
			endstr = document.cookie.length;
		}
		return unescape(document.cookie.substring(offset, endstr));
	};

	function deleteCookie(name, path, domain) {
		if (getCookie(name)) {
			document.cookie = name + "=" + ((path) ? "; path=" + path : "")
					+ ((domain) ? "; domain=" + domain : "")
					+ "; expires=Thu, 01-Jan-70 00:00:01 GMT";
		}
	};
	
	function initLoginName() {
		var userName = document.getElementById('username');
		if (getCookie('EWMS_USER_ID') != null) {
			var name = getCookie('EWMS_USER_ID');
			if (name == undefined)
				name = "";
			userName.value = name;
		}
		userName.focus();
	};
	//全局回车
	/*
	//此方法不兼容IE
	$(window).keydown(function(e) {
		if ((e.keyCode || e.which) == 13) {
			checkForm();
		}
	});
	*/
	
	function keypress(e) 
	{ 
		var e=e||event; 
		if(e.keyCode==13)checkForm();
	} 
	document.onkeypress=keypress; 
	
	
	//点击登陆
	$('#loginBtn').bind('click',function() {
		checkForm();
	});
	
	//点击验证码图片
	$('#changeImgHref').bind("click",function(){
		changeImg();
	});
	
	initLoginName();
});