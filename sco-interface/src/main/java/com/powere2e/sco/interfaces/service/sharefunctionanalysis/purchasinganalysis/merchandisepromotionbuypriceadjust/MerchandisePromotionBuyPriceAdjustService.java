package com.powere2e.sco.interfaces.service.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust.MerchandisePromotionBuyPriceAdjust;
import com.powere2e.security.model.Option;

/**
 * 商品促销分析Service接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface MerchandisePromotionBuyPriceAdjustService extends Service {
	/**
	 * 商品查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回销售明细列表
	 */
	public List<MerchandisePromotionBuyPriceAdjust> listMerchandise(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 查询促销进价调整
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandisePromotionBuyPriceAdjust> getMerchandisePromotionBuyPriceAdjust(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 导出促销进价分析execl
	 * 
	 * @param map
	 * @param out
	 * @throws Exception
	 */
	public void exportMerchandisePromotionBuyPriceAdjust(Map<String, Object> map, ServletOutputStream out) throws Exception;

	/**
	 * 保存促销进价分析
	 * 
	 * @param fileName
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public String saveMerchandisePromotionBuyPriceAdjust(String fileName, Map<String, Object> paraMap) throws Exception;

	/**
	 * 查看库存地点
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listRegion(String regionCode);
}