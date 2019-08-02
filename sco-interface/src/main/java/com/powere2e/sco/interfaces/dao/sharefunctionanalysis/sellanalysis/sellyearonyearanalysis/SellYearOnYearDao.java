package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYear;

/**
 * 销售同比Dao接口
 * 
 * @author Joyce.li
 * @since 2015年5月28日 下午3:43:05
 * @version 1.0
 */
public interface SellYearOnYearDao extends Dao {

	/**
	 * 查询销售同比列表
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYear> listSellYearOnYear(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询单品信息
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYear> queryProductInfo(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询单品销售同比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYearAnalysis> queryProductSellYearOnYear(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 商品销售同比月同步(每个月第一天凌晨同步上一个月的数据,从merchandise_sell_direct_day日直营表汇总上个月信息)
	 * 
	 * @throws Exception
	 */
	public void syncMerchandiseYoyAnalysis(Map<String, Object> map) throws Exception;

	/**
	 * 查询单品销售同比总计
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellYearOnYearAnalysis> queryProductSellSum(Map<String, Object> map, PageInfo pageInfo);

}