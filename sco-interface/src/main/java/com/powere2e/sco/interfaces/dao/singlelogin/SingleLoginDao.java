package com.powere2e.sco.interfaces.dao.singlelogin;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;

/**
 * 同步登陆用户 DAO接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年11月2日
 * @version 1.0
 */
public interface SingleLoginDao extends Dao {
	
	/**
	 * 同步来伊份用户数据
	 * 
	 * @param map
	 * 		<li>来伊份用户实例</li>
	 */
	public void completeSynUser(Map<String, Object> map);

	/**
	 * 删除用户
	 * 
	 * @param uids
	 *            根据用户ID删除用户
	 */
	public void deleteSynUser(String uids);

	/**
	 * 删除用户已经分配的角色
	 * 
	 * @param uids
	 *            用户ID
	 */
	public void deleteSynRoleRec(String uids);

}