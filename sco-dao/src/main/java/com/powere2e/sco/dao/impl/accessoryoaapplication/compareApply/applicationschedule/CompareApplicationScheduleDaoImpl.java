package com.powere2e.sco.dao.impl.accessoryoaapplication.compareApply.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.compareApply.applicationschedule.CompareApplicationScheduleDao;
import com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule.AccessoryApplicationSchedulea;

/**
 * 辅料询价单比较 进度信息 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月19日
 */
public class CompareApplicationScheduleDaoImpl extends DaoImpl implements
		CompareApplicationScheduleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5698380651379880346L;

	@Override
	public List<AccessoryApplicationSchedulea> listApplicationScheduleCompare(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(CompareApplicationScheduleDao.class, "listApplicationScheduleCompare", map, pageInfo);
	}

	@Override
	public void insertApplicationScheduleCompare(Map<String, Object> map) {
		this.insert(CompareApplicationScheduleDao.class, "insertApplicationScheduleCompare", map);
	}

}