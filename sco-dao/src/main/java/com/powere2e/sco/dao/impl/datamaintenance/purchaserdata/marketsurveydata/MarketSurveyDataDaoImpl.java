package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.marketsurveydata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.marketsurveydata.MarketSurveyDataDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.marketsurveydata.MarketSurveyData;

/**
 * 竞品价格数据 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年3月30日
 */
public class MarketSurveyDataDaoImpl extends DaoImpl implements
		MarketSurveyDataDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6636562128715255664L;

	@Override
	public List<MarketSurveyData> listMarketSurveyData(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(MarketSurveyDataDao.class, "listMarketSurveyData", map,
				pageInfo);
	}

	@Override
	public void deleteMarketSurveyData(Map<String, Object> map) {
		this.delete(MarketSurveyDataDao.class, "deleteMarketSurveyData", map);
	}

	@Override
	public void insertMarketSurverData(Map<String, Object> map) {
		this.insert(MarketSurveyDataDao.class, "insertMarketSurverData", map);
	}

}