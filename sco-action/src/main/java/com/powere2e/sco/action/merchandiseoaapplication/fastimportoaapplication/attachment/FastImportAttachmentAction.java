package com.powere2e.sco.action.merchandiseoaapplication.fastimportoaapplication.attachment;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastimportoaapplication.ApplicationFastImportService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastimportoaapplication.attachment.FastImportAttachmentService;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;
import com.powere2e.sco.service.impl.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMServiceImpl;

/**
 * 快速引入申请报告 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月15日
 * @version 1.0
 */
public class FastImportAttachmentAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6474249089032459674L;
	private ApplicationFastImportService fastImportApplicationService;// 商品快速引进主模块
	private FastImportAttachmentService fastImportAttachmentService;// 申请文件
	
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		fastImportApplicationService = (ApplicationFastImportService) ConfigFactory
				.getInstance().getBean("fastImportApplicationService");
		fastImportAttachmentService = (FastImportAttachmentService) ConfigFactory
				.getInstance().getBean("fastImportAttachmentService");
	}

	/**
	 * 本次选择的意向品及供应商列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doListIntentionSupplier() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> applicationMerchandises = MerchandiseOaApplicationUtil
				.getIntentionAndSupplierCodeGroupList(this
						.asString("intentionAndSupplierCodes"));
		map.put("list", applicationMerchandises);
		List<SupplierAttachmentM> list = SupplierAttachmentMServiceImpl
				.getInstance().listIntentionSupplier(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 上传申请文件
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doUploadApplicationAttachment() throws Exception {
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");

		String oaFastimportFilePath = PathUtils.getOaFastimportFilePath();
		List<File> fileList = this.doUploadBySaveDir(oaFastimportFilePath);
		if (!fileList.isEmpty()) {
			File file = fileList.get(0);
			//文件相对路径
			String path = oaFastimportFilePath.replaceAll(
					ConfigPath.getUploadFilePath(), "").concat(file.getName());
			//文件原始名称
			String attachmentName = StrUtils.getFileResourceName(file.getName());
			SupplierAttachmentM attch = this.getAttachmentMap(path, attachmentName);
			this.fastImportAttachmentService.completeUploadAttachment(applicationCode, 
					intentionAndSupplierCodes, attch);
		}
		this.forwardData(true, null, "文件上传成功!");
	}

	/**
	 * 申请文件列表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doListApplicationFiles() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String applicationCode = this.asString("applicationCode");
		String intentionAndSupplierCodes = this
				.asString("intentionAndSupplierCodes");
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil
				.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		map.put("applicationCode", applicationCode);
		map.put("list", codeList);

		List<ApplicationFastadjustprice> list = ApplicationFastadjustpriceServiceImpl
				.getInstance().listApplicationFiles(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 删除申请文件(快速调价),需要同时删除服务器文件和本地申请报告
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doCompleteDeleteApplicationFiles() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		//校验状态
		String applicationCode = this.asString("applicationCode");
		fastImportApplicationService.validateApplicationStatus(applicationCode,
				BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT
						.toString(), intentionAndSupplierCodes);
		
		map.put("applicationCode", applicationCode);
		map.put("reportCodes", this.asString("reportCodes"));
		map.put("list", codeList);
		map.put("intentionAndSupplierCodes", intentionAndSupplierCodes);
		this.fastImportAttachmentService.completeDeleteApplicationFiles(map);
		this.forwardData(true, null, "删除成功!");
	}
	
	/**
	 * 下载供应商附件上传文件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.fastImport")
	public void doDownloadOriginalFile() throws Exception {
		String address = this.asString("path");
		File f = new File(address);
		this.forwardDownload("upload/" + this.asString("path"), ExcelUtils
				.getEncodeFileName(StrUtils.getFileResourceName(f.getName())));
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @param path
	 *            文件路径
	 * @param attachmentName
	 *            文件原始名称
	 * @return
	 * @throws Exception
	 */
	private SupplierAttachmentM getAttachmentMap(String path,
			String attachmentName) throws Exception {
		SupplierAttachmentM attachment = new SupplierAttachmentM();
		this.asBean(attachment);
		attachment.setPath(path);
		attachment.setAttachmentName(attachmentName);
		return attachment;
	}
}
