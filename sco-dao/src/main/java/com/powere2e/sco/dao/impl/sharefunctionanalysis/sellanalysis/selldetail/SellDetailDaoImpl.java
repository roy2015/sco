package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.selldetail;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selldetail.SellDetailDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selldetail.SellDetail;

/**
 * 销售明细DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class SellDetailDaoImpl extends DaoImpl implements SellDetailDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211178178055872194L;

	// 查询
	@Override
	public List<SellDetail> listSellDetail(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SellDetailDao.class, "searchSellDetail", map, pageInfo);
	}

	// 查询汇总
	@Override
	public List<SellDetail> listSellDetails(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SellDetailDao.class, "searchSellDetails", map, pageInfo);
	}

	// 装载一个销售明细
	@Override
	public SellDetail loadSellDetail(Map<String, Object> map) {
		return (SellDetail) this.get(SellDetailDao.class, "searchSellDetail", map);
	}

	@Override
	public List<SellDetail> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(SellDetailDao.class, "searchMerchandise", map, pageInfo);
	}
}