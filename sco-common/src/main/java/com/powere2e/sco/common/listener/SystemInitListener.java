package com.powere2e.sco.common.listener;

import com.powere2e.frame.commons.SystemController;
import com.powere2e.frame.web.InitListener;

/**
 * 系统初始化参数
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月17日
 * @version 1.0
 */
public class SystemInitListener implements InitListener {

	private static final long serialVersionUID = -2879731800131259310L;

	@Override
	public void execute() {
		// 设置系统ID的获取方式 IdFactory.Id = true 使用IdFactory获取否则使用Sequence获取
		SystemController.getInstance().put("IdFactory.Id", true);
		// 去掉验证码
		SystemController.getInstance().put("security.removeCheckCode", true);
		// 是否去掉用户和角色数据权限的控制
//		SystemController.getInstance().put("security.controlAllUserAndRole",
//				false);
	}

	@Override
	public String getName() {
		return "SCO";
	}

}
