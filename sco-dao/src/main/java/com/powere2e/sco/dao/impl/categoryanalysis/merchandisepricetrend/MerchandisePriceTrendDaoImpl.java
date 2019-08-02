package com.powere2e.sco.dao.impl.categoryanalysis.merchandisepricetrend;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.categoryanalysis.merchandisepricetrend.MerchandisePriceTrendDao;
import com.powere2e.sco.model.categoryanalysis.merchandisepricetrend.MerchandisePriceTrend;
import com.powere2e.security.model.Option;

/**
 * 商品价格趋势DAO接口的实现
 * 
 * @author len.zhao
 * @version 1.0
 * @since 2015年5月8日
 */
public class MerchandisePriceTrendDaoImpl extends DaoImpl implements MerchandisePriceTrendDao {

	private static final long serialVersionUID = 297830519028876219L;

	@Override
	public List<MerchandisePriceTrend> listMerchandisePriceTrend(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandisePriceTrendDao.class, "listMerchandisePriceData", map, null);
	}

	@Override
	public List<MerchandisePriceTrend> listMerchandiseData(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandisePriceTrendDao.class, "listMerchandiseData", map, pageInfo);
	}

	@Override
	public List<MerchandisePriceTrend> getMerchandiseSellData(Map<String, Object> map) {
		return this.query(MerchandisePriceTrendDao.class, "merchandiseSellData", map, null);

	}
	@Override
	public List<MerchandisePriceTrend> getPriceOrDate(Map<String, Object> map) {
		return this.query(MerchandisePriceTrendDao.class, "searchPriceOrDate", map, null);

	}
	/**
	 * 查看地区
	 */
	@Override
	public List<Option> listRegion(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.query(MerchandisePriceTrendDao.class, "searchRegion", map, null);
	}
}
