package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.websitematerial;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.websitematerial.WebsiteMaterialDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.websitematerial.WebsiteMaterial;
import com.powere2e.security.model.Option;

/**
 * 公示网站原料数据 Dao实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月7日
 * @version 1.0
 */
public class WebsiteMaterialDaoImpl extends DaoImpl implements
		WebsiteMaterialDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1489561782708841053L;

	@Override
	public List<WebsiteMaterial> listExistsWebsiteMaterial(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(WebsiteMaterialDao.class, "listExistsWebsiteMaterial", map, pageInfo);
	}

	@Override
	public Date searchMinCreated() {
		 return (Date) this.get(WebsiteMaterialDao.class, "searchMinCreated", null);
	}
	
	
	@Override
	public List<WebsiteMaterial> listExistsDataGroupByMonth(Map<String, Object> map) {
		return this.query(WebsiteMaterialDao.class, "existsDataGroupByMonth", map, null);
	}
	@Override
	public List<WebsiteMaterial> listNotExistsWebsiteMaterial(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(WebsiteMaterialDao.class, "listNotExistsWebsiteMaterial", map, pageInfo);
	}

	@Override
	public WebsiteMaterial loadWebsiteMaterialByMateCode(Map<String, Object> map) {
		return (WebsiteMaterial) this.get(WebsiteMaterialDao.class, "loadWebsiteMaterialByMateCode", map);
	}

	@Override
	public String loadWebMaterialPrice(Map<String, Object> map) {
		return (String) this.get(WebsiteMaterialDao.class, "loadWebMaterialPrice", map);
	}
	
	@Override
	public void insertWebMaterialPrice(Map<String, Object> map) {
		this.insert(WebsiteMaterialDao.class, "insertWebMaterialPrice", map);
	}

	@Override
	public void deleteWebsiteMaterialPrice(Map<String, Object> map) {
		this.delete(WebsiteMaterialDao.class, "deleteWebsiteMaterialPrice", map);
	}

	@Override
	public List<Option> listMaterialNameOption(Map<String, Object> map) {
		return this.query(WebsiteMaterialDao.class, "listMaterialNameOption", map, null);
	}

	@Override
	public List<Option> listMaterialRegionOption(Map<String, Object> map) {
		return this.query(WebsiteMaterialDao.class, "listMaterialRegionOption", map, null);
	}

	@Override
	public List<Option> listMaterialWebNameOption(Map<String, Object> map) {
		return this.query(WebsiteMaterialDao.class, "listMaterialWebNameOption", map, null);
	}

	@Override
	public List<Option> listMaterialWebUrlOption(Map<String, Object> map) {
		return this.query(WebsiteMaterialDao.class, "listMaterialWebUrlOption", map, null);
	}
	
}