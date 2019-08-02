package com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.marketsurveydata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.marketsurveydata.MarketSurveyData;

/**
 * 竞品价格市调数据 Dao接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月30日
 * @version 1.0
 */
public interface MarketSurveyDataDao extends Dao {

	/**
	 * 竞品价格市调数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 竞品价格市调数据列表
	 */
	public List<MarketSurveyData> listMarketSurveyData(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 删除竞品价格市调数据
	 * 
	 * @param map
	 *            商品ID
	 */
	public void deleteMarketSurveyData(Map<String, Object> map);

	/**
	 * 新增竞品价格市调数据
	 * 
	 * @param map
	 *            数据实例
	 */
	public void insertMarketSurverData(Map<String, Object> map);

}