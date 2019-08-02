package com.powere2e.sco.action.merchandiseoaapplication.fastadjustpriceoaapplication;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.common.MasterDataTypeService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.MerchandiseOaApplicationService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceService;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustprice;

/**
 * 申请报告(快速调价)的WEB请求响应类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationFastadjustpriceAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6474249089032459674L;
	private ApplicationFastadjustpriceService fastadjustpriceService;
	private MerchandiseOaApplicationService merchandiseOaApplicationService;
	private MasterDataTypeService masterDataTypeService;// 获取主键id的service

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		fastadjustpriceService = (ApplicationFastadjustpriceService) ConfigFactory.getInstance().getBean("fastAdjustpriceService");
		merchandiseOaApplicationService = (MerchandiseOaApplicationService) ConfigFactory.getInstance().getBean("merchandiseOaApplicationService");
		masterDataTypeService = (MasterDataTypeService) ConfigFactory.getInstance().getBean("masterDataTypeService");
	}

	/**
	 * 显示快速调价页面
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doShowApplicationFastadjustpriceGrid() throws Exception {
		this.forwardPage("sco/merchandiseOaApplication/fastAdjustpriceOaApplication/applicationFastadjustpriceGrid.ftl");
	}

	/**
	 * 申请报告(快速调价)列表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListMerchandiseApplicationFastadjustprice() throws Exception {
		Map<String, Object> map = getMerchandise().toMap();
		String applicationDateStart = this.asString("applicationDateStart");
		String applicationDateEnd = this.asString("applicationDateEnd");

		map.put("applicationDateStart", applicationDateStart);
		map.put("applicationDateEnd", applicationDateEnd);

		map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_FASTADJUSTPRICE.toString());// 设置只查询快速调价类型的申请列表
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
	public void doShowInsertApplicationFastadjustpriceForm() throws Exception {
		// 页面OA传递申请单号
		String applicationCode = "OA".concat(masterDataTypeService.nextID("S_OA_APPLICATION"));
		// 向页面传递所选择的意向品编号和供应商编号组
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		this.putObject("applicationCode", applicationCode);
		this.putObject("intentionAndSupplierCodes", intentionAndSupplierCodes);
		this.forwardPage("sco/merchandiseOaApplication/fastAdjustpriceOaApplication/fastAdjustpricePanel.ftl");
	}

	/**
	 * 修改申请报告(快速调价)界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
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
		this.forwardPage("sco/merchandiseOaApplication/fastAdjustpriceOaApplication/fastAdjustpricePanel.ftl");

	}

	/**
	 * 删除申请报告(快速调价)
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doDeleteApplicationFastadjustprice() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCodes", this.asString("applicationCodes"));
		fastadjustpriceService.completeDeleteFastadjustpriceByApplicationCode(map);

		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 导出列表页申请记录
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doExportReportFastadjustpriceApplicationToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = ("商品快速调价OA申请列表_").concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		
		Map<String, Object> map = getMerchandise().toMap();
		String applicationDateStart = this.asString("applicationDateStart");
		String applicationDateEnd = this.asString("applicationDateEnd");

		map.put("applicationDateStart", applicationDateStart);
		map.put("applicationDateEnd", applicationDateEnd);

		map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_FASTADJUSTPRICE.toString());// 设置只查询快速调价类型的申请列表
		fastadjustpriceService.exportSignedQtyExcel(map, out);
		/*List<MerchandiseIntention> list = merchandiseOaApplicationService.queryMerchandiseApplicationList(map, null);
		this.putObject("applicationList", list);
		this.forwardDownload("excel/sco/merchandiseoaapplication/fastAdjustpriceOaApplicationTemplate.xlsx", ExcelUtils.getEncodeFileName("商品快速调价OA申请列表_" + DateUtils.formateDateTime() + ".xlsx"));*/
	}

	/**
	 * 申请文件的商品
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListApplicationFileMerchandise() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		map.put("list", codeList);

		List<ApplicationFastadjustprice> list = fastadjustpriceService.listApplicationFileMerchandise(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 上传申请文件
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doUploadApplicationAttachment() throws Exception {
		String oaFastadjustpriceFilePath = PathUtils.getOaFastadjustpriceFilePath();
		List<File> fileList = this.doUploadBySaveDir(oaFastadjustpriceFilePath.concat("/"));
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		
		//先判断是否可以操作
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil
				.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_FASTADJUSTPRICE.toString());// 申请类型
		List<ApplicationMerchandise> appList = merchandiseOaApplicationService.listIntentionOaApplication(resultMap);
		if (appList != null && appList.size() > 0) {
			for (ApplicationMerchandise merchandise : appList) {
				if (!BusinessConstants.MerchandiseApplicationStatus.CG.toString().equals(
						merchandise.getApplicationStatus())
						&& !BusinessConstants.MerchandiseApplicationStatus.BH.toString().equals(
								merchandise.getApplicationStatus())) {
					//如果有一条记录的状态不是"草稿"或"驳回"，就不能点击保存按钮
					this.forwardData(false, null, "选择的数据不是“草稿”或”驳回“的状态,无法修改!");
				}
			}
		}
		
		if (!fileList.isEmpty()) {
			File file = fileList.get(0);
			String reportFileName = StrUtils.getFileResourceName(file.getName());
			String path = oaFastadjustpriceFilePath.replaceAll(ConfigPath.getUploadFilePath(), "").concat(file.getName());// 文件路径
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("applicationCode", applicationCode);
			map.put("intentionAndSupplierCodes", intentionAndSupplierCodes);
			map.put("merchandiseCode", this.asString("merchandiseCode"));
			map.put("supplierCode", this.asString("intentionSupplierCode"));
			map.put("path", path);
			map.put("reportFileName", reportFileName);
			try {
				String receiptInfo = fastadjustpriceService.completeInsertOrUpdateFastadjustprice(file, map);
				if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString().equals(receiptInfo)
						|| MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
					this.forwardData(true, null, this.getText("public.success"));
				} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER.toString().equals(receiptInfo)) {
					this.forwardData(false, null, "选择的数据中包含已经OA申请的数据,不能重复申请!");
				} else {
					this.forwardData(false, null, "选择的数据不是“草稿”或”驳回“的状态,无法修改!");
				}
			} catch (Exception e) {
				// TODO，上传删除的文件
			}
		}
	}

	/**
	 * 申请文件列表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doListApplicationFiles() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		map.put("applicationCode", applicationCode);
		map.put("list", codeList);

		List<ApplicationFastadjustprice> list = fastadjustpriceService.listApplicationFiles(map, this.getPageInfo());
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
	public void doSyncApplicationFastadjustprice() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String applicationCode = this.asString("applicationCode");
		map.put("applicationCode", applicationCode);

		// 判断是否缺少必填文件
		List<ApplicationLackFileM> list = fastadjustpriceService.queryNotHaveReportMerchandiseFastadjustprice(map);
		if (list == null || list.size() == 0) {
			// 缺少必填文件，就不同步OA申请单
			StringBuffer check = new StringBuffer();
		/*	check.append("所选申请单[商品编号-供应商编号:<br>");
			for (ApplicationLackFileM reportNew : list) {
				check.append(reportNew.getMerchandiseCode() 
					.concat("-").concat(reportNew.getSupplierCode()))
					.append(";<br>");
			}*/
			check.append("缺少必填文件申请文件，不能新增创建OA快速调价申请!");
			this.forwardData(false, check.toString(), "缺少必填文件");
		} else {
//			map.put("applicationStatus", BusinessConstants.ApplicationStatus.YX.toString());
//			merchandiseOaApplicationService.updateOaApplicationStatus(map);

			this.forwardData(true, null, this.getText("public.success"));
		}
	}

	/**
	 * 撤销允许OA同步
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doUndoApplicationFastadjustprice() throws Exception {
		String applicationCodes = this.asString("applicationCodes");
		merchandiseOaApplicationService.completeUndoOaApplicationStatus(applicationCodes);

		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 删除申请文件(快速调价),需要同时删除服务器文件和本地申请报告
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doDeleteApplicationFiles() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String applicationCode=this.asString("applicationCode");
		String reportCodes=this.asString("reportCodes");
		String intentionAndSupplierCodes=this.asString("intentionAndSupplierCodes");
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		map.put("applicationCode", applicationCode);
		map.put("reportCodes", reportCodes);
		map.put("list", codeList);
		//根据参数去查询所有上传的文件信息，然后根据上传的路径，删除服务器的文件
		List<ApplicationFastadjustprice> list = fastadjustpriceService.listApplicationFiles(map, null);

		map.clear();
		map.put("reportCodes", reportCodes);
		map.put("applicationCode", applicationCode);
		map.put("intentionAndSupplierCodes", intentionAndSupplierCodes);
		String receiptInfo = fastadjustpriceService.deleteApplicationFiles(map);
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString().equals(receiptInfo)) {
			//删除服务器文件
			if (list != null && list.size() > 0) {
				for(ApplicationFastadjustprice applicationFile:list){
					this.deleteFile(applicationFile.getPath());
				}
			}
			this.forwardData(true, null, this.getText("public.success"));
		} else {
			this.forwardData(false, null, "选择的数据不是“草稿”或”驳回“的状态,不能删除!");
		}
	}
	
	/**
	 * 下载供应商附件上传文件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doDownloadOriginalFile() throws Exception {
		String address = this.asString("path");
		File f = new File(address);
		this.forwardDownload("upload/" + this.asString("path"), ExcelUtils
				.getEncodeFileName(StrUtils.getFileResourceName(f.getName())));

	}
}
