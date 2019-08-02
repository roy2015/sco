package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedAccessoryDao;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedAccessory;
/**
 * 辅料报价-辅料信息DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedAccessoryDaoImpl extends DaoImpl implements AccessoryQuotedAccessoryDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8072862749590153899L;
	//查询
	@Override
	public List<AccessoryQuotedAccessory> listAccessoryQuotedAccessory(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryQuotedAccessoryDao.class, "searchAccessoryQuotedAccessory", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedAccessory(Map<String, Object> map){
		this.insert(AccessoryQuotedAccessoryDao.class, "saveAccessoryQuotedAccessory", map);
	}
	//删除
	@Override
	public void deleteAccessoryQuotedAccessory(Map<String, Object> map){
		this.delete(AccessoryQuotedAccessoryDao.class, "deleteAccessoryQuotedAccessory", map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedAccessory(Map<String, Object> map){
		this.update(AccessoryQuotedAccessoryDao.class, "updateAccessoryQuotedAccessory", map);
	}
	//装载一个辅料报价-辅料信息
	@Override
	public AccessoryQuotedAccessory loadAccessoryQuotedAccessory(Map<String, Object> map) {
		return (AccessoryQuotedAccessory)this.get(AccessoryQuotedAccessoryDao.class, "searchAccessoryQuotedAccessory", map);
	}
}