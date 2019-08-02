package com.powere2e.sco.dao.impl.remind;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.remind.GtasksRemindDao;
import com.powere2e.sco.model.remind.GtasksRemind;

/**
 * 待办事项提醒DaoImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class GtasksRemindDaoImpl extends DaoImpl implements GtasksRemindDao{

	private static final long serialVersionUID = -3474857860817042538L;

	@Override
	public List<GtasksRemind> listGtasksRemind(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(GtasksRemindDao.class, "listGtasksRemind", map, pageInfo);
	}

	@Override
	public void deleteGtasksRemind() {
		this.delete(GtasksRemindDao.class, "deleteGtasksRemind", null);
	}
	
	@Override
	public void insertGtasksRemindDHInfo() {
		this.insert(GtasksRemindDao.class, "insertGtasksRemindDHInfo", null);
	}

	@Override
	public void insertGtasksRemindMerchandiseInfo() {
		this.insert(GtasksRemindDao.class, "insertGtasksRemindMerchandiseInfo", null);
	}
	
	@Override
	public void insertGtasksRemindMerchandiseFastadjustpriceInfo() {
		this.insert(GtasksRemindDao.class, "insertGtasksRemindMerchandiseFastadjustpriceInfo", null);
	}

	@Override
	public void insertGtasksRemindAccessoryInfo() {
		this.insert(GtasksRemindDao.class, "insertGtasksRemindAccessoryInfo", null);
	}

	@Override
	public void insertIsNotWebSiteInfo() {
		this.insert(GtasksRemindDao.class, "insertIsNotWebSiteInfo", null);
	}
}
