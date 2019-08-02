package com.powere2e.sco.action.merchandiseoaapplication.reportnewoaapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.google.gson.Gson;
import com.lowagie.text.pdf.PushbuttonField;
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
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNewService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.reportanalysis.AnalysisReportNewService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNewService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.MerchandiseOaApplication;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SameMerchandiseNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SellAnticipatedNew;
import com.powere2e.sco.service.impl.merchandiseintention.MerchandiseQuotedServiceImpl;
import com.powere2e.security.utils.PowerUtils;

import net.sf.json.JSONArray;

/**
 * 申请报告(新品引进)的WEB请求响应类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationReportNewAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4779328921886632598L;
	private ApplicationReportNewService reportNewService;// 申请报告的service
	private MerchandisePriceNewService priceNewService;// 价格的service
	private MerchandiseOaApplicationService merchandiseOaApplicationService;// oa申请的service
	private SupplierAttachmentMService supplierAttachmentMService;// 供应商附件、证件的service
	private AnalysisReportNewService analysisReportNewService;// 分析报告的service
	private MasterDataTypeService masterDataTypeService;// 获取主键id的service
	private List<ApplicationReportNew> applicationList = new ArrayList<ApplicationReportNew>();// 商品申请报告
	private List<ApplicationLackFileM> lackFileList = new ArrayList<ApplicationLackFileM>();// 缺少建议文件的商品
	private String priceRowsList;
	private String sameRowsList;
	private String sellRowsList;
	private String materialRowsList;
	
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		reportNewService = (ApplicationReportNewService) ConfigFactory.getInstance().getBean("reportNewService");
		priceNewService = (MerchandisePriceNewService) ConfigFactory.getInstance().getBean("priceNewService");
		merchandiseOaApplicationService = (MerchandiseOaApplicationService) ConfigFactory.getInstance().getBean(
				"merchandiseOaApplicationService");
		supplierAttachmentMService = (SupplierAttachmentMService) ConfigFactory.getInstance().getBean(
				"supplierAttachmentMService");
		analysisReportNewService = (AnalysisReportNewService) ConfigFactory.getInstance().getBean(
				"analysisReportNewService");
		masterDataTypeService = (MasterDataTypeService) ConfigFactory.getInstance().getBean("masterDataTypeService");
	}

	/**
	 * 显示新品引进OA申请页面
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowApplicationReportNewGrid() throws Exception {
		this.forwardPage("sco/merchandiseOaApplication/reportNewOaApplication/applicationReportNewGrid.ftl");
	}

	/**
	 * 新品引进OA申请意向品列表
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListMerchandiseIntentionApplication() throws Exception {
		Map<String, Object> map = getMerchandiseIntention().toMap();
		String createDateStart = this.asString("createDateStart");
		String createDateEnd = this.asString("createDateEnd");
		String applicationDateStart = this.asString("applicationDateStart");
		String applicationDateEnd = this.asString("applicationDateEnd");
		// 巡查申请单提交日期
		String visitDateStart = this.asString("visitDateStart");
		String visitDateEnd = this.asString("visitDateEnd");
		// 包装设计申请提交日期
		String packageDateStart = this.asString("packageDateStart");
		String packageDateEnd = this.asString("packageDateEnd");

		map.put("createDateStart", createDateStart);
		map.put("createDateEnd", createDateEnd);
		map.put("applicationDateStart", applicationDateStart);
		map.put("applicationDateEnd", applicationDateEnd);

		map.put("visitDateStart", visitDateStart);
		map.put("visitDateEnd", visitDateEnd);
		map.put("packageDateStart", packageDateStart);
		map.put("packageDateEnd", packageDateEnd);

		map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_NEW.toString());// 设置只查询新品引进类型的申请列表
		//查询"新品引进"时，该记录要求没有做"新品快速引进"
		map.put("notExistsApplicationType", BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT.toString());
		List<MerchandiseIntention> list = merchandiseOaApplicationService.queryIntentionApplicationList(map,
				this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 添加申请报告(新品引进)界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowInsertApplicationReportNewForm() throws Exception {
		ApplicationReportNew applicationReportNew = new ApplicationReportNew();
		// 向页面传递OA申请单号
		String applicationCode = "OA".concat(masterDataTypeService.nextID("S_OA_APPLICATION"));
		// 向页面传递所选择的意向品编号和供应商编号组
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		applicationReportNew.setApplicationCode(applicationCode);

		applicationList = reportNewService.listApplicationReportNew(applicationCode, intentionAndSupplierCodes);
		this.putObject("applicationReportNew", applicationReportNew);
		this.putObject("applicationCode", applicationCode);
		this.putObject("intentionAndSupplierCodes", intentionAndSupplierCodes);
		this.putObject("applicationList", applicationList);
		this.forwardPage("sco/merchandiseOaApplication/reportNewOaApplication/reportNewPanel.ftl");
	}

	/**
	 * 修改申请报告(新品引进)界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowUpdateApplicationReportNewForm() throws Exception {
		String applicationCode = this.asString("applicationCode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);

		String intentionAndSupplierCodes = "";
		List<ApplicationMerchandise> codeList = merchandiseOaApplicationService
				.findMerchandiseCodeAndSupplierCodeByApplicationCode(map);
		if (codeList != null && codeList.size() > 0) {
			for (int i = 0; i < codeList.size(); i++) {
				ApplicationMerchandise eachMerchandise = codeList.get(i);
				if (i < codeList.size() - 1) {
					intentionAndSupplierCodes += eachMerchandise.getMerchandiseCode() + ":"
							+ eachMerchandise.getSupplierCode() + ",";
				} else if (i == codeList.size() - 1) {
					intentionAndSupplierCodes += eachMerchandise.getMerchandiseCode() + ":"
							+ eachMerchandise.getSupplierCode();
				}
			}
		}

		applicationList = reportNewService.listApplicationReportNew(applicationCode, intentionAndSupplierCodes);
		// this.putObject("applicationReportNew", applicationReportNew);
		this.putObject("applicationCode", applicationCode);
		this.putObject("intentionAndSupplierCodes", intentionAndSupplierCodes);
		this.putObject("applicationList", applicationList);
		this.forwardPage("sco/merchandiseOaApplication/reportNewOaApplication/reportNewPanel.ftl");
	}

	/**
	 * 添加申请报告(新品引进)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doInsertApplicationReportNew() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil
				.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_NEW.toString());// 申请类型
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
				if(merchandise.getCreateby()!=null&&!PowerUtils.getCurrentUser().getLoginName().equalsIgnoreCase(merchandise.getCreateby())){
					this.forwardData(false, null, "您不是创建人,无法修改!");
					return;
				}
			}
		}
		Gson gson = new Gson();
		if (applicationList != null && applicationList.size() > 0) {
			JSONArray jsonArr = JSONArray.fromObject("[" + priceRowsList + "]");
			Object priceObjArr[] = jsonArr.toArray();

			jsonArr = JSONArray.fromObject("[" + sameRowsList + "]");
			Object sameObjArr[] = jsonArr.toArray();

			jsonArr = JSONArray.fromObject("[" + sellRowsList + "]");
			Object sellObjArr[] = jsonArr.toArray();
			
			jsonArr=JSONArray.fromObject("["+materialRowsList+"]");
			Object materialObjArr[]=jsonArr.toArray();
			
			
			for (int i = 0; i < applicationList.size(); i++) {
				List<ApplicationReportNew> applicationListNew = new ArrayList<ApplicationReportNew>();
				if (priceRowsList != null) {
					MerchandisePriceNew priceArr[] = gson.fromJson(priceObjArr[i].toString(),
							MerchandisePriceNew[].class);
					if (priceArr != null) {
						applicationList.get(i).setPriceArr(priceArr);
					}
				}
				if (sameRowsList != null) {
					SameMerchandiseNew sameArr[] = gson.fromJson(sameObjArr[i].toString(), SameMerchandiseNew[].class);
					if (sameArr != null) {
						applicationList.get(i).setSameArr(sameArr);
					}
				}
				if (sellRowsList != null) {
					SellAnticipatedNew sellArr[] = gson.fromJson(sellObjArr[i].toString(), SellAnticipatedNew[].class);
					if (sellArr != null) {
						applicationList.get(i).setSellArr(sellArr);
					}
				}
				
				if (materialRowsList != null) {
					MerchandiseMaterial materialArr[] = gson.fromJson(materialObjArr[i].toString(), MerchandiseMaterial[].class);
					if (materialArr != null) {
						applicationList.get(i).setMaterialArr(materialArr);
					}

				}
				applicationListNew.add(applicationList.get(i));
				//保存报表为html
				String path =priceNewService.saveApplicationReportNewToHtml("applicationReportNew_"+applicationCode+"_"+(i+1), applicationListNew);
				applicationList.get(i).setPath(path);
			}
		}
		String receiptInfo = reportNewService.insertApplicationReportNew(applicationList, applicationCode,
				intentionAndSupplierCodes);
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString().equals(receiptInfo)
				|| MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
			
			for(ApplicationReportNew applicationReportNew:applicationList){
				applicationReportNew.setPath(applicationReportNew.getPath().replace(ConfigPath.getUploadFilePath(), ""));
				reportNewService.updateApplicationReportNew(applicationReportNew);
			}
			// 将新添加的东西查询出来
			applicationList = reportNewService.listApplicationReportNew(applicationCode, intentionAndSupplierCodes);
			this.putObject("applicationList", applicationList);
			this.forwardData(true, null, this.getText("public.success"));
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER.toString().equals(receiptInfo)) {
			this.forwardData(false, null, "选择的数据中包含已经OA申请的数据,不能重复申请!");
		} else {
			this.forwardData(false, null, "选择的数据不是“草稿”或”驳回“的状态,无法修改！");
		}
	}
	
	/**
	 * 添加申请报告(新品引进)草稿
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doInsertApplicationReportNewCG() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil
				.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_NEW.toString());// 申请类型
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
				if(merchandise.getCreateby()!=null&&!PowerUtils.getCurrentUser().getLoginName().equalsIgnoreCase(merchandise.getCreateby())){
					this.forwardData(false, null, "您不是创建人,无法修改!");
					return;
				}
			}
		}
		Gson gson = new Gson();
		if (applicationList != null && applicationList.size() > 0) {
			JSONArray jsonArr = JSONArray.fromObject("[" + priceRowsList + "]");
			Object priceObjArr[] = jsonArr.toArray();

			jsonArr = JSONArray.fromObject("[" + sameRowsList + "]");
			Object sameObjArr[] = jsonArr.toArray();

			jsonArr = JSONArray.fromObject("[" + sellRowsList + "]");
			Object sellObjArr[] = jsonArr.toArray();
			
			jsonArr=JSONArray.fromObject("["+materialRowsList+"]");
			Object materialObjArr[]=jsonArr.toArray();
			
			
			for (int i = 0; i < applicationList.size(); i++) {
				try {
					List<ApplicationReportNew> applicationListNew = new ArrayList<ApplicationReportNew>();
					if (priceRowsList != null) {
						MerchandisePriceNew priceArr[] = gson.fromJson(priceObjArr[i].toString(),
								MerchandisePriceNew[].class);
						if (priceArr != null) {
							applicationList.get(i).setPriceArr(priceArr);
						}
					}
					if (sameRowsList != null) {
						SameMerchandiseNew sameArr[] = gson.fromJson(sameObjArr[i].toString(), SameMerchandiseNew[].class);
						if (sameArr != null) {
							applicationList.get(i).setSameArr(sameArr);
						}
					}
					if (sellRowsList != null) {
						SellAnticipatedNew sellArr[] = gson.fromJson(sellObjArr[i].toString(), SellAnticipatedNew[].class);
						if (sellArr != null) {
							applicationList.get(i).setSellArr(sellArr);
						}
					}
					
					if (materialRowsList != null) {
						MerchandiseMaterial materialArr[] = gson.fromJson(materialObjArr[i].toString(), MerchandiseMaterial[].class);
						if (materialArr != null) {
							applicationList.get(i).setMaterialArr(materialArr);
						}

					}
					applicationListNew.add(applicationList.get(i));
					//保存报表为html
					/*String path =priceNewService.saveApplicationReportNewToHtml("applicationReportNew_"+applicationCode+"_"+(i+1), applicationListNew);
					applicationList.get(i).setPath(path);*/
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String receiptInfo = reportNewService.insertApplicationReportNew(applicationList, applicationCode,
				intentionAndSupplierCodes);
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString().equals(receiptInfo)
				|| MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
			
			for(ApplicationReportNew applicationReportNew:applicationList){
			//	applicationReportNew.setPath(applicationReportNew.getPath().replace(ConfigPath.getUploadFilePath(), ""));
				reportNewService.updateApplicationReportNew(applicationReportNew);
			}
			// 将新添加的东西查询出来
			applicationList = reportNewService.listApplicationReportNew(applicationCode, intentionAndSupplierCodes);
			this.putObject("applicationList", applicationList);
			this.forwardData(true, null, this.getText("public.success"));
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER.toString().equals(receiptInfo)) {
			this.forwardData(false, null, "选择的数据中包含已经OA申请的数据,不能重复申请!");
		} else {
			this.forwardData(false, null, "选择的数据不是“草稿”或”驳回“的状态,无法修改！");
		}
	}

	/**
	 * 商品价格(新品引进)查询
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListMerchandisePriceNew() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("intentionCode", this.asString("intentionCode"));
		map.put("supplierCode", this.asString("supplierCode"));
		map.put("applicationCode", this.asString("applicationCode"));
		List<MerchandisePriceNew> list = priceNewService.listMerchandisePriceNew(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 同商品原料情况(调价)查询
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListMerchandiseMaterialPriceNew() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("intentionCode", this.asString("intentionCode"));
		map.put("supplierCode", this.asString("supplierCode"));
		map.put("applicationCode", this.asString("applicationCode"));
		List<MerchandiseMaterial> list = priceNewService.listMerchandiseMaterial(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 同类商品(新品引进)查询
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListSameMerchandiseNew() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("intentionCode", this.asString("intentionCode"));
		map.put("supplierCode", this.asString("supplierCode"));
		map.put("applicationCode", this.asString("applicationCode"));
		List<SameMerchandiseNew> list = priceNewService.listSameMerchandiseNew(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 销售预计(新品引进)查询
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListSellAnticipatedNew() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("intentionCode", this.asString("intentionCode"));
		map.put("supplierCode", this.asString("supplierCode"));
		map.put("applicationCode", this.asString("applicationCode"));
		List<SellAnticipatedNew> list = priceNewService.listSellAnticipatedNew(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 删除申请报告(新品引进)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doDeleteApplicationReportNew() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCodes", this.asString("applicationCodes"));
		reportNewService.completeDeleteReportNewByApplicationCode(map);

		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 */
	private MerchandiseIntention getMerchandiseIntention() throws Exception {
		MerchandiseIntention merchandiseIntention = new MerchandiseIntention();
		this.asBean(merchandiseIntention);
		return merchandiseIntention;
	}

	/**
	 * 导出列表页申请记录
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doExportReportNewApplicationToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = ("商品新品引进OA申请列表_").concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		
		Map<String, Object> map = getMerchandiseIntention().toMap();
		String createDateStart = this.asString("createDateStart");
		String createDateEnd = this.asString("createDateEnd");
		String applicationDateStart = this.asString("applicationDateStart");
		String applicationDateEnd = this.asString("applicationDateEnd");

		// 巡查申请单提交日期
		String visitDateStart = this.asString("visitDateStart");
		String visitDateEnd = this.asString("visitDateEnd");
		// 包装设计申请提交日期
		String packageDateStart = this.asString("packageDateStart");
		String packageDateEnd = this.asString("packageDateEnd");

		map.put("createDateStart", createDateStart);
		map.put("createDateEnd", createDateEnd);
		map.put("applicationDateStart", applicationDateStart);
		map.put("applicationDateEnd", applicationDateEnd);

		map.put("visitDateStart", visitDateStart);
		map.put("visitDateEnd", visitDateEnd);
		map.put("packageDateStart", packageDateStart);
		map.put("packageDateEnd", packageDateEnd);

		map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_NEW.toString());// 设置只查询新品引进类型的申请列表
		map.put("exportNew", this.asString("exportNew"));//新品引进导出数据时:该OA申请单创建人
		
		//查询"新品引进"时，该记录要求没有做"新品快速引进"
		map.put("notExistsApplicationType", BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT.toString());
		reportNewService.exportSignedQtyExcel(map, out);
		/*merchandiseOaApplicationService.queryIntentionApplicationList(map, null);
		this.putObject("applicationList", list);
		this.forwardDownload("excel/sco/merchandiseoaapplication/reportNewOaApplicationTemplate.xlsx",
				ExcelUtils.getEncodeFileName("商品新品引进OA申请列表_" + DateUtils.formateDateTime() + ".xlsx"));
*/
	}

	/**
	 * 检查申请单是否存在必填文件和建议文件
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doSyncApplicationNew() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String applicationCode = this.asString("applicationCode");
		map.put("applicationCode", applicationCode);

		// 判断是否缺少必填文件
		List<ApplicationLackFileM> list = reportNewService.queryNotHaveReportMerchandiseNew(map);
		if (list != null && list.size() > 0) {
			// 缺少必填文件，就不同步OA申请单
			StringBuffer check = new StringBuffer();
			for (ApplicationLackFileM reportNew : list) {
				check.append("申请单号:" + reportNew.getApplicationCode() + ",商品编号:" + reportNew.getMerchandiseCode()
						+ ",供应商编号:" + reportNew.getSupplierCode() + "<br/>");
			}
			this.forwardData(false, check.toString(), "缺少必填文件");
		} else {
			List<ApplicationLackFileM> attachmentList = supplierAttachmentMService.listAttachmentLackInfo(
					applicationCode, BusinessConstants.ApplicationType.MERCHANDISE_NEW.toString());// 存在必填文件，判断是否缺少附件、证件
			List<ApplicationLackFileM> analysisReportList = analysisReportNewService
					.listNoLinkMCAReportApplication(applicationCode);// 判断是否缺少分析报告
			if ((attachmentList == null || attachmentList.size() == 0)
					&& (analysisReportList == null || analysisReportList.size() == 0)) {
				// 不需要修改商品OA申请状态
				// map.put("applicationStatus",
				// BusinessConstants.ApplicationStatus.YX.toString());
				// merchandiseOaApplicationService.updateOaApplicationStatus(map);

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
	public void doShowLackSuggestFileNew() throws Exception {
		String applicationCode = this.asString("applicationCode");
		List<ApplicationLackFileM> attachmentList = supplierAttachmentMService.listAttachmentLackInfo(applicationCode,
				BusinessConstants.ApplicationType.MERCHANDISE_NEW.toString()); // 查询缺少的证件、附件
		List<ApplicationLackFileM> analysisReportList = analysisReportNewService
				.listNoLinkMCAReportApplication(applicationCode);// 查询缺少的分析报告

		lackFileList.addAll(attachmentList);
		lackFileList.addAll(analysisReportList);

		this.putObject("applicationCode", applicationCode);
		this.putObject("lackFileList", lackFileList);
		this.forwardPage("sco/merchandiseOaApplication/reportNewOaApplication/lackSuggestFileNewGrid.ftl");
	}

	/**
	 * 插入没有建议文件的商品信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doInsertApplicationLackFileNew() throws Exception {
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
	 * 撤销允许OA同步
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doUndoApplicationNew() throws Exception {
		String applicationCodes = this.asString("applicationCodes");
		merchandiseOaApplicationService.completeUndoOaApplicationStatus(applicationCodes);

		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 查看OA系统的申请单号和联系人
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doFindApproveOpinion() throws Exception {
		String applicationCode = this.asString("applicationCode");
		MerchandiseOaApplication oaApplication = merchandiseOaApplicationService.findApproveOpinion(applicationCode);
		if (oaApplication != null) {
			this.forwardData(true, oaApplication.getOaApplicationCode() + ":" + oaApplication.getOaContacts(),
					this.getText("public.success"));
		} else {
			this.forwardData(false, null, "没有OA系统同步数据，不能查看OA审批意见!");
		}
	}

	/**
	 * 新增创建TBPM巡厂申请功能
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doCreateTbpmVisit() throws Exception {
		String codes = this.asString("codes");
		String applicationVfCode = masterDataTypeService.nextID("S_APPLICATION_VISIT_FACTORY");
		// 新增商品巡厂申请单
		merchandiseOaApplicationService.insertApplicationVisitFactory(applicationVfCode,codes);
		this.forwardData(true, null, applicationVfCode);//将新建的巡厂申请单号传递到页面，然后跳转到OA系统
	}

	/**
	 * 新增创建TBPM包装设计功能
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doCreateTbpmPackage() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String merchandiseCode = this.asString("merchandiseCode");
		String supplierCode = this.asString("supplierCode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		//包装进度申请状态由接口那边传输过来
//		map.put("applicationStatus", BusinessConstants.VisitApplicationStatus.SPZ.toString());
		map.put("merchandiseCode", merchandiseCode);
		map.put("supplierCode", supplierCode);

		String applicationPdCode = masterDataTypeService.nextID("S_APPLICATION_PACKAGE_DESIGN");
		map.put("applicationPdCode", applicationPdCode);// 获取主键
		// 商品包装设计申请单
		merchandiseOaApplicationService.insertApplicationPackageDesign(map);
		MerchandiseQuoted mq = new MerchandiseQuoted(); 
		map.put("intentionSupplierCode", supplierCode);
		MerchandiseQuotedServiceImpl.getMerchandiseQuotedServiceInstance()
			.searchCompanyInfoInSapAndLastQuoted(supplierCode, mq, map);
		this.forwardData(true, mq, applicationPdCode);
	}

	public List<ApplicationReportNew> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<ApplicationReportNew> applicationList) {
		this.applicationList = applicationList;
	}

	public List<ApplicationLackFileM> getLackFileList() {
		return lackFileList;
	}

	public void setLackFileList(List<ApplicationLackFileM> lackFileList) {
		this.lackFileList = lackFileList;
	}

	public String getPriceRowsList() {
		return priceRowsList;
	}

	public void setPriceRowsList(String priceRowsList) {
		this.priceRowsList = priceRowsList;
	}

	public String getSameRowsList() {
		return sameRowsList;
	}

	public void setSameRowsList(String sameRowsList) {
		this.sameRowsList = sameRowsList;
	}

	public String getSellRowsList() {
		return sellRowsList;
	}

	public void setSellRowsList(String sellRowsList) {
		this.sellRowsList = sellRowsList;
	}

	public String getMaterialRowsList() {
		return materialRowsList;
	}

	public void setMaterialRowsList(String materialRowsList) {
		this.materialRowsList = materialRowsList;
	}

}
