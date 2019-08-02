package com.powere2e.sco.service.impl.remind;

import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.remind.AccessoryWarnDao;
import com.powere2e.sco.interfaces.service.remind.AccessoryWarnService;

/**
 * 辅料商品预警记录ServiceImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月30日
 */
public class AccessoryWarnServiceImpl extends ServiceImpl implements AccessoryWarnService {

	private static final long serialVersionUID = -5306474691924344953L;
	private AccessoryWarnDao accessoryWarnDao;

	public AccessoryWarnDao getAccessoryWarnDao() {
		return accessoryWarnDao;
	}

	public void setAccessoryWarnDao(AccessoryWarnDao accessoryWarnDao) {
		this.accessoryWarnDao = accessoryWarnDao;
	}

	@Override
	public void insertAccessoryWarn() {
		try {
			LoggerUtil.logger.info("辅料预警记录定时执行插入数据!");
			this.accessoryWarnDao.insertAccessoryWarn();
		} catch (Exception e) {
			LoggerUtil.logger.error("辅料预警记录,插入数据出现异常!异常信息:"+e);
		}
	}

}
