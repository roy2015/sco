package com.powere2e.sco.interfaces.dao.categoryanalysis.merchandisepricetrend;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.categoryanalysis.merchandisepricetrend.MerchandisePriceTrend;
import com.powere2e.security.model.Option;

/**
 * 商品价格趋势Dao接口
 * 
 * @author len.zhao
 * @since 2015年5月8日
 * @version 1.0
 */
public interface MerchandisePriceTrendDao extends Dao {

	/**
	 * 查询商品主数据和价格数据
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 签量数据列表
	 */
	public List<MerchandisePriceTrend> listMerchandisePriceTrend(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 商品数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 签量数据列表
	 */
	public List<MerchandisePriceTrend> listMerchandiseData(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询商品销售数据
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 签量数据列表
	 */
	public List<MerchandisePriceTrend> getMerchandiseSellData(Map<String, Object> map);

	/**
	 * 查看地区
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listRegion(Map<String, Object> map);

	/**
	 * 查询下一次价格日期
	 * 
	 * @param map
	 * @return
	 */
	public List<MerchandisePriceTrend> getPriceOrDate(Map<String, Object> map);

}
