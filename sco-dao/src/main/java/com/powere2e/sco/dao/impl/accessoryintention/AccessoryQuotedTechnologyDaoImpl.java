package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedTechnologyDao;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedTechnology;
/**
 * 辅料报价-工艺信息DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedTechnologyDaoImpl extends DaoImpl implements AccessoryQuotedTechnologyDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2076178923109853025L;
	//查询
	@Override
	public List<AccessoryQuotedTechnology> listAccessoryQuotedTechnology(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryQuotedTechnologyDao.class, "searchAccessoryQuotedTechnology", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedTechnology(Map<String, Object> map){
		this.insert(AccessoryQuotedTechnologyDao.class, "saveAccessoryQuotedTechnology", map);
	}
	//删除
	@Override
	public void deleteAccessoryQuotedTechnology(Map<String, Object> map){
		this.delete(AccessoryQuotedTechnologyDao.class, "deleteAccessoryQuotedTechnology", map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedTechnology(Map<String, Object> map){
		this.update(AccessoryQuotedTechnologyDao.class, "updateAccessoryQuotedTechnology", map);
	}
	//装载一个辅料报价-工艺信息
	@Override
	public AccessoryQuotedTechnology loadAccessoryQuotedTechnology(Map<String, Object> map) {
		return (AccessoryQuotedTechnology)this.get(AccessoryQuotedTechnologyDao.class, "searchAccessoryQuotedTechnology", map);
	}
}