package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryAccessoryDao;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryAccessory;
/**
 * 询价单原材料DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryAccessoryDaoImpl extends DaoImpl implements AccessoryEnquiryAccessoryDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4235543810733644011L;
	//查询
	@Override
	public List<AccessoryEnquiryAccessory> listAccessoryEnquiryAccessory(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryEnquiryAccessoryDao.class, "searchAccessoryEnquiryAccessory", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryAccessory(Map<String, Object> map){
		this.insert(AccessoryEnquiryAccessoryDao.class, "saveAccessoryEnquiryAccessory", map);
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryAccessory(Map<String, Object> map){
		this.delete(AccessoryEnquiryAccessoryDao.class, "deleteAccessoryEnquiryAccessory", map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryAccessory(Map<String, Object> map){
		this.update(AccessoryEnquiryAccessoryDao.class, "updateAccessoryEnquiryAccessory", map);
	}
	//装载一个询价单原材料
	@Override
	public AccessoryEnquiryAccessory loadAccessoryEnquiryAccessory(Map<String, Object> map) {
		return (AccessoryEnquiryAccessory)this.get(AccessoryEnquiryAccessoryDao.class, "searchAccessoryEnquiryAccessory", map);
	}
}