package com.powere2e.sco.action.datamaintenance.purchaserdata.websitename;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.websitename.WebsiteNameMaintenanceService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.websitename.Website;
import com.powere2e.security.model.Option;

/**
 * 公示网站名称维护的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class WebsiteNameMaintenanceAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6602640565610114521L;
	private WebsiteNameMaintenanceService websiteNameMaintenanceService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		websiteNameMaintenanceService = (WebsiteNameMaintenanceService) ConfigFactory.getInstance().getBean("websiteService");
	}
	/**
	 * 商品细分类下拉维护设置列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.purchaserdata.websiteName")
	public void doWebsiteNameMaintenanceGrid() throws Exception {
		this.forwardPage("sco/dataMaintenance/purchaserData/websiteNameMaintenance/websiteNameMaintenanceGrid.ftl");
	}
	/**
	 * 公示网站名称维护列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.websiteName")
	public void doListWebsiteNameMaintenance() throws Exception {
		Map<String, Object> map = getWebsiteNameMaintenance().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<Website> list = websiteNameMaintenanceService.listWebsite(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个公示网站名称维护
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.websiteName")
	public void doLoadWebsiteNameMaintenance() throws Exception {
		Website website = this.websiteNameMaintenanceService.loadWebsite(asString("websiteCode"));
		this.forwardData(true, website, null);
	}

	/**
	 * 添加公示网站名称维护界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.websiteName.insert")
	public void doShowInsertWebsiteNameMaintenanceForm() throws Exception {
		Website website = new Website();
		this.putObject("website", website);
		this.forwardPage("sco/dataMaintenance/purchaserData/websiteNameMaintenance/websiteNameMaintenanceForm.ftl");
	}
	/**
	 * 网站名称数据查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListWebsiteNameMaintenanceInfo() {
		List<Option> list =websiteNameMaintenanceService.listWebsiteInfo();
		this.forwardData(true, list, null);
	}
	/**
	 * 查询公示网站
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private boolean doListWebsiteNameMaintenanceByInfo(String websiteName) throws Exception {
		boolean erification = false;
		Website website = websiteNameMaintenanceService.loadWebsite(websiteName);
		if (website == null) {
			erification = true;
		}
		return erification;
	}
	/**
	 * 添加公示网站名称维护
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.websiteName.insert")
	public void doInsertWebsiteNameMaintenance() throws Exception {
		Website website = getWebsiteNameMaintenance();
		boolean erification = this.doListWebsiteNameMaintenanceByInfo(website.getWebsiteName());
		String msg = "该公示网站名称已存在，不可重复添加";
		if (erification == true) {
			msg=this.getText("public.success");
			websiteNameMaintenanceService.insertWebsite(website);
		}
		this.forwardData(erification, null, msg);
	}
	/**
	 * 获取当前公示网站ID是否存在于原料
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public boolean doListWebsiteNameMaintenance(String websiteCode) throws Exception {
		boolean erification = false;
		String[] role = websiteCode.split(",");
		for (int i = 0; i < role.length; i++) {
			Integer count = websiteNameMaintenanceService.serachMaterial(role[i]);
			if (count <= 0) {
				erification = true;
			} else {
				erification = false;
				break;
			}
		}
		return erification;
	}
	/**
	 * 删除公示网站名称维护
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.websiteName.delete")
	public void doDeleteWebsiteNameMaintenance() throws Exception {
		boolean erification = doListWebsiteNameMaintenance(asString("websiteCode"));
		String msg = "不可删除已关联原料的公示网站";
		if (erification == true) {
			msg=this.getText("public.success");
			websiteNameMaintenanceService.deleteWebsite(asString("websiteCode"));
		}
		this.forwardData(erification, null,msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Website getWebsiteNameMaintenance() throws Exception {
		Website website = new Website();
		this.asBean(website);
		return website;
	}
}
