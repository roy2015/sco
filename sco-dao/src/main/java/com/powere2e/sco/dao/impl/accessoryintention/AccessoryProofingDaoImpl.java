package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryProofingDao;
import com.powere2e.sco.model.accessoryintention.AccessoryProofing;
/**
 * 辅料打样DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryProofingDaoImpl extends DaoImpl implements AccessoryProofingDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1533383630862809540L;
	//查询
	@Override
	public List<AccessoryProofing> listAccessoryProofing(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryProofingDao.class, "searchAccessoryProofing", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryProofing(Map<String, Object> map){
		this.insert(AccessoryProofingDao.class, "saveAccessoryProofing", map);
	}
	//删除
	@Override
	public void deleteAccessoryProofing(Map<String, Object> map){
		this.delete(AccessoryProofingDao.class, "deleteAccessoryProofing", map);
	}
	//修改
	@Override
	public void updateAccessoryProofing(Map<String, Object> map){
		this.update(AccessoryProofingDao.class, "updateAccessoryProofing", map);
	}
	//装载一个辅料打样
	@Override
	public AccessoryProofing loadAccessoryProofing(Map<String, Object> map) {
		return (AccessoryProofing)this.get(AccessoryProofingDao.class, "searchAccessoryProofing", map);
	}
}