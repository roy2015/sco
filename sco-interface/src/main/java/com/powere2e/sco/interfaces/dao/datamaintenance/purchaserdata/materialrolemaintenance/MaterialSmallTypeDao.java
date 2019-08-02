package com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.MaterialSmallType;
/**
 * 原料小类DAO接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public interface MaterialSmallTypeDao extends Dao {
	/**
	 * 原料小类查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回原料小类列表
	 */
	public List<MaterialSmallType> listMaterialSmallType(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个原料小类
	 *
	 * @param map
	 *				
	 * @return
	 */
	public MaterialSmallType loadMaterialSmallType(Map<String,Object> map);
	/**
	 * 添加原料小类
	 *
	 * @param map
	 *				
	 */
	public void insertMaterialSmallType(Map<String, Object> map);
	/**
	 * 删除原料小类
	 *
	 * @param map 
	 *				必须参数id为要删除的原料小类id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMaterialSmallType(Map<String, Object> map);
	/**
	 * 修改原料小类
	 *
	 * @param map 
	 *				必须参数id为要修改原料小类的id号，不能为数组
	 */
	public void updateMaterialSmallType(Map<String, Object> map);
	/**
	 * 查询原料表中是否存在原料小类
	 * @param map
	 * @return
	 */
	public Integer searchMaterial(Map<String,Object> map);
}