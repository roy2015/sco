package com.powere2e.sco.interfaces.service.remind;

import java.util.Map;
import com.powere2e.frame.commons.service.Service;

/**
 * 其他提醒-已阅清除Service接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public interface ElseRemindFlagService extends Service {

	/**
	 * 添加其他提醒-已阅清除
	 * 
	 * @param map 参数
	 */
	public void insertElseRemindFlag(Map<String, Object> map);
}
