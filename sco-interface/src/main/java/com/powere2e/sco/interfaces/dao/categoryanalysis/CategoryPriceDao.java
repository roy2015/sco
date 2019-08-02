package com.powere2e.sco.interfaces.dao.categoryanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.categoryanalysis.CategoryPrice;

/**
 * 商品价格带 公共DAO接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月28日
 */
public interface CategoryPriceDao extends Dao {

	/**
	 * 显示汇总数据
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 汇总数据list
	 */
	public List<CategoryPrice> listCollectData(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 显示明细数据
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 明细数据list
	 */
	public List<CategoryPrice> listDetailData(Map<String, Object> map,
			PageInfo pageInfo);
	
	/**
	 * 查询区域内每个商品不同阶段的PSD销量
	 * 
	 * @param map
	 *            查询条件
	 * @return 每个商品不同阶段的PSD销量
	 */
	public List<CategoryPrice> listSoldQty(Map<String, Object> map);
	
	/**
	 * 查询区域内每个商品不同阶段的PSD销售额
	 * 
	 * @param map
	 *            查询条件
	 * @return 每个商品不同阶段的PSD销售额
	 */
	public List<CategoryPrice> listSoldPrice(Map<String, Object> map);

	/**
	 * 查询区域内每个商品不同阶段的PSD毛利额
	 * 
	 * @param map
	 *            查询条件
	 * @return 每个商品不同阶段的PSD毛利额
	 */
	public List<CategoryPrice> listGrossFitQty(Map<String, Object> map);

	/**
	 * 查询自动计算价格带所需数据
	 * 
	 * @param map
	 *            查询条件
	 * 
	 * @return 数据('|'隔开)
	 */
	public String listPriceRegionDate(Map<String, Object> map);

	/**
	 * dataGrid上面的数据统计
	 * 
	 * @param map
	 *            查询条件
	 * @return SKU最多的价格带 | 销售量最大价格带 | 销售价最大的价格带 | 毛利额最大的价格带
	 */
	public List<String> listCalculateTotal(Map<String, Object> map);

}