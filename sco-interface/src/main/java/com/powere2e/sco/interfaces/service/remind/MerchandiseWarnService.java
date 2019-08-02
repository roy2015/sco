package com.powere2e.sco.interfaces.service.remind;

import com.powere2e.frame.commons.service.Service;


/**
 * 商品预警记录接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月30日
 */
public interface MerchandiseWarnService extends Service{

	/**
	 * 添加商品预警记录(定时任务 每月1日)
	 */
	public void insertMerchandiseWarn();
}
