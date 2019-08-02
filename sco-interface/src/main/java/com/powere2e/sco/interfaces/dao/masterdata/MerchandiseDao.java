package com.powere2e.sco.interfaces.dao.masterdata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.masterdata.Merchandise;

/**
 * 商品数据 Dao接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月22日
 * @version 1.0
 */
public interface MerchandiseDao extends Dao {

	/**
	 * 商品数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 商品数据列表
	 */
	public List<Merchandise> listMerchandise(Map<String, Object> map, PageInfo pageInfo);
	
	/**
	 * 商品销售方式查询
	 * 
	 * @return 销售方式列表
	 */
	public List<String> listMerchandiseStorageForm();
	
	/**
	 * 查询商品的编号
	 * 
	 * @param map
	 *            查询条件
	 * @return 商品编号list
	 */
	public List<String> listMerchandiseCode(Map<String, Object> map);
	/**
	 * 根据ID号加载一个商品角色
	 *
	 * @param map
	 *				
	 * @return
	 */
	public Merchandise loadMerchandise(Map<String,Object> map);
	

}