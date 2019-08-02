<#compress>
<script type="text/javascript" charset="UTF-8">
	<#include "layout/north.js" />
</script>
<link rel="stylesheet" type="text/css" href="${contextPath}css/scoHeader.css" />
<style>
#layout_north_zxMenu .menu-sep{display:none}
a:hover.l-btn-plain {
  background:none;
  color: #000000;
  border:none;
  margin:1px;
  -moz-border-radius: none;
  -webkit-border-radius: none;
  border-radius: none;
}
a.m-btn-plain-active {
  border:none;
  margin:1px;
  background:none;
  color: #000000;
}
.m-btn-downarrow{  
background:none;
}
</style>
<div class="ymx-head">
  <div class="ymx-logo02"></div>
  <div class="ymx-headright">
  <div class="ymx-span">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-hy"></i> 欢迎：<small>${login_user.realName}</small>  工号 <span>${login_user.userID}</span> 登录SCO成本分析系统</a></div>
    <div class="ymx-headtop">
    	
    <!--<span><a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_skinMenu'"><i class="icon-pf"></i>更换皮肤</a></span>-->
    <span class="bt-bj">&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu'">系统设置</a></span>
    <span class="bt-bj">&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu'">退出系统</a></span>
    </div>
  </div>
</div>





<!--
<div class="header_new">
	<div class="logo_2"><img src="images/logo_2.png" /></div>
	<div class="st_bj"></div>
</div>
<div id="sessionInfoDiv" class='user' style="position: absolute;left: 5px;bottom: -3px;">
	${action.getText("pub.title.welcome")}<font color="#fca93b">${login_user.realName}</font> 
	<#if ('${agentName}'!=''&&'${agentName}'!=null)>
		${action.getText("pub.title.agentNow")}<font color="#fca93b">${agentName}</font>${action.getText("pub.title.operate")} 
	</#if>
	<td></td>
	${action.getText("pub.title.loginTime")}${login_user.loginTime?string("yyyy年MM月dd日")}
</div>
-->


<#--
	<div id="layout_north_skinMenu" style="width: 100px; display: none;">
		<div onclick="sy.changeTheme('default');">${action.getText("pub.skin.default")}</div>
		<div onclick="sy.changeTheme('gray');">${action.getText("pub.skin.gray")}</div>
		<div onclick="sy.changeTheme('metro');">${action.getText("pub.skin.metro")}</div>
		<div onclick="sy.changeTheme('cupertino');">${action.getText("pub.skin.cupertino")}</div>
		<div onclick="sy.changeTheme('dark-hive');">${action.getText("pub.skin.darkHive")}</div>
		<div onclick="sy.changeTheme('pepper-grinder');">${action.getText("pub.skin.pepperGrinder")}</div>
		<div onclick="sy.changeTheme('sunny');">${action.getText("pub.skin.sunny")}</div>
		<div onclick="sy.changeTheme('metro-blue');">${action.getText("pub.skin.metroBlue")}</div>
		<div onclick="sy.changeTheme('metro-green');">${action.getText("pub.skin.metroGreen")}</div>
		<div onclick="sy.changeTheme('metro-orange');">${action.getText("pub.skin.metroOrange")}</div>
		<div onclick="sy.changeTheme('metro-red');">${action.getText("pub.skin.metroRed")}</div>
		<div onclick="sy.changeTheme('metro-gray');">${action.getText("pub.skin.metroGray")}</div>
	</div>
-->
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<#-- <div onclick="northFn.showLoggedUserInfo();">${action.getText("pub.title.personInfo")}</div> -->
	<div onclick="northFn.showChangPwdForm()">${action.getText("pub.title.changePassword")}</div>
	<div class="menu-sep" style="display:<#if !action.hasFuncPower("com.escm.controlPanel.agent") && !action.hasFuncPower("com.escm.controlPanel.changeDatePower")>none</#if>;"></div>
	<div onclick="northFn.showSetAgentTarget();" style="display:<#if !action.hasFuncPower("com.escm.controlPanel.agent")>none</#if>;">${action.getText("pub.title.proxyUser")}</div>
	<#-- <div onclick="northFn.showChangeDataPower();" style="display:<#if !action.hasFuncPower("com.escm.controlPanel.changeDatePower")>none</#if>;">${action.getText("pub.title.changeDatePower")}</div> -->
	<div class="menu-sep"  style="display:<#if !action.hasFuncPower("com.escm.controlPanel.systemMassage")>none</#if>;"></div>
	<div onclick="northFn.showUpdateMassage();" style="display:<#if !action.hasFuncPower("com.escm.controlPanel.systemMassage")>none</#if>;">${action.getText("pub.title.systemMassage")}</div>
</div>

<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<!--
		<div onclick="northFn.logout();">重新登录</div>
	-->
	<div class="menu-sep"></div>
	<div onclick="northFn.logout(true);">${action.getText("pub.title.logout")}</div>
</div>
</#compress>