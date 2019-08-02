package com.powere2e.sco.action.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.common.MaterialPropertiesService;
import com.powere2e.security.model.Option;

/**
 * 原料相关属性数据查询
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年3月19日
 */
public class MaterialPropertiesAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3378859039439920224L;
	private MaterialPropertiesService materialPropertiesService;

	@Override
	protected void beforeBuild() {
		materialPropertiesService = (MaterialPropertiesService) ConfigFactory.getInstance()
				.getBean("materialPropertiesService");
	}

	/**
	 * 查询原料(大)类别
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMaterialBigType() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Option> list = this.materialPropertiesService.listMaterialBigType(map);
		this.forwardData(true, list, null);
	}

	/**
	 * 查询原料(小)类别
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMaterialSmallType() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialBigTypeCode", "'" + this.asString("materialBigTypeCode") + "'");
		List<Option> list = this.materialPropertiesService.listMaterialSmallType(map);
		this.forwardData(true, list, null);
	}

	/**
	 * 查询原料名称(公示网站原料名称)
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListWebsiteMaterialName() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialSmallTypeCode", "'" + this.asString("materialSmallTypeCode") + "'");
		List<Option> list = this.materialPropertiesService.listWebsiteMaterialName(map);
		this.forwardData(true, list, null);
	}
	/**
	 * 查询仓位名称
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListWebsiteMaterialSiteName() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Option> list = this.materialPropertiesService.listWebsiteMaterialSiteName(map);
		this.forwardData(true, list, null);
	}
}
