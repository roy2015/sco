package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYear;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;

/**
 * 销售同比dao接口的实现
 * 
 * @author Joyce.li
 * @since 2015年6月8日 下午7:06:59
 * @version 1.0
 */
public class SellYearOnYearDaoImpl extends DaoImpl implements SellYearOnYearDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211178178055872194L;

	// 查询销售同比列表
	@Override
	public List<SellYearOnYear> listSellYearOnYear(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SellYearOnYearDao.class, "listSellYearOnYear", map, pageInfo);
	}

	// 查询单品信息
	@Override
	public List<SellYearOnYear> queryProductInfo(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SellYearOnYearDao.class, "queryProductInfo", map, pageInfo);
	}

	// 单品销售同比
	@Override
	public List<SellYearOnYearAnalysis> queryProductSellYearOnYear(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SellYearOnYearDao.class, "queryProductSellYearOnYear", map, pageInfo);
	}

	// 商品销售同比月同步
	@Override
	public void syncMerchandiseYoyAnalysis(Map<String, Object> map) throws Exception {
		this.insert(SellYearOnYearDao.class, "syncMerchandiseYoyAnalysis", map);
	}

	// 查询单品销售同比总计
	@Override
	public List<SellYearOnYearAnalysis> queryProductSellSum(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SellYearOnYearDao.class, "queryProductSellSum", map, pageInfo);
	}

}