package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.websitename;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.websitename.WebsiteNameMaintenanceDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.websitename.WebsiteNameMaintenanceService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.websitename.Website;
import com.powere2e.security.model.Option;

/**
 * 公示网站名称维护业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class WebsiteNameMaintenanceServiceImpl extends ServiceImpl implements WebsiteNameMaintenanceService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1987761462230571024L;
	private WebsiteNameMaintenanceDao websiteNameMaintenanceDao;

	public static WebsiteNameMaintenanceService getInstance() {
		return (WebsiteNameMaintenanceService) ConfigFactory.getInstance().getBean("websiteService");
	}

	// 获得公示网站名称维护DAO实例
	public WebsiteNameMaintenanceDao getWebsiteNameMaintenanceDao() {
		return websiteNameMaintenanceDao;
	}

	// 设置公示网站名称维护DAO实例
	public void setWebsiteNameMaintenanceDao(WebsiteNameMaintenanceDao websiteNameMaintenanceDao) {
		this.websiteNameMaintenanceDao = websiteNameMaintenanceDao;
	}

	// 查询
	@Override
	public List<Website> listWebsite(Map<String, Object> map, PageInfo pageInfo) {
		List<Website> listWebsite = this.websiteNameMaintenanceDao.listWebsite(map, pageInfo);
		return listWebsite;
	}

	// 添加
	@Override
	public void insertWebsite(Website website) {
		this.websiteNameMaintenanceDao.insertWebsite(website.toMap());
	}

	// 删除
	@Override
	public void deleteWebsite(String websiteCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("websiteCode", websiteCode.split(","));
		this.websiteNameMaintenanceDao.deleteWebsite(map);
	}

	@Override
	public List<Option> listWebsiteInfo() {
		return this.websiteNameMaintenanceDao.listQualitative();
	}

	// 加载一个公示网站名称维护
	@Override
	public Website loadWebsite(String websiteName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("websiteName", websiteName);
		return this.websiteNameMaintenanceDao.loadWebsite(map);
	}

	// 获取原料中存在该网站名称的数量
	@Override
	public Integer serachMaterial(String websiteCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("websiteCode", websiteCode);
		return this.websiteNameMaintenanceDao.searchMaterial(map);
	}
}