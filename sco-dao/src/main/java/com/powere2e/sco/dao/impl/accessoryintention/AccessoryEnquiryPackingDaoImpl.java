package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryPackingDao;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryPacking;
/**
 * 询价单原材料DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryPackingDaoImpl extends DaoImpl implements AccessoryEnquiryPackingDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7935014912840819056L;
	//查询
	@Override
	public List<AccessoryEnquiryPacking> listAccessoryEnquiryPacking(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryEnquiryPackingDao.class, "searchAccessoryEnquiryPacking", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryPacking(Map<String, Object> map){
		this.insert(AccessoryEnquiryPackingDao.class, "saveAccessoryEnquiryPacking", map);
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryPacking(Map<String, Object> map){
		this.delete(AccessoryEnquiryPackingDao.class, "deleteAccessoryEnquiryPacking", map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryPacking(Map<String, Object> map){
		this.update(AccessoryEnquiryPackingDao.class, "updateAccessoryEnquiryPacking", map);
	}
	//装载一个询价单原材料
	@Override
	public AccessoryEnquiryPacking loadAccessoryEnquiryPacking(Map<String, Object> map) {
		return (AccessoryEnquiryPacking)this.get(AccessoryEnquiryPackingDao.class, "searchAccessoryEnquiryPacking", map);
	}
}