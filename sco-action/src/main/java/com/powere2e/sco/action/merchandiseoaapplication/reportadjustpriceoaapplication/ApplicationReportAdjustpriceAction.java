package com.powere2e.sco.action.merchandiseoaapplication.reportadjustpriceoaapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.service.common.MasterDataTypeService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.MerchandiseOaApplicationService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustpriceService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustpriceService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.reportanalysis.AnalysisReportNewService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMService;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.SameMerchandiseAdjustprice;
import com.powere2e.security.utils.PowerUtils;

/**
 * 申请报告(快速调价)的WEB请求响应类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationReportAdjustpriceAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1915300534646641923L;
	private ApplicationReportAdjustpriceService reportAdjustpriceService;// 申请报告的service
	private HistoryPriceAdjustpriceService priceAdjustpriceService;// 价格的service
	private MerchandiseOaApplicationService merchandiseOaApplicationService;// oa申请的service
	private SupplierAttachmentMService supplierAttachmentMService;// 供应商附件、证件的service
	private AnalysisReportNewService analysisReportNewService;// 分析报告的service
	private MasterDataTypeService masterDataTypeService;// 获取主键id的service
	private List<ApplicationReportAdjustprice> applicationList = new ArrayList<ApplicationReportAdjustprice>();// 商品申请报告
	private List<ApplicationLackFileM> lackFileList = new ArrayList<ApplicationLackFileM>();// 缺少建议文件的商品
	private String oldPriceRowsList;
	private String nowPriceRowsList;
	private String sameRowsList;
	private String materialRowsList;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		reportAdjustpriceService = (ApplicationReportAdjustpriceService) ConfigFactory.getInstance().getBean("reportAdjustpriceService");
		priceAdjustpriceService = (HistoryPriceAdjustpriceService) ConfigFactory.getInstance().getBean("priceAdjustpriceService");
		merchandiseOaApplicationService = (MerchandiseOaApplicationService) ConfigFactory.getInstance().getBean("merchandiseOaApplicationService");
		supplierAttachmentMService = (SupplierAttachmentMService) ConfigFactory.getInstance().getBean("supplierAttachmentMService");
		analysisReportNewService = (AnalysisReportNewService) ConfigFactory.getInstance().getBean("analysisReportNewService");
		masterDataTypeService = (MasterDataTypeService) ConfigFactory.getInstance().getBean("masterDataTypeService");
	}

	/**
	 * 显示快速调价页面
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowApplicationReportAdjustpriceGrid() throws Exception {
		this.forwardPage("sco/merchandiseOaApplication/reportAdjustpriceOaApplication/applicationReportAdjustpriceGrid.ftl");
	}

	/**
	 * 申请报告(快速调价)列表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListMerchandiseApplicationAdjustprice() throws Exception {
		Map<String, Object> map = getMerchandise().toMap();
		String applicationDateStart = this.asString("applicationDateStart");
		String applicationDateEnd = this.asString("applicationDateEnd");

		map.put("applicationDateStart", applicationDateStart);
		map.put("applicationDateEnd", applicationDateEnd);

		map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString());// 设置只查询正常调价类型的申请列表
		List<MerchandiseIntention> list = merchandiseOaApplicationService.queryMerchandiseApplicationList(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 添加申请报告(快速调价)界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowInsertApplicationReportAdjustpriceForm() throws Exception {
		// 页面OA传递申请单号
		String applicationCode = "OA".concat(masterDataTypeService.nextID("S_OA_APPLICATION"));
		// 向页面传递所选择的意向品编号和供应商编号组
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");

		applicationList = reportAdjustpriceService.listApplicationReportAdjustprice(applicationCode, intentionAndSupplierCodes);
		this.putObject("applicationCode", applicationCode);
		this.putObject("intentionAndSupplierCodes", intentionAndSupplierCodes);
		this.putObject("applicationList", applicationList);
		this.forwardPage("sco/merchandiseOaApplication/reportAdjustpriceOaApplication/reportAdjustpricePanel.ftl");
	}

	/**
	 * 修改申请报告(快速调价)界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowUpdateApplicationReportAdjustpriceForm() throws Exception {
		String applicationCode = this.asString("applicationCode");
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
		applicationList = reportAdjustpriceService.listApplicationReportAdjustprice(applicationCode, intentionAndSupplierCodes);

		this.putObject("applicationCode", applicationCode);
		this.putObject("intentionAndSupplierCodes", intentionAndSupplierCodes);
		this.putObject("applicationList", applicationList);

		this.forwardPage("sco/merchandiseOaApplication/reportAdjustpriceOaApplication/reportAdjustpricePanel.ftl");
	}

	/**
	 * 添加申请报告(快速调价)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doInsertApplicationReportAdjustprice() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		//先判断是否可以操作
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil
				.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString());// 申请类型
		List<ApplicationMerchandise> appList = merchandiseOaApplicationService.listIntentionOaApplication(map);
		if (appList != null && appList.size() > 0) {
			for (ApplicationMerchandise merchandise : appList) {
				if (!BusinessConstants.MerchandiseApplicationStatus.CG.toString().equals(
						merchandise.getApplicationStatus())
						&& !BusinessConstants.MerchandiseApplicationStatus.BH.toString().equals(
								merchandise.getApplicationStatus())) {
					//如果有一条记录的状态不是"草稿"或"驳回"，就不能点击保存按钮
					this.forwardData(false, null, "选择的数据不是“草稿”或”驳回“的状态,无法修改!");
				}
				/*if(merchandise.getCreateby()!=null&&!PowerUtils.getCurrentUser().getLoginName().equalsIgnoreCase(merchandise.getCreateby())){
					this.forwardData(false, null, "您不是创建人,无法修改!");
					return;
				}*/
			}
		}
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd");
		Gson gson = builder.create();
		if (applicationList != null && applicationList.size() > 0) {
			JSONArray jsonArr=JSONArray.fromObject("["+oldPriceRowsList+"]");
			Object oldPriceObjArr[]=jsonArr.toArray();
			
			jsonArr=JSONArray.fromObject("["+nowPriceRowsList+"]");
			Object nowPriceObjArr[]=jsonArr.toArray();
			
			jsonArr=JSONArray.fromObject("["+sameRowsList+"]");
			Object sameObjArr[]=jsonArr.toArray();
			
			jsonArr=JSONArray.fromObject("["+materialRowsList+"]");
			Object materialObjArr[]=jsonArr.toArray();
			for (int i = 0; i < applicationList.size(); i++) {
				List<ApplicationReportAdjustprice> applicationReportAdjustprice = new ArrayList<ApplicationReportAdjustprice>();
				if (oldPriceRowsList != null) {
					HistoryPriceAdjustprice oldPriceArr[] = gson.fromJson(oldPriceObjArr[i].toString(), HistoryPriceAdjustprice[].class);
					if (oldPriceArr != null) {
						applicationList.get(i).setOldPriceArr(oldPriceArr);
					}
				}
				if (nowPriceRowsList != null) {
					HistoryPriceAdjustprice nowPriceArr[] = gson.fromJson(nowPriceObjArr[i].toString(), HistoryPriceAdjustprice[].class);
					if (nowPriceArr != null) {
						applicationList.get(i).setNowPriceArr(nowPriceArr);
					}

				}
				if (sameRowsList != null) {
					SameMerchandiseAdjustprice sameArr[] = gson.fromJson(sameObjArr[i].toString(), SameMerchandiseAdjustprice[].class);
					if (sameArr != null) {
						applicationList.get(i).setSameArr(sameArr);
					}

				}
				if (materialRowsList != null) {
					MerchandiseMaterial materialArr[] = gson.fromJson(materialObjArr[i].toString(), MerchandiseMaterial[].class);
					if (materialArr != null) {
						applicationList.get(i).setMaterialArr(materialArr);
					}

				}
				applicationReportAdjustprice.add(applicationList.get(i));
				//保存报表为html
				String path = priceAdjustpriceService.saveApplicationReportAdjustpriceToHtml(
						"applicationReportAdjustprice" + applicationCode + "_" + (i + 1), applicationCode,
						applicationReportAdjustprice, intentionAndSupplierCodes);
				applicationList.get(i).setPath(path);
			}
		}
		String receiptInfo = reportAdjustpriceService.insertApplicationReportAdjustprice(applicationList, applicationCode, intentionAndSupplierCodes);
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString().equals(receiptInfo)
				|| MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
			for(ApplicationReportAdjustprice applicationReportAdjustprice:applicationList){
				applicationReportAdjustprice.setPath(applicationReportAdjustprice.getPath().replace(ConfigPath.getUploadFilePath(), ""));
				reportAdjustpriceService.updateApplicationReportAdjustprice(applicationReportAdjustprice);
			}
			// 将新添加的东西查询出来
			applicationList = reportAdjustpriceService.listApplicationReportAdjustprice(applicationCode, intentionAndSupplierCodes);
			this.putObject("applicationList", applicationList);
			this.forwardData(true, applicationList, this.getText("public.success"));
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER.toString().equals(receiptInfo)) {
			this.forwardData(false, null, "选择的数据中包含已经OA申请的数据,不能重复申请!");
		} else {
			this.forwardData(false, null, "选择的数据不是“草稿”或”驳回“的状态,无法修改!");
		}
	}
	/**
	 * 添加申请报告(快速调价)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doInsertApplicationReportAdjustpriceCG() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		//先判断是否可以操作
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil
				.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString());// 申请类型
		List<ApplicationMerchandise> appList = merchandiseOaApplicationService.listIntentionOaApplication(map);
		if (appList != null && appList.size() > 0) {
			for (ApplicationMerchandise merchandise : appList) {
				if (!BusinessConstants.MerchandiseApplicationStatus.CG.toString().equals(
						merchandise.getApplicationStatus())
						&& !BusinessConstants.MerchandiseApplicationStatus.BH.toString().equals(
								merchandise.getApplicationStatus())) {
					//如果有一条记录的状态不是"草稿"或"驳回"，就不能点击保存按钮
					this.forwardData(false, null, "选择的数据不是“草稿”或”驳回“的状态,无法修改!");
				}
			/*	if(merchandise.getCreateby()!=null&&!PowerUtils.getCurrentUser().getLoginName().equalsIgnoreCase(merchandise.getCreateby())){
					this.forwardData(false, null, "您不是创建人,无法修改!");
					return;
				}*/
			}
		}
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd");
		Gson gson = builder.create();
		if (applicationList != null && applicationList.size() > 0) {
			JSONArray jsonArr=JSONArray.fromObject("["+oldPriceRowsList+"]");
			Object oldPriceObjArr[]=jsonArr.toArray();
			
			jsonArr=JSONArray.fromObject("["+nowPriceRowsList+"]");
			Object nowPriceObjArr[]=jsonArr.toArray();
			
			jsonArr=JSONArray.fromObject("["+sameRowsList+"]");
			Object sameObjArr[]=jsonArr.toArray();
			
			jsonArr=JSONArray.fromObject("["+materialRowsList+"]");
			Object materialObjArr[]=jsonArr.toArray();
			for (int i = 0; i < applicationList.size(); i++) {
				try {
					List<ApplicationReportAdjustprice> applicationReportAdjustprice = new ArrayList<ApplicationReportAdjustprice>();
					if (oldPriceRowsList != null) {
						HistoryPriceAdjustprice oldPriceArr[] = gson.fromJson(oldPriceObjArr[i].toString(), HistoryPriceAdjustprice[].class);
						if (oldPriceArr != null) {
							applicationList.get(i).setOldPriceArr(oldPriceArr);
						}
					}
					if (nowPriceRowsList != null) {
						HistoryPriceAdjustprice nowPriceArr[] = gson.fromJson(nowPriceObjArr[i].toString(), HistoryPriceAdjustprice[].class);
						if (nowPriceArr != null) {
							applicationList.get(i).setNowPriceArr(nowPriceArr);
						}

					}
					if (sameRowsList != null) {
						SameMerchandiseAdjustprice sameArr[] = gson.fromJson(sameObjArr[i].toString(), SameMerchandiseAdjustprice[].class);
						if (sameArr != null) {
							applicationList.get(i).setSameArr(sameArr);
						}

					}
					if (materialRowsList != null) {
						MerchandiseMaterial materialArr[] = gson.fromJson(materialObjArr[i].toString(), MerchandiseMaterial[].class);
						if (materialArr != null) {
							applicationList.get(i).setMaterialArr(materialArr);
						}

					}
					applicationReportAdjustprice.add(applicationList.get(i));
					//保存报表为html
					/*String path = priceAdjustpriceService.saveApplicationReportAdjustpriceToHtml(
							"applicationReportAdjustprice" + applicationCode + "_" + (i + 1), applicationCode,
							applicationReportAdjustprice, intentionAndSupplierCodes);
					applicationList.get(i).setPath(path);*/
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String receiptInfo = reportAdjustpriceService.insertApplicationReportAdjustprice(applicationList, applicationCode, intentionAndSupplierCodes);
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString().equals(receiptInfo)
				|| MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
			for(ApplicationReportAdjustprice applicationReportAdjustprice:applicationList){
			//	applicationReportAdjustprice.setPath(applicationReportAdjustprice.getPath().replace(ConfigPath.getUploadFilePath(), ""));
				reportAdjustpriceService.updateApplicationReportAdjustprice(applicationReportAdjustprice);
			}
			// 将新添加的东西查询出来
			applicationList = reportAdjustpriceService.listApplicationReportAdjustprice(applicationCode, intentionAndSupplierCodes);
			this.putObject("applicationList", applicationList);
			this.forwardData(true, applicationList, this.getText("public.success"));
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER.toString().equals(receiptInfo)) {
			this.forwardData(false, null, "选择的数据中包含已经OA申请的数据,不能重复申请!");
		} else {
			this.forwardData(false, null, "选择的数据不是“草稿”或”驳回“的状态,无法修改!");
		}
	}
	/**
	 * 删除申请报告(快速调价)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doDeleteApplicationReportAdjustprice() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCodes", this.asString("applicationCodes"));
		reportAdjustpriceService.deleteAllReportAdjustpriceByApplicationCode(map);

		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 导出列表页申请记录
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doExportReportAdjustpriceApplicationToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = ("商品正常调价OA申请列表_").concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		
		Map<String, Object> map = getMerchandise().toMap();
		String applicationDateStart = this.asString("applicationDateStart");
		String applicationDateEnd = this.asString("applicationDateEnd");

		map.put("applicationDateStart", applicationDateStart);
		map.put("applicationDateEnd", applicationDateEnd);

		map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString());// 设置只查询正常调价类型的申请列表
		reportAdjustpriceService.exportSignedQtyExcel(map, out);
		/*List<MerchandiseIntention> list = merchandiseOaApplicationService.queryMerchandiseApplicationList(map, null);
		this.putObject("applicationList", list);
		this.forwardDownload("excel/sco/merchandiseoaapplication/reportAdjustpriceOaApplicationTemplate.xlsx", ExcelUtils.getEncodeFileName("商品正常调价OA申请列表_" + DateUtils.formateDateTime() + ".xlsx"));*/
	}

	/**
	 * 历史与本次价格(正常调价)
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListHistoryPriceAdjustprice() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("piceType", this.asString("piceType"));
		map.put("intentionCode", this.asString("intentionCode"));
		map.put("supplierCode", this.asString("supplierCode"));
		map.put("applicationCode", this.asString("applicationCode"));
		List<HistoryPriceAdjustprice> list = priceAdjustpriceService.listHistoryPriceAdjustprice(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 本次价格比历史价格高(正常调价)
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListComparePriceAdjustprice() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("intentionCode", this.asString("intentionCode"));
		map.put("supplierCode", this.asString("supplierCode"));
		map.put("applicationCode", this.asString("applicationCode"));
		List<HistoryPriceAdjustprice> list = priceAdjustpriceService.listComparePriceAdjustprice(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 同类商品市场零售价(正常调价)
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListSameMerchandiseAdjustprice() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("intentionCode", this.asString("intentionCode"));
		map.put("supplierCode", this.asString("supplierCode"));
		map.put("applicationCode", this.asString("applicationCode"));
		List<SameMerchandiseAdjustprice> list = priceAdjustpriceService.listSameMerchandiseAdjustprice(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 同商品原料情况(调价)查询
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListMerchandiseMaterialAdjustprice() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("intentionCode", this.asString("intentionCode"));
		map.put("supplierCode", this.asString("supplierCode"));
		map.put("applicationCode", this.asString("applicationCode"));
		List<MerchandiseMaterial> list = priceAdjustpriceService.listMerchandiseMaterial(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 */
	private Merchandise getMerchandise() throws Exception {
		Merchandise merchandise = new Merchandise();
		this.asBean(merchandise);
		return merchandise;
	}

	/**
	 * 检查申请单是否存在必填文件和建议文件
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doSyncApplicationAdjustprice() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String applicationCode = this.asString("applicationCode");
		map.put("applicationCode", applicationCode);

		// 判断是否缺少必填文件
		List<ApplicationLackFileM> list = reportAdjustpriceService.queryNotHaveReportMerchandiseAdjustprice(map);
		if (list != null && list.size() > 0) {
			// 缺少必填文件，就不同步OA申请单
			StringBuffer check = new StringBuffer();
			for (ApplicationLackFileM reportNew : list) {
				check.append("申请单号:" + reportNew.getApplicationCode() + ",商品编号:" + reportNew.getMerchandiseCode() + ",供应商编号:" + reportNew.getSupplierCode() + "<br/>");
			}
			this.forwardData(false, check.toString(), "缺少必填文件");
		} else {
			List<ApplicationLackFileM> attachmentList = supplierAttachmentMService.listAttachmentLackInfo(applicationCode,BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString());// 存在必填文件，判断是否缺少附件、证件
			List<ApplicationLackFileM> analysisReportList = analysisReportNewService.listNoLinkMCAReportApplication(applicationCode);// 判断是否缺少分析报告
			if ((attachmentList == null || attachmentList.size() == 0) && (analysisReportList == null || analysisReportList.size() == 0)) {
//				map.put("applicationStatus", BusinessConstants.ApplicationStatus.YX.toString());
//				merchandiseOaApplicationService.updateOaApplicationStatus(map);

				this.forwardData(true, null, this.getText("public.success"));
			} else {
				this.forwardData(false, null, "缺少建议文件");
			}

		}
	}

	/**
	 * 查询缺少的建议稳健
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowLackSuggestFileAdjustprice() throws Exception {
		String applicationCode = this.asString("applicationCode");
		List<ApplicationLackFileM> attachmentList = supplierAttachmentMService.listAttachmentLackInfo(applicationCode,BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString());// 查询缺少的证件、附件
		List<ApplicationLackFileM> analysisReportList = analysisReportNewService.listNoLinkMCAReportApplication(applicationCode);// 查询缺少的分析报告
		lackFileList.addAll(attachmentList);
		lackFileList.addAll(analysisReportList);

		this.putObject("applicationCode", applicationCode);
		this.putObject("lackFileList", lackFileList);
		this.forwardPage("sco/merchandiseOaApplication/reportAdjustpriceOaApplication/lackSuggestFileAdjustpriceGrid.ftl");
	}

	/**
	 * 插入没有建议文件的商品信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doInsertApplicationLackFileAdjustprice() throws Exception {
		if (lackFileList != null && lackFileList.size() > 0) {
			String applicationCode = this.asString("applicationCode");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", lackFileList);
			map.put("applicationCode", applicationCode);

			merchandiseOaApplicationService.insertApplicationLackFileM(map);
			this.forwardData(true, null, this.getText("public.success"));
		} else {
			this.forwardData(false, null, "保存缺失文件说明失败!");
		}
	}

	/**
	 * 撤销运行OA同步
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doUndoApplicationAdjustprice() throws Exception {
		String applicationCodes = this.asString("applicationCodes");
		merchandiseOaApplicationService.completeUndoOaApplicationStatus(applicationCodes);

		this.forwardData(true, null, this.getText("public.success"));
	}

	public List<ApplicationLackFileM> getLackFileList() {
		return lackFileList;
	}

	public void setLackFileList(List<ApplicationLackFileM> lackFileList) {
		this.lackFileList = lackFileList;
	}

	public List<ApplicationReportAdjustprice> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<ApplicationReportAdjustprice> applicationList) {
		this.applicationList = applicationList;
	}

	public String getOldPriceRowsList() {
		return oldPriceRowsList;
	}

	public void setOldPriceRowsList(String oldPriceRowsList) {
		this.oldPriceRowsList = oldPriceRowsList;
	}

	public String getNowPriceRowsList() {
		return nowPriceRowsList;
	}

	public void setNowPriceRowsList(String nowPriceRowsList) {
		this.nowPriceRowsList = nowPriceRowsList;
	}

	public String getSameRowsList() {
		return sameRowsList;
	}

	public void setSameRowsList(String sameRowsList) {
		this.sameRowsList = sameRowsList;
	}

	public String getMaterialRowsList() {
		return materialRowsList;
	}

	public void setMaterialRowsList(String materialRowsList) {
		this.materialRowsList = materialRowsList;
	}

}
