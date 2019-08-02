package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail.MerchandiseStockDetail;
import com.powere2e.security.model.Option;

/**
 * 商品进货明细DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface MerchandiseStockDetailDao extends Dao {
	/**
	 * 查询商品
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandiseStockDetail> listMerchandise(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 促销分析明细
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandiseStockDetail> listMerchandiseStockDetailDetailInfo(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查看库存地点名称
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listRegion(Map<String, Object> map);

	/**
	 * 查看仓位
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listWarehouse();

	/**
	 * 查看库存地点
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listWarehouseSite();
}