package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.websitename;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.websitename.WebsiteNameMaintenanceDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.websitename.Website;
import com.powere2e.security.model.Option;

/**
 * 公示网站名称维护DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class WebsiteNameMaintenanceDaoImpl extends DaoImpl implements WebsiteNameMaintenanceDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6888228112741708007L;

	// 查询下拉列表
	@Override
	public List<Option> listQualitative() {
		return this.query(WebsiteNameMaintenanceDao.class, "listQualitative", null, null);
	}

	// 查询
	@Override
	public List<Website> listWebsite(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(WebsiteNameMaintenanceDao.class, "searchWebsite", map, pageInfo);
	}

	// 查询
	@Override
	public List<Website> listMaxPriceUpdateDate(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(WebsiteNameMaintenanceDao.class, "searchMaxPriceUpdateDate", map, pageInfo);
	}

	// 添加
	@Override
	public void insertWebsite(Map<String, Object> map) {
		this.insert(WebsiteNameMaintenanceDao.class, "saveWebsite", map);
	}

	// 删除
	@Override
	public void deleteWebsite(Map<String, Object> map) {
		this.delete(WebsiteNameMaintenanceDao.class, "deleteWebsite", map);
	}

	// 装载一个公示网站名称维护
	@Override
	public Website loadWebsite(Map<String, Object> map) {
		return (Website) this.get(WebsiteNameMaintenanceDao.class, "loadWebsite", map);
	}

	@Override
	public Integer searchMaterial(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.get(WebsiteNameMaintenanceDao.class, "searchmaterial", map);
	}
}