package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 * 整体销售环比Dao接口
 * @author Joyce.li
 *  @since 2015年7月15日 上午10:18:07
 *  @version 1.0
 */
public interface MarketLinkRelativeDao extends Dao {

	/**
	 * 查询整体销售环比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellLinkRelativeAnalysis> queryMarketSellLinkRelative(Map<String, Object> map, PageInfo pageInfo);

}