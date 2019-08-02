package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selllinkrelative.MerchandiseLinkRelativeDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.LinkRelative;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 * 销售环比dao接口的实现
 * 
 * @author Joyce.li
 * @since 2015年7月7日 上午11:30:58
 * @version 1.0
 */
public class MerchandiseLinkRelativeDaoImpl extends DaoImpl implements MerchandiseLinkRelativeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8563267107810000418L;

	// 查询销售环比列表
	@Override
	public List<LinkRelative> listLinkRelative(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseLinkRelativeDao.class, "listLinkRelative", map, pageInfo);
	}

	// 查询单品销售环比的单品信息列表
	@Override
	public List<LinkRelative> queryMerchandiseLinkRelative(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseLinkRelativeDao.class, "queryMerchandiseLinkRelative", map, pageInfo);
	}

	// 查询单品销售环比的环比信息
	@Override
	public List<SellLinkRelativeAnalysis> queryProductSellLinkRelative(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseLinkRelativeDao.class, "queryProductSellLinkRelative", map, pageInfo);
	}

}