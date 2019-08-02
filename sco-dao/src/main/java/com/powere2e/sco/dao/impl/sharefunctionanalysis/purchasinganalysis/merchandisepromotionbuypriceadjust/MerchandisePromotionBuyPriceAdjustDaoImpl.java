package com.powere2e.sco.dao.impl.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust.MerchandisePromotionBuyPriceAdjustDao;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust.MerchandisePromotionBuyPriceAdjust;
import com.powere2e.security.model.Option;

/**
 * 商品进货明细DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MerchandisePromotionBuyPriceAdjustDaoImpl extends DaoImpl implements MerchandisePromotionBuyPriceAdjustDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211178178055872194L;

	/**
	 * 查看调整价格时间和价格
	 */
	@Override
	public List<MerchandisePromotionBuyPriceAdjust> listPromotionPriceOrDate(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandisePromotionBuyPriceAdjustDao.class, "searchPromotionPriceOrDate", map, pageInfo);
	}
	/**
	 * 查看商品
	 */
	@Override
	public List<MerchandisePromotionBuyPriceAdjust> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandisePromotionBuyPriceAdjustDao.class, "searchMerchandise", map, pageInfo);
	}

	/**
	 * 查看库存地点
	 */
	@Override
	public List<Option> listRegion(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.query(MerchandisePromotionBuyPriceAdjustDao.class, "searchRegion", map, null);
	}
	@Override
	public List<MerchandisePromotionBuyPriceAdjust> listMerchandisePromotionBuyPriceAdjust(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MerchandisePromotionBuyPriceAdjustDao.class, "searchMerchandise", map, pageInfo);
	}
}