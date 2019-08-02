package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust.MerchandiseLongBuyPriceAdjust;
import com.powere2e.security.model.Option;

/**
 * 商品进货明细DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface MerchandiseLongBuyPriceAdjustDao extends Dao {
	/**
	 * 查询商品
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandiseLongBuyPriceAdjust> listMerchandise(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 查询汇总商品信息
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandiseLongBuyPriceAdjust> listMerchandiseSum(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 查看库存地点
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listWarehouseSite(Map<String, Object> map);

	/**
	 * 查看调整价格的时间和单价
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandiseLongBuyPriceAdjust> listPriceOrDate(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查看调整价格时间段的进货数据
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandiseLongBuyPriceAdjust> listReceiptInfo(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 查看某一个的调整价格的时间和单价
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandiseLongBuyPriceAdjust> listPriceOrDates(Map<String, Object> map, PageInfo pageInfo);
}