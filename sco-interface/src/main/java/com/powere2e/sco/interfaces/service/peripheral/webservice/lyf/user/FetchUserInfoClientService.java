package com.powere2e.sco.interfaces.service.peripheral.webservice.lyf.user;

import com.powere2e.frame.commons.service.Service;

/**
 * 来伊份用户数据抓取
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月30日
 * @version 1.0
 */
public interface FetchUserInfoClientService extends Service {

	/**
	 * 抓取来伊份用户数据并同步到SCO系统中(增量导入)
	 */
	public void completeFetchUser();

}
