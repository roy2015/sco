package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedTotalDao;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedTotal;
/**
 * 辅料报价-总报价DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedTotalDaoImpl extends DaoImpl implements AccessoryQuotedTotalDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3559980576238485690L;
	//查询
	@Override
	public List<AccessoryQuotedTotal> listAccessoryQuotedTotal(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryQuotedTotalDao.class, "searchAccessoryQuotedTotal", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedTotal(Map<String, Object> map){
		this.insert(AccessoryQuotedTotalDao.class, "saveAccessoryQuotedTotal", map);
	}
	//删除 
	@Override
	public void deleteAccessoryQuotedTotal(Map<String, Object> map){
		this.delete(AccessoryQuotedTotalDao.class, "deleteAccessoryQuotedTotal", map);
	}
	@Override
	public void deleteAccessoryQuotedTotalFromQuotedCode(Map<String, Object> map){
		this.delete(AccessoryQuotedTotalDao.class, "deleteAccessoryQuotedTotalFromQuotedCode", map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedTotal(Map<String, Object> map){
		this.update(AccessoryQuotedTotalDao.class, "updateAccessoryQuotedTotal", map);
	}
	//装载一个辅料报价-总报价
	@Override
	public AccessoryQuotedTotal loadAccessoryQuotedTotal(Map<String, Object> map) {
		return (AccessoryQuotedTotal)this.get(AccessoryQuotedTotalDao.class, "searchAccessoryQuotedTotal", map);
	}
}