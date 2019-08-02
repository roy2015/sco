package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SmallSellYearOnYearDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYear;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;

/**
 * 小分类销售同比dao接口实现
 * 
 * @author Joyce.li
 * @since 2015年6月8日 下午7:08:15
 * @version 1.0
 */
public class SmallSellYearOnYearDaoImpl extends DaoImpl implements SmallSellYearOnYearDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211178178055872194L;

	// 查询小分类信息
	@Override
	public List<SellYearOnYear> listSmallTypeInfo(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SmallSellYearOnYearDao.class, "listSmallTypeInfo", map, pageInfo);
	}

	// 小分类销售同比
	@Override
	public List<SellYearOnYearAnalysis> querySmallSellYearOnYear(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SmallSellYearOnYearDao.class, "querySmallSellYearOnYear", map, pageInfo);
	}

	// 查询小分类销售汇总
	@Override
	public List<SellYearOnYearAnalysis> querySmallSellSum(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SmallSellYearOnYearDao.class, "querySmallSellSum", map, pageInfo);
	}

}