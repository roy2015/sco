package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.relevancematerialandwebsite;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsiteDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;

/**
 * 关联商品原料与公示网站原料 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月13日
 */
public class RelevanceMaterialAndWebsiteDaoImpl extends DaoImpl implements
		RelevanceMaterialAndWebsiteDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2323202882441129981L;

	// 查询
	@Override
	public List<RelevanceMaterialAndWebsite> listRelevanceMaterialAndWebsite(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(RelevanceMaterialAndWebsiteDao.class, 
				"listRelevanceMaterialAndWebsite", map, pageInfo);
	}

	// 添加
	@Override
	public void insertRelevanceMaterialAndWebsite(Map<String, Object> map) {
		this.insert(RelevanceMaterialAndWebsiteDao.class, "saveRelevanceMaterialAndWebsite", map);
	}

	// 删除
	@Override
	public void deleteRelevanceMaterialAndWebsite(Map<String, Object> map) {
		this.delete(RelevanceMaterialAndWebsiteDao.class, "deleteRelevanceMaterialAndWebsite", map);
	}

	@Override
	public String ifExistsReleMateAndWeb(Map<String, Object> map) {
		return (String) this.get(RelevanceMaterialAndWebsiteDao.class, "ifExistsReleMateAndWeb", map);
	}

	@Override
	public void completeNotLinkMaterialAndWebsite(Map<String, Object> map) {
		this.update(RelevanceMaterialAndWebsiteDao.class, "completeNotLinkMaterialAndWebsite", map);
	}

}