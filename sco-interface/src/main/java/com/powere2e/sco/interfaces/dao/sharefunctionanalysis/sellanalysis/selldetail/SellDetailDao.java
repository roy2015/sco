package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selldetail;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selldetail.SellDetail;

/**
 * 销售明细DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface SellDetailDao extends Dao {
	/**
	 * 销售明细查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回销售明细列表
	 */
	public List<SellDetail> listSellDetail(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 销售明细汇总查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回销售明细列表
	 */
	public List<SellDetail> listSellDetails(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 商品信息查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回销售明细列表
	 */
	public List<SellDetail> listMerchandise(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个销售明细
	 *
	 * @param map
	 *
	 * @return
	 */
	public SellDetail loadSellDetail(Map<String, Object> map);
}