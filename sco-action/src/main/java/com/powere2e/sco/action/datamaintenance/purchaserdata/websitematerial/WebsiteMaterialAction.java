package com.powere2e.sco.action.datamaintenance.purchaserdata.websitematerial;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.websitematerial.WebsiteMaterialService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.websitematerial.WebsiteMaterial;
import com.powere2e.security.model.Option;

/**
 * 公示网站原料数据模块
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月7日
 * @version 1.0
 */
public class WebsiteMaterialAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1530452696253508523L;
	private WebsiteMaterialService websiteMaterialService;

	@Override
	protected void beforeBuild() {
		websiteMaterialService = (WebsiteMaterialService) ConfigFactory
				.getInstance().getBean("websiteMaterialService");
	}

	/**
	 * 显示公示网站原料模块主界面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.websiteMaterial")
	public void doShowWebsiteMaterialMain() {
		this.forwardPage("sco/dataMaintenance/purchaserData/websiteMaterial/websiteMaterialGrid.ftl");
	}

	/**
	 * 查询已有网站价格数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.websiteMaterial")
	public void doListExistsWebsiteMaterial() throws Exception {
		Map<String, Object> map = this.getWebsiteMaterial().toMap();
		map.put("priceEndDate", this.asDate("priceEndDate"));
		map.put("exact", true);//公示网站原料名称下拉框用的是名称,sql中会使用模糊查询,这里改用精确查询
		List<WebsiteMaterial> list = this.websiteMaterialService
				.listExistsWebsiteMaterial(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 查询缺少网站价格数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.websiteMaterial")
	public void doListNotExistsWebsiteMaterial() throws Exception {
		Map<String, Object> map = this.getWebsiteMaterial().toMap();
		map.put("priceEndDate", this.asDate("priceEndDate"));
		List<WebsiteMaterial> list = this.websiteMaterialService
				.listNotExistsWebsiteMaterial(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示新增页面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.websiteMaterial.add")
	public void doShowAddWebsiteMaterial() throws Exception {
		WebsiteMaterial website = this.websiteMaterialService
				.loadWebsiteMaterialByMateCode(this.getWebsiteMaterial().toMap());
		if(website == null) {
			throw new EscmException("当前所选数据可能已发生变动,请刷新页面获取最新数据");
		}
		this.putObject("website", website);
		this.forwardPage("sco/dataMaintenance/purchaserData/websiteMaterial/websiteMaterialForm.ftl");
	} 
	
	/**
	 * 新增原料价格数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.websiteMaterial.add")
	public void doInsertWebMaterialPrice() throws Exception {
		this.websiteMaterialService.insertWebMaterialPrice(this.getWebsiteMaterial());
		this.forwardData(true, null, "新增成功");
	}

	/**
	 * 删除原料价格数据
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.websiteMaterial.add")
	public void doDeleteWebsiteMaterialPrice() {
		String ids = this.asString("ids");
		if(StringUtils.isBlank(ids)) {
			throw new EscmException("请勾选至少一条记录");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		this.websiteMaterialService.deleteWebsiteMaterialPrice(map);
		this.forwardData(true, null, "删除成功");
	}
	
	/**
	 * 查询公示网站原料名称下拉框
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMaterialNameOption() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialBigTypeCode", this.asString("materialBigTypeCode"));
		map.put("materialSmallTypeCode", this.asString("materialSmallTypeCode"));
		map.put("materialCode", this.asString("materialCode"));
		List<Option> list = this.websiteMaterialService.listMaterialNameOption(map);
		this.forwardData(true, list, null);
	}

	/**
	 * 查询价格地区下拉框
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMaterialRegionOption() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialBigTypeCode", this.asString("materialBigTypeCode"));
		map.put("materialSmallTypeCode", this.asString("materialSmallTypeCode"));
		map.put("materialCode", this.asString("materialCode"));
		List<Option> list = this.websiteMaterialService.listMaterialRegionOption(map);
		this.forwardData(true, list, null);
	}
	
	/**
	 * 查询网站名称下拉框
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMaterialWebNameOption() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialBigTypeCode", this.asString("materialBigTypeCode"));
		map.put("materialSmallTypeCode", this.asString("materialSmallTypeCode"));
		map.put("materialCode", this.asString("materialCode"));
		map.put("materialRegionCode", this.asString("materialRegionCode"));
		List<Option> list = this.websiteMaterialService.listMaterialWebNameOption(map);
		this.forwardData(true, list, null);
	}

	/**
	 * 查询网站地址下拉框
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMaterialWebUrlOption() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialBigTypeCode", this.asString("materialBigTypeCode"));
		map.put("materialSmallTypeCode", this.asString("materialSmallTypeCode"));
		map.put("materialCode", this.asString("materialCode"));
		map.put("materialRegionCode", this.asString("materialRegionCode"));
		List<Option> list = this.websiteMaterialService.listMaterialWebUrlOption(map);
		this.forwardData(true, list, null);
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private WebsiteMaterial getWebsiteMaterial() throws Exception {
		WebsiteMaterial websiteMaterial = new WebsiteMaterial();
		this.asBean(websiteMaterial);
		return websiteMaterial;
	}

}
