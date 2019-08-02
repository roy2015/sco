package com.powere2e.sco.dao.impl.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.ApplicationQuotedDao;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationQuoted;

/**
 * 报价单DAO接口的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月30日
 */
public class ApplicationQuotedDaoImpl extends DaoImpl implements ApplicationQuotedDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3797548874095649916L;

	// 查询
	@Override
	public List<ApplicationQuoted> listApplicationQuoted(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(ApplicationQuotedDao.class, "searchApplicationQuoted", map, pageInfo);
	}

	// 查询
	@Override
	public List<ApplicationQuoted> listApplicationQuotedForDhInfo(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(ApplicationQuotedDao.class, "searchApplicationQuotedForDhInfo", map, pageInfo);
	}

	// 添加
	@Override
	public void insertApplicationQuoted(Map<String, Object> map) {
		this.insert(ApplicationQuotedDao.class, "saveApplicationQuoted", map);
	}

	// 删除
	@Override
	public void deleteApplicationQuoted(Map<String, Object> map) {
		this.delete(ApplicationQuotedDao.class, "deleteApplicationQuoted", map);
	}

	// 修改
	@Override
	public void updateApplicationQuoted(Map<String, Object> map) {
		this.update(ApplicationQuotedDao.class, "updateApplicationQuoted", map);
	}

	// 装载一个报价单
	@Override
	public ApplicationQuoted loadApplicationQuoted(Map<String, Object> map) {
		return (ApplicationQuoted) this.get(ApplicationQuotedDao.class, "searchApplicationQuoted", map);
	}
}