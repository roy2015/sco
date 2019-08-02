package com.powere2e.sco.interfaces.dao.merchandisecostanalysis.merchandiseitemcost;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandisecostanalysis.merchandiseitemcost.Merchandiseitemcost;


/**
 * 商品分项成本类比
 * @author lipengjie
 * @version 1.0
 * @since  2015年4月23日
 */
public interface MerchandiseItemCostDao extends Dao {
	/**
	 * 查询商品分项成本类比
	 * 
	 * @param map 条件Map
	 * @param pageInfo 分页信息
	 * @return 查询的结果
	 */
	public List<Merchandiseitemcost> searchMechandiseItemCost(Map<String, Object> map,PageInfo pageInfo);

}
