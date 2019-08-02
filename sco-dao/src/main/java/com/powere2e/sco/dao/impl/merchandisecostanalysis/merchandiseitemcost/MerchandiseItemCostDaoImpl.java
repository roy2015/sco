package com.powere2e.sco.dao.impl.merchandisecostanalysis.merchandiseitemcost;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.merchandiseitemcost.MerchandiseItemCostDao;
import com.powere2e.sco.model.merchandisecostanalysis.merchandiseitemcost.Merchandiseitemcost;

/**
 * 商品分项成本类比
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月23日
 */
public class MerchandiseItemCostDaoImpl extends DaoImpl implements MerchandiseItemCostDao {

	private static final long serialVersionUID = -1208576451267454732L;

	@Override
	public List<Merchandiseitemcost> searchMechandiseItemCost(Map<String, Object> map,PageInfo pageInfo) {
		return this.query(MerchandiseItemCostDao.class, "searchMechandiseItemCost", map, pageInfo);
	}


}
