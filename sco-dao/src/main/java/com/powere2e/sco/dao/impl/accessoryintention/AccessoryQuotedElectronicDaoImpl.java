package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedElectronicDao;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElectronic;
/**
 * 辅料报价单-电子版DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedElectronicDaoImpl extends DaoImpl implements AccessoryQuotedElectronicDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4801790678818069446L;
	//查询
	@Override
	public List<AccessoryQuotedElectronic> listAccessoryQuotedElectronic(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryQuotedElectronicDao.class, "searchAccessoryQuotedElectronic", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedElectronic(Map<String, Object> map){
		this.insert(AccessoryQuotedElectronicDao.class, "saveAccessoryQuotedElectronic", map);
	}
	//删除
	@Override
	public void deleteAccessoryQuotedElectronic(Map<String, Object> map){
		this.delete(AccessoryQuotedElectronicDao.class, "deleteAccessoryQuotedElectronic", map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedElectronic(Map<String, Object> map){
		this.update(AccessoryQuotedElectronicDao.class, "updateAccessoryQuotedElectronic", map);
	}
	//装载一个辅料报价单-电子版
	@Override
	public AccessoryQuotedElectronic loadAccessoryQuotedElectronic(Map<String, Object> map) {
		return (AccessoryQuotedElectronic)this.get(AccessoryQuotedElectronicDao.class, "searchAccessoryQuotedElectronic", map);
	}
	//装载一个辅料报价单-电子版
		@Override
		public AccessoryQuotedElectronic loadAccessoryQuotedElectronicFor(Map<String, Object> map) {
			return (AccessoryQuotedElectronic)this.get(AccessoryQuotedElectronicDao.class, "loadAccessoryQuotedElectronic", map);
		}
}