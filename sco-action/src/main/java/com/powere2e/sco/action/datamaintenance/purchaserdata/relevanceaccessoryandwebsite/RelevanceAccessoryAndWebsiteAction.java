package com.powere2e.sco.action.datamaintenance.purchaserdata.relevanceaccessoryandwebsite;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.utils.BusinessUtils;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.relevanceaccessoryandwebsite.RelevanceAccessoryAndWebsiteService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevanceaccessoryandwebsite.RelevanceAccessoryAndWebsite;

/**
 * 辅料和网站关联的WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月17日
 */
public class RelevanceAccessoryAndWebsiteAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -624087782780184525L;
	private RelevanceAccessoryAndWebsiteService relevanceAccessoryAndWebsiteService;
	
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		relevanceAccessoryAndWebsiteService = (RelevanceAccessoryAndWebsiteService) ConfigFactory
				.getInstance().getBean("relevanceAccessoryAndWebsiteService");
	}

	/**
	 * 显示辅联商品辅料公示网站界面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceAccessoryAndWebsite")
	public void doShowRelevanceAccessoryAndWebsiteMain () {
		this.putObject("accDetTypeList", BusinessUtils.ACCESSORY_DETAIL_TYPE);
		this.forwardPage("sco/dataMaintenance/purchaserData/relevanceAccessoryAndWebsite/relevanceAccessoryAndWebsiteGrid.ftl");
	}
	
	/**
	 * 查询关联列表数据
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceAccessoryAndWebsite")
	public void doListRelevanceAccessoryAndWebsite() throws Exception {
		Map<String, Object> map = this.getRelevanceAccessoryAndWebsite().toMap();
		List<RelevanceAccessoryAndWebsite> list = relevanceAccessoryAndWebsiteService
				.listRelevanceAccessoryAndWebsite(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示辅料和网站关联界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceAccessoryAndWebsite.add")
	public void doShowInsertRelevanceAccessoryAndWebsiteForm() throws Exception {
		this.putObject("merchandiseCode", this.asString("merchandiseCode"));
		this.putObject("ingredientCode", this.asString("ingredientCode"));
		this.putObject("materialCode", this.asString("materialCode"));
		this.forwardPage("sco/dataMaintenance/purchaserData/relevanceAccessoryAndWebsite/relevanceAccessoryAndWebsiteForm.ftl");
	}

	/**
	 * 关联辅料和网站
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceAccessoryAndWebsite.add")
	public void doInsertRelevanceAccessoryAndWebsite() throws Exception {
		RelevanceAccessoryAndWebsite relAcceAndWebsite = this.getRelevanceAccessoryAndWebsite();
		relevanceAccessoryAndWebsiteService.insertRelevanceAccessoryAndWebsite(relAcceAndWebsite);
		this.forwardData(true, null, "关联成功");
	}

	/**
	 * 删除
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceAccessoryAndWebsite.del")
	public void doDeleteRelevanceAccessoryAndWebsite() throws Exception {
		String ids = this.asString("ids");
		if(StringUtils.isBlank(ids) || ids.length() < 2) {
			throw new EscmException("请勾选至少一条记录");
		}
		relevanceAccessoryAndWebsiteService.deleteRelevanceAccessoryAndWebsite(ids);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceAccessoryAndWebsite.del")
	public void doExportDataToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = "辅料辅料信息列表-".concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = this.getRelevanceAccessoryAndWebsite().toMap();
		this.relevanceAccessoryAndWebsiteService.exportDataToExcel(map, out);
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private RelevanceAccessoryAndWebsite getRelevanceAccessoryAndWebsite()
			throws Exception {
		RelevanceAccessoryAndWebsite relevanceAccessoryAndWebsite = new RelevanceAccessoryAndWebsite();
		this.asBean(relevanceAccessoryAndWebsite);
		return relevanceAccessoryAndWebsite;
	}
	
}
