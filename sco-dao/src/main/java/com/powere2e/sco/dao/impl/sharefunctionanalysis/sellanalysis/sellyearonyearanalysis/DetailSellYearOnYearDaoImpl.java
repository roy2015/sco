package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.DetailSellYearOnYearDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYear;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;

/**
 * 明细类销售同比dao接口实现
 * 
 * @author Joyce.li
 * @since 2015年6月8日 下午7:06:45
 * @version 1.0
 */
public class DetailSellYearOnYearDaoImpl extends DaoImpl implements DetailSellYearOnYearDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211178178055872194L;

	// 查询明细类信息
	@Override
	public List<SellYearOnYear> listDetailTypeInfo(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(DetailSellYearOnYearDao.class, "listDetailTypeInfo", map, pageInfo);
	}

	// 明细类销售同比
	@Override
	public List<SellYearOnYearAnalysis> queryDetailSellYearOnYear(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(DetailSellYearOnYearDao.class, "queryDetailSellYearOnYear", map, pageInfo);
	}

	// 查询明细类销售同比总计
	@Override
	public List<SellYearOnYearAnalysis> queryDetailSellSum(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(DetailSellYearOnYearDao.class, "queryDetailSellSum", map, pageInfo);
	}

}