package com.powere2e.sco.dao.impl.masterdata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.masterdata.MerchandiseRoleDao;
import com.powere2e.security.model.Option;

/**
 * 商品角色查询 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年3月18日
 */
public class MerchandiseRoleDaoImpl extends DaoImpl implements
		MerchandiseRoleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2513888168399157661L;

	@Override
	public List<Option> listQualitative(Map<String, Object> map) {
		return this.query(MerchandiseRoleDao.class, "listQualitative", map,
				null);
	}

	@Override
	public List<Option> listQuantify(Map<String, Object> map) {
		return this.query(MerchandiseRoleDao.class, "listQuantify", map, null);
	}

	/**
	 * 更改商品角色维护
	 */
	@Override
	public void updateMerchandise(Map<String, Object> map) {
		
		this.update(MerchandiseRoleDao.class, "updateMerchandiseRole", map);
	}

	/**
	 * 添加商品角色
	 */
	@Override
	public void insertMerchandise(Map<String, Object> map) {
		this.insert(MerchandiseRoleDao.class, "insertMerchandiseRole", map);
	}

	@Override
	public List<String> listMerchandiseDxRoleStorageForm() {
		
		return this.query(MerchandiseRoleDao.class, "listMerchandiseDxRoleStorageForm", null, null);
	}
	
	@Override
	public String searchMerchandiseDxRoleName(Map<String, Object> map) {
		
		return (String) this.get(MerchandiseRoleDao.class, "searchMerchandiseDxRoleName", map);
	}
	@Override
	public Integer searchMerchandiseCount(Map<String, Object> map) {
		
		return (Integer) this.get(MerchandiseRoleDao.class, "searchMerchandiseRole", map);
	}

	@Override
	public String searchMerchandiseDlRoleName(Map<String, Object> map) {
		
		return (String) this.get(MerchandiseRoleDao.class, "searchMerchandiseDlRoleName", map);
	}
}