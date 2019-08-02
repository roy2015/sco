package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedMaterialDao;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedMaterial;
/**
 * 辅料报价-原材料信息DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedMaterialDaoImpl extends DaoImpl implements AccessoryQuotedMaterialDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3669458659382554848L;
	//查询
	@Override
	public List<AccessoryQuotedMaterial> listAccessoryQuotedMaterial(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryQuotedMaterialDao.class, "searchAccessoryQuotedMaterial", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedMaterial(Map<String, Object> map){
		this.insert(AccessoryQuotedMaterialDao.class, "saveAccessoryQuotedMaterial", map);
	}
	//删除
	@Override
	public void deleteAccessoryQuotedMaterial(Map<String, Object> map){
		this.delete(AccessoryQuotedMaterialDao.class, "deleteAccessoryQuotedMaterial", map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedMaterial(Map<String, Object> map){
		this.update(AccessoryQuotedMaterialDao.class, "updateAccessoryQuotedMaterial", map);
	}
	//装载一个辅料报价-原材料信息
	@Override
	public AccessoryQuotedMaterial loadAccessoryQuotedMaterial(Map<String, Object> map) {
		return (AccessoryQuotedMaterial)this.get(AccessoryQuotedMaterialDao.class, "searchAccessoryQuotedMaterial", map);
	}
}