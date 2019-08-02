package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryDao;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiry;
/**
 * 询价单DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryDaoImpl extends DaoImpl implements AccessoryEnquiryDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7889577535516302415L;
	//查询
	@Override
	public List<AccessoryEnquiry> listAccessoryEnquiry(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryEnquiryDao.class, "searchAccessoryEnquiry", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiry(Map<String, Object> map){
		this.insert(AccessoryEnquiryDao.class, "saveAccessoryEnquiry", map);
	}
	//删除
	@Override
	public void deleteAccessoryEnquiry(Map<String, Object> map){
		this.delete(AccessoryEnquiryDao.class, "deleteAccessoryEnquiry", map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiry(Map<String, Object> map){
		this.update(AccessoryEnquiryDao.class, "updateAccessoryEnquiry", map);
	}
	//装载一个询价单
	@Override
	public AccessoryEnquiry loadAccessoryEnquiry(Map<String, Object> map) {
		return (AccessoryEnquiry)this.get(AccessoryEnquiryDao.class, "searchAccessoryEnquiry", map);
	}
}