package com.powere2e.sco.interfaces.service.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust.MerchandiseLongBuyPriceAdjust;
import com.powere2e.security.model.Option;

/**
 * 商品促销分析Service接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface MerchandiseLongBuyPriceAdjustService extends Service {
	/**
	 * 商品查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回销售明细列表
	 */
	public List<MerchandiseLongBuyPriceAdjust> listMerchandise(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 导出促销明细分析execl
	 * 
	 * @param map
	 * @param out
	 * @throws Exception
	 */
	public void exportMerchandiseLongBuyPriceAdjust(Map<String, Object> map, ServletOutputStream out) throws Exception;

	/**
	 * 保存促销明细分析
	 * 
	 * @param fileName
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public String saveMerchandiseLongBuyPriceAdjust(String fileName, Map<String, Object> paraMap) throws Exception;

	/**
	 * 促销分析明细查询
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<MerchandiseLongBuyPriceAdjust> getMerchandiseLongBuyPriceAdjustDetailInfo(Map<String, Object> map, PageInfo pageInfo) throws Exception;
	/**
	 * 促销分析汇总查询
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public List<MerchandiseLongBuyPriceAdjust> getMerchandiseLongBuyPriceAdjustSum(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 查看库存地点
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listWarehouseSite(String regionCode);
}