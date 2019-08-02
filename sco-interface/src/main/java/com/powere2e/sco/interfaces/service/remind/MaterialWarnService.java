package com.powere2e.sco.interfaces.service.remind;

import com.powere2e.frame.commons.service.Service;

/**
 * 原料预警记录接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月22日
 */
public interface MaterialWarnService extends Service{

	/**
	 * 添加原料预警记录(定时任务 每月1日)
	 */
	public void insertMaterialWarn();
}
