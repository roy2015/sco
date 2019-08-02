<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>来伊份SCO成本分析系统</title>
<link href="${contextPath}css/style.css" rel="stylesheet" type="text/css" />
<script src="${contextPath}js/jquery.js" type="text/javascript"></script>
<script src="${contextPath}js/login.js" type="text/javascript"></script>
<script src="${contextPath}js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/jquery.graytip.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        $("#username").graytip();
        $("#password").graytip("密码");
        $("#checkcode").graytip("验证码");
    	if (top.location != self.location){top.location=self.location;}
    });
    
</script>
</head>
<body class="setting">
    
	<div class="logo"><img src="images/logo.png"/></div>
	<div class="main">
		<div class="from_bd">
		<h3 class="orange">来伊份SCO成本分析系统<!-- 错误提示小气泡 -->
			<div id="wrong" class="littlepop" style="display:none">&nbsp;用户名或密码错误</div>
		</h3>
    
    	<form action="index.html" method="post" name="myform">
    		<ul class="form_st">
       		<li><input name="username" type="text"  class="user guang" id="username"  /></li>
    		<li><input name="password" type="password"  class="password guang" id="password" value="123456"/></li>
    		<li><input name="checkcode" type="text"  class="yzm guang" id="checkcode" value="AAAA"/>&nbsp;&nbsp;&nbsp;
    		<a id='changeImgHref' href="#"  onfocus="this.blur()">
    		<img id="imgObj"  src = "security/ValidateCodeServlet"/></a></li>
         	<li class="cooke"><label><input id="remUser" name="remUser" type="checkbox" value="1" class="checkbox" checked = true/><span>${action.getText("common.button.rememberUserName")}</span></label></li>
       	 	<li><input id="loginBtn" name="login" type="button" class="login" /></li>
    	 </form>
		</div>
	</div>
	<div class="foot"><div class="copy">${action.getText("pub.title.propertyInCopyright")}</div>
</body>
</html>
