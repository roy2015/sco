package com.powere2e.sco.service.impl.remind;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.remind.GtasksRemindDao;
import com.powere2e.sco.interfaces.service.remind.GtasksRemindService;
import com.powere2e.sco.model.remind.GtasksRemind;

/**
 * 待办事项提醒ServiceImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class GtasksRemindServiceImpl extends ServiceImpl implements GtasksRemindService {

	private static final long serialVersionUID = 3208889211102587887L;
	private GtasksRemindDao gtasksRemindDao;
	
	public GtasksRemindDao getGtasksRemindDao() {
		return gtasksRemindDao;
	}

	public void setGtasksRemindDao(GtasksRemindDao gtasksRemindDao) {
		this.gtasksRemindDao = gtasksRemindDao;
	}

	@Override
	public List<GtasksRemind> listGtasksRemind(Map<String, Object> map, PageInfo pageInfo) {
		return this.gtasksRemindDao.listGtasksRemind(map, pageInfo);
	}
	
	@Override
	public void insertGtasksRemind(){
		try {
			this.gtasksRemindDao.deleteGtasksRemind();
			this.gtasksRemindDao.insertGtasksRemindMerchandiseInfo();
			this.gtasksRemindDao.insertGtasksRemindMerchandiseFastadjustpriceInfo();
			this.gtasksRemindDao.insertGtasksRemindAccessoryInfo();
			this.gtasksRemindDao.insertGtasksRemindDHInfo();
			this.gtasksRemindDao.insertIsNotWebSiteInfo();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
