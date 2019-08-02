package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.relevanceaccessoryandwebsite;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.relevanceaccessoryandwebsite.RelevanceAccessoryAndWebsiteDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevanceaccessoryandwebsite.RelevanceAccessoryAndWebsite;

/**
 * 关联辅料与公示网站原料 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月17日
 */
public class RelevanceAccessoryAndWebsiteDaoImpl extends DaoImpl implements
		RelevanceAccessoryAndWebsiteDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7828317578108744746L;

	// 查询
	@Override
	public List<RelevanceAccessoryAndWebsite> listRelevanceAccessoryAndWebsite(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(RelevanceAccessoryAndWebsiteDao.class, 
				"listRelevanceAccessoryAndWebsite", map, pageInfo);
	}

	// 添加
	@Override
	public void insertRelevanceAccessoryAndWebsite(Map<String, Object> map) {
		this.insert(RelevanceAccessoryAndWebsiteDao.class, "insertRelevanceAccessoryAndWebsite", map);
	}

	// 删除
	@Override
	public void deleteRelevanceAccessoryAndWebsite(Map<String, Object> map) {
		this.delete(RelevanceAccessoryAndWebsiteDao.class, "deleteRelevanceAccessoryAndWebsite", map);
	}

	@Override
	public String ifExistsReleAccessoryAndWeb(Map<String, Object> map) {
		return (String) this.get(RelevanceAccessoryAndWebsiteDao.class, "ifExistsReleAccessoryAndWeb", map);
	}

}