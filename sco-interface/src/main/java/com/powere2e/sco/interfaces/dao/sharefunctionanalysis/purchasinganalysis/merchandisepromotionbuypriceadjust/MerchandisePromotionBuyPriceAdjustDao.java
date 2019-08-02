package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust.MerchandisePromotionBuyPriceAdjust;
import com.powere2e.security.model.Option;

/**
 * 商品进货明细DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface MerchandisePromotionBuyPriceAdjustDao extends Dao {
	/**
	 * 查询商品
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandisePromotionBuyPriceAdjust> listMerchandise(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查看库存地点
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listRegion(Map<String, Object> map);

	/**
	 * 查看商品促销进价调整
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandisePromotionBuyPriceAdjust> listMerchandisePromotionBuyPriceAdjust(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查看调整价格的时间和单价
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandisePromotionBuyPriceAdjust> listPromotionPriceOrDate(Map<String, Object> map, PageInfo pageInfo);

}