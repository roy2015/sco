package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.LinkRelative;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 *  单品销售同比Dao接口
 *  @author Joyce.li
 *  @since 2015年7月7日 上午10:21:28
 *  @version 1.0
 */
public interface MerchandiseLinkRelativeDao extends Dao {

	/**
	 * 查询销售环比列表
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<LinkRelative> listLinkRelative(Map<String, Object> map, PageInfo pageInfo);
	
	/**
	 * 查询单品销售环比的单品信息列表
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<LinkRelative> queryMerchandiseLinkRelative(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询单品销售同比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellLinkRelativeAnalysis> queryProductSellLinkRelative(Map<String, Object> map, PageInfo pageInfo);
	
	
}