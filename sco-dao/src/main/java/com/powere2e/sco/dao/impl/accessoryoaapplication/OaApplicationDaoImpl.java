package com.powere2e.sco.dao.impl.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.OaApplicationDao;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;

/**
 * 商品OA申请单DAO接口的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public class OaApplicationDaoImpl extends DaoImpl implements OaApplicationDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5014049711414761317L;

	// 查询
	@Override
	public List<OaApplication> listOaApplication(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(OaApplicationDao.class, "searchOaApplication", map, pageInfo);
	}

	// 添加
	@Override
	public void insertOaApplication(Map<String, Object> map) {
		this.insert(OaApplicationDao.class, "saveOaApplication", map);
	}

	// 删除
	@Override
	public void deleteOaApplication(Map<String, Object> map) {
		this.delete(OaApplicationDao.class, "deleteOaApplication", map);
	}

	// 修改
	@Override
	public Integer updateOaApplication(Map<String, Object> map) {
		return this.update(OaApplicationDao.class, "updateOaApplication", map);
	}

	// 修改
	@Override
	public void updateOaApplicationForUndo(Map<String, Object> map) {
		this.update(OaApplicationDao.class, "updateOaApplicationForUndo", map);
	}

	// 修改
	@Override
	public void updateOaApplicationForAllow(Map<String, Object> map) {
		this.update(OaApplicationDao.class, "updateOaApplicationForAllow", map);
	}

	// 装载一个商品OA申请单
	@Override
	public OaApplication loadOaApplication(Map<String, Object> map) {
		return (OaApplication) this.get(OaApplicationDao.class, "searchOaApplication", map);
	}
}