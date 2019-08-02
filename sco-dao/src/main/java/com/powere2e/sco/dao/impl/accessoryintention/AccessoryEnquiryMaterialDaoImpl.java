package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryMaterialDao;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryMaterial;
/**
 * 询价单原材料DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryMaterialDaoImpl extends DaoImpl implements AccessoryEnquiryMaterialDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8375430460856090889L;
	//查询
	@Override
	public List<AccessoryEnquiryMaterial> listAccessoryEnquiryMaterial(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryEnquiryMaterialDao.class, "searchAccessoryEnquiryMaterial", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryMaterial(Map<String, Object> map){
		this.insert(AccessoryEnquiryMaterialDao.class, "saveAccessoryEnquiryMaterial", map);
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryMaterial(Map<String, Object> map){
		this.delete(AccessoryEnquiryMaterialDao.class, "deleteAccessoryEnquiryMaterial", map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryMaterial(Map<String, Object> map){
		this.update(AccessoryEnquiryMaterialDao.class, "updateAccessoryEnquiryMaterial", map);
	}
	//装载一个询价单原材料
	@Override
	public AccessoryEnquiryMaterial loadAccessoryEnquiryMaterial(Map<String, Object> map) {
		return (AccessoryEnquiryMaterial)this.get(AccessoryEnquiryMaterialDao.class, "searchAccessoryEnquiryMaterial", map);
	}
}