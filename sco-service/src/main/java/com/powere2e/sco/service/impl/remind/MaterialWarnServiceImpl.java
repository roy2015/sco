package com.powere2e.sco.service.impl.remind;

import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.remind.MaterialWarnDao;
import com.powere2e.sco.interfaces.service.remind.MaterialWarnService;

/**
 * 原料预警记录ServiceImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月22日
 */
public class MaterialWarnServiceImpl extends ServiceImpl implements MaterialWarnService{

	
	private static final long serialVersionUID = 8713700718737061347L;
	private MaterialWarnDao materialWarnDao;
	
	public MaterialWarnDao getMaterialWarnDao() {
		return materialWarnDao;
	}

	public void setMaterialWarnDao(MaterialWarnDao materialWarnDao) {
		this.materialWarnDao = materialWarnDao;
	}
	
	@Override
	public void insertMaterialWarn() {
		try {
			LoggerUtil.logger.info("原料预警记录定时执行插入数据!");
			this.materialWarnDao.insertMaterialWarn();
		} catch (Exception e) {
			LoggerUtil.logger.error("原料预警记录,插入数据出现异常!异常信息:"+e);
		}
	}

}
