package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selllinkrelative.MarketLinkRelativeDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 * 整体销售环比Dao实现
 * 
 * @author Joyce.li
 * @since 2015年7月15日 上午10:18:07
 * @version 1.0
 */
public class MarketLinkRelativeDaoImpl extends DaoImpl implements MarketLinkRelativeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2039767275696916937L;

	// 查询整体销售环比
	public List<SellLinkRelativeAnalysis> queryMarketSellLinkRelative(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MarketLinkRelativeDao.class, "queryMarketSellLinkRelative", map, pageInfo);
	}

}