package com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.accessoryfinetype;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.accessoryfinetype.AccessoryFineType;
/**
 * 辅料细分类维护DAO接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public interface AccessoryFineTypeDao extends Dao {
	/**
	 * 辅料细分类维护查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回辅料细分类维护列表
	 */
	public List<AccessoryFineType> listAccessoryFineType(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个辅料细分类维护
	 *
	 * @param map
	 *				
	 * @return
	 */
	public AccessoryFineType loadAccessoryFineType(Map<String,Object> map);
	/**
	 * 添加辅料细分类维护
	 *
	 * @param map
	 *				
	 */
	public void insertAccessoryFineType(Map<String, Object> map);
	/**
	 * 删除辅料细分类维护
	 *
	 * @param map 
	 *				必须参数id为要删除的辅料细分类维护id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccessoryFineType(Map<String, Object> map);
	/**
	 * 查询辅料表中是否存在细分类
	 * @param map
	 * @return
	 */
	public Integer searchAccessoryFineType(Map<String,Object> map);
}