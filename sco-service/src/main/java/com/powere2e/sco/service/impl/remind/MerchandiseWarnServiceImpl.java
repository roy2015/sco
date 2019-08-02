package com.powere2e.sco.service.impl.remind;

import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.remind.MerchandiseWarnDao;
import com.powere2e.sco.interfaces.service.remind.MerchandiseWarnService;

/**
 * 商品预警记录ServiceImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月30日
 */
public class MerchandiseWarnServiceImpl extends ServiceImpl implements MerchandiseWarnService {

	private static final long serialVersionUID = 294231025631342490L;
	private MerchandiseWarnDao merchandiseWarnDao;

	public MerchandiseWarnDao getMerchandiseWarnDao() {
		return merchandiseWarnDao;
	}

	public void setMerchandiseWarnDao(MerchandiseWarnDao merchandiseWarnDao) {
		this.merchandiseWarnDao = merchandiseWarnDao;
	}

	@Override
	public void insertMerchandiseWarn() {
		try {
			LoggerUtil.logger.info("商品预警记录定时执行插入数据!");
			this.merchandiseWarnDao.insertMerchandiseWarn();
		} catch (Exception e) {
			LoggerUtil.logger.error("商品预警记录,插入数据出现异常!异常信息:"+e);
		}
	}

}
