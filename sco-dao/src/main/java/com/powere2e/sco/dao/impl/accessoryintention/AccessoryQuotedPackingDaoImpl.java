package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedPackingDao;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedPacking;
/**
 * 辅料报价-内外包装信息DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedPackingDaoImpl extends DaoImpl implements AccessoryQuotedPackingDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3400215140500635927L;
	//查询
	@Override
	public List<AccessoryQuotedPacking> listAccessoryQuotedPacking(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryQuotedPackingDao.class, "searchAccessoryQuotedPacking", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedPacking(Map<String, Object> map){
		this.insert(AccessoryQuotedPackingDao.class, "saveAccessoryQuotedPacking", map);
	}
	//删除
	@Override
	public void deleteAccessoryQuotedPacking(Map<String, Object> map){
		this.delete(AccessoryQuotedPackingDao.class, "deleteAccessoryQuotedPacking", map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedPacking(Map<String, Object> map){
		this.update(AccessoryQuotedPackingDao.class, "updateAccessoryQuotedPacking", map);
	}
	//装载一个辅料报价-内外包装信息
	@Override
	public AccessoryQuotedPacking loadAccessoryQuotedPacking(Map<String, Object> map) {
		return (AccessoryQuotedPacking)this.get(AccessoryQuotedPackingDao.class, "searchAccessoryQuotedPacking", map);
	}
}