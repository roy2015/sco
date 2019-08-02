package com.powere2e.sco.action.accessoryoaapplication;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.server.sequence.SequenceFactory;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryQuotedCountService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.ApplicationQuotedService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.ApplicationReportAccessoryService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.CommitteeApplyService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.DhInfoService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.OaApplicationService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.SubscribeAccessoryService;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.WlInfoService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryQuotedCount;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationQuoted;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationReportAccessory;
import com.powere2e.sco.model.accessoryoaapplication.CommitteeApply;
import com.powere2e.sco.model.accessoryoaapplication.DhInfo;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;
import com.powere2e.sco.model.accessoryoaapplication.SubscribeAccessory;
import com.powere2e.sco.model.accessoryoaapplication.WlInfo;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryAccessoryServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryElseServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryMaterialServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryPackingServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryTechnologyServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.ApplicationReportAccessoryServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.OaApplicationServiceImpl;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;
import com.powere2e.security.utils.PowerUtils;

/**
 * 辅料意向品的WEB请求响应类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月16日
 */

/**
 * 辅料 —竞价委员会申请
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月16日
 */
public class CommitteeApplyAction extends UploadUtils {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 7467505029752175810L;

	private CommitteeApplyService committeeApplyService;
	private AccessoryEnquiryQuotedCountService accessoryEnquiryQuotedCountService;
	private ApplicationQuotedService applicationQuotedService;
	private ApplicationReportAccessoryService applicationReportAccessoryService;
	private DhInfoService dhInfoService;
	private WlInfoService wlInfoService;
	private SubscribeAccessoryService subscribeAccessoryService;
	private OaApplicationService oaApplicationService;// 辅料OA申请的service
	private List<SubscribeAccessory> subscribeAccessoryTextList = new ArrayList<SubscribeAccessory>();

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		committeeApplyService = (CommitteeApplyService) ConfigFactory.getInstance().getBean("committeeApplyService");
		applicationQuotedService = (ApplicationQuotedService) ConfigFactory.getInstance().getBean("applicationQuotedService");
		applicationReportAccessoryService = (ApplicationReportAccessoryService) ConfigFactory.getInstance().getBean("applicationReportAccessoryService");
		subscribeAccessoryService = (SubscribeAccessoryService) ConfigFactory.getInstance().getBean("subscribeAccessoryService");
		dhInfoService = (DhInfoService) ConfigFactory.getInstance().getBean("dhInfoService");
		wlInfoService = (WlInfoService) ConfigFactory.getInstance().getBean("wlInfoService");
		accessoryEnquiryQuotedCountService = (AccessoryEnquiryQuotedCountService) ConfigFactory.getInstance().getBean("accessoryEnquiryQuotedCountService");
		oaApplicationService = (OaApplicationService) ConfigFactory.getInstance().getBean("oaApplicationService");
	}

	/**
	 * 商品意向品列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doShowCommitteeApplyGrid() throws Exception {
		this.forwardPage("sco/accessoryOaApplication/committeeApply/committeeApplyGrid.ftl");
	}

	/**
	 * 新增竞价委员会申请页面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doAddCommitteeApply() throws Exception {
		List<SubscribeAccessory> subscribeAccessoryList = new ArrayList<SubscribeAccessory>();
		// List<WlInfo> wlInfoList = new ArrayList<WlInfo>();
		ApplicationReportAccessory applicationReportAccessory = new ApplicationReportAccessory();
		String applicationCodeNow = this.asString("applicationCode");
		String quotedCodes = this.asString("quotedCodes");
		if (applicationCodeNow == null || "null".equals(applicationCodeNow)) {
			// 页面OA传递申请单号
			applicationCodeNow = this.getOaApplicationPrimaryKey();
			// 向页面传递所选择的意向品编号和供应商编号组
			String qcs[] = quotedCodes.split(",");
			for (int i = 0; i < qcs.length; i++) {
				CommitteeApply committeeApply = new CommitteeApply();
				SubscribeAccessory subscribeAccessory = new SubscribeAccessory();
				committeeApply = committeeApplyService.loadCommitteeApplyIntentionApplication(qcs[i]);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("enquiryCode", committeeApply.getEnquiryCode());
				List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList = accessoryEnquiryQuotedCountService.listAccessoryEnquiryQuotedCount(map, null);
				subscribeAccessory.setIntentionCode(committeeApply.getIntentionCode());
				subscribeAccessory.setAccessoryEnquiryQuotedCountList(accessoryEnquiryQuotedCountList);
				subscribeAccessory.setAccessoryName(committeeApply.getIntentionName());
				subscribeAccessory.setEnquiryCode(committeeApply.getEnquiryCode());
				subscribeAccessory.setAccessoryEnquiryQuotedCountList(accessoryEnquiryQuotedCountList);
				subscribeAccessory.setAccessoryEnquiryPackingList(AccessoryEnquiryPackingServiceImpl.getInstance().listAccessoryEnquiryPacking(map, null));
				subscribeAccessory.setAccessoryEnquiryAccessoryList(AccessoryEnquiryAccessoryServiceImpl.getInstance().listAccessoryEnquiryAccessory(map, null));
				subscribeAccessory.setAccessoryEnquiryMaterialList(AccessoryEnquiryMaterialServiceImpl.getInstance().listAccessoryEnquiryMaterial(map, null));
				subscribeAccessory.setAccessoryEnquiryTechnologyList(AccessoryEnquiryTechnologyServiceImpl.getInstance().listAccessoryEnquiryTechnology(map, null));
				subscribeAccessory.setAccessoryEnquiryElseList(AccessoryEnquiryElseServiceImpl.getInstance().listAccessoryEnquiryElse(map, null));
				subscribeAccessoryList.add(subscribeAccessory);
				// WlInfo wlInfo = new WlInfo();
				// wlInfo.setIntentionCode(committeeApply.getIntentionCode());
				// wlInfo.setIntentionName(committeeApply.getIntentionName());
				// wlInfo.setSupplierCode(committeeApply.getSupplierCode() ==
				// null ? committeeApply.getIntentionSupplierCode() :
				// committeeApply.getSupplierCode());
				// wlInfo.setSupplierName(committeeApply.getSupplierName() ==
				// null ? committeeApply.getIntentionSupplierName() :
				// committeeApply.getSupplierName());
				// wlInfo.setQuotedCode(committeeApply.getQuotedCode());
				// wlInfoList.add(wlInfo);
			}
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("applicationCode", applicationCodeNow);
			// wlInfoList = wlInfoService.listWlInfo(map, null);
			if (!StringUtils.isNotBlank(quotedCodes)) {
				quotedCodes = "";
				List<ApplicationQuoted> applicationQuotedList = applicationQuotedService.listApplicationQuoted(map, null);
				for (ApplicationQuoted applicationQuoted : applicationQuotedList) {
					quotedCodes = quotedCodes + applicationQuoted.getQuotedCode() + ",";
				}
			}
			applicationReportAccessory = applicationReportAccessoryService.loadApplicationReportAccessory(applicationCodeNow);
			subscribeAccessoryList = subscribeAccessoryService.listSubscribeAccessory(map, null);
			if (subscribeAccessoryList != null && subscribeAccessoryList.size() > 0) {
				for (SubscribeAccessory subscribeAccessory : subscribeAccessoryList) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("enquiryCode", subscribeAccessory.getEnquiryCode());
					List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList = accessoryEnquiryQuotedCountService.listAccessoryEnquiryQuotedCount(map1, null);
					subscribeAccessory.setAccessoryEnquiryQuotedCountList(accessoryEnquiryQuotedCountList);
					subscribeAccessory.setAccessoryEnquiryPackingList(AccessoryEnquiryPackingServiceImpl.getInstance().listAccessoryEnquiryPacking(map1, null));
					subscribeAccessory.setAccessoryEnquiryAccessoryList(AccessoryEnquiryAccessoryServiceImpl.getInstance().listAccessoryEnquiryAccessory(map1, null));
					subscribeAccessory.setAccessoryEnquiryMaterialList(AccessoryEnquiryMaterialServiceImpl.getInstance().listAccessoryEnquiryMaterial(map1, null));
					subscribeAccessory.setAccessoryEnquiryTechnologyList(AccessoryEnquiryTechnologyServiceImpl.getInstance().listAccessoryEnquiryTechnology(map1, null));
					subscribeAccessory.setAccessoryEnquiryElseList(AccessoryEnquiryElseServiceImpl.getInstance().listAccessoryEnquiryElse(map1, null));
				}
			} else {
				String qcs[] = quotedCodes.split(",");
				for (int i = 0; i < qcs.length; i++) {
					CommitteeApply committeeApply = new CommitteeApply();
					SubscribeAccessory subscribeAccessory = new SubscribeAccessory();
					committeeApply = committeeApplyService.loadCommitteeApplyIntentionApplication(qcs[i]);
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("enquiryCode", committeeApply.getEnquiryCode());
					List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList = accessoryEnquiryQuotedCountService.listAccessoryEnquiryQuotedCount(map1, null);
					subscribeAccessory.setAccessoryEnquiryQuotedCountList(accessoryEnquiryQuotedCountList);
					subscribeAccessory.setAccessoryName(committeeApply.getIntentionName());
					subscribeAccessory.setIntentionCode(committeeApply.getIntentionCode());
					subscribeAccessory.setEnquiryCode(committeeApply.getEnquiryCode());
					subscribeAccessory.setAccessoryEnquiryQuotedCountList(accessoryEnquiryQuotedCountList);
					subscribeAccessory.setAccessoryEnquiryPackingList(AccessoryEnquiryPackingServiceImpl.getInstance().listAccessoryEnquiryPacking(map1, null));
					subscribeAccessory.setAccessoryEnquiryAccessoryList(AccessoryEnquiryAccessoryServiceImpl.getInstance().listAccessoryEnquiryAccessory(map1, null));
					subscribeAccessory.setAccessoryEnquiryMaterialList(AccessoryEnquiryMaterialServiceImpl.getInstance().listAccessoryEnquiryMaterial(map1, null));
					subscribeAccessory.setAccessoryEnquiryTechnologyList(AccessoryEnquiryTechnologyServiceImpl.getInstance().listAccessoryEnquiryTechnology(map1, null));
					subscribeAccessory.setAccessoryEnquiryElseList(AccessoryEnquiryElseServiceImpl.getInstance().listAccessoryEnquiryElse(map1, null));
					subscribeAccessoryList.add(subscribeAccessory);
					// WlInfo wlInfo = new WlInfo();
					// wlInfo.setIntentionCode(committeeApply.getIntentionCode());
					// wlInfo.setIntentionName(committeeApply.getIntentionName());
					// wlInfo.setSupplierCode(committeeApply.getSupplierCode()
					// == null ? committeeApply.getIntentionSupplierCode() :
					// committeeApply.getSupplierCode());
					// wlInfo.setSupplierName(committeeApply.getSupplierName()
					// == null ? committeeApply.getIntentionSupplierName() :
					// committeeApply.getSupplierName());
					// wlInfo.setQuotedCode(committeeApply.getQuotedCode());
					// wlInfoList.add(wlInfo);
				}
			}
			// if (wlInfoList == null) {
			// wlInfoList = new ArrayList<WlInfo>();
			// String qcs[] = quotedCodes.split(",");
			// for (int i = 0; i < qcs.length; i++) {
			// CommitteeApply committeeApply =
			// committeeApplyService.loadCommitteeApplyIntentionApplication(qcs[i]);
			// WlInfo wlInfo = new WlInfo();
			// wlInfo.setIntentionCode(committeeApply.getIntentionCode());
			// wlInfo.setIntentionName(committeeApply.getIntentionName());
			// wlInfo.setSupplierCode(committeeApply.getSupplierCode() == null ?
			// committeeApply.getIntentionSupplierCode() :
			// committeeApply.getSupplierCode());
			// wlInfo.setSupplierName(committeeApply.getSupplierName() == null ?
			// committeeApply.getIntentionSupplierName() :
			// committeeApply.getSupplierName());
			// wlInfo.setQuotedCode(committeeApply.getQuotedCode());
			// Map<String, Object> map2 = new HashMap<String, Object>();
			// map2.put("enquiryCode", committeeApply.getEnquiryCode());
			// List<SubscribeAccessory> subscribeAccessory =
			// subscribeAccessoryService.listSubscribeAccessory(map2, null);
			// String enquiryCount = "";
			// if (subscribeAccessory != null && subscribeAccessory.size() > 0)
			// enquiryCount = subscribeAccessory.get(0).getEnquiryCount();
			// wlInfo.setEnquiryCount(enquiryCount);
			// wlInfoList.add(wlInfo);
			// }
			// }
		}
		if (applicationReportAccessory != null) {
			applicationReportAccessory.setResponsiblePerson(PowerUtils.getCurrentUser().getRealName());
		} else {
			applicationReportAccessory = new ApplicationReportAccessory();
			applicationReportAccessory.setResponsiblePerson(PowerUtils.getCurrentUser().getRealName());
			applicationReportAccessory.setDepartments("辅料采购部");
		}
		this.removeDuplicate(subscribeAccessoryList);
		this.putObject("applicationReportAccessory", applicationReportAccessory);
		this.putObject("subscribeAccessoryList", subscribeAccessoryList);
		// this.putObject("wlInfoList", wlInfoList);
		this.putObject("subscribeAccessoryCount", subscribeAccessoryList.size());
		this.putObject("applicationCodeNow", applicationCodeNow);
		this.putObject("quotedCodes", quotedCodes);
		this.forwardPage("sco/accessoryOaApplication/committeeApply/addCommitteeApply.ftl");
	}

	/**
	 * 采购委员会竞价单OA申请意向品列表
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doListCommitteeApplyIntentionApplication() throws Exception {
		Map<String, Object> map = getCommitteeApply().toMap();
		String supplierNo = this.asString("intentionSupplierCode");
		String supplierName = this.asString("intentionSupplierName");
		map.put("search", this.asString("search"));
		map.put("intentionSupplierCode", supplierNo);
		map.put("intentionSupplierName", supplierName);
		map.put("supplierCode", supplierNo);
		map.put("supplierName", supplierName);
		List<CommitteeApply> list = committeeApplyService.listCommitteeApplyIntentionApplication(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 采购委员会竞价单OA物料信息列表
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doListWlInfo() throws Exception {
		List<WlInfo> list = new ArrayList<WlInfo>();
		String applicationCodeNow = this.asString("applicationCodeNow");
		String quotedCodes = this.asString("quotedCodes");
		String qcs[] = quotedCodes.split(",");
		// 是否有申请单号
		ApplicationQuoted applicationQuoted = applicationQuotedService.loadApplicationQuoted(qcs[0]);
		if (applicationQuoted != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("applicationCode", applicationCodeNow);
			list = wlInfoService.listWlInfo(map, null);
			for (int i = 0; i < qcs.length; i++) {
				CommitteeApply committeeApply = committeeApplyService.loadCommitteeApplyIntentionApplication(qcs[i]);
				WlInfo wlInfo = new WlInfo();
				wlInfo.setIntentionCode(committeeApply.getIntentionCode());
				wlInfo.setIntentionName(committeeApply.getIntentionName());
				wlInfo.setSupplierCode(committeeApply.getSupplierCode() == null ? committeeApply.getIntentionSupplierCode() : committeeApply.getSupplierCode());
				wlInfo.setSupplierName(committeeApply.getSupplierName() == null ? committeeApply.getIntentionSupplierName() : committeeApply.getSupplierName());
				wlInfo.setQuotedCode(committeeApply.getQuotedCode());
				wlInfo.setEnquiryCode(committeeApply.getEnquiryCode());
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("enquiryCode", committeeApply.getEnquiryCode());
				List<SubscribeAccessory> subscribeAccessory = subscribeAccessoryService.listSubscribeAccessory(map2, null);
				String enquiryCount = "";
				if (subscribeAccessory != null && subscribeAccessory.size() > 0)
					enquiryCount = subscribeAccessory.get(0).getEnquiryCount();
				wlInfo.setEnquiryCount(enquiryCount);
				list.add(wlInfo);
			}
			if (list != null && list.size() > 1) {
				for (int i = 0; i < list.size(); i++) // 外循环是循环的次数
				{
					for (int j = list.size() - 1; j > i; j--) // 内循环是 外循环一次比较的次数
					{

						if (list.get(i).getQuotedCode().equals(list.get(j).getQuotedCode())) {
							list.remove(j);
						}

					}
				}
			}
		} else {
			for (int i = 0; i < qcs.length; i++) {
				CommitteeApply committeeApply = new CommitteeApply();
				committeeApply = committeeApplyService.loadCommitteeApplyIntentionApplication(qcs[i]);
				WlInfo wlInfo = new WlInfo();
				wlInfo.setIntentionCode(committeeApply.getIntentionCode());
				wlInfo.setIntentionName(committeeApply.getIntentionName());
				wlInfo.setSupplierCode(committeeApply.getSupplierCode() == null ? committeeApply.getIntentionSupplierCode() : committeeApply.getSupplierCode());
				wlInfo.setSupplierName(committeeApply.getSupplierName() == null ? committeeApply.getIntentionSupplierName() : committeeApply.getSupplierName());
				wlInfo.setQuotedCode(committeeApply.getQuotedCode());

				list.add(wlInfo);
			}
		}
		this.forwardData(true, list, null);
	}

	/**
	 * 采购委员会竞价单OA申请意向品列表
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doLoadCommitteeApplyIntentionApplication() throws Exception {
		CommitteeApply committeeApply = committeeApplyService.loadCommitteeApplyIntentionApplication(asString("quotedCode"));
		this.forwardData(true, committeeApply, null);
	}

	/**
	 * 采购委员会申请报告
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doInsertApplicationReportAccessoryAndSubscribeAccessory() throws Exception {
		String applicationCodeNow = asString("applicationCodeNow");
		String quotedCodes = asString("quotedCodes");
		String html = asString("data");
		System.out.println(html);
		String applicationType = BusinessConstants.ApplicationType.ACCESSORY_CGWYHJJD.toString();

		if (committeeApplyService.committeeInsertUpdateDeleteIsOk(quotedCodes, applicationCodeNow, applicationType)) {

			try {
				ApplicationReportAccessory applicationReportAccessory = getApplicationReportAccessory();
				applicationReportAccessory.setApplicationCode(applicationCodeNow);
				List<SubscribeAccessory> subscribeAccessoryList = new ArrayList<SubscribeAccessory>();
				subscribeAccessoryList = this.subscribeAccessoryTextList;
				for (SubscribeAccessory subscribeAccessory : subscribeAccessoryList) {
					subscribeAccessory.setApplicationCode(applicationCodeNow);
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("enquiryCode", subscribeAccessory.getEnquiryCode());
					List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList = accessoryEnquiryQuotedCountService.listAccessoryEnquiryQuotedCount(map1, null);
					subscribeAccessory.setAccessoryEnquiryQuotedCountList(accessoryEnquiryQuotedCountList);
					subscribeAccessory.setAccessoryEnquiryPackingList(AccessoryEnquiryPackingServiceImpl.getInstance().listAccessoryEnquiryPacking(map1, null));
					subscribeAccessory.setAccessoryEnquiryAccessoryList(AccessoryEnquiryAccessoryServiceImpl.getInstance().listAccessoryEnquiryAccessory(map1, null));
					subscribeAccessory.setAccessoryEnquiryMaterialList(AccessoryEnquiryMaterialServiceImpl.getInstance().listAccessoryEnquiryMaterial(map1, null));
					subscribeAccessory.setAccessoryEnquiryTechnologyList(AccessoryEnquiryTechnologyServiceImpl.getInstance().listAccessoryEnquiryTechnology(map1, null));
					subscribeAccessory.setAccessoryEnquiryElseList(AccessoryEnquiryElseServiceImpl.getInstance().listAccessoryEnquiryElse(map1, null));
				}
				committeeApplyService.insertApplicationReportAccessoryAndSubscribeAccessory(applicationReportAccessory, subscribeAccessoryList);
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("applicationReportAccessory", applicationReportAccessory);
				paraMap.put("subscribeAccessoryList", subscribeAccessoryList);
				String fileName = applicationCodeNow;
				String msg = this.saveToHtml(fileName, paraMap);
				if (!StringUtils.isBlank(msg)) {
					this.forwardData(false, null, "报表保存失败");
					return;
				}
			} catch (Exception e) {
				this.forwardData(false, null, "保存失败，请重试");
				return;
			}

		} else {
			this.forwardData(false, null, "当前操作不正确，报价单在其他申请单中或者当前申请单状态下不可修改");
			return;
		}
		this.forwardData(true, null, "保存成功");
	}

	public String saveToHtml(String fileName, Map<String, Object> paraMap) {
		String tarPath;
		try {
			tarPath = FreeMarkerUtil.generateHtml("committee/applicationReportAccessory.ftl", "committee".concat("/").concat(fileName), paraMap);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			ApplicationReportAccessory applicationReportAccessory = (ApplicationReportAccessory) paraMap.get("applicationReportAccessory");
			applicationReportAccessory.setPath(tarPath.replace(ConfigPath.getUploadFilePath(), ""));
			ApplicationReportAccessoryServiceImpl.getInstance().updateApplicationReportAccessory(applicationReportAccessory);
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	/**
	 * 大货信息列表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListDhInfo() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", asString("applicationCodeNow"));
		List<DhInfo> list = dhInfoService.listDhInfo(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 报价单列表 (大货)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListApplicationQuotedForDhInfo() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", asString("applicationCodeNow"));
		List<ApplicationQuoted> list = applicationQuotedService.listApplicationQuotedForDhInfo(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示上传大货信息文件
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowInsertDhFile() throws Exception {
		String intentionCode = this.asString("intentionCode");// 意向品编号
		String supplierCode = this.asString("supplierCode");
		String applicationCode = this.asString("applicationCode");
		this.putObject("intentionCode", intentionCode);
		this.putObject("supplierCode", supplierCode);
		this.putObject("applicationCode", applicationCode);
		this.forwardPage("sco/accessoryOaApplication/committeeApply/dhInfoForm.ftl");
	}

	/**
	 * 保存大货信息
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doInsertDhInfo() throws Exception {
		DhInfo dhInfo = new DhInfo();
		List<File> fList = null;
		dhInfo = getDhInfo();
		dhInfo.setId(SequenceFactory.getInstance().nextID("DH_INFO"));
		if ("其他".equals(dhInfo.getFileType()))
			dhInfo.setFileType(dhInfo.getFileTypeOther());
		String oaDhFilePath = PathUtils.getOaDhFilePath();
		if (this.getUploads() != null && this.getUploads()[0] != null) {
			fList = this.doUploadBySaveDir(oaDhFilePath);
		}
		if (fList != null) {
			String path = oaDhFilePath.replaceAll(ConfigPath.getUploadFilePath(), "").concat(fList.get(0).getName());// 文件路径
			dhInfo.setPath(path);
			dhInfo.setFileName(fList.get(0).getName());
		}
		dhInfoService.insertDhInfo(dhInfo);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 删除大货信息
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDeleteDhInfo() throws Exception {
		String dhId = this.asString("dhId");
		if (dhId != null) {
			String dhinfoId[] = dhId.split(",");
			committeeApplyService.deleteDhInfo(dhinfoId);
			this.forwardData(true, null, this.getText("public.success"));
		}
	}

	/**
	 * 下载大货文件
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDownloadDhFile() {
		String path = "upload/".concat(this.asString("path"));
		this.forwardDownload(path);
	}

	/**
	 * 删除OA申请
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDeleteApplication() {
		String applicationCode = this.asString("applicationCode");
		if (StringUtils.isNotBlank(applicationCode)) {
			String applicationCodes[] = applicationCode.split(",");
			/*
			 * String applicationCodeIn=""; for(String code:applicationCodes){ applicationCodeIn=applicationCodeIn+code+","; }
			 */
			// applicationCodeIn=applicationCodeIn.substring(0,applicationCodeIn.length()-2);
			// Map<String,Object> map=new HashMap<String,Object>();
			// map.put("applicationCodeIn", applicationCodeIn);
			committeeApplyService.deleteApplication(applicationCodes);

		}
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 关闭OA申请
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doCloseApplication() {
		String applicationCode = this.asString("applicationCode");
		if (StringUtils.isNotBlank(applicationCode)) {
			String applicationCodes[] = applicationCode.split(",");
			committeeApplyService.closeApplication(applicationCodes);

		}
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 撤销允许OA同步
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doUndoOaSynchronous() {
		String applicationCode = this.asString("applicationCode");
		if (StringUtils.isNotBlank(applicationCode)) {
			String applicationCodes[] = applicationCode.split(",");
			committeeApplyService.undoOaSynchronous(applicationCodes);

		}
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 允许OA同步
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doAllowOaSynchronous() {
		String check = "";
		String applicationCode = this.asString("applicationCode");
		check = committeeApplyService.checkApplication(applicationCode);
		if (StringUtils.isNotBlank(applicationCode) && "".equals(check)) {
			committeeApplyService.allowOaSynchronous(applicationCode);
			this.forwardData(true, null, this.getText("public.success"));
		} else {
			this.forwardData(false, null, check);
		}

	}

	/**
	 * 保存物料信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doInsertWlInfo() throws Exception {
		String rows = this.asString("rows");
		/*
		 * String applicationCodeNow = asString("applicationCodeNow"); String quotedCodes = asString("quotedCodes"); String applicationType = BusinessConstants.ApplicationType.ACCESSORY_CGWYHJJD.toString(); if (committeeApplyService.committeeInsertUpdateDeleteIsOk(quotedCodes, applicationCodeNow, applicationType)) {
		 */
		try {
			if (!StringUtils.isBlank(rows)) {
				JSONArray jsonArr = JSONArray.fromObject(rows);
				WlInfo[] dataArray = new WlInfo[jsonArr.size()];
				for (int j = 0; j < jsonArr.size(); j++) {
					dataArray[j] = (WlInfo) JSONObject.toBean(jsonArr.getJSONObject(j), WlInfo.class);
				}
				this.committeeApplyService.insertWlInfo(dataArray);
			}
		} catch (Exception e) {
			this.forwardData(false, null, "保存失败，请重试");
			return;
		}
		/*
		 * }else{ this.forwardData(false, null, "当前操作不正确，报价单在其他申请单中或者当前申请单状态下不可修改"); return; }
		 */
		this.forwardData(true, null, "保存成功");
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private CommitteeApply getCommitteeApply() throws Exception {
		CommitteeApply committeeApply = new CommitteeApply();
		this.asBean(committeeApply);
		return committeeApply;
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private ApplicationReportAccessory getApplicationReportAccessory() throws Exception {
		ApplicationReportAccessory applicationReportAccessory = new ApplicationReportAccessory();
		this.asBean(applicationReportAccessory);
		return applicationReportAccessory;
	}

	private DhInfo getDhInfo() throws Exception {
		DhInfo dhInfo = new DhInfo();
		this.asBean(dhInfo);
		return dhInfo;
	}

	// 获取OA申请单的主键
	private String getOaApplicationPrimaryKey() {
		return "OA".concat(MasterDataTypeServiceImpl.getInstance().nextID("S_OA_APPLICATION"));
	}

	public List<SubscribeAccessory> getSubscribeAccessoryTextList() {
		return subscribeAccessoryTextList;
	}

	public void setSubscribeAccessoryTextList(List<SubscribeAccessory> subscribeAccessoryTextList) {
		this.subscribeAccessoryTextList = subscribeAccessoryTextList;
	}

	public void removeDuplicate(List<SubscribeAccessory> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).getEnquiryCode().equals(list.get(i).getEnquiryCode())) {
					list.remove(j);
				}
			}
		}
	}

	/*
	 * 查看采购委员会竞价OA申请审批意见
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doLookApproveOpinion() throws Exception {
		String applicationCode = this.asString("applicationCode");
		OaApplication oaApplication = oaApplicationService.loadOaApplication(applicationCode);
		if (oaApplication != null && StringUtils.isNotBlank(oaApplication.getOaApplicationCode()) ) {
			this.forwardData(true, oaApplication.getOaApplicationCode() + ":" + oaApplication.getOaContacts(), this.getText("public.success"));
		} else {
			this.forwardData(false, null, "没有OA系统同步数据，不能查看OA审批意见!");
		}
	}

	/**
	 * 判断是否通过审批
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doIfApproved() throws Exception {
		OaApplication oa = OaApplicationServiceImpl.getInstance().loadOaApplication(asString("applicationCode"));
		boolean flag = (oa == null ? false : (BusinessConstants.ApplicationStatus.SPTG.toString().equals(oa.getApplicationStatus())));
		if (flag) {
			this.forwardData(flag, null, null);
			return;
		} else {
			this.forwardData(flag, null, "该申请单未通过OA审批，不能操作!");
		}
	}
	
	/**
	 * 下载报表文件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDownloadOriginalFile() throws Exception {
		String address = this.asString("path");
		String reportsName = this.asString("reportsName");
		//下载申购单时需要使用用户填写的名称
		String fileName = new File(address).getName();
		if (StringUtils.isNotBlank(reportsName)) {
			String ext = StrUtils.getStrAftLastSplit(fileName, ".");
			fileName = reportsName;
			if (StringUtils.isNotBlank(ext)) { //如果没有后缀名
				fileName += "." + ext;
			}
		} else {
			fileName = ExcelUtils.getEncodeFileName(StrUtils
					.getFileResourceName(fileName));
		}
		this.forwardDownload("upload/" + address, fileName);
	}
}
