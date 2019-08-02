package com.powere2e.sco.action.singlelogin;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.SessionManager;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.web.action.AuthorityAction;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.security.UserService;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 单点登录 web请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年10月29日
 */
public class SingleLoginAction extends AuthorityAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4262348492668828612L;
	private UserService userService;
	
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		userService = (UserService) ConfigFactory.getInstance().getBean(
				"userManager");
	}
	
	/***
	 * 通过登录名登录系统
	 */
	public void doLoginWithUserId() throws Exception {
		try {
			String userId = this.asString("ivuser");
			LoggerUtil.logger.debug("获取用户名:<br>" + userId);
			if (StringUtils.isBlank(userId) || "null".equalsIgnoreCase(userId)) {
				this.forwardPage("login.ftl");
				return;
			}
			User ifLogin = PowerUtils.getRealLoggedUser();
			if (ifLogin == null) {// 未登陆
				SessionManager.getInstance().put("app_user_clientID", "1");//clientID用于区别属于某个项目的标志，默认为1
				User user = userService.loadUser(userId);
				if (user == null) throw new EscmException("该用户尚未同步到SCO系统中!");
				PowerUtils.loginWithoutPassword(user.getLoginName());
				this.login();
			}
			this.forwardPage("index.ftl");//跳转到首页
		} catch (EscmException e) {
			e.printStackTrace();
			LoggerUtil.logger.debug("登陆时异常:<br>" + StrUtils.getStackMsgToStr(e));
			this.putObject("errorMessage", e.getMessage());
			this.forwardPage("error.ftl");
		} catch (Throwable e) {
			e.printStackTrace();
			LoggerUtil.logger.debug("登陆时异常:<br>" + StrUtils.getStackMsgToStr(e));
			this.putObject("errorMessage", "登陆到SCO系统时发生错误!");
			this.forwardPage("error.ftl");
		}
	}
}
