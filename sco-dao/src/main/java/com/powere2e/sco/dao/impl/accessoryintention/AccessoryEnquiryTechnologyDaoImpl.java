package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryTechnologyDao;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryTechnology;
/**
 * 询价单原材料DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryTechnologyDaoImpl extends DaoImpl implements AccessoryEnquiryTechnologyDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8246976494751453407L;
	//查询
	@Override
	public List<AccessoryEnquiryTechnology> listAccessoryEnquiryTechnology(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryEnquiryTechnologyDao.class, "searchAccessoryEnquiryTechnology", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryTechnology(Map<String, Object> map){
		this.insert(AccessoryEnquiryTechnologyDao.class, "saveAccessoryEnquiryTechnology", map);
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryTechnology(Map<String, Object> map){
		this.delete(AccessoryEnquiryTechnologyDao.class, "deleteAccessoryEnquiryTechnology", map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryTechnology(Map<String, Object> map){
		this.update(AccessoryEnquiryTechnologyDao.class, "updateAccessoryEnquiryTechnology", map);
	}
	//装载一个询价单原材料
	@Override
	public AccessoryEnquiryTechnology loadAccessoryEnquiryTechnology(Map<String, Object> map) {
		return (AccessoryEnquiryTechnology)this.get(AccessoryEnquiryTechnologyDao.class, "searchAccessoryEnquiryTechnology", map);
	}
}