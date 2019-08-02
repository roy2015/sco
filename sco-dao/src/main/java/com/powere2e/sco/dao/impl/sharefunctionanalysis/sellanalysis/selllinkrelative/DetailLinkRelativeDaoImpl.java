package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selllinkrelative.DetailLinkRelativeDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.LinkRelative;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;

/**
 * 明细类销售环比Dao实现
 * 
 * @author Joyce.li
 * @since 2015年7月15日 上午10:07:00
 * @version 1.0
 */
public class DetailLinkRelativeDaoImpl extends DaoImpl implements DetailLinkRelativeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9053075256034105479L;

	// 查询明细类信息
	public List<LinkRelative> listDetailTypeInfo(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(DetailLinkRelativeDao.class, "listDetailTypeInfo", map, pageInfo);
	}

	// 查询单品销售环比
	public List<SellLinkRelativeAnalysis> queryDetailSellLinkRelative(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(DetailLinkRelativeDao.class, "queryDetailSellLinkRelative", map, pageInfo);
	}

}