package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;

/**
 * 整体销售同比Dao接口
 * 
 * @author Joyce.li
 * @since 2015年6月8日 下午5:45:29
 * @version 1.0
 */
public interface MarketSellYearOnYearDao extends Dao {

	/**
	 * 查询整体销售同比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYearAnalysis> queryMarketSellYearOnYear(Map<String, Object> map, PageInfo pageInfo);
	
	/**
	 * 查询整体销售同比总计
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYearAnalysis> queryMarketSellSum(Map<String, Object> map, PageInfo pageInfo);


}