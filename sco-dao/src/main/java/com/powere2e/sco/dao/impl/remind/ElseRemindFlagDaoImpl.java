package com.powere2e.sco.dao.impl.remind;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.remind.ElseRemindFlagDao;
import com.powere2e.sco.model.remind.ElseRemindFlag;

/**
 * 其他提醒-已阅清除DaoImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class ElseRemindFlagDaoImpl extends DaoImpl implements ElseRemindFlagDao {

	private static final long serialVersionUID = -483031992489333891L;

	@Override
	public List<ElseRemindFlag> listElseRemindFlag(Map<String, Object> map ,PageInfo pageInfo) {
		return this.query(ElseRemindFlagDao.class, "listElseRemindFlag", map, pageInfo);
	}
	
	@Override
	public void insertElseRemindFlag(Map<String, Object> map) {
		this.insert(ElseRemindFlagDao.class, "insertElseRemindFlag", map);
	}

}
