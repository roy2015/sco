package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selllinkrelative.SmallLinkRelativeDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.LinkRelative;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 * 小分类销售环比Dao实现
 * 
 * @author Joyce.li
 * @since 2015年7月15日 上午10:18:30
 * @version 1.0
 */
public class SmallLinkRelativeDaoImpl extends DaoImpl implements SmallLinkRelativeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7265014476719217326L;

	// 查询小分类信息
	public List<LinkRelative> listSmallTypeInfo(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SmallLinkRelativeDao.class, "listSmallTypeInfo", map, pageInfo);
	}

	// 查询小分类销售环比
	public List<SellLinkRelativeAnalysis> querySmallSellLinkRelative(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SmallLinkRelativeDao.class, "querySmallSellLinkRelative", map, pageInfo);
	}

}