package com.powere2e.sco.dao.impl.singlelogin;

import java.util.HashMap;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.singlelogin.SingleLoginDao;

/**
 * 单点登陆模块
 * 
 * @author Gavillen.Zhou
 * @since 2015年11月2日
 * @version 1.0
 */
public class SingleLoginDaoImpl extends DaoImpl implements SingleLoginDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3288265560388298875L;

	@Override
	public void completeSynUser(Map<String, Object> map) {
		this.update(SingleLoginDao.class, "completeSynUser", map);
	}

	@Override
	public void deleteSynUser(String uids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uids", uids);
		this.update(SingleLoginDao.class, "deleteSynUser", map);
	}

	@Override
	public void deleteSynRoleRec(String uids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uids", uids);
		this.delete(SingleLoginDao.class, "deleteSynRoleRec", map);
	}

}