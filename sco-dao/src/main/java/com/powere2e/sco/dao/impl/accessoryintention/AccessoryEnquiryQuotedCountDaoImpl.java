package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryQuotedCountDao;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryQuotedCount;
/**
 * 询价单原材料DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryQuotedCountDaoImpl extends DaoImpl implements AccessoryEnquiryQuotedCountDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -734640007104079683L;
	//查询
	@Override
	public List<AccessoryEnquiryQuotedCount> listAccessoryEnquiryQuotedCount(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryEnquiryQuotedCountDao.class, "searchAccessoryEnquiryQuotedCount", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryQuotedCount(Map<String, Object> map){
		this.insert(AccessoryEnquiryQuotedCountDao.class, "saveAccessoryEnquiryQuotedCount", map);
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryQuotedCount(Map<String, Object> map){
		this.delete(AccessoryEnquiryQuotedCountDao.class, "deleteAccessoryEnquiryQuotedCount", map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryQuotedCount(Map<String, Object> map){
		this.update(AccessoryEnquiryQuotedCountDao.class, "updateAccessoryEnquiryQuotedCount", map);
	}
	//装载一个询价单原材料
	@Override
	public AccessoryEnquiryQuotedCount loadAccessoryEnquiryQuotedCount(Map<String, Object> map) {
		return (AccessoryEnquiryQuotedCount)this.get(AccessoryEnquiryQuotedCountDao.class, "searchAccessoryEnquiryQuotedCount", map);
	}
}