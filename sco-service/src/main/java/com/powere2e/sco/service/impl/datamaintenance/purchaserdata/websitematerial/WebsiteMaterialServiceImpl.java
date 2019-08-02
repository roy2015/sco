package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.websitematerial;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.websitematerial.WebsiteMaterialDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.websitematerial.WebsiteMaterialService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.websitematerial.WebsiteMaterial;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;
import com.powere2e.security.model.Option;
import com.powere2e.security.utils.PowerUtils;

/**
 * 公示网站原料数据 Service实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月7日
 * @version 1.0
 */
public class WebsiteMaterialServiceImpl extends ServiceImpl implements
		WebsiteMaterialService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8323127259737944328L;
	private WebsiteMaterialDao websiteMaterialDao;

	// 获取WebsiteMaterial Dao实例
	public WebsiteMaterialDao getWebsiteMaterialDao() {
		return websiteMaterialDao;
	}

	// 设置WebsiteMaterial Dao实例
	public void setWebsiteMaterialDao(WebsiteMaterialDao websiteMaterialDao) {
		this.websiteMaterialDao = websiteMaterialDao;
	}

	@Override
	public List<WebsiteMaterial> listExistsWebsiteMaterial(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.websiteMaterialDao.listExistsWebsiteMaterial(map, pageInfo);
	}

	@Override
	public List<WebsiteMaterial> listNotExistsWebsiteMaterial(Map<String, Object> map,
			PageInfo pageInfo) {
		//查询时间区间
		Date startDate = (Date) map.get("priceDate");
		Date endDate = (Date) map.get("priceEndDate");
		if (startDate == null) {
			startDate = this.websiteMaterialDao.searchMinCreated();
			endDate = DateUtils.getLastMonthByNow();
		}
		List<String> dateRegion = DateUtils.getRegionDates(startDate, endDate);
		//若区间内无对应日期
		List<WebsiteMaterial> list = new ArrayList<WebsiteMaterial>();
		if (dateRegion.isEmpty()) return list;
		
		map.put("list", dateRegion);
		map.put("startDate", startDate);
		map.put("endDate", DateUtils.formatStrToDate(dateRegion.get(dateRegion.size() - 1), "yyyy-MM-dd"));
		list = this.websiteMaterialDao.listExistsDataGroupByMonth(map);
		if (list.isEmpty()) return list;
		
		map.put("list", list);
		return this.websiteMaterialDao.listNotExistsWebsiteMaterial(map, pageInfo);
	}

	@Override
	public WebsiteMaterial loadWebsiteMaterialByMateCode(Map<String, Object> map) {
		return this.websiteMaterialDao.loadWebsiteMaterialByMateCode(map);
	}

	@Override
	public void insertWebMaterialPrice(WebsiteMaterial websiteMaterial) {
		String repeatId = this.websiteMaterialDao.loadWebMaterialPrice(websiteMaterial.toMap());
		if (repeatId != null) {
			throw new EscmException("该原料该日期已有记录，不可保存");
		}
		websiteMaterial.setId(MasterDataTypeServiceImpl.getInstance().nextID("s_material_price"));//ID
		websiteMaterial.setCreateby(PowerUtils.getCurrentUser().getUserId());//创建人
		this.websiteMaterialDao.insertWebMaterialPrice(websiteMaterial.toMap());
	}

	@Override
	public void deleteWebsiteMaterialPrice(Map<String, Object> map) {
		this.websiteMaterialDao.deleteWebsiteMaterialPrice(map);
	}

	//查询公示网站原料名称下拉框
	@Override
	public List<Option> listMaterialNameOption(Map<String, Object> map) {
		return this.websiteMaterialDao.listMaterialNameOption(map);
	}

	// 查询价格区域下拉框
	public List<Option> listMaterialRegionOption(Map<String, Object> map) {
		return this.websiteMaterialDao.listMaterialRegionOption(map);
	}

	@Override
	public List<Option> listMaterialWebNameOption(Map<String, Object> map) {
		return this.websiteMaterialDao.listMaterialWebNameOption(map);
	}

	@Override
	public List<Option> listMaterialWebUrlOption(Map<String, Object> map) {
		return this.websiteMaterialDao.listMaterialWebUrlOption(map);
	}

}