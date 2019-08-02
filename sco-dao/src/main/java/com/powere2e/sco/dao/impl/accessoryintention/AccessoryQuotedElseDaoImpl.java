package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedElseDao;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElse;
/**
 * 辅料报价-其他成本DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedElseDaoImpl extends DaoImpl implements AccessoryQuotedElseDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1806477651907051959L;
	//查询
	@Override
	public List<AccessoryQuotedElse> listAccessoryQuotedElse(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryQuotedElseDao.class, "searchAccessoryQuotedElse", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedElse(Map<String, Object> map){
		this.insert(AccessoryQuotedElseDao.class, "saveAccessoryQuotedElse", map);
	}
	//删除
	@Override
	public void deleteAccessoryQuotedElse(Map<String, Object> map){
		this.delete(AccessoryQuotedElseDao.class, "deleteAccessoryQuotedElse", map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedElse(Map<String, Object> map){
		this.update(AccessoryQuotedElseDao.class, "updateAccessoryQuotedElse", map);
	}
	//装载一个辅料报价-其他成本
	@Override
	public AccessoryQuotedElse loadAccessoryQuotedElse(Map<String, Object> map) {
		return (AccessoryQuotedElse)this.get(AccessoryQuotedElseDao.class, "searchAccessoryQuotedElse", map);
	}
}