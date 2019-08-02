package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 快速调价物料信息 DAO接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月21日
 */
public interface WlInfoFastAdjustDao extends Dao {

	/**
	 * 物料信息列表
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 进度信息list
	 */
	public List<MerchandiseWlInfo> listWlInfoFastAdjust(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询物料读取价格信息
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 关联报表list
	 */
	public List<MerchandiseWlInfo> listWlInfoDetail(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 验证所选地区是否已被关联
	 * 
	 * @param map
	 *            查询条件
	 * @return 已关联的地区
	 */
	public List<String> ifWlInfoExists(Map<String, Object> map);
	
	/**
	 * 新增商品合同价
	 * 
	 * @param map 价格、价格地区
	 */
	public void insertMerchandiseContractPrice(Map<String, Object> map);

	/**
	 * 查询某个物料信息
	 * 
	 * @param map
	 *            查询添加
	 * @return 物料信息
	 */
	public MerchandiseWlInfo loadWlInfo(Map<String, Object> map);

	/**
	 * 更新商品合同价
	 * 
	 * @param map 价格、价格地区
	 */
	public void updateMerchandiseContractPrice(Map<String, Object> map);

	/**
	 * 删除商品合同价
	 */
	public void deleteMerchandiseContractPrice(Map<String, Object> map);

}