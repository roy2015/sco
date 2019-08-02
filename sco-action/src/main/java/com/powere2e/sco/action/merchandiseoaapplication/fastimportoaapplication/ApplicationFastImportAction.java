package com.powere2e.sco.action.merchandiseoaapplication.fastimportoaapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.common.MasterDataTypeService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.MerchandiseOaApplicationService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastimportoaapplication.ApplicationFastImportService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;

/**
 * 商品快速引进 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public class ApplicationFastImportAction extends UploadUtils {

	/**
	 * 
	 */
	private static final long serialVersionUID = -549512973077950861L;
	private ApplicationFastImportService fastImportApplicationService;// 快速引进service
	private MerchandiseOaApplicationService merchandiseOaApplicationService;// OA申请的service
	private MasterDataTypeService masterDataTypeService;// 获取主键id的service
	private ApplicationFastadjustpriceService fastadjustpriceService;
	
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		fastImportApplicationService = (ApplicationFastImportService) ConfigFactory
				.getInstance().getBean("fastImportApplicationService");
		merchandiseOaApplicationService = (MerchandiseOaApplicationService) ConfigFactory
				.getInstance().getBean("merchandiseOaApplicationService");
		masterDataTypeService = (MasterDataTypeService) ConfigFactory
				.getInstance().getBean("masterDataTypeService");
		fastadjustpriceService = (ApplicationFastadjustpriceService) ConfigFactory.getInstance().getBean("fastAdjustpriceService");
	}

	/**
	 * 显示快速调价页面
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doShowApplicationFastImportMain() throws Exception {
		this.forwardPage("sco/merchandiseOaApplication/fastImportOaApplication/fastImportGrid.ftl");
	}

	/**
	 * 查询快速引进数据
	 * 
	 * @return
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doListApplicationFastImport() throws Exception {
		List<MerchandiseIntention> list = merchandiseOaApplicationService
				.queryIntentionApplicationList(
						this.getMerchandiseIntentionMap(), this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 商品快速引进添加界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doShowInsertApplicationFastImportForm() throws Exception {
		// 页面OA传递申请单号
		String applicationCode = "OA".concat(masterDataTypeService
				.nextID("S_OA_APPLICATION"));
		// 向页面传递所选择的意向品编号和供应商编号组
		String intentionAndSupplierCodes = this
				.asString("intentionAndSupplierCodes");
		this.putObject("applicationCode", applicationCode);
		this.putObject("intentionAndSupplierCodes", intentionAndSupplierCodes);
		this.forwardPage("sco/merchandiseOaApplication/fastImportOaApplication/fastImportPanel.ftl");
	}
	
	/**
	 * 修改申请报告(快速引进)界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doShowUpdateApplicationFastadjustpriceForm() throws Exception {
		// 页面OA传递申请单号
		String applicationCode = this.asString("applicationCode");
		// 向页面传递所选择的意向品编号和供应商编号组
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);

		String intentionAndSupplierCodes = "";
		List<ApplicationMerchandise> codeList = merchandiseOaApplicationService.findMerchandiseCodeAndSupplierCodeByApplicationCode(map);
		if (codeList != null && codeList.size() > 0) {
			for (int i = 0; i < codeList.size(); i++) {
				ApplicationMerchandise eachMerchandise = codeList.get(i);
				if (i < codeList.size() - 1) {
					intentionAndSupplierCodes += eachMerchandise.getMerchandiseCode() + ":" + eachMerchandise.getSupplierCode() + ",";
				} else if (i == codeList.size() - 1) {
					intentionAndSupplierCodes += eachMerchandise.getMerchandiseCode() + ":" + eachMerchandise.getSupplierCode();
				}
			}
		}

		this.putObject("applicationCode", applicationCode);
		this.putObject("intentionAndSupplierCodes", intentionAndSupplierCodes);
		this.forwardPage("sco/merchandiseOaApplication/fastImportOaApplication/fastImportPanel.ftl");
	}
	
	/**
	 * 删除申请报告(快速调价)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doCompleteDelFastImport() throws Exception {
		String[] applicationCodes = this.asStrings("applicationCodes[]");// 审批单号
		String appCodes = "";
		if (applicationCodes != null && applicationCodes.length > 0) {
			appCodes = this.fastImportApplicationService.completeDelFastImport(
					StringUtils.join(applicationCodes, ","));
		}
		if (StringUtils.isNotBlank(appCodes)) {
			appCodes = appCodes.replace("'", "");
			this.forwardData(false, appCodes, null);
			return;
		}
		this.forwardData(true, null, this.getText("public.success"));
	}
	/**
	 * 导出快速引进数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doExportFastImportToExcel() throws Exception {
		Map<String, Object> map = this.getMerchandiseIntentionMap();
		map.put("exportNew", this.asString("exportNew"));//导出数据时:该OA申请单创建人
		List<MerchandiseIntention> list = merchandiseOaApplicationService
				.queryIntentionApplicationList(map, null);
		ServletOutputStream out = response.getOutputStream();
		String fileName = "商品快速引进OA申请列表_".concat(DateUtils.formateDateTime())
				.concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1),
						Constant.DEFAULT_ENCODED_2) + "\"");
		this.fastImportApplicationService.exportFastImportToExcel(list, out);
	}

	/**
	 * 检查申请单是否存在必填文件和建议文件
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doSyncApplicationFastadjustprice() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String applicationCode = this.asString("applicationCode");
		map.put("applicationCode", applicationCode);

		// 判断是否缺少必填文件
		List<ApplicationLackFileM> list = fastadjustpriceService.queryNotHaveReportMerchandiseFastadjustprice(map);
		if (list == null || list.size() == 0) {
			// 缺少必填文件，就不同步OA申请单
			StringBuffer check = new StringBuffer();
			/*check.append("所选申请单[商品编号-供应商编号:<br>");
			for (ApplicationLackFileM reportNew : list) {
				check.append(reportNew.getMerchandiseCode() 
						.concat("-").concat(reportNew.getSupplierCode()))
						.append(";<br>");
			}*/
			check.append("缺少必填文件申请文件，不能新增创建OA快速引进申请!");
			this.forwardData(false, check.toString(), "缺少必填文件");
		} else {
			this.forwardData(true, null, this.getText("public.success"));
		}
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @return 实例map
	 * @throws Exception
	 */
	private Map<String, Object> getMerchandiseIntentionMap() throws Exception {
		MerchandiseIntention mi = new MerchandiseIntention();
		this.asBean(mi);
		Map<String, Object> map = mi.toMap();
		map.put("applicationType",
				BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT
						.toString());// 只查询快速引进类型的申请列表
		map.put("notExistsApplicationType",
				BusinessConstants.ApplicationType.MERCHANDISE_NEW);// 新品引进与快速引进互斥,需除开

		String createDateStart = this.asString("minCreateDate");
		String createDateEnd = this.asString("maxCreateDate");
		String applicationDateStart = this.asString("minApplicationDate");
		String applicationDateEnd = this.asString("maxApplicationDate");
		// 巡查申请单提交日期
		String visitDateStart = this.asString("minVisitDate");
		String visitDateEnd = this.asString("maxVisitDate");
		// 包装设计申请提交日期
		String packageDateStart = this.asString("minPackageDate");
		String packageDateEnd = this.asString("maxPackageDate");

		map.put("createDateStart", createDateStart);
		map.put("createDateEnd", createDateEnd);
		map.put("applicationDateStart", applicationDateStart);
		map.put("applicationDateEnd", applicationDateEnd);

		map.put("visitDateStart", visitDateStart);
		map.put("visitDateEnd", visitDateEnd);
		map.put("packageDateStart", packageDateStart);
		map.put("packageDateEnd", packageDateEnd);
		return map;
	}

}
