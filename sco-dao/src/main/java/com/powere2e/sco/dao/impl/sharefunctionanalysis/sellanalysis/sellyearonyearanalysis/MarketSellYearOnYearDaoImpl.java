package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.MarketSellYearOnYearDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;

/**
 * 整体销售同比dao接口的实现
 * 
 * @author Joyce.li
 * @since 2015年6月8日 下午7:09:17
 * @version 1.0
 */
public class MarketSellYearOnYearDaoImpl extends DaoImpl implements MarketSellYearOnYearDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211178178055872194L;

	// 整体销售同比
	@Override
	public List<SellYearOnYearAnalysis> queryMarketSellYearOnYear(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MarketSellYearOnYearDao.class, "queryMarketSellYearOnYear", map, pageInfo);
	}

	@Override
	public List<SellYearOnYearAnalysis> queryMarketSellSum(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MarketSellYearOnYearDao.class, "queryMarketSellSum", map, pageInfo);
	}

}