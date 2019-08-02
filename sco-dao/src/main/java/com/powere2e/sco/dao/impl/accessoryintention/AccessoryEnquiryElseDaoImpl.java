package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryElseDao;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryElse;
/**
 * 询价单其他要求DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月30日
 */
public class AccessoryEnquiryElseDaoImpl extends DaoImpl implements AccessoryEnquiryElseDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8811482754247397673L;
	//查询
	@Override
	public List<AccessoryEnquiryElse> listAccessoryEnquiryElse(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryEnquiryElseDao.class, "searchAccessoryEnquiryElse", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryElse(Map<String, Object> map){
		this.insert(AccessoryEnquiryElseDao.class, "saveAccessoryEnquiryElse", map);
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryElse(Map<String, Object> map){
		this.delete(AccessoryEnquiryElseDao.class, "deleteAccessoryEnquiryElse", map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryElse(Map<String, Object> map){
		this.update(AccessoryEnquiryElseDao.class, "updateAccessoryEnquiryElse", map);
	}
	//装载一个询价单其他要求
	@Override
	public AccessoryEnquiryElse loadAccessoryEnquiryElse(Map<String, Object> map) {
		return (AccessoryEnquiryElse)this.get(AccessoryEnquiryElseDao.class, "searchAccessoryEnquiryElse", map);
	}
}