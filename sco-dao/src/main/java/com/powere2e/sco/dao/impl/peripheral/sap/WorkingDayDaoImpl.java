package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.WorkingDayDao;

public class WorkingDayDaoImpl extends DaoImpl implements WorkingDayDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4770993486781839761L;

	@Override
	public void insertWorkingDay(Map<String, Object> map) {
		this.insert(WorkingDayDao.class, "saveWorkingDay", map);
	}

	@Override
	public void deleteWorkingDay(Map<String, Object> map) {
		this.delete(WorkingDayDao.class, "deleteWorkingDay", map);
	}

	@Override
	public int searchWeekDayByDate(Map<String, Object> map) {
		return (int) this.get(WorkingDayDao.class, "searchWeekDayByDate",
				map);
	}
}
