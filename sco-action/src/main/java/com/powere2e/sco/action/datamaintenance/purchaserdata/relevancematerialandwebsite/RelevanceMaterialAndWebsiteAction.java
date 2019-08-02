package com.powere2e.sco.action.datamaintenance.purchaserdata.relevancematerialandwebsite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsiteService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.security.model.Option;

/**
 * 原料和网站关联的WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月13日
 */
public class RelevanceMaterialAndWebsiteAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7961089497666373273L;
	private RelevanceMaterialAndWebsiteService relevanceMaterialAndWebsiteService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		relevanceMaterialAndWebsiteService = (RelevanceMaterialAndWebsiteService) ConfigFactory
				.getInstance().getBean("relevanceMaterialAndWebsiteService");
	}

	/**
	 * 显示关联商品原料与公示网站界面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite")
	public void doShowRelevanceMaterialAndWebsiteMain () {
		StringBuilder merchanseCodes = new StringBuilder();
		StringBuilder supplierCodes = new StringBuilder();
		StringBuilder msStr = new StringBuilder();
		//   ----   从新品引进页面跳转过来
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		if (StringUtils.isNotBlank(intentionAndSupplierCodes)) {
			List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
			for (ApplicationMerchandise appMer : list) {
				msStr.append(appMer.getMerchandiseCode()).append(",").append(appMer.getSupplierCode()).append(",");
				merchanseCodes.append(appMer.getMerchandiseCode()).append(",");
				supplierCodes.append(appMer.getSupplierCode()).append(",");
			}
		}
		//   ----   从新品引进页面跳转过来
		this.putObject("merchanseCodes", merchanseCodes.length() > 0 
				? merchanseCodes.substring(0, merchanseCodes.length() -1 ).toString() : merchanseCodes.toString());
		this.putObject("supplierCodes", supplierCodes.length() > 0 
				? supplierCodes.substring(0, supplierCodes.length() -1 ).toString() : supplierCodes.toString());
		this.putObject("msStr",
				msStr.length() > 0 ? msStr.substring(0, msStr.length() - 1).toString() : msStr.toString());
		this.putObject("oaType", this.asString("oaType"));
		this.forwardPage("sco/dataMaintenance/purchaserData/relevanceMaterialAndWebsite/relevanceMaterialAndWebsiteGrid.ftl");
	}
	
	/**
	 * 查询关联列表数据
	 * 
	 * @return 查询出lsit(导出Excel时使用) 
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite")
	public List<RelevanceMaterialAndWebsite> doListRelevanceMaterialAndWebsite() throws Exception {
		Map<String, Object> map = getRelevanceMaterialAndWebsite().toMap();
//		map.put("merchanseCodes", StrUtils.concatStr(this.asString("merchanseCodes").split(",")));
//		map.put("supplierCodes", StrUtils.concatStr(this.asString("supplierCodes").split(",")));
		String msStr = this.asString("msStr");
		if (StringUtils.isNotBlank(msStr)) {
			String[] paraArr =  StrUtils.concatStr(msStr.split(",")).split(",");
			map.put("paraArr", paraArr);
			int sizeOfArr = paraArr.length == 0 ? 0 : paraArr.length/2 + 2;
			map.put("sizeOfArr", sizeOfArr > paraArr.length ? paraArr.length : sizeOfArr);
		}
		map.put("oaType", this.asString("oaType"));
		boolean removePage = StringUtils.isNotBlank(this.asString("removePage"));//导出时不分页
		List<RelevanceMaterialAndWebsite> list = relevanceMaterialAndWebsiteService
				.listRelevanceMaterialAndWebsite(map, removePage ? null : this.getPageInfo());
		this.forwardData(true, list, null);
		return list;
	}

	/**
	 * 显示原料和网站关联界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite.add")
	public void doShowInsertRelevanceMaterialAndWebsiteForm() throws Exception {
		this.putObject("merchandiseCode", this.asString("merchandiseCode"));
		this.putObject("supplierCode", this.asString("supplierCode"));
		this.putObject("ingredientCode", this.asString("ingredientCode"));
		this.putObject("materialCode", this.asString("materialCode"));
		this.forwardPage("sco/dataMaintenance/purchaserData/relevanceMaterialAndWebsite/relevanceMaterialAndWebsiteForm.ftl");
	}

	/**
	 * 关联原料和网站
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite.add")
	public void doInsertRelevanceMaterialAndWebsite() throws Exception {
		RelevanceMaterialAndWebsite relevanceMaterialAndWebsite = getRelevanceMaterialAndWebsite();
		relevanceMaterialAndWebsiteService.insertRelevanceMaterialAndWebsite(relevanceMaterialAndWebsite);
		this.forwardData(true, null, "关联成功");
	}

	/**
	 * 删除
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite.del")
	public void doDeleteRelevanceMaterialAndWebsite() throws Exception {
		String ids = this.asString("ids");
		if(StringUtils.isBlank(ids) || ids.length() < 2) {
			throw new EscmException("请勾选至少一条记录");
		}
		relevanceMaterialAndWebsiteService.deleteRelevanceMaterialAndWebsite(ids);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite.del")
	public void doExportDataToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = "商品原料信息列表-".concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		this.relevanceMaterialAndWebsiteService.exportDataToExcel(this.doListRelevanceMaterialAndWebsite(), out);
	}

	/**
	 * 暂不关联公示网站
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite.notLink")
	public void doNotLinkMaterialAndWebsite() {
		String inCode = this.asString("inCode");
		String matCode = this.asString("matCode");
		relevanceMaterialAndWebsiteService.completeNotLinkMaterialAndWebsite(inCode, matCode);
		
		this.forwardData(true, null, this.getText("public.success"));
	}
	
	/**
	 * 查询商品原料类型
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.relevanceMaterialAndWebsite")
	public void doListMerchandiseMaterialType() {
		List<Option> list = new ArrayList<Option>();
		list.add(new Option(BusinessConstants.MaterialType.ZL.toString(), 
				BusinessConstants.MaterialType.ZL.getTypeName()));
		list.add(new Option(BusinessConstants.MaterialType.FL.toString(), 
				BusinessConstants.MaterialType.FL.getTypeName()));
		this.forwardData(true, list, null);
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private RelevanceMaterialAndWebsite getRelevanceMaterialAndWebsite()
			throws Exception {
		RelevanceMaterialAndWebsite relevanceMaterialAndWebsite = new RelevanceMaterialAndWebsite();
		this.asBean(relevanceMaterialAndWebsite);
		return relevanceMaterialAndWebsite;
	}
}
