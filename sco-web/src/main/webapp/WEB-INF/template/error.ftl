<#compress>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>错误提示</title>
<script src="${contextPath}/js/jquery.js" type="text/javascript"></script>
<script src="${contextPath}/js/jquery.json-2.3.min.js" type="text/javascript"></script>
<script type="text/javascript">


$(document).ready( function(){
	
});
</script>
</head>

<body>
	<div style="font-size:15px; color:#F00;text-align:center">
		<span><img src="images/warning.png" width="24" height="24" /></span>&nbsp;&nbsp;
		<#if errorMessage != null>
           ${errorMessage}
	    <#else>
           ${APP_ERROR_MESSAGE}
	    </#if>
	</div>
</body>

</html>
</#compress>