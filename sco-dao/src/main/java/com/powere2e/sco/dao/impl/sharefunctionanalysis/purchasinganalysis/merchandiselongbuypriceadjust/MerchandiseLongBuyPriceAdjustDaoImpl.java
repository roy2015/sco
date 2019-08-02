package com.powere2e.sco.dao.impl.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust.MerchandiseLongBuyPriceAdjustDao;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust.MerchandiseLongBuyPriceAdjust;
import com.powere2e.security.model.Option;

/**
 * 商品进货明细DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MerchandiseLongBuyPriceAdjustDaoImpl extends DaoImpl implements MerchandiseLongBuyPriceAdjustDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211178178055872194L;

	/**
	 * 查看调整价格时间和价格
	 */
	@Override
	public List<MerchandiseLongBuyPriceAdjust> listPriceOrDate(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandiseLongBuyPriceAdjustDao.class, "searchPriceOrDate", map, pageInfo);
	}
	/**
	 * 查看调整价格时间和价格
	 */
	@Override
	public List<MerchandiseLongBuyPriceAdjust> listPriceOrDates(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandiseLongBuyPriceAdjustDao.class, "searchPriceOrDates", map, pageInfo);
	}
	/**
	 * 查看调整价格时间段的1进货数据
	 */
	@Override
	public List<MerchandiseLongBuyPriceAdjust> listReceiptInfo(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandiseLongBuyPriceAdjustDao.class, "searchReceiptInfo", map, pageInfo);
	}
	/**
	 * 查看商品
	 */
	@Override
	public List<MerchandiseLongBuyPriceAdjust> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandiseLongBuyPriceAdjustDao.class, "searchMerchandise", map, pageInfo);
	}

	/**
	 */
	@Override
	public List<Option> listWarehouseSite(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.query(MerchandiseLongBuyPriceAdjustDao.class, "searchWarehouseSite", map, null);
	}
	@Override
	public List<MerchandiseLongBuyPriceAdjust> listMerchandiseSum(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandiseLongBuyPriceAdjustDao.class, "searchMerchandiseSum", map, pageInfo);
	}
}