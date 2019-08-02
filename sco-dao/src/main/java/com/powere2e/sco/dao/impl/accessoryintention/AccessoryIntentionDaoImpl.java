package com.powere2e.sco.dao.impl.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryIntentionDao;
import com.powere2e.sco.model.accessoryintention.AccessoryIntention;
import com.powere2e.sco.model.accessoryintention.AccessoryIntentionSupplier;
import com.powere2e.security.model.Option;
/**
 * 辅料意向品DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月16日
 */
public class AccessoryIntentionDaoImpl extends DaoImpl implements AccessoryIntentionDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8962631372229441566L;
	//查询
	@Override
	public List<AccessoryIntention> listAccessoryIntention(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryIntentionDao.class, "searchAccessoryIntention", map,pageInfo);
	}
	//查询关联供应商
		@Override
		public List<AccessoryIntentionSupplier> listAccessoryIntentionSupplier(Map<String, Object> map,PageInfo pageInfo){
			return this.query(AccessoryIntentionDao.class, "searchAccessoryIntentionSupplier", map,pageInfo);
		}
	//添加
	@Override
	public void insertAccessoryIntention(Map<String, Object> map){
		this.insert(AccessoryIntentionDao.class, "saveAccessoryIntention", map);
	}

	// 添加关联供应商
	@Override
	public void insertAccessoryIntentionSupplier(Map<String, Object> map) {
		this.insert(AccessoryIntentionDao.class,
				"insertAccessoryIntentionSupplier", map);
	}

	// 页面添加供应商到表
	@Override
	public void insertIntentionSupplier(Map<String, Object> map) {
		this.insert(AccessoryIntentionDao.class,
				"insertIntentionSupplier", map);
	}

	// 删除
	@Override
	public void deleteAccessoryIntention(Map<String, Object> map){
		this.delete(AccessoryIntentionDao.class, "deleteAccessoryIntention", map);
	}
	//删除关联供应商
		@Override
		public void deleteAccessoryIntentionSupplier(Map<String, Object> map){
			this.delete(AccessoryIntentionDao.class, "deleteAccessoryIntentionSupplier", map);
		}
	//修改
	@Override
	public void updateAccessoryIntention(Map<String, Object> map){
		this.update(AccessoryIntentionDao.class, "updateAccessoryIntention", map);
	}
	//装载一个辅料意向品
	@Override
	public AccessoryIntention loadAccessoryIntention(Map<String, Object> map) {
		return (AccessoryIntention)this.get(AccessoryIntentionDao.class, "searchAccessoryIntention", map);
	}
	@Override
	public List<Option> listSupplier(Map<String, Object> map) {
		return this.query(AccessoryIntentionDao.class, "listSupplier", map, null);
	}
	@Override
	public List<Option> listMinceType(Map<String, Object> map) {
		return this.query(AccessoryIntentionDao.class, "listMinceType", map, null);
	}
	@Override
	public AccessoryIntentionSupplier loadIntentionSupplier(Map<String, Object> map2) {
		// TODO Auto-generated method stub
		return (AccessoryIntentionSupplier)this.get(AccessoryIntentionDao.class, "loadIntentionSupplier", map2);
	}
}