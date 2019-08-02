package com.powere2e.sco.service.impl.remind;

import java.util.Map;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.remind.ElseRemindDao;
import com.powere2e.sco.interfaces.dao.remind.ElseRemindFlagDao;
import com.powere2e.sco.interfaces.service.remind.ElseRemindFlagService;

/**
 * 其他提醒-已阅清除ServiceImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class ElseRemindFlagServiceImpl extends ServiceImpl implements ElseRemindFlagService {

	private static final long serialVersionUID = 4621232323867920520L;
	private ElseRemindFlagDao elseRemindFlagDao;
	private ElseRemindDao elseRemindDao;
	
	public ElseRemindFlagDao getElseRemindFlagDao() {
		return elseRemindFlagDao;
	}

	public void setElseRemindFlagDao(ElseRemindFlagDao elseRemindFlagDao) {
		this.elseRemindFlagDao = elseRemindFlagDao;
	}
	
	public ElseRemindDao getElseRemindDao() {
		return elseRemindDao;
	}

	public void setElseRemindDao(ElseRemindDao elseRemindDao) {
		this.elseRemindDao = elseRemindDao;
	}

	@Override
	public void insertElseRemindFlag(Map<String, Object> map) {
		this.elseRemindFlagDao.insertElseRemindFlag(map);
	}

}
