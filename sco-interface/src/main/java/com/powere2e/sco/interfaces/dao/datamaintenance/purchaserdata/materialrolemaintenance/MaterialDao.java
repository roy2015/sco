package com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.Material;

/**
 * 原料DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public interface MaterialDao extends Dao {
	/**
	 * 原料查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回原料列表
	 */
	public List<Material> listMaterial(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个原料
	 *
	 * @param map 查询参数
	 *
	 * @return 原料信息
	 */
	public Material loadMaterial(Map<String, Object> map);

	/**
	 * 添加原料
	 *
	 * @param map 原料实例
	 *
	 */
	public void insertMaterial(Map<String, Object> map);

	/**
	 * 添加原料地区
	 *
	 * @param map 原料地区实例
	 *
	 */
	public void insertMaterialRegion(Map<String, Object> map);

	/**
	 * 删除原料
	 *
	 * @param map
	 *            必须参数id为要删除的原料id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMaterial(Map<String, Object> map);

	/**
	 * 修改原料
	 *
	 * @param map
	 *            必须参数id为要修改原料的id号，不能为数组
	 */
	public void updateMaterial(Map<String, Object> map);

	/**
	 * 查询当前编号
	 * 
	 * @return
	 */
	public Integer searchMaterialId();
	
	/**
	 * 添加原料地区价格
	 *
	 * @param map 原料地区实例
	 *
	 */
	public void insertMaterialPrice(Map<String, Object> map);
	
	/**
	 * 修改原料地区
	 * 
	 * @param map
	 *            原料地区实例
	 */
	public void updateMaterialRegion(Map<String, Object> map);
	
	/**
	 * 修改原料地区价格
	 * 
	 * @param map 原料价格实例
	 */
	public void updateMaterialPrice(Map<String, Object> map);
	
}