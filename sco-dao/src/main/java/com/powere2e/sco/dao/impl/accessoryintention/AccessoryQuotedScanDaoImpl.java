package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedScanDao;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedScan;
/**
 * 辅料报价单-扫描版DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedScanDaoImpl extends DaoImpl implements AccessoryQuotedScanDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6173633424713525495L;
	//查询
	@Override
	public List<AccessoryQuotedScan> listAccessoryQuotedScan(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryQuotedScanDao.class, "searchAccessoryQuotedScan", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedScan(Map<String, Object> map){
		this.insert(AccessoryQuotedScanDao.class, "saveAccessoryQuotedScan", map);
	}
	//删除
	@Override
	public void deleteAccessoryQuotedScan(Map<String, Object> map){
		this.delete(AccessoryQuotedScanDao.class, "deleteAccessoryQuotedScan", map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedScan(Map<String, Object> map){
		this.update(AccessoryQuotedScanDao.class, "updateAccessoryQuotedScan", map);
	}
	//装载一个辅料报价单-扫描版
	@Override
	public AccessoryQuotedScan loadAccessoryQuotedScan(Map<String, Object> map) {
		return (AccessoryQuotedScan)this.get(AccessoryQuotedScanDao.class, "searchAccessoryQuotedScan", map);
	}
}