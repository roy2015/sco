package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.LinkRelative;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 * 明细类销售环比Dao接口
 * 
 * @author Joyce.li
 * @since 2015年7月15日 上午10:07:00
 * @version 1.0
 */
public interface DetailLinkRelativeDao extends Dao {

	/**
	 * 查询明细类信息
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<LinkRelative> listDetailTypeInfo(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询单品销售环比
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<SellLinkRelativeAnalysis> queryDetailSellLinkRelative(Map<String, Object> map, PageInfo pageInfo);

}