<#compress>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${action.getText("pub.title.systemTitle")}</title>
<script>
	function loginOut(){
		var param = {dotyp:"2",ac:"loginOut"};
		$.ajax({
			type:"POST",
			url:"login!run.do",
			dataType : "json",
			data: param,
			success:callbackLoginOut,
			error:function(msg){}
		});

	}

	function callbackLoginOut(data){
	   if (data != null) {
		    try{
		    if (data.success == true){
		    
		     if($.browser.msie) {
 				window.location = 'login.html';
			  }else{
			  	window.location = 'login.html';
			  }
			
			}
		}
			catch (ex){};
		}else{
		}
	}
</script>
</head>
<body>
<div class="header"><div class="logo"><img src="images/logo.png" class="png" /></div></div>
<div class="header">
	<div class="hleft png">
    	<div class="logo"><img src="images/nlogo.png" class="png" /></div>
    </div>
    <div class="hrigt png">
    	<div class="user">
        	<div class="loginout png"><a href="javascript:loginOut()">Logout</a></div>
            <div class="help png"><a href="#">Help</a></div>
            <div class="Settings png"><a href="#">User Configuration</a></div>
            <div id= "welcome" class="info png">Welcome：<font color="red">${loginUser.loginName}</font> <td></td><td></td>Login:${loginUser.loginTime?string("yyyy年MM月dd日")}</div>
        </div>
    </div>
</div>
</body>
</html>
</#compress>