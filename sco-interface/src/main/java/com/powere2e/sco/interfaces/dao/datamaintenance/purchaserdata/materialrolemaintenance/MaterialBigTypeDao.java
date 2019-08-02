package com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.MaterialBigType;
/**
 * 原料大类DAO接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public interface MaterialBigTypeDao extends Dao {
	/**
	 * 原料大类查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回原料大类列表
	 */
	public List<MaterialBigType> listMaterialBigType(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个原料大类
	 *
	 * @param map
	 *				
	 * @return
	 */
	public MaterialBigType loadMaterialBigType(Map<String,Object> map);
	/**
	 * 添加原料大类
	 *
	 * @param map
	 *				
	 */
	public void insertMaterialBigType(Map<String, Object> map);
	/**
	 * 删除原料大类
	 *
	 * @param map 
	 *				必须参数id为要删除的原料大类id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMaterialBigType(Map<String, Object> map);
	/**
	 * 修改原料大类
	 *
	 * @param map 
	 *				必须参数id为要修改原料大类的id号，不能为数组
	 */
	public void updateMaterialBigType(Map<String, Object> map);
	/**
	 * 查询原料小类表中是否存在原料小类
	 * @param map
	 * @return
	 */
	public Integer searchMaterialSmallType(Map<String,Object> map);
}