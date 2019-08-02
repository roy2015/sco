package com.powere2e.sco.action.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.remind.ElseRemindFlagService;
import com.powere2e.sco.interfaces.service.remind.ElseRemindService;
import com.powere2e.sco.interfaces.service.remind.GtasksRemindService;
import com.powere2e.sco.model.remind.ElseRemind;
import com.powere2e.sco.model.remind.ElseRemindFlag;
import com.powere2e.sco.model.remind.GtasksRemind;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 首页
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class HomeAction extends WorkAction {

	private static final long serialVersionUID = -4887942589292816407L;
	private GtasksRemindService gtasksRemindService;
	private ElseRemindService elseRemindService;
	private ElseRemindFlagService elseRemindFlagService;
	
	@Override
	protected void beforeBuild() {
		gtasksRemindService = (GtasksRemindService) ConfigFactory.getInstance().getBean("gtasksRemindService");
		elseRemindService = (ElseRemindService) ConfigFactory.getInstance().getBean("elseRemindService");
		elseRemindFlagService = (ElseRemindFlagService) ConfigFactory.getInstance().getBean("elseRemindFlagService");
	}
	
	/**
	 * 跳转到首页
	 * @throws Exception
	 */
	@Authority(privilege="com.powere2e.sco")
	public void doShowHome() throws Exception {
		this.forwardPage("sco/home/home.ftl");
	}
	
	/**
	 * 查询待办事项提醒
	 * 
	 * @throws Exception
	 *             可能抛出的异常
	 */
	@Authority(privilege="com.powere2e.sco")
	public void doListGtasksRemind() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<GtasksRemind> list = this.gtasksRemindService.listGtasksRemind(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 查询其他提醒
	 * 
	 * @throws Exception
	 *             可能抛出的异常
	 */
	@Authority(privilege="com.powere2e.sco")
	public void doListElseRemind() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("remindType", this.asString("remindType"));
		List<ElseRemind> list = this.elseRemindService.listElseRemind(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 添加已阅清除
	 * 
	 * @throws Exception
	 *             可能抛出的异常
	 */
	@Authority(privilege="com.powere2e.sco")
	public void doAddElseRemindFlag() throws Exception {
		String remindCodes = this.asString("reminds");
		List<ElseRemindFlag> list = new ArrayList<>();
		ElseRemindFlag flag = null;
		JSONArray array = JSONArray.fromObject(remindCodes);
		User user = PowerUtils.getCurrentUser();
		for (int i = 0; i < array.size(); i++) {
			flag = new ElseRemindFlag();
			flag.setRemindCode(array.getJSONObject(i).getString("remindCode"));
			flag.setFlagPerson(user.getUserId());
			flag.setRemindType(array.getJSONObject(i).getString("remindType"));
			flag.setQlCode(array.getJSONObject(i).getString("qlCode"));
			flag.setConfigCode(array.getJSONObject(i).getString("configCode"));
			list.add(flag);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		this.elseRemindFlagService.insertElseRemindFlag(map);
	}
}
